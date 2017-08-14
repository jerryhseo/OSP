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
<portlet:defineObjects />

<portlet:renderURL var="createType">
    <portlet:param name="jspPage" value="/html/icecapmanager/create_type.jsp" />
</portlet:renderURL>

<portlet:renderURL var="createCollection">
    <portlet:param name="jspPage" value="/html/icecapmanager/search_data_type.jsp" />
     <portlet:param name="actionType" value="0"/>
</portlet:renderURL>

<portlet:renderURL var="createEntry">
    <portlet:param name="jspPage" value="/html/icecapmanager/search_data_type.jsp" />
    <portlet:param name="actionType" value="3"/>
</portlet:renderURL>

<portlet:renderURL var="addTypeAnalyzer">
    <portlet:param name="jspPage" value="/html/icecapmanager/search_data_type.jsp" />
    <portlet:param name="actionType" value="1"/>
</portlet:renderURL>

<portlet:renderURL var="addTypeEditor">
    <portlet:param name="jspPage" value="/html/icecapmanager/search_data_type.jsp" />
     <portlet:param name="actionType" value="2"/>
</portlet:renderURL>

<portlet:renderURL var="searchSimData">
    <portlet:param name="jspPage" value="/html/icecapmanager/search_sim_data_main.jsp" />
</portlet:renderURL>

<%-- <portlet:renderURL var="createTypeStructure">
    <portlet:param name="jspPage" value="/html/icecapmanager/create_type_structure.jsp" />
</portlet:renderURL> --%>


This is the <b>IceCap Manager</b> portlet in View mode.

<p><a href="<%= createType %>">Create Data Type</a></p>
<p><a href="<%= createCollection %>">Create Data Collection</a></p>
<p><a href="<%= createEntry %>">Create Data Entry</a></p>
<p><a href="<%= addTypeEditor %>">Add Data Type Editor</a></p>
<p><a href="<%= addTypeAnalyzer %>">Add Data Type Analyzer</a></p>
<p><a href="<%= searchSimData %>">Search for Simulation Data</a></p>
