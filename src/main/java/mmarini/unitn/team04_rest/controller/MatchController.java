package mmarini.unitn.team04_rest.controller;


import mmarini.unitn.team04_rest.model.Championship;
import mmarini.unitn.team04_rest.model.Match;
import mmarini.unitn.team04_rest.model.ResourceNotFoundException;
import mmarini.unitn.team04_rest.repository.ChampionshipRepository;
import mmarini.unitn.team04_rest.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;
    private final ChampionshipRepository championshipRepository;

    @Autowired
    public MatchController(MatchService matchService, ChampionshipRepository championshipRepository) {
        this.matchService = matchService;
        this.championshipRepository = championshipRepository;
    }

    /**
     * Get a calendar of all championships with matches grouped by date
     *
     * @return Map with Championship as key and a nested Map of dates and match lists as value
     */
    @GetMapping("/calendar")
    public ResponseEntity<Map<Integer, Map<LocalDateTime, List<Match>>>> getAllChampionshipsCalendar() {
        return ResponseEntity.ok(matchService.getAllChampionshipsCalendar());
    }

    /**
     * Get the calendar of matches for a specific championship
     *
     * @param championshipId the ID of the championship to get the calendar for
     * @return Map with dates as keys and match lists as values
     */
    @GetMapping("/calendar/championship/{championshipId}")
    public ResponseEntity<Map<LocalDateTime, List<Match>>> getChampionshipCalendar(
            @PathVariable Integer championshipId) {
        Championship championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Championship not found with id: " + championshipId));
        return ResponseEntity.ok(matchService.getChampionshipCalendar(championship));
    }

    /**
     * Get matches across all championships for a specific date
     *
     * @param date the date to get matches for (format: yyyy-MM-dd'T'HH:mm:ss)
     * @return List of matches on the specified date
     */
    @GetMapping("/by-date")
    public ResponseEntity<List<Match>> getMatchesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(matchService.getMatchesByDate(date));
    }

    /**
     * Get matches for a specific championship on a specific date
     *
     * @param championshipId the ID of the championship to get matches for
     * @param date           the date to get matches for (format: yyyy-MM-dd'T'HH:mm:ss)
     * @return List of matches for the specified championship on the specified date
     */
    @GetMapping("/championship/{championshipId}/by-date")
    public ResponseEntity<List<Match>> getMatchesByChampionshipAndDate(
            @PathVariable Integer championshipId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        Championship championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Championship not found with id: " + championshipId));
        return ResponseEntity.ok(matchService.getMatchesByChampionshipAndDate(championship, date));
    }
}
