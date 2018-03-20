<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="virtualLabRequestListURL" id="virtualLabRequestList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="getSitePagePlidURL" id="getSitePagePlid" copyCurrentRenderParameters="false" />

<liferay-portlet:actionURL var="updateVirtualLabStatusURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="updateVirtualLabStatus" />
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="virtualLabInfomationURL" id="virtualLabInfomation" copyCurrentRenderParameters="false" />

<style>
.subtitlearea{
	margin-left: 10px;
}
.detailViewSubTitle{padding-left: 0px !important; padding-right: 0px !important;}
</style>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList(1);
	
	$("#<portlet:namespace/>virtualLab-process-dialog").dialog({
		autoOpen: false,
		width: 914,
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
	
	$("#<portlet:namespace/>virtualLab-process-dialog-close-btn").click(function() {
		$("#<portlet:namespace/>virtualLab-process-dialog").dialog("close");
	});
});

function <portlet:namespace/>dataSearchList(pageNumber) {
	if(pageNumber == 0) {
		$("#<portlet:namespace/>cur_page").val(1);
	} else {
		$("#<portlet:namespace/>cur_page").val(pageNumber);
	}
	
	var searchForm = $("form[name=searchForm]").serialize();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabRequestListURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
			var virtualLabRequestList = msg.virtualLabRequestList;
			var curPage = msg.curPage;
			var selectLine = msg.selectLine;
			var virtualLabCount = msg.virtualLabCount - ((curPage -1) * selectLine);
			var pageList = msg.pageList;
			
			var rowResult;
			$("#<portlet:namespace/>virtualLabListBody tr:not(:has(#1))").remove();
			
			if(virtualLabRequestList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "8")
						  .css("text-align","center")
						  .addClass("TC left")
						  .css("height", "40px")
						  .text("<liferay-ui:message key='edison-there-are-no-data' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>virtualLabListBody").append($rowResult);
			} else {
				for(var i = 0; i < virtualLabRequestList.length; i++) {
					$rowResult = $("<tr/>");
 					if(i%2 == 1){
 						$rowResult.addClass("tablebgtr");
 					}
					$rowResult.attr("onClick","<portlet:namespace/>virtualLabRequestConfirmDialog('" + virtualLabRequestList[i].virtualLabId + "')")
							  .css("cursor","pointer");
					$("<td/>").text(virtualLabRequestList[i].groupName)
							  .addClass("TC left")
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRequestList[i].virtualLabTitle)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRequestList[i].virtualLabUniversityField)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRequestList[i].virtualLabPersonName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(privateTextConverter2(virtualLabRequestList[i].userFirstName))
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRequestList[i].userScreenName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRequestList[i].virtualLabRequestDt)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRequestList[i].virtualLabStatusNm)
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

