package com.cmi.emdsystem.dto;

public class UserDTO {

    Integer userId;
    String userName;
    String email;
    Integer userAccess;

    public UserDTO() {

    }

    public UserDTO(Integer userId, String userName, String email, Integer userAccess) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.userAccess = userAccess;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserAccess() {
        return userAccess;
    }

    public void setUserAccess(Integer userAccess) {
        this.userAccess = userAccess;
    }

}
