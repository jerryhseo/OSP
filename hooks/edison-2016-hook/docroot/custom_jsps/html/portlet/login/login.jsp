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
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ include file="/html/portlet/login/init.jsp" %>
<%
	String loginMsg = LanguageUtil.get(themeDisplay.getLocale(),"Sign-in");
	String termsOfUseURL = themeDisplay.getURLHome()+"/-/termsofuse/view";
%>

<style type="text/css">
	.aui .control-group.error .help-inline{
		color: #a94442;
	}
	
	.portlet-login .logo{
		text-align: center;
		width: 100%;
		margin-bottom: 5px;
	}
	
	.portlet-login .login-btn{
		width : 100%;
	}
	
	.portlet-login .portlet-body{
		width : 600px;
		margin: 10px auto;
		border: solid 6px #e5e5e5; 
 		padding: 40px 0px; 
	}
	
	.portlet-login .loginbox{
		width : 450px;
		padding: 10px 0px;
		margin: 0 auto;
		border-bottom: 2px solid #e5e5e5;
	}
	
	.portlet-login .navi{
		width : 450px;
		margin: 10px auto;
	}
	
	.portlet-login .alert-error{
		width : 450px;
		margin: 0 auto;
	}
	
	
	
	.portlet-login div.h20 {
		height: 20px;
		clear: both;
	}
	
	.aui .portlet-login .loginbox input[type=text].field,
	.aui .portlet-login .loginbox input[type=password].field{
		height: 40px;
		margin-bottom: 5px;
	}
	
</style>


