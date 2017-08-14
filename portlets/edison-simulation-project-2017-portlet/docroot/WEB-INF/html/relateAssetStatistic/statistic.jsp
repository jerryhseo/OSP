<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%-- <link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css"/> --%>


<div class="commrighttxt">
	<ul>
		<li><liferay-ui:message key="edison-simulation-project-relate-info-statistics"></liferay-ui:message></li>
		<li class="stxt2"><img src="${contextPath}/images/comm_proj/icon01.png" width="13" height="12">
		<liferay-ui:message key="org.kisti.edison.science.model.ScienceApp"></liferay-ui:message>
			: ${data.linkScienceAppCount}</li>
		<li class="stxt2"><img src="${contextPath}/images/comm_proj/icon02.png" width="13" height="12">
		<liferay-ui:message
				key="org.kisti.edison.content.model.Content"></liferay-ui:message> :
			${data.linkConetentCount}</li>
		<li class="stxt2"><img src="${contextPath}/images/comm_proj/icon03.png" width="13" height="12">
		<liferay-ui:message
				key="com.kisti.osp.icecap.model.DataCollection"></liferay-ui:message>
			: ${data.linkScienceDataCount}</li>
		<li class="stxt2"><img src="${contextPath}/images/comm_proj/icon04.png" width="13" height="12">
		<liferay-ui:message
				key="org.kisti.edison.model.SimulationProject"></liferay-ui:message>
			: ${data.linkSimulationProjectCount}</li>
		<li class="stxt2 <c:if test="${authYn eq 'Y' }">last</c:if>"><img src="${contextPath}/images/comm_proj/icon05.png" width="13" height="12">
		<liferay-ui:message
				key="com.liferay.portal.model.Group"></liferay-ui:message>
			: ${data.linkCumunityCount }
		</li>	
	</ul>
</div>


