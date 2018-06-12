function getGithubInfo(user) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it. The function should finally return the object(it now contains the response!)
    var xhttp = new XMLHttpRequest();
    var url = "https://api.github.com/users/"+user;
    xhttp.open('GET', url, false);
    xhttp.send();
    return xhttp;
}

function showUser(user) {

    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    $("#header2").text(user.login);
    $(".avatar").html("<img src='"+ user.avatar_url+"'/>");
    var link = "<a target='_blank' href='"+user.html_url+"'> URL </a>";
    $(".information").html("User Information" +
        "<br/><br/>Login Name :"+ user.login
        +"<br/>Login ID : "+ user.id
        +"<br/> GitHub URL : "+ user.url
        +"<br/> GitHub Public Repos Count : "+ user.public_repos);
    $("#profile").show();
}

function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed
    $("#header2").text("User does not exist");
    $(".avatar").text("");
    $(".information").text("");
    $("#profile").show();
}


$(document).ready(function(){
    $(document).on('keypress', '#username', function(e){
        //check if the enter(i.e return) key is pressed

        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse
            response = getGithubInfo(username);
            //if the response is successful show the user's details
            if (response.status == 200) {
                showUser(JSON.parse(response.responseText));
                //else display suitable message
            } else {
                noSuchUser(username);
            }
        }
    })
});
