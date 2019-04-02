<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
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
                       <li><a href="#" id="<portlet:namespace/>openLocalFile"><i class="icon-file"></i>Open local file</a></li>
                       <li><a href="#" id="<portlet:namespace/>openServerFile"><i class="icon-file"></i>Open Server file</a></li>
				</ul>
			</div>
		</div>	
	</div>
	<div class="row-fluid osp-frame">
		<div class="col-sm-12 osp-scroll-canvas"
				style="<%=visualizerConfig.getDisplayStyle()%>"
				id="<portlet:namespace/>canvas">
			<video 
					id="<portlet:namespace/>player"
					controls autoplay
					>
			</video>
		</div>
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
			loadCanvas: <portlet:namespace/>loadText,
			procFuncs:{
				readServerFile: function( jsonData ){
					console.log('Custom function for readServerFile....');
				}
			}
};
 
 var <portlet:namespace/>visualizer;
 
<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
<portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>' ) );
 
/***********************************************************************
 * Canvas functions
***********************************************************************/
function <portlet:namespace/>loadText( jsonData, changeAlert ){	
	switch( jsonData.type_ ){
	case OSP.Enumeration.PathType.FILE:
	    <portlet:namespace/>visualizer.createTempFilePath('<%=request.getContextPath()%>', {}, changeAlert, false);
		break;
	case OSP.Enumeration.PathType.FOLDER:
	case OSP.Enumeration.PathType.EXT:
	    // <portlet:namespace/>visualizer.readFirstServerFileURL();
		break;
	case OSP.Enumeration.PathType.URL:
		<portlet:namespace/>setTitle(jsonData.name_);
		console.log('Video URL: ', jsonData );
		/*
		if( jsonData.fileType_ !== 'mp4' ){
			return;
		}
		*/
		
		var videoNode = document.getElementById('<portlet:namespace/>player');
		videoNode.src = jsonData.content_;
		break;
	case OSP.Enumeration.PathType.CONTENT:
	case OSP.Enumeration.PathType.FILE_CONTENT:
	default:
		alert('Un supported yet.');
	}
}

function <portlet:namespace/>addSource( target, url ){
	var source = '<source src="'+url+'" type="video/mp4">';
	console.log(source);
	target.html( source );
}

function <portlet:namespace/>setTitle( title ){
	$('#<portlet:namespace/>title').html( '<h4 style="margin:0;">'+title+'</h4>' );
}

function <portlet:namespace/>processInitAction( jsonInitData ){
	if(jsonInitData && !jsonInitData.repositoryType_ ){
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
	
	 $(<portlet:namespace/>player).attr('height', $(<portlet:namespace/>canvas).height() );
	
	<portlet:namespace/>visualizer.processInitAction( jsonInitData, false );
}

/***********************************************************************
 * Window Event binding functions 
 ***********************************************************************/

$('#<portlet:namespace/>openLocalFile').click(function(){
	var domFileSelector = $('<input type=\"file\" id=\"<portlet:namespace/>selectFile\"/>');
	domFileSelector.click();
	
	domFileSelector.on(
			'change',
			function(event){
				var URL = window.URL || window.webkitURL;
				
				var file = this.files[0];
				<portlet:namespace/>setTitle(OSP.Util.getLocalFileName(this));
				var type = file.type;
				var videoNode = document.getElementById('<portlet:namespace/>player');
				var canPlay = videoNode.canPlayType(type);
				if (canPlay === '') {
					return;
				}

				var fileURL = URL.createObjectURL(file);
				videoNode.src = fileURL;
			}
	);
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
 
function <portlet:namespace/>loadDataEventHandler( data, callbackParams ){
	console.log('[<portlet:namespace/>loadDataEventHandler] ', data );
	
	<portlet:namespace/>visualizer.loadCanvas( data, false );
}

function <portlet:namespace/>responseDataEventHandler( data, callbackParams ){
	console.log('[<portlet:namespace/>responseDataEventHandler]', data, callbackParams);
	
	switch( callbackParams.procFunc ){
	case 'readServerFileURL':
		<portlet:namespace/>visualizer.createTempFilePath( 
				'<%=request.getContextPath()%>',
				data,
				false,
				false
		);
		break;
	}
}

function <portlet:namespace/>initializeEventHandler( data, callbackParams ){
	console.log('[<portlet:namespace/>initializeEventHandler] ');
	
	/*
	<portlet:namespace/>visualizer.processInitAction( data, true );
	*/
	Liferay.Portlet.refresh("#p_p_id<portlet:namespace/>");
}

</script>

