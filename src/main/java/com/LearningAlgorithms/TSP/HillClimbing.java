package com.LearningAlgorithms.TSP;

import java.util.ArrayList;

public class HillClimbing extends TSPSolver{
    public static final int ITERATIONS_BEFORE_MAXIMA = 1000000;

    private ArrayList<City> finalRoute = new ArrayList<City>();

    public ArrayList<City> getFinalRoute() {
        return finalRoute;
    }

    @Override
    public void solve(ArrayList<City> cities, City firstCity, boolean steps) {
        Route currentRoute = new Route(cities, firstCity);
        Route adjacentRoute;
        int iterationsToMaximaCounter = 0;

        if(steps){
            System.out.println("Starting route: " + currentRoute + " " + currentRoute.calculateTotalDistance());
        }
        while(iterationsToMaximaCounter < ITERATIONS_BEFORE_MAXIMA){
            adjacentRoute = obtainAdjacentRoute(new Route(cities, firstCity));
            if(adjacentRoute.calculateTotalDistance() < currentRoute.calculateTotalDistance()){
                int improvement = currentRoute.calculateTotalDistance() - adjacentRoute.calculateTotalDistance();
                iterationsToMaximaCounter = 0;
                currentRoute = new Route(adjacentRoute);
                if(steps){
                    System.out.println("Found better route: " + currentRoute);
                    System.out.println("Route improved by: " + improvement + " KM");
                }
            }else{
                iterationsToMaximaCounter++;
            }
        }
        if(iterationsToMaximaCounter == 1000000) {
            System.out.println("Maximum number of iterations without improvement reached");
            System.out.println("Final route is: " + currentRoute + " " + currentRoute.calculateTotalDistance());
        }
    }


    // randomly pick two indexes of the current route
    public Route obtainAdjacentRoute(Route route) {
        int x1 = 0;
        int x2 = 0;

        while(x1 == x2){
            x1 = (int)Math.floor(((route.getCities().size()-1) * Math.random() + 1));
            x2 = (int)Math.floor(((route.getCities().size()-1) * Math.random() + 1));
        }
        // using chosen indexes get the cities from the route
        City city1 = route.getCities().get(x1);
        City city2 = route.getCities().get(x2);

        // Swap the cities in the route
        route.getCities().set(x2, city1);
        route.getCities().set(x1, city2);

        return route;
    }

    @Override
    public Steps solveAndRecord(ArrayList<City> cities, City firstCity, boolean showSteps) {

        Steps steps = new Steps(); // creating an instance of Steps class to store messages

        Route currentRoute = new Route(cities, firstCity);
        Route adjacentRoute;
        int iterationsToMaximaCounter = 0;

        if(showSteps){
            steps.addStep("Starting route: " + currentRoute + " " + currentRoute.calculateTotalDistance());
        }
        while(iterationsToMaximaCounter < ITERATIONS_BEFORE_MAXIMA){
            adjacentRoute = obtainAdjacentRoute(new Route(cities, firstCity));
            if(adjacentRoute.calculateTotalDistance() < currentRoute.calculateTotalDistance()){
                int improvement = currentRoute.calculateTotalDistance() - adjacentRoute.calculateTotalDistance();
                iterationsToMaximaCounter = 0; // resetting counter after finding better route
                currentRoute = new Route(adjacentRoute);
                if(showSteps){ //adding message about better route found.
                    steps.addStep("Found better route: " + currentRoute);
                    steps.addStep("Route improved by: " + improvement + " KM");
                }
            }else{
                iterationsToMaximaCounter++;
            }
        }
        if(iterationsToMaximaCounter == 1000000) { //if no improvement is found final messages are added.
            steps.addStep("Maximum number of iterations without improvement reached");
            steps.addStep("Final route is: " + currentRoute + " " + currentRoute.calculateTotalDistance());
        }

        // Saving final route, so it can later be put in the model
        this.finalRoute = currentRoute.getCities();

        return steps; // Function now returns object containing recorded steps
    }
}


