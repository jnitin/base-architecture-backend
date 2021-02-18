package com.backend.api.services.impl;

import com.backend.api.domain.Address;
import com.backend.api.dto.AddressDto;
import com.backend.api.exceptions.DataIntegrityException;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.AddressRepository;
import com.backend.api.services.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final RestTemplate restTemplate;
    @Override
    public Address create(AddressDto addressDto) {
        final var address = toEntity(addressDto);
        return addressRepository.save(address);
    }

    @Override
    public Address findByCep(String cep) {
        return addressRepository.findAddressByCep(cep).orElseGet(() -> {
            if (!isCepValid(cep)) {
                throw new DataIntegrityException("CEP inválido");
            }
            Address fromApi;
            try {
                final AddressDto dto = restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json/", AddressDto.class);

                fromApi = create(dto);
            } catch (RuntimeException e) {
                throw new ObjectNotFoundException("Não foi possível encontrar o CEP desejado.");
            }
            return fromApi;
        });
    }

    private Boolean isCepValid(String cep) {
        if (cep.length() != 8) {
            return false;
        }
        return Pattern.matches("[0-9]{8}", cep);
    }

    private Address toEntity(AddressDto dto) {
        return Address
                .builder()
                .bairro(dto.getBairro())
                .cep(dto.getCep().replace("-", ""))
                .complemento(dto.getComplemento())
                .localidade(dto.getLocalidade())
                .logradouro(dto.getLogradouro())
                .uf(dto.getUf())
                .build();
    }

}
