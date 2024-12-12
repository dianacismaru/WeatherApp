package com.example.t2scd.application.usecases;

import com.example.t2scd.entities.CityEntity;
import com.example.t2scd.repositories.CityRepository;
import com.example.t2scd.repositories.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {

	private final CityRepository cityRepository;
	private final CountryRepository countryRepository;
	
	public CityEntity saveCity(CityEntity cityEntity) {
		if (cityEntity.getIdTara() == null || cityEntity.getNume() == null
				|| cityEntity.getLat() == null || cityEntity.getLon() == null) {
			throw new IllegalArgumentException("All fields must be provided");
		}

		boolean countryExists = countryRepository.existsById(cityEntity.getIdTara());
		if (!countryExists) {
			throw new NoSuchElementException("Country with ID " + cityEntity.getIdTara() + " does not exist");
		}

		Optional<CityEntity> existingCity = cityRepository.findByIdTaraAndNume(cityEntity.getIdTara(), cityEntity.getNume());
		if (existingCity.isPresent()) {
			throw new RuntimeException("City with name " + cityEntity.getNume() + " already exists in this country");
		}

		return cityRepository.save(cityEntity);
	}


	public List<CityEntity> getAllCities() {
		return cityRepository.findAll();
	}

	public void updateCity(int id, CityEntity updatedCity) {
		Optional<CityEntity> existingCityOpt = cityRepository.findById(id);
		if (existingCityOpt.isPresent()) {
			CityEntity existingCity = existingCityOpt.get();
			existingCity.setNume(updatedCity.getNume());
			existingCity.setLat(updatedCity.getLat());
			existingCity.setLon(updatedCity.getLon());
			cityRepository.save(existingCity);
		} else {
			throw new NoSuchElementException("City with ID " + id + " not found.");
		}
	}

	public void deleteCity(int id) {
		if (cityRepository.existsById(id)) {
			cityRepository.deleteById(id);
		} else {
			throw new NoSuchElementException("City with ID " + id + " not found.");
		}
	}

	public List<CityEntity> getCitiesByCountry(int idTara) {
		return cityRepository.findAllByIdTara(idTara);
	}
}

