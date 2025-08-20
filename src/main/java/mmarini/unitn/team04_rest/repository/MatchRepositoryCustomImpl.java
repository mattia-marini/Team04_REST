package mmarini.unitn.team04_rest.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import mmarini.unitn.team04_rest.model.Championship;
import mmarini.unitn.team04_rest.model.Match;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchRepositoryCustomImpl implements MatchRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<Integer, Map<LocalDateTime, List<Match>>> getCalendarOfAllChampionships() {
        TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m ORDER BY m.championship.id, m.date, m.homeTeam.name", Match.class);
        List<Match> matches = query.getResultList();

        return matches.stream()
                .collect(Collectors.groupingBy(
                        match -> match.getChampionship().getId(),   // use the id instead of the object
                        Collectors.groupingBy(Match::getDate)
                ));
    }

    @Override
    public Map<LocalDateTime, List<Match>> getCalendarOfChampionship(Championship championship) {
        TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m WHERE m.championship = :championship ORDER BY m.date, m.homeTeam.name", Match.class);
        query.setParameter("championship", championship);
        List<Match> matches = query.getResultList();

        return matches.stream().collect(Collectors.groupingBy(Match::getDate));
    }

    @Override
    public List<Match> getMatchesByDate(LocalDateTime date) {
        TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m WHERE m.date = :date ORDER BY m.championship.id, m.homeTeam.name", Match.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Match> getMatchesByChampionshipAndDate(Championship championship, LocalDateTime date) {
        TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m WHERE m.championship = :championship AND m.date = :date ORDER BY m.homeTeam.name", Match.class);
        query.setParameter("championship", championship);
        query.setParameter("date", date);
        return query.getResultList();
    }
}
