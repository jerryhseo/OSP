<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>ParaViewWeb - Visualizer</title>
    <link rel="stylesheet" type="text/css" href="apps/Visualizer/main.css">
    <link rel="icon" type="image/png" href="favicon.png">
</head>
<body onbeforeunload="stop()" onunload="stop()">
    <script src="./lib/core/vtkweb-loader.js" app-template="pv-visualizer-tpl" load="core, pv-visualizer, pv-visualizer-main-js"></script>
    <script type="text/javascript">
      var config = <%= request.getSession().getAttribute("config") %>;
      var reallyStop = true,
        stop = vtkWeb.NoOp,
        start = function(connection) {
            $('.app-wait-start-page').remove();
            $('.hide-on-start').removeClass('hide-on-start');

            pv.initializeVisualizer(
                connection.session,
                '.pv-viewport', '.pv-pipeline', '.pv-proxy-editor', '.pv-files', '.pv-source-list',
                '.pv-filter-list', '.pv-data-info', '.pv-global-settings-editor', '.pv-savedata-options');

            $('[data-toggle="tooltip"]').tooltip({container: 'body'});

            // Update stop method to use the connection
            stop = function() {
                if (reallyStop === true) {
                    connection.session.call('application.exit', []);
                }
            };
        };

        // Try to launch the Viz process
        vtkWeb.smartConnect(config, start, function(code,reason){
            console.log(reason);
        });
    </script>
</body>
</html>
