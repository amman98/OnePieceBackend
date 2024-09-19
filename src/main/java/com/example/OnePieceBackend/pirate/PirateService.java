package com.example.OnePieceBackend.pirate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PirateService {
    
    private final PirateRepository pirateRepository;

    @Autowired
    public PirateService(PirateRepository pirateRepository) {
        this.pirateRepository = pirateRepository;
    }

    public List<Pirate> getPiratesByCrew(int crewId) {
        return pirateRepository.findPiratesByCrew(crewId);
    }

    public void addNewPirate(Pirate pirate) {

    }

    public void deletePirate(Long pirateId) {

    }

    public void updatePirate(Long pirateId, String name, String epithet, Integer bounty, String role, Integer age, String devilFruit) {

    }

    public void changeCrews(Long pirateId, Long crewId) {
        
    }
}
