#include "myheap.h"
#include <iostream>

#define left(i) i*2
#define right(i) i*2+1
#define parent(i) i/2

Myheap::Myheap(){
    
    
}

void Myheap::maxHeapify(int i){
    int l = left(i);
    int r = right(i);
    int largest = i;

    if(l < heapsize && array[l] > array[i]){
        largest = l;
    }

    if(r < heapsize && array[r] > array[largest]){
        largest = r;
    }
    if(largest != i){
        swap(array[i], array[largest]);
        maxHeapify(largest);
    }
}

void Myheap::buildHeap(vector<int> v){
    array = v;
    //array.insert(array.begin(), 0);
    heapsize = array.size() -1;
    for(int i = heapsize/2; i>=1; i--){
        //cout << "heapify " << i << endl;
        maxHeapify(i);
    }
}

void Myheap::printHeap(){
    cout<<"current heap: ";
        for (int i=0;i<=heapsize;i++) {
            cout<<array[i]<<" ";
        }
    cout<<endl;
}

int Myheap::getHeapSize(){
    return heapsize;
}
