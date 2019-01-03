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
${portType}<br/>${portData}
<div class="row" id="<portlet:namespace/>canvas">
	
</div>
<c:if test="${portType eq 'inputPorts'}">
	<button onclick="<portlet:namespace/>returnJobData()" class="btn btn-primary">
		SAVE
	</button>
</c:if>
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
	
	var scienceApp = new OSP.ScienceApp();
	if('${portType}'==='inputPorts'){
		scienceApp.deserializeInputPorts( JSON.parse('${portData}') );
	}else if('${portType}'==='logPorts'){
		scienceApp.deserializeLogPorts( JSON.parse('${portData}') );
	}else if('${portType}'==='outputPorts'){
		scienceApp.deserializeOutputPorts( JSON.parse('${portData}') );
	}
	
	<portlet:namespace/>workbench.scienceApp(scienceApp);
	
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
		console.log('OSP_REGISTER_EVENTS: ['+e.portletId+', '+new Date()+']', e.portletType );
		<portlet:namespace/>workbench.handleRegisterEvents( e.portletId, e.portletType, e.data );
		
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
		
		var inputData = new OSP.InputData( e.data );
		if( inputData.type() === OSP.Enumeration.PathType.STRUCTURED_DATA ){
			var dataType = new OSP.DataType();
			dataType.deserializeStructure(inputData.context());
			var dataStructure = dataType.structure(); 
			var fileContents = dataStructure.activeParameterFormattedInputs();
			
			var data = new OSP.InputData();
			data.portName("${portName}");
			data.order(1);
			data.type( OSP.Enumeration.PathType.FILE_CONTENT );
			data.context( fileContents[0].join('') );
			console.log(JSON.stringify(data));
			job.inputData("${portName}",data);
		}else{
			console.log(JSON.stringify(inputData));
			job.inputData("${portName}",inputData);
		}
// 		<portlet:namespace/>workbench.handleDataChanged(e.portletId, e.data);
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


/*OSP_REQUEST_JOB_KEY 구현 필요*/
/***********************************************************************
* portlet Function 
***********************************************************************/ 
function <portlet:namespace/>createSimulationAndJob(){
	var simulation  = <portlet:namespace/>workbench.newSimulation();
	simulation.uuid('${simulationUuid}');
	<portlet:namespace/>workbench.workingSimulation(simulation)
	
	var portDataJson = JSON.parse('${portData}')["${portName}"];
	var job = simulation.newJob();
	job.uuid('${jobUuid}');
	if(portDataJson.hasOwnProperty(OSP.Constants.INPUTS)){
		var inputData = new OSP.InputData(portDataJson[OSP.Constants.INPUTS]);
		job.inputData("${portName}",inputData);
	}
	
	simulation.workingJob(job);
}

function <portlet:namespace/>returnJobData(){
	var simulation = <portlet:namespace/>workbench.workingSimulation();
	var job = simulation.workingJob();
	console.log(JSON.stringify(job.inputData("${portName}")));
}

</script>
