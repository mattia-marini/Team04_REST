package mmarini.unitn.team04_rest.repository;

import mmarini.unitn.team04_rest.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}