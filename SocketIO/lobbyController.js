// JavaScript Document

$(function() {
	"use strict";
	
	if(Tools.prefs.data.lobby !== null) {
		updateLobbyPage(Tools.prefs.data.lobby);
	}
	
	$('#createlobbyBut').click(function() {
		createLobby();
	});
	
	lobbies.on('askConfirmation', function(message, callback) {
		Tools.askConfirmation(message, function(result) {
			callback(result);
		});
	});
	lobbies.on('alertPlayer', function(message) {
		Tools.sendAlert(message);
	});
	
	lobbies.on('leaveLobby', function() {
		Tools.prefs.data.lobby = null;
		Tools.prefs.save(1);
		Tools.showPage(Tools.pages.member);
		Tools.sendAlert('Left Lobby');
	});
	
	/*
	lobbies.emit('getLobbyList', function(list) {
		displayLobbies(list);
	});
	*/
	
	lobbies.on('updateLobbyList', function(list) {
		displayLobbies(list);
	});
	
	lobbies.on('addLobby', function(lobby) {
		displayLobby(lobby);
	});
	
	lobbies.on('removeLobby', function(lobby) {
		$('#'+lobby).remove();
	});
	
	lobbies.on('updateLobbyPage', function(lobby) {
		updateLobbyPage(lobby);
	});
	
	
	$(document).click(function(e){
		if ($(e.target).is($('.changeSetting'))) {
			changeSetting($(e.target), $('input[name=gametype]:checked').val());
		}		
		if($(e.target).is($('#finish'))) {
			installLobby(); 
		}
		if($(e.target).is($('#lobbyBut'))) {
			Tools.showPage(Tools.pages.lobby); 
		}
		if($(e.target).is($('#leaveLobby'))) {
			lobbies.emit('leaveLobby', Tools.prefs.data.lobby, function(success, error) {
				if(success) {
					
				} else {
					Tools.sendAlert(error);
				}
			});
		}
		if($(e.target).is($('.lobbyDiv'))) {

			var name = $(e.target).attr('id');
			if(name === Tools.prefs.data.lobby) {
				Tools.sendAlert('Already in lobby');
				return false;
			}
			console.log(name);
			lobbies.emit('joinLobby', name, function(success, error) {
				if(success) {
					Tools.prefs.data.lobby = name;
					Tools.prefs.save(1);
					updateLobbyPage(name);
					Tools.showPage(Tools.pages.lobby);
				} else {
					Tools.sendAlert(error);
				}
			});
			
		} 
		
		if ($(e.target).is($('.selectPlayer'))) {
			var player = $(this).name;
			lobbies.emit('selectPlayer', player, function(change) {
				if(change) {
					Tools.sendAlert('change is good');
				}
			});
		}
		
		
	});
	
	
});

