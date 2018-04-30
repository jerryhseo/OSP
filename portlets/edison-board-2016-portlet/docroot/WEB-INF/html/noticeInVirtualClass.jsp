<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<liferay-portlet:resourceURL var="getBoardListURL" id="getBoardList" escapeXml="false" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="listSize" value="4" />
	<liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
</liferay-portlet:resourceURL>

<style>
.conwrap2left {
	width: 100%;
	float: left;
}
/* Virtual Lab Class QnA */
.rightt {
	background: url(${contextPath}/images/class_bl5.png) no-repeat 0 5px;
	padding-left: 65px;
}

/* .conwrap2right {
	width: 47%;
	float: right;
} */

.qalist {
	padding-bottom: 19px;
	border-bottom: solid 1px #bcbcbc;
	/*font-family:'Nanum Gothic', sans-serif;*/
}

.qalist ul {
	margin: 0;
	padding: 0;
}

.qalist ul li {
	list-style: none;
	font-size: 15px;
	color: #666;
	line-height: 2.5em;
}

.qalist ul li:nth-child(even) {
	line-height: 1.2em;
}

.qalist ul li a:link {
	color: #666;
}

.qalist ul li a:hover {
	text-decoration: underline;
}

.btn_s_q {
	background-color: #d4e961;
	font-size: 11px;
	color: #474f58 !important;
	font-weight: 600;
	border-radius: 2px;
	text-align: center;
	padding: 0 10px;
	line-height: 1.2em !important;
	height: 22px;
	margin: 0px 2px 0 2px;
	border: solid 1px #ddd;
	cursor: pointer !important;
}

.btn_s_a {
	background-color: #01dee9;
	font-size: 11px;
	color: #474f58 !important;
	font-weight: 600;
	border-radius: 2px;
	text-align: center;
	padding: 0 10px;
	line-height: 1.2em !important;
	height: 22px;
	margin: 0px 2px 0 2px;
	border: solid 1px #ddd;
	cursor: pointer !important;
}

.classnotice{
	width: 345px;
}

