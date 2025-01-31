package com.alperen.flightplanner.controller;

import com.alperen.flightplanner.model.Flight;
import com.alperen.flightplanner.service.FlightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        try {
            Flight createdFlight = flightService.addFlight(flight);
            return ResponseEntity.ok(createdFlight);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Flight>> getFlights(
            @RequestParam(required = false) String departureCity,
            @RequestParam(required = false) String arrivalCity,
            @RequestParam(required = false) String date,
            Pageable pageable) {
        Page<Flight> flights = flightService.getFlights(departureCity, arrivalCity, date, pageable);
        return ResponseEntity.ok(flights);
    }


}
