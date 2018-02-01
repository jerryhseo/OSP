<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%-- <script src="${contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${contextPath}/js/jquery-ui.min.js"></script>
<script src="${contextPath}/js/main.js"></script>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery/jquery-ui.theme.min.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery/jquery-ui.min.css" media="screen"/> --%>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<%
	String groupNm = (String)request.getAttribute("groupNm");
	String groupId = (String)request.getAttribute("groupId");
	String mode = (String)request.getAttribute("mode");
	String contentSeq = (String)request.getAttribute("contentSeq");
	String contentDiv = (String)request.getAttribute("contentDiv");
	String codeOption = (String)request.getAttribute("codeOption");
	
	String selectDisable = "";
	if(mode.equals(Constants.UPDATE)){
		selectDisable = "disabled=\"disabled\"";
	}
	String create = LanguageUtil.format(locale, "edison-button-save", "");
// 	String update = LanguageUtil.format(locale, "edison-content-update", "");

	Locale[] locales = LanguageUtil.getAvailableLocales();
	String localesStr = "";
	
	String titleTargetLang = (String)request.getAttribute("locale");
	String title = "title_"+titleTargetLang;
	
%>
<style type="text/css">
.aui input[type="checkbox"] {
    margin: 0px;
}
.aui .control-group {
	margin-bottom: 0px;
}

.aui select, .aui input[type="password"], .aui textarea
	{
	margin-bottom: 0px;
}

.aui .long_field {
	width: 350px;
}

.aui .short_field {
	width: 200px;
}
.aui .too_long_field{
	width: 500px;
}
.aui .edison_file {
	border: 1px solid #CCCCCC;
	margin-bottom: 2px;
}

.aui .input-localized-input {
	display: table-row;
}

.aui .tooltip.top {
	display: block;
}
.aui .tooltip {
	display: none;
}

.manualContentFile{
	display: none;
}

</style>
<liferay-portlet:actionURL secure="<%=request.isSecure()%>" var="modifyGeneralContentUrl">
	<portlet:param name="myaction" value="generalModify" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
<%-- 	<liferay-portlet:param name="mode" value="<%=mode %>" /> --%>
	<liferay-portlet:param name="contentSeq" value="<%=contentSeq %>" />
	<liferay-portlet:param name="contentDiv" value="<%=contentDiv %>" />	
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchLine" value="${searchLine}" />
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="deleteSingleEdisonFileURL" escapeXml="false" id="deleteSingleEdisonFile" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="contentUserInfoURL" id="contentUserInfo" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updateContentOwnerURL" id="updateContentOwner" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updateContentManagerURL" id="updateContentManager" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteContentManagerURL" id="deleteContentManager" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="contentAuthCheckURL" escapeXml="false" id="contentAuthCheck" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="contentfiledownloadURL" escapeXml="false" id="contentfiledownload" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="contentSeq" value="<%=contentSeq %>" />
</liferay-portlet:resourceURL>


<liferay-portlet:renderURL var="detailViewURL"  copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="generalModifyView" />
	<liferay-portlet:param name="mode" value="<%=Constants.VIEW%>" />
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchLine" value="${searchLine}" />
	<liferay-portlet:param name="contentSeq" value="<%=contentSeq %>" />
	<liferay-portlet:param name="contentDiv" value="<%=contentDiv %>" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL>


<aui:form action="<%= modifyGeneralContentUrl %>" method="post" name="createGeneralContentForm" enctype="multipart/form-data" cssClass="table-responsive panel edison-panel">
	<aui:input name="groupId" type="hidden" value="${groupId }"/>
	<aui:input name="mode" type="hidden" value="<%=mode%>"/>
