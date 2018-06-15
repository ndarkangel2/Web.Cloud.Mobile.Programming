angular.module('CalculatorApp', [])
    .controller('CalculatorController', function ($scope) {
        // Write code simple calculator operations
        $scope.a = 0;
        $scope.b = 0;
        $scope.fistscope = function() {
            return $scope.a - 0;
        }

        $scope.secoundscope = function() {
            return $scope.b - 0;
        }
        $scope.result= 0;



        $scope.result = function() {
            if ($scope.buttons== '*') {
                return $scope.a * $scope.b;
            }
            if ($scope.buttons == '/') {
                return $scope.a / $scope.b;
            }
            if ($scope.buttons == '+') {
                return $scope.a + $scope.b;
            }
            if ($scope.buttons == '-') {
                return $scope.a - $scope.b;
            }
        };
    });
