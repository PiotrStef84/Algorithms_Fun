package com.LearningAlgorithms.BinPacking;

import java.util.ArrayList;
import java.util.List;

public class Bin {

    private int id;
    private int capacity;
    private int currentSize;

    private int remainingSpace;

    private List<Integer> items;



    public Bin (int capacity, int id){
        this.id = id;
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public boolean put(Integer item){
        if(this.currentSize + item > capacity){
            return false;
        }else{
            this.items.add(item);
            this.currentSize += item;

            return true;
        }
    }

    public int getId() {
        return id;
    }

    public int getCurrentSize(){
        return currentSize;
    }

    public int getRemainingSpace() {
        return capacity - currentSize;
    }

    @Override
    public String toString() {
        String contentOfBin = "Items in a bin #" + this.id + ": ";

        for(Integer item : this.items){
            contentOfBin += item + ", ";
        }

        return contentOfBin;
    }
}
