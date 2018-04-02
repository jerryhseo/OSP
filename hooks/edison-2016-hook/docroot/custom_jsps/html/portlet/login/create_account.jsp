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
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>

<%@page import="java.util.Comparator"%>

<%@ include file="/html/portlet/login/init.jsp" %>
<%
String EdisonExpando_USER_UNIVERSITY = renderRequest.getAttribute("EdisonExpando_USER_UNIVERSITY")!=null?(String)renderRequest.getAttribute("EdisonExpando_USER_UNIVERSITY"):"";
String EdisonExpando_USER_PROJECT_CATEGORY_ID = renderRequest.getAttribute("EdisonExpando_USER_PROJECT_CATEGORY_ID")!=null?(String)renderRequest.getAttribute("EdisonExpando_USER_PROJECT_CATEGORY_ID"):"";

String saml_email = (String) renderRequest.getAttribute("saml_email");
String saml_eppn =  (String) renderRequest.getAttribute("saml_eppn");
String saml_firstName =  (String) renderRequest.getAttribute("saml_firstName");

List<AssetCategory> assetCategoryList = new ArrayList<AssetCategory>();
if(renderRequest.getAttribute("assetCategoryList")!=""){
	assetCategoryList = (List<AssetCategory>) renderRequest.getAttribute("assetCategoryList");
}
String duplicationCheckURL = themeDisplay.getURLPortal()+themeDisplay.getPathMain();
		 
String redirect = ParamUtil.getString(request, "redirect");

String openId = ParamUtil.getString(request, "openId");
boolean male = ParamUtil.getBoolean(request, "male", true);

Calendar birthdayCalendar = CalendarFactoryUtil.getCalendar();

birthdayCalendar.set(Calendar.MONTH, Calendar.JANUARY);
birthdayCalendar.set(Calendar.DATE, 1);
birthdayCalendar.set(Calendar.YEAR, 1970);

String duplicateScreenNameMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-create-account-id-used");
String passwordErrMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-create-account-password-unfit");
String duplicateEmaidMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-create-account-email-used");
String idErrMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-create-account-id-unfit");

PasswordPolicy edionPasswordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());

Comparator<Group> sort = new Comparator<Group>() {
	public int compare(Group o1Group, Group o2Group) {
		long o1 = o1Group.getGroupId();
		long  o2 = o2Group.getGroupId();
		return o1 < o2 ? -1 : (o1 == o2 ? 0 : 1);
	}
};

List<Group> siteGroups = ListUtil.sort(GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(),themeDisplay.getSiteGroupId(),true),sort);

%>

<liferay-portlet:renderURL 
	portletName="edisonorgcodesearch_WAR_edisondefault2016portlet" 
	portletMode="view" 
	windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="syscommoncdURL">
	<liferay-portlet:param name="up_code" value="1501"/>
	<liferay-portlet:param name="com_search_type" value="orgSearch"/>
	<liferay-portlet:param name="popupType" value="createAccount"/>
	<liferay-portlet:param name="popup_title" value="edison-org-code-search"/>
</liferay-portlet:renderURL>

<style type="text/css">
    #university-group{ margin-bottom: 20px;}
    #university-group .required{ position: absolute; top: 35px;}
	.logintitlebox {
		margin: 10px auto;
		width: 100%;
	}
	
	.logintitle {
		font-size: 27px;
		color: #000;
		padding: 0px 20px 12px 0px;
		font-family: Arial, Nanum Barun Gothic, NanumGothic;
		float: left;
	}
	
	.loginintro {
		vertical-align: bottom;
		font-size: 13px;
		color: #777777;
		padding: 15px 0px 12px 0px;
		float: left;
	}
	
	.joinbox {
		width: 94%;
		border: solid 6px #e5e5e5;
		text-align: center;
		padding: 22px 30px;
		text-align: left;
	}
	
	.jointitle{
		background: url(/edison-2016-hook/images/termsofuse/member_icon.gif) 0px 8px no-repeat;
		font-size: 19px;
		color: #6e6e6e;
		font-weight: 600;
		padding-left: 25px;
		text-align: left;
		line-height: 30px;
		font-family: Arial, Nanum Barun Gothic,NanumGothic;
	}
	
	.joinintro {
		vertical-align: bottom;
		font-size: 14px;
		color: #6e6e6e;
		padding: 15px 0px 25px 0px;
		line-height: 10px;
	}
  
    .body_layout{margin-bottom: 20px;}
</style>

<portlet:actionURL secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>" var="createAccountURL">
	<portlet:param name="struts_action" value="/login/create_account" />
