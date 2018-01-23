<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.util.PortletKeys"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>

<liferay-portlet:actionURL var="updateVirtualLabInfomationURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="updateVirtualLabInfomation" />
	<liferay-portlet:param name="groupId" value="${groupId }" />
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="virtualLabDisableURL" id="virtualLabDisable" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="virtualLabUserInfoURL" id="virtualLabUserInfo" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL var="searchProfessorURL" portletMode="view" copyCurrentRenderParameters="false" windowState="<%= LiferayWindowState.POP_UP.toString() %>" >
	<liferay-portlet:param name="myRender" value="searchProfessor" />
	<liferay-portlet:param name="virtualLabProfessor" value="virtualLabProfessor"/>
	<liferay-portlet:param name="professorSeq" value="professorSeq"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL 
	portletName="edisonorgcodesearch_WAR_edisondefault2016portlet" 
	portletMode="view"
	windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="syscommoncdURL">
	<liferay-portlet:param name="up_code" value="1501"/>
	<liferay-portlet:param name="com_search_type" value="orgSearch"/>
	<liferay-portlet:param name="popup_title" value="edison-org-code-search"/>
	<liferay-portlet:param name="universityFieldNm" value="virtualLabUniversityField"/>
	<liferay-portlet:param name="universityField" value="universityField"/>
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="deleteFileURL" escapeXml="false" id="deleteFile" copyCurrentRenderParameters="false">
	<portlet:param name="virtualLabId" value="${labInfo.virtualLabId}"/>
</liferay-portlet:resourceURL>

<style type="text/css">
.input-localized-content{
	margin:5px;
}
	
.virtualvisual {
	width: 1220px;
	height: 193px;
	background: url(/edison-virtuallab-2016-portlet/images/<%=themeDisplay.getLanguageId()%>/virtualvisual0201.jpg)
		no-repeat
}

.virtualLabTitle{
    color: #000;
    padding: 22px 0 12px 0;
    font-family: Tahoma,Arial,Nanum Barun Gothic,NanumGothic;
    margin: 0;
}

</style>

<c:choose>
	<c:when test="${labInfo.iconId != 0}">
		<div class="mainImage">
			<img src="/documents/${labInfo.iconRepositoryId }/${labInfo.iconUuid }?imageThumbnail=2" style="margin:0 10px 0 0;width:150px; height:150px;"/>
		</div> 
	</c:when>
	<c:otherwise>
		<div class="mainImage">
			<img src="${contextPath }/images/noimage_110.png?imageThumbnail=2" style="margin:0 10px 0 0;width:150px; height:150px;"/>
		</div>
	</c:otherwise>
</c:choose>

<div class="mainInfo">
<h1 class="virtualLabTitle">
	${labInfo.virtualLabTitle }
	<span class="${groupClass }"><liferay-ui:message key='${groupField }' /></span>
	<span class="label_fieldNm">${labInfo.virtualLabUniversityFieldNm }</span>
</h1>
<div class="concustomHr">.</div>
	<div class="mainButton">
		<input type="button" value="<liferay-ui:message key='edison-button-board-list' />"  class="btn btn-default" onclick="<portlet:namespace/>moveLabList()" />
		<c:if test="${role eq 'MANAGER' || role eq 'ADMIN' }">
			<input type="button" value="<liferay-ui:message key='edison-course-mycourse-list' />" class="btn btn-default" onclick="<portlet:namespace/>myCourse()" />
		</c:if>
	</div>
