package com.example.t2scd.repositories;

import com.example.t2scd.entities.CityEntity;
import com.example.t2scd.entities.TemperatureEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TemperatureRepositoryCustomImpl implements TemperatureRepositoryCustom {

	private final EntityManager entityManager;

	public TemperatureRepositoryCustomImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<TemperatureEntity> findTemperatures(Double lat, Double lon, LocalDateTime fromDate,
													LocalDateTime untilDate) {
		return findTemperaturesInternal(lat, lon, null, null, fromDate, untilDate);
	}

	@Override
	public List<TemperatureEntity> findTemperaturesByCity(Integer idOras, LocalDateTime fromDate,
														  LocalDateTime untilDate) {
		return findTemperaturesInternal(null, null, idOras, null, fromDate, untilDate);
	}

	@Override
	public List<TemperatureEntity> findTemperaturesByCountry(Integer idTara, LocalDateTime fromDate,
															 LocalDateTime untilDate) {
		return findTemperaturesInternal(null, null, null, idTara, fromDate, untilDate);
	}

	private List<TemperatureEntity> findTemperaturesInternal(Double lat, Double lon, Integer idOras, Integer idTara,
															 LocalDateTime fromDate, LocalDateTime untilDate) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TemperatureEntity> query = cb.createQuery(TemperatureEntity.class);
		Root<TemperatureEntity> temperature = query.from(TemperatureEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		if (idOras != null) {
			predicates.add(cb.equal(temperature.get("idOras"), idOras));
		}

		if (idTara != null) {
			Subquery<Integer> citySubquery = query.subquery(Integer.class);
			Root<CityEntity> city = citySubquery.from(CityEntity.class);
			citySubquery.select(city.get("id"))
					.where(cb.equal(city.get("idTara"), idTara));
			predicates.add(temperature.get("idOras").in(citySubquery));
		}

		if (lat != null || lon != null) {
			Subquery<Integer> citySubquery = query.subquery(Integer.class);
			Root<CityEntity> city = citySubquery.from(CityEntity.class);
			List<Predicate> cityPredicates = new ArrayList<>();

			if (lat != null) {
				cityPredicates.add(cb.equal(city.get("lat"), lat));
			}
			if (lon != null) {
				cityPredicates.add(cb.equal(city.get("lon"), lon));
			}

			citySubquery.select(city.get("id")).where(cityPredicates.toArray(new Predicate[0]));
			predicates.add(temperature.get("idOras").in(citySubquery));
		}

		if (fromDate != null) {
			predicates.add(cb.greaterThanOrEqualTo(temperature.get("timestamp"), fromDate));
		}
		if (untilDate != null) {
			predicates.add(cb.lessThanOrEqualTo(temperature.get("timestamp"), untilDate));
		}

		query.where(predicates.toArray(new Predicate[0]));

		TypedQuery<TemperatureEntity> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}
}
