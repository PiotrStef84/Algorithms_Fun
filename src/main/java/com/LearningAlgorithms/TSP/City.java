package com.LearningAlgorithms.TSP;

public class City {


    private static final double EARTH_EQUATORIAL_RADIUS = 6378.1370D;
    private static final double CONVERT_DEGREES_TO_RADIANS = Math.PI/180D;
    private static final double CONVERT_KM_TO_MILES = 0.621371;



    private double longitude;
    private double latitude;
    private String name;

    public City(){}

    public City(String name, double latitude, double longitude) {
//        this.longitude = longitude * CONVERT_DEGREES_TO_RADIANS;
//        this.latitude = latitude * CONVERT_DEGREES_TO_RADIANS;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public double measureDistance(City city){
        double deltaLongitude = (city.getLongitude()* CONVERT_DEGREES_TO_RADIANS) - (this.getLongitude()* CONVERT_DEGREES_TO_RADIANS);
        double deltaLatitude = (city.getLatitude()*CONVERT_DEGREES_TO_RADIANS) - (this.getLatitude()* CONVERT_DEGREES_TO_RADIANS);
        double a = Math.pow(Math.sin(deltaLatitude / 2D), 2D) +
                Math.cos(this.getLatitude()* CONVERT_DEGREES_TO_RADIANS) * Math.cos(city.getLatitude() * CONVERT_DEGREES_TO_RADIANS) * Math.pow(Math.sin(deltaLongitude / 2D), 2D);
        return CONVERT_KM_TO_MILES * EARTH_EQUATORIAL_RADIUS * 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));

    }
}
