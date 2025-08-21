package mmarini.unitn.team04_rest.repository;

import mmarini.unitn.team04_rest.model.Championship;
import mmarini.unitn.team04_rest.model.Match;
import mmarini.unitn.team04_rest.model.MatchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, MatchId>, MatchRepositoryCustom {

    // Find matches by date
    //List<Match> findByDateTimeOrderByChampionshipIdAscHomeTeamNameAsc(LocalDate date);

    // Find matches by championship and date
    //List<Match> findByChampionshipAndDateTimeOrderByHomeTeamNameAsc(Championship championship, LocalDate date);
}