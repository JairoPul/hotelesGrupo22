/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsUri = 'ws://' + document.location.host
+ document.location.pathname.substr(0,document.location.pathname.indexOf("/faces")) + '/websocket';
console.log(wsUri);
var websocket = new WebSocket(wsUri); //Inicializa el websocket
var textField = document.getElementById("texto");
var users = document.getElementById("users");
var chatlog = document.getElementById("chatlog");
var username;
var unido = false;

function join() {
    unido = true;
    username = textField.value;
    websocket.send(username + " se unió");
    document.getElementById("unirse").style.setProperty("visibility", "hidden");
    document.getElementById("unirse").blur();
    document.getElementById("enviar").style.removeProperty("visibility");
    document.getElementById("desconectar").style.removeProperty("visibility");
}

function send_message() {
    if(unido){
        websocket.send(username + ": " + textField.value);
    }
}

function disconnect() {
    websocket.send(username + " se desconectó");
    chatlog.innerHTML += "Te has desconectado";
    websocket.close();
    document.getElementById("unirse").style.setProperty("visibility", "hidden");
    document.getElementById("enviar").style.setProperty("visibility","hidden");
    document.getElementById("desconectar").style.setProperty("visibility","hidden");
}

websocket.onopen = function (evt) {};

websocket.onclose = function (evt) {};

websocket.onmessage = function (evt) {
    if (unido) {
        var mes = evt.data.replace("&", "$amp;")
                .replace("<","&lt;")
                .replace(">", "&gt;")
                .replace("'", "&#039;");
        
        const json = JSON.parse(mes);

        if (json.server === "no") {
            chatlog.innerHTML += json.message;
        } else if (json.server === "yes"){
            if (json.isThereChatlog === "yes") {
                chatlog.innerHTML = json.chatlog;
            }
            users.innerHTML = json.users;
        }
    }
    
};

websocket.onerror = function (evt) {};

function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
}



