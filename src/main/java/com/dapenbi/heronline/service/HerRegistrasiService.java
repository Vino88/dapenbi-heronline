package com.dapenbi.heronline.service;

import com.dapenbi.heronline.dto.CuspUpdateHerRegisSPDto;
import com.dapenbi.heronline.models.HerRegistrasiDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Map;

public interface HerRegistrasiService {
    Page<HerRegistrasiDetail> findByTahunHer(String tahunHer, String searchParam, Pageable pageable);
}
