package com.alperen.flightplanner.repository;

import com.alperen.flightplanner.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByDepartureCity(String departureCity);


    @Query("SELECT f FROM Flight f " +
            "WHERE (:departureCity IS NULL OR f.departureCity = :departureCity) " +
            "AND (:arrivalCity IS NULL OR f.arrivalCity = :arrivalCity) " +
            "AND (:date IS NULL OR DATE(f.departureTime) = :date)")
    Page<Flight> findFlights(
            @Param("departureCity") String departureCity,
            @Param("arrivalCity") String arrivalCity,
            @Param("date") LocalDate date,
            Pageable pageable);


}