<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>


<!-- JQuery -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/osp-analyzer.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
%>

<div class="container-fluid osp-analyzer">
	<div class="row-fluid header">
		<div class="col-sm-10" id="<portlet:namespace/>title"></div>
		<div class="col-sm-2" >
			<div class="dropdown right">
             
				
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					View the output structure<span class="caret"></span>
				</button>
				<!-- Link or button to toggle dropdown -->
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="javascript:$('#<portlet:namespace/>selectFile').click()"><i class="icon-folder-open"> Open local...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>openFileExplorer()"><i class="icon-folder-open"> Open server...</i></a></li>					 
				</ul>
			</div>
		</div>	
	</div>
	
	
	<div class="row-fluid frame">
		<iframe style="height:1000px" class="col-sm-12 iframe-canvas" id="<portlet:namespace/>canvas" src="<%=request.getContextPath()%>/html/atomtransistoranalyzer/AtomTransistor_Viewer.jsp">
		</iframe>
	</div>	
</div>
		
<div id="<portlet:namespace/>hiddenSection" class="osp-analyzer hidden">
	<div id="<portlet:namespace/>fileExplorer" title="Select a file" >
		<div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
		<div>
			<input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
			<input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
		</div>
	</div>
	<input type="file" id="<portlet:namespace/>selectFile"/>
	
</div>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
 <portlet:namespace/>passNamespace();
 var atomTransitorAnalyzerImagePath = "<%=request.getContextPath()%>/html/atomtransistoranalyzer/pallet.png";
 var <portlet:namespace/>connector = '<%=connector%>';
 var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
 var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_epv";
 if( "<portlet:namespace/>".lastIndexOf("_INSTANCE_") > 0)
 	<portlet:namespace/>fileExplorerId += "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
 else
 	<portlet:namespace/>fileExplorerId += '001';
 var <portlet:namespace/>initData;
 var <portlet:namespace/>currentData;
 var <portlet:namespace/>mode = '<%=mode%>';
 var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');

/***********************************************************************
 * Initialization section using parameters
 ***********************************************************************/
 if( <portlet:namespace/>eventEnable === false ){
	<portlet:namespace/>initialize( JSON.parse('<%=inputData%>') );
	<portlet:namespace/>loadEPDViewer( JSON.parse( JSON.stringify(<portlet:namespace/>initData) ) );
	<portlet:namespace/>initializeFileExplorer();
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
 $("#<portlet:namespace/>file-explorer-ok").click(function(e){
	e.preventDefault();
	var eventData = {
			portletId : '<%=portletDisplay.getId()%>',
			targetPortlet : <portlet:namespace/>fileExplorerId
	};
	Liferay.fire( 'OSP_REQUEST_DATA', eventData);
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
	e.preventDefault();
	<portlet:namespace/>closeFileExplorer();
});


$('#<portlet:namespace/>selectFile').bind(
		'change', 
		function(event){
			var reader = new FileReader();
			var inputFile = this.files[0];

			reader.onload = function (e) {
				<portlet:namespace/>loadEPData(e.target.result);
				delete <portlet:namespace/>currentData;
			};

			reader.readAsText(inputFile);
		}
);
$('#<portlet:namespace/>selectFile2').bind(
		'change', 
		function(event){
			var reader = new FileReader();
			var inputFile = this.files[0];

			reader.onload = function (e) {
				<portlet:namespace/>loadEPData2(e.target.result);
				delete <portlet:namespace/>currentData;
			};

			reader.readAsText(inputFile);
		}
);

function <portlet:namespace/>openFileExplorer(){
	AUI().use('liferay-portlet-url', function(A){
		if($("#<portlet:namespace/>file-explorer-content").children().length > 0){
			$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}else{
			var inputData;
			if(	!$.isEmptyObject(<portlet:namespace/>initData) && (
				<portlet:namespace/>initData.type_ === 'file' ||
				<portlet:namespace/>initData.type_ === 'folder' ||
				<portlet:namespace/>initData.type_ === 'ext' )){
				inputData = JSON.parse( JSON.stringify(<portlet:namespace/>initData) );
			}
			else{
				inputData = {};
				inputData.repositoryType_ = '<%=OSPRepositoryTypes.USER_HOME.toString()%>';
				inputData.type_ ='folder';
				inputData.parent = '';
				inputData.name_ = '';
			}
			
			var dialogURL = Liferay.PortletURL.createRenderURL();
			dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
			dialogURL.setParameter('inputData', JSON.stringify(inputData));
			dialogURL.setParameter('mode', 'VIEW');
			dialogURL.setParameter('eventEnable', false);
			dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
			dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
			
			$("#<portlet:namespace/>file-explorer-content").load( dialogURL.toString() );
			$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}
	});
} 

function <portlet:namespace/>closeFileExplorer(){
	$<portlet:namespace/>fileExplorerDialogSection.dialog("close");
}
 /***********************************************************************
  * Handling OSP Events
  ***********************************************************************/

Liferay.on( 
	'LOCAL_WignerFET_Draw_Graph', 
	function(eventData){	
		var myId = '<%=portletDisplay.getId()%>';	
		
		var data = eventData.data;		
				
		$('#<portlet:namespace/>canvas').each(function(){
			// <portlet:namespace/>drawDevice( data );
		});		
	}
);


Liferay.on(
	'OSP_HANDSHAKE',
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( myId !== e.targetPortlet )	return;
		
		console.log(e.targetPortlet+'>> OSP_EVENTS_REGISTERED: ['+e.portletId+']', e);
			
		<portlet:namespace/>connector = e.portletId;
		if( e.action )
			<portlet:namespace/>mode = e.mode;
		else
			<portlet:namespace/>mode = 'VIEW';
			
		var events = [
			'OSP_EVENTS_REGISTERED',
			'OSP_LOAD_DATA',
			'OSP_REFRESH_OUTPUT_VIEW',
			'OSP_INITIALIZE'
		];
		var eventData = {
			portletId: myId,
			targetPortlet: <portlet:namespace/>connector,
			data: events
		};
		Liferay.fire( 'OSP_REGISTER_EVENTS', eventData );
	}
);

