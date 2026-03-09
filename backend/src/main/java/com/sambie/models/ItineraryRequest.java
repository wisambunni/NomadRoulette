package com.sambie.models;

/**
 * Input from front end
 * @param originAirport e.g., 'JFK'
 * @param startDate e.g., '2025-12-01'
 * @param endDate e.g., '2025-12-07'
 * @param budget Total budget for the trip in USD
 */
public record ItineraryRequest(
  String originAirport,
  String startDate,
  String endDate,
  int budget
) {}