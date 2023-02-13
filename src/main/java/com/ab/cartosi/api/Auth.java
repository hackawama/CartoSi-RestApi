package com.ab.cartosi.api;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.cartosi.entities.AppUser;
import com.ab.cartosi.entities.AuthForm;
import com.ab.cartosi.service.AccountService;

@RestController
@CrossOrigin
public class Auth {
    private JwtEncoder jwtEncoder;
    private AuthenticationManager authenticationManager;
    private AccountService acService;

    public Auth(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager, AccountService acService) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.acService = acService;
    }

    @PostMapping("/users")
    public AppUser addAppUser(@RequestBody AppUser aUser) {
        return acService.addNewUser(aUser);
    }

    @PostMapping("/token")
    public Map<String, String> jwtToken(@RequestBody AuthForm authForm) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authForm.getUsername(), authForm.getPassword()));
        Map<String, String> idToken = new HashMap<>();
        Instant instant = Instant.now();
        String scope = auth.getAuthorities().stream().map(aut -> aut.getAuthority()).collect(Collectors.joining(" "));
        AppUser appUser = acService.loadUserByUsername(authForm.getUsername());
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .subject(auth.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(800000, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope", scope)
                .claim("strucure", appUser.getStructure().getLabel())
                .claim("strucureId", appUser.getStructure().getId())
                .build();
        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
        idToken.put("TOKEN", jwtAccessToken);
        return idToken;
    }

}
