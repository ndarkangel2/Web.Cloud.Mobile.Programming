var angularTodo = angular.module('toDoApp', []);

angularTodo.controller('angularTodoC', ['$scope', function ($scope) {
    // define list of items
    $scope.items = [{todoText:'List Start Here', done:false}];

    // Write code to push new item

    $scope.submitNewItem = function () {
        $scope.items.push({todoText:$scope.input, done:false});
        $scope.input = "";
    };

    // Write code to complete item
    $scope.completeItem = function (index) {
        $scope.items[index].completed = !$scope.items[index].completed;
    };

    // Write code to delete item


    $scope.deleteItem = function (index) {
        $scope.items.splice(index,1);

    };

}]);
