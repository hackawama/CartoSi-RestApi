package com.ab.cartosi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ab.cartosi.entities.Structure;
import com.ab.cartosi.repositories.StructureRepo;

@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    StructureRepo structureRepo;

    @Override
    public Structure StoreFile(MultipartFile file, Long id) {

        Structure st = structureRepo.findById(id).get();
        try {
            st.setImage(file.getBytes());
        } catch (IOException e) {

            e.printStackTrace();
        }
        structureRepo.save(st);
        return st;
    }

    @Override
    public Structure getStrucutre(Long id) {
        return structureRepo.findById(id).get();
    }

}
