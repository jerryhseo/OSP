<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<liferay-portlet:resourceURL var="searchConfigURL"		id="searchConfig"	escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="searchListURL" 		id="searchList"		escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getScienceAppURL" 		id="getScienceApp"		escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getScienceAppDetailViewURL" escapeXml="false" id="getScienceAppDetailView" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getClickScienceAppURL" escapeXml="false" id="getClickScienceApp" copyCurrentRenderParameters="false"/>
<liferay-portlet:renderURL var="simulationListURL" portletMode='view'></liferay-portlet:renderURL>
<liferay-portlet:resourceURL var="simulationCreateURL" escapeXml="false" id="simulationCreate" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="simulationUpdateURL" escapeXml="false" id="simulationUpdate" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getListCustomScienceAppCommandOptionsURL" escapeXml="false" id="getListCustomScienceAppCommandOptions" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getServerFileListURL" escapeXml="false" id="getServerFileList" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getParameterScienceAppURL" escapeXml="false" id="getParameterScienceApp" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getChangeControlAssociate" id="getChangeControlAssociate" ><portlet:param name="id" value="getChangeControlAssociate"/></liferay-portlet:resourceURL>
<liferay-portlet:resourceURL var="getChangeControlAssign" id="getChangeControlAssign" ><portlet:param name="id" value="getChangeControlAssign"/></liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="createJobURL" escapeXml="false" id="createJob"/>
<liferay-portlet:resourceURL var="deleteJobURL" escapeXml="false" id="deleteJob"/>
<liferay-portlet:resourceURL var="submitJobURL" escapeXml="false" id="submitJob"/>
<liferay-portlet:resourceURL var="getListSubmitSimulationJobURL" escapeXml="false" id="getListSubmitSimulationJob"/>
<liferay-portlet:resourceURL var="saveClickTab" id="cickTab" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:resourceURL var="getCountUserUseCoreURL" escapeXml="false" id="getCountUserUseCore"/>

<liferay-portlet:resourceURL var="createPortDataSessionURL" 		id="createPortDataSession"		escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="monitoringSearchParam" escapeXml="false" id="searchJobParam" copyCurrentRenderParameters="false"/>
 
<liferay-portlet:resourceURL var="getRerunCommandParameterURL" escapeXml="false" id="getRerunCommandParameter" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="myFileAddURL" id="myFileAdd" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="resorceConfigURL" 		escapeXml="false" id="searchConfig"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="searchSimulationJobDataURL" escapeXml="false" id="searchSimulationJobData" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="solverTypeListURL" 		escapeXml="false" id="solverTypeList" 	 copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="resorceSearchURL" 		escapeXml="false" id="projectSearch" 	 copyCurrentRenderParameters="false"/>
<liferay-portlet:renderURL var="projectDetailUrl" portletName="edisonsimulationproject_WAR_edisonsimulationproject2017portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myRender" value="detailView" />
</liferay-portlet:renderURL>


<script src="${contextPath}/js/jquery.blockUI.js"></script>
<% 
	//Tab Setting
	String tabNames = (String)request.getAttribute("tabNames");
	String visitSite = CustomUtil.strNull(request.getAttribute("visitSite"));
	String tabsValues = (String)request.getAttribute("tabsValues");
	String portletNameSpace = "_"+portletDisplay.getId()+"_"+"tagScript";
	
	
	String searchSwNm = LanguageUtil.get(themeDisplay.getLocale(), "edison-appstore-solver-name");
	String searchSwTitle = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-app-title");
	String searchOrgNm = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-orgNm");
	String searchDev = LanguageUtil.get(themeDisplay.getLocale(), "developer");
	String searchAll = searchSwTitle+" or "+searchSwNm+" or "+searchOrgNm+" or "+searchDev;
%>   
<style>
     
