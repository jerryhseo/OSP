<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<%-- <link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/> --%>

<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="userSiteLeaveURL">
	<portlet:param name="myaction" value="siteLeave" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="userSiteJoinURL">
	<portlet:param name="myaction" value="siteJoin" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="updateSiteAssetEntryVisibleStatusURL" copyCurrentRenderParameters="false">
	<portlet:param name="myaction" value="updateSiteAssetEntryVisibleStatus" />
</liferay-portlet:actionURL>
<liferay-portlet:renderURL var="siteLeaveOwnerViewURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="siteLeaveOwnerView" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="getCheckOwnerURL" id="getCheckOwner" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="deleteAppManagerIdURL" id="deleteAppManagerId" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="deleteAuthManagerIdURL" id="deleteAuthManagerId" copyCurrentRenderParameters="false" escapeXml="false"/>



<div class="table-responsive panel edison-panel">

	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="20" height="20" /> 
			<liferay-ui:message key='edison-default-site-join-title' />
		</h3>
	</div>
	
	<aui:form name="siteJoinForm">
		<aui:input type="hidden" name="groupId"  ></aui:input>
		<aui:input type="hidden" name="removeUserIds"></aui:input>
		<aui:input type="hidden" name="addUserIds"></aui:input>
		<aui:input type="hidden" name="siteVisibleStatus"></aui:input>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
			<colgroup>
				<col width="30%" />
				<col width="20%" />
				<col width="20%" />
				<col width="*" />
			</colgroup>
			<thead>
				<tr>
					<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-group' /></th>
					<th align="center" scope="col"><liferay-ui:message key='edison-default-site-join-member' /></th>
					<th align="center" scope="col"><liferay-ui:message key='edison-default-site-join-title' /></th>
					<c:if test="${isAdmin }">
						<th align="center" scope="col"><liferay-ui:message key='edison-default-site-asset-entry-visible-status' /></th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userSiteGroupList }" var="site">
				<tr id="tr_${site.groupId }" >
					<td class="center"><a href="${site.friendlyUrl }" target="_blank">${site.groupNm }</a></td>
					<td class="center">${site.groupMemberCount }</td>
					<td class="center" >
					<c:choose>
						<c:when test="${site.memberStatus eq 'join'}">
							<a href="#" onClick="siteLeave('siteJoin', '${site.groupId}', '<%=user.getUserId()%>');">
								<img src="/edison-portal-type1-theme/images/common/join.png"/>
								&nbsp;<liferay-ui:message key="edison-default-site-join-regist"/>
							</a>
							
						</c:when>
						<c:when test="${site.memberStatus eq 'leave'}">
							
							<a href="#" onClick="siteLeave('siteLeave',  '${site.groupId}', '<%=user.getUserId()%>');">
								<img src="/edison-portal-type1-theme/images/common/leave.png"/>
								&nbsp;<liferay-ui:message key="edison-default-site-join-leave"/>
							</a>
						</c:when>
						
					</c:choose>
					</td>
					<c:if test="${isAdmin }">
						<td class="center">
							<c:choose>
								<c:when test="${site.groupAssetEntry.visible}">
									<a href="#" onClick="updateSiteAssetEntryVisibleStatus('${site.groupId }','false')" ><liferay-ui:message key="edison-default-site-asset-entry-visible-active"/></a>
								</c:when>
								<c:otherwise>
									<a href="#" onClick="updateSiteAssetEntryVisibleStatus('${site.groupId }','true')" ><liferay-ui:message key="edison-default-site-asset-entry-visible-inactive"/></a>
								</c:otherwise>
							</c:choose>
						</td>
					</c:if>
				</tr>
				</c:forEach>
				<!-- <tr>
					<td></td>
					<td></td>
					<td><img src="/edison-portal-type1-theme/images/common/leave.png"/></td>
				</tr> -->
				
			</tbody>
		</table>
	</aui:form>
</div>


<div class="h40" style="clear:both"></div>

<img id="loadingBox" src="${pageContext.request.contextPath}/images/loading.gif" width="400" style="display: none;"/>

<div id="site-leave-owner-view-dialog" title="사이트탈퇴OWNER목록" class="bigpopupbox" style="display:none;">

</div>

<aui:script>

function checkOwner(groupId, userIds){
	var bool = false;
	var data = {
			"<portlet:namespace/>groupId" : groupId,
			"<portlet:namespace/>userIds" : userIds
	}
	jQuery.ajax({
		type: "POST",
		url: "<%=getCheckOwnerURL%>",
		data: data,
		async : false,
		success: function(data) {
			var totalCnt = data.totalCnt;
			
			if(totalCnt > 0){
				popup(groupId, userIds);
			}else{
				bool = true;
			}
		},error:function(data,e){ 
		},complete: function(){
		}
	});
	return bool;
}

