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
		margin-bottom: 0px;
		font-size: 17px;
		margin-right: 20px;
		cursor: pointer;
	}
	
	#port-sampe-file-dialog .puptitle {
		font-size: 17px;
		font-weight: 600;
		color: #334b7e;
		padding-left: 8px;
		padding: 10px;
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
		<div class="edison-panel">
			<div class="panel-heading clearfix">
				<div class="btn-group pull-right">
					<button class="btn btn-primary" type="button" onClick="<portlet:namespace/>portSave();"><span class="icon-ok"> <liferay-ui:message key='edison-button-save'/></span></button>
				</div>
			</div>
			<div class="panel-body">
				<form name="<portlet:namespace/>portSampleForm" id="<portlet:namespace/>portSampleForm" method="POST" action="<%=addPortSampleFileURL%>" enctype="multipart/form-data" class="form-inline">
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
						<button class="btn btn-default" type="button" onClick="<portlet:namespace/>getUserInfo('owner');"><span class="icon-file"> file save</span></button>
					</div>
					<div class="form-group" id="status">
						<c:if test="${!empty data.portSampleTitle}">
							<div class="down_date portSampleFileClass"  onclick="<portlet:namespace/>fileDownload('${data.portSampleId}')" style="cursor: pointer;display: inline-block;">
								${data.portSampleTitle}
							</div>
							<img src='${contextPath}/images/icon_dustbin.png' class="portSampleFileClass noUpdateHidden" width='13' height='14' style="cursor:pointer" onClick="<portlet:namespace/>deleteFile('${data.portSampleId}','portSampleFile','portSampleFileClass');"/>
						</c:if>
					</div>
					
					<div class="clearfix" style="border-bottom: solid 1px #ccc;margin: 15px 0px;"></div>
					
					<div class="form-group puptitle">
						<input type="radio" name="CheckFile" id ="dataTypeSampleFile" value="${data.dataTypeSampleId}">
						<label for='dataTypeSampleFile'>
							<liferay-ui:message key='edison-table-list-header-data-type-file'/>
						</label>
					</div>
					<div class="form-group">
						<div class="down_date"  onclick="<portlet:namespace/>fileDownload('${data.dataTypeSampleId}')" style="cursor: pointer;display: inline-block;">
							${data.dataTypeSampleTitle}
						</div>
					</div>
				</form>
			</div>
		</div>
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