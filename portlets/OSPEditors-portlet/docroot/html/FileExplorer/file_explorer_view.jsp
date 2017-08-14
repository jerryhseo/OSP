<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@ include file="../init.jsp" %>

<script src="<%=request.getContextPath()%>/js/jstree/jstree.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/file-explorer-portlet.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jstree/themes/default/style.min.css"/>

 <portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = (String)renderRequest.getAttribute("inputData");
boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
String connector = (String)renderRequest.getAttribute("connector");
String mode = (String)renderRequest.getAttribute("mode");
String action = (String)renderRequest.getAttribute("action");

boolean isPopup = LiferayWindowState.isExclusive(request);
%>

<div class="row-fluid file-explorer-portlet editor-portlet">
	<div class="span12">
		<div class="row-fluid" id="<portlet:namespace/>choicePanel" style="padding:10px 0 0 10px;">
			<div class="span8">
				<input class="choidPanelInput" id="<portlet:namespace/>selected" style="width:100%;"/>
			</div>
			<div class="offset2 span2 dropdown-wrapper" id="<portlet:namespace/>menuSection">
				<div class="dropdown">
                  <i class="icon-reorder icon-menu"></i>
					<!-- Link or button to toggle dropdown -->
					<div class="dropdown-content">
                        <div class="dropdown-item" id="<portlet:namespace/>sample"><i class="icon-file"> Take sample</i></div>
                        <div class="dropdown-item" id="<portlet:namespace/>upload"><i class="icon-upload"> Upload</i></div>
                        <div class="dropdown-item" id="<portlet:namespace/>download"><i class="icon-download-alt"> Download</i></div>
					</div>
				</div>
			</div>	
		</div>
		<div class="row-fluid" id="<portlet:namespace/>canvasPanel">
			<div class="span12 canvas" id="<portlet:namespace/>canvas"></div>
		</div>
		<div id="<portlet:namespace/>hiddenSection" style="display:none;">
			<form action="<%= serveResourceURL.toString() %>" enctype="multipart/form-data" method="post" id="<portlet:namespace/>uploadForm">
				<input type="file" id="<portlet:namespace/>selectFile" name="<portlet:namespace/>uploadFile"/>
				<input type="text" id="<portlet:namespace/>parentPath" name="<portlet:namespace/>parentPath"/>
				<input type="text" id="<portlet:namespace/>fileName" name="<portlet:namespace/>fileName"/>
				<input type="text" id="<portlet:namespace/>connector" name="<portlet:namespace/>connector"/>
				<input type="text" id="<portlet:namespace/>command" name="<portlet:namespace/>command" />
			</form>
					
			<div id="<portlet:namespace/>confirmDialog">
				<input type="text" id="<portlet:namespace/>uploadFileName"/><br/>
				<p id="<portlet:namespace/>confirmMessage">
					File already exists. Change file name or just click 'OK' button to overlap. 
				</p>
			</div>
			<a id="<portlet:namespace/>downloadAnchor" target="_blank" style="z-index:1000;">Download</a>
		</div>
	</div>
</div>


<script>
//var $ = $.noConflict();
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>canvas = $('#<portlet:namespace/>canvas');
var <portlet:namespace/>contextPath = '<%=request.getContextPath()%>';
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>downloadMode = false;
var <portlet:namespace/>eventEnable = <%=eventEnable %>;
var <portlet:namespace/>mode = '<%=mode %>';
var <portlet:namespace/>action = '<%=action %>';
var <portlet:namespace/>extension;
var <portlet:namespace/>sampleSelected = false;
var <portlet:namespace/>basePath;
var <portlet:namespace/>selectedFile;



/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( <portlet:namespace/>mode === 'VIEW' ){
	$('#<portlet:namespace/>menuSection').css('display', 'none');
}

if( !<portlet:namespace/>eventEnable ){
	var inputData = '<%=inputData%>';
	var initData;
	if( !inputData ){
		initData = new OSP.InputData();
		initData.parent( '');
		initData.name('');
		initData.type(OSP.Enumeration.PathType.FOLDER);
		initData.relative( true );
	}
	else{
		initData = new OSP.InputData(JSON.parse(inputData));
	}

	<portlet:namespace/>initFileExplorer(initData, true, <portlet:namespace/>action );
}


