<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>

<%@ include file="/common/init.jsp"%>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/jstree/themes/proton/style.css" media="screen"/>

<%
	OSPVisualizerConfig visualizerConfig = OSPVisualizerUtil.getVisualizerConfig(renderRequest, portletDisplay, user);
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
var <portlet:namespace/>currentTimeOut;

var <portlet:namespace/>analyzerStructure = "";

var <portlet:namespace/>config = {
			namespace: '<portlet:namespace/>',
			displayCanvas: <portlet:namespace/>content,
			portletId: '<%=portletDisplay.getId()%>',
			connector: '<%=visualizerConfig.connector%>',
			resourceURL: '<%=serveResourceURL%>',
			eventHandlers: {
				'OSP_HANDSHAKE': <portlet:namespace/>handshakeEventHandler,
				'OSP_LOAD_DATA': <portlet:namespace/>loadDataEventHandler,
				'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler,
				'OSP_RESPONSE_JOB_KEY' : <portlet:namespace/>responseJobKeyEventHandler,
				'OSP_RESPONSE_DELETE_SIMULATION_RESULT' : <portlet:namespace/>simulationDeleteEventHandler,
				'OSP_RESPONSE_DELETE_SIMULATION_JOB_RESULT' : <portlet:namespace/>jobDeleteEventHandler,
				'OSP_DISABLE_CONTROLS': <portlet:namespace/>disableControlsEventHandler
			},
			disabled: JSON.parse( '<%=visualizerConfig.disabled%>')
};

var <portlet:namespace/>visualizer;
$(function() {
	<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
	<%-- <portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>' ), false ); --%>
	
	<portlet:namespace/>init();
	
	<portlet:namespace/>connector = <portlet:namespace/>config.connector;
});

/***********************************************************************
* Handling OSP Events
***********************************************************************/
function <portlet:namespace/>handshakeEventHandler( jsonData, params ){
	<portlet:namespace/>visualizer.configConnection( params.connector, params.disabled );
	<portlet:namespace/>visualizer.fireRegisterEventsEvent();
}

function <portlet:namespace/>loadDataEventHandler( jsonData, params ){
	<portlet:namespace/>jobUserName = jsonData[OSP.Constants.USER];
	<portlet:namespace/>fireWorkbenchEvent(OSP.Event.OSP_REQUEST_JOB_KEY);
	/*Workbench Data 존재*/
	var inputData = new OSP.InputData( jsonData );
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

function <portlet:namespace/>initializeEventHandler( data, params ){
	<portlet:namespace/>jobUserName = "";
	<portlet:namespace/>fireWorkbenchEvent(OSP.Event.OSP_REQUEST_JOB_KEY);
	<portlet:namespace/>loadDataFile("IS_NULL","","");
}

function <portlet:namespace/>disableControlsEventHandler( data, params ){
	<portlet:namespace/>isBlock = params.disabled;
}

function <portlet:namespace/>responseJobKeyEventHandler( data, params ){
	<portlet:namespace/>simulationUuid = data.simulationUuid;
	<portlet:namespace/>jobSeqNo = data.jobSeqNo;
	<portlet:namespace/>searchNavigator(); 
}

function <portlet:namespace/>simulationDeleteEventHandler(data,params){
	var status = data.status;
	var simulationUuid = data.simulationUuid;
	if(status){
		<portlet:namespace/>removeProject('SIMULATION');
	}
}

function <portlet:namespace/>jobDeleteEventHandler(data,params){
	if(data.status){
		<portlet:namespace/>removeProject('JOB');
	}
}

Liferay.on(OSP.Event.OSP_FROM_ANALYZER_EVENT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		var command = e.command;
		if (command == 'close.chart') {
			$("#<portlet:namespace/>ChartViewerArea").css("display","none");
			
			if($("#<portlet:namespace/>BladeMeshViewerArea").is(":hidden")){
				$("#<portlet:namespace/>BladeMeshViewerArea").slideDown("fast");
			}
		}else if (command == 'set.display') {
			<portlet:namespace/>analyzerStructure = JSON.stringify(e.data);
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
	if(!<portlet:namespace/>isBlock){
		$("#<portlet:namespace/>fileSelectedText").html("Sample Selected");
		<portlet:namespace/>fireWorkbenchEvent(OSP.Event.OSP_SAMPLE_SELECTED);
	}
}
</script>