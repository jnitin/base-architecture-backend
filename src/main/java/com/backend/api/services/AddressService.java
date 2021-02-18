package com.backend.api.services;

import com.backend.api.domain.Address;
import com.backend.api.dto.AddressDto;

public interface AddressService extends CrudService<Address, AddressDto> {
    Address findByCep(String cep);
}