/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
 $('#<portlet:namespace/>upload').click(function(){
	 var targetFolder;
	 if( <portlet:namespace/>selectedFile.type() === OSP.Enumeration.PathType.FOLDER ){
		 targetFolder = OSP.Util.mergePath( <portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent());
		 targetFolder = OSP.Util.mergePath( targetFolder, <portlet:namespace/>selectedFile.name() );
	 }
	 else if ( <portlet:namespace/>selectedFile.type() === OSP.Enumeration.PathType.FILE || 
			   <portlet:namespace/>selectedFile.type() === OSP.Enumeration.PathType.EXT){
		 targetFolder = OSP.Util.mergePath( <portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent());
	 }
	 
	 var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data:{
				targetFolder: targetFolder
			}
	 };
	
	Liferay.fire( OSP.Event.OSP_UPLOAD_FILE, eventData );
	//$('#<portlet:namespace/>selectFile').click();
});

$('#<portlet:namespace/>sample').click(function(){
	$('#<portlet:namespace/>selected').val( '--- sample selected ---');
	$('#<portlet:namespace/>selected').attr('disabled', true);
	<portlet:namespace/>sampleSelected = true;
	
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector
	}
	
	Liferay.fire( OSP.Event.OSP_SAMPLE_SELECTED, eventData );
});

$('#<portlet:namespace/>download').click(function(){
	if( <portlet:namespace/>downloadMode === false ){
		<portlet:namespace/>downloadMode = true;
		$('#<portlet:namespace/>canvas').find('i.jstree-checkbox').css('display', 'inline');
		alert('To download files, select files to be downloaded and SELECT AGAIN download menu.');
		$('#<portlet:namespace/>canvas').jstree().redraw(true);
		return;
	}
	
	var selectedNodes = $('#<portlet:namespace/>canvas').jstree(true).get_selected();
	for( var index in  selectedNodes ){
		var node = selectedNodes[index];
		if( node === '..' ){
			selectedNodes = OSP.Util.removeArrayElement(selectedNodes, index);
			break;
		}
	}
	//console.log( selectedNodes );
	//console.log('Parent of selected: '+ OSP.Util.mergePath(<portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent()));
	
	data = {
		<portlet:namespace/>command: 'DOWNLOAD',
		<portlet:namespace/>fileNames: JSON.stringify(selectedNodes),
		<portlet:namespace/>folderPath: OSP.Util.mergePath(<portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent())
	};
	
	var base = '<%=serveResourceURL.toString()%>';
	var sep = (base.indexOf('?') > -1) ? '&' : '?';
	var url = base+sep+$.param(data);
	
	//console.log( 'Download URL: '+ url);
	($('#<portlet:namespace/>downloadAnchor').attr('href', url))[0].click();

	<portlet:namespace/>downloadMode = false;
	$('#<portlet:namespace/>canvas').jstree().uncheck_all();
	$('#<portlet:namespace/>canvas').find('i.jstree-checkbox').css('display', 'none');
});

