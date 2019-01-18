<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@ include file="../init.jsp" %>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<%
OSPVisualizerConfig visualizerConfig = OSPVisualizerUtil.getVisualizerConfig(renderRequest, portletDisplay, user);
%>



<div class="container-fluid osp-visualizer">
	<div class="row-fluid osp-frame">
		<iframe 
				class="col-sm-12 osp-iframe-canvas"  
				style="<%=visualizerConfig.getDisplayStyle()%> border:solid #eeeeee 1px; padding: 0px;" 
				id="<portlet:namespace/>canvas" 
				src="<%=request.getContextPath()%>/html/2DMeshEditor/mesh-editor.jsp">
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
		case OSP.Enumeration.PathType.CONTENT:	// 로컬 파일 or 편집기에서 작성했을때 
		// 타이틀
		//뷰
		//서버 저장 .. 
		// 로컬 파일 컨텐츠를 서버 저장하는 함수 visual~~.savtAs Sever`~~~

//	<portlet:namespace/>visualizer.saveAtServerAs();
			break;
		case OSP.Enumeration.PathType.FILE_CONTENT:	//서버 연결된 파일이 옴
			if( jsonData.name_ )
				<portlet:namespace/>setTitle( OSP.Util.mergePath(jsonData.parent_, jsonData.name_) );
			
			<portlet:namespace/>set2DmeshEditorContent( Liferay.Util.unescapeHTML(jsonData.content_) );

	//		if( !<portlet:namespace/>disabled && changeAlert )
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
	<portlet:namespace/>visualizer.callIframeFunc('setMeshName', null, title);

//	$('#<portlet:namespace/>title').html( '<h4 style="margin:0;">'+title+'</h4>' );
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
	console.log('fireDataChangedEvent in 2Dmesh Editor wrapper...');
	<portlet:namespace/>visualizer.fireDataChangedEvent({
		content_: content 
	});
};

/***********************************************************************
  * Functions which call iframe functions 
  ***********************************************************************/
function <portlet:namespace/>set2DmeshEditorContent( content ){
	<portlet:namespace/>visualizer.callIframeFunc('view2Dmesh', null, content );
}

function <portlet:namespace/>fireRequestSampleContentEvent() {
	if( <portlet:namespace/>disabled )
		return;

	<portlet:namespace/>visualizer.fireRequestSampleContentEvent();
};



function <portlet:namespace/>uploadLocalFile() {
	if( <portlet:namespace/>disabled )
		return;
	
	var uploadSuccessFunc = function(data){
		console.log( 'Upload Success: ', data );
		<portlet:namespace/>visualizer.loadCanvas( data, true );
	};
	
	<portlet:namespace/>visualizer.uploadLocalFile( uploadSuccessFunc );
};

function <portlet:namespace/>downloadCurrentFile() {
	<portlet:namespace/>visualizer.downloadCurrentFile();
};

function <portlet:namespace/>openLocalFile() {
	<portlet:namespace/>visualizer.openLocalFile();
};

function <portlet:namespace/>openServerFile() {
	<portlet:namespace/>visualizer.openServerFile();
};

function <portlet:namespace/>saveAtServer(content) {
	<portlet:namespace/>visualizer.saveAtServer(content);
};


function <portlet:namespace/>saveAtServerAs(content) {
	<portlet:namespace/>visualizer.saveAtServerAs(content);
};

/***********************************************************************
 * Window Event binding functions 
 ***********************************************************************/


/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
function <portlet:namespace/>loadDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>loadDataEventHandler] ', data );
//	console.log('[<portlet:namespace/>loadDataEventHandler] ', params );
	
	<portlet:namespace/>visualizer.loadCanvas( data, params.changeAlert );
//	<portlet:namespace/>visualizer.loadCanvas( data, false );
}

function <portlet:namespace/>requestDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>requestDataEventHandler]', data, params);
	<portlet:namespace/>visualizer.callIframeFunc('saveMeshData', function(content){
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
		<portlet:namespace/>visualizer.callIframeFunc('saveMeshData', function(content){
			<portlet:namespace/>visualizer.runProcFuncs( 'saveAtServerAs', data.parent_, data.name_, content );
			<portlet:namespace/>setTitle( OSP.Util.mergePath( data.parent_, data.name_) );
		});
		break;
	}
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	console.log('[<portlet:namespace/>initializeEventHandler] ', data, params );
	
	<portlet:namespace/>visualizer.processInitAction();
	
	<portlet:namespace/>visualizer.callIframeFunc('init2DmeshEditor', null, '' );
	<portlet:namespace/>setTitle('');
}

function <portlet:namespace/>disableControlsEventHandler( data, params ){
	console.log('[<portlet:namespace/>disableControlsEventHandler] ');
	<portlet:namespace/>disabled = params.disabled;
	<portlet:namespace/>visualizer.disabled( params.disabled );
	<portlet:namespace/>visualizer.callIframeFunc('disable', null, params.disabled);
}
</script>



