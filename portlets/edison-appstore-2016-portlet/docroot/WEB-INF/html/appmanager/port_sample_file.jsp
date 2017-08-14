<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/scienceappmanager.css" media="screen"/>

<script type="text/javascript" src="${contextPath}/js/jquery.form.min.js"></script>

<liferay-portlet:resourceURL var="addPortSampleFileURL" id="addPortSampeFile" copyCurrentRenderParameters="false">
	<portlet:param name="scienceAppId" value="${data.scienceAppId}"/>
	<portlet:param name="portName" value="${data.portName}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="searchDlFileURL" escapeXml="false" id="searchDlFile" copyCurrentRenderParameters="false"/>

<style type="text/css">
	#port-sampe-file-dialog label{
		display: inline;
		margin-bottom: 0px;
		font-size: 17px;
	}
	#port-sampe-file-dialog .puptrline {
		border-bottom: solid 1px #ccc;
	}
	
	#port-sampe-file-dialog .puptitle {
		font-size: 17px;
		font-weight: 600;
		color: #334b7e;
		padding-left: 8px;
		padding: 10px;
	}
	
	#port-sampe-file-dialog .puptxt {
		font-size: 14px;
		font-weight: 600;
		color: #666;
		line-height: 25px;
		padding: 10px 0 10px 8px;
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
</style>

<div class="newWindow" style="background-color: #fff; overflow-x: hidden;overflow-y: hidden;">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">
				Sample File
			</div>
		</div>
		<div class="newWclose">
			<img id="sample-file-close-btn" name="sample-file-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	
	<div class="newWcont01">
		<div style="width:50%; float:right; text-align:right; padding-top:15px;">
			<input class="addIp button02_1" onclick="<portlet:namespace/>portSave();return false;" value="<liferay-ui:message key='edison-button-save'/>" type="button"/>
		</div>
		<form name="<portlet:namespace/>portSampleForm" id="<portlet:namespace/>portSampleForm" method="POST" action="<%=addPortSampleFileURL%>" enctype="multipart/form-data" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data" >
				<colgroup>
					<col width="27%">
					<col width="25%">
					<col width="15%">
					<col width="33%">
				</colgroup>
				<tr class="puptrline">
					<td class="puptitle">
						<input type="radio" name="CheckFile" id="portSampleFile" value="${data.portSampleId}">
						<label for='portSampleFile'>
							<liferay-ui:message key='edison-table-list-header-port-file'/>
						</label>
					</td>
					<td class="puptxt">
						<input type="file" id="<portlet:namespace/>sampleFile" name="<portlet:namespace/>sampleFile"/>
					</td>
					<td class="puptxt">
						<input class="addIp button02_1" value="file save" type="submit" id="<portlet:namespace/>fileSave"/>
					</td>
					<td class="puptxt" id="status">
						<c:if test="${!empty data.portSampleTitle}">
							<div class="down_date portSampleFileClass"  onclick="<portlet:namespace/>fileDownload('${data.portSampleId}')" style="cursor: pointer;display: inline-block;">
								${data.portSampleTitle}
							</div>
							<img src='${contextPath}/images/icon_dustbin.png' class="portSampleFileClass noUpdateHidden" width='13' height='14' style="cursor:pointer" onClick="<portlet:namespace/>deleteFile('${data.portSampleId}','portSampleFile','portSampleFileClass');"/>
						</c:if>
					</td>
				</tr>
				
				<c:if test="${!empty data.dataTypeSampleTitle}">
					<tr class="puptrline">
						<td class="puptitle">
							<input type="radio" name="CheckFile" id ="dataTypeSampleFile" value="${data.dataTypeSampleId}">
							<label for='dataTypeSampleFile'>
								<liferay-ui:message key='edison-table-list-header-data-type-file'/>
							</label>
						</td>
						<td class="puptxt" colspan="3">
							<div class="down_date"  onclick="<portlet:namespace/>fileDownload('${data.dataTypeSampleId}')" style="cursor: pointer;display: inline-block;">
								${data.dataTypeSampleTitle}
							</div>
						</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</div>

<div id="progress_bar_wrap2" style="display: none;">
	<div id="progress_bar_line">
		<div id="progress_bar2"><span id="percent">0%</span></div>    
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
		
		   
		$('#<portlet:namespace/>portSampleForm').ajaxForm({
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
		
	$("#sample-file-close-btn").click(function() {
		$("#port-sampe-file-dialog").dialog("close");
	});
	
	<portlet:namespace/>fileCheckFromRadio();
});

function <portlet:namespace/>fileCheckFromRadio(){
	var sampleFileName = "";
	if(!scienceApp.inputPort('${data.portName}').sample()){
		sampleFileName = "dataTypeSampleFile";
	}else{
		sampleFileName = "portSampleFile";
	}
	$("input:radio[name='CheckFile'][id='"+sampleFileName+"']").prop("checked",true);
}

function <portlet:namespace/>portSave(){
	if($("input:radio[name='CheckFile']").is(":checked")){
		var sampeFileId = $("input:radio[name='CheckFile']:checked").attr("id");
		if(sampeFileId=='dataTypeSampleFile'){
			scienceApp.inputPort('${data.portName}').removeProperty(OSP.Constants.SAMPLE);
			$("#<portlet:namespace/>inputPorts").val(JSON.stringify(scienceApp.inputPorts()));
			$("#port-sampe-file-dialog").dialog("close");
		}else{
			var sampeFileValue = $("input:radio[name='CheckFile']:checked").val();
			var dlFileEntry = <portlet:namespace/>searchDlFile(sampeFileValue);
			
			scienceApp.inputPort('${data.portName}').setSample(sampeFileValue, dlFileEntry.treePath, dlFileEntry.title, OSP.Constants.DLENTRY_ID, true)
			
			$("#<portlet:namespace/>inputPorts").val(JSON.stringify(scienceApp.inputPorts()));
			$("#port-sampe-file-dialog").dialog("close");
		}
	}else{
		alert(Liferay.Language.get('edison-this-field-is-required',['file']));
		return false;
	}
}

function <portlet:namespace/>searchDlFile(fileEntryId){
	var searchData = {
			"<portlet:namespace/>fileEntryId" : fileEntryId
			};
	var returnOBJ;
	jQuery.ajax({
		type: "POST",
		url: "<%=searchDlFileURL%>",
		data: searchData,
  		async : false,
  		dataType: 'json',
		success: function(data) {
			  var obj = {
				title:  data.title,
				treePath: data.treePath
			  };
			  returnOBJ =  obj;
		},error:function(jqXHR, textStatus, errorThrown){ 
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}  
			return false;
		}
	});
	return returnOBJ;
}


function <portlet:namespace/>showFile(fileEntryId,title){
	$status = $('#status');
	$("input:radio[name='CheckFile'][id='portSampleFile']").val(fileEntryId);
	
	$("<div/>").addClass("down_date portSampleFileClass")
			   .attr("onclick","<portlet:namespace/>fileDownload('"+fileEntryId+"')")
			   .css("cursor","pointer")
			   .css("display","inline-block")
			   .html(title)
			   .appendTo($status);

	$("<img/>").attr("src","${contextPath}/images/icon_dustbin.png")
			   .addClass("potyTypeFile portSampleFileClass")
			   .attr("width","13")
			   .attr("height","14")
			   .css("cursor","pointer")
			   .attr("onclick","<portlet:namespace/>deleteFile('"+fileEntryId+"','portSampleFile','portSampleFileClass')")
			   .appendTo($status);
}
</script>