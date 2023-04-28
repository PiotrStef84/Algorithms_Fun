package com.LearningAlgorithms.BinPacking;



import java.util.ArrayList;
import java.util.List;

public class NextFit extends BinPackingSolver{

    private List<Bin> bins;
    private List<Integer> items;
    private int binCapacity;
    private int binCounter = 1;

    public List<Bin> getBins() {
        return bins;
    }

    public NextFit(List<Integer> items, int binCapacity) {
        this.items = items;
        this.binCapacity = binCapacity;
        this.bins = new ArrayList<>(items.size());
    }


    public void solve(boolean showSteps) {

        for(Integer item : this.items){
            if(item > this.binCapacity){
                System.out.println("No feasible solution...");
                return;
            }
        }

        this.bins.add(new Bin(binCapacity, binCounter)); // first bin

        int currentBin = 0;

        for (Integer currentItem : items) {
            boolean isOk = false; // track whether we have put the item into a bin or not

            while (!isOk) {
                if (currentBin == this.bins.size()) { // item does not fit in last bin -> try a new bin
                    Bin newBin = new Bin(binCapacity, ++binCounter);
                    newBin.put(currentItem);
                    this.bins.add(newBin);
                    isOk = true;
                } else if (this.bins.get(currentBin).put(currentItem)) { // current item fits in the given bin
                    isOk = true;
                } else {
                    currentBin++; // trying the next bin
                }
            }
        }
    }

    @Override
    public Steps solveAndRecord(boolean showSteps) {
        return null;
    }

    public void showResults() {
        for (Bin bin : this.bins) {
            System.out.println(bin);
        }
    }
}
