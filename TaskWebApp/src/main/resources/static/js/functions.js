$(function() {
	
})

//var myUrl = $(".myUrl").text();
//console.log(myUrl);

var socket = io.connect('http://' + urlSocket + ':9097');
socket
		.on(
				'connect',
				function() {
					console.log("connected");
					socket.emit('join', 'Web App Client '+ urlWeb+ ':9090 Connected');					
				});

socket.on('message', function(data) {
	console.log(data);
});

socket.on('records', function(data){
	console.log('records ' + data);
	$(".period").val(data);
});


var timeout;

function clearPeriod() {
	clearTimeout(timeout);
}

function stopProcess() {
	$.ajax({
		type : "GET",
		url : "http://"+ urlWeb + ":9091/pro/stop",
		success : function(result) {
			console.log(result);
			clearPeriod();
		}
	});
}

function startProcess() {
	// alert(param);
	// $.post("http://127.0.0.1:9090/register", {"cliendID":param},
	// function(result){
	console.log(urlWeb);
	// });
	$.ajax({
		type : "GET",
		url : "http://" + urlWeb + ":9091/pro/start",
		success : function(result) {
			console.log(result);

		}
		
	})
	timeout = setTimeout(function() {
		startProcess()
	}, 2 * 1000);

}