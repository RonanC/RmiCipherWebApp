// basic angular controller module

var myApp = angular.module('myApp',[]);

myApp.controller('InfoController', ['$scope', '$window', function($scope, $window) {
//	$scope.keyLen = 3;
	$scope.cipherText = "JNORDDBENCAWUINQMZWTVAIVWINV";
	$scope.plainText = "ANTIDISESTABLISHMENTARIANISM";
	$scope.keyWord = "Jav";
}]);