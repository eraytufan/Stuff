#include "priorityq.h"
#include <iostream>

#define left(i) i*2
#define right(i) i*2+1
#define parent(i) i/2

PriorityQ::PriorityQ(){
    
    
}

int PriorityQ::extractMax(){

    if(h.heapsize <= 0){
        //std::cout << "heap underflow" << endl; 
        return -1;
    }
    int max = h.array[1];
    swap(h.array[1], h.array[h.heapsize]);
    h.heapsize--;
    h.maxHeapify(1);
    return max;
    
}

void PriorityQ::increaseKey(int i, int k){
    /*if(k < h.array[i]){
        //std::cout << "new key is smaller than current key" << endl;
    }*/
    //25 percent increases the key
    k = h.array[i] * 5/4;
    h.array[i] = k;
    while(i > 1 && h.array[parent(i)] < h.array[i]){
        swap(h.array[i], h.array[parent(i)]);
        i = parent(i);
    }
}

void PriorityQ::insert(int i){

    h.heapsize++;
    h.array.push_back(i);
    int j = h.heapsize;

    while (j>1 && h.array[parent(j)] < h.array[j]) {
        swap(h.array[parent(j)],h.array[j]);
        j = parent(j);
    }   

}


