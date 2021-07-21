package com.backend.api.query;

import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface UserQuery {
    Page<ReadProfileDto> findUnlinkedProfiles(Long id, Pageable pageable, Specification specification);
}
