package ex.model.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name="puppies")
public class Puppy extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal price;
    private String contact;
    private String picture;
    private Dog dog;



    public Puppy() {
    }
@Column(nullable = false)
@Size(min = 3,max = 20,message = "Name length must be from 3 to 20 characters ")
    public String getName() {
        return name;
    }

    public Puppy setName(String name) {
        this.name = name;
        return this;
    }
    @Column(nullable = false,columnDefinition = "TEXT")
    @Size(min = 10, message = "Description must be min 10 characters")
    public String getDescription() {
        return description;
    }

    public Puppy setDescription(String description) {
        this.description = description;
        return this;
    }
    @Column(nullable = false)
    @DecimalMin(value = "0",message = "Price can't be negative")
    public BigDecimal getPrice() {
        return price;
    }

    public Puppy setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    @Column(nullable = false)
    @Size(min = 5, message = "Contact must be min 10 characters")
    public String getContact() {
        return contact;
    }

    public Puppy setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Puppy setPicture(String picture) {
        this.picture = picture;
        return this;
    }
@ManyToOne
@JoinColumn(name = "dog_id")
    public Dog getDog() {
        return dog;
    }

    public Puppy setDog(Dog dog) {
        this.dog = dog;
        return this;
    }
}
