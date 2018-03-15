<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="org.kisti.edison.bestsimulation.model.SimulationJob"%>
<%@page import="org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil"%>
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
	SimulationJob job = null;
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
		style="height: 70vh;"
	></div>
	<div
		id="<portlet:namespace/>hiddenSection"
		style="display: none;"
	>
		<div id="<portlet:namespace/>fileExplorer" title="Select a file" >
            <div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
            <div>
                <input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
                <input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
            </div>
        </div>
		
		<div id="<portlet:namespace/>confirmDialog">
			<p>Simulation is not saved. Would you like to save current simulation?</p>
		</div>
		<div id="<portlet:namespace/>confirmCreateJobDialog">
			<p>Submitted job data changed.<br>
			       Would you like to copy the job with changed data?</p>
		</div>
		<div id="<portlet:namespace/>confirmOverlapDialog">
			<input
				type="text"
				id="<portlet:namespace/>uploadFileName"
			/><br />
			<p>File already exists.Change file name or just click 'OK' button to overlap.</p>
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

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>workbench = new OSP.Workbench( '<portlet:namespace/>');
<portlet:namespace/>workbench.id( '<%=portletDisplay.getId()%>' );

var <portlet:namespace/>uploadProcess = {};
var <portlet:namespace/>dialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = 'FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_'+
+ "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);

$('#<portlet:namespace/>fileExplorer').dialog({
	autoOpen: false,
	resizable: false,
	height: 600,
	width: 450,
	modal: true
});				

/***********************************************************************
 * Initailization section and handling Liferay events
 ***********************************************************************/
Liferay.on('portletReady', function( e ){
	if( <portlet:namespace/>workbench.id() === e.portletId ){
		<portlet:namespace/>workbench.layout( new OSP.Layout(JSON.parse('<%=workbenchLayout.toString()%>')));
		<portlet:namespace/>workbench.type ( '<%=workbenchType%>' );
		<portlet:namespace/>workbench.classId( <%=classId%> );
		<portlet:namespace/>workbench.customId( <%=customId%> );
		<portlet:namespace/>workbench.jobStatusPortlet('edisonworkbenchjobstatusandresult_WAR_edisonsimulationportlet');
		
		switch( <portlet:namespace/>workbench.type() ){
			case OSP.Enumeration.WorkbenchType.SIMULATION_WITH_APP:
			case OSP.Enumeration.WorkbenchType.SIMULATION_RERUN:
				var scienceApp = new OSP.ScienceApp();
				scienceApp.id( <%=scienceApp.getScienceAppId()%> );
				scienceApp.name( '<%=scienceApp.getName()%>' );
				scienceApp.version( '<%=scienceApp.getVersion()%>' );
				scienceApp.runType( '<%=scienceApp.getRunType()%>' );
				
				if( '<%=inputPorts%>' ) 
					scienceApp.deserializeInputPorts( JSON.parse('<%=inputPorts%>') );
				if( '<%=logPorts%>' )
					scienceApp.deserializeLogPorts( JSON.parse('<%=logPorts%>') );
				if( '<%=outputPorts%>' )
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
				$('#<portlet:namespace/>canvas').html( result );
				
				<portlet:namespace/>workbench.loadPortlets('<%=LiferayWindowState.EXCLUSIVE%>');
			}
		});
	}
	else{
		console.log( 'Get ready event form others: '+e.portletId);
	}
});

/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
		OSP.Event.OSP_REGISTER_EVENTS,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REGISTER_EVENTS: ['+e.portletId+', '+new Date()+']', e.portletType );
				<portlet:namespace/>workbench.handleRegisterEvents( e.portletId, e.portletType, e.data );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_SAMPLE_CONTENT,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_SAMPLE_CONTENT: ['+e.portletId+', '+new Date()+']');
				<portlet:namespace/>workbench.handleRequestSampleContent( e.portletId, '<%=serveResourceURL%>');
			}
		}
);

Liferay.on(
		OSP.Event.OSP_SAMPLE_SELECTED,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )
				return;
			console.log('OSP_SAMPLE_SELECTED: ['+e.portletId+', '+new Date()+']');
			<portlet:namespace/>workbench.handleSampleSelected( e.portletId, '<%=serveResourceURL%>');
		}
);

