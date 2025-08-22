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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
    public ResponseEntity<Map<Integer, Map<LocalDate, List<Match>>>> getAllChampionshipsCalendar() {
        return ResponseEntity.ok(matchService.getAllChampionshipsCalendar());
    }

    /**
     * Get matches for a specific date, grouped by championship ID
     *
     * @param date the date to filter matches (format: yyyy-MM-dd)
     * @return Map with Championship ID as key and list of matches as value
     */
    @GetMapping("/calendar/{date}")
    public ResponseEntity<Map<Integer, List<Match>>> getMatchesByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(matchService.getMatchesByDate(date));
    }


    /**
     * Get matches for a specific date, grouped by championship ID
     *
     * @param date the date to filter matches (format: yyyy-MM-dd)
     * @return Map with Championship ID as key and list of matches as value
     */
    @GetMapping("/calendar/results/{date}")
    public ResponseEntity<Map<Integer, Map<Integer, Integer>>> getMatchesResultsByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Map<Integer, List<Match>> matches = matchService.getMatchesByDate(date);
        Map<Integer, Map<Integer, Integer>> results = matches.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, // championshipId
                e -> e.getValue().stream().collect(Collectors.toMap(Match::getId,   // key = matchId
                        m -> {
                            Random random = new Random();
                            int p1 = m.getHomeTeam().getStrength() + random.nextInt(-8, 9);
                            int p2 = m.getAwayTeam().getStrength() + random.nextInt(-8, 9);
                            if (p1 < p2 - 3) return 1; // -3 is to make draw more likely
                            else if (p2 < p1 - 3) return 2;
                            else return 0;
                        }))));

        return ResponseEntity.ok(results);
    }

    /**
     * Get the calendar of matches for a specific championship
     *
     * @param championshipId the ID of the championship to get the calendar for
     * @return Map with dates as keys and match lists as values
     */
    @GetMapping("/calendar/championship/{championshipId}")
    public ResponseEntity<Map<LocalDateTime, List<Match>>> getChampionshipCalendar(@PathVariable Integer championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow(() -> new ResourceNotFoundException("Championship not found with id: " + championshipId));
        return ResponseEntity.ok(matchService.getChampionshipCalendar(championship));
    }

    /**
     * Get matches for a specific championship on a specific date
     *
     * @param championshipId the ID of the championship to get matches for
     * @param date           the date to get matches for (format: yyyy-MM-dd'T'HH:mm:ss)
     * @return List of matches for the specified championship on the specified date
     */
    @GetMapping("/championship/{championshipId}/by-date")
    public ResponseEntity<List<Match>> getMatchesByChampionshipAndDate(@PathVariable Integer championshipId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow(() -> new ResourceNotFoundException("Championship not found with id: " + championshipId));
        return ResponseEntity.ok(matchService.getMatchesByChampionshipAndDate(championship, date));
    }
}
