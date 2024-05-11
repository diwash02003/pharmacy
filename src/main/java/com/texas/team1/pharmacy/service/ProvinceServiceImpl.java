package com.texas.team1.pharmacy.service;

import com.texas.team1.pharmacy.dto.ProvinceDto;
import com.texas.team1.pharmacy.entity.Province;
import com.texas.team1.pharmacy.repo.ProvinceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepo provinceRepo;

    public ProvinceServiceImpl(ProvinceRepo provinceRepo) {
        this.provinceRepo = provinceRepo;
    }

    @Override
    public ProvinceDto saveProvince(ProvinceDto provinceDto) {
        Province province = new Province();
        province.setId(provinceDto.getId());
        province.setName(provinceDto.getName());
        try {
            province = provinceRepo.save(province);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return new ProvinceDto(province.getId(), province.getName());
    }

    @Override
    public ProvinceDto updateProvince(ProvinceDto provinceDto) {
        return null;
    }

    @Override
    public ProvinceDto findById(Integer id) {
        Optional<Province> optionalProvince = provinceRepo.findById(id);
        if (optionalProvince.isPresent()) {
            return new ProvinceDto(optionalProvince.get().getId(), optionalProvince.get().getName());
        }
        return null;
    }

    @Override
    public List<ProvinceDto> findAll() {
        List<Province> provinceList = provinceRepo.findAll();
        List<ProvinceDto> provinceDtoList = new ArrayList<>();
        for (Province province : provinceList) {
            provinceDtoList.add(new ProvinceDto(province.getId(), province.getName()));
        }
        return provinceDtoList;
    }

    @Override
    public void delete(Integer id) {
        provinceRepo.deleteById(id);
    }
}
