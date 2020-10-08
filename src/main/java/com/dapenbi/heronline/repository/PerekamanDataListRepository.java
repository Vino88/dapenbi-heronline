package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.PerekamanData;
import com.dapenbi.heronline.models.PerekamanDataList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerekamanDataListRepository extends JpaRepository<PerekamanDataList, Integer> {
    @Query(
            value = "select pd from PerekamanDataList pd where pd.name like :param or pd.email like :param or pd.username like :param or pd.nip like :param"
    )
    Page<PerekamanDataList> getPagingAllDataWithParam(@Param("param") String searchParam, Pageable pageable);

    @Query(
            value = "select pd from PerekamanDataList pd "
    )
    Page<PerekamanDataList> getPagingAllData(Pageable pageable);
}
