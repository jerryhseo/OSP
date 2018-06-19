<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NGL - webapp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-2.2.3.min.js" ></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" ></script>
<link type="text/css" href="https://code.jquery.com/ui/1.11.4/themes/south-street/jquery-ui.css" rel="stylesheet" />


<link inline rel="icon" type="<%=request.getContextPath()%>/image/png" href="ParaView.png">

<!-- Paraview Glance -->
<script inline src="<%=request.getContextPath()%>/js/glance.js"></script>
<script src="<%=request.getContextPath()%>/js/glance-external-ITKReader.js"></script>
<script src="<%=request.getContextPath()%>/js/glance-external-Workbox.js"></script>



<!-- css style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />



</head>

<body style="width:100%; height:100%">
<%@include file="/html/init.jsp"%>



<div id="viewArea">
	<div id="paraviewer"></div>
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

const container = document.querySelector('#paraviewer');
console.log("[GlanceViewer] querySelector test 1 ");
const viewer = Glance.createViewer(container);

console.log("[GlanceViewer] querySelector test 2 ", Glance);
viewer.processURLArgs();
console.log("[GlanceViewer] querySelector test 3 ", $('.FileLoader-content-3r3j7').first());
$('.FileLoader-content-3r3j7').children().first().after('<button id="loadFromFileServer" class="Button-button-h9783"><i class="fa fa-upload" type="upload" style="padding-right: 10px;"></i>Load from Server</button>');
console.log("[GlanceViewer] querySelector test 4 namespace ", namespace);

$('#loadFromFileServer').click(function(){
			parent.openFileExplorerfromParaviewGlanceOSP();
});

function loadFileOnParaviewGlance(inputData, serveResourceURL){
	console.log("[GlanceViewer] laod data Input Data ", inputData);
	console.log("[GlanceViewer] laod data from URL ", url);
	viewer.loadURL(inputData.inputData.name_, serveResourceURL);
}
</script>
</body>
</html>