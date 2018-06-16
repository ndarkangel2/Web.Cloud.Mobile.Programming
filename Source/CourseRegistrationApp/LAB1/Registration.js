var app = angular.module('courseRegApp', []);

app.controller('courseRegCtrl', function($scope) {

    $scope.availableSeats = [{text:'Total Seats', seats:40},{text:'Available Seats', seats: 14}];


    // Function to Enroll/Drop for a Course
    $scope.enrollOrDrop = function (event) {
        $scope.modifiedSeats = [];

        // If Event is Enroll, then Modifying the Available Seats
        if(event.target.innerHTML == 'Enroll'){
            $scope.availableSeats.forEach(function (value) {
                if(value.text == 'Available Seats'){
                    // Decreasing the Available Seats
                    value.seats = value.seats-1;
                }
                $scope.modifiedSeats.push(value);
            });
            $scope.availableSeats = $scope.modifiedSeats;
            // Showing a Success Message
            $("#successEnrolling").text("Congrats, You have Successfully Enrolled into the Course");
            // Changing the Button text to Drop
            $("#course1Enroll").text('Drop');
        }else{
            // Else, if Button Event is Drop
            $scope.availableSeats.forEach(function (value) {
                if(value.text == 'Available Seats'){
                    // Increasing the Available Seats on Click of Drop
                    value.seats = value.seats+1;
                }
                $scope.modifiedSeats.push(value);
            });
            $scope.availableSeats = $scope.modifiedSeats;
            // Showing Success message of Dropping the class
            $("#successEnrolling").text("Congrats, You have Successfully Dropped from the Course");
            // Changing the button text to Enroll
            $("#course1Enroll").text('Enroll');
        }
    };

});
