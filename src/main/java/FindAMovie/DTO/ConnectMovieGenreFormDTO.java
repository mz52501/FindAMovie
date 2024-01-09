package FindAMovie.DTO;

public class ConnectMovieGenreFormDTO {
    private Integer movieid;
    private Integer[] genresid;

    public ConnectMovieGenreFormDTO() {}

    public ConnectMovieGenreFormDTO(Integer movieid, Integer[] genresid) {
        this.movieid = movieid;
        this.genresid = genresid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public Integer[] getGenresid() {
        return genresid;
    }

    public void setGenresid(Integer[] genresid) {
        this.genresid = genresid;
    }
}
