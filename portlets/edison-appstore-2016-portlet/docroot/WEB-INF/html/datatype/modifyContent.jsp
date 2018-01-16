<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>

<script type="text/javascript" src="${contextPath}/js/jquery.form.min.js"></script>

<liferay-portlet:renderURL var="dataTypePreURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>">
	<liferay-portlet:param name="dataTypeId" value="${dataTypeMap.typeId}" />
	<liferay-portlet:param name="modifyMode" value="modifyEditorAnalyzer" />
	
	<liferay-portlet:param name="searchByPrePage" value="${searchByPrePage}" />
	<liferay-portlet:param name="portType" value="${portType}" />
	<liferay-portlet:param name="portName" value="${portName}" />
	
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<portlet:param name="myRender" value="dataTypeModifyRender" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL var="submitURL" copyCurrentRenderParameters="false" name="dataTypeAction">
	<liferay-portlet:param name="dataTypeId" value="${dataTypeMap.typeId}" />
	<liferay-portlet:param name="modifyMode" value="modifyContent"/>
	<liferay-portlet:param name="mode" value="${mode}"/>

	<liferay-portlet:param name="searchByPrePage" value="${searchByPrePage}" />
	<liferay-portlet:param name="portType" value="${portType}" />
	<liferay-portlet:param name="portName" value="${portName}" />
	
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="dataTypeFileURL" id="addDataTypeSampeFile" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="dataTypeId" value="${dataTypeMap.typeId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="deleteFileURL" escapeXml="false" id="deleteFile" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="dataTypeId" value="${dataTypeMap.typeId}" />
</liferay-portlet:resourceURL>

<style type="text/css">
	.edison-data-type-editor #progress_bar_wrap2 {
		width:500px;  
		padding: 10px 30px 30px 30px; 
		background-color:#f7f7f7;
		border-top: 1px solid #e8e8e8; 
	    border-right: 1px solid #e8e8e8; 
	    border-left: 1px solid #e8e8e8; 
	    border-bottom: 1px solid #e8e8e8; 
	    overflow-y: hidden;
	}
	.edison-data-type-editor #progress_bar_line {
		padding:1px; 
		border-top: 1px solid #CCCCCC; 
	    border-right: 1px solid #CCCCCC; 
	    border-left: 1px solid #CCCCCC; 
	    border-bottom: 1px solid #CCCCCC; 
	}
	.edison-data-type-editor #progress_bar2 {
		width: 0%;
		background-image:url(/edison-appstore-2016-portlet/images/progress_bar.jpg);
		height:20px; 
		text-align:right; 
		line-height:15px; 
		font-size:11px; color:#000000;
	}
</style>
<div class="container">
	<div class="edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				<a onClick="<portlet:namespace/>historyBack();" style="cursor: pointer;"> ${redirectName} </a> >
				<c:choose>
					<c:when test="${mode eq 'update'}">
						DataType
						<liferay-ui:message key='action.UPDATE' />
					</c:when>
					<c:otherwise>
						DataType
						<liferay-ui:message key='registration' />
					</c:otherwise>
				</c:choose>
			</h3>
		</div>
	</div>
	<aui:form name="modifyStructure" method="POST" action="<%=submitURL%>">
		<aui:input name="structure" type="hidden" />
	</aui:form>
	<aui:form name="frmPre" method="POST" action="<%=dataTypePreURL%>">
		
	</aui:form>
	<form name="<portlet:namespace/>frm" id="<portlet:namespace/>frm" method="POST" action="<%=dataTypeFileURL%>" enctype="multipart/form-data" >
		<div class="table1_list">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data" >
				<colgroup>
					<col width="20%">
					<col width="40%">
					<col width="15%">
					<col width="25%">
				</colgroup>
				<tr>
					<th><liferay-ui:message key='name' /></th>
					<td>
						${dataTypeMap.name}
					</td>
					<th><liferay-ui:message key='version' /></th>
					<td>
						${dataTypeMap.version}
					</td>
				</tr>
				<tr>
					<th><liferay-ui:message key='description' /></th>
					<td colspan="3">
						${dataTypeMap.description}
					</td>
				</tr>
				<c:if test="${!empty editorStr}">
					<tr>
						<th><liferay-ui:message key='edison-science-appstore-toolkit-editor-list' /></th>
						<td>
							${editorName}
						</td>
						<th>Default Editor</th>
						<td>
							${defaultEditorName}
						</td>
					</tr>
				</c:if>
				<c:if test="${!empty analyzerStr}">
					<tr>
						<th><liferay-ui:message key='edison-science-appstore-toolkit-analyzer-list' /></th>
						<td>
							${analyzerName}
						</td>
						<th>Default Analyzer</th>
						<td>
							${defaultAnalyzerName}
						</td>
					</tr>
				</c:if>
				<tr>
					<th><liferay-ui:message key='add-sample-data'/><span class="requiredField"> *</span></th>
					<td>
						<input type="file" id="<portlet:namespace/>sampleFile" name="<portlet:namespace/>sampleFile"/>
					</td>
					<td>
						<button class="btn btn-default" type="submit"><span class="icon-file"> file save</span></button>
					</td>
					<td id="status">
						<c:if test="${!empty dataTypeMap.sampleTitle}">
							<div class="down_date dataTypeFileClass"  onclick="<portlet:namespace/>fileDownload('${dataTypeMap.samplePath}')" style="cursor: pointer;display: inline-block;">
								${dataTypeMap.sampleTitle}
							</div>
							<img src='${contextPath}/images/icon_dustbin.png' class="dataTypeFileClass noUpdateHidden" width='13' height='14' style="cursor:pointer" onClick="<portlet:namespace/>deleteFile('${dataTypeMap.samplePath}','dataTypeFileClass');"/>
						</c:if>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<div class="pull-right" style="margin: 18px 0px;">
		<button type="button" class="btn btn-default" onclick="<portlet:namespace/>preBack();"><span class="icon-arrow-left">  <liferay-ui:message key='previous'/></span></button>
		<c:if test="${inputdeckExist}">
			<button type="button" class="btn btn-default" onclick="<portlet:namespace/>modify();"><span class="icon-edit">  <liferay-ui:message key='save'/></span></button>
		</c:if>
		<button type="button" class="btn btn-default" onclick="<portlet:namespace/>historyBack();"><span class="icon-list-ul">  <liferay-ui:message key='edison-virtuallab-surveyResultList-list'/></span></button>
	</div>
	
	<div class="h15"/>
	
	<c:if test="${inputdeckExist}">
		<liferay-portlet:runtime portletName="edisoninputDeck_WAR_edisonappstore2016portlet"/>
	</c:if>
	
	
	<div id="progress_bar_wrap2" style="display: none;">
	    <div id="progress_bar_line">
	        <div id="progress_bar2"><span id="percent">0%</span></div>    
	    </div>
	</div>
	
