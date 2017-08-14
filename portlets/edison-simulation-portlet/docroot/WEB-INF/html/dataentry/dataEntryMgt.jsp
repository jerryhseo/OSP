<%@page import="org.kisti.edison.util.EdisonHttpUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<style>
.aui .control-group {
	margin-bottom: 0px;
}
.aui .too_long_field{
	width: 500px;
}
.dataEntryButtonDiv{
	text-align: right;
	margin-bottom: 10px;
}

.edison .table1_list td.titleTr{
	border-right: 1px solid #c2c2c2;
}
.aui .tooltip.top {
	display: block;
}
.aui .tooltip {
	display: none;
}
.edison .table1_list a{
	color : #00aaff;
}
</style>

<liferay-portlet:renderURL var="dataCollectionDialogueURL" portletName="edisondatacollection_WAR_edisonsimulationportlet" windowState="<%=LiferayWindowState.POP_UP.toString() %>" >
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="retrieveMapCollectionURL" escapeXml="false" id="retrieveMapCollection" copyCurrentRenderParameters="false"/>

<liferay-portlet:actionURL secure="<%=request.isSecure()%>" var="manageDataEntryURL">
	<portlet:param name="myAction" value="manageDataEntry" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchLine" value="${searchLine}" />
	
</liferay-portlet:actionURL>

<liferay-portlet:actionURL secure="<%=request.isSecure()%>" var="deleteDataEntryURL">
	<portlet:param name="myAction" value="deleteDataEntry" />
	<liferay-portlet:param name="dataEntryId" value="${dataEntry.entryId}" />
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="deleteSingleEdisonFileURL" escapeXml="false" id="deleteSingleEdisonFile" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="dataEntryId" value="${dataEntry.entryId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:renderURL var="openDataDetailUrl" portletName="edisondatacollection_WAR_edisonsimulationportlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myRender" value="detailViewDataCollection" />
</liferay-portlet:renderURL>

<%
	String thisPageRedirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
%>
<aui:form name="dataEntryManageForm" method="post">
	<aui:input type="hidden" name="collectionId" value="${collectionInfoMap.collection.collectionId }"/>
	<aui:input type="hidden" name="mode" value=""/>
	
	<c:if test="${redirectURL ne ''}"> 
		<h3><a onClick="<portlet:namespace/>historyBack()" style="cursor: pointer;"> ${redirectName } </a>  > 
		Data Entry <liferay-ui:message key="edison-virtuallab-scienceapp-management" />
		</h3>
	</c:if>
	<div class="virtitlebox">
		<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
		<div class="virtitle">
			<liferay-ui:message key='edison-science-appstore-toolkit-default-information' />
		</div>
		<div style="width:60%; float:right; text-align:right; padding-top:15px;">
			<div class="contentbtnGroup">
			<c:if test="${empty dataEntry}">
				<input type="button" onclick="<portlet:namespace/>actionUpdate('<%=Constants.ADD%>');" value="<liferay-ui:message key='edison-button-save' />"  class="button02_1" />
			</c:if>
			<c:if test="${!empty dataEntry}">
				<input class="addIp button02_1" onclick="<portlet:namespace/>actionUpdate('<%=Constants.UPDATE%>'); return false;" value="<liferay-ui:message key='edison-button-board-modify'/>" type="button">
				<input class="addIp button02_1" onclick="<portlet:namespace/>actionDelete(); return false;" value="<liferay-ui:message key='delete'/>" type="button">
			</c:if>
			</div>
		</div>
	</div>
	
	<div class="h10"></div>
		
	<div class="table1_list">
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed;">
			<colgroup>
				<col width="30%" />
				<col width="20%" />
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr>
					<th ><liferay-ui:message key="edison-table-list-header-file"/><span class="requiredField"> *</span> <liferay-ui:icon-help message="edison-data-entry-descriptive-message"/></th>
					<td colspan="2">
						<c:if test="${empty monitoringResultFileId}">
							<c:choose>
								<c:when test="${!empty dataEntryFile}">
									<div id="<portlet:namespace/>fileIconDiv">
										<div style="cursor:pointer;display: inline-block;" onclick="<portlet:namespace/>fileDownload('${dataEntryFile.fileEntryId }')" >
											${dataEntryFile.title}
											<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
										</div>
										&nbsp;&nbsp;
										
									</div>
								</c:when>
								<c:otherwise>
									<input type="file" name="<portlet:namespace/>entryFile" />
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${!empty monitoringResultFileId}">
							${monitoringResultFileNm }
						</c:if> 
					</td>
				</tr>
				<tr>
					<th id="dataCollectionInfoHeaderTd" rowspan="${defaultRowNum+1}">
						Data Collection<span class="requiredField"> *</span> <liferay-ui:icon-help message="edison-data-entry-select-data-collection-descriptive-message"/>
						
						<c:if test="${empty collectionInfoMap.collection}">
						<br/>
						<input type="button" value="<liferay-ui:message key='edison-table-list-header-select' />" class="button06" 
						onClick="<portlet:namespace/>openDataCollectionSelectedView();"/>
						</c:if>
					</th>
					<c:if test="${empty collectionInfoMap.collection}">
 						<td id="selectedDataCollectionTd" colspan="2">
 						</td>
					</c:if>
