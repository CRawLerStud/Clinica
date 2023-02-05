package com.example.clinica.service;

import com.example.clinica.models.Sectie;
import com.example.clinica.repository.RepositoryException;
import com.example.clinica.repository.SectieRepository;

public class SectiiService {

    private final SectieRepository repository;

    public SectiiService(SectieRepository repository) {
        this.repository = repository;
    }

    public Sectie findOne(Long ID) throws RepositoryException{
        return repository.findOne(ID);
    }

    public Iterable<Sectie> findAll(){
        return repository.findAll();
    }
}
