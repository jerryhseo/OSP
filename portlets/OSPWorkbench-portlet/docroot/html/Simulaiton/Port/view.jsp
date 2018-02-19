<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init-simulation-workbench.jsp"%>

<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<div class="panel panel-primary" id="<portlet:namespace/>port-remote" style="left: 80%;top: 60px;position: absolute;z-index: 8;">
	<div class="panel-heading" style="z-index: 10;cursor: move;">Port Selector</div>
	<div class="panel-body">
		<div class="panel-group" id="<portlet:namespace/>port-accordion">
		
			
		</div>
	</div>
	<div class="panel-footer" style="min-width: 385px;">
		<div id="<portlet:namespace/>port-edit-btn-group">
			프로비넌스 엔진 <liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/>
			<input type="checkbox" data-toggle="toggle" data-onstyle="success" data-offstyle="danger" data-on="Enabled" data-off="Disabled">
			<button class="btn btn-primary btn-flat" id="<portlet:namespace/>jobSave"><span class="icon-save">  저장</span></button>
			<button class="btn btn-success btn-flat" id="<portlet:namespace/>jobSubmit"><span class="icon-cloud-upload">  제출</span></button>
		</div>
		<div id="<portlet:namespace/>port-view-btn-group">
			<button class="btn btn-primary btn-flat" id="<portlet:namespace/>jobRerun"><span class="icon-repeat">  Re-Run</span></button>
		</div>
	</div>
</div>

<script type="text/javascript">
/***********************************************************************
 * Global variables
 ***********************************************************************/
var <portlet:namespace/>connector;
var <portlet:namespace/>scienceApp = new OSP.ScienceApp();
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
		Liferay.fire( OSP.Event.OSP_REQUEST_APP_INFO, eventData );
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_APP_INFO, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		if(e.data.scienceApp.templateId().indexOf("flow")>-1){
			<portlet:namespace/>isFlow = true;
		}else{
			<portlet:namespace/>isFlow = false;
		}
		
		var myId = '<%=portletDisplay.getId()%>';
		if(e.targetPortlet === myId){
			var eventData = {
				portletId: myId,
				targetPortlet: e.portletId
			}
			<portlet:namespace/>init();
			Liferay.fire( OSP.Event.OSP_REQUEST_PORT_INFO, eventData );
		}
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

Liferay.on(OSP.Event.OSP_REFRESH_JOB_STATUS, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		console.log('OSP_REFRESH_JOB_STATUS: ['+e.portletId+', '+new Date()+']', e.data);
		
		<portlet:namespace/>updateJobPortStatus(e.data);
	}
});

Liferay.on(OSP.Event.OSP_PORT_STATUS_CHANGED, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
// 	if(e.targetPortlet === myId){
		console.log('OSP_PORT_STATUS_CHANGED: ['+e.portletId+', '+new Date()+']', e.data.status);
		
		var $item = $('#<portlet:namespace/>'+e.data.portName);
		switch( e.data.status ){
			case OSP.Enumeration.PortStatus.READY:
				$item.addClass('list-group-item-success');
				// $item.addClass( css.selected );
				break;
			case OSP.Enumeration.PortStatus.LOG_VALID:
			case OSP.Enumeration.PortStatus.OUTPUT_VALID:
				$item.removeClass( 'portInvalid' );
				break;
			case OSP.Enumeration.PortStatus.LOG_INVALID:
			case OSP.Enumeration.PortStatus.OUTPUT_INVALID:
				$item.addClass( 'portInvalid' );
				break;
		}
// 	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_SAVE_SIMULATION_RESULT, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		if(e.data){
			toastr["success"]("", Liferay.Language.get('edison-data-update-success'));
		}else{
			toastr["error"]("", Liferay.Language.get('edison-data-update-error'));
		}
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_FLOW_LAYOUT_CODE_UPDATE, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId||e.targetPortlet === "BROADCAST"){
		console.log('OSP_RESPONSE_FLOW_LAYOUT_CODE_UPDATE: ['+e.portletId+', '+new Date()+']', e.data);
		<portlet:namespace/>flowPortChange(e.data.flowLayoutCode);
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
		css.LiClass = 'list-group-item <portlet:namespace/>port-input';
		css.Title = '입력';
		if(<portlet:namespace/>isFlow){
			css.collapse='';
		}else{
			css.collapse=' in';
		}
		
		break;
	case OSP.Enumeration.PortType.LOG:
		css.PanelClass = 'panel panel-info';
		css.LiClass = 'list-group-item <portlet:namespace/>port-log';
		css.Title = '로그';
		css.collapse='';
		break;
	case OSP.Enumeration.PortType.OUTPUT:
		css.PanelClass = 'panel panel-info';
		css.LiClass = 'list-group-item <portlet:namespace/>port-output';
		css.Title = '출력';
		css.collapse='';
	default:
	}
	
	var accordionId = "<portlet:namespace/>collapse_"+portType.toUpperCase();
	$targetDiv = $("#<portlet:namespace/>port-accordion");
	$panelDiv = $("<div/>").addClass(css.PanelClass).appendTo($targetDiv);
	
	
	$panelHeding = $("<div/>").addClass("panel-heading").appendTo($panelDiv);
	$("<h4/>").addClass("panel-title").append(
			$("<a/>").attr("data-toggle","collapse").attr("data-parent","#<portlet:namespace/>port-accordion")
					 .attr("href","#"+accordionId).html(css.Title+"<span class=\"badge pull-right\">"+ports.length+"</span>")
			).appendTo($panelHeding);
	
	$collapse = $("<div/>").attr("id",accordionId).addClass("collapse "+ css.collapse).appendTo($panelDiv);
	$panelBody = $("<div/>").addClass("panel-body").appendTo($collapse);
	$listDiv = $("<div/>").addClass("list-group").appendTo($panelBody);
	
	for( var index in ports ){
		var port = ports[index];
		var portStatus = port.status();
		
		var $item = $("<a/>").css("cursor","pointer").addClass(css.LiClass)
				   .attr("id","<portlet:namespace/>"+port.name())
				   .attr("href","#")
				   .attr("data-port-type",portType)
				   .html(port.name()).appendTo($listDiv);
		
		$item.click(function(){
			<portlet:namespace/>selectPort(this);
		});
	}
}

