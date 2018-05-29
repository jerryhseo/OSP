<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>
<%
	String searchSwNm = LanguageUtil.get(themeDisplay.getLocale(), "edison-appstore-solver-name");
	String searchSwTitle = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-app-title");
	String searchOrgNm = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-orgNm");
	String searchDev = LanguageUtil.get(themeDisplay.getLocale(), "developer");
	String searchAll = "("+searchSwTitle+"+"+searchSwNm+"+"+searchOrgNm+"+"+searchDev+")";
%>
<head>

<liferay-portlet:resourceURL var="resorceSearchURL" 		escapeXml="false" id="searchList" 			 copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="resorceConfigURL" 		escapeXml="false" id="searchConfig" 		 copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL"	 escapeXml="false" id="edisonFileDownload"	 copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="solverTypeListURL" 		escapeXml="false" id="solverTypeList" 		 copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="isSiteMemberURL" 			escapeXml="false" id="isSiteMember" 		 copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL plid="${simulationPlid}" portletName="_SIMULATION_WAR_edisonportlet_" portletMode="view" var="exeURL"/>

<liferay-portlet:actionURL var="redirectSolverExeURL"  portletMode="view" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="redirectSolverExe"/>
	<liferay-portlet:param name="redirect" value="<%=exeURL%>"/>
</liferay-portlet:actionURL>

<liferay-portlet:renderURL var="renderViewURL">
	<portlet:param name="myaction" value="detailView" />
	<portlet:param name="edionCopyParam" value="true" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL var="saveClickTabURL" portletMode="view" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="saveClickTab"/>
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="saveClickTab" id="cickTab" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:resourceURL var="favoriteAppListURL" id="favoriteAppList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteFavoriteAppURL" id="deleteFavoriteApp" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL plid="${simulationPlid}" portletName="_SIMULATION_WAR_edisonportlet_" portletMode="view" var="exeURL">
	<liferay-portlet:param name="p_p_state" value="normal"/>
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="solverFavoriteURL" 		escapeXml="false" id="solverFavorite"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>
	
<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="updateSovelInfoActionUrl" portletMode="view" >
	<portlet:param name="myAction" value="updateSolverInfoAction"/>
	<portlet:param name="descriptionId" value="${solver.descriptionId}"/>
	<portlet:param name="solverId" value="${solver.scienceAppId}"/>
	<portlet:param name="redirectName" value="${redirectName}"/>
	<portlet:param name="redirectURL" value="${redirectURL}"/>
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="addFavoriteAppURL" id="addFavoriteApp" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteFavoriteAppURL" id="deleteFavoriteApp" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="scienceAppCategoryURL" id="scienceAppCategory" copyCurrentRenderParameters="false" />
<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="detailView" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" 
  portletName="SimulationWorkbench_WAR_OSPWorkbenchportlet">
    <liferay-portlet:param name="workbenchType" value="SIMULATION_WITH_APP"/>
</liferay-portlet:renderURL>

<style type="text/css">
#solverTypeBody .portalClass:hover ,.onClass{
	background-color:#e5eff8;
}
.tail {
	border-color:#e5eff8 transparent transparent transparent;
	border-width:8px;
	border-style:solid;
	margin-left:-7px;
	width:0px;
	height:0px;
	position:absolute;
	display:none;
}
.onClass .tail {
	display:block;
}
#solverTypeBody .portalClass:hover .tail {
	display:block;
}

#solverTypeBody .siteClass:hover , .onClass2{
	background-color:#f2efeb;
}
.tail2 {
	border-color:#f2efeb transparent transparent transparent;
	border-width:8px;
	border-style:solid;
	margin-left:-7px;
	width:0px;
	height:0px;
	position:absolute;
	display:none;
}
.onClass2 .tail2 {
	display:block;
}
#solverTypeBody .siteClass:hover .tail2 {
	display:block;
}
.sideline{
	width:1px; background-color:#e5e5e5;
}
.categoriesDiv{
	text-align: center;
}
.subtitlearea{
	margin-left: 10px;
}
.tabWidth{
	width: 1200px !important;
}
</style>

<script src="${pageContext.request.contextPath}/js/jquery-dateFormat.js"></script>


