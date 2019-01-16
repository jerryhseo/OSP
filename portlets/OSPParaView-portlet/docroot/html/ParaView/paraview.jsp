<html style="height:100%; margin:0px;">
<head>
<!-- 
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>ParaViewWeb Visualizer</title>
 -->
</head>
<body style="margin:0px; height:100%; overflow:hidden;">
<iframe id="canvas" style="width:100%;height:100%;border:none;overflow:hidden;">
</iframe>
<!-- 
<div class='content'></div>
<script src="../pv5/Visualizer.js"></script>
 -->

<script type="text/javascript">
var namespace;

function setNamespace( ns ){
	namespace = ns;
} 

function cleanParaView(){
	document.getElementById('canvas').removeAtribute('src');
}

function connectParaview( launcherURL, dataDir, fileName ){
	var fileToLoad;
	 if( fileName ){
			fileToLoad = dataDirectory+ '/' + fileName ;
	}
	
	var paraViewSrc = '<%=request.getContextPath()%>/html/pv5/start.jsp?';
	paraViewSrc += 'launcherURL='+launcherURL+
							 '&dataDirectory='+dataDir;
	if( fileToLoad ){
		paraViewSrc += '&fileToLoad='+fileToLoad;
	}
	
	console.log( 'paraViewSrc: '+paraViewSrc);
	
	document.getElementById('canvas').setAttribute('src', paraViewSrc);
}

/*
function connectParaview( launcherURL, dataDir, fileName ){
	var config = {
		application: 'visualizer-dir',
		dataDir: dataDir
	};
	if( fileName ){
			config.fileToLoad = dataDirectory+ '/' + fileName ;
	}
	
	Visualizer.connect(config);
  	Visualizer.autoStopServer(5);
}
*/
</script>
</body>
</html><!-- 
 -->