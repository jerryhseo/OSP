<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<liferay-portlet:resourceURL var="getBatchMonitoringListURL" id="getBatchMonitoringList" escapeXml="false" copyCurrentRenderParameters="false">
</liferay-portlet:resourceURL>
<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="scienceAppExecuteBatchUrl" portletMode="view">
	<portlet:param name="myAction" value="scienceAppExecuteBatch" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="universityExecuteBatchUrl" portletMode="view">
	<portlet:param name="myAction" value="universityExecuteBatch" />
</liferay-portlet:actionURL>

<style type="text/css">
	.onMouse:hover {
		background-color: #ddd;
	}
	
	.faqContent {
		background: #f7f7fb;
		display:none;
	}
	
	.onFaqContent {
		display:table-row;
	}
	
	.subtitlearea{
		margin-left: 10px;
	}
</style>

<div class="table-responsive panel edison-panel">
	<input type="button"
			value="<liferay-ui:message key='edison-batch-manual-execute' />"
			class="btn btn-default"
			onclick="<portlet:namespace/>showOrHideBatchManualExecute()" />
	
	<div id="batchManualExecute" style="display: none;">
		
		<h1><liferay-ui:message key='edison-batch-manual-execute' /></h1>
		
		<div class="tabletopbox clear">
			<form id="<portlet:namespace/>batchExecuteForm" name="<portlet:namespace/>batchExecuteForm" method="post" style="margin:0px;">
				<input type="hidden" id="<portlet:namespace/>currentPage" name="<portlet:namespace/>currentPage" value="${currentPage}">
				
				<div id="data_wrap">
					<div class="tabletoptab">
				  		<input class="box01" type="text" id="<portlet:namespace/>startDt" name="<portlet:namespace/>startDt" readonly="readonly" value="${startDt}"/> 
							~	<input class="box01" type="text" id="<portlet:namespace/>endDt" name="<portlet:namespace/>endDt" readonly="readonly" value="${endDt}"/>
					</div>
					<div class="search03" style="padding: 11px 10px 9px 515px;">
					</div>
				</div>
			
			</form>
		</div>
	
		<div class="table1_list">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
				<colgroup id="boardColgroup">
					<col width="50%" />
					<col width="50%"/>
				</colgroup>
				<thead>
					<tr>
						<th><liferay-ui:message key='edison-table-list-header-division' /></th>
						<th><liferay-ui:message key='edison-table-list-header-run' /></th>
					</tr>
				</thead>
				<tbody id="<portlet:namespace/>batchExcuteListBody">
					<tr>
						<td class="TC"><liferay-ui:message key="edison-appstore-affiliation-exec-sts" /></td>
						<td class="TC"><a style="cursor:pointer;" onclick="<portlet:namespace/>universityExecuteBatchExecute()"><liferay-ui:message key="edison-table-list-header-run" /></a></td>
					</tr>
					<tr>
						<td class="TC"><liferay-ui:message key="edison-appstore-search-sts" /></td>
						<td class="TC"><a style="cursor:pointer;" onclick="<portlet:namespace/>scienceAppBatchExecute()"><liferay-ui:message key="edison-table-list-header-run" /></a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="panel-heading clearfix">
		<h2>
			<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
			<span class="subtitlearea">
				<liferay-ui:message key='edison-batch-execute-history' />
			</span>
		</h2>
	</div>
	
	<div class="table1_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
			<colgroup id="boardColgroup">
				<col width="8%" />
				<col width="10%" />
				<col width="12%" />
				<col width="10%" />
				<col/>
				<col width="8%" />
				<col width="8%" />
			</colgroup>
			<thead>
			<tr>
				<th><liferay-ui:message key='edison-table-list-header-index' /></th>
				<th><liferay-ui:message key='edison-table-list-header-excute-dt' /></th>
				<th><liferay-ui:message key='edison-table-list-header-division' /></th>
				<th><liferay-ui:message key='edison-table-list-header-manual-execute-yn' /></th>
				<th><liferay-ui:message key='edison-table-list-header-message' /></th>
				<th><liferay-ui:message key='edison-table-list-header-status' /></th>
				<th><liferay-ui:message key='edison-table-list-header-userid' /></th>
			</tr>
			</thead>
			<tbody id="<portlet:namespace/>batchMonitoringListBody">
			</tbody>
		</table>
	</div>
	
	<div id="<portlet:namespace/>paging" class="paging text-center"></div>
