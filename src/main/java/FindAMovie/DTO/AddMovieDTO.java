package FindAMovie.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddMovieDTO {
    private Integer id;
    private LocalDate relDate;
    private LocalTime duration;
    private String description;
    private String title;
    private String image;

    public AddMovieDTO() {}

    public AddMovieDTO(Integer id, LocalDate relDate, LocalTime duration, String description, String title, String image) {
        this.id = id;
        this.relDate = relDate;
        this.duration = duration;
        this.description = description;
        this.title = title;
        this.image = image;
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
}
