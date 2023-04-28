package com.LearningAlgorithms.BinPacking;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestFitDecreasing extends BinPackingSolver{

    private List<Bin> bins;
    private List<Integer> items;
    private int binCapacity;
    private int binCounter = 1;

    public List<Bin> getBins() {
        return bins;
    }

    public BestFitDecreasing(List<Integer> items, int binCapacity) {
        this.items = items;
        this.binCapacity = binCapacity;
        this.bins = new ArrayList<>(items.size());
    }

    @Override
    public void solve(boolean showSteps) {
        Collections.sort(items, Collections.reverseOrder());

        BestFit bestFit = new BestFit(this.items, this.binCapacity);
        bestFit.solve(showSteps);
        this.bins = bestFit.getBins();

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
