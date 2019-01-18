<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init-simulation-workbench.jsp"%>

<liferay-portlet:resourceURL var="serveResourceURL"	id="serveResource" copyCurrentRenderParameters="false"/>

<style type="text/css">
	
</style>

<%
	PortletPreferences preferences = portletDisplay.getPortletSetup();
	preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
	preferences.store();
	
	JSONObject workbenchLayout = (JSONObject) renderRequest.getAttribute("workbenchLayout");
%>
${portType}<br/>${portData}<br/>${simulationUuid}
<div id="<portlet:namespace/>canvas" style="height: 500px;">
	
</div>
<div class="row">
	<div class="col-md-12">
		<div class="btn-group pull-right" style="margin: 5px 0px;">
			<c:if test="${saveFlag}">
				<button class="btn btn-primary" onclick="<portlet:namespace/>returnJobData()">
					<span class="icon-save"> Save</span>
				</button>
			</c:if>
			<button class="btn btn-close" onclick="<portlet:namespace/>close()">
				<span class="icon-arrow-down"> Close</span>
			</button>
		</div>
	</div>
</div>
<script type="text/javascript">
/***********************************************************************
* Global variables section
***********************************************************************/

var <portlet:namespace/>workbench = new OSP.Workbench( '<portlet:namespace/>');
<portlet:namespace/>workbench.id('<%=portletDisplay.getId()%>');

/***********************************************************************
* Initailization section and handling Liferay events
***********************************************************************/
$(function(e) {
	bStart();
	<portlet:namespace/>workbench.layout( new OSP.Layout(JSON.parse('<%=workbenchLayout.toString()%>')));
	<portlet:namespace/>workbench.currentUserName('${screenName}');
	
	var scienceApp = new OSP.ScienceApp();
	if('${portType}'==='inputPorts'){
		scienceApp.deserializeInputPorts( JSON.parse('${portData}') );
	}else if('${portType}'==='logPorts'){
		scienceApp.deserializeLogPorts( JSON.parse('${portData}') );
	}else if('${portType}'==='outputPorts'){
		scienceApp.deserializeOutputPorts( JSON.parse('${portData}') );
	}
	
	<portlet:namespace/>workbench.scienceApp(scienceApp);
	
// 	<portlet:namespace/>createSimulationAndJob();
	
	// Resolving workbench layout
	$.ajax({
		url: '<%=serveResourceURL.toString()%>',
		type:'POST',
		dataType:'text',
		async: false,
		data:{
			<portlet:namespace/>command:'RESOLVE_TEMPLATE',
			<portlet:namespace/>namespace: '<portlet:namespace/>',
			<portlet:namespace/>templateDir: '/templates'
		},
		success: function( result ){
			$('#<portlet:namespace/>canvas').html(result);
			/*All Layout Grid*/
			
			<portlet:namespace/>workbench.resizeLayout('<portlet:namespace/>');
			<portlet:namespace/>workbench.loadPortlets('<%=LiferayWindowState.EXCLUSIVE%>');
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				console.log("<portlet:namespace/>RESOLVE_TEMPLATE-->"+textStatus+": "+jqXHR.responseText);
			}else{
				console.log("<portlet:namespace/>RESOLVE_TEMPLATE-->"+textStatus+": "+errorThrown);
			}
		},
		complete:function(){
			bEnd();
		}
	});
});


/***********************************************************************
* Handling OSP Events
***********************************************************************/
Liferay.on(OSP.Event.OSP_REGISTER_EVENTS,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_REGISTER_EVENTS: ['+e.portletId+', '+new Date()+']' );
		<portlet:namespace/>workbench.handleRegisterEvents( e.portletId, e.data );
		
		<portlet:namespace/>createSimulationAndJob();
	}
});


Liferay.on(OSP.Event.OSP_REQUEST_DATA_STRUCTURE,function( e ){
	console.log('OSP_REQUEST_DATA_STRUCTURE: ['+e.portletId+', '+new Date()+']');
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		<portlet:namespace/>workbench.handleRequestDataStructure( e.portletId, '<%=serveResourceURL.toString()%>' );
	}
});

