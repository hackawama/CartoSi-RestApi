package com.ab.cartosi.repositories.devices;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.ab.cartosi.entities.devices.Switch;
import com.ab.cartosi.entities.Structure;

public interface SwitchRepo extends JpaRepository<Switch, Long> {
    List<Switch> findByRoom_Stage_Building_Structure(Structure structure);

}
