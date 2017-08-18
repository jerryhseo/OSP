<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="org.kisti.edison.science.model.ScienceApp"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@ include file="../init.jsp"%>
<link
	rel="stylesheet"
	type="text/css"
	href="<%=request.getContextPath()%>/js/jquery/jquery-layout/layout-default-latest.css"
>
<link
	rel="stylesheet"
	type="text/css"
	href="<%=request.getContextPath()%>/html/Workbench/css/workbench.css"
>
<script
	type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-layout/jquery.layout-latest.js"
></script>
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
	System.out.println("Template Name: " + templateFile);
	System.out.println(columns.toString());

	ScienceApp scienceApp = null;
	String inputPorts = "";
	String logPorts = "";
	String outputPorts = "";

	String workbenchType = (String) renderRequest.getAttribute("workbenchType");
	long classId = (Long) renderRequest.getAttribute("classId");
	long customId = (Long) renderRequest.getAttribute("customId");

	if (workbenchType.equalsIgnoreCase("SIMULATION_WITH_APP")
	    || workbenchType.equalsIgnoreCase("SIMULATION_RERUN")) {
		scienceApp = (ScienceApp) renderRequest.getAttribute("scienceApp");
		inputPorts = (String) renderRequest.getAttribute("inputPorts");
		logPorts = (String) renderRequest.getAttribute("logPorts");
		outputPorts = (String) renderRequest.getAttribute("outputPorts");
	}else {
		System.out.println("Un-recognizable workbench type: " + workbenchType);
	}
%>
<liferay-portlet:resourceURL
	var="serveResourceURL"
	copyCurrentRenderParameters="false"
></liferay-portlet:resourceURL>

<div
	class="row-fluid workbench-portlet"
	id="<portlet:namespace/>panel"
	style="border: none;"
>
	<div
		class="span12"
		id="<portlet:namespace/>canvas"
		style="height: 60vh;"
	></div>
	<div
		id="<portlet:namespace/>hiddenSection"
		style="display: none;"
	>
		<div
			id="<portlet:namespace/>fileExplorer"
			title="Select a file"
		>
			<div id="<portlet:namespace/>confirmDialog">
				<p id="<portlet:namespace/>confirmMessage">Simulation is not
					saved. Would you like to save current simulation?</p>
			</div>
			<div id="<portlet:namespace/>confirmOverlapDialog">
				<input
					type="text"
					id="<portlet:namespace/>uploadFileName"
				/><br />
				<p id="<portlet:namespace/>confirmMessage">File already exists.
					Change file name or just click 'OK' button to overlap.</p>
			</div>
			<div id='<portlet:namespace/>newSimulationDialog'>
				<input
					type="text"
					id="<portlet:namespace/>newSimulationTitle"
					style="width: 95%;"
				/>
			</div>
			<div id='<portlet:namespace/>getCoresDialog'>
				<input
					type="text"
					id="<portlet:namespace/>numberOfCores"
					style="width: 95%;"
				/>
			</div>
			<input
				type="file"
				id="<portlet:namespace/>selectLocalFile"
				name="<portlet:namespace/>uploadFile"
			/>
		</div>
	</div>
</div>
<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>submitQueue = [];
var <portlet:namespace/>workbench = new OSP.Workbench();
<portlet:namespace/>workbench.id( '<%=portletDisplay.getId()%>' );

var <portlet:namespace/>uploadProcess = {};
var <portlet:namespace/>dialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = 'FileExplorer_WAR_OSPEditorsportlet_INSTANCE_workbench';

/***********************************************************************
 * Initailization section and handling Liferay events
 ***********************************************************************/
Liferay.on('portletReady', function( e ){
	if( <portlet:namespace/>workbench.id() === e.portletId ){
		<portlet:namespace/>workbench.layout( new OSP.Layout(JSON.parse('<%=workbenchLayout.toString()%>')));
		<portlet:namespace/>workbench.type ( '<%=workbenchType%>' );
		<portlet:namespace/>workbench.classId( <%=classId%> );
		<portlet:namespace/>workbench.customId( <%=customId%> );
		
		switch( <portlet:namespace/>workbench.type() ){
			case OSP.Enumeration.WorkbenchType.SIMULATION_WITH_APP:
				var scienceApp = new OSP.ScienceApp();
				scienceApp.id( <%=scienceApp.getScienceAppId()%> );
				scienceApp.name( '<%=scienceApp.getName()%>' );
				scienceApp.version( '<%=scienceApp.getVersion()%>' );
				scienceApp.runType( '<%=scienceApp.getRunType()%>' );
				scienceApp.deserializeInputPorts( JSON.parse('<%=inputPorts%>') );
				if( '<%=logPorts%>' !== '' )
					scienceApp.deserializeLogPorts( JSON.parse('<%=logPorts%>') );
				if( '<%=outputPorts%>' !== '' )
					scienceApp.deserializeOutputPorts( JSON.parse('<%=outputPorts%>') );
				
				<portlet:namespace/>workbench.scienceApp(scienceApp);
				//console.log('Workbench: ', <portlet:namespace/>workbench);
				break;
			case OSP.Enumeration.WorkbenchType.SIMULATION_RERUN:
				var scienceApp = new OSP.ScienceApp();
				scienceApp.id( <%=scienceApp.getScienceAppId()%> );
				scienceApp.name( '<%=scienceApp.getName()%>' );
				scienceApp.version( '<%=scienceApp.getVersion()%>' );
				scienceApp.runType( '<%=scienceApp.getRunType()%>' );
				scienceApp.deserializeInputPorts( JSON.parse('<%=inputPorts%>') );
				if( '<%=logPorts%>' !== '' )
					scienceApp.deserializeLogPorts( JSON.parse('<%=logPorts%>') );
				if( '<%=outputPorts%>' !== '' )
					scienceApp.deserializeOutputPorts( JSON.parse('<%=outputPorts%>') );
				
				<portlet:namespace/>workbench.scienceApp(scienceApp);
				break;
			default:
				console.log( '[ERROR] Un-recognizable workbench type or not supported yet: '+<portlet:namespace/>workbench.type());
		}
		
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
				//console.log("serveResourceURL.toString() ",  result );
				$('#<portlet:namespace/>canvas').html( result );
				
				<portlet:namespace/>workbench.loadPortlets( true, '<%=LiferayWindowState.EXCLUSIVE%>', <portlet:namespace/>handShakeCallback);
			}
		});
	}
	else{
		console.log( 'Get ready event form others: '+e.portletId);
	}
});

