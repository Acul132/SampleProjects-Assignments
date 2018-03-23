//
//  Board.hpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef Board_hpp
#define Board_hpp


//Enum class to represent each potential value a Piece on the board can be 
enum class Piece {empty = ' ', first = 'X', second = 'O'};

class Board{
public:
    //Default constructor that calls the clear() function to set all of the spaces on the board to empty
    Board(){clear();};
    
    //Sets all of the pieces on the board to empty
    void clear();
    
    //Displays the board with it's contents to std output
    void display();
    
    //Makes a move by placing the entered Piece into the position int
    void makeMove(Piece,int);
    
    //"undo's" a move by setting that location on the board to empty
    void undoMove(int);
    
    //Checks if the given spot is legal to put a new piece, returns true if it is legal
    //and false if it is illegal
    bool isLegal(Piece,int) const;
    
    //Check's the whole board to determine if there is a winner for the given piece, returns true
    //if there is a winner and false otherwise
    bool isWinner(Piece) const;
    
    //Check's the board to see if all of the locations contian a non-empty piece, returns true if full
    // and false otherwise
    bool isFull() const;
    
private:
    Piece pieces[9];        //Creates an array of size 9 to hold the pieces
};

#endif /* Board_hpp */
