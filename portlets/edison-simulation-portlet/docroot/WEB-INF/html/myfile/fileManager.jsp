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
	String uploadDestFolerId = request.getAttribute("uploadDestFolerId")!= null?(String)request.getAttribute("uploadDestFolerId"):"";
	String destFolerParents = request.getAttribute("destFolerParents")!= null?(String)request.getAttribute("destFolerParents"):"";
%>

<liferay-portlet:resourceURL var="getRootDataURL" id="getRootData" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getSelectedFolderURL" id="getSelectedFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="moveNodeURL"   id="moveNode"   copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="copyFileURL"   id="copyFile"   copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="createFolderURL" id="createNewFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="renameFolderURL" id="renameFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="deleteFolderURL" id="deleteFolder" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="copyFileURL"   id="copyFile"   copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="deleteFileURL" id="deleteFile" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getItemInfoURL" id="getItemInfo" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:actionURL secure="<%= request.isSecure() %>" var="fileIBUploadURL">
	<liferay-portlet:param name="myaction" value="fileIBUpload" />
	<liferay-portlet:param name="returnId" value="<%=returnId%>" />
	<liferay-portlet:param name="returnFileName" value="<%=returnFileName%>" />
	<liferay-portlet:param name="cluster" value="<%=cluster%>" />
	<liferay-portlet:param name="workflowType" value="<%=workflowType%>" />
</liferay-portlet:actionURL>


<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>jQuery File Manager</title>

<!-- File manager CSS & JS -->
<link media="all" rel="stylesheet" href="${contextPath}/js/fileManager/jquery.file-manager/jquery.file-manager.css" />
<link href="${contextPath}/css/fileManager/contextMenu.css" rel="stylesheet" type="text/css" />
<link media="all" rel="stylesheet" href="${contextPath}/css/fileManager/default.css" />
<link media="all" rel="stylesheet" href="${contextPath}/css/fileManager/styles.css" />

<link href="${contextPath}/css/treeview-monitoring.css" rel="stylesheet" type="text/css">	<!-- Out Side Border -->

<script src="${contextPath}/js/fileManager/jquery.file-manager/jquery.file-manager.js"></script>
<script src="${contextPath}/js/fileManager/contextMenu.js"></script>
<script src="${contextPath}/js/fileManager/services.js"></script>

<script src="${contextPath}/js/fileManager/jquery-confirm.js"></script>
<link media="all" rel="stylesheet" href="${contextPath}/css/fileManager/jquery-confirm.css" />

<link rel="stylesheet" href="${contextPath}/css/toastr.min.css">
<script src="${contextPath}/js/toastr.min.js"></script>

<!-- END File manager -->

<style>
	.file-manager-body{
		height: 530px;
		overflow-y: auto;
	}
	.breadcrumb{
		margin-bottom: 0px !important;
	}
	.breadcrumb_item.unSelected{
		cursor: pointer;
	}
	.breadcrumb_item.selected{
		cursor: default;
	}
	#<portlet:namespace/>filManagerUpload, #fileDownloadIframe{
		display: none;
	}
	.nav.navbar-nav .toolbar:HOVER{
		cursor: pointer;
	}
	
	.toast-designer-pos { top: 130px; right: 550px; }
	
	.container.popup{
		width: 100% !important;
		padding-left: 0px !important;
		padding-right: 0px !important;
	}
	.container.select-btn{
		text-align: right;
	}
	
	.container.myfilebox{
		padding-left: 0px;
		padding-right: 0px;
	}
	
	.info-table{
		margin : 10px 0px;
		padding: 0px 10px;
		width: 100%;
	}
	
	.info-tr{
		border-bottom: 1px solid #e5e5e9;
		padding: 5px 0px;
	}
	
	.breadcrumb > li+li:before{
		content: "/\00a0" !important;
		padding: 0 5px !important;
		color: #ccc !important;
	}
	
	#<portlet:namespace/>nodeTitle{
		font-size: 20px;
	}
	
	#<portlet:namespace/>nodeInfoTable th{
		padding-left: 15px;
		border-right: 1px solid #e5e5e9;
	}
	
	#<portlet:namespace/>nodeInfoTable td{
		padding: 5px 0px 5px 15px;
		font-size: 12px;
	}
