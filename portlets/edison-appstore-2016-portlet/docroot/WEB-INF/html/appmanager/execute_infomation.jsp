<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="org.kisti.edison.science.Exception.ScienceAppException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="org.kisti.edison.science.service.constants.ScienceAppConstants"%>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>

<%
	String mode = GetterUtil.get(request.getAttribute("mode"), "");
	String fileErrorMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-science-appstore-toolkit-file-size-error-message");
%>

<script type="text/javascript" src="${contextPath}/js/jquery.form.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/main.js"></script>


<portlet:actionURL var="submitURL" copyCurrentRenderParameters="false" name="appAction">
	<portlet:param name="clickTab" value="${clickTab}"/>
	<portlet:param name="actionType" value="exeInfomation"/>
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
	<portlet:param name="isPort" value="${isPort}"/>
	
	<portlet:param name="searchValue" value="${searchValue}"/>
	<portlet:param name="searchOption" value="${searchOption}"/>
	<portlet:param name="searchStatus" value="${searchStatus}"/>
	<portlet:param name="p_curPage" 	value="${p_curPage}" />
	<portlet:param name="redirectURL" 	value="${redirectURL}" />
</portlet:actionURL>

<liferay-portlet:renderURL var="libChangeRenderURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
	<portlet:param name="myRender" value="libChangeRender"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="commonLibRenderURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
	<portlet:param name="myRender" value="commonLibRender"/>
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="searchRequestLibURL" escapeXml="false" id="searchRequestLib" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="deployWarURL" escapeXml="false" id="deployWar" copyCurrentRenderParameters="false"/>


<liferay-portlet:resourceURL var="addScienceAppFileURL" id="addScienceAppFile" copyCurrentRenderParameters="false">
	<portlet:param name="appName" value="${data.name}"/>
	<portlet:param name="appVersion" value="${data.version}"/>
	<portlet:param name="appId" value="${scienceAppId}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="fileUploadByGitHubCompileURL" id="fileUploadByGitHubCompile" copyCurrentRenderParameters="false">
	<portlet:param name="appName" value="${data.name}"/>
	<portlet:param name="appVersion" value="${data.version}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="getClusterListURL" escapeXml="false" id="getClusterList" copyCurrentRenderParameters="false"/>

<style type="text/css">
    .exe-field-wrapper { margin-bottom: 0px !important; }
    .exe-field-wrapper > input {display: inline !important;}
    .deploy { float: right; margin-top: 0px !important; }
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
	
	#progress_bar_wrap2 {
		width:500px;  
		padding: 10px 30px 30px 30px; 
		background-color:#f7f7f7;
		border-top: 1px solid #e8e8e8; 
	    border-right: 1px solid #e8e8e8; 
	    border-left: 1px solid #e8e8e8; 
	    border-bottom: 1px solid #e8e8e8; 
	    overflow-y: hidden;
	}
	#progress_bar_line {
		padding:1px; 
		border-top: 1px solid #CCCCCC; 
	    border-right: 1px solid #CCCCCC; 
	    border-left: 1px solid #CCCCCC; 
	    border-bottom: 1px solid #CCCCCC; 
	}
	#progress_bar2 {
		width: 0%;
		background-image:url(/edison-appstore-2016-portlet/images/progress_bar.jpg);
		height:20px; 
		text-align:right; 
		line-height:15px; 
		font-size:11px; color:#000000;
	} 
	.gitHubResultDiv{
		background-color: #f5f5f5;
		border-radius: 6px;
		padding: 20px; 
		border: solid 1px #d1d1d1; 
		margin-top: 10px; 
	}
	.aui .text_field{
		width: 100%;
		resize: none;
		height:auto;
		margin-bottom: 10px;
	}
	.aui .tooltip.top {
		display: block;
	}
	.aui .tooltip {
		display: none;
	}
	
