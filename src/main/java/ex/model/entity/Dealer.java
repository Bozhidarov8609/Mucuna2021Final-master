package ex.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "dealers")
public class Dealer extends BaseEntity {

    private String companyName;
    private String description;
    private BigDecimal earnedMoney;
    private String address;
    private String phoneNumber;
    private UserEntity userEntity;
    private Set<Product> products;

    public Dealer() {
    }
    @Column(nullable = false,unique = true)
    @Length(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getCompanyName() {
        return companyName;
    }

    public Dealer setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
@Column
@Size(min = 5, message = "Must be minimum 5 characters.")
    public String getDescription() {
        return description;
    }

    public Dealer setDescription(String description) {
        this.description = description;
        return this;
    }
@Column()

    public BigDecimal getEarnedMoney() {
        return earnedMoney;
    }

    public Dealer setEarnedMoney(BigDecimal earnedMoney) {
        this.earnedMoney = earnedMoney;
        return this;
    }
    @Column
    @Size(min = 5,message = "Address length must be min 5 characters")
    public String getAddress() {
        return address;
    }

    public Dealer setAddress(String address) {
        this.address = address;
        return this;
    }
    @Column(name = "phone_number")
    @Size(min = 5,message = "Number length must be min 5 characters")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Dealer setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }



    @OneToOne(mappedBy = "dealer")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Dealer setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
@OneToMany(mappedBy = "dealer",fetch = FetchType.EAGER)
    public Set<Product> getProducts() {
        return products;
    }

    public Dealer setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }
}
