package com.example.OnePieceBackend.crew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrewRepository
        extends JpaRepository<Crew, Long> {
    
    // @Query("SELECT * FROM Crew c where c.name = ?1")
    Optional<Crew> findCrewByName(String name);
}
