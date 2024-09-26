package com.example.OnePieceBackend.pirate;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PirateRepository {
    
    // @Query("SELECT * from Pirate WHERE crewId = ?")
    List<Pirate> findPiratesByCrew(Long crewId);
}
