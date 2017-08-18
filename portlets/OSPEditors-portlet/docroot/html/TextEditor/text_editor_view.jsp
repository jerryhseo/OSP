<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/texteditor-portlet.css">

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

<div class="row-fluid texteditor-portlet editor-portlet" >
	<div class="span12">
		<div class="row-fluid" id="<portlet:namespace/>menuSection" style="height:30px;">
			<div class="offset10 span2 dropdown-wrapper" id="<portlet:namespace/>menuSection">
				<div class="dropdown">
                  <i class="icon-reorder icon-menu"></i>
					<!-- Link or button to toggle dropdown -->
					<div class="dropdown-content">
                        <div class="dropdown-item" id="<portlet:namespace/>sample"><i class="icon-folder-open"> Take sample</i></div>
						<div class="dropdown-item" id="<portlet:namespace/>openLocal"><i class="icon-folder-open"> Open local...</i></div>
						<div class="dropdown-item" id="<portlet:namespace/>openServer"><i class="icon-folder-open"> Open server...</i></div>
						<div class="dropdown-item" id="<portlet:namespace/>saveAs"><i class="icon-save"> Save as...</i></div>
					</div>
				</div>
			</div>	
		</div>
		<div class="row-fluid canvasPanel" id="<portlet:namespace/>canvasPanel">
			<textarea class="span12 canvas" id="<portlet:namespace/>canvas" ></textarea>
		</div>
		<div id="<portlet:namespace/>hiddenSection" style="display:none;">
			<div id="<portlet:namespace/>fileExplorer" title="Select a file" ></div>
			<div id="<portlet:namespace/>confirmDialog">
				<input type="text" id="<portlet:namespace/>uploadFileName"/><br/>
				<p>
					File already exists. Change file name or just click 'OK' button to overlap. 
				</p>
			</div>
			<input type="file" id="<portlet:namespace/>selectFile"/>
			
			<img id="<portlet:namespace/>processingMessage" src="<%=request.getContextPath()%>/images/processing_01.gif"/>
		</div>
	</div>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = 'broadcast';
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = 'FileExplorer_WAR_OSPEditorsportlet_INSTANCE_te'+
    + "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
var <portlet:namespace/>initData;
var <portlet:namespace/>action = '<%=action%>';


/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/

if( '<%=eventEnable%>' == false ){
	<portlet:namespace/>connector = '<%=connector%>';
	
	<portlet:namespace/>initData = new OSP.InputData(JSON.parse('<%=inputData%>'));
	console.log('TextEditor Init Data', <portlet:namespace/>initData );
	
	<portlet:namespace/>loadText( <portlet:namespace/>initData );
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
	
	Liferay.fire( 
			OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, 
			eventData
	);
});

$('#<portlet:namespace/>saveAs').click(function(e){
	e.preventDefault();
	alert( 'SaveAs ');
	var inputData = new OSP.InputData();
	inputData.type( OSP.Enumeration.PathType.FOLDER);
	inputData.parent( '' );
	inputData.name( '' );
	inputData.relative( true );
	
	<portlet:namespace/>fileExplorerDialog( 'VIEW', 'SAVEAS', inputData );
});

$('#<portlet:namespace/>openLocal').click(function(e){
	$('#<portlet:namespace/>selectFile').click();
});

$('#<portlet:namespace/>openServer').click(function(){
	var inputData;
	if(	<portlet:namespace/>initData && 
			<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FILE &&
			<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER &&
			<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.EXT ){
		inputData = <portlet:namespace/>initData;
	}
	else{
		inputData = new OSP.InputData();
		inputData.type( OSP.Enumeration.PathType.FOLDER );
		inputData.parent('');
		inputData.name('');
	}
	<portlet:namespace/>fileExplorerDialog( 'VIEW', 'READ_REPOSITORY', inputData );
});

$('#<portlet:namespace/>canvas').on('change', function(){
	var inputData = new OSP.InputData();
	inputData.type( OSP.Enumeration.PathType.FILE_CONTENT );
	inputData.context( $(this).val() );
	
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: OSP.Util.toJSON(inputData)
	};
	
	Liferay.fire(OSP.Event.OSP_DATA_CHANGED, eventData );
});


