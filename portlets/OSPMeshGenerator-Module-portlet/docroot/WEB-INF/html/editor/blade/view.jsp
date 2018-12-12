<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/jstree/themes/proton/style.css" media="screen"/>

<%
	PortletPreferences preferences = portletDisplay.getPortletSetup();
	preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE)); 
	preferences.store();
%>

<style type="text/css">
div#<portlet:namespace/>content{
	height: 100%;
	padding: 10px 15px;
	overflow: hidden;
}

div#<portlet:namespace/>content div.blade-row{
	margin-left: -5px;
	margin-right: -5px;
}

div#<portlet:namespace/>content div.blade-col{
	padding-left: 5px;
	padding-right: 5px;
}

div#<portlet:namespace/>content div.blade-full-height{
	height: inherit;
	margin-bottom: 0px;
}

div#<portlet:namespace/>content div.blade-panel-heading{
	background-color: white;
}


div#<portlet:namespace/>navigatorParameter{
	display: none;
}
</style>

<div id="<portlet:namespace/>content">
	<div class="row blade-row blade-full-height">
		<div class="col-md-4 blade-col blade-full-height">
			<%@ include file="./navigator.jsp" %>
		</div>
		<div class="col-md-8 blade-col blade-full-height" id="<portlet:namespace/>BladeMeshViewerArea">
			<liferay-portlet:runtime portletName="BladeMeshViewer_WAR_OSPMeshGeneratorModuleportlet" queryString="viewerFromType=editor"/>
		</div>
		<div class="col-md-8 blade-col blade-full-height" id="<portlet:namespace/>ChartViewerArea" style="display: none;border: 1px solid #d3d3d3;">
			<liferay-portlet:runtime portletName="MeshXYChartViewer_WAR_OSPMeshGeneratorModuleportlet" queryString=""/>
		</div>
	</div>
	
	<%@ include file="./mesh-modal.jsp" %>
</div>


<script type="text/javascript" src="${contextPath}/js/jstree.min.js"></script>
<script src="${contextPath}/js/meshconstants.js"></script>

<script type="text/javascript">
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = '';

var <portlet:namespace/>simulationUuid = '';
var <portlet:namespace/>jobSeqNo = '';
var <portlet:namespace/>projectId = 0;

var <portlet:namespace/>isBlock = false;

var <portlet:namespace/>vcToken = "";
var <portlet:namespace/>jobUserName = "";
var <portlet:namespace/>currentUserName = "${currentUserName}";
/***********************************************************************
* Handling OSP Events
***********************************************************************/
Liferay.on(OSP.Event.OSP_HANDSHAKE,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>connector = e.portletId;
		var events = [
				OSP.Event.OSP_EVENTS_REGISTERED,
				OSP.Event.OSP_LOAD_DATA
			];
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector,
				data : events
			};
		Liferay.fire(OSP.Event.OSP_REGISTER_EVENTS, eventData);
	}
});

Liferay.on(OSP.Event.OSP_EVENTS_REGISTERED,function(e) {
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>init();
	}
});
		
Liferay.on(OSP.Event.OSP_INITIALIZE,function(e) {
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>jobUserName = "";
		<portlet:namespace/>fireWorkbenchEvent(OSP.Event.OSP_REQUEST_JOB_KEY);
		<portlet:namespace/>loadDataFile("IS_NULL","","");
	}
});

Liferay.on(OSP.Event.OSP_LOAD_DATA,function(e) {
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		console.log('[<portlet:namespace/>] OSP_LOAD_DATA: ', e );
		<portlet:namespace/>jobUserName = e.data[OSP.Constants.USER];
		<portlet:namespace/>fireWorkbenchEvent(OSP.Event.OSP_REQUEST_JOB_KEY);
		/*Workbench Data 존재*/
		var inputData = new OSP.InputData( e.data );
		switch( inputData.type() ){
			case OSP.Enumeration.PathType.FILE:
				<portlet:namespace/>loadDataFile("IS_FILE",inputData.parent(),inputData.name());
				break;
			case OSP.Enumeration.PathType.DLENTRY_ID:
				<portlet:namespace/>loadDataFile("IS_SAMPLE","","");
				break;
			default:
			
		}
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_JOB_KEY,function(e) {
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>simulationUuid = e.data.simulationUuid;
		<portlet:namespace/>jobSeqNo = e.data.jobSeqNo;
		<portlet:namespace/>searchNavigator(); 
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_DELETE_SIMULATION_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		var status = e.data.status;
		var simulationUuid = e.data.simulationUuid;
		if(status){
			<portlet:namespace/>removeProject('SIMULATION');
		}
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_DELETE_SIMULATION_JOB_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		if(e.data.status){
			<portlet:namespace/>removeProject('JOB');
		}
	}
});

