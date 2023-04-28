package com.LearningAlgorithms.TSP;

import java.util.ArrayList;

public class NearestNeighbor extends TSPSolver {


    private ArrayList<City> finalRoute = new ArrayList<City>();

    public ArrayList<City> getFinalRoute() {
        return finalRoute;
    }

    public void solve(ArrayList<City> cities, City firstCity, boolean showSteps) {
        //boolean showSteps = steps;
        ArrayList<City> shortestRouteCities = new ArrayList<>();
        City nextCity = firstCity;


        // if steps is set to true, application will print a set of messages, starting from first city
        if(showSteps) {

            System.out.println("Starting city is: " + firstCity.getName());
        }

        shortestRouteCities.add(nextCity);
        cities.remove(firstCity);

        while(cities.size() > 0){
            nextCity = getNextCity(cities, nextCity, showSteps);
            shortestRouteCities.add(nextCity);
            // if steps is set to true, the message informing of number of remaining cities is shown
            if(showSteps) {

                System.out.println("There are " + cities.size() + " more cities to visit");
                System.out.println("current route is:" + shortestRouteCities + "\n");
            }

            cities.remove(nextCity);
        }
        Route found = new Route(shortestRouteCities);
        System.out.println(found);
        System.out.println(found.calculateTotalDistance());
    }

    private City getNextCity(ArrayList<City> cities, City currentCity, boolean showSteps){
        double shortestDistance = Double.MAX_VALUE;
        City nextCity = new City();
        for (City city : cities){
            if(shortestDistance > currentCity.measureDistance(city)){
                shortestDistance = currentCity.measureDistance(city);
                nextCity = city;
            }
        }
        if(showSteps){
            System.out.println("The next closest city is " + nextCity.getName() + ": with " + (int) nextCity.measureDistance(currentCity) + " km");
        }
        return nextCity;
    }

    private City getAnotherCity(ArrayList<City> cities, City currentCity, boolean showSteps, Steps steps){
        double shortestDistance = Double.MAX_VALUE;
        City nextCity = new City();
        for (City city : cities){
            if(shortestDistance > currentCity.measureDistance(city)){
                shortestDistance = currentCity.measureDistance(city);
                nextCity = city;
            }
        }
        if(showSteps){
            steps.addStep("The next closest city is " + nextCity.getName() + ": with " + (int) nextCity.measureDistance(currentCity) + " km");
            //System.out.println("The next closest city is " + nextCity.getName() + ": with " + (int) nextCity.measureDistance(currentCity) + " km");
        }
        return nextCity;
    }

    public Steps solveAndRecord(ArrayList<City> cities, City firstCity, boolean showSteps) {

        Steps steps = new Steps();
        ArrayList<City> shortestRouteCities = new ArrayList<>();
        City nextCity = firstCity;


        // if steps is set to true, application will print a set of messages, starting from first city
        if(showSteps) {

            steps.addStep("Starting city is: " + firstCity.getName());
            //System.out.println("Starting city is: " + firstCity.getName());
        }

        shortestRouteCities.add(nextCity);
        cities.remove(firstCity);

        while(cities.size() > 0){
            //nextCity = getNextCity(cities, nextCity, showSteps);  // Make sure that extra steps from GetAnotherCity are added
            nextCity = getAnotherCity(cities, nextCity, showSteps, steps);

            shortestRouteCities.add(nextCity);
            // if steps is set to true, the message informing of number of remaining cities is shown
            if(showSteps) {
                steps.addStep("There are " + cities.size() + " more cities to visit");
                steps.addStep("current route is:" + shortestRouteCities + "\n");

                //System.out.println("There are " + cities.size() + " more cities to visit");
                //System.out.println("current route is:" + shortestRouteCities + "\n");
            }

            cities.remove(nextCity);
        }
        Route found = new Route(shortestRouteCities);
        System.out.println(found);
        System.out.println(found.calculateTotalDistance());

        this.finalRoute = found.getCities();

        if(showSteps) {
            steps.addStep(found.toString());
            steps.addStep("Total distance: "+ found.calculateTotalDistance());
        }

        return steps;
    }


}
