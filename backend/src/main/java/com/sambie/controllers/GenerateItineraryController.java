package com.sambie.controllers;

import com.sambie.models.ItineraryResponse;
import com.sambie.models.ItineraryRequest;
import org.springframework.stereotype.Service;

@Service
public class GenerateItineraryController {
  public ItineraryResponse generateSurpriseItinerary(ItineraryRequest request) {
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