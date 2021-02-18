package com.backend.api.services.impl;

import com.backend.api.domain.Address;
import com.backend.api.dto.AddressDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.AddressRepository;
import com.backend.api.services.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address create(AddressDto addressDto) {
        return null;
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado"));
    }
}
