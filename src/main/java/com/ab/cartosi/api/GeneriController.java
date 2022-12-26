package com.ab.cartosi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ab.cartosi.entities.Structure;
import com.ab.cartosi.service.GenericService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneriController {

    GenericService genSvc;

    public GeneriController(GenericService genSvc) {
        this.genSvc = genSvc;
    }

    @PostMapping("/upload-image")
    public ResponseEntity<Structure> handleImageUpload(@RequestParam("image") MultipartFile image,
            @RequestParam("id") Long id) {
        Structure structure = genSvc.StoreFile(image, id);
        return ResponseEntity.ok(structure);
    }

    @GetMapping("/structure/{id}")
    public ResponseEntity<Structure> getStrucuture(@PathVariable Long id) {
        return ResponseEntity.ok(genSvc.getStrucutre(id));
    }

}