/*Workflow*/
	#accordion {
		border: solid 1px #000;
		border-bottom: 0px;
	}
	
	.ui-state-active .simultitleboxon {
		background: #e3eff9;
	}
	
	.ui-state-active .simulrighton {
		background: #f5fbfe;
	}
	
	.ui-state-active .simulicon .iconDiv {
		background: url(${contextPath}/images/btn_simulopen.png);
	}
	
	.simulheader {
		cursor:pointer;
	}
	
	.iconDiv {
		width:21px;
		height:14px;
		background:url(${contextPath}/images/btn_simulclose.png);
	}
	
	.simulcont {
		display:none;
		height:423px;
		overflow:auto;
	}
	
	.wrapContent {
		white-space:nowrap; text-overflow:ellipsis; -o-text-overflow: ellipsis; overflow:hidden; -moz-binding: url('ellipsis.xml#ellipsis');
	}
	
	.aui .control-group {
		margin-bottom:0px
	}
	
	.table2_list table input[type="text"] {
		margin-bottom:0px;
	}
	
	.ui-tooltip {
		background: white;
		font-weight: 600;
	}
	
	#accordion input[type="button"], .simulheader input[type="button"], .buttonbox input[type="button"] {
		font-weight: 600;
		font-size: 14px;
		line-height: normal;
	}
</style>
<script type="text/javascript">

var currentFrameNumber = 0;

var workflowStepState = new Array(false, false, false, false);

var preFormCount = 0;
var parameterFormCount = 0;

var slideTitle = new Array(
							'<liferay-ui:message key="edison-simulation-left-tab-label-1" />',
							'<liferay-ui:message key="edison-simulation-left-tab-label-2" />',
							'<liferay-ui:message key="edison-simulation-left-tab-label-3" />',
							'<liferay-ui:message key="edison-simulation-left-tab-label-4" />'
							);

var workflowButtonSpeed = 400;

//선택한 Tab Id
var selectedTabId = "";

//입력포트 객체
var portMapList;

//liferay-ui 탭 이벤트 return Script
function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
	var searchData = {"<portlet:namespace/>groupId":value};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=saveClickTab%>",
		async : false,
		data  : searchData,
		success: function(data) {
			location.href="<%=simulationListURL%>&<portlet:namespace/>thisGroupId="+value;
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}
 
function simulationAllInit(){
	workflowStepState = new Array(false, false, false, false);
	
	simulationSelectInit();
	simulationInsertInit();
	simulationDataInsertInit();
	simulationJobCreateInit();
} 

function simulationSelectInit(){	
	$("#<portlet:namespace/>scienceAppId").val("");
	$("#<portlet:namespace/>scienceApp_name").val("");

	$("#<portlet:namespace/>isAppSubmitStatus").val("true");
	$("#<portlet:namespace/>isSubmit").val("false");
	$("#<portlet:namespace/>simulationUuid").val("");
	$("#<portlet:namespace/>cluster").val("");
	$("#<portlet:namespace/>exec_path").val("");
	$("#<portlet:namespace/>isCommandOption").val("false");

	$("#scienceAppListBody > tr > td").css("backgroundColor", "transparent");	
}
function simulationInsertInit(){


	$("#<portlet:namespace/>simulation_title").val("");
	$("#<portlet:namespace/>simulation_description").val("");
	
	$("#<portlet:namespace/>metaScienceApp_title").text("");
	$("#<portlet:namespace/>metaVersion").text("");
	$("#<portlet:namespace/>metaMgtDate").text("");
	$("#<portlet:namespace/>metaScienceApp_affilation").text("");
	$("#<portlet:namespace/>metaDeveloperNames").text("");
}
function simulationDataInsertInit(){

}
function simulationJobCreateInit(){

}

