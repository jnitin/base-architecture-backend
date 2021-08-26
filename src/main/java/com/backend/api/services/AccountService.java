package com.backend.api.services;

import com.backend.api.domain.Account;
import com.backend.api.dto.create.CreateAccountDto;
import com.backend.api.dto.read.ReadAccountDto;
import com.backend.api.dto.update.UpdateAccountDto;
import com.backend.api.pagination.Filter;

public interface AccountService extends CrudService<Account, CreateAccountDto, ReadAccountDto, UpdateAccountDto, Filter> {
}
