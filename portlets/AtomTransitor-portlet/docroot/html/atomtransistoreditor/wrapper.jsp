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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/osp-editor.css"/>

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
					Sample FDF files<span class="caret"></span>
				</button>
				<!-- Link or button to toggle dropdown -->
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="javascript:$('#<portlet:namespace/>selectFile').click()"><i class="icon-folder-open"> Open local...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>openFileExplorer()"><i class="icon-folder-open"> Open server...</i></a></li>					 
				</ul>				
				<input type="button" id="CC_Struc" value=" View the input structure" onclick="<portlet:namespace/>fireTextChangedEvent()">	
				    
			</div>
			
		</div>	
	</div>
	
<div class="row-fluid frame">
		<iframe 
			class="col-sm-12 iframe-canvas"
			id="<portlet:namespace/>TBox" style="height:900px; width:600px"
			src="<%=request.getContextPath()%>/html/atomtransistoreditor/AtomTransistor_editor.jsp"
		></iframe>
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
<portlet:namespace/>passNamespace();

var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');


if( <portlet:namespace/>eventEnable === false ){
	<portlet:namespace/>initData = JSON.parse('<%=inputData%>');
	if( !<portlet:namespace/>initData.repositoryType_ ){
		<portlet:namespace/>initData.repositoryType_ = '<%=OSPRepositoryTypes.USER_HOME.toString()%>';
	}

	<portlet:namespace/>loadEPDEditor( OSP.Util.toJSON(<portlet:namespace/>initData) );
}

Liferay.on(
	'OSP_HANDSHAKE', 
	function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
	
		console.log(myId+' >> OSP_HANDSHAKE: ['+e.portletId+']', e);
		
		<portlet:namespace/>connector = e.portletId;		
				
		if( e.mode )
			<portlet:namespace/>mode = e.mode;
		else
			<portlet:namespace/>mode = 'VIEW';
			
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
);

Liferay.on(
	'OSP_EVENTS_REGISTERED', 
	function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
		
		console.log(myId+' >> OSP_EVENTS_REGISTERED: ['+e.portletId+']', e);
	}
);

function <portlet:namespace/>passNamespace(){
	setTimeout(
		function(){
			var iframe = document.getElementById('<portlet:namespace/>TBox');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			if ( iframeDoc.readyState  == 'complete' && iframe.contentWindow.setNamespace ) {
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
		 
Liferay.on( 
	'OSP_LOAD_DATA', 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet !== myId )	return;
	
		console.log(myId+' >> OSP_LOAD_DATA: ['+e.portletId+']', e);
		
		<portlet:namespace/>initData = e.data;
		if( ! <portlet:namespace/>initData.repositoryType_){
			<portlet:namespace/>initData.repositoryType_ = 'USER_HOME';
		}
		
		<portlet:namespace/>loadEPDEditor( OSP.Util.toJSON(<portlet:namespace/>initData) );
	}
);

Liferay.on( 
		'OSP_INITIALIZE', 
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet !== myId )	return;
		
			console.log(myId+' >> OSP_INITIALIZE: ['+e.portletId+']', e);
			
			<portlet:namespace/>displayEPData();
		}
	);


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
			console.log('InputData not available: ', inputData );
			break;
	}
}

function <portlet:namespace/>readFileContent( inputData ){
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
		dataType : 'json',
		success: function(result) {
			<portlet:namespace/>displayEPData(result.context_);
		},
		error:function(result,e){
			console.log(result);
			console.log('AJAX ERROR-->', inputData);
		}
	});
}

function <portlet:namespace/>displayEPData( data ){
    // Get a handle to the iframe element
    setTimeout(
    	function(){
    	    var iframe = document.getElementById('<portlet:namespace/>TBox');
    	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

    	    // Check if loading is complete
    	    console.log( 'iframeDoc.readyState', iframeDoc.readyState);
    	    if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.displayEPDEditor ) {
   	        	iframe.contentWindow.displayEPDEditor( data );
    	    } 
    	    else{
    	    	<portlet:namespace/>displayEPData( data );
    	    }
    	},
    	10
    );
}



Liferay.on(
	'OSP_REQUEST_DATA', 
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		
		if( e.targetPortlet !== myId )	return;
		
		console.log(myId+' >> OSP_REQUEST_DATA: ['+e.portletId+']', e);

		var content = $('#<portlet:namespace/>TBox').prop('contentWindow').getParameters();
		console.log("EDP Editor : ", content);
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
		var myId = '<%=portletDisplay.getId()%>';
		
		if( e.targetPortlet !== myId )	return;
		
		console.log(myId+' >> OSP_INITIALIZE: ['+e.portletId+']', e);
		
	}
);

function <portlet:namespace/>fireTextChangedEvent( data ){
	console.log('<portlet:namespace/>fireTextChangedEvent', data );
	
	//$('#<portlet:namespace/>').click();
	
	
	<portlet:namespace/>ViewStructure();
	
	

}




Liferay.on(
       	'Receive_Struc_from_Viewer',
       	function( e ){
       		console.log('Receive_Struc_from_Viewer: ', e.data );
       		var object = e.data;

       		<portlet:namespace/>Send_Struc_to_S( object );
       	}
       );





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
				<portlet:namespace/>load_FDF_P(e.target.result);
				delete <portlet:namespace/>currentData;
			};

			reader.readAsText(inputFile);
		}
);



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
			for( var i=AtomCoor_start_i            ; i<=AtomCoor_end_i            ; i++ ) {	seldata += lines[i]+"\n";}
			for( var i=SuperCell_start_i           ; i<=SuperCell_end_i           ; i++ ) {	seldata += lines[i]+"\n";}
			
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: <portlet:namespace/>connector,
					data: seldata
			};
					
			Liferay.fire('Receive_Struc_from_Editor', eventData );
			
		}




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
			if( iframeDoc.readyState  == 'complete' && iframe.contentWindow.load_FDF_S ){
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
				if( iframeDoc.readyState  == 'complete' && iframe.contentWindow.load_struc_from_P ){
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