<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
<%@include file="../init.jsp"%>

<%
OSPVisualizerConfig visualizerConfig = OSPVisualizerUtil.getVisualizerConfig(renderRequest, portletDisplay, user);
String launcherURL = (String)renderRequest.getAttribute("launcherURL");
%>
<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
 
 <div class="container-fluid osp-visualizer">
 	<div class="row-fluid no-header-frame">
		<iframe 
				class="col-sm-12 osp-iframe-canvas"  
				style="<%=visualizerConfig.getDisplayStyle()%>" 
				id="<portlet:namespace/>canvas" 
				src="<%=request.getContextPath()%>/html/ParaView/paraview.jsp">
		</iframe>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables and initialization section
 ***********************************************************************/
var <portlet:namespace/>launcherURL = '<%=launcherURL%>';
var <portlet:namespace/>canvas = document.getElementById('<portlet:namespace/>canvas');

var <portlet:namespace/>config = {
		namespace: '<portlet:namespace/>',
		displayCanvas: <portlet:namespace/>canvas,
		portletId: '<%=portletDisplay.getId()%>',
		connector: '<%=visualizerConfig.connector%>',
		menuOptions: JSON.parse('<%=visualizerConfig.menuOptions%>'), 
		resourceURL: '<%=serveResourceURL%>',
		eventHandlers: {
				'OSP_LOAD_DATA': <portlet:namespace/>loadDataEventHandler,
				'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler
		},
		loadCanvas: <portlet:namespace/>loadParaView,
		procFuncs:{
			readServerFile: function( jsonData ){
				console.log('Custom function for readServerFile....');
			}
		}
};

var <portlet:namespace/>visualizer;
$('#<portlet:namespace/>canvas').load( function(){
	<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
	<portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>' ) );
});
/***********************************************************************
 * Canvas functions
 ***********************************************************************/
function <portlet:namespace/>loadParaView( jsonData, changeAlert ){
	var dataDir;
	var fileName;
	switch( jsonData.type_ ){
	case OSP.Enumeration.PathType.FILE:
		fileName = jsonData.name_;
	case OSP.Enumeration.PathType.FOLDER:
		dataDir = jsonData.parent_;
		$.ajax({
			url: '<%=serveResourceURL.toString()%>',
			type:'POST',
			dataType:'text',
			data:{
				    <portlet:namespace/>command: 'GET_ABSOLUTE_PATH',
				    <portlet:namespace/>targetPath: dataDir,
				    <portlet:namespace/>repositoryType: jsonData.repositoryType_
			},
			success: function( result ){
				console.log( 'Result: '+result);
				<portlet:namespace/>visualizer.callIframeFunc( 'connectParaview', null, <portlet:namespace/>launcherURL, dataDir, fileName );
			},
			error: function( data, e){
				console.log('ParaView AJAX ERROR-->'+e);
			}
		});
		break;
	default:
		<portlet:namespace/>showAlert('Un-supported data type: '+jsonData.type_);
	}
}

function <portlet:namespace/>processInitAction( jsonInitData ){
	if( jsonInitData && !jsonInitData.repositoryType_ ){
		// Do nothing if repository is not specified.
		// This means processInitAction will be performed OSP_SHAKEHAND event handler.
		return;  
	}
	
	if( !jsonInitData.user_ ){	
		jsonInitData.user_ = '<%=user.getScreenName()%>';
		jsonInitData.dataType_ = {
				name: 'any',
				version:'0.0.0'
		};
		jsonInitData.type_ = OSP.Enumeration.PathType.FOLDER;
		jsonInitData.parent_ = '';
		jsonInitData.name_ = '';
	}
	
	<portlet:namespace/>visualizer.processInitAction( jsonInitData, false );
}

/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
function <portlet:namespace/>loadDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>loadDataEventHandler] ', data );
	
	<portlet:namespace/>visualizer.loadCanvas( data, false );
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	console.log('[<portlet:namespace/>initializeEventHandler] ', Liferay.PortletDisplay );
	
	<portlet:namespace/>visualizer.callIframeFunc('cleanParaview', null );
	<portlet:namespace/>visualizer.processInitAction( null, false );
}
</script>
