<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="updatePortURL" escapeXml="false" id="updatePort" copyCurrentRenderParameters="false"/>

<h3>
	<img src="${pageContext.request.contextPath}/images/title_virtual.png" />
	INPUT(Default Editor), OUTPUT PORT(Default Analyzer) Migration
</h3>
<p>Port에  Default-editor-id, Default-analyzer-id에 사이언스 앱 ID 대신 실행 파일 명으로 변경 하는 프로그램</p>
<p>port의 앱 이름을 최신화</p>
<p>INPUT_PORT_COUNT : ${inputTotalCnt}</p>
<p>LOG_PORT_COUNT : ${logTotalCnt}</p>
<p>OUTPUT_PORT_COUNT : ${outputTotalCnt}</p>

<input type="button" class="btn btn-default" value="INPUTPORT_실행" onclick="<portlet:namespace/>execute('INPUT');"/>
<input type="button" class="btn btn-default" value="LOGPORT_실행" onclick="<portlet:namespace/>execute('LOG');"/>
<input type="button" class="btn btn-default" value="OUTPUTPORT_실행" onclick="<portlet:namespace/>execute('OUTPUT');"/>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
function <portlet:namespace/>execute(type){
	bStart();
	setTimeout(function(){
		var dataForm = {
				"<portlet:namespace/>type" : type
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=updatePortURL%>",
			async : false,
			data : dataForm,
			success: function(result) {
// 				alert(result.result);
			},error:function(data,e){ 
				bEnd();
// 				alert(e);
			}
		});
		bEnd();
	}, 2000);
}
</script>