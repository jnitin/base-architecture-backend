package com.backend.api.services.impl;

import com.backend.api.domain.Account;
import com.backend.api.dto.create.CreateAccountDto;
import com.backend.api.dto.read.ReadAccountDto;
import com.backend.api.dto.update.UpdateAccountDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.repositories.AccountRepository;
import com.backend.api.services.AccountService;
import com.backend.api.services.CompanyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final CompanyService companyService;
  private final DataMapper mapper;

  @Override
  public Account create(CreateAccountDto createAccountDto) {
    final var account = toEntity(createAccountDto);
    accountRepository.save(account);
    companyService.addEntity(account);
    return account;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Account findById(Long id) {
    return accountRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrado"));
  }

  @Override
  public void update(Long id, UpdateAccountDto dto) {
    final var company = CompanyService.getSelectedCompany();
    final var account = findById(id);
    account.update(dto, company);
    accountRepository.save(account);
  }

  @Override
  public Page<ReadAccountDto> findAll(Pageable pageable) {
    final var page = accountRepository.findAll(pageable.getPageable());
    return mapper.mapAllTo(page, ReadAccountDto.class);
  }

  @Override
  public Page<ReadAccountDto> findAll(Pageable pageable, Filter filter) {
    final Page<Account> accounts = accountRepository.findAll(pageable.getPageable());
    return mapper.mapAllTo(accounts, ReadAccountDto.class);
  }

  @Override
  public Page<Account> findAll(Specification spec, PageRequest pageRequest) {
    return accountRepository.findAll(spec, pageRequest);
  }

  @Override
  public Account toEntity(CreateAccountDto createAccountDto) {
    return Account
        .builder()
        .balance(0F)
        .name(createAccountDto.getName())
        .code(createAccountDto.getCode())
        .type(createAccountDto.getType())
        .build();
  }
}
