<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.Locale"%>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>

<liferay-portlet:resourceURL var="professorListURL" id="professorList" copyCurrentRenderParameters="false" />

<liferay-portlet:resourceURL var="syncProfessorListURL" id="syncProfessorList" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL var="professorManagementURL" portletMode="view" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="professorManagement" />
</liferay-portlet:renderURL>
<style type="text/css">
.onHover:hover {
		background:#e0e0e0;
	}
</style>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<input type="button" value="Synce" onclick="<portlet:namespace/>syncProfessorList();" class="btn btn-default"/>

<script>
AUI().ready(function() {
	<portlet:namespace/>dataSearchList(1);
});


function <portlet:namespace/>syncProfessorList() {
	bStart();
	setTimeout(function(){
		jQuery.ajax({
			type: "POST",
			url: "<%=syncProfessorListURL%>",
			async : false,
			success: function(msg) {
				<portlet:namespace/>dataSearchList(1);
			},error:function(msg,e){
				bEnd();
				alert("syncProfessorList : "+ e);
			}
		});
		bEnd();
	}, 2000);
}

function <portlet:namespace/>dataSearchList(pageNumber) {
	if(pageNumber == 0) {
		$("#<portlet:namespace/>cur_page").val(1);
		$("#<portlet:namespace/>searchField").val("");
	} else {
		$("#<portlet:namespace/>cur_page").val(pageNumber);
	}
	var searchForm = $("form[name=<portlet:namespace/>searchForm]").serialize();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=professorListURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
			var professorList = msg.professorList;
			var curPage = msg.curPage;
			var selectLine = msg.selectLine;
			var professorCount = msg.professorCount - ((curPage -1) * selectLine);
			var pageList = msg.pageList;
			var redirectURL = msg.redirectURL;
			
			var rowResult;
			$("#<portlet:namespace/>professorListBody tr:not(:has(#1))").remove();
			
			if(professorList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").addClass("center)")
						  .attr("colspan", "6")
						  .css("text-align","center")
						  .text("<liferay-ui:message key='edison-search-no-result' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>professorListBody").append($rowResult);
			} else {
				
				for(var i = 0; i < professorList.length; i++) {
					$rowResult = $("<tr/>").attr("onClick","<portlet:namespace/>moveProfessor('" + professorList[i].professorSeq + "','" + professorList[i].userId + "','" + redirectURL + "')")
										   .addClass("onHover")  					   
										   .css("cursor","pointer");
					$("<td/>").addClass("center")
							  .text(professorCount--)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(professorList[i].screenName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(professorList[i].firstName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(professorList[i].emailAddress)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(professorList[i].universityFieldNm)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").addClass("center")
							  .text(professorList[i].major)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("#<portlet:namespace/>professorListBody").append($rowResult);
				}
			}
			
			$("#<portlet:namespace/>pageListDiv").html(pageList);
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>moveProfessor(professorSeq, userId, redirectURL) {
	var URL = "<%=professorManagementURL%>&<portlet:namespace/>professorSeq="+ professorSeq +"&<portlet:namespace/>userId=" + userId +"&<portlet:namespace/>redirectURL=" + redirectURL;
	window.location.href = URL;
}
</script>

<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			<liferay-ui:message key='edison-professor-history-management-list' />
		</h3>
		
		<div class="tabletopbox">
			<form method="post" name="<portlet:namespace/>searchForm" id="<portlet:namespace/>searchForm" style="margin: 0px;" onsubmit="return false;">
				<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1"/>
				
				<div class="input-group">
					<input id="<portlet:namespace/>searchField" name="<portlet:namespace/>searchField" class="form-control" type="text" maxlength="15" placeholder="<liferay-ui:message key='edison-id' />" onKeydown="if(event.keyCode ==13){<portlet:namespace/>dataSearchList('1');return false;}" />
					<div class="input-group-btn">
						<button id="search_button" name="<portlet:namespace />search_button" class="btn btn-default" type="button" onclick="<portlet:namespace/>dataSearchList(1)"><i class="icon-search"></i></button>
						<button id="total_search_button" name="<portlet:namespace />total_search_button" type="button" class="btn btn-default" onclick="<portlet:namespace/>dataSearchList(0)" >
							Clear
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<table class="table table-bordered table-hover edison-table" width="100%" border="0" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="10%" />
			<col width="10%" />
			<col width="20%" />
			<col width="20%" />
			<col width="20%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-index' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-id' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-name' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-email' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-create-account-field-title-university' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-create-account-field-title-major' /></th>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>professorListBody">
		</tbody>
	</table>
	<div id="<portlet:namespace/>spaceDiv" align="center"></div>
	<div id="<portlet:namespace/>pageListDiv" class=text-center></div>	<!-- pagination -->
</div>
</body>
