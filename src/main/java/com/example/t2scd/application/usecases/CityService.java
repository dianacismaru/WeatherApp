package com.example.t2scd.application.usecases;

import com.example.t2scd.entities.CityEntity;
import com.example.t2scd.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {

	private final CityRepository cityRepository;
	
	public CityEntity saveCountry(CityEntity countryEntity) {
		if (countryEntity.getNume() == null || countryEntity.getLat() == null || countryEntity.getLon() == null) {
			throw new IllegalArgumentException("All fields must be provided");
		}
		Optional<CityEntity> existingCountry = cityRepository.findByNume(countryEntity.getNume());
		if (existingCountry.isPresent()) {
			throw new IllegalArgumentException("Country with name " + countryEntity.getNume() + " already exists");
		}
		return cityRepository.save(countryEntity);
	}


	public List<CityEntity> getAllCountries() {
		return cityRepository.findAll();
	}

	public void updateCountry(int id, CityEntity updatedCountry) {
		Optional<CityEntity> existingCountryOpt = cityRepository.findById(id);
		if (existingCountryOpt.isPresent()) {
			CityEntity existingCountry = existingCountryOpt.get();
			existingCountry.setNume(updatedCountry.getNume());
			existingCountry.setLat(updatedCountry.getLat());
			existingCountry.setLon(updatedCountry.getLon());
			cityRepository.save(existingCountry);
		} else {
			throw new IllegalArgumentException("Country with ID " + id + " not found.");
		}
	}

	public void deleteCountry(int id) {
		if (cityRepository.existsById(id)) {
			cityRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Country with ID " + id + " not found.");
		}
	}
}

