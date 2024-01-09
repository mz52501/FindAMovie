package FindAMovie.DTO;

public class ReviewDTO {
    private Integer movieid;
    private Integer userid;
    private Integer rating;
    private String comment;
    private String username;

    public ReviewDTO() {}

    public ReviewDTO(Integer movieid, Integer userid, Integer rating, String comment, String username) {
        this.movieid = movieid;
        this.userid = userid;
        this.rating = rating;
        this.comment = comment;
        this.username = username;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