function siteLeave(myaction, groupId, userIds){
	
	var check = true;
	if(myaction == "siteLeave"){
		check = checkOwner(groupId, userIds);
	}
	
	if(check){
		var confirmText = "edison-default-site-join-";
		if(myaction == "siteLeave"){
			confirmText += "leave-confirm";
		}else if(myaction == "siteJoin"){
			confirmText += "regist-confirm";
			
		}
		
		if(confirm(Liferay.Language.get(confirmText))){
			
			//manager 삭제
			var managerDeleteBool = true;
			if(myaction == "siteLeave"){
				managerDeleteBool = deleteManager(groupId, userIds)
			}

			if(managerDeleteBool){
				$("#<portlet:namespace/>groupId").val(groupId);
				
				if(myaction == "siteLeave"){
					$("#<portlet:namespace/>removeUserIds").val(userIds);
					$("#<portlet:namespace/>siteJoinForm").attr("action", "<%=userSiteLeaveURL%>");
				}else if(myaction == "siteJoin"){
					$("#<portlet:namespace/>addUserIds").val(userIds);
					$("#<portlet:namespace/>siteJoinForm").attr("action", "<%=userSiteJoinURL%>");
				}
				
				$("#<portlet:namespace/>siteJoinForm").attr("method", "post");
				$("#<portlet:namespace/>siteJoinForm").submit();
			}else{
				alert(Liferay.Language.get("edison-data-delete-error"))
			}
		} 
	}
 }
 
function deleteManager(groupId, userIds){
	
// 	bStart();
	//APP MANAGER DELETE
	var appManagerBool = false;
	var authManagerBool = false;
	var data = {
			"<portlet:namespace/>groupId" : groupId,
			"<portlet:namespace/>userIds" : userIds
	}
	
	jQuery.ajax({
		type: "POST",
		url: "<%=deleteAppManagerIdURL%>",
		data: data,
		async : false,
		success: function(data) {
			var result = data.result;
			if(result == "done"){
				appManagerBool = true;
			}
		},error:function(data,e){ 
			appManagerBool = false;
		}
	}); 
	
	
	jQuery.ajax({
		type: "POST",
		url: "<%=deleteAuthManagerIdURL%>",
		data: data,
		async : false,
		success: function(data) {
			var result = data.result;
			
			if(result == "done"){
				authManagerBool = true;
			}
		},error:function(data,e){ 
			authManagerBool = false;
		}
	}); 
	
// 	bEnd();
	if(appManagerBool && authManagerBool){
		return true;
	}
	return false;
	//AUTH MANAGER DELETE
}

function updateSiteAssetEntryVisibleStatus(groupId, updateStatus){
	
	var msg = Liferay.Language.get("edison-default-site-asset-entry-visible-change-inactive-alert");
	if(updateStatus == "true"){
		msg = Liferay.Language.get("edison-default-site-asset-entry-visible-change-active-alert");
	}
	if(!confirm(msg)){
		return false;
	}
	
	$("#<portlet:namespace/>groupId").val(groupId);
	$("#<portlet:namespace/>siteVisibleStatus").val(updateStatus);
	
	$("#<portlet:namespace/>siteJoinForm").attr("action", "<%=updateSiteAssetEntryVisibleStatusURL%>");
	$("#<portlet:namespace/>siteJoinForm").attr("method", "post");
	$("#<portlet:namespace/>siteJoinForm").submit();
}

function popup(groupId, userIds){
	$("#site-leave-owner-view-dialog").dialog({
		autoOpen: false,
		width: 650,
	    height: 540,
	    modal: true,
	    resizable: false,
	    show: {effect:'fade', speed: 800}, 
        hide: {effect:'fade', speed: 800},
	    open: function(event, ui) {
	    	$(this).parent().css('overflow', 'visible');
	    	$(this).css('overflow', 'visible');
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");

	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#site-leave-owner-view-dialog").dialog("close");
	    	})
	    },
	    close: function() {
	    	$("#site-leave-owner-view-dialog").empty();
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	$("#site-leave-owner-view-dialog").dialog("open");
	
	var URL = "<%=siteLeaveOwnerViewURL%>"+"&<portlet:namespace/>groupId="+groupId+"&<portlet:namespace/>userIds="+userIds;
	
	$("#site-leave-owner-view-dialog").load(URL, function (e) {
		$("#site-leave-owner-view-dialog").dialog("open")
	});
}
</aui:script>
