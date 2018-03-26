<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<portlet:renderURL var="renderURL">
    <portlet:param name="jspPage" value="/html/ospngl/nglViewer.jsp"/>
</portlet:renderURL>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = (String)renderRequest.getAttribute("connector");
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
%>






<div class="container-fluid osp-analyzer">
	<div class="row-fluid canvas">
		<iframe class ="col-sm-12 iframe" id="<portlet:namespace/>canvas"  src="<%=request.getContextPath()%>/html/ospngl/load_ospngl.jsp" width="100%" height="600px" style="border:0">
		</iframe>
	</div>
</div>

<div id="<portlet:namespace/>hiddenSection" class="osp-analyzer hidden">
	<div id="<portlet:namespace/>fileExplorer" class="panel panel-primary ui-draggable" style="padding:0px;margin-bottom:0px;">
		<!-- title -->
		<div class="panel-heading">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id='<portlet:namespace/>closeDialog'>&times;</button>
			<h4>Select a File</h4>
		</div>
		
		<!-- content -->
		<div class="panel-body" id="<portlet:namespace/>file-explorer-content" style="height: 81%"></div>

		
		<!-- bottom -->
		<div class="panel-footer">
			<div class="ui-dialog-buttonset">
				<input class="btn btn-primary" id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
				<input class="btn" id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
			</div>
		</div>	
		
	</div>
</div>

<script>


/***********************************************************************
 * Global variables section
 ***********************************************************************/
<portlet:namespace/>passNamespace();
var <portlet:namespace/>canvas = $('#<portlet:namespace/>canvas');
var <portlet:namespace/>connector = '<%=connector%>';
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');


var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_nl";

if( "<portlet:namespace/>".lastIndexOf("_INSTANCE_") > 0)
	<portlet:namespace/>fileExplorerId += "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
else
	<portlet:namespace/>fileExplorerId += '001'; 

var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');

if(<portlet:namespace/>eventEnable === false){
	<portlet:namespace/>initialize( JSON.parse('<%=inputData%>') );
	<portlet:namespace/>loadNGLFile( <portlet:namespace/>initData.clone() );
	<portlet:namespace/>initializeFileExplorer();
}

/***********************************************************************
 * Initailization section using parameters
***********************************************************************/
// for test
// <portlet:namespace/>eventEnable = false;

function <portlet:namespace/>passNamespace(){
	setTimeout(
			function(){
			    var iframe = document.getElementById('<portlet:namespace/>canvas');
				if ( <portlet:namespace/>iframeReady() && iframe.contentWindow.setNamespace) {
					iframe.contentWindow.setNamespace('<portlet:namespace/>');
				} 
				else{
					<portlet:namespace/>passNamespace();
				}
			}, 
			10
	);
}

function <portlet:namespace/>iframeReady(){
	var iframe = document.getElementById('<portlet:namespace/>canvas');
	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	if ( iframeDoc.readyState  == 'complete' ) {
		return true;
	} 
	else{
		return false;
	}
}

$("#<portlet:namespace/>fileExplorer").dialog({
	autoOpen: false,
	resizable: false,
	height: 600,
	width: 450,
	modal: true,
	show: {effect:'fade', speed: 800}, 
    hide: {effect:'fade', speed: 800}
});
$(".ui-dialog-titlebar").remove();


$("#<portlet:namespace/>closeDialog").click(function() {
	$("#<portlet:namespace/>fileExplorer").dialog("close");
});


/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************

$('#<portlet:namespace/>download').click(function(){
	console.log("[NGLViewer] download Request.");
	<portlet:namespace/>downloadCurrentFile();
});
********/
$("#<portlet:namespace/>file-explorer-ok").click(function(e){
	e.preventDefault();
	var eventData = {
		portletId : '<%=portletDisplay.getId()%>',
		targetPortlet : <portlet:namespace/>fileExplorerId,
	};
	Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});


