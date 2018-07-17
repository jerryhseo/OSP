<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Paraview - Glance</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-2.2.3.min.js" ></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" ></script>
<link type="text/css" href="https://code.jquery.com/ui/1.11.4/themes/south-street/jquery-ui.css" rel="stylesheet" />


<link rel="icon" type="/OSPParaviewGlance-portlet/image/png" href="ParaView.png">

<!-- css style -->
<link rel="stylesheet" href="/OSPParaviewGlance-portlet/css/main.css" />

<!-- Paraview Glance -->
<script inline type="text/javascript" src="/OSPParaviewGlance-portlet/js/runtime.js"></script>
<script inline type="text/javascript" src="/OSPParaviewGlance-portlet/js/vendors.js"></script>
<script inline type="text/javascript" src="/OSPParaviewGlance-portlet/js/glance.js" ></script>
<script type="text/javascript" src="/OSPParaviewGlance-portlet/js/glance-external-ITKReader.js"></script>
<script type="text/javascript" src="/OSPParaviewGlance-portlet/js/glance-external-Workbox.js"></script>

<link inline rel="icon" type="image/png" href="/OSPParaviewGlance-portlet/js/ParaView.png">

</head>

<body style="width:100%; height:100%">








<div id="viewArea">
	<div id="root-container"></div>
</div>
<script>
var namespace;
var serveResourceURL;

function setNamespace( ns, parentServeResourceURL ){
	namespace = ns;
	serveResourceURL = parentServeResourceURL;
	console.log('[GlanceViewer] Set namespace : ' + namespace);
	console.log('[GlanceViewer] Set serveResourceURL : ' + serveResourceURL);
}

console.log("[GlanceViewer] querySelector test 6 ", Glance);

const container = document.querySelector('#root-container');
const viewer = Glance.createViewer(container);
viewer.processURLArgs();

console.log("[GlanceViewer] Initialization glance viewer ", viewer);

var serverFilebutton = '<button id="GlanceViewerserverFileOpen" class="btn btn--flat App-toolbarButton-2qjph" type="button" style="position: relative;"><div class="btn__content"><i aria-hidden="true" class="icon material-icons">folder</i> <span>Open Server</span></div></button>';

//$(".toolbar__content").append(serverFilebutton);
var test;

//console.log("[GlanceViewer] Test toolbar conetents . ", $(".toolbar__content"));


$(".toolbar__content").each(function(index){
	if(index == 1){
		$(this).append(serverFilebutton);
	}
});


$('#GlanceViewerserverFileOpen').click(function(){
	console.log("[GlanceViewer] server file open ");
	parent.openFileExplorerfromParaviewGlanceOSP();
});


function loadFileOnParaviewGlance(inputData, serveResourceURL){
	console.log("[GlanceViewer] laod data Input Data ", inputData);
	console.log("[GlanceViewer] laod data from URL ", url);
	console.log("[GlanceViewer] load view method check ", viewer);
	//viewer.loadURL(inputData.inputData.name_, serveResourceURL);
}
</script>
</body>
</html>