<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/image-viewer-portlet.css">
<script src="<%=request.getContextPath()%>/js/osp/osp_basic_object.js"></script>
<script src="<%=request.getContextPath()%>/js/osp/osp_super_class.js"></script>
<script src="<%=request.getContextPath()%>/js/osp/osp_datatype.js"></script>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<portlet:renderURL var="renderURL">
    <portlet:param name="jspPage" value="/html/ImageViewer/load_image.jsp"/>
</portlet:renderURL>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = (String)renderRequest.getAttribute("inputData");
String connector = (String)renderRequest.getAttribute("connector");
String action = (String)renderRequest.getAttribute("action");
boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
boolean isPopup = LiferayWindowState.isExclusive(request);
%>

<div class="row-fluid image-viewer-portlet common-analyzer-portlet" id="<portlet:namespace/>ground">
	<div class="span12" style="height:inherit;">
		<div class="row-fluid menu-section" id="<portlet:namespace/>menuSection">
			<div class="span8 offset1" id="<portlet:namespace/>title"></div>
			<div class="dropdown-wrapper" >
				<div class="dropdown">
                  <i class="icon-reorder icon-menu"></i>
					<!-- Link or button to toggle dropdown -->
					<div class="dropdown-content">
						<div class="dropdown-item" id="<portlet:namespace/>openLocal"><i class="icon-folder-open"> Open local...</i></div>
						<div class="dropdown-item" id="<portlet:namespace/>openServer"><i class="icon-folder-open"> Open server...</i></div>
						<div class="dropdown-item" id="<portlet:namespace/>download"><i class="icon-download-alt"> Download</i></div>
					</div>
				</div>
			</div>	
		</div>
		<div class="row-fluid canvas-wrapper" id="<portlet:namespace/>canvasPanel">
			<iframe class ="span12 canvas" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/ImageViewer/load_image.jsp">
			</iframe>
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
	</div>
</div>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector;
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_iv";
if( "<portlet:namespace/>".lastIndexOf("_INSTANCE_") > 0)
	<portlet:namespace/>fileExplorerId += "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
else
	<portlet:namespace/>fileExplorerId += '001';
	
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>action = '<%=action%>';
var <portlet:namespace/>eventEnable = <%=eventEnable%>;

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
 //for test...
 //<portlet:namespace/>eventEnable = false;
 
 if( <portlet:namespace/>eventEnable === false ){
     var inputData = '<%=inputData%>';
     if(!inputData){
         <portlet:namespace/>initData = new OSP.InputData();
     }else{
         <portlet:namespace/>initData = new OSP.InputData(JSON.parse(inputData));
     }
     <portlet:namespace/>connector = '<%=connector%>';
     
//     <portlet:namespace/>initData.type('file');
//     <portlet:namespace/>initData.parent('');
//     <portlet:namespace/>initData.name('test_image.jpg')
     <portlet:namespace/>loadImage(<portlet:namespace/>initData, 'fit');
   }

$<portlet:namespace/>fileExplorerDialogSection.dialog({
	autoOpen: false,
	resizable: false,
	height: 600,
	width: 450,
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
        <portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.URI &&
        <portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.CONTEXT ){
        inputData = <portlet:namespace/>initData;
    }else{
        inputData = new OSP.InputData();
        inputData.type( OSP.Enumeration.PathType.FOLDER );
        inputData.parent('');
        inputData.name('');
    }
    <portlet:namespace/>fileExplorerDialog('VIEW', 'READ', inputData);
});

$('#<portlet:namespace/>download').click(function(){
    <portlet:namespace/>downloadCurrentFile();
});

$("#<portlet:namespace/>file-explorer-ok").click(function(e){
  e.preventDefault();
  var eventData = {
      portletId : '<%=portletDisplay.getId()%>',
      targetPortlet : <portlet:namespace/>fileExplorerId,
      action: "READ"
  };
  Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
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
			    $('#<portlet:namespace/>canvas').iviewer('loadImage', e.target.result);
			    $("#<portlet:namespace/>selectFile").val("");
			    <portlet:namespace/>currentData = null;
            }
			reader.readAsDataURL(input.files[0]);
		}
		
);