// ADD
//필터 조회
function solverTypeList(){
	var searchData = {
			"<portlet:namespace/>groupId":"<%=visitSite%>"
	};

	jQuery.ajax({
		type: "POST",
		url: "<%=solverTypeListURL%>",
		async:true,
		data: searchData,
		success:function(solverTypeList){
			$("#solverTypeBody").empty();
			var evalDataMap = eval("(" + solverTypeList + ")");
			var dataMap = evalDataMap.solverTypeList;
			var solverTypeCount = dataMap.length;
			var solverTypeWidth = 99 / solverTypeCount;
			var projectListMap = evalDataMap.projectList;
			var projectListMapCnt = evalDataMap.projectListCnt;
			
			if("${tabViewYn}" == "Y") {
				for (var i = 0; i < solverTypeCount; i++) {
					var imageValue = "";
					if(typeof dataMap[i].image != "undefined") imageValue = dataMap[i].image.value;
					$categoryTd = $("<td/>").attr("id", "solvertype_" + dataMap[i].categoryId)
							  .attr("onClick", "solverTypeClick(" + dataMap[i].categoryId + ")")
							  .attr("width", solverTypeWidth + "%")
							  .css("cursor","pointer")
							  .css("border", "none")
							  .addClass("portalClass")
							  .append(
									$("<div/>").attr("align", "center").css("text-align","center").append(
													   $("<img/>").attr("src", "${contextPath}/images/solverType/" + imageValue +  ".png").css("margin", "15px 0px 0px 0px").css("height", "76px").css("max-width", "100px")
									  )
							  ).append(
									   $("<div/>").css("text-align","center").css("height", "55px").css("margin", "0 auto")
												  .append(
														  $("<p/>").html(dataMap[i].title).css("padding","3px").css("margin", "0px").css("word-break","keep-all")
									   )
							  ).append(
									   $("<div/>").addClass("tail")
												  .css("left", ((solverTypeWidth / 2) + (solverTypeWidth * i)) + (0.1 * i) + "%")
							  ).appendTo($("#solverTypeBody"));
					
					if(dataMap[i].categoryId=="${params.categoryId}"){
						$categoryTd.addClass("onClass");
					}
					
					if(i != (dataMap.length -1)) {
						$("<td/>").css("width", "1px")
								  .css("padding", "20px 0px")
								  .css("border","none")
								  .append(
									$("<div/>").append(
											   $("<img/>").attr("src", "${contextPath}/images/categ_divline01.gif")
									  )
									)
								  .appendTo($("#solverTypeBody"));
					}
					
				}
			} else {
				for (var i = 0; i < dataMap.length; i++) {
					$categoryTd = $("<td/>").attr("id", "solvertype_" + dataMap[i].categoryId)
							  .attr("onClick", "solverTypeClick(" + dataMap[i].categoryId + ")")
							  .attr("width", solverTypeWidth + "%")
							  .css("cursor","pointer")
							  .css("border", "none")
							  .addClass("siteClass")
							  .append(
									$("<div/>").attr("align", "center").append(
													   $("<img/>").attr("src", "${contextPath}/images/solverType/" + dataMap[i].image.value + ".png").css("margin", "15px 0px 0px 0px").css("height", "76px").css("max-width", "100px")
									  )
							  ).append(
									   $("<div/>").css("text-align", "center").css("height", "55px").css("margin", "0 auto")
												  .append(
														  $("<p/>").html(dataMap[i].title).css("padding","3px").css("margin", "0px").css("word-break","keep-all")
									   )
							  ).append(
									   $("<div/>").addClass("tail2")
												  .css("left", ((solverTypeWidth / 2) + (solverTypeWidth * i)) + (0.1 * i) + "%")
							  ).appendTo($("#solverTypeBody"));
					
					if(dataMap[i].categoryId=="${params.categoryId}"){
						$categoryTd.addClass("onClass2");
					}
					
					if(i != (dataMap.length -1)) {
						$("<td/>").css("width", "1px")
								  .css("padding", "20px 0px")
								  .css("border","none")
								  .append(
									$("<div/>").append(
											$("<img/>").attr("src", "${contextPath}/images/categ_divline01.gif")
										)
									)
								  .appendTo($("#solverTypeBody"));
					}
					
				}
			}
			
			// ADD
			if(projectListMapCnt==0){
				$vRow = $("<tr/>");
				$("<td/>").attr("colSpan","6")
						  .html("<p><liferay-ui:message key='edison-there-are-no-data' /></p>")
						  .css("textAlign","center")
						  .appendTo($vRow);
				
				$("#simulationProjectTableBody").append($vRow);
			}else{
				for(var i=0; i<projectListMapCnt;i++){
					
					$vRow = $("<tr/>");
					
					// sequence
					$("<td/>").text(projectListMapCnt-i).addClass("TC").appendTo($vRow);
					
					// title
					$tdRow = $("<td/>").css("word-break", "break-all")
									   .css("text-align","left")
									   .text(projectListMap[i].title)
									   .attr("onclick","<portlet:namespace/>moveSimulationProjectDetail('"+projectListMap[i].simulationProjectId+"')")
									   .css("cursor", "pointer")
					$tdRow.appendTo($vRow);
					
					// 기관명
					$("<td/>").addClass("TC").appendTo($vRow);
					
					// 아이디
					$("<td/>").addClass("TC").appendTo($vRow);
					
					// 메뉴얼
					$("<td/>").addClass("TC").appendTo($vRow);
					
					// 상세정보
					$("<td/>").addClass("TC").appendTo($vRow);
					
					$("#simulationProjectTableBody").append($vRow);
				}
				
				
				//즐겨 찿기 솔버 추가 후에 검색한 솔버가 없을 경우 no-data 표시
			}
			
		},error:function(e){
		}
	});
}

