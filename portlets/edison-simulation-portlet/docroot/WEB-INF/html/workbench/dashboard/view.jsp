<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="searchSimulationURL" id="searchSimulation" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchSimulationWithPageURL" id="searchSimulationWithPage" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:resourceURL var="searchSimulationJobURL" id="searchSimulationJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchSimulationJobInfoURL" id="searchSimulationJobInfo" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="updateSimulationURL" id="updateSimulation" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="updateSimulationJobURL" id="updateSimulationJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="readOutLogURL" id="readOutLog" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchProjectURL" id="searchProject" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="addProjectShareJobURL" id="addProjectShareJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="removeProjectShareURL" id="addProjectShareJob" copyCurrentRenderParameters="false" escapeXml="false"/>


<script src="${contextPath}/js/lib/jquery.mustache.js"></script>
<script src="${contextPath}/js/lib/mustache.min.js"></script>
<style type="text/css">
	.treeview-menu > li.active > a{
		color: #ff5454 !important;
	 }
</style>
<ul class="sidebar-menu top" data-widget="tree" id="<portlet:namespace/>sidebar-menu">
	<li class="header" id="<portlet:namespace/>side-job-status"></li>
	<li id="<portlet:namespace/>pagin" class="text-center">
	
	</li>
</ul>
<ul class="sidebar-menu bottom" data-widget="tree">
	<li>
		<div class="sidebar-toggle-wrapper" class="sidebar-toggle" id="sidebar-toggle" data-toggle="push-menu" role="button"  >
			<a href="javascript:void(0);" class="sidebar-toggle" >
				<i class="fa fa-angle-left fa-2x pull-right"></i>
				<span class="sr-only">Toggle navigation</span>
			</a>
		</div>
	</li>
</ul>
<script type="text/javascript">
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>parentNamespace;
var <portlet:namespace/>connector;
var <portlet:namespace/>jqPortletBoundaryId;
var <portlet:namespace/>scienceAppId;
var <portlet:namespace/>refreshJobLogTimer;
var <portlet:namespace/>workSimulationId;
var <portlet:namespace/>workSimulationJobId;
var <portlet:namespace/>scienceAppNameAndVerstion="";		// 2018.03.26, 현재 실행중인 ScienceApp의 이름과 버전
var <portlet:namespace/>deleteSimulationJobTitle="";			// 2018.03.26, SimulationJob 삭제 시 해당 데이터의 이름

var <portlet:namespace/>scienceApp = new OSP.ScienceApp();

var <portlet:namespace/>searchJobLine = 5;
var <portlet:namespace/>simulationIsCopy = false;

var <portlet:namespace/>refreshTimer;
var <portlet:namespace/>portOuterHtmlArray = new Array();

var <portlet:namespace/>STATUS_READY = false;


var <portlet:namespace/>prevStatus = (function(){
	var jobStatus = {};
	var thisCheck = [];
	var page = 1;
	return {
		log: function(){
			console.log("jobStatus", jobStatus);
			console.log("thisCheck", thisCheck);
		},
		isExist: function(jobUuid){
			return jobStatus.hasOwnProperty(jobUuid);
		},
		isCheckExist: function(){
			return thisCheck.length>0?true:false;
		},
		isThisTimeSuccess: function(jobUuid, status){
			// 1701011 : success
			return jobStatus[jobUuid] != 1701011 && status == 1701011;
		},
		setJobStatus: function(jobUuid, status){
			jobStatus[jobUuid] = status;
			thisCheck.push(jobUuid);
		},
		getJobStatus: function(jobUuid){
			if(jobStatus.hasOwnProperty(jobUuid)){
				return jobStatus[jobUuid];
			}else{
				return 0;
			}
		},
		clearStatusCache: function(){
			if(thisCheck.length > 0){
				for(var key in jobStatus){
					if($.inArray(key, thisCheck) === -1){
						delete jobStatus[key];
					}
				}
				thisCheck = [];
			}
		},
		setJobPage:function(paging){
			page = paging;
		},
		getJobPage:function(){
			return page;
		}
		
	};
})();

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
		Liferay.fire( OSP.Event.OSP_REQUEST_APP_INFO, eventData);
		
		<portlet:namespace/>init();
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_APP_INFO, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>scienceAppId = e.data.scienceApp.id();
		<portlet:namespace/>scienceAppNameAndVerstion = e.data.scienceApp.name() + " " + e.data.scienceApp.version();

		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
			portletId: myId,
			targetPortlet: e.portletId
		}
		Liferay.fire( OSP.Event.OSP_REQUEST_PORT_INFO, eventData );
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_PORT_INFO, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		console.log('OSP_RESPONSE_PORT_INFO: ['+e.portletId+', '+new Date()+']', e.data.scienceApp);
		<portlet:namespace/>scienceApp = e.data.scienceApp;
		
		var inputPorts = <portlet:namespace/>scienceApp.inputPortsArray(); 
		<portlet:namespace/>settingPorts(inputPorts, OSP.Enumeration.PortType.INPUT);
		
		var logPorts = <portlet:namespace/>scienceApp.logPortsArray(); 
		<portlet:namespace/>settingPorts(logPorts, OSP.Enumeration.PortType.LOG);

		var outputPorts = <portlet:namespace/>scienceApp.outputPortsArray(); 
		<portlet:namespace/>settingPorts( outputPorts, OSP.Enumeration.PortType.OUTPUT);
		
		
		<portlet:namespace/>STATUS_READY = true;
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_CREATE_SIMULATION_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		if(e.data){
			toastr["success"]("", Liferay.Language.get('edison-data-insert-success'));
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-insert-error'));
		}
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_DELETE_SIMULATION_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		var status = e.data.status;
		var simulationUuid = e.data.simulationUuid;
		if(status){
			toastr["success"]("", Liferay.Language.get('edison-data-delete-success'));
			<portlet:namespace/>removeProjectShare(simulationUuid,'SIMULATION');
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-delete-error'));
		}
	}
});
Liferay.on(OSP.Event.OSP_RESPONSE_CREATE_SIMULATION_JOB_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		if(e.data.status){
			toastr["success"]("", Liferay.Language.get('edison-data-insert-success'));
			<portlet:namespace/>searchSimulationJob(e.data.simulationUuid);
			<portlet:namespace/>selectRow(e.data.simulationUuid,e.data.jobUuid);
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-insert-error'));
		}
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_CANCLE_SIMULATION_JOB_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
// 	if(e.targetPortlet === myId){
		if(e.data.status){
			toastr["success"]("", Liferay.Language.get('edison-data-update-success'));
			<portlet:namespace/>syncJobStatusList(<portlet:namespace/>workSimulationId);
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
		}
// 	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_DELETE_SIMULATION_JOB_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		if(e.data.status){
			toastr["success"]("", Liferay.Language.get('edison-data-delete-success'));
			$("li#<portlet:namespace/>job-"+e.data.jobUuid).remove();
			<portlet:namespace/>selectRow(e.data.simulationUuid);
			<portlet:namespace/>removeProjectShare(e.data.jobUuid,'JOB');
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-delete-error'));
		}
	}
});


Liferay.on(OSP.Event.OSP_RESPONSE_SUBMIT_JOB_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		if(e.data.status){
			console.log('OSP_RESPONSE_SUBMIT_JOB_RESULT: ['+e.portletId+', '+new Date()+']', e.data);
			toastr["success"]("", Liferay.Language.get('edison-job-submit-success'));
			//Global workSimulationJobId Setting
			<portlet:namespace/>workSimulationJobId = e.data.jobUuid;
			var jobSubmitCnt = e.data.jobSubmitCnt;
			
			/*************************************
			* Submit 작업이 단일 또는 복수에 따른 BL
			* JOB을 Submit 후에는 jobUuid가 변경됨
			**************************************/
			if(jobSubmitCnt>1){
				<portlet:namespace/>searchSimulationJob(e.data.simulationUuid);
				<portlet:namespace/>selectRow(e.data.simulationUuid,e.data.jobUuid);
			}else{
				/*NEW JOB STATUS Setting*/
				<portlet:namespace/>prevStatus.setJobStatus(e.data.jobUuid, <portlet:namespace/>prevStatus.getJobStatus(e.data.tempJobUuid));
				<portlet:namespace/>submitJobElementChange(e.data.tempJobUuid,e.data.jobUuid);
			}
		}else{
			toastr["error"]("", Liferay.Language.get('edison-job-submit-error'));
		}
	}
});


