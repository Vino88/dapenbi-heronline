package com.dapenbi.heronline.service;

import com.dapenbi.heronline.dto.MenuDto;
import com.dapenbi.heronline.models.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    Menu findById(Integer id);

    Iterable<Menu>findAll();

    Page<Menu> findAll(Pageable pageable);

    Page<Menu> findAllWithParam(String searchParam, Pageable pageable);

    void addOrUpdateMasterPPBI(MenuDto requestData);

    void delete(Integer id);
}
