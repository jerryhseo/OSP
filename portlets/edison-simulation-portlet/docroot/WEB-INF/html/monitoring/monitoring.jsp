<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="org.kisti.edison.util.EdisonHttpUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import= "org.kisti.edison.util.MonitoringStatusConstatns"%>

<%
	
	//Tab Setting
	String tabNames = (String)request.getAttribute("tabNames");
	String visitSite = CustomUtil.strNull(request.getAttribute("visitSite"));
	String parentGroupId = CustomUtil.strNull(request.getAttribute("parentGroupId"));
	String tabsValues = (String)request.getAttribute("tabsValues");
	String portletNameSpace = "_"+portletDisplay.getId()+"_"+"tagScript";
	
	String selectedGroupId =CustomUtil.strNull(request.getAttribute("selectedGroupId"));
	boolean deleteMonitoring = (Boolean)request.getAttribute("deleteMonitoring");
	
	boolean userIdSearchStatus = GetterUtil.getBoolean(request.getAttribute("userIdSearchStatus"));
	
	String searchStr = LanguageUtil.format(locale, "edison-appstore-solver-name", "")+" or "+LanguageUtil.format(locale, "edison-simulation-execute-job-create-list-job-name", "");
	
	if(userIdSearchStatus){
		searchStr +=" or "+ LanguageUtil.format(locale, "edison-table-list-header-userid", "");
	}
	
	String monitoringRedirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
%>
<style type="text/css">
.aui input[type="text"],
.aui input[type="password"],
.aui textarea,
.aui .edison_select{
	margin-bottom: 0px;
}

.aui .control-group{
	display: inline;
}
.postlist:hover {
	background: #e0e0e0;
}
</style>
<liferay-portlet:resourceURL var="saveClickTab" id="cickTab" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="stopSimulationAPI" escapeXml="false" id="stopAPICall" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="monitoringStatusUpdate" 	escapeXml="false" id="updateJobStatus" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="errorSimulationAPI" 		escapeXml="false" id="errorAPICall" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="scienceAppMiddleFileURL" 		escapeXml="false" id="scienceAppMiddleFile" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="monitoringSearchJobURL" 	escapeXml="false" id="searchJobList" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="monitoringSearchParam" 	escapeXml="false" id="searchJobParam" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="monitoringDeleteJobURL" 	escapeXml="false" id="deleteJob" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="searchPostProcessorURL" 		escapeXml="false" id="searchJobPostProcessor" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="exeSearchURL" 		escapeXml="false" id="exeSearch" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="postGraphURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="middleGraphView" />
	<liferay-portlet:param name="groupId" value="<%=selectedGroupId%>"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="resultDownLoadURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>"  copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="resultDownLoad" />
	<liferay-portlet:param name="groupId" value="<%=selectedGroupId%>"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="monitoringSearchUrl" copyCurrentRenderParameters="false" windowState="<%= WindowState. NORMAL.toString()%>">
	<liferay-portlet:param name="simulationClassId" value="${simulationClassId}"/>
	<liferay-portlet:param name="simulationCustomId" value="${simulationCustomId}"/>
	<liferay-portlet:param name="classSearchUser" value="${classSearchUser}"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="postJmoleURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>"  copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="postJmole" />
	<liferay-portlet:param name="groupId" value="<%=selectedGroupId%>"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="postOneDPlotURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>"  copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="postOneDplot" />
	<liferay-portlet:param name="groupId" value="<%=selectedGroupId%>"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="postImageViewerURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>"  copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="postImageViewer" />
	<liferay-portlet:param name="groupId" value="<%=selectedGroupId%>"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="postWebGLViewerURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>"  copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="postWebGLViewer" />
	<liferay-portlet:param name="groupId" value="<%=selectedGroupId%>"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="postTextViewerURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>"  copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="postTextViewer" />
	<liferay-portlet:param name="groupId" value="<%=selectedGroupId%>"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false"
 plid="${workBenchPlid}" 
 portletName="Workbench_WAR_OSPWorkbenchportlet"
 windowState="<%=LiferayWindowState.NORMAL.toString()%>" 
 portletMode="<%=LiferayPortletMode.VIEW.toString()%>">
	<liferay-portlet:param name="workbenchType" value="SIMULATION_RERUN" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="monitoringAnalysisURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="Workbench_WAR_OSPWorkbenchportlet" windowState="<%= LiferayWindowState.POP_UP.toString()%>">
<liferay-portlet:param name="workbenchType" value="MORANALYSIS" />
</liferay-portlet:renderURL>

<c:if test="${tabViewYn eq 'Y'}">
	<div class="contabmenu">
		<edison-ui:tabs names="<%=tabNames%>" tabsValues="<%=tabsValues%>" value="<%=visitSite%>" refresh="<%=false%>" onClick="<%=portletNameSpace%>" minwidth="195"/>
	</div>
</c:if>



<div class="h1">
	<liferay-ui:message key="edison-simulation-monitoring-title" />	
</div>

<aui:form method="post" name="monitoringSearch" action="<%=monitoringSearchUrl%>">
	<aui:input name="currentPage" type="hidden" value="1"/>
	<aui:input name="userId" type="hidden" value="${userId}"/>
	<aui:input name="selectedGroupId" type="hidden" value="${selectedGroupId}"/>
	<aui:input name="jobStatus" type="hidden" value="${param.jobStatus}"/>
	<aui:input name="simulationUuid" type="hidden" value="${param.simulationUuid}"/>
	<aui:input name="jobSeqNo" type="hidden" value="${param.jobSeqNo}"/>
	
	<div class="tabletopbox">
		<div class="search">
			<div class="searchbox">
				<aui:input name="searchValue" class="textfieldcss" type="text" placeholder="<%=searchStr%>" label="" style="width: 300px;"/>
			</div>
			<input name="search_button" type="submit" value="<liferay-ui:message key='edison-button-search' />" class="button01"/>
			<input name="total_search_button" type="button" value="<liferay-ui:message key='edison-button-all-search' />" class="button01" onClick="<portlet:namespace/>allSearch();" />
		</div>
		<div class="tabletoptab01">
