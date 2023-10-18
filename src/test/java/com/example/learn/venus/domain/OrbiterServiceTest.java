package com.example.learn.venus.domain;

import com.example.learn.venus.data.DataAccessException;
import com.example.learn.venus.data.OrbiterRepositoryDouble;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrbiterServiceTest {
    OrbiterService service = new OrbiterService(new OrbiterRepositoryDouble());
    @Test
    void shouldAddOrbiter(){

    }
    @Test
    void shouldNotAddNullOrbiter() throws DataAccessException {
        OrbiterResult result = service.add(null);
        assertFalse(result.isSuccess());

    }
    @Test
    void shouldNotAddAstronautWithNoRoom() throws DataAccessException {

    }


}