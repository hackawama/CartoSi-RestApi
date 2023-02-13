package com.ab.cartosi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ab.cartosi.entities.Network;
import com.ab.cartosi.entities.Structure;

@RepositoryRestController
public interface NetworkRepo extends JpaRepository<Network, Long> {
    List<Network> findAllByStructure(Structure structure);
}