function <portlet:namespace/>selectPort(object){
	if(!$(object).hasClass("disabled")){
		if(!$(object).hasClass("active")){
			$(object).addClass("active");
			$(object).siblings().removeClass("active");
			
			if(<portlet:namespace/>isFlow){
				<portlet:namespace/>flowLayoutUpdate($(object).attr("data-port-type"));
			}
			
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet:<portlet:namespace/>connector,
					portName: $(object).text(),
					portType: $(object).attr("data-port-type")
			};
			Liferay.fire( OSP.Event.OSP_PORT_SELECTED, eventData);
		}
	}
}

function <portlet:namespace/>flowLayoutUpdate(portType){
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet:<portlet:namespace/>connector,
			flowLayoutCode :portType.toUpperCase()
	};
	Liferay.fire( OSP.Event.OSP_REQUEST_FLOW_LAYOUT_CODE_UPDATE, eventData);
}


function <portlet:namespace/>updateJobPortStatus(data){
	var status = data.jobStatus;
	var statusOrignCode = jobCodeConvertorFromStatus(status);
	$(".<portlet:namespace/>port-input").removeClass("active");
	$(".<portlet:namespace/>port-log").removeClass("active");
	$(".<portlet:namespace/>port-output").removeClass("active");
	
	$(".<portlet:namespace/>port-log").removeClass("disabled").css("cursor","pointer");
	$(".<portlet:namespace/>port-output").removeClass("disabled").css("cursor","pointer");
	
	$("#<portlet:namespace/>port-accordion .list-group-item-success").removeClass("list-group-item-success");
	
	if(statusOrignCode<1701005){
		$(".<portlet:namespace/>port-log").addClass("disabled").css("cursor","not-allowed");
		$(".<portlet:namespace/>port-output").addClass("disabled").css("cursor","not-allowed");
	}else if(statusOrignCode==1701006){
		$(".<portlet:namespace/>port-output").addClass("disabled").css("cursor","not-allowed");
	}
	
	$("#<portlet:namespace/>port-edit-btn-group").css("display","none");
	$("#<portlet:namespace/>port-view-btn-group").css("display","none");
	
	$("#<portlet:namespace/>jobSave").attr('onclick', '').unbind('click');
	$("#<portlet:namespace/>jobSubmit").attr('onclick', '').unbind('click');
	$("#<portlet:namespace/>jobRerun").attr('onclick', '').unbind('click');
	
	if(statusOrignCode==0){
		$("#<portlet:namespace/>port-edit-btn-group").css("display","block");
		$("#<portlet:namespace/>jobSave").click(function(){
			<portlet:namespace/>saveSimulationJob();
		});
		
		$("#<portlet:namespace/>jobSubmit").click(function(){
			var myId = '<%=portletDisplay.getId()%>';
			var eventData = {
					portletId : myId,
					targetPortlet : <portlet:namespace/>connector
			};
			Liferay.fire(OSP.Event.OSP_SUBMIT_JOB, eventData);
		});
		
	}else{
		$("#<portlet:namespace/>port-view-btn-group").css("display","block");
		$("#<portlet:namespace/>jobRerun").click(function(){
			var myId = '<%=portletDisplay.getId()%>';
			var eventData = {
					portletId : myId,
					targetPortlet : <portlet:namespace/>connector
			};
			Liferay.fire(OSP.Event.OSP_REQUEST_SIMULATION_MODAL, eventData);
		});
	}
	
	if(<portlet:namespace/>isFlow){
		<portlet:namespace/>flowPortChange(nullToStr(data.flowLayoutCode))
	}
}


function <portlet:namespace/>flowPortChange(flowLayoutCode){
	if(flowLayoutCode!=""){
		/*$('.collapse.in').collapse('hide');*/
		if(flowLayoutCode==="LOG"&&<portlet:namespace/>scienceApp.logPortsArray().length ==0){
			flowLayoutCode = "OUTPUT";
		}
		var collapseObject = $("#<portlet:namespace/>collapse_"+flowLayoutCode);
 		if(collapseObject.is('.collapse:not(.show)')){
			collapseObject.collapse('show');
 		}
	}
}

function <portlet:namespace/>saveSimulationJob(){
	var myId = '<%=portletDisplay.getId()%>';
	var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector
	};
				
	Liferay.fire(OSP.Event.OSP_SAVE_SIMULATION, eventData);
}


</script>