<%-- 	<aui:input name="contentDiv" type="hidden" value="<%=contentDiv%>"/> --%>
<%-- 	<aui:input name="contentSeq" type="hidden" value="<%=contentSeq%>"/> --%>
	<aui:input name="entryId" type="hidden" value="${entryId }"/>
	<aui:input name="deleteAdvancedFile" type="hidden" value=""/>
	
	<c:if test="${redirectURL ne ''}"> 
		<c:set value="<%=title%>" var="title"/>
		<h3><a onClick="<portlet:namespace/>historyBack()" style="cursor: pointer;"> ${redirectName } </a>  > <liferay-ui:message key='edison-content-management' /></h3>
	</c:if>
	
	<div class="table1_list">
		<div class="panel-heading clearfix">
		
			<h3 class="panel-title pull-left">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
				<liferay-ui:message key='edison-science-appstore-toolkit-default-information' />
			</h3>
			
			<div class="input-group">
				<div class="input-group-btn">
					<c:choose>
						<c:when test="${mode eq 'add' }">
							<div class="contentbtnGroup">
								<button class="btn button02_1" type="button" onclick="<portlet:namespace/>actionCall('<%=Constants.ADD%>');">
									<liferay-ui:message key='edison-button-save' />
								</button>
							</div>
						</c:when>
						<c:otherwise>
							<div class="contentbtnGroup">
								<input class="addIp btn button02_2" style="width: 100px;" onclick="<portlet:namespace/>goDetailView();" value="<liferay-ui:message key='edison-simulation-monitoring-table-header-detail'/>" type="button">
						
								<button class="btn button02_1" style="margin: 0px 5px;" type="button" onclick="<portlet:namespace/>actionCall('<%=Constants.UPDATE%>');">
									<liferay-ui:message key='edison-button-board-modify'/>
								</button>
								<c:if test="${isOwner == true }"> 
									<button class="btn button02_1" type="button" onclick="<portlet:namespace/>actionCall('<%=Constants.DELETE%>');">
										<liferay-ui:message key='edison-button-board-delete'/>
									</button>
								</c:if>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	
		<table border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" style="width:99%;">
			<colgroup>
				<col width="16%" />
				<col width="34%" />
				<col width="16%" />
				<col width="34%" />
			</colgroup>
			<tbody>
				<tr>
					<th><liferay-ui:message key="edison-content-service-language"/><span class="requiredField"> *</span></th>
					<td>
						<aui:select name="serviceLanguage" label="" >
							<option value=""><liferay-ui:message key='full' /></option>
							<%
							String siteDefaultLanuageId = LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale());
							
							for(Locale aLocale : locales){
								String languageId = LocaleUtil.toLanguageId(aLocale);
								if(localesStr.equals("")){
									localesStr += languageId;
								}else{
									localesStr += ","+languageId;
								}
								
								String languageNm = aLocale.getDisplayName(themeDisplay.getLocale());
							%>
								<aui:option label="<%=languageNm%>" value="<%=languageId%>" selected="<%=languageId.equals(siteDefaultLanuageId) %>"/>
							<%} %>
					
						</aui:select>
					</td>
					<th><liferay-ui:message key="edison-workflow-public-status"/><span class="requiredField"> *</span></th>
					<td>
						<aui:select name="openYn" label="">
							<aui:option value="Y" selected="${content.openYn eq 'Y'}"><liferay-ui:message key='edison-appstore-status-service'/></aui:option>
							<aui:option value="N" selected="${content.openYn eq 'N'}"><liferay-ui:message key='edison-appstore-status-private'/></aui:option>
						</aui:select>
					</td>
				</tr>
				<tr>
					<th><liferay-ui:message key="edison-content-type"/><span class="requiredField"> *</span></th>
					<td colspan="3">
						<select name="<portlet:namespace/>contentDivSelect" class="btn btn-default" id ="<portlet:namespace/>contentDivSelect" onChange="<portlet:namespace/>changeContentDiv(this.value)" <%=selectDisable %>>
							<%=codeOption%>
						</select>
					</td>
				</tr>
				<tr>
					<th><liferay-ui:message key="edison-table-list-header-title"/><span class="requiredField"> *</span></th>
					<td colspan="3">
						<div class="control-group">
							<liferay-ui:input-localized name="title" xml="${content.title}" cssClass="form-control"  type="input" style="width:500px;"> </liferay-ui:input-localized>
						</div>
					</td>
				</tr>
				<tr>
					<th><liferay-ui:message key="edison-table-list-header-resume"/><span class="requiredField"> *</span></th>
					<td colspan="3">
						<div class="control-group">
							<liferay-ui:input-localized name="resume" xml="${content.resume}" type="textarea" rows="3" spellcheck="false" style="width: 95%; resize:none;margin-bottom:4px;">
							</liferay-ui:input-localized>
							
						</div>
					</td>
				</tr>
				
				<tr>
					<th><liferay-ui:message key="edison-table-list-header-file"/><span class="requiredField"> *</span> <liferay-ui:icon-help message="edison-content-file-descriptive-message"/></th>
					<td class="advancedContentTd">
					
						<span class="advancedContent"> HTML파일 <input name="<portlet:namespace/>contentHtmlYn" id="<portlet:namespace/>contentHtmlYn" type="checkbox"/><br/></span>
					
						<c:choose>
							<c:when test="${mode eq 'add' }">
	<%-- 								<aui:input type="file" name="contentFile" cssClass="edison_file" label=""></aui:input> --%>
								<div class="control-group">
									<input type="file" name="<portlet:namespace/>contentFile" id="<portlet:namespace/>contentFile"  style="border:1px solid #CCCCCC;" />
								</div>
							</c:when>
							<c:otherwise>
								<c:if test="${content.contentFileNm != ''}">
								
								<div id="contentFilediv" style="display:inline-block;">
									<span>
										<span style="cursor:pointer" onclick="<portlet:namespace/>contentFileDownload()" class="onMouseHover">
										${content.contentFileNm}
										
											<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
										</span>
									</span>
										&nbsp;&nbsp;
										
									<img src='${contextPath}/images/icon_dustbin.png' width='13' height='14' style="cursor:pointer" onclick="<portlet:namespace/>deleteAdvancedFiles('contentFilediv', 'contentFile')"/>
								</div>
								</c:if>
								<c:if test="${content.contentFileNm == ''}">
									<div class="control-group">
										<input type="file" name="<portlet:namespace/>contentFile" id="<portlet:namespace/>contentFile"  style ="border:1px solid #CCCCCC;" />
									</div>
	<%-- 									<aui:input type="file" name="contentFile" cssClass="edison_file" label=""></aui:input> --%>
								</c:if>
							</c:otherwise>
						</c:choose>
					</td>
					
					<th class="advancedExecuteFileName"><liferay-ui:message key="edison-content-execute-file"/></th>
					<td class="advancedExecuteFileName">
						<aui:input label="" value="${content.advancedStartFileNm }" name="advancedStartFileNm" id="advancedStartFileNm" type="text" readonly="true" >
						</aui:input>
					</td>
				</tr>
				
				
				<%for(Locale aLocale : locales){
					String languageId = LocaleUtil.toLanguageId(aLocale);
					String languageNm =aLocale.getDisplayName(themeDisplay.getLocale());
					String manualId = "contentFileNm_manual_"+languageId;
					String manualTitle = "manualTitle_"+languageId;
				%>
					<tr class="manualContentFile">
						<td colspan="3">
							
							<%=languageNm%>&nbsp;&nbsp;
							<c:choose>
							<c:when test="${mode eq 'add' }">
								<input type="file" id="<portlet:namespace/>manual<%=languageId%>" name="<portlet:namespace/>manual<%=languageId%>" style ="border:1px solid #CCCCCC;"><br/>
							</c:when>
							<c:otherwise>
								<c:set value="<%=manualId%>" var="manualId"/>
								<c:if test="${content[manualId] ne null}">
									<div id="contentManualFilediv<%=languageId %>">
										<span>
											<span style="cursor:pointer" onclick="<portlet:namespace/>contentFileDownload('<%=languageId %>')" class="onMouseHover">
												${content[manualId]}
											
												<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
											</span>
										</span>
										&nbsp;&nbsp;
										<%-- <span style="cursor:pointer" onclick="<portlet:namespace/>deleteAdvancedFile('contentManualFilediv<%=languageId %>', 'manual<%=languageId%>')">
											<u>[delete]</u>
										</span> --%>
										<img src='${contextPath}/images/icon_dustbin.png' width='13' height='14' style="cursor:pointer" onclick="<portlet:namespace/>deleteAdvancedFiles('contentManualFilediv<%=languageId %>', 'manual<%=languageId%>')"/>
									</div>
								</c:if>
							</c:otherwise>
							</c:choose>
							
							
						</td>
					</tr>
				<%} %>
					
				<tr>
					<th><liferay-ui:message key="edison-content-main-image"/><span class="requiredField"> *</span></th>
					<td colspan="3">
						<div id="fileListDiv">
						<c:choose>
							<c:when test="${mode eq 'update' }">
								<c:if test="${fn:length(fileList) < 1}"><input type="file" name="<portlet:namespace/>mainImage" id="<portlet:namespace/>mainImage"  style ="border:1px solid #CCCCCC;"></c:if>
								
								<c:forEach items="${fileList}" var="fileMap">
								<div id="fileListDiv">
										<span style="cursor:pointer" onclick="<portlet:namespace/>fileDownload('${fileMap.fileEntryId }')" class="onMouseHover">
											${fileMap.fileTitle}
											<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
										</span>
											&nbsp;&nbsp;
										<%-- <span style="cursor:pointer" onclick="<portlet:namespace/>deleteSingleEdisonFile('${fileMap.fileEntryId}')">
											<u>[delete]</u>
										</span> --%>
										
										<img src='${contextPath}/images/icon_dustbin.png' width='13' height='14' style="cursor:pointer" onclick="<portlet:namespace/>deleteSingleEdisonFile('${fileMap.fileEntryId}', '<%=contentSeq%>')"/>
										<br>
								</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<input type="file" name="<portlet:namespace/>mainImage" id="<portlet:namespace/>mainImage"  style ="border:1px solid #CCCCCC;">
							</c:otherwise>
						</c:choose>
						</div>
					</td>
				</tr>
				
				<tr>
					<th rowspan="${fn:length(parentCategoryList)+1}">
						<liferay-ui:message key='edison-science-appstore-view-tab-category' /><span class="requiredField"> *</span>
					</th>
					
				</tr>
				<c:forEach items="${parentCategoryList}" var="parentCategory">
					<tr>
						<td id="<portlet:namespace/>${parentCategory.value}_parentTd" colspan="4">
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
				
				
				<!-- Owner 및 Manager 지정 -->
				<c:if test="${mode eq 'update' && isOwner == true}">
					<tr>
						<th><liferay-ui:message key="edison-virtuallab-owner"/></th>
						<td colspan="3">
							<input id="<portlet:namespace/>nowOwnerName" name="<portlet:namespace/>nowOwnerName" 
								type="text" value="${content.insertNm}" style="width: 120px;margin-bottom:0px;" readOnly/>
							<input id="<portlet:namespace/>newOwnerName" name="<portlet:namespace/>newOwnerName" 
								type="text" maxlength="15" placeholder="<liferay-ui:message key='edison-table-list-header-userid' />" 
								style="margin-bottom:0px;" onkeypress="if(event.keyCode==13) {<portlet:namespace/>getUserInfo('owner', '<%=contentSeq %>'); return false;}" />
							<input id="search_button"  name="<portlet:namespace />search_button" type="button" class="btn btn-default" value="<liferay-ui:message key='edison-content-owner-transfer' />" onClick="<portlet:namespace/>getUserInfo('owner' , '<%=contentSeq %>')"/>
						</td>
					</tr>	
					<tr>
						<th><liferay-ui:message key="edison-content-manager"/></th>
						<td  colspan="3">
							<%-- <div class="searchbox03">
								<input id="<portlet:namespace/>now_MgrUserScreenName" name="<portlet:namespace/>now_MgrUserScreenName" type="hidden"/>
								<input type="button" class="btnsearch" value="" onclick="<portlet:namespace/>getUserInfo('manager');">
							</div> --%>
							
							<c:forEach var="manager" items="${contentManagerList }">
								<input id="<portlet:namespace/>nowMgrId" name="<portlet:namespace/>nowMgrId" type="hidden" value="${manager.userId}" readonly/>
								<input id="<portlet:namespace/>nowMgrName" name="<portlet:namespace/>nowMgrName" type="text" value="${manager.userScreenName}" style="width: 120px;margin-bottom: 0px;"  readonly />
							</c:forEach>
							<input id="<portlet:namespace/>newMgrName" name="<portlet:namespace/>newMgrName" type="text" maxlength="15" placeholder="<liferay-ui:message key='edison-table-list-header-userid' />" style="margin-bottom:0px;" onkeypress="if(event.keyCode==13) {<portlet:namespace/>getUserInfo('manager', '<%=contentSeq %>'); return false;}"/>
							<input id="search_button"  name="<portlet:namespace />search_button" type="button" class="btn btn-default" value="<liferay-ui:message key='edison-content-manager-transfer' />" onClick="<portlet:namespace/>getUserInfo('manager', '<%=contentSeq %>')"/>
							
							<c:if test="${contentManagerList!=null && fn:length(contentManagerList) > 0 }">
								<input id="search_button"  name="<portlet:namespace />search_button" type="button" class="btn btn-default" value="<liferay-ui:message key='edison-button-board-delete' />" onClick="<portlet:namespace/>deleteContentMgr('<%=contentSeq%>')"/>
							</c:if>
						</td>
					</tr>	
				</c:if>
			</tbody>
		</table>
	</div>
	<br/>
	
	<c:if test="${mode ne 'add' }">
		<liferay-portlet:runtime 
			portletName="edisonrelateasset_WAR_edisondefault2016portlet" 
			defaultPreferences="" 
			queryString="&entryId=${entryId}&isMgrBtn=${isOwner || isManager}&isVirTitle=true&redirectURL=${redirectURL }&redirectName=${redirectName }"/>
	</c:if>
