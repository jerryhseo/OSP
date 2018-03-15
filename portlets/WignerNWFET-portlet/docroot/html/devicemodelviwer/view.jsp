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

<div class="container-fluid common-analyzer-portlet ">
	<div class="row-fluid canvas">
		<iframe id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/devicemodelviwer/DeviceModelViewer.jsp"></iframe>	
	</div>
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
		<portlet:namespace/>drawDevice(<portlet:namespace/>initData.context_);
	}
}

Liferay.on('OSP_HANDSHAKE', function( e ){
	var myId = '<%=portletDisplay.getId()%>';	
	if( e.targetPortlet === myId )
	{
		<portlet:namespace/>connector = e.portletId;		
//		$('#<portlet:namespace/>canvas').css('height', eventData.height);
		if( e.data )
		{		
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
	}
});

Liferay.on('OSP_EVENTS_REGISTERED', function( e ){
	   var myId = '<%=portletDisplay.getId()%>';
	   		
	   if( e.targetPortlet === myId )
	   {
		   console.log('[devicemodelviwer] OSP_EVENTS_REGISTERED: ['+e.portletId+', '+new Date()+']');
		   
	   		<portlet:namespace/>connector = e.portletId; 
	   		
	   		<portlet:namespace/>passNamespace();
		}
});

function <portlet:namespace/>passNamespace(){
    setTimeout(
         function(){
      	    var iframe = document.getElementById('<portlet:namespace/>canvas');
      	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

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

Liferay.on( 'OSP_LOAD_DATA', function(eventData){	
	
	var myId = '<%=portletDisplay.getId()%>';
	if( eventData.targetPortlet !== myId )	return;
	
	var data = eventData.data;
	console.log( 'DeviceModelViewer LoadData: ', data );
	
	switch( data.type_ ){
		case 'context':
			<portlet:namespace/>drawDevice( data.context_ );
			break;
		default:
			console.log('DeviceModelViewer: Unsupport data type');
	}
});

/*
Liferay.on( 'LOCAL_WignerFET_Draw_Device', function(eventData){	
	
	var myId = '<%=portletDisplay.getId()%>';
	
	
	var data = eventData.data;
	console.log( 'DeviceModelViewer: ', data );
			
	<portlet:namespace/>drawDevice( data );
});
*/

function <portlet:namespace/>drawDevice( data ){
    // Get a handle to the iframe element
    setTimeout(
    	function(){
    	    var iframe = document.getElementById('<portlet:namespace/>canvas');
    	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

    	    // Check if loading is complete
    	    if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.Draw_Device ) {
   	        	iframe.contentWindow.Draw_Device(data);
    	    } 
    	    else{
    	    	<portlet:namespace/>drawDevice( data );
    	    }
    	},
    	10
    );
}
</script>