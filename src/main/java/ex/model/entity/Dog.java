package ex.model.entity;

import ex.model.entity.enums.BreedNameEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table(name = "dogs")
public class Dog extends BaseEntity {

    private String name;
    private int age;
    private String sex;
    private BreedNameEnum breed;
    private String picture;
    private boolean isApproved;
    private UserEntity userEntity;
    private Set<Puppy> puppy;

    public Dog() {
    }
    @Column(nullable = false)
    @Length(min = 3,max = 20,message = "Dog's name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }
@Column
@Min(0)
    public int getAge() {
        return age;
    }

    public Dog setAge(int age) {
        this.age = age;
        return this;
    }
@Column
    public String getSex() {
        return sex;
    }

    public Dog setSex(String sex) {
        this.sex = sex;
        return this;
    }
@Column
@Enumerated(EnumType.STRING)
    public BreedNameEnum getBreed() {
        return breed;
    }

    public Dog setBreed(BreedNameEnum breed) {
        this.breed = breed;
        return this;
    }
@Column
    public String getPicture() {
        return picture;
    }

    public Dog setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public Dog setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }

    @ManyToOne()
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Dog setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
@OneToMany(mappedBy = "dog",fetch = FetchType.EAGER)
    public Set<Puppy> getAd() {
        return puppy;
    }

    public Dog setAd(Set<Puppy> puppy) {
        this.puppy = puppy;
        return this;
    }
}
