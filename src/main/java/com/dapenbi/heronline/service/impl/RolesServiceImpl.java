package com.dapenbi.heronline.service.impl;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.RoleDto;
import com.dapenbi.heronline.models.Roles;
import com.dapenbi.heronline.repository.RolesRepository;
import com.dapenbi.heronline.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public Roles findById(Integer id) {
        return rolesRepository.findById(id).get();
    }

    @Override
    public Page<Roles> findAllWithParam(String searchParam, Pageable pageable) {
        return rolesRepository.getPagingAllData("%"+searchParam+"%",pageable);
    }

    @Override
    public void addOrUpdateMasterPPBI(RoleDto requestData) {
        if (requestData.getId() == null){
            Roles roles = new Roles();
            roles.setName(requestData.getName());
            roles.setCreatedAt(new Date());
            roles.setUserStamp(Prefix.LocalComputerName());
            rolesRepository.save(roles);
        } else {
            Optional<Roles> roles = rolesRepository.findById(requestData.getId());
            Roles currentData = roles.get();
            currentData.setName(requestData.getName());
            currentData.setUpdatedAt(new Date());
            currentData.setUserStamp(Prefix.LocalComputerName());
            rolesRepository.save(currentData);
        }
    }

    @Override
    public void delete(Integer id) {
        rolesRepository.deleteById(id);
    }

    @Override
    public Iterable<Roles> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Page<Roles> findAll(Pageable pageable) {
        return rolesRepository.findAll(pageable);
    }
}
