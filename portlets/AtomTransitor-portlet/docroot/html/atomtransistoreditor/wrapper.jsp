<%@page import="com.kisti.osp.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<!-- JQuery -->
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

<div class="container-fluid osp-editor">
	<div class="row-fluid header">
		<div class="col-sm-10" id="<portlet:namespace/>title"></div>
		<div class="col-sm-2" >
			<div class="dropdown text-right">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					Sample FDF files<span class="caret"></span>
				</button>
				<!-- Link or button to toggle dropdown -->
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="javascript:<portlet:namespace/>takeSample();"><i class="icon-file"> Sample</i></a></li>
					<li><a href="javascript:$('#<portlet:namespace/>selectFile').click();"><i class="icon-folder-open"> Open local...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>openFileExplorer();"><i class="icon-folder-open"> Open server...</i></a></li>					 
				</ul>				
			</div>
			<div class="text-right">
				<input type="checkbox" id="CC_Struc" value=" View the input structure" onclick="<portlet:namespace/>fireTextChangedEvent()">
				View the input structure
			</div>
		</div>	
	</div>

	<div class="row-fluid" style="height:90%">
		<iframe class="span12 canvas" id="<portlet:namespace/>TBox" src="<%=request.getContextPath()%>/html/atomtransistoreditor/AtomTransistor_editor.jsp"></iframe>
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
<script>console.log("[ATOM EDITOR] test 1 yejin 3")</script>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/

<portlet:namespace/>passNamespace();

var <portlet:namespace/>connector = 'broadcast';
var $<portlet:namespace/>fileExplorerDialogSection = $("#<portlet:namespace/>fileExplorer");
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_at";
if( "<portlet:namespace/>".lastIndexOf("_INSTANCE_") > 0)
	<portlet:namespace/>fileExplorerId += "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
else
	<portlet:namespace/>fileExplorerId += "001";

console.log('[ATOM EDITOR] file explorer id Check : '+<portlet:namespace/>fileExplorerId);

var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>saveAction = false;
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');


/***********************************************************************
 * Menu click events and binding functions 
 ***********************************************************************/

$('#<portlet:namespace/>selectFile').bind(
		'change', 
		function(event){
			var reader = new FileReader();
			var inputFile = this.files[0];

			reader.onload = function (e) {
				<portlet:namespace/>load_FDF_P(e.target.result);
				delete <portlet:namespace/>currentData;
			};

			reader.readAsText(inputFile);
		}
);

$("#<portlet:namespace/>fileExplorer").dialog({
	autoOpen: false,
	resizable: false,
	height: 600,
	width: 600,
	modal: true,
	show: {effect:'fade', speed: 800}, 
    hide: {effect:'fade', speed: 800}
});
$(".ui-dialog-titlebar").remove();

$("#<portlet:namespace/>file-explorer-ok").click(function(e){
	e.preventDefault();
	var eventData = {
		portletId : '<%=portletDisplay.getId()%>',
		targetPortlet : <portlet:namespace/>fileExplorerId,
	};
	Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
	$<portlet:namespace/>fileExplorerDialogSection.dialog( 'close' );
});



/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
 if( "<%=eventEnable%>" == false ){
		<portlet:namespace/>connector = "<%=connector%>";
		
		<portlet:namespace/>initData = new OSP.InputData(JSON.parse("<%=inputData%>"));
		if( !<portlet:namespace/>initData.repositoryType_ )
			<portlet:namespace/>initData.repositoryType("<%=OSPRepositoryTypes.USER_HOME.toString()%>");
		
		
		<portlet:namespace/>loadEPDEditor( OSP.Util.toJSON( <portlet:namespace/>initData.clone() ) );
		
		<portlet:namespace/>initializeFileExplorer();
}
 

