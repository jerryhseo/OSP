<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<%
	String selectedGroupId =CustomUtil.strNull(request.getAttribute("selectedGroupId"));
	String icebreakerUrl =CustomUtil.strNull(request.getAttribute("icebreakerUrl"));
	String zipFileId =CustomUtil.strNull(request.getAttribute("zipFileId"));
%>

<style type="text/css">
.aui input[type="text"],
.aui input[type="password"],
.aui textarea,
.aui .edison_select{
	margin-bottom: 0px;
}
.file_tr:hover {
	background-color: #e0e0e0;
}

</style>
<div class="newWheader" id="<portlet:namespace/>result-dialog-title" style="cursor: move;">
	<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
		<div class="newWtitle"><liferay-ui:message key="edison-simulation-monitoring-result-file-down-title"/></div>
	</div>
	<div class="newWclose" style="cursor: pointer;">
		<img id="<portlet:namespace/>result-down-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer;"/>
	</div>
</div>
<div class="table-responsive panel edison-panel">
	<div style="margin-bottom: 5px;float: right;">
		<c:if test="${!empty zipFileId}">
			<input type="button" onclick="<portlet:namespace/>allDown();return false;" class="btn btn-default" value='<liferay-ui:message key="edison-simulation-monitoring-result-file-all-down"/>' />
		</c:if>
	</div>
	
	<table width="470" border="0" cellpadding="0" cellspacing="0" class="table table-bordered table-hover edison-table" style="word-break: break-all;">
		<colgroup>
			<col width="200px" />
			<col width="100px" />
			<col width="100px" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="left"><liferay-ui:message key="edison-table-list-header-file-nm"/></th>
				<th scope="col"><liferay-ui:message key="edison-table-list-header-file-size"/></th>
				<th scope="col">Data Entry</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty resultList}">
					<c:forEach items="${resultList}" var="model" varStatus="data">
						<tr class="file_tr">
							<td class="left" style="cursor: pointer;" onclick="<portlet:namespace/>fileDown('${model.fileId}');">
								${model.fileName}
							</td>
							<td  style="cursor: pointer;" onclick="<portlet:namespace/>fileDown('${model.fileId}');">${model.fileSize}</td>
							<td class="center">
								<input type="button" value="<liferay-ui:message key='edison-button-register'/>" class="btn btn-default"  onclick="<portlet:namespace/>createDataEntry('${model.fileName}', '${model.fileId}', '${model.jobUuid }');"/>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3" class="TC"><liferay-ui:message key='edison-there-are-no-data'/></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
</div>


<script type="text/javascript">
function <portlet:namespace/>allDown(){
	var zipFileId = "<%=zipFileId%>";
	if(zipFileId!=""){
		window.location.href = '<%=icebreakerUrl%>/api/file/download?id='+zipFileId;
	}else{
		alert('<liferay-ui:message key="edison-simulation-monitoring-services-are-not-support" />');
	}
	
	
}

function <portlet:namespace/>fileDown(fileId){
	var url = '<%=icebreakerUrl%>/api/file/download?id='+fileId;
	window.location.href = url;
}

function <portlet:namespace/>createDataEntry(fileNm, fileId, jobUuid){
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPortletMode("view");
		portletURL.setPlid('${myPagePlid}');
		portletURL.setPortletId("edisondataentry_WAR_edisonsimulationportlet");
		portletURL.setWindowState("<%=LiferayWindowState.MAXIMIZED.toString()%>");
		portletURL.setParameter("myRender", "manageViewDataEntry");
		portletURL.setParameter("jobUuid", jobUuid);
		portletURL.setParameter("monitoringResultFileId", fileId);
		portletURL.setParameter("monitoringResultFileNm", fileNm);
		portletURL.setParameter("jobSeqNo", '${jobSeqNo}');
		portletURL.setParameter("groupId", '${groupId}');
		portletURL.setParameter("simulationSubjectId", '${simulationSubjectId}');
		portletURL.setParameter("simulationUuid", '${simulationUuid}');
		portletURL.setParameter("redirectURL", "${redirectURL}");
		portletURL.setParameter("redirectName", Liferay.Language.get('edison-simulation-monitoring-title'));
		window.location.href = portletURL.toString();
	});
}


$("#<portlet:namespace/>result-down-dialog-close-btn").click(function(){
	$("#<portlet:namespace/>result-down-dialog").dialog("close");
})
</script>