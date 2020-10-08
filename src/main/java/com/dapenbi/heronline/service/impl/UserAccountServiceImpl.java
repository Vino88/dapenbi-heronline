package com.dapenbi.heronline.service.impl;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.UserRequest;
import com.dapenbi.heronline.dto.UsersDto;
import com.dapenbi.heronline.models.Users;
import com.dapenbi.heronline.repository.MasterPPBIRepository;
import com.dapenbi.heronline.repository.RolesRepository;
import com.dapenbi.heronline.repository.UserRepository;
import com.dapenbi.heronline.repository.UsersRepository;
import com.dapenbi.heronline.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    UserRepository userAccountRepository;

    @Autowired
    MasterPPBIRepository masterPPBIRepository;
    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Users> getAll(String searchParam) {
        Iterable<Users> usersIterable= userRepository.findAll();
        List<Users> usersList = StreamSupport.stream(usersIterable.spliterator(),false).collect(Collectors.toList());
        return usersList;
    }

    @Override
    public void addOrUpdateUsers(UsersDto usersDto) {
        if (usersDto.getId() == null){
            Users usersAccount = new Users();
            usersAccount.setName(usersDto.getName());
            usersAccount.setEmail(usersDto.getEmail());
            usersAccount.setUsername(usersDto.getUsername());
            usersAccount.setPassword(BCrypt.hashpw(usersDto.getPassword(),BCrypt.gensalt(11)));
            usersAccount.setMasterPPBI(masterPPBIRepository.findById(usersDto.getMasterPPBI()).get());
            usersAccount.setRole(rolesRepository.findById(usersDto.getRole()).get());
            usersAccount.setCreatedAt(new Date());
            usersAccount.setUserStamp(Prefix.LocalComputerName());
            userRepository.save(usersAccount);
        } else {
            Optional<Users> userData = userRepository.findById(usersDto.getId());
            Users currentData = userData.get();
            currentData.setName(usersDto.getName());
            currentData.setEmail(usersDto.getEmail());
            currentData.setEmail(usersDto.getEmail());
            currentData.setUsername(usersDto.getUsername());
            currentData.setPassword(currentData.getPassword());
            currentData.setMasterPPBI(masterPPBIRepository.findById(usersDto.getMasterPPBI()).get());
            currentData.setRole(rolesRepository.findById(usersDto.getRole()).get());
            currentData.setUpdatedAt(new Date());
            currentData.setUserStamp(Prefix.LocalComputerName());
            userRepository.save(currentData);
        }
    }

    @Override
    public Users findById(Integer id) {
        Optional<Users> userData = userRepository.findById(id);
        Users currentData = userData.get();
        return currentData;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<Users> findAll(String searchParam, Pageable pageable) {
        Page<Users> user = userRepository.findAll(pageable);
        if (searchParam != ""){
            user = userRepository.getPagingAllData(searchParam, pageable);
        }
        return user;
    }

    @Override
    public Page<UserRequest> findAllByRoleUser(String searchParam, Pageable pageable) {
        List<Users> dataTemp = userAccountRepository.getDataByRoleUser(searchParam);
        List<UserRequest> userRequestList = new ArrayList<UserRequest>();
        for (Users dataUser : dataTemp){
            UserRequest userRequest = new UserRequest();
            userRequest.setId(dataUser.getId());
            userRequest.setName(dataUser.getName());
            userRequest.setRole(dataUser.getRole().getId());
            userRequest.setUsername(dataUser.getUsername());
            userRequest.setEmail(dataUser.getEmail());
            userRequestList.add(userRequest);
        }
        Page<UserRequest> pages = new PageImpl<UserRequest>(userRequestList, pageable, userRequestList.size());
        return pages;
    }
}
