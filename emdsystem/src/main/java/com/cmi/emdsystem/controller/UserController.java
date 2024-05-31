package com.cmi.emdsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.emdsystem.model.User;
import com.cmi.emdsystem.repository.UserRepository;
import com.cmi.emdsystem.service.UserService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@GetMapping("/userlist")
	public List<User> getAllUser() {
		return userService.findAllUser();
	}

	@GetMapping("/userinfo/{userId}")
	public User getUserInfo(@PathVariable Integer userId) throws Exception {
		return userService.findUserByUserId(userId);
	}

	@GetMapping("/useremailaddress/{emailAddress}")
	public User getEmail(@PathVariable String emailAddress) throws Exception {
		return userService.findUserByEmailAddress(emailAddress);
	}

	/* Create User */

	@PostMapping("/usercreate")
	public void saveUserInfo(@RequestBody User user) throws Exception {
		int checkInfo = userService.saveUserInfo(user);
		if (checkInfo == 1) {
			System.out.print("Same ID Detected");
		} else if (checkInfo == 2) {
			System.out.print("Same Email Detected");
		} else {
			System.out.print("Saved Successfully");
		}
	}

	/* Edit User */
	@PostMapping("/useredit")
	public void editUserInfo(@RequestBody User user) throws Exception {
		int checkInfo = userService.editUserInfo(user);
		// if(checkInfo == 1) {
		// return "Email Address exists. Please change to other Email Address";
		// }
		// else {
		// return "User Information has been changed";
		// }
	}

	/* Delete User */

	@DeleteMapping("/userdelete")
	public void deleteUserInfo(@RequestBody User user) throws Exception {
		userRepository.delete(user);
	}

	/* Get User with Linked PC */

	@GetMapping("/link/unlinkeduser")
	public List<User> getUnlinkedUser() {
		return userRepository.findBylinkUserIsNull();
	}

	@GetMapping("/link/linkeduser")
	public List<User> getlinkedUser() {
		return userRepository.findBylinkUserIsNotNull();
	}
	/*
	 * Get User with linked PC
	 * 
	 * @GetMapping("/link/unlinkeduser") public List<User> getUnLinkedUser() {
	 * return userRepository.findByLinkIsNull(); }
	 * 
	 * @GetMapping("/link/linkeduser") public List<User> getLinkedUser() { Link link
	 * = new Link(); List<Link> links = new ArrayList<Link>(1); links.add(link);
	 * return userRepository.findByLinkNotIn(links); }
	 */

}
