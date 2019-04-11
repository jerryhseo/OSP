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

<liferay-portlet:resourceURL var="fileUploadCompileURL" id="fileUploadCompile" copyCurrentRenderParameters="false">
	<portlet:param name="appName" value="${data.name}"/>
	<portlet:param name="appVersion" value="${data.version}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="getClusterListURL" escapeXml="false" id="getClusterList" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getUploadFolderListURL" escapeXml="false" id="getUploadFolderList" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="readSimFileURL" escapeXml="false" id="readSimFile" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="writeSimFileURL" escapeXml="false" id="writeSimFile" copyCurrentRenderParameters="false"/>

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
	
	.<portlet:namespace/>upload-type,
	.<portlet:namespace/>upload-type > label {
		text-align: center;
		font-size: inherit !important;
		font-weight: normal !important;
		font-family: inherit !important;
		line-height: inherit !important;
	}
	
	#<portlet:namespace/>fileEditor,
	#<portlet:namespace/>editorSimFile,
	#<portlet:namespace/>sourceFileLog,
	#<portlet:namespace/>goParentFolder{
		display: none;
	}
	
	textarea.<portlet:namespace/>exe-file-info{
		width: 100%;
		resize: none;
	}
	
	div.<portlet:namespace/>exe-file-info{
		padding: 5px 10px;
		border: 1px solid #ccc;
		border-radius: 5px;
	}
	
	#<portlet:namespace/>sourceFileLog{
		margin-bottom: 10px;
	}
	
	#<portlet:namespace/>sourceFile{
		display: inline-block;
	}
	
	#<portlet:namespace/>exeFileList,
	#<portlet:namespace/>exeFolderList{
		margin: 10px 0px;
	}
	
	#<portlet:namespace/>exeFileListEmpty,
	.<portlet:namespace/>no-exe-data{
		text-align: center;
	}
	
	#<portlet:namespace/>goParentFolder{
		display: inline-block;
		margin-right: 10px;
		padding: 0px 5px;
		float: right;
		border: 1px solid #e2e2e2;
		border-radius: 5px;
	}
	
	#<portlet:namespace/>goParentFolder:HOVER,
	.<portlet:namespace/>exe-folder:HOVER{
		cursor: pointer;
	}
	
	#<portlet:namespace/>simFileEditor > textarea{
		width: 100%;
		min-height: 400px;
		border-radius: 5px;
		border: 1px solid #e2e2e2;
		resize: none;
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
				<td id="opendLevelTd" colspan="3">
					<aui:select name="openLevel" label="" onChange="changeOpenLevel(this.value);" cssClass="noupdate">
						${openLevelOptions}
					</aui:select>
				</td>
				<%-- <th id="sourceFileTh">Source File</th>
				<td id="sourceFileTd">
					<input type="file" id="<portlet:namespace/>sourceFile" name="<portlet:namespace/>sourceFile" disabled="disabled" onchange="<portlet:namespace/>disableDeploy()">
					<c:if test="${data.sourceFileId ne null}">
						<div class="down_date sourceFileClass"  onclick="<portlet:namespace/>fileDownload('${data.sourceFileId }')" style="cursor: pointer;display: inline-block;">
							${data.sourceFileTitle}
						</div>
						<img src='${contextPath}/images/icon_dustbin.png' class="sourceFileClass noUpdateHidden" 
                        width='13' height='14' style="cursor:pointer" onClick="<portlet:namespace/>deleteFile('${data.sourceFileId}','soruceFile','sourceFileClass');"/>
					</c:if>
                    
				</td> --%>
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
				
				<tr id="<portlet:namespace/>fileEditor">
					<td class="<portlet:namespace/>file-editor" colspan="4">
						<div id="<portlet:namespace/>sourceFileLog">
							<div class="<portlet:namespace/>file-editor-title">
								<i class="icon-file-alt"></i> Build Log
							</div>
							
							<div class="h10"></div>
							
							<div class="gitHubResultDiv" id="<portlet:namespace/>uploadGitHubInput">
								${scienceAppCompile.result}
							</div>
						</div>
						
						<%-- <div class="<portlet:namespace/>file-editor-title">
							<i class="icon-chevron-right"></i> <liferay-ui:message key='edison-science-appstore-exe-file' />
						</div> --%>
						<div class="h10"></div>
						
						<div class="<portlet:namespace/>exe-file-info">
							<i class="icon-home"></i>
							<div id="<portlet:namespace/>exeFolderBreadcrumb" style="display: inline-block;">
							</div>
							<div id="<portlet:namespace/>goParentFolder">
								<liferay-ui:message key='parent-folder' /> <i class="icon-level-up"></i>
							</div>
							
							<div class="h10"></div>
							
							<input type="hidden" id="<portlet:namespace/>exeRootPath" value="" />
							<input type="hidden" id="<portlet:namespace/>exeFolderObj" value="" />
							<input type="hidden" id="<portlet:namespace/>exeFileObj" value="" />
							
							<span class="<portlet:namespace/>file-editor-title-sub">
								<i class="icon-chevron-right"></i> FOLDER
							</span>
							<div id="<portlet:namespace/>exeFolderList">
							</div>
							
							<div class="h10"></div>
							
							<span class="<portlet:namespace/>file-editor-title-sub">
								<i class="icon-chevron-right"></i> FILE
							</span>
							<div id="<portlet:namespace/>exeFileList">
								<div id="<portlet:namespace/>exeFileListEmpty" style="display: none;">
									<liferay-ui:message key='edison-workflow-data-empty-message' />
								</div>
								<table id="<portlet:namespace/>exeFileListTable" style="width: 100%; display: none;">
									<colgroup>
										<col width="75%">
										<col width="25%">
									</colgroup>
									
									<thead>
										<th>Name</th>
										<th>File Size</th>
									</thead>
									
									<tbody id="<portlet:namespace/>exeFileListBody">
									</tbody>
								</table>
							</div>
						</div>
					</td>
				</tr>
				
				<tr id="<portlet:namespace/>editorSimFile">
					<th>Simrc <liferay-ui:message key='edit' /></th>
					<td>
						<input type="button" id="<portlet:namespace/>editSimrc" class="btn btn-default" value="파일 편집" />
					</td>
					<th>Simpost <liferay-ui:message key='edit' /></th>
					<td>
						<input type="button" id="<portlet:namespace/>editSimpost" class="btn btn-default" value="파일 편집" />
					</td>
				</tr>
			</table>
			
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