</style>

<aui:form name="form">
	<aui:input type="hidden" name="groupId" id="groupId" value="${groupId }"></aui:input>
	<aui:input type="hidden" name="vcToken" id="vcToken" value="${icebreakerVcToken.vcToken}"></aui:input>
	<aui:input type="hidden" name="icebreakerUrl" id="icebreakerUrl" value="${icebreakerUrl}"></aui:input>
</aui:form>

<!-- File Upload Form -->
<form method="post" name="<portlet:namespace/>fileUploadForm" enctype="multipart/form-data" action="<%=fileIBUploadURL %>" >
	<input type="hidden" name="groupId" value="${groupId }" />
	<input type="hidden" name="vcToken" value="${icebreakerVcToken.vcToken}" />
	<input type="hidden" id="destFolderId" name="destFolderId" value="" />
	<input type="hidden" id="destFolderParents" name="destFolderParents" value="" />
	<input type="hidden" id="destFolderPath" name="destFolderPath" value="" />
	<input type="hidden" name="isPopUp" value="<%=popupState%>" />
	<input id="<portlet:namespace/>filManagerUpload" name="<portlet:namespace/>addFile" type="file" multiple style="display: none;" onchange="_fileManager_uploadFile(this);" />
</form>
	
	<div class="h10"></div>
	
<div class="container myfilebox">
	<input type="hidden" id="<portlet:namespace/>thisChildFolderCnt" value="">
	<input type="hidden" id="<portlet:namespace/>thisChildFileCnt" value="">
	
	<nav class="navbar navbar-default">
		<div>
			
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="padding-left: 15px; padding-right: 15px;">
				<ul class="nav navbar-nav">
					<li title="Level up" class="toolbar toolbar-level-up"><a class="icon-fb-file-upload" style="font-size: 18px;"></a></li>
					<li title="Upload" class="toolbar toolbar-upload"><a class="icon-upload" style="font-size: 18px;"></a></li>
					<li title="New folder" class="toolbar toolbar-new-folder"><a class="icon-folder-open" style="font-size: 18px;"></a></li>
					<li title="Grid view" class="toolbar toolbar-gridview"><a class="icon-th" style="font-size: 18px;"></a></li>
					<li title="List view" class="toolbar toolbar-listview"><a class="icon-th-list" style="font-size: 18px;"></a></li>
				</ul>
				
				<form class="navbar-form navbar-right" role="search">
					<div class="input-group">
						<input type="text" id="<portlet:namespace/>searchKeyword" onkeyup="<portlet:namespace/>searchNode(this.value)" class="form-control nav-inp fileSearchBox" placeholder="Search" />
						<div class="input-group-btn">
							<input type="button" value="Clear" id="<portlet:namespace/>searchInit" name="searchInit" class="btn btn-default" onclick="<portlet:namespace/>searchNode('')" style="width: 65px;" /> 
						</div>
					</div>
				</form>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>
	<ol class="breadcrumb">
		<li>
			<span id="HOME" class="breadcrumb_item selected" onclick="moveFolder(this)">HOME</span>
		</li>
	</ol>
	
	<!-- Folder, File List -->
	<div class="explorer file-manager-body">
		
	</div>
	
	<div id="fileDownloadIframe"> </div>
	
</div>

<div class="container popup select-btn">
	<input class="addIp button08_1" id="<portlet:namespace/>selectBtn" onclick="_fileManager_selectBtn('btn');" value="<liferay-ui:message key='edison-table-list-header-select'/>" type="button" />
</div>

<button id="<portlet:namespace/>nodeInfoModalBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#<portlet:namespace/>nodeInfoModal" style="display: none;">
</button>

<!-- Modal -->
<div class="modal fade" id="<portlet:namespace/>nodeInfoModal" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace/>nodeInfoModal" aria-hidden="true" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<span class="modal-title" id="<portlet:namespace/>nodeTitle">
					<liferay-ui:message key="edison-science-appstore-view-tab-detail-view"/>
				</span>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<!-- File Manager Script -->
