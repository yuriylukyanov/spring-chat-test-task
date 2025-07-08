package com.example.postgres.demo.services;

import com.example.postgres.demo.configuration.OSMConfig;
import com.example.postgres.demo.dto.OSMDetailsResult;
import com.example.postgres.demo.dto.OSMSearchResult;
import com.example.postgres.demo.dto.SearchCitiesResult;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Service
public class OpenStreetMapService {
    private final RestClient client;

    public OpenStreetMapService(OSMConfig config) {
        this.client = RestClient.create(config.getBasicUrl());
    }

    public List<SearchCitiesResult> searchCities(String query) {
        var response = client.get().uri(uriBuilder ->
                uriBuilder.path("/search")
                .queryParam("q", query)
                .queryParam("format", "json")
                .build())
            .header("Content-Type", "application/json")
            .retrieve()
            .body(new ParameterizedTypeReference<List<OSMSearchResult>> () {});

        return response.stream()
                .map(r -> {
                    var mapped = new SearchCitiesResult();
                    mapped.setPlaceId(r.getPlaceId());
                    mapped.setDisplayName(r.getDisplayName());
                    return mapped;
                }).toList();
    }

    public OSMDetailsResult getDetailInformation(String placeId) {
        var response = client.get().uri(uriBuilder ->
                        uriBuilder.path("/details.php")
                                .queryParam("place_id", placeId)
                                .queryParam("addressdetails", 1)
                                .queryParam("hierarchy", 0)
                                .queryParam("group_hierarchy", 1)
                                .queryParam("polygon_geojson", 1)
                                .queryParam("format", "json")
                                .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<OSMDetailsResult> () {});

        return response;
    }
}
