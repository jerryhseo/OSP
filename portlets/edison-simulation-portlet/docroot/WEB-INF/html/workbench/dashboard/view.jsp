<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="searchSimulationURL" id="searchSimulation" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchSimulationJobURL" id="searchSimulationJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchSimulationJobInfoURL" id="searchSimulationJobInfo" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="updateSimulationURL" id="updateSimulation" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="updateSimulationJobURL" id="updateSimulationJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="readOutLogURL" id="readOutLog" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="jobResultFileURL" id="jobResultFile" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchProjectURL" id="searchProject" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="addProjectShareJobURL" id="addProjectShareJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="removeProjectShareURL" id="addProjectShareJob" copyCurrentRenderParameters="false" escapeXml="false"/>


<portlet:resourceURL var='downManualFileURL' id='downManualFile' escapeXml="false"></portlet:resourceURL>

<script src="${contextPath}/js/lib/jquery.mustache.js"></script>
<script src="${contextPath}/js/lib/mustache.min.js"></script>
<style type="text/css">
	.treeview-menu > li.active > a{
		color: #ff5454 !important;
	 }
</style>
<ul class="sidebar-menu top" data-widget="tree" id="<portlet:namespace/>sidebar-menu">
	<li class="header">
		<div class="header-inner" id="<portlet:namespace/>appInfoHeader">
			<h2 id="<portlet:namespace/>appName"></h2>
			<h4 id="<portlet:namespace/>appVersion"></h4>
		</div>
	</li>
	<li>
		 <a href="#" class="sidebar-btn" data-btn-type="new-simulation"> <i class="fa fa-lg fa-file"></i> <span>New Simulation</span> </a>
	</li>
</ul>
<section id="<portlet:namespace/>pagin" class="text-center">
	
</section>
<ul class="sidebar-menu bottom" data-widget="tree">
	<li>
		<div class="sidebar-toggle-wrapper" class="sidebar-toggle" id="sidebar-toggle" data-toggle="push-menu" role="button"  >
			<a href="#" class="sidebar-toggle" >
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
var <portlet:namespace/>workSimulationJobId;


var <portlet:namespace/>prevStatus = (function(){
	var jobStatus = {};
	var thisCheck = [];
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
			return jobStatus[jobUuid];
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
		}
	};
})();
var <portlet:namespace/>refreshTimer;
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
		<portlet:namespace/>drawAppInfomation(e.data.scienceApp);
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
	if(e.targetPortlet === myId){
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
			<portlet:namespace/>searchSimulationJob(e.data.simulationUuid,'');
			<portlet:namespace/>selectRow(e.data.simulationUuid,e.data.jobUuid);
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-insert-error'));
		}
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_DELETE_SIMULATION_JOB_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		if(e.data.status){
			toastr["success"]("", Liferay.Language.get('edison-data-delete-success'));
			$("li#<portlet:namespace/>job-"+e.data.jobUuid).remove();
			<portlet:namespace/>selectRow(e.data.simulationUuid,'');
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
			toastr["success"]("", Liferay.Language.get('edison-job-submit-success'));
			<portlet:namespace/>searchSimulationJob(e.data.simulationUuid,e.data.jobId);
		}else{
			toastr["error"]("", Liferay.Language.get('edison-job-submit-error'));
		}
	}
});


Liferay.on(OSP.Event.OSP_REFRESH_SIMULATIONS, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		<portlet:namespace/>searchJobUUid = nullToStr(e.data.searchJobUuid);
		<portlet:namespace/>searchSimulation(nullToStr(e.data.simulationUuid),nullToStr(e.data.searchJobUuid),1);
	}
});


Liferay.on(OSP.Event.OSP_RESPONSE_SIMULATION_MODAL, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		<portlet:namespace/>searchSimulationModalOpen(1,e.data);
	}
});

/***********************************************************************
 * Portlet AJAX Function
 ***********************************************************************/
function <portlet:namespace/>drawAppInfomation(data){
	$("#<portlet:namespace/>appName").html(cutStr(data.name(),12));
	$("#<portlet:namespace/>appVersion").html(data.version());
	
	if(data.currentManualId()!=0){
		$targetDiv = $("#<portlet:namespace/>appInfoHeader");
		
		$("<button/>").addClass("btn btn-default btn-xs").attr("type","button")
			.append(
				$("<span/>").addClass("icon-book").html("  Manual")
			)
			.attr("onclick","<portlet:namespace/>manualDownLoad('"+data.currentManualId()+"')")
			.appendTo($targetDiv);
	}
}
 