<!-- 					<td id="selectedDataCollectionTd"> -->
						<%-- <c:if test="${!empty collectionInfoMap.collection }">
							<div style="float:left;width:40%;">${collectionInfoMap.collection.name }</div>
							<div style="float:left;width:20%;">${collectionInfoMap.collection.version }</div>
							<div style="float:left;width:40%;">${collectionInfoMap.dataType.name } ( V${collectionInfoMap.dataType.version })</div>
						</c:if> --%>
<!-- - 					</td>  -->
				</tr>
				<c:if test="${!empty collectionInfoMap.collection }">
					<tr>
						<td> Collection Name </td>
						<td><a href="#;return false;" onclick="<portlet:namespace/>moveCollection(${collectionInfoMap.collection.collectionId })">${collectionInfoMap.collection.name }</a></td>
						
					</tr>
					<tr>
						<td> Collection Version </td>
						<td>${collectionInfoMap.collection.version }</td>
					</tr>
					<tr>
						<td> Data Type ( Version )</td>
						<td>${collectionInfoMap.dataType.name } (&nbsp;V${collectionInfoMap.dataType.version }&nbsp;)</td>
					</tr>
					<c:if test="${!empty  collectionInfoMap.dataTypeEditorMap.editor }">
						<tr>
							<td> Data Type Editor</td>
							<td>${ collectionInfoMap.dataTypeEditorMap.editor }</td>
						</tr>
					</c:if>
					<c:if test="${!empty collectionInfoMap.dataTypeEditorMap.analyzer }">
					<tr>
						<td> Data Type Analyzer</td>
						<td>${ collectionInfoMap.dataTypeEditorMap.analyzer }</td>
					</tr>
					</c:if>
				</c:if>
				<tr>
					<th><liferay-ui:message key="edison-simulation-execute-simulation-description"/><span class="requiredField"> *</span></th>
					<td colspan="2">
						<liferay-ui:input-localized name="comment" xml="${dataEntry.comments }" cssClass="too_long_field"  type="input"></liferay-ui:input-localized>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

</aui:form>

<script>
$(function(){
	
	if("${alreadyExistJobDataMap.alreadyExistDataEntry}" == "true"){
		var collectionName = "${alreadyExistJobDataMap.collectionName}";
		var dataEntryFileName = "${alreadyExistJobDataMap.dataEntryFileName}";

		alert(Liferay.Language.get("edison-data-entry-duplication-subjectid-inputdata",["'"+collectionName+"'", "'"+dataEntryFileName+"'"]));
		//alert("해당 Job은 DataCollection("+collectionName + ")에 파일(" + dataEntryFileName +")이 등록되어있습니다. 관리자에게 문의하세요");
		javascript:history.back(-1); 
	}
});

