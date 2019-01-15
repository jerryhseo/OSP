<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@ include file="../init.jsp" %>

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
				<ul class="dropdown-menu dropdown-menu-left">
                       <li><a href="#" id="<portlet:namespace/>sample"><i class="icon-file"></i>Sample</a></li>
                       <li><a href="#" id="<portlet:namespace/>openLocalFile"><i class="icon-file"></i>Open local file</a></li>
                       <li><a href="#" id="<portlet:namespace/>openServerFile"><i class="icon-file"></i>Open Server file</a></li>
                       <li><a href="#" id="<portlet:namespace/>save"><i class="icon-file"></i>Save</a></li>
                       <li><a href="#" id="<portlet:namespace/>saveAs"><i class="icon-file"></i>Save as...</a></li>
                       <li><a href="#" id="<portlet:namespace/>saveAtLocal"><i class="icon-file"></i>Save at local</a></li>
                       <li><a href="#" id="<portlet:namespace/>download"><i class="icon-download-alt"></i> Download</a></li>					
				</ul>
			</div>
		</div>	
	</div>
	<div class="row-fluid osp-frame">
		<iframe 
				class="col-sm-12 osp-iframe-canvas"  
				style="<%=visualizerConfig.getDisplayStyle()%>border:solid #eeeeee 1px;" 
				id="<portlet:namespace/>canvas" 
				src="<%=request.getContextPath()%>/html/TextEditor/text-editor.jsp">
		</iframe>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables and initialization section
 ***********************************************************************/
 console.log( '== LIFERAY ==', Liferay );
 
var <portlet:namespace/>canvas = document.getElementById('<portlet:namespace/>canvas');
var <portlet:namespace/>disabled = JSON.parse( '<%=visualizerConfig.disabled%>');

var <portlet:namespace/>config = {
			namespace: '<portlet:namespace/>',
			displayCanvas: <portlet:namespace/>canvas,
			portletId: '<%=portletDisplay.getId()%>',
			connector: '<%=visualizerConfig.connector%>',
			menuOptions: JSON.parse('<%=visualizerConfig.menuOptions%>'), 
			resourceURL: '<%=serveResourceURL%>',
			eventHandlers: {
					'OSP_LOAD_DATA': <portlet:namespace/>loadDataEventHandler,
					'OSP_REQUEST_DATA':<portlet:namespace/>requestDataEventHandler,
					'OSP_RESPONSE_DATA':<portlet:namespace/>responseDataEventHandler,
					'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler,
					'OSP_DISABLE_CONTROLS': <portlet:namespace/>disableControlsEventHandler
			},
			loadCanvas: <portlet:namespace/>loadCanvas,
			procFuncs:{
				readServerFile: function( jsonData ){
					console.log('Custom function for readServerFile....');
				}
			},
			disabled: JSON.parse( '<%=visualizerConfig.disabled%>')
};

var <portlet:namespace/>visualizer;
$('#<portlet:namespace/>canvas').load(function(){
	<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
	<portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>' ) );
});
	
/***********************************************************************
 * Canvas functions
 ***********************************************************************/
function <portlet:namespace/>loadCanvas( jsonData, changeAlert ){
	console.log( 'jsonData: ', jsonData );
	
	switch( jsonData.type_){
		case OSP.Enumeration.PathType.FILE:
			<portlet:namespace/>visualizer.readServerFile( jsonData );
			break;
		case OSP.Enumeration.PathType.CONTENT:
		case OSP.Enumeration.PathType.FILE_CONTENT:
			if( jsonData.name_ )
				<portlet:namespace/>setTitle( OSP.Util.mergePath(jsonData.parent_, jsonData.name_) );
			
			<portlet:namespace/>setTextEditorContent( Liferay.Util.unescapeHTML(jsonData.content_) );

			if( changeAlert )
				<portlet:namespace/>visualizer.fireDataChangedEvent();
			break;
		case OSP.Enumeration.PathType.DLENTRY_ID:
			<portlet:namespace/>visualizer.readDLFileEntry();
			break;
		case OSP.Enumeration.PathType.URL:
			alert( 'Un-supported yet.');
			changeAlert = false;
			break;
		default:
			console.log('Path Type Error: Cannot display with this path type', jsonData );
			changeAlert = false;
			return;
	}
	
};

