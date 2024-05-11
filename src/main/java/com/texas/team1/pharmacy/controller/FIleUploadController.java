package com.texas.team1.pharmacy.controller;

import com.texas.team1.pharmacy.dto.LocationDto;
import com.texas.team1.pharmacy.utility.LocationDataProcessor;
import com.texas.team1.pharmacy.utility.PoijiUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Controller
@RequestMapping("/upload")
public class FIleUploadController {



    private final PoijiUtils poijiUtils;
    private  final LocationDataProcessor locationDataProcessor;

    public FIleUploadController(PoijiUtils poijiUtils, LocationDataProcessor locationDataProcessor) {
        this.poijiUtils = poijiUtils;
        this.locationDataProcessor = locationDataProcessor;
    }

    @PostMapping("/excel")
    public String uploadExcel(@ModelAttribute FileDto fileDto) throws IOException {
       // System.out.println("hlooo");
        List<LocationDto> locationDtoList = poijiUtils.readData(fileDto.getMultipartFile());
        locationDataProcessor.processLocationDto(locationDtoList);
        locationDtoList.forEach(System.out :: println);
        return "redirect:/";
    }

}