package com.ab.cartosi.service;

import java.util.List;

import com.ab.cartosi.entities.Building;
import com.ab.cartosi.entities.Network;
import com.ab.cartosi.entities.Room;
import com.ab.cartosi.entities.Stage;

public interface UtilsService {
    Network saveNEtwork(Network network, Long structureID);

    List<Network> getAllNetworksByStructureId(Long structureId);

    List<Building> getAllBuildingsByStructureId(Long structureId);

    List<Building> saveBuildingByStructure(Building building, Long structureId);

    List<Stage> getAllStagesByStructure(Long structureId);

    List<Stage> saveStageByStructure(Stage stage, Long structureId);

    List<Room> getAllRoomsByStructure(Long structureId);

    Room saveRoom(Room room, Long structureId);

    Building getBuildingById(long l);
}
