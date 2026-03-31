package com.sambie.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import serpapi.SerpApi;
import serpapi.SerpApiException;

@Service
public class FlightService {
    private final SerpApi serpApi;

    public FlightService(SerpApi serpApi) {
        this.serpApi = serpApi;
    }

    public List<JsonObject> getFlightsWithinBudget(String origin, String startDate, String endDate, int budget) {
        Map<String, String> parameter = new HashMap<>();

        parameter.put("engine", "google_travel_explore");
        parameter.put("departure_id", origin);
        parameter.put("currency", "USD");
        parameter.put("outbound_date", startDate);
        parameter.put("return_date", endDate);

        try {
            JsonObject data = serpApi.search(parameter);
            JsonArray destinations = data.get("destinations").getAsJsonArray();

            List<JsonObject> result = new ArrayList<>();
            for (JsonElement el : destinations) {
                JsonObject destination = el.getAsJsonObject();
                JsonElement priceEl = destination.get("flight_price");
                if (priceEl == null || priceEl.isJsonNull()) continue;
                int price = priceEl.getAsInt();
                if (price <= budget) {
                    result.add(destination);
                }
            }
            return result;
        } catch (SerpApiException ex) {
            System.out.println(ex.toString());
            return List.of();
        }
    }

}
