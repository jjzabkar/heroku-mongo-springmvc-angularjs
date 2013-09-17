'use strict';

// Declare app level module which depends on filters, and services
angular.module('myApp', [ 'ngRoute', 'myApp.filters', 'myApp.services', 'myApp.directives', 'myApp.controllers' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/foo', {
				templateUrl : 'resources/partials/foo.html',
				controller : 'FooListCtrl'
			});
			$routeProvider.when('/about', {
				templateUrl : 'resources/partials/about.html'
			});
			$routeProvider.otherwise({
				redirectTo : '/foo'
			});
		} ]);