</div>


<script type="text/javascript">
AUI().ready(function() {
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
	var status = $('#status');
	
	   
	$('#<portlet:namespace/>frm').ajaxForm({
		beforeSubmit: function(arr, $form, options){
			if($("#<portlet:namespace/>sampleFile").val()!=""){
	    		return true;
	    	}else{
	    		return false;
	    	}
		},
	    beforeSend: function() {
	    	if($("#<portlet:namespace/>sampleFile").val()!=""){
	    		status.empty();
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
	    },
	    error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}
			return false;
		},
	    success: function(msg) {
	        var percentVal = '100%';
	        bar.width(percentVal)
	        percent.html(percentVal);
	    },
		complete: function(xhr) {
			$("#progress_bar_wrap2").dialog("close");
			
			var out =  $.parseJSON(xhr.responseText);
			var fileEntryId = out.fileEntryId;
			var title = out.title;
			<portlet:namespace/>showFile(fileEntryId,title);
		}
	});
	
	
	<portlet:namespace/>addStructuredDataEditor();
});

	Liferay.on( 'OSP_RESPONSE_DATA', function(eventData){
		$("#<portlet:namespace/>structure").val(JSON.stringify(eventData.data));
		document.<portlet:namespace/>modifyStructure.submit();
	});
	
	function <portlet:namespace/>modify(){
		if(<portlet:namespace/>fileCheck()){
			var data = {
					targetPortlet: 'BROADCAST',
					sourcePortlet: '<portlet:namespace/>'
			};
			Liferay.fire('OSP_REQUEST_DATA',data);
		}
	}
	
	function <portlet:namespace/>addStructuredDataEditor(){
		if('${inputdeckExist}'){
			AUI().use(function(A){
				var data = {
						targetPortlet: 'BROADCAST',
						sourcePortlet: '<portlet:namespace/>',
						strStructure: '${dataTypeMap.structure}'
				};
				
				Liferay.fire('OSP_SHOW_SDE',data);
			});
		}
	}
	
	function <portlet:namespace/>fileDownload(p_fileEntryId){
		location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
	}

	function <portlet:namespace/>showFile(fileEntryId,title){
		$status = $('#status');
		
		$("<div/>").addClass("down_date dataTypeFileClass")
				   .attr("onclick","<portlet:namespace/>fileDownload('"+fileEntryId+"')")
				   .css("cursor","pointer")
				   .css("display","inline-block")
				   .html(title)
				   .appendTo($status);
	
		$("<img/>").attr("src","${contextPath}/images/icon_dustbin.png")
				   .addClass("potyTypeFile dataTypeFileClass")
				   .attr("width","13")
				   .attr("height","14")
				   .css("cursor","pointer")
				   .attr("onclick","<portlet:namespace/>deleteFile('"+fileEntryId+"','dataTypeFileClass')")
				   .appendTo($status);
	}
	function <portlet:namespace/>historyBack(){
		if(<portlet:namespace/>fileCheck()){
			location.href = "${redirectOrignURL}";
		}
	}
	
	function <portlet:namespace/>preBack(){
		document.<portlet:namespace/>frmPre.submit();
	}
	
	function <portlet:namespace/>fileCheck(){
		if($("#status").children().length!=0){
			return true;
		}else{
			alert(Liferay.Language.get('edison-this-field-is-required',['Sample-Data']));
			$("#<portlet:namespace/>sampleFile").focus();
			return false;
		}
		alert();
	}
	
	function <portlet:namespace/>deleteFile(p_fileEntryId,objectClass){
		if(!confirm(Liferay.Language.get('file-delete-confirm'))) return;
		var deleteForm = {
				"<portlet:namespace/>fileEntryId" : p_fileEntryId
				};
		
		jQuery.ajax({
			type: "POST",
 			url: "<%=deleteFileURL%>",
			data: deleteForm,
	  		async : false,
			success: function(data) {
				alert(Liferay.Language.get('edison-data-delete-success'));
				$("."+objectClass).remove();
				var percentVal = '0%';
				$('.bar').width(percentVal);
				$('.percent').html(percentVal);
			},error:function(data,e){ 
				alert("deleteFile System error!");	
			}
		});
	}
</script>
