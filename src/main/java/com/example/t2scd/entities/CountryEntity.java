package com.example.t2scd.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CountryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String nume;

	private Double lat;

	private Double lon;
}