<script>
	var toastr;
	
	toastr.options = {
		"closeButton": true,
		"debug": false,
		"newestOnTop": true,
		"progressBar": false,
		"positionClass": "toast-designer-pos",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "1000",
		"timeOut": "5000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "slideDown",
		"hideMethod": "slideUp"
	};
	
	$(function(){
		
		// popup으로 출력할 경우 CSS 적용을 위한 class 추가, 선택버튼 View
		if(<%=popupState%>){
			$(".myfilebox").addClass("popup");
			$(".select-btn").show();
		} else {
			$(".select-btn").hide();
		}
		
		// Init File Manager
		initFileManager();
		
		$('.nav-inp').focusin(function() {
			$(this).animate({
				width: "350px"
			}, 700);
		}).focusout(function() {
			$(this).animate({
				width: "172px"
			}, 700);
		});
		
		// Level Up Icon Click Event
		$('.toolbar-level-up').click(function(){
			folderLevelup();
		});
		
		// Upload Icon Click Event 
		$('.toolbar-upload').click(function(){
			$("#<portlet:namespace/>filManagerUpload").click();
		});
		
		// New Folder Icon Click Event
		$('.toolbar-new-folder').click(function(){
			_fileManager_createNewFolder();
		});
		
	});
	
	// Init File Manager
	function initFileManager(){
		
		var groupId = $("#<portlet:namespace/>groupId").val();
		var vcToken = $("#<portlet:namespace/>vcToken").val();
		var icebreakerUrl = $("#<portlet:namespace/>icebreakerUrl").val();
		
		var fileExt = "${fileExt}";
		var fileIdFilter = "${fileIdFilter}";
		
		bStart();
		$.ajax({
			// url: '${contextPath}/sample-data/root.json'
			url: "<%=getRootDataURL%>",
			cache: false,
			data: {
				"<portlet:namespace/>groupId" : groupId,
				"<portlet:namespace/>vcToken" : vcToken,
				"<portlet:namespace/>icebreakerUrl" : icebreakerUrl,
				"<portlet:namespace/>fileExt" : fileExt,
				"<portlet:namespace/>fileIdFilter" : fileIdFilter
			},
			success: function(response) {
				$('.explorer').fileManager(response.dataArray);
				var folderCnt = response.folderCount;
				var fileCnt = response.fileCount;
				$("#<portlet:namespace/>thisChildFolderCnt").val(folderCnt);
				$("#<portlet:namespace/>thisChildFileCnt").val(fileCnt);
			}, error:function(data,e){ 
				toastr["error"]("", Liferay.Language.get('edison-data-search-error'));
			},complete: function(){
				bEnd();
			}
		});
	}
	
	// Get Folder and File in Selected Folder
	function viewSelectedFolder(selectedFolderId){
		
		var groupId = $("#<portlet:namespace/>groupId").val();
		var vcToken = $("#<portlet:namespace/>vcToken").val();
		var icebreakerUrl = $("#<portlet:namespace/>icebreakerUrl").val();
		
		var fileExt = "${fileExt}";
		var fileIdFilter = "${fileIdFilter}";
		
		bStart();
		$.ajax({
			url: "<%=getSelectedFolderURL%>",
			cache: false,
			data: {
				"<portlet:namespace/>groupId" : groupId,
				"<portlet:namespace/>vcToken" : vcToken,
				"<portlet:namespace/>icebreakerUrl" : icebreakerUrl,
				"<portlet:namespace/>selectFolderId" : selectedFolderId,
				"<portlet:namespace/>fileExt" : fileExt,
				"<portlet:namespace/>fileIdFilter" : fileIdFilter
			},
			success: function(response) {
				$('.explorer').fileManager(response.dataArray);
			}, error:function(data,e){ 
				toastr["error"]("", Liferay.Language.get('edison-data-search-error'));
			},complete: function(){
				bEnd();
			}
		});
	}
	
	// Chage breadcrumb
	function changeBreadCrumb(folder){
		$('ol.breadcrumb .breadcrumb_item').removeClass("selected");
		$('ol.breadcrumb .breadcrumb_item').addClass("unSelected");
		
		breadcrumb_item_li = $('<li></li>');
		$('<span></span>').addClass("breadcrumb_item sub selected")
						  .attr("id", folder.id)
						  .attr("onclick", "moveFolder(this)")
						  .text(folder.name)
						  .appendTo(breadcrumb_item_li);
		breadcrumb_item_li.appendTo($('ol.breadcrumb'));
	}
	
	// Level Up Event
	function folderLevelup(){
		if(1 < $('ol.breadcrumb li').length){
			$('ol.breadcrumb li:last-child').remove();
			
			lastBreadCrumbItem = $('ol.breadcrumb li:last-child .breadcrumb_item');
			
			lastBreadCrumbItem.addClass("selected");
			lastBreadCrumbItem.removeClass("unSelected");
			viewSelectedFolder(lastBreadCrumbItem.attr("id"));
		}
	}
	
	// breadcrumb text click event
	function moveFolder(selectedFolder){
		var folder = $(selectedFolder);
		folder.parent().nextAll("li").remove();
		folder.removeClass("unSelected");
		folder.addClass("selected");
		viewSelectedFolder(folder.attr("id"));
	}
	
	// Create New Folder
	function _fileManager_createNewFolder(){
		
		var parentPath = "/";
		if(1 < $(".breadcrumb_item").length){
			$(".breadcrumb_item.sub").each(function(index, item){
				var value = $(item).text();
				if(index == 0){
					parentPath = value;
				} else {
					parentPath += "/" + value;
				}
			})
		}
		
		var parentId = $(".breadcrumb_item.selected").attr("id");
		
		$.confirm({
			title: Liferay.Language.get('new-folder'),
			content: "<div>Input New Folder Name</div>"+
					 "<div><input type=\"text\" value=\"new\" class=\"new-folder-name form-control\" /></div>"+
					 "<div class=\"h10\"></div>",
			buttons: {
				formSubmit: {
					text: "Submit",
					btnClass: "btn-blue",
					action: function(){
						var newFolderName = this.$content.find('.new-folder-name').val();
						
						if(newFolderName === null || newFolderName === "" || !newFolderName){
							toastr["error"]("", Liferay.Language.get('please-enter-a-value'));
							return false;
						}
						
						var groupId = $("#<portlet:namespace/>groupId").val();
						var vcToken = $("#<portlet:namespace/>vcToken").val();
						
						bStart();
						jQuery.ajax({
							type: "POST",
							url: "<%=createFolderURL%>",
							cache: false,
							data: {
								"<portlet:namespace/>groupId" : groupId,
								"<portlet:namespace/>vcToken" : vcToken,
								"<portlet:namespace/>parentId" : parentId,
								"<portlet:namespace/>parentpath" : parentPath,
								"<portlet:namespace/>newFolderNm" : newFolderName
							},
							async : true,
							success: function(data) {
								var responseStatus = data.status
								if(responseStatus == 200 || responseStatus == 201){
									$(".breadcrumb_item.selected").click();
								} else {
									toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
								}
							},error: function(){
								toastr["error"]("", Liferay.Language.get('edison-data-insert-error'));
							},complete: function(){
								bEnd();
							}
						});
						
					}
				},
				cancel: function(){}
			}
		});
		
	}
	
	//folder rename event
	function _fileManager_renameFolder(selectedItem){
		
		$.confirm({
			title: Liferay.Language.get('edison-simulation-myfile-rename-folder'),
			content: "<div>Input New Folder Name</div>"+
					 "<div><input type=\"text\" value=\"" + selectedItem.attr("node_name") + "\" class=\"new-folder-name form-control\" /></div>"+
					 "<div class=\"h10\"></div>",
			buttons: {
				formSubmit: {
					text: "Submit",
					btnClass: "btn-blue",
					action: function(){
						var newFolderName = this.$content.find('.new-folder-name').val();
						
						if(newFolderName === null || newFolderName === "" || !newFolderName){
							toastr["error"]("", Liferay.Language.get('please-enter-a-value'));
							return false;
						}
						newFolderName = newFolderName.replace(/(\s*)/g,"");
						
						var selectFolderId = selectedItem.attr("id");
						
						var groupId = $("#<portlet:namespace/>groupId").val();
						var vcToken = $("#<portlet:namespace/>vcToken").val();
						
						bStart();
						jQuery.ajax({
							type: "POST",
							url: "<%=renameFolderURL%>",
							cache: false,
							data: {
								"<portlet:namespace/>groupId" : groupId,
								"<portlet:namespace/>vcToken" : vcToken,
								"<portlet:namespace/>selectFolderId" : selectFolderId,
								"<portlet:namespace/>newFolderNm" : newFolderName
							},
							async : true,
							success: function(data) {
								var responseStatus = data.status
								if(responseStatus == 200 || responseStatus == 201){
									$(".breadcrumb_item.selected").click();
								} else {
									toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
								}
							},error: function(){
								toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
							},complete:function(){
								bEnd();
							}
						});
						
					}
				},
				cancel: function(){}
			}
		});
		
	}
	
	function _fileManager_deleteFolder(selectedItem){
		
		$.confirm({
			
			title: Liferay.Language.get('delete-file'),
			content: Liferay.Language.get('edison-simulation-myfile-delete-confirm-alert'),
			buttons:{
				confirm: function(){
					
					var selectFolderId = selectedItem.attr("id");
					var groupId = $("#<portlet:namespace/>groupId").val();
					var vcToken = $("#<portlet:namespace/>vcToken").val();
					
					bStart();
					jQuery.ajax({
						type: "POST",
						url: "<%=deleteFolderURL%>",
						cache: false,
						data: {
							"<portlet:namespace/>groupId" : groupId,
							"<portlet:namespace/>vcToken" : vcToken,
							"<portlet:namespace/>selectFolderId" : selectFolderId
						},
						async : true,
						success: function(data) {
							if(data.status == 200 || data.status == 201){
								$(".breadcrumb_item.selected").click();
							}else{
								toastr["error"]("", Liferay.Language.get('edison-data-delete-error'));
							}
						},error:function(){
							toastr["error"]("", Liferay.Language.get('edison-data-delete-error'));
						},complete: function(){
							bEnd();
						}
					});	
					
				}, 
				cancel:function(){}
			}
			
		});
		
	}
	
	// Copy Note Event
	var selectCopyNode = null;
	var fileCut = false;
	
	function _fileManager_copyNode(selectedItem){
		
		
		// selectCopyNode 초기화
		selectCopyNode = null;
		selectCopyNode = selectedItem;
		
		if(selectCopyNode != null){
			toastr["success"]("", Liferay.Language.get('edison-simulation-myfile-copy-file-alert'));
		} 
	}

	//파일 붙여넣기
	function _fileManager_pasteNode(selectedItem){
		
		var targetId = selectedItem.attr("id");
		
		var copyFilesArray = new Array();
		
		for(var i=0; i<selectCopyNode.length; i++){
			// JSON으로 데이터 만들어서 array에 Push
			var jsonNodeInfo = new Object();
			
			jsonNodeInfo.sourceId = selectCopyNode.attr("id");
			jsonNodeInfo.sourceFileName = selectCopyNode.attr("node_name");
			
			copyFilesArray.push(JSON.stringify(jsonNodeInfo));
		}
		
		var destPath = "";
		if(1 < $(".breadcrumb_item").length){
			$(".breadcrumb_item.sub").each(function(index, item){
				var value = $(item).text();
				if(index == 0){
					destPath = value;
				} else {
					destPath += "/" + value;
				}
			})
		}
		destPath = destPath.replace("HOME/", "");
		destPath += "/" + selectedItem.text() + "/";
		
		if(0 < copyFilesArray.length && targetId != ""){
			var groupId = $("#<portlet:namespace/>groupId").val();
			var vcToken = $("#<portlet:namespace/>vcToken").val();
			
			bStart();
			jQuery.ajax({
				type: "POST",
				url: "<%=copyFileURL%>",
				cache: false,
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
					if(data.status == 200 || data.status == 201){
						$(".breadcrumb_item.selected").click();
					}else{
						toastr["error"]("", Liferay.Language.get('edison-data-delete-error'));
					}
				},error:function(){
					toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
				},complete: function(){
					// 붙여넣기 기능 동작 후 Copy된 파일이 없도록 null 초기화
					bEnd();
					selectCopyNode=null;
				}
			});
		}
	}
	
	// file download
	function _fileManager_fileDownload(selectedItem){
		
		$iframeDiv = $("#fileDownloadIframe");
		$iframeDiv.empty();
		
		$iframe = $("<iframe/>").attr("src","<%=icebreakerPublicUrl%>/api/file/download?id="+selectedItem.attr('id'));
		$iframeDiv.append($iframe);
	}
	
	// file delete
	function _fileManager_fileDelete(selectedItem){
		
		if(confirm(Liferay.Language.get('edison-simulation-myfile-delete-file-confirm-alert'))){
			
			var groupId = $("#<portlet:namespace/>groupId").val();
			var vcToken = $("#<portlet:namespace/>vcToken").val();
			var deleteFileId = selectedItem.attr("id");
			
			bStart();
			jQuery.ajax({
				type: "POST",
				url: "<%=deleteFileURL%>",
				cache: false,
				data: {
					"<portlet:namespace/>groupId" : groupId,
					"<portlet:namespace/>vcToken" : vcToken,
					"<portlet:namespace/>deletefileId" : deleteFileId
				},
				async : true,
				success: function(data) {
					if(data.status == 200 || data.status == 201){
						$(".breadcrumb_item.selected").click();
					}else{
						toastr["error"]("", Liferay.Language.get('edison-data-delete-error'));
					}
				},error: function(){
					toastr["error"]("", Liferay.Language.get('edison-data-delete-error'));
				},complete: function(){
					bEnd();
				}
			});	
		}
	}
	
	function _fileManager_moveNode(sourceNode, targetNode){
		var sourceNodeId = sourceNode.attr("id");
		var sourceNodeName = sourceNode.attr("node_name");
		var targetId = targetNode.attr("id");
		var targetName = targetNode.attr("node_name");
		var nodeType = sourceNode.attr("nodeType");	// node type : directory, file
		
		var destPath = "";
		$(".breadcrumb_item").each(function(index, item){
			if(0<index){
				var value = $(item).text();
				destPath += value + "/";
			}
		})
		destPath += targetName + "/" + sourceNodeName;
		
		if(sourceNodeId != "" || targetId != ""){
			var groupId = $("#<portlet:namespace/>groupId").val();
			var vcToken = $("#<portlet:namespace/>vcToken").val();
			var icebreakerUrl = $("#<portlet:namespace/>icebreakerUrl").val();
			
			bStart();
			jQuery.ajax({
				type: "POST",
				url: "<%=moveNodeURL%>",
				cache: false,
				data: {
					"<portlet:namespace/>nodeType" : nodeType,
					"<portlet:namespace/>destPath" : destPath,
					"<portlet:namespace/>groupId" : groupId,
					"<portlet:namespace/>vcToken" : vcToken,
					"<portlet:namespace/>icebreakerUrl" : icebreakerUrl,
					"<portlet:namespace/>sourceId" : sourceNodeId,
					"<portlet:namespace/>targetId" : targetId
				},
				async : true,
				success: function(data) {
					$(".breadcrumb_item.selected").click();
				},error:function(){
					toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
				},complete: function(){
					bEnd();
				}
			}); 
		}
	}
	
	// file upload
	function _fileManager_uploadFile(input){
		
		var filesLength = input.files.length;
		
		var selectNode = $('ol.breadcrumb .breadcrumb_item.selected').attr("id");		// 파일 업로드 할 폴더의 ID
		
		if(selectNode == "HOME" || selectNode == ""){//선택한 노드가 없거나 루트선택
			selectNode = "HOME";
		}
		
		var nodeParents = "";		//destFolderParents		부모 폴더들의 ID
		var destPath = "";			// Upload할 폴더 Path
		$(".breadcrumb_item").each(function(index, item){
			var value = $(item).attr("id");
			if(index == 0){
				nodeParents += value;
			} else {
				nodeParents += ", " + value;
			}
			
			var filePath = $(item).text();
			destPath += filePath + "/";
		});
		
		$("#destFolderParents").val(nodeParents);
		$("#destFolderPath").val(destPath);
		 
		selectNode = selectNode.valueOf();  //destFolderId
		$("#destFolderId").val(selectNode);
		
		if(0 < filesLength){
			$("form[name = <portlet:namespace/>fileUploadForm]").submit();
		} else {
			toastr["info"]("", Liferay.Language.get('edison-simulation-execute-user-define-select-your-own-attachments'));
			return false;
		}
	}
	
	// View Info (getItemInfo)
	function _fileManager_viewInfo(selectedItemId, itemType, bg){
		
		var groupId = $("#<portlet:namespace/>groupId").val();
		var vcToken = $("#<portlet:namespace/>vcToken").val();
		var icebreakerUrl = $("#<portlet:namespace/>icebreakerUrl").val();
		
		bStart();
		jQuery.ajax({
			type: "POST",
			url: "<%=getItemInfoURL%>",
			cache: false,
			data: {
				"<portlet:namespace/>groupId" : groupId,
				"<portlet:namespace/>vcToken" : vcToken,
				"<portlet:namespace/>icebreakerUrl" : icebreakerUrl,
				"<portlet:namespace/>itemId" : selectedItemId,
				"<portlet:namespace/>itemType" : itemType
			},
			async : true,
			success: function(data) {
				var itemInfoMap = data.itemInfoMap;
				
				// 1000 bytes 이상일 경우 KB 단위로 변환
				var itemSize = "";
				if(1000 <= itemInfoMap.size){
					itemSize = (itemInfoMap.size/1000).toFixed(3) + " KB";
				} else if(1 < itemInfoMap.size && itemInfoMap.size < 1000) {
					itemSize = itemInfoMap.size + " Bytes";
				} else {
					itemSize = itemInfoMap.size + " Byte";
				}
				
				var infoText = "";
				var breadCrumbPath = "";
				$("span.breadcrumb_item").each(function(index){
					if(bg && index == $("span.breadcrumb_item").length-1){
						return true;
					}
					breadCrumbPath += "/" + $(this).text();
				});
				
				var tableColgroup = "<colgroup> <col width=\"30%\"> <col width=\"70%\"> </colgroup>"
				
				if(itemType == 'folder'){
					if(selectedItemId == undefined || selectedItemId == null || selectedItemId == ''){
						var homeFolderCnt = $("#<portlet:namespace/>thisChildFolderCnt").val()
						var homeFileCnt = $("#<portlet:namespace/>thisChildFileCnt").val()
						infoText = "<table id=\"<portlet:namespace/>nodeInfoTable\" class=\"info-table\" style=\"width:100%\">" + tableColgroup +
										"<tr class=\"info-tr\"><th class=\"info-key\">NAME</th><td class=\"info-value\">HOME</td></tr>" + 
										"<tr class=\"info-tr\"><th class=\"info-key\">TYPE</th><td class=\"info-value\">FOLDER</td></tr>" +
										"<tr class=\"info-tr\"><th class=\"info-key\">PATH</th><td class=\"info-value\">/HOME</td></tr>" + 
										"<tr class=\"info-tr\">" + 
											"<th class=\"info-key\">Content</th>" + 
											"<td class=\"info-value\">Folders : " + homeFolderCnt + ", files : " + homeFileCnt + "</td>" + 
										"</tr>" + 
									"</table>";
					} else {
						infoText = "<table id=\"<portlet:namespace/>nodeInfoTable\" class=\"info-table\" style=\"width:100%\">" + tableColgroup +
										"<tr class=\"info-tr\"><th class=\"info-key\">NAME</th><td class=\"info-value\">"+itemInfoMap.name+"</td></tr>" + 
										"<tr class=\"info-tr\"><th class=\"info-key\">TYPE</th><td class=\"info-value\">FOLDER</td></tr>" +
										"<tr class=\"info-tr\"><th class=\"info-key\">PATH</th><td class=\"info-value\">"+breadCrumbPath+ "/" + itemInfoMap.name +"</td></tr>" +
										"<tr class=\"info-tr\">" + 
											"<th class=\"info-key\">Content</th>" + 
											"<td class=\"info-value\">Folders : " + itemInfoMap.folderCount + ", files : " + itemInfoMap.fileCount + "</td>" + 
										"</tr>" +
										"<tr class=\"info-tr\"><th class=\"info-key\">SIZE</th><td class=\"info-value\">"+itemSize+"</td></tr>" + 
										"<tr class=\"info-tr\"><th class=\"info-key\">Last Modified</th><td class=\"info-value\">"+itemInfoMap.lastModified+"</td></tr>" + 
									"</table>";
					}
				} else if(itemType == 'file'){
					infoText = "<table id=\"<portlet:namespace/>nodeInfoTable\" class=\"info-table\" style=\"width:100%\">" + tableColgroup +
										"<tr class=\"info-tr\"><th class=\"info-key\">NAME</th><td class=\"info-value\">"+itemInfoMap.name+"</td></tr>" + 
										"<tr class=\"info-tr\"><th class=\"info-key\">TYPE</th><td class=\"info-value\">FILE</td></tr>" +
										"<tr class=\"info-tr\"><th class=\"info-key\">PATH</th><td class=\"info-value\">"+breadCrumbPath+ "/" + itemInfoMap.name +"</td></tr>" +
										"<tr class=\"info-tr\"><th class=\"info-key\">SIZE</th><td class=\"info-value\">"+itemSize+"</td></tr>" + 
										"<tr class=\"info-tr\"><th class=\"info-key\">Last Modified</th><td class=\"info-value\">"+itemInfoMap.lastModified+"</td></tr>" + 
									"</table>";
				}
				
				$("#<portlet:namespace/>nodeInfoModal div.modal-body").html("");
				$("#<portlet:namespace/>nodeInfoModal div.modal-body").append(infoText);
				$("#<portlet:namespace/>nodeInfoModalBtn").click();
				
				/* $.dialog({
					title : "Information",
					content: infoText,
					columnClass: 'col-md-6 col-md-offset-3'
				}); */
			},error:function(){
				toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
			},complete: function(){
				bEnd();
			}
		});	
	}
	
	// Search Function
	function <portlet:namespace/>searchNode(input){
		if(input === null || input === ""){
			$("#<portlet:namespace/>searchKeyword").val("");
			$(".fileManager_searchNode").show();
		} else {
			var searchDataUpper = input.toUpperCase();
			var searchDataLower = input.toLowerCase();
			
			$(".fileManager_searchNode").hide();
			//Search된 값에 해당하는 File만 display
			$(".fileManager_searchNode[node_name*="+searchDataUpper+"]").show();
			$(".fileManager_searchNode[node_name*="+searchDataLower+"]").show();
		}
	}
	
	
	function <portlet:namespace/>fileDivWidthEvent(){
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
		var rightWidth = fileboxWidth - leftWidth - 5;
		
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
				/* if(fileTableHeight > 584){
					$("div.ui-resizable-handle.ui-resizable-w").css("height", fileTableHeight + "px");
				}  */
			}
		});
	}
	
	// Open Popup Modal : 팝업 호출하는 portlet에서 작성되어야 할 내용
	$("#openFileManagerPopup").click(function() {
		var type = "MESH";
		var fileExt = DASH.Constants.getFileExtension(type);
		fileIdFilter = "";
		
		AUI().use("liferay-portlet-url", function(a) {
			var portletURL = Liferay.PortletURL.createRenderURL();
			portletURL.setPortletMode("view");
			portletURL.setWindowState("pop_up");
			portletURL.setPortletId("edisonfilemanager_WAR_edisonsimulationportlet");
			portletURL.setParameter("fileExt",fileExt);
			portletURL.setParameter("fileSearchType",type);
			portletURL.setParameter("fileIdFilter",fileIdFilter);
			
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
				title: "File SEARCH"
				}
			);
		});
	});
	
	function _fileManager_selectBtn(eventType){
		
		var fileArray = new Array();
		var selectedFiles = $('.ui-selected');
		
		if(eventType==='btn'){
			selectedFiles.each(function(){
				fileId = $(this).attr("id");
				fileName = $(this).attr("title");
				var fileMapArray = [fileId,fileName];
				fileArray.push(fileMapArray);
			})
		} else if(eventType==='dbl') {
			fileId = selectedFiles.attr("id");
			fileName = selectedFiles.attr("title");
			var fileMapArray = [fileId,fileName];
			fileArray.push(fileMapArray);
		}
		
		Liferay.Util.getOpener().getFileIdAndName('${fileSearchType}',fileArray);
		
	}
</script>
