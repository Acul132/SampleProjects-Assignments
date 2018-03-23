//
//  main.cpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-12.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include "Game.hpp"

int main(){
    Game ttt = Game();  //Creating a game object
    ttt.selectPlayers();    //Selecting the player types
    ttt.play();             //Running the game
    ttt.announceWinner();   //Dispalying results
    
    return 0;
}
