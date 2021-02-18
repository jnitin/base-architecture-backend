package com.backend.api.controllers;

import com.backend.api.dto.AddressDto;
import com.backend.api.mapper.DataMapper;
import com.backend.api.services.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressController {
    private final AddressService addressService;
    private final DataMapper mapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDto findById(@PathVariable Long id) {
        final var address = addressService.findById(id);
        return mapper.mapTo(address, AddressDto.class);
    }
}