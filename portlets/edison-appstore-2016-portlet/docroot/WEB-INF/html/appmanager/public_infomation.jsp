<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="org.kisti.edison.util.EdisonHttpUtil"%>


<%@ page import="org.kisti.edison.science.Exception.ScienceAppException" %>

<!-- ckeditor -->
<%@ page import="com.liferay.portal.kernel.util.StringBundler"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil"%>
<%@ page import="com.liferay.portal.model.Group"%>

<portlet:actionURL var="submitURL" copyCurrentRenderParameters="false" name="appAction" >
	<portlet:param name="clickTab" value="${clickTab}"/>
	<portlet:param name="isPort" value="${isPort}"/>
	<portlet:param name="actionType" value="publicData"/>
	
	<portlet:param name="name" value="${data.name}"/>
	<portlet:param name="version" value="${data.version}"/>
	<portlet:param name="title" value="${data.title}"/>
	
	<portlet:param name="entryId" value="${data.entryId}"/>
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
	<portlet:param name="searchValue" value="${searchValue}"/>
	<portlet:param name="searchOption" value="${searchOption}"/>
	<portlet:param name="searchStatus" value="${searchStatus}"/>
	<portlet:param name="p_curPage" 	value="${p_curPage}" />
	<portlet:param name="redirectURL" 	value="${redirectURL}" />
</portlet:actionURL>

<%
	Locale[] locales = LanguageUtil.getAvailableLocales();
	String localesStr = "";

	String exceptionNameMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-app-validation-name-exception-msg");
	String exceptionVersionMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-app-validation-version-exception-msg");
	String textAreaPlaceholder = LanguageUtil.get(themeDisplay.getLocale(),"Enter-one-value-per-line");
	
	String mode = GetterUtil.get(request.getAttribute("mode"), "");
	boolean isPort = GetterUtil.getBoolean(request.getAttribute("isPort"), false);
	
	String assetRedirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
	String assetRedirectName = LanguageUtil.get(themeDisplay.getLocale(),"edison-appstore-myapp-list");
	
%>
<!-- ckeditor -->
<%
	Map<String, String> fileBrowserParamsMap = (Map<String, String>)request.getAttribute("liferay-ui:input-editor:fileBrowserParams");
	
	String fileBrowserParams = marshallParams(fileBrowserParamsMap);

	StringBundler sb = new StringBundler(8);
	String portletId = portletDisplay.getRootPortletId();
	String mainPath = themeDisplay.getPathMain();

	String doAsUserId = themeDisplay.getDoAsUserId();
	long doAsGroupId = themeDisplay.getDoAsGroupId();
		Locale siteLocale = themeDisplay.getLocale();
	String doasLocale = siteLocale.getLanguage();

	if (doAsGroupId == 0) {
		doAsGroupId = (Long)themeDisplay.getSiteGroupId();
	}
	
	Group group = GroupLocalServiceUtil.getGroup(doAsGroupId);
	String currentFolder = "/"+doAsGroupId+" - "+"edison"+"/";
	
	sb.append(mainPath);
	sb.append("/portal/fckeditor?p_p_id=");
	sb.append(HttpUtil.encodeURL(portletId));
	sb.append("&doAsUserId=");
	sb.append(HttpUtil.encodeURL(doAsUserId));
	sb.append("&doAsGroupId=");
	sb.append(HttpUtil.encodeURL(String.valueOf(doAsGroupId)));
	sb.append(fileBrowserParams);

	String connectorURL = HttpUtil.encodeURL(sb.toString());
%>
<!-- ------------- -->
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
	
	.aui .text_field{
		width: 100%;
		resize: none;
		height:auto;
		margin-bottom: 10px;
	}
	
	.aui .swrightcont .alert{
		margin-top: 10px;
	}
