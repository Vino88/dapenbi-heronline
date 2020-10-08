package com.dapenbi.heronline.service;

import com.dapenbi.heronline.dto.MasterPPBIRequest;
import com.dapenbi.heronline.models.MasterPPBI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MasterPPBIService {
    /* Example get List All */
    List<MasterPPBI> getAll(String searchParam);

    /* Example function save(create/update) */
    void addOrUpdateMasterPPBI(MasterPPBIRequest masterPPBIRequest);

    /* Example pencarian berdasarkan Id */
    MasterPPBI findById(Integer id);

    /* Example Hapus Data */
    void delete(Integer id);

    Page<MasterPPBI> findAllWithParam(String searchParam, Pageable pageable);

    Iterable<MasterPPBI>findAll();

    Page<MasterPPBI>findAll(Pageable pageable);
}
