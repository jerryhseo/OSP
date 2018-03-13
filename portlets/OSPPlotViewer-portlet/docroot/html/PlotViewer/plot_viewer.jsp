<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
%>

<div class="container-fluid osp-analyzer">
	<div class="row-fluid header">
		<div class="col-sm-10" id="<portlet:namespace/>title"></div>
		<div class="col-sm-2" >
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					Menu<span class="caret"></span>
				</button>
				<ul class="dropdown-menu dropdown-menu-right">
					<li> <a href="javascript:<portlet:namespace/>openLocalFile()"><i class="icon-folder-open"> Open local...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>openServerFile()"><i class="icon-folder-open"> Open server...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>downloadCurrentFile()"><i class="icon-download-alt"> Download</i></a></li> 
				</ul>
			</div>
		</div>	
	</div>
	<div class="row-fluid frame">
		<iframe class ="col-sm-12 iframe-canvas" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/PlotViewer/load_plot.jsp">
		</iframe>
	</div>
</div>
<div id="<portlet:namespace/>hiddenSection" class="osp-analyzer hidden">
	<div id="<portlet:namespace/>fileExplorer" title="Select a file" >
              <div id="<portlet:namespace/>file-explorer-content" style="height:95%"></div>
              <div>
                  <input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
                  <input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
              </div>
	</div>
	<input type="file" id="<portlet:namespace/>selectFile"/>
	<img id="<portlet:namespace/>loadingBox" src="<%=request.getContextPath()%>/images/processing.gif" width="200" style="display: none;"/>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = '<%=connector%>';
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_od";
if( '<portlet:namespace/>'.lastIndexOf('_INSTANCE_') > 0)
	<portlet:namespace/>fileExplorerId += '<portlet:namespace/>'.substring('<portlet:namespace/>'.lastIndexOf('_INSTANCE_')+10);
else
	<portlet:namespace/>fileExplorerId += '001';
	
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>highCharts;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
 // for test
 //<portlet:namespace/>eventEnable = false;

