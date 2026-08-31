package com.robin.client_oauth2.expone.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @GetMapping("/public")
    public String hello() {
        return "Public Resource";
    }

    @GetMapping("/protected")
    public String protectedResource(@AuthenticationPrincipal OAuth2User principal) {

        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }

        String username = principal.getAttribute("name");
        System.out.println("Username: " + username);

        return String.format("Hello %s!", username);
    }

}
