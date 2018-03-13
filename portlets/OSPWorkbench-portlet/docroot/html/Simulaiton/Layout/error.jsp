<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/html/init.jsp"%>

<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>

<%@ page import="com.kisti.osp.workbench.Exception.SimulationWorkbenchException" %>


<%
	SimulationWorkbenchException sae = (SimulationWorkbenchException)SessionErrors.get(renderRequest, SimulationWorkbenchException.class.getName());
%>
<div class="alert alert-error">
	<c:if test="<%= sae.getType() == SimulationWorkbenchException.NO_SCIENCEAPP_ID%>">
		<liferay-ui:message key="edison-app-no-access-exception-msg" />
	</c:if>
	<c:if test="<%= sae.getType() == SimulationWorkbenchException.NO_SCIENCEAPP_LAYOUT_EXIST%>">
		<liferay-ui:message key="edison-app-no-access-exception-msg" />
	</c:if>
	
	<c:if test="<%= sae.getType() == SimulationWorkbenchException.SCIENCEAPP_PORT_SEARCH_EXCEPTION%>">
		<liferay-ui:message key="edison-app-no-access-exception-msg" />
	</c:if>
</div>
