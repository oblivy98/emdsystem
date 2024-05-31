package com.cmi.emdsystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "MST_SYAIN")
public class User {

    @Id
    @Column(name = "CD_SYAINNO", length = 99999, nullable = false)
    private Integer userId;

    @NotBlank(message = "Name is Mandatory")
    @Size(min = 2, max = 40, message = "Length must not be less than 2 or more than 40")
    @Column(name = "NM_SYAIN", length = 40, nullable = false)
    private String userName;

    @Column(name = "USER_ACCESS", length = 2)
    private int userAccess;

    @Email
    @Column(name = "EMAIL_ADDRESS", length = 40)
    private String emailAddress;

    @OneToMany(mappedBy = "userLink", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Link> linkUser = new ArrayList<Link>();

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAccess() {
        return userAccess;
    }

    public void setUserAccess(int userAccess) {
        this.userAccess = userAccess;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
