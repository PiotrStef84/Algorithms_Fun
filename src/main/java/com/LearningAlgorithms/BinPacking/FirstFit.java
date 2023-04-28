package com.LearningAlgorithms.BinPacking;

import java.util.ArrayList;
import java.util.List;

public class FirstFit extends BinPackingSolver {

    private List<Bin> bins;
    private List<Integer> items;
    private int binCapacity;
    private int binCounter = 1;

    public FirstFit(List<Integer> items, int binCapacity) {
        this.items = items;
        this.binCapacity = binCapacity;
        this.bins = new ArrayList<>(items.size());
    }

    public void solve(boolean showSteps) {

        double lowerBand = 0;

        for(Integer item : this.items){
            if(item > this.binCapacity){
                System.out.println("No feasible solution...");
                return;
            }
            lowerBand += item;
        }
        if(showSteps){
            System.out.println("Items to be sorted: " +this.items);
            System.out.println("Lower band: " + lowerBand);
            System.out.println("Minimal number of bins required is: " + Math.ceil(lowerBand/binCapacity));
        }
        this.bins.add(new Bin(binCapacity, binCounter)); // first bin

        for (Integer currentItem : items) {
            boolean isOk = false; // track whether we have put the item into a bin or not
            int currentBin = 0;

            while (!isOk) {
                if (currentBin == this.bins.size()) { // item does not fit in last bin -> try a new bin

                    if(showSteps){
                        System.out.println(currentItem + " Does not fit into any open bins");
                        System.out.println("Opening a new bin");
                    }

                    Bin newBin = new Bin(binCapacity, ++binCounter);
                    newBin.put(currentItem);

                    if(showSteps) {
                        System.out.println("Putting " + currentItem + " in bin: " + (currentBin +1));
                    }

                    this.bins.add(newBin);

                    isOk = true;
                }
                else if (this.bins.get(currentBin).put(currentItem)) { // current item fits in the given bin

                    if(showSteps) {
                        System.out.println("Putting " + currentItem + " in bin: " + (currentBin + 1));
                    }

                    isOk = true;
                } else {
                    currentBin++; // trying the next bin
                }
            }
        }
    }

    @Override
    public Steps solveAndRecord(boolean showSteps) {
        Steps steps = new Steps();

        double lowerBand = 0;

        for(Integer item : this.items){
            if(item > this.binCapacity){
                steps.addStep("No feasible solution...");
                return steps;
            }
            lowerBand += item;
        }
        if(showSteps){
            steps.addStep("Items to be sorted: " +this.items);
            steps.addStep("Lower band: " + lowerBand);
            steps.addStep("Minimal number of bins required is: " + Math.ceil(lowerBand/binCapacity));
        }
        this.bins.add(new Bin(binCapacity, binCounter)); // first bin

        for (Integer currentItem : items) {
            boolean isOk = false; // track whether we have put the item into a bin or not
            int currentBin = 0;

            while (!isOk) {
                if (currentBin == this.bins.size()) { // item does not fit in last bin -> try a new bin

                    if(showSteps){
                        steps.addStep(currentItem + " Does not fit into any open bins");
                        steps.addStep("Opening a new bin");
                    }

                    Bin newBin = new Bin(binCapacity, ++binCounter);
                    newBin.put(currentItem);

                    if(showSteps) {
                        steps.addStep("Putting " + currentItem + " in bin: " + (currentBin +1));
                    }

                    this.bins.add(newBin);

                    isOk = true;
                }
                else if (this.bins.get(currentBin).put(currentItem)) { // current item fits in the given bin

                    if(showSteps) {
                        steps.addStep("Putting " + currentItem + " in bin: " + (currentBin + 1));
                    }

                    isOk = true;
                } else {
                    currentBin++; // trying the next bin
                }
            }
        }
        return steps;
    }

    public void showResults() {
        System.out.println("Final content of bins:");
        for (Bin bin : this.bins) {
            System.out.println(bin);
        }
    }
}


