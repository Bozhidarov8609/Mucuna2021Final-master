package ex.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {

    private String  name;
    private String place;
    private String picture;
    private BigDecimal price;
    private UserEntity userEntity;

    public Event() {
    }
    @Column(nullable = false)
    @Length(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }
    @Column(nullable = false)
    @Length(min = 3,max = 100,message = "Place length must be between 3 and 100 characters (inclusive 3 and 100).")
    public String getPlace() {
        return place;
    }

    public Event setPlace(String place) {
        this.place = place;
        return this;
    }
@Column
    public String getPicture() {
        return picture;
    }

    public Event setPicture(String picture) {
        this.picture = picture;
        return this;
    }
@Column
@DecimalMin(value = "0",message = "Price must be positive or 0.")
    public BigDecimal getPrice() {
        return price;
    }

    public Event setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUser() {
        return userEntity;
    }

    public Event setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
