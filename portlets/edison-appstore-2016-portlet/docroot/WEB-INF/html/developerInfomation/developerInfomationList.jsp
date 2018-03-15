<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>


<script src="<c:url value="/js/jquery-1.10.2.min.js" />"></script>
<script src="<c:url value="/js/jquery-ui.min.js" />"></script>
<script src="<c:url value="/js/main.js" />"></script>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery/jquery-ui.min.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery/jquery-ui.theme.min.css" media="screen"/>

<liferay-portlet:renderURL var="requestRequestWorkspaceURL" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myRender" value="workspaceRequest" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />	
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="developerRequestStatusURL" id="developerRequestStatus" copyCurrentRenderParameters="false" />

<style type="text/css">
.ellipsis {overflow: hidden; text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp: 1; 
	-webkit-box-orient: vertical; word-wrap:break-word; line-height: 2em; height: 2em;}
</style>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList();
	
	var display = "${display}";
	if(display == "VIEW") {
		$("#<portlet:namespace/>display").css("display", "block");
	} else {
		$(".portlet-borderless-container").css("min-height", "0");
	}
});

function <portlet:namespace/>dataSearchList() {
	var dataForm = {
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=developerRequestStatusURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var developerStatus = msg.developerStatus;
			
			var rowResult;
			$("#<portlet:namespace/>developerInfomationBody tr:not(:has(#1))").remove();
			
			if(developerStatus.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "8")
						  .css("text-align","center")
						  .text("<liferay-ui:message key='edison-there-are-no-data' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>developerInfomationBody").append($rowResult);
				$("#requestBtn").css("display", "");
			} else {
				for(var i = 0; i < developerStatus.length; i++) {
					$rowResult = $("<tr/>").attr("onclick", "<portlet:namespace/>goRequestWorkspace()")
					                       .css("cursor", "pointer");
					
					$("<td/>").text(developerStatus[i].developerSort)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerStatus[i].developerId)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerStatus[i].developerPassword)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerStatus[i].requestDate)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerStatus[i].useStart)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerStatus[i].useEnd)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(developerStatus[i].requestStatus)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").css("text-align","center")
							  .append($("<div/>").text(developerStatus[i].processNote)
									   .addClass("ellipsis"))
							  .appendTo($rowResult);
					$("#<portlet:namespace/>developerInfomationBody").append($rowResult);
				}
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>goRequestWorkspace(){
	location.href = "<%=requestRequestWorkspaceURL%>";
}

</script>

<div id="<portlet:namespace/>display" style="display:none;" class="table-responsive panel edison-panel" >
	<div class="panel-heading clearfix">
		<div class="panel-title">
			<img src="<%=request.getContextPath() %>/images/title_virtual.png" width="20" height="20" /> 
			<liferay-ui:message key='edison-appstore-developer-request-info' />
		</div>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
		<colgroup>
			<col width="12%" />
			<col width="12%" />
			<col width="12%" />
			<col width="12%" />
			<col width="12%" />
			<col width="12%" />
			<col width="12%" />
			<col width="11%" />
		</colgroup>
		<thead>
			<tr>
				<th align="center" scope="col"><liferay-ui:message key='edison-appstore-developer-request-purpose' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-userid' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-appstore-workspace-temporary-password' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-req-date' /></th>
				<th align="center" scope="col" colspan="2"><liferay-ui:message key='edison-appstore-developer-preferred-date' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-science-appstore-toolkit-change-processing-status' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-science-appstore-toolkit-change-result-of-management' /></th>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>developerInfomationBody">
		</tbody>
	</table>
</div>
<div id="requestBtn" class="buttonbox"style="width:auto; position:absolute; right:1%; display:none;">
	<input class="addIp btn btn-default" style="min-width:90px;" onclick="<portlet:namespace/>goRequestWorkspace();" value="<liferay-ui:message key='edison-appstore-workspace-request' />" type="button">
</div>