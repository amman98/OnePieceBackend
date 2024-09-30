package com.example.OnePieceBackend.pirate;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PirateService {
    
    private final PirateRepository pirateRepository;

    @Autowired
    public PirateService(PirateRepository pirateRepository) {
        this.pirateRepository = pirateRepository;
    }

    public Pirate getPirate(Long pirateId) {
        return pirateRepository.getReferenceById(pirateId);
    }

    public List<Pirate> getPiratesByCrew(Long crewId) {
        return pirateRepository.findPiratesByCrewId(crewId);
    }

    public void addNewPirate(Pirate pirate) { 
        if(pirate.getName().isBlank()) {
            throw new IllegalStateException("pirate must have a name");
        }
        
        pirateRepository.save(pirate);
    }

    public void deletePirate(Long pirateId) {
        if(!pirateRepository.existsById(pirateId)) {
            throw new IllegalStateException("pirate with id " + pirateId + " does not exist");
        }

        Pirate pirate = pirateRepository.getReferenceById(pirateId);

        if(pirate.getRole() != null && pirate.getRole().equals("Captain")) {
            throw new IllegalStateException("A captain can't abandon their crew");
        }

        pirateRepository.deleteById(pirateId);
    }

    @Transactional
    public void updatePirate(Long pirateId, String name, String epithet, String role, Integer age, String devilFruit) {
        Pirate pirate = pirateRepository.findById(pirateId)
                .orElseThrow(() -> new IllegalStateException(
                        "pirate with id " + pirateId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(pirate.getName(), name)) {
            pirate.setName(name);
        }

        if(epithet != null && epithet.length() > 0 && !Objects.equals(pirate.getEpithet(), epithet)) {
            pirate.setEpithet(epithet);
        }

        // no validation checks needed as there will be radio buttons in UI for determining role
        pirate.setRole(role);

        if(age != null && age > 0 && !Objects.equals(pirate.getAge(), age)) {
            pirate.setAge(age);
        }

        if(devilFruit != null && devilFruit.length() > 0 && !Objects.equals(pirate.getDevilFruit(), devilFruit)) {
            pirate.setDevilFruit(devilFruit);
        }
    }

    @Transactional
    public void changeCrews(Long pirateId, Long newCrewId) {
        Pirate pirate = pirateRepository.getReferenceById(pirateId);

        if(pirate.getRole().equals("Captain")) {
            throw new IllegalStateException("A captain can't abandon their crew");
        }

        pirate.setCrewId(newCrewId);
    }
}
