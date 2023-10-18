package com.example.learn.venus.data;

import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;

import java.util.List;

public interface OrbiterRepository {
    List<Orbiter> findAll() throws DataAccessException;

    Orbiter findById(int orbiterId) throws DataAccessException;

    List<Orbiter> findByType(OrbiterType type) throws DataAccessException;

    Orbiter add(Orbiter orbiter) throws DataAccessException;

    boolean update(Orbiter orbiter) throws DataAccessException;

    boolean deleteBId(int orbiterId) throws DataAccessException;
}
