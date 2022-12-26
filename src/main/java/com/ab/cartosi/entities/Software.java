package com.ab.cartosi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Reviewed
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String version;
    @ManyToOne
    @JoinColumn(name = "ID_TYPE")
    private TypeSoftware type;
    @ManyToOne
    @JoinColumn(name = "ID_BRAND")
    private Brand brand;
}
