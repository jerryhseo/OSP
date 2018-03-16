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
    <portlet:param name="jspPage" value="/html/icesheetmanager/view.jsp" />
</portlet:renderURL>

<portlet:actionURL var="searchPROVActionURL" name="searchPROVAction">
</portlet:actionURL> 

This is the <b>PROV doc Creation</b> portlet in View mode.
<%
	SimpleDateFormat logsdf = new SimpleDateFormat("yyyy-MM-dd");
	String createDate = logsdf.format(new Date(System
			.currentTimeMillis()));
	String modifiedDate = createDate;
%>

<form action='<%= searchPROVActionURL %>' method="post">
	<!-- <font>*  Enter simulation uuid:</font>
		<input type="text" name="<portlet:namespace />reqSimUuidVal" value="729664e6-fa61-4eb0-9902-4ec3ecd8af3f" size=30 maxlength=50>
	-->
	<font>Choose a Science App:</font>
		<select name="<portlet:namespace />reqAppName">
		 	<option value="2D_Incomp_P" selected>2D_Incomp_P(CFD)</option>
	 	 	<option value="2D_Comp_P">2D_Comp_P(CFD)</option>
	 	 	<option value="KDFT">KDFT(CHEM)</option>
	 	 	<option value="3D_Electrophysiology_CanineHF">3D_Electrophysiology_CanineHF(CMED)</option>
		 	<option value="3D_Electrophysiology_CanineN ">3D_Electrophysiology_CanineN(CSD)</option>
		 	<option value="CSD_EPLAST">CSD_EPLAST(CSD)</option>
		 	<option value="cr2d_st">cr2d_st(CSD)</option>
		 	<option value="roundSTMtip">roundSTMtip(NANO)</option>
		 	<option value="acuteSTMtip">acuteSTMtip(NANO)</option>
		 	<option value="UTB_FET">UTB_FET(NANO)</option>
		 	<option value="TB_EM_NEGF">TB_EM_NEGF(NANO)</option>
		</select>
		<select name="<portlet:namespace />reqAppVersion">
 			<option value="3.0.0" selected>3.0.0</option>
 			<option value="2.5.0">2.5.0</option>
 			<option value="2.0.1">2.0.1</option>
 			<option value="2.0.0">2.0.0</option>
 			<option value="1.0.1">1.0.1</option>
 			<option value="1.0.0">1.0.0</option>
 		</select>
	<br/>
	<font>Start Date:</font>
		<select name="<portlet:namespace />reqStartYear">
			<option value="2017" selected>2017</option>
	 	 	<option value="2016">2016</option>
		</select>
		<select name="<portlet:namespace />reqStartMon">
			<option value="1">1</option>
	 	 	<option value="2">2</option>
	 	 	<option value="3">3</option>
	 	 	<option value="4" selected>4</option>
	 	 	<option value="5">5</option>
	 	 	<option value="6">6</option>
	 	 	<option value="7">7</option>
	 	 	<option value="8">8</option>
	 	 	<option value="9">9</option>
	 	 	<option value="10">10</option>
	 	 	<option value="11">11</option>
	 	 	<option value="12">12</option>
		</select>
		<select name="<portlet:namespace />reqStartDay">
			<option value="1" selected>1</option>
	 	 	<option value="2">2</option>
	 	 	<option value="3">3</option>
	 	 	<option value="4">4</option>
	 	 	<option value="5">5</option>
	 	 	<option value="6">6</option>
	 	 	<option value="7">7</option>
	 	 	<option value="8">8</option>
	 	 	<option value="9" selected>9</option>
	 	 	<option value="10">10</option>
	 	 	<option value="11">11</option>
	 	 	<option value="12">12</option>
	 	 	<option value="13">13</option>
	 	 	<option value="14">14</option>
	 	 	<option value="15">15</option>
	 	 	<option value="16">16</option>
	 	 	<option value="17">17</option>
	 	 	<option value="18">18</option>
	 	 	<option value="19">19</option>
	 	 	<option value="20">20</option>
	 	 	<option value="21">21</option>
	 	 	<option value="22">22</option>
	 	 	<option value="23">23</option>
	 	 	<option value="24">24</option>
	 	 	<option value="25">25</option>
	 	 	<option value="26">26</option>
	 	 	<option value="27">27</option>
	 	 	<option value="28">28</option>
	 	 	<option value="29">29</option>
	 	 	<option value="30">30</option>
	 	 	<option value="31">31</option>
		</select>
	<br />
	<font>End Date:</font>
		<select name="<portlet:namespace />reqEndYear">
			<option value="2017" selected>2017</option>
	 	 	<option value="2016">2016</option>
		</select>
		<select name="<portlet:namespace />reqEndMon">
			<option value="1">1</option>
	 	 	<option value="2">2</option>
	 	 	<option value="3">3</option>
	 	 	<option value="4" selected>4</option>
	 	 	<option value="5">5</option>
	 	 	<option value="6">6</option>
	 	 	<option value="7">7</option>
	 	 	<option value="8">8</option>
	 	 	<option value="9">9</option>
	 	 	<option value="10">10</option>
	 	 	<option value="11">11</option>
	 	 	<option value="12">12</option>
		</select>
		<select name="<portlet:namespace />reqEndDay">
			<option value="1">1</option>
	 	 	<option value="2">2</option>
	 	 	<option value="3">3</option>
	 	 	<option value="4">4</option>
	 	 	<option value="5">5</option>
	 	 	<option value="6">6</option>
	 	 	<option value="7">7</option>
	 	 	<option value="8">8</option>
	 	 	<option value="9">9</option>
	 	 	<option value="10">10</option>
	 	 	<option value="11">11</option>
	 	 	<option value="12" selected>12</option>
	 	 	<option value="13">13</option>
	 	 	<option value="14">14</option>
	 	 	<option value="15">15</option>
	 	 	<option value="16">16</option>
	 	 	<option value="17">17</option>
	 	 	<option value="18">18</option>
	 	 	<option value="19">19</option>
	 	 	<option value="20">20</option>
	 	 	<option value="21">21</option>
	 	 	<option value="22">22</option>
	 	 	<option value="23">23</option>
	 	 	<option value="24">24</option>
	 	 	<option value="25">25</option>
	 	 	<option value="26">26</option>
	 	 	<option value="27">27</option>
	 	 	<option value="28">28</option>
	 	 	<option value="29">29</option>
	 	 	<option value="30">30</option>
	 	 	<option value="31">31</option>
		</select>
	<br/>
	<input type="submit" value="Search"> 
	<!-- 
	<br/>
	<input type="reset"  value="Reset">
	-->
</form> 

<p><a href="<%= viewURL %>">Back</a></p>