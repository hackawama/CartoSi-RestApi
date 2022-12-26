package com.ab.cartosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import com.ab.cartosi.entities.CategoryHardware;;

@RepositoryRestController
public interface CategoryRepo extends JpaRepository<CategoryHardware, Long> {
}