function <portlet:namespace/>handShakeCallback( sourceId, targetId ){
    var portlet = <portlet:namespace/>workbench.getPortlet( targetId );
    if( portlet.portType() ){
        console.log('++++++++++++++++++++++++++++++++');
        console.log( portlet );
    }
    
	var eventData = {
			portletId: sourceId,
			targetPortlet: targetId,
			action: portlet.portType()
	};
	
	Liferay.fire( OSP.Event.OSP_HANDSHAKE, eventData );
}


/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
		OSP.Event.OSP_REGISTER_EVENTS,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REGISTER_EVENTS: ['+e.portletId+', '+new Date()+']');
				
				if( <portlet:namespace/>workbench.registerEvents( e.portletId, e.data ))
					<portlet:namespace/>workbench.fire( OSP.Event.OSP_EVENTS_REGISTERED, e.portletId, e.data );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_SAMPLE_CONTENT,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_SAMPLE_CONTENT: ['+e.portletId+', '+new Date()+']');
				
				var scienceApp = <portlet:namespace/>workbench.scienceApp();
				var portlet = <portlet:namespace/>workbench.getPortlet(e.portletId);
				var port = scienceApp.getPort( portlet.portName() );
				var dataType = port.dataType();
				
				console.log('Science App Port: ', port );

				var sample = port.sample();
				var dlEntryId = 0;
				var command;
				var data;
				if( sample ){
					data = {
							<portlet:namespace/>command: 'READ_DLENTRY',
							<portlet:namespace/>dlEntryId: sample.dlEntryId(),
                            <portlet:namespace/>dataTypeName: dataType.name,
                            <portlet:namespace/>dataTypeVersion: dataType.version
					};
				}
				else{
					data = {
							<portlet:namespace/>command: 'READ_DATATYPE_SAMPLE',
							<portlet:namespace/>dataTypeName: dataType.name,
							<portlet:namespace/>dataTypeVersion: dataType.version
					};
				}
				//console.log( 'sample: ', sample);
				$.ajax({
					url: '<%=serveResourceURL.toString()%>',
					type:'POST',
					async: false,
					dataType: 'json',
					data: data,
					success:function(result){
					    console.log( 'Read Sample Result', result);
					    if( result.error ){
					        alert( result.error );
					        return;
					    }
					    
						var contentType = result.contentType;
						
						var inputData = new OSP.InputData();
						inputData.type( contentType);
						inputData.order( port.order() );
						inputData.portName( port.name() );
						switch( contentType ){
							case 'fileContent':
								inputData.context( result.fileContent );
								break;
							case 'structuredData':
								var ospDataType = new OSP.DataType();
								ospDataType.deserializeStructure( JSON.parse(result.dataStructure) );
								console.log( 'OSP Data Type: ', ospDataType.structure() );
								if( result.fileContent ){
								    console.log( '------result.fileContent ', result.fileContent );
								    ospDataType.loadStructure( result.fileContent );
								}
								inputData.context( ospDataType.structure() );
								break;
							default:
								console.log('[ERROR] Wrong sample data type.'+contentType);
								return;
						}
						
						<portlet:namespace/>setJobInputData( portlet.portName(), inputData )
						
						<portlet:namespace/>workbench.fire( 
								OSP.Event.OSP_LOAD_DATA, 
								e.portletId, 
								OSP.Util.toJSON(inputData) );
					},
					error: function(){
					    alert('[ERROR] AJAX FAILED during READ_DATATYPE_SAMPLE: '+sample.dlEntryId()+
					          '\nPlease contact site manager.');
						console.log('[ERROR] AJAX FAILED during READ_DATATYPE_SAMPLE');
					}
				});
			}
		}
);

Liferay.on(
		OSP.Event.OSP_SAMPLE_SELECTED,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )
				return;
			console.log('OSP_SAMPLE_SELECTED: ['+e.portletId+', '+new Date()+']');
			
			var scienceApp = <portlet:namespace/>workbench.scienceApp();
			var portlet = <portlet:namespace/>workbench.getPortlet(e.portletId);
			var inputPort = scienceApp.inputPort( portlet.portName() );
			var portDataType = inputPort.dataType();
			
			var inputData = new OSP.InputData();
			inputData.portName( inputPort.name() );
			inputData.order( inputPort.order() );
			inputData.type( OSP.Enumeration.PathType.DLENTRY_ID );
			inputData.dirty( true );

			var sample = inputPort.sample();
			if( sample ){
				inputData.dlEntryId( sample.dlEntryId() );
				<portlet:namespace/>setJobInputData( portlet.portName(), inputData );
			}
			else{
				$.ajax({
					url: '<%=serveResourceURL.toString()%>',
					type: 'post',
					dataType: 'json',
					data:{
						<portlet:namespace/>command: 'GET_DATATYPE_SAMPLE',
						<portlet:namespace/>dataTypeName: portDataType.name,
						<portlet:namespace/>dataTypeVersion: portDataType.version
					},
					success: function( result ){
						console.log( 'Data Type Sample: ', result );
						inputData.dlEntryId( result.dlEntryId );
						<portlet:namespace/>setJobInputData( portlet.portName(), inputData );
					},
					error: function( data, e ){
						console.log( '[ERROR] Getting data type sample: '+ portDataType.name );
					}
				});
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_PORT_INFO,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_PORT_INFO: ['+e.portletId+', '+new Date()+']');
				
				var scienceApp = <portlet:namespace/>workbench.scienceApp();
				
				// set input ports' status
				var inputPorts = scienceApp.inputPorts();
				
				for( var portName in inputPorts ){
					var inputPort = inputPorts[portName];
					inputPort.status( OSP.Enumeration.DataStatus.CLEAN );
				}
				
				var data = {
						inputPorts: inputPorts,
						outputPorts: scienceApp.outputPorts(),
						logPorts: scienceApp.logPorts()
				};
							
				<portlet:namespace/>workbench.fire( OSP.Event.OSP_RESPONSE_PORT_INFO, e.portletId, data );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_APP_INFO,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_APP_INFO: ['+e.portletId+', '+new Date()+']');
				
				var data = {
					scienceAppId: <portlet:namespace/>workbench.scienceApp().id()
				};
				
				<portlet:namespace/>workbench.fire( OSP.Event.OSP_RESPONSE_APP_INFO, e.portletId, data);
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_MONITOR_INFO,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_MONITOR_INFO: ['+e.portletId+', '+new Date()+']');
				
				var data = {
					scienceAppId: <portlet:namespace/>workbench.scienceApp().id(),
					classId: <portlet:namespace/>workbench.classId(),
					customId: <portlet:namespace/>workbench.customId()
				};
			
				<portlet:namespace/>workbench.fire( OSP.Event.OSP_RESPONSE_MONITOR_INFO, e.portletId, data);
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_SIMULATION_UUID,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_SIMULATION_UUID: ['+e.portletId+', '+new Date()+']');
				
				<portlet:namespace/>handleRequestSimulationUuid( e.portletId );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_PORT_SELECTED,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_PORT_SELECTED: ['+e.portletId+', '+new Date()+']');
				//console.log( 'Selected Port Name: '+e.portName);
				var layout = <portlet:namespace/>workbench.layout();
				var prevPortlet = layout.getVisualPortlet(e.portName);
					
				if( e.portName === prevPortlet.portName() ){
					console.log(e.portName+' already visual. Do nothing.');
				}
				else{
						layout.switchDisplayColumnPortlet( 
						        <portlet:namespace/>workbench.scienceApp(),
								e.portName, 
								<portlet:namespace/>workbench.id(), 
								true, 
								'<%=LiferayWindowState.EXCLUSIVE%>', 
								<portlet:namespace/>handShakeCallback );
				}
			}
		}
);

