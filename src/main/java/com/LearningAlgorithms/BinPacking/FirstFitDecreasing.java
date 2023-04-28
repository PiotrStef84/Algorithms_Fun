package com.LearningAlgorithms.BinPacking;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstFitDecreasing extends BinPackingSolver{

    private List<Bin> bins;
    private List<Integer> items;
    private int binCapacity;
    private int binCounter = 1;

    public FirstFitDecreasing(List<Integer> items, int binCapacity) {
        this.items = items;
        this.binCapacity = binCapacity;
        this.bins = new ArrayList<>(items.size());
    }

    public void solve(boolean showSteps) {
        Collections.sort(items, Collections.reverseOrder());

        if (this.items.get(0) > this.binCapacity) {
            System.out.println("No feasible solution...");
            return;
        }

        this.bins.add(new Bin(binCapacity, binCounter)); // first bin

        for (Integer currentItem : items) {
            boolean isOk = false; // track whether we have put the item into a bin or not
            int currentBin = 0;

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
