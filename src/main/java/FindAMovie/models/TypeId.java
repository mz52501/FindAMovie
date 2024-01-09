package FindAMovie.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TypeId implements Serializable {
    private static final long serialVersionUID = -4161915787833198484L;
    @Column(name = "movieid", nullable = false)
    private Integer movieid;
    @Column(name = "genreid", nullable = false)
    private Integer genreid;

    public Integer getGenreid() {
        return genreid;
    }

    public void setGenreid(Integer genreid) {
        this.genreid = genreid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreid, movieid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TypeId entity = (TypeId) o;
        return Objects.equals(this.genreid, entity.genreid) &&
                Objects.equals(this.movieid, entity.movieid);
    }
}