Liferay.on(
		OSP.Event.OSP_RESPONSE_DATA,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']');
				var portName = e.data.portName;
				//console.log( 'portName: '+portName );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_DATA_CHANGED,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )
				return;
			console.log('OSP_DATA_CHANGED: ['+e.portletId+', '+new Date()+']');
			
			var portlet = <portlet:namespace/>workbench.getPortlet( e.portletId );
			var portName = portlet.portName();
			if( !portName ){
				console.log( "[Warning] Received OSP.Event.OSP_DATA_CHANGED from a portlet without port name: "+e.portletId);
				return;
			}
			
			// Check the event coming from input ports
			var scienceApp = <portlet:namespace/>workbench.scienceApp();
			var port = scienceApp.inputPort( portName ); 
			if( !port ){
				console.log('[Warning] Not an input port: '+portName);
				return;
			}
			
			
			var simulation = <portlet:namespace/>workbench.workingSimulation(); 
			var job = simulation.workingJob();
			//console.log('Data Changed WorkingJob: ', job);
			if( !job ){
				alert('No working job. At least one job should be selected...');
				return;
			}
			
			var data = new OSP.InputData( e.data );
			var inputData = new OSP.InputData();
			inputData.type( data.type() );
			inputData.portName( port.name() );
			inputData.order(port.order() );
			inputData.parent( data.parent() );
			inputData.name( data.name() );
			inputData.uri( data.uri() );
			inputData.id( data.id() );
			inputData.relative( true );
			inputData.dirty( true );
			inputData.context( data.context() );
			
			job.inputData( portName, inputData );
			console.log( 'OSP_DATA_CHANGED:', e.data);
		}
);

Liferay.on(
		OSP.Event.OSP_CREATE_SIMULATION,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_CREATE_SIMULATION: ['+e.portletId+', '+new Date()+']');
				
				var workingSimulation = <portlet:namespace/>workbench.workingSimulation();
				if( !workingSimulation ){
					<portlet:namespace/>newSimulation();
				}
				else{
					if( workingSimulation.checkDirty() === OSP.Enumeration.DataStatus.DIRTY ){
						$('#<portlet:namespace/>confirmDialog').dialog({
							resizable: false,
							height: "auto",
							title:'Make sure',
							width: 400,
							modal: true,
							buttons: {
								'YES': function() {
										$( this ).dialog( "destroy" );
										<portlet:namespace/>saveSimulation( workingSimulation );
										<portlet:namespace/>newSimulation();
								},
								'NO': function() {
									$( this ).dialog( "destroy" );
								}
							}
						});
					}
					else{
						<portlet:namespace/>newSimulation();
					}
				}
			}
		}
);

Liferay.on(
		OSP.Event.OSP_SUBMIT_SIMULATION,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_SUBMIT_SIMULATION: ['+e.portletId+', '+new Date()+']');
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				
				var dirtyJobs = simulation.getDirtyJobs();
				if( dirtyJobs.length < 1 ){
					return;
				}

				if( simulation.runType() === 'Sequential' ){
					<portlet:namespace/>submitSimulation( simulation );
				}
				else{
					<portlet:namespace/>setNumberOfCores();
				}
			}
		}
);

Liferay.on(
		OSP.Event.OSP_SAVE_SIMULATION,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_SAVE_SIMULATION: ['+e.portletId+', '+new Date()+']');
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				if( simulation.checkDirty() )
					<portlet:namespace/>saveSimulation( simulation );
				else
					alert('No changes to be saved: '+simulation.title() );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_DELETE_SIMULATION,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_DELETE_SIMULATION: ['+e.portletId+', '+new Date()+']');
				var simulation = <portlet:namespace/>workbench.workingSimulation();

				<portlet:namespace/>deleteSimulation( simulation, e.portletId );
			}
		}
);


Liferay.on(
		OSP.Event.OSP_SIMULATION_SELECTED,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_SIMULATION_SELECTED: ['+e.portletId+', '+new Date()+']');
				var simulation = <portlet:namespace/>workbench.getSimulation( e.data.simulationUuid );
				if( !simulation ){
					<portlet:namespace/>loadSimulation( e.data.simulationUuid );
				}
				else{
					var prevSimulation = <portlet:namespace/>workbench.workingSimulation();
					if( prevSimulation.uuid() === simulation.uuid() )
						return;
					
					if( prevSimulation.checkDirty() ){
						$('#<portlet:namespace/>confirmDialog').dialog({
							resizable: false,
							height: "auto",
							title:'Make sure to save...',
							width: 400,
							modal: true,
							buttons: {
								'YES': function() {
										$( this ).dialog( "destroy" );
										<portlet:namespace/>saveSimulation( prevSimulation );
								},
								'NO': function() {
									$( this ).dialog( "destroy" );
								}
							}
						});
					}

					<portlet:namespace/>workbench.workingSimulation( simulation );
					
					Liferay.fire( 
							OSP.Event.OSP_REQUEST_WORKING_JOB_INFO,
							{
								portletId: '<%=portletDisplay.getId()%>',
								targetPortlet: 'BROADCAST',
								data: {
									simulationUuid: simulation.uuid()
								}
							}
					);
				}
			}
		}
);

