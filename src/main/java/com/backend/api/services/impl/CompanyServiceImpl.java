package com.backend.api.services.impl;

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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyServiceImpl implements CompanyService {

    final private CompanyRepository companyRepository;
    final private DataMapper mapper;

    @Override
    public Company create(CreateCompanyDto createCompanyDto) {
        return null;
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

    }

    @Override
    public Page<ReadCompanyDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Company toEntity(CreateCompanyDto createCompanyDto) {
        return null;
    }

    @Override
    public List<ReadCompanyDto> getMenus(User user) {
        final var companies = companyRepository.getMenus(user.getId());
        return mapper.mapAllTo(companies, ReadCompanyDto.class);
    }


//    public List<CreateCompanyDto> getMenus() {
//        UserSS user = UserSSService.authenticated();
//        return toDTO(repo.getMenus(user.getId()));
//    }


}
