<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
%>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<portlet:renderURL var="renderURL">
	<portlet:param name="jspPage" value="/html/ProteinViewer/load_protein.jsp"/>
</portlet:renderURL>

<div class="container-fluid common-analyzer-portlet">
	<div class="row-fluid header">
		<div class="col-sm-8" id="<portlet:namespace/>title"></div>
		<div class="col-sm-offset-2 col-sm-2" >
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					Menu<span class="caret"></span>
				</button>
				<!-- Link or button to toggle dropdown -->
				<ul class="dropdown-menu cursor">
					<li id="<portlet:namespace/>openLocal"><i class="icon-folder-open"> Open local...</i></li>
					<li id="<portlet:namespace/>openServer"><i class="icon-folder-open"> Open server...</i></li>
					<li id="<portlet:namespace/>download"><i class="icon-download-alt"> Download</i></li>
				</ul>
			</div>
		</div>	
	</div>
	
	<div class="row-fluid canvas">
		<iframe class ="col-sm-12 iframe" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/ImageViewer/load_image.jsp">
		</iframe>
	</div>
</div>
		
<div id="<portlet:namespace/>hiddenSection" style="display:none;">
	<div id="<portlet:namespace/>fileExplorer" title="Select a file" >
		<div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
		<div>
			<input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
			<input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
		</div>
	</div>
	<input type="file" id="<portlet:namespace/>selectFile"/>
</div>

<script type="text/javascript">
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = '<%=connector%>';
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPEditorsportlet_INSTANCE_pv"
    + "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');


/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
 // for test
 //<portlet:namespace/>eventEnable = false;
 
if( <portlet:namespace/>eventEnable === false ){
	<portlet:namespace/>connector = '<%=connector%>';
	var inputData = '<%=inputData%>';
	
	if(inputData){
		<portlet:namespace/>initData = JSON.parse( inputData );
	}
	else{
		<portlet:namespace/>initData = {};
	}
	
	<portlet:namespace/>loadProtein( <portlet:namespace/>initData );
}

$<portlet:namespace/>fileExplorerDialogSection.dialog({
	autoOpen: false,
	resizable: false,
	height: 600,
	width: 600,
	modal: true
});

/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
$('#<portlet:namespace/>openLocal').click(function(){
	$('#<portlet:namespace/>selectFile').click();
});

$('#<portlet:namespace/>openServer').click(function(){
	var inputData;
	if(<portlet:namespace/>initData && (
		<portlet:namespace/>initData.type_ === 'file' ||
		<portlet:namespace/>initData.type_ === 'folder' ||
		<portlet:namespace/>initData.type_ === 'ext' )){
		inputData = <portlet:namespace/>initData;
	}else{
		inputData = {};
		inputData.type_ = 'folder';
		inputData.repositoryType_ = '<%=OSPRepositoryTypes.USER_HOME.toString()%>';
		inputData.parent_ = '';
		inputData.name_ = '';
	}
	
	<portlet:namespace/>fileExplorerDialog('VIEW', inputData);
});

$('#<portlet:namespace/>download').click(function(){
	<portlet:namespace/>downloadCurrentFile();
});

$("#<portlet:namespace/>file-explorer-ok").click(function(e){
	e.preventDefault();
	var eventData = {
			portletId : '<%=portletDisplay.getId()%>',
			targetPortlet : <portlet:namespace/>fileExplorerId
	};
	Liferay.fire( 'OSP_REQUEST_DATA', eventData);
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});


$('#<portlet:namespace/>selectFile').bind(
		'change', 
		function(event){
			var input = document.getElementById('<portlet:namespace/>selectFile');
			var reader = new FileReader();

			reader.onload = function (e) {
				$('#<portlet:namespace/>canvas').each(function(){
							$(this).prop('contentWindow').loadProtein(
											e.target.result, 
											$('#<portlet:namespace/>canvas').width(), 
											$('#<portlet:namespace/>canvas').height());

							delete <portlet:namespace/>currentData;
				});
			};

			reader.readAsDataURL(input.files[0]);
		}
);

function <portlet:namespace/>fileExplorerDialog( mode, inputData ){
	AUI().use('liferay-portlet-url', function(A){
		var dialogURL = Liferay.PortletURL.createRenderURL();
		dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
		dialogURL.setParameter('inputData', JSON.stringify(inputData));
		dialogURL.setParameter('mode', mode);
		dialogURL.setParameter('eventEnable', false);
		dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
		dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
		
		if($("#<portlet:namespace/>file-explorer-content").children().length > 0){
		    $<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}else{
		    $("#<portlet:namespace/>file-explorer-content").load( dialogURL.toString());
		    $<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}
	});
} 

/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
	'OSP_HANDSHAKE',
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet === myId ){
			<portlet:namespace/>connector = e.portletId;
			if( e.action )
				<portlet:namespace/>mode = e.mode;
			else
				<portlet:namespace/>mode = 'VIEW';
				
			var events = [
				'OSP_EVENTS_REGISTERED',
				'OSP_LOAD_DATA',
				'OSP_REFRESH_OUTPUT_VIEW',
				'OSP_INITIALIZE'
			];
			var eventData = {
				portletId: myId,
				targetPortlet: <portlet:namespace/>connector,
				data: events
			};
			Liferay.fire( 'OSP_REGISTER_EVENTS', eventData );
		}
	}
);

Liferay.on(
	'OSP_EVENTS_REGISTERED',
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if(e.targetPortlet === myId){
			console.log(e.portletId+' activated at '+new Date()+']');
		}
	}
);

