<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@ include file="./init.jsp" %>

<%-- <script src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/js/osp/osp_basic_object.js"></script>
<script src="<%=request.getContextPath()%>/js/osp/osp_super_class.js"></script>
<script src="<%=request.getContextPath()%>/js/osp/osp_datatype.js"></script> --%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dropdown.css"/>

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
String repositoryType = (String)renderRequest.getAttribute("repositoryType"); 
%>

<div class="row-fluid file-explorer-portlet editor-portlet" style="overflow: hidden;">
	<div class="span12" style="height: inherit;">
		<div class="row-fluid" id="<portlet:namespace/>choicePanel" style="padding:10px 0 0 10px;">
			<div class="span8">
				<span id="<portlet:namespace/>title" style="width:100%;"></span>
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
		<div class="row-fluid" id="<portlet:namespace/>canvasPanel" style="height: 90%;">
			<iframe class="span12 canvas" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/epd_editor.jsp"></iframe>
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
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>canvas = $('#<portlet:namespace/>canvas');
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>eventEnable = <%=eventEnable %>;
var <portlet:namespace/>mode = '<%=mode %>';
var <portlet:namespace/>action = '<%=action %>';
var <portlet:namespace/>repositoryType = '<%=repositoryType%>';

var <portlet:namespace/>extension;
var <portlet:namespace/>selectedFile;

var <portlet:namespace/>runningMode = '_DEBUG_';
/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/

if( !<portlet:namespace/>eventEnable ){
	var inputData = '<%=inputData%>';
	if( !inputData ){
	    <portlet:namespace/>selectedFile = {};
	    <portlet:namespace/>selectedFile.parent_ = '';
	    <portlet:namespace/>selectedFile.name_ = '';
	    <portlet:namespace/>selectedFile.type_ = 'folder';
	}
	else{
	    <portlet:namespace/>selectedFile = JSON.parse(inputData);
	}
	<portlet:namespace/>passNamespace();
	<portlet:namespace/>loadEPDEditor(<portlet:namespace/>selectedFile, true );
}

function <portlet:namespace/>passNamespace(){
	setTimeout(
			function(){
			    var iframe = document.getElementById('<portlet:namespace/>canvas');
				if ( <portlet:namespace/>iframeReady() && iframe.contentWindow.setNamespace) {
					iframe.contentWindow.setNamespace('<portlet:namespace/>');
				} 
				else{
					<portlet:namespace/>passNamespace();
				}
			}, 
			10
	);
}

function <portlet:namespace/>iframeReady(){
	var iframe = document.getElementById('<portlet:namespace/>canvas');
	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	if ( iframeDoc.readyState  == 'complete' ) {
		return true;
	} 
	else{
		return false;
	}
}
/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/
 $('#<portlet:namespace/>upload').click(function(){
	 var targetFolder;
	 if( <portlet:namespace/>selectedFile.type_ === 'folder' ){
		 targetFolder = <portlet:namespace/>mergePath( 
		                                               <portlet:namespace/>selectedFile.parent_, 
		                                               <portlet:namespace/>selectedFile.name_ );
	 }
	 else if ( <portlet:namespace/>selectedFile.type_ === 'file' || 
			   <portlet:namespace/>selectedFile.type_ === 'ext'){
		 targetFolder = <portlet:namespace/>selectedFile.parent_;
	 }
	 
	 var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data:{
				targetFolder: targetFolder
			}
	 };
	
	Liferay.fire( 'OSP_UPLOAD_FILE', eventData );
	//$('#<portlet:namespace/>selectFile').click();
});

$('#<portlet:namespace/>sample').click(function(){
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector
	}
	
	Liferay.fire( 'OSP_REQUEST_SAMPLE_CONTENT', eventData );
});


