package com.example.postgres.demo.controllers;

import com.example.postgres.demo.services.OpenStreetMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("osm")
public class OSMController {
    private OpenStreetMapService openStreetMapService;

    public OSMController(OpenStreetMapService openStreetMapService) {
        this.openStreetMapService = openStreetMapService;
    }

    @GetMapping(value = "search")
    public ResponseEntity createChat(String query) throws Exception {
        return ResponseEntity.ok(openStreetMapService.searchCities(query));
    }
}
