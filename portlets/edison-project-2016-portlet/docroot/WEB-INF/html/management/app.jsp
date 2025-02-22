<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="org.kisti.edison.util.EdisonHttpUtil"%>

<liferay-portlet:resourceURL var="myAppForProjectListURL" id="myAppForProjectList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updateMyAppProjectCategoryURL" id="updateMyAppProjectCategory" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL var="scienceAppDetailURL" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="detailView" />
</liferay-portlet:renderURL>

<%
	String redirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
%>

<style type="text/css">
select.categorySelect {
	width: 170px;
	margin-bottom: 0px;
}

.managementTitle{
	font-size: 18px;
	color: #000;
	padding-top: 22px;
}
.selectProject_menu{
	float: left;
	margin-right: 10px;
	width: 160px;
	padding: 5px;
	border: solid 1px #ddd;
}
.subtitlearea{
	margin-left: 10px;
}
.detailViewSubTitle{padding-left: 0px !important; padding-right: 0px !important;}
</style>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList();
	
});


function <portlet:namespace/>updateApppProjectCategoryId(selectId, scienceAppId){
	if(confirm(Liferay.Language.get("edison-project-management-app-update-confirm"))){
		var selectedCategoryId = $("#"+selectId).val();
		var groupId = $("#<portlet:namespace/>groupId").val();
		
		jQuery.ajax({
			type: "POST",
			url: "<%=updateMyAppProjectCategoryURL%>",
			async : false,
			data : {
				"<portlet:namespace/>groupId": groupId,
				"<portlet:namespace/>selectedCategoryId": selectedCategoryId,
				"<portlet:namespace/>scienceAppId":scienceAppId
			},
			success: function(data) {
				alert(data.msg);
				$("#<portlet:namespace/>select_project").val("0");
				<portlet:namespace/>dataSearchList(1);
			},
			error: function(data) {
				alert(data.msg);
			}
		});
	
	}
}

function <portlet:namespace/>dataSearchListAll(){
	$("#<portlet:namespace/>textfield").val("");
	<portlet:namespace/>dataSearchList(1);
}

