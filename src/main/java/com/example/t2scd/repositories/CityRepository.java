package com.example.t2scd.repositories;

import com.example.t2scd.entities.CityEntity;
import com.example.t2scd.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
	Optional<CityEntity> findByNume(String nume);
}
