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
	public ResponseEntity<?> createCity(@RequestBody CityEntity cityEntity) {
		CityEntity createdCityEntity = cityService.saveCity(cityEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", createdCityEntity.getId()));
	}

	@GetMapping
	public ResponseEntity<List<CityEntity>> getAllCities() {
		List<CityEntity> cities = cityService.getAllCities();
		return ResponseEntity.ok(cities);
	}

	@GetMapping("/country/{idTara}")
	public ResponseEntity<List<CityEntity>> getCitiesByCountry(@PathVariable("idTara") int idTara) {
		List<CityEntity> cities = cityService.getCitiesByCountry(idTara);
		return ResponseEntity.ok(cities);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCity(@PathVariable("id") int id, @RequestBody CityEntity cityEntity) {
		if (cityEntity.getId() != id) {
			return ResponseEntity.badRequest().build();
		}
		cityService.updateCity(id, cityEntity);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable("id") int id) {
		cityService.deleteCity(id);
		return ResponseEntity.ok().build();
	}
}
