package com.example.learn.venus.domain;

import com.example.learn.venus.data.DataAccessException;
import com.example.learn.venus.data.OrbiterRepository;
import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<OrbiterType, Integer>counts = countType();
        counts.put(orbiter.getType(), counts.get(orbiter.getType())+1);
        res = validateDomain(counts);
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
        boolean success = repository.update(orbiter);
        if(!success){
            res.addErrorMessages("Could not find OrbiterId " + orbiter.getOrbiterId());
        }
        return res;
    }
    public OrbiterResult deleteById(int orbiterId) throws DataAccessException{
        OrbiterResult res = new OrbiterResult();
        Orbiter orbiter = repository.findById(orbiterId);
        if(orbiter == null){
            res.addErrorMessages("Could not find orbiter Id " + orbiter);
            if(!res.isSuccess()){
                return res;
            }

            boolean success = repository.deleteBId(orbiterId);
            if(!success){
                res.addErrorMessages("Could not find OrbiterId " + orbiter.getOrbiterId());
            }

            return res;
        }

        Map<OrbiterType, Integer>counts = countType();
        counts.put(orbiter.getType(), counts.get(orbiter.getType())-1);

        return null;
    }
    private Map<OrbiterType, Integer> countType(){

        HashMap<OrbiterType, Integer> counts = new HashMap<>();
        counts.put(OrbiterType.MODULE,0);
        counts.put(OrbiterType.MODULE_WITH_DOCK,0);
        counts.put(OrbiterType.ASTRONAUT,0);
        counts.put(OrbiterType.SHUTTLE,0);
        counts.put(OrbiterType.VENUSIAN,0);
        try {
            List<Orbiter> allOrbiters = repository.findAll();
            for(Orbiter o: allOrbiters) {
                switch (o.getType()) {
                    case MODULE:
                        counts.put(OrbiterType.MODULE, counts.get(OrbiterType.MODULE) + 1);
                        break;
                    case MODULE_WITH_DOCK:
                        counts.put(OrbiterType.MODULE_WITH_DOCK, counts.get(OrbiterType.MODULE_WITH_DOCK) + 1);
                        break;
                    case ASTRONAUT:
                        counts.put(OrbiterType.ASTRONAUT, counts.get(OrbiterType.ASTRONAUT) + 1);
                        break;
                    case SHUTTLE:
                        counts.put(OrbiterType.SHUTTLE, counts.get(OrbiterType.SHUTTLE) + 1);
                        break;
                    case VENUSIAN:
                        counts.put(OrbiterType.VENUSIAN, counts.get(OrbiterType.VENUSIAN) + 1);
                        break;
                }
            }
        }catch(DataAccessException ex){

        }
        return counts;
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

    private OrbiterResult validateDomain(Map<OrbiterType, Integer> counts){

        int astroCount = counts.get(OrbiterType.ASTRONAUT);
        int shuttleCount = counts.get(OrbiterType.SHUTTLE);
        int modCount = counts.get(OrbiterType.MODULE);
        int dockCount =counts.get(OrbiterType.MODULE_WITH_DOCK);


        OrbiterResult res = new OrbiterResult();
        if(astroCount > modCount * 4 + dockCount * 2){
            res.addErrorMessages("No room for an astronaut.");
        }

        if(shuttleCount > dockCount){
            res.addErrorMessages("No room for a shuttle.");
        }

        return res;
    }
}