Liferay.on(OSP.Event.OSP_REFRESH_SIMULATIONS, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	console.log('OSP_REFRESH_SIMULATIONS: ['+e.portletId+', '+new Date()+']', e.data, e);
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		<portlet:namespace/>searchJobUUid = nullToStr(e.data.searchJobUuid);
		
		<portlet:namespace/>refreshSimulation(nullToStr(e.data.simulationUuid),nullToStr(e.data.searchJobUuid));
	}
});


Liferay.on(OSP.Event.OSP_RESPONSE_SIMULATION_MODAL, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		var isCopy = false;
		if(typeof e.data.isCopy !='undefined'){
			isCopy = e.data.isCopy;
		}
		<portlet:namespace/>searchSimulationModalOpen(1,isCopy);
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_SIMULATION_EDIT_VIEW, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		$("#<portlet:namespace/>sidebar-btn-edit-simulation" ).trigger( "click" );
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_NEW_JOB_VIEW, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		$("#<portlet:namespace/>sidebar-btn-new-simulation-job" ).trigger( "click" );
	}
});

Liferay.on(OSP.Event.OSP_REPONSE_DELETE_JOB_VIEW, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		<portlet:namespace/>deleteSimulationJobFormEvent(e.data);
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_JOB_RESULT_VIEW, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		var simulationUuid = e.data.simulationUuid;
		var jobUuid = e.data.jobUuid;
		<portlet:namespace/>jobResultFileView(simulationUuid, jobUuid);
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_JOB_LOG_VIEW, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		var simulationUuid = e.data.simulationUuid;
		var jobUuid = e.data.jobUuid;
		<portlet:namespace/>jobSystemLog(simulationUuid, jobUuid, 0, 'out');
	}
});



Liferay.on(OSP.Event.OSP_PORT_STATUS_CHANGED, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	console.log('OSP_PORT_STATUS_CHANGED: ['+e.portletId+', '+new Date()+']', e.data.status);
	
	$item = $("li.menu-open ul.treeview-menu.port-area li#<portlet:namespace/>"+e.data.portName);
	
	switch( e.data.status ){
		case OSP.Enumeration.PortStatus.READY:
			$item.addClass('SUCCESS');
			break;
		case OSP.Enumeration.PortStatus.LOG_VALID:
		case OSP.Enumeration.PortStatus.OUTPUT_VALID:
			$item.addClass('SUCCESS');
			break;
		case OSP.Enumeration.PortStatus.LOG_INVALID:
		case OSP.Enumeration.PortStatus.OUTPUT_INVALID:
			$item.addClass('SUCCESS');
			break;
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_COPY_JOB, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		var simulationUuid = e.data.simulationUuid;
		<portlet:namespace/>copyJobAndAddJob(simulationUuid)
	}
});


/***********************************************************************
 * Portlet AJAX Function
 ***********************************************************************/
var <portlet:namespace/>refreshSimulationTimer;
function <portlet:namespace/>refreshSimulation(simulationUuid,jobUuid){
	if(<portlet:namespace/>STATUS_READY){
		if(<portlet:namespace/>refreshSimulationTimer){
			clearInterval(<portlet:namespace/>refreshSimulationTimer);
		}
		<portlet:namespace/>searchSimulation(simulationUuid,jobUuid);
	}else{
		if (typeof <portlet:namespace/>refreshSimulationTimer === "undefined") {
			<portlet:namespace/>refreshSimulationTimer = setInterval(<portlet:namespace/>refreshSimulation, 500, simulationUuid,jobUuid);
		}
	}
}

function <portlet:namespace/>searchSimulation(simulationUuid,jobUuid){
	var eventData = {
			targetPortlet: <portlet:namespace/>connector,
			simulationUuid: simulationUuid
		};
	Liferay.fire( OSP.Event.OSP_REFRESH_URL_CHANGE, eventData );
	
	
	var scienceAppId = <portlet:namespace/>scienceAppId;
	var searchForm = {
		"<portlet:namespace/>scienceAppId": scienceAppId,
		"<portlet:namespace/>simulationUuid": simulationUuid,
		"<portlet:namespace/>jobUuid": jobUuid
		
	};
	
	$("#<portlet:namespace/>pagin").empty();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchSimulationURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			var searchData = result.searchJob;
// 			console.log(searchData);
			if(searchData!=null){
				$targetUL = $("#<portlet:namespace/>sidebar-menu");
				$topLi = $("<li/>").attr("id","<portlet:namespace/>job-"+searchData._jobUuid).prependTo($targetUL).addClass("treeview");
				$aWrapper = $("<a/>").attr("href","#").attr("data-simulation-uuid",searchData._simulationUuid)
							.attr("data-job-uuid",searchData._jobUuid)
							.attr("onclick","<portlet:namespace/>jobSelect(this);return false;")
							.appendTo($topLi);
				$("<i/>").addClass("fa fa-lg icon-search").appendTo($aWrapper);
				$("<span/>").attr("id","jobTitle").html("  "+cutStr(searchData._jobTitle,15)).appendTo($aWrapper);
				$("<i/>").addClass("fa fa-lg pull-right icon-info-sign sidebar-btn").css("cursor","pointer")
				 .attr("data-btn-type","search-job-info")
				 .attr("data-job-uuid",searchData._jobUuid)
				 .appendTo($aWrapper);
			}
			
			var simulation = result.simulation;
			if(simulation!=null){
				$targetLI = $("#<portlet:namespace/>side-job-status");
				$targetLI.empty();
				
				$("<i/>").addClass("fa fa-folder").appendTo($targetLI);
				$("<span/>").css("padding-left","5px").html(cutStr(simulation._simulationTitle,20)).appendTo($targetLI);
				if(simulationUuid === ''){
					<portlet:namespace/>workSimulationId = simulation._simulationUuid;
				}else{
					<portlet:namespace/>workSimulationId = simulationUuid;
				}
				
				/*adminLTE Tree Active*/
				var trees = $('[data-widget="tree"]');
				trees.tree();
				
				<portlet:namespace/>searchSimulationJob(<portlet:namespace/>workSimulationId);
				<portlet:namespace/>selectRow(<portlet:namespace/>workSimulationId,'');
			}else{
				<portlet:namespace/>searchSimulationModalOpen(1,false);
			}
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert("<portlet:namespace/>searchSimulation-->"+textStatus+": "+jqXHR.responseText);
			}else{
				alert("<portlet:namespace/>searchSimulation-->"+textStatus+": "+errorThrown);
			}  
		}
	});
}

function <portlet:namespace/>addSimulation(){
	var modal = $("#"+<portlet:namespace/>parentNamespace+"simulation-modal");
	
	if (<portlet:namespace/>isModalValidate(modal)) {
		var myId = '<%=portletDisplay.getId()%>';
		var data = {
				title : modal.find("form #title").val(),
				jobTitle : '#001',
				jobInitData : ''
			};
		
		var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector,
			data : data
		};
		
		Liferay.fire(OSP.Event.OSP_CREATE_SIMULATION, eventData);
		
		modal.modal('hide');
	}
}

function <portlet:namespace/>copyJobAndAddSimulation() {
	var modal = $("#"+<portlet:namespace/>parentNamespace+"simulation-modal");
	
	if (<portlet:namespace/>isModalValidate(modal)) {
	
		var workbench = window[<portlet:namespace/>parentNamespace+"workbench"];
		var simulation = workbench.workingSimulation();
		
		var jobTitle = '';
		var jobInitData = '';
		
		if(simulation){
			var job = simulation.workingJob();
			var simulationJobTitle = job.title_;
			var jobTitle = '';
			if(simulationJobTitle.indexOf('copy')==0){
				jobTitle= job.title_;
			}else{
				jobTitle= "copy  "+job.title_;
			}
			jobInitData = JSON.stringify(job.copyInputs());
		}
		
		
		var myId = '<%=portletDisplay.getId()%>';
		var data = {
				title : modal.find("form #title").val(),
				jobTitle : jobTitle,
				jobInitData : jobInitData
			};
		
		var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector,
			data : data
		};
		
		Liferay.fire(OSP.Event.OSP_CREATE_SIMULATION, eventData);
		
		modal.modal('hide');
	}
}

