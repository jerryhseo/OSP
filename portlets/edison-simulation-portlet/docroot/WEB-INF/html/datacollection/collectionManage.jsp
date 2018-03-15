<%@page import="org.kisti.edison.bestsimulation.portlet.datacollection.Exception.DataCollectionException"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>
<%
// 	String exceptionNameMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-data-collection-validation-name-exception-msg");
	String exceptionNameMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-app-validation-name-exception-msg");
	String exceptionVersionMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-app-validation-version-exception-msg");
	
	String mode = (String)request.getAttribute("mode");
	Locale[] locales = LanguageUtil.getAvailableLocales();
	String localesStr = "";
%>
<style>

.aui .control-group {
	margin-bottom: 0px;
}
.aui .too_long_field{
	width: 500px;
}
.aui .long_field{
	width: 350px;
}
.aui .text_field{
	width: 99%;
	height: 80px;
	resize: none;
} 
.aui .tooltip.top {
	display: block;
}
.aui .tooltip {
	display: none;
}
.taglib-icon-help img{
    margin-left: 8px;
}
 
.dataTypeButtonDiv{
	text-align: right;
	margin-bottom: 10px;
}
.input-group-btn input{
	margin: 0px 1px;
}
.input-group-btn input:HOVER{
	cursor: pointer;
}
</style>
<liferay-portlet:actionURL secure="<%=request.isSecure()%>" var="manageDataCollectionUrl">
	<portlet:param name="myAction" value="manageDataCollection" />
	<liferay-portlet:param name="mode" value="${mode}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchLine" value="${searchLine}" />
	<liferay-portlet:param name="isAdmin" value="${isAdmin}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL secure="<%=request.isSecure()%>" var="deleteDataCollectionUrl">
	<portlet:param name="myAction" value="deleteDataCollection" />
	<liferay-portlet:param name="collectionId" value="${collection.collectionId}" />
</liferay-portlet:actionURL>

<liferay-portlet:renderURL var="dataTypeEditorDialogueURL" portletName="edisondatatypeeditor_WAR_edisonappstore2016portlet" windowState="<%=LiferayWindowState.POP_UP.toString() %>" >
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL> 

