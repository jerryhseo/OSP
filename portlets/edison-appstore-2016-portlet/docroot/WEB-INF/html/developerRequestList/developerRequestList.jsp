<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="developerRequestListURL" id="developerRequestList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="getSitePagePlidURL" id="getSitePagePlid" copyCurrentRenderParameters="false" />

<liferay-portlet:actionURL var="updateVirtualLabStatusURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="updateVirtualLabStatus" />
</liferay-portlet:actionURL>

<liferay-portlet:renderURL var="workspacePopUpUrl" portletName="edisonworkspace_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.POP_UP.toString() %>" >  
  <liferay-portlet:param name="workspacePopup" value="pop_up" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="workspaceViewURL" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" portletName="edisonworkspace_WAR_edisonappstore2016portlet" >
	<liferay-portlet:param name="myaction" value="workspaceView" />
	<liferay-portlet:param name="edionCopyParam" value="true" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="workspacePopup" value="${workspacePopup}" />
</liferay-portlet:renderURL>

<style>
.subtitlearea{
	margin-left: 10px;
}
.detailViewSubTitle{padding-left: 0px !important; padding-right: 0px !important;}
</style>

<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix detailViewSubTitle">
		<h2>
			<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
			<span class="subtitlearea">
				<liferay-ui:message key='edison-appstore-developer-request-list' />
			</span>
		</h2>
	</div>
	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
		<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1" />
	</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1_list table table-bordered table-hover edison-table">
		<colgroup>			
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
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-userid' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-usernm' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-email' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-req-date' /></th>
				<th align="center" scope="col" colspan="2"><liferay-ui:message key='edison-appstore-developer-use-date' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-status' /></th>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>developerRequestListBody" >
		</tbody>
	</table>
	<div id="<portlet:namespace/>spaceDiv" align="center"></div>
	<div id="<portlet:namespace/>pageListDiv" class="paging text-center"></div>
</div>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList(1);
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
		url: "<%=developerRequestListURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
			var developerRequestList = msg.developerRequestList;
			var curPage = msg.curPage;
			var selectLine = msg.selectLine;
			var developerRequestCount = msg.developerRequestCount - ((curPage -1) * selectLine);
			var pageList = msg.pageList;
			
			var rowResult;
			$("#<portlet:namespace/>developerRequestListBody tr:not(:has(#1))").remove();
			
			if(developerRequestList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "8")
						  .css("text-align","center")
						  .addClass("TC left")
						  .css("height", "40px")
						  .text("<liferay-ui:message key='edison-there-are-no-data' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>developerRequestListBody").append($rowResult);
			} else {
				for(var i = 0; i < developerRequestList.length; i++) {
					$rowResult = $("<tr/>");
 					if(i%2 == 1){
 						$rowResult.addClass("tablebgtr");
 					}
					$rowResult.attr("onClick","<portlet:namespace/>workspaceView('" + developerRequestList[i].userId + "','" + developerRequestList[i].requestStatusId + "') ")
							  .css("cursor","pointer");
					$("<td/>").text(developerRequestList[i].userScreenName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(privateTextConverter2(developerRequestList[i].userFirstName))
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(privateEmailConverter2(developerRequestList[i].userEmailAddress))
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerRequestList[i].requestDate)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerRequestList[i].useStart)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerRequestList[i].useEnd)
							  .css("text-align","center")
							  .appendTo($rowResult);
					
					if(developerRequestList[i].requestStatusId == "1202007"){
						$("<td/>").text(developerRequestList[i].requestStatus)
								  .css("text-align","center").css("color","red")
								  .appendTo($rowResult);
					}else{
						$("<td/>").text(developerRequestList[i].requestStatus)
								  .css("text-align","center")
								  .appendTo($rowResult);
					}
					
					$("#<portlet:namespace/>developerRequestListBody").append($rowResult);
				}
			}
			$("#<portlet:namespace/>pageListDiv").html(pageList);
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>workspaceView(userId, selectStatus){
	var searchForm = document.searchParamForm;
	var URL = "<%=workspaceViewURL%>"
			  + "&_edisonworkspace_WAR_edisonappstore2016portlet_userId=" + userId
			  + "&_edisonworkspace_WAR_edisonappstore2016portlet_selectStatus=" + selectStatus;
	window.location.href = URL;
}

</script>

<aui:script>
<!-- 가상실험실 요청관리 페이지 URL 생성 및 이동 -->
function <portlet:namespace/>goRequestManagementURL(requestStatusId) {
	
	var poupUrl = '<%=workspacePopUpUrl%>'+'&_edisonworkspace_WAR_edisonappstore2016portlet_selectStatus='+requestStatusId;
	AUI().use("liferay-portlet-url", function(a) {
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
				id: 'edisonvirtuallabrequestmanagement_WAR_edisonvirtuallabportlet',
				uri: poupUrl
			}
		);
		
	});
}

</aui:script>