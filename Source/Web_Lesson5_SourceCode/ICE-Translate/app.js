// 'use strict';

// Declare app level module which depends on views, and components
angular.module('myTranslate', [])

    .controller('View2Ctrl', function ($scope, $http) {

        var handler;
        $scope.getTranslate = function () {
            var inputEntered = document.getElementById("txt_input").value;
            var translateQuery = document.getElementById("drop_downmenu").value; console.log(inputEntered, translateQuery)
            if (inputEntered != null && inputEntered != "" && translateQuery != null && translateQuery != "") {

                //This is the API  that Translate based on the input and dropdown menu.
                handler = $http.get("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20180619T205152Z.4177241efff41882.4d4af6d6887749e8fabf94e004b683394c334418" +
                "&text="+inputEntered +
                "&lang="+"en-"+translateQuery)

                console.log(handler)
                handler.success(function (data) {
                    if (data != null) {
                        // Tie an array named "venueList" to the scope which is an array of objects.
                        // Each object should have key value pairs where the keys are "name", "id" , "location" and values are their corresponding values from the response
                        // Marks will be distributed between logic, implementation and UI
                     // data.response.text[0];
                      console.log(data)
                      $scope.translatetext = data.text;

                    }

                })
                    .error(function (data) {
                    alert("There was some error processing your request. Please try after some time.");
                });
            }
        }
    });
