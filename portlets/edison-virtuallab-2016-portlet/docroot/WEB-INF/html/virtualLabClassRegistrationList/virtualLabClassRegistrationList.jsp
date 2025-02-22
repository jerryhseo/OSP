<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>

<liferay-portlet:resourceURL var="classRegistrationListURL" id="classRegistrationList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="virtualLabClassRegisterCheckURL" id="virtualLabClassRegisterCheck" copyCurrentRenderParameters="false" />

<liferay-portlet:actionURL var="registerRequestURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="registerRequest" />
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="classCancelResourceURL" id="cancelClassResource" copyCurrentRenderParameters="false" />

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList(1);
	
	$("#register-request-dialog").dialog({
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
	    	
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	$("#register-request-dialog-close-btn").click(function() {
		$("#register-request-dialog").dialog("close");
	});
	
	$("#request-denied-dialog").dialog({
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
	    	
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	$("#request-denied-dialog-close-btn").click(function() {
		$("#request-denied-dialog").dialog("close");
	});

});

function <portlet:namespace/>dataSearchList(pageNumber) {
	if(pageNumber == 0) {
		$("#<portlet:namespace/>cur_page").val(1);
		$("#<portlet:namespace/>search_parameter").val("");
	} else {
		$("#<portlet:namespace/>cur_page").val(pageNumber);
	}
	
	var searchForm = $("form[name=searchForm]").serialize();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=classRegistrationListURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
			var virtualLabClassRegisterList = msg.virtualLabClassRegisterList;
			var groupId = msg.groupId;
			var pageList = msg.pageList;
			var rowResult;
			$("#<portlet:namespace/>registrationListBody tr:not(:has(#1))").remove();
			
			if(virtualLabClassRegisterList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").addClass("center")
						  .attr("colspan", "8")
						  .css("text-align","center")
						  .text("<liferay-ui:message key='edison-search-no-result' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>registrationListBody").append($rowResult);
			} else {
				
				for(var i = 0; i < virtualLabClassRegisterList.length; i++) {
					$rowResult = $("<tr/>");
					
 					if(i%2 == 1){
 						$rowResult.addClass("tablebgtr");
 					}
					
					$("<td/>").addClass("center")
							  .text(virtualLabClassRegisterList[i].groupName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(virtualLabClassRegisterList[i].virtualLabTitle)
							  .css("text-align","left")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(virtualLabClassRegisterList[i].virtualLabUniversityField)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(virtualLabClassRegisterList[i].virtualLabPersonName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(virtualLabClassRegisterList[i].classTitle)
							  .css("text-align","left")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(virtualLabClassRegisterList[i].createDt)
							  .css("text-align","center")
							  .appendTo($rowResult);
					if(virtualLabClassRegisterList[i].requestSort == "CONFIRM" || virtualLabClassRegisterList[i].requestSort == "TEMP") {
						$("<td/>").addClass("center").css("text-align","center")
								  .append($("<p/>").text("<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-take-class' />")
												   .css("font-weight", "600")
												   .css("color", "blue")
												   .css("margin","0")
									)
						  .appendTo($rowResult);
						$("<td/>").addClass("center").css("text-align","center")
								  .append($("<input/>").attr("value", "<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-move' />")
													   .addClass("btn btn-default")
													   .attr("type", "button")
													   .css("text-align","center")
													   .attr("onClick","<portlet:namespace/>registerCheck('" + virtualLabClassRegisterList[i].virtualLabId + "','" + virtualLabClassRegisterList[i].classId + "','" + virtualLabClassRegisterList[i].groupId + "')")
								  )
						  .appendTo($rowResult);
					} else if (virtualLabClassRegisterList[i].requestSort == "REQUEST") {
						$("<td/>").addClass("center").css("text-align","center")
								  .append($("<p/>").text("<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-during-registration' />")
												   .css("font-weight", "600")
												   .css("margin","0")
									)
								  .appendTo($rowResult);
						$("<td/>").addClass("center").css("text-align","center")
								  .append($("<input/>").attr("value", "<liferay-ui:message key='edison-button-cancel' />")
													   .addClass("btn btn-default")
													   .attr("type", "button")
													   .css("text-align","center")
													   .attr("onClick","<portlet:namespace/>registerCheck('" + virtualLabClassRegisterList[i].virtualLabId + "','" + virtualLabClassRegisterList[i].classId + "','" + virtualLabClassRegisterList[i].groupId + "')")
								  )
								  .appendTo($rowResult);
					} else if (virtualLabClassRegisterList[i].requestSort == "DENIED") {
						$("<td/>").addClass("center").css("text-align","center")
								  .append($("<p/>").text("<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-registration-denial' />")
												   .css("font-weight", "600")
												   .css("color", "red")
												   .css("margin","0")
								  )
								  .appendTo($rowResult);
						$("<td/>").addClass("center").css("text-align","center")
								  .append($("<input/>").attr("value", "<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-reason-denial' />")
													   .addClass("btn btn-default")
													   .attr("type", "button")
													   .attr("onClick","<portlet:namespace/>openDeniedDialog('" + "<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-reason-denial' />" + "','" + virtualLabClassRegisterList[i].processNote + "','" + virtualLabClassRegisterList[i].classId + "','" + virtualLabClassRegisterList[i].groupId +"')")
								  )
								  .appendTo($rowResult);
					} else {
						$("<td/>").addClass("center")
								  .text("<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-unknown-class' />")
								  .css("text-align","center")
								  .appendTo($rowResult);
					}
					
					$("#<portlet:namespace/>registrationListBody").append($rowResult);
				}
			}
			$("#<portlet:namespace/>pageListDiv").html(pageList);
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>registerCheck(virtualLabId, classId, groupId) {
	var checkForm = {
		"<portlet:namespace/>groupId" : groupId,
		"<portlet:namespace/>virtualLabId" : virtualLabId,
		"<portlet:namespace/>classId" : classId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabClassRegisterCheckURL%>",
		async : false,
		data : checkForm,
		success: function(msg) {
			var virtualLabClassUserInfo = msg.virtualLabClassUserInfo;
			var classURL = msg.classURL;
			
			if(msg.result == "ADMINISTRATOR") {
					window.location.href = classURL + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_classId=" + classId + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_groupId=" + groupId;
			}else {
				$("#<portlet:namespace/>registerClassRequestNo").val(virtualLabClassUserInfo.classId);
				$("#classPersonnel").text(virtualLabClassUserInfo.classPersonnel);
				$("#totalUserCount").text(virtualLabClassUserInfo.totalUserCount);
				$("#registerClassTitle").text(virtualLabClassUserInfo.classTitle);
				$("#registerClassProfessor").text(virtualLabClassUserInfo.virtualLabPersonName);
				$("#registerClassCompany").text(virtualLabClassUserInfo.virtualLabUniversityField);
				$("#registerClassCount").text("(" + virtualLabClassUserInfo.totalUserCount + "/" + virtualLabClassUserInfo.classPersonnel + ")");
				
				if(virtualLabClassUserInfo.requestSort == "REQUEST") {
					requestDialog(virtualLabClassUserInfo.virtualUserId);
				} else if(virtualLabClassUserInfo.requestSort == "DENIED") {
					alert(virtualLabClassUserInfo.processNote);
				} else if(virtualLabClassUserInfo.requestSort == "CONFIRM" || virtualLabClassUserInfo.requestSort == "TEMP") {
					window.location.href = classURL + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_classId=" + classId + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_groupId=" + groupId;
				} else if(virtualLabClassUserInfo.totalUserCount >= virtualLabClassUserInfo.classPersonnel){
					alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-over-personnel' />");
				} else {
					requestDialog(virtualLabClassUserInfo.virtualUserId);
				}
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function requestDialog(virtualUserId) {
	if (virtualUserId == undefined) {
		$('#register_request_button').val("<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-course-registration' />");
		$("#<portlet:namespace/>registerVirtualUserId").val(0);
	} else {
		$('#register_request_button').val("<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-cancel-registration' />");
		$("#<portlet:namespace/>registerVirtualUserId").val(virtualUserId);
	}
	$("#register-request-dialog").dialog( "open" );
}

function <portlet:namespace/>openDeniedDialog(title, content, classId, groupId) {
	if(classId > 0) {
		$("#register_delete_button").css("visibility", "visible");
	}else {
		$("#register_delete_button").css("visibility", "hidden");
	}
	$("#<portlet:namespace/>tempClassId").val(classId);
	$("#<portlet:namespace/>tempGroupId").val(groupId);
	$("#request-denied-title").text(title);
	$("#request-denied-content").text(content);
	$("#request-denied-dialog").dialog("open");
}

function deleteClassRequest() {
	var tempClassId = $("#<portlet:namespace/>tempClassId").val();
	var tempGroupId = $("#<portlet:namespace/>tempGroupId").val();
	<portlet:namespace/>cancelClassRequest(tempClassId, tempGroupId);
	$("#<portlet:namespace/>request-denied-dialog").dialog("close");
}


function <portlet:namespace/>cancelClassRequest(classId, groupId) {
	if(confirm("<liferay-ui:message key='edison-course-class-cancel-request-alert' />")){	
		var checkForm = {
			"<portlet:namespace/>classId" : classId,
			"<portlet:namespace/>groupId" : groupId
		}
		
		jQuery.ajax({
			type: "POST",
			url: "<%=classCancelResourceURL%>",
			async : false,
			data : checkForm,
			success: function(msg) {
				$("#request-denied-dialog").dialog("close");
				<portlet:namespace/>dataSearchList();
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
	}
}

</script>

<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
	<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1"/>
</form>

<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-class-registration-status' />
		</h3>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
		<colgroup>
			<col width="9%" />
			<col width="20%" />
			<col width="15%" />
			<col width="10%" />
			<col width="20%" />
			<col width="11%" />
			<col width="15%" />
		</colgroup>
		<thead>
			<tr>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-site' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-affiliation' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-professor' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-request-date' /></th>
				<th align="center" scope="col" colspan="2"><liferay-ui:message key='edison-virtuallab-tablerow-status' /></th>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>registrationListBody">
		</tbody>
	</table>
	<div id="<portlet:namespace/>spaceDiv" align="center"></div>
	<div id="<portlet:namespace/>pageListDiv" class="paging"></div>
</div>

<div id="register-request-dialog" title="<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-course-registration' />" class="newWindow" style="background-color: #fff; display:none;" >
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">
				<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-class-registration-status' />
			</div>
		</div>
		<div class="newWclose">
			<img id="register-request-dialog-close-btn" name="register-request-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	<form id="registerRequestForm" name="registerRequestForm" method="post" action="<%= registerRequestURL %>" onsubmit="">
		<input id="classPersonnel" name="classPersonnel" type="hidden" value="0">
		<input id="totalUserCount" name="totalUserCount" type="hidden" value="0">
		<input id="<portlet:namespace/>registerVirtualUserId" name="<portlet:namespace/>registerVirtualUserId" type="hidden">
		<input id="<portlet:namespace/>registerClassRequestNo" name="<portlet:namespace/>registerClassRequestNo" type="hidden">
		<div class="newWcont01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="width:500px;">
				<colgroup>
					<col width="50%" />
					<col width="50%" />
				</colgroup>
				<tbody>
					<tr class="puptrline">
						<td class="puptitle"><liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' /> : </td>
						<td class="puptitle" id="registerClassTitle"></td>
					</tr>
					<tr>
						<td class="puptxt2"><liferay-ui:message key='edison-virtuallab-tablerow-professor' /> : </td>
						<td class="puptxt2" id="registerClassProfessor"></td>
					</tr>
					<tr>
						<td class="puptxt2"><liferay-ui:message key='edison-virtuallab-tablerow-affiliation' /> : </td>
						<td class="puptxt2" id="registerClassCompany"></td>
					</tr>
					<tr>
						<td class="puptxt2"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-student-number' />(<liferay-ui:message key='edison-virtuallab-course-current' />/<liferay-ui:message key='edison-virtuallab-course-maximum' />) : </td>
						<td class="puptxt2" id="registerClassCount"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="text-align: right; margin:0px 30px 25px 0px;">
			<input id="register_request_button" name="register_request_button" class="btn btn-default" type="submit" value="<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-course-registration' />" />
		</div>
	</form>
</div>

<div id="request-denied-dialog" title="<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-course-registration' />" class="newWindow" style="background-color: #fff; display:none;" >
	<input id="<portlet:namespace/>tempClassId" name="<portlet:namespace/>tempClassId" type="hidden">
	<input id="<portlet:namespace/>tempGroupId" name="<portlet:namespace/>tempGroupId" type="hidden">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">
				<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-reason-denial' />
			</div>
		</div>
		<div class="newWclose">
			<img id="request-denied-dialog-close-btn" name="request-denied-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	
	<div class="newWcont01">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="width:350px; min-height:50px;">
			<tbody>
				<tr>
					<td id="request-denied-content" class="puptxt2"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="text-align: right; margin:0px 25px 30px 0px;">
		<input id="register_delete_button" name="register_delete_button" class="btn btn-default" type="button" onclick="deleteClassRequest()" value="<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-cancel-registration' />" />
	</div>
</div>
