package com.backend.api.controllers;

import com.backend.api.domain.Account;
import com.backend.api.dto.create.CreateAccountDto;
import com.backend.api.dto.read.ReadAccountDto;
import com.backend.api.dto.update.UpdateAccountDto;
import com.backend.api.pagination.Filter;
import com.backend.api.services.ProfileService;
import com.backend.api.services.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController extends CrudController<Account, CreateAccountDto, ReadAccountDto, UpdateAccountDto, Filter> {

  private final AccountService accountService;

  public AccountController(ProfileService profileService, AccountService accountService) {
    super(accountService);
    this.accountService = accountService;
  }

}