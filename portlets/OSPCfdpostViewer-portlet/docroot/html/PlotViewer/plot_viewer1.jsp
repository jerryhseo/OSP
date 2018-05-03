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
String mode = GetterUtil.getString(renderRequest.getAttribute("mode"), "VIEW");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
%>

<div class="container-fluid osp-analyzer">
	<div class="row-fluid header">
		<div class="" id="<portlet:namespace/>title"></div>
		<div class="navbar-custom-menu" >
			<div class="dropdown">
	 			<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
						Menu<span class="caret"></span>
				</button>
				<ul class="dropdown-menu dropdown-menu-right">
					<li> <a href="javascript:<portlet:namespace/>openLocalFile()"><i class="icon-folder-open"> Open local...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>openServerFile()"><i class="icon-folder-open"> Open server...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>downloadCurrentFile()"><i class="icon-download-alt"> Download</i></a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="row-fluid frame">
		<pre class="col-sm-12 canvas" id="<portlet:namespace/>canvas"></pre>
	</div>
</div>
<div id="<portlet:namespace/>hiddenSection" style="display: none;">
	<input type="file" id="<portlet:namespace/>selectFile"/>
	<div id="<portlet:namespace/>fileExplorer" title="Select a file" >
		<div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
		<div>
			<input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
			<input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
		</div>
	</div>
	<a id="<portlet:namespace/>downloadAnchor" target="_blank" style="z-index:1000;">Download</a>
	<img id="<portlet:namespace/>processingMessage" src="<%=request.getContextPath()%>/images/processing_01.gif"/>
</div>

<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>



<!-- Plotly libs -->
<script src="<%=request.getContextPath()%>/js/plotly/plotly-latest.min.js"></script>

<script>



/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector;
var $<portlet:namespace/>fileExplorerDialogSection = $('#<portlet:namespace/>fileExplorer');
var <portlet:namespace/>fileExplorerId = "FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_tv";
if( "<portlet:namespace/>".lastIndexOf("_INSTANCE_") > 0)
	<portlet:namespace/>fileExplorerId += "<portlet:namespace/>".substring("<portlet:namespace/>".lastIndexOf("_INSTANCE_")+10);
else
	<portlet:namespace/>fileExplorerId += '001';
var <portlet:namespace/>initData;
var <portlet:namespace/>currentData;
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>eventEnable = <%=eventEnable%>;

/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
if( <portlet:namespace/>eventEnable === false ){
    <portlet:namespace/>connector = '<%=connector%>';
    var inputData = '<%=inputData%>'
    if(!inputData){
        <portlet:namespace/>initData = new OSP.InputData();
    }else{
        <portlet:namespace/>initData = new OSP.InputData(JSON.parse(inputData));
    }
    <portlet:namespace/>loadCFDPostData(<portlet:namespace/>initData.clone());
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
 function <portlet:namespace/>openLocalFile(){
	$('#<portlet:namespace/>selectFile').click();
}

function <portlet:namespace/>openServerFile(){
	var inputData;
    if(<portlet:namespace/>initData &&
        <portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.URI &&
        <portlet:namespace/>initData.type() !== OSP.Enumeration.PathType.CONTEXT ){
        inputData = <portlet:namespace/>initData;
    }else{
        inputData = new OSP.InputData();
        inputData.type( OSP.Enumeration.PathType.FOLDER );
        inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
        inputData.parent('');
        inputData.name('');
    }
    <portlet:namespace/>fileExplorerDialog('VIEW', inputData);
}

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
			var reader = new FileReader();
		    reader.onload = function (evt) {
		        var textContents = evt.target.result;
		        $('#<portlet:namespace/>canvas').text( textContents );
		        $("#<portlet:namespace/>selectFile").val("");
                delete <portlet:namespace/>currentData;
		    };
		    var inputFile = document.getElementById('<portlet:namespace/>selectFile');
		    reader.readAsText(inputFile.files[0]);
		}
);

