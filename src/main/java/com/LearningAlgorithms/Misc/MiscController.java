package com.LearningAlgorithms.Misc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MiscController {

    @RequestMapping(value="dev", method = RequestMethod.GET)
    public String goToForDevelopersPage() {

        return "developers";
    }

    @RequestMapping(value="about", method = RequestMethod.GET)
    public String goToAboutPage() {

        return "developers";
    }
}
