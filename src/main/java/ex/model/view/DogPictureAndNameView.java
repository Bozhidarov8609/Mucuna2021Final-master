package ex.model.view;

public class DogPictureAndNameView {
private Long id;
private String picture;
private String name;
private String breed;
private String sex;



    public DogPictureAndNameView() {
    }

    public Long getId() {
        return id;
    }

    public DogPictureAndNameView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public DogPictureAndNameView setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getName() {
        return name;
    }

    public DogPictureAndNameView setName(String name) {
        this.name = name;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public DogPictureAndNameView setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public DogPictureAndNameView setSex(String sex) {
        this.sex = sex;
        return this;
    }


}
