<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%-- <script type="text/javascript" src="${contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/main.js"></script>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery/jquery-ui.theme.min.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery/jquery-ui.min.css" media="screen"/> --%>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<%-- <link href="${contextPath}/css/owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/owl-carousel/owl.theme.css" rel="stylesheet" type="text/css" />
<script src="${contextPath}/js/owl-carousel/owl.carousel.min.js"></script> --%>

<%
	//Tab Setting
	String tabNames = (String)request.getAttribute("tabNames");
	String listTabValue = CustomUtil.strNull(request.getAttribute("listTabValue"));
	
 %>
<style type="text/css">
	.aui .contleftbox .contimg img{
		width: 220px;
		height: 200px;
		max-width: none; 
	}

	.aui .control-group{
		margin-bottom: 0px;
	}
	
	.aui input[type="text"],
	.aui input[type="password"],
	.aui textarea{
		margin-bottom: 0px;
	}
	
	.aui .long_field{
		width: 350px;
	}
	
	.aui .short_field{
		width: 200px;
	}
	
	.aui .edison_file{
		border:1px solid #CCCCCC;
		margin-bottom:2px;
	}
	
	.aui .input-localized-input{
		display: table-row;
	}
	
	.aui .icon-edison{
		font-family: fontawesome-alloy;
		display: inline;
		cursor: pointer;
		margin:0 1px 0 1px;
	}
	
	.aui .icon-edison.icon-file:before{
		content: "\f15b";
		font-size: 2em;
	}
	
	.aui .icon-edison.icon-picture:before{
		content: "\F03E";
		font-size: 2em;
	}
	
	.aui .icon-edison.icon-text:before{
		content: "\F15C";
		font-size: 2em;
	}
	
	.aui .tabletopbox .radio{
		float:left;
		padding-right: 20px;
	}
	
</style>
<liferay-portlet:resourceURL var="retrieveGeneralURL" id="retrieveListGeneral" copyCurrentRenderParameters="false" escapeXml="false"/>


<liferay-portlet:renderURL var="contentSearchURL" copyCurrentRenderParameters="false" >
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="generalWriterURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
	<liferay-portlet:param name="myaction" value="generalModifyView" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="contentManageViewURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
	<liferay-portlet:param name="myaction" value="contentManageView" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="contentTabSearchURL" portletMode='view'/>


<style>
	#<portlet:namespace/>textfield{
		width: 220px;
	}
</style>
<!-- 페이지 타이틀 & 네비게이션 -->
<c:if test="${isAdmin == false }">
	<div class="h10"></div>
	<div class="contabmenu"> 
		<edison-ui:tabs names="<%=tabNames%>" url="<%=contentTabSearchURL%>" tabsValues="owner_content,manager_content" value="<%=listTabValue%>" param="tabValue" minwidth="230"/>
	</div>
</c:if>

<div class="h10"></div>
	
