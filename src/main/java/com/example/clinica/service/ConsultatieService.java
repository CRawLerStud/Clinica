package com.example.clinica.service;

import com.example.clinica.models.Consultatie;
import com.example.clinica.models.Medic;
import com.example.clinica.repository.ConsultatieRepository;
import com.example.clinica.utils.observer.Observer;
import com.example.clinica.utils.utils.ConsultatieEvent;
import com.example.clinica.validation.ConsultatieValidator;
import com.example.clinica.validation.ValidationException;

public class ConsultatieService {

    private final ConsultatieValidator validator;
    private final ConsultatieRepository repository;

    public ConsultatieService(ConsultatieValidator validator, ConsultatieRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public Iterable<Consultatie> findAllForMedic(Medic medic){
        return repository.findAllForMedic(medic);
    }

    public Long save(Consultatie consultatie) throws ValidationException {
        validator.validate(consultatie);
        return repository.save(consultatie);
    }

    public void addObserver(Observer<ConsultatieEvent> eventObserver){
        repository.addObserver(eventObserver);
    }
}