function <portlet:namespace/>selectRow(simulationUuid, jobUuid){
	$topUl = $("ul#<portlet:namespace/>sidebar-menu");
	var topSimultionSection = $("li#<portlet:namespace/>side-job-status");
	
	var size = $topUl.children("li.job-list").length;
	if(size==0){
		<portlet:namespace/>searchSimulationJob(simulationUuid);
	}
	var <portlet:namespace/>rowTimer;
	<portlet:namespace/>rowTimer = setTimeout(function(){
		if(nullToStr(jobUuid)===''){
			$topUl.find("li.job-list:first > a").trigger('click');
		}else{
			$topUl.find("li#<portlet:namespace/>job-"+jobUuid+" > a").trigger('click');
		}
		if(<portlet:namespace/>rowTimer){
			clearTimeout(<portlet:namespace/>rowTimer);
		}
	}, 500);
}


function <portlet:namespace/>loadSimulationJobs(currentPage){
	<portlet:namespace/>prevStatus.setJobPage(currentPage);
	<portlet:namespace/>prevStatus.clearStatusCache();
	<portlet:namespace/>searchSimulationJob(<portlet:namespace/>workSimulationId);
	<portlet:namespace/>selectRow(<portlet:namespace/>workSimulationId,'');
}

function <portlet:namespace/>searchSimulationJob(simulationUuid){
	/*Status Cache Clear*/
	<portlet:namespace/>prevStatus.clearStatusCache();
	
	/*Schedule Clear*/
	if(<portlet:namespace/>refreshTimer){
		clearInterval(<portlet:namespace/>refreshTimer);
	}
	
	/*Set Page*/
	var currentPage = <portlet:namespace/>prevStatus.getJobPage();
	
	var searchForm = {
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>paginFunction": "loadSimulationJobs",
			"<portlet:namespace/>searchLine": <portlet:namespace/>searchJobLine,
			"<portlet:namespace/>currentPage": currentPage
		};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchSimulationJobURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			var length = result.jobs.length;
			$targetObject = $("li#<portlet:namespace/>side-job-status").parent();
			$targetObject.find("li.job-list").remove();
			$paginLi = $("li#<portlet:namespace/>pagin");
			
			var isEdit = result.isEdit;
			/*권한이 있을 경우에만 수정 가능*/
			if(isEdit){
				$("li#<portlet:namespace/>job-edit-area").remove();
				$editLi = $("<li/>").attr("id","<portlet:namespace/>job-edit-area").css("display","none");
				$editLi.insertBefore($paginLi);
				$editSpan = $("<span/>").addClass("btn-group edit-btn-group").appendTo($editLi);
				
				
				$("<button/>").addClass("btn btn-default btn-sm sidebar-btn").attr("type","button").attr("title","Job Create")
							  .attr("data-simulation-uuid",simulationUuid)
							  .attr("job-count",result.totalCount)
							  .attr("data-btn-type","new-simulation-job")
							  .attr("id","<portlet:namespace/>sidebar-btn-new-simulation-job")
							  .html("<i class=\"fa fa-plus-circle\"></i>")
							  .appendTo($editSpan);
							  
				$("<button/>").addClass("btn btn-default btn-sm sidebar-btn").attr("type","button").attr("title","Edit Simulation")
							  .attr("data-simulation-uuid",simulationUuid)
							  .attr("data-btn-type","edit-simulation")
							  .attr("id","<portlet:namespace/>sidebar-btn-edit-simulation")
							  .html("<i class=\"fa fa-edit\"></i>")
							  .appendTo($editSpan);
			}
			
			if(length>0){
				var isUncompletedJobExist = false;
				for(var i = 0; i < length; i++) {
					var job = result.jobs[i];
// 					console.log(job);
					
					
					var jobStatus = job._jobStatus;
					var jobUuid = job._jobUuid;
					/*
					   - Status Setting
					   1701005 - QUEUED
					   1701006 - RUNNING 일 경우  Sync JOB ADD
					   1701011	SUCCESS
					   1701012	FAILED 일 경우 JOB Status Set OR UPDATE
					   isUncompletedJobExist 값에 의하여 Sync는 설정 되지 않음.
					...상태 UPDATE를 통하여 OSP_JOB_STATUS_CHANGED 가 중복 발생 되지 않음.
					*/
					if(jobStatus == 0 || jobStatus == 1701005 || jobStatus == 1701006){
						isUncompletedJobExist = true;
					}
					
					var jobStatusCss = "fa fa-sticky-note init";
					if(jobStatus==1701005||jobStatus==1701006){
						jobStatusCss = "fa fa-sticky-note running";
					}else if(jobStatus==1701010){
						jobStatusCss = "fa fa-sticky-note cancel";
					}else if(jobStatus==1701011){
						jobStatusCss = "fa fa-sticky-note success";
					}else if(jobStatus==1701012){
						jobStatusCss = "fa fa-sticky-note fail";
					}
					
					$topLi = $("<li/>").attr("id","<portlet:namespace/>job-"+job._jobUuid)
							.attr("job-status",jobStatus)
							.addClass("treeview job-list");
					$topLi.insertBefore($paginLi);
					
					$("<span/>").addClass("label label-primary pull-right  sidebar-btn").attr("data-btn-type","search-job-info").css("cursor","pointer")
								.append(
										$("<i/>").addClass("icon-arrow-right")
								).appendTo($topLi);
					$aWrapper = $("<a/>").attr("href","#").attr("data-simulation-uuid",simulationUuid).attr("data-job-uuid",job._jobUuid)
								.attr("onclick","<portlet:namespace/>jobSelect(this);return false;").appendTo($topLi);
					$("<i/>").addClass(jobStatusCss).appendTo($aWrapper);
					$("<span/>").attr("id","jobTitle").html(cutStr(job._jobTitle,15)).appendTo($aWrapper);
					
					
					/*job Status Chahe Setting*/
					if(<portlet:namespace/>prevStatus.getJobStatus(jobUuid)!=jobStatus){
						<portlet:namespace/>prevStatus.setJobStatus(jobUuid, jobStatus);
					}
					
				}
				
				/*setInterval Setting*/
				if(isUncompletedJobExist){
					<portlet:namespace/>refreshTimer = setInterval(<portlet:namespace/>syncJobStatusList, 5000, simulationUuid);
				}
				$("#<portlet:namespace/>pagin").html(result.pagingStr);
			}else{
				/*JOB 이 없을 경우 jobController에  Event 전달*/
				var eventData = {
						targetPortlet: <portlet:namespace/>connector
					};
				Liferay.fire( OSP.Event.OSP_REQUEST_JOB_CONTROLL_RESET, eventData );
			}
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				console.log("<portlet:namespace/>searchSimulationJob-->"+textStatus+": "+jqXHR.responseText);
			}else{
				console.log("<portlet:namespace/>searchSimulationJob-->"+textStatus+": "+errorThrown);
			}
			
			if(<portlet:namespace/>refreshTimer){
				clearInterval(<portlet:namespace/>refreshTimer);
			}
		}
	});
}

