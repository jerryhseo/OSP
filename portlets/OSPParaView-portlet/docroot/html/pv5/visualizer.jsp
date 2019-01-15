<!DOCTYPE html>
<html style="height:100%; margin:0px;">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>ParaViewWeb Visualizer</title>
  </head>
  <body style="height:100%; margin:0px;">
    <div class='content'></div>
  <script src="Visualizer.js"></script>
  <script>
  	var config = <%= request.getSession().getAttribute("config") %>;
  	Visualizer.connect(config);
  	Visualizer.autoStopServer(5);
  </script>
  </body>
</html>