function <portlet:namespace/>fileExplorerDialog( mode, inputData ){
    AUI().use('liferay-portlet-url', function(A){
        var dialogURL = Liferay.PortletURL.createRenderURL();
        dialogURL.setPortletId(<portlet:namespace/>fileExplorerId);
        var initData = <portlet:namespace/>initData;
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

/***********************************************************************
 * Handling OSP Events
***********************************************************************/
Liferay.on( OSP.Event.OSP_HANDSHAKE,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if( e.targetPortlet === myId ){
			<portlet:namespace/>connector = e.portletId;
			if( e.action )
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
	}
);

Liferay.on(	OSP.Event.OSP_EVENTS_REGISTERED,
	function(e){
		var myId = '<%=portletDisplay.getId()%>';
		if(e.targetPortlet === myId){
			console.log(e.portletId+' activated at '+new Date()+']');
		}
	}
);

Liferay.on(	OSP.Event.OSP_LOAD_DATA,
		function(e){
			var myId = '<%=portletDisplay.getId()%>';
			if( e.targetPortlet === myId ){
				console.log('TextViewer OSP_LOAD_DATA: ['+e.portletId+', '+new Date()+']', e.data);

				<portlet:namespace/>initData = new OSP.InputData( e.data );
				if( <portlet:namespace/>initData.type() === OSP.Enumeration.PathType.FOLDER ){
					<portlet:namespace/>initData.parent(
								OSP.Util.mergePath(<portlet:namespace/>initData.parent(), <portlet:namespace/>initData.name()));
					<portlet:namespace/>initData.name("");
				}

				if( !<portlet:namespace/>initData.repositoryType() )
					  <portlet:namespace/>initData.repositoryType('<%=OSPRepositoryTypes.USER_JOBS.toString()%>');

				var eventData = {
							portletId: myId,
							targetPortlet: <portlet:namespace/>fileExplorerId,
							data: OSP.Util.toJSON( <portlet:namespace/>initData )
				};
				Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );

				<portlet:namespace/>loadCFDPostData( <portlet:namespace/>initData.clone() );
			}
		}
);

Liferay.on( OSP.Event.OSP_RESPONSE_DATA,
        function( e ){
            var myId = '<%=portletDisplay.getId()%>';
            if( e.targetPortlet === myId ){
               console.log('TextViewer OSP_RESPONSE_DATA: ['+e.portletId+', '+new Date()+']');
               //alert( eventData.sourceData.action );
                if( e.portletId === <portlet:namespace/>fileExplorerId ){
                    var inputData = new OSP.InputData( e.data );

                    if( inputData.type() !== OSP.Enumeration.PathType.FILE ){
                        alert( 'File should be selected!' );
                        return;
                    }
                    else{
                        <portlet:namespace/>loadCFDPostData( inputData );
                        $<portlet:namespace/>fileExplorerDialogSection.dialog('close');
                    }
                }
            }
        }
);

Liferay.on( OSP.Event.OSP_REFRESH_OUTPUT_VIEW,
           function( e ){
               var eventData = {
                       portletId: '<%=portletDisplay.getId()%>',
                       targetPortlet: <portlet:namespace/>connector
               };

               Liferay.fire(OSP.Event.OSP_REQUEST_OUTPUT_PATH, eventData);
           }
);

Liferay.on(	OSP.Event.OSP_INITIALIZE,
		function(e){
			if( e.targetPortlet === '<%=portletDisplay.getId()%>'){
				$("#<portlet:namespace/>canvas").empty();
				<portlet:namespace/>setTitle( '' );
			}
		}
);

/***********************************************************************
 * Golbal functions
***********************************************************************/
function <portlet:namespace/>loadCFDPostData( inputData ){
	if( ! inputData.repositoryType() )
		inputData.repositoryType('<%=OSPRepositoryTypes.USER_JOBS.toString()%>');

    switch( inputData.type() ){
        case OSP.Enumeration.PathType.FILE:
            <portlet:namespace/>loadData( inputData, 'READ_FILE' );
            break;
        case OSP.Enumeration.PathType.FOLDER:
        case OSP.Enumeration.PathType.EXT:
            <portlet:namespace/>getFirstFileName( inputData );
            break;
        case OSP.Enumeration.PathType.FILE_CONTENT:
            $('#<portlet:namespace/>canvas').text( inputData.context() );
            break;
        case OSP.Enumeration.PathType.URL:
            alert( 'Un-supported yet: '+inputData.type());
            break;
        default:
            alert( 'Un-expected Path type: '+ inputData.type());
    }
}

function <portlet:namespace/>getFirstFileName( argData ){
    var inputData = argData.clone();
    if( inputData.type() === 'folder' ){
    	inputData.parent( OSP.Util.mergePath(inputData.parent(), inputData.name()) );
    	inputData.name('');
    }
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
            inputData.name( data.fileName );
            inputData.type(OSP.Enumeration.PathType.FILE);
            <portlet:namespace/>loadData( inputData, 'READ_FILE' );
        },
        error:function(d, e){
            console.log('[CFDPOSTViewer] getFirstFileName() Ajax ERROR: '+JSON.stringify(data));
        },
        complete: function( jqXHR, textStatus ){
        }
    });
}


