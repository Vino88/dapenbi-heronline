package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Kota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KotaRepository extends JpaRepository<Kota, Long> {
    @Query(
            value = "select count(p.id) from Kota p"
    )
    Integer getCountKota();

    List<Kota> findAllById(Long id);
    List<Kota> findAllByProvinsi_Id(Long id);
    List<Kota> findAllByProvinsi_IdAndNamaKotaLike(Long id, String namaKota);
}
