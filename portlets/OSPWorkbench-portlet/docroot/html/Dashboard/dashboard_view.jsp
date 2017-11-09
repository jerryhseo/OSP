<%@page import="javax.portlet.PortletPreferences"%>
<%@ include file="../init.jsp" %>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/html/Dashboard/css/dashboard.css">

<div id="<portlet:namespace/>DashBoardSection" class="row-fluid dashboard-portlet">
	<div class="span12" id="<portlet:namespace/>portsSection">
		<div class="row-fluid" id="<portlet:namespace/>buttonSection">
			<div class="span12">
				<i class="icon-magic lg btn_leftsmall icon" title="New Simulation" onclick="<portlet:namespace/>newSimulation()"></i>
				<i class="icon-plus-sign lg btn_leftsmall icon" title="Add Job" onclick="<portlet:namespace/>addSimulationJob()"></i>
				<i class="icon-play-circle lg btn_leftsmall icon" title="Run Simulation" onclick="<portlet:namespace/>runSimulation()"></i>
				<i class="icon-save lg btn_leftsmall icon" title="Save Simulation" onclick="<portlet:namespace/>saveSimulation()"></i>
				<i class="icon-eye-open lg btn_leftsmall icon" title="Job Status" onclick="<portlet:namespace/>showJobStatus()"></i>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<!-- InputPort & inputPort List   -->
				<div id='<portlet:namespace/>inputPortSectionHeader' class='LogPortName'>Input Ports</div>
				<ul id='<portlet:namespace/>inputPortList' class="logPort">
				</ul>
				
				<!-- LogPort & LogPort List   -->
				<div id='<portlet:namespace/>logPortSectionHeader' class='LogPortName'>Log Ports</div>
				<ul id='<portlet:namespace/>logPortList' class="logPort">
				</ul>
				
				<!-- OutputPort & outputPort List   -->
				<div id='<portlet:namespace/>outputPortSectionHeader' class='LogPortName'>Output Ports</div>
				<ul id='<portlet:namespace/>outputPortList' class="logPort">
				</ul>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
/***********************************************************************
 * Global variables
 ***********************************************************************/
var <portlet:namespace/>connector;
var <portlet:namespace/>scienceApp = new OSP.ScienceApp();


/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
 