function <portlet:namespace/>setTitle( title ){
	$('#<portlet:namespace/>title').html( '<h4 style="margin:0;">'+title+'</h4>' );
};

function <portlet:namespace/>processInitAction( jsonInitData ){
	
	if( !jsonInitData.repositoryType_ ){
		// Do nothing if repository is not specified.
		// This means processInitAction will be performed OSP_SHAKEHAND event handler.
		return;  
	}
	
	jsonInitData.user_ = jsonInitData.user_ ? jsonInitData.user_ : '<%=user.getScreenName()%>';
	jsonInitData.type_ = jsonInitData.type_ ? jsonInitData.type_ : OSP.Enumeration.PathType.FOLDER;
	jsonInitData.parent_ = jsonInitData.parent_ ? jsonInitData.parent_ : '';
	jsonInitData.name_ = jsonInitData.name_ ? jsonInitData.name_ : '';
	
	<portlet:namespace/>visualizer.processInitAction( jsonInitData, false );
}


 /***********************************************************************
  * Functions called by iframe jsp 
  ***********************************************************************/
function <portlet:namespace/>fireDataChangedEvent( content ){
	console.log('fireDataChangedEvent in text editor wrapper...');
	<portlet:namespace/>visualizer.fireDataChangedEvent({
		content_: content 
	});
};

/***********************************************************************
  * Functions which call iframe functions 
  ***********************************************************************/
function <portlet:namespace/>setTextEditorContent( content ){
	<portlet:namespace/>visualizer.callIframeFunc('setContent', null, content );
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

	<portlet:namespace/>visualizer.openLocalFile();
});

$('#<portlet:namespace/>openServerFile').click(function(){
	if( <portlet:namespace/>disabled )
		return;

	<portlet:namespace/>visualizer.openServerFile();
});

$('#<portlet:namespace/>save').click(function(){
	if( <portlet:namespace/>disabled )
		return;

	<portlet:namespace/>visualizer.callIframeFunc('getContent', function( content ){
		<portlet:namespace/>visualizer.saveAtServer(content);
	});
});

$('#<portlet:namespace/>saveAs').click(function(){
	if( <portlet:namespace/>disabled )
		return;

	<portlet:namespace/>visualizer.saveAtServerAs();
});

$('#<portlet:namespace/>saveAtLocal').click(function(){
	<portlet:namespace/>visualizer.callIframeFunc('getContent', function( content ){
		<portlet:namespace/>visualizer.saveAtLocal(content, 'text/plain');
	});
});

$('#<portlet:namespace/>download').click(function(){
	<portlet:namespace/>visualizer.downloadCurrentFile();
});

/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
function <portlet:namespace/>loadDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>loadDataEventHandler] ', data );
	
	<portlet:namespace/>visualizer.loadCanvas( data, !<portlet:namespace/>disabled );
}

function <portlet:namespace/>requestDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>requestDataEventHandler]', data, params);
	<portlet:namespace/>visualizer.callIframeFunc('getContent', function(content){
		<portlet:namespace/>visualizer.fireResponseDataEvent({content_: content}, params );
	});
}

function <portlet:namespace/>responseDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>responseDataEventHandler]', data, params);
	
	switch( params.procFunc ){
	case 'readServerFile':
		<portlet:namespace/>visualizer.runProcFuncs( 'readServerFile', data );
		break;
	case 'saveAtServerAs':
		<portlet:namespace/>visualizer.callIframeFunc('getContent', function(content){
			<portlet:namespace/>visualizer.runProcFuncs( 'saveAtServerAs', data.parent_, data.name_, content );
			<portlet:namespace/>setTitle( OSP.Util.mergePath( data.parent_, data.name_) );
		});
		break;
	}
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	console.log('[<portlet:namespace/>initializeEventHandler] ', data, params );
	
	<portlet:namespace/>visualizer.processInitAction();
	
	<portlet:namespace/>visualizer.callIframeFunc('setContent', null, '' );
	<portlet:namespace/>setTitle('');
}

function <portlet:namespace/>disableControlsEventHandler( data, params ){
	console.log('[<portlet:namespace/>disableControlsEventHandler] ');
	<portlet:namespace/>disabled = params.disabled;
	<portlet:namespace/>visualizer.disabled( params.disabled );
	<portlet:namespace/>visualizer.callIframeFunc('disable', null, params.disabled);
}
</script>



