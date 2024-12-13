package com.example.t2scd.repositories;

import com.example.t2scd.entities.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureRepositoryCustom {
	List<TemperatureEntity> findTemperatures(Double lat, Double lon, LocalDateTime fromDate, LocalDateTime untilDate);
}
