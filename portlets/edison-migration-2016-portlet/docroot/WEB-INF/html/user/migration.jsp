<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="userMigrationURL">
	<portlet:param name="myaction" value="userMigration" />
</liferay-portlet:actionURL>

<h3>User Migration</h3>
<aui:form action="<%= userMigrationURL %>" method="post" name="userMigrationForm" enctype="multipart/form-data">
	<div style="float:left">
		<aui:input type="file" label="" name="UserExcelFile" cssClass="edison_file" value="">
			<aui:validator name="required"/>
		</aui:input>
	</div>
	<div style="float:right">
		<aui:button type="submit" name="contentBtn" value="User Migration" />
	</div>
</aui:form>


<script type="text/javascript">
</script>
