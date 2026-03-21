package com.sambie.controllers;

import com.sambie.models.ItineraryResponse;
import com.sambie.models.ItineraryRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateItineraryController {

  @PostMapping("/itineraries")
  public ItineraryResponse generateSurpriseItinerary(@RequestBody ItineraryRequest request) {
    System.out.println("Processing trip from: " + request.originAirport());

    //TODO: The actual api calls will go here
    return new ItineraryResponse(
      "Tokyo, Japan",
      "Delta Flight 123",
      "Capsule Hotel Shinjuku",
      request.budget() - 100
    );
  }
}