Liferay.on(
	'OSP_EVENTS_REGISTERED',
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( myId !== e.targetPortlet )	return;
		
		console.log(e.targetPortlet+'>> OSP_EVENTS_REGISTERED: ['+e.portletId+']', e);
	}
);

Liferay.on( 
	'OSP_LOAD_DATA', 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
		
		console.log(e.targetPortlet+'>> OSP_RESPONSE_DATA: ['+e.portletId+']', e);
		
		<portlet:namespace/>initialize( e.data );
	
		<portlet:namespace/>loadEPDViewer( JSON.parse( JSON.stringify(<portlet:namespace/>initData) ) );
		<portlet:namespace/>initializeFileExplorer();
	}
);

Liferay.on(
	'EP_DRAW_OBJECTS',
	function( e ){
		console.log('EP_DRAW_OBJECTS:', e.data );
		var objects = e.data;
		
		<portlet:namespace/>loadEPDViewer( objects );
	}
);







Liferay.on(
	'OSP_RESPONSE_DATA',
	function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if( myId !== e.targetPortlet )	return;
		
		console.log(e.targetPortlet+'>> OSP_RESPONSE_DATA: ['+e.portletId+']', e);
			
		if( e.portletId === <portlet:namespace/>fileExplorerId ){
			var inputData = e.data;

			if( inputData.type_ !== 'file' ){
				alert( 'File should be selected!' );
				return;
			}
			else{
				<portlet:namespace/>loadEPDViewer( inputData );
				<portlet:namespace/>closeFileExplorer();
			}
		}
	}
);

Liferay.on(
	'OSP_REFRESH_OUTPUT_VIEW',
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( myId !== e.targetPortlet )	return;
		
		console.log(e.targetPortlet+'>> OSP_REFRESH_OUTPUT_VIEW: ['+e.portletId+']', e);
		var eventData = {
				portletId: '<%=portletDisplay.getId()%>',
				targetPortlet: <portlet:namespace/>connector
		};

		Liferay.fire('OSP_REQUEST_OUTPUT_PATH', eventData);
	}
);

Liferay.on(
		'OSP_INITIALIZE',
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( myId !== e.targetPortlet )	return;
			
			console.log(e.targetPortlet+'>> OSP_INITIALIZE: ['+e.portletId+']', e);
			$("#<portlet:namespace/>canvas").attr('src', '<%=request.getContextPath()%>/html/atomtransistoranalyzer/AtomTransistor_Viewer.jsp');
			if( $.isEmptyObject(<portlet:namespace/>initData) )	return;
   			
   			<portlet:namespace/>initializeFileExplorer();
		}
);

Liferay.on(
       	'Receive_Struc_from_Editor',
       	function( e ){
       		console.log('Receive_Struc_from_Editor: ', e.data );
       		var object = e.data;
       		
       		<portlet:namespace/>Send_Struc_to_S( object );
       	}
       );

