<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/common/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.kisti.osp.icecap.DataTypeException"%>


<liferay-portlet:actionURL var="submitURL" copyCurrentRenderParameters="false" name="dataTypeAction">
	<liferay-portlet:param name="modifyMode" value="modifyEditorAnalyzer" />
	<liferay-portlet:param name="mode" value="${mode}" />
	
	<liferay-portlet:param name="searchByPrePage" value="${searchByPrePage}" />
	<liferay-portlet:param name="portType" value="${portType}" />
	<liferay-portlet:param name="portName" value="${portName}" />
	
	
	<liferay-portlet:param name="dataTypeId" value="${dataTypeMap.typeId}" />
	<liferay-portlet:param name="dtName" value="${dataTypeMap.name}" />
	<liferay-portlet:param name="dtVersion" value="${dataTypeMap.version}" />
	
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</liferay-portlet:actionURL>

<%
	String exceptionNameMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-app-validation-name-exception-msg");
%>

<style type="text/css">
	.edison-data-type-editor .long_field {
	    width: 350px;
	}
	
	.edison-data-type-editor .max_long_field {
	    width: 97%;
	}
	
	.edison-data-type-editor .checkbox{
		cursor: pointer;
	}
	.newsubtitle{
		font-size: 27px !important;
	}
	.subtitlearea{
		margin-left: 10px;
	}
	.aTitle{
		color: #0af !important;
		text-decoration: none;
	}
</style>

<c:if test="<%= SessionErrors.contains(renderRequest, DataTypeException.class.getName()) %>">
	<div class="alert alert-error">
		<liferay-ui:message key="edison-science-appstore-data-type-name-duplication-exception-msg" />
	</div>
</c:if>

<div class="container">
	<div class="edison-panel">
		<div class="panel-heading clearfix">
			<h2 class="panel-title pull-left newsubtitle">
				<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png"/>
				<span class="subtitlearea">
					<a onClick="<portlet:namespace/>historyBack();" style="cursor: pointer;" class="aTitle"> ${redirectName} </a> >
					<c:choose>
						<c:when test="${mode eq 'update'}">
							DataType
							<liferay-ui:message key='action.UPDATE' />
						</c:when>
						<c:otherwise>
							DataType
							<liferay-ui:message key='registration' />
						</c:otherwise>
					</c:choose>
				</span>
			</h2>
		</div>
		<aui:form name="frm" method="POST" action="<%=submitURL%>">
			<div class="table1_list">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<colgroup>
					    <col width="20%">
					    <col width="40%">
					    <col width="15%">
					    <col width="25%">
					</colgroup>
					
					<tr>
                        <th>
                            <liferay-ui:message key='name' /><span class="requiredField"> *</span></th>
                        <td>
                            <c:choose>
                                <c:when test="${editDataName}">
                                    <aui:input name="dtName" type="text" cssClass="long_field" label="" value="" maxLength="100">
                                        <aui:validator name="required" />
                                        <aui:validator name="custom" errorMessage="<%=exceptionNameMsg%>">
                                            function (val, fieldNode, ruleValue) { var retbool = true; var kor_check = /^[A-Za-z0-9\\_]*$/; if (!kor_check.test(val)){ retbool = false; } return retbool; }
                                        </aui:validator>
                                    </aui:input>
                                </c:when>
                                <c:otherwise>
                                    <input name="name" value="${dataTypeMap.name}" type="text" class="field long_field" readonly="readonly" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <th>
                            <liferay-ui:message key='version' /><span class="requiredField">*</span></th>
                        <td>
                            <c:choose>
                                <c:when test="${editDataName}">
                                    <input name="name" value="1.0.0" type="text" class="field short_field" readonly="readonly" />
                                </c:when>
                                <c:otherwise>
                                    <input name="name" value="${dataTypeMap.version}" type="text" class="field short_field" readonly="readonly" />
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
					<tr>
						<th><liferay-ui:message key='description' /></th>
						<td colspan="3">
							<liferay-ui:input-localized name="description" xml="${dataTypeMap.description}" cssClass="field max_long_field" type="input" maxLength="80" />
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key='edison-science-appstore-toolkit-editor-list' /></th>
						<td>
							<c:forEach var="data" items="${editorList}">
								<aui:input name="editor" id="editor_${data.scienceAppId}" label="${data.name}" value="${data.scienceAppId}" data-app-name="${data.name}" type="checkbox" onClick="checkEditor(this.id);"/>
							</c:forEach>
						</td>
						<c:choose>
							<c:when test="${portType eq 'INPUT' }">
						       <th>Default Editor<span class="requiredField"> *</span></th>
							</c:when>
							<c:otherwise>
								<th>Default Editor</th>
							</c:otherwise>
						</c:choose>
						<td>
							<c:set var="editorSelectoptions" value="${fn:split(editorStr,',')}" />
							<select id="<portlet:namespace/>defaultEditorSelect" name="<portlet:namespace/>defaultEditorSelect" class="aui-field-select">
								<option value=""></option>
							</select>
						</td>
					</tr>
					<tr>
						<th><liferay-ui:message key='edison-science-appstore-toolkit-analyzer-list' /></th>
						<td>
							<c:forEach var="data" items="${analyzerList}">
								<aui:input name="analyzer" id="analyzer_${data.scienceAppId}" label="${data.name}" value="${data.scienceAppId}" data-app-name="${data.name}" type="checkbox" onClick="checkAnalyzer(this.id);"/>
							</c:forEach>
						</td>
						<c:choose>
							<c:when test="${portType eq 'OUTPUT' }">
						       <th>Default Analyzer<span class="requiredField"> *</span></th>
							</c:when>
							<c:otherwise>
								<th>Default Analyzer</th>
							</c:otherwise>
						</c:choose>
						<td>
							<select id="<portlet:namespace/>defaultAnalyzerSelect" name="<portlet:namespace/>defaultAnalyzerSelect" class="aui-field-select">
								<option value=""></option>
							</select>
						</td>
					</tr>
				</table>
			</div>
		</aui:form>
	</div>
	<div class="pull-right" style="margin: 18px 0px;">
		<button type="button" class="btn btn-default" onclick="<portlet:namespace/>historyBack();"><span class="icon-list-ul">  <liferay-ui:message key='edison-virtuallab-surveyResultList-list'/></span></button>
		<button type="button" class="btn btn-default" onclick="<portlet:namespace/>modifyDataType();"><span class="icon-arrow-right">  <liferay-ui:message key='next'/></span></button>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	<portlet:namespace/>createSelectBox('${mode}');
});

