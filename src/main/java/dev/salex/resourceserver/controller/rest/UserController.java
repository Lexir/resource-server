package dev.salex.resourceserver.controller.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("/profile")
    @PreAuthorize("#oauth2.hasAnyScope('read')")
    public String getOauth2Principal(OAuth2Authentication auth) {
        return "Access granted for " + auth.getPrincipal();
    }
}