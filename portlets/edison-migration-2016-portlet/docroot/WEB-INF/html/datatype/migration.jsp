<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="dataTypeMirURL" escapeXml="false" id="dataTypeMirgration" copyCurrentRenderParameters="false"/>

<h3>Data Type Migration</h3>
<p>기존 Port Type을 icecap_datatype으로 Data 이관 및 JSON Format형태 변경</p>

<c:if test="${dataTypeMir}">
	<input type="button" value="실행" onclick="<portlet:namespace/>execute();"/>
</c:if>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
function <portlet:namespace/>execute(){
	bStart();
	setTimeout(function(){
		jQuery.ajax({
			type: "POST",
			url: "<%=dataTypeMirURL%>",
			async : false,
			dataType: 'json',
			success: function(result) {
				var msg = "MIRDATA==>"+result.mirPortTypeCnt+"\r\n";
				    msg += "ERRORDATA==>"+result.errorPortTypeCnt+"\r\n";
				    msg += "DELETEDATA==>"+result.deletePortTypeCnt+"\r\n";
				    alert(msg);
			},error:function(data,e){ 
				bEnd();
				alert(e);
			}
		});
		bEnd();
	}, 2000);
}
</script>