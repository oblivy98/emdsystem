package com.cmi.emdsystem.service;

import java.util.List;

import com.cmi.emdsystem.model.User;

public interface UserService {

	public User findUserByEmailAddress(String emailAddress) throws Exception;

	public User findUserByUserId(Integer userId) throws Exception;

	public Integer countUserWithAccess() throws Exception;

	/* Create User */

	public Integer saveUserInfo(User user) throws Exception;

	public Integer editUserInfo(User user) throws Exception;

	List<User> findAllUser();
}
