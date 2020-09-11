package com.pengal.Siren.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @RequestMapping("/error")
    public String handleError() {
        return "Oops looks like something went wrong";
    }
}
