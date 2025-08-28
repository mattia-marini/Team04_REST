package mmarini.unitn.team04_rest.service;


import mmarini.unitn.team04_rest.model.Championship;
import mmarini.unitn.team04_rest.model.ChampionshipNew;
import mmarini.unitn.team04_rest.repository.ChampionshipNewRepository;
import mmarini.unitn.team04_rest.repository.ChampionshipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChampionshipService {
    ChampionshipNewRepository championshipNewRepository;
    ChampionshipRepository championshipRepository;

    public ChampionshipService(ChampionshipNewRepository championshipNewRepository, ChampionshipRepository championshipRepository) {
        this.championshipNewRepository = championshipNewRepository;
        this.championshipRepository = championshipRepository;
    }

    public ChampionshipNew findRandomChampionshipNew() {
        return championshipNewRepository.findRandomNew();
    }

    public Optional<Championship> getChampionshipById(Integer championshipId) {
        return championshipRepository.findById(championshipId);
    }


}
