<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="searchSimulationURL" id="searchSimulation" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchSimulationJobURL" id="searchSimulationJob" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="searchSimulationJobInfoURL" id="searchSimulationJobInfo" copyCurrentRenderParameters="false" escapeXml="false"/>

<portlet:resourceURL var='downManualFileURL' id='downManualFile' escapeXml="false"></portlet:resourceURL>

<script src="${contextPath}/js/lib/jquery.mustache.js"></script>
<script src="${contextPath}/js/lib/mustache.min.js"></script>

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
		<portlet:namespace/>drawAppInfomation(e.data.scienceApp);
		<portlet:namespace/>searchSimulation(e.data.scienceApp.id());
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
function <portlet:namespace/>searchSimulation(scienceAppId){
	var searchForm = {
		"<portlet:namespace/>scienceAppId": scienceAppId
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
					$topLi = $("<li/>").addClass("treeview <portlet:namespace/>simulation-section").appendTo($targetUL);
					$aWrapper = $("<a/>").attr("href","#").appendTo($topLi);
					$("<i/>").addClass("fa fa-lg fa-folder").appendTo($aWrapper);
					$("<span/>").html(simulation._simulationTitle).appendTo($aWrapper);
					$("<span/>").addClass("pull-right-container")
								.append(
										$("<i/>").addClass("fa fa-angle-left pull-right")
										)
								.appendTo($aWrapper);
					
					<portlet:namespace/>searchSimulationJob(scienceAppId,simulation._simulationUuid,$topLi);
				}
				
				$("#<portlet:namespace/>pagin").html(result.pagingStr);
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

function <portlet:namespace/>searchSimulationJob(scienceAppId,simulationUuid,targetObject){
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
			if(length>0){
				$topUl = $("<ul/>").addClass("treeview-menu").appendTo(targetObject);
				
				$editLi = $("<li/>").appendTo($topUl);
				$editSpan = $("<span/>").addClass("btn-group edit-btn-group").appendTo($editLi);
				
				
				$("<button/>").addClass("btn btn-default btn-sm sidebar-btn").attr("type","button").attr("title","Job Create")
							  .attr("data-btn-type","new-simulation-job")
							  .html("<i class=\"fa fa-plus-circle\"></i>")
							  .appendTo($editSpan);
							  
				$("<button/>").addClass("btn btn-default btn-sm sidebar-btn").attr("type","button").attr("title","Edit Simulation")
							  .attr("data-btn-type","edit-simulation")
							  .html("<i class=\"fa fa-edit\"></i>")
							  .appendTo($editSpan);
				
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
					$topLi = $("<li/>").appendTo($topUl);
					$aWrapper = $("<a/>").attr("href","javascript:<portlet:namespace/>jobSelect('"+simulationUuid+"','"+job._jobUuid+"')").appendTo($topLi);
					$("<i/>").addClass(jobStatusCss).appendTo($aWrapper);
					$("<span/>").html(job._jobTitle).appendTo($aWrapper);
					$("<span/>").addClass("label label-primary pull-right")
							.append(
									$("<i/>").addClass("icon-arrow-right sidebar-btn").attr("data-btn-type","search-job-info").attr("data-job-uuid",job._jobUuid)
							).appendTo($aWrapper);
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

function <portlet:namespace/>jobSelect(simulationUuid,jobUuid){
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

/***********************************************************************
 * Portlet Side btn event
 ***********************************************************************/
 var jqPortletBoundaryId;
 var <portlet:namespace/>panelTemplateData = {
	   "new-simulation": {
			"boxtitle":"New Simulation",
			"col": 3,
			"body": "tpl-new-simulation-panel-setting"
	   },
	   "edit-simulation": {
			"boxtitle":"Edit Simulation",
			"col": 3,
			"body": "tpl-edit-simulation-panel-setting"
	   },
	   "new-simulation-job": {
			"boxtitle":"New Simulation Job",
			"col": 3,
			"body": "tpl-new-job-panel-setting"
	   },
	   "search-job-info": {
			"boxtitle":"Job Infomation",
			"col": 4,
			"body": "tpl-job-infomation-panel-setting",
			"script-search":"searchSimulationJobInfo"
	   }
	};
function <portlet:namespace/>init(){
	jqPortletBoundaryId = "#p_p_id" + <portlet:namespace/>parentNamespace;
	$.Mustache.addFromDom();
	$(jqPortletBoundaryId + " .sidebar-btn").click(function(e){
		e.preventDefault();
		var btnType = $(this).attr("data-btn-type");
		var templateData = <portlet:namespace/>panelTemplateData[btnType];
		if(templateData){
			<portlet:namespace/>activateLi(this);
// 			templateData["boxtitle"] = $(this).text()
			$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
			
			if(templateData["script-search"]){
				var data;
				if(btnType=="search-job-info"){
					data = <portlet:namespace/>searchSimulationJobInfo($(this).attr("data-job-uuid"));
				}
				console.log(data);
				$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .box-body").mustache(templateData["body"], data);
			}else{
				$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .box-body").mustache(templateData["body"], templateData);
			}
			if(templateData["footer"]){
				$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .box").append($.Mustache.render(templateData["footer"], templateData));
			}
			$("#" + <portlet:namespace/>parentNamespace + "menu-panel-box .menu-panel-close").click(function(e){
				e.preventDefault();
				$(".menu-panel").hide('slide', {direction:'left'}, 500);
				$(jqPortletBoundaryId + " .sidebar > .sidebar-menu > li.active").removeClass("active");
			});
			$(".menu-panel").show('slide', {direction:'left'}, 500);
		}
	});
}

function <portlet:namespace/>activateLi(jqLink){
	$(jqPortletBoundaryId + " .sidebar > .sidebar-menu > li.active").removeClass("active");
	$(jqLink).parent("li").addClass("active");
}

function <portlet:namespace/>manualDownLoad(manualId){
	window.location.href="<%=downManualFileURL.toString()%>&<portlet:namespace/>fileEntryId="+manualId;
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
<form class="form-horizontal">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Job Title</label>
      <input type="text" class="form-control" id="title" placeholder="{{simulation._jobTitle}}" value="">
    </div>
	<table id="example2" class="table table-bordered table-hover">
		<tr>
			<th>실행 시간</th>
			<td>{{startDt}}</td>
		</tr>
		<tr>
			<th>종료 시간</th>
			<td>{{endDt}}</td>
		</tr>
		<tr>
			<th>수행 시간</th>
			<td>{{executeTime}} minute</td>
		</tr>
		<tr>
			<th>작업상태</th>
			<td>
				<img src="${contextPath}/images/monitoring/ko_KR/{{jobStatusImg}}" onerror='this.src="${contextPath}/images/monitoring/ko_KR/monitor_QUEUED.png"'/>
				{{jobStatusNm}}
			</td>
		</tr>
		<tr>
			<th>시스템 로그</th>
			<td>
				<button class="btn btn-default icon-bar-chart"></button>
			</td>
		</tr>
		<tr>
			<th>결과 파일</th>
			<td>
				<button class="btn btn-default icon-save"></button>
			</td>
		</tr>
	</table>
  </div>
  <div class="box-footer">
    <button type="submit" class="btn btn-primary btn-flat ">save</button>
    <button type="submit" class="btn btn-danger btn-flat pull-right">Delete</button>
  </div>
</form>
</script>
<script id="tpl-new-simulation-panel-setting" type="text/html">
<form class="form-horizontal">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control" id="title" placeholder="" value="">
    </div>
  </div>
  <div class="box-footer">
    <button type="submit" class="btn btn-primary btn-flat pull-right">save</button>
  </div>
</form>
</script>
<script id="tpl-edit-simulation-panel-setting" type="text/html">
<form class="form-horizontal">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control" id="title" placeholder="" value="">
    </div>
  </div>
  <div class="box-footer">
    <button type="submit" class="btn btn-primary btn-flat pull-right">save</button>
  </div>
</form>
</script>
<script id="tpl-new-job-panel-setting" type="text/html">
<form class="form-horizontal">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control" id="title" placeholder="" value="">
    </div>
  </div>
  <div class="box-footer">
    <button type="submit" class="btn btn-primary btn-flat pull-right">save</button>
  </div>
</form>
</script>

