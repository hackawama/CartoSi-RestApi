package com.ab.cartosi.service;

import java.util.List;

import com.ab.cartosi.entities.devices.*;

public interface DeviceService {
    public Computer saveComputer(Computer computer);

    public List<DeviceType> getAllDeviceType();

    public List<Computer> getComputersByStructure(Long structureId);

    public Switch saveSwitch(Switch switchToSave);

    public List<Switch> getSwitchesByStructure(Long structureId);

    public Phone savePhone(Phone phone);

    public List<Phone> getPhonesByStructure(Long structureId);

}
