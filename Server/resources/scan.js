angular.module('scanApp', [])
	.controller('scanControler', function($http) {
		var db = this;
		db.site = {url:'url',classifictclassification:'in progress'};
		$http({
		  method: 'GET',
		  url: 'http://192.168.99.203:4567/getWebsites'
		}).then(function successCallback(response) {
			db.site = response.data[0];
		  }, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		  });
	}
	);