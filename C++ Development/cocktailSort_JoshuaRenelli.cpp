//
//  main.cpp
//  Assignment02
//
//  Created by Josh Renelli on 2017-10-19.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include <iostream>
#include <cstdlib>
using namespace std;

const int arraySize = 20;                 //Maximum of allowed elements
int userInput();
void cocktailSort(int arr[], int arrSize, int counter[]);
bool forwardPass(int arr[],int arrSize, int counter[]);
bool backwardPass(int arr[],int arrSize, int counter[]);
void outputArray(int arr[],int arrSize);
void fillArray(int arr[],int arrSize);


int main() {
    
    int comp_xchg[] =  {0,0};                       //Array to track # of comps and xchg's
    int unsortedArray [arraySize];
    
    int numOfElements = userInput();
    
    fillArray(unsortedArray,numOfElements);
    cout << "Unsorted Array : ";
    outputArray(unsortedArray,numOfElements);
    
    cocktailSort(unsortedArray,numOfElements,comp_xchg);
    cout << "\n" << "Comparisons made : " << comp_xchg[0] << "\n";
    cout << "Exchanges made : " << comp_xchg[1] << "\n";

    cout << "Sorted array : ";
    outputArray(unsortedArray, numOfElements);
    cout << "\n";
    
}

int userInput(){
    int numOfElements;
    
    do{
        cout << "Please enter the number of elements(max 20): ";
        cin >> numOfElements;
        cout << "\n";
        
    }while(numOfElements > arraySize);
    
    return numOfElements;
}

void outputArray(int arr[],int arrSize){
    for(int i = 0; i < arrSize; i++)
        cout << arr[i] << " ";
}

void fillArray(int arr[], int arrSize){
    srand((int)time(0));
    for(int i = 0; i < arrSize; i++)
        arr[i] = rand() % 21;               //Creates values in array from 0-20
}

void cocktailSort(int arr[],int arrSize, int counter[]){
    bool swapped;
    do{
        swapped = forwardPass(arr,arrSize,counter);
        if(swapped)                         //Loops through the array backwards
            swapped = backwardPass(arr,arrSize,counter);
    }while(swapped);                        //Loop again if a swap was made
}

bool forwardPass(int arr[],int arrSize, int counter[]){
    bool swapped = false;
    
    for(int i = 0; i <= arrSize -2; i++){
        counter[0]++;                   //Increases the value of comparison
        if(arr[i] > arr[i+1]){
            counter[1]++;               //Increases the value of exchange
            int temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
            swapped = true;
        }
    }
    return swapped;                     //Returns true if a swap was made
}


bool backwardPass(int arr[],int arrSize, int counter[]){
    bool swapped = false;
    
    for(int i = arrSize; i >= 0; i--){
        counter[0]++;                   //Increases the value of comparison
        if(arr[i] > arr[i+1]){
            counter[1]++;               //Increases the value of exchange
            int temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
            swapped = true;
        }
    }
    return swapped;                     //Returns true if a swap was made
}



