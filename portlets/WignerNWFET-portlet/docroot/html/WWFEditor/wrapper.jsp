<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/osp-editor.css"/>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
%>

<div class="container-fluid osp-editor">
	<div class="row-fluid header">
		<div class="col-sm-10" id="<portlet:namespace/>title"></div>
		<div class="col-sm-2 text-right" >
			<button class="btn btn-primary" type="button" onclick="javascript:<portlet:namespace/>takeSample()">
					Sample
			</button>
		</div>
	</div>			
	<div class="row-fluid frame" >
		<iframe class="col-sm-12 iframe-canvas" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/WWFEditor/wwfeditor.jsp"></iframe>
	</div>
</div>

<script>
<portlet:namespace/>passNamespace();

var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');


if( <portlet:namespace/>eventEnable === false ){
	<portlet:namespace/>initData = JSON.parse('<%=inputData%>');
	if( !<portlet:namespace/>initData.repositoryType_ ){
		<portlet:namespace/>initData.repositoryType_ = '<%=OSPRepositoryTypes.USER_HOME.toString()%>';
	}

	<portlet:namespace/>loadWWFEditor( OSP.Util.toJSON(<portlet:namespace/>initData) );
}

Liferay.on(
	'OSP_HANDSHAKE', 
	function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
	
		console.log('[WWFEditor wrapper]'+myId+' >> OSP_HANDSHAKE: ['+e.portletId+']', e);
		
		<portlet:namespace/>connector = e.portletId;		
				
		if( e.mode )
			<portlet:namespace/>mode = e.mode;
		else
			<portlet:namespace/>mode = 'VIEW';
			
		var events = [
			OSP.Event.OSP_EVENTS_REGISTERED,
			OSP.Event.OSP_LOAD_DATA
		];
		var eventData = {
			portletId: myId,
			targetPortlet: <portlet:namespace/>connector,
			data: events
		};
		Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
	}
);

Liferay.on(
	'OSP_EVENTS_REGISTERED', 
	function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
	
		console.log('[WWFEditor wrapper]'+myId+' >> OSP_EVENTS_REGISTERED: ['+e.portletId+']', e);
	}
);

function <portlet:namespace/>passNamespace(){
	setTimeout(
		function(){
			var iframe = document.getElementById('<portlet:namespace/>canvas');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

			if ( iframeDoc.readyState  == 'complete' && iframe.contentWindow.setNamespace ) {
				iframe.contentWindow.setNamespace( '<portlet:namespace/>');
			}
			else{
				<portlet:namespace/>passNamespace();
			}
		}, 
		10
	);
}
		 
Liferay.on( 
	'OSP_LOAD_DATA', 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
	
		console.log('[WWFEditor wrapper]'+myId+' >> OSP_LOAD_DATA: ['+e.portletId+']', e);
		
		var <portlet:namespace/>initData = e.data;
		if( ! <portlet:namespace/>initData.repositoryType_){
			<portlet:namespace/>initData.repositoryType_ = 'USER_HOME';
		}
		
		<portlet:namespace/>loadWWFEditor( OSP.Util.toJSON(<portlet:namespace/>initData) );
	}
);

function <portlet:namespace/>loadWWFEditor( inputData ){
	switch( inputData.type_ ){
		case 'file':
			<portlet:namespace/>readFileContent( inputData );
			<portlet:namespace/>currentData = inputData;
			break;
		case 'fileContent':
		case 'context':
			<portlet:namespace/>loadParametersDrawDevice(inputData.context_);
			<portlet:namespace/>currentData = inputData;
			break;
		case 'dlFileEntry':
		case 'folder':
		case 'ext':
		case 'url':
			alert.log('Un-supported data type: '+inputData.type() );
			break;
		default:
			console.log('[WWFEditor wrapper]'+'InputData not available: ', inputData );
			break;
	}
}

function <portlet:namespace/>readFileContent( inputData ){
	var data = Liferay.Util.ns( 
			'<portlet:namespace/>',
			{
				command: 'READ_FILE',
				repositoryType: inputData.repositoryType_,
				pathType: inputData.type_,
				parentPath: inputData.parent_,
				fileName: inputData.name_
			}
	);
		
	$.ajax({
		url: '<%= serveResourceURL.toString()%>', 
		type: 'POST',
		data  : data,
		dataType : 'json',
		success: function(result) {
			<portlet:namespace/>loadParametersDrawDevice(result.context_);
		},
		error:function(result,e){
			console.log('[WWFEditor wrapper]'+result);
			console.log('[WWFEditor wrapper]'+'AJAX ERROR-->', inputData);
		}
	});
}

function <portlet:namespace/>loadParametersDrawDevice( data ){
    // Get a handle to the iframe element
    setTimeout(
    	function(){
    	    var iframe = document.getElementById('<portlet:namespace/>canvas');
    	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

    	    // Check if loading is complete
    	    console.log( 'iframeDoc.readyState', iframeDoc.readyState);
    	    if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.loadParametersDrawDevice ) {
   	        	iframe.contentWindow.loadParametersDrawDevice(data);
    	    } 
    	    else{
    	    	<portlet:namespace/>loadParametersDrawDevice( data );
    	    }
    	},
    	10
    );
}
		
Liferay.on(
	'OSP_REQUEST_DATA', 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		
		if( e.targetPortlet !== myId )	return;
		
		console.log('[WWFEditor wrapper]'+myId+' >> OSP_REQUEST_DATA: ['+e.portletId+']', e);

		var content = $('#<portlet:namespace/>canvas').prop('contentWindow').getParametersFromDrawDevice();
		
		var eventData = {
			portletId: myId,
			targetPortlet: <portlet:namespace/>connector,
			data: {
				type_: 'fileContent',
				repositoryType_: <portlet:namespace/>initData.repositoryType_,
				context_: content,
				params: e.params
			}
		};
		
		Liferay.fire( 'OSP_RESPONSE_DATA', data );
	}
);
		

Liferay.on(
	'OSP_INITIALIZE',
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		
		if( e.targetPortlet !== myId )	return;
		
		console.log('[WWFEditor wrapper]'+myId+' >> OSP_INITIALIZE: ['+e.portletId+']', e);
		
		<portlet:namespace/>initialize();
	}
);

function <portlet:namespace/>initialize(){
	setTimeout(
		function(){
			var iframe = document.getElementById('<portlet:namespace/>canvas');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

			// Check if loading is complete
			if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.initialize ) {
				iframe.contentWindow.initialize();
			} 
			else{
				<portlet:namespace/>initialize();
			}
		},
		10
	);
}

function <portlet:namespace/>fireLocalWignerFETEvent( data ){
	console.log('[WWFEditor wrapper]'+'fireLocalWignerFETEvent', data );
	
	//alert(data.input1);
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: data
	};
	
	Liferay.fire('LOCAL_WignerFET_Draw_Device', eventData);
	//Liferay.fire('LOCAL_WignerFET_Draw_Device', eventData);
}

function <portlet:namespace/>fireOnChangeEvent( data ){
	console.log('[WWFEditor wrapper]'+'fireOnChangeEvent', data );
	var inputData = {};
	inputData.type_ = 'fileContent';
	
	if( $.isEmptyObject(<portlet:namespace/>initData) )
		inputData.repositoryType_ = '<%=OSPRepositoryTypes.USER_HOME.toString()%>';
	else
		inputData.repositoryType_ = <portlet:namespace/>initData.repositoryType_;
	inputData.context_ = data;
	
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: inputData
	};
	
	//alert(data.input1);
	
	Liferay.fire('OSP_DATA_CHANGED', eventData);
}
</script>