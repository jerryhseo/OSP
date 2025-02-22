<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringBundler"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil"%>
<%@ page import="com.liferay.portal.model.Group"%>

<%!
	public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit_article_content.jsp";
%>

<%
	long originalBoardPlid = 0;
	String boardPlid = CustomUtil.strNull((portletPreferences.getValue("originalBoardPlid", "0")));
	
	if(boardPlid.equals("")){
		originalBoardPlid = 0;
	}else{
		originalBoardPlid = Long.parseLong(boardPlid);
	}
	String originalBoardPortletName = CustomUtil.strNull((portletPreferences.getValue("originalBoardPortletName", "")));
	
	if(originalBoardPlid == 0) originalBoardPlid = plid;
	
	String defaultLanguageId = (String)request.getAttribute("edit_article.jsp-defaultLanguageId");
	String toLanguageId = (String)request.getAttribute("edit_article.jsp-toLanguageId");
%>

 <%
 		Map<String, String> fileBrowserParamsMap = (Map<String, String>)request.getAttribute("liferay-ui:input-editor:fileBrowserParams");
 		
 		String fileBrowserParams = marshallParams(fileBrowserParamsMap);
		
 		StringBundler sb = new StringBundler(8);
		String portletId = portletDisplay.getRootPortletId();
		String mainPath = themeDisplay.getPathMain();

		String doAsUserId = themeDisplay.getDoAsUserId();
		long doAsGroupId = themeDisplay.getDoAsGroupId();
 		Locale siteLocale = themeDisplay.getLocale();
		String doasLocale = siteLocale.getLanguage();

		if (doAsGroupId == 0) {
			doAsGroupId = (Long)themeDisplay.getSiteGroupId();
		}
		
		Group group = GroupLocalServiceUtil.getGroup(doAsGroupId);
		String currentFolder = "/"+doAsGroupId+" - "+"edison"+"/";
		
		sb.append(mainPath);
		sb.append("/portal/fckeditor?p_p_id=");
		sb.append(HttpUtil.encodeURL(portletId));
		sb.append("&doAsUserId=");
		sb.append(HttpUtil.encodeURL(doAsUserId));
		sb.append("&doAsGroupId=");
		sb.append(HttpUtil.encodeURL(String.valueOf(doAsGroupId)));
		sb.append(fileBrowserParams);

		String connectorURL = HttpUtil.encodeURL(sb.toString());
%>

<liferay-portlet:renderURL var="boardListURL" portletMode='view'>
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

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="deleteSingleEdisonFileURL" escapeXml="false" id="deleteSingleEdisonFile" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	
	<liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="addBoardActionUrl" portletMode="view">
	<portlet:param name="myAction" value="addBoardAction" />
	<portlet:param name="boardSeq" value="${boardMap.boardSeq }" />
	
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="updateBoardActionUrl" portletMode="view" >
	<portlet:param name="myAction" value="updateBoardAction"/>
	<portlet:param name="boardSeq" value="${boardMap.boardSeq }" />
	
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
</liferay-portlet:actionURL>
<liferay-portlet:renderURL copyCurrentRenderParameters="false" var="getBoardMaxRenderURL" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq }" />
	<liferay-portlet:param name="myRender" value="getBoardRender"/>
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
</liferay-portlet:renderURL>
<liferay-portlet:renderURL copyCurrentRenderParameters="false" var="getBoardRenderURL">
	<liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
	<liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq }" />
	<liferay-portlet:param name="myRender" value="getBoardRender"/>
	
	<liferay-portlet:param name="customId" value="${customId}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
	<liferay-portlet:param name="isCustomAdmin" value="${isCustomAdmin}" />
	<liferay-portlet:param name="isDefaultUserWrite" value="${isDefaultUserWrite}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	
	<liferay-portlet:param name="searchValue" value="${searchValue}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="maxWindowStatus" value="${maxWindowStatus}" />
</liferay-portlet:renderURL>

<style>
.dp_ib {display: inline-block !important; margin: 1px;}

.subtitlearea{
	margin-left: 10px;
}

@media screen and (max-width:1000px) {
	.<portlet:namespace/>popup-date-label{
		display: block !important;
	}
	
	#<portlet:namespace/>noticeFileAddBtn{
		width: 25% !important;
		display: inline-block !important;
		margin-bottom: 10px !important;
	}
	
	#<portlet:namespace/>fileTDArea input[type=file]{
		width: 75% !important;
	}
	
	#<portlet:namespace/>fileTDArea input[type=button]{
		width: 20% !important;
	}
	
	#<portlet:namespace/>fileListTitle{
		margin-bottom: 10px !important;
	}
	
	.dp_ib{
		width: auto;
		float: left;
	}
	
	.boardbtnbox input[type=button]{
		height: 40px !important;
		padding: 0px 19px !important;
		display: inline-block !important;
	}
	
}
</style>

