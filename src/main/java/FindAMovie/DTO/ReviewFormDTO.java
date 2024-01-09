package FindAMovie.DTO;

public class ReviewFormDTO {
    private Integer userid;
    private Integer movieid;
    private String comment;
    private Integer rating;

    public ReviewFormDTO() {}

    public ReviewFormDTO(Integer userid, Integer movieid, String comment, Integer rating) {
        this.userid = userid;
        this.movieid = movieid;
        this.comment = comment;
        this.rating = rating;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
