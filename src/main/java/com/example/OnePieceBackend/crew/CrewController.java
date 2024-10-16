package com.example.OnePieceBackend.crew;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/crew")
public class CrewController {
    private final CrewService crewService;

    @Autowired
    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping(path = "{crewId}")
    public Optional<Crew> getCrew(@PathVariable("crewId") Long crewId) {
        return crewService.getCrew(crewId);
    }

    @GetMapping
    public List<Crew> getAllCrews() {
        return crewService.getAllCrews();
    }

    @PostMapping
    public void addNewCrew(@RequestBody Crew crew) {
        crewService.addNewCrew(crew);
    }

    @DeleteMapping(path = "{crewId}")
    public void deleteCrew(
            @PathVariable("crewId") Long crewId) {
        crewService.deleteCrew(crewId);
    }

    @PutMapping(path = "{crewId}")
    public void updateCrew(
            @PathVariable("crewId") Long crewId, 
            @RequestParam(required = false) String name) {
        crewService.updateCrew(crewId, name);
    }
}
