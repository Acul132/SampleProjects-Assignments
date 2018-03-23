//
//  RandomPlayer.cpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include "Board.hpp"
#include "Player.hpp"
#include "ComputerPlayer.hpp"
#include "RandomPlayer.hpp"
#include <string>
#include <stdlib.h>
#include <iostream>

using std::cout;

RandomPlayer::RandomPlayer(Piece piece):ComputerPlayer(piece){};

void RandomPlayer::makeMove(Board& board)const{
    int nextMove = rand()%9;
    
    while(true){
        if(board.isLegal(Player::getPiece(), nextMove)){
            board.makeMove(Player::getPiece(), nextMove);
            break;
        }
        nextMove = rand()%9;
    }
    
    cout << "Piece placed at position " << nextMove << "\n";
}


