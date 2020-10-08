package com.dapenbi.heronline.service;

import com.dapenbi.heronline.models.PerekamanData;
import com.dapenbi.heronline.models.PerekamanDataList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PerekamanDataService {
    Page<PerekamanData> getPagingAllData(String searchParam, Pageable pageable);

    Page<PerekamanDataList> getPagingAllDataList(String searchParam, Pageable pageable);
}
