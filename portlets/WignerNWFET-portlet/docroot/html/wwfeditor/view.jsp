<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

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

<div class="container-fluid common-editor-portlet ">
	<div class="row-fluid canvas">
		<iframe id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/wwfeditor/wwfeditor.jsp"></iframe>
	</div>

<script>
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';



if( '<%=eventEnable%>' === 'false' ){
	<portlet:namespace/>connector = '<%=connector%>';
	var inputData = '<%=inputData%>';
	
	if(inputData){
		<portlet:namespace/>initData = JSON.parse( inputData );
		<portlet:namespace/>loadProtein();
	}
	
}

Liferay.on(	'OSP_HANDSHAKE', function( e ){
	var myId = '<%=portletDisplay.getId()%>';	
	if( e.targetPortlet === myId )
	{
	    console.log('[wwfeditor] OSP_HANDSHAKE: ['+e.portletId+', '+new Date()+']');
		<portlet:namespace/>connector = e.portletId;		
//		$('#<portlet:namespace/>canvas').css('height', eventData.height);
			
		var events = [
						'OSP_EVENTS_REGISTERED'							
					];
		var eventData = {
						portletId: myId,
						targetPortlet: <portlet:namespace/>connector,
						data: events
		};			
		
		
		Liferay.fire('OSP_REGISTER_EVENTS', eventData);
	}
});

Liferay.on('OSP_EVENTS_REGISTERED', function( e ){
   var myId = '<%=portletDisplay.getId()%>';
   		
   if( e.targetPortlet === myId )
   {
	   console.log('[wwfeditor] OSP_EVENTS_REGISTERED: ['+e.portletId+', '+new Date()+']', e.data);
	   
   		<portlet:namespace/>connector = e.portletId; 
   		<portlet:namespace/>passNamespace();
	}
});

function <portlet:namespace/>passNamespace(){
    // Get a handle to the iframe element
    setTimeout(
               function(){
            	    var iframe = document.getElementById('<portlet:namespace/>canvas');
            	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
            	    
					console.log('iframeDoc.readyState: '+iframeDoc.readyState);
            	    if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.setNamespace ) {
            	        iframe.contentWindow.setNamespace( '<portlet:namespace/>');
            	    }
            	    else{
            	    	<portlet:namespace/>passNamespace();
            	    }
               }, 
               10
	);
}
		 
Liferay.on( 'OSP_LOAD_DATA', function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if( e.targetPortlet === myId ){
		var data = e.data;
		console.log('[wwfeditor] OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']', e.data);
		
		// read data from file. Not implemented yet!
		var inputData = e.data;
		if( ! inputData.repositoryType_){
			inputData.repositoryType_ = 'USER_HOME';
		}
		
		switch( inputData.type_ ){
		case 'file':
			$.ajax({
				url: '<%=serveResourceURL.toString()%>',
				type: 'post',
				dataType: 'text',
				data:{
					<portlet:namespace/>command: 'READ_FILE',
					<portlet:namespace/>repositoryType: inputData.repositoryType_,
					<portlet:namespace/>filePath: <portlet:namespace/>mergePath( inputData.parent_, inputData.name_ )
				},
				success: function( result ){
					console.log( result );
					<portlet:namespace/>loadParametersDrawDevice( result );
				},
				error: function(){
					console.log( 'AJAX error');
				}
			});
			break;
		case 'fileContent':
		    <portlet:namespace/>loadParametersDrawDevice( inputData.context_ );
		    break;
		default:
		    console.log('Not supported yet: '+inputData.type_ );
			break;
		}
	}
});

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
		
Liferay.on( 'OSP_REQUEST_DATA', function(e){
	var myId = '<%=portletDisplay.getId()%>';

     
	if( e.targetPortlet === myId ){
		console.log('[wwfeditor] OSP_REQUEST_DATA: ['+e.portletId+', '+new Date()+']', e.data);
		
		var params; 
		$('#<portlet:namespace/>canvas').each(function(){
			params = $(this).prop('contentWindow').getParametersFromDrawDevice();
		});
		
		var eventData = {
			portletId: myId,
			targetPortlet: <portlet:namespace/>connector,
			data: {
				type_: 'fileContent',
				context_: params
			}
		};
		
		Liferay.fire( 'OSP_RESPONSE_DATA', data );
	}
});
		

Liferay.on(
		'OSP_INITIALIZE',
		function(e){
			console.log('[wwfeditor] OSP_INITIALIZE: ['+e.portletId+', '+new Date()+']');
			
			<portlet:namespace/>initialize();
		}
);

function <portlet:namespace/>initialize(){
	setTimeout(
	       	function(){
	       	    var iframe = document.getElementById('<portlet:namespace/>canvas');
	       	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	       	    // Check if loading is complete
	       	    console.log( 'iframeDoc.readyState', iframeDoc.readyState);
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

function <portlet:namespace/>mergePath( parent, child ){
	if( !parent && !child )	return '';
	if( !parent )
		return child;
	if( !child )
		return parent;
	
	return parent+'/'+child;
}

function <portlet:namespace/>fireLocalWignerFETEvent( data ){
	console.log('fireLocalWignerFETEvent', data );
	
	//alert(data.input1);
	/*
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: data
	};
	
	Liferay.fire('LOCAL_WignerFET_Draw_Device', eventData);
	*/
	//Liferay.fire('LOCAL_WignerFET_Draw_Device', eventData);
	
	var inputData = {};
	inputData.type_ = 'context';
	inputData.context_ = data;
	
	var eventData = {
					event: 'OSP_LOAD_DATA',
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: <portlet:namespace/>connector,
					data: inputData
	};
	Liferay.fire('OSP_REQUEST_SPREAD_TO_PORT', eventData);
}

function <portlet:namespace/>fireOnChangeEvent( data ){
	console.log('fireOnChangeEvent', data );
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: data
	};
	
	//alert(data.input1);
	
	Liferay.fire('OSP_DATA_CHANGED', eventData);
}
</script>