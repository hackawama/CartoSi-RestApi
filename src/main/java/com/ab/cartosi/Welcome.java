package com.ab.cartosi;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Welcome {

    @RequestMapping("/")
    public String home() {
        return "HELLO From CartoSI";
    }

    @GetMapping("/testsec1")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public Map<String, Object> testsec1(Authentication auth) {
        return Map.of("message", "testSec1",
                "username", auth.getName(),
                "Roles", auth.getAuthorities());
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/testsec2")
    public Map<String, String> testsec2(String data) {
        return Map.of("data", data);
    }
}