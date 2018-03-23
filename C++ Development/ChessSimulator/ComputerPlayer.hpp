//
//  ComputerPlayer.hpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef ComputerPlayer_hpp
#define ComputerPlayer_hpp
#include "Board.hpp"
#include "Player.hpp"
#include <string>

//Abrstract Derived class from the Parent class Player
class ComputerPlayer : public Player{
public:
    
    //Argumented constructor that will take in a Piece object to use with the Player consturctor
    ComputerPlayer(Piece piece);
    
    //Making the makeMove function virtual again to be used by a derived class of ComputerPlayer
    virtual void makeMove(Board&) const = 0;
    
private:
    static int counter;     //Static counter variable to track how many computers there are
};

#endif /* ComputerPlayer_hpp */
