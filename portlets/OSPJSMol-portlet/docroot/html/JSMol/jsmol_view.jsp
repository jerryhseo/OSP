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
String connector = (String)renderRequest.getAttribute("connector");
boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
String mode = (String)renderRequest.getAttribute("mode");
%>
<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>

<div class="row-fluid jsmol-portlet common-analyzer-portlet">
    <div class="span12" style="height:inherit;">
        <div class="row-fluid menu-section" id="<portlet:namespace/>menuSection">
          <div class="span8 offset1" id="<portlet:namespace/>title"></div>
          <div class="dropdown-wrapper" >
            <div class="dropdown">
                      <i class="icon-reorder icon-menu"></i>
              <!-- Link or button to toggle dropdown -->
              <div class="dropdown-content">
                <div class="dropdown-item" id="<portlet:namespace/>openServer"><i class="icon-folder-open"> Open server...</i></div>
              </div>
            </div>
          </div>  
        </div>

        <div class="row-fluid canvas-wrapper" id="<portlet:namespace/>canvasPanel" >
            <iframe class="span12 canvas" id="<portlet:namespace/>canvas"  src="<%=request.getContextPath()%>/html/JSMol/load_jsmol.jsp"></iframe>
        </div>

        <div class="row-fluid" id="<portlet:namespace/>hiddenSection" style="display: none;">
            <input type="file" id="<portlet:namespace/>selectFile"/>
            <div id="<portlet:namespace/>fileExplorer" title="Select a file" >
                <div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
                <div>
                    <input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
                    <input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
                </div>
            </div>
        </div>
    </div>
</div>

<script>

/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector;
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPEditorsportlet_INSTANCE_jmol"
    + "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);

var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');

/***********************************************************************
 * Initailization section using parameters
***********************************************************************/
// for test
// <portlet:namespace/>eventEnable = false;

if( <portlet:namespace/>eventEnable === false ){
  var inputData = '<%=inputData%>';
  <portlet:namespace/>connector = '<%=connector%>';
  if(!inputData){
      <portlet:namespace/>initData = new OSP.InputData();
  }else{
      <portlet:namespace/>initData = new OSP.InputData(JSON.parse(inputData));
  }

	//for test
//	<portlet:namespace/>initData.type_ = 'file';
//	<portlet:namespace/>initData.parent_ = 'pdbs';
//	<portlet:namespace/>initData.name_ = '1nmr.pdb';

  <portlet:namespace/>loadJSMolFile( <portlet:namespace/>initData );
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
    inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
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
  			if( e.mode )
  				<portlet:namespace/>action = e.mode;
  			else
  				<portlet:namespace/>action = 'VIEW';	
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
			console.log(e.portletId+' activated. '+new Date()+']');
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
                           OSP.Util.mergePath(<portlet:namespace/>initData.parent(), <portlet:namespace/>initData.name()));
                       <portlet:namespace/>initData.name("");
                   }
               <portlet:namespace/>loadJSMolFile( new OSP.InputData( e.data ) );
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
                           <portlet:namespace/>loadJSMolFile( inputData );
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
function <portlet:namespace/>loadJSMolFile( inputData ){
	switch( inputData.type() ){
	case OSP.Enumeration.PathType.FILE:
	    <portlet:namespace/>currentData = inputData.clone();
	    <portlet:namespace/>drawJSMol( inputData );
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

function <portlet:namespace/>drawJSMol( inputData ){
    setTimeout(
	    function(){
	    	var iframe = document.getElementById('<portlet:namespace/>canvas');
	    	var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

	    	console.log( 'iframeDoc.readyState', iframeDoc.readyState);
	    	if (  iframeDoc.readyState  == 'complete' && iframe.contentWindow.loadJSMolFile ) {
	    	    AUI().use('liferay-portlet-url', function(a) {
	                <portlet:namespace/>currentData = inputData.clone();

	    	        var serveResourceUrl = Liferay.PortletURL.createResourceURL();
	    	        
	    	        serveResourceUrl.setPortletId('<%=portletDisplay.getId()%>');
	    	        serveResourceUrl.setParameter('command', 'READ_FILE');
	    	        serveResourceUrl.setParameter('repositoryType', inputData.repositoryType());
	    	        serveResourceUrl.setParameter('pathType', inputData.type());
	    	        serveResourceUrl.setParameter('parentPath', inputData.parent());
	    	        serveResourceUrl.setParameter('fileName', inputData.name());
	    	        serveResourceUrl.setParameter('relative', inputData.relative());
	    	        
		    	    iframe.contentWindow.loadJSMolFile(serveResourceUrl.toString());
		    	    
		    	    $('#<portlet:namespace/>title').html(inputData.name());
	    	    });
	    	} 
	    	else{
	    		<portlet:namespace/>drawJSMol( inputData );
	    	}
	    }, 
	    10
	);
}

function <portlet:namespace/>getFirstFileName( argData ){
    var inputData = argData.clone();
   
    var data = {
            <portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
            <portlet:namespace/>pathType: inputData.type(),
            <portlet:namespace/>repositoryType: inputData.repositoryType(),
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
            <portlet:namespace/>drawJSMol( inputData );
        },
        error:function(data,e){
            console.log('AJAX ERROR-->'+e);
        },
        complete: function( jqXHR, textStatus ){
        }
    });
}

function <portlet:namespace/>downloadCurrentFile(){
    if(<portlet:namespace/>currentData &&
        <portlet:namespace/>currentData.type() === OSP.Enumeration.PathType.FILE ){
        var filePath = <portlet:namespace/>currentData;
        var data = {
            <portlet:namespace/>command: "DOWNLOAD_FILE",
            <portlet:namespace/>pathType: filePath.type(),
            <portlet:namespace/>repositoryType: inputData.repositoryType(),
            <portlet:namespace/>parentPath: filePath.parent(),
            <portlet:namespace/>fileName: filePath.name(),
            <portlet:namespace/>relative: filePath.relative()
        };
        
        var base = '<%=serveResourceURL.toString()%>';
        var sep = (base.indexOf('?') > -1) ? '&' : '?';
        var url = base + sep + $.param(data);
        ($('#<portlet:namespace/>downloadAnchor').attr('href', url))[0].click();
    }
}

</script>
 
