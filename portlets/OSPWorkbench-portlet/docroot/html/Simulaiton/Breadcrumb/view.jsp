<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init-simulation-workbench.jsp"%>

<c:set var="contextPath" value="<%=request.getContextPath() %>" scope="page" />

<div class="btn-group btn-breadcrumb" id="<portlet:namespace/>breadcrumb">
	<button class="btn btn-default" type="button" onclick="<portlet:namespace/>historyBack();"><span class="fa fa-history" id="<portlet:namespace/>backTitle"></span></button>
	<button class="btn btn-default" type="button" style="cursor: not-allowed;"><span class="icon-desktop" id="<portlet:namespace/>appName"></span></button>
	<button class="btn btn-default" type="button" style="cursor: not-allowed;"><span class="fa fa-folder" id="<portlet:namespace/>simulationTitle"></span></button>
	<button class="btn btn-default" type="button" style="cursor: not-allowed;"><span class="fa fa-folder" id="<portlet:namespace/>jobTitle"></span></button>
	
</div>
<script type="text/javascript">
/***********************************************************************
 * Global variables
 ***********************************************************************/
var <portlet:namespace/>connector;
var <portlet:namespace/>backURL;
var <portlet:namespace/>isFlow;
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
		<portlet:namespace/>displayInit(e.data);
	}
});

Liferay.on(OSP.Event.OSP_REFRESH_JOB_STATUS,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		<portlet:namespace/>breadcrumbChange(e.data);
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_FLOW_LAYOUT_CODE_UPDATE, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		console.log('OSP_RESPONSE_FLOW_LAYOUT_CODE_UPDATE: ['+e.portletId+', '+new Date()+']', e.data);
// 		<portlet:namespace/>flowBreadcrumbChange(e.data.flowLayoutCode);
	}
});
/***********************************************************************
 * Golbal functions
************************************************************************/
function <portlet:namespace/>historyBack(){
	if(<portlet:namespace/>backURL!=""){
		location.href = <portlet:namespace/>backURL;
	}
}

function <portlet:namespace/>displayInit(data){
	if(nullToStr(data.workbenchId)!=""){
// 		console.log(data.scienceApp);
		if(data.scienceApp.templateId().indexOf("flow")>-1){
			$targetDiv = $("#<portlet:namespace/>breadcrumb");
			
			$("<button/>").addClass("btn btn-primary").attr("type","button").css("cursor","pointer").append(
					$("<span/>").addClass("fa fa-folder").html("  "+Liferay.Language.get('edison-simulation-input'))
			).attr("onclick","<portlet:namespace/>flowLayoutUpdate('INPUT')").appendTo($targetDiv);
			
			
			if(data.scienceApp.logPortsArray().length > 0){
				$("<button/>").addClass("btn btn-default").attr("type","button").append(
						$("<span/>").addClass("fa fa-folder").html("  "+Liferay.Language.get('edison-simulation-log'))
				).css("cursor","not-allowed").attr("id","<portlet:namespace/>logBreadcrumb").appendTo($targetDiv);
			}
			
			if(data.scienceApp.outputPortsArray().length > 0){
				$("<button/>").addClass("btn btn-default").attr("type","button").append(
						$("<span/>").addClass("fa fa-folder").html("  "+Liferay.Language.get('edison-simulation-analysis'))
				).css("cursor","not-allowed").attr("id","<portlet:namespace/>outBreadcrumb").appendTo($targetDiv);
			}
			
			<portlet:namespace/>isFlow = true;
		}else{
			$targetDiv = $("#<portlet:namespace/>breadcrumb");
			$("<button/>").addClass("btn btn-primary").attr("type","button").css("cursor","not-allowed").append(
					$("<span/>").addClass("icon-columns").html("  Layout")
				).appendTo($targetDiv);
			
			<portlet:namespace/>isFlow = false;
		}
	}
	if(nullToStr(data.workbenchRedirectURL)!=""){
		<portlet:namespace/>backURL = data.workbenchRedirectURL;
	}
	if(nullToStr(data.workbenchRedirectName)!=""){
		$("#<portlet:namespace/>backTitle").html("  "+data.workbenchRedirectName);
	}
	if(nullToStr(data.scienceApp)!=""){
		$("#<portlet:namespace/>appName").html("  "+data.scienceApp.name());
	}
}


function <portlet:namespace/>breadcrumbChange(data){
	if(nullToStr(data.simulationTitle)!=""){
		$("#<portlet:namespace/>simulationTitle").html("  "+data.simulationTitle);
	}
	if(nullToStr(data.jobTitle)!=""){
		$("#<portlet:namespace/>jobTitle").html("  "+data.jobTitle);
	}
	if(<portlet:namespace/>isFlow){
		<portlet:namespace/>flowBreadcrumbChange(nullToStr(data.flowLayoutCode));
	}
}

function <portlet:namespace/>flowBreadcrumbChange(flowLayoutCode){
	if(flowLayoutCode!=""){
		
		var logBreadcrumbActive = false;
		var outBreadcrumbActive = false;
		
		var logBreadcrumb = $("button#<portlet:namespace/>logBreadcrumb");
		var outBreadcrumb = $("button#<portlet:namespace/>outBreadcrumb");
		
		logBreadcrumb.css("cursor","not-allowed").removeAttr("onclick").removeClass("btn-primary").addClass("btn-default");
		outBreadcrumb.css("cursor","not-allowed").removeAttr("onclick").removeClass("btn-primary").addClass("btn-default");
		
		
		if(flowLayoutCode!=""){
			if(flowLayoutCode ==="LOG"){
				logBreadcrumbActive = true;
			}else if(flowLayoutCode ==="OUTPUT"){
				logBreadcrumbActive = true;
				outBreadcrumbActive = true;
			}
		}
		
		if(logBreadcrumbActive){
			logBreadcrumb.css("cursor","pointer").addClass("btn-primary").attr("onclick","<portlet:namespace/>flowLayoutUpdate('LOG')");
		}
		
		if(outBreadcrumbActive){
			outBreadcrumb.css("cursor","pointer").addClass("btn-primary").attr("onclick","<portlet:namespace/>flowLayoutUpdate('OUTPUT')");
		}
	}
}

function <portlet:namespace/>flowLayoutUpdate(flowLayoutCode){
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet:<portlet:namespace/>connector,
			flowLayoutCode :flowLayoutCode
	};
	Liferay.fire( OSP.Event.OSP_REQUEST_FLOW_LAYOUT_CODE_UPDATE, eventData);
}
</script>