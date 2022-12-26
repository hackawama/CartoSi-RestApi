package com.ab.cartosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ab.cartosi.entities.Software;

@RepositoryRestController
public interface SoftwareRepo extends JpaRepository<Software, String> {
}
