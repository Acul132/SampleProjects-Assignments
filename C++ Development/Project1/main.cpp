//
//  main.cpp
//  Project1
//
//  Created by Josh Renelli on 2017-11-20.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include "Coor.h"
#include "Vector.h"
#include "Path.h"
#include <iostream>

using namespace std;

int main(){
    Path points;
    points.addPoint(Coor(0,0));
    points.addPoint(Coor(1,1));
    points.addPoint(Coor(2,2));
    points.addPoint(Coor(3,3));
    points.addPoint(Coor(4,4));
    points.addPoint(Coor(5,5));
    cout << "Size : " << points.num_points() << "\nLength: " << points.length() << endl;
    
    return 0;
}
