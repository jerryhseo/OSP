<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>	

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>

<%
	String portletWindowState = (String)request.getAttribute("portletWindowState");
%>
<liferay-portlet:renderURL var="simulationProjectSearchURL" copyCurrentRenderParameters="false" windowState="<%=portletWindowState%>">
	<liferay-portlet:param name="isRedirect" value="${isRedirect}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="managetmentSimulationProjectUrl" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="management" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="isRedirect" value="${isRedirect}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="detailViewSimulationProjectUrl" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="detailView" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="isRedirect" value="${isRedirect}" />
</liferay-portlet:renderURL>

<div id="<portlet:namespace/>display" style="display:none; margin-bottom: 30px;" >
	
	<%-- <div class="h10"></div>
	<div class="tabletopbox clear">
		<!--Paging 셀렉트-->
		<div class="tabletopright">
			<select id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" title="옵션" onchange="<portlet:namespace/>searchSimulationProjectList('${currentPage}');" class="selectview" style="line-height: 15px;">
				<option value="5" <c:if test="${listSize eq 5}">selected="selected"</c:if>>5<liferay-ui:message key="edison-search-views"/></option>
				<option value="10" <c:if test="${listSize eq 10}">selected="selected"</c:if>>10<liferay-ui:message key="edison-search-views"/></option>
				<option value="15" <c:if test="${listSize eq 15}">selected="selected"</c:if>>15<liferay-ui:message key="edison-search-views"/></option>
				<option value="20" <c:if test="${listSize eq 20}">selected="selected"</c:if>>20<liferay-ui:message key="edison-search-views"/></option>
			</select>
		</div>
	</div>	 --%>
	<div class="table-responsive panel edison-panel">
		
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				<liferay-ui:message key='edison-simulation-project' />
			</h3>
			
			<div class="input-group">
				<input name="<portlet:namespace/>textfield" type="text" id="<portlet:namespace/>textfield" placeholder="<liferay-ui:message key="edison-table-list-header-title"/>" size="40" value="${searchText}" 
						class="form-control" onKeydown="if(event.keyCode ==13)<portlet:namespace/>searchSimulationProjectList('1');" />
				<div class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="<portlet:namespace/>searchSimulationProjectList('1');"><i class="icon-search"></i></button>
					<button class="btn btn-default dropdown-toggle" type="button" name="fullsize" id="fullsize" onclick="<portlet:namespace/>initSimulationProjectList();"><liferay-ui:message key="edison-button-all-search"/></button>
					
					<select class="btn btn-default dropdown-toggle" id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" title="옵션" onchange="<portlet:namespace/>searchSimulationProjectList('${currentPage}');" style="line-height: 15px;">
						<option value="5" <c:if test="${listSize eq 5}">selected="selected"</c:if>>5<liferay-ui:message key="edison-search-views"/></option>
						<option value="10" <c:if test="${listSize eq 10}">selected="selected"</c:if>>10<liferay-ui:message key="edison-search-views"/></option>
						<option value="15" <c:if test="${listSize eq 15}">selected="selected"</c:if>>15<liferay-ui:message key="edison-search-views"/></option>
						<option value="20" <c:if test="${listSize eq 20}">selected="selected"</c:if>>20<liferay-ui:message key="edison-search-views"/></option>
					</select>
				</div>
			</div>
		</div>
		
		<table class="table table-bordered table-hover edison-table" width="100%" border="0" cellspacing="0" cellpadding="0">
			<colgroup>
				<col width="10%" />
				<col width="*" />
				<col width="15%" />
				<col width="11%" />
				<col width="11%" />				
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-index" /></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-title"/></th>
					<th scope="col"><liferay-ui:message key="edison-workflow-public-status"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-name"/></th>
					<c:if test="${portletWindowState ne 'pop_up' }">
						<th scope="col"><liferay-ui:message key="edison-table-list-header-date"/></th>
					</c:if>
					<c:if test="${portletWindowState eq 'pop_up' }">
						<th scope="col"></th>s
					</c:if>
				</tr>
			</thead>
			<tbody id="<portlet:namespace/>mySimulationProjectListBody">
				<c:choose>
					<c:when test="${fn:length(mySimulationProjectList) eq 0 }">
						<tr>
							<td colspan="5" style="text-align: center; ">
								<liferay-ui:message key='edison-there-are-no-data' />
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="mySimulationProject" items="${mySimulationProjectList}" varStatus="status">
							<tr>
								<td class="TC center">
									${seq - status.index}
								</td>
								<!-- pop up인 경우 title style, onclick function 제거 -->
								<c:if test="${portletWindowState ne 'pop_up' }">
									<td style="cursor:pointer;" onclick="<portlet:namespace/>goDetailView('${mySimulationProject.simulationProjectId}');" >
								</c:if>
								<c:if test="${portletWindowState eq 'pop_up' }">
									<td>
								</c:if>
										<div style="word-break:break-all;" class="ellipsis">${mySimulationProject.title}</div>
									</td>
								<td class="TC center" >
									<c:if test="${mySimulationProject.projectOpenYn eq true}"><liferay-ui:message key='edison-simulation-project-public' /></c:if>
									<c:if test="${mySimulationProject.projectOpenYn ne true}"><liferay-ui:message key='edison-simulation-project-private' /></c:if>
								</td>
								<td class="TC center">
									${mySimulationProject.screenName}
								</td>
								<td class="TC center">
									<c:if test="${portletWindowState ne 'pop_up' }">
										${mySimulationProject.insertDtStr}
									</c:if>
									<!-- popup인 경우 선택버튼 추가 -->
									<c:if test="${portletWindowState eq 'pop_up'}">
										<c:choose>
											<c:when test="${fn:contains(customIdList, mySimulationProject.simulationProjectId)}">
											</c:when>
											<c:otherwise>
												<!-- mouse hover 필요 -->
												<input style="cursor: pointer;" type="button" class="topbtn" onclick="<portlet:namespace/>selectProjectForShare('${mySimulationProject.title}', '${mySimulationProject.simulationProjectId}', '${simulationClassId}');" value="<liferay-ui:message key='edison-table-list-header-select' />" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</td>
							</tr>	
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<div id="<portlet:namespace/>paging" class="paging">${paging}</div>
	
	<c:if test="${portletWindowState ne 'pop_up' }">
		<div class="buttonbox"style="width:auto; right:1%; bottom: 24px; float: right;">
			<c:if test="${not empty userId || userId ne null }">
				<input class="addIp btn btn-default" style="min-width:90px;" onclick="<portlet:namespace/>goCreateManagementPage();" 
					value="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-create'/>" type="button">
			</c:if>
		</div>
	</c:if>
