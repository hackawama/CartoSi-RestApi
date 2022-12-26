package com.ab.cartosi.service;

import org.springframework.web.multipart.MultipartFile;

import com.ab.cartosi.entities.Structure;

public interface GenericService {
    public Structure StoreFile(MultipartFile file, Long id);

    public Structure getStrucutre(Long id);
}
