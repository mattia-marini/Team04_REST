package mmarini.unitn.team04_rest.repository;

import mmarini.unitn.team04_rest.model.ChampionshipNew;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChampionshipNewRepository extends CrudRepository<ChampionshipNew, Integer> {

    @Query(value = "SELECT * FROM championship_new ORDER BY RAND() LIMIT 1", nativeQuery = true)
    ChampionshipNew findRandomNew();
}
