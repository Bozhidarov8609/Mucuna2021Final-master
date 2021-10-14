package ex.model.service;

import ex.model.entity.UserEntity;
import ex.model.entity.enums.BreedNameEnum;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;

public class DogServiceModel extends BaseEntityServiceModel {

    private String name;
    private int age;
    private String sex;
    private BreedNameEnum breed;
    private MultipartFile image;
    private String picture;
    private boolean isApproved;
    private UserEntity userEntity;



    public DogServiceModel() {
    }

    @Length(min = 3,max = 20,message = "Dog's name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public DogServiceModel setName(String name) {
        this.name = name;
        return this;
    }
    @Min(0)
    public int getAge() {
        return age;
    }

    public DogServiceModel setAge(int age) {
        this.age = age;
        return this;
    }
    @Length(min = 3,max = 6,message = " length must be between 3 and 6 characters .")
    public String getSex() {
        return sex;
    }

    public DogServiceModel setSex(String sex) {
        this.sex = sex;
        return this;
    }
    @Enumerated(EnumType.STRING)
    public BreedNameEnum getBreed() {
        return breed;
    }

    public DogServiceModel setBreed(BreedNameEnum breed) {
        this.breed = breed;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public DogServiceModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public DogServiceModel setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public DogServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public DogServiceModel setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }
}
