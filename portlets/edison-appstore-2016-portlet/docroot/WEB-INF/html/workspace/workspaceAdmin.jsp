<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<liferay-portlet:resourceURL var="workspaceListURL" id="workspaceList" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="workspaceViewURL" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" >
	<liferay-portlet:param name="myaction" value="workspaceView" />
	<liferay-portlet:param name="edionCopyParam" value="true" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="workspacePopup" value="${workspacePopup}" />
</liferay-portlet:renderURL>

<script type="text/javascript">
/************** ready   ***************/
$(document).ready(function() {
	$("#<portlet:namespace/>selectStatus").val("${selectStatus}");
	<portlet:namespace/>dataSearchList("${params.curPage}");
});
/************** ready end   ***************/

function workspaceView(userId){
	var searchForm = document.searchParamForm;
	var URL = "<%=workspaceViewURL%>&<portlet:namespace/>userId="+userId;
	
	var curPage = searchForm.<portlet:namespace/>curPage.value;
	var searchField = searchForm.<portlet:namespace/>searchField.value;
	var selectLine = searchForm.<portlet:namespace/>select_line.value;
	var selectStatus = searchForm.<portlet:namespace/>selectStatus.value;
	
	URL +="&<portlet:namespace/>curPage="+curPage;
	URL +="&<portlet:namespace/>searchField="+searchField;
	URL +="&<portlet:namespace/>selectLine="+selectLine;
	URL +="&<portlet:namespace/>selectStatus="+selectStatus;
	
	window.location.href = URL;
}

//목록검색
function <portlet:namespace/>dataSearchList(curPage){
	var selectValue = "${selectStatus}";
	if(curPage == 0) {
		curPage = 1
		$("#<portlet:namespace/>curPage").val("1");
		$("#<portlet:namespace/>searchField").val("");
		if(selectValue == ""){
			$("#<portlet:namespace/>selectStatus").val("0");
		}
		$("#<portlet:namespace/>select_line").find('option:first').attr('selected', 'selected');
	} else {
		$("#<portlet:namespace/>curPage").val(curPage);
	}

	var paramData = $("form[name=searchParamForm]").serialize();
	jQuery.ajax({
		type: "POST",
		url: "<%=workspaceListURL%>",
		async : false,
		data  : paramData,
		dataType: 'json',
		success: function(data) {	    			
			var dataMap = data;
			var dataCount = dataMap.totalCnt - ((curPage -1) * dataMap.select_line);
			
			$("#<portlet:namespace/>workspaceList").empty();		
			if(dataMap.dataList.length==0){
				
				$("#<portlet:namespace/>workspaceList").append('<tr><td class="center" colspan=7 style="text-align:center"><liferay-ui:message key="edison-there-are-no-data" /></td></tr>');
				
			}else{
				
				for(var i = 0 ; i < dataMap.dataList.length; i++ ){									
					$trNode = $("<tr/>").attr("onclick", "workspaceView('"+dataMap.dataList[i].userId+"')").css("cursor", "pointer");
 					if(i%2 == 1){
 						$trNode.addClass("tablebgtr");
 					}
					$("<td/>").addClass("center").text(dataCount--).appendTo($trNode);					
					$("<td/>").addClass("center").append($("<a/>").attr("href","#").text(dataMap.dataList[i].screenName)).appendTo($trNode);
					$("<td/>").addClass("center").text(dataMap.dataList[i].firstName).appendTo($trNode);
					$("<td/>").addClass("center").text(dataMap.dataList[i].emailAddress).appendTo($trNode);
					$("<td/>").addClass("center").text(dataMap.dataList[i].requestStatusNm).appendTo($trNode);
					$("<td/>").addClass("center").text(dataMap.dataList[i].requestDate).appendTo($trNode);
					$("<td/>").addClass("center").text(dataMap.dataList[i].useStart+" ~ "+dataMap.dataList[i].useEnd).appendTo($trNode);
					$("#<portlet:namespace/>workspaceList").append($trNode);
				}
			}
		
			//페이징 초기화pageListDiv
			document.getElementById("<portlet:namespace/>page_navi").innerHTML = dataMap.pageList;
			
		},error:function(data,e){   
			alert(e);				  
			return false;
		}
	});
}
</script>