</aui:form>

		
		<div id="<portlet:namespace/>content-owner-add-dialog" title="<liferay-ui:message key='edison-appstore-solver-owner-change' />" class="newWindow" style="display:none; background-color:white; padding:0px;">
			<div class="newWheader">
				<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
					<div class="newWtitle"><liferay-ui:message key="edison-appstore-solver-owner-change"/></div>
				</div>
				<div class="newWclose" style="cursor: pointer;">
					<img id="content-owner-add-dialog-close-btn" name="content-owner-add-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="25" height="25" style="cursor:pointer; float: right;"/>
				</div>
			</div>
			<div style="padding: 30px;" class="newWcont01 table-responsive panel edison-panel">
				<form id="ownerUpdateForm" name="ownerUpdateForm" method="post" action="<%=updateContentOwnerURL%>" > 
					<div class="table1_list" style="width:85%; padding:15px; margin:0 auto;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
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
								<tr id="projectNotConfirm"> 
									<td colspan="2"  style="text-align: center; color:#f03030;">
										<liferay-ui:message key='edison-content-project-affiliation-yn-user-get-info-alert' />
										<br/>
										<liferay-ui:message key='edison-content-owner-register-confirm' />
									</td>
								</tr>
								<tr  id="projectConfirm">
									<td colspan="2"  style="text-align: center; color:#f03030;">
										<liferay-ui:message key='edison-content-owner-register-confirm' />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<input id="<portlet:namespace/>ownerUserId" name="<portlet:namespace/>ownerUserId" type="hidden">
					<input id="<portlet:namespace/>ownerUserName" name="<portlet:namespace/>ownerUserName" type="hidden">
					<input id="<portlet:namespace/>pastOnwerId" name="<portlet:namespace/>pastOnwerId" type="hidden">
					<div style="text-align:center;">
						<input id="register_request_button" name="<portlet:namespace />register_request_button" class="yellowbtn" type="button" value="<liferay-ui:message key='edison-button-register' />" onclick="<portlet:namespace/>updateContentOwner('<%=contentSeq %>')"/>
					</div>
				</form>
			</div>
		</div>
		
		

	 <div id="<portlet:namespace/>content-manager-add-dialog" title="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-manager-register' />" class="newWindow" style="display:none; background-color:white; padding:0px;">
			<div class="newWheader">
				<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
					<div class="newWtitle"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-manager-register' /></div>
				</div>
				<div class="newWclose" style="cursor: pointer;">
					<img id="content-manager-add-dialog-close-btn" name="content-manager-add-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="25" height="25" style="cursor:pointer; float: right;"/>
				</div>
			</div>
			<div style="padding: 30px;" class="newWcont01">
				<form id="managerUpdateForm" name="managerUpdateForm" method="post" action="<%= updateContentManagerURL %>" > 
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
									<td colspan="2"  style="text-align: center; color:#f03030;"><liferay-ui:message key='edison-content-manager-register-confirm' /></td>
								</tr>
							</tbody>
						</table>
					</div>
					<input id="<portlet:namespace/>managerUserId" name="<portlet:namespace/>managerUserId" type="hidden" >
					<input id="<portlet:namespace/>pastManagerId" name="<portlet:namespace/>pastManagerId" type="hidden" >
					<div style="text-align:center;">
						<input id="register_request_button" name="<portlet:namespace />register_request_button" class="yellowbtn" type="button" value="<liferay-ui:message key='edison-button-register' />" onclick="<portlet:namespace/>updateContentManager('<%=contentSeq%>')"/>
					</div>
				</form>
			</div>
		</div> 
		
		<div id="fileDownloadIframe"> </div>

