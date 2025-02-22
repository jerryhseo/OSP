<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<liferay-portlet:renderURL var="boardListURL" portletMode='view' >
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="boardListMaxURL" portletMode='view' windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="getBoardMaxRenderUrl" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
	<liferay-portlet:param name="myRender" value="getBoardRender"/>
	<liferay-portlet:param name="groupBoardSeq" value="${boardMap.boardSeq}" />
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="getBoardRenderURL" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="getBoardRender"/>
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="groupBoardSeq" value="${boardMap.boardSeq}" />
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL var="deleteBoardActionUrl" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myAction" value="deleteBoardAction"/>
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="deleteSingleEdisonFileURL" escapeXml="false" id="deleteSingleEdisonFile" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	
	<liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="addReplyBoardActionURL">
	<liferay-portlet:param name="myAction" value="addReplyBoardAction"/>
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="updateReplyBoardActionURL">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
	<liferay-portlet:param name="myAction" value="updateReplyBoardAction"/>
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="deleteReplyBoardActionUrl">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
	<liferay-portlet:param name="myAction" value="deleteReplyBoardAction"/>
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
</liferay-portlet:actionURL>

<%
	boolean isCustomAdmin = GetterUtil.get(request.getAttribute("isCustomAdmin"), false);
%>

<style>
.subtitlearea{
	margin-left: 10px;
}

@media screen and (max-width:767px) {
	.boardbtnbox{
		text-align: right;
	}
	
	.boardbtnbox input[type=button]{
		height: 40px;
		padding: 5px 19px;
		display: inline-block;
	}
}

@media screen and (max-width:1000px) {
	#<portlet:namespace/>noticeFileListTitle{
		width: 22% !important;
	}
}
</style>

<c:choose>
	<c:when test="${replyBoardSeq == null || replyBoardSeq == ''}">
		<c:set var="actionUrl" value="<%=addReplyBoardActionURL%>"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="<%=updateReplyBoardActionURL%>"/>	
	</c:otherwise>
</c:choose>
<script type="text/javascript" src="${contextPath}/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<c:choose>
	<c:when test="${empty redirectName }">
		<h2>
			<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
			<span class="subtitlearea">
				${boardDivTitle}
			</span>
		</h2>
	</c:when>
	<c:otherwise>
		<h2>
			<a onClick="historyBack<portlet:namespace/>()" style="cursor: pointer;"> ${redirectName } </a>  > ${boardDivTitle}
		</h2>
	</c:otherwise>
</c:choose>


<form name="<portlet:namespace/>viewForm" action="<%=getBoardRenderURL%>" method="post" style="margin:0px;">
	<input type="hidden" id="<portlet:namespace/>RENDER_SORT" name="<portlet:namespace/>RENDER_SORT" value="VIEW">
	<input type="hidden" id="<portlet:namespace/>replyBoardSeq" name="<portlet:namespace/>replyBoardSeq" value="">
	<input type="hidden" id="<portlet:namespace/>select_languageId" name="<portlet:namespace/>select_languageId" value="<%= themeDisplay.getLanguageId()%>">
</form>

<div class="table1_list">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr class="tablebgtr01">
		<c:choose>
			<c:when test="${boardDiv.divNm=='FAQ' || boardDiv.divNm=='QNA'}">
				<td width="75%" class="title"><liferay-ui:message key='edison-appstore-myapp-question' /> : ${boardMap.title} </td>
			</c:when>
			<c:otherwise>
				<td width="75%" class="title"><liferay-ui:message key='edison-table-list-header-title' /> : ${boardMap.title} </td>
			</c:otherwise>
		</c:choose>
		
		<td width="25%" class="bold TR">${boardMap.writerName} ｜ ${boardMap.writerDate} </td>
	</tr>
	<tbody>
	<tr style="height: 230px;">
		<td colspan="2" style="vertical-align: top;">
			${ boardMap.content }
		</td>
	</tr>
	
	<c:choose>
		<c:when test="${boardDiv.fileUpLoadUseYn == true}">
			<c:if test="${fn:length(fileList) > 0}">
			<tr>
				<td colspan="2">
					<div id="<portlet:namespace/>noticeFileListTitle" style="float: left; width: 10%;">
						<img src="${contextPath}/images/fileicon.png" width="19" height="21" />&nbsp;<liferay-ui:message key='edison-table-content-header-attachments' />&nbsp;&nbsp;｜&nbsp;&nbsp;
					</div>
					<div style="float: left; width: 78%;">
						<c:forEach items="${fileList}" var="fileMap">
							<span style="cursor:pointer; margin-right:10px; white-space: nowrap;" onclick="<portlet:namespace/>fileDownload('${fileMap.fileEntryId }')" class="onMouseHover">
								<img src="${contextPath}/images/fileicon2.png" width="16" height="16" />
								${fileMap.fileTitle }
							</span>
						</c:forEach>
					</div>
				</td>
			</tr>	
			</c:if>
		</c:when>
	</c:choose>
	</tbody>
	</table>