<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">

		<%
		String signedInAs = HtmlUtil.escape(user.getFullName());

		if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
			String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());

			if (PropsValues.DOCKBAR_ADMINISTRATIVE_LINKS_SHOW_IN_POP_UP) {
				signedInAs = "<a class=\"signed-in\" href=\"javascript:Liferay.Util.openWindow({dialog: {destroyOnHide: true}, title: '" + HtmlUtil.escapeJS(LanguageUtil.get(pageContext, "my-account")) + "', uri: '" + HtmlUtil.escapeJS(myAccountURL) + "'});\">" + signedInAs + "</a>";
			}
			else {
				myAccountURL = HttpUtil.setParameter(myAccountURL, "controlPanelCategory", PortletCategoryKeys.MY);

				signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
			}
		}
		%>

		<%= LanguageUtil.format(pageContext, "you-are-signed-in-as-x", signedInAs, false) %>
	</c:when>
	<c:otherwise>

		<%
		String redirect = ParamUtil.getString(request, "redirect");

		String login = LoginUtil.getLogin(request, "login", company);
		String password = StringPool.BLANK;
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

		if (Validator.isNull(authType)) {
			authType = company.getAuthType();
		}
		
		%>

		<portlet:actionURL secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>" var="loginURL">
			<portlet:param name="struts_action" value="/login/login" />
		</portlet:actionURL>
		
		<portlet:renderURL var="forgotIdURL">
			<portlet:param name="struts_action" value="/login/forgot_id" />
		</portlet:renderURL>
		
		<portlet:renderURL var="forgotPasswordURL">
			<portlet:param name="struts_action" value="/login/forgot_password" />
		</portlet:renderURL>
		
		<div class="logo">
			<img alt="EDISON" src="<%=themeDisplay.getLayoutSetLogo()%>">
		</div>
		
		<aui:form action="<%= loginURL %>" autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>' cssClass="sign-in-form" method="post" name="fm" onSubmit="event.preventDefault();">
			<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="doActionAfterLogin" type="hidden" value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? true : false %>" />

			<c:choose>
				<c:when test='<%= SessionMessages.contains(request, "userAdded") %>'>

					<%
					String userEmailAddress = (String)SessionMessages.get(request, "userAdded");
					String userPassword = (String)SessionMessages.get(request, "userAddedPassword");
					%>

					<div class="alert alert-success">
						<c:choose>
							<c:when test="<%= company.isStrangersVerify() || Validator.isNull(userPassword) %>">
								<%= LanguageUtil.get(pageContext, "thank-you-for-creating-an-account") %>

								<c:if test="<%= company.isStrangersVerify() %>">
									<%= LanguageUtil.format(pageContext, "your-email-verification-code-has-been-sent-to-x", userEmailAddress) %>
								</c:if>
							</c:when>
							<c:otherwise>
								<%= LanguageUtil.format(pageContext, "thank-you-for-creating-an-account.-your-password-is-x", userPassword, false) %>
							</c:otherwise>
						</c:choose>

						<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
							<%= LanguageUtil.format(pageContext, "your-password-has-been-sent-to-x", userEmailAddress) %>
						</c:if>
					</div>
				</c:when>
				<c:when test='<%= SessionMessages.contains(request, "userPending") %>'>

					<%
					String userEmailAddress = (String)SessionMessages.get(request, "userPending");
					%>

					<div class="alert alert-success">
						<%= LanguageUtil.format(pageContext, "thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved", userEmailAddress) %>
					</div>
				</c:when>
				
				<c:when test='<%= SessionMessages.contains(renderRequest, "userEmailSender") %>'>
					<div class="alert alert-success">
						<liferay-ui:message key="your-request-completed-successfully" />
					</div>
				</c:when>
			</c:choose>
			
			<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-login-because-the-maximum-number-of-users-has-been-reached" />
			<liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
			<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
			<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= UserLockoutException.class %>" message="this-account-has-been-locked" />
			<liferay-ui:error exception="<%= UserPasswordException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= UserScreenNameException.class %>" message="authentication-failed" />
			
			<div class="loginbox">
				<aui:fieldset>
					<aui:input label="" cssClass="clearable" type="text" name="login" value="<%= login %>" style="font-size: 13px;" autoFocus="<%= true %>" showRequiredLabel="<%= false %>" maxLength="32" placeholder="Enter Your ID">
						<aui:validator name="required"/>
					</aui:input>
					
					<aui:input label="" cssClass="clearable" type="password" value="<%= password %>" style="font-size: 13px;" name="password" showRequiredLabel="<%= false %>" placeholder="Enter Your Password">
						<aui:validator name="required"/>
					</aui:input>
					<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>
				</aui:fieldset>
				
				<aui:button-row>
					<aui:button type="submit" value="<%=loginMsg%>" cssClass="login-btn"/>
					<div class="h20"></div>
					<aui:button type="button" value="KAFE" cssClass="btn-success login-btn"/>
				</aui:button-row>
			</div>
		</aui:form>
		<div class="navi">
			<div style="float:left;">
				<a href="<%=forgotIdURL%>&saveLastPath=false" target="_self"><span class="icon-user-md">  <liferay-ui:message key="forgot-id"/></span></a>
				<a href="<%=forgotPasswordURL%>&saveLastPath=false" target="_self"><span class="icon-user-md">  <liferay-ui:message key="edison-forgot-password"/></span></a>
			</div>
			<div style="float:right;">
				<a href="<%=termsOfUseURL%>" target="_self"><span class="icon-user">  <liferay-ui:message key="Register-edison"/></span></a>
			</div>
		</div>
		
		<aui:script use="aui-base">
			A.all('.clearable').on('focus',function(event){
				var currentTargetNode = event.currentTarget;
				var parentNode = currentTargetNode.get('parentNode');
				if(parentNode.hasClass('error')){
					parentNode.removeClass('error');
				}
			});
			
			var form = A.one(document.<portlet:namespace />fm);

			form.on(
				'submit',
				function(event) {
					var redirect = form.one('#<portlet:namespace />redirect');

					if (redirect) {
						var redirectVal = redirect.val();

						redirect.val(redirectVal + window.location.hash);
					}

					submitForm(form);
				}
			);

			var password = form.one('#<portlet:namespace />password');

			if (password) {
				password.on(
					'keypress',
					function(event) {
						Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
					}
				);
			}
		</aui:script>
	</c:otherwise>
</c:choose>