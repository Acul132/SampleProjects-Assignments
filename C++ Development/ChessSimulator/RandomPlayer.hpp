//
//  RandomPlayer.hpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef RandomPlayer_hpp
#define RandomPlayer_hpp

#include "Board.hpp"
#include "Player.hpp"
#include "ComputerPlayer.hpp"
#include <string>


//Derived class of the ComputerPlayer class that places Piece's in random position
class RandomPlayer : public ComputerPlayer{
public:
    //Argumented constructor that takes in a Piece object to be used in the ComputerPlayer constucter
    RandomPlayer(Piece piece);
    
    //makeMove is the function that is called is used by the Game class and is defined here to
    //provide details as to how an object of RandomPlayer will make a move
    void makeMove(Board&)const;
};


#endif /* RandomPlayer_hpp */
