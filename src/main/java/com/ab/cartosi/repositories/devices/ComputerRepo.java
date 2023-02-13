package com.ab.cartosi.repositories.devices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import java.util.List;
import com.ab.cartosi.entities.devices.Computer;
import com.ab.cartosi.entities.Structure;

@RepositoryRestController
public interface ComputerRepo extends JpaRepository<Computer, Long> {
    List<Computer> findByRoom_Stage_Building_Structure(Structure structure);

}