/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
 Liferay.on(
		'OSP_HANDSHAKE',
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				<portlet:namespace/>connector = e.portletId;
				if( e.repository )
					<portlet:namespace/>action = e.action;
				else
					<portlet:namespace/>action = 'input';
					
				
				var events = [
					'OSP_EVENTS_REGISTERED',
					'OSP_LOAD_DATA',
					'OSP_REQUEST_DATA'
				];
				var eventData = {
					portletId: myId,
					targetPortlet: <portlet:namespace/>connector,
					data: events
				};
				Liferay.fire( 'OSP_REGISTER_EVENTS', eventData );
			}
		}
);

Liferay.on(
		'OSP_EVENTS_REGISTERED',
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
			    <portlet:namespace/>passNamespace();
			    if( <portlet:namespace/>selectedFile ){
				    <portlet:namespace/>loadEPDEditor( <portlet:namespace/>selectedFile );
				}
			}
		}
);

Liferay.on(
		'OSP_LOAD_DATA',
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				console.log('['+myId+'] OSP_LOAD_DATA: ', e );
				
				var inputData = e.data;
				
				<portlet:namespace/>loadEPDEditor( inputData );
			}
		}
);

Liferay.on(
		'OSP_REQUEST_DATA',
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				var eventData = {
					portletId: myId,
					targetPortlet:e.portletId,
					data: OSP.Util.toJSON(<portlet:namespace/>selectedFile),
					action: e.action
				}
				Liferay.fire( OSP.Event.OSP_RESPONSE_DATA, eventData );
			}
		}
);