</portlet:actionURL>
<div class="body_layout container">
<aui:form action="<%= createAccountURL %>" method="post" name="fm">
	<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="openId" type="hidden" value="<%= openId %>" />
	<aui:input name="eppn" type="hidden" value="<%= saml_eppn %>" />

	<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
	<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
	<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
	<liferay-ui:error exception="<%= CaptchaMaxChallengesException.class %>" message="maximum-number-of-captcha-attempts-exceeded" />
	<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />
	<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-create-user-account-because-the-maximum-number-of-users-has-been-reached" />
	<liferay-ui:error exception="<%= ContactFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= ContactFullNameException.class %>" message="please-enter-a-valid-first-middle-and-last-name" />
	<liferay-ui:error exception="<%= ContactLastNameException.class %>" message="please-enter-a-valid-last-name" />
	<liferay-ui:error exception="<%= DuplicateOpenIdException.class %>" message="a-user-with-that-open-id-already-exists" />
	<liferay-ui:error exception="<%= DuplicateUserEmailAddressException.class %>" message="the-email-address-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%= DuplicateUserIdException.class %>" message="the-user-id-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%= DuplicateUserScreenNameException.class %>" message="the-screen-name-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%= EmailAddressException.class %>" message="please-enter-a-valid-email-address" />

	<liferay-ui:error exception="<%= GroupFriendlyURLException.class %>">

		<%
		GroupFriendlyURLException gfurle = (GroupFriendlyURLException)errorException;
		%>

		<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.DUPLICATE %>">
			<liferay-ui:message key="the-screen-name-you-requested-is-associated-with-an-existing-friendly-url" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />
	<liferay-ui:error exception="<%= NoSuchListTypeException.class %>" message="please-select-a-type" />
	<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />
	<liferay-ui:error exception="<%= PhoneNumberException.class %>" message="please-enter-a-valid-phone-number" />
	<liferay-ui:error exception="<%= RequiredFieldException.class %>" message="please-fill-out-all-required-fields" />
	<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class %>" message="the-email-address-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= ReservedUserIdException.class %>" message="the-user-id-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= ReservedUserScreenNameException.class %>" message="the-screen-name-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= TermsOfUseException.class %>" message="you-must-agree-to-the-terms-of-use" />
	<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= UserIdException.class %>" message="please-enter-a-valid-user-id" />

	<liferay-ui:error exception="<%= UserPasswordException.class %>">

		<%
		UserPasswordException upe = (UserPasswordException)errorException;
		%>

		<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_CONTAINS_TRIVIAL_WORDS %>">
			<liferay-ui:message key="that-password-uses-common-words-please-enter-in-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
		</c:if>

		<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_INVALID %>">
			<liferay-ui:message key="that-password-is-invalid-please-enter-in-a-different-password" />
		</c:if>

		<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_LENGTH %>">

			<%
			PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());
			%>

			<%= LanguageUtil.format(pageContext, "that-password-is-too-short-or-too-long-please-make-sure-your-password-is-between-x-and-512-characters", String.valueOf(passwordPolicy.getMinLength()), false) %>
		</c:if>

		<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_TRIVIAL %>">
			<liferay-ui:message key="that-password-is-too-trivial" />
		</c:if>

		<c:if test="<%= upe.getType() == UserPasswordException.PASSWORDS_DO_NOT_MATCH %>">
			<liferay-ui:message key="the-passwords-you-entered-do-not-match-each-other-please-re-enter-your-password" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= UserScreenNameException.class %>" message="please-enter-a-valid-screen-name" />
	<liferay-ui:error exception="<%= WebsiteURLException.class %>" message="please-enter-a-valid-url" />

	<c:if test='<%= SessionMessages.contains(request, "openIdUserInformationMissing") %>'>
		<div class="alert alert-info">
			<liferay-ui:message key="you-have-successfully-authenticated-please-provide-the-following-required-information-to-access-the-portal" />
		</div>
	</c:if>

	<aui:model-context model="<%= Contact.class %>" />
  
	<div class="logintitlebox">
			<div class="logintitle"><liferay-ui:message key="edison-create-account-message" /></div>
			<div class="loginintro"><liferay-ui:message key="edison-create-account-course-message" /></div>
	</div>
	<div class="h20"></div>
	<div class="joinbox">
		<div class="jointitle"><liferay-ui:message key="edison-create-account-member-information-input-message" /></div>
		<div class="joinintro">
			<p><font color="red">*</font> <liferay-ui:message key="edison-create-account-description-message-first-line" /></p>
			<p><liferay-ui:message key="edison-create-account-description-message-second-line" /></p>
			<p><liferay-ui:message key="edison-create-account-description-message-third-line" /></p>
