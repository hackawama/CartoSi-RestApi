package com.ab.cartosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ab.cartosi.entities.AppUser;

@RepositoryRestController
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByName(String username);
}