<c:choose>
	<c:when test="${boardMap.boardSeq == null || boardMap.boardSeq == ''}">
		<c:set var="actionUrl" value="<%=addBoardActionUrl%>"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="<%=updateBoardActionUrl%>"/>	
	</c:otherwise>
</c:choose>

<form id="boardInputForm<portlet:namespace/>" name="boardInputForm<portlet:namespace/>" method="POST"  action="${actionUrl}" onsubmit="return boardInputFormCheck<portlet:namespace/>()" enctype="multipart/form-data">
	<input type="hidden" name="<portlet:namespace/>RENDER_SORT" value="UPDATE">
	<input type="hidden" id="<portlet:namespace/>currentLocale" name="<portlet:namespace/>current_languageId" value="${select_languageId }">

	<div class="table1_list table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h2>
				<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
				<span class="subtitlearea">
					${boardDivTitle}
				</span>
			</h2>
		</div>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-bordered table-hover edison-table">
			<colgroup id="boardColgroup">
				<col width="15%" />
				<col width="85%" />
			</colgroup>
			
			<c:choose>
				<c:when test="${boardDiv.divNm=='NOTICE' || boardDiv.divNm=='FAQ'}">
					<tr>			
						<th><liferay-ui:message key='edison-board-select-language' /></th>
						<td>
								<%
								Locale[] availLocales = LanguageUtil.getAvailableLocales();
								for(int i=0;i<availLocales.length;i++){
								%>
								<label style="display:inline;">
								<input type="radio" id="<portlet:namespace/>select_languageId" name="<portlet:namespace/>select_languageId" value="<%=availLocales[i]%>" <%if(CustomUtil.strNull(request.getAttribute("select_languageId")).equals(availLocales[i].toString())) out.print("checked"); %>
								<c:if test="${boardMap.boardSeq != null }">
								 	onChange="changeLanguage()"
								</c:if>
								/>
								<img width="17px" height="12px" src="<%=themeDisplay.getPathThemeImages() %>/language/<%=availLocales[i]%>.png" />
								<liferay-ui:message key='<%="edison-board-radiobutton-" + availLocales[i].toString()%>' />
								</label>
								<%
								}
								%>
						</td>
					</tr>
			
					<c:if test="${boardDiv.popupYn==true && virClassNotice eq 'N'}">	
					<tr>
						<th><liferay-ui:message key='edison-board-select-popup' /></th>
						<td colspan="3">
							<label for="<portlet:namespace/>popupYn">
								<input type="checkbox" id="<portlet:namespace/>popupYn" name="<portlet:namespace/>popupYn" value="true" <c:if test="${boardMap.popupYn=='true'}">checked</c:if>> 
								<liferay-ui:message key='edison-board-use-popup' />
							</label>
						</td>
					</tr>	
					<tr>
						<th><liferay-ui:message key='edison-board-use-popup-date' /></th>
						<td>
							<label class="<portlet:namespace/>popup-date-label">
								<input name="<portlet:namespace/>popupStartDt" id="<portlet:namespace/>popupStartDt" readonly="readonly" value="${boardMap.popupStartDt }"/> 
							</label>
							<label class="<portlet:namespace/>popup-date-label">
								 ~ <input name="<portlet:namespace/>popupEndDt" id="<portlet:namespace/>popupEndDt" readonly="readonly" value="${boardMap.popupEndDt }"/>
							</label>
						</td>
					</tr>
					</c:if>
					<c:if test="${boardDiv.divCd=='100' && isPortal == true && virClassNotice eq 'N'}">
						<th><liferay-ui:message key='edison-virtuallab-tablerow-site' /></th>
						<td>
							<div>
								<label class="dp_ib" for="<portlet:namespace/>allSite"><input type="checkbox" id="<portlet:namespace/>allSite">&nbsp;<liferay-ui:message key='full' /></label>
								<c:forEach var="group" items="${groupList}">
									<c:set var="groupChecked" value=""></c:set>
									<c:if test="${!empty siteGroups}">
										<c:forEach var="siteGroup" items="${siteGroups}">
											<c:if test="${group.groupId eq siteGroup}">
												<c:set var="groupChecked" value="checked"></c:set>
											</c:if>
										</c:forEach>
									</c:if>
									<label class="dp_ib" for="<portlet:namespace/>siteGroup_${group.groupId }" >&nbsp;<input type="checkbox" id="<portlet:namespace/>siteGroup_${group.groupId }" name= "<portlet:namespace/>siteGroup" value="${group.groupId }" ${groupChecked}>
									&nbsp;${group.name }</label>
								</c:forEach>
							</div>
						</td>
					</c:if>
				</c:when>
				<c:otherwise>
						<input type="hidden" id="<portlet:namespace/>select_languageId" name="<portlet:namespace/>select_languageId" value="<%=themeDisplay.getLocale()%>">
				</c:otherwise>
			</c:choose>

			<tr>
				<c:choose>
					<c:when test="${boardDiv.divNm=='FAQ'}">
						<th><liferay-ui:message key='edison-appstore-myapp-question' /></th>
					</c:when>
					<c:otherwise>
						<th><liferay-ui:message key='title' /></th>
					</c:otherwise>
				</c:choose>
				<td><input type="text" id="<portlet:namespace/>title" class="form-control" name="<portlet:namespace/>title" style="width:100%; margin:0px;" value="${boardMap.title }"> </td>
			</tr>
			<tr>
				<td colspan="2" style="height:300px;">
					<textarea id="<portlet:namespace/>content" name="<portlet:namespace/>content" style="width:100%;height:300px;">${boardMap.content }</textarea>
				</td>
			</tr>
		</table>
		
		<c:choose>
			<c:when test="${boardDiv.fileUpLoadUseYn == true}">
			
			<div style="border-bottom: 1px solid #dddddd; width: 100%; display: inline-block; padding: 5px 0px;">
				<div class="col-md-2 col-sm-12" style="text-align: center; border-right: 1px solid #dddddd;">
					<div style="font-size: 15px;">
						<liferay-ui:message key='edison-table-list-header-file' />&nbsp;
						<input type="button" id="<portlet:namespace/>noticeFileAddBtn"  value="<liferay-ui:message key='edison-button-file-add' />" class="btn btn-default" onClick="moreFileTag()" style="cursor:pointer;"/>	
					</div>
				</div>
				
				<div id="<portlet:namespace/>fileTDArea" class="col-md-10 col-sm-12" ></div>
			</div>
			
			<div style="border-bottom: 1px solid #dddddd; width: 100%; display: inline-block; padding: 5px 0px;">
				<div id="<portlet:namespace/>fileListTitle" class="col-md-2 col-sm-12" style="text-align: center; border-right: 1px solid #dddddd; color: #777">
					<img src="${contextPath}/images/fileicon.png" width="19" height="21" />
					&nbsp;<liferay-ui:message key='edison-table-content-header-attachments' />
				</div>
				<div id="<portlet:namespace/>fileListDiv" class="col-md-10 col-sm-12">
					<c:forEach items="${fileList}" var="fileMap">
						<span style="cursor: pointer;" onclick="<portlet:namespace/>fileDownload('${fileMap.fileEntryId }')" class="onMouseHover">
							<img src="${contextPath}/images/fileicon2.png" width="16" height="16" />
							${fileMap.fileTitle }
						</span>
						&nbsp;&nbsp;
						<span style="cursor:pointer" onclick="deleteSingleEdisonFile<portlet:namespace/>('${fileMap.fileEntryId }', '${fileMap.fileUserId }')">
							<u>[delete]</u>
						</span>
						<br>
					</c:forEach>
				</div>
			</div>
			</c:when>
		</c:choose>
	</div>
