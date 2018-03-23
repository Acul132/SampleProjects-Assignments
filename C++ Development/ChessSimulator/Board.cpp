//
//  Board.cpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//
#include <iostream>
#include "Board.hpp"

using std::cout;


void Board::clear(){
    for(int i = 0; i < 9; ++i)  //Loops through each element setting it to Piece::Empty
        pieces[i]=Piece::empty;
}

void Board::display(){
    //Displays each line by casting the Piece type to a char
    cout << "+-----------+\n"
    << "| "<< static_cast<char>(pieces[0])<<" | "<<static_cast<char>(pieces[1])
    <<" | "<<static_cast<char>(pieces[2])<<" |\n"
    << "+-----------+\n"
    << "| "<< static_cast<char>(pieces[3])<<" | "<<static_cast<char>(pieces[4])
    <<" | "<<static_cast<char>(pieces[5])<<" |\n"
    << "+-----------+\n"
    << "| "<< static_cast<char>(pieces[6])<<" | "<<static_cast<char>(pieces[7])
    <<" | "<<static_cast<char>(pieces[8])<<" |\n"
    << "+-----------+\n";
}

void Board::makeMove(Piece newPiece, int position){
    if(isLegal(newPiece,position))
        pieces[position] = newPiece;       //Only makes the move if the move is legal
}

void Board::undoMove(int position){
    pieces[position] = Piece::empty;
}

bool Board::isLegal(Piece newPiece, int position)const{
    return pieces[position] == Piece::empty;
}

bool Board::isWinner(Piece piece) const{
    //Check Vertical axis
    for(int i = 0; i < 3; ++i)
        if(pieces[i] == pieces[i+3] && pieces[i+3] == pieces[i+6] && pieces[i] != Piece::empty)
            if(pieces[i] == piece)
                return true;
    
    //Check horizontal axis
    for(int i = 0; i < 9; i+=3)
        if(pieces[i] == pieces[i+1] && pieces[i+1] == pieces[i+2] && pieces[i] != Piece::empty)
            if(pieces[i] == piece)
                return true;
    
    //Checking top-left to bottom-right
    if(pieces[0] == pieces[4] && pieces[4] == pieces[8] && pieces[0] != Piece::empty)
        if(pieces[0] == piece)
            return true;
    
    //Checking top-right to bottom-left
    if(pieces[2] == pieces[4] && pieces[4] == pieces[6] && pieces[2] != Piece::empty)
        if(pieces[2] == piece)
            return true;
    
    return false;       //There is no winner
}

bool Board::isFull() const{
    for(int i = 0; i < 9; ++i)      //Loops through each board element checking for Piece::empty
        if(pieces[i] == Piece::empty)
            return false;
    return true;
}