function createLobby() {
	"use strict";
	$('body').append(
		//Create container element (div) for signup form.
		$('<div>', {class: 'modal-fixed'}).append(
		$('<div>', {id: 'createLobby', class: 'modal'}).append(
			$('<h1>').text('Create Lobby'), 
			$('<div>', {id: 'error'}),
			

			//Create Form Container
			$('<form>', {action: '#', method: 'post'}).append(

				//Lobby name
				$('<input>', {
					type: 'text',
					id: 'lobbyname',
					name: 'lobbyname',
					placeholder: 'Lobby Name...'
				}),

				//Game mode Radio Buttons
				$('<input>', {
					type: 'radio',
					id: '1vs1',
					name: 'gamemode',
					checked: 'checked'
				}),
				$('<label>', {
					for: '1vs1'
				}).text('1 vs 1'),
				
				$('<input>', {
					type: 'radio',
					id: '2vs2',
					name: 'gamemode'
				}),
				$('<label>', {
					for: '2vs2'
				}).text('2 vs 2'),
				
				$('<input>', {
					type: 'radio',
					id: '4ffa',
					name: 'gamemode'
				}),
				$('<label>', {
					for: '4ffa'
				}).text('4 ffa'),
				//--------------------------
				
				$('<br>'),
				
				
				//Create Game Setting buttons
				$('<input>', {
					type: 'radio',
					id: 'stock',
					name: 'gametype',
					class: 'changeSetting',
					value: 'stock',
					checked: 'checked'
				}),
				$('<label>', {
					for: 'stock'
				}).text('Stock'),
				
				$('<input>', {
					type: 'radio',
					id: 'time',
					name: 'gametype',
					value: 'time'
				}),
				$('<label>', {
					for: 'time'
				}).text('Time'),
				
				$('<input>', {
					type: 'button',
					id: 'inc',
					class: 'changeSetting',
					value: '+'
				}),
				
				$('<input>', {
					type: 'text',
					id: 'setting',
					name: 'setting',
					value: '1'
				}),
				
				$('<input>', {
					type: 'button',
					id: 'dec',
					class: 'changeSetting',
					value: '-'
				}),
				//----------------------------
				
				$('<br>'),
				
				
				$('<input>', {
					type: 'radio',
					id: 'public',
					name: 'lobbytype',
					value: 'public',
					checked: 'checked'
				}),
				$('<label>', {
					for: 'public'
				}).text('Public'),
				
				$('<input>', {
					type: 'radio',
					id: 'private',
					name: 'lobbytype',
					value: 'private'
				}),
				$('<label>', {
					for: 'private'
				}).text('Private'),
				
				$('<input>', {
					type: 'text',
					id: 'lobbypass',
					name: 'lobbypass',
					style: 'width: 70%;',
					placeholder: 'Lobby Password...'
				}),
				
				$('<br>'),
				
				$('<input>', {
					type: 'button',
					id: 'finish',
					value: 'Create Lobby'
				})
				
			)	  
		)
		)
	);
}

function installLobby() {
	"use strict";
	var error = '';
	var name = $('#lobbyname').val();
	var gamemode = $('input[name=gamemode]:checked').attr('id');
	var gametype = $('input[name=gametype]:checked').val();
	var setting = parseInt($('#setting').val());
	var lobbytype = $('input[name=lobbytype]:checked').attr('id');
	var lobbypass = $('#lobbypass').val();
	var maxPlayers = 4;
	
	if(name === '') {
		error += 'Please Enter Lobby Name<br />';
	}
	
	if(lobbytype === 'private' && lobbypass === '') {
		error += 'Please Enter Lobby Password';
	}
	
	if(error !== '') {
		Tools.sendError(error);
		return false;
	}
	
	if(gamemode === '1vs1') {
		maxPlayers = 2;
	}
	
	var lobby = {name: name, gamemode: gamemode, gametype: gametype, setting: setting, lobbytype: lobbytype, lobbypass: lobbypass, players: {p1: null, p2: null}, owner: Tools.prefs.data.username, maxPlayers: maxPlayers}; 
	
	if(maxPlayers === 4) {
		lobby.players.p3 = null;
		lobby.players.p4 = null;
	}
	
	lobbies.emit('installLobby', lobby, function(success, error) {
		if(success) {
			Tools.prefs.data.lobby = lobby.name;
			Tools.prefs.save(1);
			updateLobbyPage(lobby.name);
			Tools.showPage(Tools.pages.lobby);
			$('.modal-fixed').remove();
		} else {
			$('.modal-fixed').remove();
			if(typeof error !== 'undefined') {
				Tools.sendAlert(error);
			}
		}
	});
	
}

function changeSetting(button, type) {
	"use strict";
	var option = button.attr('id');
	var value = parseInt($('#setting').val());
	var change = 0;
	
	if(option === 'inc')	 {
		change = 1;
	}
	
	if(option === 'dec') {
		change = -1;
	}
	if(option === 'stock') {
		if(value > 8) {
			value = 8;
		}
	}
	
	value += change;
	
	
	if(type === 'stock') {
		if(value > 8) {
			return false; 
		}
	}
	if(type === 'time') {
		if(value > 15) {
			return false;
		}
	}
	if(value < 1) {
		return false;
	} 
	
	$('#setting').attr('value', value);
}

