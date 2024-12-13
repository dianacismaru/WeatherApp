package com.example.t2scd.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
		name = "temperatures",
		uniqueConstraints = @UniqueConstraint(columnNames = {"idOras", "timestamp"})
)
public class TemperatureEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer idOras;

	@Column(nullable = false)
	private Double valoare;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDateTime timestamp;

	@PrePersist
	public void generateTimestamp() {
		this.timestamp = LocalDateTime.now();
	}
}
