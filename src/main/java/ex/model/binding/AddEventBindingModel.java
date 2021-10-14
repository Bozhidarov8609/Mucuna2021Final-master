package ex.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class AddEventBindingModel {
    private String  name;
    private String place;
    private MultipartFile image;
    private BigDecimal price;

    public AddEventBindingModel() {
    }
    @Length(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public AddEventBindingModel setName(String name) {
        this.name = name;
        return this;
    }
    @Length(min = 3,max = 50,message = "Place length must be between 3 and 50 characters (inclusive 3 and 20).")
    public String getPlace() {
        return place;
    }

    public AddEventBindingModel setPlace(String place) {
        this.place = place;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public AddEventBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
    @DecimalMin(value = "0",message = "Price must be positive or 0.")
    public BigDecimal getPrice() {
        return price;
    }

    public AddEventBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
