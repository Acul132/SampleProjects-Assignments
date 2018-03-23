

$(function(){
    //Current users username
    var username = Tools.prefs.data.username;
    var loggedin = Tools.prefs.data.loggedin;
    
    //Enter user into chat if logged in
    if(loggedin){
        chatIO.emit('enteredChat', username);
    }
    
    //Check if user clicked another user's username in chat/connected-userlist
    $(document).click(function (e){
        if($(e.target).is($('.messageUsername'))){
            Friends.display(username);
        }
    });
    
    //Allow user to submit text by pressing enter key
    $('#inputtext').keypress(function(event){
        if(event.keyCode == 13){
            $('#sendMsg').click();
        }
    });
    
    //Send text to every other socket connected to the chat
    $('#sendMsg').click(function(){
        $('#generalmessages').scrollTop($('#generalmessages')[0].scrollHeight);     //Scroll the chat area down to the most recent sent message
        var sender = Tools.prefs.data.username;
        var message = "<li><span class='messageUsername'>"+sender+"</span> :"+$("#inputtext").val()+"</li>";        //Send message to server
        //Only emits sentMessage when the message isn't empty
        if($("#inputtext").val())
            chatIO.emit('sentMessage', message); 
        $("#inputtext").val('');
    });
    
    //Append message to message area once received from server
    chatIO.on('receiveMessage', function(message){
        $("#generalmessages").append(message);
    });
    
    //Update the userlist once a new user is received from the server
    chatIO.on('updateUserList', function(userList){
        var html = '';
        for(i = 0; i < userList.length; i++){
            html+= "<li><span class='messageUsername'>"+userList[i]+"</span></li>"
        }
        $("#connectedusers").val();
        $("#connectedusers").append(html);
    });
    
    
    //Called once a user enteres the chat
    function enterChat(){
        chatIO.emit('enteredChat',username);
    }
});

