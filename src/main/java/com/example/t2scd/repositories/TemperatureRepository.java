package com.example.t2scd.repositories;

import com.example.t2scd.entities.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Integer>, TemperatureRepositoryCustom {
}
