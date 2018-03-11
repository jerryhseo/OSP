<!DOCTYPE html>
<html style="height:95%;">
<head>
    <script src="<%=request.getContextPath()%>/js/Highcharts-4.2.7/api/js/jquery-1.11.3.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/Highcharts-4.2.7/js/highcharts.js"></script>
    <script src="<%=request.getContextPath()%>/js/Highcharts-4.2.7/js/themes/dark-blue.js"></script>
    <script src="<%=request.getContextPath()%>/js/onedplot/oneDplot.js"></script>
    
    <script>

	var currentData;
	var currentTitle;
	var currentSubtitle;
	
	/***********************************************************************
	 * Golbal functions
	 ***********************************************************************/
	function drawPlot( plotData, title, subtitle){
	    currentData = plotData;
	    currentTitle = title;
	    currentSubtitle = subtitle;
	    
		var highCharts = highCharts;
		var loadedSeriesMap = OSPPlot.SeriesMap();
		
		var highChartsConfig = OSPPlot.onedToHighChartsConfig( plotData, title, subtitle, loadedSeriesMap );
		if( !highChartsConfig ){
			alert( 'Wrong file format: OneDPlot' );
			return;
		}
	
		if( highCharts ){
		    // dead block
		    var highChartsSeries = highChartsConfig.series;
			for( var i=0; i<highChartsSeries.length; i++ ){
				highCharts.addSeries( highChartsSeries[i] );
			}
			var subtitle = highCharts.options.subtitle.text + ', '+subtitle;
			highCharts.setTitle( null, { text: subtitle }, false );
		}else{
		    $('#canvas').highcharts( highChartsConfig );
		    highCharts = $('#canvas').highcharts();
		    highCharts = highCharts;
			
			$.each(['line', 'spline', 'area', 'areaspline', 'scatter', 'logarithmic'], function (i, type) {
				$('#' + type).on('click', function () {
					if( type != 'logarithmic' ){
					    for( var i=0; i<highCharts.series.length; i++ ){
							highCharts.yAxis[0].update({
								type: 'linear'
							}, false);
							highCharts.series[i].update({
								type: type
							});
						}
					}
					else if( type == 'logarithmic'){
						highCharts.yAxis[0].update({
							type: type
						});
					}
				});
			});
		}
	}
	</script>
</head>
<body style="height:100%;">
	<div id="actionButtons" >
		<div style="height:10%;">
			<input style="margin:0px;"  type="button" value="scatter" id="scatter"/>
			<input style="margin:0px;"  type="button" value="line" id="line"/>
			<input style="margin:0px;"  type="button" value="area" id="area"/>
			<input style="margin:0px;"  type="button" value="spline" id="spline"/>
			<input style="margin:0px;"  type="button" value="areaspline" id="areaspline"/>
			<input style="margin:0px;"  type="button" value="logarithmic" id="logarithmic"/>
		</div>
	</div>
	
	<div id="canvas" style="height:85%;"></div>
</body>
</html>