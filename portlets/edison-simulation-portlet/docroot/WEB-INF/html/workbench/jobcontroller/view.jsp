<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<portlet:resourceURL var='downManualFileURL' id='downManualFile' escapeXml="false"></portlet:resourceURL>

<liferay-portlet:resourceURL var="transferJobDataURL" escapeXml="false" id="transferJobData" copyCurrentRenderParameters="false"/>
<liferay-portlet:renderURL var="collectionPopupURL" portletName="sdrcommon_WAR_SDR_baseportlet" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="action" value="collectionPopup" />
	<portlet:param name="targetGroupId" value="${sdrGroupId}" />
	<portlet:param name="allCollection" value="true" />
</liferay-portlet:renderURL>

<style type="text/css">
	nav.workbench-custom-nav ul > li:not(.<portlet:namespace/>divider-vertical){
		text-align: center;
		padding: 12px;
		cursor: pointer;
	}
	
	nav.workbench-custom-nav .<portlet:namespace/>divider-vertical {
		height: 50px;
		margin: 9px;
		border-left: 2px solid #f2f2f2;
		border-right: 1px solid #ffffff;
	}

	.nav li.<portlet:namespace/>divider-vertical{
		display: none;
	}
	
	
</style>
<!-- App Name -->
<div class="logo">
	<div class="logo-lg">
		<h3 style="margin-top:10px;" id="<portlet:namespace/>appName"></h3>
		<h5 id="<portlet:namespace/>appVersion"></h5>
	</div>
	<div class="logo-sm">
		<i class="fa fa-microchip" style="color:#00a65a;"></i>
	</div>
</div>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar workbench-custom-nav">
	<!-- Navbar Left Menu -->
	<div class="navbar-left">
		<ul class="nav navbar-nav">
			<li id="<portlet:namespace/>header-li-simulation" style="display: none;">
				<i class="fa fa-cubes fa-2x"></i><br>
				<span class="nav-icon-text">Simulations</span>
			</li>
			<li id="<portlet:namespace/>header-li-edit"  style="display: none;">
				<i class="fa fa-cogs fa-2x"></i><br>
				<span class="nav-icon-text">Edit</span>
			</li>
			<li class="<portlet:namespace/>divider-vertical" id="<portlet:namespace/>job-li-divider"></li>
			<li id="<portlet:namespace/>header-li-new"  style="display: none;" data-divider="job-li-divider">
				<i class="fa fa-plus-square-o fa-2x"></i><br>
				<span class="nav-icon-text">New</span>
			</li>
			<li id="<portlet:namespace/>header-li-save"  style="display: none;" data-divider="job-li-divider">
				<i class="fa fa-save fa-2x"></i><br>
				<span class="nav-icon-text">Save</span>
			</li>
			<li id="<portlet:namespace/>header-li-wf-copy"  style="display: none;" data-divider="job-li-divider">
				<i class="fa fa-copy fa-2x"></i><br>
				<span class="nav-icon-text">Copy</span>
			</li>
			<li id="<portlet:namespace/>header-li-copy"  style="display: none;" data-divider="job-li-divider">
				<i class="fa fa-copy fa-2x"></i><br>
				<span class="nav-icon-text">Copy</span>
			</li>
			<li id="<portlet:namespace/>header-li-delete"  style="display: none;" data-divider="job-li-divider">
				<i class="fa fa-trash-o fa-2x"></i><br>
				<span class="nav-icon-text">Delete</span>
			</li>
			<li id="<portlet:namespace/>header-li-select"  style="display: none;" data-divider="job-li-divider">
				<i class="fa fa-check-square-o fa-2x"></i><br>
				<span class="nav-icon-text">Select</span>
			</li>
			<li class="<portlet:namespace/>divider-vertical" id="<portlet:namespace/>ib-li-divider"></li>
			<li id="<portlet:namespace/>header-li-submit"  style="display: none;" data-divider="ib-li-divider">
				<i class="fa fa-cloud-upload fa-2x"></i><br>
				<span class="nav-icon-text">Submit</span>
			</li>
			<li id="<portlet:namespace/>header-li-cancel"  style="display: none;" data-divider="ib-li-divider">
				<i class="fa fa-window-close-o fa-2x"></i><br>
				<span class="nav-icon-text">cancel</span>
			</li>
			<li id="<portlet:namespace/>header-li-log"  style="display: none;" data-divider="ib-li-divider">
				<i class="fa fa-desktop fa-2x"></i><br>
				<span class="nav-icon-text">Log</span>
			</li>
			<li id="<portlet:namespace/>header-li-download"  style="display: none;" data-divider="ib-li-divider">
				<i class="fa fa-cloud-download fa-2x"></i><br>
				<span class="nav-icon-text">Download</span>
			</li>
			<li class="<portlet:namespace/>divider-vertical" id="<portlet:namespace/>data-li-divider"></li>
			<li id="<portlet:namespace/>header-li-data"  style="display: none;" data-divider="data-li-divider">
				<i class="fa fa-share-square-o fa-2x"></i><br>
				<span class="nav-icon-text">Open Data</span>
			</li>
			<li id="<portlet:namespace/>header-li-manual" data-divider="data-li-divider">
				<i class="fa fa-book fa-2x"></i><br>
				<span class="nav-icon-text">Manual</span>
			</li>
		</ul>
	</div>
	<!-- Navbar Right Menu -->
