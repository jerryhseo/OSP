<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>

<liferay-portlet:resourceURL var="favoriteAppListURL" id="favoriteAppList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteFavoriteAppURL" id="deleteFavoriteApp" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="detailView" />
</liferay-portlet:renderURL>

<style type="text/css">
</style>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList();
});

function <portlet:namespace/>dataSearchList() {
	var dataForm = {
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=favoriteAppListURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var favoriteAppList = msg.favoriteAppList;
			var favoriteAppManualList = msg.favoriteAppManualList;
			
			var rowResult;
			$("#<portlet:namespace/>favoriteAppBody tr:not(:has(#1))").remove();
			
			if(typeof favoriteAppList == "undefined" || favoriteAppList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").addClass("center")
						  .attr("colspan", "4")
						  .css("text-align","center")
						  .text("<liferay-ui:message key='edison-there-are-no-data' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>favoriteAppBody").append($rowResult);
			} else {
				for(var i = 0; i < favoriteAppList.length; i++) {
					$rowResult = $("<tr/>").css("border-bottom", "1px solid rgb(224, 224, 224)");
					$("<td/>").addClass("center")
							  .append($("<img/>").attr("src", "${pageContext.request.contextPath}/images/scienceappstoreview/favoriteiconon.png")
							  .attr("onClick", "event.cancelBubble=true; <portlet:namespace/>deleteFavoriteApp('" + favoriteAppList[i].scienceAppId + "','"+ favoriteAppList[i].groupId +"');")
							  .text(favoriteAppList[i].title)
							  .css("cursor", "pointer")
					 		 ).appendTo($rowResult);
					$("<td/>").append($("<a/>").attr("onClick", "event.cancelBubble=true; <portlet:namespace/>moveSolverDetail('" + favoriteAppList[i].scienceAppId + "','" + favoriteAppList[i].groupId + "');")
							  .text(favoriteAppList[i].name + '(' + favoriteAppList[i].title + ')')
							  .css("cursor", "pointer")
					 		 ).css("white-space","nowrap")
							  .css("overflow","hidden")
							  .css("text-overflow","ellipsis")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .css("text-align","center").text("Ver " + favoriteAppList[i].version)
							  .appendTo($rowResult);
					
					if(favoriteAppManualList[i].fileEntryId != undefined) {
						$("<td/>").addClass("center")
								  .css("text-align","center")
								  .append($("<a/>")
								  .attr("onClick", "event.cancelBubble=true; <portlet:namespace/>fileDownload('" + favoriteAppManualList[i].fileEntryId + "');")
								  .text("<liferay-ui:message key='edison-table-list-header-manual' />")
								  .css("cursor", "pointer")
								 ).appendTo($rowResult);
					} else {
						$("<td/>").addClass("center").css("text-align","center").html("<liferay-ui:message key='edison-table-list-header-manual' />").appendTo($rowResult);
					}
					$("#<portlet:namespace/>favoriteAppBody").append($rowResult);
				}
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>deleteFavoriteApp(solverId,groupId) {
	if(confirm("<liferay-ui:message key='edison-appstore-favorite-app-delete-alert' />")){	
		var dataForm = {
			"<portlet:namespace/>solverId" : solverId,
			"<portlet:namespace/>groupId" : groupId
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=deleteFavoriteAppURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var result = msg.result;
				if(result == '200') {
					alert("<liferay-ui:message key='edison-data-delete-success' />");
				}
				<portlet:namespace/>dataSearchList();
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});

	}
}

function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

</script>

<aui:script>

function <portlet:namespace/>moveSolverDetail(scienceAppId, groupId) {
	AUI().use("liferay-portlet-url", function(a) {
		var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
		var params = "&" + thisPortletNamespace + "solverId=" + scienceAppId;
			params += "&" + thisPortletNamespace + "groupId=" + groupId;
			location.href = "<%=scienceAppDetailUrl%>" + params;
	});
}

function <portlet:namespace/>moveSimulation(groupId, solverId) {
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPlid("${simulationPlid}"); /*가상실험실 생성 관리 페이지 Plid*/
		portletURL.setPortletId("edisonbestsimulation_WAR_edisonsimulation2016portlet"); /*시뮬레이션 포틀릿 ID*/
		portletURL.setParameter("directGroupId", groupId); /*solver groupId*/
		portletURL.setParameter("directScienceAppId", solverId); /*선택된 solverId*/
		window.location.href = portletURL.toString();
	});
}
</aui:script>


<div class="table-responsive panel edison-panel" style="float: left;">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="<%=request.getContextPath() %>/images/title_virtual.png" width="20" height="20" /> 
			<liferay-ui:message key='edison-appstore-favorite-app' />
		</h3>
	</div>
	
	<table class="table table-bordered table-hover edison-table" width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed;">
		<colgroup>
			<col width="40" />
			<col width="*" />
<!-- 			<col width="100" /> -->
<!-- 			<col width="200" /> -->
			<col width="100" />
			<col width="80" />
<!-- 			<col width="80" /> -->
		</colgroup>
		<thead>
			<tr>
				<th align="center" scope="col"><liferay-ui:message key='  ' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-appstore-solver-name' />(<liferay-ui:message key='edison-table-list-header-app-title' />)</th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-version' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-manual' /></th>
<%-- 				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-run' /></th> --%>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>favoriteAppBody">
		</tbody>
	</table>
</div>