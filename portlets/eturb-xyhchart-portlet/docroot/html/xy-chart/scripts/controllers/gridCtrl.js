angular.module('app').gridCtrl = function($scope, $http, Upload, $mdDialog, $mdMedia, $rootScope, $sce, $location, $anchorScroll, $timeout, $stateParams, dataService) {
	$scope.gridTitle = 'Grid';

	var search = location.search;

	function autoMerge(grid, refresh) {
		var mc = [],
			CM = grid.option("colModel"),
			i = CM.length,
			data = grid.option("dataModel.data");

		while (i--) {
			var dataIndx = CM[i].dataIndx,
				rc = 1,
				j = data.length;

			while (j--) {
				var cd = data[j][dataIndx],
					cd_prev = data[j - 1] ? data[j - 1][dataIndx] : undefined;
				if (cd_prev !== undefined && cd == cd_prev) {
					rc++;
				}
				else if (rc > 1) {
					mc.push({ r1: j, c1: i, rc: rc, cc: 1 });
					rc = 1;
				}
			}
		}
		grid.option("mergeCells", mc);
		if (refresh) {
			grid.refreshView();
		}
	};

	function mkAxesData(data) {
		var list = [];
		for(var l = 0;l < data[0].length;l++){
			list.push([data[0][l], data[1][l], data[2][l]]);
		}
		return list;
	}

	$scope.getCsvData = function(excel){
		var lines, lineNumber, data, length;
		var inviteList = [];
		lines = excel.split(";");
		lineNumber = 0;
		for (var i = lines.length - 1; i >= 0; i--) {
			l = lines[i];

		   lineNumber++;
			data = l.split(',');

		   var date = data[0];
		   var time = data[1];
		   var temperature = data[2];

		   inviteList.push([ date, time, temperature ]);
		}

		return inviteList;
	}

	var obj = {
		width: "100%",
		height: 400,
		resizable: true,
		sort: function () {
			autoMerge(this);
		},
		title: $scope.gridTitle,
		// numberCell: { show: false },
		scrollModel: { autoFit: true },
		virtualX: true,
		trackModel: { on: true },
		sortModel: { sorter: [{ dataIndx: 'rpm', dir: 'up'}], space: true },
		toolbar: {
			items: [
			{
				type: 'button',
				label: 'Export',
				icon: 'ui-icon-arrowthickstop-1-s',
				listener: function () {
					var blob = this.exportData({
						//url: "/pro/demos/exportData",
						format: 'csv',
						render: true
					});
					if(typeof blob === "string"){
						blob = new Blob([blob]);
					}
					saveAs(blob, "pqGrid.csv" );
				}
			},
			// {
			//     type: 'button',
			//     icon: 'ui-icon-plus',
			//     label: 'Add Product',
			//     listener: function () {
			//         //append empty row at the end.
			//         var grid = this
			//         var rowData = { ProductName: 'new product', UnitPrice: 0.2 }; //empty row
			//         var rowIndx = grid.addRow({ rowData: rowData });
			//         grid.goToPage({ rowIndx: rowIndx });
			//         grid.editFirstCellInRow({ rowIndx: rowIndx });
			//     }
			// },
			// { type: 'separator' },
			{
				type: 'button',
				icon: 'ui-icon-arrowreturn-1-s',
				label: 'Undo',
				options: { disabled: true },
				listener: function () {
					var grid = this
					grid.history({ method: 'undo' });
				}
			},
			{
				type: 'button',
				icon: 'ui-icon-arrowrefresh-1-s',
				label: 'Redo',
				options: { disabled: true },
				listener: function () {
					var grid = this
					grid.history({ method: 'redo' });
				}
			}
			]
		},
		historyModel: { checkEditableAdd: true },
		destroy: function () {
			//clear the interval upon destroy.
			clearInterval(interval);
		},
		history: function (evt, ui) {
			var $tb = this.toolbar(),
				$undo = $tb.find("button:contains('Undo')"),
				$redo = $tb.find("button:contains('Redo')");

			if (ui.canUndo != null) {
				$undo.button("option", { disabled: !ui.canUndo });
			}
			if (ui.canRedo != null) {
				$redo.button("option", "disabled", !ui.canRedo);
			}
			$undo.button("option", { label: 'Undo (' + ui.num_undo + ')' });
			$redo.button("option", { label: 'Redo (' + ui.num_redo + ')' });
		},
		pageModel: { type: "local", rPP: 20, strRpp: "{0}", strDisplay: "{0} to {1} of {2}" },
		change: function( event, ui ) { $scope.rebuild($scope.data, 'data', null); },
		create: function(){
            gridApi = this;
        }
	};


	$scope.gridOptions = obj;

	if(search.indexOf('type=') > -1 && (search.split('type=')[1] == 'chart1' || search.split('type=')[1] == 'chart2')){
        obj.colModel = [{title:''}];
        $scope.initFileInput = function() {
            document.getElementById('file_input').onchange = function() {
                var inputData = [];
                $scope.data = [];
                var file = this.files[0];
                $scope.rangeValue = {xmin:null, xmax:null, ymin:null, ymax:null};
                $scope.chartInterfaceX = {minValue:0, maxValue:1, options: {floor:0, ceil:1, step:0.001, precision:3}}
                $scope.chartInterfaceY = {minValue:0, maxValue:1, options: {floor:0, ceil:1, step:0.001, precision:3}}

                var reader = new FileReader();
                reader.onload = function(progressEvent) {
                    var lines = this.result.split('\n');
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
                    obj.dataModel = { data: $scope.data };
                    $scope.gridOptions = obj;
                    // gridApi.refresh();
                    // console.log(obj)
                    jQuery("#grid").pqGrid(obj);
                    $timeout(function(){
                        jQuery("#grid").pqGrid("refreshView");
                        $scope.chartInterfaceX = {minValue:$scope.rangeValue.xmin - 0.001, maxValue:$scope.rangeValue.xmax + 0.001, options: {floor:$scope.rangeValue.xmin - 0.001, ceil:$scope.rangeValue.xmax + 0.001, step:0.00001, precision:5}};
                        $scope.chartInterfaceY = {minValue:$scope.rangeValue.ymin - 0.001, maxValue:$scope.rangeValue.ymax + 0.001, options: {floor:$scope.rangeValue.ymin - 0.001, ceil:$scope.rangeValue.ymax + 0.001, step:0.00001, precision:5}};
                        $scope.rebuild($scope.data, 'data', null);
                    },100);
                };
                reader.readAsText(file);
            };
        };

	}
	obj.dataModel = { data: $scope.data };

	jQuery("#grid").pqGrid(obj);
}
