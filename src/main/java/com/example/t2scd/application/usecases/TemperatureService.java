package com.example.t2scd.application.usecases;

import com.example.t2scd.application.dto.TemperatureDTO;
import com.example.t2scd.entities.TemperatureEntity;
import com.example.t2scd.repositories.CityRepository;
import com.example.t2scd.repositories.CountryRepository;
import com.example.t2scd.repositories.TemperatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TemperatureService {
	private final TemperatureRepository temperatureRepository;
	private final CityRepository cityRepository;
	private final CountryRepository countryRepository;

	public TemperatureEntity saveTemperature(TemperatureEntity temperatureEntity) {
		if (temperatureEntity.getValoare() == null || temperatureEntity.getIdOras() == null) {
			throw new IllegalArgumentException("All fields must be provided");
		}

		boolean cityExists = cityRepository.existsById(temperatureEntity.getIdOras());
		if (!cityExists) {
			throw new NoSuchElementException("City with ID " + temperatureEntity.getIdOras() + " does not exist");
		}

		return temperatureRepository.save(temperatureEntity);
	}
	
	public void updateTemperature(int id, TemperatureEntity updatedTemperature) {
		Optional<TemperatureEntity> existingTemperatureOpt = temperatureRepository.findById(id);
		if (existingTemperatureOpt.isPresent()) {
			TemperatureEntity existingTemperature = existingTemperatureOpt.get();
			existingTemperature.setValoare(updatedTemperature.getValoare());
			existingTemperature.setIdOras(updatedTemperature.getIdOras());
			temperatureRepository.save(existingTemperature);
		} else {
			throw new NoSuchElementException("Temperature with ID " + id + " not found.");
		}
	}

	public void deleteTemperature(int id) {
		if (temperatureRepository.existsById(id)) {
			temperatureRepository.deleteById(id);
		} else {
			throw new NoSuchElementException("Temperature with ID " + id + " not found.");
		}
	}

	public List<TemperatureDTO> getTemperatures(Double lat, Double lon, String from, String until) {
		LocalDateTime fromDate = null;
		LocalDateTime untilDate = null;

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if (from != null) {
				fromDate = LocalDate.parse(from, formatter).atStartOfDay();
			}
			if (until != null) {
				untilDate = LocalDate.parse(until, formatter).atTime(23, 59, 59);
			}
		} catch (DateTimeParseException e) {
			System.out.println(e.getMessage());
			return List.of();
		}

		List<TemperatureEntity> results = temperatureRepository.findTemperatures(lat, lon, fromDate, untilDate);
		return results.stream()
				.map(entity -> new TemperatureDTO(entity.getId(), entity.getValoare(), entity.getTimestamp()))
				.collect(Collectors.toList());
	}

	public List<TemperatureDTO> getTemperaturesByCity(Integer idOras, String from, String until) {
		LocalDateTime fromDate = null;
		LocalDateTime untilDate = null;

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if (from != null) {
				fromDate = LocalDate.parse(from, formatter).atStartOfDay();
			}
			if (until != null) {
				untilDate = LocalDate.parse(until, formatter).atTime(23, 59, 59);
			}
		} catch (DateTimeParseException e) {
			System.out.println(e.getMessage());
			return List.of();
		}

		if (!cityRepository.existsById(idOras)) {
			throw new NoSuchElementException("City with ID " + idOras + " does not exist");
		}

		List<TemperatureEntity> results = temperatureRepository.findTemperaturesByCity(idOras, fromDate, untilDate);
		return results.stream()
				.map(entity -> new TemperatureDTO(entity.getId(), entity.getValoare(), entity.getTimestamp()))
				.collect(Collectors.toList());
	}

	public List<TemperatureDTO> getTemperaturesByCountry(Integer idTara, String from, String until) {
		LocalDateTime fromDate = null;
		LocalDateTime untilDate = null;

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if (from != null) {
				fromDate = LocalDate.parse(from, formatter).atStartOfDay();
			}
			if (until != null) {
				untilDate = LocalDate.parse(until, formatter).atTime(23, 59, 59);
			}
		} catch (DateTimeParseException e) {
			System.out.println(e.getMessage());
			return List.of();
		}

		if (!countryRepository.existsById(idTara)) {
			throw new NoSuchElementException("Country with ID " + idTara + " does not exist");
		}

		List<TemperatureEntity> temperatures = temperatureRepository.findTemperaturesByCountry(idTara, fromDate, untilDate);

		return temperatures.stream()
				.map(entity -> new TemperatureDTO(entity.getId(), entity.getValoare(), entity.getTimestamp()))
				.collect(Collectors.toList());
	}

}

