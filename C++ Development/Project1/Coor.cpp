//
//  Coor.cpp
//  Project1
//
//  Created by Josh Renelli on 2017-11-20.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include "Coor.h"
#include <iostream>

const Vector operator -(const Coor& first, const Coor& second){
    //Uses the Vector classes accessors to subtract the elements from each other
    return Vector(second.getX()-first.getX(),second.getY()-first.getY());
}
