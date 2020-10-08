package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.MasterPPBI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterPPBIRepository extends JpaRepository<MasterPPBI, Integer> {
    @Query(
        value = "select mp from MasterPPBI mp where mp.name like :param or mp.alamatSurat1 like :param or mp.alamatSurat2 like :param or mp.alamatSurat3 like :param or mp.alamatSurat4 like :param"

    )
    List<MasterPPBI> getAllData(@Param("param")String searchParam);

    @Query(
            value = "select mp from MasterPPBI mp where mp.name like :param or mp.alamatSurat1 like :param or mp.alamatSurat2 like :param or mp.alamatSurat3 like :param or mp.alamatSurat4 like :param"
    )
    Page<MasterPPBI> getPagingAllData(@Param("param")String searchParam, Pageable pageable);

}