function <portlet:namespace/>openFileExplorer(){
	AUI().use('liferay-portlet-url', function(A){
		console.log('[NGLViewer] test open file explorer');
		if($("#<portlet:namespace/>file-explorer-content").children().length > 0){
			console.log('[NGLViewer] test open file explorer : open exist file explorer');
		
			$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}else{
			console.log('[NGLViewer] test open file explorer : create file explorer');	
		
			var inputData;
			if(	!$.isEmptyObject(<portlet:namespace/>initData) && (
				<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FILE ||
				<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER ||
				<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.EXT )){
				inputData = <portlet:namespace/>initData;
			}
			else{
				inputData = new OSP.InputData();
				inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
				inputData.type( OSP.Enumeration.PathType.FOLDER );
				inputData.parent('');
				inputData.name('');
			}
			
			var dialogURL = Liferay.PortletURL.createRenderURL();
			dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
			dialogURL.setParameter('inputData', JSON.stringify(inputData));
			dialogURL.setParameter('mode', 'VIEW');
			dialogURL.setParameter('eventEnable', false);
			dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
			dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
			
			$("#<portlet:namespace/>file-explorer-content").load( dialogURL.toString());
			$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}
	});
}


/***********************************************************************
 * Handling OSP Events
***********************************************************************/
Liferay.on(
  	OSP.Event.OSP_HANDSHAKE,
  	function(e){
  		var myId = '<%=portletDisplay.getId()%>';
  		if( e.targetPortlet === myId ){
  			console.log('[NGLViewer]OSP_HANDSHAKE: ['+e.portletId+', '+new Date()+']');
  			<portlet:namespace/>connector = e.portletId;
  			if( e.mode )
  				<portlet:namespace/>action = e.mode;
  			else
  				<portlet:namespace/>action = 'VIEW';	
  			var events = [
  				OSP.Event.OSP_EVENTS_REGISTERED,
  				OSP.Event.OSP_LOAD_DATA
  			];
  			var eventData = {
  				portletId: myId,
  				targetPortlet: <portlet:namespace/>connector,
  				data: events
  			};
  			
  			Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
  		}
  	}
);

Liferay.on(
	OSP.Event.OSP_EVENTS_REGISTERED,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if(e.targetPortlet === myId){
			console.log('[NGLViewer]Regestered'+e.portletId+' activated. '+new Date()+']');
		}
	}
);

Liferay.on( 
	OSP.Event.OSP_LOAD_DATA, 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet === myId ){
			console.log('[NGLViewer]OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']', e.data);
			//<portlet:namespace/>initData = new OSP.InputData( e.data );
			<portlet:namespace/>currentData = new OSP.InputData( e.data );
			if( <portlet:namespace/>currentData.type() === OSP.Enumeration.PathType.FOLDER ){
				<portlet:namespace/>currentData.parent(
					OSP.Util.mergePath(<portlet:namespace/>currentData.parent(), <portlet:namespace/>currentData.name()));
					//<portlet:namespace/>initData.name("");
					<portlet:namespace/>currentData.name("");
			}
			<portlet:namespace/>loadNGLFile( <portlet:namespace/>currentData );
		}
	}
);

Liferay.on(
           OSP.Event.OSP_RESPONSE_DATA,
           function( e ){
               if( e.targetPortlet === '<%=portletDisplay.getId()%>' ){
                   console.log('[NGLViewer] OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']');
                   if( e.portletId === <portlet:namespace/>fileExplorerId ){
                       var inputData = new OSP.InputData( e.data );
                       
                       if( inputData.type() !== OSP.Enumeration.PathType.FILE ){
                           alert( 'File should be selected!' );
                           return;
                       }
                       else{
                           <portlet:namespace/>loadNGLFile( inputData );
                           $<portlet:namespace/>fileExplorerDialogSection.dialog('close');
                       }
                   }
               }
           }
);