<%-- 			<input type="image" style="margin-left:90px;" src="${contextPath}/images/monitoring/<%=themeDisplay.getLanguageId()%>/search_ALL<c:if test="${param.jobStatus eq null||param.jobStatus eq ''}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('');"/> --%>
			<ul>
				<li style="cursor: pointer;">
					<input type="image" src="${contextPath}/images/monitoring/search_QUEUED<c:if test="${param.jobStatus eq '1701005'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.QUEUED%>');"  style="display: block;"/>
					<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.QUEUED%>');"><liferay-ui:message key="edison-simulation-monitoring-queued"/></p>
				</li>
				<li style="cursor: pointer;">
					<input type="image" src="${contextPath}/images/monitoring/search_RUNNING<c:if test="${param.jobStatus eq '1701006'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.RUNNING%>');" style="display: block;"/>
					<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.RUNNING%>');"><liferay-ui:message key="edison-simulation-monitoring-running"/></p>
				</li>
				<li style="cursor: pointer;">
					<input type="image" src="${contextPath}/images/monitoring/search_FAILED<c:if test="${param.jobStatus eq '1701012'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.FAILED%>');" style="display: block;"/>
					<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.FAILED%>');"><liferay-ui:message key="edison-simulation-monitoring-fail"/></p>
				</li>
				<li style="cursor: pointer;">
					<input type="image" src="${contextPath}/images/monitoring/search_SUCCESS<c:if test="${param.jobStatus eq '1701011'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.SUCCESS%>');" style="display: block;"/>
					<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.SUCCESS%>');"><liferay-ui:message key="edison-simulation-monitoring-success"/></p>
				</li>
				<li style="cursor: pointer;">
					<input type="image" src="${contextPath}/images/monitoring/search_CANCEL<c:if test="${param.jobStatus eq '1701010'}">_active</c:if>.png" onclick="<portlet:namespace/>statusSearch('<%=MonitoringStatusConstatns.CANCELED%>');" style="display: block;"/>
					<p onclick="<portlet:namespace/>statusSearchAndSubmit('<%=MonitoringStatusConstatns.CANCELED%>');"><liferay-ui:message key="edison-simulation-monitoring-cancel"/></p>
				</li>
			</ul>
		</div>
		<div class="tabletopright">
			<aui:select name="searchLine" onChange="searchLine();" cssClass="edison_select selectview" label="">
				<aui:option value="10">10<liferay-ui:message key="edison-search-views"/></aui:option>
				<aui:option value="15">15<liferay-ui:message key="edison-search-views"/></aui:option>
				<aui:option value="20">20<liferay-ui:message key="edison-search-views"/></aui:option>
				<aui:option value="30">30<liferay-ui:message key="edison-search-views"/></aui:option>
			</aui:select>
		</div>
	</div>
</aui:form>
<div class="table5_list borderno">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="5%">
			<col width="*">
			<col width="8%">
			<c:if test="${deleteMonitoring eq true }">
				<col width="10%">
			</c:if>
			<col width="5%">
			<col width="9%">
			<col width="10%">
			<col width="9%">
			<col width="15%">
			<col width="15%">
		</colgroup>
		<thead>
			<tr>
				<th rowspan="2" scope="col"><liferay-ui:message key="edison-table-list-header-index"/></th>
				<th rowspan="2" scope="col"><liferay-ui:message key="edison-appstore-solver-name" />/<liferay-ui:message key="edison-simulation-execute-job-create-list-job-name" /></th>
				<th rowspan="2" scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-detail"/></th>
				<c:if test="${deleteMonitoring eq true }">
					<th rowspan="2" scope="col"><liferay-ui:message key="edison-table-list-header-userid" /></th>
				</c:if>
				<th rowspan="2" scope="col"><liferay-ui:message key="edison-simulation-execute-job-create-list-state" /></th>
				<th rowspan="2" scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-job-cancle"/></th>
				<th rowspan="2" scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-check-moderate"/></th>
				<th rowspan="2" scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-job-manage"/></th>
				<th colspan="2" scope="col" class="borderno"><liferay-ui:message key="edison-simulation-monitoring-table-header-check-result"/></th>
			</tr>
			<tr>
				<th scope="col" class="greyth"><p><liferay-ui:message key="edison-simulation-monitoring-table-header-result-down"/></p></th>
				<th scope="col" class="greyth"><p><liferay-ui:message key="edison-simulation-monitoring-table-header-result-visual"/></p></th>
			</tr> 
		</thead>
		<tbody id="mtbody">
			<c:choose>
				<c:when test="${!empty dataList}">
					<c:forEach items="${dataList}" var="model" varStatus="data">
						<c:set value="" var="trClass"/>
						<c:if test="${data.index%2==1}">
							<c:set value="tablebgtr" var="trClass"/>
						</c:if>
						
						<c:set value="<%= false %>" var="subJobState"/>
						<c:if test="${model.jobCnt eq 'Y'}">
							<c:set value="<%=true%>" var="subJobState"/>
						</c:if>
						
						<c:choose>
							<c:when test="${subJobState}">
								<tr id="row_${model.jobUuid}" sub-job="${model.jobCnt}"class="${trClass}">
							</c:when>
							<c:otherwise>
								<tr id="row_${model.jobUuid}" data-status="${model.jobStatus}" sub-job="${model.jobCnt}" simulation-id="${model.simulationUuid}" scienceApp-id="${model.scienceAppId}" cluster="${model.cluster}" class="${trClass}">
							</c:otherwise>
						</c:choose>
							<td class="TC">
								<c:if test="${subJobState}">
									<img src="${contextPath}/images/monitoring/btn_plus.png" class="plusBtn" style="cursor: pointer;" id="${model.simulationUuid}" data-uuid="${model.jobUuid}"/>
								</c:if>
								${seq - data.index}
							</td>
							<td>
								${model.scienceAppName}<br/>/${model.simulationTitle}
							</td>
							<c:choose>
								<c:when test="${subJobState}">
									<td></td>
									<c:if test="${deleteMonitoring eq true }">
										<td></td>
									</c:if>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</c:when>
								<c:otherwise>
									<td class="TC">
										<c:if test="${model.cluster ne 'EDISON-RESTORE'}">
											<img src="${contextPath}/images/monitoring/bnt_info.png" onclick="<portlet:namespace/>searchSimulationParam('${model.simulationUuid}','${model.jobSeqNo}','${model.jobUuid}');" style="cursor: pointer;" />
										</c:if>
									</td>
									<c:if test="${deleteMonitoring eq true }">
										<td>
											<span style="text-decoration: underline;cursor: pointer;" onclick="<portlet:namespace/>searchUser('${model.userId}');">${model.userNm}</span>
										</td>
									</c:if>
									<td class="TC">
										<img src="${contextPath}/images/monitoring/<%=themeDisplay.getLanguageId()%>/${model.jobStatusImg}"/>
									</td>
									<td id="job_controll" class="TC">
										
									</td>
									<td id="middle_check" class="TC" logFileProcess-state="${model.jobLogFileProcessorYn }">
										
									</td>
									<td class="TC">
										<c:set value="<%=themeDisplay.getUserId()%>" var="thisUser"/>
										<c:if test="${deleteMonitoring || model.userId eq thisUser}">
											<c:if test="${model.cluster ne 'EDISON-RESTORE'}">
												<img src="${contextPath}/images/monitoring/btn_monitor_delete.png" style="cursor: pointer;" onclick="<portlet:namespace/>deleteMonitoring('${model.simulationUuid}','0');" alt="delete" title="delete">
											</c:if>	
										</c:if>
										<c:if test="${model.cluster ne 'EDISON-RESTORE'}">
											<img src="${contextPath}/images/monitoring/btn_monitor_rerun.png" style="cursor: pointer;" onclick="<portlet:namespace/>restartSimulation('${model.scienceAppId}', '${model.jobUuid}');" alt="rerun" title="rerun">
										</c:if>	
									</td>
									<td id="result_down" class="TC">
										
									</td>
									<td id="result_view" class="TC" postprocess-state="${model.jobPostProcessorYn}" middleFileprocess-state="${model.jobMiddleFileProcessorYn}">
										
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="11" class="TC"><liferay-ui:message key='edison-there-are-no-data'/></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<div class="paging">
	<div style="width:100%;text-align: center;">
		${paging}
	</div>
</div>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<table id="hideJobTable" style="display: none">
</table>

<div id="<portlet:namespace/>result-down-dialog" style="display:none; background-color:white; padding:0px;" class="newWindow">
</div>

<div id="<portlet:namespace/>jobparameter-dialog" title="<liferay-ui:message key="edison-simulation-execute-job-detail"/>" style="display:none; background-color:white; padding:0px;" class="newWindow">
	<div class="newWheader" id="<portlet:namespace/>jobparameter-dialog-title" style="cursor: move;">
			<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
				<div class="newWtitle"><liferay-ui:message key="edison-simulation-execute-job-detail"/></div>
			</div>
			<div class="newWclose" style="cursor: pointer;">
				<img id="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
			</div>
	</div>
	<div id="<portlet:namespace/>jobparameter-dialog-content" style="padding: 30px;" class="newWcont01">
	</div>
</div>

<%-- <div id="<portlet:namespace/>error-dialog" style="display:none; background-color:white; padding:0px;" class="newWindow">
	<div class="newWheader" id="<portlet:namespace/>error-dialog-title" style="cursor: move;">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">Log</div>
		</div>
		<div class="newWclose" style="cursor: pointer;">
			<img id="<portlet:namespace/>error-dialog-close-btn" name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
		</div>
	</div>
	<div id="<portlet:namespace/>error-dialog-content" style="padding: 30px;" class="newWcont01">
			
	</div>
</div> --%>


<div id="<portlet:namespace/>error-dialog" style="display:none; background-color:white; padding:0px;" class="newWindow">
	<div class="newWheader" id="<portlet:namespace/>error-dialog-title" style="cursor: move;">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle"><liferay-ui:message key="edison-simulation-monitoring-post-process-choice"/></div>
		</div>
		<div class="newWclose" style="cursor: pointer;">
			<img id="<portlet:namespace/>error-dialog-close-btn" name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
		</div>
	</div>
	<div style="padding: 30px;overflow:auto; max-height:400px;" class="newWcont01">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1" style="word-break: break-all;table-layout: fixed;">
			<colgroup>
				<col width="300px" />
				<col width="*" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="left"><liferay-ui:message key="edison-simulation-execute-port-label-portname"/></th>
					<th scope="col" class="left"><liferay-ui:message key="edison-simulation-monitoring-post-process-nm"/></th>
				</tr>
			</thead>
			<tbody id="<portlet:namespace/>error-dialog-content" style="font-size: 25px;">
				
			</tbody>
		</table>
	</div>
</div>


<div id="<portlet:namespace/>post-dialog" style="display:none; background-color:white; padding:0px;" class="newWindow">
	<div class="newWheader" id="<portlet:namespace/>post-dialog-title" style="cursor: move;">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle"><liferay-ui:message key="edison-simulation-monitoring-post-process-choice"/></div>
		</div>
		<div class="newWclose" style="cursor: pointer;">
			<img id="<portlet:namespace/>post-dialog-close-btn" name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
		</div>
	</div>
	<div style="padding: 30px;overflow:auto; max-height:400px;" class="newWcont01">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1" style="word-break: break-all;table-layout: fixed;">
			<colgroup>
				<col width="300px" />
				<col width="*" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="left"><liferay-ui:message key="edison-simulation-execute-port-label-portname"/></th>
					<th scope="col" class="left"><liferay-ui:message key="edison-simulation-monitoring-post-process-nm"/></th>
				</tr>
			</thead>
			<tbody id="<portlet:namespace/>post-dialog-content" style="font-size: 25px;">
				
			</tbody>
		</table>
	</div>
</div>

<div id="<portlet:namespace/>show-analyzer-dialog">
	<div id="<portlet:namespace/>show-analyzer-dialog-content"></div>
</div>


<script type="text/javascript">
function searchLine(){
	$("#<portlet:namespace/>monitoringSearch").submit();
}



function <portlet:namespace/>statusSearch(status){
	$("#<portlet:namespace/>jobStatus").val(status);
}

function <portlet:namespace/>statusSearchAndSubmit(status){
	$("#<portlet:namespace/>jobStatus").val(status);
	$("#<portlet:namespace/>monitoringSearch").submit();
}

function <portlet:namespace/>allSearch(){
	$("#<portlet:namespace/>searchValue").val("");
	$("#<portlet:namespace/>userId").val("");
	$("#<portlet:namespace/>jobStatus").val("");
	$("#<portlet:namespace/>monitoringSearch").submit();
}

function <portlet:namespace/>pageSearch(p_currentPage){
	$("#<portlet:namespace/>currentPage").val(p_currentPage);
	$("#<portlet:namespace/>monitoringSearch").submit();
}

function <portlet:namespace/>searchUser(userId){
	$("#<portlet:namespace/>userId").val(userId);
	$("#<portlet:namespace/>monitoringSearch").submit();
}

//선택한 Tab Id
var selectedTabId = "";

//liferay-ui 탭 이벤트 return Script
function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
	var searchData = {"<portlet:namespace/>groupId":value};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=saveClickTab%>",
		async : false,
		data  : searchData,
		success: function(data) {
			selectedTabId = value;
			$("#<portlet:namespace/>selectedGroupId").val(selectedTabId);
			$("#<portlet:namespace/>monitoringSearch").submit();
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}
//상태값에 따른 모니터링 작업 영역 update
function <portlet:namespace/>searchData(){
	
}

//후처리기 목록 조회
function <portlet:namespace/>searchPostProcessor(jobSeqNo,simulationUuid,jobUuid){
	var searchData = {
			"<portlet:namespace/>jobSeqNo": jobSeqNo,
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>jobUuid": jobUuid,
			"<portlet:namespace/>groupId": "<%=selectedGroupId%>"
			};
	
	//console.log('searchPostProcessor');
	jQuery.ajax({
		type: "POST",
		url: "<%=searchPostProcessorURL%>",
		data  : searchData,
		dataType: 'json',
		async:false,
		success:function(msg){
			if(msg!=""){
				var dataSize = msg.length;
				var dataMap = msg.portMapList;
				//console.log( JSON.stringify( dataMap, null, 4) );
				$dialogBody = $("#<portlet:namespace/>post-dialog-content");
				for(var i=0; i< dataMap.length; i++){
					
					$tr = $("<tr></tr>").addClass("postlist").attr("onClick",
							"event.cancelBubble=true;openAnalyzerWindow('" + 
							dataMap[i].exeFileName +"', '" + 
							dataMap[i].title +"', '" + 
							dataMap[i].portName +"', '" + 
							dataMap[i].portName +"', '" + 
							dataMap[i].groupId +"', '" + 
							dataMap[i].fileListType +"', '" + 
							dataMap[i].fileListValue +"', '" + 
							dataMap[i].jobUuid +"', '" + 
							dataMap[i].token +"', '" + 
							dataMap[i].plid +"', '" +
							dataMap[i].shipmentForm +"', '" +
							dataMap[i].lookUpPath +"', '" +
							dataMap[i].filePath +"', '" +
							simulationUuid+"');")
							.appendTo($dialogBody);
					$("<td></td>").addClass("TC").css("cursor","pointer").text(dataMap[i].portName).appendTo($tr);
					$("<td></td>").addClass("TC").css("cursor","pointer").text(dataMap[i].name).appendTo($tr);
				}
				$("#<portlet:namespace/>post-dialog").dialog("open");
			}
		},
		error:function(msg){
			alert("System Exception[searchPostProcessor]: " + msg);
		}
	});
}


//상태값에 따른 모니터링 작업 영역 update
function <portlet:namespace/>monitoringController(jobSeqNo,simulationUuid,jobUuid,scienceAppId,jobStatus,area){
	$trArea = area;
	$jobControllArea =  $trArea.children("td[id=job_controll]");
	$middleCheckArea = $trArea.children("td[id=middle_check]");
	$resultDownArea = $trArea.children("td[id=result_down]");
	$resultViewArea = $trArea.children("td[id=result_view]");
	
	//초기화
	$jobControllArea.empty();
	$middleCheckArea.empty();
	$resultDownArea.empty();
	$resultViewArea.empty();
	
	//대기,처리중
	if(jobStatus=="<%=MonitoringStatusConstatns.QUEUED%>"||jobStatus=="<%=MonitoringStatusConstatns.RUNNING%>"){
		$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_cancel.png")
				   .css("cursor","pointer")
// 				   .attr("onClick","<portlet:namespace/>stop_simulation('"+jobSeqNo+"','"+simulationUuid+"','"+jobUuid+"','"+area+"');")
				   .click(function(){<portlet:namespace/>stop_simulation(jobSeqNo,simulationUuid,jobUuid,area);})
				   .appendTo($jobControllArea);
		
		
		//2015-05-14 추가(처리중일 경우 중간 결과 확인 할 수 있도록)
		if(jobStatus=="<%=MonitoringStatusConstatns.RUNNING%>"){
			if($middleCheckArea.attr("logFileProcess-state")=="Y"){
				$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_error.png")
				.attr("width","22px")
				.attr("height","22px")
				   .click(function(){<portlet:namespace/>error_event(simulationUuid, jobSeqNo, jobUuid);})
				   .css("cursor","pointer")
				   .appendTo($middleCheckArea);
				
				$middleCheckArea.append("&nbsp;");
			}
			
			if($resultViewArea.attr("middleFileprocess-state")=="Y"){
				$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_graph.png")
					   .click(function(){<portlet:namespace/>graph_event(scienceAppId,jobUuid, simulationUuid);})
					   .attr("width","22px")
					   .attr("height","22px")
					   .css("cursor","pointer")
					   .appendTo($middleCheckArea);
			}
		}
	}else if(jobStatus=="<%=MonitoringStatusConstatns.SUCCESS%>"){
		if($middleCheckArea.attr("logFileProcess-state")=="Y"){
			$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_error.png")
						.attr("width","22px")
						.attr("height","22px")
					   .click(function(){<portlet:namespace/>error_event(simulationUuid, jobSeqNo, jobUuid);})
					   .css("cursor","pointer")
					   .appendTo($middleCheckArea);
			
			$middleCheckArea.append("&nbsp;");
		}
		
		if($resultViewArea.attr("middleFileprocess-state")=="Y"){
			$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_graph.png")
					   .attr("width","22px")
					   .attr("height","22px")
					   .click(function(){<portlet:namespace/>graph_event(scienceAppId,jobUuid, simulationUuid);})
					   .css("cursor","pointer")
					   .appendTo($middleCheckArea);
		}
		
		$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_save.png")
				   .click(function(){<portlet:namespace/>down_event(scienceAppId, simulationUuid, jobSeqNo,jobUuid);})
				   .attr("width","22px")
				   .attr("height","22px")
				   .css("cursor","pointer")
				   .appendTo($resultDownArea);
// 		if($resultViewArea.attr("postprocess-state")=="Y"){
			$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_visual.png")
					   .attr("width","22px")
					   .attr("height","22px")
					   .css("cursor","pointer")
// 					   .click(function(){<portlet:namespace/>searchPostProcessor(jobSeqNo,simulationUuid,jobUuid);})
						.click(function(){event.cancelBubble=true; <portlet:namespace/>restartSimulation(scienceAppId,jobUuid);})
					   .appendTo($resultViewArea);
// 		}
	//실패
	}else if(jobStatus=="<%=MonitoringStatusConstatns.FAILED%>"){
		if($middleCheckArea.attr("logFileProcess-state") == "Y"){
			$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_error.png")
					   .attr("width","22px")
					   .attr("height","22px")
					   .click(function(){<portlet:namespace/>error_event(simulationUuid, jobSeqNo, jobUuid);})
					   .css("cursor","pointer")
					   .appendTo($middleCheckArea);
			
			$middleCheckArea.append("&nbsp;");
		}
/* 		$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_graph.png")
				   .attr("width","22px")
				   .attr("height","22px")
				   .click(function(){<portlet:namespace/>graph_event(scienceAppId,jobUuid, simulationUuid);})
				   .css("cursor","pointer")
				   .appendTo($middleCheckArea); */
		
		$("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_save.png")
				   .attr("width","22px")
				   .attr("height","22px")
				   .click(function(){<portlet:namespace/>down_event(scienceAppId, simulationUuid, jobSeqNo, jobUuid);})
				   .css("cursor","pointer")
				   .appendTo($resultDownArea);
	}
}

//결과 다운로드
function <portlet:namespace/>down_event(scienceAppId, simulationUuid, jobSeqNo, jobUuid){
	var URL = "<%=resultDownLoadURL%>&<portlet:namespace/>jobUuid="+jobUuid+"&<portlet:namespace/>jobSeqNo="+jobSeqNo;
	URL += "&<portlet:namespace/>simulationUuid="+simulationUuid;
	URL += "&<portlet:namespace/>simulationSubjectId="+scienceAppId;
	URL += "&<portlet:namespace/>redirectName="+Liferay.Language.get("edison-simulation-monitoring-title");
	URL += "&<portlet:namespace/>redirectURL=<%=monitoringRedirectURL%>";
	
	
	$resultDialog = $("#<portlet:namespace/>result-down-dialog").dialog({
		autoOpen: true,
		width: '500',
		height: '580',
		modal: true,
		resizable: true,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	$(this).parent().css('overflow','visible');
	    	$(this).load(URL);
	    	
	    	$("body").css('overflow','hidden')
	    	
	    	$("#<portlet:namespace/>result-down-dialog-close-btn").bind('click',function(){
	    		$("#<portlet:namespace/>result-down-dialog").dialog("close");
	    	});
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#<portlet:namespace/>result-down-dialog").dialog("close");
			})
	    },
	    close: function() {
	    	$("body").css('overflow','');
	    }
	}).draggable({
			handle: "#<portlet:namespace/>result-dialog-title"
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
}

//중간보기
function <portlet:namespace/>graph_event(scienceAppId, jobUuid, simulationUuid){
	var searchData = {
				"<portlet:namespace/>scienceAppId": scienceAppId,
				"<portlet:namespace/>groupId": "<%=selectedGroupId%>"
				};
	jQuery.ajax({
		type: "POST",
		url: "<%=scienceAppMiddleFileURL%>",
		data  : searchData,
		dataType: 'json',
		success:function(data){
			if(data.fileState){
				openAnalyzerWindow( 
						data.portMap.exeFileName, 
						data.portMap.title, 
						data.portMap.portName, 
						data.portMap.portName, 
						data.portMap.groupId , 
						"TEMP" , 
						data.portMap.fileListValue , 
						jobUuid, 
						data.portMap.token, 
						data.portMap.plid,
						data.shipmentForm,
						data.lookUpPath,
						data.filePath,
						simulationUuid);
			}else{
				alert(Liferay.Language.get('edison-simulation-monitoring-services-are-not-support'));
			}
		},
		error:function(msg){
			alert("System Exception error graph_event: " + msg);
		}
	});
}

//결과보기 - 에러보기
function <portlet:namespace/>error_event(simulationUuid, jobSeqNo, jobUuid){
	var searchData = {
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>jobSeqNo,": jobSeqNo,
			"<portlet:namespace/>jobUuid": jobUuid,
			"<portlet:namespace/>groupId": "<%=selectedGroupId%>"
			};
	jQuery.ajax({
		type: "POST",
		url: "<%=errorSimulationAPI%>",
		data  : searchData,
		datatype:"text",
		success:function(data){
			if(data!=""){
				var dataSize = data.length;
				var dataMap = data.portMapList;
				//console.log( JSON.stringify( dataMap, null, 4) );
				$dialogBody = $("#<portlet:namespace/>error-dialog-content");
				
				for(var i=0; i< dataMap.length; i++){
					$tr = $("<tr></tr>").addClass("postlist").attr("onClick",
							"event.cancelBubble=true;openAnalyzerWindow('" + 
							dataMap[i].exeFileName +"', '" + 
							dataMap[i].title +"', '" + 
							dataMap[i].portName +"', '" + 
							dataMap[i].portName +"', '" + 
							dataMap[i].groupId +"', '" + 
							dataMap[i].fileListType +"', '" + 
							dataMap[i].fileListValue +"', '" + 
							dataMap[i].jobUuid +"', '" + 
							dataMap[i].token +"', '" + 
							dataMap[i].plid +"', '" +
							dataMap[i].shipmentForm +"', '" +
							dataMap[i].lookUpPath +"', '" +
							dataMap[i].filePath +"', '" +
							simulationUuid+"');")
							.appendTo($dialogBody);
					$("<td></td>").addClass("TC").css("cursor","pointer").text(dataMap[i].portName).appendTo($tr);
					$("<td></td>").addClass("TC").css("cursor","pointer").text(dataMap[i].name).appendTo($tr);
				}
// 				$("#<portlet:namespace/>error-dialog").dialog("open");
				$("#<portlet:namespace/>error-dialog").dialog("open");
			}
			
			/* if ( data.length == 0 ){
				alert(Liferay.Language.get('edison-simulation-monitoring-log-file-is-not-exist'));
				return;
			}
			
			$content = $("#<portlet:namespace/>error-dialog-content");
			data = data.replace(/'/g, "&apos;").replace(/"/g, "&quot;");
			$content.html(data);
			$("#<portlet:namespace/>error-dialog").dialog("open"); */
		},
		error:function(msg){
			alert("System Exception[error_event]: " + msg);
		}
	});
}

//시뮬레이션 중지
function <portlet:namespace/>stop_simulation(jobSeqNo,simulationUuid,jobUuid,area){
	var con = confirm('<liferay-ui:message key="edison-simulation-monitoring-canceling-a-job" />');
	if(con){
		<portlet:namespace/>stopSimulationAPI(jobSeqNo,simulationUuid,jobUuid,area);
	}else{
	return;
	}
}

//작업 취소 API 호출 
function <portlet:namespace/>stopSimulationAPI(jobSeqNo,simulationUuid,jobUuid,area){
	if(jobUuid == ""){
		<portlet:namespace/>updateStatus(jobSeqNo,simulationUuid,"<%=MonitoringStatusConstatns.CANCELED%>");
<%-- 		<portlet:namespace/>eventChangeStatusView(area.attr("id"),"<%=MonitoringStatusConstatns.CANCELED%>",simulationUuid,jobSeqNo,false); --%>
		alert('<liferay-ui:message key="edison-simulation-monitoring-job-was-canceled" />');
	}else{
		var stopData = {
				"<portlet:namespace/>simulationUuid": simulationUuid,
				"<portlet:namespace/>jobUuid": jobUuid,
				"<portlet:namespace/>groupId": "<%=selectedGroupId%>"
				};
		jQuery.ajax({
			type: "POST",
			url: "<%=stopSimulationAPI%>",
			data  : stopData,
			dataType:"text",
			success:function(msg){
				if(msg=="200"){
					<portlet:namespace/>updateStatus(jobSeqNo,simulationUuid,"<%=MonitoringStatusConstatns.CANCELED%>");
					<portlet:namespace/>eventChangeStatusView(area.attr("id"),"<%=MonitoringStatusConstatns.CANCELED%>",simulationUuid,jobSeqNo,false);
					alert('<liferay-ui:message key="edison-simulation-monitoring-job-was-canceled" />');
				}else if(msg=="405"){
					alert('<liferay-ui:message key="edison-simulation-monitoring-the-state-can-not-cancel" />');
				}else if(msg=="404"){
					alert('<liferay-ui:message key="edison-simulation-monitoring-no-job" />');
				}else if(msg=="400"){
					alert("Bad Paramter.response CODE:"+msg);
				}else{
					alert('<liferay-ui:message key="edison-simulation-monitoring-bad-request" />.response CODE:'+msg);
				}
			},
			error:function(msg){
				alert("System Exception stopSimulationAPI: " + msg);
			}
		});
	}
}

//DB 상태 UPDATE
function <portlet:namespace/>updateStatus(jobSeqNo,simulationUuid,updateStatus){
	var updateData = {
					"<portlet:namespace/>jobSeqNo": jobSeqNo,"<portlet:namespace/>simulationUuid": simulationUuid,
					"<portlet:namespace/>groupId": "<%=selectedGroupId%>","<portlet:namespace/>jobStatus":updateStatus
	};
	jQuery.ajax({
		type: "POST",
		url: "<%=monitoringStatusUpdate%>",
		data  : updateData,
		error:function(msg){
			alert("System Exception updateStatus: " + msg);
		}
	});
}

//plus, minus button event 정의
$(function(){
	$("#mtbody").on("click","img.minusBtn",function(){
		var id = $(this).attr("id");
		$(this).attr("src","${contextPath}/images/monitoring/btn_plus.png");
		$(this).removeClass('minusBtn');
		$(this).addClass('plusBtn');
		
		$("tr[id^=cRow_"+id+"__]").remove();
	});
	
	$("#mtbody").on("click","img.plusBtn",function(){
		var myClass = $(this).attr("class");
		var id = $(this).attr("id");
		var parentRowId  = "row_"+$(this).attr("data-uuid");
		
		$(this).attr("src","${contextPath}/images/monitoring/btn_minus.png");
		$(this).removeClass('plusBtn');
		$(this).addClass('minusBtn');
		
		$hideJobList = $("#hideJobTable"); 
		$hideJobList.empty();
		var simulationUuid = id;
		var searchData = {"<portlet:namespace/>simulationUuid": simulationUuid,"<portlet:namespace/>groupId": "<%=selectedGroupId%>"};
		jQuery.ajax({
			type: "POST",
			url: "<%=monitoringSearchJobURL%>",
			async : false,
			data: searchData,
			dataType: 'json',
			success: function(dataMap) {
				var dataSize = dataMap.dataList.length;
				for(var i= 0 ; i<dataSize; i++){
					var data = dataMap.dataList[i];
					$hideJobTr = $("<tr></tr>");
					$hideJobTr.attr("id","cRow_"+id+"__"+data.jobSeqNo);
					$hideJobTr.attr("sub-job","N");
					$hideJobTr.attr("simulation-id",id);
					$hideJobTr.attr("data-status",data.jobStatus);
					$hideJobTr.attr("seq-no",data.jobSeqNo);
					
					$("<td></td>").addClass("TC").text("-").appendTo($hideJobTr);
					$("<td></td>").html(data.jobTitle).appendTo($hideJobTr);
					if(data.cluster != "EDISON-RESTORE"){
						$("<td></td>").addClass("TC").append(
								$("<img/>").attr("src","${contextPath}/images/monitoring/bnt_info.png")
										   .css("cursor","pointer")
										   .attr("onClick", "event.cancelBubble=true; <portlet:namespace/>searchSimulationParam('"+data.simulationUuid+"','"+data.jobSeqNo+"','"+data.jobUuid+"');")
						).appendTo($hideJobTr);
					}else{
						$("<td></td>").addClass("TC").append().appendTo($hideJobTr);
					}
					
					if(<%=deleteMonitoring%>){
						$("<td></td>").append(
								$("<span></span>").css("text-decoration","underline")
												  .css("cursor","pointer")
												  .click(function(){
													  <portlet:namespace/>searchUser(data.userId);
												  })
												  .html(data.userNm)
						).appendTo($hideJobTr);
					}
					
					$("<td></td>").addClass("TC").append(
							$("<img/>").attr("src","${contextPath}/images/monitoring/<%=themeDisplay.getLanguageId()%>/"+data.jobStatusImg)
						).appendTo($hideJobTr);
					
					
					$("<td></td>").addClass("TC").attr("id","job_controll").appendTo($hideJobTr);
					$("<td></td>").addClass("TC").attr("id","middle_check").attr("logFileProcess-state",data.jobLogFileProcessorYn).appendTo($hideJobTr);
					
					$jobManageTd = $("<td></td>").addClass("TC").appendTo($hideJobTr);
					if(<%=deleteMonitoring%>||data.userId==<%=themeDisplay.getUserId()%>){
						if(data.cluster != "EDISON-RESTORE"){
							$("<img>").attr("src","${contextPath}/images/monitoring/btn_monitor_delete.png")
									  .css("cursor","pointer")
									  .attr("onClick", "event.cancelBubble=true; <portlet:namespace/>deleteMonitoring('"+data.simulationUuid+"','"+data.jobSeqNo+"');")
									  .appendTo($jobManageTd);
							$jobManageTd.append("&nbsp;")
						}	
					}
					if(data.cluster != "EDISON-RESTORE"){
						$("<img>").attr("src","${contextPath}/images/monitoring/btn_monitor_rerun.png")
								  .css("cursor","pointer")
								  .attr("onClick", "event.cancelBubble=true; <portlet:namespace/>restartSimulation('"+data.scienceAppId+"', '"+data.jobUuid+"');")
								  .appendTo($jobManageTd);
					}
					$("<td></td>").addClass("TC").attr("id","result_down").appendTo($hideJobTr);
					$("<td></td>").addClass("TC").attr("postprocess-state",data.jobPostProcessorYn).attr("middleFileprocess-state",data.jobMiddleFileProcessorYn).attr("id","result_view").appendTo($hideJobTr);
					
					$hideJobTr.appendTo($hideJobList);
					if(data.cluster != "EDISON-RESTORE"){
						<portlet:namespace/>monitoringController(data.jobSeqNo,data.simulationUuid,data.jobUuid,data.scienceAppId,data.jobStatus,$("#cRow_"+id+"__"+data.jobSeqNo));
					}
				}
				$("#mtbody tr[id="+parentRowId+"]").after($("#hideJobTable tr").effect("highlight",{},3000));
			},
			error:function(msg){
				alert("System Exception plusBtn: " + msg);
			}
		});
	});
});

//시뮬레이션 파라미터 정보 조회
function <portlet:namespace/>searchSimulationParam(simulationUuid,jobSeqNo,jobUuid){
	var searchData = {
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>groupId": "<%=selectedGroupId%>",
			"<portlet:namespace/>jobSeqNo": jobSeqNo,
			"<portlet:namespace/>jobUuid": jobUuid
			};
	jQuery.ajax({
		type: "POST",
		url: "<%=monitoringSearchParam%>",
		data  : searchData,
		async : false,
// 		dataType: 'json',
		success: function(data) {
			$content = $("#<portlet:namespace/>jobparameter-dialog-content");
			$content = $content.empty();
			/* var optionSize = data.optionList.length;
			var paramSize = data.parameterList.length; */
			
			if(<%=deleteMonitoring%>){
				$("<div>").addClass("tbcell070201").html("SID: "+simulationUuid + "<br/>JID: "+jobUuid).appendTo($content);
			}
			
			$("<div>").addClass("tbcell070101").append(
					$("<img/>").attr("src","${contextPath}/images/monitoring/contents_arr.png")
					).append("<liferay-ui:message key='edison-button-board-submit-time'/>").appendTo($content);
			$("<div>").addClass("tbcell070302").html(data.jobSubmitDt).appendTo($content);
			
			$("<div>").addClass("tbcell070101").append(
					$("<img/>").attr("src","${contextPath}/images/monitoring/contents_arr.png")
					).append("<liferay-ui:message key='edison-simulation-monitoring-table-header-complete-time'/>").appendTo($content);
			
			var timeHtmlStr = "";
			if(data.jobEndDt!=""){
				timeHtmlStr = data.jobEndDt;
			}
			

			if(data.jobStatus=="<%=MonitoringStatusConstatns.QUEUED%>"){
				timeHtmlStr += "<span>(<liferay-ui:message key='edison-simulation-monitoring-submit'/>)</span>";
			}else if(data.jobStatus=="<%=MonitoringStatusConstatns.RUNNING%>"){
				timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-table-header-waiting-time"/>  '+data.stayDt+"/"+'<liferay-ui:message key="edison-simulation-monitoring-running"/>'+")</span>";
			}else if(data.jobStatus=="<%=MonitoringStatusConstatns.SUSPENDED%>"){
				timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-table-header-waiting-time"/>  '+data.stayDt+"/"+'<liferay-ui:message key="edison-simulation-monitoring-error"/>'+")</span>";
			}else if(data.jobStatus=="<%=MonitoringStatusConstatns.FAILED%>"){
				timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-error" />'+")</span>";
			}else if(data.jobStatus=="<%=MonitoringStatusConstatns.SUBMISSION_FAILED%>"){
				timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-error" />'+")</span>";
			}else if(data.jobStatus=="<%=MonitoringStatusConstatns.CANCELED%>"){
				timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-cancel" />'+")</span>";
			}else{
				timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-table-header-waiting-time"/>  '+data.stayDt+"/"+'<liferay-ui:message key="edison-simulation-monitoring-table-header-running-time"/>  '+data.executeDt+")</span>";
			}
			$("<div>").addClass("tbcell070202").css("border-bottom","solid 1px #dadada").html(timeHtmlStr).appendTo($content);
			
			if(data.inputPorts != "" && data.simulationJobData != "") {
				var inputPorts = JSON.parse(data.inputPorts);
				var simulationJobData = JSON.parse(data.simulationJobData);
				
				var portNameList = Object.keys(inputPorts);
				
				$("<div>").addClass("tbcell070101").append(
						$("<img/>").attr("src","${contextPath}/images/monitoring/contents_arr.png")
						).append("<liferay-ui:message key='edison-simulation-execute-job-pre' />").appendTo($content);
				
				
				for (var i = 0; i < portNameList.length; i++) {
					var portType = simulationJobData[portNameList[i]]["editor_type"];
					var portData = simulationJobData[portNameList[i]];
					
					var optionHtmlStr = "";
					if(portType == "Inputdeck") {
						optionHtmlStr+="<p style='color:black; margin: 0px;'>PortName : "+portNameList[i]+"</p><p style='white-space: pre-wrap; word-break: break-all;'>"+portData['port_data']+"</p>"
					} else if (portType == "File" || portType == "Text"){
						if(typeof portData != "object") {
							portData = JSON.parse(portData);
						}
						
						if(portData["fileName"] == "SAMPLE") {
							optionHtmlStr+="<p style='color:black; margin: 0px;'>PortName : "+portNameList[i]+"</p><p style='white-space: pre-wrap; word-break: break-all;'>SAMPLE FILE</p>"
						} else {
							optionHtmlStr+="<p style='color:black; margin: 0px;'>PortName : "+portNameList[i]+"</p><p style='white-space: pre-wrap; word-break: break-all;'>"+ "File Name : " +portData["fileName"]+"</p>"
						}
					} else {
						optionHtmlStr+="<p style='color:black; margin: 0px;'>PortName : "+portNameList[i]+"</p><p style='white-space: pre-wrap; word-break: break-all;'>"+portData+"</p>"
					}
					
					$("<div>").addClass("tbcell070201").html(optionHtmlStr).appendTo($content);;
					
				}
			}
			
			/* if(optionSize>0){
				$("<div>").addClass("tbcell070101").append(
						$("<img/>").attr("src","${contextPath}/images/monitoring/contents_arr.png")
						).append("<liferay-ui:message key='edison-simulation-execute-job-pre' />").appendTo($content);
				var optionHtmlStr = "";
				for(var i=0;i<optionSize;i++){
					if(data.optionList[i].optionType == "1907003" || data.optionList[i].optionType == "1907004" || data.optionList[i].optionType == "1907005"){
						optionHtmlStr+="<p>"+data.optionList[i].optionNm+" : "+data.optionList[i].optionLogicalFileValue+"</p>"
					}else{
						optionHtmlStr+="<p>"+data.optionList[i].optionNm+" : "+data.optionList[i].optionValue+"</p>"
					}
				}
				
				$("<div>").addClass("tbcell070201").html(optionHtmlStr).appendTo($content);;
			}
			
			if(paramSize>0){
				$("<div>").addClass("tbcell070101").append(
						$("<img/>").attr("src","${contextPath}/images/monitoring/contents_arr.png")
						).append("<liferay-ui:message key='edison-simulation-execute-job-detail'/>").appendTo($content);;
				var paramHtmlStr = "";
				for(var i=0;i<paramSize;i++){
					var dataValue = data.parameterList[i];
					if(dataValue.paraElement == "1909005"){
						paramHtmlStr+="<p>"+dataValue.paraName + dataValue.paraDelimiterStr + dataValue.paraLogicalFileValue+dataValue.paraSeperatorStr+"</p>";
					}else{
						paramHtmlStr+="<p>"+dataValue.paraName + dataValue.paraDelimiterStr + dataValue.paraValue + dataValue.paraSeperatorStr+"</p>";
					}
				}
				$("<div>").addClass("tbcell070301").css("border-bottom","none").html(paramHtmlStr).appendTo($content);;
				
			} */
			
			$("#<portlet:namespace/>jobparameter-dialog").dialog("open");
		},
		error:function(msg){
			alert("System searchSimulationParam : " + msg);
		}
	});
}

