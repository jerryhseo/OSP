<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
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
 
 boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
 String inputData = GetterUtil.getString( renderRequest.getAttribute("inputData"), "{}");
 String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
 String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
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
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');
var <portlet:namespace/>mode = '<%=mode%>';

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
 //for test...
 //<portlet:namespace/>eventEnable = false;

if(!<portlet:namespace/>eventEnable){
  $(function(){
    $("#<portlet:namespace/>canvasPanel").css("height", $(document).height());
  });

  var inputData = '<%=inputData%>';
  var initData;
  if( !inputData ){
      initData = new OSP.InputData();
  }
  else{
      initData = new OSP.InputData(JSON.parse(inputData));
  }
  
  //initData.type('file');
  //initData.parent('Map');
  //initData.name('Map.html')

  <portlet:namespace/>loadHtml(initData);
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
      <portlet:namespace/>mode = e.mode;
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
			console.log(myId + ' activated by OSP_EVENTS_REGISTERED.');
		}
	}
);
 
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
	    $.ajax({
				url: '<%=serveResourceURL.toString()%>',
				type:'POST',
				data:{
	    				<portlet:namespace/>command: 'GET_COPIED_TEMP_FILE_PATH',
						<portlet:namespace/>repositoryType: indexPath.repositoryType(),
				        <portlet:namespace/>parentPath: indexPath.parent(),
	    				<portlet:namespace/>pathType: indexPath.type(),
	    				<portlet:namespace/>fileName: indexPath.name(),
	    				<portlet:namespace/>relative: indexPath.relative()
				},
				dataType:'text',
				success: function( tempPath ){
				    var url = '<%=request.getContextPath()%>';
				    url += '/' + tempPath + '/'+indexPath.name();
				    $('#<portlet:namespace/>canvas').attr('src', url);
				}
	    });
	}else if(indexPath.type() == OSP.Enumeration.PathType.URL){
		$('#<portlet:namespace/>canvas').attr('src', indexPath.uri());
	}
}
</script>
