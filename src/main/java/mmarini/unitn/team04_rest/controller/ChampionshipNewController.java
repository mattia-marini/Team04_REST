package mmarini.unitn.team04_rest.controller;

import mmarini.unitn.team04_rest.model.ChampionshipNew;
import mmarini.unitn.team04_rest.repository.ChampionshipNewRepository;
import mmarini.unitn.team04_rest.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionshipNewController {
    private final ChampionshipNewRepository championshipNewRepository;

    @Autowired
    public ChampionshipNewController(ChampionshipNewRepository championshipNewRepository) {
        this.championshipNewRepository = championshipNewRepository;
    }

    @GetMapping("/api/news")
    public ChampionshipNew getRandomNew() {
        return championshipNewRepository.findRandomNew();
    }
}

