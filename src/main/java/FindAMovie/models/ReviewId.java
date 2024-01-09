package FindAMovie.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewId implements Serializable {
    private static final long serialVersionUID = -259489419324657605L;
    @Column(name = "movieid", nullable = false)
    private Integer movieid;
    @Column(name = "userid", nullable = false)
    private Integer userid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieid, userid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReviewId entity = (ReviewId) o;
        return Objects.equals(this.movieid, entity.movieid) &&
                Objects.equals(this.userid, entity.userid);
    }
}