package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {
    @Query(
            value = "select count(p.id) from Kecamatan p"
    )
    Integer getCountKecamatan();

    List<Kecamatan> findAllById(Long id);
    List<Kecamatan> findAllByKota_IdAndNamaKecamatanLike(Long id, String namaKecamatan);
    List<Kecamatan> findAllByKota_Id(Long id);
}
