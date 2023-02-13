package com.ab.cartosi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.cartosi.entities.Building;
import com.ab.cartosi.entities.Structure;

public interface BuildingRepo extends JpaRepository<Building, Long> {

    List<Building> findAllByStructure(Structure structure);

}
