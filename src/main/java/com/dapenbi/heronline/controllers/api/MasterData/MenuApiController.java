package com.dapenbi.heronline.controllers.api.MasterData;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.MenuDto;
import com.dapenbi.heronline.dto.component.Select2Default;
import com.dapenbi.heronline.models.Menu;
import com.dapenbi.heronline.service.MenuService;
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
public class MenuApiController {

    @Autowired
    MenuService menuService;

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_MENU)
    public Page<Menu> index(Pageable pageRequest, @RequestParam(name = "searchParam", required = false) String searchParam){
        Page<Menu> aIt = menuService.findAll(pageRequest);
        if (searchParam != "") {
            aIt = menuService.findAllWithParam(searchParam, pageRequest);
        }
        return aIt;
    }

    @PostMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_MENU)
    public ResponseEntity<MessageUtil> save(@ModelAttribute MenuDto requestData){
        MessageUtil messageUtil;
        try {
            menuService.addOrUpdateMasterPPBI(requestData);
            messageUtil = new MessageUtil("success", "Data berhasil disimpan");
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal disimpan");
        }
        return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_MENU+Prefix.SELECT2)
    public List<Select2Default> select2Api(Pageable pageable, @RequestParam(name = "searchParam",required = false)String q){
        Iterable<Menu> it = menuService.findAllWithParam( "%"+q+"%",pageable);
        if (q==null){
            it =menuService.findAll();
        }
        List<Select2Default>select2DefaultList = new ArrayList<>();
        it.forEach((k)->{
            MenuDto roleDto = k.toDto();
            select2DefaultList.add(new Select2Default(String.valueOf(k.getId()),k.getName(),roleDto));
        });
        return select2DefaultList;
    }

    @DeleteMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_MENU+"/delete/{id}")
    public ResponseEntity<MessageUtil> delete(@PathVariable String id){
        MessageUtil messageUtil;
        try {
            menuService.delete(Integer.parseInt(id));
            messageUtil = new MessageUtil("success", "Data berhasil dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
