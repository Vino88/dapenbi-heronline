package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.CompositeId.HerRegistrasiDetailPK;
import com.dapenbi.heronline.models.HerRegistrasiDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HerRegistrasiDetailRepository extends JpaRepository<HerRegistrasiDetail, HerRegistrasiDetailPK> {
    @Query(
            value = "select * from TBL_T_HER_REGISTRASI_DETAIL where tahun_her=:param1 AND (nip like :param2 or nama_peserta like :param2 or nama_penerima like :param2)",
            nativeQuery = true
    )
    Page<HerRegistrasiDetail> getDataByTahunHerWithParam(@Param("param1") String tahunHer, @Param("param2") String searchParam, Pageable pageable);

    Page<HerRegistrasiDetail> findAllById(HerRegistrasiDetailPK id, Pageable pageable);

    Page<HerRegistrasiDetail> findAllById_TahunHer(String tahunHer, Pageable pageable);

    Page<HerRegistrasiDetail> findAllById_TahunHerAndId_Nip(String tahunHer, String Nip, Pageable pageable);

    HerRegistrasiDetail findById_TahunHerAndId_Nip(String tahunHer, String Nip);

}