<aui:form name="contentListForm" method="post">
	<aui:input type="hidden" name="groupId" value="${groupId }"/>
	<aui:input type="hidden" name="isAdmin" value="${isAdmin }"/>
	<aui:input type="hidden" name="contentSeq" value=""/>
	<aui:input type="hidden" name="contentDiv" value=""/>
	<div class="table-responsive panel edison-panel">
		
		<div class="panel-heading clearfix">
		
			<h3 class="panel-title pull-left">
				<img src="${contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				<liferay-ui:message key='edison-content' />
			</h3>
			
			<div class="input-group">
				<select class="form-control" id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" title="옵션" onchange="<portlet:namespace/>generalContentSearch('','');" style="width: 24%;">
					<option value="10">10<liferay-ui:message key="edison-search-views"/></option>
					<option value="15">15<liferay-ui:message key="edison-search-views"/></option>
					<option value="20">20<liferay-ui:message key="edison-search-views"/></option>
				</select>
					
				<input class="form-control" name="<portlet:namespace/>textfield" type="text"
					   id="<portlet:namespace/>textfield"
					   placeholder="<liferay-ui:message key="edison-table-list-header-title"/>"
					   size="40"
					   onKeydown="if(event.keyCode ==13)<portlet:namespace/>generalContentSearch('');"
					   value="${searchText }" 
					   style="width:75%; margin-left: 1%;"
				/>
				
				<div class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="<portlet:namespace/>generalContentSearch('');">
						<i class="icon-search"></i>
					</button>
					<button class="btn btn-default" type="button" name="fullsize" id="fullsize" value="Clear" onclick="<portlet:namespace/>dafaultContentAllSearch();">
						Clear
					</button>
				</div>
				
				
			</div>
		</div>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="일반콘텐츠 테이블" class="table table-bordered table-hover edison-table">
			<colgroup>
				<col width="10%" />
				<col width="15%" />
				<col width="30%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-index" /></th>
					<th scope="col"><liferay-ui:message key="edison-content-type"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-title"/></th>
					<th scope="col"><liferay-ui:message key="edison-workflow-public-status"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-name"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-date"/></th>
				</tr>
			</thead>
			<tbody id="contentTableBody">
			<c:choose>
				<c:when test="${fn:length(dataList) == 0}">
					<tr>
						<td class="center" colspan="6"><liferay-ui:message key="edison-there-are-no-data"/></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="data" items="${dataList }" varStatus="status">
						<c:if test="${status.index % 2 == 1}">
							<tr class="tablebgtr" style="word-break: break-all; cursor: pointer;"
								onClick="<portlet:namespace/>generalModify('<%=Constants.VIEW%>','${data.contentSeq }','${data.contentDiv}')">	
						</c:if>
						<c:if test="${status.index % 2 == 0}">
							<tr style="word-break: break-all; cursor: pointer;"
								onClick="<portlet:namespace/>generalModify('<%=Constants.VIEW%>','${data.contentSeq }','${data.contentDiv}')">
						</c:if>
							<td class="center">${seq - status.index }</td>
							<td class="center">${data.contentDivNm }</td>
							<td >${data.title }</td>
							<td class="center">${data.openYn }</td>
							<td class="center">${data.screenName}</td>
							<td class="center">${ data.insertDate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		
		<div class="h10"></div>
		
		<div class="row" style="margin: 0px;">
			<div class="col-md-10 paging">
				<div id="<portlet:namespace/>paging" style="width:100%;text-align: center;">${paging }</div>
			</div>
			
			<div class="col-md-2 buttonbox" id="<portlet:namespace/>addGeneralContentDiv" style="margin: 18px 0px;">
				<input type="button" class="btn btn-default" value="<liferay-ui:message key="edison-content-create" />" onclick="<portlet:namespace/>contentManageViewMove('<%=Constants.ADD%>');return false;" style="width: 100%;" />
			</div>
			
		</div>
	</div>

</aui:form>	

<script type="text/javascript">

$(function(){
	var searchLine = "${searchLine}";
	if(searchLine != ""){
		$("#<portlet:namespace/>select_line").val(searchLine);
	}
})

//일반 콘텐츠 조회
var currentPage = "";

function <portlet:namespace/>generalContentSearch(p_currentPage){
	currentPage = p_currentPage;
	
	//검색어
	var searchText = $("#<portlet:namespace/>textfield").val();
	
	//라인검색 값
	var searchLine = $("#<portlet:namespace/>select_line").val();
	var groupId = $("#<portlet:namespace/>groupId").val()
	
	var searchParameter = "";
	
	if( searchText !=""){
		searchParameter += "&<portlet:namespace/>searchText="+searchText;
	}
	searchParameter += "&<portlet:namespace/>groupId="+groupId;
	searchParameter += "&<portlet:namespace/>currentPage="+currentPage;
	searchParameter += "&<portlet:namespace/>searchLine="+searchLine;
	searchParameter += "&<portlet:namespace/>listTabValue=<%=listTabValue%>";
	location.href="<%=contentSearchURL%>"+searchParameter;
	
}

//일반콘텐츠 Modify
function <portlet:namespace/>generalModify(mode,seq,contentDiv){
	$("#<portlet:namespace/>mode").val(mode);
	$("#<portlet:namespace/>contentSeq").val(seq);
	$("#<portlet:namespace/>contentDiv").val(contentDiv);
	
	var searchParameter = "";
	searchParameter += "&<portlet:namespace/>mode="+mode;
	searchParameter += "&<portlet:namespace/>contentSeq="+seq;
	searchParameter += "&<portlet:namespace/>contentDiv="+contentDiv;
	
	if($("#<portlet:namespace/>textfield").val()!=""){
		searchParameter += "&<portlet:namespace/>searchText="+$("#<portlet:namespace/>textfield").val();
	}
	searchParameter += "&<portlet:namespace/>currentPage="+currentPage;
	location.href="<%=generalWriterURL%>"+searchParameter;
	
	//$("#<portlet:namespace/>contentListForm").submit();
// 	<portlet:namespace/>contentListForm.submit(); 
}

function <portlet:namespace/>contentManageViewMove(mode){
	$("#<portlet:namespace/>mode").val(mode);
	
	var searchParameter = "";
	searchParameter += "&<portlet:namespace/>mode="+mode;
	
	if($("#<portlet:namespace/>textfield").val()!=""){
		searchParameter += "&<portlet:namespace/>searchText="+$("#<portlet:namespace/>textfield").val();
	}
	searchParameter += "&<portlet:namespace/>currentPage="+currentPage;
	location.href="<%=contentManageViewURL%>"+searchParameter;	
}

//일반 콘텐츠 페이징조회
function <portlet:namespace/>generalContentPageSearch(p_currentPage){
	<portlet:namespace/>generalContentSearch(p_currentPage);
}

//일반 콘텐츠 전체보기
function <portlet:namespace/>dafaultContentAllSearch(){
	$("#<portlet:namespace/>textfield").val("");
	//탭 초기화
	/* var imagePath = "${contextPath}/images/content/";
	$preOnAtag = $(".tabletoptab li.on>a");
	if($preOnAtag.length > 0){
		$preOnAtag.find("img:first-child").attr("src",imagePath+$preOnAtag.attr("href").replace("#", "")+"off.png")
		$(".tabletoptab li").removeClass('on');
	} */
	
	<portlet:namespace/>generalContentSearch("");
}


</script>
