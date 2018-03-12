<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
</head>
<!-- ADD THE CLASS sidebar-collapse TO HIDE THE SIDEBAR PRIOR TO LOADING THE SITE -->
<body>
    <!-- Main content -->
    <section class="plotcontent">
      <div id="myDiv"></div>
    </section>

<!-- jQuery 3 -->
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<!-- Plotly libs -->
<script src="<%=request.getContextPath()%>/js/plotly-latest.min.js"></script>


<script>
  var d3 = Plotly.d3;
  var gd;


  window.onresize = function() {
    var h = $( window ).height();
    var w = $( window ).width();

    var update = {
      width: w,
      height: h
    };

    Plotly.relayout(gd, update);
  };

  readJson = function (inData){
	  console.log("[PlotyViewer] load data1 ");
	  var data = JSON.parse(inData);
	 console.log("[PlotyViewer] load data2 ", data);
    var h = $( window ).height();
    var w = $( window ).width();

    //      console.log(h);
    //      console.log(w);
    data.layout.width = w;
    data.layout.height = h;
    data.layout.margin = {t:20};

    var gd3 = d3.select('#myDiv');

    gd = gd3.node();
    data.divClass = gd;
    if (data.frames) {
 	   Plotly.newPlot(gd, data).then(function() {
 	     Plotly.animate(gd, data.frames, 
				 {
    				"mode": "immediate",
    				"fromcurrent": true,
    				"transition": {
    					"duration": 5
    				},
    				"frame": {
    					"duration": 50,
    					"redraw": false
    				}

				}
 	     )
 	   })
     } else {
       Plotly.newPlot(gd, data);
     }
  };

  loadImagePlotly = function (url_path){
	  console.log("[PlotyViewer] load data ");
    $.ajax({
      url: url_path,
      success: function (data){
        readJson(data);
     }
   })
   
	  //readJson(url_path);
  };



</script>
</body>
</html>
