package com.backend.api.services;

import com.backend.api.domain.Base;


public interface CrudService<Bean extends Base, Dto> {
    Bean create(Dto dto);

    Bean findById(Long id);


}