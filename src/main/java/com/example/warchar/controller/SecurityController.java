package com.example.warchar.controller;

import com.example.warchar.exception.UserAlreadyExistException;
import com.example.warchar.payload.RegisterRequest;
import com.example.warchar.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping(value = "/register")
    public void register(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            securityService.registerUser(registerRequest);
        } catch (UserAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
