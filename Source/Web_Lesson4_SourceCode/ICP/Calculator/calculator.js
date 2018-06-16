angular.module('CalculatorApp', [])
    .controller('CalculatorController', function ($scope) {

        $scope.equation = "";
        $scope.number = 0;
        $scope.num1 = 0;
        $scope.num2 =0;
        $scope.oper = null;
        $scope.add = "+";
        $scope.substract = "-";
        $scope.multiply = "*";
        $scope.divide = "/";
        $scope.equals = "=";

        $scope.buttonClicked = function(btn){

            $scope.equation += btn;

        };
        $scope.clear = function(number)
        {
            $scope.equation="";
            $scope.number=0;
        }
        $scope.calculate = function (equation) {
            for (var i = 0; i<equation.length; i++)
            {
                if(equation[i]=="+" || equation[i]=="-" || equation[i]=="*" || equation[i]=="/")
                {
                    $scope.oper = equation[i];
                    var input = i;
                }
            }
            $scope.num1 = parseInt(equation.substring(0,input ));
            $scope.num2 = parseInt(equation.substring(input +1,equation.length));
            if($scope.oper=="+"){
                $scope.number = $scope.num1+$scope.num2;
            }
            else if($scope.oper=="-"){
                $scope.number = $scope.num1-$scope.num2;
            }
            else if($scope.oper=="*"){
                $scope.number = $scope.num1*$scope.num2;
            }
            else if($scope.oper=="/"){
                $scope.number = $scope.num1/$scope.num2;
            }
        } ;

    });