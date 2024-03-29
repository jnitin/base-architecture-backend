package com.backend.api.services;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.domain.Base;
import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.dto.create.CreateCompanyDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.update.UpdateCompanyDto;
import com.backend.api.pagination.Filter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface CompanyService extends CrudService<Company, CreateCompanyDto, ReadCompanyDto, UpdateCompanyDto, Filter> {
  List<ReadCompanyDto> getMenus(User user);

  static Company getSelectedCompany() {
    final UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    return userAuthentication.getCompany();
  }

  <Entity extends Base> void addEntity(Entity entity);
}
