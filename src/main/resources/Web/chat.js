var chatLocation = 10;
var socket = new WebSocket("ws://localhost:8080/bableHub");
var chatMsgs = "";

socket.onopen = function(event){  
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
  var form = document.getElementById("chatInput");
  form.reset();
  if (socket.readyState == WebSocket.OPEN){
    socket.send(message);
  }
};


function submitOnEnter(e, message){ 
 
 if (e.keyCode == 13) {
   send(message); 
   return false;
 }
};
