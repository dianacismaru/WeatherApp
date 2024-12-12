package com.example.t2scd.rest;

import com.example.t2scd.application.usecases.CityService;
import com.example.t2scd.entities.CityEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cities")
public class CityController {

	private final CityService cityService;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@PostMapping
	public ResponseEntity<?> createCountry(@RequestBody CityEntity cityEntity) {
		try {
			CityEntity createdCityEntity = cityService.saveCountry(cityEntity);

			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", createdCityEntity.getId()));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<CityEntity>> getAllCountries() {
		List<CityEntity> cities = cityService.getAllCountries();
		return ResponseEntity.ok(cities);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCountry(@PathVariable("id") int id, @RequestBody CityEntity cityEntity) {
		try {
			if (cityEntity.getId() != id) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			cityService.updateCountry(id, cityEntity);
			return ResponseEntity.ok("Country updated successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCountry(@PathVariable("id") int id) {
		try {
			cityService.deleteCountry(id);
			return ResponseEntity.ok("Country deleted successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
