package com.example.t2scd.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CityEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private Integer idTara;

	@Column(nullable = false, unique = true)
	private String nume;

	private Double lat;
	private Double lon;
}
