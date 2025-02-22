<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<liferay-portlet:resourceURL var="virtualLabClassListURL" id="virtualLabClassList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="virtualLabClassInfoURL" id="virtualLabClassInfo" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="virtualClassUserInfoURL" id="virtualClassUserInfo" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="virtualClassManagerListURL" id="virtualClassManagerList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteStudentURL" id="deleteStudent" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteVirtualClassManagerURL" id="deleteVirtualClassManager" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="virtualLabClassDisableURL" id="virtualLabClassDisable" copyCurrentRenderParameters="false" />

<liferay-portlet:actionURL var="virtualClassSaveURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="virtualClassSave" />
</liferay-portlet:actionURL>

<style type="text/css">
.aui .tooltip{
	display: none;
}

.selectClassRow {
	background-color: #e0e0e0;
}

.aui input {
	margin-bottom:0px;
}
.sizenormal{
}

.edison .button01b {
	padding:3px 7px;
}
.h5{
    height: 5px;
    clear: both;
}

.edison .input-localized-content td {
	padding:0px;
	border:none;
}

.edison .input-localized-content {
	margin:0px;
	margin-left:5px;
}

.onHover:hover {
	background:#e0e0e0;
}

.detailViewSubTitle{padding-left: 0px !important;}

</style>

<script type="text/javascript">
AUI().ready(function() {
	
	var checkValue = "${virtualLabClassInfo.classId}";
	if (checkValue != '') {
		<portlet:namespace/>dataSearchList("${virtualLabClassInfo.curPage}");
		<portlet:namespace/>getClassManagerList();
	} else {
		<portlet:namespace/>dataSearchList(1);
	}
	
	$( "#<portlet:namespace/>classStartDt" ).datepicker({
		showButtonPanel: true,
		showOn: 'button',
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		buttonImage: "${contextPath}/images/calendar.png",
		buttonImageOnly: true,
		onClose: function( selectedDate ) {
			$( "#<portlet:namespace/>classEndDt" ).datepicker( "option", "minDate", selectedDate );
			var curDate = new Date(); 
			curDate = getFormatDate(curDate);
			
			var selectedTmpDate = selectedDate.split("-");
			var curTmpDate = curDate.split("-");
			
			var tmpDateYear = parseInt(selectedTmpDate[0]);
			var tmpDateMonth = parseInt(selectedTmpDate[1]);
			var tmpDateDay = parseInt(selectedTmpDate[2]);
			
			var curTmpDateYear = parseInt(curTmpDate[0]);
			var curTmpDateMonth = parseInt(curTmpDate[1]);
			var curTmpDateDay = parseInt(curTmpDate[2]);
			
			var resultYear = tmpDateYear - curTmpDateYear;
			var resultMonth = (tmpDateMonth - curTmpDateMonth) + 6  
			var resultDay = tmpDateDay - curTmpDateDay;
			
			if(resultYear > 0){
				for(var a=1; a<=resultYear; a++){
					resultMonth = resultMonth + 12;	
				}
			}
			
			$( "#<portlet:namespace/>classEndDt" ).datepicker( "option", "maxDate", "+"+resultMonth+"M"+"+"+resultDay+"D");
		}
	});
	$( "#<portlet:namespace/>classEndDt" ).datepicker({
		showButtonPanel: true,
		showOn: 'button',
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		buttonImage: "${contextPath}/images/calendar.png",
		buttonImageOnly: true,
		onClose: function( selectedDate ) {
			$( "#<portlet:namespace/>classStartDt" ).datepicker( "option", "maxDate", selectedDate );
		  }
	});
	
	$("#<portlet:namespace/>virtualClass-manager-add-dialog").dialog({
		autoOpen: false,
		width: 'auto',
		height: 'auto',
		modal: true,
		resizable: false,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
			$(this).css('overflow', 'hidden');
			$(this).parent().removeClass("ui-widget-content");
			$(this).parent().removeClass("ui-widget");
			$(this).removeClass("ui-widget-content");
			$(this).removeClass("ui-dialog-content");
			document.virtualClassManagerAddForm.<portlet:namespace/>register_request_button.disabled=false;
		},
		close: function() {
			$("#<portlet:namespace/>classUserId").val("0");
		}
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	$("#<portlet:namespace/>virtualClass-manager-add-dialog-close-btn").click(function() {
		$("#<portlet:namespace/>virtualClass-manager-add-dialog").dialog("close");
	});
	
	
	$("#<portlet:namespace/>virtualClass-add-dialog").dialog({
		autoOpen: false,
		width: 'auto',
		height: 'auto',
		modal: true,
		resizable: false,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
			$(this).css('overflow', 'hidden');
			$(this).parent().removeClass("ui-widget-content");
			$(this).parent().removeClass("ui-widget");
			$(this).removeClass("ui-widget-content");
			$(this).removeClass("ui-dialog-content");
		},
		close: function() {
			$("#<portlet:namespace/>classUserId").val("0");
		}
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	$("#<portlet:namespace/>virtualClass-add-close-btn").click(function() {
		$("#<portlet:namespace/>virtualClass-add-dialog").dialog("close");
	});
	
});

/**
 *  yyyyMMdd 포맷으로 반환
 */

function getFormatDate(date){
	var year = date.getFullYear();                 //yyyy
	var month = (1 + date.getMonth());             //M
	month = month >= 10 ? month : '0' + month;     // month 두자리로 저장
	var day = date.getDate();                      //d
	day = day >= 10 ? day : '0' + day;             //day 두자리로 저장
	return  year + '-' + month + '-' + day;
}

function <portlet:namespace/>dialogClose() {
	$("#<portlet:namespace/>virtualClass-manager-add-dialog").dialog("close");
}

function <portlet:namespace/>addDialogClose() {
	$("#<portlet:namespace/>virtualClass-add-dialog").dialog("close");
}

function <portlet:namespace/>dataSearchList(curPage) {
	$('#<portlet:namespace/>curPage').val(curPage);
	var dataForm = {
		"<portlet:namespace/>curPage" : curPage,
		"<portlet:namespace/>virtualLabId" : "${virtualLabId}",
		"<portlet:namespace/>groupId" : "${groupId}"
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabClassListURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var virtualLabClassList = msg.virtualLabClassList;
			var curPage = msg.curPage;
			var selectLine = msg.selectLine;
			var totalCnt = msg.totalCnt - ((curPage -1) * selectLine);
			var pageList = msg.pageList;
			var groupId = msg.groupId;
			var rowResult;
			$("#<portlet:namespace/>virtualLabClassListBody tr:not(:has(#1))").remove();
			
			if(virtualLabClassList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "8")
						  .css("text-align","center")
						  .css("height", "40px")
						  .html("<liferay-ui:message key='edison-there-are-no-data' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>virtualLabClassListBody").append($rowResult);
			} else {
				for(var i = 0; i < virtualLabClassList.length; i++) {
					$rowResult = $("<tr/>").attr("onClick", "<portlet:namespace/>goClass('" + virtualLabClassList[i].classId + "','" +  groupId + "','" +  virtualLabClassList[i].classTitle + "','" +  virtualLabClassList[i].virtualLabId + "')")
										   .addClass("onHover")  					   
										   .css("cursor","pointer");
										   
					
 					if(i%2 == 1){
 						$rowResult.addClass("tablebgtr");
 					}
 					if(virtualLabClassList[i].classId == "${virtualLabClassInfo.classId}") {
 						$rowResult.removeClass("tablebgtr");
 						$rowResult.addClass("selectClassRow");
 					}
 					
					$("<td/>").text(totalCnt--)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].classTitle)
							  .css("text-align","left")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].classStartDt + "~" + virtualLabClassList[i].classEndDt)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].classCreateDt)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].userCount + "/" +  (virtualLabClassList[i].classPersonnel*1 + virtualLabClassList[i].classExternalPersonnel*1))
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").html("<input type='button' value='<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-student-delete' />' class='btn btn-default' onclick='event.cancelBubble=true; <portlet:namespace/>deleteStudent(\"" + virtualLabClassList[i].virtualLabId + "\",\"" + virtualLabClassList[i].classId + "\",\"" + groupId + "\")'/>")
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").html("<input type='button' value='<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-delete' />' class='btn btn-default' onclick='event.cancelBubble=true; <portlet:namespace/>virtualLabClassDisable(\"" + virtualLabClassList[i].virtualLabId + "\",\"" + virtualLabClassList[i].classId + "\",\"" + groupId + "\")'/>")
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").html("<input type=\"button\" onclick=\"event.cancelBubble=true; <portlet:namespace/>setClassData('" + virtualLabClassList[i].classId + "')\" class=\"btn btn-default\" value=\"<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-update' />\" />")
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("#<portlet:namespace/>virtualLabClassListBody").append($rowResult);
				}
			}
			
			if(pageList != '') {
				$("#<portlet:namespace/>pageListDiv").addClass("paging").html(pageList);
			} else {
				$("#<portlet:namespace/>pageListDiv").removeClass("paging").html(pageList);
			}
			
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
	
}

