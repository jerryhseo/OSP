<%@ include file="/html/init.jsp" %>
<%@ page import="org.kisti.edison.model.EdisonExpando" %>
<%@ page import="org.kisti.edison.util.EdisonExpndoUtil" %>
<%@ page import="org.kisti.edison.util.EdisonUserUtil" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoTable" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoValue" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.CompanyLocalServiceUtil" %>





<%
	long childChallengeID = ParamUtil.getLong(request, "childChallengeID");
	long challengeid = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeID).getChallengeid();	

	User currentUser = UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser()));
	currentUser.getFullName();
	currentUser.getDisplayEmailAddress();
	
	String userUniversityCode = "University";
	String userMajor = "Major";
	
	
	userUniversityCode = currentUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY).toString();
	userMajor = currentUser.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR).toString();
	
	
	long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
	ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId,ExpandoTable.class.getName(),EdisonExpando.TALBE_NAME);
	ExpandoValue universityValue = ExpandoValueLocalServiceUtil.getValue(companyId, table.getClassNameId(), EdisonExpando.TALBE_NAME, EdisonExpando.CDNM, Long.valueOf(userUniversityCode));
%>

<blockquote>
<h3><b><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teaminformation") %></b></h3>
</blockquote>
<br><br>


<portlet:actionURL name="createTeam" var="createTeam" windowState="normal">
	<portlet:param name="mvcPath" value="/html/teaminformation/view.jsp" />
	<portlet:param name="childChallengeID" value="<%=String.valueOf(childChallengeID) %>"/>
	<portlet:param name="userScreenName" value="<%=currentUser.getScreenName() %>"/>
</portlet:actionURL>

<aui:form class="form-horizontal" id="<portlet:namespace/>teamForm" enctype="multipart/form-data" method="POST" action="<%=createTeam%>">
	<table class="table">
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-name") %></label></td>
			<td><liferay-ui:input-localized id="inputUserName" name="inputUserName" xml="<%=currentUser.getFullName() %>" disabled="<%=true %>"></liferay-ui:input-localized></td>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-email") %></label></td>
			<td><input type="text" id="<portlet:namespace/>inputUserEmail" name="<portlet:namespace/>inputUserEmail" value="<%=currentUser.getDisplayEmailAddress() %>" disabled="<%=true %>"></input></td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-university") %></label></td>
			<td><liferay-ui:input-localized id="inputUserUniversity" name="inputUserUniversity" disabled="<%=true %>" xml="<%=universityValue.getData()%>"></liferay-ui:input-localized></td>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-major") %></label></td>
			<td><liferay-ui:input-localized id="inputUserMajor" name="inputUserMajor" xml="<%=userMajor %>" disabled="<%=true %>" ></liferay-ui:input-localized></td>	
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-field") %></label></td>
			<td><liferay-ui:input-localized id="inputApplyFiled" name="inputApplyFiled" xml="<%=ChallengeLocalServiceUtil.getChallenge(challengeid).getName() %>" disabled="<%=true %>"></liferay-ui:input-localized></td>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-grade") %></label>
			<td><select name="<portlet:namespace/>inputUserGrade">
					<option value="1"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-1") %></option>
					<option value="2"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-2") %></option>
					<option value="3"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-3") %></option>
					<option value="4"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-4") %></option>
					<option value="5"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-5") %></option>
					<option value="6"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-6") %></option>
					<option value="7"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-7") %></option>
				</select>
			</td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-teamname") %></label></td>
			<td colspan="3"><liferay-ui:input-localized style="width:80%" required="required" autofocus="autofocus" name="inputTeamName" xml=""></liferay-ui:input-localized></td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-papername") %></label></td>
			<td colspan="3"><liferay-ui:input-localized  type="textarea" style="width:80%" name="inputPaperName" xml=""></liferay-ui:input-localized></td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-paperabstract") %></label></td>
			<td colspan="3"><liferay-ui:input-localized type="textarea" rows="10" style="width:80%" name="inputPaperAbstract" xml=""></liferay-ui:input-localized></td>
		</tr>
		
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-papersubmission") %></label></td>
			<td>
				<input type="file" name="<portlet:namespace/>papersubmission" accept=".doc, .docx"></input><label>.doc, .docx </label>
			</td>
			<td colspan="2">
				<input type="file" name="<portlet:namespace/>papersubmissionPDF" accept=".pdf"></input><label>.pdf </label>
			</td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-presentationsubmission") %></label></td>
			<td colspan="3"><input type="file" id="<portlet:namespace/>presentationsubmission" name="<portlet:namespace/>presentationsubmission" accept=".ppt, .pptx"></input>
				<label>.ppt, .pptx</label>
			</td>
		</tr>
	

		<tr>
			<td>&nbsp;</td>
			<td colspan="3">
			<!-- submission button -->
			<div class="control-group">
				<div class="controls">
					
					<button class="btn btn-primary" type="submit"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-create") %></button>
					
					<button class="btn" type="reset"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-reset") %></button>
					
					
				</div>
			</div>
			</td>
		</tr>
	</table>
</aui:form>