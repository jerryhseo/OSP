<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="virtualLabRequestListURL" id="virtualLabRequestList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="virtualLabInfomationURL" id="virtualLabInfomation" copyCurrentRenderParameters="false" />

<liferay-portlet:actionURL var="updateVirtualLabStatusURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="updateVirtualLabStatus" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL var="saveClickTabURL" portletMode="view" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="saveClickTab"/>
</liferay-portlet:actionURL>
<style>
label.checkbox-label{
	display:inline-block;
	margin-top: 7px;
	margin-right:5px;
}

label.checkbox-label input[type=checkbox]{
	vertical-align: middle;
	bottom: 2px;
	margin-right: 2px;
}

.tabletopright1{position: absolute; right: 155px; top:10px; width: 120px; height:17px; border-radius:3px 3px 3px 3px; -webkit-border-radius:3px 3px 3px 3px;  background-color:#fff; padding:5px; border:solid 1px #ddd;}

.puptxt {
    font-size: 15px;
}

.aui select,.aui textarea,.aui input[type="text"]{
	margin: 3px 0px;
}

.onHover:hover {
		background:#e0e0e0;
	}
</style>

<body>
	<%
		//Tab Setting
		String tabNames = (String)request.getAttribute("tabNames");
		String tabsValues = (String)request.getAttribute("tabsValues");
		String portletNameSpace = "_"+portletDisplay.getId()+"_"+"tagScript";
		String visitSite = CustomUtil.strNull(request.getAttribute("visitSite"));
		String searchField = CustomUtil.strNull(request.getAttribute("searchField"));
	%>
	
		<c:if test="${not empty tabsValues}">
			<div class="contabmenu"> 
				<edison-ui:tabs names="<%=tabNames%>" tabsValues="<%=tabsValues%>" value="<%=visitSite%>" refresh="<%=false%>" onClick="<%=portletNameSpace%>" minwidth="195"/>
			</div>
		</c:if>
	
		
		<div class="table-responsive panel edison-panel">
			<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
				<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1"/>
				<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${visitSite}"/>
			</form>
				<div class="panel-heading clearfix">
					<h1 class="pull-left">
						<c:if test="${groupName ne null}">
							<img src="${pageContext.request.contextPath}/images/title_virtual.png" />
							<liferay-ui:message key='edison-virtuallab-request-list' />(${groupName})
						</c:if>
						<c:if test="${groupName eq null }">
							<img src="${pageContext.request.contextPath}/images/title_virtual.png" />
							<liferay-ui:message key='edison-virtuallab-request-list' />
						</c:if>
					</h1>
				
					<div class="input-group" style="top: 22px;">
						<input type="text" class="form-control" id="<portlet:namespace/>search_parameter" name="<portlet:namespace/>search_parameter" maxlength="15" placeholder="<liferay-ui:message key='edison-virtuallab-placeholder' />" onkeypress="<portlet:namespace/>onKeyDown();" style="width: 50%; float: right; margin-top: 0px; margin-left: 1%;"/>
						
						<select id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" onchange="<portlet:namespace/>dataSearchList(0)" class="form-control" style="width: 15%; float: right; margin-top: 0px;">
							<option value="10">10<liferay-ui:message key='edison-search-views' /></option>
							<option value="20">20<liferay-ui:message key='edison-search-views' /></option>
							<option value="30">30<liferay-ui:message key='edison-search-views' /></option>
							<option value="40">40<liferay-ui:message key='edison-search-views' /></option>
						</select>
						
						<select id="<portlet:namespace/>select_virtualLab_status" name="<portlet:namespace/>select_virtualLab_status" title="option" onchange="<portlet:namespace/>dataSearchList(0)" class="form-control" style="width: 20%; float: right; margin-top: 0px;">
							<option value="0">All</option>
							${selectOptionStr}
						</select>
						
						
						<div class="input-group-btn">
							<button class="btn btn-default" type="button" onClick="<portlet:namespace/>dataSearchList()">
								<i class="icon-search"></i>
							</button>
							<button class="btn btn-default" onClick="<portlet:namespace/>dataSearchList(0)">
								Clear
							</button>
						</div>
					</div>
				</div>
				
			
			<div class="table1_list">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
					<colgroup>
						<col width="5%" />
						<col width="20%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
					</colgroup>
					<thead>
						<tr>
							<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-index' /></th>
							<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-virtuallab' /></th>
							<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-affiliate' /></th>
							<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-professor' /></th>
							<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-applicant' /></th>
							<th align="center" scope="col"><liferay-ui:message key='edison-science-appstore-toolkit-change-request-date' /></th>
							<th align="center" scope="col"><liferay-ui:message key='edison-simulation-execute-job-create-list-state' /></th>
						</tr>
					</thead>
					<tbody id="<portlet:namespace/>virtualLabListBody">
					</tbody>
				</table>
			</div>
			<div id="<portlet:namespace/>spaceDiv" align="center"></div>
			<div id="<portlet:namespace/>pageListDiv" class="paging text-center"></div>
		</div>
</body>

	<div id="<portlet:namespace/>jobparameter-dialog" title="<liferay-ui:message key="edison-simulation-execute-job-detail"/>" style="display:none; background-color:white; padding:0px;" class="newWindow">
		<div class="newWheader" id="<portlet:namespace/>jobparameter-dialog-title" style="cursor: move;">
				<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
					<div class="newWtitle"><liferay-ui:message key="edison-simulation-execute-job-detail"/></div>
				</div>
				<div class="newWclose" style="cursor: pointer;">
					<img id="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
				</div>
		</div>
		<div id="<portlet:namespace/>jobparameter-dialog-content" style="padding: 30px;" class="newWcont01">
		</div>
	</div>

	<div id="<portlet:namespace/>jobparameter-dialog" title="<liferay-ui:message key="edison-simulation-execute-job-detail"/>" style="display:none; background-color:white; padding:0px;" class="newWindow">
		<div class="newWheader" id="<portlet:namespace/>jobparameter-dialog-title" style="cursor: move;">
				<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
					<div class="newWtitle"><liferay-ui:message key="edison-simulation-execute-job-detail"/></div>
				</div>
				<div class="newWclose" style="cursor: pointer;">
					<img id="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
				</div>
		</div>
		<div id="<portlet:namespace/>jobparameter-dialog-content" style="padding: 30px;" class="newWcont01">
		</div>
	</div>


	<div id="<portlet:namespace/>virtualLab-process-dialog" title="<liferay-ui:message key='edison-virtuallab-request-infomation' />" class="newWindow" style="display:none; background-color:white; padding:0px;">
		<form id="virtualLabManageForm" name="virtualLabManageForm" method="post" action="<%= updateVirtualLabStatusURL %>" class="table-responsive panel edison-panel">
			<input id="<portlet:namespace/>processVirtualLabId" name="<portlet:namespace/>processVirtualLabId" type="hidden" value="0"/>
			<input id="<portlet:namespace/>requestUserId" name="<portlet:namespace/>requestUserId" type="hidden" value="0"/>
			<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${visitSite}"/>
			
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
				<input id="register_request_button" name="register_request_button" type="submit" class="btn btn-default" value="<liferay-ui:message key='edison-virtuallab-save' />" />
			</div>
			
		</form>
	</div>
	
	
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
			$("#<portlet:namespace/>search_parameter").val("");
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
					$("<td/>").attr("colspan", "7")
							  .css("text-align","center")
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
								  .css("cursor","pointer")
								  .addClass("onHover");
						$("<td/>").text(virtualLabCount--)
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
						$("<td/>").html(privateTextConverter2(virtualLabRequestList[i].userFirstName) + "(" +  virtualLabRequestList[i].userScreenName + ")")
								  .css("text-align","center")
								  .appendTo($rowResult);
						$("<td/>").text(virtualLabRequestList[i].virtualLabRequestDt)
								  .css("text-align","center")
								  .appendTo($rowResult);
						if(virtualLabRequestList[i].virtualLabStatus == "1401003") {	//반려일 경우 빨간 글씨
							$("<td/>").text(virtualLabRequestList[i].virtualLabStatusNm)
									  .css("text-align","center")
									  .css("color", "red")
									  .appendTo($rowResult);
						}else if(virtualLabRequestList[i].virtualLabStatus == "1401001"){	// 생성 요청중 파란 글씨
							$("<td/>").text(virtualLabRequestList[i].virtualLabStatusNm)
									  .css("text-align","center")
									  .css("color", "blue")
									  .appendTo($rowResult);
						} else {
							$("<td/>").text(virtualLabRequestList[i].virtualLabStatusNm)
									  .css("text-align","center")
									  .appendTo($rowResult);
						}
						
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
				}
				
				$("#<portlet:namespace/>virtualLab-process-dialog").dialog("open");
			},error:function(msg,e){
				alert(e);
				return false;
			}
		});
	}
	
	function <portlet:namespace/>onKeyDown() {
		if(event.keyCode == 13 && $("#<portlet:namespace/>search_parameter").val() != ""){
			<portlet:namespace/>dataSearchList();
		}
	}
	
	//liferay-ui 탭 이벤트 return Script
	function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
		window.location.href = "<%= saveClickTabURL.toString() %>"+"&<portlet:namespace/>groupId=" + value;
	}
</script>
