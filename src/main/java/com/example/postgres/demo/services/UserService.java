package com.example.postgres.demo.services;

import com.example.postgres.demo.dto.AddUser;
import com.example.postgres.demo.dto.SetLocationDTO;
import com.example.postgres.demo.entities.User;
import com.example.postgres.demo.exceptions.BadRequestException;
import com.example.postgres.demo.repositories.UserRepository;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OpenStreetMapService openStreetMapService;

    public UserService(
            UserRepository userRepository,
            OpenStreetMapService openStreetMapService) {
        this.userRepository = userRepository;
        this.openStreetMapService = openStreetMapService;
    }

    @Transactional
    public UUID createUser(AddUser dto) throws Exception {
        if (dto.getUsername() == null || dto.getUsername().isEmpty())
            throw new BadRequestException("empty username");
        if (!userRepository.existsByUsername(dto.getUsername())) {
            var user = new User();
            user.setId(UUID.randomUUID());
            user.setUsername(dto.getUsername());
            user.setCreatedAt(OffsetDateTime.now());
            userRepository.save(user);
            return user.getId();
        } else {
            throw new BadRequestException("username " + dto.getUsername() + " already exists");
        }
    }

    public void setLocation(SetLocationDTO dto) throws BadRequestException {
        if (dto.getUserId() == null)
            throw new BadRequestException("empty userId");

        if (ObjectUtils.isEmpty(dto.getPlaceId()))
            throw new BadRequestException("empty placeId");

        var userOption = userRepository.findById(dto.getUserId());

        if (userOption.isEmpty())
            throw new BadRequestException("user not found");

        var locationDetailInfo = openStreetMapService.getDetailInformation(dto.getPlaceId());

        var user = userOption.get();

        user.setPlaceId(locationDetailInfo.getPlaceId());
        user.setPlaceName(locationDetailInfo.getLocalName());
        user.setPlaceLon(locationDetailInfo.getCentroid().getCoordinates().get(0));
        user.setPlaceLat(locationDetailInfo.getCentroid().getCoordinates().get(1));

        userRepository.save(user);
    }
}
