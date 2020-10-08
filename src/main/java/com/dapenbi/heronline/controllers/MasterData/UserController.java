package com.dapenbi.heronline.controllers.MasterData;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    UserAccountService userAccountService ;

    @GetMapping(Prefix.PATH_MASTER_DATA+Prefix.PATH_USER)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "name", required = false,
                                defaultValue = "Guest") String name){
        String context = servletContext.getContextPath();
        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        try {
            model.addAttribute("user_path_api", Prefix.getURLBase(request) + Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_USER);
        }catch (Exception ex){
            model.addAttribute("user_path_api", Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_USER);
        }
        return "master/user";
    }

}
