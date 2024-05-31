package com.cmi.emdsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.emdsystem.dto.LoginDTO;
import com.cmi.emdsystem.service.LoginService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public void login(@RequestBody LoginDTO loginDTO) {
        loginService.loginAuthentication(loginDTO);
    }

}
