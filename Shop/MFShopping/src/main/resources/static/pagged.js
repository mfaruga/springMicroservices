var app = angular.module('app', ['ui.grid','ui.grid.pagination']);

app.controller('StudentCtrl', ['$scope','StudentService', 
    function ($scope, StudentService) {
        var paginationOptions = {
            pageNumber: 1,
            pageSize: 5,
        sort: null
        };
 
    StudentService.getStudents(
      paginationOptions.pageNumber,
      paginationOptions.pageSize).then(function(response){
        $scope.gridOptions.data = response.data.content;
        $scope.gridOptions.totalItems = response.data.totalElements;
      },function(error){
    	  // TODO add error handler
      });
    
    $scope.gridOptions = {
            paginationPageSizes: [5, 10, 20],
            paginationPageSize: paginationOptions.pageSize,
            enableColumnMenus:true,
        useExternalPagination: true,
            columnDefs: [
               { name: 'id' },
               { name: 'name' },
               { name: 'description' }
            ],
            onRegisterApi: function(gridApi) {
               $scope.gridApi = gridApi;
               gridApi.pagination.on.paginationChanged(
                 $scope, 
                 function (newPage, pageSize) {
                   paginationOptions.pageNumber = newPage;
                   paginationOptions.pageSize = pageSize;
                   
                   StudentService.getStudents(newPage,pageSize)
                     .then(function(response){
                       $scope.gridOptions.data = response.data.content;
                       $scope.gridOptions.totalItems = response.data.totalElements;
                     });
                });
            }
        };
    }]);

app.service('StudentService',['$http', function ($http) {
	 
    function getStudents(pageNumber,size) {
        pageNumber = pageNumber > 0?pageNumber - 1:0;
        return $http({
          method: 'GET',
            url: 'custom/getpaged?page='+pageNumber+'&size='+size
        });
    }
    return {
        getStudents: getStudents
    };
}]);