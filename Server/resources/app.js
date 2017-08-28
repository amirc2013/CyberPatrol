angular.module('dbApp', [])
	.controller('DBControler', function() {
		var db = this;
		db.sites = [
		{name:'niv', url:'niv.com', classification:'evil'},
		{name:'guy', url:'guy.com', classification:'good'},
		{name:'amir', url:'amir.co.il', classification:'nice'}];
	}
	);