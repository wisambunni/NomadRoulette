package com.sambie.handlers;

import com.sambie.controllers.GenerateItineraryController;
import com.sambie.models.ItineraryRequest;
import com.sambie.models.ItineraryResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GenerateItineraryHandler implements Function<ItineraryRequest, ItineraryResponse> {
  private final GenerateItineraryController controller;

  public GenerateItineraryHandler(GenerateItineraryController controller) {
    this.controller = controller;
  }

  @Override
  public ItineraryResponse apply(ItineraryRequest request) {
    System.out.println("Handler received a valid JSON request");

    return controller.generateSurpriseItinerary(request);
  }
}