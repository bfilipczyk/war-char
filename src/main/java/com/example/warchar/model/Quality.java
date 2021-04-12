package com.example.warchar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descrition;

    public Quality(Long id, String descrition) {
        this.id = id;
        this.descrition = descrition;
    }

    public Quality() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }
}
