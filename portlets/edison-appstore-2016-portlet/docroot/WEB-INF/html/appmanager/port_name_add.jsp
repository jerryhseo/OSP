<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="checkePortNameURL" copyCurrentRenderParameters="false" id="checkePortName" escapeXml="false">
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
	<portlet:param name="portType" value="${portType}"/>
</liferay-portlet:resourceURL>

<%
	String prePortName = GetterUtil.get(request.getAttribute("portName"), "");
%>

<style type="text/css">
</style>
<div class="newWindow" style="background-color: #fff; overflow-x: hidden;overflow-y: hidden;">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">
				<liferay-ui:message key="edison-table-list-header-port-name"/>
			</div>
		</div>
		<div class="newWclose">
			<img id="port-name-close-btn" name="port-name-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	<div class="newWcont01">
		<div class="input-group">
			<input type="text" class="field" id="portName" size="40" maxlength="30" onKeydown="if(event.keyCode ==13){<portlet:namespace/>checkName();return false;}" />
			<span class="input-group-btn">
				<button class="btn btn-primary" type="button" onclick="<portlet:namespace/>checkName();"><span class="icon-ok"> <liferay-ui:message key="add"/></span></button>
			</span>
		</div>
	</div>
</div>
<script type="text/javascript">
AUI().ready(function() {
	$("#port-name-close-btn").click(function() {
		$("#port-name-dialog").dialog("close");
	});
});

function <portlet:namespace/>checkName(){
	if($("#portName").val()==""){
		alert(Liferay.Language.get('edison-create-account-description-message-first-line'));
		$("#portName").focus();
		return false;
	}else{
		var checkName = $("#portName").val();
		if(!/^[-|_]?[a-zA-Z0-9_]+$/.test(checkName)){
			alert(Liferay.Language.get('expression-is-not-valid-ex-3',['param','param1','-param']));
			return false;
		}
		
		if(!scienceApp.verifyPortName(checkName)){
			alert(Liferay.Language.get('edison-science-appstore-toolkit-duplicate-message'));
			return false;
		}
	}
	
	$('.ui-widget-overlay').click();
	var val = $("#portName").val();
	<portlet:namespace/>dataTypeSearchOpen('${portType}',val);
}

</script>