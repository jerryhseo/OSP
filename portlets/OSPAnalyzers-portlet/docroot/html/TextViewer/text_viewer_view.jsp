<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<style>
.text-viewer-portlet{
	padding:0;
	margin: 0;
}
.text-viewer-portlet .canvas-wrapper{height: 100%;}
#<portlet:namespace/>canvas{
	margin: 0;
	border: none;
	overflow-y:auto;
    height: 100%;
}
</style>

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

<div class="row-fluid text-viewer-portlet common-analyzer-portlet">
	<div class="span12" style="height:inherit;">
        <div class="row-fluid menu-section" id="<portlet:namespace/>menuSection">
          <div class="dropdown-wrapper" >
            <div class="dropdown">
                      <i class="icon-reorder icon-menu"></i>
              <!-- Link or button to toggle dropdown -->
              <div class="dropdown-content">
                <div class="dropdown-item" id="<portlet:namespace/>download"><i class="icon-download-alt"> Download...</i></div>
				<div class="dropdown-item" id="<portlet:namespace/>openLocal"><i class="icon-folder-open"> Open local...</i></div>
                <div class="dropdown-item" id="<portlet:namespace/>openServer"><i class="icon-folder-open"> Open server...</i></div>
              </div>
            </div>
          </div>  
        </div>

        <div class="row-fluid canvas-wrapper" id="<portlet:namespace/>canvasPanel" >
            <pre class="span12 canvas" id="<portlet:namespace/>canvas"></pre>
        </div>

        <div class="row-fluid" id="<portlet:namespace/>hiddenSection" style="display: none;">
            <input type="file" id="<portlet:namespace/>selectFile"/>
            <div id="<portlet:namespace/>fileExplorer" title="Select a file" >
                <div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
                <div>
                    <input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
                    <input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
                </div>
            </div>
            <a id="<portlet:namespace/>downloadAnchor" target="_blank" style="z-index:1000;">Download</a>
            <img id="<portlet:namespace/>processingMessage" src="<%=request.getContextPath()%>/images/processing_01.gif"/>
        </div>
    </div>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector;
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPEditorsportlet_INSTANCE_tv"
    + "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>action = '<%=action%>';

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( '<%=eventEnable%>' === 'false' ){
    <portlet:namespace/>connector = '<%=connector%>';
    var inputData = '<%=inputData%>'
    if(!inputData){
        <portlet:namespace/>initData = new OSP.InputData();
    }else{
        <portlet:namespace/>initData = new OSP.InputData(JSON.parse(inputData));
    }
    <portlet:namespace/>loadText(<portlet:namespace/>initData);
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
    var inputData = new OSP.InputData();
    inputData.type( 'folder' );
    inputData.parent( '' );
    inputData.name('');
    inputData.relative(true);
    
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
			var reader = new FileReader();
		    reader.onload = function (evt) {
		        var textContents = evt.target.result;
		        $('#<portlet:namespace/>canvas').text( textContents );
		        $("#<portlet:namespace/>selectFile").val("");
                delete <portlet:namespace/>currentData;
		    };
		    var inputFile = document.getElementById('<portlet:namespace/>selectFile');
		    reader.readAsText(inputFile.files[0]);
		}
);

function <portlet:namespace/>fileExplorerDialog( mode, action, inputData ){
    AUI().use('liferay-portlet-url', function(A){
        var dialogURL = Liferay.PortletURL.createRenderURL();
        dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
        var initData = <portlet:namespace/>initData;
        dialogURL.setParameter('inputData', JSON.stringify(inputData));
        dialogURL.setParameter('mode', mode);
        dialogURL.setParameter('action', <portlet:namespace/>action);
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
	OSP.Event.OSP_HANDSHAKE,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet === myId ){
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
                console.log('TextViewer OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']');
                
                <portlet:namespace/>initData = new OSP.InputData( e.data );
                if( <portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER ){
                    <portlet:namespace/>initData.parent(
                                                        OSP.Util.mergePath(<portlet:namespace/>initData.parent(), <portlet:namespace/>initData.name()));
                    <portlet:namespace/>initData.name("");
                }

                <portlet:namespace/>loadText( <portlet:namespace/>initData );
            }
        }
);

