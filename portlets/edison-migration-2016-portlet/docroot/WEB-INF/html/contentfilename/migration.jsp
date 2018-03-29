<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="updateContentFileNameURL" escapeXml="false" id="updateContentFileName" copyCurrentRenderParameters="false"/>

<h3>
	<img src="${pageContext.request.contextPath}/images/title_virtual.png" />
	Content Convertor File Name
</h3>
<h4>
	실제 파일 경로의 파일 명이 {contentSeq} 의 파일명으로 되어 있지 않은 물리적 파일 명 변경 <br/>
	고급콘텐츠는 제외 <br/>
	CODE : 2001001 - 강의노트,2001002 - 매뉴얼,2001003 - 참고자료,2001004 - 고급콘텐츠<br/>
	매뉴얼은 다국어로 물리적 파일이 분리되어 있음.
</h4>
<input type="button" class="btn btn-default" value="강의노트_실행" onclick="<portlet:namespace/>execute('2001001');"/>
<input type="button" class="btn btn-default" value="매뉴얼_실행" onclick="<portlet:namespace/>execute('2001002');"/>
<input type="button" class="btn btn-default" value="참고자료_실행" onclick="<portlet:namespace/>execute('2001003');"/>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
function <portlet:namespace/>execute(contentDiv){
	bStart();
	setTimeout(function(){
		var dataForm = {
				"<portlet:namespace/>contentDiv" : contentDiv
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=updateContentFileNameURL%>",
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