function solverTypeClick(categoryId) {
	if("${tabViewYn}" == "Y") {
		$(".onClass").removeClass("onClass");
		$("#solvertype_" + categoryId).addClass("onClass");
	} else {
		$(".onClass2").removeClass("onClass2");
		$("#solvertype_" + categoryId).addClass("onClass2");
	}
	
	/* 필터 초기화 */
	$("#<portlet:namespace/>categoryId").val(categoryId);
	<portlet:namespace/>dataSearchList();
}

function <portlet:namespace/>dataSearchList(p_curPage){
	var categoryIdValue = $("#<portlet:namespace/>categoryId").val();
	
	if(p_curPage == null || p_curPage == 0){
		p_curPage = "1";
	}
	
	var currentTabGroupId = <%=visitSite%>;
	
	var searchData = {
			"<portlet:namespace/>groupId" : currentTabGroupId,
			"<portlet:namespace/>categoryId":categoryIdValue,
			"<portlet:namespace/>searchText" : $("#<portlet:namespace/>searchValue").val()
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=resorceSearchURL%>",
		data: searchData,
		success: function(data) {
			var dataMap = eval("(" + data + ")");
			var dataList = dataMap.dataList;
			var totalCnt = dataMap.totalCnt;
			var select_line = dataMap.select_line;
			var pageList = dataMap.pageList;
			
			$("#simulationProjectTableBody tr:not(:has(#1))").remove();
			var vSummaryListBody =  document.getElementById("simulationProjectTableBody");
			var vRow, vCell;
			
			$("#simulationProjectTableBody tr:not(:has(#1))").remove();
			
			if(totalCnt==0){
				$vRow = $("<tr/>");
				$("<td/>").attr("colSpan","6")
						  .html("<p><liferay-ui:message key='edison-there-are-no-data' /></p>")
						  .css("textAlign","center")
						  .appendTo($vRow);
				
				$("#simulationProjectTableBody").append($vRow);
			}else{
				var pageNum = totalCnt - (p_curPage-1) * select_line;
				
				for(var i=0; i<totalCnt;i++){
					
					$vRow = $("<tr/>");
					
					// sequence
					$("<td/>").text(pageNum--).addClass("TC").appendTo($vRow);
					
					// title
					$tdRow = $("<td/>").css("word-break", "break-all")
									   .css("text-align","left")
									   .text(dataList[i].title)
									   .attr("onclick","<portlet:namespace/>moveSimulationProjectDetail('"+dataList[i].simulationProjectId+"')")
									   .css("cursor", "pointer")
					$tdRow.appendTo($vRow);
					
					// 기관명
					$("<td/>").addClass("TC").appendTo($vRow);
					
					// 아이디
					$("<td/>").addClass("TC").appendTo($vRow);
					
					// 메뉴얼
					$("<td/>").addClass("TC").appendTo($vRow);
					
					// 상세정보
					$("<td/>").addClass("TC").appendTo($vRow);
					
					$("#simulationProjectTableBody").append($vRow);
				}
				
				
				//즐겨 찿기 솔버 추가 후에 검색한 솔버가 없을 경우 no-data 표시
				if(totalCnt==0){
					$vRow = $("<tr/>");
					$("<td/>").attr("colSpan","9")
							  .html("<p><liferay-ui:message key='edison-there-are-no-data' /></p>")
							  .css("textAlign","center")
							  .appendTo($vRow);
					
					$("#simulationProjectTableBody").append($vRow);
				}
				
			}
			//페이징 초기화pageListDiv
			if(pageList != null && pageList != '' && pageList != 'undefined'){
				document.getElementById("pageListDiv").innerHTML = pageList;
			}
		},
		error:function(msg){
			alert("System Exception : " + msg);
		}
	});
}

