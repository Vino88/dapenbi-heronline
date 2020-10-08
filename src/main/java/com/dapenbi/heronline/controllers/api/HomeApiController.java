package com.dapenbi.heronline.controllers.api;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.models.Dashboard;
import com.dapenbi.heronline.models.Menu;
import com.dapenbi.heronline.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class HomeApiController {

    @Autowired
    DashboardRepository dashboardRepository;

    @GetMapping(Prefix.PATH_API + "/dashboard")
    public Dashboard index(@RequestParam(name = "tahunHer", required = false) String tahunHer){
        Dashboard dashboard = dashboardRepository.findFirstByTahunHer(tahunHer);
        return dashboard;
    }
}
