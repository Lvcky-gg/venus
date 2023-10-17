package com.example.learn.venus.data;

import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrbiterFileRepositoryTest {
    private static final String SEED_PATH = "./data/orbiters-seed.csv";
    private static final String TEST_PATH = "./data/orbiters-test.csv";

    private OrbiterFileRepository repo = new OrbiterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH),
                Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

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
    @Test
    void shouldFindOneOfEachType(){
        List<Orbiter> modules = repo.findByType(OrbiterType.MODULE);
        List<Orbiter> modulesWithDocks = repo.findByType(OrbiterType.MODULE_WITH_DOCK);
        List<Orbiter> shuttle = repo.findByType(OrbiterType.SHUTTLE);
        List<Orbiter> astronaut = repo.findByType(OrbiterType.ASTRONAUT);
        List<Orbiter> venusian = repo.findByType(OrbiterType.VENUSIAN);

        assertNotNull(modules);
        assertEquals(1, modules.size());

        assertNotNull(modulesWithDocks);
        assertEquals(1, modules.size());

        assertNotNull(shuttle);
        assertEquals(1, modules.size());

        assertNotNull(astronaut);
        assertEquals(1, modules.size());

        assertNotNull(venusian);
        assertEquals(1, modules.size());

    }

    @Test
    void shouldAddOrbiter() throws DataAccessException {
        Orbiter orbiter = new Orbiter();
        orbiter.setType(OrbiterType.MODULE);
        orbiter.setName("TEST");
        orbiter.setSponsor("TEST");
        Orbiter actual = repo.add(orbiter);

        assertNotNull(actual);
        assertEquals(6, actual.getOrbiterId());
    }


}