package com.example.clinica.service;

import com.example.clinica.models.Medic;
import com.example.clinica.repository.MedicRepository;

public class MedicService {

    private final MedicRepository repository;

    public MedicService(MedicRepository repository) {
        this.repository = repository;
    }

    public Iterable<Medic> findAllForSectie(Long idSectie){
        return repository.findAllInSectie(idSectie);
    }

    public Iterable<Medic> findAll(){
        return repository.findAll();
    }

}
