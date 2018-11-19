<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>

<%@ page import="org.kisti.edison.science.Exception.ScienceAppException" %>


<portlet:actionURL var="submitURL" copyCurrentRenderParameters="false" name="appAction">
	<portlet:param name="clickTab" value="${clickTab}"/>
	<portlet:param name="isPort" value="${isPort}"/>
	<portlet:param name="actionType" value="appInfomation"/>
	
	<portlet:param name="developers" value="${data.developersTextArea}"/>
	<portlet:param name="license" value="${data.license}"/>
	<portlet:param name="entryId" value="${data.entryId}"/>
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
	<portlet:param name="searchValue" value="${searchValue}"/>
	<portlet:param name="searchOption" value="${searchOption}"/>
	<portlet:param name="searchStatus" value="${searchStatus}"/>
	<portlet:param name="p_curPage" 	value="${p_curPage}" />
	<portlet:param name="redirectURL" 	value="${redirectURL}" />
</portlet:actionURL>

<liferay-portlet:renderURL var="popupRequsetScienceAppCommentListURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.POP_UP.toString() %>" portletMode="view">
	<liferay-portlet:param name="myaction" value="popupRequsetScienceAppCommentList" />
</liferay-portlet:renderURL>


<liferay-portlet:resourceURL var="subCategortSearchURL" id="subCategortSearch" copyCurrentRenderParameters="false" escapeXml="false"/>

<%
	Locale[] locales = LanguageUtil.getAvailableLocales();
	String localesStr = "";
	
	String siteDefaultLanuageId = LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale());
	
	for(Locale aLocale : locales){
		String languageId = LocaleUtil.toLanguageId(aLocale);
		if(localesStr.equals("")){
			localesStr += languageId;
		}else{
			localesStr += ","+languageId;
		}
		
		String languageNm = aLocale.getDisplayName(themeDisplay.getLocale());
	}
	

	String exceptionNameMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-app-validation-name-exception-msg");
	String exceptionVersionMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-app-validation-version-exception-msg");
	
	String mode = GetterUtil.get(request.getAttribute("mode"), "");
	boolean isPort = GetterUtil.getBoolean(request.getAttribute("isPort"), false);
%>
<style type="text/css">
	.aui .long_field{
		width: 350px !important;
	}
	
	.aui .short_field{
		width: 150px !important;
	}
	
	.aui .too_long_field{
		width: 500px !important;
	}
	
	.aui .swrightcont .alert{
		margin-top: 10px;
	}
</style>
<c:if test="<%= SessionErrors.contains(renderRequest, ScienceAppException.class.getName()) %>">
	<%
		ScienceAppException sae = (ScienceAppException)SessionErrors.get(renderRequest, ScienceAppException.class.getName());
	%>
	<div class="alert alert-error">
		<c:if test="<%= sae.getType() == ScienceAppException.EXISTS_NAME_VERSION_DATABASE %>">
			<liferay-ui:message key="edison-app-duplication-app-name-exception-msg" />
		</c:if>
		
		<c:if test="<%= sae.getType() == ScienceAppException.FAIL_VALIDATION_SCIENCE_APP_NAME %>">
			<liferay-ui:message key="edison-app-validation-name-exception-msg" />
		</c:if>
	</div>
</c:if>
<div class="edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			<liferay-ui:message key='edison-science-appstore-toolkit-default-information' />
		</h3>
		<div class="btn-group pull-right">
			<input class="button02_2" onclick="<portlet:namespace/>goList();" value="<liferay-ui:message key='edison-button-board-list'/>" type="button">
			<c:if test="${data.status gt 1901003}">
				<input class="button02_1" onclick="<portlet:namespace/>copyScienceApp();" value="<liferay-ui:message key='edison-appstore-copy'/>" type="button">
			</c:if>
			
			<c:if test="${appStatusButtonView}">
				<c:if test="${data.status eq '1901001'}">
					<input class="button02_3" onclick="<portlet:namespace/>statusSubmit('1901002');" value="<liferay-ui:message key='edison-appstore-status-request'/>" type="button">
				</c:if>
				<c:if test="${data.status eq '1901002'}">
					<input class="button02_3" onclick="<portlet:namespace/>statusSubmit('1901001');" value="<liferay-ui:message key='edison-appstore-status-denial'/>" type="button">
					<input class="button02_3" onclick="<portlet:namespace/>statusSubmit('1901003');" value="<liferay-ui:message key='edison-appstore-status-private'/>" type="button">
					<input class="button02_3" onclick="<portlet:namespace/>statusSubmit('1901004');" value="<liferay-ui:message key='edison-appstore-status-service'/>" type="button">
				</c:if>
				<c:if test="${data.status eq '1901003'}">
					<input class="button02_3" onclick="<portlet:namespace/>statusSubmit('1901004');" value="<liferay-ui:message key='edison-appstore-status-service'/>" type="button">
				</c:if>
				
				<c:if test="${data.status eq '1901004'}">
					<input class="button02_3" onclick="<portlet:namespace/>statusSubmit('1901003');" value="<liferay-ui:message key='edison-appstore-status-private'/>" type="button">
				</c:if>
			</c:if>
			
			<input class="button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.ADD%>');return false;" value="<liferay-ui:message key='edison-button-save'/>" type="button">
			<c:if test="${!empty scienceAppId && ownerThan }">
				<input class="button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.DELETE%>');return false;" value="<liferay-ui:message key='delete'/>" type="button">
			</c:if>
		</div>
	</div>