function <portlet:namespace/>fileExplorerDialog( mode, action, inputData ){
	AUI().use('liferay-portlet-url', function(A){
		var dialogURL = Liferay.PortletURL.createRenderURL();
		dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
		dialogURL.setParameter('inputData', JSON.stringify(inputData));
		// dialogURL.setParameter('action', <portlet:namespace/>action ? <portlet:namespace/>action : 'output' );
		dialogURL.setParameter('action', <portlet:namespace/>action );
		dialogURL.setParameter('mode', mode);
		dialogURL.setParameter('eventEnable', false);
		dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
		dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
		
		console.log( 'Open fileExplorer '+<portlet:namespace/>action);
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
	OSP.Event.OSP_HANDSHAKE,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet === myId ){
			console.log('[ImageViewer]OSP_HANDSHAKE: ['+e.portletId+', '+new Date()+']');
			<portlet:namespace/>connector = e.portletId;
			<portlet:namespace/>action = e.action;
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
			console.log('[ImageViewer]OSP_EVENTS_REGISTERED: ['+e.portletId+', '+new Date()+']');
		  var eventData = {
	         portletId: myId,
	         targetPortlet: <portlet:namespace/>connector
	      };
	      Liferay.fire(OSP.Event.OSP_REQUEST_OUTPUT_PATH, eventData);
		}
	}
);

Liferay.on( 
  OSP.Event.OSP_LOAD_DATA, 
  function(e){
    var myId = '<%=portletDisplay.getId()%>';
	if( e.targetPortlet === myId ){
		console.log('[ImageViewer]OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']', e.data);
	  <portlet:namespace/>initData = new OSP.InputData( e.data );
	  if( <portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER ){
	      <portlet:namespace/>initData.parent(
              OSP.Util.mergePath(<portlet:namespace/>initData.parent(), <portlet:namespace/>initData.name()));
	      <portlet:namespace/>initData.name("");
	  }
	  <portlet:namespace/>loadImage(<portlet:namespace/>initData, 'fit');
	}
  }
);

Liferay.on(
	OSP.Event.OSP_RESPONSE_DATA,
	function( e ){
		if( e.targetPortlet === '<%=portletDisplay.getId()%>' ){
			console.log('[ImageViewer]OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']', e.data);
			if( e.portletId === <portlet:namespace/>fileExplorerId ){
				var inputData = new OSP.InputData( e.data );
				
				if( inputData.type() !== OSP.Enumeration.PathType.FILE ){
                    alert( 'File should be selected!' );
                    return;
                }
                else{
                    <portlet:namespace/>loadImage( inputData, 'fit' );
                    $<portlet:namespace/>fileExplorerDialogSection.dialog('close');
                }
			}
		}
	}
);

Liferay.on(
		OSP.Event.OSP_REFRESH_OUTPUT_VIEW,
		function(e){
			console.log('[ImageViewer]OSP_REFRESH_OUTPUT_VIEW: ['+e.portletId+', '+new Date()+']');
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
			if( e.targetPortlet === '<%=portletDisplay.getId()%>' ){
				console.log('[ImageViewer]OSP_INITIALIZE: ['+e.portletId+', '+new Date()+']');
		  		<portlet:namespace/>drawImage('<%=PortalUtil.getPortalURL(request)%>'+'<%=request.getContextPath()%>'+'/images/OSP.png', 100);
		  		<portlet:namespace/>setTitle('');
			}
		}
);


/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadImage( inputData, zooming ){
	switch( inputData.type() ){
	case OSP.Enumeration.PathType.FILE:
	    <portlet:namespace/>loadData( inputData, zooming );
		break;
	case OSP.Enumeration.PathType.FOLDER:
	case OSP.Enumeration.PathType.EXT:
	    <portlet:namespace/>getFirstFileName( inputData, zooming );
		break;
	case OSP.Enumeration.PathType.URL:
	    alert('Un supported yet.');
		break;
	default:
		alert('Un supported yet.');
	}
}

