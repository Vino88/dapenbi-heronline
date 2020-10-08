package com.dapenbi.heronline.controllers.MasterData;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.service.MasterPPBIService;
import com.dapenbi.heronline.service.MpensiunService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
public class MpensiunController {

    @Autowired
    Environment env;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    MpensiunService mpensiunService;

    @GetMapping(Prefix.PATH_MASTER_DATA+Prefix.PATH_MPENSIUN)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "name", required = false,
                                defaultValue = "Guest") String name) {
        String context = servletContext.getContextPath();
        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        try {
            model.addAttribute("mpensiun_path_api", Prefix.getURLBase(request)+Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_MPENSIUN);
        } catch (MalformedURLException e) {
            model.addAttribute("mpensiun_path_api", Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_MPENSIUN);
        }
        model.addAttribute("gateway_path_api", env.getProperty("api.address") + env.getProperty("api.servlet.context-path"));
        return "master/master-pensiun";
    }
}
