<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
<%@ include file="../init.jsp" %>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
 
 <%
 OSPVisualizerConfig visualizerConfig = OSPVisualizerUtil.getVisualizerConfig(renderRequest, portletDisplay, user);
 %>
 
 <div class="container-fluid osp-visualizer">
	<div class="row-fluid osp-no-header-frame">
		<iframe
				class="col-sm-12 osp-iframe-canvas"  
				style="<%=visualizerConfig.getDisplayStyle()%>" 
				id="<portlet:namespace/>canvas" 
				src="<%=request.getContextPath()%>/html/HtmlViewer/html-viewer.jsp">
		</iframe>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables and initialization section
 ***********************************************************************/
var <portlet:namespace/>canvas = document.getElementById('<portlet:namespace/>canvas');

var <portlet:namespace/>config = {
		namespace: '<%=visualizerConfig.namespace%>',
		displayCanvas: <portlet:namespace/>canvas,
		portletId: '<%=visualizerConfig.portletId%>',
		connector: '<%=visualizerConfig.connector%>',
		menuOptions: JSON.parse('<%=visualizerConfig.menuOptions%>'),
		resourceURL: '<%=serveResourceURL%>',
		eventHandlers: {
				'OSP_LOAD_DATA': <portlet:namespace/>loadDataEventHandler,
				'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler
		},
		loadCanvas: <portlet:namespace/>loadHtml
};

var <portlet:namespace/>visualizer;
$('#<portlet:namespace/>canvas').load(function(){
	<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
	<portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>') );
});

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
 * Global variables and initialization section
 ***********************************************************************/
function <portlet:namespace/>loadHtml( jsonData, changeAlert ){
	switch( jsonData.type_ ){
	case OSP.Enumeration.PathType.FILE:
		<portlet:namespace/>visualizer.openHtmlIndex();
		break;
	case OSP.Enumeration.PathType.URL:
		var url = '<%=request.getContextPath()%>/' + jsonData.content_;
	    <portlet:namespace/>visualizer.callIframeFunc('setContent', null, url);
	    break;
	default:
		<portlet:namespace/>visualizer.showAlert('[HtmlViewer] Type mismatch: '+jsonData.type_ );
		break;
	}
}

/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
function <portlet:namespace/>loadDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>loadDataEventHandler] ', data );
	
	<portlet:namespace/>visualizer.loadCanvas( data, true );
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	console.log('[<portlet:namespace/>initializeEventHandler] ', data, params );
	
	<portlet:namespace/>visualizer.callIframeFunc('setContent', null, '');
}
</script>
