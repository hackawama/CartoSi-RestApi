package com.ab.cartosi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.cartosi.entities.Stage;
import com.ab.cartosi.entities.Structure;

public interface StageRepo extends JpaRepository<Stage, Long> {
    List<Stage> findByBuilding_Structure(Structure structure);

}
