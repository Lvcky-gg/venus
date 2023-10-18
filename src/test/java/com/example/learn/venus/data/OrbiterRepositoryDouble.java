package com.example.learn.venus.data;

import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;

import java.util.List;

public class OrbiterRepositoryDouble implements OrbiterRepository{

    @Override
    public List<Orbiter> findAll() throws DataAccessException {
        return null;
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
        return null;
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
