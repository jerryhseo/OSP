<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="org.kisti.edison.util.EdisonHttpUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import= "org.kisti.edison.util.MonitoringStatusConstatns"%>

<liferay-portlet:renderURL var="monitoringSearchUrl" copyCurrentRenderParameters="false" 
	windowState="<%= WindowState. NORMAL.toString()%>">
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="workflowEditURL" copyCurrentRenderParameters="false" plid="${workflowPlid}" 
	windowState="<%=LiferayWindowState.POP_UP.toString()%>" portletName="workflowsimulationexecutor_WAR_edisonworkflow2016portlet">
</liferay-portlet:renderURL>


<liferay-portlet:resourceURL var="monitoringSearchJobURL" 	escapeXml="false" id="searchJobList" copyCurrentRenderParameters="false"/>

<style type="text/css">
	
	/* job status */
	.<portlet:namespace/>job-status-in-workflow-monitoring{
		width: 100%;
	}
	
	.<portlet:namespace/>job-status-in-workflow-monitoring > ul > li{
		float: left;
		margin: 0px 10px;
		text-align: center;
	}
	
	/* search Line */
	#<portlet:namespace/>searchLine{
		width: 25%;
	}
	
	/* search Text */
	#<portlet:namespace/>searchValue{
		width: 75%;
	}
	
	#<portlet:namespace/>workflowMonitoringTableList tr{
		border-bottom: 1px solid #dddddd !important;
	}

</style>

<div>
	<h2>
		<img src="${contextPath}/images/sub_tit_bl.png" />
		<span class="subtitlearea">
			<liferay-ui:message key="edison-workflow-monitoring-title" />	
		</span>
	</h2>
</div>
<div class="h20"></div>

