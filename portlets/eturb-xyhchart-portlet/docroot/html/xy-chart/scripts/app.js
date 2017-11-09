'use strict';

angular
.module('app', [
	'ngAnimate',
	'ngAria',
	'ngCookies',
	'ngMessages',
	'ngResource',
	'ngRoute',
	'ngSanitize',
	'ngTouch',
	'ngMaterial',
	'flow',
	'ui.tinymce',
	'ui.router',
	'ngFileUpload',
	'auth0',
	'angular-jwt',
	'angular-storage',
	'pq.grid',
	'color.picker',
	'rzModule'
	])
.config(function($provide) {
        $provide.decorator('ColorPickerOptions', function($delegate) {
            var options = angular.copy($delegate);
            options.round = true;
            options.alpha = false;
            options.format = 'hex';
            return options;
        });
    });
