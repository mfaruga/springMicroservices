(function() {

	var app = angular.module('mfshop', ['ui.bootstrap']);
	
	app.controller('StoreController', [ '$http', '$log', '$scope', '$filter', function($http, $log, $scope, $filter) {
		
		this.product= {"name" : "", "description" : "" };

		this.selected = undefined;
		
		this.states = ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"];;
		
		this.addProduct = function() {
			$http.post("/custom/add", this.product).
				then(function (response) {
						    var data = response.data;
						    alert("SUCCESS");// not relevant
						  }, 
				     function (error) {
						    var data = error.data;
						    $log.info("TEST");
						    $log.info("status: " + error.status);
						  });
			this.product = {};
		};
				
		this.getStates = function(productName) {
			return $http.get("/custom/names?productName=" + productName).then(
					function (response) {
						$log.info("Names retuned ok");
						return $filter('limitTo')(response.data, 4);
					},
					function (error) {
						$log.info("Names failed");
						return ["Cukier", "Sól", "Woda"];
					});
		};
	} ]);
	

	// let's use dependency injection to get some built-in services from angular
	app.controller('SomeController', [ '$http', '$log', function($http, $log) {

		var store = this;
		// initialize this to empty array to make sure that variable is defined
		// when page load - and async call is not yet completed
		store.products = []; 

		// here we're working with premise object that is asynchronous
		// retrieving data - and we're registering callback
		$http.get('/custom/all').then(function(data) {
			store.products = data.data;
		});
	} ]);

})();