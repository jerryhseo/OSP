<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common-analyzer-portlet.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dropdown.css">

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
%>

<div class="container-fluid common-analyzer-portlet">
	<div class="row-fluid header">
		<div class="col-sm-10" id="<portlet:namespace/>title"></div>
		<div class="col-sm-2" >
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
			<iframe class ="col-sm-12 iframe" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/csvplotter/csvplotter.jsp">
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

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/

var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';

var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_cv2d"
    + "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
    

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( '<%=eventEnable%>' === 'false' ){
	<portlet:namespace/>connector = '<%=connector%>';
	var inputData = '<%=inputData%>';
	
	if(inputData){
		<portlet:namespace/>initData = JSON.parse( inputData );
		<portlet:namespace/>loadCSVPlotter( <portlet:namespace/>initData );
	}
	
	<portlet:namespace/>passNamespace();
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
     if(<portlet:namespace/>initData && 
         <portlet:namespace/>initData.type_ !== OSP.Enumeration.PathType.URI &&
         <portlet:namespace/>initData.type_ !== OSP.Enumeration.PathType.CONTEXT ){
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
 				$(this).prop('contentWindow').drawGraph(e.target.result);
 				delete <portlet:namespace/>currentData;
			}
 			
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
/*
Liferay.on( 'LOCAL_WignerFET_Draw_Graph', function(eventData){	
	var myId = '<%=portletDisplay.getId()%>';	
	
	var data = eventData.data;		
			
	$('#<portlet:namespace/>canvas').each(function(){
        // alert("fff-----");
		// <portlet:namespace/>drawDevice( data );
	});		
});
 */

Liferay.on(	'OSP_HANDSHAKE', function( e ){
	var myId = '<%=portletDisplay.getId()%>';	
	if( e.targetPortlet === myId )
	{
		<portlet:namespace/>connector = e.portletId;
		if( e.mode )
			<portlet:namespace/>mode = e.mode;
		else
			<portlet:namespace/>mode = 'VIEW';
//		$('#<portlet:namespace/>canvas').css('height', eventData.height);

		if( e.data )
		{		
			var events = [
							'OSP_EVENTS_REGISTERED'							
						];
			var eventData = {
							portletId: myId,
							targetPortlet: <portlet:namespace/>connector,
							data: events
			};			
			
			Liferay.fire('OSP_REGISTER_EVENTS', eventData);			
			
		}
	}
});

Liferay.on('OSP_EVENTS_REGISTERED', function( e ){
	   var myId = '<%=portletDisplay.getId()%>';
	   		
	   if( e.targetPortlet === myId )
	   {
		     console.log('[csvplotter] OSP_EVENTS_REGISTERED: ['+e.portletId+', '+new Date()+']');
	   		<portlet:namespace/>passNamespace();
		}
});

Liferay.on( 'OSP_LOAD_DATA', function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if( e.targetPortlet === myId ){
		console.log('[csvplotter] OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']', e.data);
		
		<portlet:namespace/>initData = e.data;
		if( <portlet:namespace/>initData.type_ === 'folder' ){
		      <portlet:namespace/>initData.parent_ = 
	              <portlet:namespace/>mergePath(<portlet:namespace/>initData.parent_, <portlet:namespace/>initData.name_);
		      <portlet:namespace/>initData.name_ = '';
		}
		if( !<portlet:namespace/>initData.repositoryType_ )
			  <portlet:namespace/>initData.repositoryType_ = '<%=OSPRepositoryTypes.USER_JOBS.toString()%>';
			  
		<portlet:namespace/>loadCSVPlotter( <portlet:namespace/>initData );
		
		var eventData = {
		                   portletId: myId,
		                   targetPortlet: <portlet:namespace/>fileExplorerId,
		                   data: <portlet:namespace/>initData
		  };
		  Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
	}
});

