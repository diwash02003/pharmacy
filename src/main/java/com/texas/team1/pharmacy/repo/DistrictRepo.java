package com.texas.team1.pharmacy.repo;

import com.texas.team1.pharmacy.entity.District;
import com.texas.team1.pharmacy.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepo extends JpaRepository<District, Integer> {
}
