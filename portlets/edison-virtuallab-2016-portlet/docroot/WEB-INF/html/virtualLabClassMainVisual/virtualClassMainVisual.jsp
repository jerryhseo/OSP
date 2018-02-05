<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@page import="com.liferay.portal.model.PasswordPolicy"%>
<%@page import="com.liferay.portal.service.PasswordPolicyLocalServiceUtil"%>

<liferay-portlet:resourceURL var="studentPasswordUpdateURL" id="studentPasswordUpdate" copyCurrentRenderParameters="false" />
<liferay-portlet:renderURL var="myClassURL" portletName="edisonmypage_WAR_edisondefault2016portlet" plid="${myClassPlid}">
	<liferay-portlet:param name="clickTab" value="myClass" />
</liferay-portlet:renderURL> 
<liferay-portlet:resourceURL var="getSurveyListURL" id="getSurveyList" copyCurrentRenderParameters="false" />

<% 
PasswordPolicy edionPasswordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());
%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/virtualClassMainVisual.css" media="screen"/>

<script type="text/javascript">
AUI().ready(function() {
	$("#<portlet:namespace/>tempUserPasswordUpdate-dialog").dialog({
		autoOpen: false,
		width: 'auto',
		height: 'auto',
		modal: true,
		resizable: false,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
	    	$(this).css('overflow', 'hidden');
	    	$(this).parent().removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget");
	    	$(this).removeClass("ui-widget-content");
	    	$(this).removeClass("ui-dialog-content");
	    },
	    close: function() {
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	$("#<portlet:namespace/>tempUserPasswordUpdate-dialog-close-btn").click(function() {
		$("#<portlet:namespace/>tempUserPasswordUpdate-dialog").dialog("close");
	});
});

function <portlet:namespace/>tempUserPasswordUpdate() {
}

function <portlet:namespace/>dialogOpen() {
	$("#<portlet:namespace/>tempUserPasswordUpdate-dialog").dialog("open");
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

function <portlet:namespace/>checkValidation() {
	var checkForm = document.tempUserPasswordUpdateForm;
	
	var curPassStr ="<liferay-ui:message key='current-password'/>";
	var newPassStr ="<liferay-ui:message key='new-password'/>";
	var passConStr ="<liferay-ui:message key='edison-create-account-field-title-password-confirm'/>";
	
	if(checkForm.<portlet:namespace/>currentPassword.value=="") {
		alert("<liferay-ui:message key='edison-this-field-is-required' arguments='"+curPassStr+"'/>");
		checkForm.<portlet:namespace/>currentPassword.focus();
		return false;
	}
	
	if(checkForm.<portlet:namespace/>newPassword.value=="") {
		alert("<liferay-ui:message key='edison-this-field-is-required' arguments='"+newPassStr+"'/>");
		checkForm.<portlet:namespace/>newPassword.focus();
		return false;
	}
		
	if(checkForm.<portlet:namespace/>reNewPassword.value=="") {
		alert("<liferay-ui:message key='edison-this-field-is-required' arguments='"+passConStr+"'/>");
		checkForm.<portlet:namespace/>reNewPassword.focus();
		return false;
	}
	
	if(checkForm.<portlet:namespace/>newPassword.value != checkForm.<portlet:namespace/>reNewPassword.value) {
		alert("<liferay-ui:message key='please-enter-the-same-value-again' />");
		checkForm.<portlet:namespace/>reNewPassword.focus();
		return false;
	}
	
	if(checkForm.<portlet:namespace/>currentPassword.value == checkForm.<portlet:namespace/>newPassword.value) {
		alert("<liferay-ui:message key='please-enter-the-same-value-again' />");
		checkForm.<portlet:namespace/>newPassword.focus();
		return false;
	}
	
	
	
	if(!passWordCheck(checkForm.<portlet:namespace/>currentPassword.value)) {
		alert("<liferay-ui:message key='edison-create-account-description-message-Fourth-line' />");
		checkForm.<portlet:namespace/>currentPassword.focus();
		return false;
	}
	
	if(!passWordCheck(checkForm.<portlet:namespace/>newPassword.value)) {
		alert("<liferay-ui:message key='edison-create-account-description-message-Fourth-line' />");
		checkForm.<portlet:namespace/>newPassword.focus();
		return false;
	}
	if(!passWordCheck(checkForm.<portlet:namespace/>reNewPassword.value)) {
		alert("<liferay-ui:message key='edison-create-account-description-message-Fourth-line' />");
		checkForm.<portlet:namespace/>reNewPassword.focus();
		return false;
	}
	
	var updateForm = $("form[name=tempUserPasswordUpdateForm]").serialize();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=studentPasswordUpdateURL%>",
		async : false,
		data : updateForm,
		success: function(msg) {
			if (msg == "200") {
				alert("<liferay-ui:message key='edison-virtuallab-classMainVisual-password-changed' />");
				$("#<portlet:namespace/>tempUserPasswordUpdate-dialog").dialog("close");
			} else if(msg == "300"){
				alert("<liferay-ui:message key='edison-create-account-password-unfit' />");
				checkForm.<portlet:namespace/>currentPassword.focus();
			} else {
				alert("<liferay-ui:message key='edison-data-event-error' />");
			}
			return true;
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}


/* 설문조사 */
function <portlet:namespace/>openSurvey(){
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPortletMode("view");
		portletURL.setWindowState("pop_up");
		portletURL.setPortletId("edisonvirtuallabclasssurvey_WAR_edisonvirtuallab2016portlet"); 
		portletURL.setParameter("classId", "${classInfo.classId}");
		portletURL.setParameter("groupId", "${classInfo.groupId}");
		Liferay.Util.openWindow(
			{
				dialog: {
					width:1024,
					height:720,
					cache: false,
					draggable: false,
					resizable: false,
					modal: true,
					destroyOnClose: true,
					after: {
						render: function(event) {
							$("button.btn.close").on("click", function(e){
								$("body").css('overflow','');
							});
						}
					}
				},
			id: "surveyDialog",
			uri: portletURL.toString(),
			title: "<liferay-ui:message key='edison-virtuallab-survey' />",
			}
		);
		
	});
}

/* 학생관리 */
function <portlet:namespace/>openStudentManagement(){
	
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPortletMode("view");
		portletURL.setWindowState("pop_up");
		portletURL.setPortletId("edisonvirtuallabclassstudentmanagement_WAR_edisonvirtuallab2016portlet"); 
		portletURL.setParameter("classId", "${classInfo.classId}");
		portletURL.setParameter("groupId", "${classInfo.groupId}");
		Liferay.Util.openWindow(
			{
				dialog: {
					width:1024,
					height:720,
					cache: false,
					draggable: false,
					resizable: false,
					modal: true,
					destroyOnClose: true,
					after: {
						render: function(event) {
							$("button.btn.close").on("click", function(e){
								$("body").css('overflow','');
							});
						}
					}
				},
			id: "studentManagementDialog",
			uri: portletURL.toString(),
			title: "<liferay-ui:message key='edison-virtuallab-student-infomation' />",
			}
		);
		
	});
}

