package mmarini.unitn.team04_rest.controller;

import mmarini.unitn.team04_rest.model.ChampionshipNew;
import mmarini.unitn.team04_rest.service.ChampionshipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionshipNewController {

    private final ChampionshipService championshipService;

    public ChampionshipNewController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping("/api/news")
    public ChampionshipNew getRandomNew() {
        return championshipService.findRandomChampionshipNew();
    }
}

