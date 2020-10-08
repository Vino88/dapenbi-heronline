package com.dapenbi.heronline.controllers.MasterData;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.service.MasterPPBIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
public class PPBIController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    MasterPPBIService ppbiService;

    @GetMapping(Prefix.PATH_MASTER_DATA+Prefix.PATH_PPBI)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {
        String context = servletContext.getContextPath();
        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        try {
            model.addAttribute("ppbi_path_api", Prefix.getURLBase(request)+Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_PPBI);
        } catch (MalformedURLException e) {
            model.addAttribute("ppbi_path_api", Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_PPBI);
        }
        return "master/ppbi";
    }
}
