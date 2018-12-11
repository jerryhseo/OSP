<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="updateLayoutURL" id="updateLayout" copyCurrentRenderParameters="false" />

<style>
.subtitlearea{
	margin-left: 10px;
}
</style>

<h2>
	<img src="${contextPath}/images/sub_tit_bl.png"/>
	<span class="subtitlearea">
		<liferay-ui:message key='edison-migration-title' />
	</span>
</h2>

<div class="h30"></div>

<h3>2018 APP LAYOUT Migration</h3>
<h4>${totalCnt}</h4>
수행건수 : <p id="changeLayoutCnt">0</p>
NOT UPDATE 건수 : <p id="notUpdateCnt">0</p>
에러건수 : <p id="changeErrorCnt">0</p>
<input type="button" class="btn btn-default" value="실행" onclick="<portlet:namespace/>execute();"/>

<script type="text/javascript">
	function <portlet:namespace/>execute(){
		bStart();
		setTimeout(function(){
			<portlet:namespace/>migrationExecute();
			bEnd();
		}, 2000);
	}
	
	function <portlet:namespace/>migrationExecute(){
		jQuery.ajax({
			type: "POST",
			url: "<%=updateLayoutURL%>",
			async : false,
			dataType: 'json',
			success: function(msg) {
				$("#changeLayoutCnt").html(msg.successCnt);
				$("#notUpdateCnt").html(msg.notUpdateCnt);
				$("#changeErrorCnt").html(msg.errorCnt);
			},error:function(msg,e){
				alert("ERROR");
			}
		});
	}
</script>