<liferay-portlet:renderURL var="detailViewURL" windowState="<%= LiferayWindowState.MAXIMIZED.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="detailViewDataCollection" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchLine" value="${searchLine}" />
	<liferay-portlet:param name="collectionId" value="${collection.collectionId}" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="retrieveMapdataTypeURL" escapeXml="false" id="retrieveMapdataType" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="deleteSingleEdisonFileURL" escapeXml="false" id="deleteSingleEdisonFile" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<div class="table-responsive panel edison-panel">
	<c:if test="<%= SessionErrors.contains(renderRequest, DataCollectionException.class.getName()) %>">
		<%
			DataCollectionException dcException = (DataCollectionException)SessionErrors.get(renderRequest, DataCollectionException.class.getName());
		%>
		<div class="alert alert-error">
			<c:if test="<%= dcException.getType() == DataCollectionException.EXISTS_NAME_VERSION_DATABASE %>">
				<liferay-ui:message key="edison-data-collection-duplication-name-exception-msg" />
			</c:if>
			
			<c:if test="<%= dcException.getType() == DataCollectionException.FAIL_VALIDATION_SCIENCE_APP_NAME %>">
				<liferay-ui:message key="edison-data-collection-validation-name-exception-msg" />
			</c:if>
		</div>	
	</c:if>
	<aui:form method="POST" name="collectionForm">
		<aui:input type="hidden" name="dataTypeId" value="${dataType.typeId }"/>
		<c:if test="${redirectURL ne ''}"> 
			<h3><a onClick="<portlet:namespace/>historyBack()" style="cursor: pointer;"> ${redirectName } </a>  > 
			Data Collection <liferay-ui:message key="edison-virtuallab-scienceapp-management"/>
			</h3>
		</c:if>
		
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
				<liferay-ui:message key='edison-science-appstore-toolkit-default-information' /> 
				<liferay-ui:icon-help message="edison-data-collection-descriptive-message"/>
			</h3>
			
			<div class="input-group">
				<div class="input-group-btn" align="right">
					<c:if test="${empty collection}">
						<input type="button" onclick="<portlet:namespace/>actionUpdate('<%=Constants.ADD%>');" value="<liferay-ui:message key='edison-button-save' />"  class="button02_1" />
					</c:if>
					<c:if test="${!empty collection}">
						<input class="addIp button02_2" onclick="<portlet:namespace/>goDetailView();" value="<liferay-ui:message key='edison-simulation-monitoring-table-header-detail'/>" type="button" style="width: 100px;" >
						<input class="addIp button02_1" onclick="<portlet:namespace/>actionUpdate('<%=Constants.UPDATE%>'); return false;" value="<liferay-ui:message key='edison-button-board-modify'/>" type="button">
						<input class="addIp button02_1" onclick="<portlet:namespace/>actionDelete(); return false;" value="<liferay-ui:message key='delete'/>" type="button">
					</c:if>
				</div>
			</div>
		</div>
		
		<div class="table1_list">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="35%" />
				</colgroup>
				<tbody>
					<tr>
						<th><liferay-ui:message key="edison-create-account-field-title-name"/><span class="requiredField"> *</span></th>
						<td>
							<aui:input name="name" type="text" cssClass="long_field" label="" value="${collection.name }" maxLength="100">
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
						</td>
						
						<th><liferay-ui:message key='version' /> <span class="requiredField">*</span></th>
						<td>
							<aui:input name="version" type="text" cssClass="short_field" label="" value="${collection.version }" placeholder="ex) 1.0.0">
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
						</td>
					</tr>
					
					<tr>
						<th><liferay-ui:message key="edison-content-service-language"/></th>
						<td>
							<aui:select name="targetLanguage" label="" >
							<option value="" selected="selected"><liferay-ui:message key='full' /></option>
							<%
							
							for(Locale aLocale : locales){
								String languageId = LocaleUtil.toLanguageId(aLocale);
								if(localesStr.equals("")){
									localesStr += languageId;
								}else{
									localesStr += ","+languageId;
								}
								
								String languageNm = aLocale.getDisplayName(themeDisplay.getLocale());
							%>
								<aui:option label="<%=languageNm%>" value="<%=languageId%>"/>
							<%} %>
						</aui:select>
						</td>
						<th><liferay-ui:message key="edison-workflow-public-status"/></th>
						<td>
							<aui:select name="status" label="">
								<aui:option value="public"><liferay-ui:message key='edison-appstore-status-service'/></aui:option>
								<aui:option value="private"><liferay-ui:message key='edison-appstore-status-private'/></aui:option>
							</aui:select>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key="edison-table-list-header-title"/><span class="requiredField"> *</span></th>
						<td colspan="3">
							<liferay-ui:input-localized name="title" xml="${collection.title}" cssClass="too_long_field"  type="input"/>
						</td>
					</tr>
					
					<tr>
						<th id="dataTypeInfoHeaderTd"  rowspan="${defaultRowNum + 1}">
							<liferay-ui:message key="edison-data-collection-select-data-type-list"/> <span class="requiredField">*</span> <liferay-ui:icon-help message="edison-data-collection-select-data-type-descriptive-message"/>
							<br/>
							
							<c:if test="${empty dataType }">
								<input type="button" value="<liferay-ui:message key='edison-table-list-header-select' />" class="btn btn-default" 
								onClick="<portlet:namespace/>openDataTypeView();"/>
							</c:if>
						</th>
						<c:if test="${empty dataType }">
							<td id="selectedDataTypeTd" colspan="3">
							</td>
						</c:if>
						
					</tr>
					
					<c:if test="${!empty dataType }">
						<tr>
							<td > Data Type Name </td>
							<td colspan="2">${dataType.name }</td>
						</tr>
						
						<tr>
							<td > Data Type Version </td>
							<td colspan="2">${dataType.version }</td>
						</tr>
						
						<c:if test="${!empty dataTypeEditorMap.editor }">
							<tr>
								<td > Data Type Editor </td>
								<td colspan="2">${dataTypeEditorMap.editor }</td>
							</tr>	
						</c:if>
						
						<c:if test="${!empty dataTypeEditorMap.analyzer }">
							<tr>
								<td > Data Type Analyzer </td>
								<td colspan="2">${dataTypeEditorMap.analyzer }</td>
							</tr>	
						</c:if>
						
					</c:if>
						
					<!-- Category Start -->
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
					<!-- Category END -->
					
					<tr>
						<th><liferay-ui:message key='descriptive' /></th>
						<td colspan="3">	
							<liferay-ui:input-localized name="description" xml="${collection.description}" cssClass="text_field" type="textarea"/>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key="edison-appstore-icon"/></th>
						<td colspan="3" >
							<c:choose>
								<c:when test="${!empty dcIcon }">
									<div id="<portlet:namespace/>fileIconDiv">
										<div style="cursor:pointer;display: inline-block;" onclick="<portlet:namespace/>fileDownload('${dcIcon.fileEntryId }')" >
											${dcIcon.fileTitle}
											<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
										</div>
										&nbsp;&nbsp;
										
										<img src='${contextPath}/images/icon_dustbin.png' width='13' height='14' style="cursor:pointer" 
													onClick="<portlet:namespace/>deleteSingleEdisonFile('${dcIcon.fileEntryId}');" />
									</div>
								</c:when>
								<c:otherwise>
									<aui:input type="file" name="dc_icon" label=""/>
								</c:otherwise>
							</c:choose>
	<%-- 						<aui:input type="file" name="dc_icon" label=""/> --%>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key="edison-content-main-image"/></th>
						<td colspan="3" >
							<%-- <aui:input type="file" name="dc_mainImg" label=""/> --%>
							<div id="<portlet:namespace/>fileTDArea">
								<div id="<portlet:namespace/>fileDivDefault">
									<input type="file" name="<portlet:namespace/>dc_mainImg" />
									<input type="button" value="<liferay-ui:message key='edison-button-file-add' />" class="btn btn-default" onClick="<portlet:namespace/>moreFileTag()" style="cursor:pointer;"/>
								</div>
							</div>
							
							<c:if test="${fn:length(dcMainImg) > 0}">
								<c:forEach var="mainImg" items="${dcMainImg }">
									<div style="cursor:pointer;display: inline-block;" onclick="<portlet:namespace/>fileDownload('${mainImg.fileEntryId }')" >
										${mainImg.fileTitle}
										<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
									</div>
									&nbsp;&nbsp;
									
									<img src='${contextPath}/images/icon_dustbin.png' width='13' height='14' style="cursor:pointer" 
												onClick="<portlet:namespace/>deleteSingleEdisonFile('${mainImg.fileEntryId}');" />
									<br/>
								</c:forEach>
							</c:if>
						</td>
					</tr>
					
					
				</tbody>
			</table>
			
			<br/>
			<c:if test="${!empty collection}">
				<liferay-portlet:runtime 
					portletName="edisonrelateasset_WAR_edisondefault2016portlet" 
					defaultPreferences="" 
					queryString="&entryId=${entryId}&isMgrBtn=${isAdmin}&isVirTitle=true&redirectURL=${redirectURL }&redirectName=${redirectName }"/>
			</c:if>
		</div>
	</aui:form>
