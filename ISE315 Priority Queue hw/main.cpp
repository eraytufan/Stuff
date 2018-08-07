#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <ctime>
#include "priorityq.h"
#include "myheap.h"

using namespace std;


int main(int argc , char** argv ){


    PriorityQ priorityq;
    
    string line;
    ifstream inFile ("bids.txt");
    
    double p = atof(argv[1]);
    cout << p << endl;
    int m = atoi(argv[2]);
    cout << m << endl;

    int pNumber = p * (100.0);


    srand(time(0));


    /*int lineNumber = 0;
    
    for(int i = 0; i<m; i++){

        if(lineNumber ==100){


            lineNumber = 0;
            continue;
        }

        lineNumber++;
    }*/
    vector<int> allNumbers;
    
        
    if (inFile.is_open()){
            
        while (getline(inFile, line,'\n')){

            /*if(lineNumber ==0){
                istringstream ss(line);
                
                
                ss >> totalResources;
                lineNumber++;

                continue;
            }*/

            istringstream ss(line);
                
            int x;
            ss >> x;
            allNumbers.push_back(x);

        }
    }
    
    
    inFile.close();

    int counter = 0;
    int mycounter = 0;
    vector<int> myvector;

    priorityq.h.buildHeap(myvector);
    int maxValue = 0;
    vector<int> maxBids;
    int updates;
    int newBids;
    int counter2 = 0;


    while(counter < m){
        if(counter!=0 && (counter%100) ==0){
            maxValue = priorityq.extractMax();
            maxBids.push_back(maxValue);
        }

        int randomPNumber = rand() %100;

        if(pNumber > randomPNumber){
            int heapsize = priorityq.h.getHeapSize();
            if(heapsize != 0){
                int index = rand() % heapsize;
                priorityq.increaseKey(index, 0);
                updates++;
            }
        }

        else{
            priorityq.insert(allNumbers.at(counter2));
            newBids++;
            counter2++;
        }
        counter++;


    }

    cout << "The bid values of auction winners: " << endl;

    for(int i = 0; i< maxBids.size(); i++){
        cout << i+1 << ". winner :";
        cout << maxBids.at(i) << " " << endl;
    }
    
    priorityq.h.printHeap();
    cout << "Number of new bids: " << newBids << endl;
    cout << "Number of updates:  " << updates << endl;  

    return 0;
  
 
}
