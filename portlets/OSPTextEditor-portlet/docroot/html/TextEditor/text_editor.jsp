<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
String mode = (String)renderRequest.getAttribute("mode");
%>


<div class="container-fluid osp-editor">
	<div class="row-fluid header" >
		<div class="col-sm-10" id="<portlet:namespace/>title"></div>
		<div class="col-sm-2" >
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					Menu<span class="caret"></span>
				</button>
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="javascript:<portlet:namespace/>takeSample()"><i class="icon-file"> Sample</i></a></li>
					<li><a href="javascript:<portlet:namespace/>openLocalFile()"><i class="icon-folder-open"> Open local...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>openServerFile()"><i class="icon-folder-open"> Open server...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>saveAs()"><i class="icon-save"> Save as...</i></a></li>
				</ul>
			</div>
		</div>	
	</div>
	<div class="row-fluid frame" >
		<textarea class="col-sm-12 canvas" id="<portlet:namespace/>canvas" ></textarea>
	</div>
</div>
<div id="<portlet:namespace/>hiddenSection" class="osp-editor hidden">
	<div id="<portlet:namespace/>fileExplorer" title="Select a file" >
              <div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
              <div>
                  <input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
                  <input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
              </div>
	</div>
	<div id="<portlet:namespace/>confirmDialog">
		<input type="text" id="<portlet:namespace/>uploadFileName"/><br/>
		<p>
			File already exists. Change file name or just click 'OK' button to overlap. 
		</p>
	</div>
	<input type="file" id="<portlet:namespace/>selectFile"/>
	
	<img id="<portlet:namespace/>processingMessage" src="<%=request.getContextPath()%>/images/processing_01.gif"/>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = 'broadcast';
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = 'FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_te';
if( '<portlet:namespace/>'.lastIndexOf('_INSTANCE_') > 0)
	<portlet:namespace/>fileExplorerId += '<portlet:namespace/>'.substring('<portlet:namespace/>'.lastIndexOf('_INSTANCE_')+10);
else
	<portlet:namespace/>fileExplorerId += '001';
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>saveAction = false;

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/

if( '<%=eventEnable%>' == false ){
	<portlet:namespace/>connector = '<%=connector%>';
	
	<portlet:namespace/>initData = new OSP.InputData(JSON.parse('<%=inputData%>'));
	if( !<portlet:namespace/>initData.repositoryType() )
		<portlet:namespace/>initData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
	
	<portlet:namespace/>loadText( <portlet:namespace/>initData );
}

$<portlet:namespace/>fileExplorerDialogSection.dialog({
	autoOpen: false,
	resizable: false,
	height: 600,
	width: 600,
	modal: true
});


/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
 function <portlet:namespace/>takeSample(){
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId: myId,
				targetPortlet: <portlet:namespace/>connector
		};
		
		Liferay.fire( OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, eventData);
}

 function <portlet:namespace/>saveAs(){
	var inputData = new OSP.InputData();
	inputData.type( OSP.Enumeration.PathType.FOLDER);
	inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
	inputData.parent( '' );
	inputData.name( '' );
	inputData.relative( true );
	<portlet:namespace/>saveAction = true;
	
	<portlet:namespace/>fileExplorerDialog( 'VIEW', inputData );
 }

 function <portlet:namespace/>openLocalFile(){
	 $('#<portlet:namespace/>selectFile').click();
 }

 function <portlet:namespace/>openServerFile(){
	var inputData;
	if(	<portlet:namespace/>initData && 
			<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FILE &&
			<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER &&
			<portlet:namespace/>initData.type() === OSP.Enumeration.PathType.EXT ){
		inputData = <portlet:namespace/>initData.clone();
	}
	else{
		inputData = new OSP.InputData();
		inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
		inputData.type( OSP.Enumeration.PathType.FOLDER );
		inputData.parent('');
		inputData.name('');
	}
	<portlet:namespace/>fileExplorerDialog( 'VIEW', inputData );
 }

$('#<portlet:namespace/>canvas').on('change', function(){
	var inputData = new OSP.InputData();
	inputData.type( OSP.Enumeration.PathType.FILE_CONTENT );
	if( $.isEmptyObject(<portlet:namespace/>initData) ){
		inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
	}
	else if( <portlet:namespace/>initData.repositoryType() )
		inputData.repositoryType(<portlet:namespace/>initData.repositoryType());
	else{
		inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
	}
	inputData.context( $(this).val() );
	
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: OSP.Util.toJSON(inputData)
	};
	
	Liferay.fire(OSP.Event.OSP_DATA_CHANGED, eventData );
});

