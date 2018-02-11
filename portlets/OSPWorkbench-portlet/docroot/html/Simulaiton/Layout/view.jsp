<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init-simulation-workbench.jsp"%>
<link rel="stylesheet" href="${contextPath}/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/css/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminCustom.css">
<link rel="stylesheet" href="${contextPath}/css/toastr.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">

<link rel="stylesheet" href="${contextPath}/css/simulation-workbench.css">

<style type="text/css">
	.simulation-workbench .vertical {
		top: 50%;
		left: 50%;
		margin-top: -20px;
		margin-left: -20px;
 		background: url(${contextPath}/images/simulation-workbench/arrow_lr.png) center no-repeat; 
	}
	
	
	.simulation-workbench .horizontal {
		left: 50%;
		margin-top: -20px;
		margin-left: -20px;
		background: url(${contextPath}/images/simulation-workbench/arrow_tb.png) center no-repeat;
	}
	
	.toast-designer-pos {top: 155px; left: 80%;}
</style>

<liferay-portlet:resourceURL var="serveResourceURL"	id="serveResource" copyCurrentRenderParameters="false">
	
</liferay-portlet:resourceURL>

<%
	PortletPreferences preferences = portletDisplay.getPortletSetup();
	preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
	preferences.store();
	
	JSONObject workbenchLayout = (JSONObject) renderRequest.getAttribute("workbenchLayout");
	
	JSONArray jsonColumns = workbenchLayout.getJSONArray("columns_");
	JSONArray columns = JSONFactoryUtil.createJSONArray();
	
	JSONObject lodingPortlets = JSONFactoryUtil.createJSONObject();
	for (int i = 0; i < jsonColumns.length(); i++) {
		JSONObject column = JSONFactoryUtil.createJSONObject();
		JSONObject jsonColumn = jsonColumns.getJSONObject(i);

		String currentPortlet = jsonColumn.getString("currentPortlet_");
		column.put("id", jsonColumn.getString("id_"));
		column.put("height", jsonColumn.getDouble("height_"));
		column.put("portletId", currentPortlet);
		columns.put(column);
		
		JSONArray portlets = JSONFactoryUtil.createJSONArray(jsonColumn.getString("portlets_"));
		for (int j = 0; j < portlets.length(); j++) {
			JSONObject portlet = portlets.getJSONObject(j);
			System.out.println(portlet.getString("instanceId_"));
			lodingPortlets.put(portlet.getString("instanceId_"), "view");
		}
		
	}

	String templateFile = workbenchLayout.getString("templateId_") + ".ftl";
	
	String inputPorts = (String) renderRequest.getAttribute("inputPorts");
	String logPorts = (String) renderRequest.getAttribute("logPorts");
	String outputPorts = (String) renderRequest.getAttribute("outputPorts");
%>
<div class="row" id="<portlet:namespace/>canvas">
	
</div>

<div class="modal fade" id="<portlet:namespace/>job-log-modal" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace/>job-log-modal" style="display: none;">
	<div class="vertical-alignment-helper">
		<div class="modal-dialog vertical-align-center" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Job System Log</h4>
				</div>
				<div class="modal-body">
					<textarea class="form-control" id="<portlet:namespace/>log-text" style="min-width: 80%;height: 350px;resize:none;" autofocus="autofocus" readonly="readonly" >
					
					</textarea>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="<portlet:namespace/>job-result-file-modal" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace/>job-result-file-modal" style="display: none;">
	<div class="vertical-alignment-helper">
		<div class="modal-dialog vertical-align-center" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Job Result File</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered table-hover"> <thead> <tr> <th>Name</th> <th>City</th> <th>Pincode</th> </tr> </thead> <tbody> <tr> <td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td> <td>Bangalore</td> <td>560001</td> </tr> <tr> <td class="center">Tanmay</td> <td>Bangalore</td> <td>560001</td> </tr> <tr> <td class="center">Tanmay</td> <td>Bangalore</td> <td>560001</td> </tr> </tbody> </table>
				</div>
				<div class="modal-footer">
					<div class="btn-group pull-right">
						<button class="btn btn-primary" id="<portlet:namespace/>all-down-btn"><span class="icon-download-alt">  <liferay-ui:message key="edison-simulation-monitoring-result-file-all-down"/></span></button>
					</div>
				
				</div>
			</div>
		</div>
	</div>
</div>


<img id="loadingBox" src="${contextPath}/images/processing.gif" width="300px" style="display: none;"/>

<script type="text/javascript">
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>workbench = new OSP.Workbench( '<portlet:namespace/>');
var toastr;
<portlet:namespace/>workbench.id('<%=portletDisplay.getId()%>');

var <portlet:namespace/>lodingPortlets = JSON.parse('<%=lodingPortlets.toString()%>');
/***********************************************************************
 * Initailization section and handling Liferay events
 ***********************************************************************/
