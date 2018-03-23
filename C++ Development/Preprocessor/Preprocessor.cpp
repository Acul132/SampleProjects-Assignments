//
//  Preprocessor.cpp
//  Preprocessor
//
//  Created by Josh Renelli on 2017-11-14.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

#include <iostream>
#include <string>
using namespace std;

class Preprocessor{
public:
    Preprocessor(): lines(0), errors(0), lastError(string::npos){};
    string process(const string&);
    const int getLines();
    const int getErrors();
    const string::size_type getErrorPos();
    
private:
    string remHash(string&);
    string remSpace(string&);
    void symbolErr(string&);
    int lines;
    int errors;
    string::size_type lastError;
};

int main() {
    
    string line;
    Preprocessor processor;
    while(getline(cin,line)){
        line = processor.process(line);
        if(!line.empty() && processor.getErrorPos() == string::npos){           //Only output line if it's non-empty and had no error
            cout << line;
            cout << endl;
        }
    }
    cout << processor.getLines() << " lines were processed\n";
    cout << processor.getErrors() << " errors occuring while processing\n";
    
    if(processor.getErrors() == 0)
        return 0;                       //Returns 0 if there were no errors
    else
        return 1;
}
    
    string Preprocessor::process(const string& str){
        string line = str;              //Creating new string so str is not eddited as it is a constant
        line = remHash(line);
        line = remSpace(line);
        symbolErr(line);
        lines++;
        return line;
    }
    
    string Preprocessor::remHash(string& str){
        string::size_type index = str.find('#');        //Finds if there is a hashtag in the string
        if(index != string::npos)
            str.erase(index);                           //Erases from a hashtag onward
        return str;
    }

    string Preprocessor::remSpace(string& str){
        int spaces = 0;
        for(int i = 0; i < str.length(); i++){
            if(static_cast<char>(str[i]) != ' ')        //Will loop until there are no more spaces at the beggining
                break;
            else
                spaces++;                               //Increases the space count for each space
        }
        str.erase(0,spaces);                            //Erases any spaces at the start of the string
        return str;
    }

    void Preprocessor::symbolErr(string& str){
        lastError = string::npos;
        string::size_type dollar = str.find('$');       //Finds the location of any potential symbol to throw error
        string::size_type percent = str.find('%');
        string::size_type ampersand = str.find('&');
        if(dollar != string::npos){
            cerr << "$ was found on line " << lines << " column " << dollar;
            lastError = dollar;
            errors++;                                   //Increase error count if an error is found
        }
        else if(percent != string::npos){
            cerr << "% was found on line " << lines << " column " << percent;
            lastError = percent;
            errors++;
        }
        else if(ampersand != string::npos){
            cerr << "& was found on line " << lines << " column " << ampersand;
            lastError = ampersand;
            errors++;
        }
    }

    const int Preprocessor::getErrors(){
        return errors;
    }
    
    const int Preprocessor::getLines(){
        return lines;
    }

    const string::size_type Preprocessor::getErrorPos(){
        return lastError;
    }