function <portlet:namespace/>loadSimulations(currentPage){
	<portlet:namespace/>searchSimulation('','',currentPage);
}

function <portlet:namespace/>searchSimulation(simulationUuid,jobUuid,currentPage){
	var scienceAppId = <portlet:namespace/>scienceAppId;
	var searchForm = {
		"<portlet:namespace/>scienceAppId": scienceAppId,
		"<portlet:namespace/>simulationUuid": simulationUuid,
		"<portlet:namespace/>jobUuid": jobUuid,
		"<portlet:namespace/>currentPage": currentPage,
		"<portlet:namespace/>paginFunction": "loadSimulations",
		"<portlet:namespace/>searchLine": "3"
		
	};
	
	$("#<portlet:namespace/>sidebar-menu li").filter(".<portlet:namespace/>simulation-section" ).remove();
	$("#<portlet:namespace/>pagin").empty();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchSimulationURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			var searchData = result.searchJob;
			console.log(searchData);
			if(searchData!=null){
				$targetUL = $("#<portlet:namespace/>sidebar-menu");
				$topLi = $("<li/>").attr("id","<portlet:namespace/>job-"+searchData._jobUuid).appendTo($targetUL);
				$aWrapper = $("<a/>").attr("href","#").attr("data-simulation-uuid",searchData._simulationUuid)
							.attr("data-job-uuid",searchData._jobUuid)
							.attr("onclick","<portlet:namespace/>jobSelect(this)")
							.appendTo($topLi);
				$("<i/>").addClass("fa fa-lg icon-search").appendTo($aWrapper);
				$("<span/>").attr("id","jobTitle").html("  "+cutStr(searchData._jobTitle,15)).appendTo($aWrapper);
				$("<i/>").addClass("fa fa-lg pull-right icon-info-sign sidebar-btn").css("cursor","pointer")
				 .attr("data-btn-type","search-job-info")
				 .attr("data-job-uuid",searchData._jobUuid)
				 .appendTo($aWrapper);
			}
			
			var length = result.simulaitons.length;
			if(length != 0) {
				$targetUL = $("#<portlet:namespace/>sidebar-menu");
				for(var i = 0; i < length; i++) {
					var simulation = result.simulaitons[i];
// 					console.log(simulation);
					$topLi = $("<li/>").addClass("treeview <portlet:namespace/>simulation-section").attr("id","<portlet:namespace/>simulation-"+simulation._simulationUuid).appendTo($targetUL);
					$aWrapper = $("<a/>").attr("href","#")
								.attr("onclick","<portlet:namespace/>searchSimulationJob('"+simulation._simulationUuid+"','')").appendTo($topLi);
					$("<i/>").addClass("fa fa-lg fa-folder").appendTo($aWrapper);
					$("<span/>").attr("id","simulationTitle").html( cutStr(simulation._simulationTitle,20)).appendTo($aWrapper);
					$("<span/>").addClass("pull-right-container")
								.append(
										$("<i/>").addClass("fa fa-angle-left pull-right")
										)
								.appendTo($aWrapper);
				}
				
				$("#<portlet:namespace/>pagin").html(result.pagingStr);
				<portlet:namespace/>selectRow(simulationUuid,'');
			}else{
				<portlet:namespace/>searchSimulationModalOpen(1,'');
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

function <portlet:namespace/>selectRow(simulationUuid, jobUuid){
	setTimeout(function(){
		var topSimultionSection;
		if(nullToStr(simulationUuid)===''){
			topSimultionSection = $(".<portlet:namespace/>simulation-section:first");
		}else{
			topSimultionSection = $("li#<portlet:namespace/>simulation-"+simulationUuid);
		}
		
		if(!topSimultionSection.hasClass("menu-open")){
			topSimultionSection.children("a").trigger('click');
		}
		
		if(nullToStr(jobUuid)===''){
			topSimultionSection.find("li[id*=<portlet:namespace/>job-]:first > a").trigger('click');
		}else{
			topSimultionSection.find("li#<portlet:namespace/>job-"+jobUuid+" > a").trigger('click');
		}
		
	}, 500);
}


function <portlet:namespace/>searchSimulationJob(simulationUuid,selectJobId){
	var searchForm = {
			"<portlet:namespace/>simulationUuid": simulationUuid
		};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchSimulationJobURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			var length = result.jobs.length;
			$targetObject = $("li#<portlet:namespace/>simulation-"+simulationUuid);
			var topUl;
			if($targetObject.find("ul").length>0){
				$targetObject.find("ul").empty();
				topUl = $targetObject.find("ul");
			}else{
				topUl = $("<ul/>").addClass("treeview-menu");
				topUl.appendTo($targetObject);
			}
			
			
			var isEdit = result.isEdit;
			/*권한이 있을 경우에만 수정 가능*/
			if(isEdit){
				$editLi = $("<li/>").appendTo(topUl);
				$editSpan = $("<span/>").addClass("btn-group edit-btn-group").appendTo($editLi);
				
				
				$("<button/>").addClass("btn btn-default btn-sm sidebar-btn").attr("type","button").attr("title","Job Create")
							  .attr("data-simulation-uuid",simulationUuid)
							  .attr("data-btn-type","new-simulation-job")
							  .html("<i class=\"fa fa-plus-circle\"></i>")
							  .appendTo($editSpan);
							  
				$("<button/>").addClass("btn btn-default btn-sm sidebar-btn").attr("type","button").attr("title","Edit Simulation")
							  .attr("data-simulation-uuid",simulationUuid)
							  .attr("data-btn-type","edit-simulation")
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
					//Status Setting
					if(jobStatus == 0 || jobStatus == 1701005 || jobStatus == 1701006){
						<portlet:namespace/>prevStatus.setJobStatus(jobUuid, jobStatus);
						isUncompletedJobExist = true;
					}
					
					var jobStatusCss = "fa fa-circle";
					if(jobStatus==1701005||jobStatus==1701006){
						jobStatusCss = "fa fa-circle running";
					}else if(jobStatus==1701011){
						jobStatusCss = "fa fa-circle success";
					}else if(jobStatus==1701012){
						jobStatusCss = "fa fa-circle fail";
					}
					$topLi = $("<li/>").attr("id","<portlet:namespace/>job-"+job._jobUuid).appendTo(topUl);
					$("<span/>").addClass("label label-primary pull-right  sidebar-btn").attr("data-btn-type","search-job-info").css("cursor","pointer")
								.append(
										$("<i/>").addClass("icon-arrow-right")
								).appendTo($topLi);
					$aWrapper = $("<a/>").attr("href","#").attr("data-simulation-uuid",simulationUuid).attr("data-job-uuid",job._jobUuid)
								.attr("onclick","<portlet:namespace/>jobSelect(this)").appendTo($topLi);
					$("<i/>").addClass(jobStatusCss).appendTo($aWrapper);
					$("<span/>").attr("id","jobTitle").html(cutStr(job._jobTitle,15)).appendTo($aWrapper);
					
					
					if(typeof selectJobId != 'undefined'){
						if(job._jobUuid===nullToStr(selectJobId)){
							$topLi.addClass("active");
							
							if(<portlet:namespace/>prevStatus.isExist(selectJobId)&&<portlet:namespace/>prevStatus.getJobStatus(selectJobId)!=jobStatus){
								var eventData = {
										portletId: '<%=portletDisplay.getId()%>',
										targetPortlet:<portlet:namespace/>connector,
										data: {
											jobUuid:selectJobId,
											jobStatus:jobStatus
										}
								};
								Liferay.fire( OSP.Event.OSP_JOB_STATUS_CHANGED, eventData);
							}else if(jobUuid === selectJobId){
								/*submit 후 JOB ID가 변경되었을때 - 처리중*/
// 								<portlet:namespace/>jobSelect($aWrapper);
							}
						}
					}
						
				}
				
				if(<portlet:namespace/>refreshTimer){
					clearInterval(<portlet:namespace/>refreshTimer);
				}
				
				//setInterval Setting
				if(isUncompletedJobExist){
					<portlet:namespace/>refreshTimer = setInterval(<portlet:namespace/>syncJobStatusList, 5000, simulationUuid);
				}
			}
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}  
		}
	});
}