</div>  
<br>
<div class="boardbtnbox" style="text-align: right; width: 99%;">
	<c:if test="${themeDisplay.isSignedIn()}">
		<c:if test="${themeDisplay.getUserId() eq boardMap.writerId || isCustomAdmin }">
			<c:choose>	
				<c:when test="${isPortal == false && boardMap.groupId ne boardGroupId}"></c:when>
				<c:otherwise>
					<input type="button" class="btn btn-default" style="margin-right:5px;  width: 70px;" onClick="javascript:<portlet:namespace/>deleteBoard(); return false;" value="<liferay-ui:message key='edison-button-board-delete' />" />
					<input type="button" class="btn btn-default" style="margin-right:5px;  width: 70px;" onClick="javascript:<portlet:namespace/>modify('${maxWindowStatus}'); return false;" value="<liferay-ui:message key='edison-button-board-modify' />" />
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:if>
	<input type="button" class="btn btn-default" onClick="goList<portlet:namespace/>('${maxWindowStatus}');" value="<liferay-ui:message key='edison-virtuallab-surveyResultList-list' />" style=" width: 70px;" />
	<c:if test="${redirectURL ne '' and redirectName ne ''}">
		<input type="button" class="btn btn-default" onClick="historyBack<portlet:namespace/>()" value="${redirectName}" style=" width: 70px;" />
	</c:if>
	<c:if test="${redirectURL ne '' and redirectName eq ''}">
		<input type="button" class="btn btn-default" onClick="historyBack<portlet:namespace/>()" value="<liferay-ui:message key='edison-virtuallab-move' />" />
	</c:if>
</div>


<c:if test="${boardDiv.replyYn==true}">
	<div class="h4">
		<liferay-ui:message key='edison-button-board-reply' />
	<%
		if(isLogin){
	%>
		<c:if test="${replyBoardSeq == null || replyBoardSeq == ''}">			
		<div id="replyInputButton" style="float: right;">
			<input type="button" onclick="replyInputFormSlideDown();" value="<liferay-ui:message key='edison-button-board-write' />" class="btn btn-default">
		</div>
		</c:if>
	<%
		}
	%>
	</div>
</c:if>

	<c:if test="${boardDiv.replyYn==true}">
		<form name="<portlet:namespace/>replyForm" method="post"  action="${actionUrl}" onsubmit="return boardInputFormCheck<portlet:namespace/>()" enctype="multipart/form-data">
			<input type="hidden" name="<portlet:namespace/>groupBoardSeq" value="${boardMap.boardSeq}">
			<input type="hidden" name="<portlet:namespace/>RENDER_SORT" value="REPLY">
			<input type="hidden" id="<portlet:namespace/>replyBoardSeq" name="<portlet:namespace/>replyBoardSeq" value="${replyBoardSeq}">
			<div class="table1_list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<colgroup id="boardColgroup">
						<col width="7%" />
						<col width="20%" />
						<col width="8%" />
						<col/>
					</colgroup>