Liferay.on(
	OSP.Event.OSP_RESPONSE_DATA,
	function( e ){
		if( e.targetPortlet === '<%=portletDisplay.getId()%>' ){
			console.log('[csvplotter]OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']', e.data);
			if( e.portletId === <portlet:namespace/>fileExplorerId ){
				var inputData = e.data;
				
				if( inputData.type_ !== 'file' ){
                    alert( 'File should be selected!' );
                    return;
                }
                else{
                    <portlet:namespace/>loadCSVPlotter( inputData );
                    $<portlet:namespace/>fileExplorerDialogSection.dialog('close');
                }
			}
		}
	}
);

Liferay.on(
	OSP.Event.OSP_REFRESH_OUTPUT_VIEW,
	function(e){
		console.log('[csvplotter]OSP_REFRESH_OUTPUT_VIEW: ['+e.portletId+', '+new Date()+']');
		var eventData = {
				portletId: '<%=portletDisplay.getId()%>',
				targetPortlet: <portlet:namespace/>connector
		};

		Liferay.fire(OSP.Event.OSP_REQUEST_OUTPUT_PATH, eventData);
	}
);

Liferay.on(
	OSP.Event.OSP_INITIALIZE,
	function(e){
		console.log('[csvplotter]OSP_INITIALIZE: ['+e.portletId+', '+new Date()+']');
	  $("#<portlet:namespace/>canvas").empty();
	}
);

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadCSVPlotter( inputData ){
	var command = '';
	switch( inputData.type_ ){
		case 'file':
		    <portlet:namespace/>currentData = inputData;
			<portlet:namespace/>callCSVPlotter( 
				'READ_FILE', 
				<portlet:namespace/>action, 
				inputData.parent_, 
				inputData.name_ );
			break;
		case 'folder':
		case 'ext':
		    <portlet:namespace/>loadFirstFile( inputData );
		    break;
	}
	
}

function <portlet:namespace/>callCSVPlotter( command, action, parentPath, fileName ){
    setTimeout(
	    function(){
	    	var iframe = document.getElementById('<portlet:namespace/>canvas');
    	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

    	    if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.drawGraph ) {
   	    		var serveResourceURL = '<%=serveResourceURL.toString()%>';
   	        	var sep = (serveResourceURL.indexOf('?') > -1) ? '&' : '?';
   	    	    var data = {
   	    	                <portlet:namespace/>command: command,
   	    	                <portlet:namespace/>action: action,
   	    	                <portlet:namespace/>parentPath: parentPath,
   	    	         		<portlet:namespace/>fileName: fileName,
   	    	    };

   	            var serveResourceURL = serveResourceURL + sep + $.param(data);
   	            console.log( 'serveResourceURL: '+serveResourceURL);

    	        iframe.contentWindow.drawGraph( 
    	                                        serveResourceURL, 
    	                                        $('#<portlet:namespace/>canvas').width(), 
    	                                        $('#<portlet:namespace/>canvas').height());
    	    }
    	    else{
    	    	<portlet:namespace/>callCSVPlotter( command, action, parentPath, fileName );
    	    }
	    }, 
	    10
	);
}

function <portlet:namespace/>passNamespace(){
    setTimeout(
            function(){
         	    var iframe = document.getElementById('<portlet:namespace/>canvas');
         	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

         	    if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.setNamespace ) {
         	        iframe.contentWindow.setNamespace( '<portlet:namespace/>');
         	    }
         	    else{
         	    	<portlet:namespace/>passNamespace();
         	    }
            }, 
            10
	);
}

function <portlet:namespace/>loadFirstFile( argData ){
    var inputData = JSON.parse(JSON.stringify( argData ));
    var data = {
            <portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
            <portlet:namespace/>pathType: inputData.type_,
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
            <portlet:namespace/>currentData = inputData;
            <portlet:namespace/>callCSVPlotter( 
        	    			'READ_FILE', 
        	    			<portlet:namespace/>action, 
							inputData.parent_, 
							inputData.name_ );
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