//모니터링 Job 삭제
//jobSeqNo 가 0일 때는 JOB테이블의 리스트 삭제
function <portlet:namespace/>deleteMonitoring(simulationUuid,jobSeqNo){
	var deleteData = {
			"<portlet:namespace/>simulationUuid": simulationUuid,
			"<portlet:namespace/>groupId": "<%=selectedGroupId%>",
			"<portlet:namespace/>jobSeqNo": jobSeqNo
			};
	
	if(confirm("<liferay-ui:message key='edison-simulation-monitoring-delete-job-question'/>")){
		jQuery.ajax({
			type: "POST",
			url: "<%=monitoringDeleteJobURL%>",
			data  : deleteData,
			async : false,
			success: function(msg) {
				if(msg=="SUCCESS"){
					alert('Delete Success');
					$("#<portlet:namespace/>monitoringSearch").submit();
				}else{
					alert("Delete Failed\n"+msg+"");
				}
			},
			error:function(msg){
				alert("System deleteMonitoring error"+msg);
			}
		});
	}
}

//재실행
function <portlet:namespace/>restartSimulation(p_scienceAppId, p_jobUuid){
	var thisPortletNamespace = "_Workbench_WAR_OSPWorkbenchportlet_";

	var URL = "<%=workbenchURL%>";
	var params = "&" +thisPortletNamespace+ "scienceAppId=" + p_scienceAppId;
	params += "&" +thisPortletNamespace+ "jobUuid=" + p_jobUuid;
	params += "&" +thisPortletNamespace+ "customId=0";
	params += "&" +thisPortletNamespace+ "classId=0";
	params += "&" +thisPortletNamespace+ "testYn=false";

	console.log(URL + params);
	location.href = URL + params;
}

