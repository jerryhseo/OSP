<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>

<liferay-portlet:resourceURL var="virtualLabManagementListURL" id="virtualLabManagementList" copyCurrentRenderParameters="false" />

<liferay-portlet:resourceURL var="virtualLabManagementTabListURL" id="virtualLabManagementTabList" copyCurrentRenderParameters="false" />
<liferay-portlet:renderURL var="virtualLabManagementDetailURL">
	<liferay-portlet:param name="myRender" value="virtualLabManagementDetail" />
	<portlet:param name="edionCopyParam" value="true" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL 
	portletName="edisonorgcodesearch_WAR_edisondefault2016portlet" 
	portletMode="view"
	windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="syscommoncdURL">
	<liferay-portlet:param name="up_code" value="1501"/>
	<liferay-portlet:param name="com_search_type" value="orgSearch"/>
	<liferay-portlet:param name="popup_title" value="edison-org-code-search"/>
	<liferay-portlet:param name="universityFieldNm" value="virtualLabUniversityField"/>
	<liferay-portlet:param name="universityField" value="universityField"/>
</liferay-portlet:renderURL>

<liferay-portlet:actionURL var="saveClickTabURL" portletMode="view" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="saveClickTab"/>
</liferay-portlet:actionURL>

<style type="text/css">
	.onHover:hover {
		background:#e0e0e0;
	}
	.backHover1:hover {
		background:#e0e0e0;
		text-decoration: underline;
		font-weight: 600;
	}
	.backHover2:hover {
		background:#e0e0e0;
		text-decoration: underline;
		font-weight: 600;
	}
	.backHover1 {
		background-color:#f8fafe;
	}
	
	.course_card_category .choose{
	background-color: #eee;
	font-weight: bold;
	}
	.virtualLabtype{
		float : left;
		margin-top: 10px;
		margin-right: 3px;
	}
.subtitlearea{
	margin-left: 10px;
}
</style>

<body>
	<div style="border-top: 1px solid #e5e5e9;">
		<div class="h40"></div>
		<div class="container">
			<h2>
				<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
				<span class="subtitlearea">
					<liferay-ui:message key='edison-course-list' />
				</span>
			</h2>
			
			<c:choose>
				<c:when test="${not empty tabsValues}">
					<div class="course_warp">
						<div class="course_card_category">
							<table id="<portlet:namespace/>tabtable" class="tabtable">
								<thead>
									<tr class="tabtable_tr">
										<td class="category_tab_td" >
											<a style="text-decoration: none;" onClick="<portlet:namespace/>allCategoryDataSearchList()"  ><p class="category_header"><liferay-ui:message key='edison-button-all-search' /></p></a>
										</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="tablist" items="${tablist }"> 
										<tr>
											<td class="category_tab_td" id="tabId_${tablist.groupId }">
												<a style="text-decoration: none;" onclick="<portlet:namespace/>dataAndTabSearchList('1','${tablist.groupId }');" ><p class="category_list"><liferay-ui:message key='${tablist.groupName }' /></p></a>
											</td>
										</tr>
									</c:forEach> 
					            </tbody>
							</table>
						</div>
						
						<div id="<portlet:namespace/>virtualLabTabListBody">
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="course_warp">
						<div id="<portlet:namespace/>virtualLabTabListBody">
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			
			<div id="courselist" class="courselist">
			
				<div class="portlet-borderless-container">
					<div class="portlet-body">
						<div style="float: left; width: 100%" class="table-responsive panel edison-panel">
							<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
								<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1"/>
								<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${groupId }"/>
								<input id="<portlet:namespace/>universityField" name="<portlet:namespace/>universityField" type="hidden" />
								
								<div class="input-group">
									<input type="text" class="form-control" id="<portlet:namespace/>search_parameter" name="<portlet:namespace/>search_parameter" style="width: 50%; float: right; margin-left: 1%;" maxlength="15" placeholder="<liferay-ui:message key='edison-virtuallab-placeholder' />" onkeypress="<portlet:namespace/>onKeyDown(event);"/>
									
									<select id="<portlet:namespace/>selectsearch_groupIdsearch_groupId_line" class="form-control" name="<portlet:namespace/>select_line" onchange="<portlet:namespace/>dataAndTabSearchList('1','')" style="line-height: 15px; width: 15%; float: right;">
										<option value="10">10<liferay-ui:message key='edison-search-views' /></option>
										<option value="20">20<liferay-ui:message key='edison-search-views' /></option>
										<option value="30">30<liferay-ui:message key='edison-search-views' /></option>
										<option value="40">40<liferay-ui:message key='edison-search-views' /></option>
									</select>
									<c:if test="${isLogin == 'Y' }">
										<select name="<portlet:namespace/>searchType" id="<portlet:namespace/>searchType" class="form-control" onChange="<portlet:namespace/>dataAndTabSearchList('1','',0)" style="width:15%; float: right;">
											<option value="ALL" ><liferay-ui:message key='full' /></option>
											<option value="addClass" <c:if test="${searchType == 'addClass' }"> selected</c:if> ><liferay-ui:message key='edison-course-virtualLabClassRegistrationList-registration-available' /></option>
											<option value="attending" <c:if test="${searchType == 'attending' }"> selected</c:if> ><liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-take-class' /></option>
										</select>
									</c:if>
									<c:if test="${isLogin == 'N' }">
										<!-- 2018.04.11. Logout 상태에서도 수강신청 중인 강좌목록 출력 -->
										<input type="hidden" name="<portlet:namespace/>searchType" id="<portlet:namespace/>searchType" value="${searchType}" />
									</c:if>
									
									<div class="input-group-btn">
										<button class="btn btn-default" onClick="<portlet:namespace/>dataAndTabSearchList('1','');"><liferay-ui:message key='edison-button-search' /></button>
										<button class="btn btn-default" onClick="<portlet:namespace/>initDataSearchList();">Clear</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<!-- 강좌 리스트 -->
				<div class="table-responsive panel edison-panel table1_list">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody id="<portlet:namespace/>virtualLabListBody">
						</tbody>
					</table>
					
					<div class="h20"></div>
					<div id="<portlet:namespace/>pageListDiv" class="text-center"></div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList(1,'','');
	<portlet:namespace/>dataSearchTabList('', '');
});

