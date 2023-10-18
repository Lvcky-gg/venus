package com.example.learn.venus.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrbiterServiceTest {
    OrbiterService service = new OrbiterService();
    @Test
    void shouldAddOrbiter(){

    }
    @Test
    void shouldNotAddNullOrbiter(){
        OrbiterResult result = service.add(null);
        assertFalse(result.isSuccess());

    }


}