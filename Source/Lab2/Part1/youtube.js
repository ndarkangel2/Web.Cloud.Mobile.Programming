// Declare app level module which depends on views, and components
angular.module('myYoutubeApp', [])

    .controller('View1Ctrl', function ($scope, $http) {
        $scope.youtubeVideo = [];
        $scope.getVideo = function () {
            var searchQuery = document.getElementById("search").value;
            if (searchQuery != null && searchQuery != "") {
                //This is the API that gives the list of youtube video based search query.
                var handler = $http.get("https://www.googleapis.com/youtube/v3/search?" +
                    "key=AIzaSyB5czr9kYzroPBWtNCFnplcjTOI-S4mYbw" +
                    "&type=video" +
                    "&maxResults=5" +
                    "&part=snippet" +
                    "&q=" +searchQuery)

                handler.success(function (response) {
                    console.log(response.items)
                    $scope.youtubeVideo = response.items;


                    //failers does not at the bot
                    //var results = "www.youtube.com/embed/"+response.items[0].id.videoId;
                    //console.log(results)

                   // $scope.videoLink = "www.youtube.com/embed/"+response.items[0].id.videoId;
                    //$scope.videoLink2 = results;
                   // console.log(response.items[0].id.videoId)
                    //var id = response.items[0].id.videoId ;
                   // function getYoutubeURL (response){
                     //   console.log(response.items[0].id.videoId)
                       // return "www.youtube.com/embed/"+id;
                    //}


                });

            }
        }
        //console.log(response.items[0].id.videoId)
       // $scope.getVideoUrl =function(id){
        //    return "https://https://www.youtube.com/embed/"+id;
       // }
    });