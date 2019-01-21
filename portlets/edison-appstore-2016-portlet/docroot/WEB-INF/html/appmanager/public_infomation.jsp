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

<liferay-portlet:resourceURL var="deletePaperFileInDLURL" id="deletePaperFileInDL" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:resourceURL var="appManagerListURL" id="appManagerList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteAppAuthURL" id="deleteAppAuth" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="appUserInfoURL" id="appUserInfo" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="appManagerAddURL" id="appManagerAdd" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="appOwnerUpdateURL" id="appOwnerUpdate" copyCurrentRenderParameters="false" />

<link rel="stylesheet" href="${contextPath}/css/toastr.min.css">
<script src="${contextPath}/js/toastr.min.js"></script>

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
	
	#<portlet:namespace/>paperFileTd .evt-btn, #<portlet:namespace/>paperLinkTd .evt-btn{
		cursor: pointer;
	}
	
	.paper-field.<portlet:namespace/>paper-file-input, .paper-field.<portlet:namespace/>paper-link-input{
		width: 90% !important;
		margin-right: 5%;
		float: left;
	}
	
	.icon-plus.evt-btn, .icon-minus.evt-btn{
		margin-left: 1%;
	}
	
	.<portlet:namespace/>appPaperFileIcon{
		margin-right: 1%;
	}
	
	.<portlet:namespace/>appPaperLinkClass{
		cursor: pointer;
		margin-right: 5%;
		width: 90%;
	}
	
	.<portlet:namespace/>appPaperLinkClass input:HOVER{
		text-decoration: underline;
	}
	
	.md-preview{
		width: 100% !important;
		padding: 10px;
	}
	
	.md-editor textarea{
		background-color: #fff !important;
	}
