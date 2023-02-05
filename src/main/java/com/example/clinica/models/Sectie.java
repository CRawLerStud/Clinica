package com.example.clinica.models;

import java.util.Objects;

public class Sectie extends Entity<Long> {

    private String nume;
    private Long idSefSectie;
    private Integer pretPerConsultatie;
    private Integer durataMaximaConsultatie;

    public Sectie(Long ID, String nume, Long idSefSectie, Integer pretPerConsultatie, Integer durataMaximaConsultatie) {
        this.setID(ID);
        this.nume = nume;
        this.idSefSectie = idSefSectie;
        this.pretPerConsultatie = pretPerConsultatie;
        this.durataMaximaConsultatie = durataMaximaConsultatie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Long getIdSefSectie() {
        return idSefSectie;
    }

    public void setIdSefSectie(Long idSefSectie) {
        this.idSefSectie = idSefSectie;
    }

    public Integer getPretPerConsultatie() {
        return pretPerConsultatie;
    }

    public void setPretPerConsultatie(Integer pretPerConsultatie) {
        this.pretPerConsultatie = pretPerConsultatie;
    }

    public Integer getDurataMaximaConsultatie() {
        return durataMaximaConsultatie;
    }

    public void setDurataMaximaConsultatie(Integer durataMaximaConsultatie) {
        this.durataMaximaConsultatie = durataMaximaConsultatie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sectie)) return false;
        Sectie sectie = (Sectie) o;
        return getId().equals(sectie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
