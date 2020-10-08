package com.dapenbi.heronline.service.impl;

import com.dapenbi.heronline.models.PerekamanData;
import com.dapenbi.heronline.models.PerekamanDataList;
import com.dapenbi.heronline.repository.PerekamanDataListRepository;
import com.dapenbi.heronline.repository.PerekamanDataRepository;
import com.dapenbi.heronline.service.PerekamanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PerekamanDataServiceImpl implements PerekamanDataService {

    @Autowired
    PerekamanDataRepository perekamanDataRepository;

    @Autowired
    PerekamanDataListRepository perekamanDataListRepository;

    @Override
    public Page<PerekamanData> getPagingAllData(String searchParam, Pageable pageable) {
        if (searchParam != ""){
            return perekamanDataRepository.getPagingAllDataWithParam(searchParam,pageable);
        }else{
            return perekamanDataRepository.getPagingAllData(pageable);
        }
    }

    @Override
    public Page<PerekamanDataList> getPagingAllDataList(String searchParam, Pageable pageable) {
        if (searchParam != ""){
            return perekamanDataListRepository.getPagingAllDataWithParam(searchParam,pageable);
        }else{
            return perekamanDataListRepository.getPagingAllData(pageable);
        }
    }
}