</div>

<script type="text/javascript">

/* 시뮬레이션 공유 */
function <portlet:namespace/>selectProjectForShare(projectTitle, customId, classId){
	if(confirm("\'" + projectTitle + "\' <liferay-ui:message key='edison-share-simulation-in-project-alert'/>") == true){
		AUI().use("liferay-portlet-url", function(a) {
			var portletURL = Liferay.PortletURL.createResourceURL();
			//portletURL.setPortletId("edisonbestsimulation_WAR_edisonsimulationportlet");
			portletURL.setPortletId("edisonmonitoring_WAR_edisonsimulationportlet");
			portletURL.setResourceId("simulationSharing");
			portletURL.setParameter("classId", classId);
			portletURL.setParameter("customId", customId);		// projectId
			portletURL.setParameter("simulationUuid", "${simulationUuid}");
			portletURL.setParameter("simulationJobUuid", "${simulationJobUuid}");
			portletURL.setParameter("simulationJobSeqNo", "${simulationJobSeqNo}");
			
			jQuery.ajax({
				type: "POST",
				url: portletURL,		// simulationController로 요청하는 URL
				async : false,
				dataType: 'json',
				success: function(result) {
					// Close Popup
					if(result.shareResult){
						<portlet:namespace/>writeTimeLineAboutSharing(customId);
					}
					<portlet:namespace/>closePopup('${dialogId}');
				},error:function(jqXHR, textStatus, errorThrown){
					alert("<liferay-ui:message key='edison-share-simulation-failed-message'/>");
				}
			});
		});
	} else{
	}
}

/* Popup close */
Liferay.provide(window,'<portlet:namespace />closePopup',
	function(popupIdToClose) {
		Liferay.Util.getWindow(popupIdToClose).destroy(); // You can try toggle/hide whatever You want
		},
	['liferay-util-window']
);
	
