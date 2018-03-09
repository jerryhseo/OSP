<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>

<liferay-portlet:actionURL var="virtualLabCreateURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="createVirtualLab" />
</liferay-portlet:actionURL>

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

<liferay-portlet:renderURL var="searchProfessorURL" portletMode="view" copyCurrentRenderParameters="false" windowState="<%= LiferayWindowState.POP_UP.toString() %>" >
	<liferay-portlet:param name="myRender" value="searchProfessor" />
	<liferay-portlet:param name="virtualLabProfessor" value="virtualLabProfessor"/>
	<liferay-portlet:param name="professorSeq" value="professorSeq"/>
</liferay-portlet:renderURL>


<script type="text/javascript">
AUI().ready(function() {
	$("#<portlet:namespace/>virtualLab-request-button").click(function() {
		$("#<portlet:namespace/>select_languageId").val("${languageId}");
		$("#<portlet:namespace/>virtualLabId").val("0");
		$("#<portlet:namespace/>virtualLabTitle").val("");
		$("#<portlet:namespace/>virtualLabUniversityField").val("");
		$("#<portlet:namespace/>virtualLabDescription").val("");
		$("#<portlet:namespace/>universityField").val("");
		$("#<portlet:namespace/>virtualLab-request-dialog").dialog( "open" );
	});
	
});

function <portlet:namespace/>checkValidation(form) {
	if(isEmpty(form.<portlet:namespace/>virtualLabProfessor.value)){
		alert(Liferay.Language.get('edison-this-field-is-required',[Liferay.Language.get('edison-table-list-header-tutor')]));
		return false;
	} else if (!checkValue(form.<portlet:namespace/>virtualLabTitle, "<liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' />", 1, 75)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>universityField, "<liferay-ui:message key='edison-create-account-field-title-university' />", 1, 10)) {
		return false;
	} else {
		return true;
	}
}

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

function checkGroup(val) {
	document.<portlet:namespace/>createVirtualLabForm.<portlet:namespace/>groupId.value = val;
}
	  
function <portlet:namespace/>onKeyDown() {
	if(event.keyCode == 13 && $("#<portlet:namespace/>search_parameter").val() != ""){
		<portlet:namespace/>dataSearchList();
	}
}

function <portlet:namespace/>cancelButton(){
	location.href="${URL}";
}
</script>
	
