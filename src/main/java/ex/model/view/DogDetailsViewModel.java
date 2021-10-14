package ex.model.view;

import ex.model.entity.UserEntity;
import ex.model.entity.enums.BreedNameEnum;

public class DogDetailsViewModel {
    private String name;
    private int age;
    private String sex;
    private BreedNameEnum breed;
    private String picture;
    private UserEntity userEntity;

    public DogDetailsViewModel() {
    }

    public String getName() {
        return name;
    }

    public DogDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public DogDetailsViewModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public DogDetailsViewModel setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public BreedNameEnum getBreed() {
        return breed;
    }

    public DogDetailsViewModel setBreed(BreedNameEnum breed) {
        this.breed = breed;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public DogDetailsViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public DogDetailsViewModel setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
