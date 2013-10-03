var chatLocation = 10;
var socket = new WebSocket("ws://localhost:8080/bableHub");
var chatMsgs = "";

socket.onopen = function(event){  
  var node = document.getElementById('chat');
  node.innerHTML = "<p>Welcome to chat! Connection opened</p>";
};

socket.onmessage = function(event){
  var msg = JSON.parse(event.data);
  var node   = document.getElementById('chat');
  if (msg.userId) {
    chatMsgs += "<p>" + msg.userId + ": " + msg.message + "</p>";
    node.innerHTML= chatMsgs;
  }

};
function send (message) {
  if (socket.readyState == WebSocket.OPEN){
    socket.send(message);
  }
}
