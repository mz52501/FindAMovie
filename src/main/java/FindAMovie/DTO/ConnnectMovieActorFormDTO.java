package FindAMovie.DTO;

public class ConnnectMovieActorFormDTO {
    private Integer movieid;
    private Integer personid;
    private String role;

    public ConnnectMovieActorFormDTO() {}

    public ConnnectMovieActorFormDTO(Integer movieid, Integer personid, String role) {
        this.movieid = movieid;
        this.personid = personid;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
