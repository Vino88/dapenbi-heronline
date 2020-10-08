package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Mpensiun;
import com.dapenbi.heronline.models.PerekamanData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerekamanDataRepository extends JpaRepository<PerekamanData, Integer> {
    @Query(
            value = "select pd from PerekamanData pd where pd.name like :param or pd.email like :param or pd.username like :param or pd.nip like :param"
    )
    Page<PerekamanData> getPagingAllDataWithParam(@Param("param")String searchParam, Pageable pageable);

    @Query(
            value = "select pd from PerekamanData pd "
    )
    Page<PerekamanData> getPagingAllData(Pageable pageable);

}
