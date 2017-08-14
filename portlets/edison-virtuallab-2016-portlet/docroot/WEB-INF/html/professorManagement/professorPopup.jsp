<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.Locale"%>

<liferay-portlet:resourceURL var="professorListURL" id="professorSearchList" copyCurrentRenderParameters="false" />

<style type="text/css">
.onHover:hover {
		background:#e0e0e0;
	}
</style>
<script>
AUI().ready(function() {
	<portlet:namespace/>dataSearchList(1);
});

var virtualLabProfessor = "${param.virtualLabProfessor}";
var virtualLabprofessorSeq = "${param.professorSeq}";

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
			
			var rowResult;
			$("#<portlet:namespace/>professorListBody tr:not(:has(#1))").remove();
			
			if(professorList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "6")
						  .css("text-align","center")
						  .text("<liferay-ui:message key='edison-search-no-result' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>professorListBody").append($rowResult);
			} else {
				
				for(var i = 0; i < professorList.length; i++) {
					$rowResult = $("<tr/>").attr("onClick","<portlet:namespace/>selectProfessor('" + professorList[i].professorSeq + "','" + professorList[i].firstName + "')")
										   .addClass("onHover")  					   
										   .css("cursor","pointer");
					$("<td/>").text(professorCount--)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(professorList[i].screenName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(professorList[i].firstName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(professorList[i].emailAddress)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(professorList[i].universityFieldNm)
					  .css("text-align","center")
					  .appendTo($rowResult);
					$("<td/>").text(professorList[i].major)
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

function <portlet:namespace/>selectProfessor(professorSeq, professorName){
	var $openerObj = $(window.opener.document);
	
	$openerObj.find("input[name*="+virtualLabProfessor+"]").val(professorName);
	$openerObj.find("input[name*="+virtualLabprofessorSeq+"]").val(professorSeq);
	
	window.self.close();
}

</script>

<div class="tabletopbox clear">
	<form method="post" name="<portlet:namespace/>searchForm" id="<portlet:namespace/>searchForm" style="margin: 0px;" onsubmit="return false">
		<div class="search">
			<div class="searchbox">
				<input name="<portlet:namespace/>searchField" type="text" id="<portlet:namespace/>searchField" placeholder="<liferay-ui:message key="edison-simulation-project-member-placeholder"/>" size="40" value="${searchText}" 
					onKeydown="if(event.keyCode ==13)<portlet:namespace/>dataSearchList('1');" />
				<input type="button" name="fullsize" id="fullsize" value="" class="btnsearch" onclick="<portlet:namespace/>dataSearchList('1');">
			</div>
			
			<input type="button" name="fullsize" id="fullsize" value="<liferay-ui:message key="edison-button-all-search"/>" class="button01" onclick="<portlet:namespace/>dataSearchList('0');">
		</div>
		
		<!--우편 셀렉트-->
		<div class="tabletopright">
			<select id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" title="옵션" onchange="<portlet:namespace/>dataSearchList('1');" class="selectview" style="line-height: 15px;">
				<option value="5" <c:if test="${listSize eq 5}">selected="selected"</c:if>>5<liferay-ui:message key="edison-search-views"/></option>
				<option value="10" <c:if test="${listSize eq 10}">selected="selected"</c:if>>10<liferay-ui:message key="edison-search-views"/></option>
				<option value="15" <c:if test="${listSize eq 15}">selected="selected"</c:if>>15<liferay-ui:message key="edison-search-views"/></option>
				<option value="20" <c:if test="${listSize eq 20}">selected="selected"</c:if>>20<liferay-ui:message key="edison-search-views"/></option>
			</select>
		</div>
	</form>	
</div>

<div class="table1_list">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="10%" />
			<col width="20%" />
			<col width="20%" />
			<col width="25%" />
			<col width="15%" />
			<col width="15%" />
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
<%-- 	<div id="<portlet:namespace/>spaceDiv" align="center"></div> --%>
	<div id="<portlet:namespace/>pageListDiv" class="paging"></div>
</div>
</body>