function <portlet:namespace/>goClass(classId, groupId, classTitle, virtualLabId) {
	window.location.href = "${classURL}" + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_classId=" + classId + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_groupId=" + groupId + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_classTitle=" + classTitle + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_virtualLabId=" + virtualLabId;
}
	


function <portlet:namespace/>setClassData(classId) {
	var dataForm = {
		"<portlet:namespace/>classId" : classId,
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabClassInfoURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var virtualLabClassInfo = msg.virtualLabClassInfo;
			if (virtualLabClassInfo != undefined && virtualLabClassInfo != null) {
				document.classUpdateForm.<portlet:namespace/>classId.value = virtualLabClassInfo.classId;
				document.classUpdateForm.<portlet:namespace/>classTitle.value = virtualLabClassInfo.classTitle;
				$('#<portlet:namespace/>classTitle_en_US').attr("value", virtualLabClassInfo.classTitle_en_US);
				$('#<portlet:namespace/>classTitle_ko_KR').attr("value", virtualLabClassInfo.classTitle_ko_KR);
				document.classUpdateForm.<portlet:namespace/>classStartDt.value = virtualLabClassInfo.classStartDt;
				document.classUpdateForm.<portlet:namespace/>classEndDt.value = virtualLabClassInfo.classEndDt;
				document.classUpdateForm.<portlet:namespace/>classPersonnel.value = virtualLabClassInfo.classPersonnel;
				document.classUpdateForm.<portlet:namespace/>classExternalPersonnel.value = virtualLabClassInfo.classExternalPersonnel;
				$('#<portlet:namespace/>idSearchField').val("");
				<portlet:namespace/>getClassManagerList();
				$('input[name=<portlet:namespace/>virtualLabCalssSaveButton]').attr('value',"<liferay-ui:message key='save' />");
				$("#<portlet:namespace/>virtualClass-add-dialog").dialog("open");
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>setClassDataClear() {
	document.classUpdateForm.<portlet:namespace/>classId.value = "0";
	document.classUpdateForm.<portlet:namespace/>classTitle.value = "";
	document.classUpdateForm.<portlet:namespace/>classStartDt.value = "";
	document.classUpdateForm.<portlet:namespace/>classEndDt.value = "";
	$('#<portlet:namespace/>idSearchField').val("");
	document.classUpdateForm.<portlet:namespace/>classPersonnel.value = "";
	document.classUpdateForm.<portlet:namespace/>classExternalPersonnel.value = "";
	$('.selectClassRow').removeClass("selectClassRow");
	$('#<portlet:namespace/>classManagerList').empty();
	$('input[name=<portlet:namespace/>virtualLabCalssSaveButton]').attr('value',"<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-create' />");
	$("#<portlet:namespace/>virtualClass-add-dialog").dialog("open");
}

function <portlet:namespace/>getUserInfo() {
	var classId = $("#<portlet:namespace/>classId").val();
	var groupId = $("#<portlet:namespace/>groupId").val();
	
	if (classId != 0 && classId != undefined) {
		var dataForm = {
			"<portlet:namespace/>classId" : classId,
			"<portlet:namespace/>groupId" : groupId,
			"<portlet:namespace/>searchField" : $("#<portlet:namespace/>idSearchField").val(),
			"<portlet:namespace/>virtualLabId" : "${virtualLabId}"
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=virtualClassUserInfoURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var result = msg.result;
				var virtualLabClassUserInfo = msg.virtualLabClassUserInfo;
				
				if(result === undefined) {
					alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-user-notfound' />");
				} else if(result == "admin") {
					alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-admin-alert' />");
				} else if(result == "tempuser") {
					alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-tempuser-alert' />");
				} else if(result == "labowner" || result == "labmanager") {
					alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-admin-already-lab' />");
				} else if(result == "classowner" || result == "classmanager") {
					alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-admin-already-class' />");
				} else if(result == "none") {
					alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-user-notfound' />");
				} else if(result == "user") {
					$("#<portlet:namespace/>managerId").text(virtualLabClassUserInfo.userScreenName);
					$("#<portlet:namespace/>managerFullName").text(privateTextConverter2(virtualLabClassUserInfo.userFullName));
					$("#<portlet:namespace/>managerEmail").text(privateEmailConverter2(virtualLabClassUserInfo.userEmailAddress));
					$("#<portlet:namespace/>classUserId").val(virtualLabClassUserInfo.userId);
					$("#<portlet:namespace/>virtualClass-manager-add-dialog").dialog("open");
				}
				return false;
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
	} else {
		alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-after-create' />");
		return false;
	}
}

function <portlet:namespace/>getClassManagerList() {
	var dataForm = {
			"<portlet:namespace/>classId" : $("#<portlet:namespace/>classId").val(),
			"<portlet:namespace/>classUserId" : $("#<portlet:namespace/>classUserId").val(),
			"<portlet:namespace/>groupId" : $("#<portlet:namespace/>groupId").val()
		};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualClassManagerListURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var virtualLabClassAuthList = msg.virtualLabClassAuthList;
			var groupId = msg.groupId;
			var rowResult;
			
			$("#<portlet:namespace/>classManagerList").empty();
			
			if(virtualLabClassAuthList != undefined && virtualLabClassAuthList.length != 0) {
				$rowResult = $("<ul/>").css("margin", "0");
				for(var i = 0; i < virtualLabClassAuthList.length; i++) {
					$("<li/>").append(
									  $("<span/>").text(privateTextConverter2(virtualLabClassAuthList[i].userFullName) + "(" + virtualLabClassAuthList[i].userScreenName + ") " + privateEmailConverter2(virtualLabClassAuthList[i].userEmailAddress))
							  )
							  .append(
									  $("<img/>").attr("src", "${contextPath}/images/popupclosebtn.png")
												 .attr("onClick", "<portlet:namespace/>deleteClassManager('" + virtualLabClassAuthList[i].customId + "','" + virtualLabClassAuthList[i].userId+ "','" + groupId + "')")
												 .css("cursor", "pointer")
												 .attr("width", "20")
												 .attr("hegith", "16")
							  )
							  .css("margin", "8px")
							  .appendTo($rowResult);
					$("#<portlet:namespace/>classManagerList").append($rowResult);
				}
			}
			
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>deleteClassManager(virtualClassId, classManagerId, groupId) {
	if(confirm("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-manager-delete' />")){	
		var dataForm = {
				"<portlet:namespace/>virtualClassId" : virtualClassId,
				"<portlet:namespace/>classManagerId" : classManagerId,
				"<portlet:namespace/>groupId" : groupId,
			};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=deleteVirtualClassManagerURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				<portlet:namespace/>getClassManagerList();
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
	}
}

function <portlet:namespace/>checkValidation(form) {
	var saveBtn = $("#<portlet:namespace/>virtualLabCalssSaveBtn");
	
	if (form.submitted) return false;
	if(!checkValue(form.<portlet:namespace/>classTitle, "<liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' />", 4, 40)) {
		saveBtn.prop("disabled", false);
		return false;
	} else if (!checkValue(form.<portlet:namespace/>classStartDt, "<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-start-date' />", 10, 14)) {
		saveBtn.prop("disabled", false);
		return false;
	} else if (!checkValue(form.<portlet:namespace/>classEndDt, "<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-end-date' />", 10, 14)) {
		saveBtn.prop("disabled", false);
		return false;
	} else if (!checkValue(form.<portlet:namespace/>classDescription, "<liferay-ui:message key='edison-simulation-execute-simulation-description' />", 0, 75)) {
		saveBtn.prop("disabled", false);
		return false;
	} else if (!checkValue(form.<portlet:namespace/>classPersonnel, "<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-student-number' />", 1, 3)) {
		saveBtn.prop("disabled", false);
		return false;
	} else {
		form.submitted = true;
		return true;
	}
	saveBtn.prop("disabled", false);
	return false;
}

function <portlet:namespace/>deleteStudent(virtualLabId, classId, groupId) {
	if(confirm("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-student-delete-alert' />")){	
		var dataForm = {
			"<portlet:namespace/>virtualLabId" : virtualLabId,
			"<portlet:namespace/>classId" : classId,
			"<portlet:namespace/>groupId" : groupId
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=deleteStudentURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				if(msg.result == "200") {
					alert("<liferay-ui:message key='edison-data-delete-success' />");
					<portlet:namespace/>dataSearchList(1);
				}
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
	}
}

function <portlet:namespace/>virtualLabClassDisable(virtualLabId, classId, groupId) {
	var dataForm = {
		"<portlet:namespace/>virtualLabId" : virtualLabId,
		"<portlet:namespace/>classId" : classId,
		"<portlet:namespace/>groupId" : groupId
	}
	
	if(confirm("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-delete-alert' />")){	
		jQuery.ajax({
			type: "POST",
			url: "<%=virtualLabClassDisableURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var result = msg.result;
				if(result == "300" || result == "400") {
					alert("<liferay-ui:message key='edison-data-delete-error' />");
				} else if (result == "200") {
					alert("<liferay-ui:message key='edison-data-delete-success' />");
					<portlet:namespace/>dataSearchList(1);
				} else if (result == "500") {
					/* 수강인원이 존재하여 클래스를 삭제 할 수 없습니다. */
					alert("<liferay-ui:message key='edison-virtuallab-virtualLabClass-student-delete-message' />");
				} else {
					alert("<liferay-ui:message key='edison-data-delete-error' />");
				}
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
	}
}

function <portlet:namespace/>virtualLabCalssCreate(){
	$("#<portlet:namespace/>virtualLabCalssSaveBtn").prop("disabled", true);
	$("#<portlet:namespace/>virtualLabCalssSaveButton").click();
}

</script>

<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix detailViewSubTitle">
		<h3 class="panel-title pull-left">
			<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
			<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-management' />
		</h3>
		<div class="input-group">
			<div class="input-group-btn">
				<input type="hidden" id="<portlet:namespace/>curPage" name="<portlet:namespace/>curPage" value="${virtualLabClassInfo.curPage}" />
				<input id="<portlet:namespace/>virtualLabClassCreateBtn" name="<portlet:namespace/>virtualLabClassCreateBtn" type="button" class="btn btn-default" value="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-new-class-create' />" onClick="<portlet:namespace/>setClassDataClear()" style="float: right;"/>
			</div>
		</div>
	</div>

	<table class="table table-bordered table-hover edison-table" width="100%" border="0" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="5%" />
			<col width="25%" />
			<col width="20%" />
			<col width="10%" />
			<col width="13%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-index' /></th>
				<th align="left" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-course-virtualLabClassRegistrationList-registration-period' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-creation-date' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-total-number4' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-student-delete' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-delete' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-update' /></th>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>virtualLabClassListBody">
		</tbody>
	</table>
	
	<div id="<portlet:namespace/>pageListDiv" class="text-center"></div>
	
	<div id="<portlet:namespace/>spaceDiv" align="center"></div>
	<div class="h30"></div>
	
	<div id="<portlet:namespace/>virtualClass-add-dialog" title="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-management' />" class="table-responsive panel edison-panel" style="background-color:#fff; display:none;">
		<div class="newWheader">
			<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
				<div class="newWtitle">
					<liferay-ui:message key='registration-information' />
				</div>
			</div>
			<div class="newWclose">
				<img id="<portlet:namespace/>virtualClass-add-close-btn" name="<portlet:namespace/>virtualClass-add-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
			</div>
		</div>
			
		<form id="classUpdateForm" name="classUpdateForm" action="<%=virtualClassSaveURL%>" method="post" onsubmit="return <portlet:namespace/>checkValidation(this);">	
			<input type="hidden" id="<portlet:namespace/>classId" name="<portlet:namespace/>classId" value="${virtualLabClassInfo.classId}" />
			<input type="hidden" id="<portlet:namespace/>virtualLabId" name="<portlet:namespace/>virtualLabId" value="${virtualLabId}" />
			<input type="hidden" id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" value="${groupId}" />
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table1_list table table-bordered table-hover edison-table">
				<colgroup>
					<col width="18%" />
					<col width="82%" />
				</colgroup>
				<tbody>
					<tr>
						<th><liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' /></th>
						<td>
							<div class="localDiv">
								<liferay-ui:input-localized id="classTitle" name="classTitle" xml="${virtualLabClassInfo.classTitleMap}" style="width:206px;" cssClass="form-control" type="input"/>
							</div>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key='edison-course-virtualLabClassRegistrationList-registration-period' /></th>
						<td>
							<input name="<portlet:namespace/>classStartDt" id="<portlet:namespace/>classStartDt" readonly="readonly" value="${virtualLabClassInfo.classStartDt}" required style="width: 140px;"/>
							&nbsp;~&nbsp;
							<input name="<portlet:namespace/>classEndDt" id="<portlet:namespace/>classEndDt" readonly="readonly" value="${virtualLabClassInfo.classEndDt}"  style="width: 140px;"/>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-student-number' /></th>
						<td>
							<input id="<portlet:namespace/>classPersonnel" name="<portlet:namespace/>classPersonnel" type="number" class="form-control" style="width: 90px;" min="1" max="999" value="${virtualLabClassInfo.classPersonnel}"/>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-external-student-number' /></th>
						<td>
							<input id="<portlet:namespace/>classExternalPersonnel" name="<portlet:namespace/>classExternalPersonnel" type="number" class="form-control" style="width: 90px;" min="0" max="999" value="${virtualLabClassInfo.classExternalPersonnel}"/>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-manager' /><br><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-id-search' /></th>
						<td class="input-group" style="display: table-cell;">
							<input id="<portlet:namespace/>idSearchField" name="<portlet:namespace/>idSearchField" type="text" class="form-control" onkeypress="if(event.keyCode == 13) { <portlet:namespace/>getUserInfo(); return false;}" style="width:150px; margin-bottom:0px; float: left;"/>
							<div class="input-group-btn">
								<button class="btn btn-default" id="<portlet:namespace/>virtualLabClassManagerSearchButton" name="<portlet:namespace/>virtualLabClassManagerSearchButton" onClick="<portlet:namespace/>getUserInfo(); return false;">
									<liferay-ui:message key='edison-button-search' />
								</button>
							</div>
							<div id="<portlet:namespace/>classManagerList"></div>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-curriculum-url' /></th>
						<td>
							<input id="<portlet:namespace/>classCurriculumUrl" name="<portlet:namespace/>classCurriculumUrl" type="text" class="form-control" style="width:150px;  margin-bottom:0px;" value="${virtualLabClassInfo.classCurriculumUrl}"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div align="right" style="margin-bottom: 10px; padding-right: 20px;">
				<c:if test="${empty virtualLabClassInfo}">
					<input id="<portlet:namespace/>virtualLabCalssSaveBtn" name="<portlet:namespace/>virtualLabCalssSaveBtn" class="btn btn-default" onclick="<portlet:namespace/>virtualLabCalssCreate()" type="button" value="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-create' />" />
					<input id="<portlet:namespace/>virtualLabCalssSaveButton" name="<portlet:namespace/>virtualLabCalssSaveButton" class="btn btn-default" style="display: none;" type="submit" value="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-create' />" />
				</c:if>
				<c:if test="${!empty virtualLabClassInfo}">
					<input id="<portlet:namespace/>virtualLabCalssSaveBtn" name="<portlet:namespace/>virtualLabCalssSaveBtn" class="btn btn-default" onclick="<portlet:namespace/>virtualLabCalssCreate()" type="button" value="<liferay-ui:message key='edison-button-board-modify' />" />
					<input id="<portlet:namespace/>virtualLabCalssSaveButton" name="<portlet:namespace/>virtualLabCalssSaveButton" class="btn btn-default" style="display: none;" type="submit" value="<liferay-ui:message key='edison-button-board-modify' />" />
				</c:if>
			</div>
			
		</form>
	</div>
	
	<div id="<portlet:namespace/>virtualClass-manager-add-dialog" title="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-manager-register' />" class="newWindow" style="background-color:#fff; display:none;">
		<div class="newWheader">
			<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
				<div class="newWtitle">
					<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-manager-register' />
				</div>
			</div>
			<div class="newWclose">
				<img id="<portlet:namespace/>virtualClass-manager-add-dialog-close-btn" name="<portlet:namespace/>virtualClass-manager-add-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
			</div>
		</div>
		
		<form id="virtualClassManagerAddForm" name="virtualClassManagerAddForm" method="post">
			<div class="newWcont01" style="min-width:500px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<colgroup>
						<col width="50%" />
						<col width="50%" />
					</colgroup>
					<tbody>
						<tr class="puptrline">
							<td class="puptitle"><liferay-ui:message key='edison-table-list-header-userid' /></td>
							<td class="puptitle" id="<portlet:namespace/>managerId"></td>
						</tr>
						<tr>
							<td class="puptxt2"><liferay-ui:message key='edison-table-list-header-usernm' /></td>
							<td class="puptxt2" id="<portlet:namespace/>managerFullName"></td>
						</tr>
						<tr>
							<td class="puptxt2">E-Mail</td>
							<td class="puptxt2" id="<portlet:namespace/>managerEmail"></td>
						</tr>
						<tr>
							<td class="puptxt2" colspan="2" style="text-align: center; color:#f03030;"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-manager-register-confirm' /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<input id="<portlet:namespace/>classUserId" name="<portlet:namespace/>classUserId" type="hidden" value="0">
			<input id="<portlet:namespace/>virtualLabId" name="<portlet:namespace/>virtualLabId" type="hidden" value="${virtualLabId}">
			<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${groupId}">
			<div style="text-align: right; margin:0px 25px 30px 0px;">
				<input id="<portlet:namespace/>register_request_button" name="register_request_button" type="button"  class="btn btn-default" value="<liferay-ui:message key='edison-button-register' />" onClick="<portlet:namespace/>getClassManagerList(); <portlet:namespace/>dialogClose(); this.disabled=true;" />
			</div>
		</form>
	</div>
</div>