Liferay.on(
	OSP.Event.OSP_READ_STRUCTURED_DATA_FILE,
	function( e ){
		if( <portlet:namespace/>workbench.id() !== e.targetPortlet ) return;
			console.log('OSP_READ_STRUCTURED_DATA_FILE: ['+e.portletId+', '+new Date()+']', e);
			<portlet:namespace/>workbench.handleReadStructuredDataFile( 
			                                                    e.portletId, 
			                                                    new OSP.InputData( e.data ), 
			                                                    '<%=serveResourceURL%>');
	}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_FILE_URL,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_FILE_URL: ['+e.portletId+', '+new Date()+']', e.data);
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_PORT_INFO,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_PORT_INFO: ['+e.portletId+', '+new Date()+']');
				
				<portlet:namespace/>workbench.handleRequestPortInfo( e.portletId, '<%=serveResourceURL%>' );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_APP_INFO,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_APP_INFO: ['+e.portletId+', '+new Date()+']');
				
				<portlet:namespace/>workbench.handleRequestAppInfo( e.portletId );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_MONITOR_INFO,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_MONITOR_INFO: ['+e.portletId+', '+new Date()+']');
				
				<portlet:namespace/>workbench.handleRequestMonitorInfo( e.portletId );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_SIMULATION_UUID,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_SIMULATION_UUID: ['+e.portletId+', '+new Date()+']');
				
				<portlet:namespace/>workbench.handleRequestSimulationUuid( e.portletId );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_PORT_SELECTED,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_PORT_SELECTED: ['+e.portletId+', '+new Date()+']');
				<portlet:namespace/>workbench.handlePortSelected( e.portName, e.portletInstanceId );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_RESPONSE_DATA,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']', e);
				var portName = e.data.portName;
			}
		}
);

Liferay.on(
		OSP.Event.OSP_DATA_CHANGED,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			console.log('OSP_DATA_CHANGED: ['+e.portletId+', '+new Date()+']', e.data);
			
			<portlet:namespace/>workbench.handleDataChanged(e.portletId, e.data, $('#<portlet:namespace/>confirmCreateJobDialog'));
		}
);

Liferay.on(
		OSP.Event.OSP_CREATE_SIMULATION,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_CREATE_SIMULATION: ['+e.portletId+', '+new Date()+']');
				
				<portlet:namespace/>workbench.handleCreateSimulation( 
				                                                      $('#<portlet:namespace/>newSimulationTitle'),
				                                                      $('#<portlet:namespace/>confirmDialog'),
				                                                      $('#<portlet:namespace/>newSimulationDialog'),
				                                                      '<%=serveResourceURL.toString()%>'
				                                                      );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_SUBMIT_SIMULATION,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_SUBMIT_SIMULATION: ['+e.portletId+', '+new Date()+']', e.data);
				
				<portlet:namespace/>workbench.handleSubmitSimulation(
				                                                     $('#<portlet:namespace/>numberOfCores'), 
				                                                     $('#<portlet:namespace/>getCoresDialog'),
				                                                     '<%=serveResourceURL.toString()%>');
			}
		}
);

Liferay.on(
		OSP.Event.OSP_SAVE_SIMULATION,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_SAVE_SIMULATION: ['+e.portletId+', '+new Date()+']');
				<portlet:namespace/>workbench.handleSaveSimulation('<%=serveResourceURL.toString()%>');
			}
		}
);

Liferay.on(
		OSP.Event.OSP_DELETE_SIMULATION,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet ) return;
			
			console.log('OSP_DELETE_SIMULATION: ['+e.portletId+', '+new Date()+']', e.data);
			<portlet:namespace/>workbench.handleDeleteSimulation(e.data.simulationUuid, '<%=serveResourceURL.toString()%>');
		}
);


Liferay.on(
		OSP.Event.OSP_SIMULATION_SELECTED,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			
			console.log('OSP_SIMULATION_SELECTED: ['+e.portletId+', '+new Date()+']');
				
			<portlet:namespace/>workbench.handleSimulationSelected(e.data.simulationUuid, $('#<portlet:namespace/>confirmDialog'), '<%=serveResourceURL.toString()%>');
		}
);

Liferay.on(
		OSP.Event.OSP_CREATE_JOB,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			
			console.log('OSP_CREATE_JOB: ['+e.portletId+', '+new Date()+']', e );
			
			<portlet:namespace/>workbench.handleCreateJob( '<%=serveResourceURL.toString()%>', e.data );
		}
);

Liferay.on(
		OSP.Event.OSP_SUBMIT_JOB,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			console.log('OSP_SUBMIT_JOB: ['+e.portletId+', '+new Date()+']');
			<portlet:namespace/>workbench.handleSubmitJob( 
			                                               e.data.jobUuid,
			                                               $('#<portlet:namespace/>numberOfCores'), 
			                                               $('#<portlet:namespace/>getCoresDialog'),
			                                               '<%=serveResourceURL.toString()%>' );
		}
);


