<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="workspaceMigrationURL">
	<portlet:param name="myaction" value="workspaceMigration" />
</liferay-portlet:actionURL>

<h3>WorkSpace Migration</h3>
<aui:form action="<%= workspaceMigrationURL %>" method="post" name="workspaceMigration" enctype="multipart/form-data">
	<div style="float:left">
		<c:choose>
			<c:when test="${fn:length(fileNameList) == 0}">
				파일없음
			</c:when>
			<c:otherwise>
				<c:forEach items="${fileNameList}" var="file">
					${file }
					<br/>
				</c:forEach>
			
			</c:otherwise>
		</c:choose>
		<aui:input type="file" label="" name="WorkSpaceExcelFile" cssClass="edison_file" value="">
			<aui:validator name="required"/>
		</aui:input>
	</div>
	<div style="float:right">
		<aui:button type="submit" name="contentBtn" value="WorkSpace Migration" />
	</div>
</aui:form>


<script type="text/javascript">
</script>