Liferay.on(
		OSP.Event.OSP_CREATE_JOB,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )
				return;
			console.log('OSP_CREATE_JOB: ['+e.portletId+', '+new Date()+']');
			
			var simulation = <portlet:namespace/>workbench.workingSimulation();
			var scienceApp = <portlet:namespace/>workbench.scienceApp();
			if( !simulation )	return;
			if( !scienceApp )	return;
			
			console.log(scienceApp);
			var data = {
					<portlet:namespace/>command: 'CREATE_JOB',
					<portlet:namespace/>simulationUuid: simulation.uuid(),
					<portlet:namespace/>scienceAppVersion: scienceApp.version(),
					<portlet:namespace/>scienceAppName: scienceApp.name()
			};
			
			$.ajax({
				type: 'POST',
				url: '<%=serveResourceURL.toString()%>', 
				async : false,
				data  : data,
				dataType : 'json',
				success: function(jsonJob ) {
					//console.log( 'new Job: ', jsonJob );
					var job = simulation.newJob(jsonJob);
					simulation.addJob( job );
					simulation.workingJob( job );
					
					var eventData = {
							portletId: <portlet:namespace/>workbench.id(),
							targetPortlet: 'BROADCAST',
							data: {
								simulationUuid: simulation.uuid(),
								jobUuid: job.uuid()
							}
					};
					
					Liferay.fire( OSP.Event.OSP_REFRESH_JOBS, eventData );
					Liferay.fire( OSP.Event.OSP_INITIALIZE, {} );
				},
				error:function(data,e){
					console.log(data);
					console.log('AJAX ERROR-->'+e);
				}
			});

		}
);

Liferay.on(
		OSP.Event.OSP_SUBMIT_JOB,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_SUBMIT_JOB: ['+e.portletId+', '+new Date()+']');
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				var jobUuid = e.data.jobUuid;
				var job = simulation.getJob( jobUuid );
				if( !job || !job.dirty() ){
					return;
				}
				
				if( scienceApp.runType() === 'Sequential' ){
					<portlet:namespace/>submitJob( job );
				}
				else{
					<portlet:namespace/>setNumberOfCores( job );
				}
				
				
			}
		}
);


Liferay.on(
		OSP.Event.OSP_JOB_SELECTED,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_JOB_SELECTED: ['+e.portletId+', '+new Date()+']');
				var jobUuid = e.data.jobUuid;
				<portlet:namespace/>handleJobSelected( jobUuid );
				
				console.log('************ workbench:', <portlet:namespace/>workbench);
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_JOB_UUID,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_JOB_UUID: ['+e.portletId+', '+new Date()+']');
				
				<portlet:namespace/>handleRequestJobUuid( e.portletId );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_SHOW_JOB_STATUS,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_SHOW_JOB_STATUS: ['+e.portletId+', '+new Date()+']');
				
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				var layout = <portlet:namespace/>workbench.layout();
				layout.switchDisplayColumnPortlet( 
                        <portlet:namespace/>workbench.scienceApp(),
						'_DOWNLOAD_', 
						<portlet:namespace/>workbench.id(), 
						true, 
						'<%=LiferayWindowState.EXCLUSIVE%>', 
						<portlet:namespace/>handShakeCallback
				);
			}
		}
);

Liferay.on(
		OSP.Event.OSP_DELETE_JOB,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_DELETE_JOB: ['+e.portletId+', '+new Date()+']');
				
				<portlet:namespace/>deleteJob( e.data.simulationUuid, e.data.jobUuid, e.portletId );
				
			}
		}
);

Liferay.on(
        OSP.Event.OSP_JOB_STATUS_CHANGED,
        function( e ){
            if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
                console.log('OSP_JOB_STATUS_CHANGED: ['+e.portletId+', '+new Date()+']', e.data);
                var jobUuid = e.data.jobUuid;
                var status = e.data.jobStatus;

                var simulation = <portlet:namespace/>workbench.workingSimulation();
                var job = simulation.getJob( jobUuid );
                job.status( OSP.Util.convertJobStatus(Number(status)) );

                var workingJob = simulation.workingJob();
                if( workingJob.uuid() !== jobUuid ){
                    return;
                }

                <portlet:namespace/>noticeJobStatusChanged( workingJob.uuid() );
                
                var scienceApp = <portlet:namespace/>workbench.scienceApp();
                var logPorts = scienceApp.logPorts();
                var outputPorts = scienceApp.outputPorts();

                switch( status ){
                    case '1701011':
                        for( var portName in outputPorts ){
                            var port = outputPorts[portName];
                            var layout = <portlet:namespace/>workbench.layout();
                            var portlet = layout.getPortlet( portName );

                            var inputData = new OSP.InputData( OSP.Util.toJSON(port.outputData()) );
                            var parentPath = OSP.Util.mergePath( simulation.getJobOutputFolder(workingJob), inputData.parent() );
            
                            inputData.parent( parentPath );
                            inputData.relative(true);

                            var eventData = {
                                    portletId: <portlet:namespace/>workbench.id(),
                                    targetPortlet: portlet.instanceId(),
                                    data: OSP.Util.toJSON(inputData)
                            };

                            Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
                        }
                    case '1701006':
                    case '1701012':
                        for( var portName in logPorts ){
                            var port = logPorts[portName];
                            var layout = <portlet:namespace/>workbench.layout();
                            var portlet = layout.getPortlet( portName );

                            var eventData = {
                                             portletId: <portlet:namespace/>workbench.id(),
                                             targetPortlet: portlet.instanceId(),
                                             data: OSP.Util.toJSON(port.outputData())
                            };

                            Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
                        }
                        
                        break;
                    default:
                        // Do nothing
                }
            }
        }
);

Liferay.on(
           OSP.Event.OSP_REQUEST_PATH,
           function( e ){
               if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
                   console.log('OSP_REQUEST_PATH: ['+e.portletId+', '+new Date()+']');
                   <portlet:namespace/>handleRequestPath( e.portletId );
               }
           }
);

Liferay.on(
           OSP.Event.OSP_REQUEST_OUTPUT_PATH,
           function( e ){
               if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
                   console.log('OSP_REQUEST_OUTPUT_PATH: ['+e.portletId+', '+new Date()+']');
                   <portlet:namespace/>handleRequestOutputPath( e.portletId );
               }
           }
);

