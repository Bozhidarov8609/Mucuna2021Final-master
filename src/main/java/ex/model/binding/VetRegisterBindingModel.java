package ex.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class VetRegisterBindingModel {
    private String vetName;
    private MultipartFile image;
    private String service;
    private String address;
    private String phoneNumber;


    public VetRegisterBindingModel() {
    }
    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getVetName() {
        return vetName;
    }

    public VetRegisterBindingModel setVetName(String vetName) {
        this.vetName = vetName;
        return this;
    }


    @Size(min = 5, message = "Must be minimum 5 characters.")
    public String getService() {
        return service;
    }

    public VetRegisterBindingModel setService(String service) {
        this.service = service;
        return this;
    }
    @Size(min = 5,message = "Address length must be min 5 characters")
    public String getAddress() {
        return address;
    }

    public VetRegisterBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }
    @Size(min = 5,message = "Number length must be min 5 characters")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public VetRegisterBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public VetRegisterBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
