package com.dapenbi.heronline.service;

import com.dapenbi.heronline.dto.RoleDto;
import com.dapenbi.heronline.models.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolesService {

    Roles findById(Integer id);

    Iterable<Roles>findAll();

    Page<Roles> findAll(Pageable pageable);

    Page<Roles> findAllWithParam(String searchParam, Pageable pageable);

    void addOrUpdateMasterPPBI(RoleDto requestData);

    void delete(Integer id);
}
