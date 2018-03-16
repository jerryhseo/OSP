<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.liferay.portal.kernel.util.LocalizationUtil"%>
<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<portlet:defineObjects />

<portlet:renderURL var="createPROV">
    <portlet:param name="jspPage" value="/html/icesheetmanager/create_PROV.jsp" />
</portlet:renderURL>

<portlet:renderURL var="searchPROV">
    <portlet:param name="jspPage" value="/html/icesheetmanager/search_PROV.jsp" />
</portlet:renderURL>

<portlet:renderURL var="retrieveOutput">
    <portlet:param name="jspPage" value="/html/icesheetmanager/retrieveOutput.jsp" />
</portlet:renderURL>

<portlet:renderURL var="browseFiles">
    <portlet:param name="jspPage" value="/html/icesheetmanager/browseFiles.jsp" />
</portlet:renderURL>

<portlet:renderURL var="storeProvRec">
    <portlet:param name="jspPage" value="/html/icesheetmanager/storeAllProvRec.jsp" />
</portlet:renderURL>

<portlet:renderURL var="storeSingleProvRec">
    <portlet:param name="jspPage" value="/html/icesheetmanager/storeSingleProvRec.jsp" />
</portlet:renderURL>

<portlet:renderURL var="readFile">
    <portlet:param name="jspPage" value="/html/icesheetmanager/readFile.jsp" />
</portlet:renderURL>

<portlet:renderURL var="browseSimulations">
    <portlet:param name="jspPage" value="/html/icesheetmanager/browseSimulations.jsp" />
</portlet:renderURL>

<%-- <portlet:renderURL var="createActivity">
    <portlet:param name="jspPage" value="/html/icesheetmanager/create_activity.jsp" />
</portlet:renderURL>

<portlet:renderURL var="createAgent">
    <portlet:param name="jspPage" value="/html/icesheetmanager/create_agent.jsp" />
</portlet:renderURL>

<portlet:renderURL var="createRelation">
    <portlet:param name="jspPage" value="/html/icesheetmanager/create_relation.jsp" />
</portlet:renderURL> --%>

This is the <b>Ice Sheet Manager</b> portlet in View mode.

<p><a href="<%= createPROV %>">Create PROV</a></p>
<p><a href="<%= searchPROV %>">Search PROV</a></p>
<p><a href="<%= storeProvRec %>">Store all Provenances</a></p>
<p><a href="<%= storeSingleProvRec %>">Store a single provenance</a></p>
<p><a href="<%= retrieveOutput %>">Retrieve an existing output directory</a></p>
<p><a href="<%= browseFiles %>">Browse files under an existing output directory</a></p>
<p><a href="<%= readFile %>">Read an output file</a></p>
<p><a href="<%= browseSimulations %>">Browse simulations on a science app</a></p>
<%-- <p><a href="<%= createActivity %>">Create Activity</a></p>
<p><a href="<%= createAgent %>">Create Agent</a></p>
<p><a href="<%= createRelation %>">Create Relation</a></p>  --%>
<%-- <p><a href="<%= createCollection %>">Create Data Collection</a></p>
<p><a href="<%= createEntry %>">Create Data Entry</a></p>
<p><a href="<%= addTypeEditor %>">Add Data Type Editor</a></p>
<p><a href="<%= addTypeAnalyzer %>">Add Data Type Analyzer</a></p>
<p><a href="<%= searchSimData %>">Search for Simulation Data</a></p> --%>