function <portlet:namespace/>dataSearchList(p_currentPage) {
	//라인검색 값
	var searchLine = $("#<portlet:namespace/>select_line").val();
	var searchText = $("#<portlet:namespace/>textfield").val();
	var projectCategoryId = $("#<portlet:namespace/>select_project").val();
	
	var dataForm = {
		"<portlet:namespace/>searchText":searchText,
		"<portlet:namespace/>searchLine":searchLine,
		"<portlet:namespace/>currentPage" : p_currentPage,
		"<portlet:namespace/>projectCategoryId" : projectCategoryId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=myAppForProjectListURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var myAppList = msg.myAppList;
			var rowResult;
			
			$("#<portlet:namespace/>myAppListBody tr:not(:has(#1))").remove();
			$("#<portlet:namespace/>paging").empty();
			
			
			if(typeof myAppList == "undefined" || myAppList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "6")
						  .css("text-align","center")
						  .text("<liferay-ui:message key='edison-there-are-no-data' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>myAppListBody").append($rowResult);
			} else {
				for(var i = 0; i < myAppList.length; i++) {
					$rowResult = $("<tr/>").css("border-bottom", "1px solid #e0e0e0").addClass("appItem" + i).attr("id", "appItem" + i);
					
					if(i%2 == 1){
 						$rowResult.addClass("tablebgtr");
 					}
					$("<td></td>").addClass("center").html(msg.seq-i).appendTo($rowResult);
					
					
					$("<td/>").append($("<a/>").attr("onClick", "event.cancelBubble=true; <portlet:namespace/>moveScienceAppDetail('" + myAppList[i].groupId + "','" + myAppList[i].scienceAppId + "');")
									  .text(myAppList[i].title)
									  .css("cursor", "pointer")
							 ).appendTo($rowResult)
							  .css("white-space","nowrap")
							  .css("overflow","hidden")
							  .css("text-overflow","ellipsis");
					$("<td/>").addClass("center").text("Ver " + myAppList[i].version).appendTo($rowResult);
					$("<td/>").addClass("center").text(myAppList[i].affiliation).appendTo($rowResult);
					$("<td/>").addClass("center").text(myAppList[i].userFirstName).appendTo($rowResult);
					
					$("<td/>").addClass("center").append($("<select/>").attr("id","categorySelect"+myAppList[i].scienceAppId).addClass("categorySelect btn btn-default").append($("<option/>").attr("value","0").attr("없음")).append(myAppList[i].categorySelectOption).attr("onChange", "<portlet:namespace/>updateApppProjectCategoryId('categorySelect"+myAppList[i].scienceAppId+"', '"+myAppList[i].scienceAppId+"')")).appendTo($rowResult);
					
 					$("#<portlet:namespace/>myAppListBody").append($rowResult);
				}

				$("#<portlet:namespace/>paging").html(msg.paging);
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}


</script>
<aui:script>
function <portlet:namespace/>moveScienceAppDetail(groupId, scienceAppId) {
	AUI().use("liferay-portlet-url", function(a) {
		
		var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
    	var params = "&" + thisPortletNamespace + "solverId=" + scienceAppId;
  	    params += "&" + thisPortletNamespace + "groupId=" + groupId;
        params += "&" + thisPortletNamespace + "redirectName=" + "<liferay-ui:message key='edison-project-management-app'/>";
        params += "&" + thisPortletNamespace + "redirectURL=<%=redirectURL%>";
    location.href = "<%=scienceAppDetailURL%>" + params;
	});
}
</aui:script>

<div id="<portlet:namespace/>display" class="table-responsive panel edison-panel" >
	<input type="hidden" id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" value="${groupId }">
	<input type="hidden" id="<portlet:namespace/>solverId" name="<portlet:namespace/>solverId" value="0">
	
	<div class="panel-heading clearfix detailViewSubTitle">
		<h2 class="pull-left" style="width: 40%;">
			<img src="${contextPath}/images/sub_tit_bl.png"/> 
			<span class="subtitlearea">
				<liferay-ui:message key='edison-project-management-app' />
			</span>
		</h2>
		
		<div class="btn-group pull-right" style="width: 60%; top: 20px;">
            <div class="input-group">
              <select style="width: 29%" id="<portlet:namespace/>select_project" name="<portlet:namespace/>select_line" onchange="<portlet:namespace/>dataSearchList(1)" class="form-control">
                  <option value="0"><liferay-ui:message key="edison-content-project-affiliation-yn"/></option>
                  ${categorySelectOption }
              </select>
              <select style="width: 20%" id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" onchange="<portlet:namespace/>dataSearchList(1)" class="form-control">
                  <option value="5">5<liferay-ui:message key="edison-search-views"/></option>
                  <option value="10">10<liferay-ui:message key="edison-search-views"/></option>
                  <option value="15">15<liferay-ui:message key="edison-search-views"/></option>
                  <option value="20">20<liferay-ui:message key="edison-search-views"/></option>
              </select>
              <input style="width: 50%; float: right;"  name="<portlet:namespace/>textfield" class="form-control" type="text" id="<portlet:namespace/>textfield"
                placeholder="<liferay-ui:message key="edison-table-list-header-title"/> or <liferay-ui:message key="edison-table-list-header-name"/>"
                onKeydown="if(event.keyCode ==13)<portlet:namespace/>dataSearchList(1);"
                style="" />
              <div class="input-group-btn">
                <button class="btn btn-default" type="button" name="fullsize" id="fullsize"
                  onclick="<portlet:namespace/>dataSearchList(1);">
                  <i class="icon-search"></i>
                </button>
                <button class="btn btn-default" name="fullsize" id="fullsize" onclick="<portlet:namespace/>dataSearchListAll(1);">
                	Clear
                </button>
              </div>
            </div>
		</div>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed;" class="table table-bordered table-hover edison-table">
		<colgroup>
			<col width="100" />
			<col width="*" />
			<col width="100" />
			<col width="200" />
			<col width="150" />
			<col width="200" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><liferay-ui:message key="edison-table-list-header-index"/></th>
				<th scope="col"><liferay-ui:message key="edison-table-list-header-title"/></th>
				<th scope="col"><liferay-ui:message key="edison-table-list-header-version"/></th>
				<th scope="col"><liferay-ui:message key="edison-create-account-field-title-university"/></th>
				<th scope="col"><liferay-ui:message key="edison-table-list-header-name"/></th>
				<th scope="col"><liferay-ui:message key="edison-content-project-affiliation-yn"/></th><!-- 소속프로젝트 -->
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>myAppListBody">
		</tbody>
	</table>
	
	<div class="paging">
		<div id="<portlet:namespace/>paging" style="width:100%;text-align: center;"></div>
	</div>
</div>