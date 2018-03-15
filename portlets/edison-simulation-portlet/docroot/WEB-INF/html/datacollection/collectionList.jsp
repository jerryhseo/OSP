<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>

<liferay-portlet:renderURL var="collectionSearchURL" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="detailViewDataCollectionURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" >
	<liferay-portlet:param name="myRender" value="detailViewDataCollection" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="manageDataCollectionURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" >
	<liferay-portlet:param name="myRender" value="manageDataCollection" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="retrieveListDataCollectionURL" id="retrieveListDataCollection" copyCurrentRenderParameters="false" escapeXml="false"/>

<style>
.edison .table1_list tr.selected {
	background-color: #A5CAEF;
}

</style>


	
<aui:form name="collectionForm" method="post">
	<aui:input type="hidden" name="isAdmin" value="${isAdmin }"></aui:input>	
	<aui:input type="hidden" name="groupId" value="${groupId }"></aui:input>	
	
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h1>
				<img src="${contextPath}/images/title_virtual.png" />
				Data Collection
			</h1>
			
			<div class="btn-group pull-right">
				<input name="<portlet:namespace/>textfield" type="text"
					id="<portlet:namespace/>textfield"
					class="form-control"
					placeholder="<liferay-ui:message key="edison-table-list-header-name"/>"
					size="40"
					onKeydown="if(event.keyCode ==13)<portlet:namespace/>dataCollectionList('');" 
					value="${searchText }"
					style="width: 220px; float: left;"
				/>
				<button class="btn btn-default" type="button" name="fullsize" id="fullsize" onclick="<portlet:namespace/>dataCollectionList('');">
					<i class="icon-search"></i>
				</button>
				<input type="button" name="fullsize" id="fullsize" value="<liferay-ui:message key="edison-button-all-search"/>" class="btn btn-default" onclick="<portlet:namespace/>dataCollectionListAllSearch();">
				
				<!--우편 셀렉트-->
				<select id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" title="옵션" onchange="<portlet:namespace/>dataCollectionList('');" class="btn btn-default" style="line-height: 15px;">
					<option value="10">10<liferay-ui:message key="edison-search-views"/></option>
					<option value="15">15<liferay-ui:message key="edison-search-views"/></option>
					<option value="20">20<liferay-ui:message key="edison-search-views"/></option>
				</select>
				
			</div>
			
		</div>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="내 사이언스데이터 테이블" class="table table-bordered table-hover edison-table">
			<colgroup>
				<col width="10%" />
				<col width="27%" />
				<col width="27%" />
				<col width="15%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-index" /></th>
					<th scope="col"><liferay-ui:message key="edison-create-account-field-title-name"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-data-type-name"/></th>
					<th scope="col"><liferay-ui:message key="edison-virtuallab-version"/></th>
					<th scope="col"><liferay-ui:message key="edison-workflow-public-status"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-date"/></th>
				</tr>
			</thead>
			<tbody id="collectionTableBody">
				<c:choose>
					<c:when test="${fn:length(collectionList) == 0 }">
						<tr>
							<td class="center" colspan="6"><liferay-ui:message key="edison-there-are-no-data"/></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="collection" items="${collectionList }" varStatus="status">
							<c:if test="${status.index % 2 == 1}">
								<tr id="collectionTr_${collection.collectionId }" class="tablebgtr" style="word-break: break-all; cursor: pointer;"
									onClick="<portlet:namespace/>collectionMoveDetail('${collection.collectionId }');">	
							</c:if>
							<c:if test="${status.index % 2 == 0}" >
								<tr id="collectionTr_${collection.collectionId }" style="word-break: break-all; cursor: pointer;"
									onClick="<portlet:namespace/>collectionMoveDetail('${collection.collectionId }');">
							</c:if>
								<td class="center">${seq - status.index }</td>
								<td >${collection.name }</td>
								<td >${collection.dataType.name}</td>
								<td class="center">${collection.version }</td>
								<td class="center">
								<c:choose>
									<c:when test="${collection.status eq 'private'}">
										<liferay-ui:message key="edison-simulation-project-private"/>
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="edison-simulation-project-public"/>
									</c:otherwise>
								</c:choose>
								</td>
								
								<td class="center">${collection.createDate }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<div class="paging text-center">
			<div id="<portlet:namespace/>paging" style="width:100%;text-align: center;">${paging }</div>
		</div>
		
		<div class="h10"></div>
		
		<div class="buttonbox" id="<portlet:namespace/>createCollection" style="float: right;">
			<c:choose>
				<c:when test="<%=LiferayWindowState.isPopUp(request)%>">
					<input type="button" class="btn btn-default choiceButton" value="<liferay-ui:message key="select" />" onclick="<portlet:namespace/>dataCollectionChoice()" style="display:none"/>
				</c:when>
				<c:otherwise>
					<c:if test="${ isAdmin==true }">
						<input type="button" class="btn btn-default" value="<liferay-ui:message key="edison-virtuallab-virtualLabClassManagement-class-create" />" onclick="<portlet:namespace/>createCollection()"/>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</aui:form>
	

