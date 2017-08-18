<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@ include file="../init.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<style>
.html-viewer-portlet {
	height: 100%;
   	min-height:400px;
   	max-height:1000px;
   	margin: 0;
   	overflow:hidden;
}
.html-viewer-portlet iframe {
   	border:none;
   	width:100%;
   	height:100%;
   	margin:0;
}
</style>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
 
 <%
 PortletPreferences preferences = portletDisplay.getPortletSetup();
 preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
 preferences.store();
 
 boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
 String inputData = (String)renderRequest.getAttribute("inputData");
 String connector = (String)renderRequest.getAttribute("connector");
 String action = (String)renderRequest.getAttribute("action");
 %>
 
<div class="row-fluid common-analyzer-portlet">
	<div class ="span12" id="<portlet:namespace/>canvasPanel" style="margin:0;">
		<iframe  id="<portlet:namespace/>canvas" ></iframe>
	</div>
</div>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>eventEnable = <%=eventEnable%>;
var <portlet:namespace/>action = '<%=action%>';

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if(!<portlet:namespace/>eventEnable){
  $(function(){
    $("#<portlet:namespace/>canvasPanel").css("height", $(document).height());
  });

  var inputData = new OSP.InputData( JSON.parse('<%=inputData%>') );
  <portlet:namespace/>connector = '<%=connector%>';
  <portlet:namespace/>loadHtml(inputData);
}

/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
  OSP.Event.OSP_HANDSHAKE,
  function(e){
    var myId = '<%=portletDisplay.getId()%>';
    if(e.targetPortlet === myId){
      <portlet:namespace/>connector = e.portletId;
      <portlet:namespace/>action = e.action;
      var events = [ 
          OSP.Event.OSP_EVENTS_REGISTERED, 
          OSP.Event.OSP_LOAD_DATA
        ];
      var eventData = {
          portletId : myId,
          targetPortlet : <portlet:namespace/>connector,
          data : events
        };
      Liferay.fire(OSP.Event.OSP_REGISTER_EVENTS, eventData);
    }
  });

Liferay.on(
  OSP.Event.OSP_EVENTS_REGISTERED, 
  function(e) {
    
    var myId = '<%=portletDisplay.getId()%>';
    if(e.targetPortlet === myId){
      var eventData = {
         portletId: myId,
         targetPortlet: <portlet:namespace/>connector
      };
      Liferay.fire(OSP.Event.OSP_REQUEST_OUTPUT_PATH, eventData);
    }
  });
 
Liferay.on(OSP.Event.OSP_LOAD_DATA, function(e){
  var myId = '<%=portletDisplay.getId()%>';
  if( e.targetPortlet === myId ){
    if( e.data){
      <portlet:namespace/>loadHtml( new OSP.InputData( e.data) );
    }
  }
});

Liferay.on(
		OSP.Event.OSP_REFRESH_OUTPUT_VIEW,
		function(e){
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: <portlet:namespace/>connector
			};

			Liferay.fire(OSP.Event.OSP_REQUEST_OUTPUT_PATH, eventData);
		}
);


/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadHtml(indexPath){
	if(indexPath.type() === OSP.Enumeration.PathType.FILE){
	    var serveResourceURL;
        serveResourceURL = Liferay.PortletURL.createResourceURL();
        serveResourceURL.setPortletId('<%=portletDisplay.getId()%>');
        serveResourceURL.setParameter('parentPath', indexPath.parent());
        serveResourceURL.setParameter('pathType', indexPath.type());
        serveResourceURL.setParameter('fileName', indexPath.name());
        serveResourceURL.setParameter('relative', indexPath.relative());
        serveResourceURL.setParameter('command', 'READ_FILE');
        $('#<portlet:namespace/>canvas').attr('src', serveResourceURL.toString());
	}else if(indexPath.type() == OSP.Enumeration.PathType.URL){
		$('#<portlet:namespace/>canvas').attr('src', indexPath.uri());
	}
}
</script>