function <portlet:namespace/>moveSimulationProjectDetail(simulationProjectId) {
	var thisPortletNamespace = "_edisonsimulationproject_WAR_edisonsimulationproject2017portlet_";
	var params = "&" + thisPortletNamespace + "simulationProjectId=" + simulationProjectId;
	    params += "&" + thisPortletNamespace + "isRedirect=" + false;
	window.open("<%=projectDetailUrl%>" + params, '_self');
}

function <portlet:namespace/>searchListAll(){
	$("#<portlet:namespace/>searchValue").val("");
	<portlet:namespace/>dataSearchList();
}

</script>

<div class="table-responsive panel edison-panel">
	<%
		if((Boolean)request.getAttribute("isPortalMain")){
	%>
	<!-- Tab view -->
	<div class="contabmenu"> 
		<edison-ui:tabs 
			names="<%=tabNames%>" 
			tabsValues="<%=tabsValues%>" 
			value="<%=visitSite%>" 
			refresh="<%=false%>" 
			onClick="<%=portletNameSpace%>"
			minwidth="195"
		/>
	</div> 
	<%
		}
	%>
	
	<div class="h10"></div>
	
	<h1 id="<portlet:namespace/>simulation_subtitle">
		<img src="${pageContext.request.contextPath}/images/title_virtual.png"/>
		<liferay-ui:message key="edison-simulation-project" />
	</h1>
	
	<div class="h10"></div>
	
	<!-- category images -->
	<div class="scAppmenu">
		<div class="table5app" style="border:none;">
			<table width="100%" height="146" border="0" cellpadding="0" cellspacing="0" >
				<tr id="solverTypeBody" style="border-left:1px solid #e5e5e5; border-right:1px solid #e5e5e5;">
				</tr>
			</table>
		</div>
	</div>
	
	<div class="h40"></div>
	 
	 <input type="hidden" id="<portlet:namespace/>categoryId"	name="<portlet:namespace/>categoryId"	value=""/>
	<aui:form method="post" id="simulationForm" name="simulationForm" cssClass="table-responsive panel edison-panel">
		<aui:input type="hidden" name="scienceAppId"			id="scienceAppId"/>
		<aui:input type="hidden" name="scienceApp_name"			id="scienceApp_name"/>
		<aui:input type="hidden" name="exec_path"			id="exec_path"/>
		<aui:input type="hidden" name="isSubmit"			id="isSubmit" value="false"/>
		<aui:input type="hidden" name="isAppSubmitStatus"	id="isAppSubmitStatus" value="false"/>
		<aui:input type="hidden" name="simulationUuid"		id="simulationUuid"/>
		<aui:input type="hidden" name="cluster"				id="cluster"/>
		<aui:input type="hidden" name="curPage" 			id="curPage" value="1"				/>
		<aui:input type="hidden" name="groupId" 			id="groupId" value="<%=visitSite%>"	/>
		<aui:input type="hidden" name="isCommandOption"		id="isCommandOption"	value="false"/>
		<aui:input type="hidden" name="searchOption"		id="searchOption" value="SCIENCEAPPSTORE_SEARCH_ALL"/>
		
		
		<!-- Button -->
		<div class="panel-heading clearfix">
			<div class="input-group">
				<input id="<portlet:namespace/>searchValue" name="<portlet:namespace/>searchValue" class="form-control" type="text" placeholder="<%=searchAll%>" size="25" maxlength="100" onKeydown="if(event.keyCode ==13)<portlet:namespace/>dataSearchList();" style="width: 50%; float: right; margin-left: 1%;" />
				
				<div class="input-group-btn">
					<button class="btn btn-default" type="button" name="fullsize" id="keyWordB" onclick="<portlet:namespace/>dataSearchList();">
						<i class="icon-search"></i>
					</button>
					
					<button class="btn btn-default" type="button" name="fullsize" id="fullsize" onclick="<portlet:namespace/>searchListAll(); return false;">
						Clear
					</button>
				</div>
				
			</div>
		</div>
		
		<!-- Simulation Project List -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="일반콘텐츠 테이블" class="table1_list table table-bordered table-hover edison-table">
			<colgroup>
				<col width="70" />
				<col width="*" />
				<col width="120" />
				<col width="120" />
				<col width="100" />
				<col width="100" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-index" /></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-title"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-orgNm"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-userid"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-manual"/></th>
					<th scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-detail"/></th>
				</tr>
			</thead>
			<tbody id="simulationProjectTableBody">
			</tbody>
		</table>
		
		<!-- Page -->
		<div class="text-center">
		</div>
	</aui:form>
	
	<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

	<div id="dialog-message" title="ScienceApp Filter" style="display:none; background:white; padding:0px;" class="newWindow">
		<form method="post" name="<portlet:namespace/>searchParamForm" style="margin:0px;">
			<div class="newWheader">
				<div class="newWtitlebox"><img src="${contextPath}/images/title_newWindow.png" width="34" height="34" />
				<div class="newWtitle">ScienceApp Filter(Category)</div></div>
				<div class="newWclose"><img id="<portlet:namespace/>dialog-message-close-btn" name="<portlet:namespace/>dialog-message-close-btn" src="${contextPath}/images/btn_closeWindow.png" width="21" height="21" style="cursor: pointer;" /></div>
			</div>
			
			<div class="newWcont01">
				<table border="0" cellspacing="0" cellpadding="0" id="configFilter" >
				</table>
				<div class="tbcell070301"></div>
				<div class="buttonbox" >
					<input type="button" value="<liferay-ui:message key="edison-button-search" />"  onclick="filterSearch();" class="btn btn-default"/>
					<input type="button" value="<liferay-ui:message key="edison-button-board-initialize" />"  onclick="filterInit();" class="btn btn-default"/>
				</div>
			</div>
			
		</form>
	</div>
