package com.backend.api.controllers;

import com.backend.api.domain.Address;
import com.backend.api.dto.AddressDto;
import com.backend.api.exceptions.DataIntegrityException;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
import com.backend.api.services.impl.AddressServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressServiceImpl addressServiceMock;

    @MockBean
    private DataMapper dataMapperMock;

    @Test
    void shouldNotFindWithInvalidCep() throws Exception {
        final var cep = "123456789";

        doThrow(new DataIntegrityException("CEP inválido"))
                .when(addressServiceMock).findByCep(eq(cep));

        mockMvc.perform(get("/address/" + cep).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotFindWithNonexistentCep() throws Exception {
        final var cep = "123456789";

        doThrow(new ObjectNotFoundException("Não foi possível encontrar o CEP desejado."))
                .when(addressServiceMock).findByCep(eq(cep));

        mockMvc.perform(get("/address/" + cep).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnAddressWithValidCep() throws Exception {
        final var cep = "12345678";
        final var address = Address.builder().build();
        final var dto = AddressDto
                .builder()
                .cep("12345678")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();

        doReturn(address)
                .when(addressServiceMock).findByCep(eq(cep));

        doReturn(dto)
                .when(dataMapperMock).mapTo(eq(address), eq(AddressDto.class));

        final var result = mockMvc.perform(get("/address/" + cep).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        final var aux = result.getResponse().getContentAsString();
        final var fromApi = objectMapper.readValue(aux, AddressDto.class);

        Assertions.assertEquals(fromApi, dto);
    }

}