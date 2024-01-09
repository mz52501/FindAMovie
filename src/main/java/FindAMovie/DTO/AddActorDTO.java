package FindAMovie.DTO;

public class AddActorDTO {
    private Integer id;
    private String name;
    private String surname;
    private String description;
    private String image;

    public AddActorDTO() {}

    public AddActorDTO(Integer id, String name, String surname, String description, String image) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.image = image;
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
}
