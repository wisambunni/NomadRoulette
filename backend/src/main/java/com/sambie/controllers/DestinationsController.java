package com.sambie.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.sambie.models.DestinationsRequest;
import com.sambie.models.DestinationsResponse;
import com.sambie.services.FlightService;

@RestController
public class DestinationsController {

    private final FlightService flightService;

    public DestinationsController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/destinations")
    public List<DestinationsResponse> generateDestinations(@RequestBody DestinationsRequest request) {
        System.out.println("Processing trips from:" + request.originAirport());

        List<JsonObject> flights = flightService.getFlightsWithinBudget(
            request.originAirport(),
            request.startDate(),
            request.endDate(),
            request.budget()
        );

        return flights.stream().map(dest -> new DestinationsResponse(
            dest.get("name").getAsString(),
            dest.getAsJsonObject("destination_airport").get("code").getAsString(),
            dest.get("country").getAsString(),
            dest.get("flight_price").getAsInt()
        )).toList();
    }
}
