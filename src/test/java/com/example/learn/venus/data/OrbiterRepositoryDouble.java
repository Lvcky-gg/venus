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
    }

    @Override
    public List<Orbiter> findAll() throws DataAccessException {
        return new ArrayList<>(orbiters);
    }

    @Override
    public Orbiter findById(int orbiterId) throws DataAccessException {
        return null;
    }

    @Override
    public List<Orbiter> findByType(OrbiterType type) throws DataAccessException {
        return null;
    }

    @Override
    public Orbiter add(Orbiter orbiter) throws DataAccessException {
        orbiters.add(orbiter);
        return orbiter;
    }

    @Override
    public boolean update(Orbiter orbiter) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteBId(int orbiterId) throws DataAccessException {
        return false;
    }
}
