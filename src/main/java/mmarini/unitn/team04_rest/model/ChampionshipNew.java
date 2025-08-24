package mmarini.unitn.team04_rest.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Check;


@Entity
public class ChampionshipNew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 128)
    private String description;

    public ChampionshipNew(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public ChampionshipNew() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
