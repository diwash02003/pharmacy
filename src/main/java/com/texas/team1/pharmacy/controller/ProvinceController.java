package com.texas.team1.pharmacy.controller;

import com.texas.team1.pharmacy.dto.ProvinceDto;
import com.texas.team1.pharmacy.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Author ProvinceController
 * java-suraj -- 2024-01-26
 */

@Slf4j
@Controller
@RequestMapping("/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping()
    public String openProvincePage(Model model) {
        if (!model.containsAttribute("provinceDto"))
            model.addAttribute("provinceDto", new ProvinceDto());
        model.addAttribute("provinceDtoList", provinceService.findAll());
        return "province/province";
    }

    @PostMapping("/save")
    public String saveProvinceData(@ModelAttribute ProvinceDto provinceDto, RedirectAttributes redirectAttributes) {
        log.info("Requested object to save {} ", provinceDto);
        String message;
        provinceDto = provinceService.saveProvince(provinceDto);
        if (provinceDto != null) {
            message = "province saved successfully";
        } else {
            message = "province save failed";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/province";
    }

    @GetMapping("/id/{id}")
    public String findById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        log.info("THE PATH VARIABLE IS {}", id);
        ProvinceDto provinceDto = provinceService.findById(id);
        redirectAttributes.addFlashAttribute("provinceDto", provinceDto);
        return "redirect:/province";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        log.info("THE PATH VARIABLE IS {}", id);
        provinceService.delete(id);
        String message = "province deleted";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/province";
    }

}