</style>
<!-- ckeditor  -->
<%!
public String marshallParams(Map<String, String> params) {
	StringBundler sb = new StringBundler();

	if (params != null) {
		for (Map.Entry<String, String> configParam : params.entrySet()) {
				sb.append(StringPool.AMPERSAND);
				sb.append(configParam.getKey());
				sb.append(StringPool.EQUAL);
				sb.append(HttpUtil.encodeURL(configParam.getValue()));
		}
	}

	return sb.toString();
}
%>
<script type="text/javascript" src="${contextPath}/editor/ckeditor/ckeditor.js" charset="utf-8"></script>

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
			<input class=" button02_2" onclick="<portlet:namespace/>goList();" value="<liferay-ui:message key='edison-button-board-list'/>" type="button">
			
			<c:if test="${data.status gt 1901003}">
				<input class=" button02_1" onclick="<portlet:namespace/>copyScienceApp();" value="<liferay-ui:message key='edison-appstore-copy'/>" type="button">
			</c:if>
			
			<c:if test="${appStatusButtonView}">
				<c:if test="${data.status eq '1901001'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901002');" value="<liferay-ui:message key='edison-appstore-status-request'/>" type="button">
				</c:if>
				<c:if test="${data.status eq '1901002' && isAdmin}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901001');" value="<liferay-ui:message key='edison-appstore-status-denial'/>" type="button">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901003');" value="<liferay-ui:message key='edison-appstore-status-private'/>" type="button">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901004');" value="<liferay-ui:message key='edison-appstore-status-service'/>" type="button">
				</c:if>
				<c:if test="${data.status eq '1901003'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901004');" value="<liferay-ui:message key='edison-appstore-status-service'/>" type="button">
				</c:if>
				
				<c:if test="${data.status eq '1901004'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901003');" value="<liferay-ui:message key='edison-appstore-status-private'/>" type="button">
				</c:if>
			</c:if>
			
			<input class=" button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.ADD%>');return false;" value="<liferay-ui:message key='edison-button-save'/>" type="button">
			<c:if test="${!empty scienceAppId && ownerThan }">
				<input class=" button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.DELETE%>');return false;" value="<liferay-ui:message key='delete'/>" type="button">
				<c:if test="${workBenchPlid ne 0 && appTestButtonView}">
					<input class=" button02_1" onclick="<portlet:namespace/>appTest();return false;" value="<liferay-ui:message key='edison-table-list-header-run'/>" type="button">
				</c:if>
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
				<th>
					<liferay-ui:message key='descriptive'/>
					<span class="requiredField"> *</span>
					<liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/>
				</th>
				<td colspan="3">
					<aui:select name="descriptionLanuage" label="" onChange="changeDescriptionLocale(this.value);">
						
					</aui:select>
					<%
						for(Locale aLocale : locales){
							String languageId = LocaleUtil.toLanguageId(aLocale);
							String descriptionKey = "description_"+languageId;
							
							if(localesStr.equals("")){
								localesStr += languageId;
							}else{
								localesStr += ","+languageId;
							}
				    %>
				    	
				    	<c:set var="descriptionKey" value="<%=descriptionKey%>"></c:set>
				    	<div id="<portlet:namespace/>descriptionDiv_<%=languageId%>" style="display: none;">
				    		<textarea id="<portlet:namespace/>description_<%=languageId%>" name="<portlet:namespace/>description_<%=languageId%>" style="width:100%;height:300px;">
				    			${data.description[descriptionKey] }
				    		</textarea>
				    	</div>
				    <%
						}
				    %>
				</td>
			</tr>
			<tr>
				<th rowspan="<%=locales.length+1%>"><liferay-ui:message key='edison-table-list-header-manual' /><span class="requiredField"> *</span></th>
			</tr>
				<%for(Locale aLocale : locales){
					String languageId = LocaleUtil.toLanguageId(aLocale);
					String languageNm =aLocale.getDisplayName(themeDisplay.getLocale());
					String manualId = "manualId_"+languageId;
					String manualTitle = "manualTitle_"+languageId;
				%>
					<tr>
						<td colspan="3">
							<%=languageNm%>&nbsp;&nbsp;
							<input type="file" id="<portlet:namespace/>app_manual<%=languageId%>" name="<portlet:namespace/>app_manual<%=languageId %>">
							<c:set value="<%=manualId%>" var="manualId"/>
							<c:set value="<%=manualTitle%>" var="manualTitle"/>
							
							<c:if test="${data[manualTitle] ne null}">
								<div class="down_date appManualClass_<%=languageId%>" id="<portlet:namespace/>appManual_<%=languageId%>" onclick="<portlet:namespace/>fileDownload('${data[manualId]}')" style="cursor: pointer;display: inline-block;">
									${data[manualTitle]}
								</div>
								<img src='${contextPath}/images/icon_dustbin.png' class="appManualClass_<%=languageId%>" width=13 height=14 style="cursor:pointer"
								onClick="<portlet:namespace/>deleteFile('${data[manualId]}','appManual','appManualClass_<%=languageId%>','<%=languageId%>');"/>
							</c:if>
						</td>
					</tr>
				<%} %>
			<tr>
				<th><liferay-ui:message key='icon' /><span class="requiredField"> *</span></th>
				<td colspan="3">
					<input type="file" id="<portlet:namespace/>app_icon" name="<portlet:namespace/>app_icon">
					<c:if test="${data.iconTitle ne null }">
						<div class="down_date appIconClass"  onclick="<portlet:namespace/>fileDownload('${data.iconId }')" style="cursor: pointer;display: inline-block;">
							${data.iconTitle}
						</div>
						<img src='${contextPath}/images/icon_dustbin.png' class="appIconClass" width='13' height='14' style="cursor:pointer" onClick="<portlet:namespace/>deleteFile('${data.iconId}','appIcon','appIconClass');" />
					</c:if>
					<div id="clear"></div>
				</td>
			</tr>
			<tr>
				<th><liferay-ui:message key='developer' /><span class="requiredField"> *</span></th>
				<td colspan="3">
					<liferay-ui:input-localized name="developers" xml="${data.developersTextArea}" type="textarea" cssClass="text_field" placeholder="<%=textAreaPlaceholder%>"/>
				</td>
			</tr>
			<tr>
				<th><liferay-ui:message key='edison-appstore-license' /></th>
				<td colspan="3">
					<aui:input name="license" type="text" value="${data.license}" label="" cssClass="long_field" size="40"/>
				</td>
			</tr>
		</table>
	</aui:form>
	
	<liferay-portlet:runtime portletName="edisonrelateasset_WAR_edisondefault2016portlet" defaultPreferences="" 
		queryString="&entryId=${data.entryId}&isMgrBtn=true&isVirTitle=true&redirectURL=<%=assetRedirectURL%>&redirectName=<%=assetRedirectName%>"/>
	


