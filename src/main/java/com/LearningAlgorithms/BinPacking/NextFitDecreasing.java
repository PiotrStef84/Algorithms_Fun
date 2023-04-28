package com.LearningAlgorithms.BinPacking;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextFitDecreasing extends BinPackingSolver{

    private List<Bin> bins;
    private List<Integer> items;
    private int binCapacity;
    private int binCounter = 1;

    public NextFitDecreasing(List<Integer> items, int binCapacity) {
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

        NextFit nextFit = new NextFit(this.items, this.binCapacity);
        if(showSteps){
        nextFit.solve(true);
        }
        else {
            nextFit.solve(false);
        }
        this.bins = nextFit.getBins();
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
