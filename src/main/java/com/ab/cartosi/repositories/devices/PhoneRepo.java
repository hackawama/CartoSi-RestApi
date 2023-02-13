package com.ab.cartosi.repositories.devices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.cartosi.entities.devices.Phone;
import com.ab.cartosi.entities.Structure;
import java.util.List;

public interface PhoneRepo extends JpaRepository<Phone, Long> {
    List<Phone> findByRoom_Stage_Building_Structure(Structure structure);

}
