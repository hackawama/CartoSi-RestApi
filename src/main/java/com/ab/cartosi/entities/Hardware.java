package com.ab.cartosi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//REVIEWED
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    @ManyToOne
    @JoinColumn(name = "ID_CAT")
    private CategoryHardware categorie;
    @ManyToOne
    @JoinColumn(name = "ID_BRAND")
    private Brand brand;

}
