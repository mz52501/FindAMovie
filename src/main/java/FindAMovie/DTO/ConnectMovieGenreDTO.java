package FindAMovie.DTO;

import FindAMovie.models.Genre;

public class ConnectMovieGenreDTO {
    private MovieDTO[] movies;
    private GenreDTO[] genres;

    public ConnectMovieGenreDTO() {}

    public ConnectMovieGenreDTO(MovieDTO[] movies, GenreDTO[] genres) {
        this.movies = movies;
        this.genres = genres;
    }

    public MovieDTO[] getMovies() {
        return movies;
    }

    public void setMovies(MovieDTO[] movies) {
        this.movies = movies;
    }

    public GenreDTO[] getGenres() {
        return genres;
    }

    public void setGenres(GenreDTO[] genres) {
        this.genres = genres;
    }
}
