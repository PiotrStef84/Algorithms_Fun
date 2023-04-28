package com.LearningAlgorithms.TSP;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class TSPController {

    ArrayList<City> initialCities = new ArrayList<>(Arrays.asList(
            new City("Boston", 42.3601, -71.0589),
            new City("Houston", 29.7604, -95.3698),
            new City("Austin", 30.2672, -97.7431),
            new City("San Francisco", 37.7749, -122.4194),
            new City("Denver", 39.7392, -104.9903),
            new City("Los Angeles", 34.0522, -118.2437),
            new City("Chicago", 41.8781, -87.6298),
            new City("New York", 40.7128, -74.0059),
            new City("Dallas", 32.7767, -96.7970),
            new City("Seattle", 47.6062, -122.3321)
    ));

    //tsp main page
    @RequestMapping(value="tsp", method = RequestMethod.GET)
    public String goToStartPage() {

        return "TSPMain";
    }


    @RequestMapping("hill")
    public String listHillSteps(ModelMap model) {


        // Array containing Cities to be visited during the tour
        ArrayList<City> initialCitiesClone = (ArrayList<City>) initialCities.clone();

        // Creating an instance of TSPSolver
        TSPSolver HillSolver = new HillClimbing();

        // passing the array of Cities to the solver along with start city and boolean value indicating to show steps
        Steps hillSteps = HillSolver.solveAndRecord(initialCities, initialCitiesClone.get(0), true);
        System.out.println(hillSteps.toString());


        List<String> steps = hillSteps.getSteps();

        // Putting steps and final route to the model, so it can be used in a view
        model.addAttribute("steps", steps);
        model.addAttribute("cities", HillSolver.getFinalRoute());

        // returning the name of the view
        return "stepsNNmap";
    }


    @RequestMapping("nearest")
    public String listNNStepsMap(ModelMap model) {



        ArrayList<City> initialCitiesClone = (ArrayList<City>) initialCities.clone();
        TSPSolver solver = new NearestNeighbor();

        Steps allSteps = solver.solveAndRecord(initialCitiesClone, initialCitiesClone.get(0), true);
        System.out.println(allSteps.toString());

        // Adding steps to the model -> to be displayed in the table
        List<String> steps = allSteps.getSteps();
        model.addAttribute("steps", steps);
        model.addAttribute("cities", solver.getFinalRoute());

        return "stepsNNmap";
    }
}
