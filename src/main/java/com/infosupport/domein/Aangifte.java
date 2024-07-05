package com.infosupport.domein;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aangifte {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bsn;
    private double bedrag;

    private transient String _self;

    public Aangifte(String bsn, double bedrag) {
        this.bsn = bsn;
        this.bedrag = bedrag;
    }
}
