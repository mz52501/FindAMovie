package FindAMovie.DTO;

import FindAMovie.models.Review;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class MovieDTO implements Comparable<MovieDTO> {
    private Integer id;
    private LocalDate relDate;
    private LocalTime duration;
    private String description;
    private String title;
    private String image;
    private ReviewDTO[] array;
    private Double avgRating;

    public MovieDTO(Integer id, LocalDate relDate, LocalTime duration, String description, String title, String image, ReviewDTO[] array, Double avgRating) {
        this.id = id;
        this.relDate = relDate;
        this.duration = duration;
        this.description = description;
        this.title = title;
        this.image = image;
        this.array = array;
        this.avgRating = avgRating;
    }

    public MovieDTO() {}

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

    public ReviewDTO[] getArray() {
        return array;
    }

    public void setArray(ReviewDTO[] array) {
        this.array = array;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }


    @Override
    public int compareTo(MovieDTO o) {
        if(this.avgRating >= o.avgRating) return 1;
        else return -1;
    }
}
