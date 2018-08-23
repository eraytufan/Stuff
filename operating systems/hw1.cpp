#include <iostream>
#include <string>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>   
#include <fstream>
#include <sstream>
#include <vector>
#include <dirent.h>
#include <thread>
#include <algorithm>
#include <sys/wait.h>

using namespace std;

//the program works in ubuntu by using g++ filename.cpp -o fork -pthread -std=gnu++11

//gets a vector of integer
//and returns the value of the median as an integer
int findMedian(vector<int> x){
    vector<int> numbers = x;
    sort (numbers.begin(), numbers.end());   
    int n = numbers.size()/2;
    
    return numbers.at(n);
        
}

//gets the name of the input file and thread's number as a parameter
//gets the numbers from the input file and calculates the maximum, minimum and median 
//reads different numbers from the input depending on thread number as parameter
void func(string inputfile, int counter, int thnumber){
    string line;
    ifstream inFile (inputfile.c_str());
    
    
    vector<int> all_numbers;
    //we give minimum 100 because it is the maximum number it can get.
    //And we give maxumum number 0 because it is the minimum number it can get.
    //So they will eventually change with input file.
    int maximum = 0;
    int minimum = 100;
    int median = 0;
    
    ifstream infile(inputfile.c_str());{
        
        if (inFile.is_open()){
            
            while (getline(inFile, line,'\n')){
                istringstream ss(line);
                
                int x;
                ss >> x;
                //depending on the parameter
                //gets the different column of the input file.
                for(int i = 0; i<counter; i++){
                    ss >> x;   
                }
                
                //calculates the minimum number
                if(x<minimum){
                    minimum = x;   
                }
                //calculates the maximum number
                else if(x>maximum){
                    maximum = x;
                }
                
                //adds all number to a vector
                all_numbers.push_back(x);
            
                
            }   
        }   
        
    }
    
    
    //finds median by calling the function
    median = findMedian(all_numbers);
    //prints all answers to the output.
    //counter + 1 represents the process number in output
    //thnumber + 1 represent the thread number in output
    cout << "Thread " << counter+1 << "." << thnumber + 1 << ": " << inputfile << " Min: " << minimum << " Max: " << maximum << " Med: " << median << endl;
    //closes input file
    inFile.close();
    
}

vector<string> getFileNames(const char* p){
    
    vector<string> fNames;
    DIR *dpdf;
    struct dirent *epdf;
    //we give .txt because we want to find only txt files.
    string str(".txt");
    dpdf = opendir(p); 
    
    if (dpdf != NULL){
       while (epdf = readdir(dpdf)){
            string fName = epdf->d_name;
            if(fName.find(str) != string::npos){ 
                fNames.push_back(fName);
            }
       }
    }
    
    return fNames;
}



int main(int argc , char** argv ){
    cout << "Master: Start" << endl;
    cout << "Master: Passed input folder path: " << argv[1] << endl;
    
    const char* path = argv[1];
    
    //by calling fork function we create a new process.
    pid_t child1 = fork();
    
    if (child1 == 0){
        //slave process 1 gets into this condition
        cout << "Slave1: Project statistics" << endl;
        
        //gets file names from the file everytime the process created
            //it gets text files in the file by calling the function.
        vector<string> files = getFileNames(path);
        //we create a for loop for file names.
        //it creates a new thread for the text file everytime it gets into loop
        for(int i=0; i<files.size(); i++){
            thread first (&func, files.at(i), 0, i);
            first.join();
        } 
        //slave 1 finishes here.
        cout << "Slave1: Done" << endl;
    }
    else{
        //master process always continues with else condition
        int stop1;
        
        //we use waitpid because we wait child processes to finish.
        waitpid(child1, &stop1, 0); 
        
        pid_t child2 = fork();
        if (child2 == 0){
            
            cout << "Slave2: Midterm statistics" << endl;
            
            vector<string> files = getFileNames(path);
            for(int i=0; i<files.size(); i++){
                thread first (&func, files.at(i), 1, i);
                first.join();
            } 
            
            cout << "Slave2: Done" << endl;
        }
        else{
            int stop2;
            waitpid(child2, &stop2, 0); 
            
            pid_t child3 = fork();
            if(child3 == 0){
                cout << "Slave3: Final statistics" << endl;
                
                vector<string> files = getFileNames(path);
                
                for(int i=0; i<files.size(); i++){
                    thread first (&func, files.at(i), 2, i);
                    first.join();
                } 
                
                cout << "Slave3: Done" << endl;
                //func("course2.txt", 0);
                
            }
            
            else{
                //master process's last else condition after creating 3 slaves.
                int stop3;
                waitpid(child3, &stop3, 0);
                cout << "Master: Finish" << endl;   
            }
        }
    }
    
    
}
