package com.example.chamcong.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "user")
public class Manager extends User {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "manager")

    private List<Employee> employees;
    private String email;
    private String password;
}
