<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

 <%
 PortletPreferences preferences = portletDisplay.getPortletSetup();
 preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
 preferences.store();
 
 boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
 String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
 String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
 String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
 %>

<div class="container-fluid osp-analyzer">
	<div class="row-fluid no-header-frame">
		<div class ="col-sm-12 canvas"  id="<portlet:namespace/>canvas"></div>
	</div>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>eventEnable = <%=eventEnable%>;
var <portlet:namespace/>mode = '<%=mode%>';

var <portlet:namespace/>dataType;
var <portlet:namespace/>initData;

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if(!<portlet:namespace/>eventEnable){
  $(function(){
    $("#<portlet:namespace/>canvas").css("height", $(document).height());
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
			console.log(myId + ' activated by OSP_EVENTS_REGISTERED.');
			
			var eventData = {
								portletId: '<%=portletDisplay.getId()%>',
								targetPortlet: <portlet:namespace/>connector
						};
						
			Liferay.fire( OSP.Event.OSP_REQUEST_DATA_STRUCTURE, eventData );
		}
	}
);
 
Liferay.on(OSP.Event.OSP_LOAD_DATA, function(eventData){
  var myId = '<%=portletDisplay.getId()%>';
  if( eventData.targetPortlet === myId ){
    if( eventData.data){
      <portlet:namespace/>loadStructure(new OSP.InputData(eventData.data));
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
				<portlet:namespace/>fileName: inputData.name(),
				<portlet:namespace/>repositoryType: inputData.repositoryType()
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
</script>