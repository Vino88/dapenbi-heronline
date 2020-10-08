package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Mpensiun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MpensiunRepository extends JpaRepository<Mpensiun, String> {

    Page<Mpensiun> findAll(Pageable pageable);

    Page<Mpensiun> findAllByNip(String nip, Pageable pageable);

    @Query(
            value = "select mp from Mpensiun mp"
    )
    List<Mpensiun> getAllLimit();

}
