package FindAMovie.DTO;

public class ConnectMovieDirectorDTO {
    private MovieDTO[] movies;
    private PersonDTO[] directors;

    public ConnectMovieDirectorDTO() {}

    public ConnectMovieDirectorDTO(MovieDTO[] movies, PersonDTO[] directors) {
        this.movies = movies;
        this.directors = directors;
    }

    public MovieDTO[] getMovies() {
        return movies;
    }

    public void setMovies(MovieDTO[] movies) {
        this.movies = movies;
    }

    public PersonDTO[] getDirectors() {
        return directors;
    }

    public void setDirectors(PersonDTO[] directors) {
        this.directors = directors;
    }
}
