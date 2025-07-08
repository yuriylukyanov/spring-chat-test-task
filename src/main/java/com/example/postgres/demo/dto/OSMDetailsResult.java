package com.example.postgres.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class OSMDetailsResult {
    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("localname")
    private String localName;

    @JsonProperty("centroid")
    private OSMCentroid centroid;

    @Data
    public class OSMCentroid {
        private ArrayList<Float> coordinates;
    }
}
