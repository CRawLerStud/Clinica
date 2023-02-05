package com.example.clinica.models;

import java.io.Serializable;

public class Entity<ID> implements Serializable {

    private static final long serialVersionUID = 1562341231231L;
    private ID id;

    public ID getId(){
        return this.id;
    }

    public void setID(ID id){
        this.id = id;
    }
}