</head>
<body>
	<%
	//Tab Setting
		String tabNames = (String)request.getAttribute("tabNames");
		String tabsValues = (String)request.getAttribute("tabsValues");
		String portletNameSpace = "_"+portletDisplay.getId()+"_"+"tagScript";
		String visitSite = CustomUtil.strNull(request.getAttribute("visitSite"));
		String searchField = CustomUtil.strNull(request.getAttribute("searchField"));
	%>
	
		<div class="table-responsive panel edison-panel tabWidth">
		
			<form name="form" method="post" action="<%=exeURL%>">
				<input name="<portlet:namespace/>id"    type="hidden"/>
			</form>
			
			<!-- title -->
			<h2>
				<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
				<span class="subtitlearea">
					ScienceApp
				</span>
			</h2> 
			
			<div class="h20"></div>
			
			<!-- tab -->
			<c:if test="${not empty tabsValues}">
				<div class="contabmenu"> 
					<edison-ui:tabs names="<%=tabNames%>" tabsValues="<%=tabsValues%>" value="<%=visitSite%>" refresh="<%=false%>" onClick="<%=portletNameSpace%>" minwidth="150"/>
				</div>
				
				<div class="h10"></div>
			</c:if>
			
			<div class="h10"></div>
			
			<!-- Category -->
			<div class="scAppmenu">
				<div class="categoriesDiv" style="border:none;">
					<table width="100%" height="146" border="0" cellpadding="0" cellspacing="0" >
						<tr id="solverTypeBody" style="border-left:1px solid #e5e5e5; border-right:1px solid #e5e5e5;">
						</tr>
					</table>
				</div>
			</div>
			
			<div class="h20"></div>
				
			<form method="post" name="searchParamForm" style="margin:0px;" onsubmit="return false;">
				<input type="hidden" id="<portlet:namespace/>groupId" 			name="<portlet:namespace/>groupId"						value=""/>
				<input type="hidden" id="<portlet:namespace/>p_curPage" 		name="<portlet:namespace/>p_curPage" 					value="${params.p_curPage}"/>
				<input type="hidden" id="<portlet:namespace/>categoryId"		name="<portlet:namespace/>categoryId"					 value="${params.categoryId}"/>
				
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left" style="width: 50%;">
					</h3>
					
					<div class="input-group">
						<select id="<portlet:namespace/>linePerPage" name="<portlet:namespace/>linePerPage" onchange="<portlet:namespace/>dataSearchList()" class="form-control" style="width: 25%;">
							<option value="10" <c:if test="${params.linePerPage == '10' }"> selected="selected"</c:if> >10<liferay-ui:message key='edison-search-views' /></option>
							<option value="20" <c:if test="${params.linePerPage == '20' }"> selected="selected"</c:if>>20<liferay-ui:message key='edison-search-views' /></option>
							<option value="30" <c:if test="${params.linePerPage == '30' }"> selected="selected"</c:if>>30<liferay-ui:message key='edison-search-views' /></option>
							<option value="40" <c:if test="${params.linePerPage == '40' }"> selected="selected"</c:if>>40<liferay-ui:message key='edison-search-views' /></option>
						</select>
						<input name="<portlet:namespace/>searchValue" class="form-control" type="text" id="<portlet:namespace/>searchValue" size="40" onKeydown="if(event.keyCode ==13)<portlet:namespace/>dataSearchList();" value ="${params.searchValue}" autocomplete="off" style="width: 74%; margin-left:1%; float: right;" />
						<div class="input-group-btn"> 
							<button class="btn btn-default" id="keyWordB" type="button">
								<i class="icon-search"></i>
							</button>
							<button class="btn btn-default" type="button" id="initB">Clear</button>
						</div>
					</div>
				</div>
			</form>
		
			 <!-- sw 선택 리스트 ---->
			<table  width="100%"  border="0" cellspacing="0" cellpadding="0" class="table1_list table-bordered table-hover edison-table">
				<colgroup>
					<col width="70" />
					<col width="70" />
					<col width="*" />
					<col width="70" />
					<col width="120" />
					<col width="100" />
					<col width="150" />
					<col width="100" />
					<col width="100" />
				</colgroup>
				
				<thead>
					<tr>
						<th scope="col"><liferay-ui:message key="edison-table-list-header-index" /></th> 		<!-- 순번 -->
						<th scope="col" colspan="2"><liferay-ui:message key="edison-table-list-header-app-title" />(<liferay-ui:message key="edison-appstore-solver-name" />)</th> 		<!-- SW 명 --> 
						<th scope="col"><liferay-ui:message key="edison-table-list-header-version" /></th>			<!-- 버전 --> 
						<th scope="col"><liferay-ui:message key="edison-table-list-header-orgNm" /></th> 		<!-- 기관명 -->
						<th scope="col"><liferay-ui:message key='edison-table-list-header-userid' /></th>								<!-- 개발자 -->
						<th scope="col"><liferay-ui:message key="edison-table-list-header-date" /></th>		<!-- 등록일시 -->
						<th scope="col"><liferay-ui:message key="edison-table-list-header-manual" /></th>		<!-- 매뉴얼 -->
						<th scope="col"><liferay-ui:message key="edison-table-list-header-run" /></th>		<!-- 실행 -->
					</tr>
				</thead>
				<tbody id="<portlet:namespace/>summaryListBody">
				</tbody>
		  </table>
		
		<div id="pageListDiv" class="text-center"></div>
		
	</div>
