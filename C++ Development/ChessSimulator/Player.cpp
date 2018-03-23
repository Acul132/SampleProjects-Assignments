//
//  Player.cpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include "Board.hpp"
#include "Player.hpp"
#include <string>
using std::string;

Player::Player(const string& thisName,Piece thisPiece):name(thisName),
piece(thisPiece){};     //Sets the member value of Player to the arguments provided

Piece Player::getPiece()const{
    return piece;
}

const string& Player::getName(){
    return name;
}

