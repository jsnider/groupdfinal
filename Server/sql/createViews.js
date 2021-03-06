var mysql = require('mysql');
var db = require('../db');


var stream = "create view stream as select images.id as image_id ,link,users.name as user_name,"+
"beers.name as beer_name,beerTypes.name as beer_type, breweries.name as brewery_name,"+
"countries.name as country, description from images "+
"Inner join users on userID=users.ID "+
"inner join beers on beerID=beers.id "+
"inner join beerTypes on beerTypeID=beerTypes.ID "+
"inner join breweries on breweryID=breweries.id "+
"inner join countries on countryID=countries.id";

var comment = "create view commentView as select comments.id as commentID, imageID, users.name as userName,comment from comments "+
"Inner join users on userID=users.ID";


console.log("Connected!");
db.query(stream, function (err, result) {
  if (err) throw err;
  console.log("View stream created");
});
db.query(comment, function (err, result) {
  if (err) throw err;
  console.log("View commentView created");
  process.exit();
});