function <portlet:namespace/>syncJobStatusList(simulationUuid){
	if(<portlet:namespace/>prevStatus.isCheckExist()){
		/*Set Page*/
		var currentPage = <portlet:namespace/>prevStatus.getJobPage();
		
		var searchForm = {
				"<portlet:namespace/>simulationUuid": simulationUuid,
				"<portlet:namespace/>paginFunction": "loadSimulationJobs",
				"<portlet:namespace/>searchLine": <portlet:namespace/>searchJobLine,
				"<portlet:namespace/>currentPage": currentPage
			};
		
		
		jQuery.ajax({
			type: "POST",
			url: "<%=searchSimulationJobURL%>",
			async : false,
			data  : searchForm,
			dataType: 'json',
			success: function(result) {
				var length = result.jobs.length;
				if(length>0){
					var isUncompletedJobExist = false;
					for(var i = 0; i < length; i++) {
						var job = result.jobs[i];
						
						var jobStatus = job._jobStatus;
						var jobUuid = job._jobUuid;
						
						if(jobStatus == 0 || jobStatus == 1701005 || jobStatus == 1701006){
							isUncompletedJobExist = true;
						}
						
						if(<portlet:namespace/>prevStatus.isExist(jobUuid)&&<portlet:namespace/>prevStatus.getJobStatus(jobUuid)!=jobStatus){
							/*job status Update*/
							<portlet:namespace/>prevStatus.setJobStatus(jobUuid, jobStatus);
							
							/*View update*/
							var jobStatusCss = "fa fa-sticky-note init";
							if(jobStatus==1701005||jobStatus==1701006){
								jobStatusCss = "fa fa-sticky-note running";
							}else if(jobStatus==1701010){
								jobStatusCss = "fa fa-sticky-note cancel";
							}else if(jobStatus==1701011){
								jobStatusCss = "fa fa-sticky-note success";
							}else if(jobStatus==1701012){
								jobStatusCss = "fa fa-sticky-note fail";
							}
							
							$jobLi = $("ul#<portlet:namespace/>sidebar-menu").find("li#<portlet:namespace/>job-"+jobUuid);
							$jobLi.attr("job-status",jobStatus);
							
							$jobLi.children("a").children("i").removeClass().addClass(jobStatusCss);
							
							
							/*현재 실행 중인 작업 상태가 업데이트 되었을 경우*/
							if(jobUuid==<portlet:namespace/>workSimulationJobId){
								var eventData = {
									portletId: '<%=portletDisplay.getId()%>',
									targetPortlet: <portlet:namespace/>connector,
									data: {
										simulationUuid : simulationUuid,
										jobUuid : jobUuid
									}
								};
								
								Liferay.fire(OSP.Event.OSP_JOB_SELECTED, eventData);
							}
						}
					}
					/*setInterval Setting*/
					if(!isUncompletedJobExist&&<portlet:namespace/>refreshTimer){
						clearInterval(<portlet:namespace/>refreshTimer);
					}
				}
			},error:function(jqXHR, textStatus, errorThrown){
				if(jqXHR.responseText !== ''){
					console.log("<portlet:namespace/>syncJobStatusList-->"+textStatus+": "+jqXHR.responseText);
				}else{
					console.log("<portlet:namespace/>syncJobStatusList-->"+textStatus+": "+errorThrown);
				}
				
				if(<portlet:namespace/>refreshTimer){
					clearInterval(<portlet:namespace/>refreshTimer);
				}
			}
		});
	}
}

function <portlet:namespace/>submitJobElementChange(preJobUuid,currentJobUuid){
	/*Object Id Change*/
	$topLi = $("li#<portlet:namespace/>job-"+preJobUuid);
	$topLi.children("a").attr("data-job-uuid",currentJobUuid);
	$topLi.attr("id","<portlet:namespace/>job-"+currentJobUuid);
	
	/*jobStatus Sync*/
	<portlet:namespace/>syncJobStatusList(<portlet:namespace/>workSimulationId);
}

function <portlet:namespace/>searchSimulationJobInfo(jobUuid){
	var searchForm = {
			"<portlet:namespace/>jobUuid": jobUuid
		};
	var returnData; 
	jQuery.ajax({
		type: "POST",
		url: "<%=searchSimulationJobInfoURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			returnData =  result; 
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}
		}
	});
	return returnData;
}

function <portlet:namespace/>jobSelect(object){
	var simulationUuid = $(object).attr("data-simulation-uuid");
	var jobUuid = $(object).attr("data-job-uuid");
	
 	/*$("#<portlet:namespace/>sidebar-menu li.job-list.action").removeClass("action");*/
	$("#<portlet:namespace/>sidebar-menu li.action").removeClass("action");
	$("#<portlet:namespace/>job-"+jobUuid).addClass("action");
	
	var myId = '<%=portletDisplay.getId()%>';
	var data = {
		simulationUuid : simulationUuid,
		jobUuid : jobUuid
	};
	
	var eventData = {
		portletId: myId,
		targetPortlet: <portlet:namespace/>connector,
		data: data
	};
	
	Liferay.fire(OSP.Event.OSP_JOB_SELECTED, eventData);
	
	//Global workSimulationJobId Setting
	<portlet:namespace/>workSimulationJobId = jobUuid;
	
	/*Mustache Event*/
	if($(".menu-panel").is(':visible')){
		$("#<portlet:namespace/>job-"+jobUuid+" span:first-child").trigger('click');
	}
	
	/*Port Grid*/
	<portlet:namespace/>displayPort(object);
}

function <portlet:namespace/>updateSimulation(panelDataType, that, event){
	if (<portlet:namespace/>isValidate()) {
		var simulationUuid  = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulationUuid;
		var title  = <portlet:namespace/>PANEL_DATA[panelDataType].form.title;
		
		<portlet:namespace/>closePanel();
		<portlet:namespace/>updateSimulationAction(simulationUuid,title);
	}
}

function <portlet:namespace/>updateSimulationAction(simulationUuid,title){
	var updateForm = {
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>title": title
		};
	jQuery.ajax({
		type: "POST",
		url: "<%=updateSimulationURL%>",
		async : false,
		data  : updateForm,
		success: function(result) {
			$("li#<portlet:namespace/>side-job-status").children("span").html(title);
			toastr["success"]("", Liferay.Language.get('edison-data-update-success'));
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert("updateSimulation-->"+textStatus+": "+jqXHR.responseText);
			}else{
				alert("updateSimulation-->"+textStatus+": "+errorThrown);
			}
			toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
		}
	});
}

function <portlet:namespace/>deleteSimulation(panelDataType, that, event){
	$.confirm({
		boxWidth: '30%',
		useBootstrap: false,
		title: 'Confirm!',
		content: '<p>Simulation Delete??</p>',
		buttons: {
			confirm: function () {
				<portlet:namespace/>closePanel();
				var myId = '<%=portletDisplay.getId()%>';
				var data = {
						simulationUuid : <portlet:namespace/>PANEL_DATA[panelDataType].form.simulationUuid
					};
				
				var eventData = {
					portletId : myId,
					targetPortlet : <portlet:namespace/>connector,
					data : data
				};
				
				Liferay.fire(OSP.Event.OSP_DELETE_SIMULATION, eventData);
				
			},
			cancel: function () {
				
			}
		}
	});
}

function <portlet:namespace/>deleteSimulationFromModal(key,paging){
	$.confirm({
		boxWidth: '30%',
		useBootstrap: false,
		title: 'Confirm!',
		content: '<p>Simulation Delete??</p>',
		buttons: {
			confirm: function () {
				var myId = '<%=portletDisplay.getId()%>';
				var data = {
						simulationUuid : key
					};
				
				var eventData = {
					portletId : myId,
					targetPortlet : <portlet:namespace/>connector,
					data : data
				};
				
				Liferay.fire(OSP.Event.OSP_DELETE_SIMULATION, eventData);
				<portlet:namespace/>loadSimulationModal(paging);
			},
			cancel: function () {
				
			}
		}
	});
}

function <portlet:namespace/>addSimulationJob(panelDataType, that, event){
	if (<portlet:namespace/>isValidate()) {
		var simulationUuid  = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulationUuid;
		var title  = <portlet:namespace/>PANEL_DATA[panelDataType].form.title;
		
		var myId = '<%=portletDisplay.getId()%>';
		
		var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector,
			title : title,
			simulationUuid : simulationUuid
		};
		
		Liferay.fire(OSP.Event.OSP_CREATE_JOB, eventData);
		
		<portlet:namespace/>PANEL_DATA[panelDataType].form.title = "";
		
		<portlet:namespace/>closePanel();
	}
}
function <portlet:namespace/>updateSimulationJob(panelDataType, that, event){
	if (<portlet:namespace/>isValidate()) {
		var simulationUuid  = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._simulationUuid;
		var jobUuid  = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._jobUuid;
		var formTitle = nullToStr(<portlet:namespace/>PANEL_DATA[panelDataType].form.title);
		var title  = formTitle==''?<portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._jobTitle:formTitle;
		
		<portlet:namespace/>closePanel();
		
		var updateForm = {
				"<portlet:namespace/>jobUuid": jobUuid,
				"<portlet:namespace/>title": title
			};
		jQuery.ajax({
			type: "POST",
			url: "<%=updateSimulationJobURL%>",
			async : false,
			data  : updateForm,
			success: function(result) {
				$("#<portlet:namespace/>job-"+jobUuid).find("span#jobTitle").html(cutStr(title,15));
				
				/*Working Job Title Change*/
				var workbench = window[<portlet:namespace/>parentNamespace+"workbench"];
				var simulation = workbench.workingSimulation();
				var job = simulation.workingJob();
				job.title(title);
				
				toastr["success"]("", Liferay.Language.get('edison-data-update-success'));
			},error:function(jqXHR, textStatus, errorThrown){
				if(jqXHR.responseText !== ''){
					alert("updateSimulationJob-->"+textStatus+": "+jqXHR.responseText);
				}else{
					alert("updateSimulationJob-->"+textStatus+": "+errorThrown);
				}
				toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
			}
		});
	}
}

