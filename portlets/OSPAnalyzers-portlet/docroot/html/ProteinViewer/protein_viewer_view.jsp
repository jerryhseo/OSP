<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<script src="<%=request.getContextPath()%>/js/pv-master/bio-pv.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pv-master/js/modernizr-2.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pv-master/js/foundation-5.4.7.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/pv-master/css/foundation.css"/>

<style>
.protein-viewer-portlet{
	padding:0;
	margin: 0;
	overflow:hidden;
}
.protein-viewer-portlet .fixed-top-button-first{
	position:absolute;
	top:10px;
	right: 30px;
	width:20px;
	height:20px;
	margin-top:10px;
	z-index:100;
}
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

<div class="row-fluid common-analyzer-portlet" id="<portlet:namespace/>canvasPanel" style="margin:0;">
	<div class="dropdown-wrapper" >
      <div class="dropdown">
        <i class="icon-reorder icon-menu"></i>
        <!-- Link or button to toggle dropdown -->
        <div class="dropdown-content">
          <div class="dropdown-item" id="<portlet:namespace/>upload"><i class="icon-upload"> Upload</i></div>
        </div>
      </div>
    </div>

	<div id="<portlet:namespace/>canvas" style="overflow:hidden;margin-top:0px;"></div>

	<div id="<portlet:namespace/>hiddenSection" style="display:none;">
		 <input type="file" id="<portlet:namespace/>selectFile" />
		 <a id="<portlet:namespace/>download" ></a>
		<div id="<portlet:namespace/>fileExplorer" title="Select a file" ></div>
		<img id="<portlet:namespace/>loadingBox" src="<%=request.getContextPath()%>/images/processing.gif" width="200" style="display: none;"/>	
	</div>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<script type="text/javascript">
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector;
var <portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPEditorsportlet_INSTANCE_protein";
var <portlet:namespace/>initData;

var <portlet:namespace/>currentData;

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( !<%=eventEnable%> ){
  <portlet:namespace/>connector = '<%=connector%>';
  <portlet:namespace/>initData = new OSP.InputData(JSON.parse('<%=inputData%>'));
  <portlet:namespace/>loadProtein(initData);
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
		  var eventData = {
	         portletId: myId,
	         targetPortlet: <portlet:namespace/>connector
	      };
	      Liferay.fire(OSP.Event.OSP_REQUEST_OUTPUT_PATH, eventData);
		}
	}
);

Liferay.on( 
  OSP.Event.OSP_LOAD_DATA, 
  function(e){
    var myId = '<%=portletDisplay.getId()%>';
	if( e.targetPortlet === myId ){
	  <portlet:namespace/>loadProtein(e.data);
	}
  }
);


/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadProtein( inputData ){
	<portlet:namespace/>currentData = inputData;
	
	var canvas = $('#<portlet:namespace/>canvas');
	//canvas.empty();

	AUI().use("liferay-portlet-url", function(a) {
		var serveResourceUrl = Liferay.PortletURL.createResourceURL();
		serveResourceUrl.setPortletId('<%=portletDisplay.getId()%>');
		serveResourceUrl.setParameter('command', 'READ_FILE');
		serveResourceUrl.setParameter('pathType', inputData.type());
		serveResourceUrl.setParameter('parentPath', inputData.parent());
		serveResourceUrl.setParameter('fileName', inputData.fileName());
		serveResourceUrl.setParameter('relative', inputData.relative());
		//canvas.iviewer('center');
		var options = {
				  width: canvas.innerWidth(),
				  height: canvas.innerHeight()-12,
				  antialias: true,
				  quality : 'medium'
		};

		var proteinViewer = pv.Viewer(canvas[0], options);
		pv.io.fetchPdb( serveResourceUrl.toString(), function(structure) {
			proteinViewer.renderAs('protein', structure, 'cartoon', { color : color.ssSuccession() });
			proteinViewer.centerOn(structure);
		});
	});
}

</script>