if( <portlet:namespace/>eventEnable === false ){
    var inputData = '<%=inputData%>';
    if(!inputData){
        <portlet:namespace/>initData = new OSP.InputData();
    }else{
        <portlet:namespace/>initData = new OSP.InputData(JSON.parse(inputData));
    }
	
	<portlet:namespace/>loadHighCharts(<portlet:namespace/>initData.clone());
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

function <portlet:namespace/>openLocalFile(){
	$('#<portlet:namespace/>selectFile').click();
}

function <portlet:namespace/>openServerFile(){
	var inputData;
    if(!$.isEmptyObject(<portlet:namespace/>initData) && 
        <portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.URI &&
        <portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.CONTEXT ){
        inputData = <portlet:namespace/>initData;
    }else{
        inputData = new OSP.InputData();
        inputData.type( OSP.Enumeration.PathType.FOLDER );
        inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
        inputData.parent('');
        inputData.name('');
    }
    <portlet:namespace/>fileExplorerDialog('VIEW', inputData);
}

$("#<portlet:namespace/>file-explorer-ok").click(function(e){
  e.preventDefault();
  var eventData = {
      portletId : '<%=portletDisplay.getId()%>',
      targetPortlet : <portlet:namespace/>fileExplorerId
  };
  Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
  $<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
	e.preventDefault();
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

$('#<portlet:namespace/>selectFile').bind(
        'change', 
        function(event){
            var input = document.getElementById('<portlet:namespace/>selectFile');
            var reader = new FileReader();
            reader.onload = function (e) {
                if(input.files[0]){
                  <portlet:namespace/>currentData = null;
                  <portlet:namespace/>drawPlot(e.target.result, input.files[0].name, '');
                  $("#<portlet:namespace/>selectFile").val("");
                }
            }
            reader.readAsText(input.files[0]);
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
	OSP.Event.OSP_HANDSHAKE,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet === myId ){
			<portlet:namespace/>connector = e.portletId;
			if( e.action )
				<portlet:namespace/>mode = e.mode;
			else
				<portlet:namespace/>mode = 'VIEW';
				
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
			console.log(e.portletId+' activated at '+new Date()+']');
		}
	}
);

Liferay.on( 
    OSP.Event.OSP_LOAD_DATA, 
    function(e){
      var myId = '<%=portletDisplay.getId()%>';
      if( e.targetPortlet === myId ){
    	  console.log(myId + ' OSP_LOAD_DATA ', e.data);
        <portlet:namespace/>initData = new OSP.InputData( e.data );
  	    if( <portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER ){
            <portlet:namespace/>initData.parent(
                OSP.Util.mergePath(<portlet:namespace/>initData.parent(), <portlet:namespace/>initData.name()));
            <portlet:namespace/>initData.name("");
        }
  	  if( !<portlet:namespace/>initData.repositoryType() )
		  <portlet:namespace/>initData.repositoryType('<%=OSPRepositoryTypes.USER_JOBS.toString()%>');
  	    
        <portlet:namespace/>loadHighCharts( <portlet:namespace/>initData.clone() );
        
        var eventData = {
  	                   portletId: myId,
  	                   targetPortlet: <portlet:namespace/>fileExplorerId,
  	                   data: OSP.Util.toJSON( <portlet:namespace/>initData )
  	  };
  	  Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
  	}
  }
);

Liferay.on(
		OSP.Event.OSP_RESPONSE_DATA,
		function( eventData ){
			var myId = '<%=portletDisplay.getId()%>';
			if( eventData.targetPortlet === myId ){
				console.log('OneDPlot OSP_RESPONSE_DATA: ['+eventData.portletId+', '+new Date()+']');
				var inputData = new OSP.InputData( eventData.data );
				
				if( inputData.type() !== OSP.Enumeration.PathType.FILE ){
                    alert( 'File should be selected!' );
                    return;
                }
                else{
                    <portlet:namespace/>loadHighCharts( inputData );
                    $<portlet:namespace/>fileExplorerDialogSection.dialog('close');
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
   			console.log('[<portlet:namespace/>]OSP_INITIALIZE: ['+e.portletId+', '+new Date()+']');
   			$("#<portlet:namespace/>canvas").attr('src', '<%=request.getContextPath()%>/html/PlotViewer/load_plot.jsp');
   			<portlet:namespace/>initData = {};
   			<portlet:namespace/>currentData = {};
   		}
 );

/***********************************************************************
 * Golbal functions
***********************************************************************/
function <portlet:namespace/>loadHighCharts( inputData ){
	if( ! inputData.repositoryType() )
		inputData.repositoryType('<%=OSPRepositoryTypes.USER_JOBS.toString()%>');
    
	switch( inputData.type() ){
	case OSP.Enumeration.PathType.FILE:
		<portlet:namespace/>loadData( inputData, 'READ_FILE' );
		break;
	case OSP.Enumeration.PathType.FOLDER:
	case OSP.Enumeration.PathType.EXT:
	    <portlet:namespace/>getFirstFileName( inputData );
		break;
	case OSP.Enumeration.PathType.FILE_CONTENT:
		<portlet:namespace/>drawPlot( inputData.context(), '', '' );
		break;
	case OSP.Enumeration.PathType.URL:
		alert( 'Un-supported yet: '+inputData.type());
		break;
	default:
		alert( 'Un-expected Path type: '+ inputData.type());
	}
}

function <portlet:namespace/>loadData( inputData, command ){
	<portlet:namespace/>currentData = inputData.clone();
		
	var data = {
			<portlet:namespace/>command: command,
			<portlet:namespace/>pathType: <portlet:namespace/>currentData.type(),
			<portlet:namespace/>repositoryType: <portlet:namespace/>currentData.repositoryType(),
			<portlet:namespace/>parentPath: <portlet:namespace/>currentData.parent(),
			<portlet:namespace/>fileName: <portlet:namespace/>currentData.name(),
	};
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>',
		data  : data,
		dataType : 'text',
		success: function(data) {
		    var title = <portlet:namespace/>currentData.name();
		    $('#<portlet:namespace/>title').html('<h4>'+title+'</h4>');
			<portlet:namespace/>drawPlot( data, title, '' );
		},error:function(data,e){
			console.log('RawPlotData AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>drawPlot( data, title, subtitle ){
    setTimeout(
	    function(){
	    	var iframe = document.getElementById('<portlet:namespace/>canvas');
	    	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	    	console.log( 'iframeDoc.readyState', iframeDoc.readyState);
	    	if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.drawPlot ) {
	   	    	iframe.contentWindow.drawPlot( data, title, subtitle );
	    	} 
	    	else{
	    		<portlet:namespace/>drawPlot( data, title, subtitle );
	    	}
	    }, 
	    10
	);
}

function <portlet:namespace/>getFirstFileName( argData ){
    var inputData = argData.clone();
    if( inputData.type() === 'folder' ){
    	inputData.parent( OSP.Util.mergePath(inputData.parent(), inputData.name()) );
    	inputData.name('');
    }
    
    var data = {
            <portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
            <portlet:namespace/>pathType: inputData.type(),
            <portlet:namespace/>repositoryType: inputData.repositoryType(),
            <portlet:namespace/>parentPath: inputData.parent(),
            <portlet:namespace/>fileName: inputData.name()
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

function <portlet:namespace/>downloadCurrentFile(){
	if(!$.isEmptyObject(<portlet:namespace/>currentData) && 
		<portlet:namespace/>currentData.type() === OSP.Enumeration.PathType.FILE &&
		<portlet:namespace/>currentData.name()){
        var filePath = <portlet:namespace/>currentData;
        var data = {
            <portlet:namespace/>command: "DOWNLOAD_FILE",
            <portlet:namespace/>pathType: filePath.type(),
            <portlet:namespace/>repositoryType: filePath.repositoryType(),
            <portlet:namespace/>parentPath: filePath.parent(),
            <portlet:namespace/>fileName: filePath.name()
        };
        
        var base = '<%=serveResourceURL.toString()%>';
        var sep = (base.indexOf('?') > -1) ? '&' : '?';
        var url = base + sep + $.param(data);
        location.href = url;
    }
}

</script>
