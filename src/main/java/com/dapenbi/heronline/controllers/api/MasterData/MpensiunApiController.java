package com.dapenbi.heronline.controllers.api.MasterData;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.models.Mpensiun;
import com.dapenbi.heronline.repository.MpensiunRepository;
import com.dapenbi.heronline.service.MpensiunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MpensiunApiController {
    @Autowired
    MpensiunService mpensiunService;

    @Autowired
    MpensiunRepository mpensiunRepository;

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_MPENSIUN)
    public Page<Mpensiun> index(Pageable pageable){
        Page<Mpensiun> uIt = mpensiunService.findAll(pageable);
        return uIt;
    }
}