function <portlet:namespace/>fileExplorerDialog( mode, action, inputData ){
	AUI().use('liferay-portlet-url', function(A){
	    $<portlet:namespace/>fileExplorerDialogSection.remove();
		
		var dialogURL = Liferay.PortletURL.createRenderURL();
		dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
		var initData = <portlet:namespace/>initData;
		dialogURL.setParameter('inputData', JSON.stringify(inputData));
		dialogURL.setParameter('mode', mode);
		dialogURL.setParameter('eventEnable', false);
		dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
		dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
		
		$<portlet:namespace/>fileExplorerDialogSection.dialog({
			autoOpen: true,
			resizable: false,
			height: 600,
			width: 400,
			modal: true,
			buttons:{
				OK: function() {
					var eventData = {
							portletId : '<%=portletDisplay.getId()%>',
							targetPortlet : <portlet:namespace/>fileExplorerId,
							action: action
					};
					Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
				},
				Cancel: function() {
					$( this ).dialog( 'destroy' );
				}
			}
		});

		$<portlet:namespace/>fileExplorerDialogSection.load( dialogURL.toString());
	});
	
} 

$('#<portlet:namespace/>selectFile').bind(
		'change', 
		function(event){
			var reader = new FileReader();
			reader.onload = function (evt) {
				var textContents = evt.target.result;
				$('#<portlet:namespace/>canvas').val( textContents );
			};
			var inputFile = document.getElementById('<portlet:namespace/>selectFile');
			reader.readAsText(inputFile.files[0]);
		}
);


/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
		OSP.Event.OSP_HANDSHAKE,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				<portlet:namespace/>connector = e.portletId;
				<portlet:namespace/>action = e.action;
	
				var events = [
					OSP.Event.OSP_EVENTS_REGISTERED,
					OSP.Event.OSP_LOAD_DATA,
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
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				console.log('Text Editor OSP_EVENTS_REGISTERED: ['+e.portletId+', '+new Date()+']');
				// Do nothing
			}
		}
);

Liferay.on( 
		OSP.Event.OSP_LOAD_DATA, 
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				console.log('Text Editor OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']');
				<portlet:namespace/>loadText( new OSP.InputData( e.data ) );
			}
		}
);

Liferay.on( 
		OSP.Event.OSP_REQUEST_DATA, 
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				console.log('Text Editor OSP_REQUEST_DATA: ['+e.portletId+', '+new Date()+']');
				
				var eventData = {
						portletId: myId,
						targetPortlet: e.portletId,
						data: {
							type_: OSP.Enumeration.PathType.FILE_CONTENT,
							context_: $('#<portlet:namespace/>canvas').val()
						}
				}
				
				Liferay.fire(
						OSP.Event.OSP_RESPONSE_DATA,
						eventData
				);
			}
		}
);


Liferay.on(
           OSP.Event.OSP_RESPONSE_DATA,
           function( e ){
               var myId = '<%=portletDisplay.getId()%>';
               if( e.targetPortlet === myId ){
                   console.log('Text Editor OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']');
                       //alert( e.sourceData.action );
                   console.log( e.action  );
                   var data = new OSP.InputData( e.data );
                   if( data.type() !== OSP.Enumeration.PathType.FILE ){
                       alert('File Name is not available. Choose another one.');
                       return;
                   }
                   
                   switch( e.action ){
                   case 'READ':
                       <portlet:namespace/>loadText( data );
                       $<portlet:namespace/>fileExplorerDialogSection.dialog('destroy');
                       break;
                   case 'SAVEAS':
                       var filePath = data;
                       $('#<portlet:namespace/>uploadFileName').val(filePath.name());
                       var confirmDialog = $('#<portlet:namespace/>confirmDialog');
                       confirmDialog.dialog(
                           {
                               resizable: false,
                               height: "auto",
                               width: 400,
                               modal: true,
                               buttons: {
                                   'OK': function() {
                                       $( this ).dialog( 'destroy' );

                                       filePath.name( $('#<portlet:namespace/>uploadFileName').val());
                                       filePath.type( OSP.Enumeration.PathType.FILE_CONTENT );
                                       filePath.context( $('#<portlet:namespace/>canvas').val() );
                                       
                                       var eventData = {
                                               portletId: '<%=portletDisplay.getId()%>',
                                               targetPortlet: <portlet:namespace/>connector,
                                               data: OSP.Util.toJSON( filePath )
                                       }
                                       
                                       Liferay.fire( OSP.Event.OSP_SAVEAS_FILE, eventData );

                                       // <portlet:namespace/>saveAs ( filePath );

                                       $<portlet:namespace/>fileExplorerDialogSection.dialog('destroy');
                                   },
                                   Cancel: function() {
                                       $( this ).dialog( 'destroy' );
                                   }
                               }
                           }
                       );
                       break;
                   default:
                       console.log('[ERROR] Unknown action.' );
                   }
               }
           }
);

