package com.sambie.models;

public record ItineraryResponse(
  String destination,
  String flightDetails,
  String hotelDetails,
  int estimatedCost
) {}