/*이벤트에 따른 상태 관련 화면 변경*/
function <portlet:namespace/>eventChangeStatusView(rowId,jobStatus,simulationUuid,jobSeqNo,jobStatusSearch){
	var searchData = {
			"<portlet:namespace/>simulationUuid": simulationUuid , 
			"<portlet:namespace/>jobSeqNo": jobSeqNo,
			"<portlet:namespace/>groupId": "<%=selectedGroupId%>",
			"<portlet:namespace/>jobStatusSearch": jobStatusSearch
		};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=monitoringSearchJobURL%>",
		data  : searchData,
		async : false,
		dataType: 'json',
		success: function(msg) {
			var data = msg.data;
			if(jobStatus!=data.jobStatus){
				$trArea = $("#"+rowId);
				$status_area = $trArea.find("td:eq(4)");
				
				$status_area.empty();
				$status_area.addClass("TC").append(
						$("<img/>").attr("src","${contextPath}/images/monitoring/<%=themeDisplay.getLanguageId()%>/"+data.jobStatusImg)
				);
				$trArea.attr("data-status",data.jobStatus);
				if(data.cluster != "EDISON-RESTORE"){
					<portlet:namespace/>monitoringController(data.jobSeqNo,data.simulationUuid,data.jobUuid,data.scienceAppId,data.jobStatus,$trArea)
				}	
			}
		},
		error:function(msg){
			alert("System Exception eventChangeStatusView: " + msg);
		}
	});
};

