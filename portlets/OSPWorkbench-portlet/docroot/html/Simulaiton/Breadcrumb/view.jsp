<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init-simulation-workbench.jsp"%>

<c:set var="contextPath" value="<%=request.getContextPath() %>" scope="page" />

<div class="btn-group btn-breadcrumb" id="<portlet:namespace/>breadcrumb">
	<button class="btn btn-default" type="button" onClick="<portlet:namespace/>historyBack();"><span class="fa fa-history" id="<portlet:namespace/>backTitle"></span></button>
	<button class="btn btn-primary" type="button" style="cursor: not-allowed;"><span class="icon-desktop" id="<portlet:namespace/>appName"></span></button>
	<button class="btn btn-primary" type="button" style="cursor: not-allowed;"><span class="fa fa-folder" id="<portlet:namespace/>simulationTitle"></span></button>
	<button class="btn btn-primary" type="button" style="cursor: not-allowed;"><span class="fa fa-folder" id="<portlet:namespace/>jobTitle"></span></button>
	
</div>
<script type="text/javascript">
/***********************************************************************
 * Global variables
 ***********************************************************************/
var <portlet:namespace/>connector;
var <portlet:namespace/>backURL;
/***********************************************************************
 * Initailization section and handling Liferay events
 ***********************************************************************/
Liferay.on(OSP.Event.OSP_HANDSHAKE, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet == myId){
		<portlet:namespace/>connector = e.portletId;
		
		var events = [
			OSP.Event.OSP_EVENTS_REGISTERED,
			OSP.Event.OSP_REQUEST_APP_INFO
		];
		
		var eventData = {
				portletId: myId,
				targetPortlet: <portlet:namespace/>connector,
				portletType: OSP.Enumeration.PortType.DASHBOARD,
				data: events
			};
		Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
	}
});

Liferay.on(OSP.Event.OSP_EVENTS_REGISTERED,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		var eventData = {
			portletId: myId,
			targetPortlet: e.portletId
		}
		Liferay.fire( OSP.Event.OSP_REQUEST_APP_INFO, eventData );
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_APP_INFO, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>breadcrumbChange(e.data);
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_BREADCRUMB_CHANGE,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		<portlet:namespace/>breadcrumbChange(e.data);
	}
});
/***********************************************************************
 * Golbal functions
************************************************************************/
function <portlet:namespace/>breadcrumbChange(data){
	if(nullToStr(data.workbenchId)!=""){
		if(data.workbenchId.indexOf("flow")>-1){
			$targetDiv = $("#<portlet:namespace/>breadcrumb");
			
			$("<button/>").addClass("btn btn-primary").attr("type","button").append(
					$("<span/>").addClass("fa fa-folder").html("  입력")
			).appendTo($targetDiv);
			
			$("<button/>").addClass("btn btn-primary").attr("type","button").append(
					$("<span/>").addClass("fa fa-folder").html("  모니터링")
			).appendTo($targetDiv);
			
			$("<button/>").addClass("btn btn-primary").attr("type","button").append(
					$("<span/>").addClass("fa fa-folder").html("  분석")
			).appendTo($targetDiv);
		}else{
			$targetDiv = $("#<portlet:namespace/>breadcrumb");
			$("<button/>").addClass("btn btn-primary").attr("type","button").css("cursor","not-allowed").append(
					$("<span/>").addClass("icon-columns").html("  Layout")
				).appendTo($targetDiv);
		}
	}
	if(nullToStr(data.backURL)!=""){
		<portlet:namespace/>backURL = data.backURL;
	}
	if(nullToStr(data.scienceApp)!=""){
		$("#<portlet:namespace/>appName").html("  "+data.scienceApp.name());
	}
	if(nullToStr(data.backTitle)!=""){
		$("#<portlet:namespace/>backTitle").html("  "+data.backTitle);
	}
	if(nullToStr(data.simulationTitle)!=""){
		$("#<portlet:namespace/>simulationTitle").html("  "+data.simulationTitle);
	}
	if(nullToStr(data.jobTitle)!=""){
		$("#<portlet:namespace/>jobTitle").html("  "+data.jobTitle);
	}
// 	if(nullToStr(data.jobStatus)!=""){
// 		if(!$(".<portlet:namespace/>layout").is(":visible")){
			
// 		}
// 	}
}
</script>