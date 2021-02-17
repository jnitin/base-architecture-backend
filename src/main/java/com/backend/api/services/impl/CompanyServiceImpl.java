package com.backend.api.services.impl;

import com.backend.api.domain.Company;
import com.backend.api.dto.create.CreateCompanyDto;
import com.backend.api.pagination.Pageable;
import com.backend.api.repositories.CompanyRepository;
import com.backend.api.services.CompanyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyServiceImpl implements CompanyService {
    final private CompanyRepository repository;

    @Override
    public Company create(CreateCompanyDto createCompanyDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Company findById(Long id) {
        return null;
    }

    @Override
    public void update(Long id, Object o) {

    }

    @Override
    public Page<Object> findAll(Pageable pageable) {
        return null;
    }


//    public List<CreateCompanyDto> getMenus() {
//        UserSS user = UserSSService.authenticated();
//        return toDTO(repo.getMenus(user.getId()));
//    }


}
