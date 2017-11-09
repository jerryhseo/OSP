<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="unicodeMigrationURL">
	<portlet:param name="myaction" value="unicodeMigration" />
</liferay-portlet:actionURL>

<h3>Inputdeck Unicode Migration</h3>
<aui:form action="<%= unicodeMigrationURL %>" method="post" name="workspaceMigration" enctype="multipart/form-data">
	<div style="float:left">
		<c:choose>
			<c:when test="${empty error}">
				건수 : ${fileSize}
			</c:when>
			<c:otherwise>
				${error}
			</c:otherwise>
		</c:choose>
	</div>
	<div style="float:right">
		<aui:button type="submit" name="contentBtn" value="Unicode Migration" />
	</div>
</aui:form>
