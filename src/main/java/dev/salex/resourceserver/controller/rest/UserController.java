package dev.salex.resourceserver.controller.rest;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"Пользователь"}, authorizations = { @Authorization(value="apiKey") })
@RestController
@RequestMapping("user")
public class UserController {

    @ApiOperation(value = "${UserController.getOauth2Principal}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Error: Unauthorized")})
    @GetMapping(value = "/profile")
    @PreAuthorize("#oauth2.hasAnyScope('userprofile')")
    public String getOauth2Principal(@ApiIgnore OAuth2Authentication auth) {
        return "Access granted for " + auth.getPrincipal();
    }
}