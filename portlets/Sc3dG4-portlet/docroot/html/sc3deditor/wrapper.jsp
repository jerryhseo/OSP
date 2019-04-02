<%@ page import="com.kisti.osp.constants.OSPRepositoryTypes" %>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<%@ page import="javax.portlet.PortletPreferences" %>

<%@ include file="../init.jsp" %>

<!-- JQuery -->
<link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css">

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
		<div class="col-sm-2">
			<div class="text-right">
			    <input type="checkbox" id="CC_Struc" value="View the device structure" onclick="<portlet:namespace/>fireSendIniStrucEvent()">View the device structure
			</div>
		</div>
	</div>

	<div class="row-fluid frame">
		<iframe
		class="col-sm-12 iframe-canvas"
		id="<portlet:namespace/>TBox"
		src="<%= request.getContextPath() %>/html/sc3deditor/sc3d_editor.jsp"
		></iframe>
	</div>
</div>

<div class="osp-analyzer hidden" id="<portlet:namespace/>hiddenSection">
	<div id="<portlet:namespace/>fileExplorer" title="Select a file">
		<div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
		<div>
			<input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
			<input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
		</div>
	</div>
	<input id="<portlet:namespace/>selectFile" type="file" />
</div>
<script>console.log("[ATOM EDITOR] test 1 yejin 3")</script>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
<portlet:namespace/>passNamespace();

var <portlet:namespace/>connector = '<%= connector %>';
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%= mode %>';
var <portlet:namespace/>eventEnable = JSON.parse('<%= eventEnable %>');

if ( <portlet:namespace/>eventEnable === false ) {
	<portlet:namespace/>initData = JSON.parse('<%= inputData %>');
	if ( !<portlet:namespace/>initData.repositoryType_ ) {
		<portlet:namespace/>initData.repositoryType_ = '<%= OSPRepositoryTypes.USER_HOME.toString() %>';
	}

	<portlet:namespace/>loadEPDEditor( OSP.Util.toJSON(<portlet:namespace/>initData) );
}

