package com.LearningAlgorithms.TSP;

import java.util.ArrayList;

public abstract class TSPSolver {


    public abstract void solve(ArrayList<City> cities, City firstCity ,boolean steps);

    public abstract Steps solveAndRecord(ArrayList<City> cities, City firstCity , boolean steps);

    public abstract ArrayList<City> getFinalRoute();


}
