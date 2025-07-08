package com.example.postgres.demo.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="user", schema = "public")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "place_id", nullable = true)
    private String placeId;

    @Column(name = "place_name", nullable = true)
    private String placeName;

    @Column(name = "place_lat", nullable = true)
    private Float placeLat;

    @Column(name = "place_lon", nullable = true)
    private Float placeLon;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPlaceName() { return placeName; }

    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getPlaceId() { return placeId; }

    public void setPlaceId(String placeId) { this.placeId = placeId; }

    public Float getPlaceLat() { return placeLat; }

    public void setPlaceLat(Float placeLat) { this.placeLat = placeLat; }

    public Float getPlaceLon() { return placeLon; }

    public void setPlaceLon(Float placeLon) { this.placeLon = placeLon; }
}
