/**
 * For versions up to Angular v.1.6, you have to use then() method. 
 * The then() method takes two arguments: a success and an 
 * error callback which will be called with a response object.
 */

var cartApp = angular.module('cartApp', []);

cartApp.controller('cartCtrl', function($scope, $http) {
	$scope.refreshCart = function(cartId) {
		$http.get(base + '/rest/cart/' + $scope.cartId).success(function(data) {
			$scope.cart = data;
		});
	};

//	cartApp.controller('cartCtrl', function($scope, $http) {
//	$scope.refreshCart = function(cartId) {
//		$http({
//			method: 'get', 
//			url: base + '/rest/cart/' + $scope.cartId
//		}).then(function(data) {
//			$scope.cart = data;
//		}, function(error) {
//			alert('Can\'t get data');			
//		});
//	};
  
	$scope.clearCart = function() {
		$http.delete(base + '/rest/cart/' + $scope.cartId).success(function(data) {
	    	  $scope.refreshCart($scope.cartId);
		});
	};
	
//	$scope.clearCart = function() {
//		$http({
//			method: 'delete', 
//			url: base + '/rest/cart/' + $scope.cartId
//		}).then(function (data) {
//			$scope.refreshCart($scope.cartId);
//		}, function (error) {
//			alert('Can\'t get data');
//    	});
//	};
  
	$scope.initCartId = function(cartId) {
		$scope.cartId = cartId;
		$scope.refreshCart($scope.cartId);
	};
  
	$scope.addToCart = function(productId) {
		$http.put(base + '/rest/cart/add/' + productId).success(function(data) {
			alert("Product Successfully added to the Cart!");
		});
	};
	
//	$scope.addToCart = function(productId) {
//		$http({
//			method: 'put', 
//			url: base + '/rest/cart/add/' + productId
//		}).then(function (data) {
//			alert("Product Successfully added to the Cart!");
//		}, function (error) {
//			alert('Can\'t get data');
//		});
//	};
  
	$scope.removeFromCart = function(productId) {
		$http.put(base + '/rest/cart/remove/' + productId).success(function(data) {
			$scope.refreshCart($scope.cartId);
		});
	};
	
//	$scope.removeFromCart = function(productId) {
//		$http({
//			method: 'put', 
//			url: base + '/rest/cart/remove/' + productId
//		}).then(function(data) {
//			$scope.refreshCart($scope.cartId)
//		}, function(error) {
//			alert('Can\'t get data');
//		});
//	};
      
});