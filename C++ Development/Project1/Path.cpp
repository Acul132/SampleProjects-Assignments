//
//  Path.cpp
//  Project1
//
//  Created by Josh Renelli on 2017-11-20.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include <iostream>
#include <vector>
#include "Path.h"

void Path::addPoint(const Coor& point){
    //Uses the std::vector push_back function to add a Coor to the vector array
    path.push_back(point);
}

const Coor& Path::operator [](const int index) const{
    return path[index];
}

Coor& Path::operator [](const int index){
    return path[index];
}

double Path::length() const{
    if(path.size() < 2) return 0.;      //Deals with a path of 0 or 1 coordiante (length of 0)
    
    double length = 0.;
    for(int i = 0; i < path.size()-1; i++){     //Loop for each distinct PAIR of coordinates
        Coor firstPoint = path[i];
        Coor secondPoint = path[i+1];
        Vector vec = (firstPoint-secondPoint);  //Creating vector using Coor subtraction
        length += vec.norm();                   //Tracking the length
    }
    return length;                              //Returning the total length
}

