package com.LearningAlgorithms.BinPacking;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BinPackingController {

    //Bin Packing Problem main page
    @RequestMapping(value="bin", method = RequestMethod.GET)
    public String goToBinPackingPage() {

        return "BinMain";
    }

    @RequestMapping(value="FirstFit", method = RequestMethod.GET)
    public String goToFirstFit(ModelMap model) {


        ArrayList<Integer> items2 = new ArrayList<>(Arrays.asList(5, 7, 5, 2, 4, 2, 5, 1, 6));
        int binCapacity = 10;

        System.out.println("****** FIRST FIT **********");
        FirstFit first = new FirstFit(items2, binCapacity);


        Steps firstFit = first.solveAndRecord(true);

        List<String> steps = firstFit.getSteps();
        model.addAttribute("steps", steps);

        return "stepsFirstFit";
    }


}
