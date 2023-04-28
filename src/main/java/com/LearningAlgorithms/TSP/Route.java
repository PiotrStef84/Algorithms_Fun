package com.LearningAlgorithms.TSP;

import java.util.ArrayList;
import java.util.Arrays;


public class Route {

    private ArrayList<City> cities = new ArrayList<City>();

    public Route(ArrayList<City> cities){
        this.cities.addAll(cities);
    }

    public Route(ArrayList<City> cities, City firstCity){
        this.cities.add(firstCity);
        cities.remove(firstCity);
        this.cities.addAll(cities);
    }

    public Route(Route route){
        route.cities.stream().forEach( x -> cities.add(x));
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public int calculateTotalDistance(){
        int citiesSize = this.getCities().size();
        return (int) (this.getCities().stream().mapToDouble(x-> {
            int cityIndex = this.getCities().indexOf(x);
            double returnValue = 0;
            if (cityIndex < citiesSize -1 ){
                returnValue = x.measureDistance(this.getCities().get(cityIndex +1 ));
            }
            return returnValue;
        }).sum() +this.getCities().get(citiesSize - 1).measureDistance(this.getCities().get(0)));
    }

    @Override
    public String toString() {
        return Arrays.toString(cities.toArray());
    }
}
