package com.example.postgres.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OSMSearchResult {
    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("osm_type")
    private String osmType;
}