<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
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
                       <li><a href="#" id="<portlet:namespace/>openLocalFile"><i class="icon-file"></i>Open local file</a></li>
                       <li><a href="#" id="<portlet:namespace/>openServerFile"><i class="icon-file"></i>Open Server file</a></li>
                       <li><a href="#" id="<portlet:namespace/>download"><i class="icon-download-alt"></i> Download</a></li>					
				</ul>
			</div>
		</div>	
	</div>
	<div class="row-fluid osp-frame">
		<iframe 
				class="col-sm-12 osp-iframe-canvas"  
				style="<%=visualizerConfig.getDisplayStyle()%>" 
				id="<portlet:namespace/>canvas" 
				src="<%=request.getContextPath()%>/html/ImageViewer/image-viewer.jsp">
		</iframe>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables and initialization section
 ***********************************************************************/
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
					'OSP_RESPONSE_DATA':<portlet:namespace/>responseDataEventHandler,
					'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler
			},
			loadCanvas: <portlet:namespace/>loadImage,
			procFuncs:{
				readServerFile: function( jsonData ){
					console.log('Custom function for readServerFile....');
				}
			}
};
 
 var <portlet:namespace/>visualizer;
 var <portlet:namespace/>allLoaded = 0;
 $($('#<portlet:namespace/>canvas')[0].contentWindow).on('load',function(){
		console.log('iframe loaded');
		<portlet:namespace/>allLoaded++;
	});

	$(window).on('load', function(){
		console.log('window loaded...');
		<portlet:namespace/>allLoaded++;
	});

/*	
 $('#<portlet:namespace/>canvas').load( function(){
	<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
	<portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>' ) );
 });
*/
/***********************************************************************
 * Canvas functions
 ***********************************************************************/
function <portlet:namespace/>loadImage( jsonData, changeAlert ){
	console.log('loadImage: ', jsonData );
	switch( jsonData.type_ ){
	case OSP.Enumeration.PathType.FILE:
	    <portlet:namespace/>visualizer.readServerFileURL();
		break;
	case OSP.Enumeration.PathType.FOLDER:
	case OSP.Enumeration.PathType.EXT:
	    <portlet:namespace/>visualizer.readFirstServerFileURL();
		break;
	case OSP.Enumeration.PathType.URL:
		<portlet:namespace/>setTitle(jsonData.name_);
		<portlet:namespace/>visualizer.callIframeFunc( 'loadImage', null, jsonData.content_ );
		break;
	case OSP.Enumeration.PathType.FILE_CONTENT:
	    alert('Un supported yet.');
		break;
	default:
		alert('Un supported yet.');
	}
}

function <portlet:namespace/>setTitle( title ){
	$('#<portlet:namespace/>title').html( '<h4 style="margin:0;">'+title+'</h4>' );
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
 * Window Event binding functions 
 ***********************************************************************/
$('#<portlet:namespace/>openLocalFile').click(function(){
	<portlet:namespace/>visualizer.openLocalFileURL();
});

$('#<portlet:namespace/>openServerFile').click(function(){
	<portlet:namespace/>visualizer.openServerFileURL();
});

$('#<portlet:namespace/>download').click(function(){
	<portlet:namespace/>visualizer.downloadCurrentFile();
});

/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
function <portlet:namespace/>loadDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>loadDataEventHandler] ', data );
	
	<portlet:namespace/>visualizer.loadCanvas( data, false );
}

function <portlet:namespace/>responseDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>responseDataEventHandler]', data, params);
	
	switch( params.procFunc ){
	case 'readServerFileURL':
		<portlet:namespace/>visualizer.runProcFuncs( 'readServerFileURL', data );
		break;
	}
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	console.log('[<portlet:namespace/>initializeEventHandler] ', Liferay.PortletDisplay );
	
	<portlet:namespace/>visualizer.callIframeFunc('loadImage', null, '<%=renderRequest.getContextPath()%>/images/OSP.png' );
	<portlet:namespace/>visualizer.processInitAction( null, false );
	<portlet:namespace/>setTitle('');
}


</script>