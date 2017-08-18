<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<script src="<%=request.getContextPath() %>/js/onedplot/oneDplot.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<style>
.canvas-body-wrapper{height: 83%}
</style>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = (String)renderRequest.getAttribute("inputData");
String connector = (String)renderRequest.getAttribute("connector");
String action = (String)renderRequest.getAttribute("action");
boolean eventEnable = (Boolean)renderRequest.getAttribute("eventEnable");
boolean isPopup = LiferayWindowState.isExclusive(request);
%>

<div class="row-fluid onedplot-portlet common-analyzer-portlet" id="<portlet:namespace/>canvasPanel" style="width:100%;padding:0;">
	<div class="span12">
		<div class="row-fluid canvas-wrapper" id="<portlet:namespace/>canvasPanel" style="margin:0; margin: 0 0 0 2px;">
			<div class="span12 canvas-wrapper">
                <div class="row-fluid menu-section" id="<portlet:namespace/>menuSection">
                  <div class="dropdown-wrapper" >
                    <div class="dropdown">
                      <i class="icon-reorder icon-menu"></i>
                      <!-- Link or button to toggle dropdown -->
                      <div class="dropdown-content">
                        <div class="dropdown-item" id="<portlet:namespace/>openLocal"><i class="icon-folder-open"> Open local...</i></div>
                        <div class="dropdown-item" id="<portlet:namespace/>openServer"><i class="icon-folder-open"> Open Server...</i></div>
                        <div class="dropdown-item" id="<portlet:namespace/>download"><i class="icon-download-alt"> Download</i></div>
                      </div>
                    </div>
                  </div>  
                </div>
				<div class="row-fluid" id="<portlet:namespace/>actionButtons" >
					<div class="actionBtns span8" style="width:100%;">
						<input style="margin:0px;" class="addIp button01b" type="button" value="scatter" id="<portlet:namespace/>scatter"/>
						<input style="margin:0px;" class="addIp button01b" type="button" value="line" id="<portlet:namespace/>line"/>
						<input style="margin:0px;" class="addIp button01b" type="button" value="area" id="<portlet:namespace/>area"/>
						<input style="margin:0px;" class="addIp button01b" type="button" value="spline" id="<portlet:namespace/>spline"/>
						<input style="margin:0px;" class="addIp button01b" type="button" value="areaspline" id="<portlet:namespace/>areaspline"/>
						<input style="margin:0px;" class="addIp button01b" type="button" value="logarithmic" id="<portlet:namespace/>logarithmic"/>
					</div>
				</div>
				<div class="row-fluid canvas-body-wrapper">
					<div id="<portlet:namespace/>canvas" class="span12" style="margin:0; height: 100%;"></div>
				</div>
			</div>
		</div>
		<div id="<portlet:namespace/>hiddenSection" style="display:none;">
			 <input type="file" id="<portlet:namespace/>selectFile" />
			<div id="<portlet:namespace/>fileExplorer" title="Select a file" >
                <div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
                <div>
                    <input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
                    <input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
                </div>
            </div>
			<img id="<portlet:namespace/>loadingBox" src="<%=request.getContextPath()%>/images/processing.gif" width="200" style="display: none;"/>
		</div>
	</div>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector;
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPEditorsportlet_INSTANCE_od"
        + "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>highCharts;
var <portlet:namespace/>action = '<%=action%>';

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( '<%=eventEnable%>' === 'false' ){
    var inputData = '<%=inputData%>';
    if(!inputData){
        <portlet:namespace/>initData = new OSP.InputData();
    }else{
        <portlet:namespace/>initData = new OSP.InputData(JSON.parse(inputData));
    }
	<portlet:namespace/>connector = '<%=connector%>';
	<portlet:namespace/>loadHighCharts(<portlet:namespace/>initData);
}

$<portlet:namespace/>fileExplorerDialogSection.dialog({
	autoOpen: false,
	resizable: false,
	height: 600,
	width: 450,
	modal: true
});

/***********************************************************************
 * Menu click events and binding functions 
***********************************************************************/
$('#<portlet:namespace/>openLocal').click(function(){
    $('#<portlet:namespace/>selectFile').click();
});

$('#<portlet:namespace/>openServer').click(function(){
    var inputData;
    if(<portlet:namespace/>initData && 
        <portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.URI &&
        <portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.CONTEXT ){
        inputData = <portlet:namespace/>initData;
    }else{
        inputData = new OSP.InputData();
        inputData.type( OSP.Enumeration.PathType.FOLDER );
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
                if(input.files[0]){
                  <portlet:namespace/>drawPlot(e.target.result, input.files[0].name, '');
                  $("#<portlet:namespace/>selectFile").val("");
                  <portlet:namespace/>currentData = null;
                }
            }
            reader.readAsText(input.files[0]);
        }
        
);

function <portlet:namespace/>fileExplorerDialog( mode, action, inputData ){
    AUI().use('liferay-portlet-url', function(A){
        var dialogURL = Liferay.PortletURL.createRenderURL();
        dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
        dialogURL.setParameter('inputData', JSON.stringify(inputData));
        dialogURL.setParameter('mode', mode);
        dialogURL.setParameter('action', <portlet:namespace/>action);
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

/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
	OSP.Event.OSP_HANDSHAKE,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet === myId ){
			<portlet:namespace/>connector = e.portletId;
			//<portlet:namespace/>action = e.action;
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
		  var eventData = {
	         portletId: myId,
	         targetPortlet: <portlet:namespace/>connector
	      };
	      Liferay.fire(OSP.Event.OSP_REQUEST_OUTPUT_PATH, eventData);
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
        if(!this.loadedInputData ||
            (this.loadedInputData
                && this.loadedInputData.parent() !== <portlet:namespace/>initData.parent()
                && this.loadedInputData.name() !== <portlet:namespace/>initData.name())){
            <portlet:namespace/>loadHighCharts( <portlet:namespace/>initData );
        }
  	    this.loadedInputData = <portlet:namespace/>initData.clone();
  	}
  }
);