</body>
<script>
	/* 선택한 Tab Id */
	var selectedTabId = "";
	
	$(document).ready(function(){
		
		(function(jQuery) {
			jQuery.fn.<portlet:namespace/>clickoutside = function(callback) {
				var outside = 1, self = $(this);
					self.cb = callback;
					this.click(function() {
						outside = 0;
					});
				$(document).click(function() {
					outside && self.cb();
					outside = 1;
					});
				return $(this);
			}
		})(jQuery);
		
		selectedTabId = "<%=visitSite%>";
		var searchField = "<%=searchField%>";
		$("#<portlet:namespace/>searchValue").val(searchField);
		<portlet:namespace/>dataSearchList("${params.p_curPage}");
		solverTypeList();
	});
	
	/* liferay-ui 탭 이벤트 return Script */
	function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
		window.location.href = "<%= saveClickTabURL.toString() %>"+"&<portlet:namespace/>groupId=" + value;
 	}
	
	/* 필터 조회 */
	function solverTypeList(){
		var searchData = {
				"<portlet:namespace/>groupId":selectedTabId
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
										$("<div/>").css("text-align","center").append(
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
										$("<div/>").append(
														   $("<img/>").attr("src", "${contextPath}/images/solverType/" + dataMap[i].image.value + ".png").css("margin", "15px 0px 0px 0px").css("height", "76px").css("max-width", "100px")
										  )
								  ).append(
										   $("<div/>").css("height", "55px").css("margin", "0 auto")
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
			},error:function(e){
			}
		});
	}
	
	function <portlet:namespace/>dataSearchList(p_curPage){
		if(p_curPage == null || p_curPage == 0){
			p_curPage = "1";
		}
		
		var currentTabGroupId = <%=visitSite%>;
		
		document.searchParamForm.<portlet:namespace/>groupId.value = selectedTabId;
		document.searchParamForm.<portlet:namespace/>p_curPage.value = p_curPage;
		var searchForm = $("form[name=searchParamForm]").serialize();
		
		jQuery.ajax({
			type: "POST",
			url: "<%=resorceSearchURL%>",
			data: searchForm,
			success: function(msg) {
				var dataMap = eval("(" + msg + ")");
				var vSummaryListBody =  document.getElementById("<portlet:namespace/>summaryListBody");
				var vRow, vCell;
				
				$("#<portlet:namespace/>summaryListBody tr:not(:has(#1))").remove();
				
				if(dataMap.dataList.length==0){
					$vRow = $("<tr/>");
					$("<td/>").attr("colSpan","9")
							  .html("<p><liferay-ui:message key='edison-there-are-no-data' /></p>")
							  .css("textAlign","center")
							  .appendTo($vRow);
					
					$("#<portlet:namespace/>summaryListBody").append($vRow);
				}else{
					var pageNum = dataMap.totalCnt - (p_curPage-1) * dataMap.select_line;
					
					for(var i=0; i<dataMap.dataList.length;i++){
						
						$vRow = $("<tr/>");
						
						$("<td/>").text(pageNum--).addClass("TC").appendTo($vRow);
						
						var vSolverIconId = 0;
						var vSolverImageSrc = "";
						vSolverIconId = dataMap.dataList[i].iconId;
						
						if(typeof vSolverIconId != "undefined"){
							vSolverImageSrc = spaceDelete("/documents/"+dataMap.dataList[i].iconRepositoryId+"/"+dataMap.dataList[i].iconUuid); 
						}else{
							vSolverImageSrc = "${contextPath}/images/scienceappstorelist/sc_appbox.jpg";
						}
						
						$("<td/>").addClass("TC").append(
							$("<img/>").attr("src",vSolverImageSrc)
									   .attr("onerror","this.src='${contextPath}/images/scienceappstorelist/sc_appbox.jpg'")
									   .css("width","33px")
									   .css("height","27px")
									   .attr("alt","Icon")
						).appendTo($vRow);

						$tdRow = $("<td/>").css("word-break", "break-all")
										   .css("text-align","left")
										   .html(dataMap.dataList[i].name+"<br/>"+dataMap.dataList[i].title)
										   .attr("onclick","<portlet:namespace/>moveScienceAppDetail('"+currentTabGroupId+"', '"+dataMap.dataList[i].scienceAppId+"')")
										   .css("cursor", "pointer")
						$tdRow.appendTo($vRow);
						
						$("<td/>").text(dataMap.dataList[i].version).addClass("TC").appendTo($vRow);
						$("<td/>").text(dataMap.dataList[i].affiliation).addClass("TC").appendTo($vRow);
						
						$("<td/>").text(dataMap.dataList[i].screenName).addClass("TC").appendTo($vRow);
						
						var modifiedDate = new Date(dataMap.dateList[i]);
						$("<td/>").text( $.format.date(modifiedDate, "yyyy-MM-dd") ).addClass("TC").attr("stateDate", new Date(dataMap.dataList[i].statusDate)).appendTo($vRow);
	
						
						/* 메뉴얼 */
						if(typeof dataMap.dataList[i].manualId != "number"){
							$("<td/>").css("text-align","center").append(
								$("<img/>").attr("align","center")
										   .attr("id","manualLinkBtn")
										   .attr("src","${contextPath}/images/btn_manual_none.jpg")
										   .css("height", "28px")
										   .css("cursor","default")
							).appendTo($vRow);
						}else{
							$("<td/>").css("text-align","center").append(
									$("<img/>").attr("align","center")
											   .attr("id","manualLinkBtn")
											   .attr("src","${contextPath}/images/btn_manual.jpg")
											   .attr("onclick", "<portlet:namespace/>fileDownload('" + dataMap.dataList[i].manualId + "')")
											   .css("height", "28px")
											   .css("cursor", "pointer")
											   .hover(
												  function(){
												  	$(this).attr("src","${contextPath}/images/btn_manual.jpg");
												  }
												)
								).appendTo($vRow);
						}
						
						/* 실행 */
						if(dataMap.dataList[i].openLevel != "DOWNLOAD_ONLY" && dataMap.dataList[i].appType == "Solver" && ${workBenchPlid} != 0){
							$("<td/>").css("text-align","center").append(
									$("<img/>").attr("src","${contextPath}/images/btn_run.jpg")
												.attr("id","manualLinkBtn")
												.attr("onClick", "<portlet:namespace/>moveWorkbenchFromList('" + dataMap.dataList[i].scienceAppId + "')")
												.css("height", "28px")
												.css("cursor","pointer")
												.css("vertical-align","middle")
												.hover(
												  function(){
												  	$(this).attr("src","${contextPath}/images/btn_run.jpg");
												  }
												)
							).appendTo($vRow);
						} else if(dataMap.dataList[i].openLevel == "DOWNLOAD_ONLY"){
							$("<td/>").css("text-align","center").append(
									$("<img/>").attr("src","${contextPath}/images/download_btn.gif")
												  .attr("id","manualLinkBtn")
												  .attr("onClick", "<portlet:namespace/>fileDownload('"+dataMap.dataList[i].srcFileName+"')")
												  .css("height", "28px")
												  .css("cursor","pointer")
							).appendTo($vRow);
						}else {
							$("<td/>").css("text-align","center").appendTo($vRow);
						}
						
						
						$("#<portlet:namespace/>summaryListBody").append($vRow);
					}
					
					
					/* 즐겨 찿기 솔버 추가 후에 검색한 솔버가 없을 경우 no-data 표시 */
					if(dataMap.totalCnt==0){
						$vRow = $("<tr/>");
						$("<td/>").attr("colSpan","9")
								  .html("<p><liferay-ui:message key='edison-there-are-no-data' /></p>")
								  .css("textAlign","center")
								  .appendTo($vRow);
						
						$("#<portlet:namespace/>summaryListBody").append($vRow);
					}
					
				}
				/* 페이징 초기화pageListDiv */
				document.getElementById("pageListDiv").innerHTML = dataMap.pageList;
			},
			error:function(msg){
				alert("System Exception : " + msg);
			},complete: function(){
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
		var searchForm = document.searchParamForm;
		var categoryIdValue = searchForm.<portlet:namespace/>categoryId;
		categoryIdValue.value		=	categoryId;
		<portlet:namespace/>dataSearchList();
	}
	
	filter = function(){
		$("#<portlet:namespace/>dialog-message").dialog("open");
	};
	
	
	/* 초기화 */
	 function filterInit (){
		
		var searchForm = document.searchParamForm;
		var searchValue = searchForm.<portlet:namespace/>searchValue;
		var categoryId = searchForm.<portlet:namespace/>categoryId;
		
		
		searchValue.value 			=	"";
		categoryId.value			=	"";
		
		$("#<portlet:namespace/>linePerPage").find('option:first').attr('selected', 'selected');
		
		if("${parentChildTab}" == 0) {
			$(".onClass").removeClass("onClass");
		} else {
			$(".onClass2").removeClass("onClass2");
		}
		
		/* form 초기화 적용 */
		searchForm.submit();
	};
	
	
	/* 검색 */
	$(document).on( "click", "#keyWordB", function(){
		<portlet:namespace/>dataSearchList();
	});
	
	/* 전체보기(clear) */
	$(document).on( "click", "#initB", function(){
		filterInit();
	});
	
	
	$(function() {
		/* 매뉴얼 Event */
		$("img[id=manualLinkBtn]").on("click", function(){
			var url = spaceDelete($(this).attr("manual-link"));
			window.open(url,"_blank");
		}),
		/* 실행 Event */
		$("img[id=exeLinkBtn]").on("click", function(){
			var form = document.form;
			form.id.value = $(this).attr("exe-id");
			if(loginDefaultUserStatus=="true"){
				alert('<liferay-ui:message key="edison-simulation-please-login" />');
				form.action = "<%=redirectSolverExeURL%>";
			}
			form.submit();
		}),
		/* 세부화면 */
		$("#simulationView").on("click",function(){
			detailView('goView', $(this).attr("data-id"));
		}),
		
		/* 문제필터 */
		$("#configFilter").find("td").on("click",function(event){
			alert("click configFilter")
			if(!$(event.target).is("input")){
				if($(this).has(":input").length>0){
					var checkbox = $(':checkbox', $(this)).get(0);
					var checked = checkbox.checked;
					if (checked == false) checkbox.checked = true;
					else checkbox.checked = false;
				}
			}
		});
		
	});

	$(document).on( "click", "#configFilter td", function(event){
		if(!$(event.target).is("input")){
			if($(this).has(":input").length>0){
				var checkbox = $(':checkbox', $(this)).get(0);
				var checked = checkbox.checked;
				if (checked == false){ checkbox.checked = true;}
				else checkbox.checked = false;
			}
		}
	});
	
	/* Manual Download */
	function <portlet:namespace/>fileDownload(p_fileEntryId){
		location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
	}
	
	/* scienceApp 상세보기 */
	function <portlet:namespace/>moveScienceAppDetail(groupId, scienceAppId) {
		var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
		var params = "&" + thisPortletNamespace + "solverId=" + scienceAppId;
		params += "&" + thisPortletNamespace + "groupId=" + groupId;
		location.href = "<%=scienceAppDetailUrl%>" + params;
	}
	
	function <portlet:namespace/>moveWorkbenchFromList(targetScienceAppId) {
		var isSignedIn = ${isSignedIn};
		if(isSignedIn){
			
			// Site Member Check
			jQuery.ajax({
				type: "POST",
				url: "<%=isSiteMemberURL%>",
				success: function(msg) {
					var isSiteMember = msg.isSiteMember;
					if(isSiteMember){
						var URL = "<%=workbenchURL%>";
						URL += "&_SimulationWorkbench_WAR_OSPWorkbenchportlet_scienceAppId="+targetScienceAppId;
						window.open(URL);
					} else {
						// Site Member가 아닌 경우 사이트 가입 여부 Confirm
						if(confirm("<liferay-ui:message key='edison-default-site-no-user' />"+"\n"+"<liferay-ui:message key='edison-default-site-join-regist-confirm' />")){
							
							var URL = "<%=themeDisplay.getPortalURL()%>";
							URL += "/my-edison?";
							URL +=	"p_p_id=edisonmypage_WAR_edisondefault2016portlet";
							URL +=	"&_edisonmypage_WAR_edisondefault2016portlet_clickTab=siteJoin";
							window.open(URL, "_self"); 
						}
					}
				},error:function(msg,e){ 
					alert(e);
					return false;
				}
			});
			
		} else {
			window.open("${signedInUrl}", "_self");
		}
	}
</script>

<aui:script>
function <portlet:namespace/>moveSimulation(solverId, groupId) {
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPlid("${simulationPlid}");
		portletURL.setPortletId("edisonbestsimulation_WAR_edisonsimulationportlet"); 
		portletURL.setParameter("directGroupId", groupId);
		portletURL.setParameter("directScienceAppId", solverId);
		window.location.href = portletURL.toString();
	});
}

</aui:script>