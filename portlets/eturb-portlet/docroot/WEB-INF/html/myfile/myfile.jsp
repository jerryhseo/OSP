<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%
    String returnId = request.getAttribute("returnId")!= null?(String)request.getAttribute("returnId"):"";
    String returnFileName = request.getAttribute("returnFileName")!= null?(String)request.getAttribute("returnFileName"):"";
    String cluster = request.getAttribute("cluster")!= null?(String)request.getAttribute("cluster"):"";
    String workflowType = request.getAttribute("workflowType")!= null?(String)request.getAttribute("workflowType"):"";
    String icebreakerUrl =CustomUtil.strNull(request.getAttribute("icebreakerUrl"));
    String icebreakerPublicUrl =CustomUtil.strNull(request.getAttribute("icebreakerPublicUrl"));
    
    //해당 파일을 POPUP으로 띄울경우
    boolean popupState = false;
    if(LiferayWindowState.isPopUp(request)){
        popupState = true;
    }
    
    String pathCopyStr = LanguageUtil.format(locale, "edison-myfile-file-path-copy", "");
    String fileCopyQStr = LanguageUtil.format(locale, "edison-myfile-file-copy-question", "");

    String mode = request.getAttribute("mode")!= null?(String)request.getAttribute("mode"):"";
    String uploaddestFolderId = request.getAttribute("uploaddestFolderId")!= null?(String)request.getAttribute("uploaddestFolderId"):"";
    String destFolderParents = request.getAttribute("destFolderParents")!= null?(String)request.getAttribute("destFolderParents"):"";
    
    String liferayWindowState = LiferayWindowState.POP_UP.toString();
    pageContext.setAttribute("liferayWindowState",liferayWindowState);
%>
<c:set var="liferayWindowState" value="${pageScope.liferayWindowState}" />

<liferay-portlet:renderURL var="fileUploadPopUpURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="myaction" value="fileUploadPopUp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="myfilePopUpURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
    <liferay-portlet:param name="myaction" value="myfilePopUp" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="deleteFileURL" id="deleteFile" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:resourceURL var="getRepositoryFolderURL" id="getRepositoryFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getChildFolderURL" id="getChildFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getChildFileURL" id="getChildFile" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="createFolderURL" id="createFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="renameFolderURL" id="renameFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="deleteFolderURL" id="deleteFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="moveNodeURL"   id="moveNode"   copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="copyFileURL"   id="copyFile"   copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="fileIBUploadURL">
    <liferay-portlet:param name="myaction" value="fileIBUpload" />
    <liferay-portlet:param name="returnId" value="<%=returnId%>" />
    <liferay-portlet:param name="returnFileName" value="<%=returnFileName%>" />
    <liferay-portlet:param name="cluster" value="<%=cluster%>" />
    <liferay-portlet:param name="workflowType" value="<%=workflowType%>" />
</liferay-portlet:actionURL>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/jquery.contextMenu.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/myfile.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jstree/themes/proton/style.css" media="screen"/>    <!-- nav style -->
<script type="text/javascript" src="${contextPath}/js/jstree.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.contextMenu.js"></script>

<div class="h20"></div>
<aui:form name="form">
    <aui:input type="hidden" name="groupId" id="groupId" value="${groupId }"></aui:input>
    <aui:input type="hidden" name="vcToken" id="vcToken" value="${vcToken}"></aui:input>
    <aui:input type="hidden" name="icebreakerUrl" id="icebreakerUrl" value="${icebreakerUrl}"></aui:input>
</aui:form>
    
<form method="post" name="<portlet:namespace/>fileUploadForm" enctype="multipart/form-data" action="<%=fileIBUploadURL %>" >
        <input type="hidden" name="groupId" value="${groupId }" />
        <input type="hidden" name="vcToken" value="${vcToken }" />
        <input type="hidden" id="destFolderId" name="destFolderId" value="" />
        <input type="hidden" id="destFolderParents" name="destFolderParents" value="" />
        <input type="hidden" name="isPopUp" value="<%=popupState%>" />
        <input id="<portlet:namespace/>uploadBtn" name="<portlet:namespace/>addFile" type="file" multiple style="display: none;" onchange="uploadFile(this);" />
</form>
 
<div class="myfilebox">
    <div class="leftcontent">
        <div id="myfileTree" class="mflefttree">
        </div>
    </div>
    
    <div>
        <!-- search -->
        <div style="float: right; padding: 10px 20px 15px 0px; width: 35%;">
            <div>
                <input id="<portlet:namespace/>searchKeyword" name="searchKeyword" type="text" onkeyup="searchFile(this.value);" style="width: 75%;" />
                <input type="button" value="<liferay-ui:message key='edison-button-board-initialize'/>" id="<portlet:namespace/>searchInit" name="searchInit" class="btn btn-primary" cssClass="btn_blue" onclick="<portlet:namespace/>searchInit();" />
            </div>
        </div>
        
        <!--right contents-->
        <div class="rightcontent" style="overflow: auto;">
            <div class="myfilefolderlist">
              <ul id ="fileTableBody">
              </ul>
            </div>
        </div>
    </div>
</div>

<!-- 폴더 생성 Btn -->
<div style="width:50%; float:left;">
    <c:if test="${portletWindowState ne liferayWindowState}">
       <input class="addIp button08_2" id="createFolder"  onclick="<portlet:namespace/>createFolder();" value="<liferay-ui:message key='edison-simulation-myfile-create-folder'/>" type="button">
    </c:if>
</div>

<!-- 업로드, 다운로드, 파일삭제 Btn -->
<div style="width:45%; float:right; text-align:right;">
        
        <c:choose>
            <c:when test="${portletWindowState eq liferayWindowState}">
                <input class="addIp button08_1" id="<portlet:namespace/>selectBtn" onclick="<portlet:namespace/>selectBtn();" value="<liferay-ui:message key='edison-table-list-header-select'/>" type="button" />
            </c:when>
            <c:otherwise>
                <input class="addIp button08_1" onclick="$('#<portlet:namespace/>uploadBtn').click();" value=<liferay-ui:message key='edison-button-upload'/> type="button" />
                <!-- <input class="addIp button08_1" onclick="openPopUpFileUpload();" value=<liferay-ui:message key='edison-button-upload'/> type="button" /> -->
                <input class="addIp button01b" style="min-width:90px;" onclick="<portlet:namespace/>checkfileDownload();" value="<liferay-ui:message key='edison-table-list-header-download'/>" type="button"/>
                <input class="addIp button01b" style="min-width:90px;" onclick="<portlet:namespace/>checkfileDelete();" value="<liferay-ui:message key='edison-button-file-delete'/>" type="button"/>
            </c:otherwise>
        </c:choose>
        
    
    <div id="fileName">
       
    </div>
    
