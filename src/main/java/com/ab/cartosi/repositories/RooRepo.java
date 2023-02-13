package com.ab.cartosi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.cartosi.entities.Room;
import com.ab.cartosi.entities.Structure;

public interface RooRepo extends JpaRepository<Room, Long> {
    List<Room> findByStage_Building_Structure(Structure structure);
}
