package com.dapenbi.heronline.service;

import com.dapenbi.heronline.models.Mpensiun;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface MpensiunService {

    Page<Mpensiun> findAll(org.springframework.data.domain.Pageable pageable);


}
