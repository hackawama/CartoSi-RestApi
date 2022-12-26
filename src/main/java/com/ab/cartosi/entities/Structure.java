package com.ab.cartosi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//REviewed
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    @ManyToMany
    private List<Hardware> hardwares;
    @ManyToMany
    private List<Software> softwares;

    private byte[] image;

}
