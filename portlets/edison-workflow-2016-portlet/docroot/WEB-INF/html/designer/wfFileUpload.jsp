<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="getSpecificSiteGroupIdUrl" escapeXml="false" id="getSpecificSiteGroupId" copyCurrentRenderParameters="false" />

<style>
	#<portlet:namespace/>portSetting .newWtitle{
		padding-left: 10px;
	}
</style>

<div id="<portlet:namespace/>portSetting" class="table-responsive panel edison-panel">
	<div class="newWheader">
		<div class="newWtitle">
			<img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			Port Setting
		</div>
		
		<div class="newWclose">
			<img id="sample-file-close-btn" name="sample-file-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	
	<div class="newWcont01">
		
		<div class="panel-body">
			<%-- <form id="<portlet:namespace>portSampleForm" name="<portlet:namespace>portSampleForm" enctype="multipart/form-data" action="test"> --%>
				
				<div class="form-group puptitle">
					<input type="radio" name="CheckFile" id ="dataTypeSampleFile" value="${data.dataTypeSampleId}">
					<label for='dataTypeSampleFile'>
						<liferay-ui:message key='edison-table-list-header-data-type-file'/>
					</label>
				</div>
				<div class="form-group">
					<div class="down_date"  onclick="<portlet:namespace/>fileDownload('${data.dataTypeSampleId}')" style="cursor: pointer;display: inline-block;">
						${data.sample_}
					</div>
				</div>
				
				<div class="clearfix" style="border-bottom: solid 1px #ccc;margin: 15px 0px;"></div>
				
				<div class="form-group puptitle">
					<input type="radio" name="CheckFile" id="portSampleFile" value="${data.portSampleId}">
					<label for='portSampleFile'>
						<liferay-ui:message key='edison-table-list-header-port-file'/>
					</label>
				</div>
				<div class="form-group">
					<input type="file" id="<portlet:namespace/>sampleFile" name="<portlet:namespace/>sampleFile" class="form-control-file"/>
				</div>
				<div class="form-group">
					<button class="btn btn-default" type="submit" ><span class="icon-file"> file save</span></button>
				</div>
				<div class="form-group" id="status">
					<c:if test="${!empty data.wfSampleFile_}">
						<div class="down_date portSampleFileClass"  onclick="<portlet:namespace/>fileDownload('${data.portSampleId}')" style="cursor: pointer;display: inline-block;">
							${data.portSampleTitle}
						</div>
						<img src='${contextPath}/images/icon_dustbin.png' class="portSampleFileClass noUpdateHidden" width='13' height='14' style="cursor:pointer" onClick="<portlet:namespace/>deleteFile('${data.portSampleId}','portSampleFile','portSampleFileClass');"/>
					</c:if>
				</div>
				
				<div class="clearfix" style="border-bottom: solid 1px #ccc;margin: 15px 0px;"></div>
				<div>
					<div>
						Default Editor
					</div>
					<select>
						<option>1</option>
						<option>2</option>
					</select>
				</div>
			<!-- </form> -->
		</div>
		
		<div class="panel-heading clearfix">
			<div class="btn-group pull-right">
				<button class="btn btn-primary" type="button" onClick="<portlet:namespace/>portSave();"><span class="icon-ok"> <liferay-ui:message key='edison-button-save'/></span></button>
			</div>
		</div>
	</div>
</div>