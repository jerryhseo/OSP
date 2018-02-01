<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="contentMigrationURL">
	<portlet:param name="myaction" value="contentMigration" />
</liferay-portlet:actionURL>

<h3>Content Migration</h3>
<aui:form action="<%= contentMigrationURL %>" method="post" name="contentMigrationForm">
	<input type="button" class="btn btn-default" value="migration"
		onclick="<portlet:namespace/>mirgationContent('migration');return false;" /> 
</aui:form>



<script>
//마이그레이션
function <portlet:namespace/>mirgationContent(status){
	
	jQuery.ajax({
		type: "POST",
		url: "<%=contentMigrationURL%>",
		async : false,
		success: function(data) {
			window.location.reload();
		},error:function(data,e){
			alert("migration error-->"+e);
		}
	});
}

</script>