<br> 
	<div class="boardbtnbox" style="text-align: right;">
		<input type="button" class="btn btn-default" style="margin-right:5px; width: 70px;" onClick="submitForm<portlet:namespace/>(); return false;" value="<liferay-ui:message key='edison-virtuallab-save' />" />
		<input type="button" class="btn btn-default" onClick="goList<portlet:namespace/>('${maxWindowStatus}');" value="<liferay-ui:message key='edison-button-board-list' />" style="width: 70px;" />
	</div>
</form>

<form name="<portlet:namespace/>viewForm" action="<%=boardListURL%>" method="post" style="margin:0px;">
	
</form>
<%!

public String marshallParams(Map<String, String> params) {
	StringBundler sb = new StringBundler();

	if (params != null) {
		for (Map.Entry<String, String> configParam : params.entrySet()) {
				sb.append(StringPool.AMPERSAND);
				sb.append(configParam.getKey());
				sb.append(StringPool.EQUAL);
				sb.append(HttpUtil.encodeURL(configParam.getValue()));
		}
	}

	return sb.toString();
}
%>

<script type="text/javascript" src="${contextPath}/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="${contextPath}/editor/ckeditor/ckeditor.js" charset="utf-8"></script> 
<script type="text/javascript">
$(document).ready(function(){
	var allChkLength = $("input[name=<portlet:namespace/>siteGroup]").length;
	var uncheckedLength = $("input[name=<portlet:namespace/>siteGroup]:checkbox:not(:checked)").length;
	var checkedLength = $("input[name=<portlet:namespace/>siteGroup]:checkbox:checked").length;

	/*분야를 낱개로 모두선택시 전체선택체크박스 체크*/
	if(allChkLength == checkedLength){
		$("#<portlet:namespace/>allSite").prop("checked",true)
	}	
});

