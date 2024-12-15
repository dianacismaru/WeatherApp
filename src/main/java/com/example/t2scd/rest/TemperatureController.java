package com.example.t2scd.rest;

import com.example.t2scd.application.usecases.TemperatureService;
import com.example.t2scd.entities.TemperatureEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		int temperatureId = temperatureService.saveTemperature(temperatureEntity).getId();
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", temperatureId));
	}

	@GetMapping
	public ResponseEntity<?> getTemperatures(
			@RequestParam(required = false) Double lat,
			@RequestParam(required = false) Double lon,
			@RequestParam(required = false) String from,
			@RequestParam(required = false) String until) {

		return ResponseEntity.ok(temperatureService.getTemperatures(lat, lon, from, until));
	}

	@GetMapping("/cities/{idOras}")
	public ResponseEntity<?> getTemperaturesByCity(
			@PathVariable Integer idOras,
			@RequestParam(required = false) String from,
			@RequestParam(required = false) String until) {

		return ResponseEntity.ok(temperatureService.getTemperaturesByCity(idOras, from, until));
	}

	@GetMapping("/countries/{idTara}")
	public ResponseEntity<?> getTemperaturesByCountry(
			@PathVariable Integer idTara,
			@RequestParam(required = false) String from,
			@RequestParam(required = false) String until) {

		return ResponseEntity.ok(temperatureService.getTemperaturesByCountry(idTara, from, until));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTemperature(@PathVariable("id") int id, 
											   @RequestBody TemperatureEntity temperatureEntity) {
		temperatureService.updateTemperature(id, temperatureEntity);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTemperature(@PathVariable("id") int id) {
		temperatureService.deleteTemperature(id);
		return ResponseEntity.ok().build();
	}
}