function <portlet:namespace/>deleteSimulationJobFormEvent(data){
	<portlet:namespace/>deleteSimulationJobAction(data.simulationUuid, data.jobUuid, data.jobTitle,false);
}


function <portlet:namespace/>deleteSimulationJob(panelDataType, that, event){
	
	var simulationUuid = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._simulationUuid;
	var jobUuid = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._jobUuid;
	var jobTitle = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._jobTitle;
		
	<portlet:namespace/>deleteSimulationJobAction(simulationUuid, jobUuid, jobTitle,true);
}


function <portlet:namespace/>deleteSimulationJobAction(simulationUuid, jobUuid, jobTitle,isClosePanel){
	$.confirm({
		boxWidth: '30%',
		useBootstrap: false,
		title: 'Confirm!',
		content: '<p>Simulation Job Delete??</p>',
		buttons: {
			confirm: function () {
				if(isClosePanel){
					<portlet:namespace/>closePanel();
				}
				
				<portlet:namespace/>deleteSimulationJobTitle = jobTitle;
				var myId = '<%=portletDisplay.getId()%>';
				var data = {
						simulationUuid : simulationUuid,
						jobUuid : jobUuid
					};
				
				var eventData = {
					portletId : myId,
					targetPortlet : <portlet:namespace/>connector,
					data : data
				};
				
				Liferay.fire(OSP.Event.OSP_DELETE_JOB, eventData);
				
			},
			cancel: function () {
				
			}
		}
	});
}

function <portlet:namespace/>shareSimulationJob(panelDataType, that, event){
	var modal = $("#"+<portlet:namespace/>parentNamespace+"share-modal");
	var shareArea = modal.find("#"+<portlet:namespace/>parentNamespace+"share-select-info");
	$shareAreaTbody = shareArea.find("tbody");
	$shareAreaTbody.empty();
	$saveButton = modal.find("button#"+<portlet:namespace/>parentNamespace+"save");
	$saveButton.attr("onclick","");
	
	var jobUuid = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._jobUuid;
	var jobTitle = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._jobTitle;
	
	var searchForm = {
		<portlet:namespace/>simulationUuid : <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._simulationUuid,
		<portlet:namespace/>jobUuid : jobUuid
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchProjectURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			console.log(result);
			var length = result.projectList.length;
			if(length!=0){
				for(var i=0;i<length;i++){
					var project = result.projectList[i];
					$tr = $("<tr></tr>").appendTo($shareAreaTbody);
					$td = $("<td></td>").appendTo($tr);
					
					$div = $("<div></div>").addClass("checkbox").css("margin-bottom","0px").appendTo($td);
					$label =$("<label></label>").appendTo($div);
					
					$("<input>").attr("type","checkbox").val(project.simulationProjectId).appendTo($label);
					$("<p></p>").text(project.title).appendTo($label);
				}
				
				var shareLength = result.shareList.length;
				if(shareLength!=0){
					for(var i=0;i<shareLength;i++){
						var share = result.shareList[i];
						$shareAreaTbody.find(":checkbox[value='"+share._customId+"']").attr("checked",true);
					}
				}
				
				$saveButton.attr("onclick","<portlet:namespace/>shareProjectByjobUuid('"+jobUuid+"','"+jobTitle+"')");
				$saveButton.css("display","block");
				
			}else{
				$tr = $("<tr></tr>").appendTo($shareAreaTbody);
				$("<td></td>").addClass("text-center").html(Liferay.Language.get('edison-search-no-result')).appendTo($tr)
				$saveButton.css("display","none");
			}
			modal.modal({ "backdrop": "static", "keyboard": false });
			
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert("shareSimulationJob-->"+textStatus+": "+jqXHR.responseText);
			}else{
				alert("shareSimulationJob-->"+textStatus+": "+errorThrown);
			}  
		}
	});
}

function <portlet:namespace/>shareProjectByjobUuid(jobUuid, jobTitle){
	var modal = $("#"+<portlet:namespace/>parentNamespace+"share-modal");
	var shareArea = modal.find("#"+<portlet:namespace/>parentNamespace+"share-select-info");
	
	<portlet:namespace/>closePanel();
	
	modal.modal('hide');
	
	var customIds = new Array();
	shareArea.find(":checkbox:checked").each(function(pi,po){
		customIds.push(po.value);
	});
	
	jQuery.ajax({
		type: "POST",
		url: "<%=addProjectShareJobURL%>",
		async : false,
		data:{
			"<portlet:namespace/>jobUuid": jobUuid,
			"<portlet:namespace/>customIds": customIds
		},
		success: function(result) {
			toastr["success"]("", Liferay.Language.get('edison-data-update-success'));
			<portlet:namespace/>writeTimeLineAboutShare(customIds, jobTitle, "add");
		},error:function(jqXHR, textStatus, errorThrown){
			toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
		}
	});
}

function <portlet:namespace/>removeProjectShare(uuid,removeShareType){
	var simulationUuid = 0;
	var jobUuid = 0;
	if(removeShareType==="SIMULATION"){
		simulationUuid = uuid;
	}else{
		jobUuid = uuid;
	}
	
	
	jQuery.ajax({
		type: "POST",
		url: "<%=removeProjectShareURL%>",
		async : false,
		data:{
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>jobUuid": jobUuid
		},
		success: function(result) {
			var removeJobList = result.removeJobList;
			var jobTitle = <portlet:namespace/>deleteSimulationJobTitle;		// 삭제된 SimulationJob의 Title
			var customIds = new Array();
			
			for(var i=0; i<removeJobList.length; i++){
				// 2018.03.26, simulationJob이 공유되었던 프로젝트의 Id 추출 
				customIds.push(removeJobList[i]._customId);
			}
			if(customIds.length > 0){
				<portlet:namespace/>writeTimeLineAboutShare(customIds, jobTitle, "delete");
			}
		},error:function(jqXHR, textStatus, errorThrown){
			
		}
	});
}

/* 시뮬레이션 공유 시간, 정보 - 타임라인 생성 */
function <portlet:namespace/>writeTimeLineAboutShare(customIds, jobTitle, writeType){
	var shareComment = "";
	
	if(writeType == "add"){
		shareComment = "<liferay-ui:message key='edison-share-simulation-success-message'/>";
	} else if (writeType == "delete"){
		shareComment = "<liferay-ui:message key='edison-simulation-execute-title'/><liferay-ui:message key='edison-share-cancel-message'/>";
	}
	
	var commentMessage = shareComment+"</br>" +
						 "<liferay-ui:message key='edison-search-science-app'/> : "+ <portlet:namespace/>scienceAppNameAndVerstion +"</br>" +
						 "<liferay-ui:message key='edison-simulation-execute-job-create-list-job-name'/> : "+jobTitle+"</br>";
	
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createResourceURL();
		portletURL.setPortletId("edisoncomment_WAR_edisonboard2016portlet");
		portletURL.setResourceId("writeTimeLineAboutShare");
		portletURL.setParameter("groupBoardSeq", customIds);		// projectId
		portletURL.setParameter("comment", commentMessage);
		
		jQuery.ajax({
			type: "POST",
			url: portletURL,
			async : false,
			dataType: 'json',
			success: function(result) {
				// 2018.03.26, 공유 내용 작성 후 삭제된 simulationJob의 Title은 공백 처리
				// 이미 삭제된 데이터이기 떄문에 Timeline 작성 목적 외 필요 없음
				<portlet:namespace/>deleteSimulationJobTitle = "";
			},error:function(jqXHR, textStatus, errorThrown){
				alert("<liferay-ui:message key='edison-share-timeline-failed-message'/>");
			}
		});
	});
}


