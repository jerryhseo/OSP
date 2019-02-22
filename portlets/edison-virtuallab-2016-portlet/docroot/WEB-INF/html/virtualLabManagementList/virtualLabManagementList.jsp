<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>

<liferay-portlet:resourceURL var="virtualLabManagementListURL" id="virtualLabManagementList" copyCurrentRenderParameters="false" />

<liferay-portlet:resourceURL var="virtualLabManagementTabListURL" id="virtualLabManagementTabList" copyCurrentRenderParameters="false" />

<liferay-portlet:resourceURL var="checkSiteRoleURL" id="checkSiteRole" copyCurrentRenderParameters="false" />

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

<liferay-portlet:resourceURL secure="<%= request.isSecure() %>" var="userSiteJoinURL" id="userSiteJoin" copyCurrentRenderParameters="false" />

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

#<portlet:namespace/>leftOrganList .course_warp{
	width: 100%;
}

@media all and (min-width: 990px){
	#<portlet:namespace/>leftOrganList{
		padding: 0px;
	}
}

#<portlet:namespace/>virtualLabList ul{
	margin: 0px;
}

	/* 2018.11.15 ADD CSS (Modify UI) */
	#<portlet:namespace/>leftOrganList .<portlet:namespace/>category_tab_td.choose{
		background-color: #eee;
		font-weight: bold;
	}

	div.<portlet:namespace/>category_tab_td{
		cursor: pointer;
		padding-top: 5px;
		padding-bottom: 5px;
		font-size: 15px;
	}
	
	.<portlet:namespace/>virtual-lab-box{
		border : solid 1px #e0e0e0;
		border-radius : 5px;
		box-shadow : 1px 1px 0px #d0d0d0;
		padding : 3px;
		width : 100%;
		cursor: pointer;
	}
	
	.<portlet:namespace/>virtual-lab-content{
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}
	
	.<portlet:namespace/>virtual-lab-content.title{
		font-size: 19px;
		color: #0084ad;
		line-height: 2.0em;
	}
	
	.<portlet:namespace/>virtual-lab-content.info{
		font-size: 13px;
		color: #647f8d;
		font-weight: 500;
		line-height: 2.0em;
	}
	
	#<portlet:namespace/>virtualLabSearchBtnGroup .form-group{
		display: table;
		width: 100%;
	}
	
	@media (max-width: 979px){
		#<portlet:namespace/>virtualLabSearchBtnGroup button{
			padding: 5px 12px !important;
		}
	}
	
	@media (max-width: 768px){
		#<portlet:namespace/>virtualLabSearchBtnGroup div.input-group{
			display: block;
		}
		#<portlet:namespace/>virtualLabSearchBtnGroup div.input-group > div:first{
			width: 70%;
		}
		#<portlet:namespace/>virtualLabSearchBtnGroup div.input-group > div:second{
			width: 30%;
		}
	}

</style>

<link media="all" rel="stylesheet" href="${contextPath}/css/jquery-confirm.css" />
<script src="${contextPath}/js/jquery-confirm.min.js"></script>

