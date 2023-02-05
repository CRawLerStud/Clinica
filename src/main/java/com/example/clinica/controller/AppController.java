package com.example.clinica.controller;

import com.example.clinica.models.Consultatie;
import com.example.clinica.models.Medic;
import com.example.clinica.models.Sectie;
import com.example.clinica.repository.RepositoryException;
import com.example.clinica.service.ConsultatieService;
import com.example.clinica.service.MedicService;
import com.example.clinica.service.SectiiService;
import com.example.clinica.utils.observer.Observer;
import com.example.clinica.utils.utils.ConsultatieEvent;
import com.example.clinica.validation.ValidationException;

public class AppController {

    private final MedicService medics;
    private final SectiiService sectii;
    private final ConsultatieService consultatii;

    public AppController(MedicService medics, SectiiService sectii, ConsultatieService consultatii) {
        this.medics = medics;
        this.sectii = sectii;
        this.consultatii = consultatii;
    }

    public Sectie findSectie(Long ID) throws RepositoryException{
        return sectii.findOne(ID);
    }

    public Iterable<Medic> findAllMedicsForSectie(Long idSectie){
        return medics.findAllForSectie(idSectie);
    }

    public Iterable<Medic> findAllMedics(){
        return medics.findAll();
    }

    public Iterable<Sectie> findAllSectii(){
        return sectii.findAll();
    }

    public Iterable<Consultatie> findAllConsultatiiForMedic(Medic medic){
        return consultatii.findAllForMedic(medic);
    }

    public Long saveConsultatie(Consultatie consultatie) throws ValidationException {
        return consultatii.save(consultatie);
    }

    public void addConsultatieObservers(Observer<ConsultatieEvent> eventObserver){
        consultatii.addObserver(eventObserver);
    }

}