function <portlet:namespace/>openFileExplorer(){
		AUI().use('liferay-portlet-url', function(A){
			if($("#<portlet:namespace/>file-explorer-content").children().length > 0){
				console.log("[ATOM EDITOR] test open file exlplore 1 open");			
				$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
			}else{
				var inputData;
				if(	!$.isEmptyObject(<portlet:namespace/>initData) && (
					<portlet:namespace/>initData.type_ === OSP.Enumeration.PathType.FILE ||
					<portlet:namespace/>initData.type_ === OSP.Enumeration.PathType.FOLDER ||
					<portlet:namespace/>initData.type_ === OSP.Enumeration.PathType.EXT )){
					inputData = <portlet:namespace/>initData;
				}
				else{
					inputData = new OSP.InputData();
					inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
					inputData.type( OSP.Enumeration.PathType.FOLDER );
					inputData.parent('');
					inputData.name('');
				}
				
				var dialogURL = Liferay.PortletURL.createRenderURL();
				dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
				dialogURL.setParameter('inputData', JSON.stringify(inputData));
				dialogURL.setParameter('mode', 'VIEW');
				dialogURL.setParameter('eventEnable', false);
				dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
				dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
				
				$("#<portlet:namespace/>file-explorer-content").load( dialogURL.toString());
				$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
			}
		});
	}
	 
	 
	function <portlet:namespace/>initializeFileExplorer(){
		if( $.isEmptyObject(<portlet:namespace/>initData) ||( 
			<portlet:namespace/>initData.type_ !== OSP.Enumeration.PathType.FILE &&
			<portlet:namespace/>initData.type_ !== OSP.Enumeration.PathType.FOLDER &&
			<portlet:namespace/>initData.type_ !== OSP.Enumeration.PathType.EXT ))	return;

		var eventData = {
	              portletId: '<%=portletDisplay.getId()%>',
	              targetPortlet: <portlet:namespace/>fileExplorerId,
	              data: OSP.Util.toJSON(<portlet:namespace/>initData)
		};
		
		Liferay.fire( 'OSP_LOAD_DATA', eventData );
	}


/***********************************************************************
 * Workbench global functions 
 ***********************************************************************/

function <portlet:namespace/>passNamespace(){
	setTimeout(
		function(){
			var iframe = document.getElementById("<portlet:namespace/>TBox");
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			if ( <portlet:namespace/>iframeReady() && iframe.contentWindow.setNamespace ) {
				console.log("[ATOM EDITOR] set name space");
				iframe.contentWindow.setNamespace( "<portlet:namespace/>");
			}
			else{
				console.log("[ATOM EDITOR] pass name space");
				<portlet:namespace/>passNamespace();
			}
		}, 
		10
	);
}

function <portlet:namespace/>iframeReady(){
	console.log("[ATOM EDITOR] iframeReady() function");
	var iframe = document.getElementById("<portlet:namespace/>TBox");
	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	if ( iframeDoc.readyState  == "complete" ) {
		return true;
	} 
	else{
		return false;
	}
}

function <portlet:namespace/>takeSample(){
	var myId = '<%=portletDisplay.getId()%>';
	var eventData = {
			portletId: myId,
			targetPortlet: <portlet:namespace/>connector
	};
	console.log("[ATOM EDITOR] take sample ", eventData);
	Liferay.fire( OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, eventData);
}

function <portlet:namespace/>loadText( inputData, changeAlert ){
	
	if( inputData.type() === OSP.Enumeration.PathType.FILE){
		<portlet:namespace/>currentData = inputData;
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
				$('#<portlet:namespace/>TBox').val( data );
				if( changeAlert )
					$('#<portlet:namespace/>TBox').trigger('change');
			},
			error:function(data,e){
				console.log("[ATOM EDITOR] load text error :", data);
				console.log("[ATOM EDITOR] load text error :", e);
			},
			complete: function( jqXHR, textStatus ){
			}
		});
	}
	else if( inputData.type() === OSP.Enumeration.PathType.FILE_CONTENT){
		$('#<portlet:namespace/>TBox').val( inputData.context() );
		if( changeAlert )
			$('#<portlet:namespace/>TBox').trigger('change');
	}
	else if( inputData.type() === OSP.Enumeration.PathType.CONTEXT){
		$('#<portlet:namespace/>TBox').text(Liferay.Util.unescapeHTML(inputData.context()));
		if( changeAlert )
			$('#<portlet:namespace/>TBox').trigger('change');
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
			        console.log( '[ATOM EDITOR][ERROR] read sample: ' + inputData.dlEntryId());
			        alert( '[ATOM EDITOR][ERROR] read sample: ' + inputData.dlEntryId() + '\nPlease contact site manager.');
			    }
			    else{
			        var inputData = new OSP.InputData( result.success);
	                $('#<portlet:namespace/>TBox').val( inputData.context() );
	                if( changeAlert )
						$('#<portlet:namespace/>TBox').trigger('change');
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

function <portlet:namespace/>saveAs(){
	console.log("[ATOM EDITOR] test saveAS fuction")
	var inputData = new OSP.InputData();
	inputData.type( OSP.Enumeration.PathType.FOLDER);
	inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
	inputData.parent( '' );
	inputData.name( '' );
	inputData.relative( true );
	<portlet:namespace/>saveAction = true;
	
	<portlet:namespace/>fileExplorerDialog( 'VIEW', inputData );
 }


 



function <portlet:namespace/>saveContentAs( inputData ){
	console.log("[ATOM EDITOR] test <portlet:namespace/>saveContentAs fuction")

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
			console.log("[ATOM EDITOR] save contentsas function ", data);
			console.log("[ATOM EDITOR] save contentsas function ", e);
			return false;
		}
	});
}




