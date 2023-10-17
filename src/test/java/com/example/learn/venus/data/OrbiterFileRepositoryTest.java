package com.example.learn.venus.data;

import com.example.learn.venus.models.Orbiter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrbiterFileRepositoryTest {

    private OrbiterFileRepository repo = new OrbiterFileRepository("./data/Orbiters.csv");

    @Test
    void shouldFindFiveOrbiters(){
        List<Orbiter> actual = repo.findAll();
        assertNotNull(actual);
        assertEquals(5, actual.size());
    }
    @Test
    void shouldFindOrbiter(){
        Orbiter kalesh = repo.findById(4);
        assertNotNull(kalesh);
        assertEquals("Kalesh Kavon",kalesh.getName());
    }
    @Test
    void shouldNotFindOrbiter(){
        Orbiter no = repo.findById(1000000);
        assertNull(no);

    }


}