</div>
<div style="clear:both"></div>
<div id="fileDownloadIframe"> </div>

<div id="icebreaker-file-upload-dialog" title="파일업로드" class="bigpopupbox" style="display:none;">
</div>

<div id="file-popup-dialog" title="선택파일팝업" class="bigpopupbox" style="display:none; background-color: #fff;">
</div>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
    $(function() {
        var folderArr = <portlet:namespace/>getRepositoryFolder();
        <portlet:namespace/>fileDivWidthEvent2();
        
//      <portlet:namespace/>initJstree(folderArr,"", null);
//      <portlet:namespace/>getChildFile("HOME");
        
        var mode = "<%=mode%>";
        if(mode == "fileUpload"){
            var array = [];
            var uploadParents = "<%=destFolderParents%>";
            if(uploadParents != ""){
                
                if( uploadParents.split(",").length > 0 ){
                    for(var i=0 ; i< uploadParents.split(",").length ; i++){
                        array.push(uploadParents.split(",")[i]);
                    }
                }   
                    
            }
            <portlet:namespace/>initJstree(folderArr, "<%=uploaddestFolderId%>" , array);
            <portlet:namespace/>getChildFile("<%=uploaddestFolderId%>");
        }else{
            //초기 jstree init
            if(folderArr != null){
                var homeChildFileList = <portlet:namespace/>getChildFile("HOME");
                
                /* 하위 파일 List 추가 */
                folderArr = folderArr.concat(homeChildFileList);
                
                <portlet:namespace/>initJstree(folderArr,"", null);
            }       
        }
        
        $("#myfileTree").jstree("deselect_all");
        
        $("#<portlet:namespace/>searchSubmit").click(function(e){
              <portlet:namespace/>totalSearchSubmit();
        });
        
        $("#<portlet:namespace/>searchInit").click(function(e){
            <portlet:namespace/>searchInit();
        });
        
        
        $.contextMenu({
            selector: '.<portlet:namespace/>file',
            build: function ($trigger, e) {
                var selectFileName = $trigger.attr("file-name");
                var fileExt = selectFileName.substr(selectFileName.lastIndexOf(".")+1, selectFileName.length);
                var contextMenuItems = <portlet:namespace/>myFileContextMenuItem(fileExt);
                return {
                    items: contextMenuItems
                };
            }
        });
        
    });
    
    /* myFile ContextMenu Item */
    function <portlet:namespace/>myFileContextMenuItem(fileExt){
        
        if(fileExt == "obj" || fileExt == "zip"){
            var items = {
                            "Analysis": {
                                name: "Analysis",
                                icon: " icon-bar-chart",
                                callback: function (key, options) {
                                    var selectItemId = options.$trigger.attr("id");
                                    var fileName = $("#"+selectItemId).attr('file-name');
                                    var fileEntryId = $("#"+selectItemId+" input[name=<portlet:namespace/>fileChk]").val();
                                    <portlet:namespace/>openAnalyzer(fileName, fileEntryId);
                                }
                            }
                       }
            return items;
        }
        
    }
    
    /* fileName으로 분기 */
    function <portlet:namespace/>openAnalyzer(fileName, fileEntryId){
        var fileExt = fileName.substr(fileName.lastIndexOf(".")+1, fileName.length);
        if(fileExt == "obj" || fileExt == "zip" ){
            AUI().use("liferay-portlet-url", function(a) {
                var portletURL = Liferay.PortletURL.createRenderURL();
                portletURL.setPortletMode("view");
                portletURL.setWindowState("pop_up");
                portletURL.setPortletId("eturbanalyzer_WAR_eturbportlet");
                portletURL.setParameter("fileEntryId", fileEntryId);
                
                Liferay.Util.openWindow(
                    {
                        dialog: {
                            width:1024,
                            height:800,
                            cache: false,
                            draggable: false,
                            resizable: false,
                            modal: true,
                            destroyOnClose: true,
                            after: {
                                render: function(event) {
                                    $("button.btn.close").on("click", function(e){
                                        $("body").css('overflow','');
                                    });
                                }
                            }
                        },
                        id: "<portlet:namespace/>fileSearchDialog",
                        uri: portletURL.toString(),
                        title: "FILE ANALYZE"
                    }
                );
            });
        }
    }
    
    //최상위 폴더 목록 가져오기
    function <portlet:namespace/>getRepositoryFolder(){
        var arr = [];
        jQuery.ajax({
            type: "POST",
            url: "<%=getRepositoryFolderURL%>",
            data: $("form[name=<portlet:namespace/>form]").serialize(),
            async : false,
            success: function(data) {
                var dataSize = data.dataList.length;
                var dataMap = data.dataList;
                if(dataSize>0){
                    for(var i=0 ; i<dataSize; i++ ){
                        var obj = {
                            "id": dataMap[i].fileId,
                            "text": dataMap[i].fileName,
                            "type":"close",
                            "li_attr": {
                                "childLength":dataMap[i].childCnt
                            }
                        };
                        arr.push(obj);
                    }
                }
            },error:function(jqXHR, textStatus, errorThrown){
                if(jqXHR.responseText !== ''){
                    alert(textStatus+": "+jqXHR.responseText);
                }else{
                    alert(textStatus+": "+errorThrown);
                }  
            }
        });
        return arr;
    }
    
    //선택한 폴더의 하위폴더 목록 가져오기
    function <portlet:namespace/>getChildFolder(folderId){
        var selectFolder = folderId.valueOf();
        var arr = [];
        var groupId = $("#<portlet:namespace/>groupId").val();
        var vcToken = $("#<portlet:namespace/>vcToken").val();
        var icebreakerUrl = $("#<portlet:namespace/>icebreakerUrl").val();
        
        jQuery.ajax({
            type: "POST",
            url: "<%=getChildFolderURL%>",
            data: {
                "<portlet:namespace/>groupId" : groupId,
                "<portlet:namespace/>vcToken" : vcToken,
                "<portlet:namespace/>icebreakerUrl" : icebreakerUrl,
                "<portlet:namespace/>selectFolderId" : selectFolder
            },
            async : false,
            success: function(data) {
                var dataSize = data.dataList.length;
                var dataMap = data.dataList;
                if(dataSize>0){
                    for(var i=0 ; i<dataSize; i++ ){
                        var obj = {
                            "id": dataMap[i].fileId,
                            "parent": selectFolder,
                            "text": dataMap[i].fileName,
                            "type":"close",
                            "li_attr": {
                                "childLength":dataMap[i].childCnt
                            }
                        };
                        
                        arr.push(obj);
                    }
                }
            },error:function(jqXHR, textStatus, errorThrown){
                if(jqXHR.responseText !== ''){
                    alert(textStatus+": "+jqXHR.responseText);
                }else{
                    alert(textStatus+": "+errorThrown);
                }
            }
        });
        return arr;
    }
    
    
    //선택한 폴더의 하위파일 목록 가져오기
    function <portlet:namespace/>getChildFile(folderId){
        
        var returnFileList = new Array();
        
        var liferayWindowState = "${liferayWindowState}";
        var selectFolder = folderId+"";
        var fileExt = "${fileExt}";
        var fileIdFilter = "${fileIdFilter}";
        
        var groupId = $("#<portlet:namespace/>groupId").val();
        var vcToken = $("#<portlet:namespace/>vcToken").val();
        var icebreakerUrl = $("#<portlet:namespace/>icebreakerUrl").val();
        
        var folderArr = null;
        if(folderId === "HOME"){
            folderArr = <portlet:namespace/>getRepositoryFolder();
        } else {
            folderArr = <portlet:namespace/>getChildFolder(folderId);
        }
        
        jQuery.ajax({
            type: "POST",
            url: "<%=getChildFileURL%>",
            data: {
                "<portlet:namespace/>groupId" : groupId,
                "<portlet:namespace/>vcToken" : vcToken,
                "<portlet:namespace/>icebreakerUrl" : icebreakerUrl,
                "<portlet:namespace/>selectFolderId" : selectFolder,
                "<portlet:namespace/>fileExt" : fileExt,
                "<portlet:namespace/>fileIdFilter" : fileIdFilter
            },
            async : false,
            success: function(data) {
                var dataSize = data.dataList.length;
                var dataMap = data.dataList;
                
                $fileTableBody = $("#fileTableBody");
                $("#fileTableBody li").remove();
                
                if(dataSize>0){
                    for(var i=0 ; i<dataSize; i++ ){
                        var fileObj = new Object();
                        
                        var fileName = dataMap[i].fileName;
                        
                        $li = $("<li/>").attr("class", "<portlet:namespace/>file").attr("file-name", fileName).appendTo($fileTableBody);
                              //.css("display", "inline-block").css("width","200px").css("white-space","nowrap").appendTo($fileTableBody);
                        if(i%2 == 1){ $li.addClass("tablebgtr"); }
                        
                        /* File Extension check */
                        /* 확장자에 따라 파일 이미지 변경 */
                        /* 확장자 : txt, obj, conf */
                        var fileExt = fileName.substr(fileName.lastIndexOf(".")+1, fileName.length).toLowerCase();
                        /* var imgFileExist = <portlet:namespace/>fileImageExist(fileExt); */
                        var fileImageName = "file03";
                        var fileImageWidth = "35px;";
                        /* if(imgFileExist){
                            fileImageName = fileExt;
                            fileImageWidth = "32px;";
                        } */
                        
                        $label = $("<label/>");
                        $checkBox = $("<input/>").attr("type","checkbox").attr("name","<portlet:namespace/>fileChk")
                                                 .attr("onclick", "<portlet:namespace/>showSelectBtn();").attr("value",dataMap[i].fileId).attr("style", "float:left;");
                        $img = $("<img/>").attr("src","${contextPath}/images/folderm/" + fileImageName + ".png").attr("width",fileImageWidth);
                        $fileName = $("<span/>").attr("id","<portlet:namespace/>thisFileName").text(fileName)
                                                .css({
                                                    "float" : "left",
                                                    "width" : "125px",
                                                    "white-space" : "nowrap",
                                                    "overflow" : "hidden",
                                                    "text-overflow" : "ellipsis"
                                                });
                        $lastModified = $("<span/>").text(dataMap[i].lastModified).attr("style", "float:left;");
                        $fileSize = $("<span/>").attr("class","filesize").text(dataMap[i].fileSize).attr("style", "float:left;");
                        
                        $label = $label.append($checkBox).append("&nbsp;&nbsp;").append($img).append("&nbsp;")
                                                         .append($fileName).append("<br/>")
                                                         .append($lastModified).append("<br/>")
                                                         .append($fileSize).css("word-break","break-all");
                        $label.appendTo($li);
                        
                        fileObj.text =  fileName;
                        fileObj.id = dataMap[i].fileId;
                        fileObj.size = dataMap[i].fileSize;
                        fileObj.type = "file";
                        
                        returnFileList.push(fileObj);
                    }
                }else{
                    $li = $("<li>").appendTo($fileTableBody);
                    $("<span>").addClass("TC")
                               .text(Liferay.Language.get('edison-there-are-no-data'))
                               .attr("colspan",4)
                               .appendTo($li);
                }
                
            },error:function(jqXHR, textStatus, errorThrown){
                if(jqXHR.responseText !== ''){
                    alert(textStatus+": "+jqXHR.responseText);
                }else{
                    alert(textStatus+": "+errorThrown);
                }
                
                returnFileList = null;
            },complete:function(){
                //helper 높이 변경
                var fileTableHeight = $(".tablemf_list").height() + 50;
                if(fileTableHeight > 582){
                    $("div.ui-resizable-handle.ui-resizable-w").css("height", fileTableHeight + "px");
                }else{
                    $("div.ui-resizable-handle.ui-resizable-w").css("height", "605px");
                }
            }
            
        });
        
        return returnFileList;
    }
    
    function <portlet:namespace/>fileImageExist(fileImageName){
        var result = null;
        $.ajax({
            url: "${contextPath}/images/folderm/" + fileImageName + ".png",
            async: false,
            success: function(data){
                result = true;
            },
            error: function(data){
                result = false;
            }
        });
        return result
    }
    
    function <portlet:namespace/>initJstree(dataArr,selectId, nodeParents){
        
        var rootData = [{
            "id":"HOME",
            "text":"HOME",
            "children": dataArr,
            "li_attr":{
                "childLength" : dataArr.length          
            }
        }];
        
        $("#myfileTree").jstree({
                "core" : {
                    "data": rootData,
                    "themes" : {
                        "name" : "proton",
                        "responsive" : true
                    },
                    "check_callback" : function(operation, node, node_parent, node_position) {
                        if (operation === "move_node") {
                            return node_parent.id != "#";
                        }
                        return true;  //allow all other operations        
                      }
                },
                "types" : {
                    "top" : {
                        
                    },
                    "file" : {
                        "icon" : "icon-file",
                        "a_attr" : { "onclick" : "<portlet:namespace/>viewerSubmit('"+this+"')"}
                    }
                },
                "contextmenu":{
                    "items": contextMenu
                },
                /* "state" : { 
                    "key" : "checklist_tree_state" 
                }, */
                "progressive_render" : true,
                "plugins" : ["types", "contextmenu",  "dnd", "progressive_render"]
            
            }).bind("loaded.jstree", function(event, data) { 
                 //$(this).jstree("open_all");//íì¬ ìµììí´ëë§ ìì¼ë¯ë¡ open_allíë©´ ì´ë¦° í´ë ìì
                 
                 //create, rename, delete 경우 jstree init 후 다시 그 노드 child 생성 및 open 
                 if(nodeParents != null){
                    for(var i = nodeParents.length-2 ; i>=0 ; i--){
                        if(nodeParents[i] != "HOME" && nodeParents[i] != "#"){
                             var childFolderArr = <portlet:namespace/>getChildFolder(nodeParents[i]);
                             
                             var node = $('#myfileTree').jstree(true).get_node(nodeParents[i]);
                             if(childFolderArr.length>0){
                                $('#myfileTree').jstree().delete_node(node.children);
                                for(var j=0; j<childFolderArr.length; j++){
                                    var obj = childFolderArr[j];
                                    $('#myfileTree').jstree().create_node( node.id ,  obj , "last", false);
                                } 
                            }
                        }
                    }
                }
                
                $("#myfileTree").jstree("select_node", selectId);
                $("#myfileTree").jstree("open_all");
                
                //아이콘변경
                <portlet:namespace/>iconChange();
            }).bind("select_node.jstree",function(evt, data){//ë¸ë ì í ì´ë²¤í¸
                if(data.node.type!="file"){
                    var length = data.node.children.length;
                
                    //파일목록 가져오기
                    var selectNodeChildFile = <portlet:namespace/>getChildFile(data.node.id);
                    
                    if(data.node.id != "HOME"){
                        //HOME가 아닐때
                        var childFolderArr = <portlet:namespace/>getChildFolder(data.node.id);
                        
                        /* 하위 파일 List 추가 */
                        childFolderArr = childFolderArr.concat(selectNodeChildFile); 
                        
                        //노드 child와 api의 하위폴더 개수와 다르면 child node create
                        if( length != childFolderArr.length ){
                            if(childFolderArr.length>0){
                                $('#myfileTree').jstree().delete_node(data.node.children);
                                for(var j=0; j<childFolderArr.length; j++){
                                    var obj = childFolderArr[j];
                                    console.log(obj);
                                    $('#myfileTree').jstree().create_node( data.node.id ,  obj , "last", false);
                                    
                                } 
                            } 
                        }
                        $("#myfileTree").jstree("toggle_node", data.node.id);
                    }
                    
                    //아이콘변경
                    <portlet:namespace/>iconChange();
                }
            }).bind("click.jstree", function (e, datap) { //ìì´ì½ í´ë¦­ ì´ë²¤í¸
                //클릭해서 노드가 없는거는 붙이기
                //클릭li 하위노드수하고 api온 자바하고 다르면 select li시키기
                var li = $(e.toElement).parent("li");
                var liId = $(e.toElement).parent("li").attr("id");
                var node = $('#myfileTree').jstree(true).get_node(liId);
                
                if(node.children != null){
                    var liChildLength = node.children.length;
                    
                    var childlength = $(e.toElement).parent("li").attr("childlength");
                    if( liChildLength != childlength && childlength > 0 ){
                        $("#myfileTree").jstree("deselect_all");
                        $("#myfileTree").jstree("select_node", li);
                    }
                    /* else if(liId == "HOME"){//HOME이고 열려있을때 노드 select
                        $("#myfileTree").jstree("deselect_all");
                        $("#myfileTree").jstree("select_node", li);
                    } */
                }
            }).bind("open_node.jstree", function(event, data) { //ë¸ëë¥¼ open
                <portlet:namespace/>iconChange();
            }).bind("close_node.jstree", function(event, data) {//ë¸ëë¥¼ closed
            }).bind("move_node.jstree", function(e, data) { // í´ë move ì´ë²¤í¸
                    var sourceId = data.node.id;
                    var sourceNodeName = data.node.text;
                    var targetId = data.parent;
                    var nodeType = data.node.type;      // node type : default, file
                    
                    console.log("sourceId" + sourceId);
                    console.log("targetId" + targetId);
                    
                    var tree = $('#myfileTree').jstree(true);
                    var node = tree.get_node(targetId);              
                    var nodeParents = node.parents;
                    var destPath = $('#myfileTree').jstree(true).get_path(node, "/") + "/"+sourceNodeName;
                    destPath = destPath.replace("HOME/", "");
                    
                    if(sourceId != "" || targetId != ""){
                        var groupId = $("#<portlet:namespace/>groupId").val();
                        var vcToken = $("#<portlet:namespace/>vcToken").val();
                        bStart();
                        jQuery.ajax({
                             type: "POST",
                             url: "<%=moveNodeURL%>",
                             data : {
                                 "<portlet:namespace/>nodeType" : nodeType,
                                 "<portlet:namespace/>destPath" : destPath,
                                 "<portlet:namespace/>groupId" : groupId,
                                 "<portlet:namespace/>vcToken" : vcToken,
                                 "<portlet:namespace/>sourceId" : sourceId,
                                 "<portlet:namespace/>targetId" : targetId
                             },
                             async : true,
                             success: function(data) {
                                 $("#myfileTree").jstree("destroy");
                                 var folderArr = <portlet:namespace/>getRepositoryFolder();
                                 var homeChildFileList = <portlet:namespace/>getChildFile("HOME");
                                 
                                 /* 하위 파일 List 추가 */
                                 folderArr = folderArr.concat(homeChildFileList);
                                 
                                 <portlet:namespace/>initJstree(folderArr, targetId, nodeParents);
                                 
                                 if(data.status == 200 || data.status == 201){
                                     if(nodeType == "file"){
	                                     alert(Liferay.Language.get('edison-simulation-myfile-move-file-alert'));
                                     } else {
	                                     alert(Liferay.Language.get('edison-simulation-myfile-move-alert'));
                                     }
                                 }else{
                                     alert(Liferay.Language.get('edison-data-update-error'));    
                                 }
                                 
                             },error:function(){
                                 bEnd();
                                 alert(Liferay.Language.get('edison-data-update-error')); 
                             },complete: function(){
                                 bEnd();
                             }
                         });
                    }
            });
    }
    
    //jstree context menu customizing
    function contextMenu(node){

        var tree = $('#myfileTree').jstree(true);
        var selectNode = $("#myfileTree").jstree("get_selected");
        
        var selectNodeType = null;
        if(selectNode.length == 1){
            selectNodeType = tree.get_node(selectNode).type;
        } else if(1 < selectNode.length){
            /* Multi File Selection */
            for(var i=0; i<selectNode.length; i++){
                nodeType = selectNodeType = tree.get_node(selectNode[i]).type;
                if(nodeType != "file"){
                    selectNodeType = null;
                    return;
                }
            }
        }
        
        if(selectNode != "HOME" && selectNodeType != "file") {//ì íí ë¸ëê° ìê±°ë ë£¨í¸ì í
            var items =  {
                    "Create": {
                        "label": Liferay.Language.get('edison-simulation-myfile-create-folder'),
                        "action": function (data) {
                            <portlet:namespace/>createFolder();
                        } 
                    },
                    "Rename": {
                        "label": Liferay.Language.get('edison-simulation-myfile-rename-folder'),
                        "action": function (data) {
                            <portlet:namespace/>renameFolder();
                            }
                        },
                    "Delete": {
                        "label": Liferay.Language.get('edison-simulation-myfile-delete-folder'),
                        "action": function (data) {
                            <portlet:namespace/>deleteFolder();
                            }
                        },
                    "Paste": {
                        "label": Liferay.Language.get('edison-simulation-myfile-paste-file'),
                        "action": function (data) {
                            <portlet:namespace/>pasteFile();
                            }
                        }
                    };
             
            return items;
        } if(selectNode != "HOME" && selectNodeType == "file") { // file contextMenu
            var items =  {
                "Copy": {
                    "label": Liferay.Language.get('edison-simulation-myfile-copy-file'),
                    "action": function (data) {
                        <portlet:namespace/>copyFile();
                    }
                },
                "Delete": {
                    "label": Liferay.Language.get('edison-button-file-delete'),
                    "action": function (data) {
                        <portlet:namespace/>checkfileDelete();
                    }
                },
                "Download": {
                    "label": Liferay.Language.get('download-file'),
                    "action": function (data) {
                        <portlet:namespace/>checkfileDownload();
                    }
                }
            };
         
            return items;
        } else if(selectNode == "HOME") {
            var items =  {
                    "Create": {
                        "label": Liferay.Language.get('edison-simulation-myfile-create-folder'),
                        "action": function (data) {
                            <portlet:namespace/>createFolder();
                        } 
                    }
            };
             
            return items;
        }
    }
    
    function <portlet:namespace/>fileDivWidthEvent2(){
        var maxWidth = 0;
        
        /*var ua = window.navigator.userAgent;
        //익스플로러인경우는 width - 25 
        if(ua.indexOf('MSIE') > 0 || ua.indexOf('Trident') > 0){
            //rightWidth = rightWidth - 25;
        }*/
        
        if(<%=popupState%>){
            $("body").css("margin","5px");
        }
        
        var fileboxWidth = $(".myfilebox").width();
        var leftWidth = $(".leftcontent").width();
        var rightWidth = fileboxWidth - leftWidth - 51;
        
        $(".myfilebox").css("width", fileboxWidth +"px");
        $(".leftcontent").css("min-width", leftWidth +"px");
        $(".leftcontent").css("width", leftWidth+"px");
        $(".rightcontent").css("width", rightWidth+"px");

        maxWidth = rightWidth;
        
        //왼쪽div resize event
          $(".rightcontent").resizable({
            maxWidth: maxWidth ,
            minWidth: maxWidth - 200 ,
            handles: 'w',
            ghost: true,
            handles: 'w',
            css:{overflow:"hidden"},
            stop: function(e, ui) {
                var parent = ui.element.parent();    
                
                var current = ui.element;
               
                var pWidth = $(".myfilebox").width();//parent.width();       
                var pHeight = parent.height();
                var cWidth = ui.element.width();
                var cHeight = ui.element.height();

                var resizeRightWidth = cWidth;
                var resizeLeftWidth = pWidth-cWidth-100;
                
                if(leftWidth >= resizeLeftWidth){//245보다 작거나 같다.
                    $(".leftcontent").css("width", leftWidth+"px");
                    resizeRightWidth = pWidth-leftWidth-50;
                }else if(leftWidth < resizeLeftWidth){//245보다 크다.
                    resizeRightWidth = pWidth-resizeLeftWidth-50;
                    $(".leftcontent").css("width", resizeLeftWidth+"px");
                }

                current.css({
                    width: resizeRightWidth+"px",
                    height : "583px;",
                    left: "0"
                });
                
                
                //helper 높이 변경
                var fileTableHeight = $(".tablemf_list").height() + 50;
                if(fileTableHeight > 584){
                    $("div.ui-resizable-handle.ui-resizable-w").css("height", fileTableHeight + "px");
                } 
            }
        });
    }
    
    //create Folder 버튼 클릭시 폴더 생성
    function <portlet:namespace/>createFolder(){
        var tree = $('#myfileTree').jstree(true);
        var selectNode = $("#myfileTree").jstree("get_selected");
        
        var obj = {
            "id":"new",
            "text":"new",
            "type":"close"
        };
        
         if(selectNode == "HOME" || selectNode == ""){//선택한 노드가 없거나 루트선택
             selectNode = "HOME";
        }
         
         var nodeParents = null;
         var parentpath = "";
         if(selectNode != ""){//선택한 노드가 있음
            var newNode = $('#myfileTree').jstree().create_node( selectNode ,  obj , "last", false);
            $("#myfileTree").jstree("open_node", selectNode);
            
            var node = tree.get_node(newNode);
            if(node){
                 tree.edit(node);
            } 
            
            nodeParents = node.parents;
            if(nodeParents.length > 0){
                for(var i = nodeParents.length-2 ; i>=0 ; i--){
                    if(nodeParents[i] != "HOME" && nodeParents[i] != "#"){
                        var nodeText = tree.get_node(nodeParents[i]).text;
                        if(parentpath != ""){
                            parentpath += "/";  
                        }
                        parentpath += nodeText;
                    }
                }
            }
        }

        $("#myfileTree").bind("create_node.jstree rename_node.jstree", function (){ //create, rename이 끝나면 event 시작
            //bStart(); 
            var newFolderNm = node.text;
            if(newFolderNm == ""){
                return false;
            }
             
            newFolderNm = newFolderNm.replace(/(\s*)/g,"");
            var parentId = selectNode.valueOf();
            
            var groupId = $("#<portlet:namespace/>groupId").val();
            var vcToken = $("#<portlet:namespace/>vcToken").val();
             
            bStart();
            jQuery.ajax({
               type: "POST",
               url: "<%=createFolderURL%>",
               data: {
                   "<portlet:namespace/>groupId" : groupId,
                   "<portlet:namespace/>vcToken" : vcToken,
                   "<portlet:namespace/>parentId" : parentId,
                   "<portlet:namespace/>parentpath" : parentpath,
                   "<portlet:namespace/>newFolderNm" : newFolderNm
               },
               async : true,
               success: function(data) {
                   $("#myfileTree").jstree("destroy");
                   var folderArr = <portlet:namespace/>getRepositoryFolder();
                   var homeChildFileList = <portlet:namespace/>getChildFile("HOME");
                   
                   /* 하위 파일 List 추가 */
                   folderArr = folderArr.concat(homeChildFileList);
                   <portlet:namespace/>initJstree(folderArr, parentId, nodeParents);
                   
                   if(data.status == 200 || data.status == 201){
                       alert(Liferay.Language.get('edison-simulation-myfile-create-alert'));
                   }else{
                       alert(Liferay.Language.get('edison-data-insert-error'));
                   }
               },error: function(){
                   bEnd();
                   alert(Liferay.Language.get('edison-data-insert-error'));
               },complete: function(){
                   bEnd();
               }
            });
        });  
    }   
    
    
    //dialogue initial
    AUI().ready(function(){
        $("#icebreaker-file-upload-dialog").dialog({
            autoOpen: false,
            width: 800,
            height: 600,
            modal: true,
            resizable: false,
            show: {effect:'fade', speed: 800}, 
            hide: {effect:'fade', speed: 800},

            open: function(event, ui) {
                $(this).parent().css('overflow', 'visible');
                $(this).css('overflow', 'visible');
                $(this).removeClass("ui-widget-content");
                $(this).parent().removeClass("ui-widget-content");

                $('.ui-widget-overlay').bind('click',function(){
                    $("#icebreaker-file-upload-dialog").dialog("close");
                })
            },
            close: function() {
                $("#icebreaker-file-upload-dialog").empty();
            }
        }).dialog("widget").find(".ui-dialog-titlebar").remove();
        
    });
    
    //upload popup
    function openPopUpFileUpload(){
        var groupId = $("#<portlet:namespace/>groupId").val();
        var vcToken = $("#<portlet:namespace/>vcToken").val();
        
        var tree = $('#myfileTree').jstree(true);
        var selectNode = $("#myfileTree").jstree("get_selected");

        if(selectNode == "HOME" || selectNode == ""){//선택한 노드가 없거나 루트선택
             selectNode = "HOME";
        }
        var node = tree.get_node(selectNode);                
        var nodeParents = node.parents;
         
        selectNode = selectNode.valueOf();
        
        var URL = "<%=fileUploadPopUpURL%>&<portlet:namespace/>isPopUp="+<%=popupState%>+"&<portlet:namespace/>groupId="+groupId;
        URL = URL + "&<portlet:namespace/>returnId=<%=returnId%>&<portlet:namespace/>returnFileName=<%=returnFileName%>&<portlet:namespace/>cluster=<%=cluster%>";
        URL = URL + "&<portlet:namespace/>workflowType=<%=workflowType%>";
        URL = URL + "&<portlet:namespace/>vcToken="+vcToken+"&<portlet:namespace/>destFolderId="+selectNode+"&<portlet:namespace/>destFolderParents="+nodeParents;
        
        $("#icebreaker-file-upload-dialog").load(URL, function (e) {
            $("#icebreaker-file-upload-dialog").dialog("open");
        });
    }
    
    //check된 file 다운로드
    function <portlet:namespace/>checkfileDownload(){
        
        var tree = $('#myfileTree').jstree(true);
        var selectFileNode = $("#myfileTree").jstree("get_selected");
        selectNodeType = tree.get_node(selectFileNode[0]).type;
        
        $iframeDiv = $("#fileDownloadIframe");
        $iframeDiv.empty();
        
        if(selectNodeType != 'file' && $("input[name=<portlet:namespace/>fileChk]:checked").length == 0 ){
            alert(Liferay.Language.get('edison-simulation-myfile-download-not-select-alert'));
        }else{
            if(selectNodeType == 'file'){
                /* jstree에서 contextMenu를 이용하여 다운로드 */
                for(var i=0; i<selectFileNode.length; i++){
                    $iframe = $("<iframe/>").attr("src","<%=icebreakerPublicUrl%>/api/file/download?id="+selectFileNode[i]).attr("style", "display:none;");
                    $iframeDiv.append($iframe);
                }
                
            } else {
                $("input[name=<portlet:namespace/>fileChk]:checked").each(function() {
                    $fileId = $(this).val();
                    $iframe = $("<iframe/>").attr("src","<%=icebreakerPublicUrl%>/api/file/download?id="+$fileId).attr("style", "display:none;");
                    $iframeDiv.append($iframe);
                });
            }
        }
        $("#<portlet:namespace/>filechkAll").prop("checked",false);
    }
    
    //check된 file 삭제
    function <portlet:namespace/>checkfileDelete(){
        var tree = $('#myfileTree').jstree(true);
        var selectFileNode = $("#myfileTree").jstree("get_selected");
        selectNodeType = tree.get_node(selectFileNode[0]).type;
        
        var deleteFile = [];
        if(selectNodeType != 'file' && $("input[name=<portlet:namespace/>fileChk]:checked").length == 0 ){
            alert(Liferay.Language.get('edison-simulation-myfile-delete-not-select-alert'));
            return false;
        }else{
            
            if(confirm(Liferay.Language.get('edison-simulation-myfile-delete-file-confirm-alert'))){
                
                if(selectNodeType == 'file'){
                    /* jstree에서 contextMenu를 이용하여 파일삭제 */
                    for(var i=0; i<selectFileNode.length; i++){
                        deleteFile.push(selectFileNode[i]);
                    }
                } else {
	                $("input[name=<portlet:namespace/>fileChk]:checked").each(function() {
	                    deleteFile.push($(this).val());
	                });
                }
                    
                var groupId = $("#<portlet:namespace/>groupId").val();
                var vcToken = $("#<portlet:namespace/>vcToken").val();
                
                if(deleteFile.length > 0){
                    var selectNode = $("#myfileTree").jstree("get_selected");
                    if(selectNodeType != 'file' && selectNode == ""){//선택한 노드가 없음
                        selectNode = "HOME";
                    } else if(selectNodeType == 'file'){
                        selectNode = tree.get_node(selectFileNode[0]).parents[0];
                        console.log("parent node : "+ selectNode);
                    }

                    bStart();
                    jQuery.ajax({
                        type: "POST",
                        url: "<%=deleteFileURL%>",
                        data: {
                            "<portlet:namespace/>groupId" : groupId,
                            "<portlet:namespace/>vcToken" : vcToken,
                            "<portlet:namespace/>deletefileId" : deleteFile.valueOf()
                        },
                        async : true,
                        success: function(data) {
                            
                            if(data.status == 200){
                                $("#myfileTree").jstree("refresh");
                                <portlet:namespace/>getChildFile(selectNode);
                                $("#<portlet:namespace/>filechkAll").prop("checked",false);
                                alert(Liferay.Language.get('edison-simulation-myfile-delete-filet-alert'));
                            }else{
                                alert(Liferay.Language.get('edison-data-delete-error'));
                            }
                        },error: function(){
                            bEnd();
                            alert(Liferay.Language.get('edison-data-delete-error'));
                        },complete: function(){
                            bEnd();
                        }
                    });  
                }
            }
        }
    }
    
    function uploadFile(input){
        
        var bool = false;
        var filesLength = input.files.length;
        
        var selectNode = $("#myfileTree").jstree("get_selected");

        if(selectNode == "HOME" || selectNode == ""){//선택한 노드가 없거나 루트선택
             selectNode = "HOME";
        }
        
        var tree = $('#myfileTree').jstree(true);
        var node = tree.get_node(selectNode);
        var nodeParents = node.parents;     //destFolderParents
        $("#destFolderParents").val(nodeParents);
         
        selectNode = selectNode.valueOf();  //destFolderId
        $("#destFolderId").val(selectNode);
        
        if(0 < filesLength){
            bool = true;
        } else {
            bool = false;
            alert(Liferay.Language.get("edison-simulation-execute-user-define-select-your-own-attachments"));
            return false;
        }
        
        if(bool){
            bStart();
            $("form[name = <portlet:namespace/>fileUploadForm]").submit();
            bEnd();
        }else{
            bEnd();
        }
    }
    
    function <portlet:namespace/>showSelectBtn(){
        var checkedLength = $('input:checkbox[name=<portlet:namespace/>fileChk]:checked').length;
        if(0<checkedLength){
            $("#<portlet:namespace/>selectBtn").show();
        } else {
            $("#<portlet:namespace/>selectBtn").hide();
        }
    }
    
    function searchFile(input){
        if(input === null || input === ""){
            $(".<portlet:namespace/>file").show();
        } else {
            $(".<portlet:namespace/>file").hide();
            //Search된 값에 해당하는 File만 display
            $(".<portlet:namespace/>file[file-name*="+input+"]").show();
        }
    }
    
    //folder rename event
    function <portlet:namespace/>renameFolder(){
        var tree = $('#myfileTree').jstree(true);
        var selectNode = $("#myfileTree").jstree("get_selected");
        
        var nodeParents = null;
        var parentpath = "";
        var node = null;
        if(selectNode != "HOME" && selectNode != ""){//선택한 노드가 있음
            node = tree.get_node(selectNode);
            if(node){
                 tree.edit(node);
            }
            nodeParents = node.parents;
        } 
        $("#myfileTree").bind("rename_node.jstree", function (){ //rename 이 끝나면 event 시작

            var newFolderNm = node.text;
            newFolderNm = newFolderNm.replace(/(\s*)/g,"");
            var selectFolderId = selectNode+"";

            var groupId = $("#<portlet:namespace/>groupId").val();
            var vcToken = $("#<portlet:namespace/>vcToken").val();
            
                bStart();
                jQuery.ajax({
                    type: "POST",
                    url: "<%=renameFolderURL%>",
                    data: {
                        "<portlet:namespace/>groupId" : groupId,
                        "<portlet:namespace/>vcToken" : vcToken,
                        "<portlet:namespace/>selectFolderId" : selectFolderId,
                        "<portlet:namespace/>newFolderNm" : newFolderNm
                    },
                    async : true,
                    success: function(data) {
                        $("#myfileTree").jstree("destroy");
                        var folderArr = <portlet:namespace/>getRepositoryFolder();
                        var homeChildFileList = <portlet:namespace/>getChildFile("HOME");
                        
                        /* 하위 파일 List 추가 */
                        folderArr = folderArr.concat(homeChildFileList);
                        <portlet:namespace/>initJstree(folderArr , selectFolderId, nodeParents);
                        
                        if(data.status == 200 || data.status == 201){
                            alert(Liferay.Language.get('edison-simulation-myfile-rename-alert'));
                        }else{
                            alert(Liferay.Language.get('edison-data-update-error'));    
                        }
                         
                    },error: function(){
                        bEnd();
                        alert(Liferay.Language.get('edison-data-update-error'));
                    },complete:function(){
                        bEnd();
                    }
                });  
            
        });
    }
        
    //폴더 삭제
    function <portlet:namespace/>deleteFolder(){
        
        var tree = $('#myfileTree').jstree(true);
        var selectNode = $("#myfileTree").jstree("get_selected");
        
        var nodeParents = null;
        var parentpath = "";
        var node = null;
        if(selectNode != "HOME" && selectNode != ""){//선택한 노드가 있음
            node = tree.get_node(selectNode);
            nodeParents = node.parents;
        } 
        if(node == null) return false;
        
        if(confirm(Liferay.Language.get('edison-simulation-myfile-delete-confirm-alert'))){ 
            //안에 파일이나 하위폴더가 있으면 삭제 no????
            
            var tree = $('#myfileTree').jstree(true);
            var selectNode = $("#myfileTree").jstree("get_selected");
            if(selectNode != "HOME" && selectNode != ""){//선택한 노드가 있음
                
            
                var node = tree.get_node(selectNode);
                if(node != null){
                    var selectFolderId = node.id + "";
                    var groupId = $("#<portlet:namespace/>groupId").val();
                    var vcToken = $("#<portlet:namespace/>vcToken").val();
                    
                    bStart();
                    jQuery.ajax({
                        type: "POST",
                        url: "<%=deleteFolderURL%>",
                        data: {
                            "<portlet:namespace/>groupId" : groupId,
                            "<portlet:namespace/>vcToken" : vcToken,
                            "<portlet:namespace/>selectFolderId" : selectFolderId
                        },
                        async : true,
                        success: function(data) {
                            if(data.status == 200 || data.status == 201){
                                 var del = tree.delete_node(node);
                                 var nodeParents = node.parents;
                                 
                                 if(del){
                                    $("#myfileTree").jstree("destroy");
                                    var folderArr = <portlet:namespace/>getRepositoryFolder();
                                    var homeChildFileList = <portlet:namespace/>getChildFile("HOME");
                                    
                                    /* 하위 파일 List 추가 */
                                    folderArr = folderArr.concat(homeChildFileList);
                                    
                                    <portlet:namespace/>initJstree(folderArr, node.parent, nodeParents);
                                     alert(Liferay.Language.get('edison-simulation-myfile-delete-alert')); 
                                 }
                            }else{
                                alert(Liferay.Language.get('edison-data-delete-error'));
                            }
                        },error:function(){
                            bEnd();
                            alert(Liferay.Language.get('edison-data-delete-error'));
                        },complete: function(){
                            bEnd();
                        }
                    });  
                }
                
            } 
        }
    }
    
    function <portlet:namespace/>totalSearchSubmit(){
        
        var searchKeyword = $("#<portlet:namespace/>searchKeyword").val();
        
        if(searchKeyword && 
            $.trim(searchKeyword) != ""){
            $(".<portlet:namespace/>file").hide();
            $(".<portlet:namespace/>file[file-name*="+searchKeyword+"]").show();
        }else{
          e.preventDefault();
          alert("No input search keyword");
        }
    }
    
    function <portlet:namespace/>searchInit(){
        $("#<portlet:namespace/>searchKeyword").val("");
        $(".<portlet:namespace/>file").show();
    }
    
    function <portlet:namespace/>selectBtn(){
        var fileArray = new Array();
        $("input[name=<portlet:namespace/>fileChk]:checked").each(function() {
            var fileId = $(this).val();
            var fileName = $(this).parent().children("#<portlet:namespace/>thisFileName").text();
            var fileMapArray = [fileId,fileName];
            fileArray.push(fileMapArray);
        }); 
        Liferay.Util.getOpener().getFileIdAndName('${fileSearchType}',fileArray);
        
    }
    
    /* Save copyNode */
    var selectCopyNode = null;
    
    function <portlet:namespace/>copyFile(){
        
        var tree = $('#myfileTree').jstree(true);
        var multiSelectNode = $("#myfileTree").jstree("get_selected");
        var selectNode = null;
        
        selectNode = multiSelectNode;
        
        // selectCopyNode 초기화
        selectCopyNode = null;
        selectCopyNode = selectNode;
        
        if(selectCopyNode != null){
            alert(Liferay.Language.get('edison-simulation-myfile-copy-file-alert'));
        }
        
    }
    
    function <portlet:namespace/>pasteFile(){
        
        var tree = $('#myfileTree').jstree(true);
        var targetNode = tree.get_node($("#myfileTree").jstree("get_selected"));
        var targetId = targetNode.id;
        
        var copyFilesArray = new Array();
        
        for(var i=0; i<selectCopyNode.length; i++){
            // JSON으로 데이터 만들어서 array에 Push
            var jsonNodeInfo = new Object();
            
            jsonNodeInfo.sourceId = tree.get_node(selectCopyNode[i]).id;
            jsonNodeInfo.sourceFileName = tree.get_node(selectCopyNode[i]).text;
            
            copyFilesArray.push(JSON.stringify(jsonNodeInfo));
        }
        
        var node = tree.get_node(targetId);              
        var nodeParents = node.parents;
        var destPath = $('#myfileTree').jstree(true).get_path(node, "/") + "/";
        destPath = destPath.replace("HOME/", "");
        
        if(0 < copyFilesArray.length && targetId != ""){
            var groupId = $("#<portlet:namespace/>groupId").val();
            var vcToken = $("#<portlet:namespace/>vcToken").val();
            bStart();
            jQuery.ajax({
                 type: "POST",
                 url: "<%=copyFileURL%>",
                 data : {
                     "<portlet:namespace/>copyFilesArray" : copyFilesArray,
                     "<portlet:namespace/>copyFilesArrayLength" : copyFilesArray.length,
                     "<portlet:namespace/>destPath" : destPath,
                     "<portlet:namespace/>groupId" : groupId,
                     "<portlet:namespace/>vcToken" : vcToken,
                     "<portlet:namespace/>targetId" : targetId
                 },
                 async : true,
                 success: function(data) {
                     $("#myfileTree").jstree("destroy");
                     var folderArr = <portlet:namespace/>getRepositoryFolder();
                     var homeChildFileList = <portlet:namespace/>getChildFile("HOME");
                     
                     /* 하위 파일 List 추가 */
                     folderArr = folderArr.concat(homeChildFileList);
                     
                     <portlet:namespace/>initJstree(folderArr, targetId, nodeParents);
                     
                     if(data.status == 200 || data.status == 201){
                         alert(Liferay.Language.get('edison-simulation-myfile-create-file-alert'));
                     }else{
                         alert(Liferay.Language.get('edison-data-update-error'));    
                     }
                     
                 },error:function(){
                     bEnd();
                     alert(Liferay.Language.get('edison-data-update-error')); 
                 },complete: function(){
                     bEnd();
                 }
             });
        }
    }
    
    function <portlet:namespace/>iconChange(){ //ìì´ì½ë³ê²½ : childê° ìëì§ ìëì§ ììë¡ ìì´ì½ ë³ê²½
//      $(".jstree-node").each(function(index){
//           var childLength = $(this).attr("childlength");
//           if(childLength > 0){
//               var flag = $(this).hasClass("jstree-leaf");
//               if(flag) {
//                   $(this).removeClass("jstree-leaf").addClass("jstree-closed");
//               }
//           }
//      });
    }
</script>
