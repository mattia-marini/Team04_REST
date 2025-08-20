package mmarini.unitn.team04_rest.service;

import mmarini.unitn.team04_rest.model.Championship;
import mmarini.unitn.team04_rest.model.Match;
import mmarini.unitn.team04_rest.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    /**
     * Get a calendar of all championships with matches grouped by date
     *
     * @return Map with Championship as key and a nested Map of dates and match lists as value
     */
    public Map<Integer, Map<LocalDateTime, List<Match>>> getAllChampionshipsCalendar() {
        return matchRepository.getCalendarOfAllChampionships();
    }

    /**
     * Get the calendar of matches for a specific championship
     *
     * @param championship the championship to get the calendar for
     * @return Map with dates as keys and match lists as values
     */
    public Map<LocalDateTime, List<Match>> getChampionshipCalendar(Championship championship) {
        return matchRepository.getCalendarOfChampionship(championship);
    }

    /**
     * Get matches across all championships for a specific date
     *
     * @param date the date to get matches for
     * @return List of matches on the specified date
     */
    public List<Match> getMatchesByDate(LocalDateTime date) {
        return matchRepository.getMatchesByDate(date);
    }

    /**
     * Get matches for a specific championship on a specific date
     *
     * @param championship the championship to get matches for
     * @param date         the date to get matches for
     * @return List of matches for the specified championship on the specified date
     */
    public List<Match> getMatchesByChampionshipAndDate(Championship championship, LocalDateTime date) {
        return matchRepository.getMatchesByChampionshipAndDate(championship, date);
    }
}
