package com.example.clinica.models;

import java.util.Objects;

public class Medic extends Entity<Long>{

    private Long id_sectie;
    private String nume;
    private String prenume;
    private Integer vechime;
    private boolean rezident;

    public Medic(Long ID, Long id_sectie, String nume, String prenume, boolean rezident, Integer vechime) {
        this.setID(ID);
        this.id_sectie = id_sectie;
        this.nume = nume;
        this.prenume = prenume;
        this.vechime = vechime;
        this.rezident = rezident;
    }

    public Long getId_sectie() {
        return id_sectie;
    }

    public void setId_sectie(Long id_sectie) {
        this.id_sectie = id_sectie;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public Integer getVechime() {
        return vechime;
    }

    public boolean isRezident() {
        return rezident;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setVechime(Integer vechime) {
        this.vechime = vechime;
    }

    public void setRezident(boolean rezident) {
        this.rezident = rezident;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medic)) return false;
        Medic medic = (Medic) o;
        return getId().equals(medic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Dr. " + prenume + " " + nume;
    }
}