function <portlet:namespace/>virtualLabRequestConfirmDialog(virtualLabId) {
	var checkForm = {
		"<portlet:namespace/>virtualLabId" : virtualLabId,
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabInfomationURL%>",
		async : false,
		data : checkForm,
		success: function(msg) {
			var virtualLabInfo = msg.virtualLabInfo;
			
			$("#<portlet:namespace/>requestUserScreenName").text(virtualLabInfo.userScreenName);
			$("#<portlet:namespace/>requestUserFullName").text(virtualLabInfo.userName);
			$("#<portlet:namespace/>requestPersonName").text(virtualLabInfo.virtualLabPersonName);
			$("#<portlet:namespace/>requestVirtualLabTitle").text(virtualLabInfo.virtualLabTitle);
			$("#<portlet:namespace/>requestUniversityField").text(virtualLabInfo.virtualLabUniversityFieldNm);
			$("#<portlet:namespace/>requestVirtualLabDescription").html("<textarea rows=\"2\" readonly=\"readonly\" style=\"width:95%; resize:none;\" spellcheck=\"false\" >" + virtualLabInfo.virtualLabDescription + "</textarea>");
			$("#<portlet:namespace/>processVirtualLabId").val(virtualLabInfo.virtualLabId);
			$("#<portlet:namespace/>requestUserId").val(virtualLabInfo.requestUserId);
			$("#<portlet:namespace/>processDescription").val(virtualLabInfo.virtualLabConfirmDescription);
			$("#<portlet:namespace/>processStatus").val(virtualLabInfo.virtualLabStatus);
			$("#<portlet:namespace/>processRequestDt").text(virtualLabInfo.virtualLabRequestDt);
			$("#<portlet:namespace/>processConfirmDt").text(virtualLabInfo.virtualLabConfirmDt);
			
			if(virtualLabInfo.virtualLabStatus==1401001){
				$("#mailCheckBox").show();
			} else if (virtualLabInfo.virtualLabStatus==1401004){
				$("#<portlet:namespace/>delete_button").show();
			}
			
			$("#<portlet:namespace/>virtualLab-process-dialog").dialog("open");
		},error:function(msg,e){
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>submitVirtualLabRequest(){
	
	var searchForm = $("form[name=searchForm]").serialize();
	
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createActionUrl();
		portletURL.setPortletId("edisonvirtuallabrequestmanagement_WAR_edisonvirtuallab2016portlet");
		portletURL.setParameter("myaction", "updateVirtualLabStatus");
		
		jQuery.ajax({
			type: "POST",
			url: portletURL,		// simulationController로 요청하는 URL
			async : false,
			dataType: 'json',
			success: function(result) {
				// Close Popup
				if(result.shareResult){
					<portlet:namespace/>writeTimeLineAboutSharing(customId);
				}
				<portlet:namespace/>closePopup('${dialogId}');
			},error:function(jqXHR, textStatus, errorThrown){
				alert("<liferay-ui:message key='edison-share-simulation-failed-message'/>");
			}
		});
	});
}

function <portlet:namespace/>deleteVirtualLab(){
	$("#<portlet:namespace/>isDelete").val("true");
	$("#virtualLabManageForm").submit();
}

</script>

<aui:script>
<!-- 가상실험실 요청관리 페이지 URL 생성 및 이동 -->
function <portlet:namespace/>goRequestManagementURL(groupId, groupName) {
	var dataForm = {
		"<portlet:namespace/>groupId" : groupId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=getSitePagePlidURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
		var requestPlid = msg.requestPlid;
		var isAdmin = msg.isAdmin;
		
		if(requestPlid > 0 || isAdmin) {
			AUI().use("liferay-portlet-url", function(a) {
				var portletURL = Liferay.PortletURL.createRenderURL();
				portletURL.setPlid(requestPlid); <!-- 가상실험실 생성 관리 페이지 Plid -->
				portletURL.setPortletMode("view");
				portletURL.setWindowState("pop_up");
				portletURL.setPortletId("edisonvirtuallabrequestmanagement_WAR_edisonvirtuallab2016portlet"); <!-- 가상실험실 생성 관리 포틀릿 ID -->
				portletURL.setParameter("selectOption", 1401001); <!-- 가상실험실 생성 요청 코드 -->
				portletURL.setParameter("groupName", groupName); <!-- 분야 이름 -->
				
				Liferay.Util.openWindow(
					{
						dialog: {
							width:1280,
							height:850,
							cache: false,
							modal: true,
							draggable: false,
							resizable: false,
							destroyOnClose: true,
							after: {
								render: function(event) {
									$("button.btn.close").on("click", function(e){
										<portlet:namespace/>dataSearchList(1);
									});
								}
							}
						},
						id: 'edisonvirtuallabrequestmanagement_WAR_edisonvirtuallab2016portlet',
						uri: portletURL.toString()
					}
				);
				
			});
		} else{
			alert("<liferay-ui:message key='edison-virtuallab-not-page-permission' />");
		}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}
</aui:script>

<div class="table-responsive panel edison-panel">
	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
		<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1" />
	</form>
	<div class="panel-heading clearfix detailViewSubTitle">
		<h2>
			<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
			<span class="subtitlearea">
				<liferay-ui:message key='edison-virtuallab-request-list' />
			</span>
		</h2>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1_list table table-bordered table-hover edison-table">
		<colgroup>
			<col width="10%" />
			<col width="15%" />
			<col width="15%" />
			<col width="15%" />
			<col width="15%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-site' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-virtuallab' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-affiliate' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-tutor' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-applicant' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-applicant-id' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-req-date' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-status' /></th>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>virtualLabListBody" >
		</tbody>
	</table>
	<div id="<portlet:namespace/>spaceDiv" align="center"></div>
	<div id="<portlet:namespace/>pageListDiv" class="paging text-center"></div>
</div>


<div id="<portlet:namespace/>virtualLab-process-dialog" title="<liferay-ui:message key='edison-virtuallab-request-infomation' />" class="newWindow" style="display:none; background-color:white; padding:0px;">
		<form id="virtualLabManageForm" name="virtualLabManageForm" method="post" action="<%= updateVirtualLabStatusURL %>" class="table-responsive panel edison-panel">
			<input id="<portlet:namespace/>processVirtualLabId" name="<portlet:namespace/>processVirtualLabId" type="hidden" value="0"/>
			<input id="<portlet:namespace/>requestUserId" name="<portlet:namespace/>requestUserId" type="hidden" value="0"/>
			<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${visitSite}"/>
			<input id="<portlet:namespace/>isDelete" name="<portlet:namespace/>isDelete" type="hidden" value=""/>
			
			<c:if test="${groupName ne null}">
				<input id="<portlet:namespace/>groupName" name="<portlet:namespace/>groupName" type="hidden" value="${groupName}"/>
			</c:if>
			<div class="newWheader">
				<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
					<div class="newWtitle"><liferay-ui:message key='edison-virtuallab-request-infomation' /></div>
				</div>
				<div class="newWclose" style="cursor: pointer;">
					<img id="<portlet:namespace/>virtualLab-process-dialog-close-btn" name="<portlet:namespace/>virtualLab-process-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer;">
				</div>
			</div>
	
			<div class="table1_list" style="padding: 20px 30px 25px 30px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
					<colgroup>
						<col width="20%" />
						<col width="30%" />
						<col width="20%" />
						<col width="30%" />
					</colgroup>
					<tbody>
						<tr class="puptrline">
							<th class="puptitle"><liferay-ui:message key='edison-virtuallab-request-user-id' /></th>
							<td class="puptxt" id="<portlet:namespace/>requestUserScreenName" />
							<th class="puptitle"><liferay-ui:message key='edison-virtuallab-request-user-name' /></th>
							<td id="<portlet:namespace/>requestUserFullName" class="puptxt"></td>
						</tr>
						<tr class="puptrline">
							<th class="puptitle" ><liferay-ui:message key='edison-table-list-header-tutor' /></th>
							<td id="<portlet:namespace/>requestPersonName" colspan="3" class="puptxt"></td>
						</tr>
						<tr class="puptrline">
							<th class="puptitle"><liferay-ui:message key='edison-table-list-header-virtuallab' /></th>
							<td id="<portlet:namespace/>requestVirtualLabTitle" class="puptxt"></td>
							<th class="puptitle"><liferay-ui:message key='edison-create-account-field-title-university' /></th>
							<td id="<portlet:namespace/>requestUniversityField" class="puptxt"></td>
						</tr>
						<tr class="puptrline">
							<th class="puptitle"><liferay-ui:message key='edison-table-list-header-resume' /></th>
							<td id="<portlet:namespace/>requestVirtualLabDescription" colspan="3" class="puptxt">
						</tr>
					</tbody>
				</table>
				
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left">
						<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
						<liferay-ui:message key='edison-virtuallab-confirm-info' />
					</h3>
				</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<colgroup>
						<col width="20%" />
						<col width="30%" />
						<col width="20%" />
						<col width="30%" />
					</colgroup>
					<tbody>
						<tr class="puptrline">
							<th class="puptitle"><liferay-ui:message key='edison-virtuallab-confirm-status' /></th>
							<td colspan="3">
								<select id="<portlet:namespace/>processStatus" name="<portlet:namespace/>processStatus" title="option" class="btn btn-default" >
									${selectOptionStr}
								</select>
							</td>
						</tr>
						<tr class="puptrline">
							<th class="puptitle"><liferay-ui:message key='edison-process-note' /></th>
							<td colspan="3">
								<input id="<portlet:namespace/>processDescription" class="form-control" name="<portlet:namespace/>processDescription" type="text" maxlength="100" style="width:90%;"/>
							</td>
						</tr>
						<tr class="puptrline">
							<th class="puptitle"><liferay-ui:message key='edison-table-list-header-req-date' /></th>
							<td id="<portlet:namespace/>processRequestDt" class="puptxt"></td>
							<th class="puptitle"><liferay-ui:message key='edison-table-list-header-confirm-date' /></th>
							<td id="<portlet:namespace/>processConfirmDt" class="puptxt"></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div style="text-align: right; margin:0px 25px 30px 0px;">
				<label class="checkbox-label" id="mailCheckBox" style="display: none;">
					<input type="checkbox" name="<portlet:namespace/>mailSendYn" id="<portlet:namespace/>mailSendYn" value="Y"/>
					<i class="icon-envelope">
						<liferay-ui:message key='edison-appstore-workspace-send-email' />
					</i>
				</label>
				<input id="<portlet:namespace/>register_request_button" name="register_request_button" type="submit" class="btn btn-default" value="<liferay-ui:message key='edison-virtuallab-save' />" onclick="<portlet:namespace/>submitForm();" />
				<input id="<portlet:namespace/>delete_button" name="delete_button" type="button" class="btn btn-default" value="<liferay-ui:message key='edison-button-board-delete' />" style="display: none; margin-left: 10px;" onclick="<portlet:namespace/>deleteVirtualLab();" />
			</div>
			
		</form>
	</div>
