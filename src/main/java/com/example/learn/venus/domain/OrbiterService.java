package com.example.learn.venus.domain;

import com.example.learn.venus.data.DataAccessException;
import com.example.learn.venus.data.OrbiterRepository;
import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;

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
        Orbiter o = repository.add(orbiter);
        res.setPayload(o);

        return res;
    }
    public OrbiterResult update(Orbiter orbiter) throws DataAccessException{
        OrbiterResult res = validateInputs(orbiter);
        if(!res.isSuccess()){
            return res;
        }
        Orbiter existing = repository.findById(orbiter.getOrbiterId());
        if(existing == null){
            res.addErrorMessages("Orbiter Id " + orbiter.getOrbiterId() + " not found.");
            return res;
        }

        if(existing.getType() != orbiter.getType()){
            res.addErrorMessages("Can not update type.");
            return res;
        }
        repository.update(orbiter);
        return res;
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


        if(orbiter.getType() == OrbiterType.ASTRONAUT
                || orbiter.getType() == OrbiterType.SHUTTLE){
            int astroCount = 0;
            int shuttleCount = 0;
            int modCount = 0;
            int dockCount = 0;
            for(Orbiter o: allOrbiters){
                switch(o.getType()){
                    case MODULE:
                        modCount++;
                        break;
                    case MODULE_WITH_DOCK:
                        dockCount++;
                        break;
                    case ASTRONAUT:
                        astroCount++;
                        break;
                    case SHUTTLE:
                        shuttleCount++;
                        break;
                }
            }
            if(orbiter.getType() == OrbiterType.ASTRONAUT){
                if(astroCount + 1 > modCount * 4 + dockCount * 2){
                    res.addErrorMessages("No room for an astronaut.");
                }
            }
            if(orbiter.getType() == OrbiterType.SHUTTLE){
                if(shuttleCount + 1 > dockCount){
                    res.addErrorMessages("No room for a shuttle.");
                }
            }

        }
        return res;
    }
}
