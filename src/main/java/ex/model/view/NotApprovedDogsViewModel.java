package ex.model.view;

import ex.model.entity.enums.BreedNameEnum;

public class NotApprovedDogsViewModel {

    private String name;
    private int age;
    private String sex;
    private BreedNameEnum breed;
    private String picture;
    private Long id;

    public NotApprovedDogsViewModel() {
    }

    public String getName() {
        return name;
    }

    public NotApprovedDogsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public NotApprovedDogsViewModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public NotApprovedDogsViewModel setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public BreedNameEnum getBreed() {
        return breed;
    }

    public NotApprovedDogsViewModel setBreed(BreedNameEnum breed) {
        this.breed = breed;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public NotApprovedDogsViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NotApprovedDogsViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
