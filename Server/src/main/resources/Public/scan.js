/**
* Parses get params out of an url
* @param string url - an url . if empty - parses current page's url
* @return array GET - an array of params passed
*/
function parseGET(url)
{  
  if(!url || url == '') url = document.location.search;
  if(url.indexOf('?') < 0) return Array();

  url = url.split('?');
  url = url[1];
  
  var GET = [];
  var params = [];
  var keyval = [];

  if(url.indexOf('#')!=-1)    
  {    
    anchor = url.substr(url.indexOf('#')+1); 
    url = url.substr(0,url.indexOf('#'));
  }

  if(url.indexOf('&') > -1) params = url.split('&');
  else params[0] = url;

  for (i=0; i<params.length; i++)
  {
    if(params[i].indexOf('=') > -1) keyval = params[i].split('=');
    else { keyval[0] = params[i]; keyval[1] = true; }
    GET[keyval[0]]=keyval[1];
  }
     
  return (GET); 
};

/**
* the andular module
*/
angular.module('scanApp', [])
  .config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.timeout = 210000000;
}]);
angular.module('scanApp', [])
	.controller('scanControler', function($http) {
		var scan = this;
		var $_GET = parseGET()
		scan.url = $_GET['url'];
		scan.classification = 'בבדיקה'
		$http({
		  method: 'POST',
		  url: 'http://192.168.99.203:4567/sampleWeb',
		  data: scan.url,
		  timeout: 210000000
		}).then(function successCallback(response) {
			scan.classification = response.data;
		  }, function errorCallback(response) {
			scan.classification = 'בדיקה נכשלה'
		  });
	}
	);