<!-- 			<p><liferay-ui:message key="edison-create-account-description-message-Fourth-line" /></p> -->
		</div>
    
  		<div class="table3_list">
          <fieldset>
            <div class="row">
              <div class="col-sm-6">
                <div class="form-group">
                  <label for="firstName"><liferay-ui:message key="edison-create-account-field-title-name" />(<fontcolor="red">*</font>)</label>
                  <aui:input autoFocus="<%=windowState.equals(WindowState.MAXIMIZED)%>" model="<%=User.class%>"
                    name="firstName" label="" cssClass="long_field" value="<%=saml_firstName%>">
                    <aui:validator name="required" />
                    <aui:validator name="maxLength">32</aui:validator>
                  </aui:input>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <label for="screenName">ID(<font color="red">*</font>)</label>
                  <c:if test="<%= !PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE) %>">
                      <aui:input label="" model="<%= User.class %>" name="screenName" cssClass="long_field">
                          <aui:validator name="required"/>
                          <aui:validator name="maxLength">32</aui:validator>
                          <aui:validator name="minLength">3</aui:validator>
                          <aui:validator  name="custom"  errorMessage="<%=duplicateScreenNameMsg%>"> 
                              function (val, fieldNode, ruleValue) {
                                  return idCheck(val);
                              }
                          </aui:validator>
                          <aui:validator  name="custom"  errorMessage="<%=idErrMsg%>">
                              function (val) {
                                  return idExpressionCheck(val);
                              }
                          </aui:validator>
                      </aui:input>
                  </c:if>
                </div>
              </div>
            </div>
            
            <c:if test="<%= PropsValues.LOGIN_CREATE_ACCOUNT_ALLOW_CUSTOM_PASSWORD %>">
            <div class="row">
              <div class="col-sm-6">
                <div class="form-group">
                  <label for="password1"><liferay-ui:message key="edison-create-account-field-title-password" />(<font color="red">*</font>)</label>
                  <aui:input label="" name="password1" size="30" type="password" value="">
                      <aui:validator name="required"/>
                      <aui:validator  name="custom"  errorMessage="<%=passwordErrMsg%>">
                          function (val, fieldNode, ruleValue) {
                              return passWordCheck(val);
                          }
                      </aui:validator>
                  </aui:input>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <label for="password2"><liferay-ui:message key="edison-create-account-field-title-password-confirm" />(<font color="red">*</font>)</label>
                  <aui:input label="" name="password2" size="30" type="password" value="">
                      <aui:validator name="required"/>
                      <aui:validator name="equalTo">
                          '#<portlet:namespace />password1'
                      </aui:validator>
                  </aui:input>
                </div>
              </div>
            </div>
            </c:if>
            
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group">
                  <label for="emailAddress"><liferay-ui:message key="edison-create-account-field-title-email" />(<font color="red">*</font>)</label>
                  <aui:input model="<%= User.class %>" name="emailAddress" label="" cssClass="long_field" value="<%=saml_email%>">
                      <c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_EMAIL_ADDRESS_REQUIRED) %>">
                          <aui:validator name="required"/>
                      </c:if>
                      <aui:validator  name="custom"  errorMessage="<%=duplicateEmaidMsg%>"> 
                          function (val, fieldNode, ruleValue) {
                              return emailCheck(val);
                          }
                      </aui:validator>
                  </aui:input>
                </div>
              </div>
            </div>
            
            <div class="row">
              <div class="col-sm-6">
                <div class="form-group">
                  <label for="com_cd_nm"><liferay-ui:message key="edison-create-account-field-title-university" />(<font color="red">*</font>)</label>
                  <div class="input-group" id="university-group">
                    <aui:input label="" id="com_cd_nm" name="com_cd_nm" cssClass="long_field" type="text" value="" readonly="readonly">
                        <aui:validator name="required"/>
                    </aui:input>
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button" onclick="syscommoncdPopup()"><liferay-ui:message key="edison-button-search"/></button>
                    </span>
                  </div>
                  <liferay-ui:custom-attribute className="<%= User.class.getName() %>" classPK="<%= 0 %>" 
                      editable="<%= true %>" label="<%= false %>" name="<%=EdisonExpando_USER_UNIVERSITY%>"/>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <label for="<portlet:namespace/>major-field-input"><liferay-ui:message key="edison-create-account-field-title-major" /></label>
                  <input type="text" name="<portlet:namespace/>major-field-input" id="<portlet:namespace/>major-field-input" class="form-control" size="30"/>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group">
                  <label><liferay-ui:message key="edison-create-account-field-title-site" /></label>
                </div>
                <div class="panel panel-default form-inline">
                <div class="checkbox panel-body">
                  <%
                      for(Group group:siteGroups){
                          if(group.isActive()&&group.getType()==1){
                              String groupName = LanguageUtil.get(themeDisplay.getLocale(), StringUtil.toUpperCase(group.getName()));
                  %>
                              <aui:input name='<%= "join_site_id_"+group.getGroupId() %>' type="checkbox" value="<%= group.getName() %>" label="<%=groupName%>"/>
                  <%
                          }
                      }
                  %>
                </div>
                </div>
              </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label><liferay-ui:message key="edison-create-account-field-title-captcha" />(<font color="red">*</font>)</label>
                        <portlet:resourceURL var="captchaURL">
                            <portlet:param name="struts_action" value="/login/captcha" />
                        </portlet:resourceURL>
                        <liferay-ui:captcha url="<%= captchaURL %>" />
                    </div>
                </div>
            </div>
            <c:choose>
              <c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_BIRTHDAY) %>">
                <div class="row">
                    <div class="col-sm-12">
                        <label for="birthday">birthday</label>
                        <aui:input name="birthday" value="<%= birthdayCalendar %>" />
                    </div>
                </div>
              </c:when>
              <c:otherwise>
                <aui:input name="birthdayMonth" type="hidden" value="<%= Calendar.JANUARY %>" />
                <aui:input name="birthdayDay" type="hidden" value="1" />
                <aui:input name="birthdayYear" type="hidden" value="1970" />
              </c:otherwise>
            </c:choose>
            <c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_MALE) %>">
              <div class="row">
                  <div class="col-sm-12">
                    <aui:select label="gender" name="male">
                        <aui:option label="male" value="1" />
                        <aui:option label="female" selected="<%= !male %>" value="0" />
                    </aui:select>
                  </div>
              </div>
            </c:if>
            <div class="form-group">
                <input type="submit" value="<liferay-ui:message key="edison-button-save" />" class="btn btn-primary">
                <input type="button" value="<liferay-ui:message key="edison-button-cancel" />" class="btn btn-default" onclick="cancle();">
            </div>
          </fieldset>
		</div>
	</div>
