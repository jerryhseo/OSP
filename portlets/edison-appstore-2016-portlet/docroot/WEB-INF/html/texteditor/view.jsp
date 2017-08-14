<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<script type="text/javascript" src="${contextPath}/js/editor/texteditor/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${contextPath}/js/editor/texteditor/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${contextPath}/js/editor/texteditor/jquery.fileupload.js"></script>
<%
	String cluster = request.getAttribute("cluster")!= null?(String)request.getAttribute("cluster"):"";
	String workflowType = request.getAttribute("workflowType")!= null?(String)request.getAttribute("workflowType"):"";
	String icebreakerUrl =CustomUtil.strNull(request.getAttribute("icebreakerUrl"));
	
	String portName = request.getParameter("portName")==null?"TextEditorPort":request.getParameter("portName").toString();
	String taskId = request.getParameter("taskId")!= null?(String)request.getParameter("taskId").toString():"";
	//해당 파일을 POPUP으로 띄울경우
	boolean popupState = false;
	if(LiferayWindowState.isPopUp(request)){
		popupState = true;
	}
	String mode = request.getAttribute("mode")!= null?(String)request.getAttribute("mode"):"";

	String dialogId = request.getParameter("dialogId")!= null?(String)request.getParameter("dialogId"):"";
	if( dialogId == null || dialogId.equals(""))
	{
		dialogId = request.getAttribute("dialogId")!= null?(String)request.getAttribute("dialogId"):"";
	}
%>
<liferay-portlet:resourceURL var="getRepositoryFolderURL" id="getRepositoryFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getChildFolderURL" id="getChildFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getChildFileURL" id="getChildFile" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="savePortletSessionValueURL" id="savePortletSessionValue" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getPortletSessionValueURL" id="getPortletSessionValue" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getTextFileURL" id="getTextFile" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="saveTextFileURL" id="saveTextFile" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="readLocalTextFileURL" id="readLocalTextFile" copyCurrentRenderParameters="false" escapeXml="false"/>
<style>
.rightcontent .ui-resizable-w, .rightcontent .ui-resizable-handle{ 
	margin-left: 0px;
	background: #dbdbdb;
	width:13px;
}

.myfilebox textarea{
  /* height: 611px;
  width: 934px; */
  height:100%;
  width:100%;
  min-width:100%;
  max-width:100%;
  overflow:auto;
  margin-bottom: 0;
  margin-left: -7px;
  margin-top: -8px;
  resize: none;
  background-color: #000;
  border: 3px groove #ccc;
  color: #ccc;
  padding: 5px;
}
.edison .button08_1:hover {
  background: #3cb0fd;
  background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
  background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
  text-decoration: none;
}

.ui-dialog {
    background-color: white;
    padding: 0px;
}

.ui-dialog .ui-dialog-titlebar {
    background-color: #3cb0fd;
}

.ui-dialog .ui-dialog-content {
    overflow: hidden;
    padding-left : 20px;
}

.ui-widget-header {
    background: #f6a828;
    border: 3px solid #3cb0fd;
    color: #fff;
    font-size: 15px;
}

input[type="text"].textEditorInput{
    width: 92%;
    min-width:92%;
    max-width:92%;
}
</style>

<div class="h10"></div>
	
<!--박스-->
<aui:form name="form">
<aui:input type="hidden" name="groupId" id="groupId" value="${groupId }"></aui:input>
<aui:input type="hidden" name="vcToken" id="vcToken" value="${icebreakerVcToken.vcToken }"></aui:input>
<div class="myfilebox">
	<div class="leftcontent">
		<div id="<portlet:namespace/>myfileTree" class="mflefttree">
		</div>
	</div>
	
	<!--right contents-->
	<div class="rightcontent" id="<portlet:namespace/>rightcontent" >
		<textarea id="<portlet:namespace/>textEditor" class="textEditor"></textarea>
	</div>
</div>
</aui:form>