//동기화 함수
function <portlet:namespace/>intervalData(){
	$("#mtbody > tr[sub-job=N]").each(function(){
		var rowStatus = $(this).attr("data-status");
		var CANCELED = Number("<%=MonitoringStatusConstatns.CANCELED%>");
		if(rowStatus<CANCELED){
			if(typeof $(this).attr("seq-no") =="undefined"){
				<portlet:namespace/>eventChangeStatusView($(this).attr("id"),rowStatus,$(this).attr("simulation-id"),1,true);
			}else{
				<portlet:namespace/>eventChangeStatusView($(this).attr("id"),rowStatus,$(this).attr("simulation-id"),$(this).attr("seq-no"),true);
			}
			
		}
	});
}

//초기 조회 - 초기 조회시 모니터링 작업 영역 UPDATE
function <portlet:namespace/>initMonitoringController(){
	bStart();
	$("#mtbody > tr[sub-job=N]").each(function(){
		if($(this).attr("cluster") != "EDISON-RESTORE"){
			<portlet:namespace/>monitoringController(1,$(this).attr("simulation-id"),$(this).attr("id").replace("row_",""),$(this).attr("scienceApp-id"),$(this).attr("data-status"), $(this));
		}
	});
	bEnd();
}
AUI().ready(function() {
	<portlet:namespace/>initMonitoringController();
	
	setInterval(<portlet:namespace/>intervalData,15*1000);
	
	$("#<portlet:namespace/>jobparameter-dialog").dialog({
		autoOpen: false,
		width: '600',
		height: '580',
		modal: true,
		resizable: true,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	$(this).parent().css('overflow','visible');
	    	
	    	$("body").css('overflow','hidden');
	    	
	    	$("#<portlet:namespace/>jobparameter-dialog-dialog-close-btn").bind('click',function(){
	    		$("#<portlet:namespace/>jobparameter-dialog").dialog("close");
	    	});
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#<portlet:namespace/>jobparameter-dialog").dialog("close");
			})
	    	
	    },
	    close: function() {
	    	$("#<portlet:namespace/>jobparameter-dialog-content").empty();
	    	$("body").css('overflow','');
	    }
	}).draggable({
			handle: "#<portlet:namespace/>jobparameter-dialog-title"
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	
	
	
	$("#<portlet:namespace/>error-dialog").dialog({
		autoOpen: false,
		width: 700,
		height: 'auto',
		modal: true,
		resizable: true,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		draggable:true,
		open: function(event, ui) {
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	$(this).parent().css('overflow','visible');
	    	
	    	$("body").css('overflow','hidden');
	    	$("#<portlet:namespace/>error-dialog-close-btn").bind('click',function(){
	    		$("#<portlet:namespace/>error-dialog").dialog("close");
	    	});
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#<portlet:namespace/>error-dialog").dialog("close");
			})
	    },
	    close: function() {
	    	$("#<portlet:namespace/>error-dialog-content").empty();
	    	$("body").css('overflow','');
	    }
	}).draggable({
			handle: "#<portlet:namespace/>error-dialog-title"
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	
	
	
	$("#<portlet:namespace/>post-dialog").dialog({
		autoOpen: false,
		width: 700,
		height: 'auto',
		modal: true,
		resizable: true,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	$(this).parent().css('overflow','visible');
	    	
	    	$("body").css('overflow','hidden');
	    	
	    	$("#<portlet:namespace/>post-dialog-close-btn").bind('click',function(){
	    		$("#<portlet:namespace/>post-dialog").dialog("close");
	    	});
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#<portlet:namespace/>post-dialog").dialog("close");
			})
	    },
	    close: function() {
	    	$("body").css('overflow','');
	    	$("#<portlet:namespace/>post-dialog-content").empty();
	    }
	}).draggable({
			handle: "#<portlet:namespace/>post-dialog-title"
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	$("#<portlet:namespace/>show-analyzer-dialog").dialog({
		autoOpen: false,
		width: 1200,
		height: 800,
		modal: true,
		resizable: false,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
/* 			$('.ui-dialog-titlebar-close').bind('click',function(){
				$("#<portlet:namespace/>show-analyzer-dialog").dialog("close");
	    	});
			$('.ui-widget-overlay').bind('click',function(){
				$("#<portlet:namespace/>show-analyzer-dialog").dialog("close");
	    	}); */
	    },
	    close: function() {
// 	    	$("body").css('overflow','');
// 			$("#<portlet:namespace/>show-analyzer-dialog").dialog("close");
// 			$("#<portlet:namespace/>show-analyzer-dialog-content").empty();
			
			location.reload();
	    }
	})
	
	
	/* simulationCustomId가 있을 경우 url에 추가 */
	if("${simulationCustomId}" > 0) {
		var searchForm = document.<portlet:namespace/>monitoringSearch;
		searchForm.action = "<%=monitoringSearchUrl%>" + "&simulationCustomId=" + "${simulationCustomId}";
	}
});

