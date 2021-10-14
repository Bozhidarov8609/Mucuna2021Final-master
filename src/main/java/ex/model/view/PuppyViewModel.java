package ex.model.view;

import ex.model.entity.Dog;

import java.math.BigDecimal;

public class PuppyViewModel {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String contact;
    private String picture;
    private Dog dog;

    public PuppyViewModel() {
    }

    public Long getId() {
        return id;
    }

    public PuppyViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PuppyViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PuppyViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PuppyViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public PuppyViewModel setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public PuppyViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public Dog getDog() {
        return dog;
    }

    public PuppyViewModel setDog(Dog dog) {
        this.dog = dog;
        return this;
    }
}
