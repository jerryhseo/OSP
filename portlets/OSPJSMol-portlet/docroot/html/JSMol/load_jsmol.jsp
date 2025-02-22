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
	if(myJmol){
		//$('#canvas').empty();
		//console.log("[JSMol] resize Applet : "+ $('body').width() +' : '+$('body').height());
		//parent.jsMolresize();
		Jmol.resizeApplet(myJmol, [$('body').width(), $('body').height()]);
		//console.log("[JSMol] resize Applet end.");
	}
	
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
        
      
        if(myJmol && myJmol._applet){
        	console.log("[JSMOL] test update 1 ", myJmol);
        	  console.log("[JSMOL] test if : "+ myJmol._applet);
        	//Jmol.setInfo(myJmol, Info, true);
        	//Jmol.loadFile(myJmol, currentUrl, Info);
        	//Jmol.script(myJmol,Info);
        	console.log("[JSMOL] update file 1");
        	Jmol.loadFile(myJmol, currentUrl);
        	console.log("[JSMOL] update file 2");
		}
		else{
			console.log("[JSMOL] test update 2 create ");
			Jmol.setDocument(0);
			myJmol = Jmol.getApplet('myJmol', Info);
			console.log("[JSMOL] create jmol object", myJmol);
			$('#canvas').html( Jmol.getAppletHtml(myJmol) );
		}
        
        //Jmol.setDocument(0);
		//myJmol = Jmol.getApplet('myJmol', Info);
		//$('#canvas').html( Jmol.getAppletHtml(myJmol) );

}  
</script>

</body>
</html>

 
