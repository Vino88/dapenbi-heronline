package com.dapenbi.heronline.controllers.Report;

import com.dapenbi.heronline.Prefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
public class ReportBelumRegistrasiController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    Environment env;

    @GetMapping(Prefix.PATH_REPORT+Prefix.PATH_BELUM_HER)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "name", required = false,
                                defaultValue = "Guest") String name){
        String context = servletContext.getContextPath();
        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        try {
            model.addAttribute("report_belum_her_path_api", Prefix.getURLBase(request)+Prefix.PATH_API+Prefix.PATH_REPORT+Prefix.PATH_BELUM_HER);
        } catch (MalformedURLException e) {
            model.addAttribute("report_belum_her_path_api", Prefix.PATH_API+Prefix.PATH_REPORT+Prefix.PATH_BELUM_HER);
        }
        model.addAttribute("gateway_path_api", env.getProperty("api.address") + env.getProperty("api.servlet.context-path"));
        return "report/belum-registrasi";
    }
}
