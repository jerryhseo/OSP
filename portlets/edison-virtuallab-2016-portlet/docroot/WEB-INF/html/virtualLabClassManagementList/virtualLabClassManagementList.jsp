<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>

<liferay-portlet:resourceURL var="virtualLabClassManagementListURL" id="virtualLabClassManagementList" copyCurrentRenderParameters="false" />

<liferay-portlet:resourceURL var="virtualLabClassRegisterCheckURL" id="virtualLabClassRegisterCheck" copyCurrentRenderParameters="false" />

<liferay-portlet:actionURL var="registerRequestURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="registerRequest" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL var="saveClickTabURL" portletMode="view" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="saveClickTab"/>
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="classCancelResourceURL" id="cancelClassResource" copyCurrentRenderParameters="false" />

<style type="text/css">
	.onHover:hover {
		background:#e0e0e0;
	}
	.backHover1:hover {
		background:#e0e0e0;
		text-decoration: underline;
		font-weight: 600;
	}
	.backHover2:hover {
		background:#e0e0e0;
		text-decoration: underline;
		font-weight: 600;
	}
	.backHover1 {
		background-color:#f8fafe;
	}
	
	.topright{
	float: right;
	margin-top: 20px;
	border-radius: 3px 3px 3px 3px;
	border-bottom-color: #fff;
	padding: 5px;
	border: solid 1px #ddd;
	}	
	
	.table_popup_list{
		line-height: 2.0em;
		width: 96.5%;
		font-size: 14px;
		margin : 10px;
		border-top: 2px solid #1c9bd3;
	}
	
	.table_popup_list th{
		border-bottom: 1px solid #b4b4b4;
		padding: 8px;
		color : #383838;
		font-weight: 600;
		background-color: #f7f7f7;
		text-align: center;
		font-size: 15px;
	}
	
	.selectview.dropdown-menu{
		display: block;
		position: relative;
	}
	
</style>

<body>
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3><liferay-ui:message key='edison-course-class-list' /></h3>
			<div class="input-group" style="float: left;">	
				<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
					<input type="text" id="<portlet:namespace/>search_parameter" class="form-control" name="<portlet:namespace/>search_parameter" style="width:250px; margin-right: 5px;" maxlength="15" placeholder="<liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' />" onkeypress="<portlet:namespace/>onKeyDown(event);" />
					<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1"/>
					<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${groupId}"/>
					<input id="<portlet:namespace/>virtualLabId" name="<portlet:namespace/>virtualLabId" type="hidden" value="${virtualLabId}"/>
					<input type="button" onClick="<portlet:namespace/>dataSearchList()" class="btn btn-default" value="<liferay-ui:message key='edison-button-search' />" />
					<input type="button" onClick="<portlet:namespace/>dataSearchList(0)" class="btn btn-default" value="<liferay-ui:message key='edison-button-all-search' />" />
					
				</form>
				
				<div class="input-group-btn">
					<select id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" onchange="<portlet:namespace/>dataSearchList(0)" class="btn btn-default">	<!-- selectview -->
						<option value="10">10<liferay-ui:message key='edison-search-views' /></option>
						<option value="20">20<liferay-ui:message key='edison-search-views' /></option>
						<option value="30">30<liferay-ui:message key='edison-search-views' /></option>
						<option value="40">40<liferay-ui:message key='edison-search-views' /></option>
					</select>
				</div>
			</div>
		</div>
		<div class="h10"></div>
		
		<div class="table7_list onhover">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
				<colgroup>
					<col width="5%" />
					<col width="20%" />
					<col width="15%" />
					<col width="15%" />
					<col width="20%" />
					<col width="12%" />
					<col width="13%" />
				</colgroup>
				<thead>
					<tr>
						<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-index' /></th>
						<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' /></th>
						<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-affiliation' /></th>
						<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-professor' /></th>
						<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' /></th>
						<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-creation-date' /></th>
						<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-total-number4' /></th>
					</tr>
				</thead>
				<tbody id="<portlet:namespace/>virtualLabListBody">
				</tbody>
			</table>
		</div>
		
		<div id="<portlet:namespace/>spaceDiv" align="center"></div>
		<div id="<portlet:namespace/>pageListDiv" class="paging"></div>
		<div class="h20"></div>
	</div>
</body>

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
		<div class="table_popup_list table1_list">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="width:500px;">
				<colgroup>
					<col width="50%" />
					<col width="50%" />
				</colgroup>
				<tbody>
					<tr class="puptrline">
						<th class="puptitle"><liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' /> : </th>
						<td class="puptitle" id="registerClassTitle"></td>
					</tr>
					<tr class="puptrline">
						<th class="puptxt2"><liferay-ui:message key='edison-virtuallab-tablerow-professor' /> : </th>
						<td class="puptxt2" id="registerClassProfessor"></td>
					</tr>
					<tr class="puptrline">
						<th class="puptxt2"><liferay-ui:message key='edison-virtuallab-tablerow-affiliation' /> : </th>
						<td class="puptxt2" id="registerClassCompany"></td>
					</tr>
					<tr class="puptrline">
						<th class="puptxt2"><liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-student-number' />(<liferay-ui:message key='edison-virtuallab-course-current' />/<liferay-ui:message key='edison-virtuallab-course-maximum' />) : </th>
						<td class="puptxt2" id="registerClassCount"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="text-align: right; margin:0px 30px 25px 0px;">
			<input id="register_request_button" name="register_request_button" class="button06" type="submit" value="<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-course-registration' />" />
		</div>
	</form>