<%
				ArrayList	replyList	= (ArrayList) request.getAttribute("replyList");
				Map map = null;
				List replyFileList = null;
				Map replyFileMap = null;
				
				if(isLogin && (replyList == null || replyList.size()==0 || CustomUtil.strNull(request.getAttribute("replyBoardSeq")).equals(""))){
%>

						<tr>
							<td colspan="4" id="replyInputTd" style="border-bottom: 0px solid rgb(204, 204, 204);padding:0px;">
								<div id="replyInputForm" style="border-bottom: 1px solid rgb(204, 204, 204);padding:10px;">
									<textarea id="<portlet:namespace/>content" name="<portlet:namespace/>content" style="width:95%;height:200px;"></textarea>
									<c:choose>
										<c:when test="${boardDiv.fileUpLoadUseYn == true}">
											<div>
												<div style="float: left;"><input type="button" value="<liferay-ui:message key='edison-button-file-add' />" class="btn btn-default" onClick="moreFileTag()" style="cursor:pointer;"/>&nbsp;&nbsp;</div>
												<div id="fileTDArea" style="float: left;"/>	
											</div>
										</c:when>
									</c:choose>
									<div class="boardbtnbox">
										<div class="boardbtn1 boardbtnboxtoppd">
											<input type="button" name="<portlet:namespace />fullsize" id="fullsize" onclick="submitForm<portlet:namespace/>();" value="<liferay-ui:message key='edison-virtuallab-save' />" class="btn btn-default">
											<input type="button" name="<portlet:namespace />fullsize" id="fullsize" onclick="replyInputFormSlideUp();" value="<liferay-ui:message key='cancel' />" class="btn btn-default">											
										</div>
									</div>
								</div>	
							</td>
						</tr>
<%
					}					
					
					if(replyList != null || replyList.size()>0){
						for(int i=0;i<replyList.size();i++){
							map = (Map)replyList.get(i);
							
							if(
								(themeDisplay.getUserId() == Long.parseLong(CustomUtil.strNull(map.get("writerId"))) || isCustomAdmin)
								&& CustomUtil.strNull(request.getAttribute("replyBoardSeq")).equals(CustomUtil.strNull(map.get("boardSeq")))
								){
%>	
								<tr>
									<td colspan="4">
										<textarea id="<portlet:namespace/>content" name="<portlet:namespace/>content" style="width:95%;height:200px;"><%=CustomUtil.strNull(map.get("content")) %></textarea>
<%
										replyFileList = (List)map.get("fileList");
										if(replyFileList != null && replyFileList.size() > 0){
%>
											<div id="fileListDiv">
<%
												for(int r=0;r<replyFileList.size();r++){
													replyFileMap = (Map)replyFileList.get(r);
%>
													<span style="cursor:pointer" onclick="<portlet:namespace/>fileDownload('<%=CustomUtil.strNull(replyFileMap.get("fileEntryId"))%>')" class="onMouseHover">
														<%=CustomUtil.strNull(replyFileMap.get("fileTitle"))%>
														<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
													</span>
													<span style="cursor:pointer" onclick="deleteSingleEdisonFile('<%=CustomUtil.strNull(replyFileMap.get("fileEntryId"))%>', '<%=CustomUtil.strNull(replyFileMap.get("fileUserId"))%>', '<%=CustomUtil.strNull(map.get("boardSeq"))%>')">
														<u>[delete]</u>
													</span>
													<br>	
<%
												}
%>											
											</div>
<%
										}//if(replyFileList != null && replyFileList.size() > 0){										
%>
										<div>
											<div style="float: left;"><input type="button" value="<liferay-ui:message key='edison-button-file-add' />" class="btn btn-default" onClick="moreFileTag()" style="cursor:pointer;"/>&nbsp;&nbsp;</div>
											<div id="fileTDArea" style="float: left;"/>	
										</div>
										<div class="boardbtnbox">
											<div class="boardbtn1 boardbtnboxtoppd">
												<input type="button" name="<portlet:namespace />fullsize" id="fullsize" onclick="submitForm<portlet:namespace/>();" value="<liferay-ui:message key='edison-button-board-modify' />" class="btn btn-default">
												<input type="button" name="<portlet:namespace />fullsize" id="fullsize" onclick="replyWriteCancel('${maxWindowStatus}');" value="<liferay-ui:message key='cancel' />" class="btn btn-default">
											</div>
										</div>
									</td>
								</tr>
<%	
							}else{									
%>
								<tr>
									<td colspan="4">
										<div style="width: 100%;text-align: right;">
										
											<%=CustomUtil.strNull(map.get("writerDate")) %>&nbsp;&nbsp;<%=CustomUtil.strNull(map.get("writerName")) %>  
<%			
											if(themeDisplay.getUserId() == Long.parseLong(CustomUtil.strNull(map.get("writerId"))) || isCustomAdmin){
%>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="replyUpdate('<%=CustomUtil.strNull(map.get("boardSeq")) %>','${maxWindowStatus}'); return false;"><u><liferay-ui:message key='edison-button-board-modify' /></u></a>
												<a href="#" onclick="replyDelete('<%=CustomUtil.strNull(map.get("boardSeq")) %>'); return false;"><u><liferay-ui:message key='edison-button-board-delete' /></u></a>
<%
											}
%>
										</div>
										<%=CustomUtil.strNull(map.get("content")) %>
<%
								replyFileList = (List)map.get("fileList");
								if(replyFileList != null && replyFileList.size() > 0){
%>
										<div id="fileListDiv">
<%
											for(int r=0;r<replyFileList.size();r++){
												replyFileMap = (Map)replyFileList.get(r);
%>
												<div style="cursor:pointer" onclick="<portlet:namespace/>fileDownload('<%=CustomUtil.strNull(replyFileMap.get("fileEntryId"))%>')" class="onMouseHover">
													<%=CustomUtil.strNull(replyFileMap.get("fileTitle"))%>
													<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
												</div>
<%
											}
%>
										</div>	
<%
								}//if(replyFileList != null && replyFileList.size() > 0){
%>
									</td>
								</tr>
<%
							}//if
						}//for
					}//if(replyList != null || replyList.size()>0){
