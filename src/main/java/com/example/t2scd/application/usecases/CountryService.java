package com.example.t2scd.application.usecases;

import com.example.t2scd.entities.CountryEntity;
import com.example.t2scd.repositories.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {

	private final CountryRepository countryRepository;

	public CountryEntity saveCountry(CountryEntity countryEntity) {
		if (countryEntity.getNume() == null || countryEntity.getLat() == null || countryEntity.getLon() == null) {
			throw new IllegalArgumentException("All fields must be provided");
		}
		Optional<CountryEntity> existingCountry = countryRepository.findByNume(countryEntity.getNume());
		if (existingCountry.isPresent()) {
			throw new IllegalArgumentException("Country with name " + countryEntity.getNume() + " already exists");
		}
		return countryRepository.save(countryEntity);
	}


	public List<CountryEntity> getAllCountries() {
		return countryRepository.findAll();
	}

	public void updateCountry(int id, CountryEntity updatedCountry) {
		Optional<CountryEntity> existingCountryOpt = countryRepository.findById(id);
		if (existingCountryOpt.isPresent()) {
			CountryEntity existingCountry = existingCountryOpt.get();
			existingCountry.setNume(updatedCountry.getNume());
			existingCountry.setLat(updatedCountry.getLat());
			existingCountry.setLon(updatedCountry.getLon());
			countryRepository.save(existingCountry);
		} else {
			throw new IllegalArgumentException("Country with ID " + id + " not found.");
		}
	}

	public void deleteCountry(int id) {
		if (countryRepository.existsById(id)) {
			countryRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Country with ID " + id + " not found.");
		}
	}
}

