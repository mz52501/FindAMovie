package FindAMovie.DTO;


public class ConnectMovieActorDTO {
    private MovieDTO[] movies;
    private PersonDTO[] actors;

    public ConnectMovieActorDTO() {}

    public ConnectMovieActorDTO(MovieDTO[] movies, PersonDTO[] actors) {
        this.movies = movies;
        this.actors = actors;
    }

    public MovieDTO[] getMovies() {
        return movies;
    }

    public void setMovies(MovieDTO[] movies) {
        this.movies = movies;
    }

    public PersonDTO[] getActors() {
        return actors;
    }

    public void setActors(PersonDTO[] actors) {
        this.actors = actors;
    }
}
