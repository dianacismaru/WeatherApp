package com.example.t2scd.entities;

import jakarta.persistence.*;

@Entity
public class CountryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nume", nullable = false, unique = true)
	private String nume;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "lon")
	private Double lon;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String name) {
		this.nume = name;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double latitude) {
		this.lat = latitude;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double longitude) {
		this.lon = longitude;
	}
}