Liferay.on(
        OSP.Event.OSP_RESPONSE_DATA,
        function( e ){
            var myId = '<%=portletDisplay.getId()%>';
            if( e.targetPortlet === myId ){
               console.log('TextViewer OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']');
               //alert( eventData.sourceData.action );
                if( e.portletId === <portlet:namespace/>fileExplorerId ){
                    var inputData = new OSP.InputData( e.data );

                    if( inputData.type() !== OSP.Enumeration.PathType.FILE ){
                        alert( 'File should be selected!' );
                        return;
                    }
                    else{
                        <portlet:namespace/>loadText( inputData );
                        $<portlet:namespace/>fileExplorerDialogSection.dialog('close');
                    }
                    //<portlet:namespace/>checkPath( inputData, 'CHECK_VALID_FILE');
                }
            }
        }
);

Liferay.on(
           OSP.Event.OSP_REFRESH_OUTPUT_VIEW,
           function( e ){
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
             //$("#<portlet:namespace/>canvas").empty();
           }
);

/***********************************************************************
 * Golbal functions
***********************************************************************/
function <portlet:namespace/>loadText( inputData ){
    switch( inputData.type() ){
        case OSP.Enumeration.PathType.FILE:
            <portlet:namespace/>loadData( inputData, 'READ_FILE' );
            break;
        case OSP.Enumeration.PathType.FOLDER:
        case OSP.Enumeration.PathType.EXT:
            <portlet:namespace/>getFirstFileName( inputData );
            break;
        case OSP.Enumeration.PathType.FILE_CONTENT:
            $('#<portlet:namespace/>canvas').text( inputData.context() );
            break;
        case OSP.Enumeration.PathType.URL:
            alert( 'Un-supported yet: '+inputData.type());
            break;
        default:
            alert( 'Un-expected Path type: '+ inputData.type());
    }
}

function <portlet:namespace/>getFirstFileName( argData ){
    var inputData = argData.clone();
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
            <portlet:namespace/>loadData( inputData, 'READ_FILE' );
        },
        error:function(data,e){
            console.log('AJAX ERROR-->'+e);
        },
        complete: function( jqXHR, textStatus ){
        }
    });
}


function <portlet:namespace/>loadData( inputData, command ){
    var data = {
            <portlet:namespace/>command: command,
            <portlet:namespace/>pathType: inputData.type(),
            <portlet:namespace/>parentPath: inputData.parent(),
            <portlet:namespace/>fileName: inputData.name(),
            <portlet:namespace/>relative: inputData.relative()
    };
    <portlet:namespace/>currentData = inputData;
    $.ajax({
        type: 'POST',
        url: '<%= serveResourceURL.toString()%>', 
        data  : data,
        dataType : 'text',
        success: function(data) {
            $('#<portlet:namespace/>canvas').text( data );
        },
        error:function(data,e){
            console.log('AJAX ERROR-->'+e);
        },
        complete: function( jqXHR, textStatus ){
        }
    });
}

function <portlet:namespace/>downloadCurrentFile(){
    if(<portlet:namespace/>currentData &&
    	<portlet:namespace/>currentData.type() === OSP.Enumeration.PathType.FILE ){
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
        ($('#<portlet:namespace/>downloadAnchor').attr('href', url))[0].click();
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
        url: '<%= serveResourceURL.toString()%>', 
        type: 'POST',
        data  : data,
        dataType : 'json',
        success: function(result) {
            if( result.valid === true){
                $<portlet:namespace/>fileExplorerDialogSection.dialog('close');
                <portlet:namespace/>loadText( filePath );
            }
            else
             alert('Selected file is invalid or not a file.: '+filePath.fullPath() );
        },
        error:function(data,e){
            console.log('AJAX ERROR: TextViewer checkPath()', data);
        }
    });
};
</script>

