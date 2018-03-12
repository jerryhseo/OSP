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

<liferay-portlet:renderURL var="contentDetailUrl" portletName="edisoncontent_WAR_edisoncontent2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="generalModifyView" />
</liferay-portlet:renderURL>

<style type="text/css">
	.btn_dn, .btn_view{cursor: pointer;}
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
				
				
			} else {
				for(var i = 0; i < getVirtualLabClassNoteList.length; i++) {
					
					if(3<=i){
						break;
					}
					
					var classNoteFileList = $("#<portlet:namespace/>classNoteFileList");
					var isContent = getVirtualLabClassNoteList[i].isContent;
					
					$("<div/>").addClass("filetitbox")
							   .text(getVirtualLabClassNoteList[i].description)
							   .appendTo(classNoteFileList);
					
					var fileBtnBox = $("<div/>").addClass("btnbox");
					if(isContent == "false"){
						$("<span/>").addClass("btn_dn")
								 .attr("onclick", "<portlet:namespace/>fileDownload('"+getVirtualLabClassNoteList[i].fileEntryId+"')")
								 .css("height", "80px").css("padding-top", "15%")
								 .text("DOWNLOAD")
								 .appendTo(fileBtnBox);
					}
					if(isContent == "true"){
						$("<span/>").addClass("btn_view")
									.attr("onclick", "<portlet:namespace/>moveContentDetail('"+getVirtualLabClassNoteList[i].contentSeq+"', '0');")
									.css("height", "80px").css("padding-top", "15%")
									.text("VIEW")
									.appendTo(fileBtnBox);
					}
					
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

function <portlet:namespace/>moveContentDetail(contentSeq, contentDiv) {
	AUI().use("liferay-portlet-url", function(a) {
		var thisPortletNamespace = "_edisoncontent_WAR_edisoncontent2016portlet_";
		var params = "&" + thisPortletNamespace + "contentDiv=" + contentDiv;
		params += "&" + thisPortletNamespace + "contentSeq=" + contentSeq;
		params += "&" + thisPortletNamespace + "redirectName=" + "<liferay-ui:message key='edison-search-total'/>";
		location.href = "<%=contentDetailUrl%>" + params;
	});
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
	<!--강의자료 다운로드-->
	<div class="conwrap2">
		<div class="conwrap2left">
			<div class="conwraptit01">
				<liferay-ui:message key='edison-virtuallab-class-file-download' />
				<c:if test="${fn:toUpperCase(role) eq 'MANAGER' || fn:toUpperCase(role) eq 'ADMIN' }">
					<div class="moreicon">
						<a href="#" onClick="<portlet:namespace/>openClassNotePopup();">
							<liferay-ui:message key='edison-virtuallab-scienceapp-management' />
							<img src="${contextPath}/images/moreicon.png" width="11" height="11">
						</a> 
					</div>
				</c:if>
			</div>
			
			<!--파일제목 및 다운로드-->
			<div id="<portlet:namespace/>classNoteFileList">
			</div>
			
		</div>

	</div>
	<!--end 강의자료 다운로드-->
	
</div>