<body>
	<div style="border-top: 1px solid #e5e5e9;">
		<div class="h40"></div>
		<div class="container">
			<div class="col-md-6" style="padding-left: 10px;">
				<h2>
					<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
					<span class="subtitlearea">
						<liferay-ui:message key='edison-course-list' />
					</span>
				</h2>
			</div>
			
			<div id="<portlet:namespace/>virtualLabSearchBtnGroup" class="col-md-6" style="padding-top: 20px; padding-right: 10px;">
					<div class="input-group">
						<div style="padding: 0px;">
							<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
								<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1"/>
								<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${groupId }"/>
								<input id="<portlet:namespace/>universityField" name="<portlet:namespace/>universityField" type="hidden" />
								<input type="text" class="form-control" id="<portlet:namespace/>search_parameter" name="<portlet:namespace/>search_parameter" style="float: right; margin-left: 1%;" maxlength="15" placeholder="<liferay-ui:message key='edison-virtuallab-placeholder' />" onkeypress="<portlet:namespace/>onKeyDown(event);"/>
								
								<c:if test="${isLogin == 'N' }">
									<!-- 2018.04.11. Logout 상태에서도 수강신청 중인 강좌목록 출력 -->
									<input type="hidden" name="<portlet:namespace/>searchType" id="<portlet:namespace/>searchType" value="${searchType}" />
								</c:if>
							</form>
						</div>
						
						<div class="input-group-btn" style="padding: 0px;">
							<div class="btn-group" role="group">
								<div class="dropdown dropdown-lg">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="caret"></span></button>
									<div class="dropdown-menu dropdown-menu-right" role="menu" style="width: 350px; padding: 6px 20px;">
										<form role="form" name="searchForm" id="<portlet:namespace/>dropdownForm">
											<!-- Course registration -->
											<div class="form-group" style="margin-top: 15px;">
												<c:if test="${isLogin == 'Y' }">
													<label for="filter">
														<i class="icon-search"></i>&nbsp;
														<liferay-ui:message key='edison-virtuallab-course-status' />
													</label>
													<select name="<portlet:namespace/>searchType" id="<portlet:namespace/>searchType" class="form-control" onChange="<portlet:namespace/>dataAndTabSearchList('1','',0)" style="border-radius: 4px; margin: 5px 0px;">
														<option value="ALL" ><liferay-ui:message key='full' /></option>
														<option value="addClass" <c:if test="${searchType == 'addClass' }"> selected</c:if> ><liferay-ui:message key='edison-course-virtualLabClassRegistrationList-registration-available' /></option>
														<option value="attending" <c:if test="${searchType == 'attending' }"> selected</c:if> ><liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-take-class' /></option>
													</select>
												</c:if>
											</div>
											
											<!-- University -->
											<div class="form-group">
												<c:choose>
													<c:when test="${not empty tabsValues}">
														<label for="filter">
															<i class="icon-search"></i>&nbsp;
															<liferay-ui:message key='edison-search-applied-disciplines' />
														</label>
														<select class="form-control" id="<portlet:namespace/>disciplinesTabList" style="border-radius: 4px; margin: 5px 0px;">
															<option value="">
																<liferay-ui:message key='edison-button-all-search' />
															</option>
															<c:forEach var="tablist" items="${tablist}">
																<option value="${tablist.groupId}">
																	<liferay-ui:message key='${tablist.groupName}' />
																</option>
															</c:forEach>
														</select>
													</c:when>
													<c:otherwise>
														<label for="filter">
															<i class="icon-search"></i>&nbsp;
															<liferay-ui:message key='edison-create-account-field-title-university' />
														</label>
														<select class="form-control" id="<portlet:namespace/>universityTabList" style="border-radius: 4px; margin: 5px 0px;">
														</select>
													</c:otherwise>
												</c:choose>
											</div>
											
											<!-- Item Count -->
											<div class="form-group">
												<label for="filter">
													<i class="icon-search"></i>&nbsp;
													<liferay-ui:message key='edison-table-list-header-views' />
												</label>
												<select id="<portlet:namespace/>selectsearch_groupIdsearch_groupId_line" class="form-control" name="<portlet:namespace/>select_line" onchange="<portlet:namespace/>dataAndTabSearchList('1','')" style="border-radius: 4px; margin: 5px 0px;">
													<option value="10">10<liferay-ui:message key='edison-search-views' /></option>
													<option value="20">20<liferay-ui:message key='edison-search-views' /></option>
													<option value="30">30<liferay-ui:message key='edison-search-views' /></option>
													<option value="40">40<liferay-ui:message key='edison-search-views' /></option>
												</select>
											</div>
										</form>	
									</div>
								</div>
							</div>
							
							<button class="btn btn-primary" style="z-index: 0" onClick="<portlet:namespace/>dataAndTabSearchList('1','');">
								<i class="icon-search"></i>
							</button>
							<button class="btn btn-default" style="z-index: 0" onClick="<portlet:namespace/>initDataSearchList();">Clear</button>
						</div>
					</div>
				
			</div>
			
			<div class="h10" style="border-bottom:1px solid #efefef;"></div>
			
			<div id="courselist_no">
				<!-- 강좌 리스트 -->
				<div id="<portlet:namespace/>virtualLabList" class=""></div>
				
				<div class="h20"></div>
				
				<div class="table-responsive panel edison-panel table1_list ">
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
	/* $("#<portlet:namespace/>searchType").val("ALL"); */
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
	
	$("div.dropdown.open").removeClass("open");
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
	if(groupId==''){
		$("#<portlet:namespace/>tabId_ALL").addClass("choose");
	} else {
		$("#<portlet:namespace/>tabId_"+groupId).addClass("choose");
	}
	$("#<portlet:namespace/>univTabId_"+universityField).addClass("choose");
	
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
			$("#<portlet:namespace/>virtualLabList").html("");
			
			if(virtualLabManagementList.length == 0) {
				$("#<portlet:namespace/>virtualLabList").css("text-align", "center").text("<liferay-ui:message key='edison-there-are-no-data' />");
			} else {
				
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
					}else if(virtualLabManagementList[i].groupName == 'CEM'){
						groupField = "<liferay-ui:message key='edison-course-CEM' />";
						groupClass = "label_cem";
						siteClass = "cem";
					}
					
					virtualLab = $("<div/>").addClass("<portlet:namespace/>virtual-lab col-md-3 col-sm-6 col-xs-12")
											.css("margin-top", "10px").css("margin-bottom", "10px")
											.css("padding-left", "10px").css("padding-right", "10px");
					
					virtualLabBox = $("<div/>").addClass("<portlet:namespace/>virtual-lab-box onHover")
											.attr("title", virtualLabManagementList[i].virtualLabTitle)
											.attr("onClick","<portlet:namespace/>moveVirtualLab('" + virtualLabManagementList[i].virtualLabId  +"', '"+ virtualLabManagementList[i].groupId +  "')");
					
					$("<div/>").css("text-align", "center").css("margin-top", "20px")
							.append($("<img/>").attr("src", "${contextPath}/images/edu_"+siteClass+".png?imageThumbnail=2")
												.attr("width", "86")
												.attr("height", "86"))
							.appendTo(virtualLabBox);
					
					$("<div/>").addClass("<portlet:namespace/>virtual-lab-content title")
								.css("width", "100%")
								.css("text-align", "center")
								.css("margin-bottom", "5px")
								.css("padding", "0px 15px")
								.text(virtualLabManagementList[i].virtualLabTitle)
								.appendTo(virtualLabBox);
					
					var textDiv = $("<div/>").css("padding", "7px 20px 6px").css("border-top", "1px solid #e7e7e7")
											.css("background-color", "#f9f9f9");
					
					$("<div/>").addClass("<portlet:namespace/>virtual-lab-content info")
								.css("width", "100%")
								.css("text-align", "left")
								.text(" " + virtualLabManagementList[i].virtualLabUniversityName)
								.appendTo(textDiv);
					$("<div/>").addClass("<portlet:namespace/>virtual-lab-content info")
								.css("width", "100%")
								.css("text-align", "left")
								.text("Prof. " + virtualLabManagementList[i].virtualLabPersonName)
								.appendTo(textDiv);
					
					/* 분야 */
					$("<div/>").append($("<input/>").addClass(siteClass+"box")
								.attr("type", "button").val(groupField))
								.css("position", "absolute")
								.css("top", "10px")
								.css("right", "25px")
								.appendTo(virtualLabBox);
								/* .css("line-height", "2.0em")
								.appendTo(textDiv); */
					
					textDiv.appendTo(virtualLabBox);
					
					virtualLabBox.appendTo(virtualLab);
					$("#<portlet:namespace/>virtualLabList").append(virtualLab);
					
				}
			}
			
			$("#<portlet:namespace/>pageListDiv").html(pageList);
			
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