</div>
	
	<script type="text/javascript">
	
	var currentPage = "${currentPage}";
	
	function <portlet:namespace/>getBatchMonitoringList(p_currentPage){
		
		currentPage = p_currentPage;
		
		var batchMonitoringInputForm = {
						"<portlet:namespace/>methodName" : "<portlet:namespace/>getBatchMonitoringList",
						"<portlet:namespace/>currentPage" : p_currentPage
						};
		jQuery.ajax({
			type: "POST",
			url: "<%=getBatchMonitoringListURL%>",
			data: batchMonitoringInputForm,
	  		async : false,
			success: function(data) {
				var batchMonitoringList = data.batchMonitoringList;		
				
				$("#<portlet:namespace/>batchMonitoringListBody tr:not(:has(#1))").remove();			
				$vRow = $("<tr/>");
				
				if(batchMonitoringList.length == 0){
					$("<td/>").attr("colspan", "7")
					  .css("height", "40px")
					  .html("<p style='text-align:center;'> <liferay-ui:message key='edison-there-are-no-data' /></p>")
					  .appendTo($vRow);				
	
					$("#<portlet:namespace/>batchMonitoringListBody").append($vRow);
				}else{
					
					for(var i = 0 ; i < batchMonitoringList.length; i++ ){
						
						$vRow = $("<tr/>").addClass("onMouse");
						
	 					if(i%2 == 1){
	 						$vRow.addClass("tablebgtr");
	 					}
						
						$("<td/>").text(data.seq-i)
								  .addClass("TC")
								  .appendTo($vRow);
	
						$("<td/>").html(batchMonitoringList[i].exeDate)
								  .addClass("TC")
								  .appendTo($vRow);
						$("<td/>").html(batchMonitoringList[i].batDiv )
								  .addClass("TC")
								  .appendTo($vRow);
						$("<td/>").html(batchMonitoringList[i].manualYN )
								  .addClass("TC")
								  .appendTo($vRow);
						$("<td/>").html(batchMonitoringList[i].message )
						  .addClass("TL")
						  .appendTo($vRow);
						$("<td/>").html(batchMonitoringList[i].status )
								  .addClass("TC")
								  .appendTo($vRow);
						$("<td/>").html(batchMonitoringList[i].insertName )
						  .addClass("TC")
						  .appendTo($vRow);
						$("#<portlet:namespace/>batchMonitoringListBody").append($vRow);
					}
				}
				
				$("#<portlet:namespace/>paging").html(data.paging);
				
			},error:function(data,e){ 
				alert("list:::batchMonitoringList===>"+e);
			},complete:function(){
				//boardSearchList("1",divCd);
			}
		});
	}
	
	function <portlet:namespace/>scienceAppBatchExecute(){
		if(!confirm(Liferay.Language.get('edion-batch-execute-alert'))){
			return false;	
		}
		var batchExecuteForm = $('#<portlet:namespace/>batchExecuteForm').attr("action", "<%=scienceAppExecuteBatchUrl%>");
		batchExecuteForm.submit();
	}
	
	function <portlet:namespace/>universityExecuteBatchExecute(){
		if(!confirm(Liferay.Language.get('edion-batch-execute-alert'))){
			return false;	
		}
		var batchExecuteForm = $('#<portlet:namespace/>batchExecuteForm').attr("action", "<%=universityExecuteBatchUrl%>");
		batchExecuteForm.submit();
	}
	
	function <portlet:namespace/>showOrHideBatchManualExecute(){
		var display = $("#batchManualExecute").css("display");

		if(display == "none"){
			$("#batchManualExecute").css("display", "block");
		}else{
			$("#batchManualExecute").css("display", "none");
		}
	}
	
	AUI().ready(function() {
		<portlet:namespace/>getBatchMonitoringList(currentPage);
	});
	
	</script>