Liferay.on(
		OSP.Event.OSP_REFRESH_OUTPUT_VIEW,
		function(e){
			console.log('[NGLViewer]OSP_REFRESH_OUTPUT_VIEW: ['+e.portletId+', '+new Date()+']');
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: <portlet:namespace/>connector
			};

			Liferay.fire(OSP.Event.OSP_REQUEST_OUTPUT_PATH, eventData);
		}
);

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadNGLFile( inputData ){
	
	console.log("[NGLViewer] Load Data : input Data ", inputData);
	switch( inputData.type() ){
	case OSP.Enumeration.PathType.FILE:
	    <portlet:namespace/>drawNGL( inputData );
		break;
	case OSP.Enumeration.PathType.FOLDER:
	case OSP.Enumeration.PathType.EXT:
	    <portlet:namespace/>getFirstFileName( inputData );
	    // serveResourceUrl.setParameter('command', 'READ_FIRST_FILE');
		break;
	case OSP.Enumeration.PathType.URL:
		alert('Un supported yet.'+inputData.type());
		break;
	default:
		alert('Un supported yet.'+inputData.type());
	}
}



function <portlet:namespace/>getFirstFileName( inputData ){
	console.log('[NGLViewer]get First File Name : ', inputData );

	var data = {
		<portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
		<portlet:namespace/>pathType: inputData.type_,
		<portlet:namespace/>repositoryType: inputData.repositoryType_,
		<portlet:namespace/>parentPath: inputData.parent_,
		<portlet:namespace/>fileName: inputData.name_
	};
    console.log("[NGLViewer] laod get first file test : ", data);
    
	$.ajax({
		url: '<%= serveResourceURL.toString()%>',
		type: 'POST',
		data  : data,
		dataType : 'json',
		success: function(data) {
			console.log("[NGLViewer] get result data ", data);
			inputData.name( data.fileName );
			inputData.type( OSP.Enumeration.PathType.FILE );
			<portlet:namespace/>drawNGL( inputData );
			console.log("[NGLViewer] Get First File Data : ", inputData);
		},
		error:function(data,e){
			console.log('[NGLViewer]AJAX ERROR1-->', data);
			console.log('[NGLViewer]AJAX ERROR2-->', e);
		},
		complete: function( jqXHR, textStatus ){
			console.log('[NGLViewer]AJAX complete ', jqXHR);
		}
	});
}

function <portlet:namespace/>drawNGL( inputData ){
    setTimeout(
	    function(){
	    	var iframe = document.getElementById('<portlet:namespace/>canvas');
	    	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	    	console.log( '[NGLViewer]iframeDoc.readyState : ', iframeDoc.readyState);
	    	if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.drawNglViewer ) {
	    		
	    		console.log("[NGLViewer] test 1 : load data in drawNGL");
	    	    AUI().use('liferay-portlet-url', function(a) {
	                <portlet:namespace/>currentData = inputData.clone();
	                if( !<portlet:namespace/>currentData.repositoryType() )
	                	<portlet:namespace/>currentData.repositoryType('<%=OSPRepositoryTypes.USER_JOBS.toString()%>');

	    	        var serveResourceUrl = Liferay.PortletURL.createResourceURL();
	    	        
	    	        serveResourceUrl.setPortletId('<%=portletDisplay.getId()%>');
	    	        serveResourceUrl.setParameter('command', 'GET_FILE');
	    	        serveResourceUrl.setParameter('repositoryType', <portlet:namespace/>currentData.repositoryType());
	    	        serveResourceUrl.setParameter('pathType', inputData.type());
	    	        serveResourceUrl.setParameter('parentPath', inputData.parent());
	    	        serveResourceUrl.setParameter('fileName', inputData.name());
	    	        serveResourceUrl.setParameter('relative', inputData.relative());
	    	        
	    	        console.log( '[NGLViewer]Draw JSMol: ', inputData);
	    	        
		    	    iframe.contentWindow.drawNglViewer(inputData, serveResourceUrl.toString());
		    	    
		    	    $('#<portlet:namespace/>title').html(inputData.name());
	    	    });
	    	} 
	    	else{
	    		console.log("[NGLViewer] test esle : load data in drawNGL");
	    	
	    		<portlet:namespace/>drawNGL( inputData );
	    	}
	    }, 
	    10
	);
}

function <portlet:namespace/>initializeFileExplorer(){
	if( $.isEmptyObject(<portlet:namespace/>initData) ||( 
		<portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.FILE &&
		<portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.FOLDER &&
		<portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.EXT ))	return;

	var eventData = {
              portletId: '<%=portletDisplay.getId()%>',
              targetPortlet: <portlet:namespace/>fileExplorerId,
              data: OSP.Util.toJSON(<portlet:namespace/>initData)
	};
	
	Liferay.fire( 'OSP_LOAD_DATA', eventData );
}

