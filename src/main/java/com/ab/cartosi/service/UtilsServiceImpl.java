package com.ab.cartosi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ab.cartosi.entities.Building;
import com.ab.cartosi.entities.Network;
import com.ab.cartosi.entities.Room;
import com.ab.cartosi.entities.Stage;
import com.ab.cartosi.entities.devices.Devices;
import com.ab.cartosi.entities.Structure;
import com.ab.cartosi.repositories.BuildingRepo;
import com.ab.cartosi.repositories.NetworkRepo;
import com.ab.cartosi.repositories.RooRepo;
import com.ab.cartosi.repositories.StageRepo;
import com.ab.cartosi.repositories.StructureRepo;

@Service
public class UtilsServiceImpl implements UtilsService {

    private NetworkRepo networkRepo;
    private StructureRepo structureRepo;
    private BuildingRepo buildingRepo;
    private StageRepo stageRepo;
    private RooRepo rooRepo;

    public UtilsServiceImpl(NetworkRepo networkRepo, StructureRepo structureRepo, BuildingRepo buildingRepo,
            StageRepo stageRepo, RooRepo rooRepo) {
        this.networkRepo = networkRepo;
        this.structureRepo = structureRepo;
        this.buildingRepo = buildingRepo;
        this.stageRepo = stageRepo;
        this.rooRepo = rooRepo;
    }

    @Override
    public Network saveNEtwork(Network network, Long structureID) {
        Structure structure = structureRepo.findById(structureID).get();
        network.setStructure(structure);
        return networkRepo.save(network);
    }

    @Override
    public List<Network> getAllNetworksByStructureId(Long structureId) {
        Structure structure = structureRepo.findById(structureId).get();
        return networkRepo.findAllByStructure(structure);
    }

    @Override
    public List<Building> getAllBuildingsByStructureId(Long structureId) {
        Structure structure = structureRepo.findById(structureId).get();
        return buildingRepo.findAllByStructure(structure);
    }

    @Override
    public List<Building> saveBuildingByStructure(Building building, Long structureId) {
        Structure structure = structureRepo.findById(structureId).get();
        building.setStructure(structure);
        buildingRepo.save(building);
        return getAllBuildingsByStructureId(structureId);
    }

    @Override
    public List<Stage> getAllStagesByStructure(Long structureId) {
        Structure structure = structureRepo.findById(structureId).get();
        List<Stage> stages = stageRepo.findByBuilding_Structure(structure);
        for (Stage stage : stages) {
            stage.setBuildingName(stage.getBuilding().getLabel());
        }
        return stages;
    }

    @Override
    public List<Stage> saveStageByStructure(Stage stage, Long structureId) {
        stageRepo.save(stage);
        return getAllStagesByStructure(structureId);
    }

    @Override
    public List<Room> getAllRoomsByStructure(Long structureId) {
        Structure structure = structureRepo.findById(structureId).get();
        List<Room> rooms = rooRepo.findByStage_Building_Structure(structure);
        for (Room room : rooms) {
            room.setPath(room.getStage().getBuilding().getLabel() + "/" + room.getStage().getLabel());
        }
        return rooms;
    }

    @Override
    public Room saveRoom(Room room, Long structureId) {
        return rooRepo.save(room);
    }

    @Override
    public Building getBuildingById(long l) {
        Building building = buildingRepo.findById(l).get();
        List<Devices> devices = new ArrayList<>();
        for (Stage stage : building.getStages()) {
            for (Room room : stage.getRooms()) {
                for (Devices device : room.getDevices()) {
                    if (device.getParentDevice() == null) {
                        devices.add(device);
                    }

                }
                room.setDevices(devices);
            }
        }
        return building;
    }

}