<div id="<portlet:namespace/>simFileEditor" title="common-lib" style="display: none;">
	<input type="hidden" id="<portlet:namespace/>simFileName" value="" />
	<div style="margin-bottom: 10px;">
		<span><i class="icon-folder-open"></i> 폴더 위치 : </span>
		<select id="<portlet:namespace/>simFilePath" class="form-control" style="max-width: 80%; display: inline-block;">
			<option value="">선택해주세요</option>
		</select>
	</div>
	<textarea rows="5" cols="5" placeholder="<liferay-ui:message key='enter-file-content' />"></textarea>
</div>

<!-- 	Progress Bar	  -->
<div id="progress_bar_wrap2" style="display: none;">
    <div id="progress_bar_line">
        <div id="progress_bar2"><span id="percent"></span></div>    
    </div>
</div>

<script type="text/javascript">
var <portlet:namespace/>isDisableDeploy = false;
	$(document).ready(function () {
<%
if(mode.equals(Constants.UPDATE)){
%>
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
		
		/* Select Run Type */
		$("#<portlet:namespace/>runType option[value='${data.runType}']").attr("selected", "selected");
<%} else { %>
		<portlet:namespace/>getClusterList();
		$('.deploy').hide();
<%}%>
		
		var hasExeUploadFile = "${exeFileUpload}";
		if(hasExeUploadFile == "true"){
			<portlet:namespace/>getExeFileInfo("${scienceAppId}", "${data.name}", "${data.version}", '');
		}
	});

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
	
	$("#<portlet:namespace/>simFileEditor").dialog({
		title: 'Edit File',
		autoOpen: false,
		width: 1000,
		height: 'auto',
		modal: true,
		resizable: false,
		draggable: false,
		position: ['center', 100],
		open: function(event, ui) {
			$("body").css('overflow','hidden');
		},
		close: function() {
			$("body").css('overflow','');
		},
		buttons:[
			{
				text:"SAVE",
				'class': 'btn btn-default',
				click: function(){
					if(!$("#<portlet:namespace/>simFilePath").val()){
						
						$.alert({
							title: '',
							content: Liferay.Language.get('edison-appstore-exe-folder-select-error-message')
						})
						return false;
					} else {
						var simFileName = $("#<portlet:namespace/>simFileName").val();
						var saveFolderPath = $("#<portlet:namespace/>simFilePath").val();
						var fileContent = $("#<portlet:namespace/>simFileEditor textarea").val();
						
						var completeSave = <portlet:namespace/>writeSimFile(simFileName, saveFolderPath, fileContent);
						if(completeSave){
							$.alert({
								title: '',
								content: 'Save Success.'
							})
						} else {
							$.alert({
								title: '',
								content: 'Save Failed.'
							})
						}
						$(this).dialog("close");
					}
				}
			},{
				text:"CLOSE",
				'class': 'btn btn-default',
				click: function(){
					$(this).dialog("close");
				}
			}
			
		]
	});
	
	//프로그래스바 탑 툴바제거
	$("#progress_bar_wrap2").siblings('div.ui-dialog-titlebar').remove();
	
	
	var bar = $('#progress_bar2');
	var percent = $('#percent');
	
	 var options = {
		timeout:   3000
	};
	 
	 
	var uploadOption;
	$('#<portlet:namespace/>scienceAppFileForm').ajaxForm({
		beforeSubmit: function(arr, $form, options){
			uploadOption = $("#<portlet:namespace/>uploadSelect").val();
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
	    success: function(result) {
	    	<portlet:namespace/>getExeFileInfo("${scienceAppId}", "${data.name}", "${data.version}", '');
	    	if(uploadOption == 'compile'){
	    		<portlet:namespace/>fileUploadCompile();
	    	}
	        /* var percentVal = '100%';
	        bar.width(percentVal)
	        percent.html(percentVal); */
	    },
		complete: function(xhr) {
// 			$("#progress_bar_wrap2").dialog("close");
			bEnd();
 			var out =  $.parseJSON(xhr.responseText);
 			var fileName = out.fileName;
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
				$("#<portlet:namespace/>sourceFileLog").show();
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

function <portlet:namespace/>fileUploadCompile(){
	bStart();
	var searchData = {
		"<portlet:namespace/>scienceAppId" : "${scienceAppId}"
	}
	
	$.ajax({
		type: "POST",
		url:"<%=fileUploadCompileURL%>",
		async : true,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			$("#<portlet:namespace/>uploadGitHubInput").empty();
			var result = data.result;
			
			if(result != ""){
				$("#<portlet:namespace/>sourceFileLog").show();
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
	$("#<portlet:namespace/>sourceFileLog").hide();
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
		/* $("#<portlet:namespace/>uploadOption_"+val+"Url").show(); */
		
		/* Git Compile & URL Descrption Display None */
		$("#<portlet:namespace/>uploadGitHubInput").empty();
		if('${!empty scienceAppCompile.result}' == 'true') $("#<portlet:namespace/>sourceFileLog").show();
		
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

function <portlet:namespace/>getExeFileInfo(appId, appName, appVersion, selectedPath){
	
	$("#<portlet:namespace/>fileEditor").show();
	$("#<portlet:namespace/>editorSimFile").hide();
	jQuery.ajax({
		type: "POST",
		url: "<%=getUploadFolderListURL%>",
		data  : { 
			<portlet:namespace/>appId: appId,
			<portlet:namespace/>appName: appName,
			<portlet:namespace/>appVersion: appVersion,
			<portlet:namespace/>uploadPath: selectedPath
		},
		dataType: 'json',
		success: function(result) {
			var uploadFileInfo = result.uploadFileInfo;
			var folderList = uploadFileInfo.folderList;
			var fileList = uploadFileInfo.fileList;
			var rootPath = uploadFileInfo.rootPath;
			
			if(!selectedPath){
				selectedPath = rootPath;
			}
			
			$("#<portlet:namespace/>exeRootPath").val(rootPath);
			if(!!folderList && folderList.length > 0){
				$("#<portlet:namespace/>exeFolderList").removeClass('<portlet:namespace/>no-exe-data').text('')
				$("#<portlet:namespace/>exeFolderObj").val(JSON.stringify(folderList));
				<portlet:namespace/>drawExeFolderList(true, selectedPath);
			} else {
				$("#<portlet:namespace/>exeFolderList").addClass('<portlet:namespace/>no-exe-data').text(Liferay.Language.get('edison-workflow-data-empty-message'));
			}
			
			if(!!fileList && fileList.length > 0){
				$("#<portlet:namespace/>exeFileListEmpty").hide();
				$("#<portlet:namespace/>exeFileListTable").show();
				$("#<portlet:namespace/>exeFileObj").val(JSON.stringify(fileList));
				<portlet:namespace/>drawExeFolderList(false, selectedPath);
			} else {
				$("#<portlet:namespace/>exeFileListTable").hide();
				$("#<portlet:namespace/>exeFileListEmpty").show();
			}
			
		}, error: function(jqXHR, textStatus, errorThrown){
			alert(textStatus+": "+errorThrown);
		}, complete: function(){
			$("#<portlet:namespace/>editorSimFile").show();
		}
		
	});
}

function <portlet:namespace/>drawExeFolderList(isFolder, selectedPath){
	
	if(!selectedPath){
		$("#<portlet:namespace/>goParentFolder").hide();
		selectedPath = $("#<portlet:namespace/>exeRootPath").val();
	} else {
		if(!!$("#<portlet:namespace/>exeRootPath").val() && selectedPath != $("#<portlet:namespace/>exeRootPath").val()){
			$("#<portlet:namespace/>goParentFolder").show();
		} else {
			$("#<portlet:namespace/>goParentFolder").hide();
		}
	}
	
	$("#<portlet:namespace/>exeFolderBreadcrumb").text(" " + selectedPath);
	
	if(isFolder){
		$("#<portlet:namespace/>exeFolderList").html("");
		
		var data = JSON.parse($("#<portlet:namespace/>exeFolderObj").val());
		var subFolderCnt = 0;
		for(var idx=0; idx<data.length; idx++){
			var folder = data[idx];
			if(folder.parentPath.trim() == selectedPath.trim()){
				var folderName = $("<i/>").addClass("icon-folder-open").text(" " + folder.name);
				var folderSpan = $("<span/>").addClass("<portlet:namespace/>exe-folder")
											.attr("onclick", "<portlet:namespace/>selectedSourceFolder('" + folder.path + "', '" + folder.parentPath + "')")
											.attr("folderPath", folder.path)
											.attr("parentPath", folder.parentPath)
											.css("padding", "5px 10px")
											.css("margin", "5px 10px")
											.css("border", "1px solid #000")
											.css("border-radius", "5px")
											.append(folderName);
				
				$("#<portlet:namespace/>exeFolderList").removeClass('<portlet:namespace/>no-exe-data').append(folderSpan);
				subFolderCnt += 1;
			}
		}
		
		if(subFolderCnt <= 0){
			$("#<portlet:namespace/>exeFolderList").addClass('<portlet:namespace/>no-exe-data').text(Liferay.Language.get('edison-workflow-data-empty-message'));
		}
	} else{
		$("#<portlet:namespace/>exeFileListBody").html("");
		
		var data = JSON.parse($("#<portlet:namespace/>exeFileObj").val());
		var subFileCnt = 0;
		for(var idx=0; idx<data.length; idx++){
			var file = data[idx];
			if(file.parentPath.trim() == selectedPath.trim()){
				$("#<portlet:namespace/>exeFileListEmpty").hide();
				$("#<portlet:namespace/>exeFileListTable").show();
				var fileTr = $("<tr/>").attr("folderPath", file.parentPath);
				var fileName = $("<i/>").addClass("icon-file-2").text(" " + file.name);
				$("<td/>").css("text-align", "left").append(fileName).appendTo(fileTr);
				
				var fileSize=0;
				var fileSizeByte = file.size;
				if(fileSizeByte < 1024){
					fileSize = fileSizeByte + " Byte";
				} else {
					fileSize = (fileSizeByte/1024).toFixed(3) + " KB";
				}
				$("<td/>").addClass("center").css("text-align", "right").text(fileSize).appendTo(fileTr);
				
				$("#<portlet:namespace/>exeFileListBody").append(fileTr);
				subFileCnt += 1;
			}
		}
		
		if(subFileCnt <= 0){
			$("#<portlet:namespace/>exeFileListTable").hide();
			$("#<portlet:namespace/>exeFileListEmpty").show();
		}
	}
}

function <portlet:namespace/>selectedSourceFolder(folderPath, parentPath){
	$("#<portlet:namespace/>goParentFolder").attr("parentPath", parentPath);
	<portlet:namespace/>drawExeFolderList(true, folderPath);
	<portlet:namespace/>drawExeFolderList(false, folderPath);
}

$("#<portlet:namespace/>goParentFolder").click(function(){
	var parentPath = $(this).attr("parentPath");
	<portlet:namespace/>drawExeFolderList(true, parentPath);
	<portlet:namespace/>drawExeFolderList(false, parentPath);
	<portlet:namespace/>setParentFolder(parentPath);
});

function <portlet:namespace/>setParentFolder(parentPath){
	var data = JSON.parse($("#<portlet:namespace/>exeFolderObj").val());
	for(var idx=0; idx<data.length; idx++){
		var file = data[idx];
		if(file.path.trim() == parentPath.trim()){
			$("#<portlet:namespace/>goParentFolder").attr("parentPath", file.parentPath);
		}
	}
}

$("#<portlet:namespace/>editSimrc").click(function(){
	<portlet:namespace/>editSimFile(true)
});

$("#<portlet:namespace/>editSimpost").click(function(){
	<portlet:namespace/>editSimFile(false);
});

function <portlet:namespace/>editSimFile(isSimrc){
	var fileContent='';
	var simFileName = '';
	if(isSimrc){
		simFileName = 'simrc';
	} else {
		simFileName = 'simpost';
	}
	
	var fileObj = $("#<portlet:namespace/>exeFileObj").val();
	var hasSimFile = false;
	var filePath;
	var folderPath;
	if(!!fileObj){
		var data = JSON.parse(fileObj);
		for(var idx=0; idx<data.length; idx++){
			var file = data[idx];
			if(file.name.toLowerCase() == simFileName){
				filePath = file.path;
				simFileName= file.name;
				folderPath = file.parentPath;
				hasSimFile = true;
			}
		}
	}
	
	if(hasSimFile && !!filePath){
		fileContent = <portlet:namespace/>readSimFile(filePath);
	}
	
	<portlet:namespace/>drawSelectSimfileOption(hasSimFile, simFileName, filePath, folderPath);
	
	$("#<portlet:namespace/>simFileEditor > textarea").val("");
	$("#<portlet:namespace/>simFileEditor > textarea").val(fileContent);
	$("#<portlet:namespace/>simFileEditor").dialog("open");
}

function <portlet:namespace/>readSimFile(filePath){
	
	var fileContent='';
	
	jQuery.ajax({
		type: "POST",
		url: "<%=readSimFileURL%>",
		data  : { 
			<portlet:namespace/>filePath: filePath
		},
		async:false,
		dataType: 'json',
		success: function(result) {
			fileContent = result.content;
		}, error: function(jqXHR, textStatus, errorThrown){
			alert(textStatus+": "+errorThrown);
		}
	});
	
	return fileContent;
}

function <portlet:namespace/>writeSimFile(fileName, folderPath, content){
	
	var savedFile = false;
	jQuery.ajax({
		type: "POST",
		url: "<%=writeSimFileURL%>",
		data  : { 
			<portlet:namespace/>fileName: fileName,
			<portlet:namespace/>folderPath: folderPath,
			<portlet:namespace/>content: content
		},
		async:false,
		dataType: 'json',
		success: function(result) {
			savedFile = result.saveSimFile;
		}, error: function(jqXHR, textStatus, errorThrown){
			savedFile = false;
		}
	});
	
	return savedFile;
}

function <portlet:namespace/>drawSelectSimfileOption(hasSimfile, fileName, filePath, folderPath){
	$("#<portlet:namespace/>simFileName").val(fileName);
	if($(".<portlet:namespace/>simfile-folder-list").length > 0){
		$("option").remove(".<portlet:namespace/>simfile-folder-list");
	}
	
	var simFilePathSelect = $("#<portlet:namespace/>simFilePath");
	simFilePathSelect.removeAttr("disabled");
	if(hasSimfile){
		$("<option/>").addClass("<portlet:namespace/>simfile-folder-list").val(folderPath).text(folderPath).attr("selected", "selected").appendTo(simFilePathSelect);
		simFilePathSelect.attr("disabled", true);
	} else {
		var folderList = $("#<portlet:namespace/>exeFolderObj").val();
		if(!!folderList){
			folderList = JSON.parse(folderList);
			for(var idx=0; idx<folderList.length; idx++){
				var folder = folderList[idx];
				$("<option/>").addClass("<portlet:namespace/>simfile-folder-list").val(folder.path).text(folder.path).appendTo(simFilePathSelect);
			}
		}
	}
}

</script>