<div style="width:90%; float:right; text-align:right;">
	<label style="display:inline;"><liferay-ui:message key="upload-file"/>: </label>
	<input type="file" name="<portlet:namespace/>localTextFile" id="<portlet:namespace/>localTextFile"  />
	<input class="button08_1" onclick="<portlet:namespace/>resetTextEditor();" value='<liferay-ui:message key="clear-all"/>' type="button"/>
	<input class="button08_1" onclick="<portlet:namespace/>openSaveFileWindow();" value='<liferay-ui:message key="save"/>' type="button"/>
	 <input class="button01b" style="min-width:90px;" id="<portlet:namespace/>cancelBtn"  value='<liferay-ui:message key="cancel"/>' type="button"/>
	 <input type="hidden" name="<portlet:namespace/>textFileId" id="<portlet:namespace/>textFileId" >
	 <input type="hidden" name="<portlet:namespace/>textFileName" id="<portlet:namespace/>textFileName" >
</div>
<div style="clear:both"></div>
<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>
<div id="<portlet:namespace/>saveFileDialog" title="Save TextEditor File" style="display: none;">
  <form id="<portlet:namespace/>saveFileForm">
    <fieldset>
      <label for="<portlet:namespace/>saveFileName"><liferay-ui:message key="file-name"/></label>
      <input type="text" name="<portlet:namespace/>saveFileName" id="<portlet:namespace/>saveFileName" class="textEditorInput" >
      <label for="<portlet:namespace/>saveFilePath"><liferay-ui:message key="file-path"/></label>
      <input type="text" name="<portlet:namespace/>saveFilePath" id="<portlet:namespace/>saveFilePath" class="textEditorInput"  readonly >
      <input type="hidden" name="<portlet:namespace/>saveFilePath" id="<portlet:namespace/>saveFolderId" >
    </fieldset>
  </form>
</div>

<script src="${contextPath}/js/science_platform_events.js"></script>
<script type="text/javascript">
var saveFileDialog;
$(function(){
	<portlet:namespace/>clearSaveFileDialog();
	<portlet:namespace/>resetTextEditor();
	var folderArr = <portlet:namespace/>getRepositoryFolder();
	<portlet:namespace/>fileDivWidthEvent();
	
	//초기 jstree init
	if(folderArr != null){
		<portlet:namespace/>initJstree(folderArr,"", null);
	}
	
	var childFileArr =<portlet:namespace/>getChildFile("HOME");
	
	if(childFileArr.length>0)
	{
		for(var j=0; j<childFileArr.length; j++)
		{
			var obj = childFileArr[j];
			$('#<portlet:namespace/>myfileTree').jstree().delete_node(obj);
			$('#<portlet:namespace/>myfileTree').jstree().create_node( "HOME" ,  obj , "last", false);
		} 
	}
	
	$("#<portlet:namespace/>myfileTree").jstree("deselect_all");
	<portlet:namespace/>iconChange();
	
	var buttonsConfig = [
		{
			text: "Ok",
			"class": "button08_1",
			click: function() 
			{
				<portlet:namespace/>saveFileUpload(); 
			}
		},
		{
			text: "Cancel",
			"class": "button08_1",
			click: function() 
			{
				<portlet:namespace/>clearSaveFileDialog();
			}
		}
    ];

	saveFileDialog = $( "#<portlet:namespace/>saveFileDialog" ).dialog({
      autoOpen: false,
      height: 231,
      width: 350,
      modal: true,
      closeOnEscape: true,
      buttons: buttonsConfig
    });
});

function <portlet:namespace/>clearSaveFileDialog()
{
	if(typeof(saveFileDialog ) != "undefined")
		saveFileDialog.dialog("close");
	
	$("form").each(function() {  
        if(this.id == "<portlet:namespace/>saveFileForm") this.reset();  
     });  
	$("#<portlet:namespace/>textFileId").val("");
	$("#<portlet:namespace/>textFileName").val("");
}
//최상위 폴더 목록 가져오기
function <portlet:namespace/>getRepositoryFolder(){
	var arr = [];
	bStart();
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
						"type": dataMap[i].fileType,
						"li_attr": {
							"childLength":dataMap[i].childCnt
						}
					};
					arr.push(obj);
				}
			}
		},error:function(data,e){ 
			alert(Liferay.Language.get('edison-data-search-error'));
		},complete: function(){
			<portlet:namespace/>resetTextEditor();
			bEnd();
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
	jQuery.ajax({
		type: "POST",
		url: "<%=getChildFolderURL%>",
		data: {
			"<portlet:namespace/>groupId" : groupId,
			"<portlet:namespace/>vcToken" : vcToken,
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
						"type": dataMap[i].fileType,
						"li_attr": {
							"childLength":dataMap[i].childCnt
						}
					};
					
					arr.push(obj);
				}
			}
		},error:function(data,e){ 
			alert(Liferay.Language.get('edison-data-search-error'));
		}
	});
	return arr;
}