function <portlet:namespace/>actionUpdate(mode){
	$("#<portlet:namespace/>mode").val(mode);
	
	//파일체크
	var fileExistCnt = 0;
	var entryFileInputSize = $("input[name=<portlet:namespace/>entryFile]").length;
	
	if(entryFileInputSize > 0){
		$("input[name=<portlet:namespace/>entryFile]").each(function(){
			var fileValue = $(this).val();
			
			if(fileValue != ""){
				fileExistCnt++;
			} 
		});
		
		if(fileExistCnt == 0){
			alert(Liferay.Language.get("edison-simulation-monitoring-post-processor-choice-file"));
			return false;
		}
	}
	
	//collection 선택 체크
	var collectionId = $("#<portlet:namespace/>collectionId").val();

	if(collectionId == ""){
		alert(Liferay.Language.get("edison-data-entry-not-select-collection-alert"));
		return false;
	}
		
	//설명 체크
	var comments = $("#<portlet:namespace/>comment").val();
	if(comments == ""){
		alert(Liferay.Language.get("edison-error-another-language-alret-message"));
		return false;
	}
	
	var URL = "<%= manageDataEntryURL %>";
	URL += "&<portlet:namespace/>dataEntryId=${dataEntry.entryId}";
	
	if('${simulationSubjectId}' != "" ){
		URL += "&<portlet:namespace/>groupId=${groupId}";
		URL += "&<portlet:namespace/>jobUuid=${jobUuid}";
		URL += "&<portlet:namespace/>jobSeqNo=${jobSeqNo}";
		URL += "&<portlet:namespace/>monitoringResultFileNm=${monitoringResultFileNm}";
		URL += "&<portlet:namespace/>monitoringResultFileId=${monitoringResultFileId}";
		URL += "&<portlet:namespace/>simulationSubjectId=${simulationSubjectId}";
		URL += "&<portlet:namespace/>simulationUuid=${simulationUuid}";
	}		
	<portlet:namespace/>dataEntryManageForm.action=URL;
	<portlet:namespace/>dataEntryManageForm.encoding = "multipart/form-data";
	<portlet:namespace/>dataEntryManageForm.submit();
}

