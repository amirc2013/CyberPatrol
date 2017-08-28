angular.module('dbApp', [])
	.controller('DBControler', function($http) {
		var db = this;
		$http({
		  method: 'GET',
		  url: 'http://192.168.99.203:4567/getWebsites'
		}).then(function successCallback(response) {
			db.sites = response.data;
		  }, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		  });
	}
	);