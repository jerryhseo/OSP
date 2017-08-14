<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.liferay.portal.kernel.util.LocalizationUtil"%>
<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
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
	
	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(dataTypeIdVal == null){
%>
		<p>Cannot find a data type matching requested data type name and version.
		Please type again.</p>
<%
	}
	else{
%>		
		This is the <b>Search Data Collection</b> portlet in View mode.

		<form action='<%= findSimDataCollectionActionURL %>' method="post">
			<input type="hidden" name="<portlet:namespace />reqDataTypeIdVal" value="<%= dataTypeIdVal %>">
			<br/>
			<font>*  Enter simulation data collection name:</font>
				<input type="text" name="<portlet:namespace />reqDataCollectionNameVal" value="yksuh" size=10 maxlength=20>
			
			<br/>
			<font>*  Enter simulation data collection version:</font>
				<input type="text" name="<portlet:namespace />reqDataCollectionVersionVal" value="1.0" size=6 maxlength=6>
			
			<br/>
			
			<input type="submit" value="Submit">
			<br/>
			<input type="reset"  value="Reset">
		</form>
<%
	}
%>
<p><a href="<%= viewURL %>">Back</a></p>