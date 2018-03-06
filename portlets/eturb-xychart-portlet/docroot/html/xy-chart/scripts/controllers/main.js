'use strict';
// console.log(submittedData);
//코스웨어 전체 데이터 저장 및 변경 함수
var appCtrl = function($scope, $http, Upload, $mdDialog, $mdMedia, $rootScope, $sce, $location, $anchorScroll, $timeout, $stateParams, $window, dataService){
	angular.module('app').gridCtrl($scope, $http, Upload, $mdDialog, $mdMedia, $rootScope, $sce, $location, $anchorScroll, $timeout, $stateParams, dataService);
	angular.module('app').chartCtrl($scope, $http, Upload, $mdDialog, $mdMedia, $rootScope, $sce, $location, $anchorScroll, $timeout, $stateParams, dataService);

	var search = location.search;
	if(search.indexOf('type=') > -1){
		var getSearch = search.split('type=')[1];
		if(getSearch.indexOf('&id=') > -1){
			$scope.tabIdx = 7;
		}else{
			switch(getSearch){
				case 'chart1':
					$scope.tabIdx = 1;
					break;
				case 'chart2':
					$scope.tabIdx = 2;
					break;
				default:
					location.search = '?type=chart1';
					break;
			}
		}
	}else{
		location.search = '?type=chart1';
	}
	
	$scope.changeTab = function(idx){
		switch(idx){
			case 1:
				location.search = '?type=chart1';
				break;
			case 2:
				location.search = '?type=chart2';
				break;
		}
	}

	$scope.colorOption = {
	    // html attributes
	    required: false,
	    disabled: false,
	    placeholder: '',
	    inputClass: '',
	    id: undefined,
	    name: undefined,
	    // validation
	    restrictToFormat: false,
	    preserveInputFormat: false,
	    allowEmpty: false,
	    // color
	    format: 'rgb',
	    hue: false,
	    saturation: true,
	    lightness: true, // Note: In the square mode this is HSV and in round mode this is HSL
	    alpha: false,
	    case: 'upper',
	    // swatch
	    swatch: true,
	    swatchPos: 'right',
	    swatchBootstrap: true,
	    swatchOnly: false,
	    // popup
	    round: false,
	    pos: 'bottom right',
	    inline: true,
	    horizontal: true,
	    // show/hide
	    show: {
	        swatch: true,
	        focus: true,
	    },
	    hide: {
	        blur: true,
	        escape: true,
	        click: true,
	    },
	    // buttons
	    close: {
	        show: false,
	        label: 'Close',
	        class: '',
	    },
	    clear: {
	        show: false,
	        label: 'Clear',
	        class: '',
	    },
	    reset: {
	        show: false,
	        label: 'Reset',
	        class: '',
	    },
	};

}

angular.module('app').controller('appCtrl', appCtrl);
