//
//  HumanPlayer.hpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef HumanPlayer_hpp
#define HumanPlayer_hpp

#include<string>
#include "Player.hpp"
#include "Board.hpp"

using std::string;

//Derived class of Player
class HumanPlayer : public Player{
public:
    //Argumented Constructor for the HumanPlayer class to be used with the Player constructor
    HumanPlayer(const string& thisName,Piece thisPiece);
    
    //Function that will be called to make moves onto the board from the Game class
    void makeMove(Board&)const;
};

#endif /* HumanPlayer_hpp */