</style>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="200" style="display: none;"/>
<div class="edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			<liferay-ui:message key='edison-appstore-execute-information' />
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
				<c:if test="${data.status eq '1901002' && isAdmin}">
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
			
			<input class="button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.ADD%>');" value="<liferay-ui:message key='edison-button-save'/>" type="button">
			
			<c:if test="${ownerThan}">
				<input class="button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.DELETE%>');return false;" value="<liferay-ui:message key='delete'/>" type="button">
			</c:if>
		</div>
	</div>
</div>

<div class="table1_list">
	<aui:form name="frm" method="POST" action="<%=submitURL%>">
		<aui:input name="actionMode" value="${mode}" type="hidden"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data" >
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<tr>
				<th><liferay-ui:message key='edison-table-list-header-file-nm' /><span class="requiredField"> *</span></th>
				<td colspan="3">
                    <aui:field-wrapper cssClass="exe-field-wrapper">
    					<aui:input name="exeFileName" type="text" cssClass="long_field noupdate" label="" value="${data.exeFileName}">
    						<aui:validator name="required"/>
    					</aui:input>
                        <c:if test="${!data.isPort && (isAdministrator || isSiteAdministrator) && data.sourceFileId ne null}">
                            <aui:button cssClass="deploy btn btn-sm btn-primary" value="Deploy" 
                                onClick="deployWar('${data.sourceFileTitle}', '${data.sourceFileId }')"/>
                        </c:if>
                    </aui:field-wrapper>
				</td>
			</tr>
			<tr>
				<th>Open Level</th>
				<td id="opendLevelTd">
					<aui:select name="openLevel" label="" onChange="changeOpenLevel(this.value);" cssClass="noupdate">
						${openLevelOptions}
					</aui:select>
				</td>
				<th id="sourceFileTh">Source File</th>
				<td id="sourceFileTd">
					<input type="file" id="<portlet:namespace/>sourceFile" name="<portlet:namespace/>sourceFile" disabled="disabled" onchange="<portlet:namespace/>disableDeploy()">
					<c:if test="${data.sourceFileId ne null}">
						<div class="down_date sourceFileClass"  onclick="<portlet:namespace/>fileDownload('${data.sourceFileId }')" style="cursor: pointer;display: inline-block;">
							${data.sourceFileTitle}
						</div>
						<img src='${contextPath}/images/icon_dustbin.png' class="sourceFileClass noUpdateHidden" 
                        width='13' height='14' style="cursor:pointer" onClick="<portlet:namespace/>deleteFile('${data.sourceFileId}','soruceFile','sourceFileClass');"/>
					</c:if>
                    
				</td>
			</tr>
			<c:choose>
				<c:when test="${data.isPort}">
					<tr>
						<th>App Type</th>
						<td colspan="3">
							<aui:select name="appType" label="" cssClass="noupdate" onChange="changeAppType(this.value);">
								${appTypeOptions}
							</aui:select>
						</td>
					</tr>
					<tr class="is-not-dwn-only">
						<th>Run Type</th>
						<td colspan="3">
							<%-- <aui:select name="runType" label="" cssClass="noupdate">
								${runTypeOptions}
								${parallelOptions}
							</aui:select> --%>
							<select id="<portlet:namespace/>runType" name="<portlet:namespace/>runType" class="aui-field-select noupdate">
								${runTypeOptions}
								<optgroup label="Parallel">
									${parallelOptions}
								</optgroup>
							</select>
						</td>
						<%-- <th>PARALLEL_Module</th>
						<td>
							<aui:select name="parallelModule" label="" disabled="<%=true%>" cssClass="runTypeDisabled">
								<option value=""><liferay-ui:message key='nobody' /></option>
								${parallelOptions}
							</aui:select>
						</td> --%>
					</tr>
					<tr class="is-not-dwn-only data-parallel-cpu">
						<th>Min CPU</th>
						<td>
							<aui:input name="minCpus" type="text" label="" cssClass="short_field runTypeDisabled" disabled="" value="${data.minCpus}">
								<aui:validator name="number"/>
								<aui:validator name="maxLength">2</aui:validator>
								<aui:validator name="max">32</aui:validator>
							</aui:input>
						</td>
						<th rowspan="2">Default CPU</th>
						<td rowspan="2">
							<aui:input name="defaultCpus" type="text" label="" cssClass="short_field runTypeDisabled" disabled="" value="${data.defaultCpus}">
								<aui:validator name="number"/>
								<aui:validator name="maxLength">2</aui:validator>
								<aui:validator name="max">32</aui:validator>
							</aui:input>
						</td>
					</tr>
					<tr class="is-not-dwn-only data-parallel-cpu">
						<th>Max CPU</th>
						<td>
							<aui:input name="maxCpus" type="text" label="" cssClass="short_field runTypeDisabled" disabled="" value="${data.maxCpus}">
								<aui:validator name="number"/>
								<aui:validator name="maxLength">2</aui:validator>
								<aui:validator name="max">32</aui:validator>
							</aui:input>
						</td>
					</tr>
					<tr class="is-not-dwn-only">
						<th>Cluster</th>
						<td colspan="3">
							<%-- <select name="<portlet:namespace/>cluster" id="<portlet:namespace/>cluster" class="form-control filter">
							</select> --%>
							<aui:select name="cluster" label="" cssClass="noupdate">
							</aui:select>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<th>App Type</th>
						<td>
							<aui:select name="appType" label="" onChange="changeAppType(this.value);" cssClass="noupdate">
								${appTypeOptions}
							</aui:select>
						</td>
						<th>Editor Type</th>
						<td>
							<aui:select name="editorType" label="" disabled="<%=false%>">
								${editorTypeOptions}
							</aui:select>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</aui:form>
