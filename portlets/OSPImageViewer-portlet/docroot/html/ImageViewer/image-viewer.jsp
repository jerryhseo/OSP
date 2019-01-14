<!DOCTYPE html>
<html style="height:100%; overflow:hidden;">
<head>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery.mousewheel.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/iviewer-0.7.11/jquery.iviewer.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/iviewer-0.7.11/jquery.iviewer.css">
    <style>
	.inner-canvas{position:relative; text-align: center; height:100%;overflow: hidden;}
    </style>
    <script>
		var namespace;
		var currentUrl;
		    
		/***********************************************************************
		 * Golbal functions
		 ***********************************************************************/
		$(window).resize( function(e){
		    loadImage( currentUrl, 'fit' );
		});
		
		function setNamespace( ns ){
			namespace = ns;
		}
		 
		function loadImage( urlToLoad ){
				if( !urlToLoad )    return;
				currentUrl = urlToLoad;

				if($('#canvas').hasClass('iviewer_cursor')){
					$('#canvas').iviewer('loadImage', urlToLoad);
				}
				else{
					var options = {
								src : currentUrl,
								update_on_resize:true
					};
					$('#canvas').iviewer( options );
					// $('#canvas').iviewer('update');
				}

				$('#canvas').iviewer('center');
				$('#canvas').iviewer( 'fit' );
				//$('#canvas').iviewer('update');
		}
	</script>
</head>
<body style="text-align: center; height:100%;">
	<div id="canvas" class="inner-canvas"></div>
</body>
</html>