<div class="table-responsive panel edison-panel" style="width: 100%; display: inline;">
	<form id="<portlet:namespace/>searchForm" action="<%=monitoringSearchUrl%>" method="post">
		<input type="hidden" id="<portlet:namespace/>userId" name="<portlet:namespace/>userId" value="${userId}" />
		<input type="hidden" id="<portlet:namespace/>searchClassUserId" name="<portlet:namespace/>searchClassUserId" value="${searchClassUserId}" />
		
		<input type="hidden" id="<portlet:namespace/>currentPage" name="<portlet:namespace/>currentPage" value="${currPage}" />
		<input type="hidden" id="<portlet:namespace/>jobStatus" name="<portlet:namespace/>jobStatus" value="${jobStatus}" />
		
		<div class="panel-heading clearfix">
			<div class="input-group pull-left" style="width: 50%;">
				<div class="<portlet:namespace/>job-status-in-workflow-monitoring">
					<ul>
						<li style="cursor: pointer;">
							<input type="image" src="${contextPath}/images/monitoring/search_QUEUED<c:if test="${jobStatus eq '1701005'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.QUEUED%>');"  style="display: block;"/>
							<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.QUEUED%>');"><liferay-ui:message key="edison-simulation-monitoring-queued"/></p>
						</li>
						<li style="cursor: pointer;">
							<input type="image" src="${contextPath}/images/monitoring/search_RUNNING<c:if test="${jobStatus eq '1701006'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.RUNNING%>');" style="display: block;"/>
							<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.RUNNING%>');"><liferay-ui:message key="edison-simulation-monitoring-running"/></p>
						</li>
						<li style="cursor: pointer;">
							<input type="image" src="${contextPath}/images/monitoring/search_FAILED<c:if test="${jobStatus eq '1701012'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.FAILED%>');" style="display: block;"/>
							<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.FAILED%>');"><liferay-ui:message key="edison-simulation-monitoring-fail"/></p>
						</li>
						<li style="cursor: pointer;">
							<input type="image" src="${contextPath}/images/monitoring/search_SUCCESS<c:if test="${jobStatus eq '1701011'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.SUCCESS%>');" style="display: block;"/>
							<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.SUCCESS%>');"><liferay-ui:message key="edison-simulation-monitoring-success"/></p>
						</li>
						<li style="cursor: pointer;">
							<input type="image" src="${contextPath}/images/monitoring/search_CANCEL<c:if test="${jobStatus eq '1701010'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.CANCELED%>');" style="display: block;"/>
							<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.CANCELED%>');"><liferay-ui:message key="edison-simulation-monitoring-cancel"/></p>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="input-group">
				<select id="<portlet:namespace/>searchLine" name="<portlet:namespace/>searchLine" class="<portlet:namespace/>search-line form-control" onchange="searchLine();" >
					<option value="10">10<liferay-ui:message key="edison-search-views"/></option>
					<option value="15">15<liferay-ui:message key="edison-search-views"/></option>
					<option value="20">20<liferay-ui:message key="edison-search-views"/></option>
					<option value="30">30<liferay-ui:message key="edison-search-views"/></option>
				</select>
				
				<input id="<portlet:namespace/>searchValue" name="<portlet:namespace/>searchValue" class="form-control" type="text" placeholder="<liferay-ui:message key="edison-appstore-solver-name"/>/<liferay-ui:message key="edison-simulation-execute-job-create-list-job-name"/> or <liferay-ui:message key="edison-table-list-header-userid"/>" />
				
				<div class="input-group-btn">
					<input name="search_button" type="submit" value="<liferay-ui:message key='edison-button-search' />" class="btn btn-default"/>
					<button name="total_search_button" type="button" class="btn btn-default" onClick="<portlet:namespace/>allSearch();">
						Clear
					</button>
				</div>
			</div>
		</div>
	</form>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
		<colgroup>
			<col width="5%">
			<col width="*">
			<col width="10%">
			<col width="15%">
			<col width="10%">
			<col width="10%">
		</colgroup>
		
		<thead>
			<tr>
				<th scope="col"><liferay-ui:message key="edison-table-list-header-index"/></th>
				<th scope="col"><liferay-ui:message key="edison-appstore-solver-name" />/<liferay-ui:message key="edison-simulation-execute-job-create-list-job-name" /></th>
				<th scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-detail"/></th>
				<%-- <c:if test="${deleteMonitoring eq true }">
				</c:if> --%>
				<th scope="col"><liferay-ui:message key="edison-table-list-header-userid" /></th>
				<th scope="col"><liferay-ui:message key="edison-simulation-execute-job-create-list-state" /></th>
				<th scope="col" class="borderno">Workflow</th>
			</tr>
		</thead>
		
		<tbody id="<portlet:namespace/>workflowMonitoringTableList">
			
			<c:choose>
				<c:when test="${empty getWorkflowMonitoringList}">
					<tr>
						<td class="center" colspan="6">
							<liferay-ui:message key='edison-there-are-no-data'/>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${getWorkflowMonitoringList}" var="workflowMonitoring" varStatus="data">
						<tr id="<portlet:namespace/>row_${workflowMonitoring.simulationId}" simulation-id="${workflowMonitoring.simulationId}">
							<td class="center">
								<c:if test="${workflowMonitoring.jobCnt eq 'Y'}">
									<img src="${contextPath}/images/monitoring/btn_plus.png" class="plusBtn" style="cursor: pointer;" simulation-id="${workflowMonitoring.simulationId}"/>
								</c:if>
								${data.count}
							</td>
							
							<td>
								${workflowMonitoring.scienceAppName}<br/>
								/${workflowMonitoring.simulationTitle}
							</td>
							
							<c:choose>
								<c:when test="${workflowMonitoring.jobCnt eq 'Y'}">
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</c:when>
								<c:otherwise>
									<td class="center">
									</td>
									
									<td class="center">
										<span style="text-decoration: underline;cursor: pointer;" onclick="<portlet:namespace/>searchUser('${workflowMonitoring.simulationUserId}');">
											${workflowMonitoring.userScreenName}
										</span>
									</td>
									
									<td class="center">
										<img src="${contextPath}/images/monitoring/status/monitor_${workflowMonitoring.jobStatusImg}"/>
									</td>
									
									<td class="center">
										<img src="${contextPath}/images/monitoring/btn_monitor_visual.png" style="cursor: pointer;" onclick="<portlet:namespace/>moveWorkflow(${workflowMonitoring.workflowId});" alt="delete" title="delete">
									</td>
								</c:otherwise>
							</c:choose>
							
							
						<tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<table id="<portlet:namespace/>hideJobTable" style="display: none">
	</table>
</div>
	
<script type="text/javascript">
	
	$(document).ready(function(){
		listSize = '${listSize}';
		searchValue = '${searchValue}';
		
		$("#<portlet:namespace/>searchLine > option[value="+listSize+"]").attr("selected", "selected");
		$("#<portlet:namespace/>searchValue").val(searchValue);
	});
	
	/* n개씩 보기 검색 */
	function searchLine(){
		$("#<portlet:namespace/>searchForm").submit();
	}
	
	
	function <portlet:namespace/>statusSearch(status){
		$("#<portlet:namespace/>jobStatus").val(status);
	}
	/* 상태 검색 */
	function <portlet:namespace/>statusSearchAndSubmit(status){
		$("#<portlet:namespace/>jobStatus").val(status);
		$("#<portlet:namespace/>searchForm").submit();
	}
	
	/* USER 검색 */
	function <portlet:namespace/>searchUser(userId){
		$("#<portlet:namespace/>userId").val(userId);
		$("#<portlet:namespace/>searchForm").submit();
	}
	
	/* 워크플로우 실행 화면으로 이동 */
	function <portlet:namespace/>moveWorkflow(targetWorkflowId){
		var URL = "<%=workflowEditURL%>";
		URL += "&_workflowsimulationexecutor_WAR_edisonworkflow2016portlet_workflowId="+targetWorkflowId;
		
		window.open(URL);
	}
	
	/* 워크플로우 모니터링 전체 리스트 검색 */
	function <portlet:namespace/>allSearch(){
		$("#<portlet:namespace/>searchValue").val("");
		$("#<portlet:namespace/>searchLine").val("");
		$("#<portlet:namespace/>currentPage").val("");
		$("#<portlet:namespace/>jobStatus").val("");
		$("#<portlet:namespace/>searchForm").submit();
	}
	
	$("#<portlet:namespace/>workflowMonitoringTableList").on("click","img.plusBtn",function(){
		var myClass = $(this).attr("class");
		var id = $(this).attr("id");
		var parentRowId  = "row_"+$(this).attr("data-uuid");
		
		$(this).attr("src","${contextPath}/images/monitoring/btn_minus.png");
		$(this).removeClass('plusBtn');
		$(this).addClass('minusBtn');
		
		$hideJobList = $("#<portlet:namespace/>hideJobTable");
		$hideJobList.empty();
		
	});
	
	$("#<portlet:namespace/>workflowMonitoringTableList").on("click","img.plusBtn",function(){
		var myClass = $(this).attr("class");
		var simulationId = $(this).attr("simulation-id");
		var parentRowId  = "<portlet:namespace/>row_"+simulationId;
		
		$(this).attr("src","${contextPath}/images/monitoring/btn_minus.png");
		$(this).removeClass('plusBtn');
		$(this).addClass('minusBtn');
		
		var searchData = {
				"<portlet:namespace/>simulationId": simulationId,
		};
		
		$hideJobList = $("#<portlet:namespace/>hideJobTable"); 
		$hideJobList.empty();
		
		jQuery.ajax({
			type: "POST",
			url: "<%=monitoringSearchJobURL%>",
			async : false,
			data: searchData,
			dataType: 'json',
			success: function(data) {
				workflowJobMonitoringList = data.getWorkflowJobMonitoringList;
				workflowJobMonitoringListSize = data.getWorkflowJobMonitoringListSize;
				
				if(0 < workflowJobMonitoringListSize){
					
					for(var i=0; i < workflowJobMonitoringListSize; i++){
						simulationId = workflowJobMonitoringList[i].simulationId;
						simulationJobId = workflowJobMonitoringList[i].simulationJobId;
						simulationJobUserId = workflowJobMonitoringList[i].simulationJobUserId;
						simulationJobTitle = workflowJobMonitoringList[i].simulationJobTitle;
						simulationJobCreateDate = workflowJobMonitoringList[i].simulationJobCreateDate;
						simulationJobModifiedDate = workflowJobMonitoringList[i].simulationJobModifiedDate;
						status = workflowJobMonitoringList[i].status;
						statusResponse = workflowJobMonitoringList[i].statusResponse;
						startTime = workflowJobMonitoringList[i].startTime;
						endTime = workflowJobMonitoringList[i].endTime;
						workflowUUID = workflowJobMonitoringList[i].workflowUUID;
						reuseWorkflowUUID = workflowJobMonitoringList[i].reuseWorkflowUUID;
						screenLogic = workflowJobMonitoringList[i].screenLogic;
						workflowId = workflowJobMonitoringList[i].workflowId;
						
						userScreenName = workflowJobMonitoringList[i].userScreenName;
						executeDate = workflowJobMonitoringList[i].executeDate;
						jobCnt = workflowJobMonitoringList[i].jobCnt;
						jobStatusImg = workflowJobMonitoringList[i].jobStatusImg;
						
						hideJobTr = $("<tr/>").attr("simulation-id", simulationId)
											.attr("simulation-job-id", simulationJobId)
											.attr("id", "<portlet:namespace/>subRow_"+simulationId+"_"+simulationJobId);
						
						/* SUB_INDEX */
						$("<td/>").addClass("center").text("-").appendTo(hideJobTr);
						
						/* SimulationJob Title */
						$("<td/>").text(simulationJobTitle).appendTo(hideJobTr);
						
						/* Detail Info */
						$("<td/>").addClass("center").appendTo(hideJobTr);
						
						/* User Name */
						userName = $("<span/>").attr("onclick", "<portlet:namespace/>searchUser("+simulationJobUserId+")")
												.css("text-decoration", "underline")
												.css("cursor", "pointer")
												.text(userScreenName);
						$("<td/>").addClass("center").append(userName).appendTo(hideJobTr);
						
						/* Job Status */
						statusImg = $("<img/>").attr("src", "${contextPath}/images/monitoring/status/monitor_" + jobStatusImg);
						$("<td/>").addClass("center").append(statusImg).appendTo(hideJobTr);
						
						/* Move Workflow Icon */
						moveWorkflowImg = $("<img/>").attr("src", "${contextPath}/images/monitoring/btn_monitor_visual.png")
														.attr("onclick", "<portlet:namespace/>moveWorkflow(" + workflowId + ")")
														.css("cursor", "pointer");
						$("<td/>").addClass("center").append(moveWorkflowImg).appendTo(hideJobTr);
						
						hideJobTr.appendTo($hideJobList);
					}
				}
				$("#<portlet:namespace/>workflowMonitoringTableList tr[id="+parentRowId+"]").after($("#<portlet:namespace/>hideJobTable tr").effect("highlight",{},3000));
			},
			error:function(msg){
				alert("System Exception plusBtn: " + msg);
			}
		});
	});
	
	$("#<portlet:namespace/>workflowMonitoringTableList").on("click","img.minusBtn",function(){
		var simulationId = $(this).attr("simulation-id");
		$(this).attr("src","${contextPath}/images/monitoring/btn_plus.png");
		$(this).removeClass('minusBtn');
		$(this).addClass('plusBtn');
		
		$("tr[id^=<portlet:namespace/>subRow_"+simulationId+"]").remove();
	});
	
</script>