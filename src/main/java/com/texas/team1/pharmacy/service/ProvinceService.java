package com.texas.team1.pharmacy.service;

import com.texas.team1.pharmacy.dto.ProvinceDto;

import java.util.List;


public interface ProvinceService {


    ProvinceDto saveProvince(ProvinceDto provinceDto);

    ProvinceDto updateProvince(ProvinceDto provinceDto);

    ProvinceDto findById(Integer id);

    List<ProvinceDto> findAll();

    void delete(Integer id);
}
