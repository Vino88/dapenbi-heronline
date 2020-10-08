package com.dapenbi.heronline.service.impl;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.MenuDto;
import com.dapenbi.heronline.models.Menu;
import com.dapenbi.heronline.repository.MenuRepository;
import com.dapenbi.heronline.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu findById(Integer id) {
        return menuRepository.findById(id).get();
    }

    @Override
    public Page<Menu> findAllWithParam(String searchParam, Pageable pageable) {
        return menuRepository.getPagingAllData("%"+searchParam+"%",pageable);
    }

    @Override
    public void addOrUpdateMasterPPBI(MenuDto requestData) {
        if (requestData.getId() == null){
            Menu menu = new Menu();
            menu.setName(requestData.getName());
            menu.setUrl(requestData.getUrl());
            menu.setGrupMenu(requestData.getGrupMenu());
            List<Menu> data = menuRepository.findDistinctByGrupMenu(requestData.getGrupMenu());
            if (data.size() > 0){
                menu.setGrupMenuSort(data.get(0).getGrupMenuSort());
            }else{
                List<Menu> listmenu = menuRepository.findAll();
                if (listmenu.size() > 0) {
                    menu.setGrupMenuSort(menuRepository.findLastNumberGrupMenu() + 1);
                }else {
                    menu.setGrupMenuSort(1);
                }
            }
            menu.setCreatedAt(new Date());
            menu.setUserStamp(Prefix.LocalComputerName());
            menuRepository.save(menu);
        } else {
            Optional<Menu> menu = menuRepository.findById(requestData.getId());
            Menu currentData = menu.get();
            currentData.setName(requestData.getName());
            currentData.setUrl(requestData.getUrl());
            currentData.setGrupMenu(requestData.getGrupMenu());
            List<Menu> data = menuRepository.findDistinctByGrupMenu(requestData.getGrupMenu());
            if (data.size() > 0){
                currentData.setGrupMenuSort(data.get(0).getGrupMenuSort());
            }else{
                List<Menu> listmenu = menuRepository.findAll();
                if (listmenu.size() > 0) {
                    currentData.setGrupMenuSort(menuRepository.findLastNumberGrupMenu() + 1);
                }else {
                    currentData.setGrupMenuSort(1);
                }
            }
            currentData.setUpdatedAt(new Date());
            currentData.setUserStamp(Prefix.LocalComputerName());
            menuRepository.save(currentData);
        }
    }

    @Override
    public void delete(Integer id) {
        menuRepository.deleteById(id);
    }

    @Override
    public Iterable<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Page<Menu> findAll(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }
}