</div>

<script>
<%
if(mode.equals(Constants.UPDATE)){
%>
	/* 카테고리 setting */
	$(document).ready(function () {
		changeCategory('${parentCategory}','${childrenCategory}');
	});
<%}%>

function <portlet:namespace/>actionUpdate(mode){
	
	if($("#<portlet:namespace/>name").val() == ""){
		alert(Liferay.Language.get('edison-data-collection-enter-name-alert'));
		return false;
	}else{
		var nameClass = $("#<portlet:namespace/>name").attr('class');
		console.log(nameClass.indexOf("error-field"))
		if(nameClass.indexOf("error-field") != -1){
			alert(Liferay.Language.get('edison-data-collection-enter-name-alert'));
			return false;
		}	
	}
	
	if($("#<portlet:namespace/>version").val() == ""){
		alert(Liferay.Language.get('edison-data-collection-enter-version-alert'));
		return false;
	}else{
		var nameClass = $("#<portlet:namespace/>version").attr('class');
		if(nameClass.indexOf("error-field") != -1){
			alert(Liferay.Language.get('edison-data-collection-enter-version-alert'));
			return false;
		}	
	}
	
	if($("#<portlet:namespace/>title").val() == ""){
		alert(Liferay.Language.get('edison-board-enter-title-alert'));
		return false;
	}
	
	if($("#<portlet:namespace/>dataTypeId").val() == ""){
		alert(Liferay.Language.get('edison-data-collection-not-select-data-type-alert'));
		return false;
	}
	
	if( $(":checkbox[name*='childrenCategoryCheckbox']:checked").length==0 ){
		alert(Liferay.Language.get('edison-science-appstore-category-error'));
		return false;
	}
	
	<portlet:namespace/>collectionForm.action="<%= manageDataCollectionUrl %>";
	
	<portlet:namespace/>collectionForm.encoding = "multipart/form-data";
	<portlet:namespace/>collectionForm.submit();
}

