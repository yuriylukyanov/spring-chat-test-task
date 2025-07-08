package com.example.postgres.demo.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class SetLocationDTO {
    private String placeId;
    private UUID userId;
}
