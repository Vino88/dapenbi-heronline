package com.dapenbi.heronline.controllers.TransactionData;

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
public class PerekamanDataController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    Environment env;

    @GetMapping(Prefix.PATH_TRANSACTION+Prefix.PATH_PEREKAMAN_DATA)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "name", required = false,
                                defaultValue = "Guest") String name){
        String context = servletContext.getContextPath();
        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        try {
            model.addAttribute("perekaman_path_api", Prefix.getURLBase(request)+Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_PEREKAMAN_DATA);
        } catch (MalformedURLException e) {
            model.addAttribute("perekaman_path_api", Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_PEREKAMAN_DATA);
        }
        try {
            model.addAttribute("base_path_api", Prefix.getURLBase(request)+Prefix.PATH_API+Prefix.PATH_TRANSACTION);
        } catch (MalformedURLException e) {
            model.addAttribute("base_path_api", Prefix.PATH_API+Prefix.PATH_TRANSACTION);
        }
        try {
            model.addAttribute("generatepass_path_api",Prefix.getURLBase(request)+ Prefix.PATH_API + Prefix.PATH_GENERATE_PASSWORD);
        } catch (MalformedURLException e) {
            model.addAttribute("generatepass_path_api",Prefix.PATH_API + Prefix.PATH_GENERATE_PASSWORD);
        }
        try {
            model.addAttribute("counter_path_api",Prefix.getURLBase(request)+ Prefix.PATH_API+Prefix.PATH_TRANSACTION +Prefix.PATH_PEREKAMAN_DATA+ "/counter-add");
        } catch (MalformedURLException e) {
            model.addAttribute("counter_path_api",Prefix.PATH_API+Prefix.PATH_TRANSACTION +Prefix.PATH_PEREKAMAN_DATA+ "/counter-add");
        }
        try {
            model.addAttribute("defaultfinger_path_api",Prefix.getURLBase(request)+ Prefix.PATH_API + "/default-fingerprint");
        } catch (MalformedURLException e) {
            model.addAttribute("defaultfinger_path_api",Prefix.PATH_API + "/default-fingerprint");
        }
        model.addAttribute("gateway_path_resources", env.getProperty("api.address"));
        model.addAttribute("gateway_path_api", env.getProperty("api.address") + env.getProperty("api.servlet.context-path"));
        model.addAttribute("gateway_remote_path_api", env.getProperty("api.remote.address") + env.getProperty("api.servlet.context-path"));
        return "transaction/perekaman-data";
    }

}