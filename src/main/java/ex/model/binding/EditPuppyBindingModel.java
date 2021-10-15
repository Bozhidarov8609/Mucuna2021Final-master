package ex.model.binding;

import ex.model.entity.Dog;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class EditPuppyBindingModel {

private Long id;
    private String description;
    private BigDecimal price;
    private String contact;

    public EditPuppyBindingModel() {
    }
    @Size(min = 10, message = "Description must be min 10 characters")
    public String getDescription() {
        return description;
    }

    public EditPuppyBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
    @DecimalMin(value = "0",message = "Price can't be negative")
    public BigDecimal getPrice() {
        return price;
    }

    public EditPuppyBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    @Size(min = 5, message = "Contact must be min 10 characters")
    public String getContact() {
        return contact;
    }

    public EditPuppyBindingModel setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public Long getId() {
        return id;
    }

    public EditPuppyBindingModel setId(Long id) {
        this.id = id;
        return this;
    }
}
