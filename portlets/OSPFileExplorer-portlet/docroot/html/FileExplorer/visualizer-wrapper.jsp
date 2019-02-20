<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@ include file="../init.jsp" %>

 <portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<!--
	We don't  use dataType in this portlet!!! If data type is injected, it is ignored simplely.
 -->
<%
OSPVisualizerConfig visualizerConfig = OSPVisualizerUtil.getVisualizerConfig(renderRequest, portletDisplay, user);
%>

<div class="container-fluid osp-visualizer">
	<div class="row-fluid osp-header">
		<div class="col-sm-10" class="osp-title">
			<input class="form-control" id="<portlet:namespace/>currentData" style="width:100%;"/>
		</div>
		<div class="col-sm-2 osp-menu"  id="<portlet:namespace/>menu">
			<div class="dropdown text-right">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					Menu<span class="caret"></span>
   				</button>
				<ul class="dropdown-menu dropdown-menu-left">
                       <li><a href="#" id="<portlet:namespace/>sample"><i class="icon-file"></i>Sample</a></li>
                       <li><a href="#" id="<portlet:namespace/>upload"><i class="icon-upload"></i> Upload</a></li>
                       <li><a href="#" id="<portlet:namespace/>download"><i class="icon-download-alt"></i> Download</a></li>					
				</ul>
			</div>
		</div>	
	</div>
	<div class="row-fluid osp-frame">
		<iframe class="col-sm-12 osp-iframe-canvas"  style="<%=visualizerConfig.getDisplayStyle()%>" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/FileExplorer/file-explorer.jsp"></iframe>
	</div>
</div>
<script>
/***********************************************************************
 * Global variables and initialization section
 ***********************************************************************/
var <portlet:namespace/>canvas = document.getElementById('<portlet:namespace/>canvas');
var <portlet:namespace/>disabled = JSON.parse( '<%=visualizerConfig.disabled%>');
var <portlet:namespace/>sampleSelected = false;

var <portlet:namespace/>config = {
		namespace: '<%=visualizerConfig.namespace%>',
		displayCanvas: <portlet:namespace/>canvas,
		portletId: '<%=visualizerConfig.portletId%>',
		connector: '<%=visualizerConfig.connector%>',
		menuOptions: JSON.parse('<%=visualizerConfig.menuOptions%>'),
		resourceURL: '<%=serveResourceURL%>',
		eventHandlers: {
				'OSP_LOAD_DATA': <portlet:namespace/>loadDataEventHandler,
				'OSP_REQUEST_DATA':<portlet:namespace/>requestDataEventHandler,
				'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler,
				'OSP_DISABLE_CONTROLS': <portlet:namespace/>disableControlsEventHandler
		},
		loadCanvas: <portlet:namespace/>loadCanvas,
		disabled: JSON.parse( '<%=visualizerConfig.disabled%>')
};

var <portlet:namespace/>visualizer;
$('#<portlet:namespace/>canvas').load(function(){
	<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
	<portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>'), false );
});

/***********************************************************************
 * Canvas functions
 ***********************************************************************/
function <portlet:namespace/>loadCanvas( jsonData, changeAlert ){
	switch( jsonData.type_){
	case OSP.Enumeration.PathType.FILE_CONTENT:
		case OSP.Enumeration.PathType.FILE:
			<portlet:namespace/>visualizer.getFolderInfo( jsonData.parent_, '', false);
			break;
		case OSP.Enumeration.PathType.FOLDER:
			<portlet:namespace/>visualizer.getFolderInfo( jsonData.parent_, '', false );
			break;
		case OSP.Enumeration.PathType.EXT:
			<portlet:namespace/>visualizer.getFolderInfo( jsonData.parent_, jsonData.name_, false );
			break;
		case OSP.Enumeration.PathType.FOLDER_CONTENT:
			var currentSelect = OSP.Util.mergePath(jsonData.parent_, jsonData.name_);
			<portlet:namespace/>setCurrentSelect( currentSelect );
				
			<portlet:namespace/>visualizer.callIframeFunc('loadFileExplorer', null, jsonData.parent_, jsonData.content_, jsonData.name_ );
			if( changeAlert ){
				<portlet:namespace/>visualizer.fireDataChangedEvent();
			}
			
			break;
		default:
			console.log('Path Type Error: Cannot display with this path type', jsonData.type_ );
			return;
	}
};

