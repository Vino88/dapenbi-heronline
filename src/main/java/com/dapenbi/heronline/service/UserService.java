package com.dapenbi.heronline.service;

import com.dapenbi.heronline.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Page<Users> getPagingAllData(String searchParam, Pageable pageable);
}
