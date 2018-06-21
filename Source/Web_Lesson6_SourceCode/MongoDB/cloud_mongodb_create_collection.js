/**
 * Created by Vijaya Yeruva on 5/27/2017.
 */
var MongoClient = require('mongodb').MongoClient;
var url = 'mongodb://root:1Admin@ds143778.mlab.com:43778/classdatabase';

MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbase = db.db("classdatabase");
    dbase.createCollection("newCollection", function(err, res) {
        if (err) throw err;
        console.log("Collection created!");
        db.close();
    });
});
