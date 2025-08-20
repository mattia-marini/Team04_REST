package mmarini.unitn.team04_rest.repository;


import mmarini.unitn.team04_rest.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {
}
