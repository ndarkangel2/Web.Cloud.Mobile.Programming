var express = require("express");
var cors = require("cors");
var parser = require("body-parser");
var Twitter = require("twitter");

var app = express();
app.use(cors());
app.use(parser.json());
app.use(parser.urlencoded({
    extended: true
}));
app.set("port", process.env.PORT || 8000);
app.set("host", process.env.HOST || "0.0.0.0");

var client = new Twitter({
    consumer_key: "wZ3Vkwm9ivDYHwLkAw9rdrCip",
    consumer_secret: "zT9w2tK24MiQgfTewqBHxJD0LYmhl3Alqj92SWbPJ593AJzaSI",
    access_token_key: "2668439552-rCx2foxPoromZhTdrAdz8hSQDHTXGeXixNgIeBb",
    access_token_secret: "ja14HWEoRFiHBykpdNQCcxojMiHfQncUfgCURLG6mewf2"
});

app.get("/", function(req, res) {
    var name = req.query.screen_name;
    console.log(name)

    client.get("friends", {
        screen_name: name
    }, function(error, tweets, response) {
        if (!error) {
            res.json(tweets);
        } else {
            res.status(500).json({
                error: error
            });
        }
    });
});

app.listen(app.get("port"), function() {
    console.log("app running");
});