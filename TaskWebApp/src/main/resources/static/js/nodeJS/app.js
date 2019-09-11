var express = require('express');  
var app = express();  
var server = require('http').createServer(app);  
var io = require('socket.io')(server);
var jsdom = require('jsdom');
const { JSDOM } = jsdom;
const { window } = new JSDOM();
const { document } = (new JSDOM('')).window;
global.document = document;

var $ = jQuery = require('jquery')(window);

console.log("app js running");

app.use(express.static(__dirname + '/node_modules'));  


var url;


io.on('connection', function(client) {
    client.on('join', function(data) {
       console.log(data);
	   if(data.includes("localhost")){
		   url="localhost";
	   }
	   else{
		   url="192.168.99.100"; 
	   }
	   client.emit('message', 'Hello from server');
    });
	
	client.on('success', function(data){
		console.log(data);	
		$.ajax({
			type : "GET",
			url : "http://"+ url+ ":9090/retrieveRecordsNumber",
			success : function(result) {
				console.log(result);
				io.emit('records', result);
			}
		});
	});
});




server.listen(9097);