/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
	OSP.Event.OSP_HANDSHAKE,
	function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
		
		console.log("[ATOM EDITOR] OSP HANDSHAKE :", e );
		
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
		console.log("[ATOM EDITOR] OSP HANDSHAKE :", eventData );
		Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
	}
);

Liferay.on(
	'OSP_EVENTS_REGISTERED', 
	function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
		console.log("[ATOM EDITOR] OSP EVENT REGISTERED :", e );
	}
);


Liferay.on( 
		OSP.Event.OSP_REQUEST_DATA, 
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				var iframe = document.getElementById('<portlet:namespace/>TBox');
				console.log("[ATOM EDITOR] OSP OSP_REQUEST_DATA :", e );
				var eventData = {
						portletId: myId,
						targetPortlet: e.portletId,
						data: {
							type_: OSP.Enumeration.PathType.FILE_CONTENT,
							context_: iframe.contentWindow.getParameters(),
							params: e.params
						}
				}				
				console.log("[ATOM EDITOR] OSP OSP_REQUEST_DATA :", eventData );
				Liferay.fire(OSP.Event.OSP_RESPONSE_DATA, eventData);
			}
		}
);

Liferay.on(
		OSP.Event.OSP_RESPONSE_DATA,
		function( e ){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet !== myId )	return;
			console.log("[ATOM EDITOR] OSP OSP_RESPONSE_DATA :", e );
			var iframe = document.getElementById('<portlet:namespace/>TBox');
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
				filePath.context( iframe.contentWindow.getParameters() );
				
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
						console.log(' [ATOM EDITOR] file save error', e, d);
					}
				});
			}
		}
);

		 
Liferay.on( 
	'OSP_LOAD_DATA', 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		console.log("[ATOM EDITOR] OSP LOAD DATA :", myId, e.targetPortlet );
		if( e.targetPortlet !== myId )	return;
	
		console.log("[ATOM EDITOR] OSP LOAD DATA :", e );
		//console.log("Atom Editor Load Data : "+myId+' >> OSP_LOAD_DATA: ['+e.portletId+']', e);
		
		<portlet:namespace/>initData = e.data;
		if( ! <portlet:namespace/>initData.repositoryType_){
			<portlet:namespace/>initData.repositoryType_ = 'USER_HOME';
		}
		<portlet:namespace/>loadEPDEditor( OSP.Util.toJSON(<portlet:namespace/>initData) );
		<portlet:namespace/>initializeFileExplorer();
	}
);

////sample file get 
//			<portlet:namespace/>takeSamPle();loadEPDEditor

Liferay.on( 
		'OSP_INITIALIZE', 
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet !== myId )	return;
			console.log("[ATOM EDITOR] OSP INITIALIZE Editor :", e );
			//console.log(myId+' >> OSP_INITIALIZE: ['+e.portletId+']', e);
			<portlet:namespace/>initializeFileExplorer();
		}
	);


Liferay.on(
	'OSP_REQUEST_DATA', 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		
		if( e.targetPortlet !== myId )	return;
		
		//console.log(myId+' >> OSP_REQUEST_DATA: ['+e.portletId+']', e);
		console.log("[ATOM EDITOR] osp request data ", e);
		var content = $('#<portlet:namespace/>TBox').prop('contentWindow').getParameters();
		console.log("[ATOM EDITOR] EDP Editor : ", content);
		var eventData = {
			portletId: myId,
			targetPortlet: <portlet:namespace/>connector,
			data: {
				type_: 'fileContent',
				repositoryType_: <portlet:namespace/>initData.repositoryType_,
				context_: content,
				params: e.params
			}
		};
		
		Liferay.fire( 'OSP_RESPONSE_DATA', data );
	}
);
		

Liferay.on(
	'OSP_INITIALIZE',
	function(e){
		console.log("[ATOM ANALYZER] osp initialize test");
		var myId = '<%=portletDisplay.getId()%>';
		
		if( e.targetPortlet !== myId )	return;
		
		//console.log(myId+' >> OSP_INITIALIZE: ['+e.portletId+']', e);
		console.log("[ATOM EDITOR] OSP INITIALIZE ", e);
	}
);


