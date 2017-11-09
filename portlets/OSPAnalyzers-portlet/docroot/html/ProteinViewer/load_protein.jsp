<!DOCTYPE html>
<html>
<head>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/bio-pv.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/js/modernizr-2.8.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/js/foundation-5.4.7.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/pv-master/css/foundation.css"/>
</head>
<body>

<div id="canvas" class="common-analyzer-portlet"></div>

<script>

    var currentUrl;
    
/***********************************************************************
 * Golbal functions
 ***********************************************************************/
$(window).resize( function(e){
    loadProtein( currentUrl, $(window).width(), $(window).height() );
});
 
function loadProtein( urlToLoad, width, height ){
        console.log( 'URL To Load: '+ urlToLoad );
        if( !urlToLoad )    return;
        currentUrl = urlToLoad;
        
        var options = {
                       width: width,
                       height: height,
                       antialias: true,
                       quality : 'medium'
        };

        var proteinViewer = pv.Viewer($('#canvas')[0], options);
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
</html>