</nav>

<script type="text/javascript">
/***********************************************************************
* Global variables
***********************************************************************/
var <portlet:namespace/>LI_EVENT = {
	"simulation":{
		"event" : OSP.Event.OSP_REQUEST_SIMULATION_MODAL
		
	},
	"edit":{
		"event" : OSP.Event.OSP_REQUEST_SIMULATION_EDIT_VIEW
	},
	"new":{
		"event" : OSP.Event.OSP_REQUEST_NEW_JOB_VIEW
	},
	"save":{
		"event" : OSP.Event.OSP_SAVE_SIMULATION
	},
	"wf-copy":{
		"event" : OSP.Event.OSP_REQUEST_COPY_JOB
	},
	"copy":{
		"event" : OSP.Event.OSP_REQUEST_SIMULATION_MODAL
	},
	"delete":{
		"event" : OSP.Event.OSP_REQUEST_DELETE_JOB_VIEW
	},
	"select":{
		"event" : OSP.Event.OSP_REQUEST_JOB_KEY
	},
	"submit":{
		"event" : OSP.Event.OSP_SUBMIT_JOB
	},
	"cancel":{
		"event" : OSP.Event.OSP_CANCEL_JOB,
		"isConfirm" : true,
		"title" : "Do you want to cancel the simulation job?"
	},
	"log":{
		"event" : OSP.Event.OSP_REQUEST_JOB_LOG_VIEW
	},
	"download":{
		"event" : OSP.Event.OSP_REQUEST_JOB_RESULT_VIEW
	},
	"data":{
		"event" : OSP.Event.OSP_REQUEST_COLLECTION_VIEW
	}
};


var <portlet:namespace/>wfLiObj 	 = {"simulation":false,"edit":false,"new":false,"wf-copy":true,"copy":false,"delete":false,"data":false};
var <portlet:namespace/>defaultLiObj = {"simulation":true,"edit":true,"new":true,"save":true,"wf-copy":false,"copy":true,"delete":true,"select":false,"submit":true,"cancel":false,"log":false,"download":false,"data":false};
var <portlet:namespace/>submitLiObj  = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":false,"delete":false,"select":false,"submit":false,"cancel":true,"log":true,"download":false,"data":false};
var <portlet:namespace/>cancelLiObj  = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":true,"delete":true,"select":false,"submit":false,"cancel":false,"log":true,"download":true,"data":false};
var <portlet:namespace/>successLiObj = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":true,"delete":true,"select":false,"submit":false,"cancel":false,"log":true,"download":true,"data":true};
var <portlet:namespace/>failLiObj    = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":true,"delete":true,"select":false,"submit":false,"cancel":false,"log":true,"download":true,"data":false};
var <portlet:namespace/>initLiObj    = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":false,"delete":false,"select":false,"submit":false,"cancel":false,"log":false,"download":false,"data":false};


var <portlet:namespace/>openDataTransSimulationIds = [];
var <portlet:namespace/>openDataTransJobId = '';
var <portlet:namespace/>openDataTransMode = '';
/***********************************************************************
* Initailization section and handling Liferay events
***********************************************************************/
Liferay.on(OSP.Event.OSP_HANDSHAKE, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet == myId){
		<portlet:namespace/>connector = e.portletId;
		<portlet:namespace/>parentNamespace = "_"+e.portletId+"_";
		
		var events = [
			OSP.Event.OSP_EVENTS_REGISTERED,
			OSP.Event.OSP_RESPONSE_APP_INFO
		];
		
		var eventData = {
				portletId: myId,
				targetPortlet: <portlet:namespace/>connector,
				data: events
			};
		Liferay.fire(OSP.Event.OSP_REGISTER_EVENTS, eventData );
	}
});

