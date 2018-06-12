<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="executeURL" id="execute" copyCurrentRenderParameters="false" />

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

<h3>APP Manual Exist</h3>
총 앱 건수 : <p id="totalCnt">0</p>
정상 앱 건수 : <p id="successAppCnt">0</p>
Error 앱 건수 : <p id="errorAppCnt">0</p>
존재 하지 않는 Manual 건수 : <p id="changeErrorCnt">0</p>
<input type="button" class="btn btn-default" value="실행" onclick="execute();"/>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
	function execute(){
		bStart();
		setTimeout(function(){
			searchApp();
			bEnd();
		}, 2000);
	}
	
	function searchApp(){
		jQuery.ajax({
			type: "POST",
			url: "<%=executeURL%>",
			async : false,
			success: function(msg) {
				$("#totalCnt").html(msg.totalCnt);
				$("#successAppCnt").html(msg.successAppCnt);
				$("#errorAppCnt").html(msg.errorAppCnt);
				$("#changeErrorCnt").html(msg.errorCnt);
			},error:function(msg,e){
			}
		});
	}
</script>
