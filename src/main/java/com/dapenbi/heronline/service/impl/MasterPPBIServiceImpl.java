package com.dapenbi.heronline.service.impl;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.MasterPPBIRequest;
import com.dapenbi.heronline.models.MasterPPBI;
import com.dapenbi.heronline.repository.MasterPPBIRepository;
import com.dapenbi.heronline.service.MasterPPBIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MasterPPBIServiceImpl implements MasterPPBIService {
    @Autowired
    MasterPPBIRepository masterPPBIRepository;


    @Override
    public List<MasterPPBI> getAll(String searchParam) {
        Iterable<MasterPPBI> masterPPBIIterable= masterPPBIRepository.findAll();
        List<MasterPPBI> masterPPBIList = StreamSupport.stream(masterPPBIIterable.spliterator(),false).collect(Collectors.toList());
        return masterPPBIList;
    }

    @Override
    public void addOrUpdateMasterPPBI(MasterPPBIRequest masterPPBIRequest) {
        if (masterPPBIRequest.getId() == null){
            MasterPPBI masterPPBI = new MasterPPBI();
            masterPPBI.setName(masterPPBIRequest.getName());
            masterPPBI.setAlamatSurat1(masterPPBIRequest.getAlamatSurat1());
            masterPPBI.setAlamatSurat2(masterPPBIRequest.getAlamatSurat2());
            masterPPBI.setAlamatSurat3(masterPPBIRequest.getAlamatSurat3());
            masterPPBI.setAlamatSurat4(masterPPBIRequest.getAlamatSurat4());
            masterPPBI.setCreatedAt(new Date());
            masterPPBI.setUserStamp(Prefix.LocalComputerName());
            masterPPBIRepository.save(masterPPBI);
        } else {
            Optional<MasterPPBI> masterPPBI = masterPPBIRepository.findById(masterPPBIRequest.getId());
            MasterPPBI currentData = masterPPBI.get();
            currentData.setName(masterPPBIRequest.getName());
            currentData.setAlamatSurat1(masterPPBIRequest.getAlamatSurat1());
            currentData.setAlamatSurat2(masterPPBIRequest.getAlamatSurat2());
            currentData.setAlamatSurat3(masterPPBIRequest.getAlamatSurat3());
            currentData.setAlamatSurat4(masterPPBIRequest.getAlamatSurat4());
            currentData.setUpdatedAt(new Date());
            currentData.setUserStamp(Prefix.LocalComputerName());
            masterPPBIRepository.save(currentData);
        }
    }

    @Override
    public MasterPPBI findById(Integer id) {
        return masterPPBIRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        masterPPBIRepository.deleteById(id);
    }

    @Override
    public Page<MasterPPBI> findAllWithParam(String searchParam, Pageable pageable) {

        //Iterable<MasterPPBI> masterPPBI = masterPPBIRepository.findAll(searchParam);
        //List<MasterPPBI> masterPPBIList = StreamSupport.stream(masterPPBI.spliterator(),false).collect(Collectors.toList());
        //List<MasterPPBI> masterPPBIList = masterPPBIRepository.getAllData("%"+searchParam+"%");
        //Page<MasterPPBI> masterPPBIPage = new PageImpl<MasterPPBI>(
                //masterPPBIList,
                //pageable,
                //masterPPBIList.size());
        return masterPPBIRepository.getPagingAllData("%"+searchParam+"%", pageable);
    }

    @Override
    public Iterable<MasterPPBI> findAll() {
        return masterPPBIRepository.findAll();
    }

    @Override
    public Page<MasterPPBI> findAll(Pageable pageable) {
        return masterPPBIRepository.findAll(pageable);
    }


}
