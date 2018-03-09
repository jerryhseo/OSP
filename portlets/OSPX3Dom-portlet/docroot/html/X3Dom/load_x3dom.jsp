<!DOCTYPE html>
<html style="height:88%;">
<head>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-2.0.2.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/x3dom-1.7.1/x3dom.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/x3dom-1.7.1/x3dom.css"/>
</head>
<body style="height:100%;">

<x3d id="x3d" style="width:100%;height:100%;"> 
	<scene style="width:100%;height:100%;">
		<inline id="canvas" style="width:100%;height:100%;"> </inline> 
	</scene>
</x3d> 


<script>

    var currentUrl;
    
/***********************************************************************
 * Golbal functions
 ***********************************************************************/
$(window).resize( function(e){
    if( currentUrl )
    	loadX3Dom( currentUrl );
});
 
function loadX3Dom( urlToLoad ){
        console.log( 'URL To Load: '+ urlToLoad );
        if( !urlToLoad )    return;
        currentUrl = urlToLoad;
        
        $('#canvas').attr('url', currentUrl );
}
    
</script>

</body>
</html>