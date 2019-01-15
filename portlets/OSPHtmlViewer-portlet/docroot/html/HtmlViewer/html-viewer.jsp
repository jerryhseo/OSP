<html  style="height:100%;border:none; overflow:hidden;">
<head>

<style>
.canvas {
	width:100%;
	height:100%;
	overflow:hidden;
	border: none;
}
</style>

</head>
<body>
	<iframe id="canvas" class="canvas"></iframe>
	<script>
	var namespace;
	
	/***********************************************************************
	 * Functions called by wrapper jsp 
	 ***********************************************************************/
	function setNamespace( ns ){
		namespace = ns;
	} 
	
	function setContent( url ){
		//console.log( content );
		document.getElementById('canvas').setAttribute( 'src', url );
	}
	</script>
</body>
</html>