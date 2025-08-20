package mmarini.unitn.team04_rest.repository;

import mmarini.unitn.team04_rest.model.Championship;
import mmarini.unitn.team04_rest.model.Match;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface MatchRepositoryCustom {

    /**
     * Extract the calendar of matches of all championships
     *
     * @return Map with Championship as key and a nested Map of dates and match lists as value
     */
    Map<Integer, Map<LocalDateTime, List<Match>>> getCalendarOfAllChampionships();

    /**
     * Extract the calendar of matches of a specified championship
     *
     * @param championship the championship to get the calendar for
     * @return Map with dates as keys and match lists as values
     */
    Map<LocalDateTime, List<Match>> getCalendarOfChampionship(Championship championship);

    /**
     * Extract the matches of all championships in the specified date
     *
     * @param date the date to get matches for
     * @return List of matches on the specified date across all championships
     */
    List<Match> getMatchesByDate(LocalDateTime date);

    /**
     * Extract the matches of a specified championship in the specified date
     *
     * @param championship the championship to get matches for
     * @param date         the date to get matches for
     * @return List of matches for the specified championship on the specified date
     */
    List<Match> getMatchesByChampionshipAndDate(Championship championship, LocalDateTime date);
}