</div>

	
	
<div class="is-not-dwn-only">
<c:if test="${data.isPort}">
	<div class="edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				<liferay-ui:message key="edison-appstore-file-compile"/>
			</h3>
		</div>
	</div>
	<div style="margin:0 auto;text-align: center;">
		<textarea id="commandTextArea" disabled="disabled" class="text_field" rows="3">${ binFolderListToStr}</textarea>
	</div>

	<div class="table1_list">
		<aui:form name="scienceAppFileForm" method="POST" action="<%=addScienceAppFileURL%>" enctype="multipart/form-data" onSubmit="return false;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data" >
				<colgroup>
					<col width="15%">
					<col width="*">
					<col width="15%">
					<col width="20%">
				</colgroup>
				<tr>
					<th><liferay-ui:message key="edison-appstore-compiler-upload-option"/> <liferay-ui:icon-help message="edison-appstore-compiler-upload-option-descriptive-message"/></th>
					<td  colspan="3">
						
						<aui:select id="uploadSelect" name="uploadSelect" label="" cssClass="noupdate" onChange="changeUploadOption(this.value);">
							<aui:option value="compile">With Compile</aui:option>
							<aui:option value="upload">Upload</aui:option>
						</aui:select>
					</td>
				</tr>
				<tr id="selectByuploadSelect" style="display: none;">
					<th><liferay-ui:message key="edison-appstore-compiler-upload-case"/> <liferay-ui:icon-help message="edison-appstore-compiler-upload-case-descriptive-message"/></th>
					<td colspan="3" >
						<aui:select name="uploadCaseSelect" label="" cssClass="noupdate">
							<aui:option value="update">Update</aui:option>
							<aui:option value="clean">Clean</aui:option>
						</aui:select>
					</td>
					
				</tr>
				<tr id="selectByCompileSelect" style="display: none;">
					<th><liferay-ui:message key="edison-button-upload"/> <liferay-ui:icon-help message="edison-appstore-compiler-descriptive-message"/></th>
					<td colspan="3" >
						<aui:select name="gitUploadCaseSelect" label="" cssClass="noupdate" onChange="changeUploadCaseSelect('file');">
							<%-- <aui:option value="url">URL</aui:option> --%>
							<aui:option value="file">File</aui:option>
						</aui:select>
					</td>
					
				</tr>
				<tr id="<portlet:namespace/>uploadOption_upload" class="uploadOptionTr" style="display: none;">
					<th><liferay-ui:message key='edison-science-appstore-exe-file' /></th>
					<td>
						<aui:input name="scienceAppFile" type="file" label="">
							<aui:validator name="required"/>
							<aui:validator name="acceptFiles" >'gz,tar,zip'</aui:validator>
							<aui:validator  name="custom"  errorMessage="<%=fileErrorMsg%>"> 
									function (val, fieldNode, ruleValue) {
										var fileObj = document.getElementById("<portlet:namespace/>scienceAppFile");
										var returnStatus = false;
										if(typeof fileObj.files[0] != "undefined"){
											var fileSize = Math.ceil(fileObj.files[0].size/1024);
											if(fileSize<=200*1024){
												returnStatus = true;
											}
										}else{
											returnStatus = true;
										}
										
										return returnStatus;
									}
								</aui:validator>
						</aui:input>
					</td>
					<td class="TC">
						<input class="button02_1" value="file save" type="submit" id="<portlet:namespace/>fileSave"/>
					</td>
					<td>
						<span id="fileUpladMsg">
							<c:if test="${exeFileUpload}">
								<liferay-ui:message key='edison-science-appstore-toolkit-file-upload-success-message' />
							</c:if>
						</span>
					</td>
				</tr>
				<tr id="<portlet:namespace/>uploadOption_compile" class="uploadOptionTr" style="display: none;">
					<th>EDISON GitHub URL</th>
					<td colspan="3" ><a style="color:#00aaff" href="https://github.com/sp-edison" target="_blank">https://github.com/sp-edison</a></td>
				</tr>
				<tr id="<portlet:namespace/>uploadOption_compileUrl" class="uploadOptionTr" style="display: none;">
					<th>GitHub URL</th>
					<td colspan="3">
						<div class="input-group">
							<input type="text" class="field too_long_field noupdate" id="<portlet:namespace/>gitHubUrl" value="${scienceAppCompile.compileUrl }"/><br/>
							<span class="input-group-btn">
								<button class="btn btn-info"  id="<portlet:namespace/>gitHubCompileBtn" onclick="<portlet:namespace/>gitHubCompile();" type="button"><span class="icon-search"> compile</span></button>
							</span>
						</div>
					</td>
				</tr>
			</table>
		
		
		
			<div class="gitHubResultDiv" id="<portlet:namespace/>uploadGitHubInput">
				${scienceAppCompile.result}
			</div>
		</aui:form>
	</div>
