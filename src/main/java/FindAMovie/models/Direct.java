package FindAMovie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "direct")
public class Direct {
    @EmbeddedId
    private DirectId id;

    @MapsId("movieid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieid", nullable = false)
    private Movie movieid;

    @MapsId("personid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personid", nullable = false)
    private Director personid;

    public Director getPersonid() {
        return personid;
    }

    public void setPersonid(Director personid) {
        this.personid = personid;
    }

    public Movie getMovieid() {
        return movieid;
    }

    public void setMovieid(Movie movieid) {
        this.movieid = movieid;
    }

    public DirectId getId() {
        return id;
    }

    public void setId(DirectId id) {
        this.id = id;
    }
}