Liferay.on(
	'OSP_HANDSHAKE',
	function( e ) {
		var myId = '<%= portletDisplay.getId() %>';
		if ( e.targetPortlet !== myId ) return;

		console.log(myId+' >> OSP_HANDSHAKE: ['+e.portletId+']', e);

		<portlet:namespace/>connector = e.portletId;

		if ( e.mode )
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
	function( e ) {
		var myId = '<%= portletDisplay.getId() %>';
		if ( e.targetPortlet !== myId ) return;

		console.log(myId+' >> OSP_EVENTS_REGISTERED: ['+e.portletId+']', e);
	}
);

function <portlet:namespace/>passNamespace() {
	setTimeout(
		function() {
			var iframe = document.getElementById('<portlet:namespace/>TBox');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			if ( iframeDoc.readyState == 'complete' && iframe.contentWindow.setNamespace ) {
				console.log("set");
				iframe.contentWindow.setNamespace( '<portlet:namespace/>');
			}
			else {
				console.log("pass");
				<portlet:namespace/>passNamespace();
			}
		},
		10
	);
}

Liferay.on(
	'OSP_LOAD_DATA',
	function(e) {
		var myId = '<%= portletDisplay.getId() %>';
		if ( e.targetPortlet !== myId ) return;

		console.log(myId+' >> OSP_LOAD_DATA: ['+e.portletId+']', e);

		<portlet:namespace/>initData = e.data;
		if ( ! <portlet:namespace/>initData.repositoryType_) {
			<portlet:namespace/>initData.repositoryType_ = 'USER_HOME';
		}

		<portlet:namespace/>loadEPDEditor( OSP.Util.toJSON(<portlet:namespace/>initData) );
	}
);

Liferay.on(
		'OSP_INITIALIZE',
		function(e) {
			var myId = '<%= portletDisplay.getId() %>';
			if ( e.targetPortlet !== myId ) return;

			console.log(myId+' >> OSP_INITIALIZE: ['+e.portletId+']', e);

			<portlet:namespace/>displayEPData();
		}
	);

function <portlet:namespace/>loadEPDEditor( inputData ) {
	switch( inputData.type_ ) {
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

function <portlet:namespace/>readFileContent( inputData ) {
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
		url: '<%= serveResourceURL.toString() %>',
		type: 'POST',
		data : data,
		dataType : 'json',
		success: function(result) {
			<portlet:namespace/>displayEPData(result.context_);
		},
		error:function(result,e) {
			console.log(result);
			console.log('AJAX ERROR-->', inputData);
		}
	});
}

function <portlet:namespace/>displayEPData( data ) {
	 // Get a handle to the iframe element
	 setTimeout(
	 	function() {
	 	    var iframe = document.getElementById('<portlet:namespace/>TBox');
	 	    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	 	    // Check if loading is complete
	 	    if ( iframeDoc.readyState == 'complete' && iframe.contentWindow.displayEPDEditor ) {
	 	    	console.log( 'displayEPData iframeDoc.readyState', iframeDoc.readyState);
	 	    	iframe.contentWindow.displayEPDEditor( data );
	 	    }
	 	    else {
	 	    	<portlet:namespace/>displayEPData( data );
	 	    }
	 	},
	 	10
	 );
}


Liferay.on(
	'OSP_REQUEST_DATA',
	function(e) {
		var myId = '<%= portletDisplay.getId() %>';

		if ( e.targetPortlet !== myId ) return;

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
	function(e) {
		var myId = '<%= portletDisplay.getId() %>';

		if ( e.targetPortlet !== myId ) return;

		console.log(myId+' >> OSP_INITIALIZE: ['+e.portletId+']', e);

	}
);

function <portlet:namespace/>fireSendIniStrucEvent( data ) {
	console.log('<portlet:namespace/>fireSendIniStrucEvent', data );

	//$('#<portlet:namespace/>').click();
	//if (document.getElementById("CC_Struc").checked)
		<portlet:namespace/>Ini_structure();


}

function <portlet:namespace/>fireSendStrucEvent( data ) {
	console.log('<portlet:namespace/>fireSendStrucEvent', data );

	//$('#<portlet:namespace/>').click();

	//if (document.getElementById("CC_Struc").checked)

	<portlet:namespace/>Changed_structure();


}

Liferay.on(
			'Receive_Struc_from_Viewer',
			function( e ) {
				console.log('Receive_Struc_from_Viewer: ', e.data );
				var object = e.data;

				<portlet:namespace/>Send_Struc_to_S( object );
			}
		);



/***********************************************************************
* Menu click events and binding functions
***********************************************************************/
$("#<portlet:namespace/>file-explorer-ok").click(function(e) {
	e.preventDefault();
	var eventData = {
			portletId : '<%= portletDisplay.getId() %>',
			targetPortlet : <portlet:namespace/>fileExplorerId
	};
	Liferay.fire( 'OSP_REQUEST_DATA', eventData);
});

$("#<portlet:namespace/>file-explorer-cancel").click(function(e) {
	e.preventDefault();
	<portlet:namespace/>closeFileExplorer();
});

$('#<portlet:namespace/>selectFile').bind(
		'change',
		function(event) {
			var reader = new FileReader();
			var inputFile = this.files[0];

			reader.onload = function(e) {
				<portlet:namespace/>load_FDF_P(e.target.result);
				delete <portlet:namespace/>currentData;
			};

			reader.readAsText(inputFile);
		}
);


function <portlet:namespace/>Ini_structure() {
	var iframe = document.getElementById('<portlet:namespace/>TBox');
	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
	
	var seldata=iframe.contentWindow.getStructure();
	var eventData = {
						portletId: '<%= portletDisplay.getId() %>',
						targetPortlet: <portlet:namespace/>connector,
						data: seldata
					};
	
	Liferay.fire('Receive_IniStruc_from_Editor', eventData );
	<portlet:namespace/>fireTextChangedEvent( seldata );
}

function <portlet:namespace/>Changed_structure() {
	var iframe = document.getElementById('<portlet:namespace/>TBox');
	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
	var seldata=iframe.contentWindow.getStructure();

	var eventData = {
		portletId: '<%= portletDisplay.getId() %>',
		targetPortlet: <portlet:namespace/>connector,
		data: seldata
	};

	Liferay.fire('Receive_Struc_from_Editor', eventData );

	<portlet:namespace/>fireTextChangedEvent( seldata );
}
		
function <portlet:namespace/>fireTextChangedEvent( data ){
	console.log('[s3deditor]test change event fire1 ', data );
	console.log('[s3deditor]test change event fire2 ', <portlet:namespace/>initData );
	console.log('[s3deditor]test change event fire3 ', <portlet:namespace/>connector );
	var inputData = new OSP.InputData();
	inputData.type( OSP.Enumeration.PathType.FILE_CONTENT );
	if( $.isEmptyObject(<portlet:namespace/>initData) ){
		inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
	}else if( <portlet:namespace/>initData.repositoryType_ ){
		console.log("[s3deditor] test re[psotpry Type]]", <portlet:namespace/>initData);	
		inputData.repositoryType(<portlet:namespace/>initData.repositoryType_);
	}else{
		inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
	}
	inputData.context( data );
	var eventData = {
		portletId: '<%=portletDisplay.getId()%>',
		targetPortlet: <portlet:namespace/>connector,
		data: OSP.Util.toJSON(inputData)
	};

	console.log('[s3deditorR]test change event fire4 ', data); 
	Liferay.fire( OSP.Event.OSP_DATA_CHANGED, eventData );

}

function <portlet:namespace/>openFileExplorer() {
	AUI().use('liferay-portlet-url', function(A) {
		if ($("#<portlet:namespace/>file-explorer-content").children().length > 0) {
			$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}else {
			var inputData;
			if ( !$.isEmptyObject(<portlet:namespace/>initData) && (
				<portlet:namespace/>initData.type_ === 'file' ||
				<portlet:namespace/>initData.type_ === 'folder' ||
				<portlet:namespace/>initData.type_ === 'ext' )) {
				inputData = JSON.parse( JSON.stringify(<portlet:namespace/>initData) );
			}
			else {
				inputData = {};
				inputData.repositoryType_ = '<%= OSPRepositoryTypes.USER_HOME.toString() %>';
				inputData.type_ ='folder';
				inputData.parent = '';
				inputData.name_ = '';
			}

			var dialogURL = Liferay.PortletURL.createRenderURL();
			dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
			dialogURL.setParameter('inputData', JSON.stringify(inputData));
			dialogURL.setParameter('mode', 'VIEW');
			dialogURL.setParameter('eventEnable', false);
			dialogURL.setParameter('connector', '<%= portletDisplay.getId() %>');
			dialogURL.setWindowState('<%= LiferayWindowState.EXCLUSIVE %>');

			$("#<portlet:namespace/>file-explorer-content").load( dialogURL.toString() );
			$<portlet:namespace/>fileExplorerDialogSection.dialog("open");
		}
	});
}

function <portlet:namespace/>closeFileExplorer() {
	$<portlet:namespace/>fileExplorerDialogSection.dialog("close");
}

function <portlet:namespace/>downloadCurrentFile() {
	if ( $.isEmptyObject(<portlet:namespace/>currentData) ||
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

	var base = '<%= serveResourceURL.toString() %>';
	var sep = (base.indexOf('?') > -1) ? '&' : '?';
	var url = base + sep + $.param(data);
	location.href = url;
}

function <portlet:namespace/>load_FDF_P( data ) {
	setTimeout(
		function() {
			var iframe = document.getElementById('<portlet:namespace/>TBox');
			var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
			if ( iframeDoc.readyState == 'complete' && iframe.contentWindow.load_FDF_S ) {
				iframe.contentWindow.load_FDF_S( data );
			}
			else {
				<portlet:namespace/>load_FDF_P( data );
			}
		},
		10
	);
}

function <portlet:namespace/>Send_Struc_to_S( data )
{
	setTimeout(
			function() {
				var iframe = document.getElementById('<portlet:namespace/>TBox');
				var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
				if ( iframeDoc.readyState == 'complete' && iframe.contentWindow.load_struc_from_P ) {
					iframe.contentWindow.load_struc_from_P( data );
				}
				else {
					<portlet:namespace/>Send_Struc_to_S( data );
				}
			},
			10
		);

}
function <portlet:namespace/>checkbox()
{
	setTimeout(
			function() {
				var iframe = document.getElementById('<portlet:namespace/>TBox');
				var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
				if ( iframeDoc.readyState == 'complete' && iframe.contentWindow.load_struc_from_P ) {
					iframe.contentWindow.load_struc_from_P( data );
				}
				else {
					<portlet:namespace/>Send_Struc_to_S( data );
				}
			},
			10
		);

}

</script>