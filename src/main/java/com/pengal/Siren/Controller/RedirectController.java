package com.pengal.Siren.Controller;

import com.pengal.Siren.Entity.Redirect;
import com.pengal.Siren.Exception.BadRequestException;
import com.pengal.Siren.Request.RedirectCreationRequest;
import com.pengal.Siren.Service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class RedirectController {

    private RedirectService redirectService;

    @Autowired
    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/{alias}")
    public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException {
//        Redirect redirect = redirectService.getRedirect(alias);
        URI uri = new URI("www.google.ca");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody RedirectCreationRequest redirectCreationRequest) throws BadRequestException {
        return ResponseEntity.ok(redirectService.createRedirect(redirectCreationRequest));
    }
}
