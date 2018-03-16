<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.Locale"%>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>

<style>
	.detailViewSubTitle{padding-left: 0px !important;}
</style>

<liferay-portlet:resourceURL var="professorListURL" id="professorList" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL var="professorManagementURL" portletMode="view" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="professorManagement" />
</liferay-portlet:renderURL>

<c:choose>
	<c:when test="${admin eq 'Y' }">
		<div class="table-responsive panel filterable edison-panel">
			<div class="panel-heading clearfix detailViewSubTitle" style="border-bottom: 0px;">
				<h3 class="panel-title pull-left">
					<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
					<liferay-ui:message key='edison-professor-infomation' />
				</h3>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="table-responsive panel filterable edison-panel">
			<div class="panel-heading clearfix" style="border-bottom: 0px;">
				<h3 class="panel-title pull-left">
					<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
					<liferay-ui:message key='edison-course-meet-the-instructors' />
				</h3>
			</div>
		</div>
	</c:otherwise>
</c:choose>

<img class="professorViewImage" src="${portraitURL }" width="150" height="150">

<div class="professorWarp">
	<c:choose>
		<c:when test="${admin eq 'Y' }">
			<ul style="padding-top : 50px">
		</c:when>
		<c:otherwise>
			<ul style="padding-top : 1px">
		</c:otherwise>
	</c:choose>
		<li class="professorTitle">${userInfomation.firstName }</li>
		<li class="professorSubTitle">E-mail : </li>
		<li class="professorDescription"><a href="mailto:${userInfomation.emailAddress }" style="color : #666;">${userInfomation.emailAddress }</a></li>
		<c:if test="${!empty professor.phone }">
			<li class="professorSubTitle">Phone : </li>	
			<li class="professorDescription">${professor.phone }</li>
		</c:if>
		<c:if test="${!empty professor.homepage }">
			<li class="professorSubTitle">Homepage : </li>
			<li class="professorDescription"><a href="http://${professor.homepage }"  target="_blank" style="color : #666;">${professor.homepage }</a></li>
		</c:if>
	</ul>
	<c:if test="${!empty record }">
		<ul>
			<li class="professorTitle">Personal History</li>
			<c:forEach var="recordTxt" items="${record }">
				<li class="professorDescription" ><p>${recordTxt }</p></li> <%-- 이력 --%>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${!empty paper }">
		<ul>
			<li class="professorTitle">Research Paper</li>
			<c:forEach var="paperTxt" items="${paper }">
				<li class="professorDescription" ><p>${paperTxt }</p></li> <%-- 논문 --%>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${!empty introduce }">
		<ul>
			<li class="professorTitle">Introduce</li>
			<c:forEach var="introduceTxt" items="${introduce }">
				<li class="professorDescription" ><p>${introduceTxt }</p></li> <%-- 소개 --%>
			</c:forEach>
		</ul>
	</c:if>
</div>
