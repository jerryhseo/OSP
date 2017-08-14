<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<style>
.structured-data-viewer-portlet .canvasPanel{ position: relative; vertical-align:middle; width:100%; border:none; height: 92%;}
.structured-data-viewer-portlet .canvas{ position: relative; vertical-align:middle; width:100%; border:none; height: 100%;}

</style>
<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = (String)renderRequest.getAttribute("inputData");
String connector = (String)renderRequest.getAttribute("connector");
boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
boolean isPopup = LiferayWindowState.isExclusive(request);
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<div class="row-fluid common-analyzer-portlet">
	<div class="span12  structured-data-viewer-portlet canvasPanel" id="<portlet:namespace/>canvasPanel" >
		<pre id="<portlet:namespace/>canvas" class="canvas" ></pre>
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
			// Do nothing
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
