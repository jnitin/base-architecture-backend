package com.backend.api.services.impl;

import com.backend.api.domain.Base;
import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.dto.create.CreateCompanyDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.update.UpdateCompanyDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Pageable;
import com.backend.api.repositories.CompanyRepository;
import com.backend.api.services.CompanyService;
import com.backend.api.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyServiceImpl implements CompanyService {

  final private CompanyRepository companyRepository;
  final private DataMapper mapper;

  @Override
  public Company create(CreateCompanyDto createCompanyDto) {
    final var company = toEntity(createCompanyDto);
    companyRepository.save(company);
    return company;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Company findById(Long id) {
    return companyRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada."));
  }

  @Override
  public void update(Long id, UpdateCompanyDto dto) {
    final var company = findById(id);
    company.update(dto);
    companyRepository.save(company);
  }

  @Override
  public Page<ReadCompanyDto> findAll(Pageable pageable) {
    final var companies = companyRepository.findAll(pageable.getPageable());
    return mapper.mapAllTo(companies, ReadCompanyDto.class);
  }

  @Override
  public Page<Company> findAll(Specification spec, PageRequest pageRequest) {
    return companyRepository.findAll(spec, pageRequest);
  }

  @Override
  public Company toEntity(CreateCompanyDto createCompanyDto) {
    return Company
        .builder()
        .name(createCompanyDto.getName())
        .cnpj(createCompanyDto.getCnpj())
        .build();
  }

  @Override
  public List<ReadCompanyDto> getMenus(User user) {
    final var companies = companyRepository.getMenus(user.getId());
    return mapper.mapAllTo(companies, ReadCompanyDto.class);
  }

  @Override
  public <Entity extends Base> void addEntity(Entity entity) {
    Method[] methods = Company.class.getDeclaredMethods();
    Method getter = Arrays.stream(methods).filter(method -> {
              Boolean pred1 = method.getReturnType().equals(Set.class);
              Boolean pred2 = method.getGenericReturnType().getTypeName().contains("<" + entity.getClass().getName() + ">");
              return pred1 && pred2;
            }
        )
        .collect(Collectors.toList()).get(0);
    try {
      if (CompanyService.getSelectedCompany() != null && CompanyService.getSelectedCompany().getId() != null) {
        Company company = findById(CompanyService.getSelectedCompany().getId());
        ((Set<Entity>) getter.invoke(company)).add(entity);
        companyRepository.save(company);
      } else {
        for (Company company : UserService.getLoggedInUser().getCompanies()) {
          company = findById(company.getId());
          ((Set<Entity>) getter.invoke(company)).add(entity);
          companyRepository.save(company);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("ERRO aqui");
    }
  }
}
