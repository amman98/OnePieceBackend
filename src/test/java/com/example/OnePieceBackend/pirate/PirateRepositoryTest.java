package com.example.OnePieceBackend.pirate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PirateRepositoryTest {

    @Autowired
    private PirateRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
    
    @Test
    void itShouldFindPiratesByCrewId() {
        // given
        Long crewId = 1L;
        Pirate usopp = new Pirate(
            "Usopp", 
            "God",
            5,
            "Sniper",
            19,
            null,
            crewId
        );

        Pirate sanji = new Pirate(
            "Sanji",
            "Black Leg",
            10,
            "Cook",
            20,
            null,
            crewId
        );

        underTest.save(usopp);
        underTest.save(sanji);

        // when
        List<Pirate> expected = underTest.findPiratesByCrewId(crewId);
        
        List<Pirate> newPirates = new ArrayList<>();
        newPirates.add(usopp);
        newPirates.add(sanji);

        // then
        assertThat(expected).isEqualTo(newPirates);
    }

    @Test
    void itShouldNotFindPiratesByCrewId() {
        // given
        Long crewId = 1L;

        // when
        List<Pirate> pirates = underTest.findPiratesByCrewId(crewId);
        int expected = pirates.size();

        // then
        assertThat(expected).isEqualTo(0);
    }

}
