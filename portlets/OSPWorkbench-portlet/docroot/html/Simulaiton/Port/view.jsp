<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init-simulation-workbench.jsp"%>

<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<div class="panel panel-primary" id="<portlet:namespace/>port-remote" style="left: 80%;top: 0px;position: absolute;z-index: 9;">
	<div class="panel-heading" style="z-index: 10;cursor: move;">Port Selector</div>
	<div class="panel-body">
		<div class="panel-group" id="<portlet:namespace/>port-accordion">
		
			
		</div>
	</div>
	<div class="panel-footer">
		프로비넌스 엔진 <liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/>
		<input type="checkbox" data-toggle="toggle" data-onstyle="success" data-offstyle="danger" data-on="Enabled" data-off="Disabled">
		<button type="submit" class="btn btn-primary btn-flat ">제출</button>
	</div>
</div>

<script type="text/javascript">
/***********************************************************************
 * Global variables
 ***********************************************************************/
var <portlet:namespace/>connector;
var <portlet:namespace/>scienceApp = new OSP.ScienceApp();

/***********************************************************************
 * Initailization section and handling Liferay events
 ***********************************************************************/
Liferay.on(OSP.Event.OSP_HANDSHAKE, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet == myId){
		<portlet:namespace/>connector = e.portletId;
		
		var events = [
			OSP.Event.OSP_EVENTS_REGISTERED,
			OSP.Event.OSP_RESPONSE_PORT_INFO
		];
		
		var eventData = {
				portletId: myId,
				targetPortlet: <portlet:namespace/>connector,
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
		<portlet:namespace/>init();
		Liferay.fire( OSP.Event.OSP_REQUEST_PORT_INFO, eventData );
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_PORT_INFO, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		console.log('OSP_RESPONSE_PORT_INFO: ['+e.portletId+', '+new Date()+']', e.data.scienceApp);
		<portlet:namespace/>scienceApp = e.data.scienceApp;
		
		var inputPorts = <portlet:namespace/>scienceApp.inputPortsArray(); 
		<portlet:namespace/>displayPorts(inputPorts, OSP.Enumeration.PortType.INPUT);

		var logPorts = <portlet:namespace/>scienceApp.logPortsArray(); 
		<portlet:namespace/>displayPorts(logPorts, OSP.Enumeration.PortType.LOG);

		var outputPorts = <portlet:namespace/>scienceApp.outputPortsArray(); 
		<portlet:namespace/>displayPorts( outputPorts, OSP.Enumeration.PortType.OUTPUT);
	}
});

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>init(){
	$( "#<portlet:namespace/>port-remote" ).draggable({
		containment: $( "#<portlet:namespace/>port-remote" ).parent().parent(),
		cursor: "move"
	});
}

function <portlet:namespace/>displayPorts( ports, portType ){
	if( ports.length <=0 ){
		return;
	}
	
	var css = {};
	switch( portType ){
	case OSP.Enumeration.PortType.INPUT:
		css.PanelClass = 'panel panel-info';
		css.LiClass = 'list-group-item list-group-item-default';
		css.Title = '입력';
		break;
	case OSP.Enumeration.PortType.LOG:
		css.PanelClass = 'panel panel-info';
		css.LiClass = 'list-group-item list-group-item-default';
		css.Title = '로그';
		break;
	case OSP.Enumeration.PortType.OUTPUT:
		css.PanelClass = 'panel panel-info';
		css.LiClass = 'list-group-item list-group-item-default';
		css.Title = '출력';
	default:
	}
	
	var accordionId = "<portlet:namespace/>collapse_"+portType;
	$targetDiv = $("#<portlet:namespace/>port-accordion");
	$panelDiv = $("<div/>").addClass(css.PanelClass).appendTo($targetDiv);
	
	
	$panelHeding = $("<div/>").addClass("panel-heading").appendTo($panelDiv);
	$("<h4/>").addClass("panel-title").append(
			$("<a/>").attr("data-toggle","collapse").attr("data-parent","#<portlet:namespace/>port-accordion")
					 .attr("href","#"+accordionId).html(css.Title)
			).appendTo($panelHeding);
	
	$collapse = $("<div/>").attr("id",accordionId).addClass("collapse").appendTo($panelDiv);
	$panelBody = $("<div/>").addClass("panel-body").appendTo($collapse);
	$ul = $("<ul/>").addClass("list-group").appendTo($panelBody);
	
	for( var index in ports ){
		var port = ports[index];
		var portStatus = port.status();
		
		var $item = $("<li/>").addClass(css.LiClass).html(port.name()).appendTo($ul);
	}
}
</script>