/* 시뮬레이션 공유 시간, 정보 - 타임라인 생성 */
function <portlet:namespace/>writeTimeLineAboutSharing(customId){
	
	var commentMessage = "<liferay-ui:message key='edison-share-simulation-success-message'/></br>" +
	                     "<liferay-ui:message key='edison-table-list-header-simulation-nm'/> : ${simulationTitle}</br>" +
	                     "<liferay-ui:message key='edison-simulation-execute-job-create-list-job-name'/> : ${simulationJobTitle}</br>";
	
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createResourceURL();
		portletURL.setPortletId("edisoncomment_WAR_edisonboard2016portlet");
		portletURL.setResourceId("writeTimeLineAboutSharing");
		portletURL.setParameter("groupBoardSeq", customId);		// projectId
		portletURL.setParameter("comment", commentMessage);
		
		jQuery.ajax({
			type: "POST",
			url: portletURL,
			async : false,
			dataType: 'json',
			success: function(result) {
				
			},error:function(jqXHR, textStatus, errorThrown){
				alert("<liferay-ui:message key='edison-share-timeline-failed-message'/>");
			}
		});
	});
}


AUI().ready(function() {
	$("#<portlet:namespace/>display").css("display", "block");
});

function <portlet:namespace/>searchSimulationProjectList(p_curPage){
	var searchParameter = "";
	if($("#<portlet:namespace/>textfield").val()!=""){
		searchParameter += "&<portlet:namespace/>searchText="+$("#<portlet:namespace/>textfield").val();
	}
	searchParameter += "&<portlet:namespace/>currentPage="+p_curPage;
	searchParameter += "&<portlet:namespace/>listSize="+$("#<portlet:namespace/>select_line option:selected").val();
	searchParameter += "&<portlet:namespace/>methodName=" + "<portlet:namespace/>searchSimulationProjectList";
	location.href="<%=simulationProjectSearchURL%>"+searchParameter;
}

function <portlet:namespace/>initSimulationProjectList(){
	var searchParameter = "";
	$("#<portlet:namespace/>textfield").val('');
	searchParameter += "&<portlet:namespace/>currentPage="+1;
	searchParameter += "&<portlet:namespace/>listSize="+10;
	location.href="<%=simulationProjectSearchURL%>"+searchParameter;
}

function <portlet:namespace/>goManagement(simulationProjectId){
	
	var searchParameter = "";
	if($("#<portlet:namespace/>textfield").val()!=""){
		searchParameter += "&<portlet:namespace/>searchText="+$("#<portlet:namespace/>textfield").val();
	}
	searchParameter += "&<portlet:namespace/>currentPage="+"${currentPage}";
	searchParameter += "&<portlet:namespace/>listSize="+$("#<portlet:namespace/>select_line option:selected").val();
	searchParameter += "&<portlet:namespace/>methodName=" + "<portlet:namespace/>searchSimulationProjectList";
	searchParameter += "&<portlet:namespace/>simulationProjectId=" + simulationProjectId;
	location.href="<%=managetmentSimulationProjectUrl%>"+searchParameter;
}

function <portlet:namespace/>goDetailView(simulationProjectId){
	
	var searchParameter = "";
	if($("#<portlet:namespace/>textfield").val()!=""){
		searchParameter += "&<portlet:namespace/>searchText="+$("#<portlet:namespace/>textfield").val();
	}
	searchParameter += "&<portlet:namespace/>currentPage="+"${currentPage}";
	searchParameter += "&<portlet:namespace/>listSize="+$("#<portlet:namespace/>select_line option:selected").val();
	searchParameter += "&<portlet:namespace/>methodName=" + "<portlet:namespace/>searchSimulationProjectList";
	searchParameter += "&<portlet:namespace/>simulationProjectId=" + simulationProjectId;
	location.href="<%=detailViewSimulationProjectUrl%>"+searchParameter;
}

function <portlet:namespace/>goCreateManagementPage(){
	
	var searchParameter = "";
	if($("#<portlet:namespace/>textfield").val()!=""){
		searchParameter += "&<portlet:namespace/>searchText="+$("#<portlet:namespace/>textfield").val();
	}
	searchParameter += "&<portlet:namespace/>currentPage="+"${currentPage}";
	searchParameter += "&<portlet:namespace/>listSize="+$("#<portlet:namespace/>select_line option:selected").val();
	searchParameter += "&<portlet:namespace/>methodName=" + "<portlet:namespace/>searchSimulationProjectList";
	location.href="<%=managetmentSimulationProjectUrl%>"+searchParameter;
}

function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}

</script>
