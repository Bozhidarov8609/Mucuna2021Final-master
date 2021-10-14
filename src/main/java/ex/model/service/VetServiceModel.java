package ex.model.service;

import ex.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class VetServiceModel extends BaseEntityServiceModel {

    private String vetName;
    private String picture;
    private MultipartFile image;
    private String service;
    private String address;
    private String phoneNumber;
    private UserEntity userEntity;

    public VetServiceModel() {
    }
    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getVetName() {
        return vetName;
    }

    public VetServiceModel setVetName(String vetName) {
        this.vetName = vetName;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public VetServiceModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }
    @Size(min = 5, message = "Must be minimum 5 characters.")
    public String getService() {
        return service;
    }

    public VetServiceModel setService(String service) {
        this.service = service;
        return this;
    }
    @Size(min = 5,message = "Address length must be min 5 characters")
    public String getAddress() {
        return address;
    }

    public VetServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }
    @Size(min = 5,message = "Number length must be min 5 characters")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public VetServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public VetServiceModel setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public VetServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
