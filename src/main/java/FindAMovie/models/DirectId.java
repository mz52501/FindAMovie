package FindAMovie.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DirectId implements Serializable {
    private static final long serialVersionUID = 3740243572236364909L;
    @Column(name = "movieid", nullable = false)
    private Integer movieid;
    @Column(name = "personid", nullable = false)
    private Integer personid;

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieid, personid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DirectId entity = (DirectId) o;
        return Objects.equals(this.movieid, entity.movieid) &&
                Objects.equals(this.personid, entity.personid);
    }
}