Liferay.on(
		OSP.Event.OSP_JOB_SELECTED,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			
			console.log('OSP_JOB_SELECTED: ['+e.portletId+', '+new Date()+']');
			<portlet:namespace/>workbench.handleJobSelected( 
			                                               e.data.jobUuid,
			                                               '<%=serveResourceURL.toString()%>' );
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_JOB_UUID,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			console.log('OSP_REQUEST_JOB_UUID: ['+e.portletId+', '+new Date()+']');
			<portlet:namespace/>workbench.handleRequestJobUuid( e.portletId );
		}
);

Liferay.on(
		OSP.Event.OSP_SHOW_JOB_STATUS,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			console.log('OSP_SHOW_JOB_STATUS: ['+e.portletId+', '+new Date()+']');
			<portlet:namespace/>workbench.handleShowJobStatus();
		}
);

Liferay.on(
		OSP.Event.OSP_DELETE_JOB,
		function( e ){
			if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			console.log('OSP_DELETE_JOB: ['+e.portletId+', '+new Date()+']');
			<portlet:namespace/>workbench.handleDeleteJob( e.data.simulationUuid, e.data.jobUuid, '<%=serveResourceURL.toString()%>' );
		}
);

Liferay.on(
		OSP.Event.OSP_JOB_STATUS_CHANGED,
		function( e ){
		if( <portlet:namespace/>workbench.id() !== e.targetPortlet )	return;
			console.log('OSP_JOB_STATUS_CHANGED: ['+e.portletId+', '+new Date()+']', e.data);
			<portlet:namespace/>workbench.handleJobStatusChanged( e.data.jobUuid, e.data.jobStatus );
		}
);

Liferay.on(
           OSP.Event.OSP_REQUEST_PATH,
           function( e ){
               if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
                   console.log('OSP_REQUEST_PATH: ['+e.portletId+', '+new Date()+']');
                   <portlet:namespace/>workbench.handleRequestPath( e.portletId );
               }
           }
);

Liferay.on(
           OSP.Event.OSP_REQUEST_OUTPUT_PATH,
           function( e ){
               if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
                   console.log('OSP_REQUEST_OUTPUT_PATH: ['+e.portletId+', '+new Date()+']');
                   <portlet:namespace/>workbench.handleRequestOutputPath( e.portletId );
               }
           }
);

Liferay.on(
           OSP.Event.OSP_REQUEST_DATA_STRUCTURE,
           function( e ){
               if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
                   console.log('OSP_REQUEST_DATA_STRUCTURE: ['+e.portletId+', '+new Date()+']');
                   <portlet:namespace/>workbench.handleRequestDataStructure( e.portletId, '<%=serveResourceURL.toString()%>' );
               }
           }
);

Liferay.on(
		OSP.Event.OSP_UPLOAD_FILE,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_UPLOAD_FILE: ['+e.portletId+', '+new Date()+']');
				<portlet:namespace/>uploadProcess.portletId = e.portletId;
				<portlet:namespace/>uploadProcess.targetFolder = e.data.targetFolder;
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
				var filePath = OSP.Util.mergePath( inputData.parent(), inputData.name() );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_JOB_DELETED,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_JOB_DELETED: ['+e.portletId+', '+new Date()+']', e.data);
				
				<portlet:namespace/>workbench.handleJobDeleted( e.data.simulationUuid, e.data.jobUuid );
			}
		}
);
   
Liferay.on(
		OSP.Event.OSP_REQUEST_SPREAD_TO_PORT,
		function( e ){
			if( <portlet:namespace/>workbench.id() === e.targetPortlet ){
				console.log('OSP_REQUEST_SPREAD_TO_PORT: ['+e.portletId+', '+new Date()+']', e );
				
				<portlet:namespace/>workbench.handleRequestSpreadToPort( e.portletId, e.event, e.data );
			}
		}
);


$("#<portlet:namespace/>file-explorer-ok").click(function(e){
	e.preventDefault();
	var eventData = {
			portletId : '<%=portletDisplay.getId()%>',
			targetPortlet : <portlet:namespace/>fileExplorerId,
			action: 'FILE_PATH'
	};
	
	Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
	e.preventDefault();
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

/***********************************************************************
 * Click OSP event handling functions 
 ***********************************************************************/
$('#<portlet:namespace/>selectLocalFile').bind(
		'change', 
		function(event){
			var uploadFile = $(this)[0].files[0];
			
			var uploadFileName = $(this).val();
			var slashIndex = uploadFileName.lastIndexOf('\\');
			if( slashIndex < 0 )
				slashIndex = uploadFileName.lastIndexOf('/'); 
			uploadFileName = uploadFileName.slice(slashIndex+1);
			
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
					console.log('DUPLICATED: '+duplicated);
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
</script>