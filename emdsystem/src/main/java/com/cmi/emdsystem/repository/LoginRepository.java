package com.cmi.emdsystem.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmi.emdsystem.model.LoginRoles;

public interface LoginRepository extends JpaRepository<LoginRoles, Serializable> {

}
