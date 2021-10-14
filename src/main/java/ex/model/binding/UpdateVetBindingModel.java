package ex.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class UpdateVetBindingModel {
    private MultipartFile image;
    private String service;
    private String address;
    private String phoneNumber;

    public UpdateVetBindingModel() {
    }

    public MultipartFile getImage() {
        return image;
    }

    public UpdateVetBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
    @Size(min = 5, message = "Must be minimum 5 characters.")
    public String getService() {
        return service;
    }

    public UpdateVetBindingModel setService(String service) {
        this.service = service;
        return this;
    }
    @Size(min = 5,message = "Address length must be min 5 characters")
    public String getAddress() {
        return address;
    }

    public UpdateVetBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }
    @Size(min = 5,message = "Number length must be min 5 characters")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UpdateVetBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
