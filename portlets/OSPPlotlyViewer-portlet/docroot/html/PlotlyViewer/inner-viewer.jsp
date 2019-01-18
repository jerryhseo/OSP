<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OneD Plot Viewer</title>
<!-- Plotly libs -->
<script src="<%=request.getContextPath()%>/js/plotly/plotly.min.js"></script>
</head>

<body style="margin:0px;">
  <div id="plotDiv"></div>
<script>

	var namespace;
	var currentData;
	var currentTitle;
	var currentSubtitle;
	
	/***********************************************************************
	 * Golbal functions
	 ***********************************************************************/
	function setNamespace( ns ){
		namespace = ns;
	}
	
	function cleanCanvas(){
//		$('#canvas').empty();
		currentData = undefined;
		currentTitle = '';
		currentSubtitle = '';
	}

  var d3 = Plotly.d3;
  var gd;


  function loadPly(data){
    var ply = JSON.parse(data);
    var gd3 = d3.select('#plotDiv');

    ply.layout.height = window.innerHeight - 45;
    ply.layout.width = window.innerWidth;
    ply.layout.margin = {t:45};

    gd = gd3.node();
    ply.divClass = gd;

    if (ply.frames) {
 	   Plotly.newPlot(gd, ply).then(function() {
 	     Plotly.animate(gd, ply.frame, 
          ply.layout.updatemenus[0].buttons[0].args[1]
		 )
 	   })
     } else {
       Plotly.newPlot(gd, ply);
     }
  }


  window.onresize = function() {
    var update = {
      width: window.innerWidth,
      height: window.innerHeight - 45
    };

    Plotly.relayout(gd, update);
  };
</script>

</body>
</html>
