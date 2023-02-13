package com.ab.cartosi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ab.cartosi.entities.devices.*;
import com.ab.cartosi.entities.Structure;

import com.ab.cartosi.repositories.TypeDeviceRepo;
import com.ab.cartosi.repositories.devices.*;
import com.ab.cartosi.repositories.StructureRepo;

@Service
public class DeviceServiceImpl implements DeviceService {

    private ComputerRepo computerRepo;
    private SwitchRepo switchRepo;
    private PhoneRepo phoneRepo;
    private TypeDeviceRepo typeDeviceRepo;
    private StructureRepo structureRepo;

    public DeviceServiceImpl(ComputerRepo computerRepo, SwitchRepo switchRepo, PhoneRepo phoneRepo,
            TypeDeviceRepo typeDeviceRepo, StructureRepo structureRepo) {
        this.computerRepo = computerRepo;
        this.switchRepo = switchRepo;
        this.phoneRepo = phoneRepo;
        this.typeDeviceRepo = typeDeviceRepo;
        this.structureRepo = structureRepo;
    }

    @Override
    public Computer saveComputer(Computer computer) {
        return computerRepo.save(computer);
    }

    @Override
    public List<DeviceType> getAllDeviceType() {
        return typeDeviceRepo.findAll();
    }

    @Override
    public List<Computer> getComputersByStructure(Long structureId) {
        Structure structure = structureRepo.findById(structureId).get();
        List<Computer> computers = computerRepo.findByRoom_Stage_Building_Structure(structure);
        for (Computer computer : computers) {
            computer.setPath(computer.getRoom().getStage().getBuilding().getLabel() + "/" +
                    computer.getRoom().getStage().getLabel() + "/" +
                    computer.getRoom().getLabel());
        }
        return computerRepo.findByRoom_Stage_Building_Structure(structure);
    }

    @Override
    public Switch saveSwitch(Switch switchToSave) {
        return switchRepo.save(switchToSave);
    }

    @Override
    public List<Switch> getSwitchesByStructure(Long structureId) {
        Structure structure = structureRepo.findById(structureId).get();
        List<Switch> switches = switchRepo.findByRoom_Stage_Building_Structure(structure);
        for (Switch switchO : switches) {
            switchO.setPath(switchO.getRoom().getStage().getBuilding().getLabel() + "/" +
                    switchO.getRoom().getStage().getLabel() + "/" +
                    switchO.getRoom().getLabel());
        }
        return switches;
    }

    @Override
    public Phone savePhone(Phone phone) {
        return phoneRepo.save(phone);
    }

    @Override
    public List<Phone> getPhonesByStructure(Long structureId) {
        Structure structure = structureRepo.findById(structureId).get();
        List<Phone> phones = phoneRepo.findByRoom_Stage_Building_Structure(structure);
        for (Phone phone : phones) {
            phone.setPath(phone.getRoom().getStage().getBuilding().getLabel() + "/"
                    + phone.getRoom().getStage().getLabel() + "/" +
                    phone.getRoom().getLabel());
        }
        return phones;
    }
}
