<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="userMigrationURL">
	<portlet:param name="myaction" value="userMigration" />
</liferay-portlet:actionURL>

<h3>Visit Site Migration</h3>
<p>${tempUserCnt}</p>
<aui:form action="<%= userMigrationURL %>" method="post" name="userMigrationForm">
	<div style="float:right">
		<aui:button type="submit" name="contentBtn" value="User Migration" />
	</div>
</aui:form>


<script type="text/javascript">
</script>