function <portlet:namespace/>actionDelete(){
	if(!confirm(Liferay.Language.get("edison-data-collection-delete-alert"))) return;
	
	<portlet:namespace/>collectionForm.action="<%= deleteDataCollectionUrl %>";
	<portlet:namespace/>collectionForm.submit();
}
function <portlet:namespace/>goDetailView(){
	location.href = "<%=detailViewURL%>";
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
//data type open Dialogue
function <portlet:namespace/>openDataTypeView(){
	AUI().use("liferay-portlet-url", function(a) {
		<%-- var portletURL = Liferay.PortletURL.createRenderURL();
			portletURL.setPortletMode("view");
			portletURL.setWindowState("pop_up");
			portletURL.setPlid('${myPagePlid}');
			portletURL.setPortletId("edisondatatypeeditor_WAR_edisonappstore2016portlet");
			portletURL.setParameter("redirectURL", "<%=themeDisplay.getURLCurrent()%>");
			portletURL.setParameter("redirectName", Liferay.Language.get('edison-appstore-myapp-list')); --%>
			
		Liferay.Util.openWindow({
			dialog: {
					width:1024,
					height:780,
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
			title: "SEARCH",
			id: "dataTypeSearchDialog",
			uri : "<%=dataTypeEditorDialogueURL%>"
		});
	}); 
}

var fileIndex = 0;
function <portlet:namespace/>moreFileTag()
{	
	fileIndex++;
	var frmTag = "<div id=\"<portlet:namespace/>fileDiv"+fileIndex+"\">";
	frmTag += "<input type=\"file\" name=\"<portlet:namespace/>dc_mainImg\" />&nbsp;";
	frmTag += "<input type=\"button\" value=\"delete\" style=\"cursor:pointer;\" class=\"btn btn-default\" onClick=\"<portlet:namespace/>deleteFileTag(\'<portlet:namespace/>fileDiv"+fileIndex+"\')\" />";
	frmTag += "</div>";
	
	$("#<portlet:namespace/>fileTDArea").append(frmTag);
}

function <portlet:namespace/>deleteFileTag(objId){	
	$("#"+objId).remove();
}

function <portlet:namespace/>fileDownload(fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+fileEntryId;
}

function <portlet:namespace/>deleteSingleEdisonFile(fileEntryId){
	if(!confirm(Liferay.Language.get('file-delete-confirm'))) return;	
	var deleteForm = {
		"<portlet:namespace/>fileEntryId" : fileEntryId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=deleteSingleEdisonFileURL%>",
		data: deleteForm,
  		async : false,
		success: function(data) {
			var resultMsg = data.resultMsg;
			if(resultMsg=="SUCCESS"){
				alert(Liferay.Language.get('edison-data-delete-success'));
				
				$fileIcon = $("#<portlet:namespace/>fileIconDiv");
				$fileIcon.empty();
				
				$("<input/>").attr("id","<portlet:namespace/>dc_icon")
							 .attr("name","<portlet:namespace/>dc_icon")
							 .attr("type","file")
							 .appendTo($fileIcon);
			}else if(resultMsg=="DELETE_FAIL"){
				alert("delete file error!");	
			}
		},error:function(data,e){ 
			alert("deleteFile System error!");	
		}
	});
}

function <portlet:namespace/>retrieveGetDataType(dataTypeId){
	
	jQuery.ajax({
		type: "POST",
		url: "<%=retrieveMapdataTypeURL%>",
		data: {
			"<portlet:namespace/>dataTypeId" : dataTypeId
		},
  		async : false,
		success: function(data) {
			var dataTypeMap = data.dataTypeObj;
			var dataTypeEditorMap = data.dataTypeObj.dataTypeEditorMap;
			var resultMsg = data.resultMsg;
			var defaultRowNum = data.dataTypeObj.defaultRowNum+1;

			$("tr[class$=openData]").remove();
			var tr = $("#dataTypeInfoHeaderTd").parent().next();
			
			$("#dataTypeInfoHeaderTd").attr("rowspan", defaultRowNum);
			$("#selectedDataTypeTd").remove();
			
			var dataTypeNameTr = $("<tr/>").addClass("openData");
			$("<td/>").text("Data Type Name").appendTo(dataTypeNameTr);
			$("<td/>").attr("colspan", "2").text(dataTypeMap.name).appendTo(dataTypeNameTr);
			tr.before(dataTypeNameTr);
			
			var dataTypeVersionTr = $("<tr/>").addClass("openData");
			$("<td/>").text("Data Type Version").appendTo(dataTypeVersionTr);
			$("<td/>").attr("colspan", "2").text(dataTypeMap.version).appendTo(dataTypeVersionTr);
			tr.before(dataTypeVersionTr);
			
			if(dataTypeEditorMap.editor != null){
				var dataTypeEditorTr = $("<tr/>").addClass("openData");
				$("<td/>").text("Data Type Editor").appendTo(dataTypeEditorTr);
				$("<td/>").attr("colspan", "2").text(dataTypeEditorMap.editor).appendTo(dataTypeEditorTr);
				tr.before(dataTypeEditorTr);
			}
			
			if(dataTypeEditorMap.analyzer != null){
				var dataTypeAnalyzerTr = $("<tr/>").addClass("openData");
				$("<td/>").text("Data Type Editor").appendTo(dataTypeAnalyzerTr);
				$("<td/>").attr("colspan", "2").text(dataTypeEditorMap.analyzer).appendTo(dataTypeAnalyzerTr);
				tr.before(dataTypeAnalyzerTr);
			}
			
			$("#<portlet:namespace/>dataTypeId").val(dataTypeId);
			/* if(resultMsg == "FAIL"){
				var tr = $("<tr></tr>").appendTo(dataTypeTableBody);
				$("<td></td>").addClass("TC")
						  .text(Liferay.Language.get('edison-there-are-no-data'))
						  .attr("colspan","2")
						  .appendTo(tr);	
				
				alert("selected data type error");
			}else{
				if(dataTypeMap != null){
					var tr = $("<tr></tr>").appendTo(dataTypeTableBody);
					$("<td></td>").addClass("TC").html(dataTypeMap.name).appendTo(tr);
					$("<td></td>").addClass("TC").html(dataTypeMap.version).appendTo(tr);
					
					
				}else{
					var tr = $("<tr></tr>").appendTo(dataTypeTableBody);
					$("<td></td>").addClass("TC")
							  .text(Liferay.Language.get('edison-there-are-no-data'))
							  .attr("colspan","2")
							  .appendTo(tr);	
				}
			} */
		},error:function(data,e){ 
			alert("selected data type error");
		}
	});
}

/*redirect URL Page Move*/
function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}
</script>
<aui:script>
	Liferay.provide(window, 'updateDataFromPopUp', function(jsonDataFromPopUp) {
		<portlet:namespace/>retrieveGetDataType(jsonDataFromPopUp);
		<portlet:namespace/>closePopup('dataTypeSearchDialog');
		$("body").css('overflow','');
	});
	
	Liferay.provide(window, 'redirectURL', function(URL) {
		location.href = URL;
	});
	
	Liferay.provide(window,'<portlet:namespace/>closePopup',
		function(popupIdToClose) {
			Liferay.Util.getWindow(popupIdToClose).destroy(); // You can try toggle/hide whatever You want
			},
		['liferay-util-window']
	);
</aui:script>