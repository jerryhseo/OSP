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
				src="<%=request.getContextPath()%>/html/sc3dviewer/sc3d_viewer.jsp">
		</iframe>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables and initialization section
 ***********************************************************************/
 
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
					'OSP_RESPONSE_DATA':<portlet:namespace/>responseDataEventHandler,
					'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler
			},
			loadCanvas: <portlet:namespace/>loadCanvas,
			procFuncs:{
				readServerFile: function( jsonData ){
					console.log('Custom function for readServerFile....');
				}
			}
};

var <portlet:namespace/>visualizer;
$('#<portlet:namespace/>canvas').load(function(){
	console.log('*****************************************************');
	<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
	<portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>', false ) );
});
	
/***********************************************************************
 * Canvas functions
 ***********************************************************************/
function <portlet:namespace/>loadCanvas( jsonData, changeAlert ){
	//alert("--------");
	var dataType;
	if( jsonData.dataType_ ){
	dataType = jsonData.dataType_.name;
	};
	switch( jsonData.type_){
		case OSP.Enumeration.PathType.FILE:
			<portlet:namespace/>visualizer.readServerFile( jsonData, changeAlert );
			break;
		case OSP.Enumeration.PathType.CONTENT:
			var dotIndex = jsonData.name_.lastIndexOf('.');
			var ext = jsonData.name_.slice(dotIndex+1);
			OSP.Debug.eventTrace('extention', ext);

			if( ext === 'js' ){ 	dataType = 'sc3d_output'; }	else{ dataType = 'sc3d_device';	}
			//alert("dataType: "+dataType);
		case OSP.Enumeration.PathType.FILE_CONTENT:
			if( jsonData.name_ ) <portlet:namespace/>setTitle(  jsonData.name_ );
			//<portlet:namespace/>visualizer.callIframeFunc('load_Struc_file', null, jsonData.content_ );
			//alert("dataType:==== "+dataType);
			
		         if(dataType === 'sc3d_device' ){<portlet:namespace/>visualizer.callIframeFunc('load_Struc_file', null, jsonData.content_ );		}
			else if(dataType === 'sc3d_output'){<portlet:namespace/>visualizer.callIframeFunc('loadEPData', null, jsonData.content_ );		}
			else{ return;}			// Error Message
							
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

function <portlet:namespace/>processInitAction( jsonInitData, changeAlert ){
	
	if( !jsonInitData.repositoryType_ ){
		// Do nothing if repository is not specified.
		// This means processInitAction will be performed OSP_SHAKEHAND event handler.
		return;  
	}
	
	jsonInitData.user_ = jsonInitData.user_ ? jsonInitData.user_ : '<%=user.getScreenName()%>';
	jsonInitData.type_ = jsonInitData.type_ ? jsonInitData.type_ : OSP.Enumeration.PathType.FOLDER;
	jsonInitData.parent_ = jsonInitData.parent_ ? jsonInitData.parent_ : '';
	jsonInitData.name_ = jsonInitData.name_ ? jsonInitData.name_ : '';
	
	<portlet:namespace/>visualizer.processInitAction( jsonInitData, changeAlert );
}



/***********************************************************************
 * Window Event binding functions 
 ***********************************************************************/


$('#<portlet:namespace/>openLocalFile').click(function(){
		
	<portlet:namespace/>visualizer.openLocalFile(null, false);
});

$('#<portlet:namespace/>openServerFile').click(function(){
	
	<portlet:namespace/>visualizer.openServerFile(null, false);
});

$('#<portlet:namespace/>download').click(function(){
	<portlet:namespace/>visualizer.downloadCurrentFile();
});


/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
function <portlet:namespace/>loadDataEventHandler( data, params ){
	
	
	<portlet:namespace/>visualizer.loadCanvas( data, params.changeAlert );
	
}



function <portlet:namespace/>responseDataEventHandler( data, params ){
	//console.log('[<portlet:namespace/>responseDataEventHandler]', data, params);
	
	switch( params.procFunc ){
	case 'readServerFile':
		<portlet:namespace/>visualizer.runProcFuncs( 'readServerFile', data, true );
		break;
	
	}
	
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	//console.log('[<portlet:namespace/>initializeEventHandler] ', data, params );
	
	<portlet:namespace/>visualizer.processInitAction();
	
	<portlet:namespace/>visualizer.callIframeFunc('removeAllObjects', null);
	

}


//----from editor----
function <portlet:namespace/>fireDataChangedEvent( content ){
	
	//alert(content);
	
	//console.log('fireDataChangedEvent in text editor wrapper...');
	<portlet:namespace/>visualizer.fireDataChangedEvent({
		type_: "content",
		content_: content 
	});
};


</script>



