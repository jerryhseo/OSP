<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<script src="<c:url value="/js/main.js" />"></script>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/scienceappmanager.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery/jquery-ui.min.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery/jquery-ui.theme.min.css" media="screen"/>
<liferay-portlet:resourceURL var="searchCommonLibURL" id="searchCommonLib" copyCurrentRenderParameters="false" escapeXml="false"/>

<style type="text/css">
</style>

<div class="newWindow" style="background-color: #fff; overflow-x: hidden;overflow-y: hidden;">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">
				<liferay-ui:message key='edison-science-appstore-toolkit-library-list-title' />
			</div>
		</div>
		<div class="newWclose">
			<img id="common-lib-close-btn" name="common-lib-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	<div class="newWcont01">
		<div class="table-responsive panel edison-panel">
			<div class="panel-heading clearfix">
				<div class="input-group">
					<input type="text" class="field" id="<portlet:namespace/>commonSearchValue" name="<portlet:namespace/>searchValue" onKeydown="if(event.keyCode ==13)<portlet:namespace/>searchCommonLib();" autocomplete="off"/>
					<div class="input-group-btn">
						<button class="btn btn-default" type="button" onClick="<portlet:namespace/>searchCommonLib();"><i class="icon-search"></i></button>
						<button class="btn btn-default" onClick="<portlet:namespace/>searchListAll();">Clear</button>
					</div>
				</div>
			</div>
			<table class = "table table-bordered table-hover edison-table">
				<thead>
					<th width="15%"><liferay-ui:message key='edison-table-list-header-index' /></th>
					<th width="*"><liferay-ui:message key='edison-table-list-header-file-nm' /></th>
					<th width="20%">Path</th>
					<th width="10%"><liferay-ui:message key='version' /></th>
					<th width="10%">Kernel Version</th>
					<th width="10%">CLib Version</th>
					<th width="10%">System Archive</th>
				</thead>
				<tbody id="<portlet:namespace/>commonLibBody">
					
				</tbody>
			</table>
			<div class="text-center" id="commonLibPage"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
AUI().ready(function() {
	$("#common-lib-close-btn").click(function() {
		$("#common-lib-dialog").dialog("close");
	});
	
	<portlet:namespace/>searchCommonLib(1);
});
function <portlet:namespace/>searchListAll(){
	var searchValue = $("#<portlet:namespace/>commonSearchValue").val("");
	<portlet:namespace/>searchCommonLib(1);
}

function <portlet:namespace/>searchCommonLib(p_curPage){
	var searchValue = $("#<portlet:namespace/>commonSearchValue").val();
	
	var searchForm = {
			"<portlet:namespace/>p_curPage": p_curPage,
			"<portlet:namespace/>searchValue": searchValue
	};
	
	$("#<portlet:namespace/>commonLibBody tr:not(:has(#1))").remove();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchCommonLibURL%>",
		async : false,
		data  : searchForm,
		dataType: 'json',
		success: function(result) {
			$tbody = $("#<portlet:namespace/>commonLibBody");
			document.getElementById("commonLibPage").innerHTML="";
			var length = result.dataList.length;
			
			if(length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").css("text-align","center")
						  .text(Liferay.Language.get('edison-there-are-no-data'))
						  .attr("colspan","7")
						  .appendTo($rowResult);
				$tbody.append($rowResult);
			}else{
				for(var i = 0; i < length; i++) {
					var data = result.dataList[i];
					
					$rowResult = $("<tr/>");
					
					if(i%2 == 1){
						$rowResult.addClass("tablebgtr");
 					}
					
					$("<td/>").addClass("TC").text(result.seq-i).appendTo($rowResult);
					$("<td/>").text(data.libName).appendTo($rowResult);
					$("<td/>").text(data.libPath).appendTo($rowResult);
					$("<td/>").addClass("TC").text(data.libraryVersion).appendTo($rowResult);
					$("<td/>").addClass("TC").text(data.kernelVer).appendTo($rowResult);
					$("<td/>").addClass("TC").text(data.cLibVer).appendTo($rowResult);
					$("<td/>").addClass("TC").text(data.sysArch).appendTo($rowResult);
					
					$tbody.append($rowResult);
				}
				
				
				document.getElementById("commonLibPage").innerHTML=result.page;
			}
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}  
		}
	});
}
</script>

