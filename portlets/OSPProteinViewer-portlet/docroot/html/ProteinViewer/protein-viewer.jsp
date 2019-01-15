<!DOCTYPE html>
<html style="height:100%;">
<head>
    <script src="<%=request.getContextPath()%>/js/pv-master/js/jquery-2.0.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/bio-pv.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/js/modernizr-2.8.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/js/foundation-5.4.7.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/pv-master/css/foundation.css"/>
</head>
<body style="height:100%;">
	<div id="canvas"  style="width:100%; height:100%; overflow:hidden;"></div>
	<script>
	var namespace;
	var currentUrl;
	    
	/***********************************************************************
	 * Golbal functions
	 ***********************************************************************/
//	$(window).resize(function(){
//		loadProtein( currentUrl, $(window).width(), $(window).height() );
//	});
	
	function setNamespace( ns ){
		namespace = ns;
	}
	
	function cleanCanvas(){
		$('#canvas').empty();
	}
	 
	function loadProtein( urlToLoad ){
			$('#canvas').empty();
	        if( !urlToLoad )    return;
	        currentUrl = urlToLoad;
	        var options = {
							width:$('body').width(),
	                       height:$('body').height(),
	                       antialias: true,
	                       quality : 'medium'
	        };
	
	        var proteinViewer = pv.Viewer(document.getElementById('canvas'), options);
	        pv.io.fetchPdb( 
	                    urlToLoad, 
	                    function(structure) {
	                        proteinViewer.renderAs('protein', structure, 'cartoon', { color : color.ssSuccession() });
	                        proteinViewer.centerOn(structure);
	                    }
	       );
	}
	</script>
</body>
</html><!-- 
 -->
