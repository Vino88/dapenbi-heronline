package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Dashboard;
import com.dapenbi.heronline.models.PerekamanDataList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    Dashboard findFirstByTahunHer(String tahunHer);
}
