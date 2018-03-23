//
//  HumanPlayer.cpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include "Board.hpp"
#include "HumanPlayer.hpp"
#include "Player.hpp"
#include <iostream>
using std::cout;
using std::cin;


HumanPlayer::HumanPlayer(const string& thisName,Piece thisPiece):Player(thisName,thisPiece){};

void HumanPlayer::makeMove(Board& board)const{
    int position;
    cout << "Enter a position for your piece ";
    cin >> position;
    while(true){        //Keeps requesting a position if the user tried to place the Piece in an illegal position
        if(board.isLegal(Player::getPiece(), position)){
            board.makeMove(Player::getPiece(), position);
            break;
        }
        cout << "Enter a VALID for your piece ";
        cin >> position;
    }
}

