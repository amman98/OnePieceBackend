package com.example.OnePieceBackend.pirate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PirateServiceTest {
    
    @Mock
    private PirateRepository pirateRepository;
    private PirateService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PirateService(pirateRepository);
    }

    @Test
    void canGetAPirate() {
        // given
        Long pirateId = 1L;

        // when
        underTest.getPirate(pirateId);

        // then
        verify(pirateRepository).findById(pirateId);
    }   

    @Test
    void canGetAllPiratesInCrew() {
        // given
        Long crewId = 1L;

        // when
        underTest.getPiratesByCrew(crewId);

        // then
        verify(pirateRepository).findPiratesByCrewId(crewId);
    }

    @Test
    void canAddPirate() {
        // given
        Pirate chopper = new Pirate(
            "Tony Tony Chopper",
            "Cotton Candy Lover",
            1,
            "Doctor",
            17,
            "Human-Human",
            1L
        );

        Pirate originalChopper = new Pirate(
            chopper.getName(),
            chopper.getEpithet(),
            chopper.getBounty(),
            chopper.getRole(),
            chopper.getAge(),
            chopper.getDevilFruit(),
            chopper.getCrewId()
        );

        // when
        underTest.addNewPirate(chopper);

        // then
        ArgumentCaptor<Pirate> pirateArgumentCaptor = ArgumentCaptor.forClass(Pirate.class);

        verify(pirateRepository).save(pirateArgumentCaptor.capture());

        Pirate capturedPirate = pirateArgumentCaptor.getValue();

        assertThat(capturedPirate).isEqualTo(originalChopper);
    }

    @Test
    void willThrowWhenNameIsEmpty() {
        // given
        Pirate test = new Pirate(
            "",
            "Who knows",
            1,
            "No clue",
            1,
            null,
            1L
        );

        // when
        // then
        assertThatThrownBy(() -> underTest.addNewPirate(test))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("pirate must have a name");

        verify(pirateRepository, never()).save(any());
    }

    @Test
    void canDeletePirate() {
        // given
        Long pirateId = 1L;

        Pirate franky = new Pirate(
            pirateId,
            "Franky",
            "Iron Man",
            4,
            "Shipwright",
            36,
            null,
            1L
        );

        given(pirateRepository.existsById(pirateId))
                .willReturn(true);

        given(pirateRepository.getReferenceById(pirateId))
                .willReturn(franky);

        // when
        underTest.deletePirate(pirateId);

        // then
        verify(pirateRepository).deleteById(pirateId);
    }

    @Test
    void deleteWillThrowWhenPirateDoesNotExist() {
        // given
        Long pirateId = 1L;

        // when
        // then
        assertThatThrownBy(() -> underTest.deletePirate(pirateId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("pirate with id " + pirateId + " does not exist");

        verify(pirateRepository, never()).deleteById(any());
    }

    @Test
    void deleteWillThrowWhenPirateIsNull() {
        // given
        Long pirateId = 1L;

        Pirate vivi = new Pirate(
            pirateId,
            "Nefeltari Vivi",
            "Princess",  // Role is null
            0,
            null,
            18,
            null,
            1L
        );

        given(pirateRepository.existsById(pirateId)).willReturn(true);
        given(pirateRepository.getReferenceById(pirateId)).willReturn(vivi);

        // when
        underTest.deletePirate(pirateId);

        // then
        verify(pirateRepository).deleteById(pirateId);
    }

    @Test
    void deleteWillThrowWhenPirateRoleIsCaptain() {
        // given
        Long pirateId = 1L;

        Pirate caribou = new Pirate(
            pirateId,
            "Caribou",
            "Wet-Haired",
            2,
            "Captain",
            32,
            "Swamp-Swamp",
            2L
        );

        given(pirateRepository.existsById(pirateId))
                .willReturn(true);

        given(pirateRepository.getReferenceById(pirateId))
                .willReturn(caribou);

        //when
        //then
        assertThatThrownBy(() -> underTest.deletePirate(pirateId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("A captain can't abandon their crew");

        verify(pirateRepository, never()).deleteById(any());
    }

    @Test
    void canUpdatePirate() {
        // given
        Long pirateId = 1L;

        Pirate vivi = new Pirate(
            pirateId,
            "Nefeltari Vivi",
            "Princess",
            0,
            null,
            18,
            null,
            1L
        );

        Pirate robin = new Pirate(
            pirateId,
            "Nico Robin",
            "Devil Child",
            9,
            "Archaeologist",
            30,
            "Flower-Flower",
            1L
        );

        given(pirateRepository.existsById(pirateId))
                .willReturn(true);

        given(pirateRepository.getReferenceById(pirateId))
                .willReturn(vivi);

        // when
        underTest.updatePirate(pirateId, robin.getName(), robin.getEpithet(), robin.getRole(), robin.getAge(), robin.getDevilFruit());
    
        // then
        assertThat(vivi.getName()).isEqualTo(robin.getName());
        assertThat(vivi.getEpithet()).isEqualTo(robin.getEpithet());
        assertThat(vivi.getRole()).isEqualTo(robin.getRole());
        assertThat(vivi.getAge()).isEqualTo(robin.getAge());
        assertThat(vivi.getDevilFruit()).isEqualTo(robin.getDevilFruit());
    }

    @Test
    void canNotUpdateWhenValuesAreNull() {
        // given
        Long pirateId = 1L;

        Pirate kidd = new Pirate(
            pirateId,
            "Eustass Kidd",
            "Captain",
            10,
            "Captain",
            23,
            "Magnet-Magnet",
            1L
        );

        given(pirateRepository.existsById(pirateId))
                .willReturn(true);

        given(pirateRepository.getReferenceById(pirateId))
                .willReturn(kidd);

        // when
        underTest.updatePirate(pirateId, null, null, null, null, null);

        // then
        assertThat(kidd.getName()).isEqualTo("Eustass Kidd");
        assertThat(kidd.getEpithet()).isEqualTo("Captain");
        assertThat(kidd.getRole()).isEqualTo("Captain");
        assertThat(kidd.getAge()).isEqualTo(23);
        assertThat(kidd.getDevilFruit()).isEqualTo("Magnet-Magnet");
    }

    @Test
    void canNotUpdateWhenValuesAreEmpty() {
        // given
        Long pirateId = 1L;

        Pirate kidd = new Pirate(
            pirateId,
            "Eustass Kidd",
            "Captain",
            10,
            "Captain",
            23,
            "Magnet-Magnet",
            1L
        );

        given(pirateRepository.existsById(pirateId))
                .willReturn(true);

        given(pirateRepository.getReferenceById(pirateId))
                .willReturn(kidd);

        // when
        underTest.updatePirate(pirateId, "", "", "", 0, "");

        // then
        assertThat(kidd.getName()).isEqualTo("Eustass Kidd");
        assertThat(kidd.getEpithet()).isEqualTo("Captain");
        assertThat(kidd.getRole()).isEqualTo("Captain");
        assertThat(kidd.getAge()).isEqualTo(23);
        assertThat(kidd.getDevilFruit()).isEqualTo("Magnet-Magnet");
    }

    @Test
    void canNotUpdateWhenValuesAreEqual() {
        // given
        Long pirateId = 1L;
        Integer age = 23;

        Pirate kidd = new Pirate(
            pirateId,
            "Eustass Kidd",
            "Captain",
            10,
            "Captain",
            age,
            "Magnet-Magnet",
            1L
        );

        given(pirateRepository.existsById(pirateId))
                .willReturn(true);

        given(pirateRepository.getReferenceById(pirateId))
                .willReturn(kidd);

        // when
        underTest.updatePirate(pirateId, kidd.getName(), kidd.getEpithet(), kidd.getRole(), kidd.getAge(), kidd.getDevilFruit());

        // then
        
    }

    @Test
    void updateWillThrowWhenPirateDoesNotExist() {
        // given
        Long pirateId = 1L;

        Pirate brook = new Pirate(
            pirateId,
            "Brook",
            "Soul King",
            2,
            "Musician",
            90,
            "Soul-Soul",
            1L
        );

        // when
        // then
        assertThatThrownBy(() -> underTest.updatePirate(pirateId, brook.getName(), brook.getEpithet(), brook.getRole(), brook.getAge(), brook.getDevilFruit()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("pirate with id " + pirateId + " does not exist");

    }

    @Test
    void canChangeCrews() {
        // given
        Long pirateId = 1L;
        Long crewId = 2L;

        Pirate jinbe = new Pirate(
            pirateId,
            "Jinbe",
            "First Son of the Sea",
            10,
            "Helmsman",
            46,
            null,
            1L
        );

        given(pirateRepository.existsById(pirateId))
                .willReturn(true);
        
        given(pirateRepository.getReferenceById(pirateId))
                .willReturn(jinbe);

        // when
        underTest.changeCrews(pirateId, crewId);

        // then
        assertThat(jinbe.getCrewId()).isEqualTo(crewId);
    }

    @Test
    void changeCrewsWillThrowWhenPirateDoesNotExist() {
        // given
        Long pirateId = 1L;
        Long crewId = 1L;

        // when
        // then
        assertThatThrownBy(() -> underTest.changeCrews(pirateId, crewId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("pirate with id " + pirateId + " does not exist");
    }

    @Test
    void changeCrewsWillThrowWhenPirateRoleIsCaptain() {
        // given
        Long pirateId = 1L;
        Long crewId = 2L;

        Pirate law = new Pirate(
            pirateId,
            "Law",
            "Surgeon of Death",
            10,
            "Captain",
            26,
            "Op-OP",
            1L
        );

        given(pirateRepository.existsById(pirateId))
                .willReturn(true);
        
        given(pirateRepository.getReferenceById(pirateId))
                .willReturn(law);

        // when
        // then
        assertThatThrownBy(() -> underTest.changeCrews(pirateId, crewId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("A captain can't abandon their crew");

    }
}