var <portlet:namespace/>syncPreSimulationUuid = ''; 
function <portlet:namespace/>syncJobStatusList(simulationUuid){
	<portlet:namespace/>syncPreSimulationUuid = simulationUuid;
	
	if(<portlet:namespace/>prevStatus.isCheckExist()){
		<portlet:namespace/>searchSimulationJob(simulationUuid,<portlet:namespace/>workSimulationJobId);
	}
	
	if(<portlet:namespace/>syncPreSimulationUuid!=''&&<portlet:namespace/>syncPreSimulationUuid != simulationUuid){
		//<portlet:namespace/>prevStatus init
		<portlet:namespace/>prevStatus.clearStatusCache();
	}
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
	
	$("#<portlet:namespace/>sidebar-menu li.active").removeClass("active");
	$("#<portlet:namespace/>job-"+jobUuid).addClass("active");
	
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
}


function <portlet:namespace/>addSimulation(panelDataType, that, event){
	if (<portlet:namespace/>isValidate()) {
		var myId = '<%=portletDisplay.getId()%>';
		var data = {
				title : <portlet:namespace/>PANEL_DATA[panelDataType].form.title,
				jobTitle : '',
				jobInitData : ''
			};
		var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector,
			data : data
		};
		
		Liferay.fire(OSP.Event.OSP_CREATE_SIMULATION, eventData);
		
		<portlet:namespace/>PANEL_DATA[panelDataType].form.title = "";
		
		<portlet:namespace/>closePanel();
	}
}
function <portlet:namespace/>updateSimulation(panelDataType, that, event){
	if (<portlet:namespace/>isValidate()) {
		var simulationUuid  = <portlet:namespace/>PANEL_DATA[panelDataType].form.simulationUuid;
		var title  = <portlet:namespace/>PANEL_DATA[panelDataType].form.title;
		
		<portlet:namespace/>closePanel();
		
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
				$("#<portlet:namespace/>simulation-"+simulationUuid).find("span#simulationTitle").html(title);
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
function <portlet:namespace/>deleteSimulationJob(panelDataType, that, event){
	$.confirm({
		boxWidth: '30%',
		useBootstrap: false,
		title: 'Confirm!',
		content: '<p>Simulation Job Delete??</p>',
		buttons: {
			confirm: function () {
				<portlet:namespace/>closePanel();
				var myId = '<%=portletDisplay.getId()%>';
				var data = {
						simulationUuid : <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._simulationUuid,
						jobUuid : <portlet:namespace/>PANEL_DATA[panelDataType].form.simulation._jobUuid
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
				
				$saveButton.attr("onclick","<portlet:namespace/>shareProjectByjobUuid('"+jobUuid+"')");
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

function <portlet:namespace/>shareProjectByjobUuid(jobUuid){
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
			
		},error:function(jqXHR, textStatus, errorThrown){
			
		}
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
		success:function(outLog){
			console.log(outLog);
			var modal = $("#"+<portlet:namespace/>parentNamespace+"job-log-modal");
			var textarea = modal.find("textarea#"+<portlet:namespace/>parentNamespace+"log-text");
			var preTextareVal = textarea.text();
			
			textarea.empty();
			if(lastPosition === 0){
				textarea.text(outLog.outLog);
			}else{
				textarea.text(textarea+outLog.outLog);
			}
			
			if(outLog.jobStatus === 1701006){
				<portlet:namespace/>refreshJobLogTimer = setTimeout(<portlet:namespace/>readOutLog, 1000, simulationUuid,jobUuid,outLog.lastPosition);
			}
			
			modal.modal({ "backdrop": "static", "keyboard": false });
		},error:function(jqXHR, textStatus, errorThrown){
// 			if(jqXHR.responseText !== ''){
// 				alert("jobSystemLog-->"+textStatus+": "+jqXHR.responseText);
// 			}else{
// 				alert("jobSystemLog-->"+textStatus+": "+errorThrown);
// 			}
			$.alert(Liferay.Language.get('edison-simulation-monitoring-log-file-is-not-exist'));
		}
	});
	
}

function <portlet:namespace/>clearReadOutLogTimer(){
	if(<portlet:namespace/>refreshJobLogTimer){
		clearTimeout(<portlet:namespace/>refreshJobLogTimer);
	}
}


function <portlet:namespace/>jobResultFileView(simulationUuid, jobUuid) {
	jQuery.ajax({
		url: '<%=jobResultFileURL.toString()%>',
		type:'POST',
		dataType:'json',
		data:{
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>jobUuid": jobUuid
		},
		success:function(result){
			console.log(result);
			var modal = $("#"+<portlet:namespace/>parentNamespace+"job-result-file-modal");
			$modalBody = modal.find(".modal-body");
			$modalBody.empty();
			var length = result.resultList.length;
			if(length>0){
				$table = $("<table></table>").addClass("table table-bordered table-hover").appendTo($modalBody);
				$thead = $("<thead></thead>").appendTo($table);
				$tr = $("<tr></tr>").appendTo($thead);
				$("<th></th>").addClass("text-center").html(Liferay.Language.get('edison-table-list-header-file-nm')).appendTo($tr);
				$("<th></th>").addClass("text-center").html(Liferay.Language.get('edison-table-list-header-file-size')).appendTo($tr);
				$tbody = $("<tbody></tbody>").appendTo($table);
				
				
				for(var i = 0; i < length; i++) {
					var data = result.resultList[i];
					$dataTr = $("<tr></tr>").css("cursor","pointer")
											.attr("onclick","<portlet:namespace/>iceBreakerFileDown('"+data.fileId+"')")
											.appendTo($tbody);
					
					$("<td></td>").html(data.fileName).appendTo($dataTr);
					$("<td></td>").addClass("text-center").html(data.fileSize).appendTo($dataTr);
				}
			}
			
			if(result.zipFileId!=""){
				modal.find(".modal-footer").css("display","block");
				modal.find(".modal-footer > "+<portlet:namespace/>parentNamespace+"all-down-btn")
					 .attr("onclick","<portlet:namespace/>iceBreakerFileDow('"+result.zipFileId+"')");
			}else{
				modal.find(".modal-footer").css("display","none");
			}
			
			
			modal.modal({ "backdrop": "static", "keyboard": false });
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert("jobSystemLog-->"+textStatus+": "+jqXHR.responseText);
			}else{
				alert("jobSystemLog-->"+textStatus+": "+errorThrown);
			}
		}
	});
}


function <portlet:namespace/>loadSimulationModal(currentPage) {
	<portlet:namespace/>searchSimulationModalOpen(currentPage,'');
}

function <portlet:namespace/>searchSimulationModalOpen(currentPage,inputs) {
	var scienceAppId = <portlet:namespace/>scienceAppId;
	var searchForm = {
		"<portlet:namespace/>scienceAppId": scienceAppId,
		"<portlet:namespace/>currentPage": currentPage,
		"<portlet:namespace/>searchLine": 10,
		"<portlet:namespace/>paginFunction": "loadSimulationModal"
	};
	
	var modal = $("#"+<portlet:namespace/>parentNamespace+"simulation-modal");
	var simulatinArea = modal.find("#"+<portlet:namespace/>parentNamespace+"simulation-area");
	$simulationTbody = simulatinArea.find("tbody");
	$simulationTbody.empty();
	
	modal.find("button#"+<portlet:namespace/>parentNamespace+"create").attr("onclick","<portlet:namespace/>copyJobAndAddSimulation()");
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchSimulationURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			var length = result.simulaitons.length;
			if(length != 0) {
				for(var i = 0; i < length; i++) {
					var simulation = result.simulaitons[i];
// 					console.log(simulation);
					$tr = $("<tr></tr>").css("cursor","pointer")
						  .attr("onclick","<portlet:namespace/>copyJobAndAddJob('"+simulation._simulationUuid+"','');")
						  .appendTo($simulationTbody);
					$("<td></td>").html(simulation._simulationTitle).appendTo($tr)
					$("<td></td>").addClass("text-center").html(simulation._simulationCreateDt).appendTo($tr)
				}
				simulatinArea.find("#"+<portlet:namespace/>parentNamespace+"pagin").html(result.pagingStr);
				simulatinArea.css("display","block");
			}else{
				simulatinArea.find("#"+<portlet:namespace/>parentNamespace+"pagin").html("");
				simulatinArea.css("display","none");
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

function <portlet:namespace/>copyJobAndAddSimulation() {
	var modal = $("#"+<portlet:namespace/>parentNamespace+"simulation-modal");
	
	if (<portlet:namespace/>isModalValidate(modal)) {
	
		var workbench = window[<portlet:namespace/>parentNamespace+"workbench"];
		var simulation = workbench.workingSimulation();
		
		var jobTitle = '';
		var jobInitData = '';
		
		if(simulation){
			var job = simulation.workingJob();
			jobTitle= "copy  "+job.title_;
			jobInitData = JSON.stringify(job.copyInputs())
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

function <portlet:namespace/>copyJobAndAddJob(simulationUuid,inputs) {
	$.confirm({
		boxWidth: '30%',
		useBootstrap: false,
		title: 'Confirm!',
		content: '<p>'+Liferay.Language.get('edison-simulation-copy-job-message')+'</p>',
		buttons: {
			confirm: function () {
				var workbench = window[<portlet:namespace/>parentNamespace+"workbench"];
				var simulation = workbench.workingSimulation();
				var job = simulation.workingJob();
				
				var myId = '<%=portletDisplay.getId()%>';
				if(inputs===''){
					inputs = JSON.stringify(job.copyInputs());
				}
				var eventData = {
					portletId : myId,
					targetPortlet : <portlet:namespace/>connector,
					title : "copy  "+job.title_,
					simulationUuid : simulationUuid,
					data:inputs
				};
				
				Liferay.fire(OSP.Event.OSP_CREATE_JOB, eventData);
				
				var modal = $("#"+<portlet:namespace/>parentNamespace+"simulation-modal");
				modal.modal('hide');
			},
			cancel: function () {
				
			}
		}
	});
}
function <portlet:namespace/>iceBreakerFileDown(fileId){
	var url = '${icebreakerUrl}/api/file/download?id=' + fileId;
	window.location.href = url;
}
/***********************************************************************
 * Portlet Side btn event
 ***********************************************************************/
 var <portlet:namespace/>PANEL_DATA = {
	   "new-simulation": {
			"boxtitle":"New Simulation",
			"col": 3,
			"body": "tpl-new-simulation-panel-setting",
			"form": {},
			"btn": {
				"create": <portlet:namespace/>addSimulation
			}
	   },
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
// 			templateData["boxtitle"] = $(this).text()
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
					
					templateData.form.title = $("#<portlet:namespace/>simulation-"+simulationUuid).find("span#simulationTitle").html();
					templateData.form.simulationUuid = simulationUuid;
				}else if(btnType=="new-simulation-job"){
					var simulationUuid = $(this).attr("data-simulation-uuid");
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

function <portlet:namespace/>activateLi(jqLink){
	$(<portlet:namespace/>jqPortletBoundaryId + " .sidebar > .sidebar-menu li.view").removeClass("view");
	$(jqLink).parent("li").addClass("view");
}

function <portlet:namespace/>manualDownLoad(manualId){
	window.location.href="<%=downManualFileURL.toString()%>&<portlet:namespace/>fileEntryId="+manualId;
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
		<tr>
			<th>System Log (Error)</th>
			<td>
				<button class="btn btn-default icon-bar-chart" onclick="<portlet:namespace/>jobSystemLog('{{form.simulation._simulationUuid}}','{{form.simulation._jobUuid}}',0,'err');"></button>
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
				{{#form.isShare}}
					<button class="btn btn-success btn-sm">{{form.isShare}}</button>
				{{/form.isShare}}
				{{^form.isShare}}
					<button class="btn btn-danger btn-sm">{{form.isShare}}</button>
				{{/form.isShare}}
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
<script id="tpl-new-simulation-panel-setting" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
<div class="box-body">
	<div class="form-group">
		<label for="title">Title</label>
		<input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.title}}" autofocus required maxlength="20"/>
		<div class="help-block with-errors"></div>
	</div>
</div>
<div class="box-footer">
	<button type="button" class="btn btn-primary btn-flat pull-right func" name="create">Create</button>
</div>
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

