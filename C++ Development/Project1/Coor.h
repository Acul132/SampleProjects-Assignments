//
//  Coor.h
//  Project1
//
//  Created by Josh Renelli on 2017-11-20.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef Coor_h
#define Coor_h
#include "Vector.h"

class Coor{
public:
    //Default Constructor initializes a Coor at (0,0)
    Coor(): x(0.), y(0.){};
    
    //Argumented constructor intializes the x and y to be equal to numX and numY
    Coor(double numX, double numY): x(numX), y(numY){};
    
    //Accessor for the x data field
    double getX() const {return x;}
    
    //Accessor for the y data field
    double getY() const {return y;}
    
    //Overloading method for subtracting two Coordinates (Coor - Coor)
    //Will return a Vector (Point A -> Point B gives vector AB)
    friend const Vector operator -(const Coor& first, const Coor& second);
    
    
private:
    double x;
    double y;
};

#endif /* Coor_h */
