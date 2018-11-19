<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/jstree/themes/proton/style.css" media="screen"/>

<%
	PortletPreferences preferences = portletDisplay.getPortletSetup();
	preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
	preferences.store();
%>

<style type="text/css">
div#_BladeDataEditor_WAR_OSPMeshGeneratorModuleportlet_content{
	height: 100%;
	padding: 20px 0px;
}

div#_BladeDataEditor_WAR_OSPMeshGeneratorModuleportlet_content div.blade-row{
	margin-left: -5px;
	margin-right: -5px;
}

div#_BladeDataEditor_WAR_OSPMeshGeneratorModuleportlet_content div.blade-col{
	padding-left: 5px;
	padding-right: 5px;
}

div#_BladeDataEditor_WAR_OSPMeshGeneratorModuleportlet_content div.blade-full-height{
	height: inherit;
}

div#_BladeDataEditor_WAR_OSPMeshGeneratorModuleportlet_content div.blade-panel-heading{
	background-color: white;
}
</style>

<div id="<portlet:namespace/>content">
	<div class="row blade-row blade-full-height">
		<div class="col-md-4 blade-col blade-full-height">
			<%@ include file="./navigator.jsp" %>
		</div>
		<div class="col-md-8 blade-col blade-full-height">
			<liferay-portlet:runtime portletName="BladeMeshViewer_WAR_OSPMeshGeneratorModuleportlet" queryString=""/>
		</div>
	</div>
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
		<portlet:namespace/>fireWorkbenchEvent(OSP.Event.OSP_REQUEST_JOB_KEY,'');
		console.log("OSP_INITIALIZE");
	}
});

Liferay.on(OSP.Event.OSP_LOAD_DATA,function(e) {
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>fireWorkbenchEvent(OSP.Event.OSP_REQUEST_JOB_KEY,'');
		console.log("OSP_LOAD_DATA");
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
/***********************************************************************
* Portlet Function
***********************************************************************/
function <portlet:namespace/>init(){
	<portlet:namespace/>navigatorInitJstree();
	
	<portlet:namespace/>parameterInitEditor(OSP.Enumeration.PathType.STRUCTURED_DATA,${parametric},'parametric');
}

function <portlet:namespace/>fireWorkbenchEvent(eventName,data){
	var myId = '<%=portletDisplay.getId()%>';
	var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector
		};
	
	Liferay.fire(eventName, eventData);
}
</script>