<style>
	#<portlet:namespace/>searchField{
		width: 220px;
	}
</style>

<div class="table-responsive panel edison-panel">
	
	<div class="panel-heading clearfix">
		<form method="post" name="searchParamForm" style="margin: 0px;" onsubmit="return false;">
			<input id="<portlet:namespace/>curPage" name="<portlet:namespace/>curPage" type="hidden" value="1"/>
			
			<!-- 페이지 타이틀 & 네비게이션 -->
			<h3 class="panel-title pull-left" >
				<img src="${contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				<liferay-ui:message key='edison-appstore-workspace-request-list' />
			</h3>
			
			<div class="input-group">
				<select class="form-control" id="<portlet:namespace/>selectStatus" name="<portlet:namespace/>selectStatus" onchange="<portlet:namespace/>dataSearchList(1)" style="width: 25%">
					<option value="0">ALL</option>
					${statusOptionStr}
				</select>
				
				<select class="form-control" id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" onchange="<portlet:namespace/>dataSearchList(1)" style="width: 22%">
					<option value="10" <c:if test="${params.selectLine == '10' }"> selected="selected" </c:if>>10<liferay-ui:message key='edison-search-views' /></option>
					<option value="20" <c:if test="${params.selectLine == '20' }"> selected="selected" </c:if>>20<liferay-ui:message key='edison-search-views' /></option>
					<option value="30" <c:if test="${params.selectLine == '30' }"> selected="selected" </c:if>>30<liferay-ui:message key='edison-search-views' /></option>
					<option value="40" <c:if test="${params.selectLine == '40' }"> selected="selected" </c:if>>40<liferay-ui:message key='edison-search-views' /></option>
				</select>
				
				<input class="form-control" id="<portlet:namespace/>searchField" name="<portlet:namespace/>searchField" type="text" maxlength="15" placeholder="<liferay-ui:message key='edison-appstore-workspace-placeholder' />" onKeydown="if(event.keyCode ==13){<portlet:namespace/>dataSearchList('1');return false;}" value="${params.searchField}" style="width: 52%; margin-left: 1%;"/>
				
				<div class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="<portlet:namespace/>dataSearchList('1');"><i class="icon-search"></i></button>
					<%-- <input class="btn btn-default" id="total_search_button" name="<portlet:namespace />total_search_button" type="button" value="Clear" onclick="<portlet:namespace/>dataSearchList(0)" /> --%>
					<button class="btn btn-default" id="total_search_button" name="<portlet:namespace />total_search_button" onclick="<portlet:namespace/>dataSearchList(0);">
						Clear
					</button>
				</div>
			</div>
			
		</form>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
		<thead>
		    <tr>
			    <th width="5%" ><liferay-ui:message key='edison-table-list-header-index' /></th>
			    <th width="12%"><liferay-ui:message key='edison-table-list-header-userid' /></th>
			    <th width="12%"><liferay-ui:message key='edison-table-list-header-usernm' /></th>
			    <th width="15%"><liferay-ui:message key='edison-table-list-header-email' /></th>
			    <th width="12%"><liferay-ui:message key='edison-table-list-header-status' /></th>
			    <th width="12%"><liferay-ui:message key='edison-table-list-header-req-date' /></th>
			    <th width="24%"><liferay-ui:message key='edison-appstore-developer-use-date' /></th>
		    </tr>
	    </thead>
	    <tbody id="<portlet:namespace/>workspaceList">
	    </tbody>
	</table>
	
	 <!-- 페이지 네비게이션 ---->
	<div class="text-center" id="<portlet:namespace/>page_navi">
	</div>
</div>

