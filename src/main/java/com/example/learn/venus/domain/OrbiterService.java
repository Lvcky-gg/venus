package com.example.learn.venus.domain;

import com.example.learn.venus.data.DataAccessException;
import com.example.learn.venus.data.OrbiterRepository;
import com.example.learn.venus.models.Orbiter;
import java.util.List;

public class OrbiterService {
    private final OrbiterRepository repository;
    public OrbiterService(OrbiterRepository repository){
        this.repository = repository;
    }

    public OrbiterResult add(Orbiter orbiter) throws DataAccessException {
        OrbiterResult res = validateInputs(orbiter);
        if(!res.isSuccess()){
            return res;
        }
        res = validateDomain(orbiter);
        if(!res.isSuccess()){
            return res;
        }

        return null;
    }
    private OrbiterResult validateInputs(Orbiter orbiter){
        OrbiterResult res = new OrbiterResult();
        if(orbiter == null){
            res.addErrorMessages("Orbiter cannot be null");
            return res;
        }
        if(orbiter.getName() == null || orbiter.getName().trim().length() == 0){
            res.addErrorMessages("name is required.");
        }
        return res;
    }

    private OrbiterResult validateDomain(Orbiter orbiter) throws DataAccessException {
        OrbiterResult res = validateInputs(orbiter);
        List<Orbiter> allOrbiters = repository.findAll();
        return res;
    }
}