</c:if>



<c:if test="${data.isPort}">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				<liferay-ui:message key='edison-science-appstore-library-request' />
			</h3>
			<div class="btn-group pull-right">
				<c:if test="${!isOsWindow}">
				</c:if>
					<button class="btn btn-default" onclick="<portlet:namespace/>openCommonLibPopup();" type="button"><span class="icon-search"> <liferay-ui:message key='views' /></span></button>
				<button class="btn btn-default" onclick="<portlet:namespace/>RequestLibPopup();" type="button"><span class="icon-arrow-right"> <liferay-ui:message key='edison-appstore-request' /></span></button>
			</div>
		</div>
		
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<th width="10%"><liferay-ui:message key='edison-table-list-header-index' /></th>
				<th width="*"><liferay-ui:message key='edison-table-list-header-file-nm' /></th>
				<th width="20%"><liferay-ui:message key='version' /></th>
				<th width="20%"><liferay-ui:message key='edison-table-list-header-date'/></th>
				<th width="15%"><liferay-ui:message key='edison-table-list-header-status' /></th>
			</thead>
			<tbody id="<portlet:namespace/>libraryRequestList">
				
			</tbody>
		</table>
		<div class="text-center" id="pageListDiv"></div>
	</div>
</c:if>
</div>
<div id="request-lib-dialog" title="request-lib" style="display: none;">
	
</div>

<div id="common-lib-dialog" title="common-lib" style="display: none;">
	
</div>
<!-- 	Progress Bar	  -->
<div id="progress_bar_wrap2" style="display: none;">
    <div id="progress_bar_line">
        <div id="progress_bar2"><span id="percent"></span></div>    
    </div>
</div>

