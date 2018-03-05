'use strict';

var dataService = function($q, $http){
	return {
		getCustomData : function(request){
            var deferred = $q.defer();
            $http.post('/retrieve-posted-data', request).then(function(response) {
                deferred.resolve(response);
            }).catch(function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        }
	}
}

angular.module('app').factory('dataService', dataService);