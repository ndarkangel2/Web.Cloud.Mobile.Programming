// 'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [])

    .controller('View1Ctrl', function ($scope, $http) {
        $scope.venueList = new Array();
        $scope.mostRecentReview = new Array();

        $scope.getVenues = function () {
            var placeEntered = document.getElementById("txt_placeName").value;
            var searchQuery = document.getElementById("txt_searchFilter").value;
            if (placeEntered != null && placeEntered != "" && searchQuery != null && searchQuery != "") {

                //This is the API that gives the list of venues based on the place and search query.
                var handler = $http.get("https://api.foursquare.com/v2/venues/search" +
                    "?client_id= CALWSHXBQSXWNAAO51UIBASAZITDCJ3WYH2GC4LAP3XID2NV" +
                    "&client_secret= VVRDCJQL4VWFUEUI4QSHYSLJQZQH5MANLDHUSGCTDLLVBGYP" +
                    "&v=20160215&limit=1" +
                    "&near=" + placeEntered +
                    "&query=" + searchQuery);

                handler.success(function (data) {
                    if (data != null && data.response != null && data.response.venues != undefined && data.response.venues != null) {
                        // Tie an array named "venueList" to the scope which is an array of objects.
                        // Each object should have key value pairs where the keys are "name", "id" , "location" and values are their corresponding values from the response
                        // Marks will be distributed between logic, implementation and UI
                        data.response.venues.forEach(function (value) {
                            var location = value.location.formattedAddress;
                            if(location != null){
                                location = location.toString();
                            }

                            $scope.venueList.push({name : value.name, id : value.id, location : location})
                            console.log(value.id)
                          //  console.log(value.id.length);
                    var handler2 = $http.get("https://api.foursquare.com/v2/venues/"+value.id+"/tips" +
                                "?client_id= CALWSHXBQSXWNAAO51UIBASAZITDCJ3WYH2GC4LAP3XID2NV" +
                                "&client_secret= VVRDCJQL4VWFUEUI4QSHYSLJQZQH5MANLDHUSGCTDLLVBGYP" +
                                "&v=20160215&limit=5");



                            handler2.success(function (data) {
                                if (data != null && data.response != null) {
                                    // Tie an array named "venueList" to the scope which is an array of objects.
                                    // Each object should have key value pairs where the keys are "name", "id" , "location" and values are their corresponding values from the response
                                    // Marks will be distributed between logic, implementation and UI

                                    $scope.mostRecentReview.push({review: data.response.tips.items[0].text})

                                } else {
                                    $("#error").text("No Data");
                                    $("#error").show();
                                }
                            })
                            handler2.error(function (data) {
                                alert("There was some error processing your request. Please try after some time.");
                            });

                        });
                    }else{
                        $("#error").text("No Data");
                        $("#error").show();

                    }

                })
                handler.error(function (data) {
                    alert("There was some error processing your request. Please try after some time.");
                });

            }
        }
    });
