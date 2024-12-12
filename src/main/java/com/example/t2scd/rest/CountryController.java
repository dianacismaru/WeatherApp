package com.example.t2scd.rest;

import com.example.t2scd.application.usecases.CountryService;
import com.example.t2scd.entities.CountryEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

	private final CountryService countryService;

	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	@PostMapping
	public ResponseEntity<?> createCountry(@RequestBody CountryEntity countryEntity) {
		CountryEntity createdCountryEntity = countryService.saveCountry(countryEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", createdCountryEntity.getId()));
	}

	@GetMapping
	public ResponseEntity<List<CountryEntity>> getAllCountries() {
		List<CountryEntity> countries = countryService.getAllCountries();
		return ResponseEntity.ok(countries);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCountry(@PathVariable("id") int id, @RequestBody CountryEntity countryEntity) {
		if (countryEntity.getId() != id) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID in path and body must match");
		}
		countryService.updateCountry(id, countryEntity);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCountry(@PathVariable("id") int id) {
		countryService.deleteCountry(id);
		return ResponseEntity.ok().build();
	}
}
