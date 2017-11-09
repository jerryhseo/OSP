<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

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
String action = (String)renderRequest.getAttribute("action");
boolean isPopup = LiferayWindowState.isExclusive(request);
%>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<portlet:renderURL var="renderURL">
	<portlet:param name="jspPage" value="/html/ProteinViewer/load_protein.jsp"/>
</portlet:renderURL>

<div class="row-fluid common-analyzer-portlet" id="<portlet:namespace/>canvasPanel" style="margin:0;">
	<div class="dropdown-wrapper" >
		<div class="dropdown">
		<i class="icon-reorder icon-menu"></i>
		<!-- Link or button to toggle dropdown -->
		<div class="dropdown-content">
			<div class="dropdown-item" id="<portlet:namespace/>openServer"><i class="icon-folder-open"> Open server...</i></div>
		</div>
	</div>

	<div class="row-fluid canvas-wrapper" id="<portlet:namespace/>canvasPanel" >
		<iframe class="span12 canvas" id="<portlet:namespace/>canvas" align="center" src="<%=renderURL.toString()%>"></iframe>
	</div>

	<div id="<portlet:namespace/>hiddenSection" style="display:none;">
		 <input type="file" id="<portlet:namespace/>selectFile" />
		 <a id="<portlet:namespace/>download" ></a>
		<div id="<portlet:namespace/>fileExplorer" title="Select a file" ></div>
		<img id="<portlet:namespace/>loadingBox" src="<%=request.getContextPath()%>/images/processing.gif" width="200" style="display: none;"/>	
	</div>
</div>

<script type="text/javascript">
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPEditorsportlet_INSTANCE_protein" +
								"<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>action = '<%=action%>';


/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( !<%=eventEnable%> ){
	var inputData = '<%=inputData%>';
	
	if(!inputData){
		<portlet:namespace/>initData = new OSP.InputData();
	}else{
		<portlet:namespace/>initData = new OSP.InputData(JSON.parse(inputData));
	}
	
	<portlet:namespace/>loadProtein(initData);
}

$<portlet:namespace/>fileExplorerDialogSection.dialog(
					{
						autoOpen: false,
						resizable: false,
						height: 600,
						width: 450,
						modal: true
					}
);

/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
$('#<portlet:namespace/>openLocal').click(function(){
	$('#<portlet:namespace/>selectFile').click();
});

$('#<portlet:namespace/>openServer').click(function(){
	var inputData;
	if(<portlet:namespace/>initData && (
		<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.URI ||
		<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FILE ||
		<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER ||
		<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.EXT )){
		inputData = <portlet:namespace/>initData;
	}else{
		inputData = new OSP.InputData();
		inputData.type( OSP.Enumeration.PathType.FOLDER );
		inputData.parent('');
		inputData.name('');
	}

	<portlet:namespace/>fileExplorerDialog('VIEW', 'READ', inputData);
});

$('#<portlet:namespace/>download').click(function(){
	<portlet:namespace/>downloadCurrentFile();
});

$("#<portlet:namespace/>file-explorer-ok").click(function(e){
	e.preventDefault();
	var eventData = {
			portletId : '<%=portletDisplay.getId()%>',
			targetPortlet : <portlet:namespace/>fileExplorerId,
			action: "READ"
	};
	
	Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
	
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

$('#<portlet:namespace/>selectFile').bind(
		'change', 
		function(event){
			var input = document.getElementById('<portlet:namespace/>selectFile');
			var reader = new FileReader();

			reader.onload = function (e) {
					$('#<portlet:namespace/>canvas').each(function(){
								console.log( 'ID: '+$(this).attr('id'));
								$(this).one("load", function(){
								$(this).prop('contentWindow').loadJSMolFile(
												e.target.result, 
												$('#<portlet:namespace/>canvas').width(), 
												$('#<portlet:namespace/>canvas').height());
								});
					});

					delete <portlet:namespace/>currentData;
			};

			reader.readAsDataURL(input.files[0]);
		}
);

function <portlet:namespace/>fileExplorerDialog( mode, action, inputData ){
	AUI().use('liferay-portlet-url', function(A){
		var dialogURL = Liferay.PortletURL.createRenderURL();
		
		dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
		dialogURL.setParameter('inputData', JSON.stringify(inputData));
		//dialogURL.setParameter('loadNow', true);
		dialogURL.setParameter('mode', mode);
		dialogURL.setParameter('eventEnable', false);
		dialogURL.setParameter('action', <portlet:namespace/>action);
		dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
		dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');

		if( $<portlet:namespace/>fileExplorerDialogSection.find('div').length === 0 ){
			$<portlet:namespace/>fileExplorerDialogSection.load( dialogURL.toString());
		}
		else{
			$<portlet:namespace/>fileExplorerDialogSection.dialog('open');
		}
	});
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
			<portlet:namespace/>action = e.action;
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
			<portlet:namespace/>initData = new OSP.InputData( e.data );
			if( <portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER ){
				<portlet:namespace/>initData.parent(
									OSP.Util.mergePath(<portlet:namespace/>initData.parent(), 
																	  <portlet:namespace/>initData.name()));
				<portlet:namespace/>initData.name("");
			}
			<portlet:namespace/>loadProtein( new OSP.InputData( e.data ) );
		}
	}
);

