var app = require('express')();
var chathttp = require('http').Server(app);
var lobbieshttp = require('http').Server(app);
var gamehttp = require('http').Server(app);
var chatIO = require('socket.io')(chathttp);
var lobbiesIO = require('socket.io')(lobbieshttp);
var gameIO = require('socket.io')(gamehttp);
app.use(require('express').static('./'));
app.get('/', function(req, res) {
	res.sendFile(__dirname + "/index.php");
}); 

var connectedUsers = [];

chathttp.listen(3004, function() {
	console.log('Listening on *:3004');
});
lobbieshttp.listen(3005, function() {
	console.log('Listening on *:3005');
});
gamehttp.listen(3006, function() {
	console.log('Listening on *:3006'); 
});


//Server side chat controller
chatIO.on('connection', function(socket) { 
	console.log('User as connected to CHAT');
    
    //Send the array of connected users to each user if the user entering is currently not already in the list
    socket.on('enteredChat', function(username){
        if(!socket.username && !connectedUsers.includes(username)){
            connectedUsers.push(username);
            chatIO.emit('updateUserList', connectedUsers);
            socket.username = username;
        }
    });
    
    //Remove user from connected list on disconnect
    socket.on('disconnect', function(username){
        if(socket.username){
            connectedUsers.splice(connectedUsers.indexOf(username),1);
            chatIO.emit('updateUserList',connectedUsers);
        } 
    });
    
    //Send the incoming message to all users connected to the chat
    socket.on('sentMessage', function(message){
        chatIO.emit('receiveMessage', message);
    });
	
});


var namespace = null;
var ns = lobbiesIO.of(namespace || "/");


//Server side lobby controller
var lobbyCtl = {
	lobbies: [],
	
	addLobby: function(lobby) {
		"use strict";
		if(this.exist(lobby.name)) {
			return [false, 'Lobby Already Exists'];
		}
		var newLobby = new this.Lobby(lobby);
		this.lobbies.push(newLobby);
		return [true];
		
	},
	
	rmLobby: function(lobby) {
		"use strict";
		for(var i = 0; i < this.lobbies.length; i++) {
			if(this.lobbies[i].name === lobby) {
				this.lobbies.splice(i,1);
			}
		}
 
		lobbiesIO.emit('removeLobby', lobby);
	},
	
	deleteIfEmpty: function(lobby) {
		"use strict";
		lobby = this.find(lobby);
		if(lobby.isEmpty()) {
			lobbyCtl.rmLobby(lobby.name);
			return true;
		}
		return false;
	},
	
	find: function(name) {
		"use strict";
		for(var i = 0; i < this.lobbies.length; i++) {
			if(this.lobbies[i].name === name) {
				return this.lobbies[i];
			}
		}
		return false;
	},
	exist: function(name) {
		"use strict";
		for(var i = 0; i < this.lobbies.length; i++) {
			if(this.lobbies[i].name === name) {
				return true;
			}
		}
		return false;
	},
	
	Lobby: function(lobby) {
		"use strict";
		this.name = lobby.name;
		this.gamemode = lobby.gamemode;
		this.gametype = lobby.gametype;
		this.setting = lobby.setting;
		this.lobbytype = lobby.lobbytype;
		this.lobbypass = lobby.lobbypass;
		this.players = lobby.players;
		this.maxPlayers = lobby.maxPlayers;
		this.owner = lobby.owner;
		
		this.isEmpty = function() {
			var isEmpty = true;
			for(var slot in this.players) {
				if(this.players[slot] !== null) {
					isEmpty = false;
				} 
			}
			return isEmpty;
		};
		
		this.swap = function(p1, p2) {
			var temp = this.players['p'+p1];
			this.players['p'+p1] = this.players['p'+p2];
			this.players['p'+p2] = temp;
		};
		
		this.join = function(player) {
			var playerArr = Object.values(this.players);
			var slot = playerArr.indexOf(null) + 1;
			if(slot === 0) {
				return [false, 'Lobby is full'];
				
			}
			this.players['p'+slot] = player.name;
			lobbiesIO.emit('addLobby', this);
			return [true];
			
		};
		
		this.leave = function(player) {
			for(var slot in this.players) {
				if(this.players[slot] === player) {
					this.players[slot] = null;
					if(!lobbyCtl.deleteIfEmpty(this.name)) {
						if(this.owner === player) {
							this.promoteNextPlayer();
						}
					}
					return [true];
				}
			}
			return [false, 'Player not found'];
		};
		
		this.promoteNextPlayer = function() {
			for(var slot in this.players) {
				if(this.players[slot] !== null) {
					this.owner = this.players[slot];
					var player = playerCtl.getByName(this.owner);
					player.socket.emit('alertPlayer', 'You have been promoted to leader');
					return true;
				}
			}
			return false;
		};
	}
	
	
};
 
