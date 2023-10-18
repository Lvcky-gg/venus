package com.example.learn.venus.domain;

import com.example.learn.venus.data.DataAccessException;
import com.example.learn.venus.data.OrbiterRepository;
import com.example.learn.venus.data.OrbiterRepositoryDouble;
import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;
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
        OrbiterResult res = service.add(new Orbiter(OrbiterType.ASTRONAUT,"TEST", 0,null));
        assertFalse(res.isSuccess());
    }
    @Test
    void shouldBeAbleToAddAstronaut() throws DataAccessException{
       service.add(new Orbiter(OrbiterType.MODULE,"TEST", 0,null));
        OrbiterResult res = service.add(new Orbiter(OrbiterType.ASTRONAUT,"TEST", 0,null));
        assertTrue(res.isSuccess());
        assertNotNull(res.getPayload());

    }
    @Test
    void shouldNotAddShuttleWithNoRoom() throws DataAccessException{
        OrbiterResult res = service.add(new Orbiter(OrbiterType.SHUTTLE,"TEST", 0,null));
        assertFalse(res.isSuccess());
    }
    @Test
    void shouldAddShuttleWithNoRoom() throws DataAccessException{
        service.add(new Orbiter(OrbiterType.MODULE_WITH_DOCK,"TEST Dock", 0,null));
        OrbiterResult res = service.add(new Orbiter(OrbiterType.SHUTTLE,"TEST", 0,null));
        assertTrue(res.isSuccess());
    }
    @Test
    void shouldUpdate() throws DataAccessException{
        OrbiterResult res = service.update(new Orbiter(OrbiterType.ASTRONAUT,"TEST UPDATE", 3,null));
        assertTrue(res.isSuccess());
    }
    @Test
    void shouldNotUpdateType() throws DataAccessException {
        OrbiterResult res = service.update(new Orbiter(OrbiterType.VENUSIAN,"TEST UPDATE", 3,null));
        assertFalse(res.isSuccess());
    }



}