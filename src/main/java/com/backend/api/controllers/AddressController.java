package com.backend.api.controllers;

import com.backend.api.dto.AddressDto;
import com.backend.api.mapper.DataMapper;
import com.backend.api.services.AddressService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressController {
    private final AddressService addressService;
    private final DataMapper mapper;

    @GetMapping("/{cep}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDto findById(@PathVariable String cep) {
        final var address = addressService.findByCep(cep);
        return mapper.mapTo(address, AddressDto.class);
    }
}