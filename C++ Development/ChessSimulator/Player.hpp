//
//  Player.hpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef Player_hpp
#define Player_hpp
#include <string>
#include "Board.hpp"
using std::string;

//Abstract class Player 
class Player{
public:
    //Argumented constructor that takes in a constant string argument and a Piece argument setting
    //The respective private arguments of Player to that
    Player(const string& thisName,Piece thisPiece);
    
    //Accessor which returns the Piece type associated with this Player
    Piece getPiece()const;
    
    //Accessor that returns the a string representation of the Name associated with this player
    const string& getName();
    
    //Virtual function to be implemented by derived classes of Player that will be used to make
    //moves onto the board
    virtual void makeMove(Board&) const = 0;
private:
    string name;
    Piece piece;
};


#endif /* Player_hpp */