//선택한 폴더의 하위파일 목록 가져오기
function <portlet:namespace/>getChildFile(folderId){
	var selectFolder = folderId+"";
	var arr = [];
	
	var groupId = $("#<portlet:namespace/>groupId").val();
	var vcToken = $("#<portlet:namespace/>vcToken").val();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=getChildFileURL%>",
		data: {
			"<portlet:namespace/>groupId" : groupId,
			"<portlet:namespace/>vcToken" : vcToken,
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
						"type": dataMap[i].fileType,
						"li_attr": {
							"childLength":dataMap[i].childCnt
						}
					};
					arr.push(obj);
				}
			}
		},error:function(data,e){ 
			alert(Liferay.Language.get('edison-data-search-error'));
		},complete:function(){
			$("#<portlet:namespace/>rightcontent").show();
					
			//helper 높이 변경
	    	var fileTableHeight = $(".tablemf_list").height() + 50;
			//console.log( fileTableHeight );
			if(fileTableHeight > 582){
	    		$("div.ui-resizable-handle.ui-resizable-w").css("height", fileTableHeight + "px");
			}else{
				$("div.ui-resizable-handle.ui-resizable-w").css("height", "632px");
			}
		}
	});
	return arr;
}

//jstree initial
function <portlet:namespace/>initJstree(dataArr,selectId, nodeParents){
	var rootData = [{
		"id":"HOME",
		"text":"HOME",
		"type":"open",
		"children": dataArr,
		"li_attr":{
			"childLength" : dataArr.length			
		}
	}];
	
	$("#<portlet:namespace/>myfileTree").jstree({
	   "core" : {
		  "data": rootData,
	     "check_callback" : true,
	   },
	   "state" : "open",
	    "types" : {
	        "open" : {
	        	"icon" : "${contextPath}/images/fileselector/myfile-icon01.png"
	        },
	        "folder" : {
	          	"icon" : "${contextPath}/images/fileselector/myfile-icon02.png"
	        },
	        "file" : {
	          	"icon" : "${contextPath}/images/fileselector/fileicon.png"
	        }
	    },
       "progressive_render" : true,
	   "plugins" : [ "contextmenu", "state", "dnd", "types","progressive_render" ,"hotkeys"]
	}).bind("loaded.jstree", function(event, data) { 
		 //$(this).jstree("open_all");//현재 최상위폴더만 있으므로 open_all하면 열린 폴더 없음
		 if(nodeParents != null){
			for(var i = nodeParents.length-2 ; i>=0 ; i--){
				if(nodeParents[i] != "HOME" && nodeParents[i] != "#"){
					
					 var childFolderArr = <portlet:namespace/>getChildFolder(nodeParents[i]);
					 
					 var node = $('#<portlet:namespace/>myfileTree').jstree(true).get_node(nodeParents[i]);
				     if(childFolderArr.length>0){
				   		$('#<portlet:namespace/>myfileTree').jstree().delete_node(node.children);
				    	for(var j=0; j<childFolderArr.length; j++){
				    		var obj = childFolderArr[j];
				    		$('#<portlet:namespace/>myfileTree').jstree().create_node( node.id ,  obj , "last", false);
				    	} 
				    }
				}
			}
		}
		
		$("#<portlet:namespace/>myfileTree").jstree("select_node", selectId);
		$("#<portlet:namespace/>myfileTree").jstree("open_all");
		
		//아이콘변경
		<portlet:namespace/>iconChange();
	}).bind("select_node.jstree",function(evt, data){//노드 선택 이벤트
		var length = data.node.children.length;
		if( data.node.type =="file" )
		{
			<portlet:namespace/>textLoad(data.node.id);
		}
		else if(data.node.id != "HOME" )
		{
			//HOME가 아닐때
	    	var childFolderArr = <portlet:namespace/>getChildFolder(data.node.id);
	    	
	    	//노드 child와 api의 하위폴더 개수와 다르면 child node create
			if( length != childFolderArr.length )
			{
		    	if(childFolderArr.length>0)
		    	{
		   			$('#<portlet:namespace/>myfileTree').jstree().delete_node(data.node.children);
		    		for(var j=0; j<childFolderArr.length; j++)
		    		{
		    			var obj = childFolderArr[j];
		    			$('#<portlet:namespace/>myfileTree').jstree().create_node( data.node.id ,  obj , "last", false);
		    		} 
		    	} 
			}
   			
   			var childFileArr =<portlet:namespace/>getChildFile(data.node.id);
			if(childFileArr.length>0)
			{
	    		for(var j=0; j<childFileArr.length; j++)
	    		{
	    			var obj = childFileArr[j];
	   				$('#<portlet:namespace/>myfileTree').jstree().delete_node(obj);
	    			$('#<portlet:namespace/>myfileTree').jstree().create_node( data.node.id ,  obj , "last", false);
	    		} 
	    	} 
			
			$("#<portlet:namespace/>myfileTree").jstree("toggle_node", data.node.id);
		}
		//아이콘변경
    	<portlet:namespace/>iconChange();
	}).bind("click.jstree", function (e, datap) { //아이콘 클릭 이벤트
		//클릭해서 노드가 없는거는 붙이기
		//클릭li 하위노드수하고 api온 자바하고 다르면 select li시키기
		var li = $(e.toElement).parent("li");
		var liId = $(e.toElement).parent("li").attr("id");
		var node = $('#<portlet:namespace/>myfileTree').jstree(true).get_node(liId);
		
		if(node.children != null){
			var liChildLength = node.children.length;
			
			var childlength = $(e.toElement).parent("li").attr("childlength");
			if( liChildLength != childlength && childlength > 0 ){
				$("#<portlet:namespace/>myfileTree").jstree("deselect_all");
				$("#<portlet:namespace/>myfileTree").jstree("select_node", li);
			}
			/* else if(liId == "HOME"){//HOME이고 열려있을때 노드 select
				$("#<portlet:namespace/>myfileTree").jstree("deselect_all");
				$("#<portlet:namespace/>myfileTree").jstree("select_node", li);
			} */
		}
	}).bind("open_node.jstree", function(event, data) { //노드를 open
		<portlet:namespace/>iconChange();
	});
}

