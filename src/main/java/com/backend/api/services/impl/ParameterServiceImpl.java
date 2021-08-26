package com.backend.api.services.impl;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.create.CreateParameterDto;
import com.backend.api.dto.read.ReadParameterDto;
import com.backend.api.dto.update.UpdateParameterDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.repositories.ParameterRepository;
import com.backend.api.services.CompanyService;
import com.backend.api.services.ParameterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ParameterServiceImpl implements ParameterService {

  private final ParameterRepository parameterRepository;
  private final CompanyService companyService;
  private final DataMapper mapper;

  @Override
  public Parameter create(CreateParameterDto createParameterDto) {
    final var parameter = toEntity(createParameterDto);
    parameterRepository.save(parameter);
    companyService.addEntity(parameter);
    return parameter;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Parameter findById(Long id) {
    return parameterRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Parâmetro não encontrado"));
  }

  @Override
  public void update(Long id, UpdateParameterDto dto) {
    final var company = companyService.findById(dto.getCompanyId());
    final var parameter = findById(id);
    parameter.update(dto, company);
    parameterRepository.save(parameter);
  }

  @Override
  public Page<ReadParameterDto> findAll(Pageable pageable) {
    final var page = parameterRepository.findAll(pageable.getPageable());
    return mapper.mapAllTo(page, ReadParameterDto.class);
  }

  @Override
  public Page<ReadParameterDto> findAll(Pageable pageable, Filter filter) {
    final Page<Parameter> parameters = parameterRepository.findAll(pageable.getPageable());
    return mapper.mapAllTo(parameters, ReadParameterDto.class);
  }

  @Override
  public Page<Parameter> findAll(Specification spec, PageRequest pageRequest) {
    return parameterRepository.findAll(spec, pageRequest);
  }

  @Override
  public Parameter toEntity(CreateParameterDto createParameterDto) {
    return Parameter
        .builder()
        .key(createParameterDto.getKey())
        .value(createParameterDto.getValue())
        .note(createParameterDto.getNote())
        .build();
  }
}