var fileIndex = 0;
function moreFileTag()
{	
	fileIndex++;
	var marginTop = "";
	if(1<fileIndex){
		marginTop = "1%";
	}
	var frmTag = "<div id=\"fileDiv"+fileIndex+"\">";
	frmTag += "<input type=\"file\" name=\"addfile\" style =\"width:50%;border:1px solid #CCCCCC;margin:0% 1% 2% 0%; float:left;\">";
	frmTag += "<input type=\"button\" value=\"delete\" style=\"cursor:pointer; margin:0% 1% 2% 0%;\" class=\"btn btn-default\" onClick=\"deleteFileTag(\'fileDiv"+fileIndex+"\')\"/>";
	frmTag += "</div>";
	
	$("#<portlet:namespace/>fileTDArea").append(frmTag);
}

function deleteFileTag(objId){	
	$("#"+objId).remove();
	if($(':input[name*=addfile]').length == 0){
		moreFileTag();
	} 
}

function goList<portlet:namespace/>(maxWindowStatus){
	if(maxWindowStatus=='Y'){
		document.<portlet:namespace/>viewForm.action = "<%=boardListMaxURL%>";
		document.<portlet:namespace/>viewForm.submit()
	}else{
		location.href = "<%=boardListURL%>";
	}
}

function submitForm<portlet:namespace/>(){
    if(
    	document.boardInputForm<portlet:namespace/>.onsubmit &&
    	!document.boardInputForm<portlet:namespace/>.onsubmit()
    ){
        return false;
    }
 	document.boardInputForm<portlet:namespace/>.submit();
}

function fRemoveHtmlTag(string) { 
	   var objReplace = new RegExp(); 
	   objReplace = /[<][^>]*[>]/gi; 
	   string.replace(objReplace, "");
	   return string.replace(objReplace, ""); 
}

function boardInputFormCheck<portlet:namespace/>(){
	   // 에디터의 내용이 textarea에 적용된다.
	var title = $("#<portlet:namespace/>title").val();
	var contentValue = CKEDITOR.instances['<portlet:namespace/>content'].getData();
// 	var content = $.trim(contentValue.replace(/&nbsp;/g, ''));
	var content = contentValue;
	if($.trim(title)==""){
		alert("<liferay-ui:message key='edison-board-enter-title-alert' />");
		$("#<portlet:namespace/>title").val("");
		$("#<portlet:namespace/>title").focus();
		$("input:radio[name='<portlet:namespace/>select_languageId']:radio[value='${select_languageId }']").prop("checked",true);
		return false;
	}else if($.trim(content)==""){
		alert("<liferay-ui:message key='edison-board-enter-content-alert' />");
		$("input:radio[name='<portlet:namespace/>select_languageId']:radio[value='${select_languageId }']").prop("checked",true);
		return false;
	}else{
		$("#<portlet:namespace/>content").val(content);
 		return true;
	}
	
	return false;
}


function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

function changeLanguage(){
	if(confirm("<liferay-ui:message key='edison-board-save-alert' />")){		
		submitForm<portlet:namespace/>();
	}else{
		var reloadUrl = "<%=getBoardRenderURL%>"
		if("${maxWindowStatus}"=="Y"){
			reloadUrl = "<%=getBoardMaxRenderURL%>";
		}
		reloadUrl += "&<portlet:namespace/>RENDER_SORT=UPDATE";
			reloadUrl += "&<portlet:namespace/>select_languageId="+$("input[name=<portlet:namespace/>select_languageId]:radio:checked").val();
		location.href = reloadUrl;
	}
}