$("#<portlet:namespace/>disciplinesTabList").change(function(){
	var groupId = $(this).val();
	
	if(groupId != ''){
		<portlet:namespace/>dataAndTabSearchList('1', groupId);
	} else {
		<portlet:namespace/>allCategoryDataSearchList();
	}
});

$("#<portlet:namespace/>universityTabList").change(function(){
	var virtualLabUniversityField = $(this).val();
	var groupId = $(this).attr("groupId");
	
	if(virtualLabUniversityField != ''){
		<portlet:namespace/>dataSearchList('1', groupId, virtualLabUniversityField); 
	} else {
		<portlet:namespace/>allCategoryDataSearchList();
	}
});

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
			
			$("#<portlet:namespace/>virtualLabTabListBody").html("");
			$("#<portlet:namespace/>universityTabList").html("");
			
			
			$("#courselist").removeClass("courselist");
			$("#courselist").removeClass("noncourselist");
			
			if(universityTablist.length != 0) {
				$("<option/>").attr("value", "")
								.attr("groupId", "")
								.text("<liferay-ui:message key='edison-button-all-search' />")
								.appendTo($("#<portlet:namespace/>universityTabList"));
				
				for(var i = 0; i < universityTablist.length; i++) {
					$("<div/>").addClass("col-md-12 <portlet:namespace/>category_tab_td onHover").attr("id", "<portlet:namespace/>univTabId_"+universityTablist[i].virtualLabUniversityField )
								.attr("onclick", "<portlet:namespace/>dataSearchList('1', '" + groupId + "', '" + universityTablist[i].virtualLabUniversityField +"');")
								.text(universityTablist[i].virtualLabUniversityName +"(" + universityTablist[i].count  + ")" ).appendTo($("#<portlet:namespace/>virtualLabTabListBody"));
					
					$("<option/>").attr("value", universityTablist[i].virtualLabUniversityField)
									.attr("groupId", groupId)
									.text(universityTablist[i].virtualLabUniversityName +"(" + universityTablist[i].count  + ")" )
									.appendTo($("#<portlet:namespace/>universityTabList"));
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
		
		/* 사이트 권한에 따른 VirtualLab 열람 */
		isSiteMember = <portlet:namespace/>checkSiteRole(groupId);
		var virtualLabManagementDetailURL = "<%=virtualLabManagementDetailURL%>"
		var URL = virtualLabManagementDetailURL + "&<portlet:namespace/>virtualLabId=" + virtualLabId+"&<portlet:namespace/>groupId="+groupId;
		if(isSiteMember){
			window.location.href = URL;
		} else {
			successedJoin = false;
			/* content = $("<div/>").html(Liferay.Language.get('edison-virtuallab-join-message') + "<br/>" + Liferay.Language.get('edison-default-site-join-regist-confirm'))
								.css("font-size", "15px"); */
			content = $("<div/>").html("<liferay-ui:message key='edison-virtuallab-join-message' />" + "<br/>" + "<liferay-ui:message key='edison-default-site-join-regist-confirm' />")
								.css("font-size", "15px");
			
			$.confirm({
				title : "<liferay-ui:message key='join-site' />",
				content: content,
				columnClass: 'col-md-5 col-md-offset-4',
				buttons: {
					OK : {
						text: 'JOIN',
						action: function(){
							successedJoin = <portlet:namespace/>siteJoin(groupId);
							
							if(successedJoin){
								window.location.href = URL;
								
								$.alert({
									title: "Successed",
									content: Liferay.Language.get("your-membership-has-been-approved")
								});
								
							} else {
								$.alert({
									title: "Failed",
									content: Liferay.Language.get("edison-data-insert-error")
								});
							}
						}
					},
					close : function (){
					}
				}
			});
			
		}
	} else {
		window.location.href = "<%=themeDisplay.getURLSignIn()%>";
	}
}

/* VirtualLab의 사이트 권한 확인 */
function <portlet:namespace/>checkSiteRole(groupId) {
	
	var isSiteMember = false;
	
	jQuery.ajax({
		type: "POST",
		url: "<%=checkSiteRoleURL%>",
		async : false,
		data : {"<portlet:namespace/>groupId":groupId},
		success: function(data) {
			isSiteMember = data.isSiteMember;
		},error:function(data,e){ 
			alert(e);
			isSiteMember = false;
		}
	});
	
	return isSiteMember;
}

function <portlet:namespace/>siteJoin(groupId){
	
	var successedJoin = false;
	var sendData = {
			"<portlet:namespace/>groupId":groupId,
			"<portlet:namespace/>addUserIds":"${userInfomation.userId}"
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=userSiteJoinURL%>",
		async : false,
		data : sendData,
		success: function(data) {
			isSiteMember = data.isSiteMember;
			
			if(isSiteMember){
				successedJoin = true;
			} else {
				successedJoin = false;
			}
		},error:function(data,e){ 
			alert(Liferay.Language.get("edison-data-insert-error"));
		}
	});
	
	return successedJoin;
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
