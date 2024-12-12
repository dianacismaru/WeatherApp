package com.example.t2scd.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
		name = "cities",
		uniqueConstraints = @UniqueConstraint(columnNames = {"idTara", "nume"})
)
public class CityEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer idTara;

	@Column(nullable = false)
	private String nume;

	private Double lat;
	private Double lon;
}
