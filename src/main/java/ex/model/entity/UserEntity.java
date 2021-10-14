package ex.model.entity;

import ex.model.entity.enums.AuthenticationProvider;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String town;
    private Role role;
    private Dealer dealer;
    private List<Dog> dog;
    private AuthenticationProvider authProvider;


    public UserEntity() {
    }
@Column(nullable = false,unique = true)
@Length(min = 3,max = 20,message = "Username length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }
@Column
@Length(min = 8,message = "Password length must be min 8 characters .")
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
@Column(nullable = false)
@Email()
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
    @Column(nullable = false)
    @Size(min = 3,message = "Town length must be min 3 characters")
    public String getTown() {
        return town;
    }

    public UserEntity setTown(String town) {
        this.town = town;
        return this;
    }



    @ManyToOne
    public Role getRole() {
        return role;
    }

    public UserEntity setRole(Role role) {
        this.role = role;
        return this;
    }
@OneToOne
@JoinColumn(name = "dealer_id",referencedColumnName = "id")
    public Dealer getDealer() {
        return dealer;
    }

    public UserEntity setDealer(Dealer dealer) {
        this.dealer = dealer;
        return this;
    }
@OneToMany(mappedBy = "userEntity",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public List<Dog> getDog() {
        return dog;
    }

    public UserEntity setDog(List<Dog> dog) {
        this.dog = dog;
        return this;
    }
@Column(name = "auth_provider")
@Enumerated(EnumType.STRING)
    public AuthenticationProvider getAuthProvider() {
        return authProvider;
    }

    public UserEntity setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
        return this;
    }
}
