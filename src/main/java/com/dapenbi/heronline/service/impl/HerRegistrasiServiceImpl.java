package com.dapenbi.heronline.service.impl;

import com.dapenbi.heronline.models.CompositeId.HerRegistrasiDetailPK;
import com.dapenbi.heronline.models.HerRegistrasiDetail;
import com.dapenbi.heronline.repository.HerRegistrasiDetailRepository;
import com.dapenbi.heronline.service.HerRegistrasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HerRegistrasiServiceImpl implements HerRegistrasiService {
    @Autowired
    HerRegistrasiDetailRepository herRegistrasiDetailRepository;

    @Override
    public Page<HerRegistrasiDetail> findByTahunHer(String tahunHer, String searchParam, Pageable pageable) {
        Page<HerRegistrasiDetail> data = null;
        if (searchParam != ""){
            data = herRegistrasiDetailRepository.findAllById_TahunHerAndId_Nip(tahunHer,searchParam,pageable);
        }else{
            HerRegistrasiDetailPK compositeId = new HerRegistrasiDetailPK();
            compositeId.setTahunHer(tahunHer);
            data = herRegistrasiDetailRepository.findAllById_TahunHer(tahunHer, pageable);
        }
        return data;
    }

}