<script type="text/javascript">
var <portlet:namespace/>isDisableDeploy = false;
<%
if(mode.equals(Constants.UPDATE)){
%>
	$(document).ready(function () {
	    $('.deploy').hide();
	    changeOpenLevel('${data.openLevel}');
//		changeRunType('${data.runType}');
		changeAppType('${data.appType}');
// 		<portlet:namespace/>noUpdateDisabled('${data.status}');
		<portlet:namespace/>getClusterList();
		
		if('${data.runType}' == "<%=ScienceAppConstants.APP_RUNTYPE_SEQUENTIAL%>"){
			$("#<portlet:namespace/>minCpus").val('0');
			$("#<portlet:namespace/>maxCpus").val('0');
			$("#<portlet:namespace/>defaultCpus").val('0');
			$(".data-parallel-cpu").hide();
		}
		
	});
<%} else { %>
	$(document).ready(function () {
		<portlet:namespace/>getClusterList();
		$('.deploy').hide();
	});
<%}%>

/* 클러스터 리스트 가져오기 */
function <portlet:namespace/>getClusterList(){
	$.ajax({
		type: "POST",
		url:"<%=getClusterListURL%>",
		async : true,
		data : {"<portlet:namespace/>cluster" : "${cluster}"},
		dataType: 'json',
		success: function(data) {
			var clusterSelect = $("#<portlet:namespace/>cluster");
			var clusterList = data.clusterList;
			clusterSelect.append(clusterList);
		},error:function(jqXHR, textStatus, errorThrown){
			alert("Get Cluster Error");
		},complete:function(){
		}
	});
}

AUI().ready(function() {
	
	// Default 'With Compile' Selected
	changeUploadOption('compile');
		
	<portlet:namespace/>searchRequestLib('1');
	//파일 업로드 시  프로그래스바 설정
	$("#progress_bar_wrap2").dialog({
			resizable: false,
			height:50,
			width:700,
			modal: true,			
			draggable: false,
			autoOpen : false
	});
	
	//프로그래스바 탑 툴바제거
	$("#progress_bar_wrap2").siblings('div.ui-dialog-titlebar').remove();
	
	
	var bar = $('#progress_bar2');
	var percent = $('#percent');
	
	 var options = {
		timeout:   3000
	};
	 
	 
	$('#<portlet:namespace/>scienceAppFileForm').ajaxForm({
		beforeSubmit: function(arr, $form, options){
			bStart();
			if($("#<portlet:namespace/>scienceAppFile").hasClass("success-field")){
	    		return true;
	    	}else{
	    		return false;
	    	}
		},
/* 	    beforeSend: function() {
	    	if(!$("#<portlet:namespace/>scienceAppFile").hasClass("error-field")){
		    	$("#progress_bar_wrap2").dialog("open");
		        var percentVal = '0%';
		        bar.width(percentVal);
		        percent.html(percentVal);
		        return true;
	    	}else{
	    		return false;
	    	}
	    },
	    uploadProgress: function(event, position, total, percentComplete) {
	        var percentVal = percentComplete + '%';
	        bar.width(percentVal)
	        percent.html(percentVal);
	    }, */
	    error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}
			return false;
		},
	    success: function(msg) {
	        /* var percentVal = '100%';
	        bar.width(percentVal)
	        percent.html(percentVal); */
	    },
		complete: function(xhr) {
// 			$("#progress_bar_wrap2").dialog("close");
			bEnd();
// 			var out =  $.parseJSON(xhr.responseText);
// 			var fileName = out.fileName;
			var binFolderListToStr = out.binFolderListToStr;
			$("#commandTextArea").val(binFolderListToStr);
			$("#fileUpladMsg").text(Liferay.Language.get('edison-science-appstore-toolkit-file-upload-success-message'));
		}
	});
	
	$("#request-lib-dialog").dialog({
		autoOpen: false,
		width: 500,
		height: 600,
	    modal: true,
	    resizable: false,
	    dialogClass: 'no-dialog-padding',
	    show: {effect:'fade', speed: 800}, 
	    hide: {effect:'fade', speed: 800},
	    open: function(event, ui) {
	    	$(this).parent().css('overflow', 'visible');
	    	$(this).css('overflow', 'visible');
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#request-lib-dialog").dialog("close");
	    	})
	    },
	    close: function() {
	    	$("#request-lib-dialog").empty();
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
});

