<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Interactive</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="icon" href="favicon.ico">
    <!-- bower:css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/angular-material/angular-material.css" />
    
    <!-- <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css" /> -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>	<!-- Modified Link -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/paramquery-pro/pqgrid.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/paramquery-pro/pqgrid.ui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/paramquery-pro/themes/gray/pqgrid.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/angular-color-picker/angularjs-color-picker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/angular-color-picker/themes/angularjs-color-picker-bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/angularjs-slider/rzslider.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/html/xy-chart/styles/css/style.css" />
    <script>
    var parentNamespace = parent.xyChartNamespace;
    </script>
    <!-- endbower -->
  </head>
  <body ng-app="app" ng-controller="appCtrl">
    <!--[if lte IE 8]>
      <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <!-- <script src="https://code.highcharts.com/highcharts.js"></script> -->
    <!-- <script src="https://code.highcharts.com/highcharts-3d.js"></script> -->
    <!-- <script src="https://code.highcharts.com/highcharts-more.js"></script> -->
    <!-- <script src="https://code.highcharts.com/modules/exporting.js"></script> -->
    <!-- <script src="https://code.highcharts.com/maps/modules/data.js"></script> -->
    <!-- <script src="https://code.highcharts.com/maps/modules/map.js"></script> -->
    <!-- <script src="https://rawgithub.com/paulo-raca/highcharts-contour/master/highcharts-contour.js"></script> -->
    <!-- <script src="https://rawgithub.com/ironwallaby/delaunay/master/delaunay.js"></script> -->
    
    <!-- bower:js -->
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/jquery/jquery.js"></script>
    
    <script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/js/highcharts-3d.js"></script>
    <script src="${pageContext.request.contextPath}/js/highcharts-more.js"></script>
    <script src="${pageContext.request.contextPath}/js/exporting.js"></script>
    <script src="${pageContext.request.contextPath}/js/map.js"></script>
    <script src="${pageContext.request.contextPath}/js/data.js"></script>
    <script src="${pageContext.request.contextPath}/js/highcharts-contour.js"></script>
    <script src="${pageContext.request.contextPath}/js/delaunay.js"></script>

    <!-- build:js(.) scripts/vendor.js -->
    

	<!-- TODO -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script> -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>	<!-- Modified Link -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/html/xy-chart/bower_components/paramquery-pro/pqgrid.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/html/xy-chart/bower_components/paramquery-pro/touch-punch/touch-punch.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/html/xy-chart/bower_components/paramquery-pro/jsZip-2.5.0/jszip.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/html/xy-chart/node_modules/file-saver/FileSaver.min.js" ></script>

    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular/angular.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/bootstrap/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-animate/angular-animate.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-aria/angular-aria.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-cookies/angular-cookies.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-messages/angular-messages.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-resource/angular-resource.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-route/angular-route.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-sanitize/angular-sanitize.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-touch/angular-touch.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-material/angular-material.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/flow.js/flow.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/ng-flow/ng-flow.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-ui-router/angular-ui-router.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/ng-file-upload/ng-file-upload.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/tinymce-dist/tinymce.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-ui-tinymce/tinymce.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/auth0-angular/auth0-angular.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/auth0-lock/auth0-lock.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-jwt/angular-jwt.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/a0-angular-storage/angular-storage.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angularjs-slider/rzslider.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/html/xy-chart/bower_components/paramquery-pro/angular/ng.pqgrid.min.js" ></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/tinycolor/tinycolor.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/bower_components/angular-color-picker/angularjs-color-picker.js"></script>

    <script src="${pageContext.request.contextPath}/html/xy-chart/scripts/js/data_structure.js"></script>
    <!-- endbower -->
    <!-- node_modules -->
    <script src="${pageContext.request.contextPath}/html/xy-chart/node_modules/crypto-js/crypto-js.js"></script>
    <!-- node_modules -->
    <!-- endbuild -->
    <!-- build:js({.tmp,app}) scripts/scripts.js -->
    <script src="${pageContext.request.contextPath}/html/xy-chart/scripts/app.js"></script>
    <!-- Service -->
    <script src="${pageContext.request.contextPath}/html/xy-chart/scripts/services/dataService.js"></script>
    <!-- Controller -->
    <script src="${pageContext.request.contextPath}/html/xy-chart/scripts/controllers/main.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/scripts/controllers/chartCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/html/xy-chart/scripts/controllers/gridCtrl.js"></script>
    <!-- endbuild -->

    <div class="layout_wrap">
        <div class="direct_link_group">
            <button type="button" ng-click="changeTab(1)" ng-disabled="tabIdx == 1" ng-class="{active: tabIdx == 1}">Camber</button>
            <button type="button" ng-click="changeTab(2)" ng-disabled="tabIdx == 2" ng-class="{active: tabIdx == 2}">SurPro</button>
            <span class="glyphicon glyphicon-remove" style="margin-top: 5px; float: right; margin-right:20px; cursor: pointer;" onclick="parent[parentNamespace + 'xyChart'].closeXYPloter(); return false;"></span>
        </div>
        <div class="chart_area" ng-include="'${pageContext.request.contextPath}/html/xy-chart/views/chart.html'"></div>
        <div class="grid_area" ng-include="'${pageContext.request.contextPath}/html/xy-chart/views/grid.html'"></div>
    </div>
</body>
</html>
