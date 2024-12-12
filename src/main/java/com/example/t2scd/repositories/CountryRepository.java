package com.example.t2scd.repositories;

import com.example.t2scd.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
	Optional<CountryEntity> findByNume(String nume);
}
