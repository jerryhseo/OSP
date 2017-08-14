<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.Locale"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>

<liferay-portlet:actionURL var="addProfessorURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="addProfessor" />
</liferay-portlet:actionURL>

<script>

function professorList() {
	window.location.href = '${myHistoryUrl }';
}
</script>

<div class="registrationContainer">
	<div class="virtitlebox">
		<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
		<div class="virtitle">
			<liferay-ui:message key='edison-professor-history-management' />
		</div> 
	</div>
	<form id="<portlet:namespace/>addProfessorForm" name="<portlet:namespace/>addProfessorForm" method="post" action="<%= addProfessorURL %>" onsubmit="return <portlet:namespace/>checkValidation(this);" style="margin:0px; padding-top: 65px;">
		<input type="hidden" id="<portlet:namespace/>userId" name="<portlet:namespace/>userId" value="${userInfomation.userId }"> 
		<div class="table2_list">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed;">
				<colgroup>
					<col width="20%" />
					<col width="30%" />
					<col width="20%" />
					<col width="30%" />
				</colgroup>
				<tbody>
					<tr class="puptrline">
						<th class="puptitle"><liferay-ui:message key='edison-create-account-field-title-name' /></th>
						<td class="puptxt">
							<input id="<portlet:namespace/>professorName" name="<portlet:namespace/>professorName" readonly="readonly" value="${userInfomation.firstName }" style="display: inline-block; margin:4px; width:230px;" type="text"/>
						</td>
						<th class="puptitle" rowspan="4"><liferay-ui:message key='edison-content-main-image' /></th>
						<td class="puptxt" rowspan="4"><img class="professorManagementImage" src="${professor.portraitURL }" width="150" height="150"></td>
					</tr>
					<tr class="puptrline">
						<th class="puptitle"><liferay-ui:message key='email-address' /></th>
						<td class="puptxt">
							<input id="<portlet:namespace/>professorEmail" name="<portlet:namespace/>professorEmail" readonly="readonly" value="${userInfomation.emailAddress }" style="display: inline-block; margin:4px; width:230px;" type="text"/>
						</td>
					</tr>
					<tr class="puptrline">
						<th class="puptitle"><liferay-ui:message key='edison-professor-homepage' /></th>
						<td class="puptxt">
							<input id="<portlet:namespace/>professorHomepage" name="<portlet:namespace/>professorHomepage" value="${professor.professorHomepage }" style="display: inline-block; margin:4px; width:230px;" type="text"/>
						</td>
						
					</tr>
					<tr class="puptrline">
						<th class="puptitle"><liferay-ui:message key='edison-professor-phone' /></th>
						<td class="puptxt">
							<input id="<portlet:namespace/>professorPhone" name="<portlet:namespace/>professorPhone" value="${professor.professorPhone }" style="display: inline-block; margin:4px; width:230px;" type="text"/>
						</td>
					</tr>
					
					<tr class="puptrline">
						<th class="puptitle"><liferay-ui:message key='edison-professor-record' /></th>
						<td class="puptxt" colspan="3">
							<liferay-ui:input-localized id="professorRecord" name="professorRecord" xml="${professor.professorRecord }" rows="5" spellcheck="false" style="width: 85%; resize:none; margin:5px;" type="textarea"/>
						</td>
					</tr>
					<tr class="puptrline">
						<th class="puptitle"><liferay-ui:message key='edison-professor-paper' /></th>
						<td class="puptxt" colspan="3">
							<liferay-ui:input-localized id="professorPaper" name="professorPaper" xml="${professor.professorPaper }"  rows="5" spellcheck="false" style="width: 85%; resize:none; margin:5px;" type="textarea"/>
						</td>
					</tr>
					<tr class="puptrline">
						<th class="puptitle"><liferay-ui:message key='edison-professor-introduce' /></th>
						<td class="puptxt" colspan="3">
							<liferay-ui:input-localized id="professorIntroduce" name="professorIntroduce" xml="${professor.professorIntroduce }" rows="5" spellcheck="false" style="width: 85%; resize:none; margin:5px;" type="textarea"/>
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div class="h20"></div>
		<div style="text-align: right; margin:0px 25px 30px 0px;">
			<input id="<portlet:namespace/>professorAddBtn" name="<portlet:namespace/>professorAddBtn" type="submit" value="<liferay-ui:message key='edison-button-save' />" class="button06" />
			<c:if test="${admin eq 'admin' }">
				<input id="<portlet:namespace/>professorListBtn" name="<portlet:namespace/>professorListBtn" type="button" onclick="professorList()" value="<liferay-ui:message key='edison-button-board-list' />" class="button06" />
			</c:if>
		</div>
	</form>
</div>