Liferay.on(OSP.Event.OSP_EVENTS_REGISTERED,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		var eventData = {
			portletId: myId,
			targetPortlet: e.portletId
		}
		Liferay.fire( OSP.Event.OSP_REQUEST_APP_INFO, eventData );
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_APP_INFO, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>init(e.data.scienceApp);
		<portlet:namespace/>displayChange("INIT",'',true);
	}
});


Liferay.on(OSP.Event.OSP_REFRESH_JOB_STATUS,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		console.log('OSP_REFRESH_JOB_STATUS: ['+e.portletId+', '+new Date()+']', e.data);
		<portlet:namespace/>displayChange(nullToStr(e.data.jobStatus),e.data.workbenchType,e.data.isEdit);
	} 
});

Liferay.on(OSP.Event.OSP_RESPONSE_JOB_CONTROLL_RESET,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		console.log('OSP_RESPONSE_JOB_CONTROLL_RESET: ['+e.portletId+', '+new Date()+']', e.data);
		<portlet:namespace/>displayChange("INIT",e.data.workbenchType,true);
	} 
});



Liferay.on(OSP.Event.OSP_RESPONSE_COLLECTION_VIEW,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		console.log('OSP_REQUEST_COLLECTION_VIEW: ['+e.portletId+', '+new Date()+']');
		
		var isTransType = typeof e.isTransType !='undefined'?e.isTransType:'TRANS_JOB';
		
		<portlet:namespace/>fn_collectionPopup(isTransType,e.data);
	} 
});

Liferay.on(OSP.Event.OSP_RESPONSE_SAVE_SIMULATION_RESULT, function( e ){
	console.log('OSP_RESPONSE_SAVE_SIMULATION_RESULT: ['+e.targetPortlet+', '+new Date()+']');
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		if(e.data){
			toastr["success"]("", Liferay.Language.get('edison-data-update-success'));
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
		}
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_JOB_KEY, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		console.log('OSP_RESPONSE_JOB_KEY: ['+e.targetPortlet+', '+new Date()+']');
		<portlet:namespace/>jobSelectResult(e.data.wfNodeId, e.data.simulationUuid,e.data.jobUuid)
	}
});




/***********************************************************************
* Portlet AJAX Function
***********************************************************************/
function <portlet:namespace/>init(scienceApp){
	<portlet:namespace/>drawAppInfomation(scienceApp);
}
function <portlet:namespace/>drawAppInfomation(data){
	$("#<portlet:namespace/>appName").html(cutStr(data.name(),12)).attr("title",data.name());
	$("#<portlet:namespace/>appVersion").html("Ver "+data.version());
	
	if(data.currentManualId()!=0){
		$("#<portlet:namespace/>header-li-manual").css("display","block");
		$("#<portlet:namespace/>data-li-divider").css("display","block");
		
		$("#<portlet:namespace/>header-li-manual").bind("click",function(){
			<portlet:namespace/>manualDownLoad(data.currentManualId());
		});
	}else{
		$("#<portlet:namespace/>header-li-manual").css("display","none");
	}
}


function <portlet:namespace/>manualDownLoad(manualId){
	window.location.href="<%=downManualFileURL.toString()%>&<portlet:namespace/>fileEntryId="+manualId;
}

function <portlet:namespace/>displayChange(status,workBenchType,isEdit){
	$("li.<portlet:namespace/>divider-vertical").css("display","none");
	
	var liObj;
	if(status=="SUCCESS"){
		liObj = $.extend({},<portlet:namespace/>successLiObj);
	}else if(status=="FAILED"){
		liObj = $.extend({},<portlet:namespace/>failLiObj);
	}else if(status=="QUEUED"){
		liObj = $.extend({},<portlet:namespace/>submitLiObj);
	}else if(status=="RUNNING"){
		liObj = $.extend({},<portlet:namespace/>submitLiObj);
	}else if(status=="CANCELED"){
		liObj = $.extend({},<portlet:namespace/>cancelLiObj);
	}else if(status=="INIT"){
		liObj = $.extend({},<portlet:namespace/>initLiObj);
	}else{
		liObj = $.extend({},<portlet:namespace/>defaultLiObj);
	}
	
	if(workBenchType==='SIMULATION_WITH_WORKFLOW'){
		for(var key in <portlet:namespace/>wfLiObj){
			liObj[key]=<portlet:namespace/>wfLiObj[key];
		}
		
		if(status=="SUCCESS"){
			liObj["select"] = true;
		}
	}
	
	if(!isEdit){
		liObj["delete"]=false;
		liObj["data"]=false;
	}
	
	for(var key in liObj){
		var element = $("#<portlet:namespace/>header-li-"+key);
		if(liObj[key]){
			element.css("display","block");
			element.unbind('click');
			element.bind("click",{key:key},function(e){
				e.stopPropagation();
				e.preventDefault();
				<portlet:namespace/>liEventFire(e.data.key);
			});
			
			var dividerId = element.attr("data-divider");
			$("#<portlet:namespace/>"+dividerId).css("display","block");
			
		}else{
			element.off();
			element.css("display","none");
		}
	}
	
	if(workBenchType==='SIMULATION_WITH_WORKFLOW'){
		$("li#<portlet:namespace/>job-li-divider").css("display","none");
	}
}

