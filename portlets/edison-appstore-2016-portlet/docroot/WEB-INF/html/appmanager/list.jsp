<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/scienceappmanager.css" media="screen"/>

<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>

<%@ page import="org.kisti.edison.science.service.constants.ScienceAppConstants" %>
<%@ page import="org.kisti.edison.science.Exception.ScienceAppException" %>

<%
	String searchSwNm = LanguageUtil.get(themeDisplay.getLocale(), "edison-appstore-solver-name");
	String searchSwTitle = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-app-title");
	String searchOrgNm = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-orgNm");
	String searchUserNm = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-name");
	String searchSwNameAndTitle = searchSwNm+"("+searchSwTitle+")";
	
	
	//Tab Setting
	String tabNames = (String)request.getAttribute("tabNames");
	String listTabValue = CustomUtil.strNull(request.getAttribute("listTabValue"));
%>

<liferay-portlet:renderURL var="swTabSearchURL" portletMode='view'/>

<liferay-portlet:renderURL var="swSearchURL" portletMode='view'>
	<portlet:param name="tabValue" value="<%=listTabValue%>"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="solverRenderURL" copyCurrentRenderParameters="<%=true%>" windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>">
	<portlet:param name="redirectURL" value="${redirectURL}" />
	<portlet:param name="redirectName" value="${redirectName}" />
	<portlet:param name="myRender" value="solverRender" />
</liferay-portlet:renderURL>

<c:choose>
	<c:when test="<%= SessionErrors.contains(renderRequest, ScienceAppException.class.getName()) %>">
		<%
			ScienceAppException sae = (ScienceAppException)SessionErrors.get(renderRequest, ScienceAppException.class.getName());
		%>
		<div class="alert alert-error">
			<c:if test="<%= sae.getType() == ScienceAppException.SCIENCE_APP_NO_AUTH%>">
				<liferay-ui:message key="edison-app-no-access-exception-msg" />
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<style type="text/css">
			.aui .tabletopsearchbox .radio{
				float:left;
				padding-right: 20px;
			}
			.tabletopsearchbox{
				background: #67788a;
				margin-top: 20px;
			}
			
			.apptype {
				float: left;
				margin-right: 10px
			}
		</style>
			
			<div class="contabmenu clearfix"> 
				<edison-ui:tabs names="<%=tabNames%>" url="<%=swTabSearchURL%>" tabsValues="owner_sw,manager_sw" value="<%=listTabValue%>" param="tabValue" minwidth="230"/>
			</div>
			<!-- 페이지 타이틀 & 네비게이션 -->
			<div class="table-responsive panel filterable edison-panel" id="<portlet:namespace/>appFilterTable">
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left">
						<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
						<liferay-ui:message key='edison-appstore-myapp-list' />
					</h3>
					<div class="btn-group pull-right">
						<button class="btn btn-default" onClick="<portlet:namespace/>searchListAll()">Clear</button>
					 	<button class="btn btn-default btn-filter"><i class="icon-filter"></i>Filter</button>
					</div>
				</div>
				
				<table class = "table table-bordered table-hover edison-table">
					<thead>
						<tr class="filters">
							<th width="5%"><liferay-ui:message key='edison-table-list-header-index' /></th>
							<th width="10%">
								<select name="<portlet:namespace/>searchAppType" id="<portlet:namespace/>searchAppType" onChange="<portlet:namespace/>dataSearchList();" class="form-control filter" disabled>
									<option value=""><liferay-ui:message key="edison-table-list-header-type" /></option>
									<option value="<%=ScienceAppConstants.APP_TYPE_SOLVER%>" <c:if test="${searchAppType == 'Solver' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-type-solver' /></option>
									<option value="<%=ScienceAppConstants.APP_TYPE_EDITOR%>" <c:if test="${searchAppType == 'Editor' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-type-editor' /></option>
									<option value="<%=ScienceAppConstants.APP_TYPE_ANALYZER %>" <c:if test="${searchAppType == 'Analyzer' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-type-analyzer' /></option>
									<option value="<%=ScienceAppConstants.APP_TYPE_CONVERTER  %>" <c:if test="${searchAppType == 'Converter' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-type-converter' /></option>
								</select>
							</th>
							<th width="*">
								<aui:input name="likeSwNameAndSwTitle" type="text" placeholder="<%=searchSwNameAndTitle%>" label="" cssClass="form-control filter"/>
							</th>
							<th width="13%"> 
								<select name="<portlet:namespace/>searchStatus" id="<portlet:namespace/>searchStatus" onChange="<portlet:namespace/>dataSearchList();" class="form-control filter" disabled>
									<option value=""><liferay-ui:message key='edison-virtuallab-confirm-status' /></option>
									<option value="1901001" <c:if test="${searchStatus == '1901001' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-status-write' /></option>
									<option value="1901002" <c:if test="${searchStatus == '1901002' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-status-request' /></option>
									<option value="1901003" <c:if test="${searchStatus == '1901003' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-status-private' /></option>
									<option value="1901004" <c:if test="${searchStatus == '1901004' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-status-service' /></option>
								</select>
							</th>
							<th width="10%">
