<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/stringeditor-portlet.css"/>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = (String)renderRequest.getAttribute("inputData");
String connector = (String)renderRequest.getAttribute("connector");
boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
String action = (String)renderRequest.getAttribute("action");
boolean isPopup = LiferayWindowState.isExclusive(request);
%>

<div class="row-fluid editor-portlet stringeditor-portlet " >
    <div class="span12 canvasPanel">
        <p>Enter string value: </p>
        <input type="text" name="<portlet:namespace/>canvas" id="<portlet:namespace/>canvas" />
    </div>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = 'broadcast';
var <portlet:namespace/>action = '<%=action%>';

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/

if( !<%=eventEnable%> ){
	<portlet:namespace/>connector = '<%=connector%>';
	<portlet:namespace/>loadString( new OSP.InputData(JSON.parse('<%=inputData%>')) );
}
	
	
/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
$('#<portlet:namespace/>canvas').on('change', function(){
	var inputData = new OSP.InputData();
	inputData.type( OSP.Enumeration.PathType.CONTEXT );
	inputData.context( $(this).val() );
	
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: OSP.Util.toJSON(inputData)
	};
	
	Liferay.fire(OSP.Event.OSP_DATA_CHANGED, eventData );
});

/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
		OSP.Event.OSP_HANDSHAKE,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( eventData.targetPortlet === myId ){
				<portlet:namespace/>connector = e.portletId;
				if( e.action )
					<portlet:namespace/>action = e.action;
				else
					<portlet:namespace/>action = 'input';
	
				var events = [
					OSP.Event.OSP_EVENTS_REGISTERED,
					OSP.Event.OSP_REQUEST_DATA
					OSP.Event.OSP_LOAD_DATA
				];
				var eventData = {
					portletId: myId,
					targetPortlet: <portlet:namespace/>connector,
					data: events
				};
				Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_EVENTS_REGISTERED,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				// Do nothing
			}
		}
);

Liferay.on( 
		OSP.Event.OSP_REQUEST_DATA, 
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				var data = {
				            type_: OSP.Enumeration.PathType.CONTEXT,
				            context_: $('#<portlet:namespace/>canvas').val() 
				};
				
				var eventData = {
						portletId: myId,
						targetPortlet: e.portletId,
						data: data
				}
				
				Liferay.fire(
						OSP.Event.OSP_RESPONSE_DATA,
						eventData
				);
			}
		}
);

Liferay.on(
        OSP.Event.OSP_LOAD_DATA,
        function( e ){
        	var myId = '<%=portletDisplay.getId()%>';
        	if( e.targetPortlet !== myId ){
        		return;
        	}
        	
        	<portlet:namespace/>loadString( new OSP.InputData( e.data ) );
        }
);

Liferay.on(
		OSP.Event.OSP_INITIALIZE,
		function( e ){
			if( e.targetPortlet === '<%=portletDisplay.getId()%>'){
				$('#<portlet:namespace/>canvas').val('');
			}
		}
);

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadString( inputData ){
	if( inputData.type() === OSP.Enumeration.PathType.CONTEXT){
		$('#<portlet:namespace/>canvas').val(Liferay.Util.unescapeHTML(inputData.context()));
	}
	else{
		alert( '[String Editor]Un-handled dataType: '+inputData.type());
	}
}
</script>

