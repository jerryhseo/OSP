<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringBundler"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil"%>
<%@ page import="com.liferay.portal.model.Group"%>

<meta property="og:title" content="Comment SNS Test" />
<meta property="og:url" content="<%=request.getRequestURL() %>" />
<meta property="og:description" content="comment portlet SNS test" />

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
        String mainPath = "/c"; //themeDisplay.getPathMain();

        String doAsUserId = ""; //themeDisplay.getDoAsUserId();
        long doAsGroupId = 23212;   //themeDisplay.getDoAsGroupId();
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

<liferay-portlet:resourceURL var="fileUploadURL" id="fileUpload" escapeXml="false" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="var" value="fileUpload" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="getCommentListURL" id="getCommentList" escapeXml="false" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="var" value="getCommentList" />
    <liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:renderURL copyCurrentRenderParameters="false" var="getCommentMaxRenderURL" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
    <liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
    <liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq }" />
    <liferay-portlet:param name="myRender" value="getCommentRender"/>
    
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

<liferay-portlet:renderURL copyCurrentRenderParameters="false" var="getCommentRenderURL">
    <liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
    <liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq }" />
    <liferay-portlet:param name="myRender" value="getCommentRender"/>
    
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

<liferay-portlet:resourceURL var="addCommentListURL" id="addCommentList" escapeXml="false" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="var" value="addCommentList" />
    <liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="updateCommentListURL" id="updateCommentList" escapeXml="false" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="var" value="updateCommentList" />
    <liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="deleteCommentListURL" id="deleteCommentList" escapeXml="false" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="var" value="deleteCommentList" />
    <liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="sendMailCommentURL" id="sendMailComment" escapeXml="false" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="var" value="sendMailComment" />
    <liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="deleteSingleEdisonFileURL" escapeXml="false" id="deleteSingleEdisonFile" copyCurrentRenderParameters="false">
    <%-- <liferay-portlet:param name="boardSeq" value="${boardMap.boardSeq}" />
    <liferay-portlet:param name="boardGroupId" value="${boardGroupId}" /> --%>
    
    <liferay-portlet:param name="customId" value="${customId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="boardGroupId" value="${boardGroupId}" />
</liferay-portlet:resourceURL>

<style>
	.<portlet:namespace/>comment_list{
		height: auto;
	}
	
	.<portlet:namespace/>comment_reply_item{
		width: 80%;
	}
	
	.<portlet:namespace/>comment_input{
		/* 코멘트 작성 form */
		padding-top : 45px;
		display: none;
	}
	
	.<portlet:namespace/>boardbtnbox, .<portlet:namespace/>commentTextArea {
		/* 저장, 수정, 취소 Btn DIV */
		padding : 10px 5px 5px 5px;
		float: right;
	}
	
	.<portlet:namespace/>buttonbox{
		/* 글쓰기 Btn DIV */
		position: inherit;
		padding-top: 1%;
		float: right;
	}
	
	.<portlet:namespace/>sub_button{
		/* 수정, 삭제 Text */
		float: right;
		padding-right: 10px;
	}
	
	.<portlet:namespace/>sub_button.delete, .<portlet:namespace/>sub_button.update, .<portlet:namespace/>sub_button.reply{
		/* 수정, 삭제 Text */
		cursor: pointer;
	}
	
	.<portlet:namespace/>sns_btn{
		cursor: pointer;
	}
	
	.<portlet:namespace/>commentTitle{
		font-size: 18px;
		color: #000;
	}
	
