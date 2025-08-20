package mmarini.unitn.team04_rest.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class MatchId implements Serializable {

    private String homeTeam;
    private String awayTeam;
    private Integer championship;
    private LocalDateTime date;

    // Constructors
    public MatchId() {
    }

    public MatchId(String homeTeam, String awayTeam, Integer championship, LocalDateTime date) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.championship = championship;
        this.date = date;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchId matchId = (MatchId) o;
        return Objects.equals(homeTeam, matchId.homeTeam) &&
                Objects.equals(awayTeam, matchId.awayTeam) &&
                Objects.equals(championship, matchId.championship) &&
                Objects.equals(date, matchId.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, championship, date);
    }
}