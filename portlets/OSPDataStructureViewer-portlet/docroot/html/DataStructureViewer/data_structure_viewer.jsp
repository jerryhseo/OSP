<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

 <%
 PortletPreferences preferences = portletDisplay.getPortletSetup();
 preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
 preferences.store();
 
 boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
 String inputData = (String)renderRequest.getAttribute("inputData");
 String connector = (String)renderRequest.getAttribute("connector");
 %>

<aui:container fluid="true" cssClass="common-analyzer-portlet">
	<aui:row  fluid="true">
		<aui:col span="12" id="canvasPanel" >
			<div  id="<portlet:namespace/>canvas" style="width:100%;"></div>
		</aui:col>
	</aui:row>
</aui:container>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<aui:script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>initialized = false;
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>eventEnable = <%=eventEnable%>;

var <portlet:namespace/>dataType;
var <portlet:namespace/>initData;

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if(!<portlet:namespace/>eventEnable){
  $(function(){
    $("#<portlet:namespace/>canvasPanel").css("height", $(document).height());
  });

  var inputData = '<%=inputData%>';
  var initData;
  if(!inputData){
    initData = new OSP.InputData();
  }else{
    initData = new OSP.InputData(JSON.parse(inputData));
  }
  <portlet:namespace/>connector = '<%=connector%>';
  <portlet:namespace/>initData = initData;
  <portlet:namespace/>loadStructure(initData);
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
 
Liferay.on(OSP.Event.OSP_LOAD_DATA, function(eventData){
  var myId = '<%=portletDisplay.getId()%>';
  if( eventData.targetPortlet === myId ){
    if( eventData.data){
      <portlet:namespace/>loadStructure(eventData.data);
    }
  }
});

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadStructure( inputData ){
	if( !<portlet:namespace/>dataType ){
		<portlet:namespace/>dataType = new OSP.DataType();
	}
	var dataType = <portlet:namespace/>dataType;
	
	switch( inputData.type() ){
	case OSP.Enumeration.PathType.STRUCTURED_DATA:
		dataType.deserializeStructure(inputData.context());
		break;
	case OSP.Enumeration.PathType.FILE:
		$.ajax({
			url: '<%=serveResourceURL.toString()%>',
			type:'POST',
			async: false,
			dataType:'text',
			data:{
				<portlet:namespace/>command: 'READ_FILE',
				<portlet:namespace/>parentPath: inputData.parent(),
				<portlet:namespace/>fileName: inputData.name()
			},
			success:function(result){
				dataType.loadStructure( result );
			},
			error: function(){
				console.log('[ERROR] AJAX FAILED during READ_SAMPLE');
			}
		});
		break;
	default:
		alert( 'Un-known dataType: '+inputData.type());
	}
		
	$('#<portlet:namespace/>canvas').empty();
	dataType.showDataStructureForm(
					'<portlet:namespace/>', 
					$('#<portlet:namespace/>canvas'),
					'<%=request.getContextPath()%>',
					'<%=themeDisplay.getLanguageId()%>');
}
</aui:script>