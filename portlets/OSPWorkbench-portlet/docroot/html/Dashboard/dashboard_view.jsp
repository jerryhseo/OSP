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
			data: events
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
		var scienceApp = <portlet:namespace/>scienceApp;
		
		if( e.data.inputPorts )
			scienceApp.inputPorts( e.data.inputPorts );
		if( e.data.logPorts)
			scienceApp.logPorts( e.data.logPorts );
		if( e.data.outputPorts )
			scienceApp.outputPorts( e.data.outputPorts );
		//console.log( scienceApp );
		<portlet:namespace/>displayPorts();
	}
});


/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>displayPort( header, list, ports, selectClass ){
	header.addClass("has-sub");
	header.addClass('active');
	header.click(function(){
		if(list.is(':visible')){
			header.removeClass('active');
			list.addClass('hide').slideUp('normal');
		}
		else{
			header.addClass('active');
			list.removeClass('hide').slideDown('normal');
		}
	});
	
	var i=0;
	list.empty();
	console.log( ports );
	
	for( var index in ports ){
		var inputPort = ports[index];
		console.log( 'Dashboard: ', inputPort );
		var $item;
		if(i == 0){
			$item = $('<li class="'+selectClass+'">'+inputPort.name()+'</li>');
		}else{
			$item = $('<li>'+inputPort.name()+'</li>');
		}
		
		$item.click(function(){
			$(this).addClass(selectClass);
			$(this).siblings().removeClass(selectClass);
			
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet:<portlet:namespace/>connector,
					portName: $(this).text()
			}
			Liferay.fire( OSP.Event.OSP_PORT_SELECTED, eventData);
		});
		
		list.append( $item );
		i++;
	}
}

function <portlet:namespace/>displayPorts(){
	var inputPorts = <portlet:namespace/>scienceApp.inputPortsArray(); 
	if(inputPorts.length > 0){
		<portlet:namespace/>displayPort( 
				$('#<portlet:namespace/>inputPortSectionHeader'),
				$('#<portlet:namespace/>inputPortList'),
				inputPorts,
				'inputPortSelected');
		
	}
	else{
		$('#<portlet:namespace/>inputPortSectionHeader').css('display', 'none');
	}

	var logPorts = <portlet:namespace/>scienceApp.logPortsArray(); 
	if(logPorts.length > 0){
		console.log( 'Log Ports: ', logPorts);
		<portlet:namespace/>displayPort( 
				$('#<portlet:namespace/>logPortSectionHeader'),
				$('#<portlet:namespace/>logPortList'),
				logPorts,
				'logPortSelected');
		
	}
	else{
		$('#<portlet:namespace/>logPortSectionHeader').css('display', 'none');
	}


	var outputPorts = <portlet:namespace/>scienceApp.outputPortsArray(); 
	if(outputPorts.length > 0){
		<portlet:namespace/>displayPort( 
				$('#<portlet:namespace/>outputPortSectionHeader'),
				$('#<portlet:namespace/>outputPortList'),
				outputPorts,
				"outputPortSelected");
		
	}
	else{
		$('#<portlet:namespace/>outputPortSectionHeader').css('display', 'none');
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
