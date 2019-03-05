<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.kisti.osp.icecap.service.DataTypeLocalServiceUtil"%>
<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<%
OSPVisualizerConfig visualizerConfig = OSPVisualizerUtil.getVisualizerConfig(renderRequest, portletDisplay, user);
%>

<div class="container-fluid osp-visualizer">
	<div class="row-fluid osp-header">
		<div class="col-sm-10">
			<div id="<portlet:namespace/>title" class="osp-title"></div>
		</div>
		<div class="col-sm-2 osp-menu"  id="<portlet:namespace/>menu">
			<div class="dropdown text-right">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					Menu<span class="caret"></span>
   				</button>
				<ul class="dropdown-menu dropdown-menu-right">
                       <li><a href="#" id="<portlet:namespace/>sample"><i class="icon-file"></i>Sample</a></li>
                       <li><a href="#" id="<portlet:namespace/>openLocalFile"><i class="icon-file"></i>Open local file</a></li>
                       <li><a href="#" id="<portlet:namespace/>openServerFile"><i class="icon-file"></i>Open Server file</a></li>
				</ul>
			</div>
		</div>	
	</div>
	<div class="row-fluid osp-frame">
		<div 
				class="col-sm-12 osp-scroll-canvas"  
				style="<%=visualizerConfig.getDisplayStyle()%>" 
				id="<portlet:namespace/>canvas"> 
		</div>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables and initialization section
 ***********************************************************************/
 var <portlet:namespace/>canvas = document.getElementById('<portlet:namespace/>canvas');
 var <portlet:namespace/>disabled = JSON.parse( '<%=visualizerConfig.disabled%>');
 var <portlet:namespace/>dataType = new OSP.DataType(); 

 var <portlet:namespace/>config = {
 			namespace: '<portlet:namespace/>',
 			displayCanvas: <portlet:namespace/>canvas,
 			portletId: '<%=portletDisplay.getId()%>',
 			connector: '<%=visualizerConfig.connector%>',
 			displayOptions: JSON.parse('<%=visualizerConfig.menuOptions%>'),
 			resourceURL: '<%=serveResourceURL%>',
 			eventHandlers: {
 					'OSP_HANDSHAKE': <portlet:namespace/>handshakeEventHandler,
 					'OSP_LOAD_DATA': <portlet:namespace/>loadDataEventHandler,
 					'OSP_REQUEST_DATA':<portlet:namespace/>requestDataEventHandler,
 					'OSP_RESPONSE_DATA':<portlet:namespace/>responseDataEventHandler,
 					'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler,
 					'OSP_DISABLE_CONTROLS': <portlet:namespace/>disableControlsEventHandler
 			},
 			loadCanvas: <portlet:namespace/>loadData,
 			procFuncs:{
 				readServerFile: function( jsonData ){
 					console.log('Custom function for readServerFile....');
 				}
 			},
 			disabled: JSON.parse( '<%=visualizerConfig.disabled%>')
 };
 
 var <portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
 <portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>' ), false );

/***********************************************************************
 * Canvas functions
 ***********************************************************************/
function <portlet:namespace/>loadData( jsonData, changeAlert ){
	var dataType = <portlet:namespace/>dataType;
	
	$('#<portlet:namespace/>canvas').empty();
	
	switch( jsonData.type_ ){
	case OSP.Enumeration.PathType.STRUCTURED_DATA:
		dataType.deserializeStructure( jsonData.content_ );
		<portlet:namespace/>refreshEditor();
		if( !<portlet:namespace/>disabled && changeAlert )
			<portlet:namespace/>visualizer.fireDataChangedEvent();
		break;
	case OSP.Enumeration.PathType.CONTENT:
	case OSP.Enumeration.PathType.FILE_CONTENT:
		dataType.loadStructure( jsonData.content_ );
		<portlet:namespace/>refreshEditor();
		if( !<portlet:namespace/>disabled && changeAlert ){
			var jsonData = {
					type_: OSP.Enumeration.PathType.STRUCTURED_DATA,
					content_: OSP.Util.toJSON( <portlet:namespace/>dataType.structure() )
			};
			<portlet:namespace/>visualizer.fireDataChangedEvent( jsonData );
		}
		break;
	case OSP.Enumeration.PathType.DLENTRY_ID:
		<portlet:namespace/>visualizer.readDLFileEntry(changeAlert);
		break;
	case OSP.Enumeration.PathType.FILE:
		<portlet:namespace/>visualizer.readServerFile(null, changeAlert);
		break;
	default:
		<portlet:namespace/>visualizer.showAlert( 'Un-known dataType: '+jsonData.type_);
		return;
	}
}

