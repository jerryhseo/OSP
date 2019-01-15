<html style="height:100%;">
<head>
</head>
<body style="margin:0px; height:100%; overflow:hidden;">
<iframe id="canvas" style="width:100%;height:100%;border:none;overflow:hidden;">
</iframe>

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
	
	document.getElementById('canvas').setAttribute('src', paraViewSrc);
}
</script>
</body>
</html><!-- 
 -->