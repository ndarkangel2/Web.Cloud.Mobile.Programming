/**
 * Created by Vijaya Yeruva on 5/27/2017.
 */

var http = require('http');
var MongoClient = require('mongodb').MongoClient;
var url = 'mongodb://root:1Admin@ds143778.mlab.com:43778/classdatabase';

MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbase = db.db("classdatabase");
    var myquery = { address: 'Main Road 989' };
    dbase.collection("newCollection").deleteOne(myquery, function(err, obj) {
        if (err) throw err;
        console.log(obj.result.n + " document(s) deleted");
        db.close();
    });
});