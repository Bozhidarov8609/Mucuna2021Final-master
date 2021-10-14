package ex.model.service;

import ex.model.entity.Dog;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class PuppyServiceModel extends BaseEntityServiceModel{
    private String name;
    private String description;
    private BigDecimal price;
    private String contact;
    private String picture;
    private MultipartFile image;
    private String dog;


    public PuppyServiceModel() {
    }
    @Size(min = 3,max = 20,message = "Name length must be from 3 to 20 characters ")
    public String getName() {
        return name;
    }

    public PuppyServiceModel setName(String name) {
        this.name = name;
        return this;
    }
    @Size(min = 10, message = "Description must be min 10 characters")
    public String getDescription() {
        return description;
    }

    public PuppyServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
@DecimalMin(value = "0",message = "Price can't be negative")
    public BigDecimal getPrice() {
        return price;
    }

    public PuppyServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    @Size(min = 5, message = "Contact must be min 10 characters")
    public String getContact() {
        return contact;
    }

    public PuppyServiceModel setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public PuppyServiceModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getDog() {
        return dog;
    }

    public PuppyServiceModel setDog(String dog) {
        this.dog = dog;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public PuppyServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