$("#<portlet:namespace/>file-explorer-ok").click(function(e){
	  e.preventDefault();
	  var eventData = {
	      portletId : '<%=portletDisplay.getId()%>',
	      targetPortlet : <portlet:namespace/>fileExplorerId,
	      params: <portlet:namespace/>saveAction
	  };
	  Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
	e.preventDefault();
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

function <portlet:namespace/>fileExplorerDialog( mode, inputData ){
	AUI().use('liferay-portlet-url', function(A){
		var dialogURL = Liferay.PortletURL.createRenderURL();
		dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
		dialogURL.setParameter('inputData', JSON.stringify(inputData));
		dialogURL.setParameter('mode', mode);
		dialogURL.setParameter('eventEnable', false);
		dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
		dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
		
		// $("#<portlet:namespace/>file-explorer-content").empty();
		if($("#<portlet:namespace/>file-explorer-content").children().length > 0){
			$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}else{
			$("#<portlet:namespace/>file-explorer-content").load( dialogURL.toString());
			$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}
	});
} 

$('#<portlet:namespace/>selectFile').bind(
		'change', 
		function(event){
			var reader = new FileReader();
			reader.onload = function (evt) {
				$('#<portlet:namespace/>canvas').val(evt.target.result);
                $('#<portlet:namespace/>canvas').trigger('change');
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
				if( e.mode )
					<portlet:namespace/>mode = e.mode;
				else
					<portlet:namespace/>mode = 'VIEW';
	
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
				<portlet:namespace/>initData = new OSP.InputData( e.data );
				if( ! <portlet:namespace/>initData.repositoryType() )
					<portlet:namespace/>initData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
					
				<portlet:namespace/>loadText( <portlet:namespace/>initData );
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
							context_: $('#<portlet:namespace/>canvas').val(),
							params: e.params
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
			if( e.targetPortlet !== myId )	return;
			
			console.log('Text Editor OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']');
			console.log( 'saveAction: '+<portlet:namespace/>saveAction );

			var filePath = new OSP.InputData( e.data );
			if( filePath.type() !== OSP.Enumeration.PathType.FILE ){
				alert('File Name is not available. Choose another one.');
				return;
			}
			
			if( !<portlet:namespace/>saveAction ){
				<portlet:namespace/>loadText( filePath, true );
				$<portlet:namespace/>fileExplorerDialogSection.dialog('close');
			}
			else{
				$('#<portlet:namespace/>uploadFileName').val(filePath.name());
				filePath.type( OSP.Enumeration.PathType.FILE_CONTENT );
				filePath.context( $('#<portlet:namespace/>canvas').val() );
				
				$.ajax({
					url: '<%=serveResourceURL.toString()%>',
					type: 'POST',
					dataType: 'json',
					data:{
						<portlet:namespace/>command: "CHECK_DUPLICATED",
						<portlet:namespace/>repositoryType: filePath.repositoryType(),
						<portlet:namespace/>parentPath: filePath.parent(),
						<portlet:namespace/>fileName: filePath.name()
					},
					success: function(result){
						var duplicated = result.duplicated;
						if( duplicated ){
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
															<portlet:namespace/>saveContentAs ( filePath );
															$<portlet:namespace/>fileExplorerDialogSection.dialog('close');
												},
												Cancel: function() {
															$( this ).dialog( 'destroy' );
												}
										}
									}
							);
						}
						else{
							<portlet:namespace/>saveContentAs ( filePath );
							$<portlet:namespace/>fileExplorerDialogSection.dialog('close');
						}
					},
					error: function( e, d ){
						console.log('OSPTextEditor: file save error');
					}
				});
			}
		}
);

Liferay.on(
		OSP.Event.OSP_INITIALIZE,
		function( e ){
			if( e.targetPortlet === '<%=portletDisplay.getId()%>'){
				$('#<portlet:namespace/>canvas').val('');
			}
		}
);

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadText( inputData, changeAlert ){
	<portlet:namespace/>currentData = inputData.clone();
	if( !<portlet:namespace/>currentData.repositoryType() )
		<portlet:namespace/>currentData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
		
	if( inputData.type() === OSP.Enumeration.PathType.FILE){
		<portlet:namespace/>currentData = inputData.clone();
		var data = {
				<portlet:namespace/>command: 'READ_FILE',
				<portlet:namespace/>mode: <portlet:namespace/>mode,
				<portlet:namespace/>repositoryType: <portlet:namespace/>currentData.repositoryType(),
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
				if( changeAlert )
					$('#<portlet:namespace/>canvas').trigger('change');
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
		if( changeAlert )
			$('#<portlet:namespace/>canvas').trigger('change');
	}
	else if( inputData.type() === OSP.Enumeration.PathType.CONTEXT){
		$('#<portlet:namespace/>canvas').text(Liferay.Util.unescapeHTML(inputData.context()));
		if( changeAlert )
			$('#<portlet:namespace/>canvas').trigger('change');
	}
	else if( inputData.type() === OSP.Enumeration.PathType.DLENTRY_ID){
		$.ajax({
			url: '<%=serveResourceURL.toString()%>',
			type:'POST',
			async: false,
			dataType:'json',
			data:{
				<portlet:namespace/>command: 'READ_DLENTRY',
				<portlet:namespace/>mode: <portlet:namespace/>mode,
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
	                if( changeAlert )
						$('#<portlet:namespace/>canvas').trigger('change');
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

function <portlet:namespace/>saveContentAs( inputData ){
	<portlet:namespace/>saveAction = false;
	var data = {
			<portlet:namespace/>command: 'SAVE_AS',
			<portlet:namespace/>mode: <portlet:namespace/>mode,
			<portlet:namespace/>pathType: inputData.type(),
			<portlet:namespace/>repositoryType: inputData.repositoryType(),
			<portlet:namespace/>parentPath: inputData.parent(),
			<portlet:namespace/>fileName: inputData.name(),
			<portlet:namespace/>context: inputData.context()
	};
	
	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'text',
		success: function(data) {
			var eventData = {
			                 portletId: '<%=portletDisplay.getId()%>', 
			                 targetPortlet: <portlet:namespace/>fileExplorerId
			}
			
			Liferay.fire( OSP.Event.OSP_INITIALIZE, eventData );
			return true;
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
			return false;
		}
	});
}
	
</script>

