(function(window){
	'use strict';
	
	if(typeof(OSPPlot) === 'undefined'){
		window.OSPPlot = {};
		
		window.OSPPlot.SeriesMap = function(){
			var map = {
					pairs: [],
					push: function( key, value ){
						this.pairs.push( [key, value] );
					},
					filter: function( key ){
						var filtered = [];
						for( var i=0; i< this.pairs.length; i++ ){
							if( this.pairs[i][0] == key )
								filtered.push( this.pairs[i] );
						}
						return filtered;
					},
					size: function(){
						return pairs.length;
					}
			};
		
			return map;
		};
		
		window.OSPPlot.HighChartsSeries = function ( id ){
			var highChartsSeries = {
					id: id,
					allowPointSelect: true,
					name:'',
					data: []
			};

			return {
				getHighChartsSeries: function(){
					return highChartsSeries;
				},
				setHighChartsSeries: function( series ){
					highChartsSeries = series;
				},
				addPoint: function( x, y ){
					var point = [];
					point.push(x);
					point.push(y);
					//console.log(point);
					highChartsSeries.data.push(point);
				},
				getId: function(){
					return highChartsSeries.id;
				},
				setId: function(id){
					highChartsSeries.id = id;
				},
				getName: function(){
					return highChartsSeries.name;
				},
				setName: function(name){
					highChartsSeries.name = name;
				}
			};
		};
		
		window.OSPPlot.HighChartsConfig = function ( title, subtitle ){
			var highChartsConfig = {
					chart: {
						type: 'line',
						zoomType: 'xy',
						panning: true,
						panKey: 'shift'
			        },
					title: {
						text: title
					},
					subtitle:{
						text: subtitle
					},
					xAxis: {
						showEmpty: false,
						title:{
							text:''
						}
					},
					yAxis: {
						showEmpty: false,
						title:{
							text:''
						}
					},
//					plotOptions: {
//						series: {
//							showCheckbox: true,
//						}
//					},
					legend: {
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'top',
						x: -10,
						y: 50,
						floating: true
					},
					series: [],
					marker: {
						enabled: true
					},
					showInLegend: true,
					tooltip:{
						followTouchMove: false
					}
			};
			var error = '';
			return {
				getHighChartsConfig: function(){
					return highChartsConfig;
				},
				setHighChartConfig: function( config ){
					highChartsConfig = config;
				},
				getTitleText: function(){
					return highChartsConfig.title.text;
				},
				setTitleText: function( text ){
					highChartsConfig.title.text = text;
				},
				getSubtitleText: function(){
					return highChartsConfig.title.text;
				},
				setSubtitleText: function( text ){
					highChartsConfig.subtitle.text = text;
				},
				getXAxisText: function(){
					return highChartsConfig.xAxis.title.text;
				},
				setXAxisText: function( text ){
					highChartsConfig.xAxis.title.text = text;
				},
				getYAxisText: function(){
					return highChartsConfig.yAxis.title.text;
				},
				setYAxisText: function( text ){
					highChartsConfig.yAxis.title.text = text;
				},
				getSeries: function(){
					return highChartsConfig.series;
				},
				setSeries: function( seriesArray ){
					highChartsConfig.series = seriesArray;
				},
				addSeries: function( series ){
					highChartsConfig.series.push( series );
				},
				appendSeries: function( seriesArray ){
					highChartsConfig.series.data = highChartsConfig.series.concat(seriesArray);
				},
				getError: function(){
					return error;
				},
				setError: function( errMsg ){
					error = errMsg;
				}
			};
		};
		
		window.OSPPlot.onedToHighChartsConfig = function ( onedRawData , title, subtitle, loadedSeriesMap ){
			var highChartsConfig = OSPPlot.HighChartsConfig( title, subtitle );

			var onedFields = onedRawData.split('#');
			onedFields.shift(); // remove empty lines
			var numField = onedFields.shift(); //first line should be the NumField parameter
			var seriesCount = parseInt( numField.split(':')[1].trim() );
			if( isNaN(seriesCount) )	return false;
			
			var labelsField = onedFields.shift(); // second line should be the definitions of labels for x-axis and y-axis.
			var labels = labelsField.split(',');
			var xLabel = labels.shift().split(':')[1].trim();
			var yLabel = labels.shift().split(':')[1].trim();
			highChartsConfig.setXAxisText( xLabel );
			highChartsConfig.setYAxisText( yLabel );
			
			for( var i=0; i<seriesCount; i++){
				var highChartsSeries = OSPPlot.HighChartsSeries( subtitle+'_'+i );
				loadedSeriesMap.push( subtitle, subtitle+'_'+i );
				var seriesField = onedFields.shift();
				var points = seriesField.split('\n');
				//console.log(JSON.stringify(points,null,4));
				
				var pointFields = points.shift().split(',');
				var seriesName = pointFields.shift().split(':')[1].trim();
				highChartsSeries.setName( seriesName );
				var pointCount = parseInt( pointFields.shift().split(':')[1].trim() );
				if( isNaN(pointCount) )	return false;
				
				//Sampling
				var maxPoints = 1000;
				var samplingStep = Math.floor(pointCount / maxPoints);
				for( var j=0; j<pointCount; j += samplingStep+1 ){
					if( points[j].trim() === '' )	continue;
					var point = points[j].trim().replace(/[\s,]+/g, ' ').split(' ');
					//console.log(point);
					highChartsSeries.addPoint( parseFloat(point[0].trim()), parseFloat(point[1].trim()) );
				}
				
				highChartsConfig.addSeries( highChartsSeries.getHighChartsSeries() );
			}

			return highChartsConfig.getHighChartsConfig();
		};
	};
})(window);


