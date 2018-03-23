//
//  Path.h
//  Project1
//
//  Created by Josh Renelli on 2017-11-20.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#ifndef Path_h
#define Path_h
#include <vector>
#include "Coor.h"


class Path{
public:
    //Default Constructor initlizes an empty vector array of Coor's
    Path(): path(std::vector<Coor>(0)){};
    
    //Add's a Coor to the vector array that is equal to the variable "point"
    void addPoint(const Coor& point);
    
    //Returns the size of the vector array (how many Coor's it contains)
    unsigned int num_points(){return static_cast<unsigned int>(path.size());}
    
    //Used with a constant path object
    //Returns a constant Coor object at the location [index] in the vector array
    const Coor& operator [](const int index) const;
    
    //Used only with a non-const object
    //Returns a reference to the Coor at the index "index"
    Coor& operator [](const int index);
    
    //Returns the total length of the path
    double length() const;
private:
    std::vector<Coor> path;
    
};

#endif /* Path_h */
