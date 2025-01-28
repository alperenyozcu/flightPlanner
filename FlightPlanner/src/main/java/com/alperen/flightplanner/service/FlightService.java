package com.alperen.flightplanner.service;


import com.alperen.flightplanner.model.Flight;
import com.alperen.flightplanner.repository.FlightRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight addFlight(Flight flight) {
        if (isFlightConflict(flight)) {
            throw new RuntimeException("Flight time conflicts!");
        }
        return flightRepository.save(flight);
    }

    private boolean isFlightConflict(Flight flight) {
        List<Flight> flights = flightRepository.findAllByDepartureCity(flight.getDepartureCity());
        for (Flight existingFlight : flights) {
            if (isConflict(flight, existingFlight)) {
                return true;
            }
        }
        return false;
    }

    private boolean isConflict(Flight newFlight, Flight existingFlight) {
        LocalDateTime newDeparture = newFlight.getDepartureTime();
        LocalDateTime newArrival = newFlight.getArrivalTime();
        LocalDateTime existingDeparture = existingFlight.getDepartureTime();
        LocalDateTime existingArrival = existingFlight.getArrivalTime();
        return (newDeparture.isBefore(existingArrival.plusMinutes(30)) && newArrival.isAfter(existingDeparture.minusMinutes(30)));
    }

    public Page<Flight> getFlights(String departureCity, String arrivalCity, String date, Pageable pageable) {
        LocalDate localDate = null;
        if (date != null && !date.isEmpty()) {
            try {
                localDate = LocalDate.parse(date);
            } catch (Exception e) {
                throw new RuntimeException("Invalid Date Format");
            }
        }
        return flightRepository.findFlights(departureCity, arrivalCity, localDate, pageable);
    }

}