.noticeContent{
	overflow: hidden;
	word-wrap: normal;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.noticeContent:HOVER{
	cursor: pointer;
	text-decoration: underline;
}

</style>

<%--### Main Board List Start ######################################################################################################################  --%>

<%
	long originalBoardPlid = Long.parseLong(CustomUtil.strNull((portletPreferences.getValue("originalBoardPlid", "0"))));
	String originalBoardPortletName = CustomUtil.strNull((portletPreferences.getValue("originalBoardPortletName", "")));
	String divCd = CustomUtil.strNull((portletPreferences.getValue("divCd", "100")));
	
	if(originalBoardPlid == 0) originalBoardPlid = plid;
%>
	<liferay-portlet:renderURL plid="<%=originalBoardPlid %>" portletMode="view" var="originalBoardListURL">
		<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
		<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	</liferay-portlet:renderURL>
	
	<liferay-portlet:renderURL portletMode="view" var="originalBoardListMaxURL" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
		<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
		<liferay-portlet:param name="customId" value="${customId}" />
		<liferay-portlet:param name="redirectName" value="${redirectName}" />
		<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
		<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
		<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
		<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	</liferay-portlet:renderURL>
	
	
	
	<liferay-portlet:renderURL plid="<%=originalBoardPlid %>" portletName="<%=originalBoardPortletName %>" portletMode="view" var="originalBoardViewURL" copyCurrentRenderParameters="false">
		<liferay-portlet:param name="myRender" value="getBoardRender" />
		<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
		<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	</liferay-portlet:renderURL>
	
	<liferay-portlet:renderURL windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" portletMode="view" var="originalBoardViewMaxURL" copyCurrentRenderParameters="false">
		<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
		<liferay-portlet:param name="redirectName" value="${redirectName}" />
		<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
		<liferay-portlet:param name="customId" value="${customId}" />
		<liferay-portlet:param name="groupId" value="${groupId}" />
		<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
		<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
		<liferay-portlet:param name="myRender" value="getBoardRender" />
	</liferay-portlet:renderURL>
	
	
	<form name="mainBoardForm<portlet:namespace/>" method="post">
		<input type="hidden" id="<portlet:namespace/>currentPage" name="<portlet:namespace/>currentPage" value="1">
	</form>
	<div class="classnotice">
		<div class="noticemore" onclick="goList<portlet:namespace/>('${maxWindowStatus}');" style="cursor: pointer;">
			<img src="${contextPath}/images/more_icon.png" width="17" height="17">
		</div>
		<ul class="noticeTitleInVirtualClass">
			<li><liferay-ui:message key="edison-virtuallab-notice"/></li>
		</ul>
		<ul class="noticeContentsInVirtualClass" id="<portlet:namespace/>noticeContentsInVirtualClass">
		
		</ul>
	</div>
	
	<script type="text/javascript">
	function goList<portlet:namespace/>(maxWindowStatus){
		var customId = "${customId}";
		
		if(maxWindowStatus=='Y'&&customId!=""){
			document.mainBoardForm<portlet:namespace/>.action = "<%=originalBoardListMaxURL%>";
			document.mainBoardForm<portlet:namespace/>.submit()
		}else{
			location.href = "<%=originalBoardListURL%>";
		}
	}
	
	function viewClick<portlet:namespace/>(p_boardSeq,maxWindowStatus){
		var customId = "${customId}";
		
		if(maxWindowStatus=='Y'&&customId!=""){
			document.mainBoardForm<portlet:namespace/>.action = "<%=originalBoardViewMaxURL%>"+"&<portlet:namespace/>boardSeq="+p_boardSeq;
			document.mainBoardForm<portlet:namespace/>.submit()
		} else {
			location.href = "<%=originalBoardViewURL%>&_<%=originalBoardPortletName%>_boardSeq="+p_boardSeq;
		}
	}
	
	function getBoardList<portlet:namespace/>(p_currentPage){
		document.mainBoardForm<portlet:namespace/>.<portlet:namespace/>currentPage.value=p_currentPage;
		var boardInputForm = {
						"<portlet:namespace/>methodName" : "getBoardList<portlet:namespace/>",
						"<portlet:namespace/>currentPage" : p_currentPage,
						"<portlet:namespace/>virtualLabId" : "${virtualLabId}",
						"<portlet:namespace/>isVirtualClass" : "${isVirtualClass}"
						};
		jQuery.ajax({
			type: "POST",
			url: "<%=getBoardListURL%>",
			data: boardInputForm,
	  		async : false,
			success: function(data) {
				var boardList = data.boardList;
				var divCd = data.divCd;
				
				var noticeList = $("#<portlet:namespace/>noticeContentsInVirtualClass");
				noticeList.html("");
				
				if(boardList.length == 0){
					/* 조회 데이터 없는 경우 */
					$("<li/>").text("<liferay-ui:message key='edison-there-are-no-data' />")
							  .appendTo(noticeList);
				}else{
					for(var i = 0 ; i < boardList.length; i++ ){
						var classTitle = boardList[i].classTitle;
						var classId = boardList[i].classId;
						var divVirtualLabClass = "";
						
						$("<li/>").addClass("noticeContent")
								  .attr("onclick", "javascript:viewClick<portlet:namespace/>('" + boardList[i].boardSeq + "','${maxWindowStatus}')")
								  .text(boardList[i].title)
								  .appendTo(noticeList);
					}
				}
				
			},error:function(data,e){ 
				alert("listMain:::BoardList===>"+e);
			}
		});
	}
	
	function getBoardAnswerList<portlet:namespace/>(groupBoardSeq){
		var returnBoardList = null;
		
		var boardInputForm = {
				"<portlet:namespace/>methodName" : "getBoardList<portlet:namespace/>",
				"<portlet:namespace/>virtualLabId" : "${virtualLabId}",
				"<portlet:namespace/>isVirtualClass" : "${isVirtualClass}",
				"<portlet:namespace/>groupBoardSeq" : groupBoardSeq
				};
		jQuery.ajax({
			type: "POST",
			url: "<%=getBoardListURL%>",
			data: boardInputForm,
				async : false,
			success: function(data) {
				var boardList = data.boardList;
				var divCd = data.divCd;
				
				if(0 < boardList.length){
					returnBoardList = boardList;
				}
			},error:function(data,e){ 
				alert("listMain:::BoardList===>"+e);
			}
		});
		
		return returnBoardList;
	}
	
	getBoardList<portlet:namespace/>('${currentPage}');
	</script>
	<style type="text/css">
	
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
					height: 40px;
					vertical-align: middle;
					}
	.smpupclosebtn{position:absolute; right:18px; top:23px;}
	.smpuptable{width:80%; padding:20px; margin:0 auto;}
	.notice-mtitle p {margin:0px; padding:0px;}
	.notice-mtitle p a,.notice-mtitle p a:visited, .notice-mtitle p a:link{color:#666;}
	.notice-mtitle p a:hover{color:#000;}
	</style>
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
							<i class="icon-save"></i>
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
	  
	<script type="text/javascript">
		function popupCreate(state){
			if(state="YES"){
				$(".smallpupboxBoard").each(function(index){
					$(this).dialog({
						resizable: false,
						modal: false,
						draggable: true,
						width:606,
						height:'auto',
						position: [100*(index+1), 100*(index+1)],
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
		
		
		function <portlet:namespace/>fileDownload(p_fileEntryId){
			location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
		}
	</script>		
<%--### Main Board List Start ######################################################################################################################  --%>