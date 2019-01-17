<%@page import="com.kisti.osp.util.OSPVisualizerUtil"%>
<%@page import="com.kisti.osp.util.OSPVisualizerConfig"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<%
OSPVisualizerConfig visualizerConfig = OSPVisualizerUtil.getVisualizerConfig(renderRequest, portletDisplay, user);
%>

<div class="container-fluid osp-visualizer">
	<div class="row-fluid osp-no-header-frame" >
	    <div class="col-sm-12 osp-canvas">
	        <aui:input label="Enter value:" type="text" id="canvas" name="canvas"/>
	    </div>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables and initialization section
 ***********************************************************************/
var <portlet:namespace/>canvas = document.getElementById('<portlet:namespace/>canvas');
var <portlet:namespace/>disabled = JSON.parse( '<%=visualizerConfig.disabled%>');

var <portlet:namespace/>config = {
		namespace: '<%=visualizerConfig.namespace%>',
		displayCanvas: <portlet:namespace/>canvas,
		portletId: '<%=visualizerConfig.portletId%>',
		connector: '<%=visualizerConfig.connector%>',
		menuOptions: JSON.parse('<%=visualizerConfig.menuOptions%>'),
		resourceURL: '',
		eventHandlers: {
				'OSP_LOAD_DATA': <portlet:namespace/>loadDataEventHandler,
				'OSP_REQUEST_DATA':<portlet:namespace/>requestDataEventHandler,
				'OSP_INITIALIZE': <portlet:namespace/>initializeEventHandler,
				'OSP_DISABLE_CONTROLS': <portlet:namespace/>disableControlsEventHandler
		},
		loadCanvas: <portlet:namespace/>loadString,
		disabled: JSON.parse( '<%=visualizerConfig.disabled%>')
};

var <portlet:namespace/>visualizer;
<portlet:namespace/>visualizer = OSP.Visualizer(<portlet:namespace/>config);
<portlet:namespace/>processInitAction( JSON.parse( '<%=visualizerConfig.initData%>') );

/***********************************************************************
 * Canvas functions
 ***********************************************************************/
function <portlet:namespace/>loadString( jsonData, changeAlert ){
	if( jsonData.type_ === OSP.Enumeration.PathType.CONTENT){
		$('#<portlet:namespace/>canvas').val(Liferay.Util.unescapeHTML(jsonData.content_));
	}
	else{
		alert( '[String Editor]Un-handled dataType: '+jsonData.type_);
	}
}

function <portlet:namespace/>processInitAction( jsonInitData ){
	if( ! jsonInitData.type_ ){	
		jsonInitData.type_ = OSP.Enumeration.PathType.CONTENT;
		jsonInitData.content_ = '';
	}
	
	<portlet:namespace/>visualizer.processInitAction( jsonInitData, true );
	$('#<portlet:namespace/>canvas').attr('disabled', <portlet:namespace/>disabled );
}
/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
$('#<portlet:namespace/>canvas').on('change', function(){
	if( <portlet:namespace/>disabled )	return;
	
	var jsonData = {
		type_: OSP.Enumeration.PathType.CONTENT,
		content_: $(this).val()
	};
	
	<portlet:namespace/>visualizer.fireDataChangedEvent( jsonData );
});


/***********************************************************************
 * Handling OSP Events and event handlers
 ***********************************************************************/
function <portlet:namespace/>loadDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>loadDataEventHandler] ', data );
	
	<portlet:namespace/>visualizer.loadCanvas( data, false );
}

function <portlet:namespace/>requestDataEventHandler( data, params ){
	console.log('[<portlet:namespace/>requestDataEventHandler]', data, params);
		
	var jsonData = {
			type_: OSP.Enumeration.PathType.CONTENT,
			content_: $('#<portlet:namespace/>canvas').val()
	};
	
	<portlet:namespace/>visualizer.fireResponseDataEvent(jsonData, params );
}

function <portlet:namespace/>initializeEventHandler( data, params ){
	console.log('[<portlet:namespace/>initializeEventHandler] ');
	
	var jsonData = {
			type_: OSP.Enumeration.PathType.CONTENT,
			content_: ''
	};
	<portlet:namespace/>visualizer.processInitAction( jsonData, true);
}

function <portlet:namespace/>disableControlsEventHandler( data, params ){
	console.log('[<portlet:namespace/>disableControlsEventHandler] ');
	
	<portlet:namespace/>disabled = params.disabled;
	$('#<portlet:namespace/>canvas').attr('disabled', <portlet:namespace/>disabled );
}

</script>

