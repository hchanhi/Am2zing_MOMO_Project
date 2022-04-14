package com.momo.place;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
