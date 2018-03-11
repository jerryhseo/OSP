<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData =  GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);;
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "EDIT");;
%>

<div class="container-fluid osp-editor">
	<div class="row-fluid header">
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
			<!-- 
			<button id="<portlet:namespace/>sample" class="btn btn-primary" type="button">Sample</button>
			 -->
		</div>
	</div>			
	<div class="row-fluid frame" >
		<div class="col-sm-12 canvas" id="<portlet:namespace/>canvas" ></div>
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
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>dataType;
var <portlet:namespace/>currentData;
var <portlet:namespace/>initData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>saveAction = false;

var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = 'FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_sde';
if( '<portlet:namespace/>'.lastIndexOf('_INSTANCE_') > 0)
	<portlet:namespace/>fileExplorerId += '<portlet:namespace/>'.substring('<portlet:namespace/>'.lastIndexOf('_INSTANCE_')+10);
else
	<portlet:namespace/>fileExplorerId += '001';

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
	
	<portlet:namespace/>loadStructure( <portlet:namespace/>currentData );
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
		inputData.type( OSP.Enumeration.PathType.EXT);
		inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
		inputData.parent( '' );
		inputData.name( 'sde' );
		<portlet:namespace/>saveAction = true;
		
		<portlet:namespace/>fileExplorerDialog( 'VIEW', inputData );
 }
 
 function <portlet:namespace/>openLocalFile(){
	 $('#<portlet:namespace/>selectFile').click();
 }
 
 function <portlet:namespace/>openServerFile(){
	var inputData;
	if(	<portlet:namespace/>currentData && 
			<portlet:namespace/>currentData.type() === OSP.Enumeration.PathType.FILE &&
			<portlet:namespace/>currentData.type() === OSP.Enumeration.PathType.FOLDER &&
			<portlet:namespace/>currentData.type() === OSP.Enumeration.PathType.EXT ){
		inputData = <portlet:namespace/>currentData;
	}
	else{
		inputData = new OSP.InputData();
		inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
		inputData.type( OSP.Enumeration.PathType.EXT );
		inputData.parent('');
		inputData.name('sde');
	}
	<portlet:namespace/>fileExplorerDialog( 'VIEW', inputData );
 }
 
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

$("#<portlet:namespace/>file-explorer-ok").click(function(e){
	  e.preventDefault();
	  var eventData = {
	      portletId : '<%=portletDisplay.getId()%>',
	      targetPortlet : <portlet:namespace/>fileExplorerId
	  };
	  Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
	  $<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
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
				var textContents = evt.target.result;
				<portlet:namespace/>currentData = null;
				dataType.loadStructure( inputData.context() );
				<portlet:namespace/>refreshEditor();
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
			if( myId === e.targetPortlet ){
				<portlet:namespace/>connector = e.portletId;
				if( e.mode )
					<portlet:namespace/>mode = e.mode;
				else
					<portlet:namespace/>mode = 'input';
				
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
				console.log(e.portletId+' activated at '+new Date()+']');
				var eventData = {
									portletId: '<%=portletDisplay.getId()%>',
									targetPortlet: <portlet:namespace/>connector
							};
							
				Liferay.fire( OSP.Event.OSP_REQUEST_DATA_STRUCTURE, eventData );
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
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				var inputData = new OSP.InputData();
				inputData.type(OSP.Enumeration.PathType.CONTEXT);
				inputData.context(<portlet:namespace/>dataType.structure());
				var eventData = {
				                 portletId: myId,
				                 targetPortlet: <portlet:namespace/>connector,
				                 data: OSP.Util.toJSON(inputData),
				                 params: e.params
				};
				
				Liferay.fire( OSP.Event.OSP_RESPONSE_DATA, eventData );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_RESPONSE_DATA,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet !== myId )	return;
			
			console.log('OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']');
			console.log( 'saveAction: '+<portlet:namespace/>saveAction, e.data );

			var filePath = new OSP.InputData( e.data );
			if( filePath.type() !== OSP.Enumeration.PathType.FILE ){
				alert('File Name is not available. Choose another one.');
				return;
			}
			
			if( !<portlet:namespace/>saveAction ){
				$<portlet:namespace/>fileExplorerDialogSection.dialog('close');
			}
			else{
				$('#<portlet:namespace/>uploadFileName').val(filePath.name());
				filePath.type( OSP.Enumeration.PathType.FILE_CONTENT );
				filePath.context( JSON.stringify(<portlet:namespace/>dataType) );
				console.log('------', filePath);
				
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
						console.log('duplicated: '+duplicated);
						if( duplicated ){
							var confirmDialog = $('#<portlet:namespace/>confirmDialog');
							confirmDialog.dialog(
									{
										resizable: false,
										height: "auto",
										width: 600,
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
						console.log('OSPSDEditor: file save error');
					}
				});
			}
		}
);

Liferay.on(
		OSP.Event.OSP_INITIALIZE,
		function( e ){
			if( e.targetPortlet === '<%=portletDisplay.getId()%>'){
				console.log('<portlet:namespace/> OSP_INITIALIZE: ['+e.portletId+', '+new Date()+']');
				var eventData = {
						portletId: '<%=portletDisplay.getId()%>',
						targetPortlet: <portlet:namespace/>connector
				};
				
				Liferay.fire( OSP.Event.OSP_REQUEST_DATA_STRUCTURE, eventData );
			}
		}
);

