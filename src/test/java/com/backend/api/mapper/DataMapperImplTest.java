package com.backend.api.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith({
        MockitoExtension.class
})
class DataMapperImplTest {
    @InjectMocks
    DataMapperImpl dataMapperImpl;
    @Mock
    ModelMapper modelMapperMock;

    @Test
    void shouldMapObjectUsingModelMapper() {
        given(
                modelMapperMock.map("String", Integer.class)
        ).willReturn(6);

        var result = dataMapperImpl.mapTo("String", Integer.class);

        assertThat(result).isEqualTo(6);
    }
}
