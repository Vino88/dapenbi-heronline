package com.dapenbi.heronline.service.impl;

import com.dapenbi.heronline.models.Mpensiun;
import com.dapenbi.heronline.repository.MpensiunRepository;
import com.dapenbi.heronline.service.MpensiunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MpensiunServiceImpl implements MpensiunService {
    @Autowired
    MpensiunRepository mpensiunRepository;

    @Override
    public Page<Mpensiun> findAll(Pageable pageable) {
        return mpensiunRepository.findAll(pageable);
    }
}
