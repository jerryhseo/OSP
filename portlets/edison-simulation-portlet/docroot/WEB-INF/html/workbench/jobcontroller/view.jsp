<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<portlet:resourceURL var='downManualFileURL' id='downManualFile' escapeXml="false"></portlet:resourceURL>

<style type="text/css">

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
			<li class="divider-vertical" id="<portlet:namespace/>simulation-li-divider"></li>
			<li id="<portlet:namespace/>header-li-new"  style="display: none;">
				<i class="fa fa-plus-square-o fa-2x"></i><br>
				<span class="nav-icon-text">New</span>
			</li>
			<li id="<portlet:namespace/>header-li-save"  style="display: none;">
				<i class="fa fa-save fa-2x"></i><br>
				<span class="nav-icon-text">Save</span>
			</li>
			<li id="<portlet:namespace/>header-li-wf-copy"  style="display: none;">
				<i class="fa fa-copy fa-2x"></i><br>
				<span class="nav-icon-text">Copy</span>
			</li>
			<li id="<portlet:namespace/>header-li-copy"  style="display: none;">
				<i class="fa fa-copy fa-2x"></i><br>
				<span class="nav-icon-text">Copy</span>
			</li>
			<li id="<portlet:namespace/>header-li-delete"  style="display: none;">
				<i class="fa fa-trash-o fa-2x"></i><br>
				<span class="nav-icon-text">Delete</span>
			</li>
			<li id="<portlet:namespace/>header-li-select"  style="display: none;">
				<i class="fa fa-check-square-o fa-2x"></i><br>
				<span class="nav-icon-text">Select</span>
			</li>
			<li class="divider-vertical"></li>
			<li id="<portlet:namespace/>header-li-submit"  style="display: none;">
				<i class="fa fa-cloud-upload fa-2x"></i><br>
				<span class="nav-icon-text">Submit</span>
			</li>
			<li id="<portlet:namespace/>header-li-cancel"  style="display: none;">
				<i class="fa fa-window-close-o fa-2x"></i><br>
				<span class="nav-icon-text">cancel</span>
			</li>
			<li id="<portlet:namespace/>header-li-log"  style="display: none;">
				<i class="fa fa-desktop fa-2x"></i><br>
				<span class="nav-icon-text">Log</span>
			</li>
			<li id="<portlet:namespace/>header-li-download"  style="display: none;">
				<i class="fa fa-cloud-download fa-2x"></i><br>
				<span class="nav-icon-text">Download</span>
			</li>
			<li class="divider-vertical <portlet:namespace/>header-manual"></li>
			<li id="<portlet:namespace/>header-li-data"  style="display: none;">
				<i class="fa fa-share-square-o fa-2x"></i><br>
				<span class="nav-icon-text">Open Data</span>
			</li>
			<li class="<portlet:namespace/>header-manual" id="<portlet:namespace/>header-li-manual">
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
		"event" : OSP.Event.OSP_SAVE_SIMULATION
	},
	"copy":{
		"event" : OSP.Event.OSP_REQUEST_SIMULATION_MODAL
	},
	"delete":{
		"event" : OSP.Event.OSP_REQUEST_DELETE_JOB_VIEW
	},
	"select":{
		"event" : OSP.Event.OSP_REQUEST_DELETE_JOB_VIEW
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
		"event" : OSP.OSP_REQUEST_JOB_LOG_VIEW
	},
	"download":{
		"event" : OSP.Event.OSP_REQUEST_JOB_RESULT_VIEW
	},
	"data":{
		"event" : OSP.Event.OSP_REQUEST_NEW_JOB_VIEW
	}
};


var <portlet:namespace/>wfLiObj 	 = {"simulation":false,"edit":false,"new":false,"wf-copy":true,"copy":false,"delete":false,"select":true,"data":false};
var <portlet:namespace/>defaultLiObj = {"simulation":true,"edit":true,"new":true,"save":true,"wf-copy":false,"copy":true,"delete":true,"select":false,"submit":true,"cancel":false,"log":false,"download":false,"data":false};
var <portlet:namespace/>submitLiObj  = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":false,"delete":false,"select":false,"submit":false,"cancel":true,"log":true,"download":false,"data":false};
var <portlet:namespace/>cancelLiObj  = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":true,"delete":true,"select":false,"submit":false,"cancel":false,"log":true,"download":true,"data":false};
var <portlet:namespace/>successLiObj = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":true,"delete":true,"select":false,"submit":false,"cancel":false,"log":true,"download":true,"data":true};
var <portlet:namespace/>failLiObj    = {"simulation":true,"edit":true,"new":true,"save":false,"wf-copy":false,"copy":true,"delete":true,"select":false,"submit":false,"cancel":false,"log":true,"download":true,"data":false};

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
		Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
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
	}
});

Liferay.on(OSP.Event.OSP_REFRESH_JOB_STATUS,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		console.log('OSP_REFRESH_JOB_STATUS: ['+e.portletId+', '+new Date()+']', e.data);
		<portlet:namespace/>displayChange(nullToStr(e.data.jobStatus),e.data.workbenchType);
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
		$(".<portlet:namespace/>header-manual").css("display","block");
		$("#<portlet:namespace/>header-li-manual").bind("click",function(){
			<portlet:namespace/>manualDownLoad(data.currentManualId());
		});
	}else{
		$(".<portlet:namespace/>header-manual").css("display","none");
	}
}


function <portlet:namespace/>manualDownLoad(manualId){
	window.location.href="<%=downManualFileURL.toString()%>&<portlet:namespace/>fileEntryId="+manualId;
}

function <portlet:namespace/>displayChange(status,workBenchType){
	var liObj;
	if(status=="SUCCESS"){
		liObj = <portlet:namespace/>successLiObj;
	}else if(status=="FAILED"){
		liObj = <portlet:namespace/>failLiObj;
	}else if(status=="QUEUED"){
		liObj = <portlet:namespace/>submitLiObj;
	}else if(status=="RUNNING"){
		liObj = <portlet:namespace/>submitLiObj;
	}else if(status=="CANCELED"){
		liObj = <portlet:namespace/>cancelLiObj;
	}else{
		liObj = <portlet:namespace/>defaultLiObj;
	}
	
	if(workBenchType==='SIMULATION_WITH_WORKFLOW'){
		for(var key in <portlet:namespace/>wfLiObj){
			liObj[key]=<portlet:namespace/>wfLiObj[key];
		}
		
		$("#<portlet:namespace/>simulation-li-divider").remove();
	}
	
	for(var key in liObj){
		var element = $("#<portlet:namespace/>header-li-"+key);
		if(liObj[key]){
			element.css("display","block");
			element.bind("click",{key:key},function(e){
				e.stopPropagation();
				e.preventDefault();
				<portlet:namespace/>liEventFire(e.data.key);
			});
		}else{
			element.off();
			element.css("display","none");
		}
	}
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
					var myId = '<%=portletDisplay.getId()%>';
					var eventData = {
							portletId : myId,
							targetPortlet : <portlet:namespace/>connector
							
					};
					Liferay.fire(object.event, eventData);
					
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
		
		if(eventKey=='copy'){eventData.isCopy = true}
		if(eventKey=='simulation'){eventData.isCopy = false}
		
		console.log(eventData);
		Liferay.fire(object.event, eventData);
	}
	
}
</script>


