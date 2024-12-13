package com.example.t2scd.rest;

import com.example.t2scd.application.dto.TemperatureDTO;
import com.example.t2scd.application.usecases.TemperatureService;
import com.example.t2scd.entities.TemperatureEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/temperatures")
public class TemperatureController {

	private final TemperatureService temperatureService;

	public TemperatureController(TemperatureService temperatureService) {
		this.temperatureService = temperatureService;
	}

	@PostMapping
	public ResponseEntity<?> createTemperature(@RequestBody TemperatureEntity temperatureEntity) {
		TemperatureEntity createdTemperatureEntity = temperatureService.saveTemperature(temperatureEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", createdTemperatureEntity.getId()));
	}

	@GetMapping
	public ResponseEntity<List<TemperatureDTO>> getTemperatures(
			@RequestParam(required = false) Double lat,
			@RequestParam(required = false) Double lon,
			@RequestParam(required = false) String from,
			@RequestParam(required = false) String until) {

		List<TemperatureDTO> temperatures = temperatureService.getTemperatures(lat, lon, from, until);
		return ResponseEntity.ok(temperatures);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTemperature(@PathVariable("id") int id, @RequestBody TemperatureEntity temperatureEntity) {
		if (temperatureEntity.getId() != id) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID in path and body must match");
		}
		temperatureService.updateTemperature(id, temperatureEntity);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTemperature(@PathVariable("id") int id) {
		temperatureService.deleteTemperature(id);
		return ResponseEntity.ok().build();
	}
}