Liferay.on( 
	'OSP_LOAD_DATA', 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId ){
			return;
		}
		console.log('[ProteinViewer] OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']', e.data );
		
		<portlet:namespace/>initData = e.data;
		 if( <portlet:namespace/>initData.type_ === 'folder' ){
		      <portlet:namespace/>initData.parent_ = 
	              <portlet:namespace/>mergePath(<portlet:namespace/>initData.parent_, <portlet:namespace/>initData.name_);
		      <portlet:namespace/>initData.name_ = '';
		  }
		 if( !<portlet:namespace/>initData.repositoryType_ )
			  <portlet:namespace/>initData.repositoryType_ = '<%=OSPRepositoryTypes.USER_JOBS.toString()%>';

		<portlet:namespace/>loadProtein( <portlet:namespace/>initData );
		
		var eventData = {
		                   portletId: myId,
		                   targetPortlet: <portlet:namespace/>fileExplorerId,
		                   data: <portlet:namespace/>initData
		  };
		  Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
	}
);

Liferay.on(
		'OSP_RESPONSE_DATA',
		function( e ){
			if( e.targetPortlet === '<%=portletDisplay.getId()%>' ){
				console.log('[ProteinViewer] OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']', e.data);
				if( e.portletId === <portlet:namespace/>fileExplorerId ){
					var inputData = e.data;

					if( inputData.type_ !== 'file' ){
						alert( 'File should be selected!' );
						return;
					}
					else{
						<portlet:namespace/>loadProtein( inputData );
						$<portlet:namespace/>fileExplorerDialogSection.dialog('close');
					}
				}
			}
		}
);

Liferay.on(
		'OSP_REFRESH_OUTPUT_VIEW',
		function(e){
			console.log('[ProteinViewer]OSP_REFRESH_OUTPUT_VIEW: ['+e.portletId+', '+new Date()+']');
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: <portlet:namespace/>connector
			};

			Liferay.fire('OSP_REQUEST_OUTPUT_PATH', eventData);
		}
);

Liferay.on(
		'OSP_INITIALIZE',
		function(e){
			console.log('[ProteinViewer]OSP_INITIALIZE: ['+e.portletId+', '+new Date()+']');
			$("#<portlet:namespace/>canvas").empty();
		}
);

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadProtein( inputData ){
	if( ! inputData.repositoryType_ )
		inputData.repositoryType_ = '<%=OSPRepositoryTypes.USER_JOBS.toString()%>';
		
	switch( inputData.type_ ){
		case 'file':
		    <portlet:namespace/>loadData( inputData );
			break;
		case 'folder':
		case 'ext':
		    <portlet:namespace/>getFirstFileName( inputData );
			break;
		case 'url':
		    alert('Un supported yet.');
			break;
		default:
			alert('Un supported yet.');
	}
}

function <portlet:namespace/>loadData( inputData ){
	AUI().use('liferay-portlet-url', function(a) {
		<portlet:namespace/>currentData = inputData;
		var serveResourceURL = Liferay.PortletURL.createResourceURL();
		serveResourceURL.setPortletId('<%=portletDisplay.getId()%>');
		serveResourceURL.setParameter('parentPath', inputData.parent_);
		serveResourceURL.setParameter('pathType', inputData.type_);
		serveResourceURL.setParameter('repositoryType', inputData.repositoryType_);
		serveResourceURL.setParameter('fileName', inputData.name_);
		serveResourceURL.setParameter('relative', inputData.relative_);
		serveResourceURL.setParameter('command', 'GET_FILE');

		<portlet:namespace/>drawProtein( serveResourceURL.toString());
	});
}

function <portlet:namespace/>drawProtein( url ){
	setTimeout(
	    function(){
	    	var iframe = document.getElementById('<portlet:namespace/>canvas');
	    	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	    	console.log( 'iframeDoc.readyState', iframeDoc.readyState);
	    	if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.loadProtein ) {
	   	    	iframe.contentWindow.loadProtein(url);
	    	} 
	    	else{
	    		<portlet:namespace/>drawProtein( url );
	    	}
	    }, 
	    10
	);
}

function <portlet:namespace/>getFirstFileName( inputData ){
    var data = {
            <portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
            <portlet:namespace/>pathType: inputData.type_,
            <portlet:namespace/>repositoryType: inputData.repositoryType_,
            <portlet:namespace/>parentPath: inputData.parent_,
            <portlet:namespace/>fileName: inputData.name_,
            <portlet:namespace/>relative: inputData.relative_
    };
        
    $.ajax({
        type: 'POST',
        url: '<%= serveResourceURL.toString()%>', 
        data  : data,
        dataType : 'json',
        success: function(data) {
            inputData.name_ = data.fileName;
            inputData.type_ = 'file';
            <portlet:namespace/>loadData( inputData );
        },
        error:function(data,e){
            console.log('AJAX ERROR-->'+e);
        },
        complete: function( jqXHR, textStatus ){
        }
    });
}

function <portlet:namespace/>downloadCurrentFile(){
    if(<portlet:namespace/>currentData && <portlet:namespace/>currentData.name_){
    	var filePath = <portlet:namespace/>currentData;
    	var data = {
  			<portlet:namespace/>command: "DOWNLOAD_FILE",
  			<portlet:namespace/>pathType: filePath.type_,
  			<portlet:namespace/>repositoryType: filePath.repositoryType_,
  			<portlet:namespace/>parentPath: filePath.parent_,
  			<portlet:namespace/>fileName: filePath.name_,
  			<portlet:namespace/>relative: filePath.relative_
    	};
    	
    	var base = '<%=serveResourceURL.toString()%>';
    	var sep = (base.indexOf('?') > -1) ? '&' : '?';
        var url = base + sep + $.param(data);
        location.href = url;
    }
}

function <portlet:namespace/>mergePath( parent, child ){
	if( !parent && !child )	return '';
	if( !parent )
		return child;
	if( !child )
		return parent;
	
	return parent+'/'+child;
}
</script>