Liferay.on(
   		OSP.Event.OSP_REFRESH,
   		function( e ){
   			if( e.targetPortlet === '<%=portletDisplay.getId()%>'){
   				console.log('OSP_REFRESH: ['+e.portletId+', '+new Date()+']');
   				
   				<portlet:namespace/>refreshEditor();
   			}
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
	$('#<portlet:namespace/>canvas').empty();
	
	switch( inputData.type() ){
	case OSP.Enumeration.PathType.STRUCTURED_DATA:
		dataType.deserializeStructure(inputData.context());
		<portlet:namespace/>refreshEditor();
		break;
	case OSP.Enumeration.PathType.FILE_CONTENT:
		dataType.loadStructure( inputData.context() );
		<portlet:namespace/>refreshEditor();
		break;
	case OSP.Enumeration.PathType.DLENTRY_ID:
		<portlet:namespace/>readDLEntry( inputData.dlEntryId() );
	case OSP.Enumeration.PathType.FILE:
		if( !inputData.repositoryType() )
			inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
		<portlet:namespace/>readFile( inputData );
		break;
	case OSP.Enumeration.PathType.EXT:
	case OSP.Enumeration.PathType.FOLDER:
		<portlet:namespace/>getFirstFileName( inputData );
		break;
	default:
		alert( 'Un-known dataType: '+inputData.type());
		return;
	}
	
	<portlet:namespace/>currentData = inputData;
}

function <portlet:namespace/>refreshEditor(){
	<portlet:namespace/>dataType.showStructuredDataEditor(
					'<portlet:namespace/>', 
					$('#<portlet:namespace/>canvas'),
					'<%=request.getContextPath()%>',
					'<%=themeDisplay.getLanguageId()%>');
};

function <portlet:namespace/>readDLEntry( dlEntryId ){
	var ajaxData = Liferay.Util.ns(
	                               '<portlet:namespace/>',
	                               {
	                            		command: 'READ_DLENTRY',
	                            		dlEntryId: dlEntryId,
	               						repositoryType: inputData.repositoryType()
	                               });
	$.ajax({
		url: '<%=serveResourceURL%>',
		type: 'post',
		dataType: 'json',
		data: ajaxData,
		success: function( result ){
			<portlet:namespace/>dataType.loadStructure( result );
			<portlet:namespace/>refreshEditor();
		},
		error: function( data, e ){
			console.log( '[ERROR] Getting data type sample: '+ portDataType.name );
		}
	});
}

function <portlet:namespace/>readFile( inputData ){
	if( ! inputData.repositoryType() )
		inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>');
	<portlet:namespace/>currentData = inputData.clone();
	
	var ajaxData = Liferay.Util.ns(
	                               '<portlet:namespace/>',
	                               {
	                            		command: 'READ_FILE',
	               						parentPath: inputData.parent(),
	               						fileName: inputData.name(),
	               						repositoryType: inputData.repositoryType()
	                               });
	$.ajax({
		url: '<%=serveResourceURL%>',
		type: 'post',
		dataType: 'text',
		data: ajaxData,
		success: function( result ){
			<portlet:namespace/>dataType.loadStructure( result );
			<portlet:namespace/>refreshEditor();
		},
		error: function( data, e ){
			console.log( '[ERROR] readFile: ', inputData );
			console.log('command: '+command);
		}
	});
}

function <portlet:namespace/>getFirstFileName( argData ){
	var inputData = argData.clone();
	
	if( ! inputData.repositoryType() )
		inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>');
    
    var data = {
            <portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
            <portlet:namespace/>pathType: inputData.type(),
            <portlet:namespace/>repositoryType: inputData.repositoryType(),
            <portlet:namespace/>parentPath: inputData.parent(),
            <portlet:namespace/>fileName: inputData.name()
    };
        
    $.ajax({
        type: 'POST',
        url: '<%= serveResourceURL.toString()%>', 
        data  : data,
        dataType : 'json',
        success: function(data) {
            inputData.name( data.fileName );
            inputData.type(OSP.Enumeration.PathType.FILE);
            <portlet:namespace/>readFile( inputData );
        },
        error:function(data,e){
            console.log('AJAX ERROR-->'+e);
        },
        complete: function( jqXHR, textStatus ){
        }
    });
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
			<portlet:namespace/>context: $('#<portlet:namespace/>canvas').val()
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

function <portlet:namespace/>downloadCurrentFile(){
	if(<portlet:namespace/>currentData && 
		<portlet:namespace/>currentData.type() === OSP.Enumeration.PathType.FILE &&
		<portlet:namespace/>currentData.name()){
        var filePath = <portlet:namespace/>currentData;
        var data = {
            <portlet:namespace/>command: "DOWNLOAD_FILE",
            <portlet:namespace/>pathType: filePath.type(),
            <portlet:namespace/>repositoryType: filePath.repositoryType(),
            <portlet:namespace/>parentPath: filePath.parent(),
            <portlet:namespace/>fileName: filePath.name(),
            <portlet:namespace/>relative: filePath.relative()
        };
        
        var base = '<%=serveResourceURL.toString()%>';
        var sep = (base.indexOf('?') > -1) ? '&' : '?';
        var url = base + sep + $.param(data);
        location.href = url;
    }
}
</script>