</style>

	<!-- 2018.10.22 _ Markdown CSS -->
	<link media="all" rel="stylesheet" href="${contextPath}/css/bootstrap-markdown.min.css" />
	<link media="all" rel="stylesheet" href="${contextPath}/css/markdown-style.css" />
	
	<!-- 2018.10.22 _ Markdown JS -->
	<script src="${contextPath}/js/marked.js"></script>
	<script src="${contextPath}/js/bootstrap-markdown.js"></script>
	<script src="${contextPath}/js/markdown-jquery.filedrop.js"></script>
	<script src="${contextPath}/js/markdown-script.js"></script>

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
				<th><liferay-ui:message key='edison-content-service-language' /><span class="requiredField"> *</span></th>
				<td colspan="3">
				
					<aui:select name="targetLanguage" label="" >
						<option value=""><liferay-ui:message key='full' /></option>
						<%
						String siteDefaultLanuageId = LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale());
						
						for(Locale aLocale : locales){
							String languageId = LocaleUtil.toLanguageId(aLocale);
							String languageNm = aLocale.getDisplayName(themeDisplay.getLocale());
						%>
							<aui:option label="<%=languageNm%>" value="<%=languageId%>" selected="<%=languageId.equals(siteDefaultLanuageId) %>"/>
						<%} %>
					</aui:select>
				</td>
			</tr>
			
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
							String descriptionMDEKey = "descriptionMDE_"+languageId;
							
							if(localesStr.equals("")){
								localesStr += languageId;
							}else{
								localesStr += ","+languageId;
							}
					%>
						
						<%-- <c:set var="descriptionKey" value="<%=descriptionKey%>"></c:set>
						<div id="<portlet:namespace/>descriptionDiv_<%=languageId%>" style="display: block;">
							<textarea id="<portlet:namespace/>descriptionCkEditor_<%=languageId%>" style="width:100%;height:300px;">
								${data.description[descriptionKey] }
							</textarea>
						</div> --%>
						
						<c:set var="descriptionMDEKey" value="<%=descriptionMDEKey%>"></c:set>
						<div id="<portlet:namespace/>descriptionMDEDiv_<%=languageId%>" style="margin-top: 10px; display: none;">
							<textarea id="<portlet:namespace/>descriptionMDE_<%=languageId%>" class="comment-md_<%=languageId%>" name="<portlet:namespace/>descriptionMDE_<%=languageId%>" placeholder="Input Description...">${data.descriptionMDE[descriptionMDEKey] }</textarea>
						</div>
						
						<!-- Form Data For Preview -->
						<input type="hidden" id="<portlet:namespace/>description_<%=languageId%>" name="<portlet:namespace/>description_<%=languageId%>" value="" />
						
						<!-- Preview -->
						<div style="display: none;">
							<div id="<portlet:namespace/>descriptionMDEPreviewDiv_<%=languageId%>" class="comment-md-preview-container_<%=languageId%>" style="display: none;">
								<div id="<portlet:namespace/>descriptionMDEPreview_<%=languageId%>" class="well well-sm well-light md-preview margin-top-10 comment-md-preview_<%=languageId%>">
								</div>
							</div>
						</div>
					<%
						}
					%>
					
				</td>
			</tr>
			
			<tr>
				<th rowspan="${fn:length(parentCategoryList)+1}">
					<liferay-ui:message key='edison-science-appstore-view-tab-category' /><span class="requiredField"> *</span>
					
				</th>
				
			</tr>
			<c:forEach items="${parentCategoryList}" var="parentCategory">
				<tr>
					<td id="<portlet:namespace/>${parentCategory.value}_parentTd" colspan="3">
						<span id="<portlet:namespace/>${parentCategory.value}_parent_open" style="cursor: pointer;" onclick="<portlet:namespace/>openRootCategory('OPEN','${parentCategory.value}');">${parentCategory.name}(OPEN)</span>
						<span id="<portlet:namespace/>${parentCategory.value}_parent_close" style="cursor: pointer;display: none;" onclick="<portlet:namespace/>openRootCategory('CLOSE','${parentCategory.value}');">${parentCategory.name}(CLOSE)</span>
					</td>
					<td colspan="2" id="<portlet:namespace/>${parentCategory.value}_childrenTd" style="display: none;">
						<c:set value="${parentCategory.value}" var="parentCategoryValue"/>
						<c:forEach items="${childrenCategoryGroupMap[parentCategoryValue]}" var="childrenCategory">
							<c:set value="${parentCategory.value}_${childrenCategory.value}_Children_Category" var="childrenCategoryName"/>
							<aui:input name="childrenCategory" id="${childrenCategoryName}" label="${childrenCategory.name}" value="${parentCategory.value}_${childrenCategory.value}" type="checkbox"/>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
			
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
			
			<!-- App Owner -->
			<tr>
				<th><liferay-ui:message key='edison-virtuallab-owner' /></th>
				<td colspan="3">
					<div class="input-group">
					<c:choose>
						<c:when test="${mode == 'add'}">
							<aui:input name="now_userScreenName" type="text" value="${userScreenName}" label="" cssClass="field short_field" readonly="readonly"/>
						</c:when>
						<c:otherwise>
							<input id="<portlet:namespace/>now_userScreenName" name="<portlet:namespace/>now_userScreenName" type="text" value="${data.userScreenName}" class="field short_field" readonly="readonly"/>
							
							<c:if test="${mode eq 'update' && ownerThan }">
								<input id="<portlet:namespace/>userScreenName_owner" name="<portlet:namespace/>userScreenName_owner" type="text" maxlength="15" placeholder="<liferay-ui:message key='edison-table-list-header-userid' />" onkeypress="<portlet:namespace/>onKeyDown('owner');" class="field long_field"/>
								<span class="input-group-btn">
									<button class="btn btn-default" type="button" onClick="<portlet:namespace/>getUserInfo('owner');"><span class="icon-user"> <liferay-ui:message key='edison-appstore-solver-transfer' /></span></button>
								</span>
							</c:if>
						</c:otherwise>
					</c:choose>
					</div>
				</td>
			</tr>
		</table>
	</aui:form>
	
	<div class="h20"></div>
	
	<c:if test="${!empty scienceAppId && ownerThan }">
		<form id="userSearchManagerForm" name="userSearchManagerForm" method="post" onsubmit="return false;">
			<div class="table-responsive panel edison-panel">
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left">
						<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
						<liferay-ui:message key='edison-appstore-solver-manager' />
					</h3>
					
					<div class="input-group">
						<input id="<portlet:namespace/>userScreenName_manager" name="<portlet:namespace/>userScreenName_manager" type="text" maxlength="15" class="form-control" placeholder="<liferay-ui:message key='edison-table-list-header-userid'/>" onKeydown="if(event.keyCode ==13)<portlet:namespace/>getUserInfo('manager');">
						<span class="input-group-btn">
							<input id="<portlet:namespace/>type" name="<portlet:namespace/>type" type="hidden" value="manager"  />
							<input id="<portlet:namespace/>scienceAppId" name="<portlet:namespace/>scienceAppId" type="hidden" value="${scienceAppId}" />
							<button class="btn btn-default" type="button" onclick="<portlet:namespace/>getUserInfo('manager');"><i class="icon-search"></i></button>
						</span>
					</div>
				</div>
				<table class = "table table-bordered table-hover edison-table">
					<thead>
						<tr>
							<th width="10%"><liferay-ui:message key='edison-table-list-header-index' /></th>
							<th width="20%"><liferay-ui:message key='edison-table-list-header-userid' /></th>
							<th width="20%"><liferay-ui:message key='edison-table-list-header-usernm' /></th>
							<th width="20%"><liferay-ui:message key='edison-table-list-header-email' /></th>
							<th width=15%""><liferay-ui:message key='edison-table-list-header-date' /></th>
							<th width="15%"><liferay-ui:message key='edison-button-board-delete' /></th>
						</tr>
					</thead>
					<tbody id="<portlet:namespace/>appManagerListBody">
					</tbody>
				</table>
			</div>
		</form>
	</c:if>
