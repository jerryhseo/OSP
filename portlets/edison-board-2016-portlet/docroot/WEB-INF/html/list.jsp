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
	
	<c:choose>
		<c:when test="${empty redirectName }">
			<h1>${boardDivTitle}</h1>
		</c:when>
		<c:otherwise>
		   <h1><a onClick="historyBack<portlet:namespace/>()" style="cursor: pointer;"> ${redirectName } </a>  > ${boardDivTitle}</h1>
		</c:otherwise>
	</c:choose>
	
	<form name="<portlet:namespace/>boardModifyForm" action="<%=getBoardRenderURL%>" method="post" style="margin:0px;">
		<input type="hidden" id="<portlet:namespace/>currentPage" name="<portlet:namespace/>currentPage" value="1">
		<input type="hidden" id="<portlet:namespace/>searchValue" name="<portlet:namespace/>searchValue" value="">
		<input type="hidden" id="<portlet:namespace/>listSize" name="<portlet:namespace/>listSize" value="">
	</form>
	<div class="tabletopbox01">
		<div class="search">
			<div class="searchbox">
				<c:if test="${boardDiv.divNm=='FAQ'}">
					<input type="text" id="<portlet:namespace/>searchText"  name="<portlet:namespace/>searchText" value="${searchValue}" onkeypress="if(event.keyCode==13)getBoardList<portlet:namespace/>(1); " placeholder="<liferay-ui:message key='edison-board-placeholder-faq' />">
				</c:if>
				<c:if test="${boardDiv.divNm!='FAQ'}">
					<input type="text" id="<portlet:namespace/>searchText"  name="<portlet:namespace/>searchText" value="${searchValue}" onkeypress="if(event.keyCode==13)getBoardList<portlet:namespace/>(1); " placeholder="<liferay-ui:message key='edison-board-placeholder' />">
				</c:if>
				<input type="button" onClick="getBoardList<portlet:namespace/>(1)" class="btnsearch">
			</div>
			<input type="button" value="<liferay-ui:message key='edison-button-all-search' />" onClick="searchAllClick<portlet:namespace/>()" class="button03">
		</div>
		<div class="tabletopright">
			<select id="<portlet:namespace/>listSize" name="<portlet:namespace/>listSize" onchange="getBoardList<portlet:namespace/>(1)" class="selectview">
				<option value="10" <c:if test="${listSize == '10' }"> selected="selected"</c:if> >10<liferay-ui:message key='edison-search-views' /></option>
				<option value="20" <c:if test="${listSize == '20' }"> selected="selected"</c:if>>20<liferay-ui:message key='edison-search-views' /></option>
				<option value="30" <c:if test="${listSize == '30' }"> selected="selected"</c:if>>30<liferay-ui:message key='edison-search-views' /></option>
				<option value="40" <c:if test="${listSize == '40' }"> selected="selected"</c:if>>40<liferay-ui:message key='edison-search-views' /></option>
			</select>
		</div>
	</div>
	
	<!-- FAQ 게시판 체크 -->
	<c:choose>
		<c:when test="${boardDiv.divNm=='FAQ'}">
			<div class="table1_list borderno">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" >
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
			</div>
		</c:when>
		<c:otherwise>
			<div class="table1_list borderno">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" >
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
			</div>
		</c:otherwise>
	</c:choose>
	
	<div id="paging<portlet:namespace/>" class="paging"></div>
		
	<div class="buttonbox" style="position: absolute; bottom: 24px; width:auto; right:1%;">
		<c:choose>
			<c:when test="${isCustomAdmin}">
				<input class="button02" type="button" onClick="writeBoard<portlet:namespace/>()" value="<liferay-ui:message key='edison-button-board-write' />" />
			</c:when>
			<c:otherwise>
				<c:if test="${isDefaultUserWrite}">
					<input class="button02" type="button" onClick="writeBoard<portlet:namespace/>()" value="<liferay-ui:message key='edison-button-board-write' />" />
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${redirectURL ne ''}">
			<input class="button02" type="button" style="margin-left:5px;" onClick="historyBack<portlet:namespace/>()" value="${redirectName}"/>
		</c:if>
	</div>

	<script type="text/javascript">
	
	function getBoardList<portlet:namespace/>(p_currentPage){
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>currentPage.value=p_currentPage;
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>searchValue.value=$("#<portlet:namespace/>searchText").val();
		document.<portlet:namespace/>boardModifyForm.<portlet:namespace/>listSize.value=$('select[id=<portlet:namespace/>listSize]').val();
		
		
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
											  .attr("onclick","viewDownRow('" + (data.seq - i) + "')");
							
							$("<td/>").text(data.seq-i)
									  .addClass("TC")
									  .appendTo($vRow);
							$("<td/>").addClass("TC")
									  .append($("<img/>").attr("src", "${contextPath}/images/Q.png"))
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].title + (boardList[i].replyCount > 0? "<b>("+boardList[i].replyCount+")</b>":"") )
									  .addClass("TL")
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].writerDate )
									  .addClass("TC")
									  .appendTo($vRow);
							$("#boardListBody<portlet:namespace/>").append($vRow);
							
							$vRow = $("<tr/>").addClass("faqContent")
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
																				  .addClass("button01b")
																				  .css("padding", "3px 7px")
																				  .css("margin-right", "10px")
																	 )
															 .append($("<input/>").attr("type", "button")
																				  .attr("value", "<liferay-ui:message key='edison-button-board-delete' />")
																				  .attr("onclick", "<portlet:namespace/>deleteBoard('" + boardList[i].boardSeq + "')")
																				  .addClass("button01b")
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
							
							$vRow = $("<tr/>").addClass("onMouseHover").attr("onclick", "javascript:viewClick<portlet:namespace/>('"+boardList[i].boardSeq+"')");
							
		 					if(i%2 == 1){
		 						$vRow.addClass("tablebgtr");
		 					}
							
							$("<td/>").text(data.seq-i)
									  .addClass("TC")
									  .appendTo($vRow);
		
							$("<td/>").html(boardList[i].title + (boardList[i].replyCount > 0? "<b>("+boardList[i].replyCount+")</b>":"") )
									  .addClass("TL")
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].writerName)
									  .addClass("TC")
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].writerDate )
									  .addClass("TC")
									  .appendTo($vRow);
							$("<td/>").html(boardList[i].readCnt )
									  .addClass("TC")
									  .appendTo($vRow);
							$("#boardListBody<portlet:namespace/>").append($vRow);
						}
					}
				}
				
				$("#paging<portlet:namespace/>").html(data.paging);
				
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
	
	//####################################################################################
	// Document Ready Define #############################################################
	//####################################################################################		

	getBoardList<portlet:namespace/>('${currentPage}');
	
	function historyBack<portlet:namespace/>(){
		location.href = "${redirectOrignURL}";
	}
</script>


<%--### Default Board List End ######################################################################################################################  --%>

