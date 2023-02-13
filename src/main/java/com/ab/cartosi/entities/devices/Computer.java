package com.ab.cartosi.entities.devices;

import javax.persistence.Entity;

@Entity
public class Computer extends Devices {

    private String ipAdddress;

    public Computer() {
        super();

    }

    public String getIpAdddress() {
        return ipAdddress;
    }

    public void setIpAdddress(String ipAdddress) {
        this.ipAdddress = ipAdddress;
    }

}
