package com.example.OnePieceBackend.pirate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "api/v1/pirate")
public class PirateController {
    private final PirateService pirateService;

    @Autowired
    public PirateController(PirateService pirateService) {
        this.pirateService = pirateService;
    }

    @GetMapping
    public List<Pirate> getPiratesByCrew(Long crewId) {
       return pirateService.getPiratesByCrew(crewId);
    }
    
    @PostMapping
    public void addNewPirate(@RequestBody Pirate pirate) {
        pirateService.addNewPirate(pirate);
    }

    @DeleteMapping(path = "{pirateId}")
    public void deletePirate(@PathVariable("pirateId") Long pirateId) {
        pirateService.deletePirate(pirateId);
    }

    @PutMapping(path = "{pirateId}")
    public void updatePirate(
            @PathVariable("pirateId") Long pirateId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String epithet,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String devilFruit) {
        pirateService.updatePirate(pirateId, name, epithet, age, devilFruit);
    }
    
}