</div>
<div class="table1_list">
	<aui:form name="frm" method="POST" action="<%=submitURL%>">
		<aui:input name="actionMode" value="${mode}" type="hidden"/>
		
		<c:if test="${mode eq 'update'}">
			<aui:input name="previousVersion" type="hidden" value="${data.version}" label=""/>
		</c:if>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data" >
			<colgroup>
				<col width="20%">
				<col width="40%">
				<col width="15%">
				<col width="25%">
			</colgroup>
			<tr>
				<th><liferay-ui:message key='edison-appstore-solver-name' /><span class="requiredField"> *</span></th>
				<td>
					<c:choose>
						<c:when test="${empty scienceAppId}">
							<aui:input name="name" type="text" cssClass="long_field field" label="" value="" maxLength="100">
								<aui:validator name="required"/>
								<aui:validator  name="custom"  errorMessage="<%=exceptionNameMsg%>">
									function (val, fieldNode, ruleValue) {
										 var retbool = true;
										 var kor_check = /^[A-Za-z0-9][A-Za-z0-9\\_]*$/;
<!-- 										 var kor_check = /[a-zA-Z][a-zA-Z0-9\\-\\.\\+\\_]+/; -->
										 if (!kor_check.test(val)){
											 retbool = false;
										 }
										return retbool;
									}
								</aui:validator>
							</aui:input>
							<p style="color: red;">※ <liferay-ui:message key='edison-appstore-solver-nm-save-not-modify' /></p>
						</c:when>
						<c:otherwise>
							<input name="name" value="${data.name}" type="text" class="field long_field" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
				</td>
				<th><liferay-ui:message key='version' /><span class="requiredField">*</span></th>
				<td>
					<c:choose>
						<c:when test="${empty scienceAppId}">
							<aui:input name="version" type="text" cssClass="field short_field" label="" value="" placeholder="ex) 1.0.0" >
								<aui:validator name="required"/>
								<aui:validator  name="custom"  errorMessage="<%=exceptionVersionMsg%>">
									function (val, fieldNode, ruleValue) {
										 var retbool = true;
										 var kor_check = /[1-9][0-9]*[.](0|[1-9][0-9]*)[.](0|[1-9][0-9]*)$/;
										 if (!kor_check.test(val)){
											 retbool = false;
										 }
										return retbool;
									}
								</aui:validator>
							</aui:input>
						</c:when>
						<c:otherwise>
							<input name="<portlet:namespace/>version" value="${data.version}" type="text" class="field short_field" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<th>
					<liferay-ui:message key='edison-table-list-header-app-title' /><span class="requiredField"> *</span>
				</th>
				<td colspan="3">
					<liferay-ui:input-localized name="title" xml="${data.title}" cssClass=" field too_long_field"  type="input"/>
				</td>
			</tr>
		</table>
	</aui:form>
	
	<div id="<portlet:namespace/>upgradeVersionForm2" style="width: 100%; margin: 10px 0px; display: none;">	
		
		<liferay-ui:message key='current-version' /> : ${data.version}<br/>
		<input type="number" title="Release" class="field field-control <portlet:namespace/>app-version" id="<portlet:namespace/>releaseNumber1" min="0" value="" >
		.
		<input type="number" title="Major" class="field field-control <portlet:namespace/>app-version" id="<portlet:namespace/>majorNumber1" min="0" value="" >
		.
		<input type="number" title="Minor" class="field field-control <portlet:namespace/>app-version" id="<portlet:namespace/>minorNumber1" min="0" value="" >
		<br/>
		<liferay-ui:message key='edison-science-appstore-upgrade-new-version-massage' />
		
		<br/>
		<liferay-ui:message key='edison-science-appstore-toolkit-app-copy-message' />
		
		<script>
			
		</script>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function () {
	});
	
	<%
	if(mode.equals(Constants.UPDATE)){
	%>
		$(document).ready(function () {
			$("#<portlet:namespace/>currVersion").val('${data.version}');
			changeLanguage('${data.targetLanguage}');
			
			$("#<portlet:namespace/>app-manager-add-dialog").dialog({
				autoOpen: false,
			    width: 450,
			    height: 'auto',
			    modal: true,
			    resizable: false,
			    show: {effect:'fade', speed: 800}, 
		        hide: {effect:'fade', speed: 800},
		        open: function(event, ui) {
			    	$(this).css('overflow', 'hidden');
			    	$(this).css("padding", "0px");
			    	$(this).css("width", "450px");
			    	$(this).parent().removeClass("ui-widget-content");
			    	$(this).parent().removeClass("ui-widget");
			    	$(this).removeClass("ui-widget-content");
			    	$(this).removeClass("ui-dialog-content");
			    },
			    close: function() {
		
			    }
			}).dialog("widget").find(".ui-dialog-titlebar").remove();
			
			$("#app-manager-add-dialog-close-btn").click(function() {
				$("#<portlet:namespace/>app-manager-add-dialog").dialog("close");
			});
			
			
			
			$("#<portlet:namespace/>app-owner-add-dialog").dialog({
				autoOpen: false,
			    width: 450,
			    height: 'auto',
			    modal: true,
			    resizable: false,
			    show: {effect:'fade', speed: 800}, 
		        hide: {effect:'fade', speed: 800},
		        open: function(event, ui) {
			    	$(this).css('overflow', 'hidden');
			    	$(this).css("padding", "0px");
			    	$(this).css("width", "450px");
			    	$(this).parent().removeClass("ui-widget-content");
			    	$(this).parent().removeClass("ui-widget");
			    	$(this).removeClass("ui-widget-content");
			    	$(this).removeClass("ui-dialog-content");
			    },
			    close: function() {
		
			    }
			}).dialog("widget").find(".ui-dialog-titlebar").remove();
			
			$("#app-owner-add-dialog-close-btn").click(function() {
				$("#<portlet:namespace/>app-owner-add-dialog").dialog("close");
			});
		});
	<%}%>
	
	function <portlet:namespace/>actionCall(mode){
		if(mode=='<%=Constants.ADD%>'){
			//앱제목 Validation
			$targetLanguage = $("#<portlet:namespace/>targetLanguage option:selected");
			var lanuageCode = $targetLanguage.val();
			
			if(lanuageCode=="" || lanuageCode==undefined){
				var id = "<portlet:namespace/>title_" + lanuageCode;
				
				var localeStr = "<%=localesStr%>";
				var localeArray = localeStr.split(",");
				
				for(var i=0; i< localeArray.length; i++){
					var id = "<portlet:namespace/>title_" + localeArray[i];
					var inputValue = $("input[name="+id+"]").val();
					if(inputValue == "" || inputValue == undefined){
						alert(Liferay.Language.get('edison-error-another-language-alret-message'));
						$("#<portlet:namespace/>title").focus();
						return false;
					}
				}
			}else{
				var id = "<portlet:namespace/>title_" + lanuageCode;
				var inputValue = $("input[name="+id+"]").val();
				if(inputValue == "" || inputValue == undefined){
					alert(Liferay.Language.get('edison-error-another-language-alret-message'));
					$("#<portlet:namespace/>title").focus();
					return false;
				}
			}
			
// 			<portlet:namespace/>frm.encoding = "multipart/form-data";
			submitForm(<portlet:namespace/>frm);
		}else{
			if(confirm(Liferay.Language.get('edison-appstore-delete-data-alert'))){
				<portlet:namespace/>frm.<portlet:namespace/>actionMode.value = mode;
			}else{
				return false;
			}
			submitForm(<portlet:namespace/>frm);
		}
		
		
	}
	
	function changeLanguage(val){
		var $tLanguage = $("select[id='<portlet:namespace/>targetLanguage']");
		if(val.indexOf(",")>-1){
			$tLanguage.val("");
		}else{
			$tLanguage.val(val);
		}
		
	}
	
	
	
	function <portlet:namespace/>onKeyDown(role) {
		if(role == 'owner'){
			if(event.keyCode == 13 && $("#<portlet:namespace/>userScreenName_owner").val() != ""){
				var pre = $("#<portlet:namespace/>now_userScreenName").val();
				var post = $("#<portlet:namespace/>userScreenName_owner").val();
				if( pre == post ){
					alert(Liferay.Language.get('edison-appstore-fail-owner'));
					return false;
				}
				<portlet:namespace/>getUserInfo('owner');
			}
		}if(role == 'manager'){
			if(event.keyCode == 13 && $("#<portlet:namespace/>userScreenName_manager").val() != ""){
				<portlet:namespace/>getUserInfo('manager');
			}
		}
	}
	
</script>
<aui:script use="aui-base,node-event-simulate">
	var updateLanguageFlag = function(event) {
		var target = event.target;
		var selectedValue = target.val();
		if(selectedValue!=""){
			A.one('li.palette-item[data-value="'+selectedValue+'"] > a').simulate('click');
		}
	};

	A.all('#<portlet:namespace/>targetLanguage').each(
		function(item) {
			if (item) {
				item.on('change', updateLanguageFlag);
			}
		}
	);	
</aui:script>