Liferay.on(
	'Receive_Struc_from_Viewer',
	function( e ){
		console.log('[ATOM EDITOR]Receive_Struc_from_Viewer: ', e.data );
		var object = e.data;
		<portlet:namespace/>Send_Struc_to_S( object );
	}
);


/***********************************************************************
 * Liferay Workbench Draw functions 
 ***********************************************************************/


function <portlet:namespace/>loadEPDEditor( inputData ){
	switch( inputData.type_ ){
		case 'file':
			<portlet:namespace/>readFileContent( inputData );
			<portlet:namespace/>currentData = inputData;
			break;
		case 'fileContent':
		case 'context':
			<portlet:namespace/>displayEPData(inputData.context_);
			<portlet:namespace/>currentData = inputData;
			break;
		case 'dlFileEntry':
		case 'folder':
		case 'ext':
		case 'url':
			alert.log('Un-supported data type: '+inputData.type() );
			break;
		default:
			console.log('[ATOM EDITOR] InputData not available: ', inputData );
			break;
	}
}

function <portlet:namespace/>readFileContent( inputData ){
	
	console.log("[ATOM EDITOR] input data in read File Content:", inputData);
	var data = Liferay.Util.ns( 
			'<portlet:namespace/>',
			{
				command: 'READ_FILE',
				repositoryType: inputData.repositoryType_,
				pathType: inputData.type_,
				parentPath: inputData.parent_,
				fileName: inputData.name_
			}
	);
		
	$.ajax({
		url: '<%= serveResourceURL.toString()%>', 
		type: 'POST',
		data  : data,
		dataType : 'text',
		success: function(result) {
			console.log("[ATOM EDITOR] file content load test : ", result);
			<portlet:namespace/>displayEPData(result);
			<portlet:namespace/>load_FDF_P( result );
		},
		error:function(result,e){
			console.log("[ATOM EDITOR] ajax error", result);
		}
	});
}

function <portlet:namespace/>displayEPData( data ){
    // Get a handle to the iframe element
    console.log("[ATOM EDITOR] Display EPDData1 : ", data);
    setTimeout(
    	function(){
    	    var iframe = document.getElementById('<portlet:namespace/>TBox');
    	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

    	    // Check if loading is complete
    	    console.log( '[ATOM EDITOR] iframeDoc.readyState', <portlet:namespace/>iframeReady());
    	    if (  <portlet:namespace/>iframeReady() && iframe.contentWindow.load_struc_from_P ) {
    	    	console.log("[ATOM EDITOR] Yejin in Display EPDData2 : ", data);
   	        	iframe.contentWindow.load_struc_from_P( data );
   	        	iframe.contentWindow.load_FDF_S( data );
    	    } 
    	    else{
    	    	<portlet:namespace/>displayEPData( data );
    	    }
    	},
    	10
    );
}




