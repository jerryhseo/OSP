<!DOCTYPE html>
<html style="height:85%;">
<head>
    <script src="<%=request.getContextPath()%>/js/jsmol/JSmol.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body style="height:100%;">

    <div id="canvas"></div>

    <script>

    var currentUrl;
    var myJmol;
    
/***********************************************************************
 * Golbal functions
 ***********************************************************************/
$(window).resize( function(e){
	//$('#canvas').empty();
	console.log("[JSMol] resize Applet : "+ $('body').width() +' : '+$('body').height());
	//parent.jsMolresize();
	Jmol.resizeApplet(myJmol, [$('body').width(), $('body').height()]);
	
	console.log("[JSMol] resize Applet end.");
});
 
function loadJSMolFile( urlToLoad ){
        console.log( '[JSMOL]URL To Load: '+ urlToLoad );
        if( !urlToLoad )    return;
        currentUrl = urlToLoad;
        
        var Info = {
                  color: '#000000',
                  height: $('body').height(),
                  width: $('body').width(),
                  //script: "load "+"/jsmol-portlet/temp/ospjm2548440710626419920.tmp",
                  //script: "load "+ fileInfos.tempFilePath.replace(/\\/g, '/'),
                  script: 'load '+ currentUrl,
                  //script: "load " + '/jsmol-portlet/js/jsmol/data/1crn.pdb',
                  use: 'HTML5',
                  j2sPath: '<%=request.getContextPath()%>/js/jsmol/j2s',
                  jarPath: '<%=request.getContextPath()%>/js/jsmol/java',
                  jarFile: 'JmolAppletSigned0.jar',
                  isSigned: true,
                  serverURL: '<%=request.getContextPath()%>/js/jsmol/php/jsmol.php',
                  disableInitialConsole: true
        };
        
        /**
        if(myJmol){
		$('#canvas').html( Jmol.getAppletHtml(myJmol, Info) );
			}
			else{
				Jmol.setDocument(0);
				myJmol = Jmol.getApplet('myJmol', Info);
				$('#canvas').html( Jmol.getAppletHtml(myJmol) );
			}
        
        */
        Jmol.setDocument(0);
		myJmol = Jmol.getApplet('myJmol', Info);
		$('#canvas').html( Jmol.getAppletHtml(myJmol) );

}
    
</script>

</body>
</html>

 
