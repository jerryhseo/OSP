<!DOCTYPE html>
<html style="height:100%;">
<head>
    <script src="<%=request.getContextPath()%>/js/pv-master/js/jquery-2.0.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/bio-pv.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/js/modernizr-2.8.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/pv-master/js/foundation-5.4.7.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/pv-master/css/foundation.css"/>
</head>
<body "height:100%;">

<div id="canvas" ></div>

<script>

    var currentUrl;
    
/***********************************************************************
 * Golbal functions
 ***********************************************************************/
$(window).resize( function(e){
    loadProtein( currentUrl );
});
 
function loadProtein( urlToLoad ){
        console.log( 'URL To Load: '+ urlToLoad );
        if( !urlToLoad )    return;
        currentUrl = urlToLoad;
        
        var options = {
                       width: $(window).width(),
                       height: $(window).height(),
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