function <portlet:namespace/>loadData( inputData, command ){
	var data = {
			<portlet:namespace/>command: command,
			<portlet:namespace/>pathType: inputData.type(),
			<portlet:namespace/>repositoryType: inputData.repositoryType(),
			<portlet:namespace/>parentPath: inputData.parent(),
			<portlet:namespace/>fileName: inputData.name(),
			<portlet:namespace/>relative: inputData.relative()
	};
	<portlet:namespace/>currentData = inputData;
	$.ajax({
		type: 'POST',
		url: '<%= serveResourceURL.toString()%>',
		data  : data,
		dataType : 'text',
		success: function(data) {
			//$('#<portlet:namespace/>canvas').text( data );
			<portlet:namespace/>readCFDData(data);
			<portlet:namespace/>setTitle( inputData.name() );
		},
		error:function(d, e){
			console.log(' loadData() Ajax ERROR: '+JSON.stringify(data));
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

function <portlet:namespace/>setTitle( title ){
	$('#<portlet:namespace/>title').html('<h5>'+title+'</h5>');
}


function <portlet:namespace/>readCFDData(data){
  var datas = data.trim().split(/[\s,="']+/);
  var mode = 0;

  var colum_val =[];
  var zone = {};
  var zone_val = [];
  var zone_keys = [];
  var trace = {};
  var x_langth, y_langth;
  var r = 0;
  var plotType = '2D';

  if (!datas[0].toLowerCase().match(/^variables/)) {

    var lines = data.split('\n');

    for (var qq = 0; qq < lines.length; qq++) {
      $('#<portlet:namespace/>canvas').append($('<p />').html(lines[qq]));
    }
//        return 0;
  } else { // plot3D type
		/*
    for(var i = 0; i < datas.length; i++) {
      if (datas[i].toLowerCase().match(/^variables/)) {
        var j = 0;
        while(!datas[i+1].toLowerCase().match(/^zone/)){
          i = i + 1;
          //console.log(datas[i])
          var replaced = datas[i].replace(/\W*/g,'').toLowerCase();
          //console.log(replaced)
          if ( replaced ) {
            colum_val[j++] = replaced;
          }
        }
      } else if (datas[i].toLowerCase().match('zone')){
        i=i+1;
        var j = 0;
        var key = "";
        var value = "";
        var i_flag = false, j_flag = false;

        //console.log(datas[i]);
        while ( datas[i].replace(/\W+/g,'').match(/^[a-zA-Z]/) ) {
          var key = datas[i].replace(/\W+/g,'').toLowerCase();
          var value = "";
          while ( ! value ) {
            value = datas[++i].replace(/\W+/g,'').toLowerCase();
          }
          if( key.match(/[i]/)){
            zone[key] = parseInt(value);
            i_flag = true;
          } else if( key.match(/[j]/)){
            zone[key] = parseInt(value);
            j_flag = true;

          } else if ( key.match(/[tf]/)) {
            zone[key] = value;
          }

          i++;
        }
        i--;

        if (i_flag && j_flag ) {
          plotType = '3D';
        } else {
          plotType = '2D';


          for (var p = 0; p < colum_val.length; p++) {
            trace[colum_val[p]] = new Array();
          }
        }
      } else {     //read data
        if (plotType == '2D'){
          for (var p = 0; p < colum_val.length; p++) {
            trace[colum_val[p]][r] = parseFloat(datas[i++]);
          }
          i--;
          r++;
        } else if (plotType == '3D') {
          trace['a'] = new Array();
          trace['b'] = new Array();

          var rr=0;

          for (var k = 0; k < zone.j; k++) {
            //console.log(k);
            for (var q = 0; q < zone.i; q++) {
              for (var p = 0; p < colum_val.length; p++) {
                if ((k==0) && (q==0) ){            // 객체 원소 생성
                  trace[colum_val[p]] = new Array();
                }
                if(p==0){
                  trace.a[rr] = q;
                } else if(p==1) {
                  trace.b[rr] = k;
                }
                trace[colum_val[p]][rr] = parseFloat(datas[i++]);
              }
              rr++;
            }
          }
          trace.x_langth = zone.i;
          trace.y_langth = zone.j;

        } else {
          return -1;
        }
      }
    }
    trace.zone = zone;
    trace.varlist = colum_val;
    trace.plotType = plotType;

//        console.log(trace);

    if (trace.plotType.match('2D')) {
      var x_flag = true, y_flag = true;
      var tab_index = class_path.slice(-1);


      for (var kk = 0; kk < colum_val.length; kk++) {
        $('#sub_tap_'+ tab_index + ' select')
          .append($('<option />',{'value' : kk}).html(colum_val[kk]));
      };

      //init select_option x,y
      select_option(tab_index, 'x_axis', 0);
      select_option(tab_index, 'y_axis', 1);

      var trace1  = {
        type: 'scatter',
        x: trace[colum_val[0]],
        y: trace[colum_val[1]]
      };
      var data2 = [trace1];

      var h = $( window ).height();
      var w = $( window ).width();

      //      console.log(h);
      //      console.log(w);
      var layout = {
        width: w - plotMarginWidth,
        height: h - plotMarginHeight - $('.nav-tabs').height(),
        margin: {
          t: 20
        }
      };

    //  var gd3 = d3.select('#'+class_path).append('div');
			var gd3 = d3.select('#<portlet:namespace/>canvas');

      gd = gd3.node();


    } else if (trace.plotType.match('3D')) {
      var x_flag = true, y_flag = true;
      //var tab_index = class_path.slice(-1);

      // createSubTab(tab_index, trace.plotType);

      for (var kk = 0; kk < colum_val.length; kk++) {
        $('#sub_tap_'+ tab_index + ' select')
          .append($('<option />',{'value' : kk}).html(colum_val[kk]));

      };

      //init select_option x,y
      select_option(tab_index, 'x_axis', 0);
      select_option(tab_index, 'y_axis', 1);
      select_option(tab_index, 'z_axis', 4);


      var trace1  = {
        type: 'carpet',
        a: trace.a,
        b: trace.b,
        x: trace[colum_val[0]],
        y: trace[colum_val[1]],
        aaxis:{
          showticklabels: "none"
        },
        baxis:{

          showticklabels: "none"
        }
      }
      var trace2 = {

        autocolorscale: true,
        zmax: 1,
        name: "Pressure",
        colorscale: "Viridis",
        zmin: -8,
        line: {
          smoothing: 0
        },
        a: trace.a,
        b: trace.b,
        z: trace[colum_val[4]],
        type: "contourcarpet",
        autocontour: false,
        zauto: false

      }
      var data2 = [trace2, trace1];

      var h = $( window ).height();
      var w = $( window ).width();

      //      console.log(h);
      //      console.log(w);
      var layout = {
        width: w - plotMarginWidth,
        height: h - plotMarginHeight - $('.nav-tabs').height(),
        margin: {
          t: 20
        }
      };
      var gd3 = d3.select('#'+class_path).append('div');

      gd = gd3.node();

    } else {
      return -1;
    }

    trace.clickCount = plotDataCount;
    trace.divClass = gd;
    trace.tapID = class_path;
    trace.tabIDNum = class_path.slice(-1);
    trace.plotData = data2;
    trace.plotLayout = layout;

    Plotly.plot(trace.divClass, trace.plotData, trace.plotLayout);
    plotDataArray[plotDataCount++] = trace;
		*/
  }
}


</script>