</div>
<div class="customHr">.</div>
<c:choose>
	<c:when test="${role eq 'MANAGER' || role eq 'ADMIN' }">
			<form id="createVirtualLabForm" name="createVirtualLabForm"  method="post" action="<%= updateVirtualLabInfomationURL %>" onsubmit="return <portlet:namespace/>checkValidation(this);" enctype="multipart/form-data">
				<aui:input name="<portlet:namespace/>groupId" id="<portlet:namespace/>sgroupId" type="hidden" value="${groupId}" label=""/>
				<div class="panel edison-panel">
					<div class="virtitlebox panel-heading clearfix">
						<h3 class="panel-title pull-left">
							<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
							<liferay-ui:message key='edison-virtuallab-infomation-management' />
						</h3>
						<div class="input-group">
							<div class="input-group-btn">
								<input id="<portlet:namespace/>virtualLab_infomation_update_button" name="<portlet:namespace/>virtualLab_infomation_update_button" type="submit" value="<liferay-ui:message key='edison-button-board-modify' />" class="btn btn-default" style="float: right;"/>
								<c:if test="${role eq 'ADMIN' }">
									<input id="<portlet:namespace/>virtualLab_delete_button" name="<portlet:namespace/>virtualLab_delete_button" type="button" value="<liferay-ui:message key='edison-button-board-delete' />" class="btn btn-default" onclick="<portlet:namespace/>virtualLabDisable(${labInfo.virtualLabId})" style="float: right;"/>
								</c:if>
							</div>
						</div>
					</div>
		
					<div class="h20"></div>
				
					<input id="<portlet:namespace/>virtualLabId" name="<portlet:namespace/>virtualLabId" type="hidden" value="${labInfo.virtualLabId}">
					<input id="<portlet:namespace/>universityField" name="<portlet:namespace/>universityField" type="hidden" value="${labInfo.virtualLabUniversityField}">
					<input id="<portlet:namespace/>professorSeq" name="<portlet:namespace/>professorSeq" type="hidden" value="${labInfo.virtualLabProfessorSeq}">
					<input id="<portlet:namespace/>status" name="<portlet:namespace/>status" type="hidden" value="UPDATE">
					<div class="">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style=" table-layout: fixed;" class="table table-bordered table-hover edison-table">
							<colgroup>
								<col width="20%" />
								<col width="30%" />
								<col width="20%" />
								<col width="30%" />
							</colgroup>
							<tbody>
								<tr class="puptrline">
									<th class="puptitle"><liferay-ui:message key='edison-table-list-header-tutor' /></th>
									<td class="puptxt">
										<input id="<portlet:namespace/>virtualLabProfessor" name="<portlet:namespace/>virtualLabProfessor" type="text" maxlength="10" readonly="readonly" style="width:190px; margin: 4px;" value="${labInfo.professorFirstName}"/>
										<input id="virtualLabSearchProfessorButton" name="virtualLabSearchProfessorButton" onclick="<portlet:namespace/>searchProfessorPopup();" type="button" value="<liferay-ui:message key='edison-button-search' />" class="btn btn-default" />
									</td>
									<c:if test="${role eq 'MANAGER' }">
										<th class="puptitle"><liferay-ui:message key='edison-virtuallab-owner' /></th>
										<td class="puptxt">
											<input id="<portlet:namespace/>virtualLabOwnerName" name="<portlet:namespace/>virtualLabOwnerName" type="text" readonly="readonly" style="width:190px; margin-bottom:0px;" value="${labInfo.userScreenName}"/>
										</td>
									</c:if>
									<c:if test="${role eq 'ADMIN' }">
										<th class="puptitle"><liferay-ui:message key='edison-virtuallab-owner' /></th>
										<td class="puptxt">
											<input id="<portlet:namespace/>virtualLabOwnerName" name="<portlet:namespace/>virtualLabOwnerName" type="text"  style="width:190px; margin-bottom:0px;" value="${labInfo.userScreenName}"/>
											<input id="virtualLab_owner_transfer_button" name="virtualLab_owner_transfer_button" onclick="<portlet:namespace/>virtualLabOwnerTransfer();" type="button" value="<liferay-ui:message key='edison-virtuallab-transfer' />" class="btn btn-default" />
										</td>
									</c:if>
								</tr>
								<tr class="puptrline">
									<th class="puptitle"><liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' /></th>
									<td class="puptxt">
										<liferay-ui:input-localized id="virtualLabTitle" name="virtualLabTitle" xml="${labInfo.virtualLabTitleMap}"  style="display: inline-block; margin:4px; width:255px;" type="input" />
									</td>
									<th class="puptitle" style="word-wrap: break-word;"><liferay-ui:message key='edison-create-account-field-title-university' /></th>
									<td class="puptxt">
										<input id="<portlet:namespace/>virtualLabUniversityField" name="<portlet:namespace/>virtualLabUniversityField" type="text" maxlength="20" readonly="readonly" style="width:190px; margin-bottom:0px;" value="${labInfo.virtualLabUniversityFieldNm}"/>
										<input id="virtualLab_search_university_button" name="virtualLab_search_university_button" onclick="<portlet:namespace/>syscommoncdPopup();" type="button" value="<liferay-ui:message key='edison-button-search' />" class="btn btn-default" />
									</td>
								</tr>
								<tr class="puptrline">
									<th class="puptitle"><liferay-ui:message key='edison-table-list-header-resume' /></th>
									<td class="puptxt" colspan="3">
									<liferay-ui:input-localized id="virtualLabDescription" name="virtualLabDescription" xml="${labInfo.virtualLabDescriptionMap}"  rows="5" spellcheck="false" style="width: 95%; resize:none; margin:5px;" type="textarea"/>
								</tr>
								
								<tr class="puptrline">
									<th class="puptitle"><liferay-ui:message key='icon' /></th>
									<td class="puptxt" colspan="3">
										<input type="file" id="<portlet:namespace/>course_icon" name="<portlet:namespace/>course_icon">
										<c:if test="${labInfo.iconTitle ne null }">
											<div class="down_date courseIconClass"  onclick="<portlet:namespace/>fileDownload('${labInfo.iconId }')" style="cursor: pointer;display: inline-block;">
												${labInfo.iconTitle}
											</div>
											<img src='${contextPath}/images/icon_dustbin.png' class="courseIconClass" width='13' height='14' style="cursor:pointer" onClick="<portlet:namespace/>deleteFile('${labInfo.iconId}','courseIcon','courseIconClass');" />
										</c:if>
										<div id="clear"></div>
									</td>
								</tr>
								
							</tbody>
						</table>
					</div>
				</div>
			</form>
	</c:when>
	<c:otherwise>
		<div><h3><liferay-ui:message key='edison-course-about-this-course' /></h3></div>
		<div class="mainDescription">${labInfo.virtualLabDescription}</div>
		<div class="concustomHr">.</div>
	</c:otherwise>	