function deleteSingleEdisonFile<portlet:namespace/>(p_fileEntryId, p_fileUserId){
	if(!confirm(Liferay.Language.get('data-delete-confirm'))){return;}
	
	var deleteForm = {
					"<portlet:namespace/>fileEntryId" : p_fileEntryId,
					"<portlet:namespace/>fileUserId" : p_fileUserId
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
				$("#<portlet:namespace/>fileListDiv").html("");			
	
				var fileHtml = "";
				if(fileList.length == 0){				
				}else{
					for(var i = 0 ; i < fileList.length; i++ ){					
						fileHtml += "<span style=\"cursor:pointer\" onclick=\"<portlet:namespace/>fileDownload(\'"+fileList[i].fileEntryId+"\')\">";
						fileHtml += fileList[i].fileTitle;
						fileHtml += " <img src=\"<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png\" width=\"16\" height=\"16\" />";						
						fileHtml += "</span>";						
						fileHtml += "&nbsp;&nbsp;";
						fileHtml += "<span style=\"cursor:pointer\" onclick=\"deleteSingleEdisonFile<portlet:namespace/>(\'"+fileList[i].fileEntryId+"\', \'"+fileList[i].fileUserId+"\')\"><u>[delete]</u></span>";
						fileHtml += "<br>";
					}
					$("#<portlet:namespace/>fileListDiv").html(fileHtml);
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

moreFileTag();

/*분야 체크박스 전체선택해제*/

$("#<portlet:namespace/>allSite").click(function(){
    if($("#<portlet:namespace/>allSite").prop("checked")){
        $("input[name=<portlet:namespace/>siteGroup]").prop("checked",true);
    }else{
        $("input[name=<portlet:namespace/>siteGroup]").prop("checked",false);
    }
});
$("input[name=<portlet:namespace/>siteGroup]").click(function(){
	var allChkLength = $("input[name=<portlet:namespace/>siteGroup]").length;
	var uncheckedLength = $("input[name=<portlet:namespace/>siteGroup]:checkbox:not(:checked)").length;
	var checkedLength = $("input[name=<portlet:namespace/>siteGroup]:checkbox:checked").length;
	
	/*분야1개라도체크안되있으면 전체선택체크박스 해제*/
	if(uncheckedLength > 0){
		$("#<portlet:namespace/>allSite").prop("checked",false)
	}
	
	/*분야를 낱개로 모두선택시 전체선택체크박스 체크*/
	if(allChkLength == checkedLength){
		$("#<portlet:namespace/>allSite").prop("checked",true)
	}
});

var fileBrowserConectorURL = "<%=connectorURL%>";
fileBrowserConectorURL = fileBrowserConectorURL +"&currentFolder=${currentFolder}";
var ckEditorLanguage = "<%=doasLocale%>";
CKEDITOR.config.autoParagraph = false;
CKEDITOR.config.tabSpaces = 0;

CKEDITOR.replace( '<portlet:namespace/>content', {
	filebrowserImageBrowseUrl: "/edison-board-2016-portlet/editor/ckeditor/filemanger/browser.html?Connector="+fileBrowserConectorURL,
	language: ckEditorLanguage,
    filebrowserUploadUrl: null,
    toolbar : [
        		['Styles', 'FontSize', '-', 'TextColor', 'BGColor'],
         		['Bold', 'Italic', 'Underline', 'Strike'],
         		['Subscript', 'Superscript'],
         		'/',
         		['Undo', 'Redo', '-', 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'SelectAll', 'RemoveFormat'],
         		['Find', 'Replace', 'SpellChecker', 'Scayt'],
         		['Outdent','Indent','Blockquote'],
         		['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
         		'/',
         		['Source'],
         		['Link', 'Unlink', 'Anchor'],
         		['Image', 'Flash',  'Table', '-', 'Smiley', 'SpecialChar', 'LiferayPageBreak'],
         		['A11YBtn']
         	]
});

$("#<portlet:namespace/>popupStartDt").datepicker({
	showButtonPanel: true,
	showOn: 'button',
	dateFormat:"yy-mm-dd",
	changeMonth: true,
	changeYear: true,
	buttonImage: "${contextPath}/images/calendar.png",
	buttonImageOnly: true,
	onClose: function( selectedDate ) {
		$("#<portlet:namespace/>popupEndDt").datepicker("option", "minDate", selectedDate);
		
	}
});

$("#<portlet:namespace/>popupEndDt").datepicker({
	showButtonPanel: true,
	showOn: 'button',
	dateFormat:"yy-mm-dd",
	changeMonth: true,
	changeYear: true,
	buttonImage: "${contextPath}/images/calendar.png",
	buttonImageOnly: true,
	onClose: function( selectedDate ) {
		$("#<portlet:namespace/>popupStartDt").datepicker("option", "maxDate", selectedDate);
		
	}
});
</script>