$('#<portlet:namespace/>selectFile').bind(
		'change', 
		function(event){
			var uploadFile = $(this)[0].files[0];
			//console.log( 'UploadFile: '+uploadFile);
			
			var uploadFileName = $(this).val();
			var slashIndex = uploadFileName.lastIndexOf('\\');
			if( slashIndex < 0 )
				slashIndex = uploadFileName.lastIndexOf('/'); 
			uploadFileName = uploadFileName.slice(slashIndex+1);
			// $('#<portlet:namespace/>fileName').val( uploadFileName);
			
			var uploadFolder = <portlet:namespace/>selectedFile.parent();
			
			// check that file name is duplicated using AJAX.
			var duplicated;
			var target = OSP.Util.mergePath(
							<portlet:namespace/>basePath,
							uploadFolder
			);
			target = OSP.Util.mergePath( target, uploadFileName );
			
			$.ajax({
				url: '<%=serveResourceURL.toString()%>',
				type: 'POST',
				async: false,
				dataType: 'json',
				data:{
					<portlet:namespace/>command: "CHECK_DUPLICATED",
					<portlet:namespace/>action: <portlet:namespace/>action,
					<portlet:namespace/>target: target
				},
				success:function(result){
					duplicated = result.duplicated;
				},
				error: function(){
					
				}
			});

			if( duplicated ){
				$('#<portlet:namespace/>uploadFileName').val(uploadFileName);
				
				var confirmDialog = $('#<portlet:namespace/>confirmDialog');
				confirmDialog.dialog(
					{
						resizable: false,
						height: "auto",
						width: 400,
						modal: true,
						buttons: {
							'OK': function() {
								<portlet:namespace/>submitUpload( 
										uploadFile, 
										OSP.Util.mergePath( <portlet:namespace/>basePath, uploadFolder),
										$('#<portlet:namespace/>uploadFileName').val()
								);
								$( this ).dialog( "close" );
							},
							Cancel: function() {
								$( this ).dialog( "close" );
							}
						}
					}
				);
			}
			else{
				<portlet:namespace/>submitUpload(
						uploadFile, 
						OSP.Util.mergePath( <portlet:namespace/>basePath, uploadFolder),
						uploadFileName
				);
			}
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
				<portlet:namespace/>action = e.data.action;
				
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
				var eventData = {
						portletId: myId,
						targetPortlet: <portlet:namespace/>connector
				};
				Liferay.fire( OSP.Event.OSP_REQUEST_PATH, eventData );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_LOAD_DATA,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				var inputData = new OSP.InputData( e.data );
				
				if( !<portlet:namespace/>selectedFile )
					<portlet:namespace/>initFileExplorer( inputData, true );
				else
					<portlet:namespace/>initFileExplorer( inputData);
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REQUEST_DATA,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
			    var data = new OSP.InputData();
				data.type(<portlet:namespace/>selectedFile.type());
				data.parent(OSP.Util.mergePath(<portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent()));
				data.name($('#<portlet:namespace/>selected').val());
				data.relative( <portlet:namespace/>selectedFile.relative())
				
				var eventData = {
					portletId: myId,
					targetPortlet:e.portletId,
					data: OSP.Util.toJSON(data),
					action: e.action
				}
				Liferay.fire( OSP.Event.OSP_RESPONSE_DATA, eventData );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_INITIALIZE,
		function( e ){
			var inputData = new OSP.InputData();
			inputData.type( OSP.Enumeration.PathType.FOLDER);
			inputData.parent( '' );
			inputData.name('');
			inputData.relative(true);
			<portlet:namespace/>initFileExplorer( inputData, true );
		}
);

Liferay.on(
		OSP.Event.OSP_REFRESH,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				<portlet:namespace/>initFileExplorer( <portlet:namespace/>selectedFile );
			}
		}
);

/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>initFileExplorer( inputData, init ){
  //console.log("inputData, init ", inputData, init);
	if( init ){
		<portlet:namespace/>selectedFile = new OSP.InputData();

		<portlet:namespace/>selectedFile.type(inputData.type());
		<portlet:namespace/>selectedFile.relative(inputData.relative());
		
		switch( inputData.type() ){
			case OSP.Enumeration.PathType.FILE:
			case OSP.Enumeration.PathType.FOLDER:
				<portlet:namespace/>basePath = OSP.Util.removeEndSlashes ( inputData.parent() );
				var filePath = OSP.Util.convertToPath( inputData.name().trim() );
				<portlet:namespace/>selectedFile.parent(filePath.parent() ? filePath.parent() : '');
				<portlet:namespace/>selectedFile.name( filePath.name() ? filePath.name() : '' );
				break;
			case OSP.Enumeration.PathType.EXT:
				<portlet:namespace/>basePath = OSP.Util.removeEndSlashes ( inputData.parent() );
				var filePath = OSP.Util.convertToPath( <portlet:namespace/>selectedFile.parent() );
				<portlet:namespace/>selectedFile.parent( filePath.parent() );
				<portlet:namespace/>selectedFile.name( OSP.Util.mergePath( filePath.name(), <portlet:namespace/>extension));
				<portlet:namespace/>extension = <portlet:namespace/>selectedFile.name();
				
				break;
			default:
				<portlet:namespace/>selectedFile.type( OSP.Enumeration.PathType.FOLDER );
				<portlet:namespace/>selectedFile.parent( '' );
				<portlet:namespace/>selectedFile.name( '' );
				<portlet:namespace/>selectedFile.relative( true );
		}
		$('#<portlet:namespace/>selected').val(<portlet:namespace/>selectedFile.name());
	}
	
	var basePath = <portlet:namespace/>basePath;
	var parentPath = <portlet:namespace/>selectedFile.parent();
	var fileName = <portlet:namespace/>selectedFile.name();
	var pathType = <portlet:namespace/>selectedFile.type();
	var relative = <portlet:namespace/>selectedFile.relative();
	var fileInfos = <portlet:namespace/>lookupPath(
			'GET_FILE_INFO',
			pathType,
			basePath, 
			parentPath,
			fileName
	);
	
	$('#<portlet:namespace/>selected').val( fileName );
	<portlet:namespace/>loadFileExplorer( fileInfos.fileInfos, parentPath );
}

