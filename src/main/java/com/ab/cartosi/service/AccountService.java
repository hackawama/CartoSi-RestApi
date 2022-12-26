package com.ab.cartosi.service;

import java.util.List;

import com.ab.cartosi.entities.AppUser;
import com.ab.cartosi.entities.Role;

public interface AccountService {
    AppUser addNewUser(AppUser aU);

    AppUser addNewAdmin();

    Role addNewRole(Role aR);

    void addRoleToUser(String username, String roleName);

    void intialStrucutres();

    AppUser loadUserByUsername(String username);

    Role loadRoleByName(String username);

    List<AppUser> listUers();
}