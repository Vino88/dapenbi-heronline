package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Provinsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinsiRepository extends JpaRepository<Provinsi, Long> {
    @Query(
            value = "select count(p.id) from Provinsi p"
    )
    Integer getCountProvinsi();

    List<Provinsi> findAllByNamaProvinsiLike(String namaProvinsi);
}
