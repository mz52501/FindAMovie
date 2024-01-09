package FindAMovie.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class MovieActorDTO {
    private Integer id;
    private LocalDate relDate;
    private LocalTime duration;
    private Integer hours;
    private Integer minutes;
    private String description;
    private String title;
    private String image;
    private PersonDTO[] actors;
    private PersonDTO[] directors;
    private GenreDTO[] genres;
    private ReviewDTO[] reviews;

    public MovieActorDTO() {}

    public MovieActorDTO(Integer id, LocalDate relDate, LocalTime duration,Integer hours,Integer minutes, String description, String title, String image, PersonDTO[] actors, PersonDTO[] directors, GenreDTO[] genres, ReviewDTO[] reviews) {
        this.id = id;
        this.relDate = relDate;
        this.duration = duration;
        this.hours = hours;
        this.minutes = minutes;
        this.description = description;
        this.title = title;
        this.image = image;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRelDate() {
        return relDate;
    }

    public void setRelDate(LocalDate relDate) {
        this.relDate = relDate;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PersonDTO[] getActors() {
        return actors;
    }

    public void setActors(PersonDTO[] actors) {
        this.actors = actors;
    }

    public PersonDTO[] getDirectors() {
        return directors;
    }

    public void setDirectors(PersonDTO[] directors) {
        this.directors = directors;
    }

    public GenreDTO[] getGenres() {
        return genres;
    }

    public void setGenres(GenreDTO[] genres) {
        this.genres = genres;
    }

    public ReviewDTO[] getReviews() {
        return reviews;
    }

    public void setReviews(ReviewDTO[] reviews) {
        this.reviews = reviews;
    }
}