Liferay.on(
		OSP.Event.OSP_INITIALIZE,
		function( e ){
			$('#<portlet:namespace/>canvas').val('');
		}
);

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadText( inputData ){
	if( inputData.type() === OSP.Enumeration.PathType.FILE){
		var data = {
				<portlet:namespace/>command: 'READ_FILE',
				<portlet:namespace/>action: <portlet:namespace/>action,
				<portlet:namespace/>pathType: inputData.type(),
				<portlet:namespace/>parentPath: inputData.parent(),
				<portlet:namespace/>fileName: inputData.fileName()
		};
		
		$.ajax({
			type: 'POST',
			url: '<%= serveResourceURL.toString()%>', 
			async : false,
			data  : data,
			dataType : 'text',
			success: function(data) {
				$('#<portlet:namespace/>canvas').val( data );
			},
			error:function(data,e){
				console.log(data);
				console.log('AJAX ERROR-->'+e);
			},
			complete: function( jqXHR, textStatus ){
			}
		});
	}
	else if( inputData.type() === OSP.Enumeration.PathType.FILE_CONTENT){
		$('#<portlet:namespace/>canvas').val( inputData.context() );
	}
	else if( inputData.type() === OSP.Enumeration.PathType.CONTEXT){
		$('#<portlet:namespace/>canvas').text(Liferay.Util.unescapeHTML(inputData.context()));
	}
	else if( inputData.type() === OSP.Enumeration.PathType.DLENTRY_ID){
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
	                $('#<portlet:namespace/>canvas').val( inputData.context() );
			    }
			},
			error: function(){
			    alert( '[ERROR] Site invalid. \nPlease contact site manager.');
			},
			complete: function( jqXHR, textStatus ){
			}
		});
	}
	else if( inputData.type() === OSP.Enumeration.PathType.URL ){
		alert( 'Un-supported yet.');
	}
	else{
		alert( 'Un-known dataType: '+inputData.type());
	}
}



function <portlet:namespace/>checkPath( filePath, command ){
	var data = {
			<portlet:namespace/>command: command,
			<portlet:namespace/>action: <portlet:namespace/>action,
			<portlet:namespace/>pathType: filePath.type(),
			<portlet:namespace/>parentPath: filePath.parent(),
			<portlet:namespace/>fileName: filePath.name(),
			<portlet:namespace/>relative: filePath.relative()
	};
	
	var check = false
	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'text',
		success: function(result) {
			check = result === 'true' ? true : false; 
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
	
	return check;
};

function <portlet:namespace/>saveAs( inputData ){
	var data = {
			<portlet:namespace/>command: 'SAVE_AS',
			<portlet:namespace/>action: <portlet:namespace/>action,
			<portlet:namespace/>pathType: inputData.type(),
			<portlet:namespace/>parentPath: inputData.parent(),
			<portlet:namespace/>fileName: inputData.name(),
			<portlet:namespace/>context: $('#<portlet:namespace/>canvas').val(),
			<portlet:namespace/>relative: inputData.relative()
	};
	
	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'text',
		success: function(data) {
			console.log( 'Save success');
			return true;
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
			return false;
		}
	});
	
	console.log( 'reached here!!!!!!');
}
	
</script>

