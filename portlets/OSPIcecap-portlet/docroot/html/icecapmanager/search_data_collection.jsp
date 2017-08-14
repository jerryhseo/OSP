<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.liferay.portal.kernel.util.LocalizationUtil"%>
<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@page import="com.kisti.osp.icecap.portlet.Constants"%>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icecapmanager/view.jsp" />
</portlet:renderURL>

<portlet:actionURL var="findSimDataCollectionActionURL" name="findSimDataCollectionAction">
</portlet:actionURL> 

<%
	Long dataTypeIdVal = (Long)renderRequest.getAttribute("reqDataTypeIdVal");
%>



<p><a href="<%= viewURL %>">Back</a></p>