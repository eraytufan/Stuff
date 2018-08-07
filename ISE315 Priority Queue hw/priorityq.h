#ifndef PRIORITYQ
#define PRIORITYQ

#include <vector>
#include "myheap.h"

using namespace std;

class PriorityQ{
    public:
    	PriorityQ();
    	Myheap h;
    	void insert(int i);
    	int extractMax();
    	void increaseKey(int i, int k);
       
    private:
        
};





#endif
