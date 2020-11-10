package hr.tvz.spiranec.studapp.security.web;

import hr.tvz.spiranec.studapp.security.DomainUserDetailsService;
import hr.tvz.spiranec.studapp.security.SecurityUtils;
import hr.tvz.spiranec.studapp.security.jwt.JwtFilter;
import hr.tvz.spiranec.studapp.security.jwt.TokenProvider;
import hr.tvz.spiranec.studapp.user.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final DomainUserDetailsService domainUserDetailsService;

    public LoginController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, DomainUserDetailsService domainUserDetailsService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.domainUserDetailsService = domainUserDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate(@Valid @RequestBody LoginController.LoginDTO login) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUsername(),
                login.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/user/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {


        System.out.println(SecurityUtils.getCurrentUserUsername().get());

        return SecurityUtils.getCurrentUserUsername()
                .map(
                        username -> domainUserDetailsService.getUserDtoByUsername(username)
                                .map(userDTO ->
                                        ResponseEntity
                                                .status(HttpStatus.OK)
                                                .body(userDTO)
                                )
                                .orElseGet(
                                        () -> ResponseEntity
                                                .status(HttpStatus.NOT_FOUND)
                                                .build()
                                )
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .build()
                );
    }

    /**
     * Return jwt token in body because Angular has problems with parsing plain string response entity
     */
    static class JWTToken {
        private String token;

        public JWTToken(String token) {
            this.token = token;
        }

    }
    
    static class LoginDTO {
        
        @NotNull
        private String username;

        @NotNull
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