function <portlet:namespace/>jobSystemLog(simulationUuid, jobUuid, lastPosition, type) {
	<portlet:namespace/>clearReadOutLogTimer();
	jQuery.ajax({
		url: '<%=readOutLogURL.toString()%>',
		type:'POST',
		dataType:'json',
		data:{
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>jobUuid": jobUuid,
			"<portlet:namespace/>lastPosition": lastPosition,
			"<portlet:namespace/>type": type
		},
		success:function(result){
// 			console.log(outLog);
			var modal = $("#"+<portlet:namespace/>parentNamespace+"job-log-modal");
			var textarea = modal.find("textarea#"+<portlet:namespace/>parentNamespace+"log-text");
			
			var isScrollMove = false;
			if(textarea[0].scrollTop==0){
				isScrollMove = true;
			}else if(textarea[0].scrollTop+textarea.outerHeight()>textarea.prop('scrollHeight')){
				isScrollMove = true;
			}
			
			var preTextareVal = textarea.text();
			textarea.empty();
			
			if(typeof result.outLog!='undefined'){
				if(lastPosition === 0){
					textarea.text(result.outLog.outLog);
				}else{
					textarea.text(preTextareVal+result.outLog.outLog);
				}
				
				if(result.jobStatus == '1701006'){
					<portlet:namespace/>refreshJobLogTimer = setInterval(<portlet:namespace/>jobSystemLog, 1000*3, simulationUuid,jobUuid,result.outLog.lastPosition,type);
				}
			}
			
			if(typeof result.errLog!='undefined'){
				var deviLog = '\n\n--------------------------ERROR LOG----------------------------\n';
				textarea.text(textarea.text()+deviLog+result.errLog.outLog);
			}
			
			if(isScrollMove){
				textarea.scrollTop(textarea.prop('scrollHeight'));
			}
			modal.modal({ "backdrop": "static", "keyboard": false });
		},error:function(jqXHR, textStatus, errorThrown){
// 			if(jqXHR.responseText !== ''){
// 				alert("jobSystemLog-->"+textStatus+": "+jqXHR.responseText);
// 			}else{
// 				alert("jobSystemLog-->"+textStatus+": "+errorThrown);
// 			}
			$.alert(Liferay.Language.get('edison-simulation-monitoring-log-file-is-not-exist'));
			<portlet:namespace/>clearReadOutLogTimer();
		}
	});
	
}

function <portlet:namespace/>clearReadOutLogTimer(){
	if(<portlet:namespace/>refreshJobLogTimer){
		clearInterval(<portlet:namespace/>refreshJobLogTimer);
	}
}

function <portlet:namespace/>jobResultFileView(simulationUuid, jobUuid) {
	$("body").css('overflow','hidden');
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPortletMode("view");
		portletURL.setWindowState("pop_up");
		portletURL.setParameter("jobUuid", jobUuid);
		portletURL.setPortletId("SimulationResultFileViewer_WAR_edisonsimulationportlet");
		Liferay.Util.openWindow({
			dialog: {
				width:1024,
				height:800,
				cache: false,
				draggable: false,
				resizable: false,
				modal: true,
				destroyOnClose: true,
				after: {
					render: function(event) {
						$("button.btn.close").on("click", function(e){
							$("body").css('overflow','');
						});
					}
				}
			},
			id: "simulationResultFileViewDialog",
			uri: portletURL.toString(),
			title: "Result File View"
		});
	});
}


function <portlet:namespace/>loadSimulationModal(currentPage) {
	<portlet:namespace/>searchSimulationModalOpen(currentPage,<portlet:namespace/>simulationIsCopy);
}

function <portlet:namespace/>searchSimulationModalOpen(currentPage,isCopy) {
	var currentSimulationUuid = <portlet:namespace/>workSimulationId;
	
	<portlet:namespace/>simulationIsCopy = isCopy;
	
	var scienceAppId = <portlet:namespace/>scienceAppId;
	var searchForm = {
		"<portlet:namespace/>scienceAppId": scienceAppId,
		"<portlet:namespace/>currentPage": currentPage,
		"<portlet:namespace/>searchLine": 10,
		"<portlet:namespace/>paginFunction": "loadSimulationModal"
	};
	
	var modal = $("#"+<portlet:namespace/>parentNamespace+"simulation-modal");
	var simulatinArea = modal.find("#"+<portlet:namespace/>parentNamespace+"simulation-area");
	var opendataArea = modal.find("#"+<portlet:namespace/>parentNamespace+"opendata-area");
	var simulationTheadTr = simulatinArea.find("thead").children("tr");
	
	$simulationTbody = simulatinArea.find("tbody");
	$simulationTbody.empty();
	
	
	$createButton = modal.find("button#"+<portlet:namespace/>parentNamespace+"create");
	if(isCopy){
		$createButton.attr("onclick","<portlet:namespace/>copyJobAndAddSimulation()");
		opendataArea.css("display","none");
		
		var checkoutBoxTh = modal.find("th#"+<portlet:namespace/>parentNamespace+"checkbox-th");
		checkoutBoxTh.css("display","none");
	}else{
		/*Create OpenData ALL Checkbox*/
		if(!simulationTheadTr.children('#'+<portlet:namespace/>parentNamespace+"checkbox-th").length){
			var allCheckBox = $("<input/>").attr("type","checkbox");
			var checkoutBoxTh = $("<th/>").attr("id",<portlet:namespace/>parentNamespace+"checkbox-th").addClass("text-center").append(allCheckBox);
			simulationTheadTr.prepend(checkoutBoxTh);
			
			allCheckBox.bind("click",function(e){
				if(allCheckBox.prop("checked")) {
					$simulationTbody.find("input[type=checkbox]").prop("checked",true);
				}else{
					$simulationTbody.find("input[type=checkbox]").prop("checked",false);
				}
				e.stopPropagation();
			});
			
			var openDataBtn = opendataArea.find("button#"+<portlet:namespace/>parentNamespace+"data");
			openDataBtn.bind("click",{modalName:<portlet:namespace/>parentNamespace+"simulation-modal"},function(e){
				<portlet:namespace/>openDataSimulation(e.data.modalName);
				e.stopPropagation();
				e.preventDefault(); 
			});
			opendataArea.css("display","block");
		}else{
			var checkoutBoxTh = modal.find("th#"+<portlet:namespace/>parentNamespace+"checkbox-th");
			checkoutBoxTh.children("input[type=checkbox]").prop("checked",false);

			checkoutBoxTh.css("display","block");
			
			opendataArea.css("display","block");
		}
		
		$createButton.attr("onclick","<portlet:namespace/>addSimulation()");
	}
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchSimulationWithPageURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			var length = result.simulaitons.length;
			if(length != 0) {
				for(var i = 0; i < length; i++) {
					var simulation = result.simulaitons[i];
// 					console.log(simulation);
					$tr = $("<tr></tr>").appendTo($simulationTbody);
					if(simulation._simulationUuid===currentSimulationUuid){
						$tr.css("background-color","bisque");
					}
					
					/*Create OpenData Checkbox*/
					if(!isCopy){
						$checkInput= $("<input/>").attr("type","checkbox").val(simulation._simulationUuid);
						$("<td></td>").addClass("text-center").css("vertical-align","middle").append($checkInput).appendTo($tr);
					}
					
					$input = $("<input/>").attr("type","text").attr("maxlength","20")
										  .addClass("form-control").val(simulation._simulationTitle)
										  .attr("data-init-val",simulation._simulationTitle)
										  .attr("data-simulation-uuid",simulation._simulationUuid);
					$("<td></td>").append($input).appendTo($tr);
					$("<td></td>").addClass("text-center").css("vertical-align","middle").html(simulation._simulationCreateDt).appendTo($tr);
					
					$btnGroup = $("<div/>").addClass("btn-group");
					$checkBtn = $("<button/>").addClass("btn btn-primary").html("<i class=\"fa fa-check\">"+" Select"+"</i>").appendTo($btnGroup);
					$deleteBtn = $("<button/>").addClass("btn btn-primary").html("<i class=\"fa fa-trash\">"+" Delete"+"</i>").appendTo($btnGroup);
					$("<td></td>").addClass("text-center").append($btnGroup).appendTo($tr);
					
					if(isCopy){
						$checkBtn.bind("click",{key:simulation._simulationUuid},function(e){
							modal.modal('hide');
							e.stopPropagation();
							e.preventDefault(); 
							<portlet:namespace/>copyJobAndAddJob(e.data.key);
						});
					}else{
						$checkBtn.bind("click",{key:simulation._simulationUuid},function(e){
							modal.modal('hide');
							e.stopPropagation();
							e.preventDefault(); 
							<portlet:namespace/>searchSimulation(e.data.key,'');
						});
					}
					
					$deleteBtn.bind("click",{key:simulation._simulationUuid},function(e){
						e.stopPropagation();
						e.preventDefault(); 
						<portlet:namespace/>deleteSimulationFromModal(e.data.key,currentPage);
					});
				}
				simulatinArea.find("#"+<portlet:namespace/>parentNamespace+"pagin").html(result.pagingStr);
				simulatinArea.css("display","block");
				
				modal.find("div#"+<portlet:namespace/>parentNamespace+"simulation-area input[type=text]").focusout(function() {
					var title = spaceDelete($(this).val());
					var initTitle = $(this).attr("data-init-val");
					if(title===''){
						$(this).val(initTitle);
					}else{
						if(title != initTitle){
							var simulationUuid = $(this).attr("data-simulation-uuid");
							<portlet:namespace/>updateSimulationAction(simulationUuid,title);
						}
					}
				});
				
				modal.find("#"+<portlet:namespace/>parentNamespace+"close-btn").css("display","block");
			}else{
				simulatinArea.find("#"+<portlet:namespace/>parentNamespace+"pagin").html("");
				simulatinArea.css("display","none");
				opendataArea.css("display","none");
				
				modal.find("#"+<portlet:namespace/>parentNamespace+"close-btn").css("display","none");
				
			}
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}  
		}
	});
	
	modal.modal({ "backdrop": "static", "keyboard": false });
}