Liferay.on(OSP.Event.OSP_DISABLE_CONTROLLS, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>isBlock = e.data.isBlock;
	}
});


Liferay.on(OSP.Event.OSP_FROM_ANALYZER_EVENT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		var command = e.command;
		if (command == 'close.chart') {
			$("#<portlet:namespace/>ChartViewerArea").css("display","none");
			
			if($("#<portlet:namespace/>BladeMeshViewerArea").is(":hidden")){
				$("#<portlet:namespace/>BladeMeshViewerArea").slideDown("fast");
			}
		}
	}
});





/***********************************************************************
* Viewer Caller Function
***********************************************************************/
function <portlet:namespace/>setXYPlotterResultPath(analyzerJob){
	
	$("#<portlet:namespace/>BladeMeshViewerArea").css("display","none");
	
	if($("#<portlet:namespace/>ChartViewerArea").is(":hidden")){
		$("#<portlet:namespace/>ChartViewerArea").slideDown("fast");
	}
	var targetPortlet = MESH.Constants.XY_VIEWER_PORTLET;
	var cmd = "set.path"
	var data = {
		"resultPath":analyzerJob.resultPath
	};
	
	<portlet:namespace/>viewerEventFire(targetPortlet,cmd,data);
}

function <portlet:namespace/>callMeshAnalyzerLoadProject(geometryGroup,meshGroup) {
	var targetPortlet = MESH.Constants.MESH_VIEWER_PORTLET;
	var cmd = "loadProject"
	var data = {
		"geometryGroup": geometryGroup,
		"meshGroup": meshGroup,
		"token": <portlet:namespace/>vcToken
	};
	
	<portlet:namespace/>viewerEventFire(targetPortlet,cmd,data);
}

function <portlet:namespace/>callMeshAnalyzerAddObject(command, object) {
	var targetPortlet = MESH.Constants.MESH_VIEWER_PORTLET;
	var cmd = command;
	var data = {
			"data": JSON.stringify(object),
			"token": <portlet:namespace/>vcToken
		};
	<portlet:namespace/>viewerEventFire(targetPortlet,cmd,data);
}



function <portlet:namespace/>viewerEventFire(targetPortlet,cmd,data){
	var myId = '<%=portletDisplay.getId()%>';
	if(targetPortlet===MESH.Constants.MESH_VIEWER_PORTLET){
		data['command'] = cmd;
	}
	
	var eventData = {
			portletId : myId,
			targetPortlet : targetPortlet,
			command : cmd,
			data : data
		};
	
	Liferay.fire(OSP.Event.OSP_FROM_EDITOR_EVENT, eventData);
}

/***********************************************************************
* Portlet Function
***********************************************************************/
function <portlet:namespace/>init(){
	<portlet:namespace/>navigatorInitJstree();
	
	<portlet:namespace/>parameterInitEditor(OSP.Enumeration.PathType.STRUCTURED_DATA,${parametric},'parametric');
}

function <portlet:namespace/>fireWorkbenchEvent(eventName){
	var myId = '<%=portletDisplay.getId()%>';
	var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector
		};
	
	Liferay.fire(eventName, eventData);
}

function <portlet:namespace/>fireDataChangeWorkbenchEvent(parentPath,fileName){
	var inputData = new OSP.InputData();
	inputData.parent( parentPath );
	inputData.name( fileName );
	inputData.type( OSP.Enumeration.PathType.FILE );
	
	console.log(OSP.Util.toJSON(inputData));
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
 			targetPortlet: <portlet:namespace/>connector,
 			data: OSP.Util.toJSON(inputData)
	};
	Liferay.fire(OSP.Event.OSP_DATA_CHANGED, eventData);
}


function <portlet:namespace/>takeSample(){
	$("#<portlet:namespace/>fileSelectedText").html("Sample Selected");
	<portlet:namespace/>fireWorkbenchEvent(OSP.Event.OSP_SAMPLE_SELECTED);
}
</script>