function <portlet:namespace/>refreshEditor(){
	<portlet:namespace/>dataType.showStructuredDataEditor(
					'<portlet:namespace/>', 
					$('#<portlet:namespace/>canvas'),
					'<%=request.getContextPath()%>',
					'<%=themeDisplay.getLanguageId()%>');
	
	var inputs = $('#<portlet:namespace/>canvas').find('input');
	
	inputs.each(function(index){
		$(this).prop('disabled', <portlet:namespace/>disabled);
	});
};

function <portlet:namespace/>processInitAction( jsonInitData, launchCanvas, changeAlert ){
	console.log( 'jsonInitData', jsonInitData );
	if( !jsonInitData.repositoryType_ || !jsonInitData.user_ ){
		// Do nothing if repository is not specified.
		// This means processInitAction will be performed OSP_SHAKEHAND event handler.
		return;  
	}
	
	jsonInitData.type_ = OSP.Enumeration.PathType.FOLDER;
	jsonInitData.parent_ = '';
	jsonInitData.name_ = '';

	console.log( 'jsonInitData', jsonInitData );
	if( jsonInitData.dataType_ ){
		<portlet:namespace/>dataType.name( jsonInitData.dataType_.name );
		<portlet:namespace/>dataType.version( jsonInitData.dataType_.version );
		
		<portlet:namespace/>visualizer.readDataTypeStructure( jsonInitData.dataType_.name, jsonInitData.dataType_.version);
	}
	<portlet:namespace/>visualizer.processInitAction( jsonInitData, changeAlert );
}

/***********************************************************************
 * Window Event binding functions 
 ***********************************************************************/

$('#<portlet:namespace/>sample').click(function(){
	if( <portlet:namespace/>disabled )
		return;

	<portlet:namespace/>visualizer.fireRequestSampleContentEvent();
});

$('#<portlet:namespace/>openLocalFile').click(function(){
	if( <portlet:namespace/>disabled )
		return;

	<portlet:namespace/>visualizer.openLocalFile( true );
});

$('#<portlet:namespace/>openServerFile').click(function(){
	if( <portlet:namespace/>disabled )
		return;

	<portlet:namespace/>visualizer.openServerFile(null, true);
});

$('#<portlet:namespace/>canvas').on('change', function(){
	if( <portlet:namespace/>disabled )
		return;
	
	let structure = <portlet:namespace/>dataType.structure();
	let pages = structure.activeParameterFormattedInputs();
	let fileContents = {
			fileCount: pages.length,
			content: pages
	};
	
	/*
	var jsonData = {
			type_: OSP.Enumeration.PathType.STRUCTURED_DATA,
			content_: OSP.Util.toJSON( <portlet:namespace/>dataType.structure() )
	};
	*/
	var jsonData = {
			type_: OSP.Enumeration.PathType.FILE_CONTENTS,
			content_: fileContents
	};
	
	
	<portlet:namespace/>visualizer.fireDataChangedEvent( jsonData );
});

/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
 function <portlet:namespace/>handshakeEventHandler( jsonData, params ){
	 <portlet:namespace/>visualizer.configConnection( params.connector, params.disabled );
	<portlet:namespace/>processInitAction( jsonData, params.changeAlert );
	<portlet:namespace/>visualizer.fireRegisterEventsEvent();
 }
 
function <portlet:namespace/>loadDataEventHandler( jsonData, params ){
	<portlet:namespace/>visualizer.loadCanvas( jsonData, params.changeAlert );
}

function <portlet:namespace/>requestDataEventHandler( jsonData, params ){
	var eventData = {
			type_: OSP.Enumeration.PathType.STRUCTURED_DATA,
			content_: OSP.Util.toJSON( <portlet:namespace/>dataType.structure() )
	};
	<portlet:namespace/>visualizer.fireResponseDataEvent(eventData, params );
}

function <portlet:namespace/>responseDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>responseDataEventHandler]', data, params);
	
	switch( callbackParams.procFunc ){
	case 'readServerFile':
		<portlet:namespace/>visualizer.runProcFuncs( 'readServerFile', data, true );
		break;
	}
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	console.log('[<portlet:namespace/>initializeEventHandler] ');
	
	var initData = JSON.parse('<%=visualizerConfig.initData%>');
	initData.dataType_ = {
		name: <portlet:namespace/>dataType.name(),
		version:<portlet:namespace/>dataType.version()
	};
	
	<portlet:namespace/>processInitAction(initData, false);
}

function <portlet:namespace/>disableControlsEventHandler( data, params ){
	console.log('[<portlet:namespace/>disableControlsEventHandler] ');
	<portlet:namespace/>disabled = params.disabled;
	var inputs = $('#<portlet:namespace/>canvas').find('input');
	inputs.each(function(index){
		$(this).attr('disabled', <portlet:namespace/>disabled);
	});
}
</script>