Liferay.on(OSP.Event.OSP_DATA_CHANGED,function( e ){
	if( <portlet:namespace/>workbench.id() !== e.targetPortlet ){
		return;
	}else{
		console.log('OSP_DATA_CHANGED: ['+e.portletId+', '+new Date()+']', e.data);
		var simulation = <portlet:namespace/>workbench.workingSimulation();
		var job = simulation.workingJob();
		
		var scienceApp = <portlet:namespace/>workbench.scienceApp();
		var port = scienceApp.inputPort("${portName}");
		
		var inputData = new OSP.InputData( e.data );
			inputData.portName(port.name());
			inputData.relative( true );
			inputData.dirty( true );
			inputData.order(port.order());
        
		if( inputData.type() === OSP.Enumeration.PathType.STRUCTURED_DATA ){
			var dataType = new OSP.DataType();
			dataType.deserializeStructure(inputData.content());
			var dataStructure = dataType.structure(); 
			var fileContents = dataStructure.activeParameterFormattedInputs();
			
			var data = new OSP.InputData();
			data.portName(port.name());
			data.order(port.order());
			data.type( OSP.Enumeration.PathType.FILE_CONTENT );
			data.content( fileContents[0].join('') );
			job.inputData(port.name(),data);
		}else{
			job.inputData(port.name(),inputData);
		}
	}	
});

Liferay.on(OSP.Event.OSP_REQUEST_SAMPLE_CONTENT,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_REQUEST_SAMPLE_CONTENT: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>workbench.handleRequestSampleContent( e.portletId, '<%=serveResourceURL%>');
	}
});
      
Liferay.on(OSP.Event.OSP_SAMPLE_SELECTED,function( e ){
	if( <portlet:namespace/>workbench.id() !== e.targetPortlet )return;
	console.log('OSP_SAMPLE_SELECTED: ['+e.portletId+', '+new Date()+']');
	<portlet:namespace/>workbench.handleSampleSelected(e.portletId,'<%=serveResourceURL%>');
});

Liferay.on(OSP.Event.OSP_REQUEST_JOB_KEY,function( e ){
	if( <portlet:namespace/>workbench.id() !== e.targetPortlet )return;
	console.log('OSP_REQUEST_JOB_INFO: ['+e.portletId+', '+new Date()+']');
	
	var simulation = <portlet:namespace/>workbench.workingSimulation();
	var job = simulation.workingJob();
	
	var simulationUuid = simulation.uuid();
	var jobUuid = job.uuid();
	var jobSeqNo = job.seqNo();
	
	var eventData = {
		targetPortlet: e.portletId,
		data : {
			simulationUuid : simulationUuid,
			jobUuid : jobUuid,
			jobSeqNo : jobSeqNo
		}
	};
	
	Liferay.fire(OSP.Event.OSP_RESPONSE_JOB_KEY, eventData);
});

/***********************************************************************
* portlet Function 
***********************************************************************/ 
function <portlet:namespace/>createSimulationAndJob(){
	var simulation  = <portlet:namespace/>workbench.newSimulation();
	simulation.uuid('${simulationUuid}');
	<portlet:namespace/>workbench.workingSimulation(simulation);
	
	var job = simulation.newJob();
	job.uuid('${jobUuid}');
	job.seqNo(1);
	job.status('${status}');
	job.isSubmit(true);
	job.user('${screenName}');

	var portDataJson = JSON.parse('${portData}')["${portName}"];
	if(portDataJson.hasOwnProperty(OSP.Constants.INPUTS)){
		var inputData = new OSP.InputData(portDataJson[OSP.Constants.INPUTS]);
		job.inputData("${portName}",inputData);
	}
	
	simulation.workingJob(job);
	
	var portType = "";
	if('${portType}'==='inputPorts'){
		portType = OSP.Enumeration.PortType.INPUT;
	}else if('${portType}'==='logPorts'){
		portType = OSP.Enumeration.PortType.LOG;
	}else if('${portType}'==='outputPorts'){
		portType = OSP.Enumeration.PortType.OUTPUT;
	}
	setTimeout(function() {
		<portlet:namespace/>workbench.handleModuleViewerData(portType);
	}, 500);
	
}

function <portlet:namespace/>returnJobData(){
	var simulation = <portlet:namespace/>workbench.workingSimulation();
	var job = simulation.workingJob();
	var jobData = JSON.stringify(job.inputData("${portName}"));
	Liferay.Util.getOpener().setJobDataFromModule("${nodeId}","${portName}",jobData, '${dialogId}');
}
function <portlet:namespace/>close(){
	Liferay.Util.getOpener().closeFromModule("${nodeId}","${portName}",'${dialogId}');
}

function <portlet:namespace/>jobSelectResult(wfNodeId, simulationUuid,jobUuid){
	Liferay.Util.getOpener().setSimAndJobFromWorkbench(wfNodeId,simulationUuid,jobUuid);
}

</script>
