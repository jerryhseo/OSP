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

<portlet:actionURL var="manageSimDataTypeActionURL" name="manageSimDataTypeAction">
</portlet:actionURL> 

This is the <b>Data Type Creation</b> portlet in View mode.
<%
	SimpleDateFormat logsdf = new SimpleDateFormat("yyyy-MM-dd");
	String createDate = logsdf.format(new Date(System
			.currentTimeMillis()));
	String modifiedDate = createDate;
%>

<form action='<%= manageSimDataTypeActionURL %>' method="post" enctype="multipart/form-data">
	<font>*  Enter simulation data type name:</font>
		<input type="text" name="<portlet:namespace />reqDataTypeNameVal" value="yksuh" size=10 maxlength=20>
	
	<br/>
	<font>*  Enter simulation data type version:</font>
		<input type="text" name="<portlet:namespace />reqDataTypeVersionVal" value="1.0" size=6 maxlength=6>
	
	<br/>
	<font>*  Enter simulation data type description:</font>
		<textarea name="<portlet:namespace />reqDataTypeDescriptionVal" rows="10" cols="50">Young's 1st Data Type.</textarea>
	<br/>
	<font>*  Enter simulation data type sample path:</font>
		<input type="file" name="<portlet:namespace />reqDataTypeFileSamplePath" size="100">
		<!-- <input type="text" name="<portlet:namespace />reqDataTypeSamplePathVal" value="/EDISON/DATA/input.data" size=50 maxlength=300>-->
	
	<br/>
	<font>*  Enter simulation data type status:</font>
		<select name="<portlet:namespace />reqDataTypeStatusVal">
		  <option value="1">public</option>
	  	  <option value="0">private</option>
		</select>

	<br/>
	
	<font>*  Provide owner Id:</font>
		<input type="text" name="<portlet:namespace />reqDataTypeOwnerIdVal" value="13907" size=5 maxlength=10>
	
	<br/>
	
	<font>*  Is this favorite?</font>
		<select name="<portlet:namespace />reqDataTypeFavoriteVal">
		  <option selected value="1">yes</option>
	  	  <option value="0">no</option>
		</select>
		
	<br/>
	<font>*  Provide a data type structure:</font>
		<!-- <input type="file" name="<portlet:namespace />reqDataTypeStructureName" size="40">-->
		<textarea name="<portlet:namespace />reqDataTypeStructureVal" cols="100">Upload your data type.</textarea>
		
	<br/>
	<!-- <font>*  Enter simulation data type userId:</font>
		<input type="text" value="10000" name="<portlet:namespace />reqDataTypeUserIdVal" value="" size=20 maxlength=30>
	
	<br/>
	<font>*  Enter simulation data type companyId:</font>
		<input type="text" value="20000" name="<portlet:namespace />reqDataTypeCompanyIdVal" value="" size=20 maxlength=30>
	
	<br/>
	<font>*  Created date:</font>
		<input type="text" name="<portlet:namespace />reqCreateTypeVal" value="<%= createDate%>" size=10 maxlength=10>
	
	<br/>
	<font>*  Modified date:</font>
		<input type="text" name="<portlet:namespace />reqModifiedTypeVal" value="<%= modifiedDate%>"size=10 maxlength=10>
	<br/>
	-->
	<input type="submit" value="Create"> <input type="submit" value="Add"> 
	<br/>
	<input type="reset"  value="Reset">
</form> 

<p><a href="<%= viewURL %>">Back</a></p>