package com.ab.cartosi.entities.devices;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.ab.cartosi.entities.Network;
import com.ab.cartosi.entities.Room;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String label;
    @ManyToOne
    @JoinColumn(name = "ID_NETWORK")
    private Network network;
    @ManyToOne
    @JoinColumn(name = "ID_DEVICETYPE")
    private DeviceType deviceType;
    @OneToMany(mappedBy = "parentDevice")
    private List<Devices> devices;
    @ManyToOne
    @JoinColumn(name = "ID_ROOM")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Room room;
    @ManyToOne
    @JsonProperty(access = Access.WRITE_ONLY)
    private Devices parentDevice;
    @Transient
    private String path;

}
