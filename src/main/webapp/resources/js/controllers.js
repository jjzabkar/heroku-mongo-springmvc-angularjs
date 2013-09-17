'use strict';
(function() {
	/* Controllers */

	var module = angular.module('myApp.controllers', []);

	module.controller('FooListCtrl', [ '$log', '$scope', 'FooService', function FooListCtrlFn($log, $scope, FooService) {
		$scope.foos = FooService.getAll();

		$scope.createFoo = function createFooFn() {
			FooService.create(function fooCreatedFn(obj) {
				$scope.foos.push(obj);
				$log.info('created ' + obj.uuid);
			});
		};

		$scope.updateFoo = function updateFooFn(foo) {
			var uuid = foo.uuid;
			console.log('updating ' + uuid);
			FooService.update({
				id : uuid
			}, angular.toJson(foo), function fooRemovedFn(data) {
				$log.info('updated ' + uuid, foo, data);
			});
		};

		$scope.removeFoo = function deleteFooFn(foo) {
			var uuid = foo.uuid;
			console.log('removing ' + uuid);
			FooService.remove({
				id : uuid
			}, {}, function fooRemovedFn() {
				var index = $scope.foos.indexOf(foo);
				if (index != null) {
					$scope.foos.splice(index, 1);
				}
				$log.info('removed ' + uuid + ' at index ' + index);
			});
		};

	} ]);

	module.controller('FooCtrl', [ '$scope', function FooCtrlFn($scope) {
		$scope.disableSaveButton = true;
	} ]);

})();