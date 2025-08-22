package mmarini.unitn.team04_rest.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import mmarini.unitn.team04_rest.model.Championship;
import mmarini.unitn.team04_rest.model.Match;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchRepositoryCustomImpl implements MatchRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<Integer, Map<LocalDate, List<Match>>> getCalendarOfAllChampionships() {
        TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m ORDER BY m.championship.id, m.dateTime, m.homeTeam.name", Match.class);
        List<Match> matches = query.getResultList();

        return matches.stream()
                .collect(Collectors.groupingBy(
                        match -> match.getChampionship().getId(),   // use the id instead of the object
                        Collectors.groupingBy(Match::getDate)
                ));
    }

    @Override
    public Map<LocalDateTime, List<Match>> getCalendarOfChampionship(Championship championship) {
        TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m WHERE m.championship = :championship ORDER BY m.dateTime, m.homeTeam.name", Match.class);
        query.setParameter("championship", championship);
        List<Match> matches = query.getResultList();

        return matches.stream().collect(Collectors.groupingBy(Match::getDateTime));
    }

    @Override
    public Map<Integer, List<Match>> getMatchesByDate(LocalDate date) {
        TypedQuery<Match> query = entityManager.createQuery(
                "SELECT m FROM Match m " +
                        "WHERE m.dateTime >= :start AND m.dateTime < :end " +
                        "ORDER BY m.championship.id, m.dateTime, m.homeTeam.name",
                Match.class
        );

        query.setParameter("start", date.atStartOfDay());
        query.setParameter("end", date.plusDays(1).atStartOfDay());

        List<Match> matches = query.getResultList();

        return matches.stream()
                .collect(Collectors.groupingBy(
                        match -> match.getChampionship().getId()
                ));
    }

    @Override
    public List<Match> getMatchesByChampionshipAndDate(Championship championship, LocalDateTime date) {
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        TypedQuery<Match> query = entityManager.createQuery(
                "SELECT m FROM Match m " +
                        "WHERE m.championship = :championship " +
                        "AND m.dateTime >= :startOfDay AND m.dateTime < :endOfDay " +
                        "ORDER BY m.homeTeam.name", Match.class);

        query.setParameter("championship", championship);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);

        return query.getResultList();
    }


}
