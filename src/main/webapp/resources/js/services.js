'use strict';
(function() {

	/* Services */

	// Demonstrate how to register services
	// In this case it is a simple value service.
	var module = angular.module('myApp.services', [ 'ngResource' ]).value('version', '0.1');

	module.provider('serviceCatalog', function serviceCatalog() {

		var myAppUrlRoot = $.cookie('myAppUrlRoot') || '';

		var serviceCatalogConfig = { // defaults
			myAppUrlRoot : myAppUrlRoot,
			uiResourcesUrlRoot : 'resources/'
		};
		var serviceCatalog = {};
		// to expose setter/getter functions
		for ( var key in serviceCatalogConfig) {
			serviceCatalog[key] = makeSetterGetter(key);
		}
		function makeSetterGetter(configKey) {
			return function setterGetter(value) {
				if (value !== undefined) {
					serviceCatalogConfig[configKey] = value;
					return serviceCatalog;
				} else {
					return serviceCatalogConfig[configKey];
				}
			};
		}

		return angular.extend(serviceCatalog, {
			$get : function $get() {
				var serviceCatalog = angular.copy(serviceCatalogConfig);
				// create resolver functions
				for ( var configKey in serviceCatalogConfig) {
					var serviceName = configKey.replace(/UrlRoot$/, '');
					if (serviceName != configKey) {
						serviceCatalog[serviceName] = makeServiceUrlResolver(serviceName, configKey);
					}
				}
				function makeServiceUrlResolver(serviceName, configKey) {
					return function resolveServiceUrl(resourceUrl) {
						var urlRoot = serviceCatalog[configKey];
						// see workaround for
						// https://github.com/angular/angular.js/issues/1243
						urlRoot = urlRoot.replace(/(:\d+)/, '$1$1');
						return urlRoot + resourceUrl;
					};
				}
				return serviceCatalog;
			}
		});
	});

	module.factory('FooService', [ '$resource', function FooServiceFn($resource) {
		return $resource('foo/:id', {
			id : '@id'
		}, {
			create : {
				method : 'GET'
			},
			get : {
				method : 'GET',
				params : {
					id : '@_id'
				}
			},
			getAll : {
				method : 'GET',
				params : {
					id : 'all'
				},
				isArray : true
			},
			remove : {
				method : 'DELETE',
				params : {
					id : '@_id'
				}
			},
			save : {
				method : 'POST',
			},
			update : {
				method : 'PUT',
				params : {
					id : '@_id'
				}
			}

		});
	} ]);

})();