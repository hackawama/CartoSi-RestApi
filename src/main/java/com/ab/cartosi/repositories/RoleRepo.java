package com.ab.cartosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ab.cartosi.entities.Role;

@RepositoryRestController
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRole(String role);

}
