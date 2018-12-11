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
	
	.subtitlearea{
		margin-left: 10px;
	}
	
	.detailViewSubTitle{padding-left: 0px !important; padding-right: 0px !important;}
	
	
	@media screen and (max-width:767px) {
		.topmenuwrap>ul {
			display: none;
		}
		td.bold.TR {
			display: none;
		}
		div#nsubtopwrap {
			display: none;
		}
		.input-group {
			display: none !important;
		}
		
		input.btn.btn-default {
			display: initial;
			margin-top: 0px;
		}
		td.TL {
			white-space: inherit !important;
		}
		th:nth-child(5), th:nth-child(4), th:nth-child(3),td:nth-child(5), td:nth-child(4), td:nth-child(3)  {
			display: none ;
		}
		div.h4 {
			display: none !important;
		}
		.boardbtnbox {
			margin-bottom: 15px;
		}
	}
	
	@media (max-width: 767px) and (max-width: 979px) {
		input.btn.btn-default {
			display: initial;
			margin-top: 0px;
		}
	}
</style>
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
		<input type="hidden" id="<portlet:namespace/>listSize" name="<portlet:namespace/>listSize" value="">
	</form>
	
	<div class="table-responsive panel edison-panel">
	
		
		<!-- Board Contents -->
		<div class="panel-heading clearfix detailViewSubTitle">
			<!-- Board Title -->
			<c:choose>
				<c:when test="${empty redirectName }">
					<h2 class="pull-left" style="width: 50%;">
						<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
						<span class="subtitlearea">
							${boardDivTitle}
						</span>
					</h2>
				</c:when>
				<c:otherwise>
					<h2 class="pull-left" style="width: 50%;">
						<a onClick="historyBack<portlet:namespace/>()" style="cursor: pointer;"> ${redirectName } </a>  > ${boardDivTitle}
					</h2>
				</c:otherwise>
			</c:choose>
			
			<div class="input-group" style="top: 20px;">
				
				<select id="<portlet:namespace/>listSize" name="<portlet:namespace/>listSize" onchange="getBoardList<portlet:namespace/>(1)" class="form-control" style="width: 25%;">
					<option value="10" <c:if test="${listSize == '10' }"> selected="selected"</c:if> >10<liferay-ui:message key='edison-search-views' /></option>
					<option value="20" <c:if test="${listSize == '20' }"> selected="selected"</c:if>>20<liferay-ui:message key='edison-search-views' /></option>
					<option value="30" <c:if test="${listSize == '30' }"> selected="selected"</c:if>>30<liferay-ui:message key='edison-search-views' /></option>
					<option value="40" <c:if test="${listSize == '40' }"> selected="selected"</c:if>>40<liferay-ui:message key='edison-search-views' /></option>
				</select>
				
				<c:if test="${boardDiv.divNm=='FAQ'}">
					<input class="form-control" type="text" id="<portlet:namespace/>searchText"  name="<portlet:namespace/>searchText" value="${searchValue}" onkeypress="if(event.keyCode==13)getBoardList<portlet:namespace/>(1); " placeholder="<liferay-ui:message key='edison-board-placeholder-faq' />" style="width: 74%; float: right; margin-left: 1%;">
				</c:if>
				<c:if test="${boardDiv.divNm!='FAQ'}">
					<input class="form-control" type="text" id="<portlet:namespace/>searchText"  name="<portlet:namespace/>searchText" value="${searchValue}" onkeypress="if(event.keyCode==13)getBoardList<portlet:namespace/>(1); " placeholder="<liferay-ui:message key='edison-board-placeholder' />" style="width: 74%; float: right; margin-left: 1%;">
				</c:if>
				
				<div class="input-group-btn">
					<button class="btn btn-default" type="button" onClick="getBoardList<portlet:namespace/>(1)"><i class="icon-search"></i></button>
					<button class="btn btn-default" onClick="searchAllClick<portlet:namespace/>()">
						Clear
					</button>
				</div>
			</div>
		</div>
		
		<!-- FAQ 게시판 체크 -->
		<c:choose>
			<c:when test="${boardDiv.divNm=='FAQ'}">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
						<colgroup id="boardColgroup">
							<col width="70" />
							<col width="50" />
							<col width="*" />
							<col width="150" />
						</colgroup>
						<thead>
							<tr>
								<th><liferay-ui:message key='edison-table-list-header-index' /></th>
								<th></th>
								<th><liferay-ui:message key='edison-table-list-header-title' /></th>
								<th><liferay-ui:message key='edison-table-list-header-date' /></th>
							</tr>
						</thead>
						<tbody id="boardListBody<portlet:namespace/>">
						</tbody>
					</table>
			</c:when>
			<c:otherwise>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
						<colgroup id="boardColgroup">
							<col width="8%" />
							<col/>
							<col width="12%" />
							<col width="10%" />
							<col width="8%" />
						</colgroup>
						<thead>
							<tr>
								<th><liferay-ui:message key='edison-table-list-header-index' /></th>
								<th><liferay-ui:message key='edison-table-list-header-title' /></th>
								<th><liferay-ui:message key='edison-table-list-header-name' /></th>
								<th><liferay-ui:message key='edison-table-list-header-date' /></th>
								<th><liferay-ui:message key='edison-table-list-header-views' /></th>
							</tr>
						</thead>
						<tbody id="boardListBody<portlet:namespace/>">
						</tbody>
					</table>
			</c:otherwise>
		</c:choose>
		
		<!-- Button -->
		<div class="buttonbox" style="text-align: right; margin-top: 15px;">
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
			
			<c:if test="${redirectURL ne '' and redirectName ne ''}">
				<input class="btn btn-default" type="button" style="margin-left:5px;" onClick="historyBack<portlet:namespace/>()" value="<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-class-move' />"/>
			</c:if>
			<c:if test="${redirectURL ne '' and redirectName eq ''}">
				<input class="btn btn-default" type="button" style="margin-left:5px;" onClick="historyBack<portlet:namespace/>()" value="<liferay-ui:message key='edison-virtuallab-move' />"/>
			</c:if>
		</div>
	
		<!-- pagination -->
		<div class="text-center">
			<ul id="pagination<portlet:namespace/>" class="pagination">
			</ul>
		</div>
		
	
	
	</div>
	<script type="text/javascript">
	
	function changeListSize<portlet:namespace/>(value){
		$('input[id=<portlet:namespace/>listSize]').val(value);
	}
	
	function getBoardList<portlet:namespace/>(p_currentPage){
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>currentPage.value=p_currentPage;
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>searchValue.value=$("#<portlet:namespace/>searchText").val();
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>listSize.value=$('input[id=<portlet:namespace/>listSize]').val();
		
		if(document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>searchValue.value == "undefined"){
			document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>searchValue.value = "";
		}
		
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
				
				$("#pagination<portlet:namespace/> li").remove();
				$("#boardListBody<portlet:namespace/> tr:not(:has(#1))").remove();
				$vRow = $("<tr/>");
				
				if("${boardDiv.divNm}"=='FAQ') {
					if(boardList.length == 0){
						$("<td/>").attr("colspan", "4")
						  .css("height", "40px")
						  .html("<p style='text-align:center;'>${boardDiv.divNm} <liferay-ui:message key='edison-there-are-no-data' /></p>")
						  .appendTo($vRow);				
		
						$("#boardListBody<portlet:namespace/>").append($vRow);
					}else{
						for(var i = 0 ; i < boardList.length; i++ ){
							
							$vRow = $("<tr/>").addClass("faqHeader")
											  .css("cursor", "pointer")
											  .css("height", "45px")
											  .attr("onclick","viewDownRow('" + (data.seq - i) + "')");
							
							$("<td/>").text(data.seq-i)
									  .addClass("TC center")
									  .appendTo($vRow);
							$("<td/>").addClass("TC center")
									  .append($("<img/>").attr("src", "${contextPath}/images/Q.png"))
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].title + (boardList[i].replyCount > 0? "<b>("+boardList[i].replyCount+")</b>":"") )
									  .addClass("TL center")
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].writerDate )
									  .addClass("TC center")
									  .appendTo($vRow);
							$("#boardListBody<portlet:namespace/>").append($vRow);
							
							$vRow = $("<tr/>").addClass("faqContent center")
											  .attr("id", "faqContent_" + (data.seq-i));
							
							$("<td/>").appendTo($vRow);
							

							$("<td/>").appendTo($vRow);
							
							var adminCheck = "${isCustomAdmin}";
							if(adminCheck) {
								$("<td/>").attr("colspan", "2")
										  .append($("<div/>").css("height", "100%").append($("<img/>").attr("src", "${contextPath}/images/A.png").css("float","left").css("margin", "4px 10px 0px 0px")))
										  .append(boardList[i].content)
										  .addClass("TL")
										  .append($("<div/>").css("text-align", "right")
															 .append($("<input/>").attr("type", "button")
																				  .attr("value", "<liferay-ui:message key='edison-button-board-modify' />")
																				  .attr("onclick", "<portlet:namespace/>modify('" + boardList[i].boardSeq + "')")
																				  .addClass("btn btn-default")
																				  .css("padding", "3px 7px")
																				  .css("margin-right", "10px")
																	 )
															 .append($("<input/>").attr("type", "button")
																				  .attr("value", "<liferay-ui:message key='edison-button-board-delete' />")
																				  .attr("onclick", "<portlet:namespace/>deleteBoard('" + boardList[i].boardSeq + "')")
																				  .addClass("btn btn-default")
																				  .css("padding", "3px 7px")
																	 )
												  )
										  .appendTo($vRow);
							} else {
								$("<td/>").attr("colspan", "2")
										  .append($("<img/>").attr("src", "${contextPath}/images/A.png").css("float","left").css("margin", "4px 10px 0px 0px"))
										  .append(boardList[i].content)
										  .addClass("TL")
										  .appendTo($vRow);
							}
							
							$("#boardListBody<portlet:namespace/>").append($vRow);
						}
					}
				} else {
					if(boardList.length == 0){
						$("<td/>").attr("colspan", "5")
						  .css("height", "40px")
						  .html("<p style='text-align:center;'>${boardDiv.divNm} <liferay-ui:message key='edison-there-are-no-data' /></p>")
						  .appendTo($vRow);				
		
						$("#boardListBody<portlet:namespace/>").append($vRow);
					}else{
						
						for(var i = 0 ; i < boardList.length; i++ ){
							
							$vRow = $("<tr/>").addClass("onMouseHover")
											  .attr("onclick", "javascript:viewClick<portlet:namespace/>('"+boardList[i].boardSeq+"')")
											  .css("height", "45px");
							
		 					if(i%2 == 1){
		 						$vRow.addClass("tablebgtr");
		 					}
							
							$("<td/>").text(data.seq-i)
									  .addClass("TC center")
									  .appendTo($vRow);
		
							$("<td/>").html(boardList[i].title + (boardList[i].replyCount > 0? "<b>("+boardList[i].replyCount+")</b>":"") )
									  .addClass("TL")
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].writerName)
									  .addClass("TC center")
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].writerDate )
									  .addClass("TC center")
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].readCnt )
									  .addClass("TC center")
									  .appendTo($vRow);
							$("#boardListBody<portlet:namespace/>").append($vRow);
						}
					}
				}
				
				// pagination
				leftPageBtn_li = $("<li/>").css("cursor", "pointer");
				leftPageBtn_a = $("<a/>").html("&laquo;"); 
				leftPageBtn_li.append(leftPageBtn_a);
				$("#pagination<portlet:namespace/>").append(leftPageBtn_li);
				
				for(var i=0; i<data.pageCount; i++){
					page_li = $("<li/>").attr("onclick", "getBoardList<portlet:namespace/>(" + (i+1) + ");return false")
					                    .css("cursor", "pointer");
					if(p_currentPage == (i+1)){
						page_li.attr("class", "active");
					}
					page_a = $("<a/>").text((i+1));
					page_li.append(page_a);
					$("#pagination<portlet:namespace/>").append(page_li);
				}
				
				rightPageBtn_li = $("<li/>").css("cursor", "pointer");
				rightPageBtn_a = $("<a/>").html("&raquo;"); 
				rightPageBtn_li.append(rightPageBtn_a);
				$("#pagination<portlet:namespace/>").append(rightPageBtn_li);
				
			},error:function(data,e){ 
				alert("list:::BoardList===>"+e);
			},complete:function(){
				//boardSearchList("1",divCd);
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
		/* $(".onFaqContent").removeClass("onFaqContent"); */
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
	
	getBoardList<portlet:namespace/>('${currentPage}');
	
	function historyBack<portlet:namespace/>(){
		location.href = "${redirectOrignURL}";
	}
</script>

