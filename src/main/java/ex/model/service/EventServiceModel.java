package ex.model.service;

import ex.model.entity.UserEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class EventServiceModel extends BaseEntityServiceModel {
    private String  name;
    private String place;
    private MultipartFile image;
    private BigDecimal price;
    private String picture;
    private UserEntity userEntity;

    public EventServiceModel() {
    }
    @Length(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public EventServiceModel setName(String name) {
        this.name = name;
        return this;
    }
    @Length(min = 3,max = 100,message = "Place length must be between 3 and 50 characters (inclusive 3 and 100).")
    public String getPlace() {
        return place;
    }

    public EventServiceModel setPlace(String place) {
        this.place = place;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public EventServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
    @DecimalMin(value = "0",message = "Price must be positive or 0.")
    public BigDecimal getPrice() {
        return price;
    }

    public EventServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public EventServiceModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public EventServiceModel setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