%>
					
				</table>
			</div>
		</form>

	</c:if>


<script type="text/javascript">
function goList<portlet:namespace/>(maxWindowStatus){
	if(maxWindowStatus=='Y'){
		document.<portlet:namespace/>viewForm.action = "<%=boardListMaxURL%>";
		document.<portlet:namespace/>viewForm.submit()
	}else{
		location.href = "<%=boardListURL%>";
	}
}

function replyInputFormSlideDown(){
	$("#replyInputButton").css("display","none");

	$( "#replyInputForm" ).slideDown( "slow", function() {
		//complete process
	});	
}

function replyInputFormSlideUp(){		
	$( "#replyInputForm" ).slideUp( "slow", function() {
		$("#replyInputButton").css("display","block");
	});		
}


function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

function <portlet:namespace/>modify(maxWindowStatus){
	document.<portlet:namespace/>viewForm.<portlet:namespace/>replyBoardSeq.value="";
	document.<portlet:namespace/>viewForm.<portlet:namespace/>RENDER_SORT.value="UPDATE";
	if(maxWindowStatus=='Y'){
		document.<portlet:namespace/>viewForm.action = "<%=getBoardMaxRenderUrl%>";
	}else{
		document.<portlet:namespace/>viewForm.action = "<%=getBoardRenderURL%>";
	}
	document.<portlet:namespace/>viewForm.submit()
}

function <portlet:namespace/>deleteBoard(maxWindowStatus){
	if(confirm(Liferay.Language.get('data-delete-confirm'))){
		document.<portlet:namespace/>viewForm.action = "<%=deleteBoardActionUrl%>";
		document.<portlet:namespace/>viewForm.submit()
	}
}

function replyUpdate(p_replyBoardSeq,maxWindowStatus){
	document.<portlet:namespace/>viewForm.<portlet:namespace/>replyBoardSeq.value=p_replyBoardSeq;
	document.<portlet:namespace/>viewForm.<portlet:namespace/>RENDER_SORT.value="REPLY";
	
	if(maxWindowStatus=='Y'){
		document.<portlet:namespace/>viewForm.action = "<%=getBoardMaxRenderUrl%>";
	}else{
		document.<portlet:namespace/>viewForm.action = "<%=getBoardRenderURL%>";
	}
	<portlet:namespace/>viewForm.submit();
}

function replyWriteCancel(maxWindowStatus){
	document.<portlet:namespace/>viewForm.<portlet:namespace/>replyBoardSeq.value="";
	document.<portlet:namespace/>viewForm.<portlet:namespace/>RENDER_SORT.value="REPLY";
	
	if(maxWindowStatus=='Y'){
		document.<portlet:namespace/>viewForm.action = "<%=getBoardMaxRenderUrl%>";
	}else{
		document.<portlet:namespace/>viewForm.action = "<%=getBoardRenderURL%>";
	}
	<portlet:namespace/>viewForm.submit();
}


