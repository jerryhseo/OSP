<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<c:if test="${fn:length(data.scienceAppList) > 0 }">
	<div class="panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
				<liferay-ui:message key="edison-simulation-project-science-app"/>
			</h3>
		</div>
	</div>
	<!--박스 나열-->
	<div class="msbox">
	    <ul>
	    	<c:forEach var="scienceApp" items="${data.scienceAppList}" varStatus="status">
	    		<c:choose>
	    			<c:when test="${authYn eq 'Y' }">
	    				<li class="txtnum" style="cursor: pointer;" onclick="<portlet:namespace/>goWorkbench('${scienceApp.scienceAppId}');">
	    			</c:when>
	    			<c:otherwise>
	    				<li class="txtnum">
	    			</c:otherwise>
	    		</c:choose>			        
		        	<img src="/documents/${scienceApp.iconRepositoryId}/${scienceApp.iconId}/${scienceApp.iconTitle}/${scienceApp.iconUuid}?imageThumbnail=2" 
		        		width="89" height="58" onerror="this.src='${contextPath}/images/comm_proj/img.gif'" />
		        	<br>
		        	<div class="ellipsis" style="line-height: 2.6em; height: 2.6em;">
		        		${scienceApp.scienceAppName}
		        	</div>		
				</li>
			</c:forEach>
	    </ul>
	</div>
</c:if>

<script type="text/javascript">

function <portlet:namespace/>goWorkbench(targetScienceAppId){
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPortletMode("view");
		portletURL.setWindowState("<%=LiferayWindowState.NORMAL.toString()%>");
		portletURL.setPlid("${workBenchPlid}");
		portletURL.setPortletId("SimulationWorkbench_WAR_OSPWorkbenchportlet");
		portletURL.setParameter("workbenchType", "SIMULATION_WITH_APP");
		portletURL.setParameter("classId", "${classId}");
		portletURL.setParameter("customId", "${simulationProjectId}");
		portletURL.setParameter("scienceAppId", targetScienceAppId);
		
		portletURL.setParameter("redirectName", "My Project");
		portletURL.setParameter("redirectURL", "${redirectURL}");
		window.location.href = portletURL;
	});
}

</script>