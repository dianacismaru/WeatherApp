package com.example.t2scd.repositories;

import com.example.t2scd.entities.TemperatureEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureRepositoryCustom {
	List<TemperatureEntity> findTemperatures(Double lat, Double lon, LocalDateTime fromDate, LocalDateTime untilDate);
	List<TemperatureEntity> findTemperaturesByCity(Integer idOras, LocalDateTime fromDate, LocalDateTime untilDate);
	List<TemperatureEntity> findTemperaturesByCountry(Integer idTara, LocalDateTime fromDate, LocalDateTime untilDate);
}