<script type="text/javascript">
	$(document).ready(function () {
		<portlet:namespace/>setCKeditor();
		
	});
	
	function <portlet:namespace/>actionCall(mode){
		if(mode=='<%=Constants.ADD%>'){
			<portlet:namespace/>frm.encoding = "multipart/form-data";

			var targetLanguage = '${data.targetLanguage}';
			if(targetLanguage != ""){
				if(targetLanguage.indexOf(",") == -1){//1개
					//description
					/* var description = $("#<portlet:namespace/>description_"+targetLanguage).val(); */
					var description = CKEDITOR.instances["<portlet:namespace/>description_"+targetLanguage].getData();
						description = $.trim(description.replace(/&nbsp;/g, ''));
					if(description == ""){
						alert(Liferay.Language.get("edison-appstore-solver-description-exception-alert") + "( "+Liferay.Language.get(targetLanguage)+" )");
						return false;
					}
					
					//manual
					var manualAleadyExist = $("#<portlet:namespace/>appManual_"+targetLanguage).is(":visible");
					if(!manualAleadyExist){
						var newManual = $("#<portlet:namespace/>app_manual" + targetLanguage).val();
						if(newManual == ""){
							alert(Liferay.Language.get("edison-appstore-solver-manual-exception-alert") + "( "+Liferay.Language.get(targetLanguage)+" )");
							return false;
						}
					}
					//developer
					var developer = $("#<portlet:namespace/>developers_"+targetLanguage).val();
					if(developer == ""){
						alert(Liferay.Language.get("edison-appstore-solver-developer-exception-alert")+ "( "+Liferay.Language.get(targetLanguage)+" )");
						return false;
					}
				}else{ //full
					var languageArray = targetLanguage.split(",");
					for(var i=0;i<languageArray.length;i++){
						//description
						/* var description = $("#<portlet:namespace/>description_"+languageArray[i]).val(); */
						
						var description = CKEDITOR.instances["<portlet:namespace/>description_"+languageArray[i]].getData();
						description = $.trim(description.replace(/&nbsp;/g, ''));
			
// 						console.log(description)
						if(description == ""){
							alert(Liferay.Language.get("edison-appstore-solver-description-exception-alert") + "( "+Liferay.Language.get(languageArray[i]) +" )");
							return false;
						}
						
						//manual
						var manualAleadyExist = $("#<portlet:namespace/>appManual_"+languageArray[i]).is(":visible");
						if(!manualAleadyExist){
							var newManual = $("#<portlet:namespace/>app_manual" + languageArray[i]).val();
							if(newManual == ""){
								alert(Liferay.Language.get("edison-appstore-solver-manual-exception-alert")  + "( "+Liferay.Language.get(languageArray[i]) +" )");
								return false;
							}
						}
						
						//developer
						var developer = $("#<portlet:namespace/>developers_"+languageArray[i]).val();
						if(developer == ""){
							alert(Liferay.Language.get("edison-appstore-solver-developer-exception-alert")  + "( "+Liferay.Language.get(languageArray[i]) +" )");
							return false;
						}
						
					}
				}
			}
			
			//icon Check
			var appIconFileName = '${data.iconTitle}';
			if(appIconFileName == ""){
				if($("#<portlet:namespace/>app_icon").val() == ""){
					alert(Liferay.Language.get("edison-simulation-project-icon-exception-msg"));
					return false;
				}
			}
			
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
	
	
	function <portlet:namespace/>setCKeditor(){
		var fileBrowserConectorURL = "<%=connectorURL%>";
		fileBrowserConectorURL = fileBrowserConectorURL +"&currentFolder=${currentFolder}";
		var ckEditorLanguage = "<%=doasLocale%>";
		CKEDITOR.config.autoParagraph = false;
		CKEDITOR.config.tabSpaces = 0;
		var localeStr = "<%=localesStr%>";
		var localeArray = localeStr.split(",");
		$descriptionSelect = $("#<portlet:namespace/>descriptionLanuage");
		
		for(var i=0; i< localeArray.length; i++){
			var locale = localeArray[i];
			CKEDITOR.replace( '<portlet:namespace/>description_'+locale, {
				filebrowserImageBrowseUrl: "/edison-appstore-2016-portlet/editor/ckeditor/filemanger/browser.html?Connector="+fileBrowserConectorURL,
				language: ckEditorLanguage,
			    filebrowserUploadUrl: null,
			    toolbar : [
			        		['Styles', 'FontSize', '-', 'TextColor', 'BGColor'],
			         		['Bold', 'Italic', 'Underline', 'Strike'],
			         		['Subscript', 'Superscript'],
			         		'/',
			         		['Undo', 'Redo', '-', 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'SelectAll', 'RemoveFormat'],
			         		['Find', 'Replace', 'SpellChecker', 'Scayt'],
			         		['Outdent','Indent','Blockquote'],
			         		['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
			         		'/',
			         		['Source'],
			         		['Link', 'Unlink', 'Anchor'],
			         		['Image', 'Flash',  'Table', '-', 'Smiley', 'SpecialChar', 'LiferayPageBreak'],
			         		['A11YBtn']
			         	]
			});
			
			var option = $("<option/>").val(locale).text(Liferay.Language.get(locale)).appendTo($descriptionSelect);
			
			if(locale==Liferay.ThemeDisplay.getLanguageId()){
				$("#<portlet:namespace/>descriptionDiv_"+locale).show();
				option.prop("selected",true);
			}
			
			
		}
	}
	function changeDescriptionLocale(selectLocaleId){
		$("div[id*=<portlet:namespace/>descriptionDiv_]").hide();
		$("#<portlet:namespace/>descriptionDiv_"+selectLocaleId).show();
	}
	
</script>