<script type="text/javascript">


<%
if(mode.equals(Constants.UPDATE)){
%>
	$(document).ready(function () {
		changeCategory('${parentCategory}','${childrenCategory}');
		changeLanguage('${content.serviceLanguage}');
	});
<%}%>

		
AUI().ready(function() {
	 
	//오너,매니저 다이얼로그 초기화
	$("#<portlet:namespace/>content-manager-add-dialog").dialog({
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
	
	$("#content-manager-add-dialog-close-btn").click(function() {
		$("#<portlet:namespace/>content-manager-add-dialog").dialog("close");
	});
	
	$("#<portlet:namespace/>content-owner-add-dialog").dialog({
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
	
	$("#content-owner-add-dialog-close-btn").click(function() {
		$("#<portlet:namespace/>content-owner-add-dialog").dialog("close");
	});
	
	//projectYn update check init
	if("<%=mode%>" == "update"){
		$projectYnValue = "${content.projectYn}";
		$("input[name=<portlet:namespace/>projectYn]").each(function() {
			$ynValue = $(this).val();
			if($ynValue == $projectYnValue){
				$(this).attr("checked", true);
			}
		});
	}
	
	if("<%=isProjectThan%>" == "true"){
		$("#<portlet:namespace/>projectYnTr").show();
	}else{
		$("#<portlet:namespace/>projectYnTr").hide();
	}
	
	//html 체크박스 이벤트
	 $("#<portlet:namespace/>contentHtmlYn").click(function() {
		 if($(this).is(":checked")){//체크되면 실행파일 입력 보임
			$(".advancedExecuteFileName").show();
			$(".advancedContentTd").attr("colspan",1);
			startExecuteFileNm($("input[name=<portlet:namespace/>contentFile]")); //실행파일 명 생성
		 }else{//체크해제, 
			 $(".advancedContentTd").attr("colspan",3);
			 $(".advancedExecuteFileName").hide(); //실행파일 명 삭제
		 }
	 });
	 $("input[name=<portlet:namespace/>contentFile]").change(function() {//실행파일 명 생성
		 	startExecuteFileNm(this);
	 });
	 
	 
	var contentDiv = "<%=contentDiv%>";
	
	if("<%=mode%>" == "add"){
		contentDiv = $("#<portlet:namespace/>contentDivSelect").val();
	}
	<portlet:namespace/>changeContentDiv(contentDiv);
});

function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}

function changeLanguage(val){
	var $sLanguage = $("select[id='<portlet:namespace/>serviceLanguage']");
	if(val.indexOf(",")>-1){
		$sLanguage.val("");
	}else{
		$sLanguage.val(val);
	}
}
function <portlet:namespace/>changeContentDiv(contentDiv){
	
	$("#<portlet:namespace/>contentDiv").val(contentDiv);
		
	//초기화
	$(".advancedContentTd").attr("colspan",3);
	$(".manualContentFile").hide();
	$(".contentFile").hide();
	$(".advancedContent").hide();
	$(".advancedExecuteFileName").hide();

	$("#<portlet:namespace/>contentHtmlYn").prop("checked", false);
	
	if(contentDiv == 2001004){//고급콘텐츠일때
		$(".contentFile").show();
		$(".advancedContent").show();
	
		if("<%=mode%>" == "update"){
			if("${content.advancedStartFileNm }" != ""){
				
				$(".advancedExecuteFileName").show();
				$(".advancedContentTd").attr("colspan",1);

				$("#<portlet:namespace/>contentHtmlYn").prop("checked",true).attr("disabled", true);		
			}
		}
	}else if(contentDiv == 2001002){//메뉴얼일떄
		$(".manualContentFile").show();
	}
	else{//그외
		$(".contentFile").show();
	}
}

/*category*/
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
/*category*/	
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

function <portlet:namespace/>actionCall(mode){
	if(mode=='<%=Constants.ADD%>' || mode=='<%=Constants.UPDATE%>'){
		var contentDiv = $("#<portlet:namespace/>contentDiv").val();
		//앱제목 Validation
		$serviceLanguage = $("#<portlet:namespace/>serviceLanguage option:selected");
		var lanuageCode = $serviceLanguage.val();
		
		if(lanuageCode==""){
// 			var id = "<portlet:namespace/>title_" + lanuageCode;
			var localeStr = "<%=localesStr%>";
			var localeArray = localeStr.split(",");
			
			for(var i=0; i< localeArray.length; i++){
				//title
				var id = "<portlet:namespace/>title_" + localeArray[i];
				var inputValue = $("input[name="+id+"]").val();
				if(inputValue == "" || inputValue == undefined){
					alert(Liferay.Language.get('edison-error-another-language-alret-message'));
					$("#<portlet:namespace/>title").focus();
					return false;
				}

				//resume
				var resumeId = "<portlet:namespace/>resume_" + localeArray[i];
				var resumeInputValue = $("input[name="+resumeId+"]").val();
				if(resumeInputValue == "" || resumeInputValue == undefined){
					alert(Liferay.Language.get('edison-error-another-language-alret-message'));
					$("#<portlet:namespace/>resume").focus();
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
			
			var resumeId = "<portlet:namespace/>resume_" + lanuageCode;
			var resumeInputValue = $("input[name="+resumeId+"]").val();
			if(resumeInputValue == "" || resumeInputValue == undefined){
				alert(Liferay.Language.get('edison-error-another-language-alret-message'));
				$("#<portlet:namespace/>resume").focus();
				return false;
			}
		} 
		
		if( $(":checkbox[name*='childrenCategoryCheckbox']:checked").length==0 ){
			alert(Liferay.Language.get('edison-science-appstore-category-error'));
			return false;
		}
		
		//콘텐츠 파일, 대표이미지 파일확장자 체크
		var checkBool = <portlet:namespace/>checkFileExtensions(contentDiv);
		if(!checkBool){ 
			return false; 
		}
		
 		<portlet:namespace/>createGeneralContentForm.submit();
	}else{
		if(!confirm(Liferay.Language.get("edison-content-delete-alert"))) return;
		$("#<portlet:namespace/>mode").val("<%=Constants.DELETE%>");
		<portlet:namespace/>createGeneralContentForm.submit();
	}
}

function <portlet:namespace/>checkFileExtensions( contentDiv){
	//첨부파일
	if(contentDiv == 2001002){
		//add 일때,
		<%for(Locale aLocale : locales){
			String languageId = LocaleUtil.toLanguageId(aLocale);
			String languageNm =aLocale.getDisplayName(themeDisplay.getLocale());
			String manualId = "manual"+languageId;
		%>
			var manualFile = $("#<portlet:namespace/><%=manualId%>").val();
			if(manualFile == ""){
				alert(Liferay.Language.get('edison-simulation-execute-user-define-select-your-own-attachments'));
				return false;
			}
			if (typeof manualFile != "undefined") {
				var fileNmArr = manualFile.split(".");
				var extension = fileNmArr[fileNmArr.length-1];
				extension = extension.toLowerCase();
				
				if(extension != "zip"){
					alert(Liferay.Language.get('edison-content-file-extension-alert'));
					return false;
				}
			}
			
		<%}%>
	}else{
		var contentFileNm = $("#<portlet:namespace/>contentFile").val();
		if(contentFileNm == ""){
			alert(Liferay.Language.get('edison-simulation-execute-user-define-select-your-own-attachments'));
			return false;
		}
		
		if(typeof contentFileNm != "undefined"){
			var fileNmArr = contentFileNm.split(".");
			var extension = fileNmArr[fileNmArr.length-1];
			extension = extension.toLowerCase();
			/* if(extension != "zip"){
				alert(Liferay.Language.get('edison-content-file-extension-alert'));
				return false;
			} */
		}
	}
	
	//대표이미지
	var mainImage = $("#<portlet:namespace/>mainImage").val();
	if (typeof mainImage != "undefined") {
		var fileNmArr = mainImage.split(".");
		var extension = fileNmArr[fileNmArr.length-1];
		extension = extension.toLowerCase();	
		
		switch (extension.toLowerCase()) {
		case 'jpg':
		case 'jpeg':
		case 'png':
			return true;
			break;
		default:
			alert(Liferay.Language.get('edison-content-main-extension-alert'));
			return false;
		}
	}
	return true;
}

function <portlet:namespace/>contentFileDownload(languageId){
	var contentDiv = '${content.contentDiv}';
	var URL = "<%=contentfiledownloadURL%>";
	if(contentDiv == 2001002){
		URL += "&<portlet:namespace/>languageId="+languageId;
	}
	location.href = URL.toString();
	
	 <%-- jQuery.ajax({
		type: "POST",
		url: "<%=contentfiledownloadURL%>",
		data: {
			"<portlet:namespace/>languageId" : languageId
		},
  		async : false,
		success: function(data) {
			
		}
	}); --%>
}

//파일 삭제
function <portlet:namespace/>deleteAdvancedFiles(fileDiv, fileId){
	if(!confirm(Liferay.Language.get("edison-content-delete-file-alert"))) return;
	
	$fileDiv = $("#"+fileDiv);
	$fileDiv.empty();
	
	$("<div></div>").addClass("control-group")
		.append(
				$("<input/>").addClass("field edison_file")
							 .attr("id","<portlet:namespace/>" + fileId)
							 .attr("name","<portlet:namespace/>" + fileId)
							 .attr("type","file")
							 .css("border","1px solid #CCCCCC")
				)
		.appendTo($fileDiv);
	
	//hidden 삭제할 파일 추가
	$("#<portlet:namespace/>deleteAdvancedFile").val("<%=Constants.DELETE%>");  
	
	//콘텐츠 파일 수정
	$("#<portlet:namespace/>" + fileId).change(function() {
		startExecuteFileNm(this);
	})
}
function startExecuteFileNm(obj){
	 var file = $(obj).val();
	 var fileExist = false;
	 var fileName = "";
	 if(file != ''){
		 dot = file.lastIndexOf(".");
		 ext = file.substring(dot).toLowerCase();
	     if(ext == ".zip"){
	    	 fileExist = true;
// 	    	 fileName = file.split("\\");
	    	 fileName = file.substring(file.lastIndexOf('\\') + 1, dot);
	     }
	 }
	 
	 var htmlYn = $("#<portlet:namespace/>contentHtmlYn").is(":checked");
	 var advancedStartFileNm = "";
	 
	 if(fileExist){//zip이고
		 if(htmlYn){//html 이면
   	 		advancedStartFileNm = fileName+"/index.html";
		 }
	 }else{
		 advancedStartFileNm = "";
	 }
	 $("#<portlet:namespace/>advancedStartFileNm").val(advancedStartFileNm);
}


function <portlet:namespace/>deleteSingleEdisonFile(p_fileEntryId, contentSeq, contentDiv){
	if(!confirm(Liferay.Language.get("edison-content-delete-file-alert"))) return;
	var deleteForm = {
			"<portlet:namespace/>fileEntryId" : p_fileEntryId,
			"<portlet:namespace/>groupId" : $('#<portlet:namespace/>groupId').val(),
			"<portlet:namespace/>contentSeq" : contentSeq,
			};
	jQuery.ajax({
		type: "POST",
		url: "<%=deleteSingleEdisonFileURL%>",
		data: deleteForm,
  		async : false,
		success: function(data) {
			var fileList = data.fileList;
			var resultMsg = data.resultMsg;
			if(resultMsg=="SUCCESS"){
				$fileListDiv = $("#fileListDiv");
				$fileListDiv.empty();
				
				$("<input/>").addClass("field edison_file")
							 .attr("id","<portlet:namespace/>mainImage")
							 .attr("name","<portlet:namespace/>mainImage")
							 .attr("type","file")
							 .css("border","1px solid #CCCCCC")
							 .appendTo($fileListDiv);
			}else if(resultMsg=="DELETE_FAIL"){
				alert("delete file error!");	
			}
		},error:function(data){ 
			alert("deleteSingleEdisonFile System error!");	
		}
	});
}

function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}


/* var fileIndex = 1;
//파일등록
function moreFileTag(){
	//if($(':input[name*=addfile]').length<2){
		fileIndex++;
		
		var div = $("<div/>").attr("id", "fileDiv"+fileIndex);
		div.append($("<input/>").attr("type","file").attr("name", "addfile").css("border", "1px solid #CCC").css("margin", "2px 4px 2px 0"));
		div.append($("<input/>").attr("type","button").attr("value","delete").css("cursor", "pointer").addClass("btn btn-default").attr("onClick", "deleteFileTag('fileDiv"+fileIndex+"')"));
		
		
		$("#fileTDArea").append(div);
} */

//삭제
function deleteFileTag(objId){	
	$("#"+objId).remove();
}

/*콘텐츠 관리자*/
function <portlet:namespace/>getUserInfo(role, contentSeq) {
	var groupId =  $('#<portlet:namespace/>groupId').val();
	if(role == 'owner'){
		var pre = $("#<portlet:namespace/>nowOwnerName").val();
		var post = $("#<portlet:namespace/>newOwnerName").val();
		if( pre == post ){
			alert(Liferay.Language.get('edison-appstore-fail-owner'));
			
			return false;
		} 
		//현재아이디와 입력아이디가 같은경우 제외
		var data = {
				<portlet:namespace/>type : "owner",
				<portlet:namespace/>contentSeq : contentSeq,
				<portlet:namespace/>groupId : groupId,
				<portlet:namespace/>nowOwnerName : pre,
				<portlet:namespace/>newOwnerName : post
		}
		
		jQuery.ajax({
			type: "POST",
			url: "<%=contentUserInfoURL%>",
			async : false,
			data :  data,
			success: function(msg) {
				var result = msg.result;
				var contentUserInfo = msg.contentUserInfo;
				if(result === undefined) {
					alert("user not found");
				} else if(result == "none") {
					alert(Liferay.Language.get('edison-virtuallab-virtualLabClassManagement-user-notfound'));
				} else if(result == "not siteMember"){
					alert(Liferay.Language.get('edison-default-site-no-user'));
				} 
//					else if(result == "notdeveloper") {
//						alert("<liferay-ui:message key='edison-appstore-workspace-permission-alert' />");
//					}
				else{
					$("#ownerId").text(contentUserInfo.userScreenName);
					$("#ownerFullName").text(contentUserInfo.userFullName);
					$("#ownerEmail").text(contentUserInfo.userEmailAddress);
					$("#<portlet:namespace/>ownerUserId").val(contentUserInfo.userId);
					$("#<portlet:namespace/>ownerUserName").val(contentUserInfo.userScreenName);
					$("#<portlet:namespace/>pastOnwerId").val(contentUserInfo.pastOnwerId);
					
					if(msg.projectUser == false){
						$("#projectConfirm").hide();
						$("#projectNotConfirm").show();
					}else{
						$("#projectConfirm").show();
						$("#projectNotConfirm").hide();
					}
					
					$("#<portlet:namespace/>content-owner-add-dialog").dialog("open");
				}
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		}); 
	}else if(role == 'manager'){
		var pre = $("#<portlet:namespace/>nowMgrName").val();
		var post = $("#<portlet:namespace/>newMgrName").val();
		if( pre == post ){
			alert(Liferay.Language.get('edison-appstore-fail-owner'));
			return false;
		} 
		var data = {
			<portlet:namespace/>type : "manager",
			<portlet:namespace/>contentSeq : contentSeq,
			<portlet:namespace/>groupId : groupId,
			<portlet:namespace/>nowMgrName : pre,
			<portlet:namespace/>newMgrName : post
		}
		jQuery.ajax({
			type: "POST",
			url: "<%=contentUserInfoURL%>",
			async : false,
// 			data : $("#userSearchManagerForm").serialize(),
			data : data,
			success: function(msg) {
				var result = msg.result;
				var contentUserInfo = msg.contentUserInfo;
				
				if(result === undefined) {
					alert("user not found");
				} else if(result == "admin") {
					alert(Liferay.Language.get('edison-virtuallab-virtualLabClassManagement-admin-alert'));
				} else if(result == "owner" || result == "manager") {
					alert(Liferay.Language.get('edison-virtuallab-virtualLabClassManagement-admin-already'));
				} else if(result == "none") {
					alert(Liferay.Language.get('edison-virtuallab-virtualLabClassManagement-user-notfound'));
				} else if(result == "user") {
					$("#managerId").text(contentUserInfo.userScreenName);
					$("#managerFullName").text(privateTextConverter2(contentUserInfo.userFullName));
					$("#managerEmail").text(privateEmailConverter2(contentUserInfo.userEmailAddress));
					$("#<portlet:namespace/>managerUserId").val(contentUserInfo.userId);
					$("#<portlet:namespace/>pastManagerId").val(contentUserInfo.pastMgrId);
					$("#<portlet:namespace/>content-manager-add-dialog").dialog("open");
				}else if(result == "not siteMember"){
					alert(Liferay.Language.get('edison-default-site-no-user'));
				} 
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
}
}
function <portlet:namespace/>updateContentManager(contentSeq){
	var groupId = $('#<portlet:namespace/>groupId').val();
	var pre = $('#<portlet:namespace/>pastManagerId').val();
	var post = $('#<portlet:namespace/>managerUserId').val();

	var data = {
			<portlet:namespace/>groupId : groupId,
			<portlet:namespace/>contentSeq : contentSeq,
			<portlet:namespace/>pastManagerId : pre,
			<portlet:namespace/>managerUserId : post,
		}
	
	jQuery.ajax({
		type: "POST",
		url: "<%=updateContentManagerURL%>",
		async : false,
		data : data,
		success: function(msg) {
			location.reload();
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
	$("#<portlet:namespace/>newMgrName").val("");
	$("#<portlet:namespace/>content-manager-add-dialog").dialog("close");
} 

function <portlet:namespace/>updateContentOwner(contentSeq){
	var groupId = $('#<portlet:namespace/>groupId').val();
	var pre = $('#<portlet:namespace/>pastOnwerId').val();
	var post = $('#<portlet:namespace/>ownerUserId').val();
	var postNm = $('#<portlet:namespace/>ownerUserName').val();
	
	var projectYn = "";
	if($("input[name=<portlet:namespace/>projectYn]:checked").length > 0){
		projectYn = $("input[name=<portlet:namespace/>projectYn]:checked").val();
	}
	
	var data = {
		<portlet:namespace/>groupId : groupId,
		<portlet:namespace/>contentSeq : contentSeq,
		<portlet:namespace/>pastOnwerId : pre,
		<portlet:namespace/>ownerUserId : post,
		<portlet:namespace/>ownerUserName : postNm,
		<portlet:namespace/>projectYn : projectYn
	}
	
	jQuery.ajax({
		type: "POST",
		url: "<%=updateContentOwnerURL%>",
		async : false,
		data : data,
		success: function(data) {
			location.reload();
			//$("#<portlet:namespace/>nowOwnerName").val(data.newOwnerScreenName);
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
	$("#<portlet:namespace/>newOwnerName").val("");
	$("#<portlet:namespace/>content-owner-add-dialog").dialog("close");
}

function <portlet:namespace/>deleteContentMgr(contentSeq){ //manager삭제
	if(!confirm(Liferay.Language.get('edison-content-manager-delete-confirm'))) return;
	
	//groupId, ContentSeq, managerUserId
	var groupId = $('#<portlet:namespace/>groupId').val();
	var manager = $('#<portlet:namespace/>nowMgrId').val();
	var data = {
		<portlet:namespace/>groupId : groupId,
		<portlet:namespace/>contentSeq : contentSeq,
		<portlet:namespace/>managerUserId : manager,
	}
	jQuery.ajax({
		type: "POST",
		url: "<%=deleteContentManagerURL%>",
		async : false,
		data : data,
		success: function(data) {
			location.reload();
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>goDetailView(){
	location.href = "<%=detailViewURL%>";
} 
</script>
