package com.dapenbi.heronline.repository;

import com.dapenbi.heronline.models.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Integer> {
    Counter findFirstByStatus(String Status);
}
