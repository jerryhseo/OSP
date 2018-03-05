angular.module('app').chartCtrl = function($scope, $http, Upload, $mdDialog, $mdMedia, $rootScope, $sce, $location, $anchorScroll, $timeout, $stateParams) {
	$scope.chartHeight = 600;
	$scope.chartTitleFontSize = 20;
	$scope.chartXYFontSize = 12;
	$scope.chartLegendFontSize = 12;
	$scope.chartType = 'spline';

	$scope.xAxisVisible = true;
	$scope.yAxisVisible = true;
	$scope.fastGridWidth = 7;
	$scope.prettyGridWidth = 21;
	$scope.colors = ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9', '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1'];
	$scope.lineWidth = [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2];

	$scope.graphData = $scope.data;

	var queryString = document.location.search.split('=');

	$scope.rebuild = function(data, type, index) {
		if(type == 'data') {
			$scope.graphData = data;
		}
		else if(type == 'title') {
			$scope.title = data;
		}
		else if(type == 'xvisible') {
			$scope.xAxisVisible = data;
		}
		else if(type == 'yvisible') {
			$scope.yAxisVisible = data;
		}
		else if(type == 'color') {
			$scope.graphColors[index] = data;
		}
		else if(type == 'width') {
			$scope.graphWidth[index] = data;
		}
		else if(type == 'height') {
			$scope.chartHeight = data;
		}
		else if(type == 'optionchartTitleFontSize') {
			$scope.chartTitleFontSize = data;
		}
		else if(type == 'optionchartXYFontSize') {
			$scope.chartXYFontSize = data;
		}
		else if(type == 'optionchartLegendFontSize') {
			$scope.chartLegendFontSize = data;
		}
		else if(type == 'optionchartType') {
			$scope.chartType = data;
		}

		if(queryString[1] == 'chart1' || queryString[1] == 'chart2') {
            if(queryString[1] == 'chart1'){
                $scope.chart1 = true;
            }else if(queryString[1] == 'chart2'){
                $scope.chart2 = true;
            }
            $scope.graphDataParsed = [];
            if(!$scope.graphColors) {
                $scope.graphColors = [];
            }
            if(!$scope.graphWidth) {
                $scope.graphWidth = [];
            }
            for(var i = 0; i < $scope.fieldNum.length; i++) {
                if(!$scope.graphColors[i]) {
                    $scope.graphColors.push($scope.colors[i]);
                }
                if(!$scope.graphWidth[i]) {
                    $scope.graphWidth.push($scope.lineWidth[i]);
                }
                $scope.graphDataParsed[i] = {
                    name: $scope.fieldNum[i],
                    marker: { enabled: false },
                    data: [],
                    lineWidth: $scope.graphWidth[i],
                    color: $scope.graphColors[i],
                    states: {
                        hover: {
                            enabled: false
                        }
                    }
                };
                for(var j = 0; j < $scope.graphData.length; j++) {
                    if(i == 0) {
                        $scope.graphDataParsed[i].data.push([parseFloat($scope.graphData[j][0]), parseFloat($scope.graphData[j][1])]);
                    }
                    else if(i == 1) {
                        $scope.graphDataParsed[i].data.push([parseFloat($scope.graphData[j][2]), parseFloat($scope.graphData[j][3])]);
                    }
                }
            }

            Highcharts.chart('container', {
                chart: {
                    type: 'spline',
                    height: $scope.chartHeight,
                    zoomType: 'xy'
                },

                colors: $scope.colors,

                title: {
                    text: $scope.title.chartTitle,
                    style: {
                        fontSize: $scope.chartTitleFontSize + 'px'
                    }
                },

                subtitle: {
                    text: ''
                },

                yAxis: {
                    title: {
                        text: $scope.title.yAxisTitle,
                        style: {
                            fontSize: $scope.chartXYFontSize + 'px'
                        }
                    },
                    labels: {
                        formatter: function () {
                            return this.value;
                        },
                        style: {
                            fontSize: $scope.chartXYFontSize + 'px'
                        }
                    },
                    visible: $scope.yAxisVisible,
                    max: parseFloat($scope.chartInterfaceY.maxValue),
                    min: parseFloat($scope.chartInterfaceY.minValue)
                },

                xAxis: {
                    title: {
                        text: $scope.title.xAxisTitle,
                        style: {
                            fontSize: $scope.chartXYFontSize + 'px'
                        }
                    },
                    labels: {
                        formatter: function () {
                            return this.value;
                        },
                        style: {
                            fontSize: $scope.chartXYFontSize + 'px'
                        }
                    },
                    visible: $scope.xAxisVisible,
                    max: parseFloat($scope.chartInterfaceX.maxValue),
                    min: parseFloat($scope.chartInterfaceX.minValue)
                },

                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    itemStyle: {
                        fontSize: $scope.chartLegendFontSize + 'px'
                    }
                },

                plotOptions: {
                    series: {
                        pointStart: 0
                    }
                },

                series: $scope.graphDataParsed,

                responsive: {
                    rules: [{
                        condition: {
                            maxWidth: 500
                        },
                        chartOptions: {
                            legend: {
                                layout: 'horizontal',
                                align: 'center',
                                verticalAlign: 'bottom'
                            }
                        }
                    }]
                }
            });
        }
	}

	$timeout(function() {
	    if(parent[parentNamespace + 'xyChart'].isReadyToLoad()){
	        if(queryString[1] == 'chart1' && parent[parentNamespace + 'xyChart'].getCamberFile()) {
	            $http({
	                url: parent[parentNamespace + 'xyChart'].getLoadFileUrl(),
	                method: "POST",
	                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	                data: jQuery.param(parent[parentNamespace + 'xyChart'].getCamberFile()) 
	            }).then(function successCallback(response){
	                if(response.data){
	                    loadGridData(response.data);
	                    $scope.title = {
	                        graphTitle : ['', '', '', '', ''],
	                        chartTitle : 'Camber Chart',
	                        xAxisTitle : $scope.axisLabel[0],
	                        yAxisTitle : $scope.axisLabel[1]
	                    };
	                    $scope.chart1 = true;
	                    $scope.rebuild(0);
	                }
	            }, function errorCallback(response){
	                   if(parent[parentNamespace + 'xyChart'].isReadyToDisplay()){
	                        alert("Could not read Camber File");
	                    }
	            });
	        }else if(queryString[1] == 'chart2' && parent[parentNamespace + 'xyChart'].getSurProFile()) {
	            $http({
	                url: parent[parentNamespace + 'xyChart'].getLoadFileUrl(),
	                method: "POST",
	                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	                data: jQuery.param(parent[parentNamespace + 'xyChart'].getSurProFile()) 
	            }).then(function successCallback(response){
	                if(response.data){
	                    loadGridData(response.data);
	                    $scope.title = {
	                        graphTitle : ['', '', '', '', ''],
	                        chartTitle : 'SurPro Chart',
	                        xAxisTitle : $scope.axisLabel[0],
	                        yAxisTitle : $scope.axisLabel[1]
	                    };
	                    $scope.chart2 = true;
	                    $scope.rebuild(0);
	                }
	            }, function errorCallback(response){
	                if(parent[parentNamespace + 'xyChart'].isReadyToDisplay()){
	                    alert("Could not read SurPro File");
	                }
	            });
	        }
	    }
	}, 500);
	
	function loadGridData(fileData){
	    var obj = $scope.gridOptions;
	    obj.colModel = [{title:''}];
	    var inputData = [];
        $scope.data = [];
        $scope.rangeValue = {xmin:null, xmax:null, ymin:null, ymax:null};
        $scope.chartInterfaceX = {minValue:0, maxValue:1, options: {floor:0, ceil:1, step:0.001, precision:3}}
        $scope.chartInterfaceY = {minValue:0, maxValue:1, options: {floor:0, ceil:1, step:0.001, precision:3}}
        readFile(fileData, obj);
	}
	
	function readFile(fileData, obj){
	    var inputData = [];
	    var lines = fileData.split('\n');
        $scope.axisLabel = [];
        $scope.fieldNum = [];
        var fieldNum = '';
        var count = null;
        var count2 = null;
        for(var line = 0; line < lines.length; line++) {
            if(lines[line].indexOf('#NumField') !== -1) {
            }
            else if(lines[line].indexOf('#Label') !== -1) {
                var axisString = lines[line].split(', ');
                $scope.axisLabel[0] = axisString[0].split(': ')[1];
                $scope.axisLabel[1] = axisString[1].split(': ')[1];
            }
            else if(lines[line].indexOf('#Field') !== -1) {
                count = 0;
                var fieldString = lines[line].split(' , ')[0];
                var fieldLabel = fieldString.split(': ');
                $scope.fieldNum.push(fieldLabel[1]);
            }
            else if(lines[line] != '') {
                var pattern = /[-.0-9]+/g;

                if(!inputData[count]) {
                    inputData[count] = [];
                }
                var lineData1 = pattern.exec(lines[line]);
                var lineData2 = pattern.exec(lines[line]);
                // if(count == 0){
                //  console.log(lineData1)
                // }
                if(!$scope.rangeValue.xmin || lineData1 * 1 < $scope.rangeValue.xmin){
                    $scope.rangeValue.xmin = lineData1 * 1;
                }
                if(!$scope.rangeValue.xmax || lineData1 * 1 > $scope.rangeValue.xmax){
                    $scope.rangeValue.xmax = lineData1 * 1;
                }
                if(!$scope.rangeValue.ymin || lineData2 * 1 < $scope.rangeValue.ymin){
                    $scope.rangeValue.ymin = lineData2 * 1;
                }
                if(!$scope.rangeValue.ymax || lineData2 * 1 > $scope.rangeValue.ymax){
                    $scope.rangeValue.ymax = lineData2 * 1;
                }
                inputData[count].push(lineData1 + '');
                inputData[count].push(lineData2 + '');
                count = count + 1;
            }
        }
        var modelKey = new Array();
        for(var key in $scope.fieldNum){
            modelKey[key] = { title: $scope.fieldNum[key], align: "center", collapsible: {}, colModel: new Array() };
            for (var i = 0;i < $scope.axisLabel.length;i++) {
                modelKey[key].colModel.push({title: $scope.axisLabel[i], dataType: "string", align: "center"});
            }
        }
        obj.colModel = modelKey;

        $scope.data = inputData;
        $scope.graphData = $scope.data
        obj.dataModel = { data: $scope.data };
        $scope.gridOptions = obj;
        jQuery("#grid").pqGrid(obj);
        $timeout(function(){
            jQuery("#grid").pqGrid("refreshView");
            $scope.chartInterfaceX = {minValue:$scope.rangeValue.xmin - 0.001, maxValue:$scope.rangeValue.xmax + 0.001, options: {floor:$scope.rangeValue.xmin - 0.001, ceil:$scope.rangeValue.xmax + 0.001, step:0.00001, precision:5}};
            $scope.chartInterfaceY = {minValue:$scope.rangeValue.ymin - 0.001, maxValue:$scope.rangeValue.ymax + 0.001, options: {floor:$scope.rangeValue.ymin - 0.001, ceil:$scope.rangeValue.ymax + 0.001, step:0.00001, precision:5}};
            $scope.rebuild($scope.data, 'data', null);
        }, 100);
	}

	function isNumber(s) {
		s += '';
		s = s.replace(/^\s*|\s*$/g, '');
		if (s == '' || isNaN(s)) return false;
		return true;
	}
}
