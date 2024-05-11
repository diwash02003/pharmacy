package com.texas.team1.pharmacy.utility;

import com.texas.team1.pharmacy.dto.LocationDto;
import com.texas.team1.pharmacy.entity.District;
import com.texas.team1.pharmacy.entity.LocalBodies;
import com.texas.team1.pharmacy.entity.Province;
import com.texas.team1.pharmacy.repo.DistrictRepo;
import com.texas.team1.pharmacy.repo.LocalBodyRepo;
import com.texas.team1.pharmacy.repo.ProvinceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LocationDataProcessor {

    private final ProvinceRepo provinceRepo;
    private  final DistrictRepo districtRepo;
    private  final LocalBodyRepo localBodyRepo;

    public LocationDataProcessor(ProvinceRepo provinceRepo, DistrictRepo districtRepo, LocalBodyRepo localBodyRepo) {
        this.provinceRepo = provinceRepo;
        this.districtRepo = districtRepo;
        this.localBodyRepo = localBodyRepo;
    }

    @Deprecated
    public void processLocationDto(List<LocationDto> locationDtoList){
        List<LocationDto> provinceLocationList = locationDtoList.stream().
                filter(locationDto -> locationDto.getParentId()==0)
                .collect(Collectors.toList());
        //insert the province list to database
       List<Province> provinceList= convertAndSaveLocationListToProvinceList(provinceLocationList);
       for(Province province: provinceList){
           log.info("WORKING FOR {}",province.getName());
           List<LocationDto> districtLocationList =locationDtoList.stream().
                   filter(locationDto -> locationDto.getParentId() == province.getCode())
                   .collect(Collectors.toList());
           List<District> districtList=convertAndSaveLocationListToDistrictList(districtLocationList, province);
           for(District district: districtList){
               List<LocationDto> localbodyLocationList =locationDtoList.stream().
                       filter(locationDto -> locationDto.getParentId() == district.getCode())
                       .collect(Collectors.toList());
               convertAndSaveLocationListToLocalBodyList(localbodyLocationList,district);
           }
       }
    }


    private List<Province> convertAndSaveLocationListToProvinceList(List<LocationDto> provinceLocationList){
        List<Province> provinceList = new ArrayList<>();
        for(LocationDto locationDto: provinceLocationList){
            provinceList.add(new Province(null,locationDto.getName(),locationDto.getNameNepali(), locationDto.getCode()));
        }
        provinceList=provinceRepo.saveAll(provinceList);
        return  provinceList;
    }

    private List<District>  convertAndSaveLocationListToDistrictList(List<LocationDto> districtLocationList, Province province){
        List<District> districtList = new ArrayList<>();
        for(LocationDto locationDto: districtLocationList){
            districtList.add(new District(null,locationDto.getName(),locationDto.getNameNepali(), locationDto.getCode(),province));
        }
        districtList=districtRepo.saveAll(districtList);
        return  districtList;
    }

    private  void convertAndSaveLocationListToLocalBodyList(List<LocationDto> localBodyLocationList, District district){
        List<LocalBodies> localBodiesList = new ArrayList<>();
        for(LocationDto locationDto: localBodyLocationList){
            localBodiesList.add(new LocalBodies(null,locationDto.getName(),locationDto.getNameNepali(), locationDto.getCode(),district));
        }
        localBodyRepo.saveAll(localBodiesList);
    }
}
