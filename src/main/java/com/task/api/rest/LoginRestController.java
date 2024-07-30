package com.task.api.rest;

import com.task.api.dto.UserDto;
import com.task.api.model.User;
import com.task.api.config.security.helper.TokenHelper;
import com.task.api.config.security.service.TokenService;
import com.task.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginRestController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserService userService;
    @Autowired
    TokenService tokenService;
    @PostMapping()
    public ResponseEntity login(@RequestBody UserDto userDto) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());
            var authentication = manager.authenticate(authToken);

            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenHelper(tokenJWT));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
