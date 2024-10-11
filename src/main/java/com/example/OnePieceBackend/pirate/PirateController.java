package com.example.OnePieceBackend.pirate;

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
@RequestMapping(path = "api/v1/pirate")
public class PirateController {
    private final PirateService pirateService;

    @Autowired
    public PirateController(PirateService pirateService) {
        this.pirateService = pirateService;
    }

    @GetMapping(path = "{pirateId}")
    public Optional<Pirate> getPirate(@PathVariable("pirateId") Long pirateId) {
        return pirateService.getPirate(pirateId);
    }

    @GetMapping(path = "crew/{crewId}")
    public List<Pirate> getPiratesByCrew(@PathVariable("crewId") Long crewId) {
       return pirateService.getPiratesByCrew(crewId);
    }
    
    @PostMapping
    public void addNewPirate(@RequestBody Pirate pirate) {
        pirateService.addNewPirate(pirate);
    }

    @DeleteMapping(path = "{pirateId}")
    public void deletePirate(
            @PathVariable("pirateId") Long pirateId) {
        pirateService.deletePirate(pirateId);
    }

    @PutMapping(path = "{pirateId}")
    public void updatePirate(
            @PathVariable("pirateId") Long pirateId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String epithet,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String devilFruit) {
        pirateService.updatePirate(pirateId, name, epithet, role, age, devilFruit);
    }
    
    @PutMapping(path = "{pirateId}/{crewId}")
    public void changeCrews(
            @PathVariable("pirateId") Long pirateId,
            @PathVariable("crewId") Long newCrewId
    ) {
        pirateService.changeCrews(pirateId, newCrewId);
    }
}
