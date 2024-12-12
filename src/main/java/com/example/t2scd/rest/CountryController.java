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
		try {
			CountryEntity createdCountryEntity = countryService.saveCountry(countryEntity);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", createdCountryEntity.getId()));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Country could not be created"));
		}
	}

	@GetMapping
	public ResponseEntity<List<CountryEntity>> getAllCountries() {
		List<CountryEntity> countries = countryService.getAllCountries();
		return ResponseEntity.ok(countries);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCountry(@PathVariable("id") int id, @RequestBody CountryEntity countryEntity) {
		try {
			if (countryEntity.getId() != id) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID in path and body must match");
			}
			countryService.updateCountry(id, countryEntity);
			return ResponseEntity.ok("Country updated successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid country data");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country not found");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCountry(@PathVariable("id") int id) {
		try {
			countryService.deleteCountry(id);
			return ResponseEntity.ok("Country deleted successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID provided");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country not found");
		}
	}
}
