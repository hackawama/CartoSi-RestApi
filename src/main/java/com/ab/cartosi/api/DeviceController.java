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
import com.ab.cartosi.service.DeviceService;
import com.ab.cartosi.entities.devices.*;;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DeviceController {
    private DeviceService deviceService;
    JwtDecoder jwtDecoder;

    public DeviceController(DeviceService deviceService, JwtDecoder jwtDecoder) {
        this.deviceService = deviceService;
        this.jwtDecoder = jwtDecoder;
    }

    public Long getStrucureIdFromToken(String token) {
        Jwt jwt = jwtDecoder.decode(token.replace("Bearer ", ""));
        Long structureId = jwt.getClaim("strucureId");
        return structureId;
    }

    @GetMapping("/devicetypes")
    public ResponseEntity<List<DeviceType>> getAllNetworksByStructure() {

        return ResponseEntity.ok(deviceService.getAllDeviceType());
    }

    @PostMapping("/computers")
    public ResponseEntity<Computer> saveComputer(@RequestBody Computer computer,
            @RequestHeader(name = "Authorization") String token) {
        // Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(deviceService.saveComputer(computer));
    }

    @GetMapping("/computers")
    public ResponseEntity<List<Computer>> getComputersByStrucutureId(
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(deviceService.getComputersByStructure(structureId));
    }

    @PostMapping("/switches")
    public ResponseEntity<Switch> saveSwitch(@RequestBody Switch switchToSave,
            @RequestHeader(name = "Authorization") String token) {
        // Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(deviceService.saveSwitch(switchToSave));
    }

    @GetMapping("/switches")
    public ResponseEntity<List<Switch>> getSwitchesByStrucutureId(
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(deviceService.getSwitchesByStructure(structureId));
    }

    @PostMapping("/phones")
    public ResponseEntity<Phone> savePhone(@RequestBody Phone phone,
            @RequestHeader(name = "Authorization") String token) {
        // Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(deviceService.savePhone(phone));
    }

    @GetMapping("/phones")
    public ResponseEntity<List<Phone>> getPhonesByStrucutureId(
            @RequestHeader(name = "Authorization") String token) {
        Long structureId = getStrucureIdFromToken(token);
        return ResponseEntity.ok(deviceService.getPhonesByStructure(structureId));
    }

}