</style>

    <%
        String mainListYn = renderRequest.getPreferences().getValue("mainListYn", "N");
    %>
    
    <c:choose>
        <c:when test="${empty redirectName }">
        	<div class="panel edison-panel">
	            <div class="panel-heading clearfix <portlet:namespace/>commentTitle" style="border-bottom: 0px;">
	            	<h3 class="panel-title pull-left">
		                <img src="/edison-default-2016-portlet/images/title_virtual.png" width="20" height="20" style="margin-right: 5px;"> 
		            	<liferay-ui:message key='edison-board-comment-title' />
	            	</h3>
	            </div>
        	</div>
        </c:when>
        <c:otherwise>
           <div class="<portlet:namespace/>commentTitle">
               <img src="/edison-default-2016-portlet/images/title_virtual.png" width="20" height="20" style="margin-right: 5px;"> 
               <a onClick="<portlet:namespace/>historyBack()" style="cursor: pointer;"> ${redirectName } </a> > <liferay-ui:message key='edison-board-comment-title' />
           </div>
        </c:otherwise>
    </c:choose>
    
    <c:if test="${isMember eq true or authYn eq 'Y'}">
	    <div class="<portlet:namespace/>buttonbox top" style="display: none;">
	        <input class="btn btn-default <portlet:namespace/>writeBtn" type="button" onClick="<portlet:namespace/>openCommentInput('commentWrite','0');" value="<liferay-ui:message key='edison-button-board-write' />" />
	    </div>
    </c:if>
    
    <input type="hidden" id="<portlet:namespace/>parentCotentSeq" name="ParentContentSeq" value="${modelId}" />  <!-- 코멘트를 등록할 경우 이용 -->
    <input type="hidden" id="<portlet:namespace/>selectBoardSeq" name="selectBoardSeq" value="" />    <!-- 코멘트 수정, 삭제 또는 댓글을 등록할 경우 이용 -->
    
    <!-- AJAX를 이용한 comment 리스트 출력 -->
    <div class="<portlet:namespace/>comment_list">
    </div>
    
    <div class="h10"></div>
        
    <c:if test="${isMember eq true or authYn eq 'Y'}">
        <div class="<portlet:namespace/>buttonbox bottom">
            <input class="btn btn-default <portlet:namespace/>writeBtn" type="button" onClick="<portlet:namespace/>openCommentInput('commentWrite','0');" value="<liferay-ui:message key='edison-button-board-write' />" />
        </div>
    </c:if>
    
    <div style="height: 10px;"></div>
    
    <!-- Comment Write -->
    <div id="<portlet:namespace/>commentInput_main" class="<portlet:namespace/>comment_input">
        <textarea id="<portlet:namespace/>commentTextArea_main" class="<portlet:namespace/>commentTextArea" name="<portlet:namespace/>commentTextArea" style="height:100px;"></textarea>
        
        <!-- 파일 첨부 기능  -->
        <form id="<portlet:namespace/>fileUploadForm_main" method="POST" action="" enctype="multipart/form-data">
	        
	            <div class="<portlet:namespace/>fileAddBtn" style="width: 80px; float: left; margin: 0% 2%;" align="center">
	                <input type="button" value="<liferay-ui:message key='edison-button-file-add' />" class="btn btn-default" onClick="<portlet:namespace/>moreFileTag('main')" style="cursor:pointer;"/>
	            </div>
	            <!-- 파일 추가 시 생성 -->
	            <div class="<portlet:namespace/>fileAddArea" style="width: 80%; float: left;">
	                <div id="<portlet:namespace/>fileDiv_main_0" class="<portlet:namespace/>fileDiv_main">
	                    <input type="file" name="addfile" class="<portlet:namespace/>addFile" id="<portlet:namespace/>file_0" style ="width:500px;border:1px solid #CCCCCC;margin-bottom:2px;">&nbsp;
	                    <input type="button" value="<liferay-ui:message key='edison-workflow-delete' />" style="cursor:pointer;" class="btn btn-default" onClick="<portlet:namespace/>deleteFileTag('<portlet:namespace/>fileDiv_main', '0')"/>
	                </div>
	            </div>
	            <div></div>
	            
	            <div id="<portlet:namespace/>fileListDiv_main" style="padding-left: 2%; float: left;">
	                <table>
	                    <colgroup width="15%" />
	                    <colgroup width="85%" />
	                    <tr>
	                        <td><img src="${contextPath}/images/fileicon.png" width="19" height="21" />&nbsp;<liferay-ui:message key='edison-table-content-header-attachments' />&nbsp;&nbsp;｜&nbsp;&nbsp;</td>
	                        <td id="<portlet:namespace/>fileListTd_main" style="padding-top: 10px;"></td>
	                    </tr>
	                </table>
	            </div>
	            
        </form>
	        
	        
	    <div class="<portlet:namespace/>boardbtnbox">
	        <div class="<portlet:namespace/>boardbtn1 boardbtnboxtoppd">
	            <!-- 글쓰기 클릭 시 저장버튼 / 수정 클릭시 수정버튼 출력 -->
	            <input type="button" name="saveBtn" onclick="<portlet:namespace/>commentSave('add', 'main'); <portlet:namespace/>closeCommentInput('main');" value="<liferay-ui:message key='edison-virtuallab-save' />" class="btn btn-default <portlet:namespace/>saveBtn" />
	            <input type="button" name="updateBtn" onclick="<portlet:namespace/>commentSave('update', 'main'); <portlet:namespace/>closeCommentInput('main');" value="<liferay-ui:message key='edison-button-update' />" class="btn btn-default <portlet:namespace/>updateBtn" />
	            <input type="button" name="cancelBtn" onclick="<portlet:namespace/>closeCommentInput('main');" value="<liferay-ui:message key='cancel' />" class="btn btn-default <portlet:namespace/>cancelBtn" />
	        </div>
	    </div>
    </div>
    <!-- Comment Write End -->
    
    <!-- Mail Send Dialog -->
    <div id="<portlet:namespace/>sendMailDialog" title="Send Mail" style="display: none;">
        <p>
            &lt;받는 사람&gt;
            <br/>
            이메일 주소&emsp;: <input type="email" id="<portlet:namespace/>receiveEmail" class="<portlet:namespace/>mailData" size="20%" value="" />
        </p>
        <p>
            &lt;전송 메시지&gt;
            <br/>
            <textarea id="<portlet:namespace/>sendMsg"  class="<portlet:namespace/>mailData" cols="50" rows="5"></textarea>
        </p>
        <p>
            &lt;보내는 사람&gt;
            <br/>
            이메일 주소&emsp;: <input type="email" id="<portlet:namespace/>sendEmail" class="<portlet:namespace/>mailData" size="20%" value="" />
        </p>
        <div class="<portlet:namespace/>boardbtnbox">
            <input type="button" name="sendDialogBtn" id="fullsize" onclick="<portlet:namespace/>sendMailComment();" value="전송" class="btn btn-default" />
            <input type="button" name="cancelDialogBtn" id="fullsize" onclick="<portlet:namespace/>closeDialog('mail');" value="취소" class="btn btn-default" />
        </div>
    </div>
    
    <div style="margin-bottom: 50px;"></div>
    
<script type="text/javascript" src="${contextPath}/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="${contextPath}/editor/ckeditor/ckeditor.js" charset="utf-8"></script> 
<script type="text/javascript" src="${contextPath}/js/jquery.form.min.js" charset="utf-8"></script> 
<script src="http://connect.facebook.net/en_US/all.js" language="JavaScript" type="text/javascript"></script>
<link href="${contextPath}/css/comment.css" rel="stylesheet" type="text/css">

