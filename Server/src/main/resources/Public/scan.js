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
	.controller('scanControler', function($http) {
		var db = this;
		var $_GET = parseGET()
		var scan_url = $_GET['url']
		db.site = {url:scan_url,classifictclassification:'בבדיקה'};
		$http.post('/url',scan_url,'').then(function successCallback(response) {
			db.site = response.data;
		  }, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		  });
	}
	);