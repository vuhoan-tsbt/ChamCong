package com.example.chamcong.entity;

import com.example.chamcong.entity.base.BaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "staffCode"}))
@Where(clause = "deleted = false")
@Accessors(chain = true)
public class User extends BaseUser {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private UserRole role;

    private String email;

    private String fullName;

    private String avatar;

    private String staffCode;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    private String password;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    private String address;

    private String phone;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<UserLoginHistory> userLoginHistory;



    @Override
    protected List<? extends UserRole> getRoles() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
        listAuthorities.add(new SimpleGrantedAuthority(this.role.getRole()));
        return listAuthorities;
    }
}
