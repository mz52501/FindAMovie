package FindAMovie.DTO;

public class ReviewMovieDTO {
    private String title;
    private ReviewDTO[] array;

    public ReviewMovieDTO() {
    }

    public ReviewMovieDTO(String title, ReviewDTO[] array) {
        this.title = title;
        this.array = array;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReviewDTO[] getArray() {
        return array;
    }

    public void setArray(ReviewDTO[] array) {
        this.array = array;
    }
}
