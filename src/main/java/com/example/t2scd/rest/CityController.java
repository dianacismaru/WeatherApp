package com.example.t2scd.rest;

import com.example.t2scd.application.usecases.CityService;
import com.example.t2scd.entities.CityEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cities")
public class CityController {

	private final CityService cityService;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@PostMapping
	public ResponseEntity<?> createCity(@RequestBody CityEntity cityEntity) {
		int cityId = cityService.saveCity(cityEntity).getId();
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", cityId));
	}

	@GetMapping
	public ResponseEntity<?> getAllCities() {
		return ResponseEntity.ok(cityService.getAllCities());
	}

	@GetMapping("/country/{idTara}")
	public ResponseEntity<?> getCitiesByCountry(@PathVariable("idTara") int idTara) {
		return ResponseEntity.ok(cityService.getCitiesByCountry(idTara));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCity(@PathVariable("id") int id, @RequestBody CityEntity cityEntity) {
		cityService.updateCity(id, cityEntity);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable("id") int id) {
		cityService.deleteCity(id);
		return ResponseEntity.ok().build();
	}
}