function <portlet:namespace/>lookupPath( 
		command, 
		pathType, 
		basePath, 
		parentPath, 
		fileName){
	var data = {
					<portlet:namespace/>command: command,
					<portlet:namespace/>action: <portlet:namespace/>action,
					<portlet:namespace/>pathType: pathType,
					<portlet:namespace/>basePath: basePath,
					<portlet:namespace/>parentPath: parentPath,
					<portlet:namespace/>fileName: fileName
			};
	
	var fileInfos = null;
	
	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		async : false,
		data  : data,
		dataType : 'json',
		success: function(data) {
			fileInfos = data;
			//console.log(JSON.stringify(fileInfos, null, 4));
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
	
	return fileInfos;
}

function <portlet:namespace/>fireDataChangedEvent( data ){
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: data
	}
	
	Liferay.fire( OSP.Event.OSP_DATA_CHANGED, eventData );
}

function <portlet:namespace/>loadFileExplorer( fileInfoList, parentPath )
{
	//console.log(JSON.stringify(fileInfos, null, 4));
	//var fileInfoList = fileInfos.fileInfos;
	var nodeDataAry = [];
	
	if( parentPath !== '' ){
		var parentNode = {
			'id': '..',
			'text': '..',
			'type': 'prev-folder',
			'li_attr': {
				'childLength': 0
			},
			'a_attr': {
				'class': 'no_checkbox'
			}
		};
		
		nodeDataAry.push( parentNode );
	}

	for(var index in fileInfoList ){
		var fileInfo = fileInfoList[index];
		var type = (fileInfo.isFile) ? 'file' : 'closed-folder';
		var obj = {
			'id': fileInfo.name,
			'text': fileInfo.name+' ['+fileInfo.size+']',
			'name': fileInfo.name,
			'type':type,
			'li_attr': {
				'childLength': 0
			}
		};
		nodeDataAry.push(obj);
	}
	
	var rootData = [{
		'id':parentPath,
		'text':(parentPath === '') ? '' : parentPath,
		'type':'opened-folder',
		'children': nodeDataAry,
		'li_attr':{
			'childLength' : nodeDataAry.length			
		}
	}];
	
	if($.jstree.reference($('#<portlet:namespace/>canvas'))){
		$('#<portlet:namespace/>canvas').jstree(true).settings.core.data = rootData;
		$('#<portlet:namespace/>canvas').jstree(true).refresh();
	}else{
	  $('#<portlet:namespace/>canvas').jstree({
	    'core' : {
	      'data': rootData,
	      'check_callback' : true,
	      'multiple': true
	      },
	    'state' : 'open',
	      'types' : {
	        'prev-folder':{
	          'icon' : '<%=request.getContextPath()%>/js/jstree/images/myfile-icon02.png'
	        },
	          'opened-folder' : {
	            'icon' : '<%=request.getContextPath()%>/js/jstree/images/myfile-icon01.png'
	          },
	          'closed-folder' : {
	              'icon' : '<%=request.getContextPath()%>/js/jstree/images/myfile-icon02.png'
	          },
	          'file' : {
	              'icon' : '<%=request.getContextPath()%>/js/jstree/images/fileicon.png'
	          }
	      },
	      'sort': function (a, b) {
	          return this.get_text(a).toLowerCase() > this.get_text(b).toLowerCase() ? 1 : -1; 
	      },
	       'progressive_render' : true,
	     'plugins' : ['state', 'dnd', 'sort', 'types', 'progressive_render' , 'hotkeys']
	  }).bind('loaded.jstree', function(event, data) { 
	    $('#<portlet:namespace/>canvas').jstree('open_all');
	    $('#<portlet:namespace/>canvas').jstree('deselect_all');
	  }).bind('select_node.jstree',function(e, data){
	    var currentParentPath = $('#<portlet:namespace/>canvas').jstree(true).settings.core.data[0].id;
	    if( $('#<portlet:namespace/>selected').attr('disabled') )
	      $('#<portlet:namespace/>selected').attr('disabled', false);
	    if( data.node.type == 'file' ){
	      $('#<portlet:namespace/>selected').val( data.node.id );
	      
	      <portlet:namespace/>selectedFile.type('file');
	      <portlet:namespace/>selectedFile.parent( currentParentPath);
	      <portlet:namespace/>selectedFile.name(data.node.id);
	      
	      var inputData = new OSP.InputData();
	      inputData.type( OSP.Enumeration.PathType.FILE );
	      inputData.parent( OSP.Util.mergePath( <portlet:namespace/>basePath, currentParentPath) );
	      inputData.name( data.node.id );
	      
	      <portlet:namespace/>fireDataChangedEvent( OSP.Util.toJSON(inputData) );
	    }else if( data.node.type == 'closed-folder'){
	      <portlet:namespace/>selectedFile.type('folder');
	      <portlet:namespace/>selectedFile.parent( OSP.Util.mergePath(currentParentPath, data.node.id));
	      <portlet:namespace/>selectedFile.name('');
	      $('#<portlet:namespace/>selected').val('');
	      
	      var inputData = new OSP.InputData();
	      inputData.type( OSP.Enumeration.PathType.FOLDER );
	      inputData.parent( OSP.Util.mergePath( <portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent()));
	      inputData.name( <portlet:namespace/>selectedFile.name() );
	      
	      <portlet:namespace/>fireDataChangedEvent( OSP.Util.toJSON(inputData) );
	      
	      var folderName = data.node.id;
	      var nextRootPath = OSP.Util.mergePath( currentParentPath, folderName);
	      
	      var nodeInfos = <portlet:namespace/>lookupPath( 
	          "GET_FILE_INFO", 
	          OSP.Enumeration.PathType.FOLDER,
	          <portlet:namespace/>basePath, 
	          <portlet:namespace/>selectedFile.parent(), 
	          ''
	      );
	      <portlet:namespace/>loadFileExplorer( nodeInfos.fileInfos, nextRootPath ); 
	      // $('#<portlet:namespace/>canvas').jstree('destroy').empty();
	    }
	    else if( data.node.type == 'prev-folder' ){
	      var folderName = '';
	      var slashIndex = currentParentPath.lastIndexOf('/');
	      var nextRootPath = slashIndex > 0 ? currentParentPath.substring(0, slashIndex) : '';
	      //console.log( 'ancestor: '+nextRootPath);
	      slashIndex = nextRootPath.lastIndexOf('/');
	      var selectFolderName = slashIndex > 0 ? nextRootPath.substring(slashIndex+1) : nextRootPath;
	      //console.log( 'folderName: '+selectFolderName);
	      $('#<portlet:namespace/>selected').val( '' );

	      <portlet:namespace/>selectedFile.type('folder');
	      <portlet:namespace/>selectedFile.parent( nextRootPath);
	      <portlet:namespace/>selectedFile.name('');
	      
	      var inputData = new OSP.InputData();
	      inputData.type( OSP.Enumeration.PathType.FOLDER );
	      inputData.parent( OSP.Util.mergePath( <portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent()));
	      inputData.name( <portlet:namespace/>selectedFile.name() );
	      
	      <portlet:namespace/>fireDataChangedEvent( OSP.Util.toJSON(inputData) );
	      
	      var nodeInfos = <portlet:namespace/>lookupPath( 
	          "GET_FILE_INFO", 
	          OSP.Enumeration.PathType.FOLDER,
	          <portlet:namespace/>basePath, 
	          nextRootPath, 
	          ''
	      );
	      // $('#<portlet:namespace/>canvas').jstree('destroy').empty();
	      <portlet:namespace/>loadFileExplorer( nodeInfos.fileInfos, nextRootPath );
	    }
	  }).bind('deselect_node.jstree',function(e, data){
	    if( data.node.id === <portlet:namespace/>selectedFile.name() ){
	      <portlet:namespace/>selectedFile.name('');
	      $('#<portlet:namespace/>selected').val( '' );
	    }
	  }).bind("refresh.jstree", function (event, data) {
	    $('#<portlet:namespace/>canvas').jstree('open_all');
      $('#<portlet:namespace/>canvas').jstree('deselect_all');
    });;
	}
}

function <portlet:namespace/>submitUpload( uploadFile, targetFolder, fileName ){
	//var uploadFolder = OSP.Util.mergePath( <portlet:namespace/>basePath, <portlet:namespace/>selectedFile.parent());
		
	var formData = new FormData();
	formData.append('<portlet:namespace/>uploadFile', uploadFile);
	formData.append('<portlet:namespace/>command', 'UPLOAD');
	formData.append('<portlet:namespace/>action', <portlet:namespace/>action);
	formData.append('<portlet:namespace/>targetFolder', targetFolder);
	formData.append('<portlet:namespace/>fileName', fileName);

	$.ajax({
		url : '<%=serveResourceURL.toString()%>',
		type : 'POST',
		data : formData,
		processData: false,  // tell jQuery not to process the data
		contentType: false,  // tell jQuery not to set contentType
		success : function(data) {
			<portlet:namespace/>initFileExplorer( <portlet:namespace/>selectedFile );
		}
	});
}
</script>