function <portlet:namespace/>actionDelete(){
	if(!confirm(Liferay.Language.get("data-delete-confirm"))){
		return;
	}

	<portlet:namespace/>dataEntryManageForm.action="<%= deleteDataEntryURL %>";
	<portlet:namespace/>dataEntryManageForm.submit();
}
function <portlet:namespace/>openDataCollectionSelectedView(){
	AUI().use("liferay-portlet-url", function(a) {
		Liferay.Util.openWindow({
			dialog: {
					width:1024,
					height:850,
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
			id: "dataCollectionSearchDialog",
			uri : "<%=dataCollectionDialogueURL.toString()%>"
		});
	}); 
}

/*redirect URL Page Move*/
function <portlet:namespace/>historyBack(){
		//파일체크
/* 	var fileExistCnt = 0;
	var entryFileInputSize = $("input[name=<portlet:namespace/>entryFile]").size();
	
	if(entryFileInputSize > 0){
		$("input[name=<portlet:namespace/>entryFile]").each(function(){
			var fileValue = $(this).val();
			
			if(fileValue != ""){
				fileExistCnt++;
			}
		});
		
		if(fileExistCnt == 0){
			alert(Liferay.Language.get("edison-simulation-monitoring-post-processor-choice-file"));
			return false;
		}
	} */
	
	location.href = "${redirectOrignURL}";
}

function <portlet:namespace/>retrieveGetDataCollection(collectionId){
	
	
	jQuery.ajax({
		type: "POST",
		url: "<%=retrieveMapCollectionURL%>",
		data: {
			"<portlet:namespace/>collectionId" : collectionId
		},
  		async : false,
		success: function(data) {
			var collectionMap = data.dataCollection;
			var dataTypeMap =  data.dataCollection.dataType;
			var dataTypeEditorMap =  data.dataCollection.dataTypeEditorMap;
			var resultMsg = data.resultMsg;
			var defaultRowNum = data.defaultRowNum+1;
			
			$("tr[class$=openData]").remove();
			
			var tr = $("#dataCollectionInfoHeaderTd").parent().next();
			
			$("#dataCollectionInfoHeaderTd").attr("rowspan", defaultRowNum);
			$("#selectedDataCollectionTd").remove();
			
			var collectionNameTr = $("<tr/>").addClass("openData");
			$("<td/>").text("Collection Name").appendTo(collectionNameTr);
			$("<td/>").text(collectionMap.name).appendTo(collectionNameTr);
			tr.before(collectionNameTr);
			
			var collectionVersionTr = $("<tr/>").addClass("openData");
			$("<td/>").text("Collection Version").appendTo(collectionVersionTr);
			$("<td/>").text(collectionMap.version).appendTo(collectionVersionTr);
			tr.before(collectionVersionTr);
			
			var dataTypeInfoTr = $("<tr/>").addClass("openData");
			var dataTypeInfo = dataTypeMap.name + " ( V" +dataTypeMap.version + " )";
			$("<td/>").text("Data Type Name ( Version )").appendTo(dataTypeInfoTr);
			$("<td/>").text(dataTypeInfo).appendTo(dataTypeInfoTr);
			tr.before(dataTypeInfoTr);
			
			if(dataTypeEditorMap.editor != null){
				var dataTypeEditorTr = $("<tr/>").addClass("openData");
				$("<td/>").text("Data Type Editor").appendTo(dataTypeEditorTr);
				$("<td/>").text(dataTypeEditorMap.editor).appendTo(dataTypeEditorTr);
				tr.before(dataTypeEditorTr);
			}
			
			if(dataTypeEditorMap.analyzer != null){
				var dataTypeAnalayzerTr = $("<tr/>").addClass("openData");
				$("<td/>").text("Data Type Analyzer").appendTo(dataTypeAnalayzerTr);
				$("<td/>").text(dataTypeEditorMap.analyzer).appendTo(dataTypeAnalayzerTr);
				tr.before(dataTypeAnalayzerTr);
			}
			
// 			$("#selectedDataCollectionTd").hide();
			
			/* $("<div/>").css("float","left").css("width", "40%").html(collectionMap.name).appendTo(selectCollectionTd);
			$("<div/>").css("float","left").css("width", "20%").html(collectionMap.version).appendTo(selectCollectionTd);
			
			var dataTypeInfo = dataTypeMap.name + " ( V" +dataTypeMap.version +" )";
			$("<div/>").css("float","left").css("width", "40%").html(dataTypeInfo).appendTo(selectCollectionTd); */
			
			$("#<portlet:namespace/>collectionId").val(collectionMap.collectionId);
		},error:function(data,e){ 
			alert("selected data type error");
		}
	});
}

function <portlet:namespace/>fileDownload(fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+fileEntryId;
}

function <portlet:namespace/>moveCollection(collectionId){
    var thisPortletNamespace = "_edisondatacollection_WAR_edisonsimulationportlet_";
    var params = "&" + thisPortletNamespace + "collectionId=" + collectionId;
    window.open("<%=openDataDetailUrl%>" + params);
}
</script>

<aui:script>
	Liferay.provide(window, 'updateDataFromPopUp', function(jsonDataFromPopUp) {
 		<portlet:namespace/>retrieveGetDataCollection(jsonDataFromPopUp);
		<portlet:namespace/>closePopup('dataCollectionSearchDialog');
		$("body").css('overflow','');
	});
	
	Liferay.provide(window,'<portlet:namespace/>closePopup',
		function(popupIdToClose) {
			Liferay.Util.getWindow(popupIdToClose).destroy(); // You can try toggle/hide whatever You want
			},
		['liferay-util-window']
	);
</aui:script>
