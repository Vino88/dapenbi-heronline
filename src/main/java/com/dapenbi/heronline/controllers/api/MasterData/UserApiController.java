package com.dapenbi.heronline.controllers.api.MasterData;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.UsersDto;
import com.dapenbi.heronline.models.Users;
import com.dapenbi.heronline.service.UserAccountService;
import com.dapenbi.heronline.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {

    @Autowired
    UserAccountService userAccountService;

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_USER)
    public Page<Users> index(Pageable pageRequest, @RequestParam(name = "searchParam", required = false) String searchParam){
        String srcParam = searchParam;
        if (searchParam != ""){
            srcParam = "%"+searchParam+"%";
        }
        Page<Users> aIt = userAccountService.findAll(srcParam,pageRequest);
        return aIt;
    }

    @PostMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_USER)
    public ResponseEntity<MessageUtil> save(@ModelAttribute UsersDto requestData){
        MessageUtil messageUtil;
        try {
            userAccountService.addOrUpdateUsers(requestData);
            messageUtil = new MessageUtil("success", "Data berhasil disimpan");
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal disimpan");
        }
        return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
    }
    @DeleteMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_USER+"/delete/{id}")
    public ResponseEntity<MessageUtil> delete(@PathVariable String id){
        MessageUtil messageUtil;
        try {
            userAccountService.delete(Integer.parseInt(id));
            messageUtil = new MessageUtil("success", "Data berhasil dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
        }
    }



}