</c:choose>


<script>
function <portlet:namespace/>syscommoncdPopup(){
	var URL = "<%=syscommoncdURL%>";
	w = 850;
	h = 550;
	x = (screen.availWidth - w) / 2;
	y = (screen.availHeight - h) / 2;
	var options = "width="+w+", height="+h+", left="+x+",top="+y+",toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
	var openPop;
	if(openPop != null){
		openPop.focus();
	}else{
		openPop = window.open(URL, "syscommoncdPopup",options);
		openPop.focus();
	}
}

function <portlet:namespace/>virtualLabDisable(virtualLabId) {
	var dataForm = {
		"<portlet:namespace/>virtualLabId" : virtualLabId,
		"<portlet:namespace/>groupId": ${groupId}
	}
	
	if(confirm("<liferay-ui:message key='edison-virtuallab-delete-alert' />")){	
		jQuery.ajax({
			type: "POST",
			url: "<%=virtualLabDisableURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var result = msg.result;
				if(result == "300" || result == "400") {
					alert("<liferay-ui:message key='edison-data-delete-error' />");
				} else if (result == "200") {
					alert("<liferay-ui:message key='edison-data-delete-success' />");
					<portlet:namespace/>moveLabList();
				} else if (result == "500") {
					alert("<liferay-ui:message key='edison-virtuallab-delete-alert-fail' />");
				} else {
					alert("<liferay-ui:message key='edison-data-delete-error' />");
				}
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
	}
}

function <portlet:namespace/>virtualLabOwnerTransfer() {
	
	var searchForm = {
		"<portlet:namespace/>userScreenName" : $("#<portlet:namespace/>virtualLabOwnerName").val(),
		"<portlet:namespace/>virtualLabId" : "${labInfo.virtualLabId}"
	};
		
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabUserInfoURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
			var result = msg.result;
			var virtualLabUserInfo = msg.virtualLabUserInfo;
			
			if(result === undefined) {
				alert("<liferay-ui:message key='edison-search-no-result' />");
			} else if(result == "admin") {
				alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-admin-alert' />");
			} else if(result == "tempuser") {
				alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-tempuser-alert' />");
			} else if(result == "owner" || result == "manager") {
				alert("<liferay-ui:message key='edison-virtuallab-virtualLabClassManagement-admin-already' />");
			} else if(result == "none") {
				alert("<liferay-ui:message key='edison-search-no-result' />");
			} else if(result == "user") {
				if(confirm("<liferay-ui:message key='edison-virtuallab-owner-transfer' />")){
					$("#<portlet:namespace/>status").val("TRANSFER");
					document.createVirtualLabForm.submit();
				}
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}


function <portlet:namespace/>checkValidation(form) {
	
	if (form.submitted) return false;
	if(!checkValue(form.<portlet:namespace/>virtualLabPersonName, "<liferay-ui:message key='edison-table-list-header-tutor' />", 1, 30)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>virtualLabTitle, "<liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' />", 1, 75)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>universityField, "<liferay-ui:message key='edison-create-account-field-title-university' />", 1, 10)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>virtualLabDescription, "<liferay-ui:message key='edison-table-list-header-resume' />", 1, 250)) {
		return false;
	} else {
		form.submitted = true;
		return true;
	}
	return false;
}

