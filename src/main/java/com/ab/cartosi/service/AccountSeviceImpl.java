package com.ab.cartosi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ab.cartosi.entities.AppUser;
import com.ab.cartosi.entities.Role;
import com.ab.cartosi.entities.Structure;
import com.ab.cartosi.repositories.AppUserRepo;
import com.ab.cartosi.repositories.RoleRepo;
import com.ab.cartosi.repositories.StructureRepo;

@Service
public class AccountSeviceImpl implements AccountService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AppUserRepo userRepo;
    @Autowired
    StructureRepo sRepo;
    @Autowired
    RoleRepo rRepo;

    @Override
    public AppUser addNewUser(AppUser aU) {
        AppUser check = loadUserByUsername(aU.getName());
        if (check == null) {
            String pw = aU.getPassword();
            aU.setPassword(passwordEncoder.encode(pw));
            return userRepo.save(aU);
        } else
            throw new RuntimeException("user Exist");
    }

    @Override
    public AppUser addNewAdmin() {

        return null;
    }

    @Override
    public Role addNewRole(Role aR) {

        return rRepo.save(aR);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return userRepo.findByName(username);
    }

    @Override
    public Role loadRoleByName(String role) {

        return rRepo.findByRole(role);
    }

    @Override
    public List<AppUser> listUers() {

        return null;
    }

    @Override
    public void intialStrucutres() {
        if (sRepo.findAll().isEmpty()) {
            System.out.println("-----INTIALISATION STRUCTURES-----");
            String[] structs = { "I.G", "INSP.INF", "DPO", "BR" };

            for (String string : structs) {
                Structure structure = new Structure();
                structure.setLabel(string);
                sRepo.save(structure);
            }
        }
    }

}