</div>
<aui:script>
var simulationJobData;
var p_scienceAppId;
var testYn = "N";
function <portlet:namespace/>myFileAdd(p_fileEntryId,p_fileNm,p_optionNm,p_workflowType){
	var insertData = {
		"<portlet:namespace/>groupId":"<%=visitSite%>",
		"<portlet:namespace/>cluster":$("#<portlet:namespace/>cluster").val(),
		"<portlet:namespace/>fileEntryId":p_fileEntryId
	}
	
	$responseFormId = $("#<portlet:namespace/>"+p_optionNm);
	$responseFileName = $("#<portlet:namespace/>"+p_optionNm+"_logical_file_value");
	
	jQuery.ajax({
		type: "POST",
		url: "<%=myFileAddURL%>",
		async : false,
		data  : insertData,
		datatype:'json',
		success: function(val) {
			if(p_workflowType=="inputport"){
				$responseFormId.val(val.fileAPIId).change();
			}else{
				$responseFormId.val(val.filePath).change();
			}
			
			$responseFileName.val(p_fileNm).change();
		},error:function(data,e){
			alert("myFileAdd_ERROR==>"+e);
		}
	});
	
}


function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

	AUI().use('aui-base','liferay-form',function(A){
		var rules = {
			<portlet:namespace/>simulation_title: {
				required: true
			}
		};
		new A.FormValidator(
			{
				boundingBox: '#<portlet:namespace/>simulationForm',
				rules: rules,
				showAllMessages:true,
				validateOnInput: true,
				extractRules: true,
				validateOnBlur: true,
				selectText: true,
			}
		);
	});
	
	AUI().ready(function() {
		
		solverTypeList();
		
		$("#<portlet:namespace/>dialog-message-close-btn").click(function() {
			$("#dialog-message").dialog("close");
		});
		
		$("#accordion").accordion({
			header : "> div > .simulheader",
			heightStyle: "content",
			animate: 200,
			beforeActivate: function( event, ui ) {
				var updownFlag = false;
				var targetFrameNumber = ui.newHeader.attr("id").substring(ui.newHeader.attr("id").length - 1, ui.newHeader.attr("id").length);
//				alert("target : " + targetFrameNumber + " current : " + currentFrameNumber);

				if(targetFrameNumber == currentFrameNumber){
					event.preventDefault();
					return;
				}
				if(workflowStepState[0] && workflowStepState[1] && targetFrameNumber == 3) {
					if(preProcessorCheck()) {
						workflowStepState[2] = true;
					} else {
						alert(slideTitle[2]);
						if(2 != currentFrameNumber) {
							$( "#accordion" ).accordion( "option", "active", 2);
						}
						event.preventDefault();
						return;
					}
				}
				
				if(targetFrameNumber > currentFrameNumber){//앞으로이동
					//입력포트나 파라미터에 입력대상이 없는 경우 skip
					for (var i = 0; i < targetFrameNumber; i++) {
						if(!workflowStepState[i]) {
							alert(slideTitle[i]);
							if(i != currentFrameNumber) {
								$( "#accordion" ).accordion( "option", "active", i);
							}
							event.preventDefault();
							return;
						}
					}
				}
				currentFrameNumber = targetFrameNumber;
			}
		});
		
		$("#accordion").accordion( "option", "icons", null ); 
		simulationAllInit(); 
		
		p_scienceAppId = "${directScienceAppId}";
		testYn = "${testYn}";
		
		if(p_scienceAppId != "" && testYn == "Y") {
			setScienceApp(p_scienceAppId);
			
			$("#scienceAppListBody tr").each(function( index, value ) {
				if($(this).attr("scienceappid") == p_scienceAppId) {
					scienceAppListClick(this);
 					$("#<portlet:namespace/>simulation_title").val("[Test] " + $(this).attr("scienceApp_name"));
					
					simulationTitleOnblur();
// 					simulationMetaInfoSet();
					preProcessorCheck();
					
					$("#accordion > .group:nth-child(1)").hide();
					$("#accordion > .group:nth-child(2)").hide();
					$("#<portlet:namespace/>simulation_subtitle").hide();
					$("#moveMonitoring").hide();
					workflowStepState[0] = true;
					$( "#accordion" ).accordion( "option", "active", 2);
 					return false;
				}
			});
			
		} else if(p_scienceAppId != ""){
			$("#<portlet:namespace/>searchValue").val("${directScienceApp_name}");
			
			setScienceApp(p_scienceAppId);
			simulationJobData = ${simulationJobData};
			$("#scienceAppListBody tr").each(function( index, value ) {
				if($(this).attr("scienceappid") == p_scienceAppId && Object.keys(simulationJobData).length > 0) {
					scienceAppListClick(this);
					$(".search_class").hide();
					
 					$("#<portlet:namespace/>simulation_title").val((simulationJobData["simulation_title"].toString().startsWith("[Rerun]")) ? simulationJobData["simulation_title"] : "[Rerun] " + simulationJobData["simulation_title"]);
					$("#<portlet:namespace/>simulation_description").val(simulationJobData["simulation_description"]);
					
					simulationTitleOnblur();
// 					simulationMetaInfoSet();
					preProcessorCheck();
					
					workflowStepState[0] = true;
					$( "#accordion" ).accordion( "option", "active", 3);
 					return false;
				} else if($(this).attr("scienceappid") == p_scienceAppId) {
					scienceAppListClick(this);
					$(".search_class").hide();
					
					$( "#accordion" ).accordion( "option", "active", 0);
				}
			});

		}
	});
	
	
	function <portlet:namespace/>moveMonitoring() {
		AUI().use("liferay-portlet-url", function(a) {
			var portletURL = Liferay.PortletURL.createRenderURL();
			portletURL.setPlid("${monitoringPlid}"); 
			portletURL.setPortletId("edisonmonitoring_WAR_edisonsimulationportlet"); 
			window.location.href = portletURL.toString();
		});
	}
	
	function addScienceAppData(portName, data){
		$('#<portlet:namespace/>' + portName).val(JSON.stringify(data));
		if(typeof data != 'undefined' && data != "") {
			$('#<portlet:namespace/>' + portName + '_info').text("SUCCESS").css("color", "green");
		} else {
			$('#<portlet:namespace/>' + portName + '_info').text("FAIL").css("color", "red");
		}
		$('#<portlet:namespace/>' + portName + '_info_btn').show();
	}
	
	Liferay.on(
		'edison-simulation-test',
			function(event) {
				testYn = event.testYn;
				if(event.testYn == "Y" && event.scienceAppId != "") {
					var scienceAppId = event.scienceAppId;
					setScienceApp(scienceAppId);
					
					$("#scienceAppListBody tr").each(function( index, value ) {
						if($(this).attr("scienceappid") == scienceAppId) {
							scienceAppListClick(this);
		 					$("#<portlet:namespace/>simulation_title").val("[Test] " + $(this).attr("scienceApp_name"));
							
							simulationTitleOnblur();
// 							simulationMetaInfoSet();
							preProcessorCheck();
							
  							$("#accordion > .group:nth-child(1)").hide();
							$("#accordion > .group:nth-child(2)").hide();  
							$("#moveMonitoring").hide();
							workflowStepState[0] = true;
							$( "#accordion" ).accordion( "option", "active", 2);
		 					return false;
						}
					});

				}
			}
	);
	
	Liferay.provide(
		window,
		'fetchResult',
	   	function( taskId, portName, value ) 
	   	{
			var content = {
					taskId : taskId,
					portName : portName,
					value: value
			};
			
			var payload = OSPEvent.createEvent(
					"stringPort",
					OSPEvent.Constants.TYPE_BROADCAST,
					'<portlet:namespace/>',
					'',
					content);
			
			Liferay.fire(OSPEvent.Constants.IPC_EVENT_CONTENT_BROADCAST, payload);
	   	},
		['aui-base','liferay-util-window','aui-dialog-iframe-deprecated','liferay-util-window']
	);
		
	Liferay.provide(
		window,
		'closePopup',
	   	function(popupId) {
	       	var popupDialog = Liferay.Util.Window.getById(popupId);
            popupDialog.destroy();
            $("body").css('overflow','');
	   	},
		['aui-base','aui-dialog','aui-dialog-iframe','liferay-util-window']
	);
	
	Liferay.on(OSPEvent.Constants.IPC_EVENT_CONTENT_BROADCAST, function(event){
		var json = event.getEventData();

		if(typeof json["value"] == "object") {
			$('#<portlet:namespace/>' + json["portName"]).html(JSON.stringify(json["value"]));
		} else {
			$('#<portlet:namespace/>' + json["portName"]).html(json["value"]);
		}
		$('#<portlet:namespace/>' + json["portName"] + "_type").val(json["taskId"]);
		
// 		alert($('#<portlet:namespace/>' + json["portName"]).val());
// 		alert(json["value"]);
		if(typeof json["value"] != 'undefined' && json["value"] != "") {
			$('#<portlet:namespace/>' + json["portName"] + '_info').text("SUCCESS").css("color", "green");
		} else {
			$('#<portlet:namespace/>' + json["portName"] + '_info').text("FAIL").css("color", "red");
		}
		$('#<portlet:namespace/>' + json["portName"] + '_info_btn').show();
	});
</aui:script>