function <portlet:namespace/>loadData( inputData, zooming ){
    AUI().use('liferay-portlet-url', function(a) {
        <portlet:namespace/>currentData = inputData;
        var serveResourceURL;
        serveResourceURL = Liferay.PortletURL.createResourceURL();
        serveResourceURL.setPortletId('<%=portletDisplay.getId()%>');
        serveResourceURL.setParameter('parentPath', inputData.parent());
        serveResourceURL.setParameter('pathType', inputData.type());
        serveResourceURL.setParameter('fileName', inputData.name());
        serveResourceURL.setParameter('relative', inputData.relative());
        serveResourceURL.setParameter('command', 'READ_IMAGE');

        <portlet:namespace/>drawImage(
                                                   serveResourceURL.toString(), 
                                                   zooming);
        
        <portlet:namespace/>setTitle(inputData.name());
    });
}

function <portlet:namespace/>drawImage( url, zooming ){
    setTimeout(
	    function(){
	    	var iframe = document.getElementById('<portlet:namespace/>canvas');
	    	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	    	console.log( 'iframeDoc.readyState', iframeDoc.readyState);
	    	if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.loadImage) {
	   	    	iframe.contentWindow.loadImage (  url, zooming );
	    	} 
	    	else{
	    		<portlet:namespace/>drawImage( url, zooming );
	    	}
	    }, 
	    10
	);
}

function <portlet:namespace/>clearImage(){
    setTimeout(
	    function(){
	    	var iframe = document.getElementById('<portlet:namespace/>canvas');
	    	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	    	console.log( 'iframeDoc.readyState', iframeDoc.readyState);
	    	if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.clearImage) {
	   	    	iframe.contentWindow.clearImage ();
	    	} 
	    	else{
	    		<portlet:namespace/>clearImage();
	    	}
	    }, 
	    10
	);
}


function <portlet:namespace/>getFirstFileName( argData, zooming ){
    var inputData = argData.clone();
    console.log('Image viewer: ', inputData );
    var data = {
            <portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
            <portlet:namespace/>pathType: inputData.type(),
            <portlet:namespace/>parentPath: inputData.parent(),
            <portlet:namespace/>fileName: inputData.name(),
            <portlet:namespace/>relative: inputData.relative()
    };
        
    $.ajax({
        type: 'POST',
        url: '<%= serveResourceURL.toString()%>', 
        data  : data,
        dataType : 'json',
        success: function(data) {
            inputData.name( data.fileName );
            inputData.type(OSP.Enumeration.PathType.FILE);
            <portlet:namespace/>loadData( inputData, zooming );
        },
        error:function(data,e){
            console.log('AJAX ERROR-->'+e);
        },
        complete: function( jqXHR, textStatus ){
        }
    });
}

function <portlet:namespace/>downloadCurrentFile(){
    if(<portlet:namespace/>currentData && <portlet:namespace/>currentData.name()){
    	var filePath = <portlet:namespace/>currentData;
    	var data = {
  			<portlet:namespace/>command: "DOWNLOAD_FILE",
  			<portlet:namespace/>pathType: filePath.type(),
  			<portlet:namespace/>parentPath: filePath.parent(),
  			<portlet:namespace/>fileName: filePath.name(),
  			<portlet:namespace/>relative: filePath.relative()
    	};
    	
    	var base = '<%=serveResourceURL.toString()%>';
    	var sep = (base.indexOf('?') > -1) ? '&' : '?';
        var url = base + sep + $.param(data);
        location.href = url;
    }
}

function <portlet:namespace/>checkPath( filePath, command ){
	var data = {
			<portlet:namespace/>command: command,
			<portlet:namespace/>pathType: filePath.type(),
			<portlet:namespace/>parentPath: filePath.parent(),
			<portlet:namespace/>fileName: filePath.name(),
			<portlet:namespace/>relative: filePath.relative()
	};
	
	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'json',
		success: function(result) {
		    if( result.valid === true ){
				$<portlet:namespace/>fileExplorerDialogSection.dialog('close');
				<portlet:namespace/>loadImage( filePath, 'fit' );
			}
			else{
				alert('Selected file is invalid or not a file.: '+filePath.fullPath() );
			}
		},
		error:function(data,e){
			console.log('AJAX ERROR-->', data, e);
		}
	});
}

function <portlet:namespace/>setTitle( title ){
	$('#<portlet:namespace/>title').html('<h5>'+title+'</h5>');
}

</script>