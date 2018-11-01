<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="searchFileListURL" id="searchFileList" copyCurrentRenderParameters="false" escapeXml="false">
	<liferay-portlet:param name="simulationUuid" value="${simulationUuid}"/>
	<liferay-portlet:param name="jobUuid" value="${jobUuid}"/>
</liferay-portlet:resourceURL>

<style type="text/css">
	
</style>
<div class="container">
	<div class="explorer" id="<portlet:namespace/>file-list-area">
		
	</div>
</div>
<script src="${contextPath}/js/workbench/resultfile/jquery.file-manager.js"></script>

<script type="text/javascript">
$(function(e) {
	<portlet:namespace/>girdFile();
});

function <portlet:namespace/>girdFile(folderPath){
	jQuery.ajax({
		type: "POST",
		url: "<%=searchFileListURL%>",
		async : false,
// 		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			$('#<portlet:namespace/>file-list-area').fileManager(result);
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}  
		}
	});
}
</script>