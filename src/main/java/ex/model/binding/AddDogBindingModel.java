package ex.model.binding;


import ex.model.entity.enums.BreedNameEnum;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;

public class AddDogBindingModel {

    private String name;
    private int age;
    private String sex;
    private BreedNameEnum breed;
    private MultipartFile image;



    public AddDogBindingModel() {
    }

    @Length(min = 3,max = 20,message = "Dog's name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public AddDogBindingModel setName(String name) {
        this.name = name;
        return this;
    }
@Min(0)
    public int getAge() {
        return age;
    }

    public AddDogBindingModel setAge(int age) {
        this.age = age;
        return this;
    }
    @Length(min = 3,max = 6,message = " length must be between 3 and 6 characters .")
    public String getSex() {
        return sex;
    }

    public AddDogBindingModel setSex(String sex) {
        this.sex = sex;
        return this;
    }
@Enumerated(EnumType.STRING)
    public BreedNameEnum getBreed() {
        return breed;
    }

    public AddDogBindingModel setBreed(BreedNameEnum breed) {
        this.breed = breed;
        return this;
    }



    public MultipartFile getImage() {
        return image;
    }

    public AddDogBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