<%-- 								<input type="text" class="form-control filter" placeholder="<liferay-ui:message key='edison-table-list-header-name' />" disabled> --%>
								<aui:input name="likeUserName" type="text" placeholder="<%=searchUserNm%>" label="" cssClass="form-control filter"/>
							</th>
							<th width="15%">
<%-- 								<input type="text" class="form-control filter" placeholder="<liferay-ui:message key='edison-table-list-header-orgNm' />" disabled> --%>
								<aui:input name="likeOrgName" type="text" placeholder="<%=searchOrgNm%>" label="" cssClass="form-control filter"/>
							</th>
							<th width="12%"><liferay-ui:message key='edison-appstore-last-modified' /></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${!empty swList}">
								<c:set value="${pageNum }" var="num"></c:set>
								<c:forEach items="${swList}" var="solverMap" varStatus="status" >
									<tr onClick="<portlet:namespace/>detailView('${solverMap.scienceAppId}');">
										<td class="center">${num}</td>
										<td class="center">${solverMap.appType}</td>
										<td>${solverMap.name}_${solverMap.version}<br/>(${solverMap.title})</td>
										<td class="center">
											<c:set value="label label-success" var="statusClass"></c:set>
											<c:if test="${solverMap.status=='1901003'}">
												<c:set value="label label-danger" var="statusClass"></c:set>
											</c:if>
											<c:if test="${solverMap.status=='1901004'}">
												<c:set value="label label-primary" var="statusClass"></c:set>
											</c:if>
											
											<span class="${statusClass}">${solverMap.statusName}</span>
										</td>
										<td>${solverMap.firstName}</td>
										<td>${solverMap.affiliation}</td>
										<td class="center">
											<fmt:formatDate pattern="yyyy-MM-dd"   value="${solverMap.modifiedDate}" />
										</td>
									</tr>
									<c:set value="${num-1 }" var="num"></c:set>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="7" class="center"><liferay-ui:message key='edison-there-are-no-data' /></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<div class="text-center">${pagingStr}</div>
			</div>
			
			<script type="text/javascript">
			function <portlet:namespace/>addApp(isPort){
				var URL = "<%=solverRenderURL%>";
				
				if(isPort!=""){
					URL += "&<portlet:namespace/>isPort=true";
				}
				location.href=URL;
			}
			
				function <portlet:namespace/>detailView(scienceAppId){
					var URL = "<%=solverRenderURL%>&<portlet:namespace/>scienceAppId="+scienceAppId;
					location.href=URL;
				}
			
				function <portlet:namespace/>searchListAll(){
					$("#<portlet:namespace/>searchStatus").val("");
					$("#<portlet:namespace/>searchAppType").val("");
					$("#<portlet:namespace/>likeSwNameAndSwTitle").val("");
					$("#<portlet:namespace/>likeUserName").val("");
					$("#<portlet:namespace/>likeOrgName").val("");
					location.href="<%=swSearchURL %>"
				}
				
				function <portlet:namespace/>dataSearchList(p_curPage){
					if(p_curPage==null){p_curPage=1;}
					var searchParameter = "&<portlet:namespace/>p_curPage="+p_curPage;
					
					if($("#<portlet:namespace/>searchStatus").val()!=""){
						searchParameter += "&<portlet:namespace/>searchStatus="+$("#<portlet:namespace/>searchStatus").val();
					}
					
					if($("#<portlet:namespace/>searchAppType").val()!=""){
						searchParameter += "&<portlet:namespace/>searchAppType="+$("#<portlet:namespace/>searchAppType").val();
					}
					
					if($("#<portlet:namespace/>likeSwNameAndSwTitle").val()!=""){
						searchParameter += "&<portlet:namespace/>likeSwNameAndSwTitle="+$("#<portlet:namespace/>likeSwNameAndSwTitle").val();
					}
					
					if($("#<portlet:namespace/>likeUserName").val()!=""){
						searchParameter += "&<portlet:namespace/>likeUserName="+$("#<portlet:namespace/>likeUserName").val();
					}
					
					if($("#<portlet:namespace/>likeOrgName").val()!=""){
						searchParameter += "&<portlet:namespace/>likeOrgName="+$("#<portlet:namespace/>likeOrgName").val();
					}
					
					location.href="<%=swSearchURL %>"+searchParameter;	
				}
				
				$(document).ready(function(){
					$('.filterable .filters input').attr("disabled","disabled");
					
					$('.filterable .filters input').keyup(function(e){
				        /* Ignore tab key */
				        var code = e.keyCode || e.which;
				        if (code == '9'){
				        	return;
				        } else if(code == '13'){
				        	<portlet:namespace/>dataSearchList();
				        }
				    });
					
					
					$('.filterable .btn-filter').click(function(){
				        var $panel = $(this).parents('#<portlet:namespace/>appFilterTable'),
				        $filters = $panel.find('.filters .filter')
				        if ($filters.prop('disabled') == true) {
				            $filters.prop('disabled', false);
				            $filters.first().focus();
				        } else {
				        	$filters.val('').prop('disabled', true);
				        }
				    });
				});
				
			
			</script>
	</c:otherwise>
</c:choose>