function openAnalyzerWindow(
		portletId, 
		portletTitle, 
		taskId, 
		portName, 
		groupId, 
		fileListType, 
		fileListValue, 
		jobUuid, 
		token, 
		plid,
		shipmentForm,
		lookUpPath,
		filePath,
		simulationUuid){
// 	console.log('Portelt ID: '+ portletId);
	AUI().use("liferay-portlet-url", function(a) {
	  	if($("#<portlet:namespace/>post-dialog").dialog('isOpen')){
	  		$("#<portlet:namespace/>post-dialog").dialog("close");
	  	}
	  	
  		<%-- var renderURL = Liferay.PortletURL.createRenderURL();
		renderURL.setPortletId( 'Workbench_WAR_OSPWorkbenchportlet');
		renderURL.setWindowState('<%=LiferayWindowState.POP_UP%>');
	  	renderURL.setParameter("workbenchType", "MORANALYSIS");
	  	console.log(portletId)
		renderURL.setParameter("portletId", portletId);
		renderURL.setParameter("pathType", shipmentForm);
		renderURL.setParameter("parentPath", lookUpPath + "/result/");
		renderURL.setParameter("fileName", filePath);
		renderURL.setParameter("relative", true);
		renderURL.setParameter("loadNow", true);  --%>
// 		renderURL.setParameter("pathType", "url");
// 		renderURL.setParameter("url", lookUpPath + "/result/" + filePath);
		
		
		
	  	/* openWindow(URL, portName, 1230, 850, portletTitle+" - "+filePath); */
		
		var param = "";
		param += "&_Workbench_WAR_OSPWorkbenchportlet_pathType="+shipmentForm;
		param += "&_Workbench_WAR_OSPWorkbenchportlet_parentPath="+lookUpPath;
		param += "&_Workbench_WAR_OSPWorkbenchportlet_fileName="+filePath;
		param += "&_Workbench_WAR_OSPWorkbenchportlet_portletId="+portletId;
		param += "&_Workbench_WAR_OSPWorkbenchportlet_relative=true";
		param += "&_Workbench_WAR_OSPWorkbenchportlet_loadNow=true";
		param += "&_Workbench_WAR_OSPWorkbenchportlet_height=0.73";
		param += "&_Workbench_WAR_OSPWorkbenchportlet_connector=<%=portletDisplay.getId()%>";
		
		var URL = "<%=monitoringAnalysisURL%>" + param;
		$("#<portlet:namespace/>show-analyzer-dialog").load(URL).dialog('open');
	});
}

function openWindow(renderURL, portName, width, height, title)
{
	var dialogId = portName+Date.now();
	/* renderURL.setName("Popup");
	renderURL.setWindowState("exclusive"); 
	renderURL.setParameter("dialogId", dialogId);
	renderURL.setParameter("workflowType", "workflow"); */
		
	/* Liferay.Util.openWindow({
		dialog: {
			cache: false,
          	destroyOnClose: true,
			centered: true,
			modal: true,
			resizable: false,
			width:width, 
			height:height
		},
		id: dialogId ,
		title: title,
		uri : url+ '&p_p_auth='+ renderURL.params.token
	}); */
}
function <portlet:namespace/>closeDialog ( data ){
	//alert( JSON.stringify(data,null,4));
	<portlet:namespace/>dialogSection.dialog('close');
}

function IsJsonString(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}
</script>