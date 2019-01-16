<!DOCTYPE>

<html>
<head>
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.css"/>
	
	<style>
	body {
		margin: 0;
		padding: 0;
	}
	.inner-canvas {
		margin: 0;
		padding: 0;
		width:100%;
		height:100%;
		overflow:none;
	}
	
	.inner-canvas .canvas {
		margin: 0;
		padding: 0;
		width:100%;
		height:100%;
		border:none;
		resize:none;
		overflow:none;
	}
	
	</style>
</head>
<body style="height: 100%">
	<div class="inner-canvas">
		<textarea id="canvas" class="canvas"></textarea>
	</div>
	<script>
	$(document).ready(function(){
// 		$('body').height(document.documentElement.clientHeight );
	});
	
	var namespace;
	var disabled = false;
	
	/***********************************************************************
	 * Functions called by wrapper jsp 
	 ***********************************************************************/
	function setNamespace( ns ){
		namespace = ns;
	} 
	
	function disable( flag ){
		$('#canvas').prop('readonly', flag );
	}
	
	function setContent( content ){
		//console.log( content );
		$('#canvas').val( content );
	}
	
	function getContent(){
		return 	$('#canvas').val();
	}
	
	function fireDataChangedEvent( content ){
		setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'fireDataChangedEvent']( content );
					}
					else{
						fireDataChangedEvent( content );
					}
				},
				10
			);
	}
	 
	
	$('#canvas').on('change', function(){
		fireDataChangedEvent( this.value );
	});
	</script>
</body>
</html>