function <portlet:namespace/>openDataSimulation(modalName){
	var modal = $("#"+modalName);
	var simulatinArea = modal.find("#"+<portlet:namespace/>parentNamespace+"simulation-area");
	
	
	var simulationTbody = simulatinArea.find("tbody");
	
	var simulationIds = [];
	simulationTbody.find("input[type=checkbox]:checked").each(function(){
		simulationIds.push($(this).val());
	});
	
	if(simulationIds.length!=0){
		modal.modal('hide');
		
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector,
				isTransType : 'TRANS_SIMULATION',
				data :{
					simulationIds : simulationIds
				}
		};
		Liferay.fire(OSP.Event.OSP_REQUEST_COLLECTION_VIEW, eventData);
	}
}

function <portlet:namespace/>copyJobAndAddJob(simulationUuid) {
	var workbench = window[<portlet:namespace/>parentNamespace+"workbench"];
	var simulation = workbench.workingSimulation();
	var job = simulation.workingJob();
	
	var myId = '<%=portletDisplay.getId()%>';
	var inputs = JSON.stringify(job.copyInputs());

	var simulationJobTitle = job.title_;
	var jobTitle =''; 
	if(simulationJobTitle.indexOf('copy')==0){
		jobTitle= job.title_;
	}else{
		jobTitle= "copy  "+job.title_;
	}
	
	var eventData = {
		portletId : myId,
		targetPortlet : <portlet:namespace/>connector,
		title : jobTitle,
		simulationUuid : simulationUuid,
		data:inputs
	};
	
	Liferay.fire(OSP.Event.OSP_CREATE_JOB, eventData);
	
	var modal = $("#"+<portlet:namespace/>parentNamespace+"simulation-modal");
	modal.modal('hide');
}

/*************************************************************************
 * Port Event
 *************************************************************************/
function <portlet:namespace/>settingPorts( ports, portType ){
	if( ports.length <=0 ){
		return;
	}
	
	var css = {};
	switch( portType ){
		case OSP.Enumeration.PortType.INPUT:
			css.IClass = 'fa fa-edit';
		break;
		case OSP.Enumeration.PortType.LOG:
			css.IClass = 'fa fa-check';
		break;
		case OSP.Enumeration.PortType.OUTPUT:
			css.IClass = 'fa fa-check';
		break;
	}
	
	$topA = $("<a/>").attr("href","javascript:void(0)");
	/*Add A tag element Objects*/
	$("<i/>").addClass("fa fa-laptop").appendTo($topA);
	$("<span/>").html(portType).appendTo($topA);
	$topSpan = $("<span/>").addClass("pull-right-container").appendTo($topA);
	$("<small/>").addClass("label pull-right bg-yellow").html(ports.length).appendTo($topSpan);
	
	for( var index in ports ){
		var port = ports[index];
		var portStatus = port.status();
		$li = $("<li/>").attr("onclick","<portlet:namespace/>selectPort(this,'"+port.name()+"','"+portType+"')")
					    .attr("id","<portlet:namespace/>"+port.name())
						.appendTo($topA);
		var $item = $("<a/>").attr("href","javascript:void(0)").html(
					"<i class=\""+css.IClass+"\"></i>"+port.name()+"</a>"
				).appendTo($li);
	}
	
// 	console.log($topA);
// 	console.log($topA.get(0).outerHTML);
	<portlet:namespace/>portOuterHtmlArray.push($topA.get(0).outerHTML);
}

function <portlet:namespace/>displayPort(object){
	$targetElement = $(object).parent();
	
	if($targetElement.children("ul").length==0){
		var topUl = $("<ul/>").addClass("treeview-menu port-area").appendTo($targetElement);
		var arrary = <portlet:namespace/>portOuterHtmlArray;
		for(var i=0;i<arrary.length;i++){
			topUl.append(arrary[i]);
		}
	}
}

function <portlet:namespace/>selectPort(object,name,portType){
	/*Port Click 시  job 상태에 따른 이벤트 제어*/
	var statusCode = $("li#<portlet:namespace/>job-"+<portlet:namespace/>workSimulationJobId).attr("job-status");
	
	if(statusCode<1701005){
		if(portType===OSP.Enumeration.PortType.LOG||portType===OSP.Enumeration.PortType.OUTPUT){
			toastr["error"]("", "Ports that are not viewable in the current job state");
			return false;
		}
	}else if(statusCode==1701006){
		if(portType===OSP.Enumeration.PortType.OUTPUT){
			toastr["error"]("", "Ports that are not viewable in the current job state");
			return false;
		}
	}
	
	<portlet:namespace/>flowLayoutUpdate(portType);
	
	var objectId = $(object).attr("id");
	$("#"+objectId).removeClass("active");
	
	$(object).addClass("active");
	$(object).siblings().removeClass("active");
	
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet:<portlet:namespace/>connector,
			portName: name,
			portType: portType
	};
	Liferay.fire( OSP.Event.OSP_PORT_SELECTED, eventData);
}

function <portlet:namespace/>flowLayoutUpdate(portType){
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet:<portlet:namespace/>connector,
			flowLayoutCode :portType.toUpperCase()
	};
	Liferay.fire( OSP.Event.OSP_REQUEST_FLOW_LAYOUT_CODE_UPDATE, eventData);
}
/***********************************************************************
 * Portlet Side btn event
 ***********************************************************************/
 var <portlet:namespace/>PANEL_DATA = {
	   "edit-simulation": {
			"boxtitle":"Edit Simulation",
			"col": 3,
			"body": "tpl-edit-simulation-panel-setting",
			"form": {},
			"btn": {
				"update": <portlet:namespace/>updateSimulation,
				"delete": <portlet:namespace/>deleteSimulation
			}
	   },
	   "new-simulation-job": {
			"boxtitle":"New Simulation Job",
			"col": 3,
			"body": "tpl-new-job-panel-setting",
			"form": {},
			"btn": {
				"create": <portlet:namespace/>addSimulationJob
			}
	   },
	   "search-job-info": {
			"boxtitle":"Job Infomation",
			"col": 4,
			"body": "tpl-job-infomation-panel-setting",
			"form": {},
			"btn": {
				"update": <portlet:namespace/>updateSimulationJob,
				"delete": <portlet:namespace/>deleteSimulationJob,
				"share": <portlet:namespace/>shareSimulationJob
			},
			"script-search":<portlet:namespace/>searchSimulationJobInfo
	   }
	};
