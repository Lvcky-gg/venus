package com.example.learn.venus.data;

import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;

import java.util.ArrayList;
import java.util.List;

public class OrbiterRepositoryDouble implements OrbiterRepository{
    private ArrayList<Orbiter> orbiters = new ArrayList<>();

    public OrbiterRepositoryDouble(){
        Orbiter moduleWithDock = new Orbiter();
        moduleWithDock.setOrbiterId(1);
        moduleWithDock.setName("Mod with Dock");
        moduleWithDock.setType(OrbiterType.MODULE_WITH_DOCK);
        orbiters.add(moduleWithDock);
        orbiters.add(new Orbiter(OrbiterType.ASTRONAUT,"Astro #1",2, null));
        orbiters.add(new Orbiter(OrbiterType.ASTRONAUT,"Astro #2",3, null));
        orbiters.add(new Orbiter(OrbiterType.SHUTTLE,"Shuttle #1",4, null));
    }

    @Override
    public List<Orbiter> findAll() throws DataAccessException {
        return new ArrayList<>(orbiters);
    }

    @Override
    public Orbiter findById(int orbiterId) throws DataAccessException {
        for (Orbiter o: orbiters){
            if(o.getOrbiterId() == orbiterId){
                return o;
            }
        }
        return null;
    }

    @Override
    public List<Orbiter> findByType(OrbiterType type) throws DataAccessException {
        ArrayList<Orbiter> res = new ArrayList<>();
        for(Orbiter o: orbiters){
            if(type == o.getType()){
                res.add(o);
            }
        }
        return res;
    }

    @Override
    public Orbiter add(Orbiter orbiter) throws DataAccessException {
        orbiters.add(orbiter);
        return orbiter;
    }

    @Override
    public boolean update(Orbiter orbiter) throws DataAccessException {
        return true;
    }

    @Override
    public boolean deleteBId(int orbiterId) throws DataAccessException {
        return true;
    }
}
