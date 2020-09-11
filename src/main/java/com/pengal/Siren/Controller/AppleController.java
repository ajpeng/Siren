package com.pengal.Siren.Controller;

import com.pengal.Siren.Repositories.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppleController {

    @Autowired AppleRepository appleRepository;

    @GetMapping("/top100")
    public String top100() {
        return "{\"TODO\"}";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query) {
        return appleRepository.getAppleResponse(query);
    }
}
