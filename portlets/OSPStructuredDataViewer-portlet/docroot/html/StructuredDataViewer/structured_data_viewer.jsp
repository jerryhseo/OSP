<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<div class="container-fluid osp-analyzer">
	<div class="row-fluid no-header-frame">
		<div class="col-sm-12 canvas" >
			<pre id="<portlet:namespace/>canvas"></pre>
		</div>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector;
var <portlet:namespace/>initData;
var <portlet:namespace/>dataType = new OSP.DataType();

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( '<%=eventEnable%>' == false ){
	<portlet:namespace/>connector = '<%=connector%>';
	var inputData = new OSP.InputData(JSON.parse('<%=inputData%>'));
	
	if( inputData.type() !== OSP.Enumeration.PathType.STRUCTURED_DATA ){
		alert('[ERROR] StructuredDataViewer: Un-recognizable input data type: '+inputData.type() );
	}
	else{
		<portlet:namespace/>initData = inputData;
		<portlet:namespace/>dataType.deserializeStructure( JSON.parse(inputData.context()) );
	}
}
		

/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
 Liferay.on(
	OSP.Event.OSP_HANDSHAKE,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet === myId ){
			<portlet:namespace/>connector = e.portletId;
			if( e.action )
				<portlet:namespace/>action = e.action;
			else
				<portlet:namespace/>action = 'output';
				
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
	}
);

Liferay.on(
	OSP.Event.OSP_EVENTS_REGISTERED,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if(e.targetPortlet === myId){
			var eventData = {
								portletId: '<%=portletDisplay.getId()%>',
								targetPortlet: <portlet:namespace/>connector
						};
						
			Liferay.fire( OSP.Event.OSP_REQUEST_DATA_STRUCTURE, eventData );
		}
	}
);

Liferay.on( 
  OSP.Event.OSP_LOAD_DATA, 
  function(e){
    var myId = '<%=portletDisplay.getId()%>';
	if( e.targetPortlet === myId ){
		<portlet:namespace/>dataType.deserializeStructure( e.data.context_);
		<portlet:namespace/>loadStructuredData( <portlet:namespace/>dataType );
	}
  }
);

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadStructuredData( dataType ){
	dataType.showStructuredDataViewer(
			'<portlet:namespace/>',
			$('#<portlet:namespace/>canvas'),
			'<%=renderRequest.getContextPath()%>',
			'<%=themeDisplay.getLanguageId()%>'
	);
}

</script>