<script type="text/javascript">

    $(document).ready(function() {
        
        <portlet:namespace/>ckEditorSetting('main');
        <portlet:namespace/>deleteCommentList();
        <portlet:namespace/>commentListView();
        
        $('#<portlet:namespace/>sendMailDialog').dialog({
            autoOpen: false,
            resizable: false,
            width : "400px"
        });
        $('#<portlet:namespace/>shareSnsDialog').dialog({
            autoOpen: false,
            resizable: false,
            width : "400px"
        });
        
    });
    
    
    //=======================================================
    //=================== CKEDITOR_SCRIPT ===================
    //=======================================================
    
    /* CKEDITOR */
    function <portlet:namespace/>ckEditorSetting(location){
	    var fileBrowserConectorURL = "<%=connectorURL%>";
	    fileBrowserConectorURL = fileBrowserConectorURL +"&currentFolder=${currentFolder}";
	    var ckEditorLanguage = "<%=doasLocale%>";
	    CKEDITOR.config.autoParagraph = false;
	    CKEDITOR.config.tabSpaces = 0;
	    
	    CKEDITOR.replace( '<portlet:namespace/>commentTextArea_'+location, {
	        filebrowserImageBrowseUrl: "/edison-board-2016-portlet/editor/ckeditor/filemanger/browser.html?Connector="+fileBrowserConectorURL,
	        language: ckEditorLanguage,
	        filebrowserUploadUrl: null,
	        /* 입력창 상단에 출력할 툴바(옵션) */
	        toolbar : [
	                   ['Undo', 'Redo', '-', 'Cut', 'Copy', 'Paste', 'PasteText', '-', 'SelectAll'],
	                   ['Bold', 'Italic', 'Underline', 'Strike'],
	                   ['Link', 'Unlink', 'Image'],
	                   '/',
	                    ['A11YBtn']
	                ]
	    });
	
    }
    
    
    
    
    //=====================================================
    //=================== PRINT_SCRIPT ====================
    //================== COMMENT / REPLY ==================
    //=====================================================
    
    /* Comment List 전체 출력 */
    function <portlet:namespace/>commentListView() {
        
        var parentContentSeq = $('#<portlet:namespace/>parentCotentSeq').val();
        var sendData = {"<portlet:namespace/>sendData" : parentContentSeq};
        
        $.ajax({
            type: "POST",
            data : sendData,
            url: "<%=getCommentListURL%>",
            success : function(data) {
                var commentListSize = data.boardList.length;
                if(commentListSize <= 0){
                    $(".<portlet:namespace/>buttonbox.top").css("display","none");
                } else {
                    $(".<portlet:namespace/>buttonbox.top").css("display","block");
                }
                
                <portlet:namespace/>makeCommentList(data, "comment");
                
            } //end Success
        }); //end ajax
    }
    
    /* Comment List 전체 삭제 */
    function <portlet:namespace/>deleteCommentList(){
        var commentItemSize = $('.<portlet:namespace/>comment_item').length;
        var commentReplyItemSize = $('.<portlet:namespace/>comment_reply_item').length;
        
        if(0<commentItemSize){
            $('.<portlet:namespace/>comment_item').remove();
        }
        if(0<commentReplyItemSize){
            $('.<portlet:namespace/>comment_reply_item').remove();
        }
    }
    
    /* 댓글 리스트 출력하기 */
    function <portlet:namespace/>openCommentReplyList(boardSeq){
        
        var userId = ${userId};
        var sendData = {"<portlet:namespace/>sendData" : boardSeq};
        
        $.ajax({
            type: "POST",
            data : sendData,
            url: "<%=getCommentListURL%>",
            success: function(data) {
                
                <portlet:namespace/>makeCommentList(data, "reply");
                
            }, //end Success
        }); //end ajax
        
        var replyBtn = $('#<portlet:namespace/>ReplyListBtn_'+boardSeq);
        replyBtn.html('댓글 접기 <img src="${contextPath}/images/myfile/myfile-icon04-up.png" />')
                .attr("onclick", "<portlet:namespace/>closeCommentReplyList('"+ boardSeq+"')");
    }
    
    /* 댓글 리스트 닫기 */
    function <portlet:namespace/>closeCommentReplyList(boardSeq){
        
        var commentList = $("#<portlet:namespace/>reply_"+boardSeq);    // 댓글이 출력되고 있는 DIV
        commentList.html("");
        
        var replyBtn = $('#<portlet:namespace/>ReplyListBtn_'+boardSeq);
        replyBtn.html('댓글 펼치기 <img src="${contextPath}/images/myfile/myfile-icon04.png" />')
                .attr("onclick", "<portlet:namespace/>openCommentReplyList('"+ boardSeq+"')");
    }
    
    // Comment/Reply List를 만들어서 화면에 출력
    // cmd : comment / reply
    function <portlet:namespace/>makeCommentList(data, cmd){
        
        var boardList = data.boardList;                     // comment 리스트
        var commentFileMap = data.commentFileMap;
        var commentWriterImgMap = data.commentWriterImgMap;
        var replyCntMap = data.replyCntMap;
        
        var commentList = null;     /* Comment List 또는 Reply List가 출력될 DIV 영역 */
        if(cmd === "comment"){
            commentList = $('.<portlet:namespace/>comment_list');
        } else if(cmd === "reply"){
            commentList = $("#<portlet:namespace/>reply_"+boardList[0].groupBoardSeq);
        }
        
        for(var i=0; i<boardList.length; i++){
            
            var userId = ${userId}; // Login 중인 User ID
            var boardSeq = ""+boardList[i].boardSeq;
            var writerImg = commentWriterImgMap[boardSeq];
            
            /* 접속 중인 유저가 Comment/Reply를 작성한 유저인지 */
            var commentWriteYn = (boardList[i].writerId == userId);
            
            var intervalDiv = $('<div/>').addClass("h10");
            var commentItem = $('<div/>').addClass("<portlet:namespace/>comment_item")    // 하나의 코멘트 정보가 출력
                                         .attr("style", "padding-bottom:20px;");
            var titleRightSns = $('<div/>').addClass("titlerightsns");                     // title 우측 Btn 출력 DIV
            var fileListDiv = $('<div/>').addClass("<portlet:namespace/>boardFileListDiv") // 첨부 파일 출력 DIV
                                         .attr("id", "<portlet:namespace/>fileList_"+boardSeq);
            
            var commentTitleDiv = null      // title이 출력되는 DIV
            var comment = null;             // 코멘트 내용과 파일 출력 DIV
            var commentTextArea = null;     // 코멘트의 내용 출력 DIV
            var imgClass = "";
            
            if(cmd === "comment"){
                commentTitleDiv = $('<div/>').addClass("titlearea");
                comment = $('<div/>').addClass("comment_div");
                commentTextArea = $('<div/>').addClass("commenttxtarea comment");
                replyListBtnDiv = $('<div/>');
                imgClass = "leftinfoimg";
            } else if(cmd === "reply"){
                commentItem.css("width","100%")
                           .css("padding-top","20px");
                commentTitleDiv = $('<div/>').attr("class", "titlearea titlea01");
                comment = $('<div/>').addClass("rightboxcomm");
                commentTextArea = $('<div/>').addClass("commenttxtarea reply");
                replyListBtnDiv = $('<div/>').css("width","90%");
                imgClass = "leftinfoimg2";
            }
            
            /* 작성자 Image 영역 */
            $('<div/>').addClass(imgClass)
                       .css("padding-top","15px")
                       .css("background-image","url("+writerImg+")")
                       .appendTo(commentItem);
            
            $('<div/>').addClass("h10")
            .appendTo(commentList);
            
            /* title 영역 */
            $('<div/>').addClass("titletxt")
                       .text(boardSeq)
                       .appendTo(commentTitleDiv);
             
            /* title 오른쪽 버튼 */
            var titleRightUl = $('<ul/>');
            
            $('<li/>').append('<input type="button" value="댓글" class="commbtn_s" onclick="<portlet:namespace/>openCommentInput(\'commentReply\',\''+boardSeq+'\');" />')
                      .appendTo(titleRightUl);
            if(commentWriteYn){
                $('<li/>').append('<input type="button" value="삭제" class="commbtn_s del" onclick="<portlet:namespace/>commentDelete(\''+boardSeq+'\');" />')
                          .appendTo(titleRightUl);
                $('<li/>').append('<input type="button" value="수정" class="commbtn_s" onclick="<portlet:namespace/>openCommentInput(\'commentUpdate\',\''+boardSeq+'\');" />')
                          .appendTo(titleRightUl);
            }
            $('<li/>').append('<img src="${contextPath}/images/comment/email_icon2.png" width="24" height="23" class="<portlet:namespace/>sns_btn" onclick="<portlet:namespace/>openDialog(\'mail\');">')    // E-mail
                      .appendTo(titleRightUl);
            $('<li/>').append('<img src="${contextPath}/images/comment/sns_02.png" width="24" height="23" class="<portlet:namespace/>sns_btn" onclick="<portlet:namespace/>shareSns(\'twitter\');">')    // twitter
                      .appendTo(titleRightUl);
            $('<li/>').append('<img src="${contextPath}/images/comment/sns_01.png" width="24" height="23" class="<portlet:namespace/>sns_btn" onclick="<portlet:namespace/>shareSns(\'facebook\');">')    // facebook
                      .appendTo(titleRightUl);
            
            titleRightUl.appendTo(titleRightSns);
            titleRightSns.appendTo(commentTitleDiv);
            commentTitleDiv.appendTo(comment);
            
            /* comment 영역 */
            $('<div/>').attr("id","<portlet:namespace/>comment_"+boardSeq)
                       .html("<br>" + boardList[i].content)
                       .appendTo(commentTextArea);
            
            // commentFIleMap의 key 값 중에 boardSeq가 있는 경우 해당 fileList 추출
            var fileList = null;
            if(boardSeq in commentFileMap){
                fileList= commentFileMap[boardSeq];
                
                for(var k=0; k<fileList.length; k++){
                    
                    var fileTitle = fileList[k].fileTitle;
                    var fileEntryId = fileList[k].fileEntryId;
                    var fileUserId = fileList[k].fileUserId;
                    
                    var fileItem = $('<div/>');
                    
                    $('<span/>').addClass("<portlet:namespace/>onMouseHover")
                                .attr("onclick", "<portlet:namespace/>fileDownload('"+ fileEntryId +"')")
                                .css("cursor","pointer").css("display","inline")
                                .html(fileTitle+"&nbsp;")
                                .append(' <img src="${contextPath}/images/fileicon2.png" />')
                                .appendTo(fileItem);
                    
                    if(commentWriteYn){
                        $('<span/>').css("cursor","pointer").css("display","inline")
                                    .attr("onclick", "<portlet:namespace/>deleteSingleEdisonFile('"+ fileEntryId +"', '"+ fileUserId +"','" + boardSeq + "')")
                                    .html("&nbsp;&nbsp;")
                                    .append(' <img src="${contextPath}/images/icon_dustbin.png"/>')
                                    .appendTo(fileItem);
                    }
                    
                    fileItem.appendTo(fileListDiv);
                    fileListDiv.appendTo(commentTextArea);
                }
            }
            
            // 댓글 개수 가져오기
            var replyCnt = replyCntMap[boardSeq];
            
            /* 작성일, 댓글펼치기 버튼 영역, 댓글이 존재하는 경우만 댓글펼치기 버튼 출력 */
            $('<span style="display:inline;">작성일 : '+boardList[i].writerDate+'</span>') .appendTo(commentTextArea);
            if(0 < replyCnt){
	            $('<span id="<portlet:namespace/>ReplyListBtn_' + boardList[i].boardSeq + '" style="cursor:pointer; width:15%; float:right;" onclick="<portlet:namespace/>openCommentReplyList('+boardSeq+')">댓글 펼치기 <img src="${contextPath}/images/myfile/myfile-icon04.png" /></span>').appendTo(commentTextArea);
            }
            
            commentTextArea.appendTo(comment);
            comment.appendTo(commentItem);
            
            var commentInput = $("<div/>").attr("id", "<portlet:namespace/>commentInput_"+boardSeq).addClass("<portlet:namespace/>comment_input");
            $("<textarea/>").attr("id","<portlet:namespace/>commentTextArea_"+boardSeq).addClass("<portlet:namespace/>commentTextArea")
                            .attr("name", "<portlet:namespace/>commentTextArea").appendTo(commentInput);
            $("<div/>").attr("id", "<portlet:namespace/>fileFormDiv_"+boardSeq).appendTo(commentInput);
            
            
            /* file 첨부 tag */
            // file Form
            var fileForm = $("<form/>").attr("id", "<portlet:namespace/>fileUploadForm_"+boardSeq).attr("method", "OST").attr("enctype", "multipart/form-data")
            
            // file Add Btn
            var fileAddBtn = $("<div/>").addClass("<portlet:namespace/>fileAddBtn").css("width","80px").css("float","left").css("margin","0% 2%").attr("align", "center");
            $("<input type=\"button\" value=\"<liferay-ui:message key='edison-button-file-add' />\" class=\"btn btn-default\" onClick=\"<portlet:namespace/>moreFileTag('"+boardSeq+"')\" style=\"cursor:pointer;\"/>").appendTo(fileAddBtn);
            fileAddBtn.appendTo(fileForm);
            
            // file Add Area
            var fileAddArea = $("<div/>").addClass("<portlet:namespace/>fileAddArea").attr("style", "width: 70%; float: left;");
            var fileDiv = $("<div/>").attr("id", "<portlet:namespace/>fileDiv_"+boardSeq+"_0").addClass("<portlet:namespace/>fileDiv_"+boardSeq);
            $("<input type=\"file\" name=\"addfile\" class=\"<portlet:namespace/>addFile\" id=\"<portlet:namespace/>file_0\" style =\"width:500px;border:1px solid #CCCCCC;margin-bottom:2px;\">&nbsp;").appendTo(fileDiv);
            $("<input type=\"button\" value=\"<liferay-ui:message key='edison-workflow-delete' />\" style=\"cursor:pointer;\" class=\"btn btn-default fileDelete_0\" onClick=\"<portlet:namespace/>deleteFileTag(\'<portlet:namespace/>fileDiv_"+boardSeq+"\', \'0\')\" />").appendTo(fileDiv);
            fileDiv.appendTo(fileAddArea);
            fileAddArea.appendTo(fileForm);
            $("<div/>").appendTo(fileForm);
            
            // file List
            var fileListDiv = $("<div/>").attr("id", "<portlet:namespace/>fileListDiv_"+boardSeq).css("float","left").css("padding","2%");
            var table = $("<table/>");
            $("<colgroup width=\"15%\" />").appendTo(table);
            $("<colgroup width=\"85%\" />").appendTo(table);
            var tr = $("<tr/>");
            $("<td/>").append("<img src=\"${contextPath}/images/fileicon.png\" width=\"19\" height=\"21\" />&nbsp;<liferay-ui:message key='edison-table-content-header-attachments' />&nbsp;&nbsp;｜&nbsp;&nbsp;").appendTo(tr);
            $("<td/>").attr("id", "<portlet:namespace/>fileListTd_"+boardSeq).css("padding-top","10px").appendTo(tr);
            tr.appendTo(table);
            table.appendTo(fileListDiv);
            fileListDiv.appendTo(fileForm);
            
            // 글 수정, 취소 버튼
            var btnBox = $("<div class='<portlet:namespace/>boardbtnbox'>");
            var boardBtn = $("<div class='<portlet:namespace/>boardbtn1 boardbtnboxtoppd'>");
            $("<input type=\"button\" name=\"saveBtn\" onclick=\"<portlet:namespace/>commentSave('add', '"+boardSeq+"'); <portlet:namespace/>closeCommentInput('"+boardSeq+"');\" value=\"<liferay-ui:message key='edison-virtuallab-save' />\" class=\"btn btn-default <portlet:namespace/>saveBtn\" />&nbsp;&nbsp;")
                .appendTo(boardBtn);
            $("<input type=\"button\" name=\"updateBtn\" onclick=\"<portlet:namespace/>commentSave('update', '"+boardSeq+"'); <portlet:namespace/>closeCommentInput('"+boardSeq+"');\" value=\"<liferay-ui:message key='edison-button-update' />\" class=\"btn btn-default <portlet:namespace/>updateBtn\" />&nbsp;&nbsp;")
                .appendTo(boardBtn);
            $("<input type=\"button\" name=\"cancelBtn\" onclick=\"<portlet:namespace/>closeCommentInput('"+boardSeq+"');\" value=\"<liferay-ui:message key='cancel' />\" class=\"btn btn-default <portlet:namespace/>cancelBtn\" />")
                .appendTo(boardBtn);
            boardBtn.appendTo(btnBox);
            btnBox.appendTo(fileForm);
            
            fileForm.appendTo(commentInput);
            
            /* 댓글이 출력될 DIV */
            $('<div/>').attr("id", "<portlet:namespace/>reply_"+boardSeq)
                       .addClass("<portlet:namespace/>replyDiv")
                       .appendTo(commentItem);
            intervalDiv.appendTo(commentItem);
            fileForm.appendTo(commentInput);
            commentInput.appendTo(commentItem);
            commentItem.appendTo(commentList);
            
            <portlet:namespace/>ckEditorSetting(boardSeq);
        }
    }
    
    
    //=====================================================
    //============== TEXT_INPUT_FORM_SCRIPT ===============
    //================== COMMENT / REPLY ==================
    //=====================================================
    
    /* 글쓰기 버튼 클릭 시 입력폼 보이기 및 글쓰기 버튼 숨김 */
    function <portlet:namespace/>openCommentInput(cmd, boardSeq) {
        
        /* 기존에 Open 되어있는 DIV 숨기기  */
        $(".<portlet:namespace/>comment_input").css("display","none;");
        
        if(cmd == "commentWrite"){
            /* 글쓰기 Button Click */
            var parentContentSeq = $('#<portlet:namespace/>parentCotentSeq').val();
            if(parentContentSeq === null){
                $('#<portlet:namespace/>selectBoardSeq').val('0');
            } else {
                $('#<portlet:namespace/>selectBoardSeq').val(parentContentSeq);
            }
            
            // 저장 버튼 활성화, 수정 버튼 비활성화
            $('.<portlet:namespace/>updateBtn').css("display","none");
            $('.<portlet:namespace/>saveBtn').css("display","");
        } else if(cmd == "commentUpdate"){
            /* 수정 Button Click */
            var content = $('#<portlet:namespace/>comment_'+boardSeq).html();
            /* 선택된 Comment의 내용을 CKEDITOR에 입력(Set) */
            CKEDITOR.instances['<portlet:namespace/>commentTextArea_'+boardSeq].setData(content);
            
            // 선택된 Comment의 BoardSeq 등록
            $('#<portlet:namespace/>selectBoardSeq').val(boardSeq);
            
            // 선택한 comment의 file list 추가하기
            var fileListTd = $("#<portlet:namespace/>fileUploadForm_"+boardSeq+ " #<portlet:namespace/>fileListTd_"+boardSeq);
            var boardFileList = $('#<portlet:namespace/>fileList_'+boardSeq).html();
            fileListTd.html("");
            fileListTd.append(boardFileList);
            
            // 수정버튼 활성화, 저장 버튼 비활성화 
            $('.<portlet:namespace/>updateBtn').css("display","");
            $('.<portlet:namespace/>saveBtn').css("display","none");
        } else if(cmd == "commentReply"){
            /* 댓글 Button Click */
            CKEDITOR.instances['<portlet:namespace/>commentTextArea_'+boardSeq].setData("");
            
            // 선택된 Comment의 BoardSeq 등록
            $('#<portlet:namespace/>selectBoardSeq').val(boardSeq);
            
            // 저장버튼 활성화, 수정 버튼 비활성화
            $('.<portlet:namespace/>updateBtn').css("display","none");
            $('.<portlet:namespace/>saveBtn').css("display","");
        }
        
        /* fileInput 개수 check */
        /* $("<div/>").attr("class", "<portlet:namespace/>fileDiv_"+boardSeq).attr("id"); */
        var inputFileLength = $(".<portlet:namespace/>fileDiv_"+boardSeq+ " input[type=file]").length;
        if(inputFileLength < 1){
            var updateFileDiv = $(".<portlet:namespace/>fileDiv_"+boardSeq);  
            $("<input/>").attr("type", "file").attr("name", "addfile").attr("class", "<portlet:namespace/>addFile").attr("id", "<portlet:namespace/>file_0")
                         .attr("style", "width:500px;border:1px solid #CCCCCC;margin-bottom:2px;").appendTo(updateFileDiv);
            $("<input/>").attr("type", "button").attr("value", "Delete").attr("style", "cursor:pointer;").attr("class", "btn btn-default fileDelete_0")
                         .attr("onclick", "<portlet:namespace/>deleteFileTag('<portlet:namespace/>fileDiv_"+boardSeq+"' ,'0')").appendTo(updateFileDiv);
        }
        
        $(".<portlet:namespace/>writeBtn").css("display","none");
        
        if(boardSeq == 0){
            /* CKEDITOR에 입력(Set)된 내용을 화면에 출력 */
            CKEDITOR.instances['<portlet:namespace/>commentTextArea_main'].getData();
	        $("#<portlet:namespace/>commentInput_main").show();
	        $("#<portlet:namespace/>commentInput_main").focus();
        } else {
            /* CKEDITOR에 입력(Set)된 내용을 화면에 출력 */
            CKEDITOR.instances['<portlet:namespace/>commentTextArea_'+boardSeq].getData();
            $("#<portlet:namespace/>commentInput_"+boardSeq).show();
            $("#<portlet:namespace/>commentInput_"+boardSeq).focus();
        }
    }
    
    /* Comment 작성 취소 클릭 시 입력폼 숨김 및 글쓰기 버튼 보이기 */
    function <portlet:namespace/>closeCommentInput(location) {
        /* 첨부파일 내용 제거 */
        $(".<portlet:namespace/>fileDiv_"+location+ " :input[name*=addfile]").val("");
        if($(':input[name*=addfile]').length == 0){
            <portlet:namespace/>moreFileTag(location);
        }
        
        $("#<portlet:namespace/>fileListDiv_"+location+" span").remove();
        $('#<portlet:namespace/>selectBoardSeq').val("");
        
        /* textarea에 입력된 값 제거 */
        $('#<portlet:namespace/>commentTextArea_'+location).val("");
        CKEDITOR.instances['<portlet:namespace/>commentTextArea_'+location].setData("");
        /* 글쓰기 버튼 출력 */
        $(".<portlet:namespace/>writeBtn").css("display","");
        /* 텍스트 엡력 form 숨기기 */
        $("#<portlet:namespace/>commentInput_"+location).hide();
    }
    
    
    //=====================================================
    //==================== CRUD_SCRIPT ====================
    //================== COMMENT / REPLY ==================
    //=====================================================
    
    /* 작성된 Comment는 Ajax를 이용하여 저장 또는 수정
              수정 시 boardSeq 데이터 필요, 댓글 작성 시 groupBoardSeq 필요 */
    function <portlet:namespace/>commentSave(cmd, boardSeq) {
        
        //var form = $('#<portlet:namespace/>fileUploadForm_main'+boardSeq);
        var form = $('#<portlet:namespace/>fileUploadForm_'+boardSeq);
        var formData = new FormData(form);
        formData.append("fileObj", $('.<portlet:namespace/>addFile')[0].files[0]);
        
        var sendComment = CKEDITOR.instances['<portlet:namespace/>commentTextArea_'+boardSeq].getData();
        
        var boardSeq = ""+$('#<portlet:namespace/>selectBoardSeq').val();  /* 글쓰기 버튼을 통한 저장 : 코멘트 , 댓글 버튼을 통한 저장 : 댓글 */
        if(boardSeq === ""){
            boardSeq = ""+$('#<portlet:namespace/>parentCotentSeq').val();
        }
        
        var sendDataKey = "";
        var sendUrl = "";
        var obj = new Object();
        obj.comment = sendComment;
        
        if(cmd === "add"){
            obj.groupBoardSeq = boardSeq;
        } else if(cmd === "update"){
            obj.boardSeq = boardSeq;
        }
        obj.formData = formData;
        
        var sendJsonData = JSON.stringify(obj);
        
        var sendData = null;
        if(cmd === "add"){
            sendUrl = "<%=addCommentListURL%>"
            sendData = {"<portlet:namespace/>saveData" : sendJsonData}
        } else if(cmd === "update"){
            sendUrl = "<%=updateCommentListURL%>"
            sendData = {"<portlet:namespace/>updateData" : sendJsonData}
        }
        
        if(sendComment !== null && sendComment !== ""){
            form.ajaxForm({
                type: "POST",
                data : sendData,
                url: sendUrl,
                enctype: "multipart/form-data",
                success : function(data) {
                    <portlet:namespace/>commentControllSuccess();
                }
            });
            form.submit();
        }
    }
    
    /* 선택된 Comment 삭제
        삭제 시 boardSeq 필요
    */
    function <portlet:namespace/>commentDelete(boardSeq) {
    	$(".<portlet:namespace/>writeBtn").css("display","");
    	
        if(!confirm(Liferay.Language.get('data-delete-confirm'))){return;}
        
        var sendData = {"<portlet:namespace/>sendData" : boardSeq};
        
        $.ajax({
            type: "POST",
            data:sendData,
            url: "<%=deleteCommentListURL%>",
            success : function(data) {
                <portlet:namespace/>commentControllSuccess();
            }
        });
    }
    
    /* 저장, 수정, 삭제 후 실행할 Script */
    function <portlet:namespace/>commentControllSuccess(location) {
        /* comment 데이터 수정/삭제 후 parentContentSeq와 selectBoardSeq의 value ""으로 변경 */
        $("#<portlet:namespace/>selectBoardSeq").val("");
        
        $(".<portlet:namespace/>fileDiv").remove();
        if($(':input[name*=addfile]').length == 0){
            <portlet:namespace/>moreFileTag(location);
        } 
        
        /* 수정/삭제 후 화면에 변경된 Comment List 반영 */
        /* Comment List가 이미 존재할 경우 기존 리스트는 제거 */
        <portlet:namespace/>deleteCommentList();
        <portlet:namespace/>commentListView();
    }
    
    
    //=====================================================
    //================== MAIL_SNS_SCRIPT ==================
    //=====================================================
    
    /* 메일 내용 작성 Dialog 창 출력 (추후 SNS 보내기 기능 추가 예정...) */
    function <portlet:namespace/>openDialog(div) {
        
        var link = document.location.href;
        
        if(div == "mail"){
            // 현재 페이지 URL을 TextArea에 입력
            $('#<portlet:namespace/>sendMsg').val(link);
            // 받는 사람 email, 전달 내용 등 입력 할 수 있는 dialog 출력
            $('#<portlet:namespace/>sendMailDialog').dialog('open');
        }
    }
    
    /* 메일 내용 작성 Dialog 창 닫기 */
    function <portlet:namespace/>closeDialog(div) {
        if(div == "mail"){
            /* 취소 버튼 클릭 시 다이얼로그 종료 */
            $('#<portlet:namespace/>sendMailDialog .<portlet:namespace/>mailData').val("");
            $('#<portlet:namespace/>sendMailDialog').dialog('close');
        } else if(div == "sns"){
            $('#<portlet:namespace/>shareSnsDialog').dialog('close');
        }
    }

    /* 현재 URL 주소 이메일로 전송 */
    function <portlet:namespace/>sendMailComment() {
        
        /* 전송 버튼 클릭 시 받는 사람 email로 전송 */
        var receiveEmail = $('#<portlet:namespace/>receiveEmail').val();
        var sendMsg = $('#<portlet:namespace/>sendMsg').val();
        var sendEmail = $('#<portlet:namespace/>sendEmail').val();
        
        //var sendMailData = [receiveEmail, sendMsg, sendEmail];
        
        var obj = new Object();
        obj.toEmail = receiveEmail;
        obj.sendMsg = sendMsg;
        obj.fromEmail = sendEmail;
        
        var sendMailData = JSON.stringify(obj);
        
        var sendData = {"<portlet:namespace/>sendMailData" : sendMailData};
        
        $.ajax({
            type: "POST",
            data : sendData,
            url: "<%=sendMailCommentURL%>",
            success : function(data) {
                alert("Mail Send Success...");
            }
            
        });
        
        
        /* 이메일 전송 성공/실패 시 알림 메시지 출력 후 close */
        <portlet:namespace/>closeDialog("mail");
    }
    
    /* 현재 게시글 SNS로 공유 */
    function <portlet:namespace/>shareSns(sns) {
        
        var link = document.location.href;
        
        /* FaceBook, Twitter, etc.  */
        if(sns == "facebook"){
            window.open("http://www.facebook.com/sharer/sharer.php?u="+link, "Share Link", "width=800, height=700, toolbar=no, menubar=no, scrollbars=no, resizable=yes");  // facebook
        } else if(sns == "twitter"){
            window.open("https://twitter.com/intent/tweet?url="+link, "Share Link", "width=800, height=700, toolbar=no, menubar=no, scrollbars=no, resizable=yes");   // twitter
        } else if(sns == "band"){
            window.open("http://band.us/plugin/share?route="+link, "Share Link", "width=800, height=700, toolbar=no, menubar=no, scrollbars=no, resizable=yes");    // band
        } else {
        }
        
        <portlet:namespace/>closeDialog("sns");
    }
    
    
    //=====================================================
    //==================== FILE_SCRIPT ====================
    //================== COMMENT / REPLY ==================
    //=====================================================
    
    // 첨부파일 입력 Form 추가
    function <portlet:namespace/>moreFileTag(location){
        /* 첨부파일 선택 form 추가 */
        var frmTag = $('.<portlet:namespace/>fileDiv_'+location); // "<div id=\"<portlet:namespace/>fileDiv_"+location+"_"+fileIndex+"\" class=\"<portlet:namespace/>fileDiv_"+location+"\">";
        var fileIndex = $('.<portlet:namespace/>fileDiv_' + location + ' :input[name*=addfile]').length;
        $("<input type=\"file\" name=\"addfile\" class=\"<portlet:namespace/>addFile\" id=\"<portlet:namespace/>file_"+fileIndex+"\" style =\"width:500px;border:1px solid #CCCCCC;margin-bottom:2px;\">&nbsp;").appendTo(frmTag);
        $("<input type=\"button\" value=\"<liferay-ui:message key='edison-workflow-delete' />\" style=\"cursor:pointer;\" class=\"btn btn-default fileDelete_"+fileIndex+"\" onClick=\"<portlet:namespace/>deleteFileTag(\'<portlet:namespace/>fileDiv_"+location+"\', \'"+fileIndex+"\')\"/>").appendTo(frmTag)
        
    }

    // 첨부파일 입력Form 제거
    function <portlet:namespace/>deleteFileTag(fileDivSeq, fileInputSeq){  
        
        /* 선택된 첨부파일 제거 */
        if(fileInputSeq == 0){
            $("." + fileDivSeq + " #<portlet:namespace/>file_"+fileInputSeq).val("");
        } else {
            $("." + fileDivSeq + " #<portlet:namespace/>file_"+fileInputSeq).remove();
            $("." + fileDivSeq + " .fileDelete_"+fileInputSeq).remove();
        }
    }
    
    // 첨부파일 삭제
    function <portlet:namespace/>deleteSingleEdisonFile(p_fileEntryId, p_fileUserId, boardSeq){
        if(!confirm(Liferay.Language.get('data-delete-confirm'))){return;}
        
        var deleteForm = {
                        "<portlet:namespace/>fileEntryId" : p_fileEntryId,
                        "<portlet:namespace/>fileUserId" : p_fileUserId,
                        "<portlet:namespace/>boardSeq" : boardSeq
                        };
        jQuery.ajax({
            type: "POST",
            url: "<%=deleteSingleEdisonFileURL%>",
            data: deleteForm,
            async : false,
            success: function(data) {
                var fileList = data.fileList;
                var boardSeq = data.boardSeq;
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
                            fileHtml += "<span style=\"cursor:pointer\" onclick=\"<portlet:namespace/>deleteSingleEdisonFile(\'"+fileList[i].fileEntryId+"\', \'"+fileList[i].fileUserId+"\', \'" + boardSeq + "\')><u>[delete]</u></span>";
                            fileHtml += "<br>";
                        }
                        $("#fileListDiv").html(fileHtml);
                    }
                    
                    <portlet:namespace/>deleteCommentList();
                    <portlet:namespace/>commentListView();
                    
                }else if(resultMsg=="DELETE_FAIL"){
                    alert("delete file error!");    
                }
            },error:function(data,e){ 
                alert("deleteSingleEdisonFile System error!");  
            },complete:function(){
            
            }
        });
    }
    
    function <portlet:namespace/>fileDownload(p_fileEntryId){
        location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;    
    }
    
    function changeLanguage(){
        if(confirm("<liferay-ui:message key='edison-board-save-alert' />")){        
            submitForm<portlet:namespace/>();
        }else{
            var reloadUrl = "<%=getCommentRenderURL%>"
            if("${maxWindowStatus}"=="Y"){
                reloadUrl = "<%=getCommentMaxRenderURL%>";
            }
            reloadUrl += "&<portlet:namespace/>RENDER_SORT=UPDATE";
                reloadUrl += "&<portlet:namespace/>select_languageId="+$("input[name=<portlet:namespace/>select_languageId]:radio:checked").val();
            location.href = reloadUrl;
        }
    }
    
</script>
