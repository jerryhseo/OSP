<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="Workbench_WAR_OSPWorkbenchportlet">
	<liferay-portlet:param name="workbenchType" value="APPSTORE"/>
	<liferay-portlet:param name="classId" value="${classId}"/>
	<liferay-portlet:param name="customId" value="${simulationProjectId}"/>
	<liferay-portlet:param name="jobUuid" value="0"/>
	<liferay-portlet:param name="testYn" value="false"/>
</liferay-portlet:renderURL>

<style>
	.sub-title{
		font-weight: 100 !important;
		font-size: 18px !important;
	}
</style>

<c:if test="${fn:length(data.scienceAppList) > 0 }">
	<div class="panel edison-panel">
		<div class="panel-heading clearfix" style="border-bottom: 0px;">
			<h3 class="panel-title sub-title pull-left">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
				<liferay-ui:message key="edison-simulation-project-science-app"/>
			</h3>
		</div>
	</div>
	<div class="h10"></div>
	<!--박스 나열-->
	<div class="msbox">
	    <ul>
	    	<c:forEach var="scienceApp" items="${data.scienceAppList}" varStatus="status">
	    		<c:choose>
	    			<c:when test="${authYn eq 'Y' }">
	    				<li class="txtnum" style="cursor: pointer; margin-bottom: 10px; ${(status.index +1) % 5 eq 0 ? 'margin-right: 0px;' : ''}" 
	    				onclick="<portlet:namespace/>goWorkbench('${scienceApp.scienceAppId}');">
	    			</c:when>
	    			<c:otherwise>
	    				<li class="txtnum" style="margin-bottom: 10px; ${(status.index +1) % 5 eq 0 ? 'margin-right: 0px;' : ''}">
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
	var URL = "<%=workbenchURL%>";
	URL += "&_Workbench_WAR_OSPWorkbenchportlet_scienceAppId="+targetScienceAppId;
	
 	location.href= URL;
}

</script>