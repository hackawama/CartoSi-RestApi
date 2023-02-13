package com.ab.cartosi.entities.devices;

import javax.persistence.Entity;

@Entity
public class Phone extends Devices {

    private String phoneNumber;

    public Phone() {
        super();

    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
