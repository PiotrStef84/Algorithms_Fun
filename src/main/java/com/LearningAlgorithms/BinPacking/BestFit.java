package com.LearningAlgorithms.BinPacking;



import java.util.ArrayList;
import java.util.List;

public class BestFit extends BinPackingSolver {


    private List<Bin> bins;
    private List<Integer> items;
    private int binCapacity;
    private int binCounter = 1;

    public List<Bin> getBins() {
        return bins;
    }

    public BestFit(List<Integer> items, int binCapacity) {
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

        for (Integer currentItem : items) {
            boolean isOk = false; // track whether we have put the item into a bin or not
            int bestBinIdx = -1;


            while(!isOk){
                for (int i = 0; i < this.bins.size(); i++) { // loop through existing bins
                    if(bins.get(i).getRemainingSpace() >= currentItem){ // check if current bin have enough space
                        if(bestBinIdx == -1){ // if it's a first bin checked index is -1
                            bestBinIdx = i;
                        } else if (bins.get(bestBinIdx).getRemainingSpace() > bins.get(i).getRemainingSpace()) { //look through space in remaining bins
                            bestBinIdx = i;         // if better bin is found that index is being saved
                        }
                    }
                }

                if(bestBinIdx != -1){ //if there is any Bin found
                    bins.get(bestBinIdx).put(currentItem); // put item in the best bin found
                    isOk = true;
                }else {
                    // if no right bin is found open a new Bin
                    Bin newBin = new Bin(binCapacity, ++binCounter);
                    this.bins.add(newBin);
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