function <portlet:namespace/>createSelectBox(mode) {
    if (mode == '<%=Constants.UPDATE%>') {
        if ('${editorStr}' != "") {
            var editorStr = '${editorStr}'.split(',');
            for (var i = 0; i < editorStr.length; i++) {
                $("input[type='checkbox'][name='<portlet:namespace/>editorCheckbox'][value*=" + editorStr[i] + "]").each(function() {
                    $(this).trigger("click");
                });
            }
            $selectbox = $("#<portlet:namespace/>defaultEditorSelect");
            $selectbox.val('${defaultEditor}');
        }

        if ('${analyzerStr}' != "") {
            var analyzerStr = '${analyzerStr}'.split(',');
            for (var i = 0; i < analyzerStr.length; i++) {
                $("input[type='checkbox'][name='<portlet:namespace/>analyzerCheckbox'][value*=" + analyzerStr[i] + "]").each(function() {
                    $(this).trigger("click");
                });
            }

            $selectbox = $("#<portlet:namespace/>defaultAnalyzerSelect");
            $selectbox.val('${defaultAnalyzer}');
        }
    }
}

function checkEditor(id) {
    $checkbox = $("#" + id);
    var appId= $checkbox.val();
    var name= $checkbox.attr("data-app-name");
    $selectbox = $("#<portlet:namespace/>defaultEditorSelect");

    if ($checkbox.prop('checked')) {
        $("<option/>").val(appId)
            .attr("id", "<portlet:namespace/>defaultEditorOption_" + appId)
            .text(name)
            .appendTo($selectbox);
    } else {
        $("#<portlet:namespace/>defaultEditorOption_" + appId).remove();
        $selectbox.focus();
    }

}

function checkAnalyzer(id) {
	$checkbox = $("#" + id);
    var appId= $checkbox.val();
    var name= $checkbox.attr("data-app-name");
    $selectbox = $("#<portlet:namespace/>defaultAnalyzerSelect");

    if ($checkbox.prop('checked')) {
        $("<option/>").val(appId)
            .attr("id", "<portlet:namespace/>defaultAnalyzerOption_" + appId)
            .text(name)
            .appendTo($selectbox);
    } else {
        $("#<portlet:namespace/>defaultAnalyzerOption_" + appId).remove();
        $selectbox.focus();
    }
}

function <portlet:namespace/>historyBack() {
    location.href = "${redirectOrignURL}";
}

function <portlet:namespace/>modifyDataType() {
    if (confirm(Liferay.Language.get('would-you-like-to-save'))) {
        var checkEditorItem = $("#<portlet:namespace/>defaultEditorSelect");
        var checkAnalyzerItem = $("#<portlet:namespace/>defaultAnalyzerSelect");

        var checkDefaultEditorVal = checkEditorItem.find("option:selected").val();
        var checkDefaultAnalyzerVal = checkAnalyzerItem.find("option:selected").val();

        if ('${scienceAppId}' != '') {
            if ('${portType}' == 'INPUT') {
                if (checkDefaultEditorVal == "") {
                    alert(Liferay.Language.get('this-field-is-mandatory'));
                    checkEditorItem.focus();
                    return false;
                }
            } else {
                if (checkDefaultAnalyzerVal == "") {
                    alert(Liferay.Language.get('this-field-is-mandatory'));
                    checkAnalyzerItem.focus();
                    return false;
                }
            }
            submitForm( <portlet:namespace/>frm);
        } else {
            if (checkDefaultEditorVal == "" && checkDefaultAnalyzerVal == "") {
                alert(Liferay.Language.get('this-field-or-another-is-mandatory', ['Defaule Editor', 'Defaule Analyzer']));
                checkDefaultItem.focus();
                return false;
            } else {
                submitForm( <portlet:namespace/>frm);
            }
        }
    } else {
        return false;
    }

}
</script>