$(function(e) {
	//page block
	bStart();
	
	<portlet:namespace/>workbench.layout( new OSP.Layout(JSON.parse('<%=workbenchLayout.toString()%>')));
	<portlet:namespace/>workbench.type ('${workbenchType}');
	<portlet:namespace/>workbench.classId('${classId}');
	<portlet:namespace/>workbench.customId('${customId}');
	
	var scienceApp = new OSP.ScienceApp();
	scienceApp.id('${scienceApp.getScienceAppId()}');
	scienceApp.name('${scienceApp.getName()}');
	scienceApp.version('${scienceApp.getVersion()}');
	scienceApp.runType('${scienceApp.getRunType()}');
	scienceApp.currentManualId('${scienceApp.getManualIdCurrentValue()}');
	
	if( '<%=inputPorts%>' ) 
		scienceApp.deserializeInputPorts( JSON.parse('<%=inputPorts%>') );
	if( '<%=logPorts%>' )
		scienceApp.deserializeLogPorts( JSON.parse('<%=logPorts%>') );
	if( '<%=outputPorts%>' )
		scienceApp.deserializeOutputPorts( JSON.parse('<%=outputPorts%>') );
	
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
			<portlet:namespace/>templateDir: '/templates',
			<portlet:namespace/>templateFile:'<%=templateFile%>',
			<portlet:namespace/>columns: '<%=columns.toString()%>'
		},
		success: function( result ){
			$('#<portlet:namespace/>canvas').html( result );
			<portlet:namespace/>workbench.loadPortlets('<%=LiferayWindowState.EXCLUSIVE%>');
		}
	});
	
	/**
	* Layout Scroll
	* vertical Left 기준 Width check -> data-equal-id="LEFT DIV" data-remainder-id="RIGHT DIV"
	* horizontal top 기준 Width check -> data-equal-id="BOTTOM DIV" data-remainder-id="TOP DIV"
	*
	**/
	var isDevider = false;
	var object,container,equalDiv,RemainderDiv,moveType;
	$(".devider").mousedown(function(e) {
		isDevider = true;
		object = e.currentTarget;
		container = $(this).parent();
		equalDiv = $("#"+$(this).attr("data-equal-id"));
		RemainderDiv = $("#"+$(this).attr("data-remainder-id"));
		moveType = $(this).hasClass("vertical") ? "vertical" : "horizontal"
	});
	
	$("body").mouseup(function(a) {
		if(isDevider){
			isDevider = false;
		}
	});
	$("body").mousemove(function(e) {
		if(isDevider){
			if(moveType =="vertical"){
				var offsetRight = container.width() - (e.clientX - container.offset().left);
				var moveLeft = Math.round(offsetRight / container.width() * 100);
				
				var objectLeftPositon = 100-moveLeft;
				if(objectLeftPositon<20){
					objectLeftPositon = 20;
				}else if(objectLeftPositon>80){
					objectLeftPositon = 80;
				}
				var objectRightPositon = 100-objectLeftPositon;
				
				$(object).css("left",objectLeftPositon+"%");
				$(equalDiv).css("width",objectLeftPositon+"%");
				$(RemainderDiv).css("width",objectRightPositon+"%");
			}else{
				var offsetTop = e.clientY-container.offset().top;
				var offsetBottom = container.height() - offsetTop;
				$(object).css("top",offsetTop+"px");
				$(equalDiv).find("div.sub-col").css("height",offsetTop+"px");
				$(RemainderDiv).find("div.sub-col").css("height",offsetBottom+"px");
			}
			
		}
	});
	
	
	toastr.options = {
			"closeButton": true,
			"debug": false,
			"newestOnTop": true,
			"progressBar": false,
			"positionClass": "toast-designer-pos",
			"preventDuplicates": false,
			"onclick": null,
			"showDuration": "300",
			"hideDuration": "1000",
			"timeOut": "5000",
			"extendedTimeOut": "1000",
			"showEasing": "swing",
			"hideEasing": "linear",
			"showMethod": "slideDown",
			"hideMethod": "slideUp"
		};
	
	
	
	
	//time out - 5 sec
	setTimeout(function(){ <portlet:namespace/>displayInit(); }, 1000*5);
});

/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(OSP.Event.OSP_REGISTER_EVENTS,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_REGISTER_EVENTS: ['+e.portletId+', '+new Date()+']', e.portletType );
		delete <portlet:namespace/>lodingPortlets[e.portletId];
		if(Object.keys(<portlet:namespace/>lodingPortlets).length===0){
			//portlet all loding check
			<portlet:namespace/>displayInit();
		}
		<portlet:namespace/>workbench.handleRegisterEvents( e.portletId, e.portletType, e.data );
	}
});

