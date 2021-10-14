package ex.model.service;

import ex.model.entity.UserEntity;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class DealerServiceModel extends BaseEntityServiceModel {
    private String companyName;
    private String description;
    private BigDecimal earnedMoney;
    private String address;
    private String phoneNumber;
    private UserEntity userEntity;

    public DealerServiceModel() {
    }
    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getCompanyName() {
        return companyName;
    }

    public DealerServiceModel setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
    @Size(min = 5, message = "Must be minimum 5 characters.")
    public String getDescription() {
        return description;
    }

    public DealerServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getEarnedMoney() {
        return earnedMoney;
    }

    public DealerServiceModel setEarnedMoney(BigDecimal earnedMoney) {
        this.earnedMoney = earnedMoney;
        return this;
    }
    @Size(min = 5,message = "Address length must be min 5 characters")
    public String getAddress() {
        return address;
    }

    public DealerServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }
    @Size(min = 5,message = "Number length must be min 5 characters")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DealerServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public DealerServiceModel setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
