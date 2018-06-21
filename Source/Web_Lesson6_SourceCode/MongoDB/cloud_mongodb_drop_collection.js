/**
 * Created by Vijaya Yeruva on 5/27/2017.
 */

var MongoClient = require('mongodb').MongoClient;
var url = 'mongodb://root:1admin@ds143778.mlab.com:43778/classdatabase';

MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbase = db.db("classdatabase");
    dbase.dropCollection("newCollection", function(err, delOK) {
        if (err) throw err;
        if (delOK) console.log("Collection deleted");
        db.close();
    });
});