Liferay.on(OSP.Event.OSP_REQUEST_APP_INFO,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_REQUEST_APP_INFO: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>workbench.handleRequestAppInfo(e.portletId);
	}
});
Liferay.on(OSP.Event.OSP_REQUEST_PORT_INFO,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_REQUEST_PORT_INFO: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>workbench.handleRequestPortInfo(e.portletId);
	}
});
Liferay.on(OSP.Event.OSP_JOB_SELECTED,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_JOB_SELECTED: ['+e.portletId+', '+new Date()+']');
		//System portlet Loading check
		<portlet:namespace/>workbench.handleJobSelected(e.data.simulationUuid,e.data.jobUuid,'<%=serveResourceURL.toString()%>');
		<portlet:namespace/>activeBlockLayout(false);
	}
});

Liferay.on(OSP.Event.OSP_REQUEST_DATA_STRUCTURE,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_REQUEST_DATA_STRUCTURE: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>workbench.handleRequestDataStructure( e.portletId, '<%=serveResourceURL.toString()%>' );
	}
});

Liferay.on(OSP.Event.OSP_PORT_SELECTED,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_PORT_SELECTED: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>workbench.handlePortSelected( e.portName, e.portletInstanceId );
	}
});

Liferay.on(OSP.Event.OSP_DATA_CHANGED,function( e ){
	if( <portlet:namespace/>workbench.id() !== e.targetPortlet ){
		return;
	}else{
		console.log('OSP_DATA_CHANGED: ['+e.portletId+', '+new Date()+']', e.data);
		<portlet:namespace/>workbench.handleDataChanged(e.portletId, e.data);
	}	
});

Liferay.on(OSP.Event.OSP_REQUEST_OUTPUT_PATH,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
	    console.log('OSP_REQUEST_OUTPUT_PATH: ['+e.portletId+', '+new Date()+']');
// 	    <portlet:namespace/>workbench.handleRequestOutputPath( e.portletId );
	}
});
Liferay.on(OSP.Event.OSP_SAVE_SIMULATION,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_SAVE_SIMULATION: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>workbench.handleSaveSimulation(e.portletId,'<%=serveResourceURL.toString()%>');
	}
});
Liferay.on(OSP.Event.OSP_CREATE_SIMULATION,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_CREATE_SIMULATION: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>workbench.handleCreateSimulation(e.portletId,e.data.title,'<%=serveResourceURL.toString()%>');
	}
});
Liferay.on(OSP.Event.OSP_DELETE_SIMULATION,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_DELETE_SIMULATION: ['+e.portletId+', '+new Date()+']', e.data);
		<portlet:namespace/>workbench.handleDeleteSimulation(e.portletId, e.data.simulationUuid, '<%=serveResourceURL.toString()%>');
		
		var simulation = <portlet:namespace/>workbench.workingSimulation();
		if(simulation.uuid()===e.data.simulationUuid){
			<portlet:namespace/>activeBlockLayout(true);
		}
	}
});

Liferay.on(OSP.Event.OSP_CREATE_JOB,function( e ){
	if( <portlet:namespace/>workbench.id() == e.targetPortlet ){
			console.log('OSP_CREATE_JOB: ['+e.portletId+', '+new Date()+']', e );
			<portlet:namespace/>workbench.handleCreateJob( e.portletId, e.simulationUuid, e.title, e.data, '<%=serveResourceURL.toString()%>' );
	}
});
Liferay.on(OSP.Event.OSP_DELETE_JOB,function( e ){
	if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
		console.log('OSP_DELETE_JOB: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>workbench.handleDeleteJob(e.portletId, e.data.simulationUuid, e.data.jobUuid, '<%=serveResourceURL.toString()%>' );
		
		var simulation = <portlet:namespace/>workbench.workingSimulation();
		var job = simulation.workingJob();
		
		if(job.uuid()===e.data.jobUuid){
			<portlet:namespace/>activeBlockLayout(true);
		}
	}
});
/***********************************************************************
 * Global Function section
 ***********************************************************************/
function errlog(eventData, msg){
	if(console){
		console.log("Unknown event data: " + (msg ? msg : "") + "\n", eventData);
	}
}

function <portlet:namespace/>activeBlockLayout(status){
	$blockSection = $("section#no-job-layout-area");
	$layoutSection = $("section#workbench-layout-area");
	
	if(status){
		$blockSection.css("display","block");
		$layoutSection.css("display","none");
	}else{
		$blockSection.css("display","none");
		$layoutSection.css("display","block");
	}
	
}
function <portlet:namespace/>displayInit(){
	if($("#loadingBox").css("display") != "none"){
		var eventData = {
				targetPortlet: 'BROADCAST',
				data : {
					simulationUuid : ''
				}
			};
			
			Liferay.fire(OSP.Event.OSP_REFRESH_SIMULATIONS, eventData);
	}
	bEnd();
}

</script>

<script src="${contextPath}/js/jquery-knob/jquery.knob.min.js"></script>
<script src="${contextPath}/js/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="${contextPath}/js/fastclick/fastclick.js"></script>
<script src="${contextPath}/js/adminlte/adminlte.js"></script>
<script src="${contextPath}/js/lib/toastr.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>