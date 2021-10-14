package ex.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddProductBindingModel {
    private String name;
    private BigDecimal price;
    private String picture;
    private String description;
    private MultipartFile image;

    public AddProductBindingModel() {
    }
    @Length(min = 3,max = 30,message = "Name length must be between 3 and 30 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public AddProductBindingModel setName(String name) {
        this.name = name;
        return this;
    }
    @DecimalMin(value = "0", message = "Price can't be negative.")
    public BigDecimal getPrice() {
        return price;
    }

    public AddProductBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public AddProductBindingModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }
    @Size(min = 5, message = "Must be minimum 5 characters.")
    public String getDescription() {
        return description;
    }

    public AddProductBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public AddProductBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
