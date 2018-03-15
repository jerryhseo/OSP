<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = (String)renderRequest.getAttribute("inputData");
String connector = (String)renderRequest.getAttribute("connector");
String mode = (String)renderRequest.getAttribute("mode");
boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
boolean isPopup = LiferayWindowState.isExclusive(request);
%>

<div class="container-fluid osp-analyzer" style="padding: 0px">
	<iframe class ="col-sm-12 iframe-canvas" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/PlotViewer/load_plot.jsp">
	</iframe>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector;
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

<portlet:namespace/>passNamespace();
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
	<portlet:namespace/>connector = '<%=connector%>';

	<portlet:namespace/>loadHighCharts(<portlet:namespace/>initData);
}

$<portlet:namespace/>fileExplorerDialogSection.dialog({
	autoOpen: false,
	resizable: false,
	height: 600,
	width: 600,
	modal: true
});

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

        <portlet:namespace/>loadHighCharts( <portlet:namespace/>initData );

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

/***********************************************************************
 * Golbal functions
***********************************************************************/

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

function <portlet:namespace/>loadHighCharts( inputData ){
	if( ! inputData.repositoryType() )
		inputData.repositoryType('<%=OSPRepositoryTypes.USER_JOBS.toString()%>');

	switch( inputData.type() ){
	case OSP.Enumeration.PathType.FILE:
		<portlet:namespace/>loadData( inputData, 'READ_FILE' );
		break;
	case OSP.Enumeration.PathType.FOLDER:
	case OSP.Enumeration.PathType.EXT:
	case OSP.Enumeration.PathType.FILE_CONTENT:
	case OSP.Enumeration.PathType.URL:
		alert( 'Un-supported yet: '+inputData.type() +'Only supported : FILE(datalist)');
		break;
	default:
		alert( 'Un-expected Path type: '+ inputData.type());
	}
}

function <portlet:namespace/>loadData( inputData, command ){
	<portlet:namespace/>currentData = inputData.clone();

	var dataload = {
			<portlet:namespace/>command: command,
			<portlet:namespace/>pathType: <portlet:namespace/>currentData.type(),
			<portlet:namespace/>repositoryType: <portlet:namespace/>currentData.repositoryType(),
			<portlet:namespace/>parentPath: <portlet:namespace/>currentData.parent(),
			<portlet:namespace/>fileName: <portlet:namespace/>currentData.name(),
			<portlet:namespace/>relative: <portlet:namespace/>currentData.relative()
	};
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>',
		data  : dataload,
		dataType : 'text',
		success: function(data) {
			var serveResourceURL = '<%=serveResourceURL.toString()%>'
			<portlet:namespace/>drawPlot( data, dataload.<portlet:namespace/>parentPath, serveResourceURL );
		},error:function(data,e){
			console.log('RawPlotData AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>drawPlot( data, dataload, serveResourceURL ){
    setTimeout(
	    function(){
	    	var iframe = document.getElementById('<portlet:namespace/>canvas');
	    	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	    	console.log( 'iframeDoc.readyState', iframeDoc.readyState);
	    	if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.drawPlot ) {
	   	    	iframe.contentWindow.drawPlot( data, dataload, serveResourceURL );
	    	}
	    	else{
	    		<portlet:namespace/>drawPlot( data, dataload, serveResourceURL );
	    	}
	    },
	    10
	);
}
</script>
