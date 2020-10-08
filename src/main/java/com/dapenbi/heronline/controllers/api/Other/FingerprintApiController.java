package com.dapenbi.heronline.controllers.api.Other;

import com.dapenbi.heronline.Prefix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class FingerprintApiController {
    @GetMapping(Prefix.PATH_API + "/default-fingerprint")
    public void defaultFingerprint(){
        String pathname = this.getClass().getClassLoader().getResource("").getPath() + "static/fingerprint/image.png";
        pathname = pathname.replace("%20"," ");
        File file = new File(pathname);
        if (file.delete()){
            System.out.println(pathname+" File deleted");
        }
    }
}