function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

function <portlet:namespace/>deleteFile(p_fileEntryId,fileType,objectClass,language){
	if(!confirm(Liferay.Language.get('file-delete-confirm'))) return;
	var deleteForm = {
			"<portlet:namespace/>fileEntryId" : p_fileEntryId,
			"<portlet:namespace/>fileType" : fileType,
			"<portlet:namespace/>language" : language
			};
	
	jQuery.ajax({
		type: "POST",
			url: "<%=deleteFileURL%>",
		data: deleteForm,
  		async : false,
		success: function(data) {
			alert(Liferay.Language.get('edison-data-delete-success'));
			$("."+objectClass).remove();
			
			if(fileType=="portType"){
				var percentVal = '0%';
				$('.bar').width(percentVal);
				$('.percent').html(percentVal);
				$("#<portlet:namespace/>fileSave").show();
			}
		},error:function(data,e){ 
			alert("deleteFile System error!");	
		}
	});
}

function <portlet:namespace/>searchProfessorPopup(){
	var URL = "<%=searchProfessorURL%>";
	w = 850;
	h = 550;		
	x = (screen.availWidth - w) / 2;
	y = (screen.availHeight - h) / 2;
	var options = "width="+w+", height="+h+", left="+x+",top="+y+",toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
	var openPop;
	if(openPop != null){
		openPop.focus();
	}else{
		openPop = window.open(URL, "searchProfessorPopup",options);
		openPop.focus();
	}
}


</script>

<aui:script>
function <portlet:namespace/>moveLabList() {
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPlid("${lablistPlid}"); <!-- 앱스토어 Plid -->
		portletURL.setPortletId("edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet"); <!-- 앱스토어 포틀릿 ID -->
		window.location.href = portletURL.toString();
	});
}

function <portlet:namespace/>myCourse(){
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPlid("${mylablistPlid}");
		portletURL.setPortletMode("view");
		portletURL.setPortletId("edisonmypage_WAR_edisondefault2016portlet");
		portletURL.setParameter("clickTab", "myCourse"); 
		window.location.href = portletURL.toString();
	});
}
</aui:script>