function <portlet:namespace/>gitHubCompile(){
	
	var gitHubUrl = $("#<portlet:namespace/>gitHubUrl").val();
	if(gitHubUrl == ""){
		alert(Liferay.Language.get("edison-appstore-file-compile-url-alert"));
		return;
	}else{
		//github 주소로 시작하는지 check
		var startString = gitHubUrl.startsWith("https://github.com");
		if(!startString){
			alert(Liferay.Language.get("edison-appstore-file-compile-url-alert"));
			return;
		}
	}
	
	bStart();
	
	var searchData = {
		"<portlet:namespace/>scienceAppId" : "${scienceAppId}",
		"<portlet:namespace/>gitHubUrl" : gitHubUrl,
	}
	
	$.ajax({
		type: "POST",
		url:"<%=fileUploadByGitHubCompileURL%>",
		async : true,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			$("#<portlet:namespace/>uploadGitHubInput").empty();
			var result = data.result;
			
			if(result != ""){
				$("#<portlet:namespace/>uploadGitHubInput").show();
				$("#<portlet:namespace/>uploadGitHubInput").html(result);	
			}
			
			var binFolderListToStr = data.binFolderListToStr;
			$("#commandTextArea").val(binFolderListToStr);
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			} 
		},complete:function(){
			bEnd();
			$("#fileUpladMsg").text(Liferay.Language.get('edison-science-appstore-toolkit-file-upload-success-message'));
		}
	});
	
}

$("#<portlet:namespace/>runType").on('change', function(){
	var runType = this.value;
	if(runType == "<%=ScienceAppConstants.APP_RUNTYPE_SEQUENTIAL%>"){
		$("#<portlet:namespace/>minCpus").val('0');
		$("#<portlet:namespace/>maxCpus").val('0');
		$("#<portlet:namespace/>defaultCpus").val('0');
		
		$(".data-parallel-cpu").hide();
	} else {
		$(".data-parallel-cpu").show();
	}
});

