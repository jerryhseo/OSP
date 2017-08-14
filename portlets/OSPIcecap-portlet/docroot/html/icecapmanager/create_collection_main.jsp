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

<portlet:renderURL var="collectionURL">
    <portlet:param name="jspPage" value="/html/icecapmanager/search_data_type.jsp" />
</portlet:renderURL>

<portlet:actionURL var="manageSimDataCollectionActionURL" name="manageSimDataCollectionAction">
</portlet:actionURL> 

This is the <b>Data Collection</b> Main portlet in View mode.

<%
	Long dataTypeIdVal = (Long)renderRequest.getAttribute("reqDataTypeIdVal");
	
	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(dataTypeIdVal == null){
%>
		Cannot find a data type matching requested data type name and version.
		Please type again.
<%
	}
	else{
		// got typeId
		SimpleDateFormat logsdf = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = logsdf.format(new Date(System
				.currentTimeMillis()));
		String modifiedDate = createDate;
%>
		<%-- <form action='<%= manageSimDataCollectionActionURL %>' method="post" enctype="multipart/form-data">
		 --%>
		 <form action='<%= manageSimDataCollectionActionURL %>' method="post">
		 	<input type="hidden" name="<portlet:namespace />reqDataTypeIdVal" value="<%= dataTypeIdVal%>">
			<font>*  Enter simulation data collection name:</font>
				<input type="text" name="<portlet:namespace />reqDataCollectionNameVal" value="yksuh" size=10 maxlength=20>
			
			<br/>
			<font>*  Enter simulation data collection version:</font>
				<input type="text" name="<portlet:namespace />reqDataCollectionVersionVal" value="1.0" size=6 maxlength=6>
			<br/>
			<font>*  Enter simulation data collection title:</font>
				<textarea name="<portlet:namespace />reqDataCollectionTitleVal" rows="10" cols="50">Young's 1st Data Collection Title.</textarea>
			<br/>
			<font>*  Enter simulation data collection description:</font>
				<textarea name="<portlet:namespace />reqDataCollectionDescriptionVal" rows="10" cols="50">Young's 1st Data Collection.</textarea>
			<br/>
			<font>*  Enter simulation data collection status:</font>
				<select name="<portlet:namespace />reqDataCollectionStatusVal">
				  <option value="1">public</option>
			  	  <option value="0">private</option>
				</select>
			<br/>
			<font>*  Enter simulation data collection target language:</font>
				<select name="<portlet:namespace />reqTargetLangVal" multiple>
				  <option value="English">English</option>
				  <option value="Korean">Korean</option>
				  <option value="Japanese">Japanese</option>
				  <option value="Chinese">Chinese</option>
			  	  <option value="French">French</option> 
				</select>
			<br/>
			<font>*  Enter simulation data collection access method:</font>
				<input type="text" name="<portlet:namespace />reqDataCollectionAccessMethodVal" value="" size=30 maxlength=50>
			<br/>
			
			<input type="submit" value="Create"> <input type="submit" value="Add"> 
			<br/>
			<input type="reset"  value="Reset">
		</form>
<%
	}
%>

<p><a href="<%= collectionURL %>">Back</a></p>