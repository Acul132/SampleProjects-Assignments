//
//  Vector.cpp
//  Project1
//
//  Created by Josh Renelli on 2017-11-03.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include <iostream>
#include "Vector.h"
#include <cmath>


double Vector::norm() const{
    return sqrt((x*x)+(y*y));           //Uses the formula Sqrt(x^2+y^2)
}

const Vector Vector:: operator -(){
    return Vector(-x,-y);               //Negates x and y
}

const Vector operator +(const Vector& first,const Vector& second){
    //Accesses the data fields using the accessors and add's the values together
    return Vector(first.getX()+second.getX(),first.getY()+second.getY());
}

const Vector operator -(const Vector& first, const Vector& second){
    //Accesses the data fields using the accessors and subtracts them from each other
    return Vector(first.getX()-second.getX(),first.getY()-second.getY());
}

