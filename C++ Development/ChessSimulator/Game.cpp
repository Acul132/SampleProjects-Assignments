//
//  Game.cpp
//  Project2
//
//  Created by Josh Renelli on 2017-12-11.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include "Board.hpp"
#include "Player.hpp"
#include "HumanPlayer.hpp"
#include "RandomPlayer.hpp"
#include "Game.hpp"
#include <iostream>
#include <string>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

using namespace std;


Game::Game():p1(nullptr),p2(nullptr),counter(0){
    srand(time(NULL));      //Resets the rand so that that the comps are truly randomzied
}

Game::~Game(){
    delete p1;      //Good practice would be to include a virtual destructor on the player
    delete p2;      //class as it is inherited in many places but it does not dynamically
    //allocated any data so is not needed and not asked for by the project
}

void Game::selectPlayers(){
    string type,name;   //Setting up strings for the Player values
    
    
    //Prompting user to determine if Player if of type HumanPlayer or type RandomPlayer
    cout << "Enter type of first player(human/computer): ";
    cin >> type;
    
    //Asks for name if the user enters "human"
    if(type.compare("human") == 0){
        cout << "\nEnter the name for the human player: ";
        cin >> name;
        p1 = new HumanPlayer(name,Piece::first);
    }
    else{
        p1 = new RandomPlayer(Piece::first);
    }
    
    //Prompting user to determine if Player if of type HumanPlayer or type RandomPlayer
    cout << "\nEnter type of second player(human/computer): ";
    cin >> type;
    //Asks for name if the user enters "human"
    if(type.compare("human") == 0){
        cout << "\nEnter the name for the human player: ";
        cin >> name;
        p2 = new HumanPlayer(name,Piece::second);
    }
    else{
        p2 = new RandomPlayer(Piece::second);
    }
}

Player* Game::nextPlayer() const{
    //Determines who's turn is next depending on which turn the counter is on
    if(counter%2 == 0)
        return p1;
    return p2;
}

bool Game::isRunning() const{
    if(board.isWinner(p1->getPiece())){
        return false;   //False if p1 has won the game
    }
    else if(board.isWinner(p2->getPiece())){
        return false;   //False if p2 has won the game
    }
    else if(board.isFull()){
        return false;   //False if the board is full
    }

    return true;
}

void Game::play(){
    //Continuous running game state which repeats displaying the board, and making moves
    while(isRunning()){
        board.display();
        nextPlayer()->makeMove(board);
        ++counter;
    }
    board.display();
}

void Game::announceWinner(){
    cout << "The game is over!\n";
    if(board.isWinner(p1->getPiece()))
        cout << "The winner is " << p1->getName();
    else if(board.isWinner(p2->getPiece()))
        cout << "The winner is " << p2->getName();
    
    else
        cout << "The game was a tie";
    cout << "\n";
}

