<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<liferay-portlet:resourceURL var="getBoardListURL" id="getBoardList" escapeXml="false" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:renderURL copyCurrentRenderParameters="false" var="getBoardMaxRenderUrl" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
	<liferay-portlet:param name="myRender" value="getBoardRender"/>
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}"/>
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL copyCurrentRenderParameters="false" var="getBoardRenderURL" >
	<liferay-portlet:param name="myRender" value="getBoardRender"/>
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}"/>
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
</liferay-portlet:renderURL>


<liferay-portlet:resourceURL var="addBoardURL" id="addBoard" escapeXml="false" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:actionURL var="deleteBoardActionUrl" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myAction" value="deleteBoardAction"/>
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
</liferay-portlet:actionURL>

<!-- Add CSS & JS -->
<link href="${contextPath}/css/newmain.css" rel="stylesheet">

<script src="${contextPath}/js/flowtype.js"></script>

<style type="text/css">
	.onMouseHover:hover {
		background-color: #ddd;
	}
	
	.faqContent {
		background: #f7f7fb;
		display:none;
	}
	
	.onFaqContent {
		display:table-row;
	}
	.noticeContent{
		margin: 0 0 5px;
	}
	
	/* Popup CSS */
	.popuptitle{
		padding-top:60px; 
		padding-left:20px; 
		font-weight:600; 
		font-size:16px; 
		color:#D35400;
	}
	
	.popupcontxt{
		height:350px;
		padding:15px 25px 15px 25px; 
		font-size:13px; 
		line-height:1.5em;
	}
	.closetxt{
		font-size:11px; 
		color:#fff; 
		margin-top:28px; 
		padding-left:15px;
	}
	
	.closetxt img{
		vertical-align:middle;
	}
	
	.ui-icon {
		visibility: hidden;
	}
	 
	.popupWrap {
	    clear: left;
	    margin-right: 10px;
	    padding: 5px;
	    float: left;
	    border-width: 1px;
	    border-color: #f9f9f9;
	    border-radius: 5px;
	    box-shadow: 0px 0px 33px 8px lightgray;
	    background-color: #f9f9f9;
	} 
	.popupTitle {
		width: 80%;
		height:50px;
		float: left;
		padding: 10px 10px 0px 46px;
		font-size: 20px;
		font-weight: 600;
		line-height: normal;
		background: url(/edison-board-2016-portlet/images/bcicon.png) no-repeat 10px 10px;
	}
	.popupClose {
		width: 30px;
		float: right;
	 	padding: 10px 10px 0px 0px;
		font-size: 24px;
		font-weight: 600;
	}
	.popupTrLine {
		width:95%;
		padding-bottom:10px;
		margin-top:10px;
		border-bottom: solid 1px #ccc;
	}
	
	.popupContent {
		width:554px;
		height:285px;
		margin :0 auto;
		padding: 5px 9px 10px 9px;
		background-color: #FFFFFF;
	}
	
	.smallpupboxBoard{
				width:100%; border-radius:5px; -webkit-border-radius:5px; border:solid 3px #ddd;
				font-family: Tahoma,Arial, Nanum Barun Gothic, NanumGothic;
				}
	.smallpuptitle{border-radius:3px 3px 0px 0px; -webkit-border-radius:3px 3px 0px 0px; padding:5px; color:#fff; 
					font-size:15px; font-weight:600; background:url(/edison-board-2016-portlet/images/popbl.png) no-repeat 15px 12px; 
					padding-left:55px; padding-right:17px; 
					background-color:#3fabc7; position:relative;
					height: auto; min-height: 40px;
					vertical-align: middle;
					}
	.smpupclosebtn{position:absolute; right:18px; top:16px;}
	.smpuptable{width:80%; padding:20px; margin:0 auto;}
	.notice-mtitle p {margin:0px; padding:0px;}
	.notice-mtitle p a,.notice-mtitle p a:visited, .notice-mtitle p a:link{color:#666;}
	.notice-mtitle p a:hover{color:#000;}
</style>

	<!-- Popup -->
	<c:if test="${popState ne 'NO'}">
			<c:forEach items="${popupList}" var="model"> 
				<div id="POPUP_${model.boardSeq}" class="smallpupboxBoard" style="width:600px; height:405px; font-family: Arial,Nanum Barun Gothic,NanumGothic;">			
					<div style="vertical-align: middle;">
						<div class="smallpuptitle"> 
							${model.title} 
						</div>	  
						<div class="smpupclosebtn">
							<img src="${contextPath}/images/closeicon.png" width="21" height="21" onclick="closePopup('POPUP_${model.boardSeq}');return false;" style="cursor: pointer;"/>
						</div>
					</div><br>
					
					<div class="popupContent" style="overflow: auto;">
						<c:out value="${model.content}" escapeXml="false"/>
					</div>
	
					<div>
						<c:forEach items="${model.fileList}" var="fileModel">
							<div style="cursor:pointer" onclick="<portlet:namespace/>fileDownload('${fileModel.fileEntryId }')" class="onMouseHover">
								${fileModel.fileTitle }
								<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
							</div>
						</c:forEach>
					</div>
					
					<div style="padding-top: 7px;">
						<div style="float:left">
							<a href="#" onclick="closePopup('POPUP_${model.boardSeq}');return false;" class="onMouseHover"><liferay-ui:message key='edison-button-board-close' /> X</a>
						</div>
						<div style="float:right;padding-right:10px;">
							<a href="#" onclick="closePopupAt('POPUP_${model.boardSeq}');return false;" class="onMouseHover"><liferay-ui:message key='edison-board-popup-close-alert' /> X</a> 
						</div>
					</div>
				</div>
			</c:forEach>
	</c:if>	


<%--### Default Board List Start ######################################################################################################################  --%>	
<% 	
	String mainListYn = renderRequest.getPreferences().getValue("mainListYn", "N");
	long originalBoardPlid = 0;
	String originalBoardPortletName = "";
	String boardPlid = CustomUtil.strNull((portletPreferences.getValue("originalBoardPlid", "0")));
	
	if(boardPlid.equals("")){
		originalBoardPlid = 0;
	}else{
		originalBoardPlid = Long.parseLong(boardPlid);
	}
	originalBoardPortletName = CustomUtil.strNull((portletPreferences.getValue("originalBoardPortletName", "")));
	if(originalBoardPlid == 0) originalBoardPlid = plid;
%>
	<form name="<portlet:namespace/>boardModifyForm" action="<%=getBoardRenderURL%>" method="post" style="margin:0px;">
		<input type="hidden" id="<portlet:namespace/>currentPage" name="<portlet:namespace/>currentPage" value="1">
		<input type="hidden" id="<portlet:namespace/>searchValue" name="<portlet:namespace/>searchValue" value="">
		<input type="hidden" id="<portlet:namespace/>listSize" name="<portlet:namespace/>listSize" value="4">
	</form>
	
	<div class="table-responsive panel edison-panel">
	
		
		<!-- Title -->
		<c:choose>
			<c:when test="${fn:toUpperCase(siteName) eq 'EDISON'}">
				<h2 class="h2title" style="padding-top: 12px; font-family: 'Nanum Gothic', sans-serif;">
					NEWS & EVENT
				</h2>
			</c:when>
			<c:otherwise>
				<h2 class="h2title" style="padding-top: 15px; font-family: 'Nanum Gothic', sans-serif;">
					<c:out value="${fn:toUpperCase(siteName)}" /> NEWS
				</h2>
			</c:otherwise>
		</c:choose>
		
		<div class="newswrap">
			<div class="container" >
				<div class="row" style="margin: 0;" id="<portlet:namespace/>noticeContents">
					<!-- Notice Contents List -->
				</div>
			</div>
		</div>
		
		<div class="newsbtnicon">
			<ul>
				<!-- pagination -->
			</ul>
		</div>
		
		<!-- 글쓰기 Btn -->
		<div class="buttonbox" style="position: absolute; bottom: 24px; width:auto; right:1%; display: none;">
			<c:choose>
				<c:when test="${isCustomAdmin}">
					<input class="btn btn-default" type="button" onClick="writeBoard<portlet:namespace/>()" value="<liferay-ui:message key='edison-button-board-write' />" />
				</c:when>
				<c:otherwise>
					<c:if test="${isDefaultUserWrite}">
						<input class="btn btn-default" type="button" onClick="writeBoard<portlet:namespace/>()" value="<liferay-ui:message key='edison-button-board-write' />" />
					</c:if>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${redirectURL ne ''}">
				<input class="btn btn-default" type="button" style="margin-left:5px;" onClick="historyBack<portlet:namespace/>()" value="${redirectName}"/>
			</c:if>
		</div>
	</div>
	
	
<script type="text/javascript">

	$(document).ready(function(){
		getBoardList<portlet:namespace/>('${currentPage}');
		
		if('${siteName}' != 'EDISON'){
			$(".newsbtnicon").css("padding-top","16px").css("padding-bottom","20px");
		}
	});
	
	function getBoardList<portlet:namespace/>(p_currentPage){
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>currentPage.value=p_currentPage;
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>searchValue.value=""; /* $("#<portlet:namespace/>searchText").val(); */
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>listSize.value=4; /* $('select[id=<portlet:namespace/>listSize]').val(); */
		
		var boardInputForm = {
						"<portlet:namespace/>methodName" : "getBoardList<portlet:namespace/>",
						"<portlet:namespace/>currentPage" : p_currentPage,
						"<portlet:namespace/>searchValue" : document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>searchValue.value,
						"<portlet:namespace/>listSize" : document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>listSize.value
						};
		jQuery.ajax({
			type: "POST",
			url: "<%=getBoardListURL%>",
			data: boardInputForm,
	  		async : false,
			success: function(data) {
				var boardList = data.boardList;		
				
				/* Pagination Click -> 기존 Notice 제거 */
				noticeDivLength = $(".newslist").length;
				if(0<noticeDivLength){
					$(".newslist").remove();
				}
				$(".newsbtnicon > ul").html("");
				
				noticeContentsListDiv = $("#<portlet:namespace/>noticeContents");
				
				if(boardList.length == 0){
					
					noticeDiv = $("<div/>").addClass("newslist col-md-3 col-sm-12")
										   .css("padding-left", "7px")
										   .css("padding-right", "7px");
					noticeUl = $("<ul/>");
					noticeEmptyLi = $("<li/>").html("<p style='text-align:center;'><liferay-ui:message key='edison-there-are-no-data' /></p>")
										 .appendTo(noticeUl);
					noticeUl.appendTo(noticeDiv);
					noticeDiv.appendTo(noticeContentsListDiv);
					
				}else{
					for(var i=0 ; i < boardList.length; i++){
						noticeDiv = $("<div/>").addClass("newslist col-md-3 col-sm-12")
											   .css("padding-left", "7px")
											   .css("padding-right", "7px");
						noticeUl = $("<ul/>").css("min-height","305px");
						noticeTitle = $("<li/>").html( boardList[i].title + (boardList[i].replyCount > 0? "<b>("+boardList[i].replyCount+")</b>":"") )
												.css("text-overflow", "ellipsis")
												.css("overflow", "hidden")
												.css("display", "-webkit-box")
												.css("-webkit-line-clamp", "2")
												.css("-webkit-box-orient", "vertical")
												.css("word-wrap", "break-word")
												.css("line-height", "1.5em")
												.css("height", "3em")
												.css("margin-bottom", "35px");
						noticeContent = $("<li/>").html(boardList[i].content)
												  .addClass("noticeContent")
												  .css("text-overflow", "ellipsis")
												  .css("overflow", "hidden")
												  .css("white-space", "nowrap")
												  .css("max-height", "80px")
												  .css("line-height", "13px");
						noticeDetailBtn = $("<a/>").addClass("btn_line")
												   .text("더보기 > ")
												   .attr("href", "#")
												   .attr("role", "button")
												   .attr("onclick", "javascript:viewClick<portlet:namespace/>('"+boardList[i].boardSeq+"')")
												   .appendTo("<li/>");
						noticeDetailBtnLi = $("<li/>").append(noticeDetailBtn);
						
						noticeTitle.appendTo(noticeUl);
						noticeContent.appendTo(noticeUl);
						noticeDetailBtnLi.appendTo(noticeUl);
						
						noticeUl.appendTo(noticeDiv);
						noticeDiv.appendTo(noticeContentsListDiv);
					}
				}
					
				/* pagination */
				for(var i=0; i<data.pageCount; i++){
					paginationLi = $("<li/>").attr("onclick", "getBoardList<portlet:namespace/>(" + (i+1) + ");return false")
											 .css("cursor", "pointer");
					defaultPageBtn = $("<img/>").attr("src", "${contextPath}/images/noticepage.png")
												.css("width", "10")
												.css("height", "10");
					currentPageBtn = $("<img/>").attr("src", "${contextPath}/images/noticepages.png")
												.css("width", "37")
												.css("height", "10");
					if(p_currentPage == (i+1)){
						paginationLi.append(currentPageBtn);
					} else {
						paginationLi.append(defaultPageBtn);
					}
					$(".newsbtnicon > ul").append(paginationLi);
				}
				
			},error:function(data,e){ 
				alert("list:::BoardList===>"+e);
			},complete:function(){
				/* boardSearchList("1",divCd); */
			}
		});
	}
	
	function viewClick<portlet:namespace/>(p_boardSeq){
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>searchValue.value=$('#<portlet:namespace/>searchText').val();
		
		var getBoardRenderURL = "";
		var searchValue = $("#<portlet:namespace/>searchText").val();
		var vMainListYn = "<%=mainListYn%>";
		if(vMainListYn == 'Y') {
			getBoardRenderURL = "<%=getBoardMaxRenderUrl%>";
		} else {
			getBoardRenderURL = "<%=getBoardRenderURL%>";
		}
		getBoardRenderURL += "&<portlet:namespace/>boardSeq="+p_boardSeq;
		document.<portlet:namespace/>boardModifyForm.action=getBoardRenderURL;
		document.<portlet:namespace/>boardModifyForm.submit();
	}
	
	function viewDownRow(rowNum) {
		$("#faqContent_" + rowNum).toggleClass("onFaqContent")
	}
	
	function searchAllClick<portlet:namespace/>(){
		$('#<portlet:namespace/>searchText').val("");
		getBoardList<portlet:namespace/>(1);
	}
	
	function writeBoard<portlet:namespace/>(){
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>searchValue.value=$('#<portlet:namespace/>searchText').val();
		var vMainListYn = "<%=mainListYn%>";
		
		if(vMainListYn == 'Y') {
			getBoardRenderURL = "<%=getBoardMaxRenderUrl%>";
		} else {
			getBoardRenderURL = "<%=getBoardRenderURL%>";
		}
		
		getBoardRenderURL += "&<portlet:namespace/>RENDER_SORT=WRITE";
		document.<portlet:namespace/>boardModifyForm.action=getBoardRenderURL;
		document.<portlet:namespace/>boardModifyForm.submit();
	}
	
	
	function <portlet:namespace/>modify(boardSeq){
		var searchValue = $("#<portlet:namespace/>searchText").val();
		document.<portlet:namespace/>boardModifyForm.action = "<%=getBoardRenderURL%>&<portlet:namespace/>boardSeq=" + boardSeq;
		document.<portlet:namespace/>boardModifyForm.submit();
	}

	function <portlet:namespace/>deleteBoard(boardSeq){
		if(confirm(Liferay.Language.get('data-delete-confirm'))){
			$("#<portlet:namespace/>groupBoardSeq").val(boardSeq);
			<portlet:namespace/>boardModifyForm.action = "<%=deleteBoardActionUrl%>&<portlet:namespace/>boardSeq=" + boardSeq;
			<portlet:namespace/>boardModifyForm.submit();
		}
	}
	
	//####################################################################################
	// Document Ready Define #############################################################
	//####################################################################################		

	getBoardList<portlet:namespace/>('${currentPage}');
	
	function historyBack<portlet:namespace/>(){
		location.href = "${redirectOrignURL}";
	}
	
	function <portlet:namespace/>searchAppStore() {
		var searchField = $("#<portlet:namespace/>searchField").val();
		if(searchField != "" && searchField != null) {
			window.location.href = "${searchURL}&_edisonsearch_WAR_edisonsearch2017portlet_searchKeyword=" + searchField;
		}
	}
	
	
	//####################################################################################
	// popup
	//####################################################################################
	
	function popupCreate(state){
		if(state="YES"){
			$(".smallpupboxBoard").each(function(index){
				$(this).dialog({
					resizable: false,
					modal: false,
					draggable: true,
					width:606,
					height:'auto',
					position: [100*(index+1), 225 + (index*10)],
				    show: {effect:'fade', speed: 800}, 
			        hide: {effect:'fade', speed: 800}
				}).dialog("widget").find(".ui-dialog-titlebar").remove();
				
			});
		}
	}
	
	function closePopup(id){
		$("#"+id).dialog("close");
	}
	
	function closePopupAt(id){
		setCookie( id, "done");
		$("#"+id).dialog("close");;
	}
	
	function setCookie( name, value) {   
		var todayDate = new Date();   
		todayDate.setDate(todayDate.getDate() + 1 );
		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"   
	}
	
	popupCreate("${popState}");
</script>


<%--### Default Board List End ######################################################################################################################  --%>

