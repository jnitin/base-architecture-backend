package com.backend.api.services.impl;

import com.backend.api.domain.Address;
import com.backend.api.dto.AddressDto;
import com.backend.api.exceptions.DataIntegrityException;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith({
        MockitoExtension.class
})
//@MockitoSettings(strictness = Strictness.LENIENT)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressServiceMock;

    @Mock
    private AddressRepository addressRepositoryMock;

    @Mock
    private RestTemplate restTemplateMock;

    @Test
    public void shouldCreate() {
        final var dto = createDto();

        final var addressFromDatabase = createAddress(1L);

        given(addressRepositoryMock.save(any()))
                .willReturn(addressFromDatabase);

        final var created = addressServiceMock.create(dto);

        assertThat(created).isEqualTo(created);

    }

    @Test
    public void shouldThrowErrorWithInvalidCep() {
        final var invalidCep = "123456789";

        assertThrows(DataIntegrityException.class,
                () -> addressServiceMock.findByCep(invalidCep)
        );
    }

    @Test
    public void shouldThrowErrorWithNonexistentCep() {
        final var cep = "12345678";

        given(addressRepositoryMock.findAddressByCep(cep))
                .willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class,
                () -> addressServiceMock.findByCep(cep)
        );
    }

    @Test
    public void shouldFindByCepFromDatabaseSuccessfully() {
        final var cep = "12345678";
        final var address = createAddress(1L);

        given(addressRepositoryMock.findAddressByCep(cep))
                .willReturn(Optional.of(address));

        final var fromService = addressServiceMock.findByCep(cep);

        assertThat(fromService).isEqualTo(address);
    }

    @Test
    public void shouldFindByCepFromApiSuccessfully() {
        final var cep = "12345678";
        final var address = createAddress(1L);
        final var dto = createDto();

        given(addressRepositoryMock.findAddressByCep(cep))
                .willReturn(Optional.empty());

        given(addressRepositoryMock.save(any()))
                .willReturn(address);

        when(restTemplateMock.getForObject(
                anyString(),
                eq(AddressDto.class))
        )
        .thenReturn(dto);

        final var fromService = addressServiceMock.findByCep(cep);

        assertThat(fromService).isEqualTo(address);
    }

    private Address createAddress() {
        return Address
                .builder()
                .bairro("bairro")
                .cep("12345678")
                .complemento("complemento")
                .localidade("localidade")
                .logradouro("logradouro")
                .uf("uf")
                .build();
    }

    private Address createAddress(Long id) {
        final var address = createAddress();
        address.setId(id);
        return address;
    }

    private AddressDto createDto() {
        return AddressDto
                .builder()
                .bairro("bairro")
                .cep("12345678")
                .complemento("complemento")
                .localidade("localidade")
                .logradouro("logradouro")
                .uf("uf")
                .build();
    }

}
