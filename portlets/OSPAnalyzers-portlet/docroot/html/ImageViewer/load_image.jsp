<!DOCTYPE html>
<html>
<head>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery.mousewheel.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/iviewer-0.7.11/jquery.iviewer.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/iviewer-0.7.11/jquery.iviewer.css">
    
    <script>

		var currentUrl;
		    
		/***********************************************************************
		 * Golbal functions
		 ***********************************************************************/
		$(window).resize( function(e){
		    loadImage( currentUrl, $(window).width(), $(window).height(), 'fit' );
		});
		 
		function loadImage( urlToLoad, width, height, zomming ){
		        console.log( 'URL To Load: '+ urlToLoad );
		        if( !urlToLoad )    return;
		        currentUrl = urlToLoad;
		        $('#canvas').width( width );
		        $('#canvas').height( height );
		        
		        if($('#canvas').hasClass('iviewer_cursor')){
			        $('#canvas').find('img').css({width:'100%', height:'100%'});
			        $('#canvas').iviewer('loadImage', urlToLoad);
			        $('#canvas').iviewer('update');
		        }
		        else{
		        	var options = {
		        	               src : currentUrl,
		        	               zoom: zomming, 
		   		                   update_on_resize:true
		        	};
					$('#canvas').iviewer( options );
					$('#canvas').iviewer('update');
		        }
		}
	</script>
</head>
<body class="common-analyzer-portlet" style="text-align: center; height:100%;">
	<div id="canvas" style="position:relative; text-align: center; height:100%;overflow:auto !important;"></div>
</body>
</html>
