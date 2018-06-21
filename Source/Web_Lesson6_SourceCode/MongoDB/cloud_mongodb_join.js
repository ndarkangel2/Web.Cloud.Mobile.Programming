var MongoClient = require('mongodb').MongoClient;
var url = 'mongodb://root:1Admin@ds143778.mlab.com:43778/classdatabase';

MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbo = db.db("classdatabase");
    dbo.collection('newCollection').aggregate([
        { $lookup:
                {
                    from: 'products',
                    localField: 'product_id',
                    foreignField: '_id',
                    as: 'orderdetails'
                }
        }
    ]).toArray(function(err, res) {
        if (err) throw err;
        console.log(JSON.stringify(res));
        db.close();
    });
});