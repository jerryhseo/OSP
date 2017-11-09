<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="updateCategoryURL" escapeXml="false" id="updateCategory" copyCurrentRenderParameters="false"/>

<h3>Science APP Category Migration</h3>
<p>기존 APP의  카테로리 정보를 Liferay Asset Category 관리 부분으로 변경 하는 프로그램</p>
<p>${appCnt}</p>

<c:if test="${scienceAppMir}">
	<input type="button" value="실행" onclick="<portlet:namespace/>execute();"/>
</c:if>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
	function <portlet:namespace/>execute(){
		bStart();
		setTimeout(function(){
			jQuery.ajax({
				type: "POST",
				url: "<%=updateCategoryURL%>",
				async : false,
				dataType: 'json',
				success: function(result) {
					alert(result.result);
				},error:function(data,e){ 
					bEnd();
					alert(e);
				}
			});
			bEnd();
		}, 2000);
	}
</script>