</div>

<div id="<portlet:namespace/>app-owner-add-dialog" title="<liferay-ui:message key='edison-appstore-solver-owner-change' />" class="newWindow" style="display:none; background-color:white; padding:0px;">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle"><liferay-ui:message key="edison-appstore-solver-owner-change"/></div>
		</div>
		<div class="newWclose" style="cursor: pointer;">
			<img id="app-owner-add-dialog-close-btn" name="app-owner-add-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="25" height="25" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	<div style="padding: 30px;" class="newWcont01">
		<form id="ownerUpdateForm" name="ownerUpdateForm" method="post" action="<%=appOwnerUpdateURL%>" > 
			<div class="table1_list" style="width:85%; padding:15px; margin:0 auto;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<colgroup>
						<col width="30%" />
						<col width="70%" />
					</colgroup>
					<tbody>
						<tr>
							<td><liferay-ui:message key='edison-table-list-header-userid' /></td>
							<td id="ownerId"></td>
						</tr>
						<tr>
							<td><liferay-ui:message key='edison-table-list-header-usernm' /></td>
							<td id="ownerFullName"></td>
						</tr>
						<tr>
							<td><liferay-ui:message key='edison-table-list-header-email' /></td>
							<td id="ownerEmail"></td>
						</tr>
						<tr>
							<td colspan="2"  style="text-align: center; color:#f03030;"><liferay-ui:message key='edison-appstore-solver-owner-register-confirm' /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<input id="<portlet:namespace/>ownerUserId" name="<portlet:namespace/>ownerUserId" type="hidden">
			<input id="<portlet:namespace/>pre_userScreenName" name="<portlet:namespace/>pre_userScreenName" type="hidden">
			<input id="<portlet:namespace/>scienceAppId" name="<portlet:namespace/>scienceAppId" type="hidden" value="${scienceAppId}">
			<div style="text-align:center;">
				<button class="btn btn-success" type="button" onclick="<portlet:namespace/>updateAppOwner();"><span class="icon-ok"> <liferay-ui:message key='edison-button-register' /></span></button>
			</div>
		</form>
	</div>
</div>

<div id="<portlet:namespace/>app-manager-add-dialog" title="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-manager-register' />" class="newWindow" style="display:none; background-color:white; padding:0px;">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-manager-register' /></div>
		</div>
		<div class="newWclose" style="cursor: pointer;">
			<img id="app-manager-add-dialog-close-btn" name="app-manager-add-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="25" height="25" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	<div style="padding: 30px;" class="newWcont01">
		<form id="managerAddForm" name="managerAddForm" method="post" action="<%=appManagerAddURL%>" > 
			<div class="table1_list" style="width:85%; padding:15px; margin:0 auto;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<colgroup>
						<col width="30%" />
						<col width="70%" />
					</colgroup>
					<tbody>
						<tr>
							<td><liferay-ui:message key='edison-table-list-header-userid' /></td>
							<td id="managerId"></td>
						</tr>
						<tr>
							<td><liferay-ui:message key='edison-table-list-header-usernm' /></td>
							<td id="managerFullName"></td>
						</tr>
						<tr>
							<td><liferay-ui:message key='edison-table-list-header-email' /></td>
							<td id="managerEmail"></td>
						</tr>
						<tr>
							<td colspan="2"  style="text-align: center; color:#f03030;"><liferay-ui:message key='edison-appstore-solver-manager-register-confirm' /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<input id="<portlet:namespace/>managerUserId" name="<portlet:namespace/>managerUserId" type="hidden">
			<input id="<portlet:namespace/>scienceAppId" name="<portlet:namespace/>scienceAppId" type="hidden" value="${scienceAppId}">
			<div style="text-align:center;">
				<button class="btn btn-success" type="button" onclick="<portlet:namespace/>addAppManager();"><span class="icon-ok"> <liferay-ui:message key='edison-button-register' /></span></button>
			</div>
		</form>
	</div>
</div>

	<liferay-portlet:runtime portletName="edisonrelateasset_WAR_edisondefault2016portlet" defaultPreferences="" 
		queryString="&entryId=${data.entryId}&isMgrBtn=true&isVirTitle=true&redirectURL=<%=assetRedirectURL%>&redirectName=<%=assetRedirectName%>"/>

