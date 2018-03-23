//
//  ComputerPlayer.cpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include "ComputerPlayer.hpp"
#include "Board.hpp"
#include "Player.hpp"
#include <string>



int ComputerPlayer::counter;    //Defining the counter variable

ComputerPlayer::ComputerPlayer(Piece piece):Player("Computer " + std::to_string(++counter),piece){}

