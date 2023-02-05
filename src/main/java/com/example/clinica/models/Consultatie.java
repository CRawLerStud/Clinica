package com.example.clinica.models;

import java.time.LocalDate;
import java.util.Objects;

public class Consultatie extends Entity<Long>{

    private Long idMedic;
    private Long CNPPacient;
    private String numePacient;
    private LocalDate data;
    private Integer ora;

    public Consultatie(Long ID, Long idMedic, Long CNPPacient, String numePacient, LocalDate data, Integer ora) {
        this.setID(ID);
        this.idMedic = idMedic;
        this.CNPPacient = CNPPacient;
        this.numePacient = numePacient;
        this.data = data;
        this.ora = ora;
    }

    public Long getIdMedic() {
        return idMedic;
    }

    public Long getCNPPacient() {
        return CNPPacient;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public LocalDate getData() {
        return data;
    }

    public Integer getOra() {
        return ora;
    }

    public void setIdMedic(Long idMedic) {
        this.idMedic = idMedic;
    }

    public void setCNPPacient(Long CNPPacient) {
        this.CNPPacient = CNPPacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setOra(Integer ora) {
        this.ora = ora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consultatie)) return false;
        Consultatie that = (Consultatie) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
