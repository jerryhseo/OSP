<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/virtualLabClass.css" media="screen"/>

<liferay-portlet:resourceURL var="virtualLabClassNoteListURL" id="virtualLabClassNoteList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="classNoteDeleteURL" id="classNoteDelete" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL var="assetLinkViewDialogueURL"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>"
	copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="popupClassNote" />
</liferay-portlet:renderURL>

<style type="text/css">
	.buttonbox0801{margin:0 auto; overflow:hidden; padding-top:18px; padding-bottom:5px; text-align:center; float:right;} 
</style>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList();
});

function <portlet:namespace/>dataSearchList() {
	var dataForm = {
		"<portlet:namespace/>classId" : "${classId}",
		"<portlet:namespace/>groupId" : "${groupId}"
	};
	
	var admin = "${role}";
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabClassNoteListURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var getVirtualLabClassNoteList = msg.getVirtualLabClassNoteList;
			$("body").css('overflow','');
			var rowResult;
			$("#<portlet:namespace/>virtualLabClassNoteBody tr:not(:has(#1))").remove();
			
			if(getVirtualLabClassNoteList.length == 0) {
				
				$("#<portlet:namespace/>classNoteFileList").html("<liferay-ui:message key='edison-there-are-no-data' />")
				 										   .css("text-align", "center");
				
				/* $rowResult = $("<tr/>").css("border-bottom", "1px solid rgb(224, 224, 224)");
				
				$("<td/>").text("This is Test Text").css("word-break","break-all")
						  .appendTo($rowResult);
				if(admin == 'admin'){
					flag = true;
					if(flag) {
						$("<td/>").append($("<input/>").attr("type", "button").addClass("button02_1").attr("value","<liferay-ui:message key='edison-table-list-header-download' />").attr("onClick", "event.cancelBubble=true; <portlet:namespace/>fileDownload('" + 0 + "');")
												   .css("cursor", "pointer")
								 ).appendTo($rowResult);
					} else {
						$("<td/>").css("height","20px").appendTo($rowResult);
					}
				
					$("<td/>").append($("<input/>").attr("type", "button").addClass("button02_1").attr("value","<liferay-ui:message key='delete' />").attr("onClick", "event.cancelBubble=true; <portlet:namespace/>classNoteDelete('" + 0 + "');")
												.css("cursor", "pointer")
					 ).appendTo($rowResult);	
				}else{
					$("<td/>").css("height","20px").appendTo($rowResult);
					flag = true;
					if(flag) {
						
						$("<td/>").append($("<input/>").attr("type", "button").addClass("button02_1").attr("value","<liferay-ui:message key='edison-table-list-header-download' />").attr("onClick", "event.cancelBubble=true; <portlet:namespace/>fileDownload('" + 0 + "');")
												   .css("cursor", "pointer")
								 ).appendTo($rowResult); 
					} else {
						$("<td/>").css("height","20px").appendTo($rowResult);
					}
				}
				
				$("#<portlet:namespace/>virtualLabClassNoteBody").append($rowResult); */
				
			} else {
				for(var i = 0; i < getVirtualLabClassNoteList.length; i++) {
					$rowResult = $("<tr/>").css("border-bottom", "1px solid rgb(224, 224, 224)");
					
					$("<td/>").text(getVirtualLabClassNoteList[i].description).css("word-break","break-all")
							  .appendTo($rowResult);
					if(admin == 'admin'){
						if(getVirtualLabClassNoteList[i].fileEntryId != 0) {
							$("<td/>").append($("<input/>").attr("type", "button").addClass("button02_1").attr("value","<liferay-ui:message key='edison-table-list-header-download' />").attr("onClick", "event.cancelBubble=true; <portlet:namespace/>fileDownload('" + getVirtualLabClassNoteList[i].fileEntryId + "');")
													   .css("cursor", "pointer")
									 ).appendTo($rowResult);
						} else {
							$("<td/>").css("height","20px").appendTo($rowResult);
						}
					
						$("<td/>").append($("<input/>").attr("type", "button").addClass("button02_1").attr("value","<liferay-ui:message key='delete' />").attr("onClick", "event.cancelBubble=true; <portlet:namespace/>classNoteDelete('" + getVirtualLabClassNoteList[i].classNoteSeq + "');")
													.css("cursor", "pointer")
						 ).appendTo($rowResult);	
					}else{
						$("<td/>").css("height","20px").appendTo($rowResult);
						if(getVirtualLabClassNoteList[i].fileEntryId != 0) {
							
							$("<td/>").append($("<input/>").attr("type", "button").addClass("button02_1").attr("value","<liferay-ui:message key='edison-table-list-header-download' />").attr("onClick", "event.cancelBubble=true; <portlet:namespace/>fileDownload('" + getVirtualLabClassNoteList[i].fileEntryId + "');")
													   .css("cursor", "pointer")
									 ).appendTo($rowResult); 
						} else {
							$("<td/>").css("height","20px").appendTo($rowResult);
						}
					}
					
					//$("#<portlet:namespace/>virtualLabClassNoteBody").append($rowResult);
					
					
					var classNoteFileList = $("#<portlet:namespace/>classNoteFileList");
					
					$("<div/>").addClass("filetitbox")
							   .text(getVirtualLabClassNoteList[i].description)
							   .appendTo(classNoteFileList);
					
					var fileBtnBox = $("<div/>").addClass("btnbox");
					$("<a/>").addClass("btn_dn")
							 .attr("href", "#")
							 .text("DOWNLOAD")
							 .appendTo(fileBtnBox);
					$("<a/>").addClass("btn_view")
							 .attr("href", "#")
							 .text("VIEW")
							 .appendTo(fileBtnBox);
					
					fileBtnBox.appendTo(classNoteFileList);
					
				}
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>classNoteDelete(classNoteSeq) {
	var dataForm = {
		"<portlet:namespace/>classNoteSeq" : classNoteSeq
	}
	
	if(confirm("<liferay-ui:message key='edison-course-class-material-delete-alert' />")){	
		jQuery.ajax({
			type: "POST",
			url: "<%=classNoteDeleteURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
					alert("<liferay-ui:message key='edison-data-delete-success' />");
					<portlet:namespace/>dataSearchList();
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
	}
}


function <portlet:namespace/>openClassNotePopup() //Relate AssetEntry 팝업 띄우기
{
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var sourceClassName = $("#<portlet:namespace/>sourceClassName").val();
	var sourceClassPK = $("#<portlet:namespace/>sourceClassPK").val();
	var classId = $("#<portlet:namespace/>classId").val();
	var groupId = $("#<portlet:namespace/>groupId").val();

	var URL = '<%=assetLinkViewDialogueURL.toString()%>' 
				+ '&<portlet:namespace/>sourceEntryId='+ sourceEntryId
				+ '&<portlet:namespace/>sourceClassName='+ sourceClassName
				+ '&<portlet:namespace/>sourceClassPK='+ sourceClassPK
				+ '&<portlet:namespace/>classId='+ classId
				+ '&<portlet:namespace/>groupId='+ groupId
				
	Liferay.Util.openWindow({
		dialog: {
			cache: false,
          	destroyOnClose: true,
			after: {
				render: function(event) {
					$("button.btn.close").on("click", function(e){
						$("body").css('overflow','');
						location.reload(); 
					});
				}
			},
			on: {
				close: function(event) {
					$("body").css('overflow','');
					location.reload(); 
				}
			},  
			centered: true,
			modal: true,
			resizable: false,
			width:1000, 
			height:720
		},
		title: Liferay.Language.get("edison-content-classnote"),
		uri : URL,
		id: "classNoteDialog",
		dialogIframe: {
			on: {
				load : function(evt) {
					$("body").css('overflow','hidden');
				}
			}
		}
	});  
}



function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

</script>
<aui:script>
Liferay.provide(window, 'updateClassNotePopUp', function() {
<portlet:namespace/>closePopup('classNoteDialog');
$("body").css('overflow','');
location.reload(); 
});

Liferay.provide(window,'<portlet:namespace/>closePopup',
function(popupIdToClose) {
	Liferay.Util.getWindow(popupIdToClose).destroy(); // You can try toggle/hide whatever You want
	},
['liferay-util-window']
);
</aui:script>

<form id="<portlet:namespace/>relatedAssetForm" method="post" >
	<aui:input type="hidden" value="${classId }" name="classId" />
	<aui:input type="hidden" value="${groupId }" name="groupId" />
</form>

<div class="panel edison-panel">
	<%-- <c:choose>
		<c:when test="${getVirtualLabClassNoteList == 0 && role eq 'member' }">
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${role eq 'admin' }">
					<div class="virtitlebox panel-heading clearfix">
						<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
						<div class="virtitle">
							<liferay-ui:message key='edison-course-class-material' />
						</div>
						<div class="buttonbox0801">
							<input id="<portlet:namespace/>classNoteManagementButton" name="<portlet:namespace/>classNoteManagementButton" type="button" class="btn btn-default" value="<liferay-ui:message key='edison-virtuallab-scienceapp-management' />" onClick="<portlet:namespace/>openClassNotePopup()"/>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<h3><liferay-ui:message key='edison-course-class-material-member' /></h3>
				</c:otherwise>
			</c:choose>
			<!-- <div class="h10"></div> -->
			<div class="table6_list">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed;" class="table table-bordered table-hover edison-table">
					<colgroup>
						<col width="*" />
						<col width="120" />
						<col width="120" />
					</colgroup>
					<tbody id="<portlet:namespace/>virtualLabClassNoteBody">
					</tbody>
				</table>
			</div>
		</c:otherwise>
	</c:choose> --%>
	
	<!--강의자료 다운로드-->
	<div class="conwrap2">
		<div class="conwrap2left">
			<div class="conwraptit01">
				강의자료 다운로드
				<div class="moreicon">
					<a href="#">
						MORE
						<img src="${contextPath}/images/moreicon.png" width="11" height="11">
					</a> 
				</div>
			</div>
			
			<!--파일제목 및 다운로드-->
			<div id="<portlet:namespace/>classNoteFileList">
			</div>
			
		</div>

	</div>
	<!--end 강의자료 다운로드-->
	
</div>