Liferay.on(
		OSP.Event.OSP_RESPONSE_DATA,
		function( eventData ){
			var myId = '<%=portletDisplay.getId()%>';
			if( eventData.targetPortlet === myId ){
				console.log('OneDPlot OSP_RESPONSE_DATA: ['+eventData.portletId+', '+new Date()+']');
				var inputData = new OSP.InputData( eventData.data );
				
				if( inputData.type() !== OSP.Enumeration.PathType.FILE ){
                    alert( 'File should be selected!' );
                    return;
                }
                else{
                    <portlet:namespace/>loadHighCharts( inputData );
                    $<portlet:namespace/>fileExplorerDialogSection.dialog('close');
                }
			}
		}
);

Liferay.on(
		OSP.Event.OSP_REFRESH_OUTPUT_VIEW,
		function( e ){
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
function <portlet:namespace/>loadHighCharts( inputData ){
    
	switch( inputData.type() ){
	case OSP.Enumeration.PathType.FILE:
		<portlet:namespace/>loadData( inputData, 'READ_FILE' );
		break;
	case OSP.Enumeration.PathType.FOLDER:
	case OSP.Enumeration.PathType.EXT:
	    <portlet:namespace/>getFirstFileName( inputData );
		break;
	case OSP.Enumeration.PathType.FILE_CONTENT:
		<portlet:namespace/>drawPlot( inputData.context(), '', '' );
		break;
	case OSP.Enumeration.PathType.URL:
		alert( 'Un-supported yet: '+inputData.type());
		break;
	default:
		alert( 'Un-expected Path type: '+ inputData.type());
	}
}

function <portlet:namespace/>loadData( inputData, command ){
	var data = {
			<portlet:namespace/>command: command,
			<portlet:namespace/>pathType: inputData.type(),
			<portlet:namespace/>parentPath: inputData.parent(),
			<portlet:namespace/>fileName: inputData.fileName(),
			<portlet:namespace/>relative: inputData.relative()
	};
	<portlet:namespace/>currentData = inputData;
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>',
		data  : data,
		dataType : 'text',
		success: function(data) {
		    var title = OSP.Util.convertToPath(inputData.name()).parent();
			var subtitle = OSP.Util.convertToPath(inputData.name()).name();
			<portlet:namespace/>drawPlot( data, title, subtitle );
		},error:function(data,e){
			console.log('RawPlotData AJAX ERROR-->'+e);
		}
	});
}

function <portlet:namespace/>drawPlot( plotData, title, subtitle){
	var highCharts = <portlet:namespace/>highCharts;
	var loadedSeriesMap = OSPPlot.SeriesMap();
	
	var highChartsConfig = OSPPlot.onedToHighChartsConfig( plotData, title, subtitle, loadedSeriesMap );
	if( !highChartsConfig ){
		alert( 'Wrong file format: OneDPlot' );
		return;
	}

	if( highCharts ){
	    // dead block
	    var highChartsSeries = highChartsConfig.series;
		for( var i=0; i<highChartsSeries.length; i++ ){
			highCharts.addSeries( highChartsSeries[i] );
		}
		var subtitle = highCharts.options.subtitle.text + ', '+subtitle;
		highCharts.setTitle( null, { text: subtitle }, false );
	}else{
	    $('#<portlet:namespace/>canvas').highcharts( highChartsConfig );
	    <portlet:namespace/>highCharts = $('#<portlet:namespace/>canvas').highcharts();
	    highCharts = <portlet:namespace/>highCharts;
		
		$.each(['line', 'spline', 'area', 'areaspline', 'scatter', 'logarithmic'], function (i, type) {
			$('#<portlet:namespace/>' + type).on('click', function () {
				if( type != 'logarithmic' ){
				    for( var i=0; i<highCharts.series.length; i++ ){
						highCharts.yAxis[0].update({
							type: 'linear'
						}, false);
						highCharts.series[i].update({
							type: type
						});
					}
				}
				else if( type == 'logarithmic'){
					highCharts.yAxis[0].update({
						type: type
					});
				}
			});
		});
	}
}

function <portlet:namespace/>getFirstFileName( argData ){
    var inputData = argData.clone();
    var data = {
            <portlet:namespace/>command: 'GET_FIRST_FILE_NAME',
            <portlet:namespace/>pathType: inputData.type(),
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
            inputData.name( data.fileName );
            inputData.type(OSP.Enumeration.PathType.FILE);
            <portlet:namespace/>loadData( inputData, 'READ_FILE' );
        },
        error:function(data,e){
            console.log('AJAX ERROR-->'+e);
        },
        complete: function( jqXHR, textStatus ){
        }
    });
}

function <portlet:namespace/>downloadCurrentFile(){
    if(<portlet:namespace/>currentData && <portlet:namespace/>currentData.name()){
        var filePath = <portlet:namespace/>currentData;
        var data = {
            <portlet:namespace/>command: "DOWNLOAD_FILE",
            <portlet:namespace/>pathType: filePath.type(),
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