</div>

<div id="request-denied-dialog" title="<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-course-registration' />" class="newWindow" style="background-color: #fff; display:none;" >
	<input id="<portlet:namespace/>tempClassId" name="<portlet:namespace/>tempClassId" type="hidden">
	<input id="<portlet:namespace/>tempVirtualUserId" name="<portlet:namespace/>tempVirtualUserId" type="hidden">
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
		<input id="register_delete_button" name="register_delete_button" class="button06" type="button" onclick="deleteClassRequest()" value="<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-cancel-registration' />" />
	</div>
</div>

<script type="text/javascript">
AUI().ready(function() {
	var isTempUser = "${isTempUser}";
	var classId = "${classId}";
	var groupId = "${visitSite}";
	
	<portlet:namespace/>dataSearchList(1);
	
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
		url: "<%=virtualLabClassManagementListURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
			var colorNum = 1;
			var curPage = msg.curPage;
			var selectLine = msg.selectLine;
			var virtualLabClassCount = msg.virtualLabClassCount - ((curPage -1) * selectLine);
			var pageList = msg.pageList;
			var virtualLabClassList = msg.virtualLabClassList;
			var groupId = msg.groupId;
			var loginYn = msg.loginYn;
			
			var rowResult;
			var rowUl;
			$("#<portlet:namespace/>virtualLabListBody div:not(:has(#1))").remove();
			$("#<portlet:namespace/>virtualLabListBody tr:not(:has(#1))").remove();
			
			if(virtualLabClassList.length ==0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "7")
						  .css("text-align","center")
						  .css("height", "40px")
						  .text("<liferay-ui:message key='edison-search-no-result' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>virtualLabListBody").append($rowResult);
			}else if(virtualLabClassList.length !=0) {
				for(var i = 0; i < virtualLabClassList.length; i++) {
					if(loginYn == 'Y'){
						$rowResult = $("<tr/>").attr("onClick","<portlet:namespace/>registerCheck('" + virtualLabClassList[i].virtualLabId + "','" +  virtualLabClassList[i].classId + "','" + groupId + "')")
											   .css("cursor","pointer")
											   .addClass("onHover")
					}else{
						$rowResult = $("<tr/>").addClass("onHover")
					}
 					if(i%2 == 1){
 						$rowResult.addClass("tablebgtr");
 					}
 					
					$("<td/>").text(virtualLabClassCount--)
							  .addClass("nothover")
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].virtualLabTitle)
							  .addClass("nothover")
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].virtualLabUniversityFieldNm)
							  .addClass("nothover")
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].virtualLabPersonName)
							  .addClass("nothover")
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].classTitle)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].classCreateDt)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabClassList[i].userCount + " / " + virtualLabClassList[i].classPersonnel)
							  .css("text-align","center")
							  .appendTo($rowResult);
					
					$("#<portlet:namespace/>virtualLabListBody").append($rowResult);
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
		"<portlet:namespace/>virtualLabId" : virtualLabId,
		"<portlet:namespace/>classId" : classId,
		"<portlet:namespace/>groupId" : groupId
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
				window.location.href = classURL + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_classId=" + classId + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_groupId=" + groupId + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_classTitle=" + virtualLabClassUserInfo.classTitle + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_virtualLabId=" + virtualLabId;
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
					var title = "<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-reason-denial' />";
					<portlet:namespace/>openDeniedDialog(title, virtualLabClassUserInfo.processNote, virtualLabClassUserInfo.virtualUserId, virtualLabClassUserInfo.classId);
				} else if(virtualLabClassUserInfo.requestSort == "CONFIRM" || virtualLabClassUserInfo.requestSort == "TEMP") {
					window.location.href = classURL + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_classId=" + classId + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_groupId=" + groupId + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_classTitle=" + virtualLabClassUserInfo.classTitle + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_virtualLabId=" + virtualLabId;
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

function <portlet:namespace/>onKeyDown(e){
	if(e.keyCode == 13){
		<portlet:namespace/>dataSearchList();
		return false;
	}
}

function deleteClassRequest() {
	var tempClassId = $("#<portlet:namespace/>tempClassId").val();
	var tempVirtualUserId = $("#<portlet:namespace/>tempVirtualUserId").val();
	<portlet:namespace/>cancelClassRequest(tempClassId, tempVirtualUserId);
	$("#request-denied-dialog").dialog("close");
}

function <portlet:namespace/>cancelClassRequest(classId, virtualUserId) {
	if(confirm("<liferay-ui:message key='edison-course-class-cancel-request-alert' />")){	
		var checkForm = {
			"<portlet:namespace/>classId" : classId,
			"<portlet:namespace/>virtualUserId" : virtualUserId
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

function <portlet:namespace/>openDeniedDialog(title, content, virtualUserId, classId) {
	if(classId > 0) {
		$("#register_delete_button").css("visibility", "visible");
	}else {
		$("#register_delete_button").css("visibility", "hidden");
	}
	$("#<portlet:namespace/>tempClassId").val(classId);
	$("#<portlet:namespace/>tempVirtualUserId").val(virtualUserId);
	$("#request-denied-title").text(title);
	$("#request-denied-content").text(content);
	$("#request-denied-dialog").dialog("open");
}

//liferay-ui 탭 이벤트 return Script
function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
	window.location.href = "<%= saveClickTabURL.toString() %>"+"&<portlet:namespace/>groupId=" + value;
}

</script>