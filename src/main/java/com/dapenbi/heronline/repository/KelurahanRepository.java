package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelurahanRepository extends JpaRepository<Kelurahan, Long> {
    @Query(
            value = "select count(p.id) from Kelurahan p"
    )
    Integer getCountKelurahan();

    List<Kelurahan> findAllById(Long id);
    List<Kelurahan> findAllByKecamatan_Id(Long id);
    List<Kelurahan> findAllByKecamatan_IdAndNamaKelurahanLike(Long id, String namaKelurahan);
}
