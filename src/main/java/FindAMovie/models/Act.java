package FindAMovie.models;

import javax.persistence.*;

@Entity
@Table(name = "acts")
public class Act {
    @EmbeddedId
    private ActId id;

    @MapsId("movieid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieid", nullable = false)
    private Movie movieid;

    @MapsId("personid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personid", nullable = false)
    private Actor personid;

    @Column(name = "role", nullable = false)
    private String role;

    public Actor getPersonid() {
        return personid;
    }

    public void setPersonid(Actor personid) {
        this.personid = personid;
    }

    public Movie getMovieid() {
        return movieid;
    }

    public void setMovieid(Movie movieid) {
        this.movieid = movieid;
    }

    public ActId getId() {
        return id;
    }

    public void setId(ActId id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}