package com.cmi.emdsystem.dto;

public class LoginDTO {
    Integer userId;
    String email;
    String role;

    public LoginDTO() {

    }

    public LoginDTO(Integer userId, String email, String role) {
        super();
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
