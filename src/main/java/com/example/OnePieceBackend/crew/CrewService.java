package com.example.OnePieceBackend.crew;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;

import jakarta.transaction.Transactional;

@Service
public class CrewService {

    private final CrewRepository crewRepository;

    @Autowired
    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public Optional<Crew> getCrew(Long crewId) {
        return crewRepository.findById(crewId);
    }

    public List<Crew> getAllCrews() {
        return crewRepository.findAll();
    }

    // TODO: add the crew id to corresponding pirate
    public void addNewCrew(Crew crew) {
        Optional<Crew> crewOptional = crewRepository
                .findCrewByName(crew.getName());
        if(crewOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        crewRepository.save(crew);
    }

    // TODO: remove crewId from all pirates in this crew
    public void deleteCrew(Long crewId) {
        boolean exists = crewRepository.existsById(crewId);
        if(!exists) {
            throw new IllegalTransactionStateException(
                "crew with id " + crewId + " does not exist");
        }
        crewRepository.deleteById(crewId);
    }

    @Transactional
    public void updateCrew(Long crewId, String name) {
        Crew crew = crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalTransactionStateException(
                    "crew with id " + crewId + " does not exist"));
        
        if(name != null && name.length() > 0 && !Objects.equals(crew.getName(), name)) {
            Optional<Crew> crewOptional = crewRepository
                    .findCrewByName(name);
            if(crewOptional.isPresent()) {
                throw new IllegalStateException("name taken");
            }
            crew.setName(name);
        }
    }
}
