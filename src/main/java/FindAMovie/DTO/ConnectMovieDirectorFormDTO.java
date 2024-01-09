package FindAMovie.DTO;

public class ConnectMovieDirectorFormDTO {
    private Integer movieid;
    private Integer personid;

    public ConnectMovieDirectorFormDTO() {}

    public ConnectMovieDirectorFormDTO(Integer movieid, Integer personid) {
        this.movieid = movieid;
        this.personid = personid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }
}
