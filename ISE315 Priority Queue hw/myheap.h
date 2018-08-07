#ifndef MYHEAP
#define MYHEAP

#include <vector>

using namespace std;

class Myheap{
    public:
    	Myheap();
        void maxHeapify(int i);
        void buildHeap(vector<int> v);
        void printHeap(); 
        int heapsize;
        vector<int> array;
        int getHeapSize();
       
    private:
        
        
        
};





#endif