Liferay.on(
	OSP.Event.OSP_RESPONSE_DATA,
	function( e ){
		if( e.targetPortlet === '<%=portletDisplay.getId()%>' ){
			console.log('ImageViewer OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']');
			if( e.portletId === <portlet:namespace/>fileExplorerId ){
				var inputData = new OSP.InputData( e.data );

				if( inputData.type() !== OSP.Enumeration.PathType.FILE ){
					alert( 'File should be selected!' );
					return;
				}
				else{
					<portlet:namespace/>loadProtein( inputData );
					$<portlet:namespace/>fileExplorerDialogSection.dialog('close');
				}
			}
		}
	}
);


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
function <portlet:namespace/>loadProtein( inputData ){
	switch( inputData.type() ){
		case OSP.Enumeration.PathType.FILE:
			<portlet:namespace/>currentData = inputData.clone();
			<portlet:namespace/>drawProtein( inputData );
			break;
		case OSP.Enumeration.PathType.FOLDER:
		    <portlet:namespace/>currentData = inputData.clone();
		    break;
		case OSP.Enumeration.PathType.EXT:
		    <portlet:namespace/>getFirstFileName( inputData );
		    // serveResourceUrl.setParameter('command', 'READ_FIRST_FILE');
			break;
		case OSP.Enumeration.PathType.URL:
			alert('Un supported yet.'+inputData.type());
			break;
		default:
			alert('Un supported yet.'+inputData.type());
	}
}

function <portlet:namespace/>drawProtein( inputData ){
	AUI().use('liferay-portlet-url', function(a) {
	var serveResourceUrl = Liferay.PortletURL.createResourceURL();

	serveResourceUrl.setPortletId('<%=portletDisplay.getId()%>');
	serveResourceUrl.setParameter('command', 'READ_FILE');
	serveResourceUrl.setParameter('action', <portlet:namespace/>action);
	serveResourceUrl.setParameter('pathType', inputData.type());
	serveResourceUrl.setParameter('parentPath', inputData.parent());
	serveResourceUrl.setParameter('fileName', inputData.name());
	serveResourceUrl.setParameter('relative', inputData.relative());

	$('#<portlet:namespace/>canvas').each(function(){
									console.log( 'ID: '+$(this).attr('id'));
									$(this).one("load", function(){
										$(this).prop('contentWindow').loadProtein(
																							serveResourceUrl.toString(), 
																							$('#<portlet:namespace/>canvas').width(), 
																							$('#<portlet:namespace/>canvas').height());
									});
	});
}
	
function <portlet:namespace/>getFirstFileName( argData ){
	var inputData = argData.clone();

	var data = {
			<portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
			<portlet:namespace/>pathType: inputData.type(),
			<portlet:namespace/>parentPath: inputData.parent(),
			<portlet:namespace/>fileName: inputData.name(),
			<portlet:namespace/>relative: inputData.relative()
	};

	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		data  : data,
		dataType : 'json',
		success: function(data) {
				inputData.type( OSP.Enumeration.PathType.FILE );
				inputData.name( data.fileName );
				<portlet:namespace/>currentData = inputData.clone();
				<portlet:namespace/>drawProtein( inputData );
		},
		error:function(data,e){
			console.log('AJAX ERROR-->'+e);
		},
		complete: function( jqXHR, textStatus ){
		}
	});
}

</script>