function <portlet:namespace/>init(){
	
	/*IE 대응 tree event*/
	if(Liferay.Browser.isIe()){
		$('[data-widget="tree"]').tree();
	}
	
	/*tooltip*/
	$('[data-toggle="tooltip"]').tooltip(); 
	//Job System Log modal close event
	$("#"+<portlet:namespace/>parentNamespace+"job-log-modal").on('hidden.bs.modal', function () {
		<portlet:namespace/>clearReadOutLogTimer();
	})
	
	<portlet:namespace/>jqPortletBoundaryId = "#p_p_id" + <portlet:namespace/>parentNamespace;
	$.Mustache.addFromDom();
	
	
	$(<portlet:namespace/>jqPortletBoundaryId).on("click",".sidebar-btn",function(e){
		e.preventDefault();
		var btnType = $(this).attr("data-btn-type");
		var templateData = <portlet:namespace/>PANEL_DATA[btnType];
		if(templateData){
			<portlet:namespace/>activateLi(this);
			templateData["lest-col"] = 12-templateData["col"];

			$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
			
			if(templateData["script-search"]){
				if(btnType=="search-job-info"){
					var jobUuid = 0;
					if(typeof $(this).attr("data-job-uuid")=="undefined"){
						jobUuid = $(this).siblings("a").attr("data-job-uuid");
					}else{
						jobUuid = $(this).attr("data-job-uuid")
					}
					
					templateData.form = templateData["script-search"](jobUuid);
				}
				$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .box-body").mustache(templateData["body"], templateData);
			}else{
				
				if(btnType=="edit-simulation"){
					var simulationUuid = $(this).attr("data-simulation-uuid");
					
					templateData.form.title = $("li#<portlet:namespace/>side-job-status").children("span").html();
					templateData.form.simulationUuid = simulationUuid;
				}else if(btnType=="new-simulation-job"){
					var simulationUuid = $(this).attr("data-simulation-uuid");
					var jobCnt = $(this).attr("job-count");
					templateData.form.title = "#"+<portlet:namespace/>alpad(String(Number(jobCnt)+1),4,0);
					templateData.form.simulationUuid = simulationUuid;
				}
				
				$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .box-body").mustache(templateData["body"], templateData);
			}
			
			$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .func").each(function (_) {
				var thisName = $(this).attr("name");
				if (templateData.btn && templateData.btn[thisName]) {
					$(this).click(function (e) {
						templateData.btn[thisName](btnType, this, e);
					});
				}
			});
			
			$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .data-binded").change(function (e) {
                var thisValue = $(this).val();
                var thisName = $(this).attr("name");
                templateData.form[thisName] = thisValue;
            });
			
			if(templateData["footer"]){
				$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .box").append($.Mustache.render(templateData["footer"], templateData));
			}
			$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .menu-panel-close").click(function(e){
				e.preventDefault();
				$(".menu-panel").hide('slide', {direction:'left'}, 500);
				$(<portlet:namespace/>jqPortletBoundaryId + " .sidebar > .sidebar-menu li.view").removeClass("view");
			});
			
			$(".menu-panel").show('slide', {direction:'left'}, 500);
		}
	});
}

function <portlet:namespace/>alpad(s, padLength, padString){
	while(s.length < padLength)
		s = padString + s;
	return s;
}

function <portlet:namespace/>activateLi(jqLink){
	$(<portlet:namespace/>jqPortletBoundaryId + " .sidebar > .sidebar-menu li.view").removeClass("view");
	$(jqLink).parent("li").addClass("view");
}

function <portlet:namespace/>isValidate() {
	$("#" + <portlet:namespace/>parentNamespace +"menu-panel-box form").validator('validate');
	return $("#" + <portlet:namespace/>parentNamespace +"menu-panel-box form").find(".has-error").length === 0;
}

function <portlet:namespace/>isModalValidate(modal) {
	modal.find("form").validator('validate');
	return modal.find(".has-error").length === 0;
}

function <portlet:namespace/>closePanel() {
    $(".menu-panel").hide('slide', { direction: 'left' }, 500);
    $(<portlet:namespace/>jqPortletBoundaryId + " .sidebar > .sidebar-menu > li.active").removeClass("active");
}
</script>
<script id="tpl-menu-panel-box" type="text/html">
<div class="col-md-{{col}}">
	<div class="box box-solid">
		<div class="box-header with-border header-inner">
			<h3 class="box-title">{{boxtitle}}</h3>
			<div class="box-tools pull-right">
				<button type="button" class="btn btn-box-tool menu-panel-close"><i class="fa fa-times"></i></button>
			</div>
		</div>
		<div class="box-body">
		</div>
	</div>
</div>
<div class="col-md-{{lest-col}} box menu-panel-close op-0">
	
</div>
</script>
<script id="tpl-job-infomation-panel-setting" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Job Title</label>
      <input type="text" name="title" class="form-control data-binded" id="title" placeholder="{{form.simulation._jobTitle}}" value="{{form.simulation._jobTitle}}" maxlength="25">
      <div class="help-block with-errors"></div>
    </div>
	<table id="example2" class="table table-bordered table-hover">
		<tr>
			<th><liferay-ui:message key='status'/></th>
			<td>
				<img src="${contextPath}/images/monitoring/ko_KR/{{form.jobStatusImg}}" onerror='this.src="${contextPath}/images/monitoring/ko_KR/monitor_QUEUED.png"'/>
				{{form.jobStatusNm}}
			</td>
		</tr>
		{{#form.startDt}}
		<tr>
			<th><liferay-ui:message key='edison-simulation-execute-job-create-list-submit-time'/></th>
			<td>{{form.startDt}}</td>
		</tr>
		{{/form.startDt}}
		{{#form.endDt}}
		<tr>
			<th><liferay-ui:message key='edison-simulation-monitoring-table-header-complete-time'/></th>
			<td>{{form.endDt}}</td>
		</tr>
		{{/form.endDt}}
		{{#form.executeTime}}
		<tr>
			<th><liferay-ui:message key='edison-simulation-monitoring-table-header-running-time'/></th>
			<td>{{form.executeTime}} minute</td>
		</tr>
		{{/form.executeTime}}
		{{#form.logView}}
		<tr>
			<th>System Log (Out)</th>
			<td>
				<button class="btn btn-default icon-bar-chart" onclick="<portlet:namespace/>jobSystemLog('{{form.simulation._simulationUuid}}','{{form.simulation._jobUuid}}',0,'out');"></button>
			</td>
		</tr>
		{{/form.logView}}
		{{#form.resultFile}}
		<tr>
			<th><liferay-ui:message key='edison-simulation-monitoring-table-header-result-down'/></th>
			<td>
				<button class="btn btn-default icon-save" onclick="<portlet:namespace/>jobResultFileView('{{form.simulation._simulationUuid}}','{{form.simulation._jobUuid}}');"></button>
			</td>
		</tr>
		{{/form.resultFile}}
		<tr>
			<th><liferay-ui:message key='edison-table-list-header-is-project-share'/></th>
			<td>
				{{form.isShare}}
			</td>
		</tr>
	</table>
  </div>
  {{#form.isEdit}}
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat func" name="update">Save</button>
	<button type="button" class="btn btn-info btn-flat func" name="share">Share</button>
    <button type="button" class="btn btn-danger btn-flat pull-right func" name="delete">Delete</button>
  </div>
  {{/form.isEdit}}
</form>
</script>
<script id="tpl-edit-simulation-panel-setting" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.title}}" autofocus required maxlength="20"/>
      <div class="help-block with-errors"></div>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat func" name="update">Save</button>
    <button type="button" class="btn btn-danger btn-flat pull-right func" name="delete">Delete</button>
  </div>
</form>
</script>
<script id="tpl-new-job-panel-setting" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.title}}" autofocus required maxlength="25"/>
      <div class="help-block with-errors"></div>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat pull-right func" name="create">Create</button>
  </div>
</form>
</script>