<div id="<portlet:namespace/>helpMarkdown" style="display: none;">
	<table style="width: 100%;">
		<colgroup>
			<col width="50%;">
			<col width="50%;">
		</colgroup>
		<thead>
			<tr>
				<th style="text-align:left;">Markdown Input</th>
				<th style="text-align:left;">Markdown Output</th>
			</tr>
		</thead>
		<tbody>
			<tr class="<portlet:namespace/>help-md-content Headers" style="display: none;">
				<td>
					# This is an H1<br/>
					## This is an H2<br/>
					### This is an H3
				</td>
				<td>
					<h1>This is an H1</h1><br/>
					<h2>This is an H2</h2><br/>
					<h3>This is an H3</h3>
				</td>
			</tr>
			<tr class="<portlet:namespace/>help-md-content Links" style="display: none;">
				<td>
					[EDISON](https://www.edison.re.kr/ "site description")
					<br/>
					https://www.edison.re.kr/
				</td>
				<td>
					<a href="https://www.edison.re.kr" title="site description">EDISON</a>
					<br/>
					<a href="https://www.edison.re.kr">https://www.edison.re.kr</a>
				</td>
			</tr>
			<tr class="<portlet:namespace/>help-md-content Images" style="display: none;">
				<td>
					![title](https://www.edison.re.kr/image/layout_set_logo?img_id=321849&t=1547995013998"image description")
				</td>
				<td>
					<img alt="title" src="https://www.edison.re.kr/image/layout_set_logo?img_id=321849&t=1547995013998" title="image description">
				</td>
			</tr>
			<tr class="<portlet:namespace/>help-md-content Tables" style="display: none;">
				<td>
					| Project Type | Pull Request | Issue |<br/>
					| ----------------- | ----------------- | ------- |<br/>
					|  Git  |  Yes  |  Yes  |<br/>
					|  SVN  |  No  |  Yes  |
				</td>
				<td>
					<table>
						<tr>
							<th>Project Type</th>
							<th>Pull Request</th>
							<th>Issue</th>
						</tr>
						<tr>
							<td>Git</td>
							<td>Yes</td>
							<td>Yes</td>
						</tr>
						<tr>
							<td>SVN</td>
							<td>No</td>
							<td>Yes</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr class="<portlet:namespace/>help-md-content Styling" style="display: none;">
				<td>
					*This is an italic*<br/>
					**This is an bold**<br/>
					~~This is an strike~~
				</td>
				<td>
					<em>This is an italic</em><br/>
					<strong>This is an bold</strong><br/>
					<del>This is an strike</del>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	
	var toastr;
	
	toastr.options = {
		"closeButton": true,
		"debug": false,
		"newestOnTop": true,
		"progressBar": false,
		"positionClass": "toast-designer-pos",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "1000",
		"timeOut": "5000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "slideDown",
		"hideMethod": "slideUp"
	};
	
	$(document).ready(function () {
		
		<portlet:namespace/>appManagerList();
		
		/* App Owner Dialog */
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
		
		/* App Manager Dialog */
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
		
		/* set Category */
		changeCategory('${data.parentCategory}','${data.childrenCategory}');
		
		<portlet:namespace/>getScienceAppVersion();
		<portlet:namespace/>setLanguageSelectList();
		/* <portlet:namespace/>setCKeditor(); */
		
	});
	
	function <portlet:namespace/>actionCall(mode){
		if(mode=='<%=Constants.ADD%>'){
			<portlet:namespace/>frm.encoding = "multipart/form-data";

			var targetLanguage = '${data.targetLanguage}';
			if(targetLanguage != ""){
				if(targetLanguage.indexOf(",") == -1){//1개
					//description
					/* var description = $("#<portlet:namespace/>description_"+targetLanguage).val();
					var description = CKEDITOR.instances["<portlet:namespace/>description_"+targetLanguage].getData(); */
					
					/* MarkDown */
					var description = $("#<portlet:namespace/>descriptionMDE_"+targetLanguage).val();
					var descriptionHtml = $("#<portlet:namespace/>descriptionMDEPreview_"+targetLanguage).html();
					description = $.trim(description.replace(/&nbsp;/g, ''));
					
					if(description == ""){
						$("#<portlet:namespace/>description_"+targetLanguage).val("");
						alert(Liferay.Language.get("edison-appstore-solver-description-exception-alert") + "( "+Liferay.Language.get(targetLanguage)+" )");
						return false;
					} else {
						$("#<portlet:namespace/>description_"+targetLanguage).val(descriptionHtml);
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
						/* var description = $("#<portlet:namespace/>description_"+languageArray[i]).val();
						var description = CKEDITOR.instances["<portlet:namespace/>description_"+languageArray[i]].getData(); */
						
						/* MarkDown */
						var description = $("#<portlet:namespace/>descriptionMDE_"+languageArray[i]).val();
						var descriptionHtml = $("#<portlet:namespace/>descriptionMDEPreview_"+languageArray[i]).html();
						description = $.trim(description.replace(/&nbsp;/g, ''));
						
						if(description == ""){
							$("#<portlet:namespace/>description_"+languageArray[i]).val("");
							alert(Liferay.Language.get("edison-appstore-solver-description-exception-alert") + "( "+Liferay.Language.get(languageArray[i]) +" )");
							return false;
						} else {
							$("#<portlet:namespace/>description_"+languageArray[i]).val(descriptionHtml);
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
			
			// scienceAppPaper URL validation Check
			if(!<portlet:namespace/>scienceAppPaperValidationCheck()){
				return;
			}
			
			// scienceAppCategory Check
			if( $(":checkbox[name*='childrenCategoryCheckbox']:checked").length==0 ){
				alert(Liferay.Language.get('edison-science-appstore-category-error'));
				return false;
			}
			
			$("input[name=<portlet:namespace/>childrenCategory]").prop("disabled",true);
			
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
	
	var currDescriptionLanguage = "";
	function <portlet:namespace/>setLanguageSelectList(){
		var localeStr = "<%=localesStr%>";
		var localeArray = localeStr.split(",");
		$descriptionSelect = $("#<portlet:namespace/>descriptionLanuage");
		
		for(var i=0; i< localeArray.length; i++){
			var locale = localeArray[i];
			var option = $("<option/>").val(locale).text(Liferay.Language.get(locale)).appendTo($descriptionSelect);
			
			if(locale==Liferay.ThemeDisplay.getLanguageId()){
				/* $("#<portlet:namespace/>descriptionDiv_"+locale).show(); */
				$("#<portlet:namespace/>descriptionMDEDiv_"+locale).show();
				option.prop("selected",true);
				currDescriptionLanguage = locale;
			}
			
			
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
			CKEDITOR.replace( '<portlet:namespace/>descriptionCkEditor_'+locale, {
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
			
			/* var option = $("<option/>").val(locale).text(Liferay.Language.get(locale)).appendTo($descriptionSelect);
			
			if(locale==Liferay.ThemeDisplay.getLanguageId()){
				$("#<portlet:namespace/>descriptionDiv_"+locale).show();
				option.prop("selected",true);
			} */
			
			
		}
	}
	
	function changeDescriptionLocale(selectLocaleId){
		/* $("div[id*=<portlet:namespace/>descriptionDiv_]").hide();
		$("#<portlet:namespace/>descriptionDiv_"+selectLocaleId).show(); */
		$("div[id*=<portlet:namespace/>descriptionMDEDiv_]").hide();
		$("#<portlet:namespace/>descriptionMDEDiv_"+selectLocaleId).show();
		currDescriptionLanguage = selectLocaleId;
	}
	
	function <portlet:namespace/>openScienceAppFilebrowser(){
		var popupWidth = 1350; 
		var popupHeight = 700;
		var screenWidth = parent.screen.width;
		var screenHeight = parent.screen.height;
		var popupLeft = (screenWidth-popupWidth)/2;
		var popupTop = (screenHeight-popupHeight)/4;
		
		var fileBrowserConectorURL = "<%=connectorURL%>";
		fileBrowserConectorURL = fileBrowserConectorURL +"&currentFolder=${currentFolder}";
		var filebrowserImageBrowseUrl = "/edison-appstore-2016-portlet/editor/ckeditor/filemanger/browser.html?Connector="+fileBrowserConectorURL;
		/* window.open(filebrowserImageBrowseUrl, '_blank', 'width='+popupWidth+', height='+popupHeight+', left='+popupLeft+', top='+popupTop); */
		window.open(filebrowserImageBrowseUrl, '_blank', 'width=' + popupWidth + ', height=' + popupHeight + ', left=' + popupLeft + ', top=' + popupTop);
	}
	
	function <portlet:namespace/>getScienceAppFilePath(filePath){
		var imgExtention = /\.(jpg|jpeg|png|gif|bmp|tiff)/i;
		
		if(filePath != null && filePath != ''){
			if(!imgExtention.test(filePath.toLowerCase())){
				alert("This is not an image file.");
			} else {
				var markdownImgTag = '![Image Description]('+filePath+' "title")';
				
				if(currDescriptionLanguage != null && currDescriptionLanguage != ''){
					var descriptionMDE_Text= $("#<portlet:namespace/>descriptionMDE_"+currDescriptionLanguage);
					descriptionMDE_Text.val(descriptionMDE_Text.val() + "\n\n" + markdownImgTag);
				}
			}
		}
	}
	
	/* 논문 파일 또는 링크 입력 항목 추가 */
	function <portlet:namespace/>addPaperField(paperType){
		
		var paperTd = null;
		var inputType = '';
		var inputName = '';
		var placeHolder = '';
		var paperDivSeq = $(".<portlet:namespace/>paper-" + paperType).length;
		
		if(paperType == 'file'){
			paperTd = $("#<portlet:namespace/>paperFileTd");
			inputType = 'file';
			inputName = '<portlet:namespace/>paperFile_'+paperDivSeq;
		} else if(paperType == 'link'){
			paperTd = $("#<portlet:namespace/>paperLinkTd");
			inputType = 'text';
			inputName = '<portlet:namespace/>paperLink';
			placeHolder = "http(s)://논문 링크 URL 형식으로 입력해주세요.";
		}
		
		
		var paperDiv = $("<div/>").addClass("<portlet:namespace/>paper-" + paperType)
								  .attr("paper-" + paperType + "-field-seq", paperDivSeq)
								  .css("margin-top", "1%");
		
		var paperInput = $("<input/>").addClass("field paper-field <portlet:namespace/>paper-" + paperType + "-input")
									  .attr("type", inputType)
									  .attr("name", inputName)
									  .attr("placeholder", placeHolder);
		
		var paperPlusBtn = $("<i/>").addClass("icon-plus evt-btn")
									.attr("onclick", "<portlet:namespace/>addPaperField('" + paperType + "')");
		
		var paperMinusBtn = $("<i/>").addClass("icon-minus evt-btn")
									 .attr("onclick", "<portlet:namespace/>deletePaperField('"+ paperDivSeq +"', '" + paperType + "')");
		
		paperInput.appendTo(paperDiv);
		paperPlusBtn.appendTo(paperDiv);
		paperMinusBtn.appendTo(paperDiv);
		
		paperDiv.appendTo(paperTd);
		
		$("#<portlet:namespace/>paperFileCount").val($(".<portlet:namespace/>paper-file").length);
	}
	
	
	/* 논문 파일 또는 링크 입력 항목 제거 */
	function <portlet:namespace/>deletePaperField(paperFeildSeq, paperType){
		$("div[paper-" + paperType + "-field-seq="+paperFeildSeq+"]").remove();
		
		$("#<portlet:namespace/>paperFileCount").val($(".<portlet:namespace/>paper-file").length);
	}
	
	function <portlet:namespace/>scienceAppPaperValidationCheck(){
		
		// URL 정규식
		var urlValid = true;
		var urlRegex = /^http(s)?:\/\/(www\.)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
		$(".<portlet:namespace/>paper-link-input").each(function(idx, elem){
			var paperLinkUrl = $(this).val();
			if(paperLinkUrl !== null && paperLinkUrl !== '' && paperLinkUrl !== undefined){
				if(!urlRegex.test(paperLinkUrl)){
					alert(Liferay.Language.get('edison-science-app-paper-link-invalid'));
					urlValid = false;
					$(this).focus();
					return;
				}
			}
		});
		
		return urlValid;
		
	}
	
	function <portlet:namespace/>deletePaperItem(scienceAppId, paperSeq, paperType){
		
		// ScienceAppPaper에서 데이터 삭제
		$.ajax({
			url: "<%=deletePaperFileInDLURL%>",
			cache: false,
			data: {
				"<portlet:namespace/>scienceAppId" : scienceAppId,
				"<portlet:namespace/>paperSeq" : paperSeq,
				"<portlet:namespace/>paperType" : paperType
			},
			success: function(response) {
				var successFileDelte = response.successFileDelte;
				
				if(successFileDelte){
					alert(Liferay.Language.get('edison-science-app-paper-delete-successed'));
					//toastr["success"]("", Liferay.Language.get('edison-science-app-paper-delete-successed'));
				} else{
					alert(Liferay.Language.get('edison-science-app-paper-delete-failed'));
					//toastr["error"]("", Liferay.Language.get('edison-science-app-paper-delete-failed'));
				}
			}, error:function(response,e){ 
				alert(Liferay.Language.get('edison-science-app-paper-delete-failed'));
				//toastr["error"]("", Liferay.Language.get('edison-science-app-paper-delete-failed'));
			},complete: function(response){
			}
		});
		
		location.reload();
	}
	
	/* Get App Manager List */
	function <portlet:namespace/>appManagerList() {
		
		var dataForm = {
				"<portlet:namespace/>scienceAppId" : "${scienceAppId}"
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=appManagerListURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var appManagerList = msg.appManagerList;
				
				var rowResult;
				$("#<portlet:namespace/>appManagerListBody tr:not(:has(#1))").remove();
				
				if(appManagerList.length == 0) {
					$rowResult = $("<tr/>");
					$("<td/>").attr("colspan", "6")
							  .css("text-align","center")
							  .text(Liferay.Language.get('edison-there-are-no-data'))
							  .appendTo($rowResult);
					$("#<portlet:namespace/>appManagerListBody").append($rowResult);
				} else {
					for(var i = 0; i < appManagerList.length; i++) {
						$rowResult = $("<tr/>");
						
						$("<td/>").text(i+1)
								  .css("text-align","center")
								  .appendTo($rowResult);
						$("<td/>").text(appManagerList[i].userScreenName)
								  .css("text-align","center")
								  .appendTo($rowResult);
						$("<td/>").text(appManagerList[i].userFullName)
								  .css("text-align","center")
								  .appendTo($rowResult);
						$("<td/>").text(appManagerList[i].userEmailAddress)
								  .css("text-align","center")
								  .appendTo($rowResult);
						$("<td/>").text(appManagerList[i].createDate)
								  .css("text-align","center")
								  .appendTo($rowResult);
						
							$("<td/>").html("<input type='button' class='btn btn-default' value='<liferay-ui:message key='edison-button-board-delete' />' onClick='<portlet:namespace/>deleteAppManager("+appManagerList[i].scienceAppManagerId+");' class='button01b' />")
									  .addClass("TC")
									  .appendTo($rowResult);
						
						$("#<portlet:namespace/>appManagerListBody").append($rowResult);
					}
				}
			},error:function(msg,e){ 
				alert("function appManagerList "+e);
				return false;
			}
		});
	}
	
	/* Delete App Manager */
	function <portlet:namespace/>deleteAppManager(scienceAppManagerId) {
		var con = confirm('<liferay-ui:message key="edison-science-appstore-toolkit-delete-solver-manager" />');
		
		if(con){
			var dataForm = {
					"<portlet:namespace/>scienceAppManagerId" : scienceAppManagerId
				};
				
				jQuery.ajax({
					type: "POST",
					url: "<%=deleteAppAuthURL%>",
					async : false,
					data : dataForm,
					success: function(msg) {
						<portlet:namespace/>appManagerList();
					},error:function(msg,e){ 
						alert("function deleteAppManager "+e);
						return false;
					}
				});
		}else{
		return;
		}
	}
	
	/* Add App Manager */
	function <portlet:namespace/>addAppManager(){
		
		var dataForm = $("form[name=managerAddForm]").serialize();
		
		jQuery.ajax({
			type: "POST",
			url: "<%= appManagerAddURL %>",
			async : false,
			data : dataForm,
			success: function(msg) {
				<portlet:namespace/>appManagerList();
			},error:function(msg,e){ 
				alert("function addAppManager "+e);
				return false;
			}
		});
		$("#<portlet:namespace/>userScreenName_manager").val("");
		$("#<portlet:namespace/>app-manager-add-dialog").dialog("close");
	}
	
	/* Get User Info */
	function <portlet:namespace/>getUserInfo(role) {
		
		if(role == 'owner'){
			var pre = $("#<portlet:namespace/>now_userScreenName").val();
			var post = $("#<portlet:namespace/>userScreenName_owner").val();
			if( pre == post ){
				alert(Liferay.Language.get('edison-appstore-fail-owner'));
				return false;
			}
			
			/*현재아이디와 입력아이디가 같은경우 제외*/
			var data = {
					<portlet:namespace/>type : "owner",
					<portlet:namespace/>scienceAppId : "${scienceAppId}",
					<portlet:namespace/>now_userScreenName : "${data.userScreenName}",
					<portlet:namespace/>userScreenName_owner : post
			};
			
			jQuery.ajax({
				type: "POST",
				url: "<%=appUserInfoURL%>",
				async : false,
				data :  data,
				success: function(msg) {
					var result = msg.result;
					var appUserInfo = msg.appUserInfo;
					if(result === undefined) {
						alert("user not found");
					} else if(result == "none") {
						alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-user-notfound' />");
					}else{
						$("#ownerId").text(appUserInfo.userScreenName);
						$("#ownerFullName").text(appUserInfo.userFullName);
						$("#ownerEmail").text(appUserInfo.userEmailAddress);
						$("#<portlet:namespace/>ownerUserId").val(appUserInfo.userId);
						$("#<portlet:namespace/>pre_userScreenName").val("${data.userId}");
						$("#<portlet:namespace/>app-owner-add-dialog").dialog("open");
					}
				},error:function(msg,e){ 
					alert("function getUserInfo "+e);
					return false;
				}
			});
		}else if(role == 'manager'){
			jQuery.ajax({
				type: "POST",
				url: "<%=appUserInfoURL%>",
				async : false,
				dataType: 'json',
				data : $("#userSearchManagerForm").serialize(),
				success: function(msg) {
					var result = msg.result;
					var appUserInfo = msg.appUserInfo;
					if(result === undefined) {
						alert("user not found");
					} else if(result == "admin") {
						alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-admin-alert' />");
					} else if(result == "owner" || result == "manager") {
						alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-admin-already' />");
					} else if(result == "none") {
						alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-user-notfound' />");
					} else if(result == "notdeveloper") {
						alert("<liferay-ui:message key='edison-appstore-workspace-permission-alert' />");
					} else if(result == "user") {
						$("#managerId").text(appUserInfo.userScreenName);
						$("#managerFullName").text(privateTextConverter2(appUserInfo.userFullName));
						$("#managerEmail").text(privateEmailConverter2(appUserInfo.userEmailAddress));
						$("#<portlet:namespace/>managerUserId").val(appUserInfo.userId);
						$("#<portlet:namespace/>app-manager-add-dialog").dialog("open");
					}
				},error:function(msg,e){ 
					alert("function getUserInfo "+e);
					return false;
				}
			});
		}
	}
	
	/* Update App Owner */
	function <portlet:namespace/>updateAppOwner(){
		var dataForm = $("form[name=ownerUpdateForm]").serialize();
		
		jQuery.ajax({
			type: "POST",
			url: "<%= appOwnerUpdateURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				<portlet:namespace/>goList();
			},error:function(msg,e){ 
				alert("function updateAppOwner "+e);
			}
		});
	}
	
	/* Change Category */
	function changeCategory(parentCategory,childrenCategory){
		var childrenArray = childrenCategory.split(',');
		var parentCategoryId = "";
		var parentCategoryArray = new Array();
		for(var i=0; i< childrenArray.length; i++){
			$("input:checkbox[value="+childrenArray[i]+"]").attr("checked",true);
		}
		
		var parentArray = parentCategory.split(',');
		for(var i=0; i< parentArray.length; i++){
			<portlet:namespace/>openRootCategory("OPEN",parentArray[i]);
		}
	}
	
	/* Open Category */
	function <portlet:namespace/>openRootCategory(status,rootCatogoryId) {
		$parentTd = $("#<portlet:namespace/>"+rootCatogoryId+"_parentTd");
		$childrenTd = $("#<portlet:namespace/>"+rootCatogoryId+"_childrenTd");
		
		var hiddenSpan;
		var showSpan;
		if(status=="OPEN"){
			hiddenSpan = $("#<portlet:namespace/>"+rootCatogoryId+"_parent_open");
			showSpan 	= $("#<portlet:namespace/>"+rootCatogoryId+"_parent_close");
			
			$parentTd.removeAttr("colspan");
			$childrenTd.show();
		}else{
			hiddenSpan = $("#<portlet:namespace/>"+rootCatogoryId+"_parent_close");
			showSpan 	= $("#<portlet:namespace/>"+rootCatogoryId+"_parent_open");
			
			$parentTd.attr("colspan","3");
			$childrenTd.hide();
		}
		
		hiddenSpan.hide();
		showSpan.show();
	}
	
	function _appManager_helpMD(text){
		var viewContent = $("#<portlet:namespace/>helpMarkdown tr."+text);
		
		var helpMarkdown = $("#<portlet:namespace/>helpMarkdown").html();
		$(".md-how-to-use-text").html(helpMarkdown);
		if(viewContent.hasClass("active")){
			$(".<portlet:namespace/>help-md-content").removeClass("active");
			$(".md-how-to-use-text").html('');
		} else {
			$(".<portlet:namespace/>help-md-content").removeClass("active");
			$(".<portlet:namespace/>help-md-content."+text).addClass("active");
			$(".<portlet:namespace/>help-md-content").hide();
			$(".<portlet:namespace/>help-md-content."+text).show()
		}
		
	}
	
</script>

<aui:script>
	AUI().ready('aui-module', function(A){
		/* Selected Service Language */
		var targetLanguage = "${data.targetLanguage}";
		if(targetLanguage != null && targetLanguage != '' && targetLanguage != 'undefined'){
			if(targetLanguage.indexOf(",") == -1){
				// targetLanguage 1개
				$("#<portlet:namespace/>targetLanguage option").prop("selected", false);
				$("#<portlet:namespace/>targetLanguage option[value="+targetLanguage+"]").prop("selected", true);
			} else {
				$("#<portlet:namespace/>targetLanguage option").prop("selected", false);
			}
		}
	});

</aui:script>


