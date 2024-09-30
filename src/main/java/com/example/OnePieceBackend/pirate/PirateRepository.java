package com.example.OnePieceBackend.pirate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PirateRepository 
        extends JpaRepository<Pirate, Long> {
    
    // @Query("SELECT * from Pirate WHERE crewId = ?")
    List<Pirate> findPiratesByCrewId(Long crewId);
}
