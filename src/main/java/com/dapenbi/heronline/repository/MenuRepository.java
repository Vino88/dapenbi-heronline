package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findDistinctByGrupMenu(String grupMenu);


    @Query(
            value = "select max(mn.grupMenuSort) from Menu mn"
    )
    Integer findLastNumberGrupMenu();

    @Query(
            value = "select mn from Menu mn where mn.name like :param or mn.id like :param "
    )
    Page<Menu> getPagingAllData(@Param("param") String searchParam, Pageable pageable);

}