/*******************************************************************
 * Golbal functions
 ***********************************************************************/
 // added
 function <portlet:namespace/>passNamespace(){
		setTimeout(
			function(){
				var iframe = document.getElementById('<portlet:namespace/>canvas');
				var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
				if ( <portlet:namespace/>iframeReady() && iframe.contentWindow.setNamespace ) {
					console.log("set");
					iframe.contentWindow.setNamespace( '<portlet:namespace/>');
				}
				else{
					console.log("pass");
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

 
 function <portlet:namespace/>loadEPDViewer( inputData ){
		switch( inputData.type_ ){
			case 'file':
				<portlet:namespace/>readFileContent( inputData );
				break;
			case 'folder':
			case 'ext':
				<portlet:namespace/>getFirstFileName( inputData );
				break;
			case 'context':
			case 'fileContent':
				<portlet:namespace/>loadEPData( inputData.context_ );				
				break;
			default:
				alert('Un supported yet.');
		}
	}

function <portlet:namespace/>displayEPDViewer( data ){
	setTimeout(
		function(){
			var iframe = document.getElementById('<portlet:namespace/>canvas');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			if( iframeDoc.readyState  == 'complete' && iframe.contentWindow.loadEPData ){
				iframe.contentWindow.loadEPData( data ); 
			}
			else{
				<portlet:namespace/>displayEPDViewer( data );
			}
		}, 
		10
	);
}




function <portlet:namespace/>Send_Struc_to_Editor( data ){
	console.log('<portlet:namespace/>Send_Struc_to_Editor', data );
	
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: data
	};
	
	Liferay.fire('Receive_Struc_from_Viewer', eventData );
}

function <portlet:namespace/>Send_Struc_to_S( data )
{
	
	setTimeout(
			function(){
				var iframe = document.getElementById('<portlet:namespace/>canvas');
				var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
				if( iframeDoc.readyState  == 'complete' && iframe.contentWindow.load_AtomCoor ){
					iframe.contentWindow.load_AtomCoor( data ); 
				}
				else{
					<portlet:namespace/>Send_Struc_to_S( data );
				}
			}, 
			10
		);
	
	
}

function <portlet:namespace/>fireReceiveStrucEvent( data )//??
{
	
	
	/*
	setTimeout(
			function(){
				var iframe = document.getElementById('<portlet:namespace/>canvas');
				var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
				if( iframeDoc.readyState  == 'complete' && iframe.contentWindow.load_AtomCoor ){
					iframe.contentWindow.load_AtomCoor( data ); 
				}
				else{
					<portlet:namespace/>Send_Struc_to_S( data );
				}
			}, 
			10
		);
	*/
	
}

/*
function <portlet:namespace/>fireMoveObjectPositionEvent( data ){
	console.log('<portlet:namespace/>fireMoveObjectPositionEvent', data );
	
	var eventData = {
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet: <portlet:namespace/>connector,
			data: data
	};
	
	Liferay.fire('EP_MOVE_OBJECT', eventData );
}
*/

function <portlet:namespace/>changeEPTransparency( shape, o ){
	setTimeout(
		function(){
			var iframe = document.getElementById('<portlet:namespace/>canvas');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			if( iframeDoc.readyState  == 'complete' && iframe.contentWindow.changeEPTransparency ){
				iframe.contentWindow.changeEPTransparency( shape, o ); 
			}
			else{
				<portlet:namespace/>changeEPTransparency( shape, o );
			}
		}, 
		10
	);
}



function <portlet:namespace/>loadEPData( data ){
	setTimeout(
		function(){
			var iframe = document.getElementById('<portlet:namespace/>canvas');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			if( iframeDoc.readyState  == 'complete' && iframe.contentWindow.loadEPData ){
				iframe.contentWindow.loadEPData( data ); 
			}
			else{
				<portlet:namespace/>loadEPData( data );
			}
		}, 
		10
	);
}


function <portlet:namespace/>readFileContent( inputData ){
	var data = {
			<portlet:namespace/>command: 'READ_FILE',
			<portlet:namespace/>pathType: inputData.type_,
			<portlet:namespace/>repositoryType: inputData.repositoryType_,
			<portlet:namespace/>parentPath: inputData.parent_,
			<portlet:namespace/>fileName: inputData.name_
	};
	
	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>', 
		data  : data,
		dataType : 'text',
		success: function(fileContent) {
			<portlet:namespace/>displayEPDViewer( fileContent );
			<portlet:namespace/>currentData = inputData;
		},
		error:function(d, e){
			console.log('[DeviceModelViewer] readFileContent() Ajax ERROR: ', inputData );
		}
	});
}

function <portlet:namespace/>getFirstFileName( inputData ){
	var data = {
			<portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
			<portlet:namespace/>pathType: inputData.type_,
			<portlet:namespace/>repositoryType: inputData.repositoryType_,
			<portlet:namespace/>parentPath: inputData.parent_,
			<portlet:namespace/>fileName: inputData.name_
	};

	$.ajax({
		url: '<%= serveResourceURL.toString()%>', 
		type: 'POST',
		data  : data,
		dataType : 'json',
		success: function(data) {
			inputData.name_ = data.fileName;
			inputData.type_ = 'file';
			<portlet:namespace/>loadEPDViewer( inputData );
		},
		error:function(data,e){
			console.log('AJAX ERROR getFirstFileName()', inputData);
		}
	});
}

function <portlet:namespace/>downloadCurrentFile(){
	if( $.isEmptyObject(<portlet:namespace/>currentData) || 
		<portlet:namespace/>currentData.type_ !== 'file' )
		return;

	var filePath = <portlet:namespace/>currentData;
	var data = {
			<portlet:namespace/>command: 'DOWNLOAD_FILE',
			<portlet:namespace/>pathType: filePath.type_,
			<portlet:namespace/>repositoryType: filePath.repositoryType_,
			<portlet:namespace/>parentPath: filePath.parent_,
			<portlet:namespace/>fileName: filePath.name_
		};

	var base = '<%=serveResourceURL.toString()%>';
	var sep = (base.indexOf('?') > -1) ? '&' : '?';
	var url = base + sep + $.param(data);
	location.href = url;
}

function <portlet:namespace/>setTitle( title ){
	if( !title ){
		title = '<h4>/'+
				<portlet:namespace/>currentData.name_ +
				'</h4>';
	}
	$('#<portlet:namespace/>title').html('<h4>'+title+'</h4>');
}

function <portlet:namespace/>initializeFileExplorer(){
	if( $.isEmptyObject(<portlet:namespace/>initData) ||( 
		<portlet:namespace/>initData.type_ !== 'file' &&
		<portlet:namespace/>initData.type_ !== 'folder' &&
		<portlet:namespace/>initData.type_ !== 'ext' ))	return;
	
	var eventData = {
              portletId: '<%=portletDisplay.getId()%>',
              targetPortlet: <portlet:namespace/>fileExplorerId,
              data: <portlet:namespace/>initData
	};
	
	Liferay.fire( 'OSP_LOAD_DATA', eventData );
}

function <portlet:namespace/>initialize( inputData ){
	inputData.parent_ = OSP.Util.removeEndSlashes(inputData.parent_);
	inputData.name_ = OSP.Util.removeEndSlashes(inputData.name_ );
	
	if( $.isEmptyObject( inputData ) ){
		return;
	}
	else{
		<portlet:namespace/>initData = JSON.parse( JSON.stringify(inputData) );
		
		if( !<portlet:namespace/>initData.repositoryType_ ){
			<portlet:namespace/>initData.repositoryType_ = '<%=OSPRepositoryTypes.USER_JOBS.toString()%>';
		}
	
		switch( <portlet:namespace/>initData.type_ ){
			case 'file':
				var subPath = OSP.Util.convertToPath( <portlet:namespace/>initData.name_ );
				
				<portlet:namespace/>initData.parent_ = <portlet:namespace/>mergePath( <portlet:namespace/>initData.parent_, subPath.parent_ );
				<portlet:namespace/>initData.name_ = subPath.name_;
				break;
			case 'folder':
				<portlet:namespace/>initData.parent_ = <portlet:namespace/>mergePath( <portlet:namespace/>initData.parent_, <portlet:namespace/>initData.name_ );
				<portlet:namespace/>initData.name_ = '';
				break;
			case 'ext':
				var subPath = OSP.Util.convertToPath( <portlet:namespace/>initData.name_ );
				<portlet:namespace/>initData.parent_ = <portlet:namespace/>mergePath( <portlet:namespace/>initData.parent_, subPath.parent_);
				<portlet:namespace/>initData.name_ = subPath.name_ ;
				break;
			case 'dlEntryId':
			case 'fileContent':
			case 'url':
				break;
			default:
				console.log('OSPProteinViewer: Un-expected type: ' + <portlet:namespace/>initData.type_);
				<portlet:namespace/>initData = {};
				<portlet:namespace/>initData.parent_ = '';
				<portlet:namespace/>initData.name_ = '';
				<portlet:namespace/>initData.type_ = 'folder';
				<portlet:namespace/>initData.repositoryType_ = '<%=OSPRepositoryTypes.USER_HOME.toString()%>';
				break;
		}
	}
}

function <portlet:namespace/>mergePath( args ){
	var merged = '';
	for( var i=0; i<arguments.length; i++ ){
		if( arguments[i] )
			merged += arguments[i] + '/';
	}
	merged = merged.substring(0, merged.length-1);
	return merged;
}
</script>