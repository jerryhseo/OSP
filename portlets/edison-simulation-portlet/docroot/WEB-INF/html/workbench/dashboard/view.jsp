<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="searchSimulationURL" id="searchSimulation" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchSimulationJobURL" id="searchSimulationJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchSimulationJobInfoURL" id="searchSimulationJobInfo" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="updateSimulationURL" id="updateSimulation" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="updateSimulationJobURL" id="updateSimulationJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="readOutLogURL" id="readOutLog" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="jobResultFileURL" id="jobResultFile" copyCurrentRenderParameters="false" escapeXml="false"/>


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
		if(e.data){
			toastr["success"]("", Liferay.Language.get('edison-data-delete-success'));
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
			$topLi = $("li#<portlet:namespace/>simulation-"+e.data.simulationUuid);
			<portlet:namespace/>searchSimulationJob('',e.data.simulationUuid,$topLi,true);
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
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-delete-error'));
		}
	}
});


Liferay.on(OSP.Event.OSP_REFRESH_SIMULATIONS, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet ==='BROADCAST'){
		<portlet:namespace/>searchSimulation(nullToStr(e.data.simulationUuid),'',1);
	}
});
/***********************************************************************
 * Portlet AJAX Function
 ***********************************************************************/
function <portlet:namespace/>drawAppInfomation(data){
	$("#<portlet:namespace/>appName").html(cutStr(data.name(),14));
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
		"<portlet:namespace/>currentPage": currentPage
		
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
			var length = result.simulaitons.length;
			if(length != 0) {
				$targetUL = $("#<portlet:namespace/>sidebar-menu");
				for(var i = 0; i < length; i++) {
					var simulation = result.simulaitons[i];
// 					console.log(simulation);
					$topLi = $("<li/>").addClass("treeview <portlet:namespace/>simulation-section").attr("id","<portlet:namespace/>simulation-"+simulation._simulationUuid).appendTo($targetUL);
					$aWrapper = $("<a/>").attr("href","#").appendTo($topLi);
					$("<i/>").addClass("fa fa-lg fa-folder").appendTo($aWrapper);
					$("<span/>").attr("id","simulationTitle").html(simulation._simulationTitle).appendTo($aWrapper);
					$("<span/>").addClass("pull-right-container")
								.append(
										$("<i/>").addClass("fa fa-angle-left pull-right")
										)
								.appendTo($aWrapper);
					
					var isEdit = true;
					if(simulation._userId !='${nowUserId}'){
						isEdit = false;
					}
					
					<portlet:namespace/>searchSimulationJob(scienceAppId,simulation._simulationUuid,$topLi,isEdit);
				}
				
				$("#<portlet:namespace/>pagin").html(result.pagingStr);
				<portlet:namespace/>selectRow(simulationUuid,'');
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
		
	}, 800);
}