</aui:form>
</div>
<script type="text/javascript">
    $("input[name*=firstName]").css("max-width","none");
    $("input[name*=screenName]").css("max-width","none");
    $("input[name*=emailAddress]").css("max-width","none");
	$("input[name*=universityField]").css("display","none");
	$("input[name*=projectCategoryId]").css("display","none");
	
	function cancle(){
		location.href = "<%= PortalUtil.getHomeURL(request)%>";
	}	
	
	
	function syscommoncdPopup(){
		var URL = "<%=syscommoncdURL%>";
		w = 850;
		h = 550;		
		x = (screen.availWidth - w) / 2;
		y = (screen.availHeight - h) / 2;
		var options = "width="+w+", height="+h+", left="+x+",top="+y+",toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
		var openPop;
		if(openPop != null){
			openPop.focus();
		}else{
			openPop = window.open(URL, "syscommoncdPopup",options);
			openPop.focus();
		}
	}
	
	function idCheck(val){
		 var retbool = true;
		 var url =  "<%=duplicationCheckURL%>";
		  jQuery.ajax({
	    		type: "POST",
	    		url:  url+"/portal/create_account_validataion",
	    		async : false,
	    		data: { 
	    			"data" : val,
	    			"checkDiv" : "ID"
	    		},
	    		success: function(data) {
	   			 if(data == 'true'){
	   				
	   			 }else{
	   				retbool = false;
	   			 }
	    		},
	    		error:function(data,e){
	    			alert("ID duplication Check Error!!!");
	    			retbool = false;
	    		}
	    	});	 
		
		  
		 return retbool;
		 
	}
	
	function emailCheck(val){
		 var retbool = true;
		 var url =  "<%=duplicationCheckURL%>";
		  jQuery.ajax({
	    		type: "POST",
	    		url:  url+"/portal/create_account_validataion",
	    		async : false,
	    		data: {
	    			"data" : val,
	    			"checkDiv" : "EMAIL"
	    		},
	    		success: function(data) {
	   			 if(data == 'true'){
	   				
	   			 }else{
	   				retbool = false;
	   			 }

	    		},
	    		error:function(data,e){
	    			alert("emailAddress duplication Check Error!!!");
	    			retbool = false;
	    		}
	    	});	 

		 return retbool;
	}
	
	function idExpressionCheck(val){
		var retbool = false;
		var idCheck = /^[A-Za-z0-9+]*$/;
		if(idCheck.test(val)){
			retbool = true;
		}
		return retbool;
	}

	function passWordCheck(val){
		var retbool = true;
		var minLength = <%=edionPasswordPolicy.getMinLength()%>;
		var reg = new RegExp("<%=edionPasswordPolicy.getRegex()%>");
		if(val.length<minLength){
			retbool = false;
		}else{
			if(!reg.test(val)){
				retbool = false;
			}
		}
		return retbool;
		
	}
	
	function <portlet:namespace/>changeCategory(value){
		$("input[name*=ExpandoAttribute--projectCategoryId-]").val(value);
	}
</script>
