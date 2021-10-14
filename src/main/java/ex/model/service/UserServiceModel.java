package ex.model.service;

import ex.model.entity.Dealer;
import ex.model.entity.Role;

public class UserServiceModel extends BaseEntityServiceModel {

    private String username;
    private String password;
    private String email;
    private String town;
    private Dealer dealer;

    private Role role;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public UserServiceModel setTown(String town) {
        this.town = town;
        return this;
    }





    public Role getRole() {
        return role;
    }

    public UserServiceModel setRole(Role role) {
        this.role = role;
        return this;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public UserServiceModel setDealer(Dealer dealer) {
        this.dealer = dealer;
        return this;
    }
}
