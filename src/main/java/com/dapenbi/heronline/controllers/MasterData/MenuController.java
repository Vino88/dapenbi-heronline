package com.dapenbi.heronline.controllers.MasterData;

import com.dapenbi.heronline.Prefix;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
public class MenuController {
    @GetMapping(Prefix.PATH_MASTER_DATA+Prefix.PATH_MENU)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "name", required = false,
                                defaultValue = "Guest") String name){
        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        try {
            model.addAttribute("menu_path_api", Prefix.getURLBase(request)+Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_MENU);
        } catch (MalformedURLException e) {
            model.addAttribute("menu_path_api", Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_MENU);
        }
        return "master/menu";
    }
}