var playerCtl = {
	
	players: [],
	
	player: function(player, session) {
		"use strict";
		this.name = player;
		this.session = session;
		this.lobby = null;
		this.socket = ns.connected[this.session];
		
		this.join = function(lobby) {
			lobby = lobbyCtl.find(lobby);
			var result = lobby.join(this);
			if(result[0]) {
				if(this.isInLobby()) {
					this.leave();
				}
				this.lobby = lobby.name;
				this.socket.join(this.lobby);
				lobbiesIO.to(this.lobby).emit('updateLobbyPage', this.lobby);
				return [true];
			} else {
				return [false, result[1]];
			}
		};
		
		this.leave = function() {
			if(!this.isInLobby()) {
				return [false, 'Not in a lobby'];
			}
			var lobby = lobbyCtl.find(this.lobby);
			var result = lobby.leave(this.name);
			if(result[0]) {
				this.socket.leave(this.lobby);
				this.lobby = null;
				lobbiesIO.to(this.session).emit('leaveLobby');
				if(lobbyCtl.exist(lobby.name)) {
					lobbiesIO.emit('addLobby', lobby);
					lobbiesIO.to(lobby.name).emit('updateLobbyPage', lobby.name);
				}
				return [true];
			} else {
				return [false, result[1]];
			}
		};
		
		this.isInLobby = function() {
			if(this.lobby === null) {
				return false;
			} else {
				return true;
			}
		};
	},
	
	add: function(player) {
		"use strict";
		if(this.exist(player)) {
			var p = this.getByName(player.name);
			clearTimeout(p.leaving);
			delete p.leaving;
			p.session = player.session;
			return false;
		}
		this.players.push(player);
	},
	
	remove: function(player) {
		"use strict";
		var index = this.players.indexOf(player);
		if(player.isInLobby()) {
			player.leave();
		}
		this.players.splice(index, 1);
	},
	
	getById: function(session) {
		"use strict";
		for(var player in this.players) {
			if(this.players[player].session === session) {
				return this.players[player];
			}
		}
		return false;  
	},
	
	getByName: function(name) {
		"use strict";
		for(var player in this.players) {
			if(this.players[player].name === name) {
				return this.players[player];
			}
		}
		return false;
	},
	
	exist: function(p) {
		"use strict";
		for(var player in this.players) {
			if(this.players[player].name === p.name) {
				return true;
			}
		}
		return false;
	},
	existById: function(session) {
		"use strict";
		for(var player in this.players) {
			if(this.players[player].session === session) {
				return true;
			}
		}
		return false;
	},
	
	isLeaving: function(session) {
		"use strict";
		var leaving = setTimeout(function(){playerCtl.remove(playerCtl.getById(session));console.log('LEFT');}, 5000);
		this.getById(session).leaving = leaving;
	}
		
};

lobbiesIO.on('connection', function(socket) {
	"use strict";
	
	socket.on('connected', function(user) {
		playerCtl.add(new playerCtl.player(user, socket.id));
		console.log(playerCtl.players);
		socket.emit('updateLobbyList', lobbyCtl.lobbies);
	});
	
	console.log('User as connected to LOBBIES'); 
	console.log(lobbyCtl.lobbies);
	
	socket.on('installLobby', function(lobby, callback) {
		var player = playerCtl.getById(socket.id);
		var result = lobbyCtl.addLobby(lobby);
		if(result[0]) {
			if(player.isInLobby()) {
				var message = 'You are already in a lobby, Creating lobby with leave your previous lobby.';
				socket.emit('askConfirmation', message, function(result) {
					if(!result) {
						lobbyCtl.rmLobby(lobby.name);
						callback(false);
					} else {
						result = player.join(lobby.name);
						if(result[0]) {
							callback(true);
						} else {
							callback(false, result[1]);
						}
					}
				});
			} else {
				result = player.join(lobby.name);
				if(result[0]) {
					callback(true);
				} else {
					callback(false, result[1]);
				}
			}
		} else {
			callback(false, result[1]);
		}
		
	}); 
	
	socket.on('getLobbyList', function(send) {
		send(lobbyCtl.lobbies);
	});
	
	//Data: name - lobby
	socket.on('joinLobby', function(lobby, callback) {
		var player = playerCtl.getById(socket.id);
		if(player.isInLobby()) {
			var message = 'You are in a lobby, you sure you want to join?';
			socket.emit('askConfirmation', message, function(result) {
				if(!result) {
					callback(false);
					return false;
				} else {
					result = player.join(lobby);
					if(result[0]) {
						callback(true);
					} else {
						callback(false, result[1]);
					}
				}
			});
		} else {
			var result = player.join(lobby);
			if(result[0]) {
				callback(true);
			} else {
				callback(false, result[1]);
			}
		}
		
	});
	
	socket.on('leaveLobby', function(lobby, callback) {
		var player = playerCtl.getById(socket.id);
		var result = player.leave(lobby);
		if(result[0]) {
			callback(true);
		} else {
			callback(false, result[1]);
		}
	});
	
	socket.on('getLobby', function(lobby, callback) {
		lobby = lobbyCtl.find(lobby);
		if(lobby) {
			callback(true, lobby);
		} else {
			callback(true, lobby, 'Lobby not found');
		}
	});
	
	socket.on('disconnect', function () {
		if(playerCtl.existById(socket.id)) {
			playerCtl.isLeaving(socket.id);
		}
	});
}); 




//Server side game controller
gameIO.on('connection', function(socket) { 
	"use strict";
	
	console.log('User as connected GAME');
	 
});