function <portlet:namespace/>setCurrentSelect ( fullPath ){
	fullPath = OSP.Util.removeEndSlashes( fullPath );
	$('#<portlet:namespace/>currentData').val( '/' + fullPath);
}

function <portlet:namespace/>processInitAction( jsonInitData, changeAlert ){
	if( !jsonInitData.repositoryType_ ){
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
	
	<portlet:namespace/>visualizer.processInitAction( jsonInitData, changeAlert );
}

 /***********************************************************************
  * Functions called by iframe jsp 
  ***********************************************************************/
function <portlet:namespace/>fireDataChangedEvent( folderPath, fileName, type ){
	<portlet:namespace/>setCurrentSelect( OSP.Util.mergePath( folderPath, fileName ) );
	
	<portlet:namespace/>visualizer.fireDataChangedEvent({
		type_: type, 
		parent_: folderPath, 
		name_: fileName 
	});
};

function <portlet:namespace/>lookupFolder( folderPath ){
	folderPath = OSP.Util.removeEndSlashes(folderPath);
	<portlet:namespace/>visualizer.loadCanvas( {
			type_: OSP.Enumeration.PathType.FOLDER, 
			parent_: folderPath, 
			name_: '' 
	}, true );
};

	/***********************************************************************
	  * Functions call functions on iframe jsp 
	  ***********************************************************************/
function <portlet:namespace/>loadFileExplorer( folderPath, fileList ){
	//console.log('loadFileExplorer parentPath: ', parentPath);
	// <portlet:namespace/>visualizer.callIframeFuncDelayed('loadFileExplorer', 10, function(){},  folderPath, fileList );
	<portlet:namespace/>visualizer.callIframeFunc('loadFileExplorer', null,  folderPath, fileList );
}

/***********************************************************************
 * Window Event binding functions 
 ***********************************************************************/
$('#<portlet:namespace/>upload').click(function(){
	if( <portlet:namespace/>disabled )
		return;
	
	var uploadSuccessFunc = function(data){
		console.log( 'Upload Success: ', data );
		<portlet:namespace/>visualizer.loadCanvas( data, true );
	};
	
	<portlet:namespace/>visualizer.uploadLocalFile( 	uploadSuccessFunc );
});

$('#<portlet:namespace/>sample').click(function(){
	if( <portlet:namespace/>disabled )
		return;

	$('#<portlet:namespace/>currentData').val( '--- sample selected ---');
	$('#<portlet:namespace/>currentData').attr('disabled', true);
	<portlet:namespace/>sampleSelected = true;
	
	<portlet:namespace/>visualizer.fireSampleSelectedEvent();
});

$('#<portlet:namespace/>download').click(function(){
	var canvas = document.getElementById('<portlet:namespace/>canvas');
	
	var selectedFiles = canvas.contentWindow.getSelectedFiles();
	console.log('Selected Files: ', selectedFiles );
	
	<portlet:namespace/>visualizer.downloadFiles(selectedFiles);
});

/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
function <portlet:namespace/>loadDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>loadDataEventHandler] ', data );
	
	<portlet:namespace/>visualizer.loadCanvas( data, params.changeAlert );
}

function <portlet:namespace/>requestDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>requestDataEventHandler]', data, params);
		
	var path = OSP.Util.convertToPath( $('#<portlet:namespace/>currentData').val() );
	console.log( 'Converted Path: ', path );
	
	<portlet:namespace/>visualizer.fireResponseDataEvent(path, params );
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	console.log('[<portlet:namespace/>initializeEventHandler] ');
	
	<portlet:namespace/>visualizer.processInitAction( null, true);
}

function <portlet:namespace/>disableControlsEventHandler( data, params ){
	console.log('[<portlet:namespace/>disableControlsEventHandler] ');
	
	<portlet:namespace/>disabled = params.disabled;
	<portlet:namespace/>visualizer.callIframeFunc('disable', null, params.disabled);
}
</script>