<div class="table-responsive panel edison-panel">
	<form id="<portlet:namespace/>createVirtualLabForm" class="table1_list" name="<portlet:namespace/>createVirtualLabForm"  enctype="multipart/form-data" method="post" action="<%= virtualLabCreateURL %>" onsubmit="return <portlet:namespace/>checkValidation(this);" style="margin:0px;">
		<input id="<portlet:namespace/>virtualLabId" name="<portlet:namespace/>virtualLabId" type="hidden" value="0">
		<input id="<portlet:namespace/>universityField" name="<portlet:namespace/>universityField" type="hidden" value="1501005">
		<input id="<portlet:namespace/>professorSeq" name="<portlet:namespace/>professorSeq" type="hidden" >
		
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
				<liferay-ui:message key='edison-virtuallab-request-infomation' />
			</h3>
		</div>
		
		<table width="100%" border="0" cellspacing="0" style="table-layout: fixed;" class="table table-bordered table-hover edison-table">
			<colgroup>
				<col width="20%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
			</colgroup>
			<tbody>
				<tr class="puptrline">
					<th class="puptitle"><liferay-ui:message key='edison-virtuallab-request-user-id' /></th>
					<td class="puptxt">${userInfomation.screenName}</td>
					<th class="puptitle"><liferay-ui:message key='edison-virtuallab-request-user-name' /></th>
					<td class="puptxt">${userInfomation.fullName}</td>
				</tr>
				<tr class="puptrline">
					<th class="puptitle"><liferay-ui:message key='edison-table-list-header-tutor' /><span class="requiredField"> *</span></th>
					<c:choose>
						<c:when test="${isPortal}">
							<td class="puptxt input-group" style="display: table-cell;">
								<input id="<portlet:namespace/>virtualLabProfessor" class="form-control" name="<portlet:namespace/>virtualLabProfessor" type="text" maxlength="10" readonly="readonly" style="margin-bottom:0px; width: 70%; "/>
								<div class="input-group-btn">
									<input id="virtualLabSearchProfessorButton" name="virtualLabSearchProfessorButton" onclick="<portlet:namespace/>searchProfessorPopup();" type="button" value="<liferay-ui:message key='edison-button-search' />" class="btn btn-default" style="width: 55px;" />
								</div>
							</td>
							<th class="puptitle" style="word-wrap: break-word;"><liferay-ui:message key='edison-table-list-header-group' /></th>
							<td class="puptxt">
								<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="23212"/>
								<select name="labGroup" onChange="checkGroup(this.value);" class="btn btn-default">
									<c:forEach items="${virtualLabGroup}" var="map">
										   <option id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" value="${map.groupId}">${map.name}</option>
									</c:forEach>
								</select>
							</td>
						</c:when>
						<c:otherwise>
							<td class="puptxt input-group" colspan="3" style="display: table-cell;">
								<input id="<portlet:namespace/>virtualLabProfessor" class="form-control" name="<portlet:namespace/>virtualLabProfessor" type="text" maxlength="10" readonly="readonly" style="margin-bottom:0px; width: 70%;"/>
								<input id="virtualLabSearchProfessorButton" name="virtualLabSearchProfessorButton" onclick="<portlet:namespace/>searchProfessorPopup();" type="button" value="<liferay-ui:message key='edison-button-search' />" class="btn btn-default" />
							</td>
							<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${groupId}"/>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr class="puptrline">
					<th class="puptitle"><liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' /><span class="requiredField"> *</span></th>
					<td class="puptxt">
						<liferay-ui:input-localized id="virtualLabTitle" name="virtualLabTitle" xml="" style="display: inline-block; margin:4px; width:230px;" type="input" cssClass="form-control"/>
					</td>
					<th class="puptitle" style="word-wrap: break-word;"><liferay-ui:message key='edison-create-account-field-title-university' /><span class="requiredField"> *</span></th>
					<td class="puptxt input-group" style="display: table-cell;">
						<input id="<portlet:namespace/>virtualLabUniversityField" class="form-control" name="<portlet:namespace/>virtualLabUniversityField" type="text" maxlength="10" readonly="readonly" style="margin-bottom:0px; width: 70%;"/>
						<div class="input-group-btn">
							<input id="virtualLab_search_university_button" name="virtualLab_search_university_button" onclick="<portlet:namespace/>syscommoncdPopup();" type="button" value="<liferay-ui:message key='edison-button-search' />" class="btn btn-default" style="width: 55px;" />
						</div>
					</td>
				</tr>
				<tr class="puptrline">
					<th class="puptitle"><liferay-ui:message key='edison-table-list-header-resume' /></th>
					<td class="puptxt" colspan="3">
						<liferay-ui:input-localized id="virtualLabDescription" name="virtualLabDescription" xml=""  rows="5" spellcheck="false" style="width: 85%; resize:none; margin:5px;" type="textarea"/>
					</td>
				</tr>
				
				<tr class="puptrline">
					<th class="puptitle"><liferay-ui:message key='icon' /></th>
					<td class="puptxt" colspan="3">
						<input type="file" id="<portlet:namespace/>course_icon" name="<portlet:namespace/>course_icon">
					</td>
				</tr>
			</tbody>
		</table>
		
		<div class="h20"></div>
		<div style="text-align: right; margin:0px 25px 30px 0px;">
			<c:choose>
				<c:when test="${role eq 'LABOWNER' }">
					<input id="<portlet:namespace/>virtualLab_creation_button" name="<portlet:namespace/>virtualLab_creation_button" type="submit" value="<liferay-ui:message key='edison-virtuallab-creation' />" class="btn btn-default" />
				</c:when>
				<c:otherwise>
					<input id="<portlet:namespace/>virtualLab_creation_button" name="<portlet:namespace/>virtualLab_creation_button" type="submit" value="<liferay-ui:message key='edison-virtuallab-creation-request' />" class="btn btn-default" />
				</c:otherwise>
			</c:choose>
			<input type="button" id="<portlet:namespace/>cancel_button" name="<portlet:namespace/>cancel_button" onclick="<portlet:namespace/>cancelButton()"  value="<liferay-ui:message key='edison-button-cancel' />" class="btn btn-default" />
		</div>
	</form>
</div>

