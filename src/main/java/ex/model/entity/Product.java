package ex.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;
    private String picture;
    private String description;
    private Dealer dealer;


    public Product() {
    }
    @Column(nullable = false)
    @Length(min = 3,max = 30,message = "Name length must be between 3 and 30 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }
@Column
@DecimalMin(value = "0", message = "Price can't be negative.")
    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
@Column
    public String getPicture() {
        return picture;
    }

    public Product setPicture(String picture) {
        this.picture = picture;
        return this;
    }
    @Column(columnDefinition = "TEXT")
    @Size(min = 5, message = "Must be minimum 5 characters.")
    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "dealer_id")
    public Dealer getDealer() {
        return dealer;
    }

    public Product setDealer(Dealer dealer) {
        this.dealer = dealer;
        return this;
    }
}
