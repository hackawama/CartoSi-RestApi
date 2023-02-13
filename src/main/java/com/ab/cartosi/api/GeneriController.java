package com.ab.cartosi.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ab.cartosi.entities.Building;
import com.ab.cartosi.entities.Network;
import com.ab.cartosi.entities.Room;
import com.ab.cartosi.entities.Stage;
import com.ab.cartosi.repositories.RooRepo;
import com.ab.cartosi.service.UtilsService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneriController {

    UtilsService utilsService;
    JwtDecoder jwtDecoder;

    public GeneriController(UtilsService utilsService, JwtDecoder jwtDecoder, RooRepo rooRepo) {
        this.utilsService = utilsService;
        this.jwtDecoder = jwtDecoder;
    }

    public Long getStrucureIdFromToken(String token) {
        Jwt jwt = jwtDecoder.decode(token.replace("Bearer ", ""));
        Long structureId = jwt.getClaim("strucureId");
        return structureId;
    }

    @PostMapping("/networks")
    public ResponseEntity<List<Network>> saveNetwork(@RequestBody Network network,
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        utilsService.saveNEtwork(network, structureId);
        return ResponseEntity.ok(utilsService.getAllNetworksByStructureId(structureId));
    }

    @GetMapping("/networks")
    public ResponseEntity<List<Network>> getAllNetworksByStructure(
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(utilsService.getAllNetworksByStructureId(structureId));
    }

    @PostMapping("/buildings")
    public ResponseEntity<List<Building>> saveBuilding(@RequestBody Building building,
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(utilsService.saveBuildingByStructure(building, structureId));
    }

    @GetMapping("/buildings")
    public ResponseEntity<List<Building>> getAlBuildingsByStructure(
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(utilsService.getAllBuildingsByStructureId(structureId));
    }

    @GetMapping("/buildings/1")
    public ResponseEntity<Building> getBuildingById() {

        return ResponseEntity.ok(utilsService.getBuildingById(1L));
    }

    @GetMapping("/stages")
    public ResponseEntity<List<Stage>> GetStagesByStructure(
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(utilsService.getAllStagesByStructure(structureId));
    }

    @PostMapping("/stages")
    public ResponseEntity<List<Stage>> saveBuilding(@RequestBody Stage stage,
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(utilsService.saveStageByStructure(stage, structureId));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getRoomsByStructure(
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(utilsService.getAllRoomsByStructure(structureId));
    }

    @PostMapping("/rooms")
    public ResponseEntity<Room> saveRoom(@RequestBody Room room,
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);

        return ResponseEntity.ok(utilsService.saveRoom(room, structureId));
    }

}
