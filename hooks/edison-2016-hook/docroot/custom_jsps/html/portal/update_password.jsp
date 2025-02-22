<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%
String passwordErrMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-create-account-password-unfit");
		 
String currentURL = PortalUtil.getCurrentURL(request);

String referer = ParamUtil.getString(request, WebKeys.REFERER, currentURL);

Ticket ticket = (Ticket)request.getAttribute(WebKeys.TICKET);

String ticketKey = ParamUtil.getString(request, "ticketKey");

if (referer.startsWith(themeDisplay.getPathMain() + "/portal/update_password") && Validator.isNotNull(ticketKey)) {
	referer = themeDisplay.getPathMain();
}

PasswordPolicy passwordPolicy = user.getPasswordPolicy();

String saveMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-button-save");
%>
<style type="text/css">
	.aui .portlet-topper{
		display: none;
	}
	
	.logintitlebox {
		margin: 0 auto;
		width: 100%;
	}
	
	.logintitle {
		font-size: 27px;
		color: #000;
		padding: 0px 20px 12px 0px;
		font-family: Arial, Nanum Barun Gothic,NanumGothic;
		float: left;
	}
	
	.loginintro {
		vertical-align: bottom;
		font-size: 13px;
		color: #777777;
		padding: 15px 0px 12px 0px;
		float: left;
	}
	
	.joinintro {
		vertical-align: bottom;
		font-size: 14px;
		color: #6e6e6e;
		padding: 15px 0px 25px 0px;
		line-height: 10px;
	}
	
	.joinbox {
		width: 94%;
		border: solid 6px #e5e5e5;
		text-align: center;
		padding: 22px 30px;
		text-align: left;
	}
	
	.edison .button08:hover{
		color: #fff;
		background: #606060;
		border: solid 1px #6a6a6b;
	}
	
	.borderline{
		height: 1px;
		border-bottom: 1px solid #e6e6e6;
	}
</style>
<div class="body_layout container">
<c:choose>
	<c:when test="<%= !themeDisplay.isSignedIn() && (ticket == null) %>">
		<div class="alert alert-warning">
			<liferay-ui:message key="your-password-reset-link-is-no-longer-valid" />

			<%
			PortletURL portletURL = new PortletURLImpl(request, PortletKeys.LOGIN, plid, PortletRequest.RENDER_PHASE);

			portletURL.setParameter("struts_action", "/login/forgot_password");
			portletURL.setWindowState(WindowState.MAXIMIZED);
			%>

			<div>
				<aui:a href="<%= portletURL.toString() %>" label="request-a-new-password-reset-link"></aui:a>
			</div>
		</div>
	</c:when>
	<c:when test="<%= SessionErrors.contains(request, UserLockoutException.class.getName()) %>">
		<div class="alert alert-error">
			<liferay-ui:message key="this-account-has-been-locked" />
		</div>
	</c:when>
	<c:otherwise>
		<aui:form action='<%= themeDisplay.getPathMain() + "/portal/update_password" %>' method="post" name="fm">
			<aui:input name="p_l_id" type="hidden" value="<%= layout.getPlid() %>" />
			<aui:input name="p_auth" type="hidden" value="<%= AuthTokenUtil.getToken(request) %>" />
			<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value="<%= referer %>" />
			<aui:input name="ticketKey" type="hidden" value="<%= ticketKey %>" />

<!-- 			<div class="alert alert-info"> -->
<!-- 				<liferay-ui:message key="please-set-a-new-password" /> -->
<!-- 			</div> -->

			<c:if test="<%= SessionErrors.contains(request, UserPasswordException.class.getName()) %>">

				<%
				UserPasswordException upe = (UserPasswordException)SessionErrors.get(request, UserPasswordException.class.getName());
				%>

				<div class="alert alert-error">
					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_ALREADY_USED %>">
						<liferay-ui:message key="that-password-has-already-been-used-please-enter-in-a-different-password" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_CONTAINS_TRIVIAL_WORDS %>">
						<liferay-ui:message key="that-password-uses-common-words-please-enter-in-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_INVALID %>">
						<liferay-ui:message key="that-password-is-invalid-please-enter-in-a-different-password" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_LENGTH %>">
						<%= LanguageUtil.format(pageContext, "that-password-is-too-short-or-too-long-please-make-sure-your-password-is-between-x-and-512-characters", String.valueOf(passwordPolicy.getMinLength()), false) %>
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_NOT_CHANGEABLE %>">
						<liferay-ui:message key="passwords-may-not-be-changed-under-the-current-password-policy" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_SAME_AS_CURRENT %>">
						<liferay-ui:message key="your-new-password-cannot-be-the-same-as-your-old-password-please-enter-in-a-different-password" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_TRIVIAL %>">
						<liferay-ui:message key="that-password-is-too-trivial" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_YOUNG %>">
						<%= LanguageUtil.format(pageContext, "you-cannot-change-your-password-yet-please-wait-at-least-x-before-changing-your-password-again", LanguageUtil.getTimeDescription(pageContext, passwordPolicy.getMinAge() * 1000), false) %>
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORDS_DO_NOT_MATCH %>">
						<liferay-ui:message key="the-passwords-you-entered-do-not-match-each-other-please-re-enter-your-password" />
					</c:if>
				</div>
			</c:if>

			<div class="logintitlebox">
				<div class="logintitle"><liferay-ui:message key="edison-forgot-password-change-title"/></div>
				<div class="loginintro"><liferay-ui:message key="please-set-a-new-password" /></div>
			</div>
			
			<div class="h20"></div>
			<div style="clear:left;"></div>
			<div class="joinbox">
				<div class="joinintro">
					<p><liferay-ui:message key="edison-create-account-description-message-Fourth-line" /></p>
				</div>
				<aui:fieldset label="new-password">
					<aui:input autoFocus="<%= true %>" class="lfr-input-text-container" label="password" name="password1" type="password">
						<aui:validator name="required" />
						<aui:validator  name="custom"  errorMessage="<%=passwordErrMsg%>">
							function (val, fieldNode, ruleValue) {
								return passWordCheck(val);
							}
						</aui:validator>
					</aui:input>
	
					<aui:input class="lfr-input-text-container" label="enter-again" name="password2" type="password">
						<aui:validator name="required" />
						<aui:validator name="equalTo">
							'#<portlet:namespace />password1'
						</aui:validator>
					</aui:input>
				</aui:fieldset>
				<div style="clear:left;"></div>
				<div class="h20"></div>
				<div class="borderline"></div>
				<div class="h10"></div>
				<div class="buttonbox08">
					<aui:button type="submit" value="<%=saveMsg%>" cssClass="button08"/>
				</div>
			</div>
		</aui:form>
	</c:otherwise>
</c:choose>
</div>
<script type="text/javascript">
	function passWordCheck(val){
		var retbool = true;
		var minLength = <%=passwordPolicy.getMinLength()%>;
		var reg = new RegExp("<%=passwordPolicy.getRegex()%>");
		if(val.length<minLength){
			retbool = false;
		}else{
			if(!reg.test(val)){
				retbool = false;
			}
		}
		return retbool;
		
	}
</script>