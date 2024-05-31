package com.cmi.emdsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.cmi.emdsystem.model.User;
import com.cmi.emdsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByEmailAddress(String emailAddress) throws Exception {

        Optional<User> user = Optional.ofNullable(userRepository.findByEmailAddress(emailAddress));
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not found with this Email Address" + emailAddress);

    }

    @Override
    public User findUserByUserId(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not found with this ID" + userId);
    }

    @Override
    public Integer countUserWithAccess() throws Exception {

        int ctrUserAccess = userRepository.countByUserAccess();
        return ctrUserAccess;
    }

    /* Create User Info */
    @Override
    public Integer saveUserInfo(User user) throws Exception {
        // int userCheck = userRepository.checkIfUserIdExist(user.getId());
        // if (userCheck == 1) {
        // return 1;
        // }

        // int emailCheck =
        // userRepository.checkIfEmailExist(user.getEmailAddress());
        // if (emailCheck == 1) {
        // return 2;
        // }

        /* passed */
        userRepository.save(user);
        return 0;
    }

    /* Edit User Info */
    public Integer editUserInfo(User user) throws Exception {
        int emailCheck = userRepository.checkIfEmailExistOnOtherAccount(user.getEmailAddress(), user.getUserId());

        if (emailCheck == 1) {
            return 1;
        }
        /* passed */
        userRepository.save(user);
        return 0;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

}