function <portlet:namespace/>jobSelect(){
	
}

function <portlet:namespace/>liEventFire(eventKey){
	var object = <portlet:namespace/>LI_EVENT[eventKey];
	var isConfirm = object.isConfirm==null||typeof object.isConfirm =="undefined"?false:object.isConfirm;
	
	if(isConfirm){
		$.confirm({
			boxWidth: '30%',
			useBootstrap: false,
			title: 'Confirm!',
			content: '<p>'+object.title+'</p>',
			buttons: {
				confirm: function () {
					if(object.script){
						var fn = window[object.script];
						if(typeof fn == 'function') {
							fn();
						}
					}else{
						var myId = '<%=portletDisplay.getId()%>';
						var eventData = {
								portletId : myId,
								targetPortlet : <portlet:namespace/>connector
								
						};
						Liferay.fire(object.event, eventData);
					}
				},
				cancel: function () {
					
				}
			}
		});
	}else{
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector
		};
		
		if(eventKey=='copy'){eventData.isCopy = true;}
		if(eventKey=='simulation'){eventData.isCopy = false;}
		if(eventKey=='data'){eventData.isTransType = 'TRANS_JOB';}
		
		console.log(object.event);
		Liferay.fire(object.event, eventData);
	}
	
}

function <portlet:namespace/>fn_collectionPopup(isTransType,data){
	/*global set*/
	<portlet:namespace/>openDataTransMode = isTransType;
	<portlet:namespace/>openDataTransSimulationIds = [];
	
	if(isTransType==='TRANS_JOB'){
		<portlet:namespace/>openDataTransSimulationIds.push(data.simulationUuid);
		<portlet:namespace/>openDataTransJobId = data.jobUuid;
	}else if(isTransType==='TRANS_SIMULATION'){
		<portlet:namespace/>openDataTransSimulationIds = data.simulationIds;
	}
	
	AUI().use('aui-base','liferay-portlet-url','aui-node', function(A) {
		Liferay.Util.openWindow({
			dialog : {
				constrain : true,
				modal : true,
				cache: false,
				destroyOnClose: true,
				width : '980px'
			},
			id : 'sdrcommon_collectionPopup',
			title : 'Collection Popup',
			uri : '${collectionPopupURL}'
		});
	});
}

function sdrcommon_collectionPopup(result){
	$.ajax({
		url: "${transferJobDataURL}",
		async: false,
		dataType: 'json',
		data : {
			"<portlet:namespace/>collectionId": result.value,
			"<portlet:namespace/>transMode": <portlet:namespace/>openDataTransMode,
			"<portlet:namespace/>simulations": <portlet:namespace/>openDataTransSimulationIds,
			"<portlet:namespace/>jobUuid":<portlet:namespace/>openDataTransJobId
		},
		method: 'POST',
		timeout: 10000,
	}).done(function (result) {
		if(result.isComplete){
			$.alert(Liferay.Language.get("edison-simulation-monitoring-export-job-success-msg")+ "<br/>" + result.msg.replace(/,/gi, ',<br/>'));
		}else{
			$.alert(Liferay.Language.get("edison-simulation-monitoring-export-job-fail-msg")+ "<br/>" + result.msg.replace(/,/gi, ',<br/>'));
		}
	}).error(function (msg) {
		$.alert(Liferay.Language.get("edison-simulation-monitoring-export-job-fail-msg"));
		console.log(msg);
	});
}

/*Workflow Return Value*/
function <portlet:namespace/>jobSelectResult(wfNodeId, simulationUuid,jobUuid){
	Liferay.Util.getOpener().setSimAndJobFromWorkbench(wfNodeId,simulationUuid,jobUuid);
}
</script>