function replyDelete(p_replyBoardSeq){
	if(confirm(Liferay.Language.get('data-delete-confirm'))){
		document.<portlet:namespace/>viewForm.<portlet:namespace/>replyBoardSeq.value=p_replyBoardSeq;
		document.<portlet:namespace/>viewForm.<portlet:namespace/>RENDER_SORT.value='REPLY_DELETE';
		<portlet:namespace/>viewForm.action = "<%=deleteReplyBoardActionUrl%>";
		<portlet:namespace/>viewForm.submit();
	}
}

var fileIndex = 0;
function moreFileTag()
{
	fileIndex++;
	var frmTag = "<div id=\"fileDiv"+fileIndex+"\">";
	frmTag += "<input type=\"file\" name=\"addfile\" style =\"width:500px;border:1px solid #CCCCCC;margin-bottom:2px;\">&nbsp;";
	frmTag += "<input type=\"button\" value=\"delete\" style=\"cursor:pointer;\" class=\"btn btn-default\" onClick=\"deleteFileTag(\'fileDiv"+fileIndex+"\')\"/>";
	frmTag += "</div>";

	$("#fileTDArea").append(frmTag);
}

function deleteFileTag(objId){	
	$("#"+objId).remove();
	if($(':input[name*=addfile]').length == 0){
		moreFileTag();
	} 
}
 
function submitForm<portlet:namespace/>(){
	if(
		document.<portlet:namespace/>replyForm.onsubmit &&
		!document.<portlet:namespace/>replyForm.onsubmit()
	){
		return;
	}
 document.<portlet:namespace/>replyForm.submit();
}

function boardInputFormCheck<portlet:namespace/>(){
	oEditors.getById["<portlet:namespace/>content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	if($("#<portlet:namespace/>content").val()==""){
		alert("<liferay-ui:message key='edison-board-enter-content-alert' />");
		return false;
	}else{
		return true;
	}
	
	return false;
}

<%if(isLogin){ %> 
	<c:if test="${boardDiv.replyYn==true}">
		moreFileTag();
		var oEditors = [];	
	
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "<portlet:namespace/>content",
			sSkinURI: "${contextPath}/editor/SmartEditor2Skin.html",
			fCreator: "createSEditor2",
			fOnAppLoad : function(){
				$("#replyInputForm").css("display", "none");
			}
		});
		
	</c:if>
<%}%>

function deleteSingleEdisonFile(p_fileEntryId, p_fileUserId, p_boardSeq){
	if(!confirm(Liferay.Language.get('data-delete-confirm'))){return;}
	var deleteForm = {
					"<portlet:namespace/>fileEntryId" : p_fileEntryId,
					"<portlet:namespace/>fileUserId" : p_fileUserId,
					"<portlet:namespace/>boardSeq" : p_boardSeq
					};
	jQuery.ajax({
		type: "POST",
		url: "<%=deleteSingleEdisonFileURL%>",
		data: deleteForm,
		async : false,
		success: function(data) {
			var fileList = data.fileList;
			var resultMsg = data.resultMsg;
			
			if(resultMsg=="SUCCESS"){
				$("#fileListDiv").html("");		

				var fileHtml = "";
				if(fileList.length == 0){
				}else{
					for(var i = 0 ; i < fileList.length; i++ ){					
						fileHtml += "<span style=\"cursor:pointer\" onclick=\"<portlet:namespace/>fileDownload(\'"+fileList[i].fileEntryId+"\')\">";
						fileHtml += fileList[i].fileTitle;
						fileHtml += " <img src=\"<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png\" width=\"16\" height=\"16\" />";						
						fileHtml += "</span>";						
						fileHtml += "&nbsp;&nbsp;";
						fileHtml += "<span style=\"cursor:pointer\" onclick=\"deleteSingleEdisonFile(\'"+fileList[i].fileEntryId+"\', \'"+fileList[i].fileUserId+"\', \'"+p_boardSeq+"\')\"><u>[delete]</u></span>";
						fileHtml += "<br>";
					}
					
					$("#fileListDiv").html(fileHtml);
				}			
			}else if(resultMsg=="DELETE_FAIL"){
				alert("delete file error!");	
			}
		},error:function(data,e){ 
			alert("deleteSingleEdisonFile System error!");	
		},complete:function(){
			
		}
	});
}

function historyBack<portlet:namespace/>(){
	location.href = "${redirectOrignURL}";
}

</script>
