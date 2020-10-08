package com.dapenbi.heronline.controllers.api.MasterData;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.MasterPPBIRequest;
import com.dapenbi.heronline.dto.component.Select2Default;
import com.dapenbi.heronline.models.MasterPPBI;
import com.dapenbi.heronline.service.MasterPPBIService;
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
public class PPBIApiController {

    @Autowired
    MasterPPBIService ppbiService;
    /* -- API -- */

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_PPBI)
    public Page<MasterPPBI> index(Pageable pageRequest, @RequestParam(name = "searchParam", required = false) String searchParam){
        Page<MasterPPBI> aIt = ppbiService.findAll(pageRequest);
        if (searchParam != "") {
            aIt = ppbiService.findAllWithParam(searchParam, pageRequest);
        }
        return aIt;
    }

    @PostMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_PPBI)
    public ResponseEntity<MessageUtil> save(@ModelAttribute MasterPPBIRequest requestData){
        MessageUtil messageUtil;
        try {
            ppbiService.addOrUpdateMasterPPBI(requestData);
            messageUtil = new MessageUtil("success", "Data berhasil disimpan");
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal disimpan");
        }
        return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_PPBI+Prefix.SELECT2)
    public List<Select2Default> select2Api(Pageable pageable, @RequestParam(name = "searchParam",required = false)String q){
        Iterable<MasterPPBI> it = ppbiService.findAllWithParam( "%"+q+"%",pageable);
        if (q==null){
            it =ppbiService.findAll();
        }
        List<Select2Default>select2DefaultList = new ArrayList<>();
        it.forEach((k)->{
            MasterPPBIRequest masterPPBIRequest = k.toDto();
            select2DefaultList.add(new Select2Default(String.valueOf(k.getId()),k.getName(),masterPPBIRequest));
        });
        return select2DefaultList;
    }

    @DeleteMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_PPBI+"/delete/{id}")
    public ResponseEntity<MessageUtil> delete(@PathVariable String id){
        MessageUtil messageUtil;
        try {
            ppbiService.delete(Integer.parseInt(id));
            messageUtil = new MessageUtil("success", "Data berhasil dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
        }
    }

}
