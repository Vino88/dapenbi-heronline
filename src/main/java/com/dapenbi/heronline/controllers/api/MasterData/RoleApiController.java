package com.dapenbi.heronline.controllers.api.MasterData;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.RoleDto;
import com.dapenbi.heronline.dto.component.Select2Default;
import com.dapenbi.heronline.models.Roles;
import com.dapenbi.heronline.service.RolesService;
import com.dapenbi.heronline.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleApiController {

    @Autowired
    RolesService rolesService;

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_ROLE)
    public Page<Roles> index(Pageable pageRequest, @RequestParam(name = "searchParam", required = false) String searchParam){
        Page<Roles> aIt = rolesService.findAll(pageRequest);
        if (searchParam != "") {
            aIt = rolesService.findAllWithParam(searchParam, pageRequest);
        }
        return aIt;
    }

    @PostMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_ROLE)
    public ResponseEntity<MessageUtil> save(@ModelAttribute RoleDto requestData){
        MessageUtil messageUtil;
        try {
            rolesService.addOrUpdateMasterPPBI(requestData);
            messageUtil = new MessageUtil("success", "Data berhasil disimpan");
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal disimpan");
        }
        return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_ROLE+Prefix.SELECT2)
    public List<Select2Default> select2Api(Pageable pageable, @RequestParam(name = "searchParam",required = false)String q){
        Iterable<Roles> it = rolesService.findAllWithParam( "%"+q+"%",pageable);
        if (q==null){
            it =rolesService.findAll();
        }
        List<Select2Default>select2DefaultList = new ArrayList<>();
        it.forEach((k)->{
            RoleDto roleDto = k.toDto();
            select2DefaultList.add(new Select2Default(String.valueOf(k.getId()),k.getName(),roleDto));
        });
        return select2DefaultList;
    }

    @DeleteMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_ROLE+"/delete/{id}")
    public ResponseEntity<MessageUtil> delete(@PathVariable String id){
        MessageUtil messageUtil;
        try {
            rolesService.delete(Integer.parseInt(id));
            messageUtil = new MessageUtil("success", "Data berhasil dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