function <portlet:namespace/>initDataSearchList(){
	$("#<portlet:namespace/>searchType").val("ALL");
	$("#<portlet:namespace/>search_parameter").val("");
	$("#<portlet:namespace/>groupId").val("");
	$("#<portlet:namespace/>universityField").val("");
	
	<portlet:namespace/>dataAndTabSearchList(1,'');
}

function <portlet:namespace/>allCategoryDataSearchList(){
	$("#<portlet:namespace/>groupId").val("");
	$("#<portlet:namespace/>universityField").val("");
	
	<portlet:namespace/>dataAndTabSearchList(1,'');
}

function <portlet:namespace/>dataAndTabSearchList(pageNumber, groupId){
	<portlet:namespace/>dataSearchList(pageNumber, groupId, '');
	<portlet:namespace/>dataSearchTabList(groupId, '');
}

function <portlet:namespace/>dataSearchList(pageNumber, groupId, universityField) {
	
	$("#<portlet:namespace/>cur_page").val(pageNumber);
	
	if(groupId != ""){
		$("#<portlet:namespace/>groupId").val(groupId);
	}
	
	if(universityField != ""){
		$("#<portlet:namespace/>universityField").val(universityField);
	}else{
		$("#<portlet:namespace/>universityField").val("");
	}
	
	groupId = $("#<portlet:namespace/>groupId").val();
	universityField = $("#<portlet:namespace/>universityField").val();
	
	$(".choose").removeClass("choose");
	$("#tabId_"+groupId).addClass("choose");
	$("#unitabId_"+universityField).addClass("choose");
	
	var searchForm = $("form[name=searchForm]").serialize();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabManagementListURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
			var virtualLabManagementList = msg.virtualLabManagementList;
			var curPage = msg.curPage;
			var selectLine = msg.selectLine;
			var virtualLabCount = msg.virtualLabCount - ((curPage -1) * selectLine);
			var pageList = msg.pageList;
			
			var universityTablist = msg.universityTablist;
			
			var rowResult;
			var rowUl;
			var groupField = "";
			var groupClass = "";
			var siteClass = "";
			$("#<portlet:namespace/>virtualLabListBody div:not(:has(#1))").remove();
			$("#<portlet:namespace/>virtualLabListBody tr:not(:has(#1))").remove();
			
			
			if(virtualLabManagementList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "7")
						  .css("text-align","center")
						  .css("height", "40px")
						  .text("<liferay-ui:message key='edison-there-are-no-data' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>virtualLabListBody").append($rowResult);
			} else {
				
				$rowResult = $("<div/>").addClass("boxlist");
				for(var i = 0; i < virtualLabManagementList.length; i++) {
					if(virtualLabManagementList[i].groupName == 'CFD'){
						groupField = "<liferay-ui:message key='edison-course-CFD' />";
						groupClass = "label_cfd";
						siteClass = "cfd";
					}else if(virtualLabManagementList[i].groupName == 'NANO'){
						groupField = "<liferay-ui:message key='edison-course-NANO' />";
						groupClass = "label_nano";
						siteClass = "nano";
					}else if(virtualLabManagementList[i].groupName == 'CHEM'){
						groupField = "<liferay-ui:message key='edison-course-CHEM' />";
						groupClass = "label_chem";
						siteClass = "chem";
					}else if(virtualLabManagementList[i].groupName == 'CSD'){
						groupField = "<liferay-ui:message key='edison-course-CSD' />";
						groupClass = "label_csd";
						siteClass = "csd";
					}else if(virtualLabManagementList[i].groupName == 'DESIGN'){
						groupField = "<liferay-ui:message key='edison-course-DESIGN' />";
						groupClass = "label_design";
						siteClass = "design";
					}else if(virtualLabManagementList[i].groupName == 'CMED'){
						groupField = "<liferay-ui:message key='edison-course-CMED' />";
						groupClass = "label_cmed";
						siteClass = "cmed";
					}else if(virtualLabManagementList[i].groupName == 'UE'){
						groupField = "<liferay-ui:message key='edison-course-UE' />";
						groupClass = "label_ue";
						siteClass = "ue";
					}else if(virtualLabManagementList[i].groupName == 'CEm'){
						groupField = "<liferay-ui:message key='edison-course-CEM' />";
						groupClass = "label_cem";
						siteClass = "cem";
					}
					
					if(virtualLabCount % 2 != 0){
						$rowUl = $("<ul/>").css("width","47%").css("padding","0px 8px 8px 8px").css("margin-left","10px").css("margin-right","10px").addClass("onHover").css("cursor","pointer")
										   .attr("onClick","<portlet:namespace/>moveVirtualLab('" + virtualLabManagementList[i].virtualLabId  +"', '"+ virtualLabManagementList[i].groupId +  "')")
										   .attr("title",virtualLabManagementList[i].virtualLabTitle);
						if(virtualLabManagementList[i].iconId != 0){
							$("<li/>").append(
									$("<img/>").attr("src", "/documents/" + virtualLabManagementList[i].iconRepositoryId  + "/" + virtualLabManagementList[i].iconUuid + "?imageThumbnail=2")
											   .attr("onerror","this.src='${contextPath}/images/edu_"+siteClass+".png?imageThumbnail=2'")
											   .attr("width", "100")
											   .attr("height", "98")
								 ).appendTo($rowUl);
						}else{														
							$("<li/>").append(
									$("<img/>").attr("src", "${contextPath}/images/edu_"+siteClass+".png?imageThumbnail=2")
											   .attr("width", "98")
											   .attr("height", "98")
								 ).appendTo($rowUl);
						}
													   
						$("<li/>").addClass("tit").text(virtualLabManagementList[i].virtualLabTitle).appendTo($rowUl);
						$("<li/>").text(virtualLabManagementList[i].virtualLabUniversityName  + " Prof. " + virtualLabManagementList[i].virtualLabPersonName).appendTo($rowUl);
						$("<li/>").addClass("box").css("margin","6px 5px 0 0")
								  .append(
											$("<input/>").addClass(siteClass+"box").attr("type", "button").val(groupField)
										 ).appendTo($rowUl);
						
						$rowUl.appendTo($rowResult);
						virtualLabCount--;
					}else{
						$rowUl = $("<ul/>").css("width","47%").css("padding","0px 8px 8px 8px").css("margin-left","10px").css("margin-right","10px").addClass("onHover").css("cursor","pointer")
										   .attr("onClick","<portlet:namespace/>moveVirtualLab('" + virtualLabManagementList[is].virtualLabId  +"', '"+ virtualLabManagementList[i].groupId +  "')")
										   .attr("title",virtualLabManagementList[i].virtualLabTitle);
						if(virtualLabManagementList[i].iconId != 0){
							$("<li/>").append(
									$("<img/>").attr("src", "/documents/" + virtualLabManagementList[i].iconRepositoryId  + "/" + virtualLabManagementList[i].iconUuid + "?imageThumbnail=2")
											   .attr("onerror","this.src='${contextPath}/images/edu_"+siteClass+".png?imageThumbnail=2'")
											   .attr("width", "100")
											   .attr("height", "98")
								 ).appendTo($rowUl);
						}else{
							$("<li/>").append(
									$("<img/>").attr("src", "${contextPath}/images/edu_"+siteClass+".png?imageThumbnail=2")
											   .attr("width", "98")
											   .attr("height", "98")
								 ).appendTo($rowUl);
						}
						
						$("<li/>").addClass("tit").text(virtualLabManagementList[i].virtualLabTitle).appendTo($rowUl);
						$("<li/>").text(virtualLabManagementList[i].virtualLabUniversityName  + " Prof. " + virtualLabManagementList[i].virtualLabPersonName).appendTo($rowUl);
						$("<li/>").addClass("box").css("margin","6px 5px 0 0")
								  .append(
											$("<input/>").addClass(siteClass+"box").attr("type", "button").val(groupField)
										 ).appendTo($rowUl);
						
						$rowUl.appendTo($rowResult);
						virtualLabCount--;
					}
					
					$("#<portlet:namespace/>virtualLabListBody").append($rowResult);
				}
			}
			$("#<portlet:namespace/>pageListDiv").html(pageList);
			
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>dataSearchTabList(groupId, universityField) {
	if(groupId != ""){
		$("#<portlet:namespace/>groupId").val(groupId);
	}else{
		$("#<portlet:namespace/>groupId").val("");
	}
	
	if(universityField != ""){
		$("#<portlet:namespace/>universityField").val(universityField);
	}else{
		$("#<portlet:namespace/>universityField").val("");
	}
	
	var searchForm = $("form[name=searchForm]").serialize();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabManagementTabListURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
			var universityTablist = msg.universityTablist;
			var rowTrResult;
			var rowTr;
			var tabsValues = "${tabsValues}";
			
			$("#<portlet:namespace/>virtualLabTabListBody tr:not(:has(#1))").remove();
			$("#<portlet:namespace/>virtualLabTabListBody table:not(:has(#1))").remove();
			$("#courselist").removeClass("courselist");
			$("#courselist").removeClass("noncourselist");
			
			if(universityTablist.length != 0) {
				$("#courselist").addClass("courselist");
				$rowTrResult = $("<table/>").addClass("tabtable").addClass("course_card_category");
				for(var i = 0; i < universityTablist.length; i++) {
						$rowTr = $("<tr/>").append($("<td/>").addClass("category_tab_td").attr("id", "unitabId_"+universityTablist[i].virtualLabUniversityField )
									 					 .append($("<a/>").css("text-decoration", "none").attr("onClick","<portlet:namespace/>dataSearchList('1', '" + groupId + "', '" + universityTablist[i].virtualLabUniversityField +"');")
																		  .append($("<p/>").addClass("category_list").text(universityTablist[i].virtualLabUniversityName +"(" + universityTablist[i].count  + ")" )
																  				  )
																 )
												  )
						$rowTr.appendTo($rowTrResult);
					$("#<portlet:namespace/>virtualLabTabListBody").append($rowTrResult);
				}
			}else{
				if( "" == tabsValues){
					$("#courselist").addClass("noncourselist");
				}else{
					$("#courselist").addClass("courselist");
				}
			}
			
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>moveVirtualLab(virtualLabId, groupId) {
	if("${isSignedIn}" == "true"){
		var virtualLabManagementDetailURL = "<%=virtualLabManagementDetailURL%>"
		var URL = virtualLabManagementDetailURL + "&<portlet:namespace/>virtualLabId=" + virtualLabId+"&<portlet:namespace/>groupId="+groupId;
		window.location.href = URL;
	} else {
		window.location.href = "<%=themeDisplay.getURLSignIn()%>";
	}
}

function <portlet:namespace/>onKeyDown() {
	if(event.keyCode == 13 && $("#<portlet:namespace/>search_parameter").val() != ""){
		<portlet:namespace/>dataAndTabSearchList(1,'');
	}
}

function <portlet:namespace/>checkValidation(form) {
	if (form.submitted) return false;
	if(!checkValue(form.<portlet:namespace/>virtualLabPersonName, "<liferay-ui:message key='edison-table-list-header-tutor' />", 1, 30)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>virtualLabTitle, "<liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' />", 1, 75)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>universityField, "<liferay-ui:message key='edison-create-account-field-title-university' />", 1, 10)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>virtualLabDescription, "<liferay-ui:message key='edison-table-list-header-resume' />", 1, 100)) {
		return false;
	} else {
		form.submitted = true;
		return true;
	}
	return false;
}

function <portlet:namespace/>syscommoncdPopup(){
	var URL = "<%=syscommoncdURL%>";
	w = 850;
	h = 550;		
	x = (screen.availWidth - w) / 2;
	y = (screen.availHeight - h) / 2;
	var options = "width="+w+", height="+h+", left="+x+",top="+y+",toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
	var openPop;
	if(openPop != null){
		openPop.focus();
	}else{
		openPop = window.open(URL, "syscommoncdPopup",options);
		openPop.focus();
	}
}

//liferay-ui 탭 이벤트 return Script
function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
	window.location.href = "<%= saveClickTabURL.toString() %>"+"&<portlet:namespace/>groupId=" + value;
}
</script>
