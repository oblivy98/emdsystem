package com.cmi.emdsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "MST_AUTHORITY")
public class LoginRoles {

    @Id
    @Column(name = "CD_SYAINNO", length = 99999, nullable = false)
    private Integer userId;

    @Email
    @Column(name = "ROLES", length = 40)
    private String roles;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

}
