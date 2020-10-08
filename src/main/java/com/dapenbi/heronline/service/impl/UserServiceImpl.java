package com.dapenbi.heronline.service.impl;

import com.dapenbi.heronline.dto.UsersDto;
import com.dapenbi.heronline.models.Users;
import com.dapenbi.heronline.repository.RolesRepository;
import com.dapenbi.heronline.repository.UserRepository;
import com.dapenbi.heronline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(s);
        UsersDto userDto = users.toDto();
        if (users == null){
            throw new UsernameNotFoundException("User not found.");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(users.getRole().getName());
        UserDetails userDetails = (UserDetails) new User(users.getUsername(), users.getPassword(), Arrays.asList(authority));

        return userDetails;
    }


    @Override
    public Page<Users> getPagingAllData(String searchParam, Pageable pageable) {
        return userRepository.getPagingAllData("%"+searchParam+"%",pageable);
    }
}
