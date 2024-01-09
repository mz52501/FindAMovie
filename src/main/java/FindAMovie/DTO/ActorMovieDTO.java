package FindAMovie.DTO;

import java.util.Set;

public class ActorMovieDTO {
    private Integer id;
    private String name;
    private String surname;
    private String description;
    private String image;
    private MovieDTO[] array;

    public ActorMovieDTO() {}

    public ActorMovieDTO(Integer id, String name, String surname, String description, String image, MovieDTO[] array) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.image = image;
        this.array = array;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MovieDTO[] getArray() {
        return array;
    }

    public void setArray(MovieDTO[] array) {
        this.array = array;
    }
}
