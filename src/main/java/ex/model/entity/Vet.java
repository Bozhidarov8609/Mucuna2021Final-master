package ex.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vets")
public class Vet extends BaseEntity {

    private String vetName;
    private String picture;
    private String service;
    private String address;
    private String phoneNumber;
    private UserEntity userEntity;

    public Vet() {
    }

    @Column(nullable = false)
    @Length(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getVetName() {
        return vetName;
    }

    public Vet setVetName(String vetName) {
        this.vetName = vetName;
        return this;
    }
    @Column
    @Size(min = 5, message = "Must be minimum 5 characters.")
    public String getService() {
        return service;
    }

    public Vet setService(String service) {
        this.service = service;
        return this;
    }
@Column
    public String getPicture() {
        return picture;
    }

    public Vet setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    @Column
    @Size(min = 5,message = "Address length must be min 5 characters")
    public String getAddress() {
        return address;
    }

    public Vet setAddress(String address) {
        this.address = address;
        return this;
    }
    @Column(name = "phone_number")
    @Size(min = 5,message = "Number length must be min 5 characters")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Vet setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @ManyToOne
    public UserEntity getUser() {
        return userEntity;
    }

    public Vet setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }


}
