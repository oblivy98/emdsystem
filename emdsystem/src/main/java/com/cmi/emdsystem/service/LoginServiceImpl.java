package com.cmi.emdsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.emdsystem.dto.LoginDTO;
import com.cmi.emdsystem.model.LoginRoles;
import com.cmi.emdsystem.model.User;
import com.cmi.emdsystem.repository.LoginRepository;
import com.cmi.emdsystem.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void loginAuthentication(LoginDTO loginDTO) {

        System.out.println("DUMAAN");
        System.out.println(loginDTO.getEmail());
        System.out.println(loginDTO.getUserId());
        LoginRoles loginRoles = new LoginRoles();
        loginRoles = loginRepository.findById(loginDTO.getUserId()).orElse(null);
        if (loginRoles != null) {
            User user = new User();
            user = userRepository.findById(loginRoles.getUserId()).orElse(null);
            if (user.getEmailAddress().equals(loginDTO.getEmail()) && user.getUserId() == loginDTO.getUserId()) {
                System.out.println(user.getUserName());
            }
        }
    }
}
