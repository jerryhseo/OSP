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
	String searchAll = "("+searchSwTitle+"+"+searchSwNm+"+"+searchOrgNm+"+"+searchUserNm+")";
	
	
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
		
			<!-- 페이지 타이틀 & 네비게이션 -->
			<div class="table-responsive panel filterable edison-panel" id="<portlet:namespace/>appFilterTable">
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left">
						<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
						<liferay-ui:message key='edison-appstore-myapp-list' />
					</h3>
					<div class="btn-group pull-right">
						<button class="btn btn-default">Clear</button>
					 	<button class="btn btn-default btn-filter"><i class="icon-filter"></i>Filter</button>
						<button class="btn btn-default dropdown-toggle " type="button" id="<portlet:namespace/>pagingdrop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							Paging  <span class="caret"></span>
			 			</button>
						<ul class="dropdown-menu" aria-labelledby="<portlet:namespace/>pagingdrop">
							<li><a href="#">10</a></li>
							<li><a href="#">30</a></li>
							<li><a href="#">50</a></li>
						</ul>
					</div>
				</div>
				
				<table class = "table table-bordered table-hover edison-table">
					<thead>
						<tr class="filters">
							<th width="5%"><liferay-ui:message key='edison-table-list-header-index' /></th>
							<th width="10%">
								<select class="form-control filter" disabled>
									<option value=""><liferay-ui:message key="edison-table-list-header-type" /></option>
									<option value="<%=ScienceAppConstants.APP_TYPE_SOLVER%>" <c:if test="${searchAppType == 'Solver' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-type-solver' /></option>
									<option value="<%=ScienceAppConstants.APP_TYPE_EDITOR%>" <c:if test="${searchAppType == 'Editor' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-type-editor' /></option>
									<option value="<%=ScienceAppConstants.APP_TYPE_ANALYZER %>" <c:if test="${searchAppType == 'Analyzer' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-type-analyzer' /></option>
									<option value="<%=ScienceAppConstants.APP_TYPE_CONVERTER  %>" <c:if test="${searchAppType == 'Converter' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-type-converter' /></option>
								</select>
							</th>
							<th width="*">
								<input type="text" class="form-control filter" placeholder="<liferay-ui:message key="edison-appstore-solver-name" />(<liferay-ui:message key="edison-table-list-header-app-title" />)" disabled>
							</th>
							<th width="10%">
								<input type="text" class="form-control filter" placeholder="<liferay-ui:message key='edison-virtuallab-version' />" disabled>
							</th>
							<th width="10%"> 
								<select name="<portlet:namespace/>searchStatus" id="<portlet:namespace/>searchStatus" onChange="<portlet:namespace/>searchList()" class="form-control filter" disabled>
									<option value=""><liferay-ui:message key='edison-virtuallab-confirm-status' /></option>
									<option value="1901001" <c:if test="${searchStatus == '1901001' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-status-write' /></option>
									<option value="1901002" <c:if test="${searchStatus == '1901002' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-status-request' /></option>
									<option value="1901003" <c:if test="${searchStatus == '1901003' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-status-private' /></option>
									<option value="1901004" <c:if test="${searchStatus == '1901004' }"> selected</c:if> ><liferay-ui:message key='edison-appstore-status-service' /></option>
								</select>
							</th>
							<th width="20%">
								<input type="text" class="form-control filter" placeholder="<liferay-ui:message key='edison-table-list-header-name' />(<liferay-ui:message key='edison-table-list-header-orgNm' />)" disabled>
							</th>
							<th width="12%"><liferay-ui:message key='edison-appstore-last-modified' /></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${!empty swList}">
								<c:set value="${pageNum }" var="num"></c:set>
								<c:forEach items="${swList}" var="solverMap" varStatus="status" >
									<tr>
										<td class="center">${num}</td>
										<td class="center">${solverMap.appType}</td>
										<td>${solverMap.name}(${solverMap.title})</td>
										<td class="center">${solverMap.version}</td>
										<td class="center">
											<c:set value="label label-success" var="statusClass"></c:set>
											<c:if test="${solverMap.status=='1901003'}">
												<c:set value="label label-primary" var="statusClass"></c:set>
											</c:if>
											<c:if test="${solverMap.status=='1901004'}">
												<c:set value="label label-danger" var="statusClass"></c:set>
											</c:if>
											<span class="${statusClass}">${solverMap.statusName}</span>
										</td>
										<td>${solverMap.firstName}(${solverMap.affiliation})</td>
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
					$("#<portlet:namespace/>searchValue").val("");
					$("#<portlet:namespace/>searchAppType").val("");
					location.href="<%=swSearchURL %>"
				}
				
				function <portlet:namespace/>searchList(scienceAppId){
					var searchParameter = "";
					if($("#<portlet:namespace/>searchStatus").val()!=""){
						searchParameter += "&<portlet:namespace/>searchStatus="+$("#<portlet:namespace/>searchStatus").val();
					}
					
					if($("#<portlet:namespace/>searchAppType").val()!=""){
						searchParameter += "&<portlet:namespace/>searchAppType="+$("#<portlet:namespace/>searchAppType").val();
					}
					
					if($("#<portlet:namespace/>searchValue").val()!=""){
						searchParameter += "&<portlet:namespace/>searchValue="+$("#<portlet:namespace/>searchValue").val();
			 			var searchOption_val = $(':radio[name="<portlet:namespace/>searchOption"]:checked').val();
			 			searchParameter += "&<portlet:namespace/>searchOption="+searchOption_val;
					}
					
					location.href="<%=swSearchURL %>"+searchParameter;
				}	
			
				function <portlet:namespace/>dataSearchList(p_curPage){
					var searchParameter = "&<portlet:namespace/>p_curPage="+p_curPage;
					
					if($("#<portlet:namespace/>searchStatus").val()!=""){
						searchParameter += "&<portlet:namespace/>searchStatus="+$("#<portlet:namespace/>searchStatus").val();
					}
					
					if($("#<portlet:namespace/>searchAppType").val()!=""){
						searchParameter += "&<portlet:namespace/>searchAppType="+$("#<portlet:namespace/>searchAppType").val();
					}
					
					if($("#<portlet:namespace/>searchValue").val()!=""){
						searchParameter += "&<portlet:namespace/>searchValue="+$("#<portlet:namespace/>searchValue").val();
			 			var searchOption_val = $(':radio[name="<portlet:namespace/>searchOption"]:checked').val();
			 			searchParameter += "&<portlet:namespace/>searchOption="+searchOption_val;
					}
					
					location.href="<%=swSearchURL %>"+searchParameter;	
				}
				
				$(document).ready(function(){
					(function(jQuery) {
						jQuery.fn.<portlet:namespace/>clickoutside = function(callback) {
							var outside = 1, self = $(this);
								self.cb = callback;
								this.click(function() {
									outside = 0;
								});
							$(document).click(function() {
								outside && self.cb();
								outside = 1;
								});
							return $(this);
						}
					})(jQuery);
					
					$("#<portlet:namespace/>searchValue").focus(function(){
						if($(".search_toggle").is(":hidden")){
							$('.search_toggle').slideToggle('fast');
						}
					});
					
					$(".search").<portlet:namespace/>clickoutside(function(){
						var search_val = $("#<portlet:namespace/>searchValue").val();
						if(search_val==""&&!$(".search_toggle").is(":hidden")){$('.search_toggle').slideToggle('fast');}
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