function <portlet:namespace/>actionCall(mode){
	if(mode=='<%=Constants.ADD%>'){
		<portlet:namespace/>frm.encoding = "multipart/form-data";
		
		$minCpus = $("#<portlet:namespace/>minCpus");
		$maxCpus = $("#<portlet:namespace/>maxCpus");
		$defaultCpus = $("#<portlet:namespace/>defaultCpus");
		if($("#<portlet:namespace/>runType").val()!="<%=ScienceAppConstants.APP_RUNTYPE_SEQUENTIAL%>"){
			
			if($maxCpus.val()==""){
				alert(Liferay.Language.get('this-field-is-mandatory'));
				$maxCpus.focus();
				return false;
			}
			
			if($defaultCpus.val()==""){
				alert(Liferay.Language.get('this-field-is-mandatory'));
				$defaultCpus.focus();
				return false;
			}
			
			if(Number($defaultCpus.val())>Number($maxCpus.val())){
				alert(Liferay.Language.get('edison-science-appstore-toolkit-execute-cpu-error'));
				$maxCpus.focus();
				return false;
			}
			
			if(Number($defaultCpus.val())<Number($minCpus.val())){
				alert(Liferay.Language.get('edison-science-appstore-toolkit-execute-cpu-min-error'));
				$minCpus.focus();
				return false;
			}
		}
		
		$minCpus.attr("disabled", false);
		$maxCpus.attr("disabled", false);
		$defaultCpus.attr("disabled", false);
		
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

function <portlet:namespace/>searchRequestLib(p_curPage){
	
	if("${data.isPort}"=="true"){
		var searchForm = {
				"<portlet:namespace/>scienceAppId" : "${scienceAppId}",
				"<portlet:namespace/>p_curPage": p_curPage
		};
		
		$("#<portlet:namespace/>libraryRequestList tr:not(:has(#1))").remove();
		
		jQuery.ajax({
			type: "POST",
			url: "<%=searchRequestLibURL%>",
			async : false,
			data  : searchForm,
			dataType: 'json',
			success: function(result) {
				$tbody = $("#<portlet:namespace/>libraryRequestList");
				document.getElementById("pageListDiv").innerHTML="";
				var length = result.dataList.length;
				if(length == 0) {
					$rowResult = $("<tr/>");
					$("<td/>").css("text-align","center")
							  .text(Liferay.Language.get('edison-there-are-no-data'))
							  .attr("colspan","5")
							  .appendTo($rowResult);
					$tbody.append($rowResult);
				}else{
					for(var i = 0; i < length; i++) {
						var data = result.dataList[i];
						
						$rowResult = $("<tr/>");
						
						if(i%2 == 1){
							$rowResult.addClass("tablebgtr");
	 					}
						
						$rowResult.css("cursor","pointer")
								  .attr("onClick", "<portlet:namespace/>RequestLibPopup('"+data.requiredLibId+"');");
						
						
						$("<td/>").addClass("TC").text(result.seq-i).appendTo($rowResult);
						$("<td/>").text(data.libraryName).appendTo($rowResult);
						$("<td/>").addClass("TC").text(data.libraryVersion).appendTo($rowResult);
						$("<td/>").addClass("TC").text(formatDate(data.requiredDate)).appendTo($rowResult);
						$("<td/>").addClass("TC").text(data.requiredState).appendTo($rowResult);
						
						$tbody.append($rowResult);
					}
					
					
					document.getElementById("pageListDiv").innerHTML=result.page;
				}
			},error:function(jqXHR, textStatus, errorThrown){
				if(jqXHR.responseText !== ''){
					alert(textStatus+": "+jqXHR.responseText);
				}else{
					alert(textStatus+": "+errorThrown);
				}  
			}
		});
	}
}

$("#common-lib-dialog").dialog({
	autoOpen: false,
	width: 850,
	height: 700,
    modal: true,
    resizable: false,
    show: {effect:'fade', speed: 800}, 
    hide: {effect:'fade', speed: 800},
    dialogClass: 'no-dialog-padding',
    open: function(event, ui) {
    	$(this).parent().css('overflow', 'visible');
    	$(this).css('overflow', 'visible');
    	$(this).removeClass("ui-widget-content");
    	$(this).parent().removeClass("ui-widget-content");
    	
    	$('.ui-widget-overlay').bind('click',function(){
    		$("#common-lib-dialog").dialog("close");
    	})
    },
    close: function() {
    	$("#common-lib-dialog").empty();
    }
}).dialog("widget").find(".ui-dialog-titlebar").remove();

function <portlet:namespace/>RequestLibPopup(p_requiredLibId){
	var renderParameter = "";
	renderParameter += "&<portlet:namespace/>scienceAppId=${scienceAppId}";
	
	if(p_requiredLibId!=""){
		renderParameter += "&<portlet:namespace/>requiredLibId="+p_requiredLibId;
	}
	var URL = "<%=libChangeRenderURL%>"+renderParameter;
	$("#request-lib-dialog").load(URL).dialog("open");
}

function <portlet:namespace/>openCommonLibPopup(){
	var URL = "<%=commonLibRenderURL%>";
	
	$("#common-lib-dialog").load(URL).dialog("open");
}

function changeOpenLevel(val){
	var file = $("#<portlet:namespace/>sourceFile");
	if(val == "<%=ScienceAppConstants.OPENLEVEL_DWN%>"){
        $(".is-not-dwn-only").hide();
        $(".is-not-dwn-only input").prop("disabled", true);
        $(".is-not-dwn-only textarea").prop("disabled", true);
    }else{
        $(".is-not-dwn-only").each(function(_){
            $(this).find("input:hidden").prop("disabled", false);            
            $(this).find("textarea:hidden").prop("disabled", false);
            $(this).show();
        });
    }
	$('.deploy').hide();
	<%
	if(mode.equals(Constants.UPDATE)){
    %>
         if(val == "<%=ScienceAppConstants.OPENLEVEL_BIN%>"){
             $('.deploy').show();    
         }
    <%
    }
	%>
	if(val == "<%=ScienceAppConstants.OPENLEVEL_BIN%>" || val == "<%=ScienceAppConstants.OPENLEVEL_DWN%>" ){
        $("#sourceFileTh").text("Binary File");
    }else{
        $("#sourceFileTh").text("Source File");         
    }
	
	if(val!="<%=ScienceAppConstants.OPENLEVEL_RUN%>"){
		if(val==''){
			file.attr("disabled",true);
		}else{
			if(Number('${data.status}')>=1901003){
				file.attr("disabled",true);
			}else{
				file.attr("disabled",false);
			}
		}
	}else{
		file.attr("disabled",true);
	}
}

function changeAppType(val){
    var select = $("#<portlet:namespace/>editorType");
    if(val=="<%=ScienceAppConstants.APP_TYPE_EDITOR%>"){
        select.attr("disabled",false);
    }else{
        select.attr("disabled",true);
    }
}

function changeRunType(val){
	if(val=="<%=ScienceAppConstants.APP_RUNTYPE_PARALLEL%>"){
		$(".runTypeDisabled").attr("disabled",false);
	}else{
		if(val!=''){
			$(".runTypeDisabled").attr("disabled",true);
		}else{
			$(".runTypeDisabled").attr("disabled",false);
		}
	}
}

function changeUploadOption(val){
	$(".uploadOptionTr").hide();
	$("#<portlet:namespace/>uploadGitHubInput").hide();
	$("#<portlet:namespace/>uploadCaseSelect").val("update");
	$("#<portlet:namespace/>gitUploadCaseSelect").val("file");
	
	if(val == ""){
		if("${data.isCompile}" == "true"){
			val = "compile";	
		}else{
			val = "upload";
		}
		$("#<portlet:namespace/>uploadSelect").val(val);
	}
	
	$("#<portlet:namespace/>uploadOption_upload").show();
	if(val == "compile"){
		/* $("#<portlet:namespace/>uploadOption_"+val+"Url").show();
		
		Git Compile & URL Descrption Display None
		if('${!empty scienceAppCompile.result}' == 'true') $("#<portlet:namespace/>uploadGitHubInput").show(); */
		
		$("#selectByuploadSelect").hide();
		$("#selectByCompileSelect").show();
	}else{
		/* $("#<portlet:namespace/>uploadOption_"+val).show(); */
		$("#selectByCompileSelect").hide();
		$("#selectByuploadSelect").show();
	}
}

function deployWar(fileName, fileId) {
    if(<portlet:namespace/>isDisableDeploy) {
        alert('You can deploy after save');
        return false;
    }
    
    if(fileName && typeof fileName === 'string' &&
        fileName.toLowerCase().indexOf('war', this.length - 'war'.length) !== -1) {
        bStart();
        jQuery.ajax({
            type: "POST",
            url: "<%=deployWarURL%>",
            data  : { 
                <portlet:namespace/>fileId: fileId,
                <portlet:namespace/>fileName: fileName,
            },
            dataType: 'json',
            success: function(result) {
                setTimeout(function() {
                    bEnd();
                    alert('Successfully Deployed')
                }, 1000);
                
            },
            error:function(jqXHR, textStatus, errorThrown){
                bEnd();
                if(jqXHR.responseText !== ''){
                    alert(textStatus+": "+jqXHR.responseText);
                }else{
                    alert(textStatus+": "+errorThrown);
                } 
            }
        });
        
    } else {
        alert('war File Only')
    }
}

function <portlet:namespace/>disableDeploy(){
    <portlet:namespace/>isDisableDeploy = true;
}

function changeUploadCaseSelect(val){
	$(".uploadOptionTr").hide();
	if(val == "file"){
		$("#<portlet:namespace/>uploadCaseSelect").val("clean");
		$("#<portlet:namespace/>uploadOption_upload").show();
	}else{
		$("#<portlet:namespace/>uploadOption_compile").show();
		$("#<portlet:namespace/>uploadOption_compileUrl").show();
	}
}
</script>