<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./init.jsp"%>
<link rel="stylesheet" href="${contextPath}/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/css/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminCustom.css">

<link rel="stylesheet" href="${contextPath}/css/simulation-workbench.css">

<style type="text/css">
	.simulation-workbench .vertical {
		top: 50%;
		left: 50%;
		margin-top: -20px;
		margin-left: -20px;
 		background: url(${contextPath}/images/simulation-workbench/arrow_lr.png) center no-repeat; 
	}
	
	
	.simulation-workbench .horizontal {
		left: 50%;
		margin-top: -20px;
		margin-left: -20px;
		background: url(${contextPath}/images/simulation-workbench/arrow_tb.png) center no-repeat;
	}
</style>

<liferay-portlet:resourceURL var="serveResourceURL"	id="serveResource" copyCurrentRenderParameters="false">
	
</liferay-portlet:resourceURL>
<%
	PortletPreferences preferences = portletDisplay.getPortletSetup();
	preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
	preferences.store();
	
	JSONObject workbenchLayout = (JSONObject) renderRequest.getAttribute("workbenchLayout");
	
	JSONArray jsonColumns = workbenchLayout.getJSONArray("columns_");
	JSONArray columns = JSONFactoryUtil.createJSONArray();
	for (int i = 0; i < jsonColumns.length(); i++) {
		JSONObject column = JSONFactoryUtil.createJSONObject();
		JSONObject jsonColumn = jsonColumns.getJSONObject(i);

		String currentPortlet = jsonColumn.getString("currentPortlet_");
		column.put("id", jsonColumn.getString("id_"));
		column.put("height", jsonColumn.getDouble("height_"));
		column.put("portletId", currentPortlet);
		columns.put(column);
	}

	String templateFile = workbenchLayout.getString("templateId_") + ".ftl";
	
	
%>
<div class="row" id="<portlet:namespace/>canvas">
	
</div>

<script type="text/javascript">
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>workbench = new OSP.Workbench( '<portlet:namespace/>');
<portlet:namespace/>workbench.id('<%=portletDisplay.getId()%>');
/***********************************************************************
 * Initailization section and handling Liferay events
 ***********************************************************************/
$(function(e) {
	<portlet:namespace/>workbench.layout( new OSP.Layout(JSON.parse('<%=workbenchLayout.toString()%>')));
	<portlet:namespace/>workbench.type ('${workbenchType}');
	// Resolving workbench layout
	$.ajax({
		url: '<%=serveResourceURL.toString()%>',
		type:'POST',
		dataType:'text',
		async: false,
		data:{
			<portlet:namespace/>command:'RESOLVE_TEMPLATE',
			<portlet:namespace/>namespace: '<portlet:namespace/>',
			<portlet:namespace/>templateDir: '/templates',
			<portlet:namespace/>templateFile:'<%=templateFile%>',
			<portlet:namespace/>columns: '<%=columns.toString()%>'
		},
		success: function( result ){
			$('#<portlet:namespace/>canvas').html( result );
			<portlet:namespace/>workbench.loadPortlets('<%=LiferayWindowState.EXCLUSIVE%>');
		}
	});
});
</script>

<script src="${contextPath}/js/jquery-knob/jquery.knob.min.js"></script>
<script src="${contextPath}/js/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="${contextPath}/js/fastclick/fastclick.js"></script>
<script src="${contextPath}/js/adminlte/adminlte.js"></script>
<script src="${contextPath}/js/lib/jquery.mustache.js"></script>
<script src="${contextPath}/js/lib/mustache.min.js"></script>