Liferay.on(
		OSP.Event.OSP_UPLOAD_FILE,
		function( e ){
			console.log('OSP_UPLOAD_FILE: ['+e.portletId+', '+new Date()+']');
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_UPLOAD_FILE: ['+e.portletId+', '+new Date()+']');
				<portlet:namespace/>uploadProcess.portletId = e.portletId;
				<portlet:namespace/>uploadProcess.targetFolder = e.data.targetFolder;
				console.log( 'Upload Process: ', <portlet:namespace/>uploadProcess );
				$('#<portlet:namespace/>selectLocalFile').click();
			}
		}
);


Liferay.on(
		OSP.Event.OSP_SAVEAS_FILE,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_SAVEAS_FILE: ['+e.portletId+', '+new Date()+']');
				
				var inputData = new OSP.InputData( e.data );
				console.log( 'Save Context: ', inputData );
				var filePath = OSP.Util.mergePath( inputData.parent(), inputData.name() );
				console.log( 'SaveAs filePath: '+filePath );
				/*
				$.ajax({
					url: '<%=serveResourceURL.toString()%>',
					type: 'POST',
					dataType: 'json',
					data: {
						<portlet:namespace/>command: 'SAVE_AS',
						<portlet:namespace/>filePath: filePath,
						<portlet:namespace/>content: inputData.context()
					},
					success: function( result ){
						alert('Saved');
					},
					error: function( data, e ){
						
					}
				});
				*/
			}
		}
);


/***********************************************************************
 * Click events and binding functions 
 ***********************************************************************/


/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>defaultSimulationTitle(){
	var now = new Date();
	var scienceApp = <portlet:namespace/>workbench.scienceApp();
	return scienceApp.name()+'-'+scienceApp.version();
}
 