</script>

<aui:script>
function <portlet:namespace/>moveClassList() {
	var virtualLabId = "${classInfo.virtualLabId }";
	var groupId = "${groupId}";
	var admin = "${admin }";
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPlid("${lablistPlid}"); <!-- 앱스토어 Plid -->
		portletURL.setPortletId("edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet");
		portletURL.setParameter("myRender", "virtualLabManagementDetail"); 
		portletURL.setParameter("virtualLabId", virtualLabId); 
		portletURL.setParameter("groupId", groupId);
		portletURL.setParameter("admin", admin);
		window.location.href = portletURL.toString();
	});
	
	jQuery.ajax({
		type: "POST",
		url: "",
		data  : searchData,
		success: function(data) {
			openWindow(renderURL, dialogId);
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
	
}



function <portlet:namespace/>myClass(){
	AUI().use("liferay-portlet-url", function(a) {
		 var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPlid("${myClassPlid}");
		portletURL.setPortletMode("view");
		portletURL.setPortletId("edisonmypage_WAR_edisondefault2016portlet");
		portletURL.setParameter("clickTab", "myClass");  
		
		window.location.href = portletURL.toString();
	});
}
</aui:script>

<div class="topbgwrap">
	<div class="classtopbg">
		<!--location-->
		<%-- <div class="classlocation">
			<ul>
				<li>
					<img src="${contextPath}/images/home_icon.png" width="14" height="13"> &nbsp;>
				</li>
				<li>
					교육 &nbsp;>
				</li>
				<li>
					CLASS
				</li>
			</ul>
		</div> --%>
		
		
		<div class="classttxt">
			<img src="${contextPath}/images/subtop_txt.png" width="643" height="40">
			<span>
				<img src="${contextPath}/images/class_txt.png" width="233" height="82">
			</span>
		</div>
		
		
		<!--class 정보-->
		<div class="infobox">
			<ul>
				<li>${classInfo.classTitle }</li>
				<li>Professor : ${classInfo.virtualLabPersonName }(${classInfo.virtualLabUniversityFieldNM })</li>
			</ul>
		</div>
		
		<!--공지사항-->
		<liferay-portlet:runtime portletName="edisonmultiboard_WAR_edisonboard2016portlet_INSTANCE_qY3mIhmesY9r"  queryString="&customId=class_${classInfo.classId}&boardGroupId=${classInfo.groupId}&redirectName=${redirectName}&redirectURL=${redirectURL}&isDefaultUserWrite=${isDefaultUserWrite}&isCustomAdmin=${isCustomAdmin }" />

		<!--버튼-->
		<div class="classtbtn" align="right">
			<div class="btn_linec"><liferay-ui:message key="edison-virtuallab-modify-my-info"/></div>&nbsp; 			<!-- 내 정보 수정 -->
			<c:if test="${role eq 'MANAGER' || role eq 'ADMIN' }">
				<div class="btn_linec" onclick="<portlet:namespace/>openStudentManagement();"><liferay-ui:message key="edison-virtuallab-student-management"/></div>&nbsp; 		<!-- 학생 관리 	-->
			</c:if>
			<div class="btn_linec" onclick="<portlet:namespace/>openSurvey();"><liferay-ui:message key="edison-virtuallab-survey"/></div>							<!-- 설문조사	-->
		</div>

	</div>
</div>

<%-- <div class="contentwrap">
	<div class="classvisual">
		<div class="cvtxt01"><img src="<%=renderRequest.getContextPath()%>/images/lighticon.png" width="17" height="25" style="margin:0 10px 0 0;"/>${classInfo.classTitle }</div>	
		<div class="cvtxt02"><img src="<%=renderRequest.getContextPath()%>/images/lighticon.png" width="17" height="25" style="margin:0 10px 0 0;"/><span>Professor :</span> ${classInfo.virtualLabPersonName }(${classInfo.virtualLabUniversityFieldNM })</div>
		<c:if test="${role eq 'TEMP'}">
			<div class="cvtxt02"><img src="<%=renderRequest.getContextPath()%>/images/lighticon.png" width="17" height="25" style="margin:0 10px 0 0;"/>
				<span><liferay-ui:message key="edison-table-list-header-usernm"/> : </span><%=themeDisplay.getUser().getFirstName()%>
			</div>
			<div class="vlbtn">
				<input id="<portlet:namespace/>tempUserPasswordUpdate" name="<portlet:namespace/>tempUserPasswordUpdate" type="button" value="<liferay-ui:message key='update-password' />" class="btn btn-default" onclick="<portlet:namespace/>dialogOpen()"/>
			</div>
		</c:if>
		<div class="vlbtn" style="float:right; margin-right:10px;">
			<input type="button" value="<liferay-ui:message key='edison-button-board-list' />" class="btn btn-default" onclick="<portlet:namespace/>moveClassList()" />	<!-- 목록 > 강좌로 이동-->
			<input type="button" value="<liferay-ui:message key='edison-course-class-myclass-list' />" class="btn btn-default" onclick="<portlet:namespace/>myClass()"  />	<!-- 나의 강의 목록 : myEdison > 수강현황 -->
		</div>
	</div>
</div> --%>

<div id="<portlet:namespace/>tempUserPasswordUpdate-dialog" class="newWindow" style="background-color:#fff; display:none;">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">
				<liferay-ui:message key='update-password' />
			</div>
		</div>
		<div class="newWclose">
			<img id="<portlet:namespace/>tempUserPasswordUpdate-dialog-close-btn" name="<portlet:namespace/>tempUserPasswordUpdate-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	
	<form id="tempUserPasswordUpdateForm" name="tempUserPasswordUpdateForm" method="post">
		<div class="newWcont01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="40%" />
					<col width="60%" />
				</colgroup>
				<tbody>
					<tr class="puptrline">
						<td class="puptitle"><liferay-ui:message key='current-password' /></td>
						<td class="puptitle">
							<input id="<portlet:namespace/>currentPassword" name="<portlet:namespace/>currentPassword" type="password" maxlength="15" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');"/>
						</td>
					</tr>
					<tr>
						<td class="puptxt2"><liferay-ui:message key='new-password' /></td>
						<td class="puptxt2">
							<input id="<portlet:namespace/>newPassword" name="<portlet:namespace/>newPassword" type="password" maxlength="15" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');"/>
						</td>
					</tr>
					<tr>
						<td class="puptxt2"><liferay-ui:message key='edison-create-account-field-title-password-confirm' /></td>
						<td class="puptxt2">
							<input id="<portlet:namespace/>reNewPassword" name="<portlet:namespace/>reNewPassword" type="password" maxlength="15" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="text-align: right; margin:0px 25px 30px 0px;">
			<input id="<portlet:namespace/>tempUserPasswordUpdate_button" name="<portlet:namespace/>tempUserPasswordUpdate_button" type="button"  class="btn btn-default" value="<liferay-ui:message key='edison-button-board-modify' />" onClick="<portlet:namespace/>checkValidation();" />
		</div>
	</form>
</div>
