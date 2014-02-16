<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WebSocket Chat</title>
</head>
<body>
<h1>WebSocket Chat</h1>
<section id="content"></section>
<input id="message" type="text"/>
<script src="http://www.google.com/jsapi"></script>
<script>google.load("jquery", "1.3")</script>
<script src="http://jquery-json.googlecode.com/files/jquery.json-2.2.min.js"></script>
<script src="http://jquery-websocket.googlecode.com/files/jquery.websocket-0.0.1.js"></script>
<script>
    var ws = $.websocket("ws://192.168.2.10:8080/websocket/echoProgrammatic", {
        events: {
            message: function(e) { $('#content').append(e.data + '<br>') }
        }
    });

    $('#message').change(function(){
        ws.send('message', this.value);
        this.value = '';
    });

    ws.onopen = function(event) {
        var username = prompt("Enter name: ");
        if(username != null) {
            ws.send('user', username);
        }
    }
</script>
</body>
</html>