angular.module('CalculatorApp', [])
    .controller('CalculatorController', function ($scope) {

        var elementsArray = [];

        clear();

        function clear() {
            $scope.equation = "";
            $scope.number = 0;
            elementsArray = [];
        }
        $scope.buttonClicked = function (btn){
            $scope.equation + btn;

        }


        function clearNumber() {
            $scope.number = "";
        };

        function equationAdd(a, b) {
            elementsArray.push(a);
            elementsArray.push(b);
            $scope.equation += a;
            $scope.equation += b;
        }

        function multiply(a, b) {
            var c = parseInt(elementsArray[a]) * parseInt(elementsArray[b]);
            elementsArray[a] = c.toString();
            elementsArray.splice(a + 1, 2);
        }

        function divide(a, b) {
            var c = parseInt(elementsArray[a]) / parseInt(elementsArray[b]);
            elementsArray[a] = c.toString();
            elementsArray.splice(a + 1, 2);
        }

        function add(a, b) {
            var c = parseInt(elementsArray[a]) + parseInt(elementsArray[b]);
            elementsArray[a] = c.toString();
            elementsArray.splice(a + 1, 2);
        }

        function substract(a, b) {
            var c = parseInt(elementsArray[a]) - parseInt(elementsArray[b]);
            elementsArray[a] = c.toString();
            elementsArray.splice(a + 1, 2);
        }


        function calculate() {
            for (var i = 0; i < elementsArray.length; i++) {
                if (elementsArray[i] == "*") {
                    multiply(i - 1, i + 1);
                    i = i - 2;
                } else if (elementsArray[i] == "/") {
                    divide(i - 1, i + 1);
                    i = i - 2;
                }

            };
            for (var i = 0; i < elementsArray.length; i++) {
                if (elementsArray[i] == "+") {
                    add(i - 1, i + 1);
                    i = i - 2;
                } else if (elementsArray[i] == "-") {
                    substract(i - 1, i + 1);
                    i = i - 2;
                }
            }
        };

        for (var i = 0; i < button.length; i++) {
            button[i].addEventListener('click', function() {
                switch (this.innerHTML) {
                    case "+":
                    {
                        equationAdd($scope.number, "+");
                        clearNumber();
                        break;
                    }
                    case "-":
                    {
                        equationAdd($scope.number, "-");
                        clearNumber();
                        break;
                    }

                    case "*":
                    {
                        equationAdd($scope.number, "*");
                        clearNumber();
                        break;
                    }
                    case "/":
                    {
                        equationAdd($scope.number, "/");
                        clearNumber();
                        break;
                    }

                    case "%":
                    {
                        equationAdd($scope.number, "%");
                        clearNumber();
                        break;
                    }
                    case "Clear":
                    {
                        clear();
                        break;

                    }

                    case "=":
                    {
                        equationAdd($scope.number, "=");
                        elementsArray.pop();
                        calculate();
                        $scope.number = elementsArray[0];
                        $scope.equation = "";
                        elementsArray.pop();
                        break;
                    }

                    default:
                    {
                        $scope.number += this.innerHTML;
                        break;
                    };

                };
            });
        }
    });
