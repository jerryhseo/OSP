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

<portlet:actionURL var="findSimDataTypeActionURL" name="findSimDataTypeAction">
</portlet:actionURL> 

<%
	String actionIntroStr = "";
	String actionType = (String)request.getParameter("actionType");
	Long reqActionType = Long.parseLong(actionType);
	if(reqActionType == Constants.COLLECTION_CREATION){
		actionIntroStr = "Create Data Collection";
	}else if(reqActionType == Constants.ENTRY_CREATION){		
		actionIntroStr = "Create Data Entry";
	}else if(reqActionType == Constants.TYPE_ANAYLYZER_ADDITION){		
		actionIntroStr = "Add Data Type Analyzer";
	}else if(reqActionType == Constants.TYPE_EDITOR_ADDITION){		
		actionIntroStr = "Add Data Type Editor";
	}
%>

This is the <b><%= actionIntroStr %> - Search Data Type</b> portlet in View mode.

<form action='<%= findSimDataTypeActionURL %>' method="post">
	<input type="hidden" name="<portlet:namespace />reqAssociatedActionVal" value="<%= actionType %>">
	<br/>
	<font>*  Enter simulation data type name:</font>
		<input type="text" name="<portlet:namespace />reqDataTypeNameVal" value="yksuh" size=10 maxlength=20>
	
	<br/>
	<font>*  Enter simulation data type version:</font>
		<input type="text" name="<portlet:namespace />reqDataTypeVersionVal" value="1.0" size=6 maxlength=6>
	
	<br/>
	
	<%-- <font>*  Select an Associated Action</font>
		<select name="<portlet:namespace />reqAssociatedActionVal" multiple>
		  <option selected="selected" value="0">Create Data Collection</option>
		  <option value="1">Add Data Type Analyzer</option>
		  <option value="2">Add Data Type Editor</option>
		</select>
	<br/> --%>
	
	<input type="submit" value="Submit">
	<br/>
	<input type="reset"  value="Reset">
</form>

<p><a href="<%= viewURL %>">Back</a></p>