function <portlet:namespace/>searchSimulationJob(paramScienceAppId,simulationUuid,targetObject,isEdit){
	var scienceAppId = paramScienceAppId==''?<portlet:namespace/>scienceAppId:paramScienceAppId;
	var searchForm = {
			"<portlet:namespace/>scienceAppId": scienceAppId,
			"<portlet:namespace/>simulationUuid": simulationUuid
		};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchSimulationJobURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			var length = result.length;
			$topUl = $("<ul/>").addClass("treeview-menu")
			if(targetObject.find("ul.treeview-menu").length >0){
				targetObject.find("ul.treeview-menu").remove();
				$topUl.css("display","block");
			}
			
			$topUl.appendTo(targetObject);
			
			//권한이 있을 경우에만 수정 가능
			if(isEdit){
				$editLi = $("<li/>").appendTo($topUl);
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
				for(var i = 0; i < length; i++) {
					var job = result[i];
// 					console.log(job);

					var jobStatus = job._jobStatus;
					var jobStatusCss = "fa fa-circle";
					if(jobStatus==1701005||jobStatus==1701006){
						jobStatusCss = "fa fa-circle running";
					}else if(jobStatus==1701011){
						jobStatusCss = "fa fa-circle success";
					}else if(jobStatus==1701012){
						jobStatusCss = "fa fa-circle fail";
					}
					$topLi = $("<li/>").attr("id","<portlet:namespace/>job-"+job._jobUuid).appendTo($topUl);
					$("<span/>").addClass("label label-primary pull-right  sidebar-btn").attr("data-btn-type","search-job-info").css("cursor","pointer")
								.append(
										$("<i/>").addClass("icon-arrow-right")
								).appendTo($topLi);
					$aWrapper = $("<a/>").attr("href","#").attr("data-simulation-uuid",simulationUuid).attr("data-job-uuid",job._jobUuid)
								.attr("onclick","<portlet:namespace/>jobSelect(this)").appendTo($topLi);
					$("<i/>").addClass(jobStatusCss).appendTo($aWrapper);
					$("<span/>").attr("id","jobTitle").html(job._jobTitle).appendTo($aWrapper);
					
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
}


function <portlet:namespace/>addSimulation(panelDataType, that, event){
	if (<portlet:namespace/>isValidate()) {
		var myId = '<%=portletDisplay.getId()%>';
		var data = {
				title : <portlet:namespace/>PANEL_DATA[panelDataType].form.title
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
		var data = {
				title : title,
				simulationUuid : simulationUuid
			};
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
				$("#<portlet:namespace/>job-"+jobUuid).find("span#jobTitle").html(title);
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


function <portlet:namespace/>jobSystemLog(simulationUuid, jobUuid, lastPosition) {
	<portlet:namespace/>clearReadOutLogTimer();
	
	jQuery.ajax({
		url: '<%=readOutLogURL.toString()%>',
		type:'POST',
		dataType:'json',
		data:{
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>jobUuid": jobUuid,
			"<portlet:namespace/>lastPosition": lastPosition
		},
		success:function(outLog){
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
			alert("Log 파일이 존재 하지 않습니다.");
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
				"delete": <portlet:namespace/>deleteSimulationJob
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
					var jobUuid = $(this).siblings("a").attr("data-job-uuid");
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
      <input type="text" name="title" class="form-control data-binded" id="title" placeholder="{{form.simulation._jobTitle}}" value="{{form.simulation._jobTitle}}">
      <div class="help-block with-errors"></div>
    </div>
	<table id="example2" class="table table-bordered table-hover">
		<tr>
			<th>작업상태</th>
			<td>
				<img src="${contextPath}/images/monitoring/ko_KR/{{form.jobStatusImg}}" onerror='this.src="${contextPath}/images/monitoring/ko_KR/monitor_QUEUED.png"'/>
				{{form.jobStatusNm}}
			</td>
		</tr>
		{{#form.startDt}}
		<tr>
			<th>실행 시간</th>
			<td>{{form.startDt}}</td>
		</tr>
		{{/form.startDt}}
		{{#form.endDt}}
		<tr>
			<th>종료 시간</th>
			<td>{{form.endDt}}</td>
		</tr>
		{{/form.endDt}}
		{{#form.executeTime}}
		<tr>
			<th>수행 시간</th>
			<td>{{form.executeTime}} minute</td>
		</tr>
		{{/form.executeTime}}
		{{#form.logView}}
		<tr>
			<th>시스템 로그</th>
			<td>
				<button class="btn btn-default icon-bar-chart" onclick="<portlet:namespace/>jobSystemLog('{{form.simulation._simulationUuid}}','{{form.simulation._jobUuid}}',0);"></button>
			</td>
		</tr>
		{{/form.logView}}
		{{#form.resultFile}}
		<tr>
			<th>결과 파일</th>
			<td>
				<button class="btn btn-default icon-save" onclick="<portlet:namespace/>jobResultFileView('{{form.simulation._simulationUuid}}','{{form.simulation._jobUuid}}');"></button>
			</td>
		</tr>
		{{/form.resultFile}}
	</table>
  </div>
  {{#form.isEdit}}
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat func" name="update">Save</button>
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
		<input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.title}}" autofocus required/>
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
      <input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.title}}" autofocus required/>
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
      <input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.title}}" autofocus required/>
      <div class="help-block with-errors"></div>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat pull-right func" name="create">Create</button>
  </div>
</form>
</script>