function displayLobbies(lobbyList) {
	"use strict";
	for(var i = 0; i < lobbyList.length; i++) {
		displayLobby(lobbyList[i]);
	}
}

function displayLobby(lobby) {
	"use strict";
	
	var display = $('<div>', {class: 'noPointer'}).append(
		$('<h1>', {class: 'noPointer'}).text(lobby.name),
		$('<h3>', {class: 'noPointer'}).text('Game mode: ' + lobby.gamemode),
		$('<h3>', {class: 'noPointer'}).text('Game type: ' + lobby.gametype + " - " + lobby.setting),
		$('<h3>', {class: 'noPointer'}).text('Lobby Privacy: ' + lobby.lobbytype)
	);
	var players = $('<h3>', {class: 'noPointer'}).text('Players: ');
	for(var player in lobby.players) {
		if (lobby.players.hasOwnProperty(player)) {
        	display.append(
				players.append(lobby.players[player] + " - ")
			);
    	}
	}
	
	if(!$('#'+lobby.name).length) {
		$('#lobbyList').append(
			$('<div>', {id: lobby.name, class: 'lobbyDiv unselectable'}).append(display)
		);
	} else {
		$('#'+lobby.name).html(display);
	}
}

function updateLobbyPage(lobby) {
	"use strict";
	lobbies.emit('getLobby', lobby, function(success, lobby, error) {
		if(success) {
			var display = $('<div>', {id: 'lobbyContainer'}).append(
				$('<button>', {id: 'leaveLobby', class: 'shadow floatRight'}).text('Leave Lobby'),
				$('<button>', {id: 'startGame', class: 'shadow floatRight'}).text('Start Game'),
				$('<h1>').text('Lobby: ' + lobby.name),
				$('<h2>').text('Leader: ' + lobby.owner),
				$('<h3>', {class: 'noPointer'}).text('Game mode: ' + lobby.gamemode),
				$('<h3>', {class: 'noPointer'}).text('Game type: ' + lobby.gametype + " - " + lobby.setting),
				$('<h3>', {class: 'noPointer'}).text('Lobby Privacy: ' + lobby.lobbytype)
			);
			
			
			
			for(var slot in lobby.players) {
				if(lobby.players[slot] === null) {
					lobby.players[slot] = 'Empty';
				}
			}
			var playerDisplay = $('<div>', {id:'playerContainer'}).append(
				$('<div>', { class: 'playerDisplay'}).append($('<div>', {id:'p1', class: 'border selectPlayer'}).append($('<h1>').text('Player 1'),$('<h2>').text(lobby.players.p1))),
				$('<div>', { class: 'playerDisplay'}).append($('<div>', {id:'p2', class: 'border selectPlayer'}).append($('<h1>').text('Player 2'),$('<h2>').text(lobby.players.p2)))
			);
			if(lobby.maxPlayers === 4) {
				playerDisplay.append(
					$('<div>', { class: 'playerDisplay'}).append($('<div>', {id:'p3', class: 'border selectPlayer'}).append($('<h1>').text('Player 3'), $('<h2>').text(lobby.players.p3))),
					$('<div>', { class: 'playerDisplay'}).append($('<div>', {id:'p4', class: 'border selectPlayer'}).append($('<h1>').text('Player 4'), $('<h2>').text(lobby.players.p4)))
				);
			}
			
			
			$('#lobbypage').html(display);
			$('#lobbypage').append(playerDisplay);
			if(Tools.prefs.data.username !== lobby.owner) {
				$('#startGame').remove();
			}
		} else {
			Tools.sendAlert(error);
		}
	});
}
