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
	<div class="virtitlebox">
		<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
		<div class="virtitle">
			<liferay-ui:message key='edison-simulation-project' />
		</div>
	</div>
	
	<div class="h10"></div>
	<div class="tabletopbox clear">
		<div class="search">
			<div class="searchbox">
				<input name="<portlet:namespace/>textfield" type="text" id="<portlet:namespace/>textfield" placeholder="<liferay-ui:message key="edison-table-list-header-title"/>" size="40" value="${searchText}" 
					onKeydown="if(event.keyCode ==13)<portlet:namespace/>searchSimulationProjectList('1');" />
				<input type="button" name="fullsize" id="fullsize" value="" class="btnsearch" onclick="<portlet:namespace/>searchSimulationProjectList('1');">
			</div>
			
			<input type="button" name="fullsize" id="fullsize" value="<liferay-ui:message key="edison-button-all-search"/>" class="button01" onclick="<portlet:namespace/>initSimulationProjectList();">
		</div>
		
		<!--우편 셀렉트-->
		<div class="tabletopright">
			<select id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" title="옵션" onchange="<portlet:namespace/>searchSimulationProjectList('${currentPage}');" class="selectview" style="line-height: 15px;">
				<option value="5" <c:if test="${listSize eq 5}">selected="selected"</c:if>>5<liferay-ui:message key="edison-search-views"/></option>
				<option value="10" <c:if test="${listSize eq 10}">selected="selected"</c:if>>10<liferay-ui:message key="edison-search-views"/></option>
				<option value="15" <c:if test="${listSize eq 15}">selected="selected"</c:if>>15<liferay-ui:message key="edison-search-views"/></option>
				<option value="20" <c:if test="${listSize eq 20}">selected="selected"</c:if>>20<liferay-ui:message key="edison-search-views"/></option>
			</select>
		</div>
	</div>	
	<div class="table7_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
					<th scope="col"><liferay-ui:message key="edison-table-list-header-date"/></th>
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
								<td class="TC">
									${seq - status.index}
								</td>
								<td style="cursor:pointer;" onclick="<portlet:namespace/>goDetailView('${mySimulationProject.simulationProjectId}');" >
									<div style="word-break:break-all;" class="ellipsis">${mySimulationProject.title}</div>
								</td>
								<td class="TC" >
									<c:if test="${mySimulationProject.projectOpenYn eq true}"><liferay-ui:message key='edison-simulation-project-public' /></c:if>
									<c:if test="${mySimulationProject.projectOpenYn ne true}"><liferay-ui:message key='edison-simulation-project-private' /></c:if>
								</td>
								<td class="TC">
									${mySimulationProject.screenName}
								</td>
								<td class="TC">
									${mySimulationProject.insertDtStr}
								</td>
							</tr>	
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<div id="<portlet:namespace/>paging" class="paging">${paging}</div>
	
	<div class="buttonbox"style="width:auto; position:absolute; right:1%; bottom: 24px;">
		<c:if test="${not empty userId || userId ne null }">
			<input class="addIp button01b" style="min-width:90px;" onclick="<portlet:namespace/>goCreateManagementPage();" 
				value="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-create'/>" type="button">
		</c:if>
	</div>
</div>

<script type="text/javascript">

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
