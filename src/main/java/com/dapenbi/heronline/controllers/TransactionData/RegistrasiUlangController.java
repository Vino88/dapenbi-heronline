package com.dapenbi.heronline.controllers.TransactionData;

import com.dapenbi.heronline.Prefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.env.Environment;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
public class RegistrasiUlangController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    Environment env;

    @GetMapping(Prefix.PATH_TRANSACTION+Prefix.PATH_REGISTRASI_ULANG)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "name", required = false,
                                defaultValue = "Guest") String name){
        String context = servletContext.getContextPath();
        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        try {
            model.addAttribute("her_path_api", Prefix.getURLBase(request)+Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_REGISTRASI_ULANG);
        } catch (MalformedURLException e) {
            model.addAttribute("her_path_api", Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_REGISTRASI_ULANG);
        }
        try {
            model.addAttribute("base_path_api", Prefix.getURLBase(request)+Prefix.PATH_API);
        } catch (MalformedURLException e) {
            model.addAttribute("base_path_api", Prefix.PATH_API);
        }
        model.addAttribute("gateway_path_api", env.getProperty("api.address") + env.getProperty("api.servlet.context-path"));
        model.addAttribute("gateway_address_api", env.getProperty("api.address") );
        model.addAttribute("gateway_remote_path_api", env.getProperty("api.remote.address") + env.getProperty("api.servlet.context-path"));
        return "transaction/registrasi-ulang";
    }

}