function <portlet:namespace/>downloadCurrentFile(){
	console.log("[JSMol] Download current data", <portlet:namespace/>currentData);
	if( $.isEmptyObject(<portlet:namespace/>currentData) || 
		<portlet:namespace/>currentData.type() !== OSP.Enumeration.PathType.FILE )
		return;
					
	var filePath = <portlet:namespace/>currentData;
	var data = {
		<portlet:namespace/>command: "DOWNLOAD_FILE",
		<portlet:namespace/>pathType: filePath.type_,
		<portlet:namespace/>repositoryType: filePath.repositoryType_,
		<portlet:namespace/>parentPath: filePath.parent_,
		<portlet:namespace/>fileName: filePath.name_
	};
	
	
	var base = '<%=serveResourceURL.toString()%>';
	var sep = (base.indexOf('?') > -1) ? '&' : '?';
	var url = base + sep + $.param(data);
	
	location.href = url;
	<portlet:namespace/>loadJSMolFile( <portlet:namespace/>currentData );
    
}

function <portlet:namespace/>setTitle( title ){
	if(!title){
		title = '<h4>/'+ <portlet:namespace/>currentData.name_+'</h4>';
	}
	$('#<portlet:namespace/>title').html('<h4>'+title+'</h4>');
}


function <portlet:namespace/>initialize( inputData ){
	inputData.parent_ = OSP.Util.removeEndSlashes(inputData.parent_);
	inputData.name_ = OSP.Util.removeEndSlashes(inputData.name_ );
	
	if( $.isEmptyObject( inputData ) ){
		return;
	}
	else{
		console.log('[JSMOL] initialize input data', inputData);
	
		<portlet:namespace/>initData = new OSP.InputData(inputData);
		
		if( !<portlet:namespace/>initData.repositoryType() ){
			<portlet:namespace/>initData.repositoryType('<%=OSPRepositoryTypes.USER_JOBS.toString()%>');
		}
	
		switch( <portlet:namespace/>initData.type() ){
			case OSP.Enumeration.PathType.FILE:
				var subPath = OSP.Util.convertToPath( <portlet:namespace/>initData.name() );
				
				<portlet:namespace/>initData.parent( OSP.Util.mergePath( <portlet:namespace/>initData.parent(), subPath.parent() ) );
				<portlet:namespace/>initData.parent( OSP.Util.mergePath( <portlet:namespace/>initData.parent(), subPath.parent() ) );
				<portlet:namespace/>initData.name( subPath.name() );
				break;
			case OSP.Enumeration.PathType.FOLDER:
				<portlet:namespace/>initData.parent( OSP.Util.mergePath( <portlet:namespace/>initData.parent(), <portlet:namespace/>initData.name() ) );
				<portlet:namespace/>initData.name( '' );
				break;
			case OSP.Enumeration.PathType.EXT:
				var subPath = OSP.Util.convertToPath( <portlet:namespace/>initData.name() );
				<portlet:namespace/>initData.parent( OSP.Util.mergePath( <portlet:namespace/>initData.parent(), subPath.parent() ) );
				<portlet:namespace/>initData.name( subPath.name() );
				break;
			case OSP.Enumeration.PathType.DLENTRY_ID:
			case OSP.Enumeration.PathType.FILE_CONTENT:
			case OSP.Enumeration.PathType.URL:
				break;
			default:
				console.log('OSPTextViewer: Un-expected type: ' + <portlet:namespace/>initData.type());
				<portlet:namespace/>initData = new OSP.InputData();
				<portlet:namespace/>initData.parent( '' );
				<portlet:namespace/>initData.name( '' );
				<portlet:namespace/>initData.type( 'folder' );
				<portlet:namespace/>initData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
				break;
		}
	}
}

function iframeClickServerOpen(){
	console.log("[NGLViewer]test openserver menu");

    <portlet:namespace/>openFileExplorer();
};

</script>