Liferay.on(
		OSP.Event.OSP_INITIALIZE,
		function( e ){
			if( e.targetPortlet === '<%=portletDisplay.getId()%>'){
				var inputData = new OSP.InputData();
				inputData.type( OSP.Enumeration.PathType.FOLDER);
				inputData.parent( '' );
				inputData.name('');
				inputData.relative(true);
				<portlet:namespace/>initFileExplorer( inputData );
			}
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
function <portlet:namespace/>initFileExplorer( inputData ){
	//console.log("inputData, init ", inputData, init);
	<portlet:namespace/>selectedFile = inputData.clone();

	<portlet:namespace/>selectedFile.type(inputData.type());
	<portlet:namespace/>selectedFile.relative(inputData.relative());
		
	switch( inputData.type() ){
		case OSP.Enumeration.PathType.FILE:
		case OSP.Enumeration.PathType.FOLDER:
			var filePath = OSP.Util.convertToPath( inputData.name().trim() );
			<portlet:namespace/>selectedFile.parent(OSP.Util.mergePath( inputData.parent(), filePath.parent()));
			<portlet:namespace/>selectedFile.name( filePath.name() ? filePath.name() : '' );
			break;
		case OSP.Enumeration.PathType.EXT:
			var filePath = OSP.Util.convertToPath( inputData.name().trim() );
			<portlet:namespace/>selectedFile.parent(OSP.Util.mergePath( inputData.parent(), filePath.parent()));
			<portlet:namespace/>selectedFile.name( filePath.name() );
			<portlet:namespace/>extension = <portlet:namespace/>selectedFile.name();
			break;
		case OSP.Enumeration.PathType.DLENTRY_ID:
			$('#<portlet:namespace/>selectedFile').val( '--- sample selected ---');
			$('#<portlet:namespace/>selectedFile').attr('disabled', true);
			<portlet:namespace/>sampleSelected = true;
		default:
			<portlet:namespace/>selectedFile.type( OSP.Enumeration.PathType.FOLDER );
			<portlet:namespace/>selectedFile.parent( '' );
			<portlet:namespace/>selectedFile.name( '' );
			<portlet:namespace/>selectedFile.relative( true );
	}
	
	$('#<portlet:namespace/>selectedFile').val(
												OSP.Util.mergePath(<portlet:namespace/>selectedFile.parent(), 
																				 <portlet:namespace/>selectedFile.name()) );
	
	var parentPath;
	var fileName = "";
	if(<portlet:namespace/>selectedFile.type() === OSP.Enumeration.PathType.FOLDER){
	    parentPath = OSP.Util.mergePath(<portlet:namespace/>selectedFile.parent(), 
                                        <portlet:namespace/>selectedFile.name()); 
	}else{
	    parentPath = <portlet:namespace/>selectedFile.parent();
	}
	var pathType = OSP.Enumeration.PathType.FOLDER;
	var relative = <portlet:namespace/>selectedFile.relative();
	<portlet:namespace/>lookupPath(
			'GET_FILE_INFO',
			pathType,
			parentPath,
			fileName
	);
}

function <portlet:namespace/>lookupFolder( parentPath, folderName ){
    console.log( 'Parent Path in lookupFolder: '+parentPath );
	var data = {
				<portlet:namespace/>command: 'GET_FILE_INFO',
				<portlet:namespace/>action: <portlet:namespace/>action,
				<portlet:namespace/>pathType: 'folder',
				<portlet:namespace/>parentPath: parentPath,
				<portlet:namespace/>fileName: folderName
	};

	var fileInfos = null;

	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		data  : data,
		dataType : 'json',
		success: function(data) {
			//console.log(JSON.stringify(data, null, 4));
			console.log( 'Parent Path: '+OSP.Util.mergePath( parentPath, folderName ) );
			<portlet:namespace/>loadFileExplorer( 
											OSP.Util.mergePath( parentPath, folderName ),
			                               data.fileInfos );
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>lookupPath( 
		command, 
		pathType, 
		parentPath, 
		fileName){
	var data = {
					<portlet:namespace/>command: command,
					<portlet:namespace/>action: <portlet:namespace/>action,
					<portlet:namespace/>pathType: pathType,
					<portlet:namespace/>parentPath: parentPath,
					<portlet:namespace/>fileName: fileName
			};
	
	var fileInfos = null;
	
	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		data  : data,
		dataType : 'json',
		success: function(data) {
			fileInfos = data;
			//console.log(JSON.stringify(fileInfos, null, 4));
			<portlet:namespace/>loadFileExplorer( 
			                               parentPath,
			                               fileInfos.fileInfos );
		},
		error:function(data,e){
			console.log(data);
			console.log('AJAX ERROR-->'+e);
		}
	});
	
	return fileInfos;
}
function <portlet:namespace/>loadFileExplorer( parentPath, fileList ){
	console.log('loadFileExplorer parentPath: ', parentPath);
    setTimeout(
	    function(){
	        var iframe = document.getElementById('<portlet:namespace/>canvas');
	        
	        if( <portlet:namespace/>iframeReady() && iframe.contentWindow.loadFileExplorer){
	    		iframe.contentWindow.loadFileExplorer(
	    				parentPath,
	    				fileList,
	    				$('#<portlet:namespace/>canvas').width(),
	    				$('#<portlet:namespace/>canvas').height()
	    		);
	    	}
	    	else{
	    	    <portlet:namespace/>loadFileExplorer(parentPath, fileList );
	    	}
	    }, 
	    10
	);
}

function <portlet:namespace/>setSelectedFile( folderPath, fileName, type ){
	$('#<portlet:namespace/>selectedFile').val( OSP.Util.mergePath( folderPath, fileName ) );
	<portlet:namespace/>selectedFile.type( type );
	<portlet:namespace/>selectedFile.parent( folderPath );
	<portlet:namespace/>selectedFile.name( fileName );
	
	var eventData = {
	     			portletId: '<%=portletDisplay.getId()%>',
	     			targetPortlet: <portlet:namespace/>connector,
	     			data: OSP.Util.toJSON(<portlet:namespace/>selectedFile)
	};
	     	
	Liferay.fire( OSP.Event.OSP_DATA_CHANGED, eventData );
}

function <portlet:namespace/>submitUpload( uploadFile, targetFolder, fileName ){
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

function <portlet:namespace/>mergePath( parent, child ){
	if( !parent && !child )	return '';
	if( !parent )
		return child;
	if( !child )
		return parent;
	
	return parent+'/'+child;
}
</script>