//아이콘변경 : child가 있는지 없는지 임시로 아이콘 변경
function <portlet:namespace/>iconChange(){ 
	$(".jstree-node").each(function(index){
  		 var childLength = $(this).attr("childlength");
		 if(childLength > 0){
			 var flag = $(this).hasClass("jstree-leaf");
			 if(flag) {
				 $(this).removeClass("jstree-leaf").addClass("jstree-closed");
			 }
		 }
  	});
}

function <portlet:namespace/>fileDivWidthEvent(){
	var maxWidth = 0;
	
	/*var ua = window.navigator.userAgent;
	//익스플로러인경우는 width - 25 
	if(ua.indexOf('MSIE') > 0 || ua.indexOf('Trident') > 0){
		//rightWidth = rightWidth - 25;
	}*/
	var fileboxWidth = $(".myfilebox").width();
	
	if(<%=popupState%>){
		$("body").css("margin","5px");
		fileboxWidth = fileboxWidth + 38;
	}

	var leftWidth = $(".leftcontent").width();
	var rightWidth = fileboxWidth - leftWidth-51;

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
	            height : "583px",
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

//upload popup
function <portlet:namespace/>openSaveFileWindow(){
	var groupId = $("#<portlet:namespace/>groupId").val();
	var vcToken = $("#<portlet:namespace/>vcToken").val();

	var tree = $('#<portlet:namespace/>myfileTree').jstree(true);
	var selectNode = $("#<portlet:namespace/>myfileTree").jstree("get_selected");
	var selectPath = tree.get_path(selectNode,"/");
	var node = tree.get_node(selectNode);				 
	var nodeParentId;
	
	if($("#<portlet:namespace/>textEditor").val() == '')
	{
		alert("파일내용을 입력해 주세요.");
	}
	else
	{
		if(selectNode == "HOME" || selectNode == ""){//선택한 노드가 없거나 루트선택
			selectNode = "HOME";
			selectPath = "/HOME/";
			nodeParentId="HOME";
			alert("파일 리스트에서 폴더를 선택하지 않을 시에는 HOME 경로에 저장됩니다.");
		}
		else if( node.type == "file")
		{
			selectPath = selectPath.replace(node.text, "");
			nodeParentId = node.parent;
		}
		else if( node.type == "open" || node.type == "folder" )
		{
			nodeParentId = node.id;
		}

		$("#<portlet:namespace/>saveFilePath").val(selectPath);
		$("#<portlet:namespace/>saveFolderId").val(nodeParentId);
	
		saveFileDialog.dialog( "open" );
	}
}

function <portlet:namespace/>saveFileUpload()
{
	var fileName = $("#<portlet:namespace/>saveFileName").val();
	var filePath = $("#<portlet:namespace/>saveFilePath").val();
	var fileContent = $("#<portlet:namespace/>textEditor").val();
	var fileFolderId = $("#<portlet:namespace/>saveFolderId").val();
	var vcToken = $("#<portlet:namespace/>vcToken").val();
	
	var isDuplicated = false;
	//IB에서 받아온 IB의 경우 등호를 기호로 인식하여 replace로 string형태로 바꿔야함
	var selectedNode = $("#"+fileFolderId.replace(/\=/g, "\\\="));
	$(selectedNode).find(" li a").each(function(index){
		
		if($(this).text() == fileName)
			isDuplicated = true;
 	});
	
	//validate
	if(fileName == '')
	{
		alert(Liferay.Language.get('enter-file-name'));
	}
	else if(isDuplicated)
	{
		alert(Liferay.Language.get('folder-or-file-name-already-exist'));	
	 }
	else if(filePath == '')
	{
		alert(Liferay.Language.get('enter-file-path'));
	}
	else if(fileContent == '')
	{
		alert(Liferay.Language.get('enter-file-content'));
	}
	else
	{
		$.ajax({
			url: "<%=saveTextFileURL%>",
			type:"post",
			dataType: "text",
			data:{
				"<portlet:namespace/>fileName" : fileName,
				"<portlet:namespace/>filePath" : filePath,
				"<portlet:namespace/>fileContent": fileContent,
				"<portlet:namespace/>fileFolderId": fileFolderId,
				"<portlet:namespace/>vcToken": vcToken
			},
			success:function(data)
			{
				var obj = {
					"id": data,
					"text": fileName,
					"type": "file"
				};
				
				$('#<portlet:namespace/>myfileTree').jstree().delete_node(obj);
    			$('#<portlet:namespace/>myfileTree').jstree().create_node( fileFolderId ,  obj , "last", false);
				
				<portlet:namespace/>clearSaveFileDialog();
				<portlet:namespace/>iconChange();

				$("#<portlet:namespace/>textFileId").val(data);
				$("#<portlet:namespace/>textFileName").val(fileName);
				
				if(workflowType == "workflow"){
					var value = {"fileId" : data, "fileName" : fileName};
					
					<portlet:namespace/>savePortletSessionValue( taskId, portName, value);
				}
			}
		});
	}
}

</script>
<!--  IPC 용 추가 -->
<script>
	var portName = '<%= portName%>';
	var workflowType = '<%= workflowType%>';
	var taskId = '<%= taskId%>';
	
	// workflowType value : workflow or single
	// editor가 창으로 열릴지 여부를 받는다(workflow일 경우 창, single일 경우 일반 포틀릿)
	if( workflowType == "workflow")
	{
		$("#<portlet:namespace/>cancelBtn").show();
	}
	else
	{
		$("#<portlet:namespace/>cancelBtn").hide();
		
		Liferay.on(OSPEvent.Constants.IPC_EVENT_REQUEST_CONTENT, function(event){
			
			var fileId = $("#<portlet:namespace/>textFileId").val();
			var filaName = $("#<portlet:namespace/>textFileName").val();

			if(fileId =="" || filaName =="")
			{
				alert(Liferay.Language.get('please-click-save-button-and-choose-a-file-to-use'));
			}
			else
			{
				var content = {
						taskId : taskId=='null'?"":taskId,
						portName : portName,
						value : {"fileId" : fileId, "fileName" : filaName}
				};
			 
				var payload = OSPEvent.createEvent(
						event.getPortName(),
						OSPEvent.Constants.TYPE_TARGET,
						'<portlet:namespace/>', 
						event.getEventEmitter(),
						content);
	
				Liferay.fire(OSPEvent.Constants.IPC_EVENT_CONTENT_BROADCAST, payload);
			}
		});
	}

	$("#<portlet:namespace/>cancelBtn").click(function() {
		Liferay.Util.getOpener().closePopup( '<%=dialogId%>' );
	});
	
	function <portlet:namespace/>savePortletSessionValue( taskId, portName, value )
	{
		$.ajax({
			url: "<%=savePortletSessionValueURL%>",
			type:"post",
			dataType: "text",
			data:{
				"<portlet:namespace/>taskId" : taskId,
				"<portlet:namespace/>portName" : portName,
				"<portlet:namespace/>value": JSON.stringify(value)
			},
			success:function(data)
			{
				Liferay.Util.getOpener().fetchResult( taskId,portName, value );
				Liferay.Util.getOpener().closePopup( '<%=dialogId%>' );
			}
		});
	}
	
	function <portlet:namespace/>getPortletSessionValue( portName )
	{
		$.ajax({
			url: "<%=getPortletSessionValueURL%>",
			type:"post",
			dataType: "text",
			data:{
				"<portlet:namespace/>portName" : portName
			},
			success:function(data)
			{
				console.log("FILESELECT GET SESSION : " + data);
			}
		});
	}
	
	function <portlet:namespace/>resetTextEditor()
	{
		$('#<portlet:namespace/>textEditor').attr("viewIndex",0);
		$('#<portlet:namespace/>textEditor').attr("totalIndex",0);
		$('#<portlet:namespace/>textEditor').attr("value", "");
		$('#<portlet:namespace/>textEditor').val("");	
		$("#<portlet:namespace/>myfileTree").jstree("deselect_all");
		$("#<portlet:namespace/>myfileTree").jstree("close_all");
		$('#<portlet:namespace/>myfileTree').jstree('open_node', 'HOME');
	}
	
	function <portlet:namespace/>textLoad(textFileId)
	{	
		var icebreakerUrl = '<%=icebreakerUrl%>';
		var downloadUrl = icebreakerUrl+"/api/file/download?id="+textFileId;
		
		var fileId = textFileId;
		var textViewData = {
			"<portlet:namespace/>downloadUrl": downloadUrl
		};
		
		$.ajax({
			type: "POST",
			url: "<%=getTextFileURL%>",
			async : false,
			data  : textViewData,
			dataType : "json",
			success: function(data) {
				<portlet:namespace/>updateTextArea( data );
			},error:function(data,e){
				console.log(data);
			}
		});
	}
	
	$('#<portlet:namespace/>localTextFile').on('click', function(e) {
    	$('#<portlet:namespace/>localTextFile').fileupload({
    	   url: "<%=readLocalTextFileURL%>",
    	   dataType : "json",
           replaceFileInput: false,
           add: function(e, data){
			   data.submit();
         	   //통신을 시작할때 처리
         	   bStart();
           },complete: function(data){
        	   <portlet:namespace/>updateTextArea( data.responseJSON );
        	   bEnd();
           }
    	});
	});
	
	function <portlet:namespace/>updateTextArea( data )
	{
		if(data.length==1){
			$('#<portlet:namespace/>textEditor').attr("viewIndex",0);
			$('#<portlet:namespace/>textEditor').attr("totalIndex",0);
			$('#<portlet:namespace/>textEditor').val(String(data));
		}else{
			$('#<portlet:namespace/>textEditor').attr("viewIndex",0);
			$('#<portlet:namespace/>textEditor').attr("totalIndex",data.length);
			$('#<portlet:namespace/>textEditor').val(String(data[0]));
			
			$('#<portlet:namespace/>textEditor').scroll(function() {
				if ($(this).scrollTop() + $(this).height() >= $(this)[0].scrollHeight - 30) {
					var viewIndex = $(this).attr("viewIndex");
					var totalIndex = $(this).attr("totalIndex");
					
					if(viewIndex<totalIndex){
						var viewIndex = viewIndex*1+1;
						$('#<portlet:namespace/>textEditor').attr("viewIndex",viewIndex);
						$('#<portlet:namespace/>textEditor').append(String(data[viewIndex]));
					}
				}
			});
		}
	}
</script>