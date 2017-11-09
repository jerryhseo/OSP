<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="getProjectListURL" id="getProjectList" copyCurrentRenderParameters="false" escapeXml="false"/>
<style type="text/css">
.eturb-editor-dashboard .table-project td{
	vertical-align: middle;
}
</style>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Project List</h4>
</div>
<div class="modal-body">
	<table class="table table-hover table-project">
		<thead>
			<tr>
				<th></th>
				<th>name</th>
				<th>create Date</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty resultList}">
					<c:set value="${seq}" var="num"></c:set>
					<c:forEach items="${resultList}" var="model" varStatus="data">
						<tr>
							<td class="TC">${num}</td>
							<td class="TC">${model.name}</td>
							<td class="TC"><fmt:formatDate pattern="yyyy-MM-dd"   value="${model.createDate}" /></td>
						</tr>
						<c:set value="${num-1 }" var="num"></c:set>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3" class="TC"><liferay-ui:message key='edison-there-are-no-data'/></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</body>
	</table>
	
	<div class="paging">${pageStr}</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
</div>
<script type="text/javascript">
$(function() {
	<portlet:namespace/>searchProjectList(1);
});

function <portlet:namespace/>searchProjectList(p_curPage){
	alert(p_curPage);
}
</script>