function <portlet:namespace/>createSimulation( title ){
	var now = new Date();
	var scienceApp = <portlet:namespace/>workbench.scienceApp();
	if( !title )
		title = newSimulation.getDefaultTitle( scienceApp.name(), now );
	
	var data = {
			<portlet:namespace/>command: 'CREATE_SIMULATION',
			<portlet:namespace/>scienceAppId: scienceApp.id(),
			<portlet:namespace/>scienceAppName: scienceApp.name(),
			<portlet:namespace/>scienceAppVersion: scienceApp.version(),
			<portlet:namespace/>srcClassCode: <portlet:namespace/>workbench.classId(),
			<portlet:namespace/>srcClassId: <portlet:namespace/>workbench.customId(),
			<portlet:namespace/>title: Liferay.Util.escapeHTML(title)
	};
	
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'json',
		success: function(jsonSimulation) {
			var simulation = new OSP.Simulation(jsonSimulation);
			simulation.runType( scienceApp.runType() );
			<portlet:namespace/>workbench.addSimulation( simulation );
			<portlet:namespace/>workbench.workingSimulation( simulation );
			<portlet:namespace/>workbench.print( 'After creating simulation');
			
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: 'BROADCAST',
					data: {
						simulationUuid: simulation.uuid()
					}
			};
			Liferay.fire( OSP.Event.OSP_REFRESH_SIMULATIONS, eventData );
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>newSimulation(){
	var tempSimulationTitle = <portlet:namespace/>defaultSimulationTitle();
	$('#<portlet:namespace/>newSimulationTitle').val( tempSimulationTitle );
	$('#<portlet:namespace/>newSimulationDialog').dialog({
		resizable: false,
		height: "auto",
		title:'Enter new simulation title...',
		width: 400,
		modal: true,
		buttons: {
			'OK': function() {
				var newSimulationTitle = $('#<portlet:namespace/>newSimulationTitle').val();
				if( newSimulationTitle.trim() ){
					$( this ).dialog( "close" );
					<portlet:namespace/>createSimulation(newSimulationTitle );
				}
				else{
					alert('New simulation title should not be empty.');
				}
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		}
	});
}

function <portlet:namespace/>loadSimulation( simulationUuid ){
	var data = {
			<portlet:namespace/>command: 'LOAD_SIMULATION',
			<portlet:namespace/>simulationUuid: simulationUuid
	};
	
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'json',
		success: function(jsonSimulation) {
			//console.log( 'Loaded Simulation: ', jsonSimulation );
			var simulation = new OSP.Simulation( jsonSimulation );
			var scienceApp = <portlet:namespace/>workbench.scienceApp();
			simulation.runType( scienceApp.runType() );
			<portlet:namespace/>workbench.addSimulation( simulation );
			<portlet:namespace/>workbench.workingSimulation( simulation );
			<portlet:namespace/>workbench.print( 'After loading simulation');
			
			Liferay.fire( 
					OSP.Event.OSP_REQUEST_WORKING_JOB_INFO, 
					{
						portletId: '<%=portletDisplay.getId()%>',
						targetPortlet: 'BROADCAST',
						data: {
							simulationUuid: simulation.uuid()
						}
					}
			);
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>saveSimulation( simulation ){
	var data = {
			<portlet:namespace/>command: 'SAVE_SIMULATION',
			<portlet:namespace/>srcClassCode: <portlet:namespace/>workbench.classId(),
			<portlet:namespace/>srcClassId: <portlet:namespace/>workbench.customId(),
			<portlet:namespace/>simulation: JSON.stringify(simulation.toDTO())
	};
	
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'text',
		success: function(result) {
			console.log( 'saveSimulation: '+result );
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>deleteSimulation( simulation, targetPortlet ){
	var data = {
			<portlet:namespace/>command: 'DELETE_SIMULATION',
			<portlet:namespace/>simulationUuid: simulation.uuid()
	};
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'json',
		success: function(result ) {
			console.log( 'deleteSimulation: ', result );
			var simulationUuid = result.simulationUuid;
			
			<portlet:namespace/>workbench.removeSimulation( simulationUuid );
			
			var eventData = {
					portletId : <portlet:namespace/>workbench.id(),
					targetPortlet: targetPortlet,
					data:{
						simulationUuid: result.simulationUuid
					}
			};
			
			Liferay.fire( OSP.Event.OSP_REFRESH_SIMULATIONS, eventData );
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>submitJob( job ){
	var ajaxData = {
			<portlet:namespace/>command: 'SUBMIT_JOB',
			<portlet:namespace/>jobUuid: job.uuid(),
			<portlet:namespace/>inputs: JSON.stringify( job.inputs() )
	};
	
	$.ajax({
		url : '<%=serveResourceURL.toString()%>',
		type: 'post',
		dataType: 'text',
		data : ajaxData,
		success : function(status){
			console.log( 'submitSuccess: ', status);
			job.status( status );
			job.dirty(false);
			job.isSubmit( true );
			
			var simulation = <portlet:namespace/>workbench.workingSimulation();
			var eventData = {
          portletId: <portlet:namespace/>workbench.id(),
          targetPortlet: 'BROADCAST',
          data: {
            simulationUuid: simulation.uuid(),
            jobUuid: job.uuid()
          }
      };
      Liferay.fire( OSP.Event.OSP_REFRESH_JOBS, eventData );
      
		},
		error : function( data, e ){
			console.log('[ERROR] submit job failed: ', data);
		}
	});
}

function <portlet:namespace/>submitSimulation( simulation ){
	var scienceApp = <portlet:namespace/>workbench.scienceApp();
	var simulationCreateTime = simulation.createTime();
	console.log( 'Simulation create Time: ', simulationCreateTime );
	//console.log('Dirty Jobs: ', dirtyJobs);
	
	var jobsToSubmit = [];
	var dirtyJobs = simulation.getDirtyJobs();
	for( var index in dirtyJobs ){
		var job = dirtyJobs[index];
		console.log( 'dirtyJob: '+index, job);
		var inputPortNames = scienceApp.getInputPortNames();
		console.log( 'inputPortNames: ', inputPortNames );
		
		var proliferatedJobs = job.proliferate( inputPortNames );
		//console.log( 'proliferated Jobs: ', proliferatedJobs );
		for( var jobIndex in proliferatedJobs ){
			var proliferatedJob = proliferatedJobs[jobIndex];
			
			if( !proliferatedJob.ncores() && simulation.ncores() ){
				proliferatedJob.ncores( simulation.ncores() );
			}
			jobsToSubmit.push( proliferatedJob );
		}
		//jobsToSumbit = jobsToSubmit.concat( proliferatedJobs );
		//console.log( 'Jobs To Submit: ', jobsToSubmit);
	}
	
	console.log( 'Jobs To Submit: ', jobsToSubmit);
	
	$.ajax({
		url: '<%=serveResourceURL.toString()%>',
		type: 'POST',
		dataType: 'json',
		data: {
			<portlet:namespace/>command: 'SUBMIT_JOBS',
			<portlet:namespace/>simulationUuid: simulation.uuid(),
			<portlet:namespace/>simulationTime: simulationCreateTime,
			<portlet:namespace/>scienceAppName: scienceApp.name(),
			<portlet:namespace/>scienceAppVersion: scienceApp.version(),
			<portlet:namespace/>ncores: simulation.ncores(),
			<portlet:namespace/>jobs: JSON.stringify( jobsToSubmit )
		},
		success: function( submittedJobs ){
			for( var index in submittedJobs ){
				var jobUuids = submittedJobs[index];
	            console.log( 'jobUuids ****************');
	            console.log( jobUuids );
	            console.log( '**************** jobUuids');
				var job = simulation.getJob( jobUuids.tempUuid );
				if( !job )  continue;
				
				job.uuid( jobUuids.uuid );
				job.isSubmit( true );
				job.dirty(false);
			}
			
			var eventData = {
					portletId: <portlet:namespace/>workbench.id(),
					targetPortlet: 'BROADCAST',
					data:{
						simulationUuid: simulation.uuid()
					}
			};
			
			Liferay.fire(OSP.Event.OSP_REFRESH_JOBS, eventData);
			//Liferay.fire(OSP.Event.OSP_REFRESH_OUTPUT_VIEW, eventData);
		},
		error: function( data, e ){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>loadJobData( job ){
    var scienceApp = <portlet:namespace/>workbench.scienceApp();
    var inputPorts = scienceApp.inputPorts();
    var outputPorts = scienceApp.outputPorts();
    var logPorts = scienceApp.logPorts();

    <portlet:namespace/>resetInputPortlets( job, inputPorts);
    <portlet:namespace/>resetLogOutputPortlets(job, logPorts, outputPorts );
}

function <portlet:namespace/>resetInputPortlets( job, inputPorts ){
	var layout = <portlet:namespace/>workbench.layout();

	for( var portName in inputPorts ){
        var portlet = layout.getPortlet( portName );
        var inputData = job.inputData( portName );
        var eventData = {
                         portletId: <portlet:namespace/>workbench.id(),
                         targetPortlet: portlet.instanceId()
                     };
        if( !inputData ){
            eventData.data = {};
            Liferay.fire( OSP.Event.OSP_INITIALIZE, eventData );
        }
        else{
            eventData.data = OSP.Util.toJSON( inputData );
            Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
        }
    }
};

function <portlet:namespace/>resetLogOutputPortlets (job, logPorts, outputPorts ){
    var layout = <portlet:namespace/>workbench.layout();

    var resetPortlets = function( ports ){
        for( var portName in ports ){
            var portlet = layout.getPortlet( portName );
            var port = ports[portName];
            
            var portData = port.outputData();
            var inputData = new OSP.InputData();
            inputData.type( portData.type() );
            
            var simulation = <portlet:namespace/>workbench.workingSimulation(); 
            var parentPath = OSP.Util.mergePath( simulation.getJobOutputFolder(job), portData.parent() );
            inputData.parent( parentPath );
            inputData.name( portData.name() );
            inputData.relative( true );

            var eventData = {
                             portletId: <portlet:namespace/>workbench.id(),
                             targetPortlet: portlet.instanceId(),
                             data: OSP.Util.toJSON( inputData )
                             };
            Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
        }
    };
    
    switch( job.status() ){
    	case 'SUCCESS':
    	    resetPortlets( outputPorts );
    	case 'RUNNING':
    	case 'FAILED':
    		resetPortlets( logPorts );
    }
};

function <portlet:namespace/>saveInputs( 
						scienceAppName,
						scienceAppVersion,
						simulationTime,
						jobNumber,
						portName,
						content ){
	var fileInfo;
	$.ajax({
		url: '<%=serveResourceURL.toString()%>',
		type:'post',
		dataType: 'json',
		async: false,
		data:{
			<portlet:namespace/>command: 'SAVE_AS_INPUT',
			<portlet:namespace/>scienceAppName: scienceAppName,
			<portlet:namespace/>scienceAppVersion: scienceAppVersion,
			<portlet:namespace/>simulationTime: simulationTime.toJSON().replace(/:/ig, '-'),
			<portlet:namespace/>jobNumber: jobNumber,
			<portlet:namespace/>portName: portName,
			<portlet:namespace/>content: content
		},
		success: function( result ){
			fileInfo = result;
		},
		error: function( data, e ){
			console.log('[ERROR] Ajax to uploadFile');
		}
	});

	//console.log('Saved Input File Info', fileInfo );
	return fileInfo;
}

function <portlet:namespace/>deleteJob( simulationUuid, jobUuid, targetPortlet ){
	var data = {
			<portlet:namespace/>command: 'DELETE_JOB',
			<portlet:namespace/>simulationUuid: simulationUuid,
			<portlet:namespace/>jobUuid: jobUuid
	};
	
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'json',
		success: function(result ) {
			console.log( 'deleteJob: ', result );
			var eventData = {
					portletId: <portlet:namespace/>workbench.id(),
					targetPortlet: targetPortlet,
					data: {
						simulationUuid: result.simulationUuid,
						jobUuid: result.jobUuid
					}
			};
			Liferay.fire( OSP.Event.OSP_REFRESH_JOBS, eventData );
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>handleRequestSimulationUuid( targetPortlet ){
  setTimeout( 
		function(){
		  simulation = <portlet:namespace/>workbench.workingSimulation();
			console.log(simulation);
			if( !simulation ){
				<portlet:namespace/>handleRequestSimulationUuid(targetPortlet);
			}
			else{
				var eventData = {
				    portletId: "<%=portletDisplay.getId()%>",
				    targetPortlet: targetPortlet,
				    data:{
  						simulationUuid: simulation.uuid(),
				    }
				};
				Liferay.fire(OSP.Event.OSP_RESPONSE_SIMULATION_UUID, eventData);
			}
		}, 
		10
	);
}

function <portlet:namespace/>handleJobSelected(jobUuid ){
	setTimeout(
			function(){
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				if( ! simulation )
					<portlet:namespace/>handleJobSelected(jobUuid );
				else{
					var job = simulation.getJob( jobUuid );
					//console.log('Job loaded: ', job );
					var eventData = {
							portletId: '<%=portletDisplay.getId()%>',
							targetPortlet: 'BROADCAST',
					};
					
					if( !job ){
						$.ajax({
							url : '<%=serveResourceURL.toString()%>',
							type : 'POST',
							dataType: 'json',
							data : {
								<portlet:namespace/>command: 'LOAD_JOB',
								<portlet:namespace/>simulationUuid: simulation.uuid(),
								<portlet:namespace/>jobUuid: jobUuid
							},
							success : function( jsonJob ) {
								console.log( 'jsonJob', jsonJob);
								
								var job = simulation.newJob( jsonJob );
								if( job.isSubmit() )
									job.dirty( false );
								simulation.addJob( job );
								simulation.workingJob( job );
								<portlet:namespace/>loadJobData( job );

								eventData.data = {
										simulationUuid: simulation.uuid(),
										jobUuid: job.uuid()
								};
								Liferay.fire(OSP.Event.OSP_REFRESH_JOB_STATUS, eventData);
								//Liferay.fire(OSP.Event.OSP_INITIALIZE, {});
							},
							error: function( data, e ){
								console.log(data);
								console.log('AJAX ERROR-->'+e);
							}
						});
					}
					else{
						simulation.workingJob( job );
						//console.log('New Working Job: '+job.uuid());
						<portlet:namespace/>loadJobData( job );
						
						eventData.data = {
								simulationUuid: simulation.uuid(),
								jobUuid: job.uuid()
						};
						Liferay.fire(OSP.Event.OSP_REFRESH_JOB_STATUS, eventData);
						//Liferay.fire(OSP.Event.OSP_INITIALIZE, {});
					}
				}
			},
			10
	);

	<portlet:namespace/>workbench.print('After selecting job');
}

function <portlet:namespace/>handleRequestPath( targetPortlet ){
	setTimeout(
			function(){
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				if( !simulation ){
					<portlet:namespace/>handleRequestPath( targetPortlet );
				}
				else{
					var workingJob = simulation.workingJob();
					if( !workingJob ){
						<portlet:namespace/>handleRequestPath( targetPortlet );
					}
					else{
						var portlet = <portlet:namespace/>workbench.getPortlet( targetPortlet );
						var scienceApp = <portlet:namespace/>workbench.scienceApp();
						var inputPort = scienceApp.inputPort( portlet.portName() );
						
						var inputData = new OSP.InputData();
						inputData.portName( inputPort.name() );
						inputData.order( inputPort.order() );
						inputData.type(OSP.Enumeration.PathType.FOLDER);
						inputData.parent( '' );
						inputData.name('');
						inputData.relative(true);
						
						<portlet:namespace/>setJobInputData( portlet.portName(), inputData )
						
						<portlet:namespace/>workbench.fire( 
								OSP.Event.OSP_LOAD_DATA, 
								targetPortlet, 
								OSP.Util.toJSON(inputData) );
					}
				}
			},
			10
	);
}

function <portlet:namespace/>handleRequestOutputPath( targetPortlet ){
	setTimeout(
			function(){
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				if( !simulation ){
					<portlet:namespace/>handleRequestOutputPath( targetPortlet );
				}
				else{
					var workingJob = simulation.workingJob();
					if( !workingJob ){
						<portlet:namespace/>handleRequestOutputPath( targetPortlet );
					}
					else{
					    console.log( 'Working Job Status: '+ workingJob.status());
						switch( workingJob.status() ){
							case 'INITIALIZE_FAILED':
							case 'INITIALIZED':
							case 'SUBMISSION_FAILED':
							case 'QUEUED':
								// To do: Show Queue Status 
							case 'SUSPEND_REQUESTED':
							case 'SUSPENDED':
								// To do: Show Suspended Status
							case 'CANCEL_REQUESTED':
							case 'CANCELED':
								// To do: Show Cancel Status
								break;
							case 'SUCCESS':
								var portlet = <portlet:namespace/>workbench.getPortlet( targetPortlet );
								var scienceApp = <portlet:namespace/>workbench.scienceApp();
								var outputPorts = scienceApp.outputPorts();
								if( !outputPorts ){
									// To do: display Download portlet
									return;
								}

								var outputPort = scienceApp.getPort( portlet.portName() );
								var portData = outputPort.outputData();	

								var parentPath =OSP.Util.mergePath( simulation.getJobOutputFolder(workingJob), portData.parent() );
								
								var inputData = new OSP.InputData();
								inputData.type(portData.type());
								inputData.parent( parentPath );
								inputData.name(portData.name());
								inputData.relative(true);
								
								<portlet:namespace/>workbench.fire( 
										OSP.Event.OSP_LOAD_DATA, 
										targetPortlet, 
										OSP.Util.toJSON(inputData) );
								break;
                            case 'RUNNING':
							case 'FAILED':
								// To do: Show Failed Status
								break;
							default:
								console.log('Unknown-status: '+workingJob.status());
						}
					}
				}
			},
			10
	);
}

function <portlet:namespace/>handleRequestJobUuid( targetPortlet ){
	setTimeout(
			function(){
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				if( !simulation ){
					<portlet:namespace/>handleRequestJobUuid( targetPortlet );
				}
				else{
					var workingJob = simulation.workingJob();
					if( !workingJob ){
						<portlet:namespace/>handleRequestJobUuid( targetPortlet );
					}
					else{
						var eventData = {
								portletId: '<%=portletDisplay.getId()%>',
								targetPortlet: targetPortlet,
								data:{
									simulationUuid: simulation.uuid(),
									jobUuid: workingJob.uuid()
								}
						};
						
						Liferay.fire( OSP.Event.OSP_RESPONSE_JOB_UUID, eventData );
					}
				}
			},
			10
	);
}

function <portlet:namespace/>setJobInputData( portName, inputData ){
	setTimeout(
			function(){
				var simulation = <portlet:namespace/>workbench.workingSimulation();
				if( !simulation )	<portlet:namespace/>setJobInputData( portName, inputData );
				else{
					var job = simulation.workingJob();
					if( !job )	<portlet:namespace/>setJobInputData( portName, inputData );
					else{
						console.log( ' +++ setJobInputData: '+portName, inputData );
						job.inputData( portName, inputData );
					}
				}
			},
			10
	);
}

function <portlet:namespace/>setNumberOfCores( job ){
	$('#<portlet:namespace/>getCoresDialog').dialog({
		resizable: false,
		height: "auto",
		title:'Enter number of cores',
		width: 400,
		modal: true,
		buttons: {
			OK: function() {
				var numberOfCores = Number($('#<portlet:namespace/>numberOfCores').val());
				if( ! isNaN( numberOfCores ) ){
					$( this ).dialog( 'destroy' );
					var simulation = <portlet:namespace/>workbench.workingSimulation();
					if( !job ){
						simulation.ncores( numberOfCores );
						<portlet:namespace/>submitSimulation( simulation );
					}
					else{
						job.ncores( numberOfCores );
						<portlet:namespace/>submitJob( job );
					}
				}
				else{
					alert('Number of cores should not be empty.');
				}
			},
			Cancel: function() {
				$( this ).dialog( 'destroy' );
			}
		}
	});
}

$('#<portlet:namespace/>selectLocalFile').bind(
		'change', 
		function(event){
			var uploadFile = $(this)[0].files[0];
			//console.log( 'UploadFile: '+uploadFile);
			
			var uploadFileName = $(this).val();
			var slashIndex = uploadFileName.lastIndexOf('\\');
			if( slashIndex < 0 )
				slashIndex = uploadFileName.lastIndexOf('/'); 
			uploadFileName = uploadFileName.slice(slashIndex+1);
			// $('#<portlet:namespace/>fileName').val( uploadFileName);
			
			// check that file name is duplicated using AJAX.
			var duplicated;
			var target = <portlet:namespace/>uploadProcess.targetFolder;
			target = OSP.Util.mergePath( target, uploadFileName );
			
			$.ajax({
				url: '<%=serveResourceURL.toString()%>',
				type: 'POST',
				dataType: 'json',
				data:{
					<portlet:namespace/>command: "CHECK_DUPLICATED",
					<portlet:namespace/>action: 'input',
					<portlet:namespace/>target: target
				},
				success:function(result){
					duplicated = result.duplicated;
					if( duplicated ){
						$('#<portlet:namespace/>uploadFileName').val(uploadFileName);
						
						var $confirmDialog = $('#<portlet:namespace/>confirmOverlapDialog');
						$confirmDialog.dialog(
							{
								resizable: false,
								height: "auto",
								width: 400,
								modal: true,
								buttons: {
									'OK': function() {
										<portlet:namespace/>submitUpload( 
												uploadFile, 
												<portlet:namespace/>uploadProcess.targetFolder,
												$('#<portlet:namespace/>uploadFileName').val()
										);
										$( this ).dialog( "destroy" );
									},
									Cancel: function() {
										$( this ).dialog( "destroy" );
									}
								}
							}
						);
					}
					else{
						<portlet:namespace/>submitUpload(
								uploadFile, 
								<portlet:namespace/>uploadProcess.targetFolder,
								uploadFileName
						);
					}
				},
				error: function(){
					
				}
			});
		}
);

function <portlet:namespace/>submitUpload( uploadFile, targetFolder, fileName ){
	//var uploadFolder = OSP.Util.mergePath( <portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent());
		
	var formData = new FormData();
	formData.append('<portlet:namespace/>uploadFile', uploadFile);
	formData.append('<portlet:namespace/>command', 'UPLOAD');
	formData.append('<portlet:namespace/>targetFolder', targetFolder);
	formData.append('<portlet:namespace/>fileName', fileName);

	$.ajax({
		url : '<%=serveResourceURL.toString()%>',
		type : 'POST',
		data : formData,
		processData: false,  // tell jQuery not to process the data
		contentType: false,  // tell jQuery not to set contentType
		success : function(data) {
			alert( 'Upload Succeded: ', JSON.stringify(data));
			var eventData = {
					portletId: <portlet:namespace/>workbench.id(),
					targetPortlet: <portlet:namespace/>uploadProcess.portletId
			};
			Liferay.fire( OSP.Event.OSP_REFRESH, eventData );
		}
	});
}

function <portlet:namespace/>noticeJobStatusChanged( jobUuid ){
    var layout = <portlet:namespace/>workbench.layout();
    var simulation = <portlet:namespace/>workbench.workingSimulation();
    var data = {
                simulationUuid: simulation.uuid(),
                jobUuid: jobUuid
    };
    
    var statusPortlet = layout.getPortlet( '_DOWNLOAD_' );
    var eventData = {
                     portletId: <portlet:namespace/>workbench.id(),
                     targetPortlet: statusPortlet.instanceId(),
                     data: data
    };
    Liferay.fire( OSP.Event.OSP_REFRESH_JOB_STATUS, eventData );
}
</script>