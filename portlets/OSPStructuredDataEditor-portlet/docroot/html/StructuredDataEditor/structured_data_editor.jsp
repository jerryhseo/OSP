<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<%-- <script src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/js/osp/osp_basic_object.js"></script>
<script src="<%=request.getContextPath()%>/js/osp/osp_super_class.js"></script>
<script src="<%=request.getContextPath()%>/js/osp/osp_datatype.js"></script> --%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/structured-data-editor-portlet.css"/>

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

<div class="row-fluid structured-data-editor-portlet editor-portlet">
	<div class="span12">
		<div class="row-fluid" id="<portlet:namespace/>choicePanel" style="padding:10px 0 0 10px;">
			<div class="offset2 span2 dropdown-wrapper" id="<portlet:namespace/>menuSection">
				<div class="dropdown">
                  <i class="icon-reorder icon-menu"></i>
					<!-- Link or button to toggle dropdown -->
					<div class="dropdown-content">
                        <div class="dropdown-item"  id="<portlet:namespace/>sample"><i class="icon-file"> Take sample</i></div>
					</div>
				</div>
			</div>	
		</div>
		
		<div class="row-fluid" id="<portlet:namespace/>canvasPanel">
			<div id="<portlet:namespace/>canvas" class="span12"></div>
		</div>
	</div>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>dataType;
var <portlet:namespace/>initData;
var <portlet:namespace/>action = '<%=action%>';


/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( !<%=eventEnable%> ){
	<portlet:namespace/>initData = new OSP.InputData(JSON.parse('<%=inputData%>'));
	
	<portlet:namespace/>loadStructure( <portlet:namespace/>initData );
}


/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
 $('#<portlet:namespace/>sample').click(function(){
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId: myId,
				targetPortlet: <portlet:namespace/>connector
		};
		
		Liferay.fire( OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, eventData);
});


$('#<portlet:namespace/>canvas').on('change', function(){
		var inputData = new OSP.InputData();
		inputData.type( OSP.Enumeration.PathType.STRUCTURED_DATA );
		inputData.context( <portlet:namespace/>dataType.structure().clone() );
		
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
			if( myId === e.targetPortlet ){
				<portlet:namespace/>connector = e.portletId;
				<portlet:namespace/>action = e.action;
				
				var events = [
					OSP.Event.OSP_LOAD_DATA,
					OSP.Event.OSP_EVENTS_REGISTERED,
					OSP.Event.OSP_REQUEST_DATA
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
			if( e.targetPortlet === myId ){
				var eventData = {
						portletId: myId,
						targetPortlet: <portlet:namespace/>connector
				};
				
				//console.log( 'OSP_EVENTS_REGISTERED event received ');
				Liferay.fire( OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, eventData );
			}
		}
);


Liferay.on( 
		OSP.Event.OSP_LOAD_DATA, 
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
			    console.log('SDE OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']');
				var inputData = new OSP.InputData( e.data );
				console.log( inputData );
		
				<portlet:namespace/>loadStructure(inputData);
			}
		}
);

Liferay.on( 
		OSP.Event.OSP_REQUEST_DATA, 
		function(eventData){
			var myId = '<%=portletDisplay.getId()%>';
			if( eventData.targetPortlet === myId ){
				var data = {
								data: <portlet:namespace/>dataType.structure(),
				};
		
				OSP.Event.responseDataToRequest(
						myId, 
						data, 
						eventData );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_INITIALIZE,
		function( e ){
			console.log('<portlet:namespace/> OSP_INITIALIZE: ['+e.portletId+', '+new Date()+']');
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: <portlet:namespace/>connector
			};
			
			Liferay.fire( OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, eventData );
		}
);


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
		//console.log( dataType.structure() );
		break;
	case OSP.Enumeration.PathType.FILE_CONTENT:
		dataType.loadStructure( inputData.context() ); 
		break;
	case OSP.Enumeration.PathType.DLENTRY_ID:
		$.ajax({
			url: '<%=serveResourceURL.toString()%>',
			type:'POST',
			async: false,
			dataType:'json',
			data:{
				<portlet:namespace/>command: 'READ_SAMPLE',
				<portlet:namespace/>action: <portlet:namespace/>action,
				<portlet:namespace/>dlEntryId: inputData.dlEntryId()
			},
			success:function(result){
			    if( result.error ){
                    console.log( '[ERROR] read sample: ' + inputData.dlEntryId());
                    alert( '[ERROR] read sample: ' + inputData.dlEntryId() + '\nPlease contact site manager.');
                }
                else{
                    var inputData = new OSP.InputData( result.success);
                    dataType.loadStructure( result.success );
                }
			},
			error: function(){
			    alert( '[ERROR] Site invalid. \nPlease contact site manager.');
			}
		});
		break;
	case OSP.Enumeration.PathType.FILE:
		$.ajax({
			url: '<%=serveResourceURL.toString()%>',
			type:'POST',
			async: false,
			dataType:'text',
			data:{
				<portlet:namespace/>command: 'READ_FILE',
				<portlet:namespace/>action: <portlet:namespace/>action,
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
	dataType.showStructuredDataEditor(
					'<portlet:namespace/>', 
					$('#<portlet:namespace/>canvas'),
					'<%=request.getContextPath()%>',
					'<%=themeDisplay.getLanguageId()%>');
}
</script>