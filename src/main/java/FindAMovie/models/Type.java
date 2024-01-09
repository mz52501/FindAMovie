package FindAMovie.models;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class Type {
    @EmbeddedId
    private TypeId id;

    @MapsId("movieid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieid", nullable = false)
    private Movie movieid;

    @MapsId("genreid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genreid", nullable = false)
    private Genre genreid;

    public Genre getGenreid() {
        return genreid;
    }

    public void setGenreid(Genre genreid) {
        this.genreid = genreid;
    }

    public Movie getMovieid() {
        return movieid;
    }

    public void setMovieid(Movie movieid) {
        this.movieid = movieid;
    }

    public TypeId getId() {
        return id;
    }

    public void setId(TypeId id) {
        this.id = id;
    }
}