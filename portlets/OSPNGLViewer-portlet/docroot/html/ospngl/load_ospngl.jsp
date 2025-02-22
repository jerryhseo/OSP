<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NGL - webapp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<%@include file="../init.jsp"%>

<!-- JQuery -->
<script src="<%=request.getContextPath()%>/js/jquery/jquery-2.2.3.min.js" ></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery-ui.min.js" ></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery.blockUI.js" ></script>


<link type="text/css" href="<%=request.getContextPath()%>/js/jquery/jquery-ui.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link href="<%=request.getContextPath()%>/js/jquery/bootstrap-toggle.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery/bootstrap-toggle.min.js"></script>

<!-- bootstrap -->
<link href="<%=request.getContextPath()%>/js/jquery/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery/bootstrap.min.js"></script>



<!-- NGL -->
<script src="<%=request.getContextPath()%>/build/js/ngl.dev.js"></script>

<!-- UI -->
<script src="<%=request.getContextPath()%>/js/lib/signals.min.js"></script>
<script src="<%=request.getContextPath()%>/js/lib/tether.min.js"></script>
<script src="<%=request.getContextPath()%>/js/lib/colorpicker.min.js"></script>
<script src="<%=request.getContextPath()%>/js/ui/ui.js"></script>
<script src="<%=request.getContextPath()%>/js/ui/ui.extra.js"></script>
<script src="<%=request.getContextPath()%>/js/ui/ui.ngl.js"></script>
<script src="<%=request.getContextPath()%>/js/gui.js"></script>

<!-- css style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="subresource" href="<%=request.getContextPath()%>/css/light.css" />
<link rel="subresource" href="<%=request.getContextPath()%>/css/dark.css" />

<script src="<%=request.getContextPath()%>/js/plugins.js"></script>

</head>

<body style="width:100%; height:90%">




<div id="viewArea">
	<div id="nglViewer"></div>
</div>
<script>
var namespace;
var serveResourceURL;
var fullPath;

function setNamespace( ns, parentServeResourceURL ){
	namespace = ns;
	serveResourceURL = parentServeResourceURL;
	console.log('[NGLViewerLoad] Set namespace : ' + namespace);
	console.log('[NGLViewerLoad] Set serveResourceURL : ' + serveResourceURL);
}



NGL.cssDirectory="<%= request.getContextPath()%>/css/";
NGL.documentationUrl = "<%=request.getContextPath()%>/build/docs/index.html";


var mdsrv = NGL.getQuery("mdsrv")
if (mdsrv) {
	NGL.DatasourceRegistry.add("file", new NGL.MdsrvDatasource(mdsrv))
	NGL.DatasourceRegistry.listing = NGL.DatasourceRegistry.get("file")
	NGL.DatasourceRegistry.trajectory = NGL.DatasourceRegistry.get("file")
}


//Plugins
//NGL.PluginRegistry.add("apbs", "<%=request.getContextPath()%>/plugins/apbs.plugin");

var stage;
document.addEventListener("DOMContentLoaded", function(){
	
	stage = new NGL.Stage();
	console.log('[NGLViewerLoad] Set gui1 ');
	NGL.StageWidget(stage, 'nglViewer');
	console.log('[NGLViewerLoad] Set gui2 ');

	var load = NGL.getQuery("load");
	if(load)
		stage.loadFile(load, {defaultRepresentation: true});
		
	
	var script = NGL.getQuery("script");
	if (script) stage.loadFile("<%=request.getContextPath()%>/scripts/" + script + ".js");
	
	
	//var plugin = NGL.getQuery("plugin");
	//if (plugin) NGL.PluginRegistry.load(plugin, stage);
	//console.log('[NGLViewerLoad] set plugin test 1 : ', plugin);
	
	var struc = NGL.getQuery("struc");
	var traj = NGL.getQuery("traj");
		
	if(struc) {
		stage.loadFile(struc, {defaultRepresentation: true}).then(function(o) {
       		if(traj) o.addTrajectory(traj)});
	}
	
	var fileMenu = $("#menubar").children().first().find(".options");
	console.log("[NGLViewerLoad] find menu test ", fileMenu);
	fileMenu.append('<div class="option" id="openServerMenu">Open Server</div>');

	var openServerMenu = $("#openServerMenu");
	openServerMenu.click(function(){
		parent.iframeClickServerOpen();
	});

	console.log("[NGLViewerLoad] stage test1: ", stage);
	console.log("[NGLViewerLoad] stage test1: ", stage.compList);
});

function drawNglViewer(inputData, serveResourceURL){
	console.log("[NGLViewerLoad] Draw NGL Viewer URL : ", serveResourceURL);
	console.log('[NGLViewerLoad] input data : ', inputData);
	var result;
	
	var data ={};
	data[namespace+'command'] = "GET_FILE";
	data[namespace+'pathType'] = "file";
	data[namespace+'repositoryType'] = inputData.repositoryType_,
	data[namespace+'parentPath'] = inputData.parent;
	data[namespace+'fileName'] = inputData.name_;
	data[namespace+'relative'] = true;

	var currentfullPath = inputData.parent+inputData.name_;
	console.log("[NGLViewerLoad] full path test ", fullPath);
	//stage.removeAllComponents();
	console.log("[NGLViewerLoad] stage test1: ", stage.compList);
	$.each(stage.compList, function(i, element){
		if($.inArray(inputData.name_, stage.compList.name) === -1){
			return;
		}
	});
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", serveResourceURL);
	xhr.responseType = "blob";
	xhr.addEventListener('load', function(){
		console.log('[NGLViewerLoad] test xmlhttprequest ', xhr.response);
		var blob = new Blob([xhr.response]);
		var file = new File([blob], inputData.name_);
		stage.loadFile(file, {defaultRepresentation: true});
	});
	xhr.send();
}
</script>
</body>
</html>