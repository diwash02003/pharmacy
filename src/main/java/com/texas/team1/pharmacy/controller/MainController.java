package com.texas.team1.pharmacy.controller;

import com.texas.team1.pharmacy.utility.PoijiUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class MainController {

    private final PoijiUtils poijiUtils;

    public MainController(PoijiUtils poijiUtils) {
        this.poijiUtils = poijiUtils;
    }

    @GetMapping
    public String openLandingPage(Model model) {
        model.addAttribute("fileDto", new FileDto());
        return "landingpage";
    }
}
