package com.robin.client_oauth2.expone.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @GetMapping("/public")
    public String hello() {
        return "Public Resource";
    }

    @GetMapping("/protected")
    public String protectedResource() {
        return "Protected Resource";
    }

}
