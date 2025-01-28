package com.alperen.flightplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Flight {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "DepartureCity cannot be null!")
    private String departureCity;

    @NotBlank(message = "Arrival cannot be null!")
    private String arrivalCity;

    @NotNull(message = "Departure time must be specified.")
    @Future(message = "The departure time must be some time in the future.")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time must be specified.")
    @Future(message = "The arrival time must be some time in the future.")
    private LocalDateTime arrivalTime;

}
