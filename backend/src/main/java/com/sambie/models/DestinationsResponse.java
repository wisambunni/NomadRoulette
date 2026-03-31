package com.sambie.models;

public record DestinationsResponse(
    String name,
    String iataCode,
    String country,
    int price
) {}