/***********************************************************************
 * Handling OSP Events
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
			portletType: OSP.Enumeration.PortType.DASHBOARD,
			data: events,
		};
		Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
	}
});

Liferay.on( 
		OSP.Event.OSP_EVENTS_REGISTERED,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if(e.targetPortlet === myId){
				var eventData = {
						portletId: myId,
						targetPortlet: e.portletId
				}
				Liferay.fire( OSP.Event.OSP_REQUEST_PORT_INFO, eventData );
			}
		}
);

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

Liferay.on(OSP.Event.OSP_REFRESH_PORTS_STATUS, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		console.log('OSP_REFRESH_PORTS_STATUS: ['+e.portletId+', '+new Date()+']', e.data.scienceApp);
		<portlet:namespace/>scienceApp = e.data.scienceApp;
		
		var inputPorts = <portlet:namespace/>scienceApp.inputPortsArray(); 
		<portlet:namespace/>displayPorts(inputPorts, OSP.Enumeration.PortType.INPUT);

		var logPorts = <portlet:namespace/>scienceApp.logPortsArray(); 
		<portlet:namespace/>displayPorts(logPorts, OSP.Enumeration.PortType.LOG);

		var outputPorts = <portlet:namespace/>scienceApp.outputPortsArray(); 
		<portlet:namespace/>displayPorts( outputPorts, OSP.Enumeration.PortType.OUTPUT);
	}
});

Liferay.on(OSP.Event.OSP_PORT_STATUS_CHANGED, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		console.log('OSP_PORT_STATUS_CHANGED: ['+e.portletId+', '+new Date()+']', e.data.status);
		
		var $item = $('#<portlet:namespace/>'+e.data.portName);
		switch( e.data.status ){
			case OSP.Enumeration.PortStatus.READY:
				$item.removeClass( 'inputPortEmpty' );
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
	}
});


/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>displayPorts( ports, portType ){
	var $headerSection;
	var $itemSection;
	var css = {};
	
	switch( portType ){
		case OSP.Enumeration.PortType.INPUT:
			$headerSection = $('#<portlet:namespace/>inputPortSectionHeader');
			$itemSection = $('#<portlet:namespace/>inputPortList');
			css.selected = 'inputPortSelected';
			css.empty = 'inputPortEmpty';
			break;
		case OSP.Enumeration.PortType.LOG:
			$headerSection = $('#<portlet:namespace/>logPortSectionHeader');
			$itemSection = $('#<portlet:namespace/>logPortList');
			css.selected = 'logPortSelected';
			css.invalid = 'portInvalid';
			break;
		case OSP.Enumeration.PortType.OUTPUT:
			$headerSection = $('#<portlet:namespace/>outputPortSectionHeader');
			$itemSection = $('#<portlet:namespace/>outputPortList');
			css.selected = 'outputPortSelected';
			css.invalid = 'portInvalid';
		default:
	}
	
	if( ports.length <=0 ){
		$headerSection.css('display', 'none');
		return;
	}
	
	$headerSection.addClass('has-sub');
	$headerSection.addClass('active');
	$headerSection.click(function(){
		if($itemSection.is(':visible')){
			$headerSection.removeClass('active');
			$itemSection.addClass('hide').slideUp('normal');
		}
		else{
			$headerSection.addClass('active');
			$itemSection.removeClass('hide').slideDown('normal');
		}
	});
	
	var i=0;
	$itemSection.empty();
	console.log( ports );
	
	for( var index in ports ){
		var port = ports[index];
		var portStatus = port.status();
		
		var $item;
		if(i === 0){
			$item = $('<li id="<portlet:namespace/>'+port.name()+'" class="'+css.selected+'">'+port.name()+'</li>');
		}else{
			$item = $('<li  id=<portlet:namespace/>"'+port.name()+'>'+port.name()+'</li>');
		}
		
		$item.click(function(){
			$(this).addClass(css.selected);
			$(this).siblings().removeClass(css.selected);
			
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet:<portlet:namespace/>connector,
					portName: $(this).text(),
					portType: portType
			}
			Liferay.fire( OSP.Event.OSP_PORT_SELECTED, eventData);
		});
		
		switch( portType ){
			case OSP.Enumeration.PortType.INPUT:
				if( portStatus === OSP.Enumeration.PortStatus.EMPTY ){
					$item.addClass( css.empty );
				}
				break;
			case OSP.Enumeration.PortType.LOG:
			case OSP.Enumeration.PortType.OUTPUT:
				if( portStatus === OSP.Enumeration.PortStatus.INVALID ){
					$item.addClass( css.invalid );
				}
				break;
		}
		
		$itemSection.append( $item );
		i++;
	}
}

//Add Simulation
function <portlet:namespace/>newSimulation(){
	var myId = '<%=portletDisplay.getId()%>';
	var eventdata = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector
	};
	
	Liferay.fire(OSP.Event.OSP_CREATE_SIMULATION, eventdata);
}

function <portlet:namespace/>addSimulationJob(){
	var myId = '<%=portletDisplay.getId()%>';
	var eventData = {
		portletId : myId,
		targetPortlet : <portlet:namespace/>connector
	};
	
	Liferay.fire(OSP.Event.OSP_CREATE_JOB, eventData);
}

function <portlet:namespace/>runSimulation(){
	var myId = '<%=portletDisplay.getId()%>';
	var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector
	};
				
	Liferay.fire(OSP.Event.OSP_SUBMIT_SIMULATION, eventData);
}

function <portlet:namespace/>saveSimulation(){
	var myId = '<%=portletDisplay.getId()%>';
	var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector
	};
				
	Liferay.fire(OSP.Event.OSP_SAVE_SIMULATION, eventData);
}

function <portlet:namespace/>showJobStatus(){
	var myId = '<%=portletDisplay.getId()%>';
	var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>connector
	};
				
	Liferay.fire(OSP.Event.OSP_SHOW_JOB_STATUS, eventData);
}

</script>
