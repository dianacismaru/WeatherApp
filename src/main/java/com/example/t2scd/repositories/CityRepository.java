package com.example.t2scd.repositories;

import com.example.t2scd.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
	Optional<CityEntity> findByNume(String nume);
	Optional<CityEntity> findByIdTaraAndNume(Integer idTara, String nume);
	List<CityEntity> findAllByIdTara(Integer idTara);
}