<script>
$(function(){
	var searchLine = "${searchLine}";
	if(searchLine != ""){
		$("#<portlet:namespace/>select_line").val(searchLine);
	}
})
var currentPage = "";
function <portlet:namespace/>dataCollectionListAllSearch(){
	 $("#<portlet:namespace/>textfield").val("");
	 <portlet:namespace/>dataCollectionList('');
}
function <portlet:namespace/>dataCollectionList(p_currentPage){
	currentPage = p_currentPage;
	
	var searchParameter = <portlet:namespace/>createSearchParameterSting("");
	location.href="<%=collectionSearchURL%>"+searchParameter;
}

function <portlet:namespace/>createCollection(){
	var mode = "<%=Constants.ADD%>";
	var searchParameter = <portlet:namespace/>createSearchParameterSting(mode);
	location.href="<%=manageDataCollectionURL%>"+searchParameter;
	
}

var selectedCollectionId = "";
function <portlet:namespace/>collectionMoveDetail(collectionId){
	if('${portletWindowState}'=='<%=LiferayWindowState.POP_UP.toString()%>'){
		$("tr[class$=selected]").removeClass("selected");
		$collectionTr = $("#collectionTr_"+collectionId);
		$collectionTr.addClass(" selected");
		
		selectedCollectionId = collectionId;
		
		$(".choiceButton").show();
	}else{
		var mode = "<%=Constants.VIEW%>";
		var searchParameter = <portlet:namespace/>createSearchParameterSting(mode);
		searchParameter += "&<portlet:namespace/>collectionId="+collectionId;
		location.href="<%=detailViewDataCollectionURL%>"+searchParameter;
	}
}

function <portlet:namespace/>createSearchParameterSting(mode){
	
	var searchText = $("#<portlet:namespace/>textfield").val();
	var searchLine = $("#<portlet:namespace/>select_line").val();
	var groupId = $("#<portlet:namespace/>groupId").val();
	var isAdmin = $("#<portlet:namespace/>isAdmin").val();
	
	var searchParameter = "";
	if( searchText !=""){
		searchParameter += "&<portlet:namespace/>searchText="+searchText;
	}
	
	if(mode != ""){
		searchParameter += "&<portlet:namespace/>mode="+mode;
	}
	searchParameter += "&<portlet:namespace/>groupId="+groupId;
	searchParameter += "&<portlet:namespace/>isAdmin="+isAdmin;
	searchParameter += "&<portlet:namespace/>currentPage="+currentPage;
	searchParameter += "&<portlet:namespace/>searchLine="+searchLine;
	
	return searchParameter;
}

function <portlet:namespace/>dataCollectionChoice(){
	Liferay.Util.getOpener().updateDataFromPopUp(selectedCollectionId);
}
</script>