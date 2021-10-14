package ex.model.service;

import ex.model.entity.Dealer;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductServiceModel  extends BaseEntityServiceModel{
    private Long id;
    private String name;
    private BigDecimal price;
    private String picture;
    private MultipartFile image;
    private String description;
    private Dealer dealer;

    public ProductServiceModel() {
    }
    @Length(min = 3,max = 30,message = "Name length must be between 3 and 30 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }
    @DecimalMin(value = "0", message = "Price can't be negative.")
    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public ProductServiceModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public ProductServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
    @Size(min = 5, message = "Must be minimum 5 characters.")
    public String getDescription() {
        return description;
    }

    public ProductServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public ProductServiceModel setDealer(Dealer dealer) {
        this.dealer = dealer;
        return this;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ProductServiceModel setId(Long id) {
        this.id = id;
        return this;
    }
}