function <portlet:namespace/>fireTextChangedEvent( data ){
	console.log('[ATOM EDITOR]test change event fire1 ', data );
	console.log('[ATOM EDITOR]test change event fire2 ', <portlet:namespace/>initData );
	console.log('[ATOM EDITOR]test change event fire3 ', <portlet:namespace/>initData.repositoryType_ );
	//$('#<portlet:namespace/>').click();
	//if(document.getElementById("CC_Struc").checked)	
		<portlet:namespace/>ViewStructure();

		var inputData = new OSP.InputData();
		inputData.type( OSP.Enumeration.PathType.FILE_CONTENT );
		if( $.isEmptyObject(<portlet:namespace/>initData) ){
			inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
		}
		else if( <portlet:namespace/>initData.repositoryType_ ){
			console.log("[ATOM EDITOR] test re[psotpry Type]]", <portlet:namespace/>initData);	
			inputData.repositoryType(<portlet:namespace/>initData.repositoryType_);
		}
		else{
			inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
		}
		inputData.context( data );
		
		
		var eventData = {
		     			portletId: '<%=portletDisplay.getId()%>',
		     			targetPortlet: <portlet:namespace/>connector,
		     			data: OSP.Util.toJSON(inputData)
		};
		Liferay.fire( OSP.Event.OSP_DATA_CHANGED, eventData );
}










		function <portlet:namespace/>ViewStructure(){
			
			/*
			if( $(this).is(":checked") === true ){
				//view
			}
			else{
				
			}
			*/
			
			
		//	if( $(this).is(":checked") === false ) return;
		
		
		
		
			
	        var iframe = document.getElementById('<portlet:namespace/>TBox');
			
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			
		//	if( iframe.contentWindow.get_CC_Struc() === false ) return;
							
			var data=iframe.contentWindow.getParameters();
			
			
						
			var N_atoms ;
			var N_Species;
			
			var Lattice_C, unit ;
			
			var ChemicalSpeciesLabel_start_i, ChemicalSpeciesLabel_end_i;	
			var LatticeVectors_start_i, LatticeVectors_end_i ;
			var AtomCoor_start_i            , AtomCoor_end_i ;
			var SuperCell_start_i           , SuperCell_end_i ;		
						
			var lines = data.split('\n');			
			
			for( var index=0; index<lines.length; index++ ){
				var line = lines[index].trim();				
				if( !line )	continue;
				
				line=line.replace(/ +/g, " ");
				
				var keyVal = line.split(' ');
				
				if(keyVal[0] === 'NumberOfAtoms'  ) N_atoms   = Number(keyVal[1]);
				
				if(keyVal[0] === 'NumberOfSpecies') N_Species   = Number(keyVal[1]);
				
				if(keyVal[0] === 'LatticeConstant') {Lattice_C = Number(keyVal[1]); unit = keyVal[2]; }
				
				if(keyVal[1] === 'ChemicalSpeciesLabel') 
				{
				          if(keyVal[0] === '%block'   ) ChemicalSpeciesLabel_start_i = index ;
					 else if(keyVal[0] === '%endblock') ChemicalSpeciesLabel_end_i   = index ;				 
				}
				
				if(keyVal[1] === 'LatticeVectors')
				{
					     if(keyVal[0] === '%block'   ) LatticeVectors_start_i = index ;
					else if(keyVal[0] === '%endblock') LatticeVectors_end_i   = index ;					
				}
							
				if(keyVal[1] === 'AtomicCoordinatesAndAtomicSpecies')
				{
					     if(keyVal[0] === '%block'   ) AtomCoor_start_i = index ;
					else if(keyVal[0] === '%endblock') AtomCoor_end_i   = index ;					
				}
				
				if(keyVal[1] === 'SuperCell')
				{
					     if(keyVal[0] === '%block'   ) SuperCell_start_i = index ;
					else if(keyVal[0] === '%endblock') SuperCell_end_i   = index ;
					
				}
				
			}
			
			
			
			
			var seldata= "";
			
			seldata += "NumberOfAtoms "  + N_atoms   +"\n";
			seldata += "NumberOfSpecies "+ N_Species +"\n";
			
			seldata += "LatticeConstant "+Lattice_C  +" "+unit+"\n";			
			
			for( var i=ChemicalSpeciesLabel_start_i; i<=ChemicalSpeciesLabel_end_i; i++ ) {	seldata += lines[i]+"\n";}
			for( var i=LatticeVectors_start_i      ; i<=LatticeVectors_end_i      ; i++ ) {	seldata += lines[i]+"\n";}
			for( var i=AtomCoor_start_i            ; i<=AtomCoor_end_i            ; i++ ) {	seldata += lines[i]+"\n"; }
			for( var i=SuperCell_start_i           ; i<=SuperCell_end_i           ; i++ ) {	seldata += lines[i]+"\n";}
			
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: <portlet:namespace/>connector,
					data: seldata
			};
					
			Liferay.fire('Receive_Struc_from_Editor', eventData );
			
		}



function <portlet:namespace/>closeFileExplorer(){
	$<portlet:namespace/>fileExplorerDialogSection.dialog("close");
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

function <portlet:namespace/>load_FDF_P( data ){
	setTimeout(
		function(){
			var iframe = document.getElementById('<portlet:namespace/>TBox');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			if( <portlet:namespace/>iframeReady() && iframe.contentWindow.load_FDF_S ){
				iframe.contentWindow.load_FDF_S( data ); 
			}
			else{
				<portlet:namespace/>load_FDF_P( data );
			}
		}, 
		10
	);
}

function <portlet:namespace/>Send_Struc_to_S( data )
{	
	setTimeout(
			function(){
				var iframe = document.getElementById('<portlet:namespace/>TBox');
				var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
				if( <portlet:namespace/>iframeReady() && iframe.contentWindow.load_struc_from_P ){
					iframe.contentWindow.load_struc_from_P( data ); 
				}
				else{
					<portlet:namespace/>Send_Struc_to_S( data );
				}
			}, 
			10
		);
	
	
}


</script>

