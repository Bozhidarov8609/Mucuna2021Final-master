package ex.model.entity;

import ex.model.entity.enums.RoleCategoryName;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleCategoryName role;

    public Role() {
    }

    public Role(RoleCategoryName role) {
        this.role = role;
    }

    @Column(nullable = false)
@Enumerated(EnumType.STRING)
    public RoleCategoryName getRole() {
        return role;
    }

    public Role setRole(RoleCategoryName role) {
        this.role = role;
        return this;
    }
}
