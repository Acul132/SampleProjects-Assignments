//
//  Game.hpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef Game_hpp
#define Game_hpp
#include "Board.hpp"
#include "HumanPlayer.hpp"
#include "ComputerPlayer.hpp"
#include "RandomPlayer.hpp"
#include "Player.hpp"
#include <string>

//The Game class is the object that will be created by a user of this project to run a
//TicTacToe game and play it
class Game{
public:
    //Default constructor to create a Game object setting both pointer to nullptr, calling the
    //default board constructor and setting the turn counter to 0
    Game();
    
    //Destructor for the Game object deleting the dynamically allocated Player objects
    ~Game();
    
    //Function that will instanciated each of the Player objects with the correct child class of Player
    void selectPlayers();
    
    //Returns the pointer that corrisponds to the next Player that should be taking their turn
    Player* nextPlayer() const;
    
    //Returns true if the board is not full and has no winner, but false if one of those cases are
    //not true
    bool isRunning() const;
    
    //The function that is called to run the tictactoe game that is set up by this object
    void play();
    
    //The function called after the game has commenced which writes to std out who is the winner
    //of the game or if it was a tie
    void announceWinner();
private:
    Board board;
    Player *p1;
    Player *p2;
    int counter;
};

#endif /* Game_hpp */
