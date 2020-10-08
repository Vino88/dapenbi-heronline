package com.dapenbi.heronline.service;

import com.dapenbi.heronline.dto.UserRequest;
import com.dapenbi.heronline.dto.UsersDto;
import com.dapenbi.heronline.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserAccountService {
    /* Example get List All */
    List<Users> getAll(String searchParam);

    /* Example function save(create/update) */
    void addOrUpdateUsers(UsersDto usersDto);

    /* Example pencarian berdasarkan Id */
    Users findById(Integer id);

    /* Example Hapus Data */
    void delete(Integer id);

    Page<Users> findAll(String searchParam, Pageable pageable);

    Page<UserRequest> findAllByRoleUser(String searchParam, Pageable pageable);

}
