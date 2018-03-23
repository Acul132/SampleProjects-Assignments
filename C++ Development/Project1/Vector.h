//
//  Vector.h
//  Project1
//
//  Created by Josh Renelli on 2017-11-20.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef Vector_h
#define Vector_h

class Vector{
public:
    //Default constructor that created a Vector with x=0 and y=0
    Vector():x(0.), y(0.){};
    
    //Argumented constructor that creates a Vector with x=numX and y=numY
    Vector(double numX,double numY):x(numX), y(numY){};
    
    //Accessor for the x data field
    double getX() const {return x;}
    
    //Accessor for the y data field
    double getY() const {return y;}
    
    //Method overloading for the "+" operator, adds both vectors together
    friend const Vector operator +(const Vector& first,const Vector& second);
    
    //Method overloading for the "-" subtracts the two vectors from each other
    friend const Vector operator -(const Vector& first, const Vector& second);
    
    //Method overloading for the unary "-" Returns the negative value of the vector
    const Vector operator -();
    
    //Returns the norm(length) of the vector 
    double norm() const;
    
private:
    double x;
    double y;
};

#endif /* Vector_h */
